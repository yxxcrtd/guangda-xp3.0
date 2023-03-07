package com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.serivce;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dto.SalesTransactionMaintenanceRegist001Dto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dao.ZG02PbcPrdInitRaiseSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dto.ZG02PbcPrdInitRaiseSub;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.pbc.classic.data.service.PbcClassicsDataFileHelper;


/**
 * ZG02产品初始募集信息service
 * @author liuyu
 *
 */
@Service(value = "zG02PbcPrdInitRaiseSubService")
@Transactional
public class ZG02PbcPrdInitRaiseSubService001 extends CommonExportService<ZG02PbcPrdInitRaiseSub> {
	/**
	 * 产品初始募集信息mapper
	 */
	@Autowired
	private ZG02PbcPrdInitRaiseSubMapper zg02PbcPrdInitRaiseSubMapper;
	
	/**
	 * 日志.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 页面查询
	 * 
	 * @param searchParam
	 * @return
	 */
	public Page<ZG02PbcPrdInitRaiseSub> searchPage(Map<String, Object> searchParam) {
		deaSearchParam(searchParam);
		int pagenum = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_PAGE), 1);
		int length = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
		Page<ZG02PbcPrdInitRaiseSub> page = queryForPage(pagenum,length,()->zg02PbcPrdInitRaiseSubMapper.searchPage(searchParam));
		dealResult(page.getRoot());
		return page;
	}


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

		List<ZG02PbcPrdInitRaiseSub> equityInfoRegistList = new ArrayList<ZG02PbcPrdInitRaiseSub>();
		param.put("submitStatus", DictCodeListExt2.报送状态_未报送.getValue());
		param.put("deptCode", CommonUtil.getDeptCode());
		List<String> keyList = new ArrayList<String>();
		if(param.get("checkKeys")!=null){
//			keyList= StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
			param.put("pbcProdId", param.get("checkKeys"));
		}
		Map<String, PbcPrdBaseinfoSub> repmap = new HashMap<String, PbcPrdBaseinfoSub>();
		deaSearchParam(param);
		equityInfoRegistList= zg02PbcPrdInitRaiseSubMapper.searchPage(param);
		String uuid = IDGenerator.generate(36);
		//公共校验方法

		return pbcDataCheckUtil.getVailDateFromPage(equityInfoRegistList,uuid,DictCodeListExt2.采集平台资管产品初始募集信息.getValue()
				,true,DictCodeListExt2.数据校验规则大类_报送前检查.getValue(),ZG02PbcPrdInitRaiseSub.class);
	}
	
	/**
	 * 查询结果处理
	 * @param resultList
	 */
	private void dealResult(List<ZG02PbcPrdInitRaiseSub> resultList) {
		for(ZG02PbcPrdInitRaiseSub result : resultList){
//            if (StringUtil.isNotEmpty(result.getCcy())){
//                result.setCcy(result.getCcy() + StringUtil.SPACE + SysDataUtil.getNameFromDataDict(DictCode.币种,result.getCcy()));
//            }
		}
	}

	/**
	 * 查询参数处理
	 * @param searchParam
	 */
	private void deaSearchParam(Map<String, Object> searchParam) {
		if(searchParam.containsKey("keyList")){
			searchParam.put("keyList",
		            StringUtil.splitToList(",",(String) searchParam.get("keyList")));
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
	public List<ZG02PbcPrdInitRaiseSub> setDataList(Map<String, Object> param) {
		List<ZG02PbcPrdInitRaiseSub> resultList = new ArrayList<>();
		deaSearchParam(param);
		//查出选中的产品代码
		if(param.get("keyList")!=null){
			List<ZG02PbcPrdInitRaiseSub> listProdId=zg02PbcPrdInitRaiseSubMapper.searchProdId(param);
			ArrayList<Object> arrayList = new ArrayList<>();
			for(int i=0;i<listProdId.size();i++){
				if(listProdId.get(i).getPbcProdId().substring(0,3).equals("INS")){
					arrayList.add(listProdId.get(i).getPbcProdId().substring(3));
				}
				arrayList.add(listProdId.get(i).getPbcProdId());
			}
			param.put("pbcProdIdList",arrayList);
			resultList = zg02PbcPrdInitRaiseSubMapper.searchPage(param);
		}else {
			resultList = zg02PbcPrdInitRaiseSubMapper.searchPageExport(param);
		}
		dealResult(resultList);
		return resultList;
	}
	

	/**
	 * 手工处理
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public void doManul(Map<String,Object> map) {
		logger.debug("手工处理开始:------------------------------"+new Date());
		String flag = map.get("flag").toString();
		List<ZG02PbcPrdInitRaiseSub> infoList = new ArrayList<>();
		if("01".equals(flag)){
			// 选中的手工处理
			 try {
				infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")),ZG02PbcPrdInitRaiseSub.class);
			} catch (IOException e) {
				logger.debug("数据转换异常",e.getMessage(),e);
				logger.error(e.getMessage(),e);
				return ;
			}
		}else{
			    Map<String,Object> pageParam;
				pageParam = RequestUtil.getPageParam();
				deaSearchParam(pageParam);
				infoList = zg02PbcPrdInitRaiseSubMapper.searchPage(pageParam);
		}
		if(infoList.isEmpty()) {
			return;
		}
		Map<String,List<String>> datuuidMap = infoList.stream().map(ZG02PbcPrdInitRaiseSub::getDatuuid).filter(com.joyintech.background.component.common.pack.common.util.StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
		List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());
		/* 手工处理数据批量进行 update by gaojian 2019-06-22 begin */
		int grps = infoList.size()/PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		int idx = 0;
		for ( int i=0;i<grps;i++ ) {
			List<ZG02PbcPrdInitRaiseSub> list1 = infoList.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
			if ( list1.size()>0 ) {
				// 原始表更新状态
				zg02PbcPrdInitRaiseSubMapper.doManul(list1,map.get("submitadd")==null?null:map.get("submitadd").toString());
			}
			idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		}
		List<ZG02PbcPrdInitRaiseSub> list2 = infoList.subList(idx, infoList.size());
		if ( list2.size()>0 ) {
			// 原始表更新状态
			zg02PbcPrdInitRaiseSubMapper.doManul(list2,map.get("submitadd")==null?null:map.get("submitadd").toString());
		}
		// 更新DAT报送状态
		if (CollectionUtils.isNotEmpty(datuuidList)){
			zg02PbcPrdInitRaiseSubMapper.doDatReportStatus(datuuidList);
		}
		/* 手工处理数据批量进行 update by gaojian 2019-06-22 end */
		logger.debug("手工处理结束:------------------------------"+new Date());
		
	}
	/**
	 * 取消手工处理
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public String doCancelManul(Map<String,Object> map) {
		logger.debug("取消手工处理开始:------------------------------"+new Date());
		String flag = map.get("flag").toString();
		List<ZG02PbcPrdInitRaiseSub> infoList = new ArrayList<>();
		if("01".equals(flag)){
			// 选中的手工处理
			try {
				infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")),ZG02PbcPrdInitRaiseSub.class);
			} catch (IOException e) {
				logger.debug("数据转换异常",e.getMessage(),e);
				logger.error(e.getMessage(),e);
				return "fail";
			}
		}else{
			// 查询方式
			deaSearchParam(map);
			infoList = zg02PbcPrdInitRaiseSubMapper.searchPage(map);
		}
		if(infoList.isEmpty())
			return "success";
		//找sub表有没有未报送的数据
		List<String> unregList = zg02PbcPrdInitRaiseSubMapper.selectRegistSeqnoList(infoList,"01");
		if(!CollectionUtils.isEmpty(unregList)){
			return "fail";
		}

		Map<String,List<String>> datuuidMap = infoList.stream().map(ZG02PbcPrdInitRaiseSub::getDatuuid).filter(StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
		List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());

		/* 取消手工处理批量进行 update by gaojian 2019-06-22 begin */
		int grps = infoList.size()/PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		int idx = 0;
		for ( int i=0;i<grps;i++ ) {
			List<ZG02PbcPrdInitRaiseSub> list1 = infoList.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
			if ( list1.size()>0 ) {
				// 更新状态
				zg02PbcPrdInitRaiseSubMapper.cancelManul(list1);
			}
			idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		}
		List<ZG02PbcPrdInitRaiseSub> list2 = infoList.subList(idx, infoList.size());
		if ( list2.size()>0 ) {
			// 更新状态
			zg02PbcPrdInitRaiseSubMapper.cancelManul(list2);
		}
		// 更新DAT报送状态
		if (CollectionUtils.isNotEmpty(datuuidList)){
			zg02PbcPrdInitRaiseSubMapper.cancelDatReportStatus(datuuidList);
		}
		/* 取消手工处理批量进行 update by gaojian 2019-06-22 end */
		logger.debug("取消手工处理结束:------------------------------"+new Date());
		return "success";
	}

	/**
	 * 人行产品代码下拉框
	 */
	public List<SelectOptionDto> getPbcProdId(Map<String, Object> map) {
		List<SelectOptionDto> dtoList = new ArrayList<SelectOptionDto>();
		dtoList = zg02PbcPrdInitRaiseSubMapper.getPbcProdId(map);
		return dtoList;
	}

	/**
	 * 产品代码下拉框
	 */
	public List<SelectOptionDto> getProdId(Map<String, Object> map) {
		List<SelectOptionDto> dtoList = new ArrayList<SelectOptionDto>();
		dtoList = zg02PbcPrdInitRaiseSubMapper.getProdId(map);
		return dtoList;
	}
}
