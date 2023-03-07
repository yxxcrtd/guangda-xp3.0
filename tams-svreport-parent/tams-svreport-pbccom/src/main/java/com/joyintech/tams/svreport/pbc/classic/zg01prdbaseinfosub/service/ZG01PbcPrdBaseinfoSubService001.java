package com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.svreport.chb.constant.CheckConfConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.pbc.classic.data.service.PbcClassicsDataFileHelper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.service.PbcPrdCashRaiseSubService001;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dao.PbcInitProdRaisingSubMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSubVo;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.service.PbcPrdInitRaiseSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao.ZG01PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.datacheck.beforesub.service.PbcBeforeSubCheck;
import com.joyintech.tams.svreport.pbc.datacheck.beforesub.service.PbcBeforeSubErrorCheck;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 	产品基本信息service
 * @author liuyu
 *
 */
@Service(value = "zG01PbcPrdBaseinfoSubService")
@Transactional(rollbackFor = Exception.class)
public class ZG01PbcPrdBaseinfoSubService001 extends CommonExportService<ZG01PbcPrdBaseinfoSub> {

	@Autowired
	private ZG01PbcPrdBaseinfoSubMapper zg01PbcPrdBaseinfoSubMapper;
	@Autowired
	private PbcDataCheckUtil pbcDataCheckUtil;
    
    private DecimalFormat df = new DecimalFormat("0.00000");
    private DecimalFormat dt = new DecimalFormat("0.00");
	/**
	 * 日志.
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 查询产品资金募集信息
	 * 
	 * @param searchParam
	 * @return
	 */
	public Page<ZG01PbcPrdBaseinfoSub> searchPage(Map<String, Object> searchParam) {
		deaSearchParam(searchParam);
		searchParam.put("model_type", "01");
		int pagenum = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_PAGE), 1);
        int length = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
		Page<ZG01PbcPrdBaseinfoSub> page = queryForPage(pagenum,length,()->zg01PbcPrdBaseinfoSubMapper.searchPage(searchParam));
		dealResult(page);
		return page;
		
	}


	/**
	 * 查询结果处理
	 * @param page
	 */
	private void dealResult(Page<ZG01PbcPrdBaseinfoSub> page) {
		List<ZG01PbcPrdBaseinfoSub> resultList = page.getRoot();
		for(ZG01PbcPrdBaseinfoSub result : resultList){
			if (StringUtil.isNotEmpty(result.getInfoType())){
				result.setInfoType( SysDataUtil.getZZDictCodeAndName(DictCode.信息类型,result.getInfoType()));
			}

			if (StringUtil.isNotEmpty(result.getPbcProdType())){
				result.setPbcProdType( SysDataUtil.getZZDictCodeAndName(DictCode.人行_产品种类,result.getPbcProdType()));
			}
            if (StringUtil.isNotEmpty(result.getRaiseType())){
				result.setRaiseType( SysDataUtil.getZZDictCodeAndName(DictCode.新人行募集方式,result.getRaiseType()));
            }
            if (StringUtil.isNotEmpty(result.getManageType())){
                result.setManageType( SysDataUtil.getZZDictCodeAndName(DictCode.人行管理类型,result.getManageType()));
            }
            if (StringUtil.isNotEmpty(result.getFiduciaryDuty())){
                result.setFiduciaryDuty( SysDataUtil.getZZDictCodeAndName(DictCode.人行受托职责,result.getFiduciaryDuty()));
            }
            if (StringUtil.isNotEmpty(result.getOperateMode())){
                result.setOperateMode(SysDataUtil.getZZDictCodeAndName(DictCode.新人行产品运作模式,result.getOperateMode()));
            }
            if (StringUtil.isNotEmpty(result.getTermRightFlag())){
                result.setTermRightFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行有无,result.getTermRightFlag()));
            }
            if (StringUtil.isNotEmpty(result.getCusRedeemFlag())){
                result.setCusRedeemFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行有无,result.getCusRedeemFlag()));
            }
            if (StringUtil.isNotEmpty(result.getCreditFlag())){
                result.setCreditFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getCreditFlag()));
            }
			//增信机构类型
			if (StringUtil.isNotEmpty(result.getCreditType())){
				//多个用,切开
				String [] creditTypes=result.getCreditType().split(GlobalConstant.COMMA);
				String creditType="";
				int i=0;
				for(String single:creditTypes){
					i++;
					creditType=creditType+ SysDataUtil.getZZDictCodeAndName(DictCode.人行增信机构类型,single);
					if(i<creditTypes.length){
						creditType=creditType+ GlobalConstant.COMMA;
					}
				}

				result.setCreditType(creditType);
			}
            //产品类型
			if (StringUtil.isNotEmpty(result.getIvstorientation())){
				result.setIvstorientation( SysDataUtil.getZZDictCodeAndName(DictCode.人行投资性质,result.getIvstorientation()));
			}
            //基本信息公开标识
			if (StringUtil.isNotEmpty(result.getBaseInfoSign())){
				result.setBaseInfoSign( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getBaseInfoSign()));
			}
            if (StringUtil.isNotEmpty(result.getCreditMode())){
            	//多个用,切开
            	String [] creditModes=result.getCreditMode().split(GlobalConstant.COMMA);
            	String creditMode="";
            	int i=0;
            	for(String single:creditModes){
            		i++;
            		creditMode=creditMode+ SysDataUtil.getZZDictCodeAndName(DictCode.人行增信形式,single);
            		if(i<creditModes.length){
            			creditMode=creditMode+ GlobalConstant.COMMA;
            		}
            	}
                result.setCreditMode(creditMode);
            }
            if (StringUtil.isNotEmpty(result.getIsConstracte())){
                result.setIsConstracte( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getIsConstracte()));
            }
            if (StringUtil.isNotEmpty(result.getTransferType())){
                result.setTransferType( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getTransferType()));
            }
			//是否现金管理类
			if (StringUtil.isNotEmpty(result.getCashManaCate())){
				result.setCashManaCate( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getCashManaCate()));
			}
			//跨境理财通标识
			if (StringUtil.isNotEmpty(result.getCrossBorderFlag())){
				result.setCrossBorderFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getCrossBorderFlag()));
			}
			//以财产权信托名义开展的资金信托标识
			if (StringUtil.isNotEmpty(result.getIsPropertyFlag())){
				result.setIsPropertyFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getIsPropertyFlag()));
			}
//            if(result.getOutDepCtry()!=null){
//				//多个用,切开
//				String [] outDepCtrys=result.getOutDepCtry().split(GlobalConstant.COMMA);
//				String outDepCtry="";
//				int i=0;
//				for(String single:outDepCtrys){
//					i++;
//					single="000"+single;
//					outDepCtry=outDepCtry+ single;
//					if(i<outDepCtrys.length){
//						outDepCtry=outDepCtry+ GlobalConstant.COMMA;
//					}
//				}
//				result.setOutDepCtry(outDepCtry);
//			}
			// 境内托管机构名称
            if(result.getInDepName()!=null){
                result.setInDepName( SvrDataUtil.getZZDictCodeAndName(DictCode.境内托管机构名称, result.getInDepName()));
            }
			//募集资金币种
			if (StringUtil.isNotEmpty(result.getRaiseCcy())){
				result.setRaiseCcy( SysDataUtil.getZZDictCodeAndName(DictCode.币种_中债,result.getRaiseCcy()));
			}
			//兑付本金币种
			if (StringUtil.isNotEmpty(result.getCashPrinCcy())){
				result.setCashPrinCcy( SysDataUtil.getZZDictCodeAndName(DictCode.币种_中债,result.getCashPrinCcy()));
			}
			//兑付收益币种
			if (StringUtil.isNotEmpty(result.getCashProfCcy())){
				result.setCashProfCcy( SysDataUtil.getZZDictCodeAndName(DictCode.币种_中债,result.getCashProfCcy()));
			}
		}
	}
	
	
	private void dealResult(List<ZG01PbcPrdBaseinfoSub> listDetail) {
		for(ZG01PbcPrdBaseinfoSub result : listDetail){
			String pbcProdId=result.getPbcProdId();
			if(StringUtil.isNotEmpty(pbcProdId) && pbcProdId.substring(0,3).equals("INS")){
				result.setPbcProdId(pbcProdId.substring(3));
			}

//			if (StringUtil.isNotEmpty(result.getInfoType())){
//				result.setInfoType( SysDataUtil.getZZDictCodeAndName(DictCode.信息类型,result.getInfoType()));
//			}

//			if (StringUtil.isNotEmpty(result.getPbcProdType())){
//				result.setPbcProdType( SysDataUtil.getZZDictCodeAndName(DictCode.人行产品品种,result.getPbcProdType()));
//			}
//			if (StringUtil.isNotEmpty(result.getRaiseType())){
//				result.setRaiseType( SysDataUtil.getZZDictCodeAndName(DictCode.新人行募集方式,result.getRaiseType()));
//			}
//			if (StringUtil.isNotEmpty(result.getManageType())){
//				result.setManageType( SysDataUtil.getZZDictCodeAndName(DictCode.人行管理类型,result.getManageType()));
//			}
//			if (StringUtil.isNotEmpty(result.getFiduciaryDuty())){
//				result.setFiduciaryDuty( SysDataUtil.getZZDictCodeAndName(DictCode.人行受托职责,result.getFiduciaryDuty()));
//			}
//			if (StringUtil.isNotEmpty(result.getOperateMode())){
//				result.setOperateMode(SysDataUtil.getZZDictCodeAndName(DictCode.新人行产品运作模式,result.getOperateMode()));
//			}
//			if (StringUtil.isNotEmpty(result.getTermRightFlag())){
//				result.setTermRightFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行有无,result.getTermRightFlag()));
//			}
//			if (StringUtil.isNotEmpty(result.getCusRedeemFlag())){
//				result.setCusRedeemFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行有无,result.getCusRedeemFlag()));
//			}
//			if (StringUtil.isNotEmpty(result.getCreditFlag())){
//				result.setCreditFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getCreditFlag()));
//			}
//			//增信机构类型
//			if (StringUtil.isNotEmpty(result.getCreditType())){
//				//多个用,切开
//				String [] creditTypes=result.getCreditType().split(GlobalConstant.COMMA);
//				String creditType="";
//				int i=0;
//				for(String single:creditTypes){
//					i++;
//					creditType=creditType+ SysDataUtil.getZZDictCodeAndName(DictCode.人行增信机构类型,single);
//					if(i<creditTypes.length){
//						creditType=creditType+ GlobalConstant.COMMA;
//					}
//				}
//				result.setCreditType(creditType);
//			}
//			//产品类型
//			if (StringUtil.isNotEmpty(result.getIvstorientation())){
//				result.setIvstorientation( SysDataUtil.getZZDictCodeAndName(DictCode.人行投资性质,result.getIvstorientation()));
//			}
//			//基本信息公开标识
//			if (StringUtil.isNotEmpty(result.getBaseInfoSign())){
//				result.setBaseInfoSign( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getBaseInfoSign()));
//			}
//			if (StringUtil.isNotEmpty(result.getCreditMode())){
//				//多个用,切开
//				String [] creditModes=result.getCreditMode().split(GlobalConstant.COMMA);
//				String creditMode="";
//				int i=0;
//				for(String single:creditModes){
//					i++;
//					creditMode=creditMode+ SysDataUtil.getZZDictCodeAndName(DictCode.人行增信形式,single);
//					if(i<creditModes.length){
//						creditMode=creditMode+ GlobalConstant.COMMA;
//					}
//				}
//				result.setCreditMode(creditMode);
//			}
//			if (StringUtil.isNotEmpty(result.getIsConstracte())){
//				result.setIsConstracte( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getIsConstracte()));
//			}
//			if (StringUtil.isNotEmpty(result.getTransferType())){
//				result.setTransferType( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getTransferType()));
//			}
//			//是否现金管理类
//			if (StringUtil.isNotEmpty(result.getCashManaCate())){
//				result.setCashManaCate( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getCashManaCate()));
//			}
//			//跨境理财通标识
//			if (StringUtil.isNotEmpty(result.getCrossBorderFlag())){
//				result.setCrossBorderFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getCrossBorderFlag()));
//			}
//			//以财产权信托名义开展的资金信托标识
//			if (StringUtil.isNotEmpty(result.getIsPropertyFlag())){
//				result.setIsPropertyFlag( SysDataUtil.getZZDictCodeAndName(DictCode.人行是否,result.getIsPropertyFlag()));
//			}

			// 境内托管机构名称
//			if(result.getInDepName()!=null){
//				result.setInDepName( SvrDataUtil.getZZDictCodeAndName(DictCode.境内托管机构名称, result.getInDepName()));
//			}



		}




	}
	
	public String getRaiseCcy(String raiseCcy){
		String ccyStr="";
		if(raiseCcy.indexOf(",") > 0){
        	//获取募集币种，以，分割
        	 String[] strArr = raiseCcy.split(",");
        	 for (int i = 0; i < strArr.length; i++) {
        		 
        		 String ccy= SysDataUtil.getNameFromDataDict(DictCode.币种_中债,strArr[i]);
        		 //拼接币种，使用&分隔
        		 if(i==0){
        			 ccyStr=ccy;
        		 }else{
        			 ccyStr="&"+ccy;
        		 }
             }
        }else{
        	ccyStr= SysDataUtil.getNameFromDataDict(DictCode.币种_中债,raiseCcy);
        }
		return ccyStr;
	}
	
	public String dateString(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(date);
		return dateString;
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
		searchParam.put("statusList",
	            StringUtil.splitToList(",",(String) searchParam.get("status")));
		searchParam.put("sendStatusList",
	            StringUtil.splitToList(",",(String) searchParam.get("sendStatus")));
		searchParam.put("submitFeedbackStatusList",
	            StringUtil.splitToList(",",(String) searchParam.get("submitFeedbackStatus")));
		searchParam.put("operateModeList",
	            StringUtil.splitToList(",",(String) searchParam.get("operateMode")));
		searchParam.put("ivstorientationList",
	            StringUtil.splitToList(",",(String) searchParam.get("ivstorientation")));
		searchParam.put("infoTypeList",
				StringUtil.splitToList(",",(String) searchParam.get("infoType")));
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
	 * 数据检查
	 * @param checkKeys 检查数据的关键字
	 * @return 检查结果
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> checkData(Map<String, Object> param){

		List<ZG01PbcPrdBaseinfoSub> equityInfoRegistList = new ArrayList<ZG01PbcPrdBaseinfoSub>();
		param.put("submitStatus", DictCodeListExt2.报送状态_未报送.getValue());
		param.put("deptCode", CommonUtil.getDeptCode());
		List<String> keyList = new ArrayList<String>();
		if(param.get("checkKeys")!=null){
			keyList= StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
			param.put("registList", keyList);
		}
		Map<String, PbcPrdBaseinfoSub> repmap = new HashMap<String, PbcPrdBaseinfoSub>();
		deaSearchParam(param);
		equityInfoRegistList= zg01PbcPrdBaseinfoSubMapper.searchPage(param);
		String uuid = IDGenerator.generate(36);
		//公共校验方法

		return pbcDataCheckUtil.getVailDateFromPage(equityInfoRegistList,uuid,DictCodeListExt2.采集平台资管产品基本信息.getValue()
				,true,DictCodeListExt2.数据校验规则大类_报送前检查.getValue(),ZG01PbcPrdBaseinfoSub.class);
	}


	/**
	 * 手工处理
	 * @param
	 */
	public void doManul(Map<String,Object> map) {
		logger.debug("手工处理开始:------------------------------"+new Date());
		String flag = map.get("flag").toString();
		List<ZG01PbcPrdBaseinfoSub> infoList = new ArrayList<>();
		if("01".equals(flag)){
			// 选中的手工处理
			try {
				infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")),ZG01PbcPrdBaseinfoSub.class);
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("数据转换异常",e.getMessage(),e);
				logger.error(e.getMessage(),e);
				return ;
			}
		}else{
			Map<String,Object> pageParam = new HashMap<>();
			pageParam.putAll(map);
			deaSearchParam(pageParam);
			infoList = zg01PbcPrdBaseinfoSubMapper.searchPage(pageParam);
		}
		// His插入
		Iterator<ZG01PbcPrdBaseinfoSub> it = infoList.iterator();
		while(it.hasNext()){
			ZG01PbcPrdBaseinfoSub info = it.next();
			//if(SysDataUtil.getNameFromDataDict(DictCode.报送状态, "03").equals(info.getSendStatus())){
			if(StringUtils.equals(info.getSendStatus(),"03")){
				it.remove();
			}
		}
		if(infoList.isEmpty()){
			return ;
		}

		Map<String,List<String>> datuuidMap = infoList.stream().map(ZG01PbcPrdBaseinfoSub::getDatuuid).filter(StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
		List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());

		/* 手工处理数据批量进行 update by gaojian 2019-06-22 begin */
		int grps = infoList.size()/ PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		int idx = 0;
		for ( int i=0;i<grps;i++ ) {
			List<ZG01PbcPrdBaseinfoSub> list1 = infoList.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
			if ( list1.size()>0 ) {
				// 原始表更新状态
				zg01PbcPrdBaseinfoSubMapper.doManul(list1,map.get("submitadd")==null?null:map.get("submitadd").toString());
				//找sub表有没有已报送的数据
				List<String> regList = zg01PbcPrdBaseinfoSubMapper.selectRegistSeqnoList(list1,"02");
				if(!CollectionUtils.isEmpty(regList)){
					List<List<String>> regseqnolist = createStringList(regList,20);
					for(int j=0;j<regseqnolist.size();j++){
                         // 历史表插入
						zg01PbcPrdBaseinfoSubMapper.insertHisBatchBySendStatus(regseqnolist.get(j));
						// 原始表删除
						zg01PbcPrdBaseinfoSubMapper.deleteBatchBySendStatus(regseqnolist.get(j));
					}
				}
			}
			idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		}
		List<ZG01PbcPrdBaseinfoSub> list2 = infoList.subList(idx, infoList.size());
		if ( list2.size()>0 ) {
			// 原始表更新状态
			zg01PbcPrdBaseinfoSubMapper.doManul(list2,map.get("submitadd")==null?null:map.get("submitadd").toString());
			//找sub表有没有已报送的数据
			List<String> regList = zg01PbcPrdBaseinfoSubMapper.selectRegistSeqnoList(list2,"02");
			if(!CollectionUtils.isEmpty(regList)){
				List<List<String>> regseqnolist = createStringList(regList,20);
				for(int j=0;j<regseqnolist.size();j++){
					// 历史表插入
					zg01PbcPrdBaseinfoSubMapper.insertHisBatchBySendStatus(regseqnolist.get(j));
					// 原始表删除
					zg01PbcPrdBaseinfoSubMapper.deleteBatchBySendStatus(regseqnolist.get(j));
				}
			}

		}

		// 更新DAT报送状态
		if (CollectionUtils.isNotEmpty(datuuidList)){
			zg01PbcPrdBaseinfoSubMapper.doDatReportStatus(datuuidList);
		}

		/* 手工处理数据批量进行 update by gaojian 2019-06-22 end */
		logger.debug("手工处理结束:------------------------------"+new Date());
	}


	public  void  cancleManuelParam(List<ZG01PbcPrdBaseinfoSub> infoList){
		/* 手工处理数据批量进行 update by gaojian 2019-06-22 begin */
		int grps = infoList.size()/ PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		int idx = 0;
		for ( int i=0;i<grps;i++ ) {
			List<ZG01PbcPrdBaseinfoSub> list1 = infoList.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
			if ( list1.size()>0 ) {
				// 原始表更新状态
				zg01PbcPrdBaseinfoSubMapper.doManul(list1,null);
				//找sub表有没有已报送的数据
				List<String> regList = zg01PbcPrdBaseinfoSubMapper.selectRegistSeqnoList(list1,"02");
				if(!CollectionUtils.isEmpty(regList)){
					List<List<String>> regseqnolist = createStringList(regList,20);
					for(int j=0;j<regseqnolist.size();j++){
						// 历史表插入
						zg01PbcPrdBaseinfoSubMapper.insertHisBatchBySendStatus(regseqnolist.get(j));
						// 原始表删除
						zg01PbcPrdBaseinfoSubMapper.deleteBatchBySendStatus(regseqnolist.get(j));
					}
				}
			}
			idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		}
		List<ZG01PbcPrdBaseinfoSub> list2 = infoList.subList(idx, infoList.size());
		if ( list2.size()>0 ) {
			// 原始表更新状态
			zg01PbcPrdBaseinfoSubMapper.doManul(list2,null);
			//找sub表有没有已报送的数据
			List<String> regList = zg01PbcPrdBaseinfoSubMapper.selectRegistSeqnoList(list2,"02");
			if(!CollectionUtils.isEmpty(regList)){
				List<List<String>> regseqnolist = createStringList(regList,20);
				for(int j=0;j<regseqnolist.size();j++){
					// 历史表插入
					zg01PbcPrdBaseinfoSubMapper.insertHisBatchBySendStatus(regseqnolist.get(j));
					// 原始表删除
					zg01PbcPrdBaseinfoSubMapper.deleteBatchBySendStatus(regseqnolist.get(j));
				}
			}

		}
	}

	/**
	 * 取消手工处理
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public String doCancelManul(Map<String,Object> map) {
		logger.debug("取消手工处理开始:------------------------------"+new Date());
		String flag = map.get("flag").toString();
		List<ZG01PbcPrdBaseinfoSub> infoList = new ArrayList<>();
		if("01".equals(flag)){
			// 选中的取消手工处理
			try {
				infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")),ZG01PbcPrdBaseinfoSub.class);
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("数据转换异常",e.getMessage(),e);
				logger.error(e.getMessage(),e);
				return "fail";
			}
		}else{

			deaSearchParam(map);
			infoList = zg01PbcPrdBaseinfoSubMapper.searchPage(map);
		}
		if(infoList.isEmpty())
			return "success";
		//找sub表有没有未报送的数据
		List<String> unregList = zg01PbcPrdBaseinfoSubMapper.selectRegistSeqnoList(infoList,"01");
		if(!CollectionUtils.isEmpty(unregList)){
			return "fail";
		}

		Map<String,List<String>> datuuidMap = infoList.stream().map(ZG01PbcPrdBaseinfoSub::getDatuuid).filter(StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
		List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());

		/* 取消手工处理批量进行 update by gaojian 2019-06-22 begin */
		int grps = infoList.size()/ PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		int idx = 0;
		for ( int i=0;i<grps;i++ ) {
			List<ZG01PbcPrdBaseinfoSub> list1 = infoList.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
			if ( list1.size()>0 ) {
				// 更新状态
				zg01PbcPrdBaseinfoSubMapper.cancelManul(list1);
				// His表数据插入sub表
				zg01PbcPrdBaseinfoSubMapper.insetBatchSubFromHisByUpdateTime(list1);
				// 删除his表中移走的数据
				zg01PbcPrdBaseinfoSubMapper.deleteBatchSubFromHisByUpdateTime(list1);
			}
			idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
		}
		List<ZG01PbcPrdBaseinfoSub> list2 = infoList.subList(idx, infoList.size());
		if ( list2.size()>0 ) {
			// 更新状态
			zg01PbcPrdBaseinfoSubMapper.cancelManul(list2);
			// His表数据插入sub表
			zg01PbcPrdBaseinfoSubMapper.insetBatchSubFromHisByUpdateTime(list2);
			// 删除his表中移走的数据
			zg01PbcPrdBaseinfoSubMapper.deleteBatchSubFromHisByUpdateTime(list2);
		}

		// 更新DAT报送状态
		if (CollectionUtils.isNotEmpty(datuuidList)){
			zg01PbcPrdBaseinfoSubMapper.cancelDatReportStatus(datuuidList);
		}

		/* 取消手工处理批量进行 update by gaojian 2019-06-22 end */
		logger.debug("取消手工处理结束:------------------------------"+new Date());
		return "success";
	}

	public List<ZG01PbcPrdBaseinfoSub> searchByExcel(Map<String, Object> searchParam) {
		deaSearchParam(searchParam);
		List<ZG01PbcPrdBaseinfoSub> reportList = zg01PbcPrdBaseinfoSubMapper.searchPageExport(searchParam);
		//数据去除多法人字段
		reportList = reportList.stream().map(item -> {
			item.setBelongInstitutionCode(null);
			return item;
		}).collect(toList());
		dealResult(reportList);

		return reportList;



	}

	public List<ZG01PbcPrdBaseinfoSub> searchByUuid(List<String> list) {
		List<ZG01PbcPrdBaseinfoSub> reportList = zg01PbcPrdBaseinfoSubMapper.searchByUuid(list);
		dealResult(reportList);
		return reportList;
	}

	private List<List<String>> createStringList(List<String> targe,int size) {
		List<List<String>> listArr = new ArrayList<List<String>>();
		//获取被拆分的数组个数
		int arrSize = targe.size()%size==0?targe.size()/size:targe.size()/size+1;
		for(int i=0;i<arrSize;i++) {
			List<String> sub = new ArrayList<String>();
			//把指定索引数据放入到list中
			for(int j = i*size;j <= size*(i+1)-1;j++) {
				if(j <= targe.size()-1) {
					sub.add(targe.get(j));
				}
			}
			listArr.add(sub);
		}
		return listArr;
	}

}
