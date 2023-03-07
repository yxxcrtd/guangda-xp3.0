package com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.service;

import com.google.common.collect.Lists;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.classic.data.service.PbcClassicsDataFileHelper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSubVo;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dao.ZG04PbcProdRaiseSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * ZG04产品资金募集信息service
 * @author liuyu
 *
 */
@Service(value = "zG04PbcPrdCashRaiseSubService")
@Transactional
public class ZG04PbcPrdCashRaiseSubService001 extends CommonExportService<ZG04PbcProdRaiseSub> {
	/**
	 * ZG04产品资金募集信息mapper
	 */
	@Autowired
	private ZG04PbcProdRaiseSubMapper zg04pbcProdRaiseSubMapper;
	/**
	 * 日志.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private DecimalFormat df = new DecimalFormat("0.00000");

    private DecimalFormat dt = new DecimalFormat("0.00");

	@Autowired
	private PbcDataCheckUtil pbcDataCheckUtil;
	/**
	 * 数据检查
	 * @param checkKeys 检查数据的关键字
	 * @return 检查结果
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> checkData(Map<String, Object> param){

		List<ZG04PbcProdRaiseSub> equityInfoRegistList = new ArrayList<ZG04PbcProdRaiseSub>();
		param.put("submitStatus", DictCodeListExt2.报送状态_未报送.getValue());
		param.put("deptCode", CommonUtil.getDeptCode());
		List<String> keyList = new ArrayList<String>();
		if(param.get("checkKeys")!=null){
//			keyList= StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
			param.put("raisingDate", param.get("checkKeys"));
		}
		Map<String, PbcPrdBaseinfoSub> repmap = new HashMap<String, PbcPrdBaseinfoSub>();
		deaSearchParam(param);
		equityInfoRegistList= zg04pbcProdRaiseSubMapper.searchPageForOnePrd(param);
		String uuid = IDGenerator.generate(36);
		//公共校验方法
		if(StringUtil.isEmptyList(equityInfoRegistList)){
			ZG04PbcProdRaiseSub zg04PbcProdRaiseSub = new ZG04PbcProdRaiseSub();
			zg04PbcProdRaiseSub.setRaisingDate((String) param.get("raisingDate"));
			equityInfoRegistList.add(zg04PbcProdRaiseSub);
		}

//		List<List<ZG04PbcProdRaiseSub>> equityInfoRegistLists = Lists.partition(equityInfoRegistList, 500);

		int groupSize = 750;
		Map<String, List<ZG04PbcProdRaiseSub>> listGroupByProdid = equityInfoRegistList.stream().collect(Collectors.groupingBy(e -> String.valueOf(e.getPbcProdId())));
		List<List<ZG04PbcProdRaiseSub>> dtoLists = new ArrayList<>();
		List<ZG04PbcProdRaiseSub> tempList = new ArrayList<>();
		int listCount = 0;
		for (Map.Entry<String, List<ZG04PbcProdRaiseSub>> entry : listGroupByProdid.entrySet()) {
			//不同 产品代码 批次合并
			if (tempList.size() + entry.getValue().size() > groupSize) {
				List<ZG04PbcProdRaiseSub> list = new ArrayList<>(tempList);
				dtoLists.add(list);
				tempList.clear();
			}
			tempList.addAll(entry.getValue());
			listCount += entry.getValue().size();
		}
		// 剩余数据
		if (CollectionUtils.isNotEmpty(tempList)){
			dtoLists.add(tempList);
		}

		List<ExcelValidDto> vailDateFromPage = new ArrayList<>();
		String currentDateTime = DateUtil.getCurrentDateTime();
		int size = 0;
//		for (List<ZG04PbcProdRaiseSub> equityInfoRegistListPart : equityInfoRegistLists) {
		for (int i = 0; i < dtoLists.size(); i++) {
			vailDateFromPage.addAll(pbcDataCheckUtil.getVailDateFromPage(dtoLists.get(i), uuid, DictCodeListExt2.采集平台资管产品存续期募集信息.getValue()
					, true, DictCodeListExt2.数据校验规则大类_报送前检查.getValue(), ZG04PbcProdRaiseSub.class));
			size+=dtoLists.get(i).size();
			logger.debug("=====================ZG04 数据检查已经校验了{}条数据",size);
		}
		logger.debug("========================ZG04 数据检查总共需要{} s",DateUtil.getBetweenDate(currentDateTime,DateUtil.getCurrentDateTime(), DateUtil.DurationType.秒));
		return  vailDateFromPage;
//		return  pbcDataCheckUtil.getVailDateFromPage(equityInfoRegistList, uuid, DictCodeListExt2.采集平台资管产品存续期募集信息.getValue()
//					, true, DictCodeListExt2.数据校验规则大类_报送前检查.getValue(), ZG04PbcProdRaiseSub.class);
	}

	/**
	 * 页面查询
	 * 
	 * @param searchParam
	 * @return
	 */
	public Page<ZG04PbcProdRaiseSub> searchPage(Map<String, Object> searchParam) {
		deaSearchParam(searchParam);
		int pagenum = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_PAGE), 1);
        int length = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
		Page<ZG04PbcProdRaiseSub> page = queryForPage(pagenum,length,()->zg04pbcProdRaiseSubMapper.searchPageForOnePrd(searchParam));
		dealResult(page.getRoot());
		return page;
	}
	
	/**
	 * 查询结果处理
	 * @param resultList
	 */
	private void dealResult(List<ZG04PbcProdRaiseSub> resultList) {
		for(ZG04PbcProdRaiseSub result : resultList){
            if (StringUtil.isNotEmpty(result.getBuyPlace())){
                result.setBuyPlace( result.getBuyPlace()+" "+ SysDataUtil.getNameFromDataDict(DictCode.行政区划_省,result.getBuyPlace()));
            }
  			if(StringUtil.isNotEmpty(result.getCustomerType1())) {

				result.setCustomerType1(result.getCustomerType1() + " " + SysDataUtil.getNameFromDataDict(DictCode.人行增信机构类型, result.getCustomerType1()));
			}

		}
	}

	/**
	 * 查询结果处理
	 * @param resultList
	 */
	private void dealResult01(List<ZG04PbcProdRaiseSub> resultList) {
		for(ZG04PbcProdRaiseSub result : resultList){


		}
	}


	/**
	 * 查询参数处理
	 * @param searchParam
	 */
	private void deaSearchParam(Map<String, Object> searchParam) {
		if (searchParam.containsKey("flag") && StringUtil.isNotEmpty(searchParam.get("flag"))) {
			searchParam.put("raisingDateList",
					StringUtil.splitToList(",", (String) searchParam.get("raisingDate")));
			searchParam.remove("raisingDate");
		}
		searchParam.put("sendStatusList",
	            StringUtil.splitToList(",",(String) searchParam.get("sendStatus")));
        if (StringUtil.isNotEmpty(searchParam.get("prodId"))){
            searchParam.put("prodIdList",
                    StringUtil.splitToList(",",(String) searchParam.get("prodId")));
        } else {
            searchParam.put("prodIdList",null);
        }
        if (StringUtil.isNotEmpty(searchParam.get("pbcProdId") )){
            searchParam.put("pbcProdIdList",
                    StringUtil.splitToList(",",(String) searchParam.get("pbcProdId")));
        } else {
            searchParam.put("pbcProdIdList",null);
        }
	}

	/**
	 * 查询要导出的数据
	 */
	/**
	 * 查询要导出的数据
	 */
	public List<ZG04PbcProdRaiseSub> setDataList(Map<String, Object> param) {
		List<ZG04PbcProdRaiseSub> resultList = new ArrayList<>();
		deaSearchParam(param);
		resultList = zg04pbcProdRaiseSubMapper.searchPageForOnePrd(param);
		dealResult01(resultList);
		return resultList;
	}
	

	/**
	 * 手工处理
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public void doManul(Map<String,Object> map) {
		logger.debug("手工处理开始:------------------------------"+new Date());
		deaSearchParam(map);
		zg04pbcProdRaiseSubMapper.doManul(map);
		List<ZG04PbcProdRaiseSub> subList = zg04pbcProdRaiseSubMapper.searchPageForOnePrd(map);
		Map<String,List<String>> datuuidMap = subList.stream().map(ZG04PbcProdRaiseSub::getDatuuid).filter(StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
		List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());
		// 更新DAT报送状态
		int grps = datuuidList.size() / PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		int idx = 0;
		for (int i = 0; i < grps; i++) {
			datuuidList.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
			if (CollectionUtils.isNotEmpty(datuuidList)){
				zg04pbcProdRaiseSubMapper.doDatReportStatus(datuuidList);
			}
			idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		}
		List<String> list2 = datuuidList.subList(idx, datuuidList.size());
		if (list2.size() > 0) {
			zg04pbcProdRaiseSubMapper.doDatReportStatus(list2);
		}
		logger.debug("手工处理结束:------------------------------"+new Date());

	}
	/**
	 * 取消手工处理
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public String doCancelManul(Map<String,Object> map) {
		logger.debug("取消手工处理开始:------------------------------"+new Date());
			// 查询方式
		deaSearchParam(map);
		zg04pbcProdRaiseSubMapper.cancelManul(map);
		List<ZG04PbcProdRaiseSub> subList = zg04pbcProdRaiseSubMapper.searchPageForOnePrd(map);
		Map<String,List<String>> datuuidMap = subList.stream().map(ZG04PbcProdRaiseSub::getDatuuid).filter(StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
		List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());
		// 更新DAT报送状态
		int grps = datuuidList.size() / PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		int idx = 0;
		for (int i = 0; i < grps; i++) {
			datuuidList.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
			if (CollectionUtils.isNotEmpty(datuuidList)){
				zg04pbcProdRaiseSubMapper.cancelDatReportStatus(datuuidList);
			}
			idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		}
		List<String> list2 = datuuidList.subList(idx, datuuidList.size());
		if (list2.size() > 0) {
			zg04pbcProdRaiseSubMapper.cancelDatReportStatus(list2);
		}
		logger.debug("取消手工处理结束:------------------------------"+new Date());
		return "success";
	}


	/**
	 * 查询结果处理
	 * @param resultList
	 */
	private void undealResult(List<ZG04PbcProdRaiseSub> resultList) {
		for(ZG04PbcProdRaiseSub result : resultList){
			if (StringUtil.isNotEmpty(result.getBuyPlace())){
				result.setBuyPlace(result.getBuyPlace().split(" ")[0]);
			}
			if (StringUtil.isNotEmpty(result.getCustomerType1())){
				result.setCustomerType1(result.getCustomerType1().split(" ")[0]);
			}
		}
	}
	
	 //处理导出人行excel的数据，金额保留两位小数，份额保留5位小数
    public void dealCashRaiseDateListForExport(PbcInitProdRaisingSubVo detail) {
        // 人民币金额
        if (detail.getRmbje() != null && BigDecimal.ZERO.compareTo(detail.getRmbje()) != 0) {
            detail.setRmbjeToString(dt.format(detail.getRmbje()));
        }
        //转人民币金额
        if (detail.getZrmb() != null && BigDecimal.ZERO.compareTo(detail.getZrmb()) != 0) {
        	detail.setZrmbToString(dt.format(detail.getZrmb()));
        }
        //金额
        if (detail.getJe() != null && BigDecimal.ZERO.compareTo(detail.getJe()) != 0) {
        	detail.setJeToString(dt.format(detail.getJe()));
        }
        // 人民币份额
        if (detail.getRmbfe() != null && BigDecimal.ZERO.compareTo(detail.getRmbfe()) != 0) {
			detail.setRmbfeToString(detail.getRmbfe()==null?null:Long.toString(detail.getRmbfe().longValue()));
        }
        //份额
        if (detail.getFe() != null && BigDecimal.ZERO.compareTo(detail.getFe()) != 0) {
			detail.setFeToString(detail.getFe()==null?null:Long.toString(detail.getFe().longValue()));
        }
    }

	/**
	 * 人行产品代码下拉框
	 */
	public List<SelectOptionDto> getPbcProdId(Map<String, Object> map) {
		List<SelectOptionDto> dtoList = new ArrayList<SelectOptionDto>();
		dtoList = zg04pbcProdRaiseSubMapper.getPbcProdId(map);
		return dtoList;
	}

	/**
	 * 产品代码下拉框
	 */
	public List<SelectOptionDto> getProdId(Map<String, Object> map) {
		List<SelectOptionDto> dtoList = new ArrayList<SelectOptionDto>();
		dtoList = zg04pbcProdRaiseSubMapper.getProdId(map);
		return dtoList;
	}
}
