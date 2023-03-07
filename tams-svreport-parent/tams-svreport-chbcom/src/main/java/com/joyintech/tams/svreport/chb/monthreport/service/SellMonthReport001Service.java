
package com.joyintech.tams.svreport.chb.monthreport.service;


import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.monthreport.dao.SellMonthReportDetailMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.ChbMonthReportDetail;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReportDetail;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubCheck;
import com.joyintech.tams.svreport.chb.monthreport.dao.SellMonthReportMapper;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReport;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SellMonthReport001Service extends CommonExportService<SellMonthReport> {
	@Autowired
    private SellMonthReportMapper sellMonthReportMapper;
	@Autowired
	private SellMonthReportDetailMapper sellMonthReportDetailMapper;
	@Autowired
	private CheckReport001Mapper checkReport001Mapper;
	@Autowired
	private ChbCheckRuleoutMapper chbCheckRuleoutMapper;
	@Autowired
	private BeforeSubCheck beforeSubCheck;
	@Autowired
    private MonthReportRegistBatchService monthReportRegistBatchService;
	@Autowired
	private CheckReport001Mapper chbReportCheckMapper;

	/**
	 * 短sql最大一次最大插入条数
	 */
	@Value("${application.reportSelectOrDeleteCountForShenFen}")
	public  int reportSelectOrDeleteCountForShenFen;
	private static final String IS_CHECK_SUCESS="01";
    private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 查询页面数据
	 *
	 * @param pageParam
	 *            查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	public Page<SellMonthReport> searchPage(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		//将发送状态改为空
		pageParam.put("sendStatus", "");
		int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
		Page<SellMonthReport> page = queryForPage(
				pagenum,legth,()->sellMonthReportMapper.searchPage(pageParam));
		return page;
	}

	/**
	 * 处理查询前的参数 <功能详细描述>
	 *
	 * @param pageParam
	 * @see
	 * @since 1.0
	 */
	public void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<SellMonthReport> astValueList = JsonUtils.json2List(keyList,
						SellMonthReport.class);
				pageParam.put("reportList", astValueList);
			} catch (IOException e) {
	            logger.error(e.getMessage(),e);
			}
		} else {
			// 查询参数条件
			// 数据状态
			String dataStatus = (String) pageParam.get("dataStatus");
			// 报送状态
			String sendStatus = (String) pageParam.get("sendStatus");
			// 反馈状态
			String submitFeedbackStatus = (String) pageParam.get("submitFeedbackStatus");


			// 将数据状态当做多选下拉框处理
			if (StringUtil.isNotBlank(dataStatus)) {
				pageParam.put("dataStatusList", StringUtil.splitToList(GlobalConstant.COMMA, dataStatus));
			}
			// 将报送状态当做多选下拉框处理
			if (StringUtil.isNotBlank(sendStatus)) {
				pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
			}
			// 将反馈状态当做多选下拉框处理
			if (StringUtil.isNotBlank(submitFeedbackStatus)) {
				pageParam.put("submitFeedbackStatusList",
						StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
			}

		}
	}

	public ResponseEntity<byte[]> download(String pageId, Map<String, Object> selectParam,String datas) throws IOException {
		// 导出文件
		List<File> sourceFiles = new ArrayList<>();
		List<SellMonthReport> dataList=new ArrayList<SellMonthReport>();
		dataList= com.joyintech.framework.util.JsonUtils.json2List(datas,SellMonthReport.class);
		SellMonthReport report = dataList.get(0);
		String reportuuid = report.getReportuuid();
		String reportName = report.getReportName();
		String termid = report.getTermid();
		Map<String,Object> data  = new HashMap<String,Object>();
		List<SellMonthReportDetail> tempList=new ArrayList<SellMonthReportDetail>();
		List<SellMonthReport> dateTempList=new ArrayList<SellMonthReport>();
		//查询时间 机构与机构名
		dateTempList = sellMonthReportMapper.selectByPrimaryKey(reportuuid);
		//获取机构名称及银行登记编码
		for(SellMonthReport Monthreport : dateTempList){
			/*String orgCode = CommonUtil.getOrgCode();*/
			String issuePartyId =CommonUtil.selectBankCode();
			String  issuePartyName=CommonUtil.selectBankCodeName();
			/*String issuePartyId = sellMonthReportMapper.selectIssuePartyId(orgCode);
			String issuePartyName = sellMonthReportMapper.selectOrgName(orgCode);*/
			String termidstr=Monthreport.getTermid();
			Monthreport.setIssuePartyName(issuePartyName);
			Monthreport.setIssuePartyId(issuePartyId);

			if(Monthreport.getTermid().contains("-")){
				int index = termidstr.indexOf("-");
				String termidstr1= termidstr.substring(0,index);
				String termidstr2=termidstr.substring(index+1);
				Monthreport.setTermid(termidstr1+termidstr2);
			}
		}
		String fileName = reportName +".xls";
		//查询当前期数的reportuuid主键
		List<SellMonthReport> reportuuidList = sellMonthReportDetailMapper.selectReportuuid(termid);
		if(reportuuidList.size() != 0){
		for(SellMonthReport monthReport :reportuuidList) {
				//分投资者分类
				if(monthReport.getReporttype().equals("1_1")) {
					tempList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
					if (!tempList.isEmpty()) {
						for (SellMonthReportDetail detail : tempList) {
							BigDecimal oneW = new BigDecimal("10000");
							BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
							BigDecimal valueAmountTotal = detail.getValueAmountTotal();
							BigDecimal balanceAmt = detail.getBalanceAmt();
							if(saleAmountTotal != null) {
								detail.setSaleAmountTotal(saleAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}else{
								detail.setSaleAmountTotal(BigDecimal.ZERO);
							}
							if(valueAmountTotal != null) {
								detail.setValueAmountTotal(valueAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}
							else{
								detail.setValueAmountTotal(BigDecimal.ZERO);
							}
							if(balanceAmt != null) {
								detail.setBalanceAmt(balanceAmt.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}
							else{
								detail.setBalanceAmt(BigDecimal.ZERO);
							}
						}
					}
					data.put("dataList1", tempList);
					data.put("data", dateTempList);
				}
				//分地区
			if(monthReport.getReporttype().equals("1_2")) {
				tempList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
				if (!tempList.isEmpty()) {
					for (SellMonthReportDetail detail : tempList) {
						BigDecimal oneW = new BigDecimal("10000");
						BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
						BigDecimal valueAmountTotal = detail.getValueAmountTotal();
						BigDecimal balanceAmt = detail.getBalanceAmt();
						if(saleAmountTotal != null) {
							detail.setSaleAmountTotal(saleAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
						}else{
							detail.setSaleAmountTotal(BigDecimal.ZERO);
						}
						if(valueAmountTotal != null) {
							detail.setValueAmountTotal(valueAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
						}
						else{
							detail.setValueAmountTotal(BigDecimal.ZERO);
						}
						if(balanceAmt != null) {
							detail.setBalanceAmt(balanceAmt.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
						}
						else{
							detail.setBalanceAmt(BigDecimal.ZERO);
						}
					}
				}
				data.put("dataList2", tempList);
				data.put("data", dateTempList);
			}
				//分销售渠道
			if(monthReport.getReporttype().equals("1_3")) {
				tempList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
				if (!tempList.isEmpty()) {
					for (SellMonthReportDetail detail : tempList) {
						BigDecimal oneW = new BigDecimal("10000");
						BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
						BigDecimal valueAmountTotal = detail.getValueAmountTotal();
						BigDecimal balanceAmt = detail.getBalanceAmt();
						if(saleAmountTotal != null) {
							detail.setSaleAmountTotal(saleAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
						}else{
							detail.setSaleAmountTotal(BigDecimal.ZERO);
						}
						if(valueAmountTotal != null) {
							detail.setValueAmountTotal(valueAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
						}
						else{
							detail.setValueAmountTotal(BigDecimal.ZERO);
						}
						if(balanceAmt != null) {
							detail.setBalanceAmt(balanceAmt.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
						}
						else{
							detail.setBalanceAmt(BigDecimal.ZERO);
						}
					}
				}
				data.put("dataList3", tempList);
				data.put("data", dateTempList);
			}
		}
		}
		 else{
			data.put("data", dateTempList);
			data.put("dataList1", tempList);
			data.put("dataList2", tempList);
			data.put("dataList3", tempList);
		}
		File depositfile = TisUtil.createFileWithScale(data, "pbc/" +"sellMonthReport_export", fileName,9);
		sourceFiles.add(depositfile);
		File outFile = null;
		outFile = sourceFiles.get(0);
		return ExportUtil.export(outFile.getName(), outFile, true);
	}


	public String priview(String pageid,Map<String, Object> selectParam,String datas) throws Exception {
		//模板
		String temp = "";
		//月报主表UUID
		String reportuuid = selectParam.get("reportuuid").toString();
		//期数
		String termid = selectParam.get("termid").toString();
		//报表类型
		String reporttype = selectParam.get("reporttype").toString();
		Map<String,Object> data  = new HashMap<String,Object>();
		List<SellMonthReportDetail> tempList=new ArrayList<SellMonthReportDetail>();
		List<SellMonthReport> dateTempList=new ArrayList<SellMonthReport>();
		//查询时间
		dateTempList = sellMonthReportMapper.selectByPrimaryKey(reportuuid);
		//获取机构名称及银行登记编码
		for(SellMonthReport report : dateTempList){
//			String orgCode = CommonUtil.getOrgCode();
			String issuePartyId =CommonUtil.selectBankCode();
			String  issuePartyName=CommonUtil.selectBankCodeName();
			/*String issuePartyId = sellMonthReportMapper.selectIssuePartyId(orgCode);
			String issuePartyName = sellMonthReportMapper.selectOrgName(orgCode);*/
			String termidstr=report.getTermid();
			report.setIssuePartyName(issuePartyName);
			report.setIssuePartyId(issuePartyId);
			if(report.getTermid().contains("-")){
				int index = termidstr.indexOf("-");
				String termidstr1= termidstr.substring(0,index);
				String termidstr2=termidstr.substring(index+1);
				report.setTermid(termidstr1+termidstr2);
			}

		}
		String fileName = "销售月度统计表"+ DateUtil.format(new Date(), "yyyy-MM-dd")+".xls";
				//分投资者分类
				if(reporttype.equals("1_1")) {
					temp = "sellMonthReport1_1";
					tempList = sellMonthReportDetailMapper.selectReportDetail(reportuuid);
					if (!tempList.isEmpty()) {
						for (SellMonthReportDetail detail : tempList) {
							BigDecimal oneW = new BigDecimal("10000");
							BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
							BigDecimal valueAmountTotal = detail.getValueAmountTotal();
							BigDecimal balanceAmt = detail.getBalanceAmt();
							if(saleAmountTotal != null) {
								detail.setSaleAmountTotal(saleAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}else{
								detail.setSaleAmountTotal(BigDecimal.ZERO);
							}
							if(valueAmountTotal != null) {
								detail.setValueAmountTotal(valueAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}
							else{
								detail.setValueAmountTotal(BigDecimal.ZERO);
							}
							if(balanceAmt != null) {
								detail.setBalanceAmt(balanceAmt.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}
							else{
								detail.setBalanceAmt(BigDecimal.ZERO);
							}
						}
					}
					data.put("dataList1", tempList);
					data.put("data", dateTempList);
				}
				//分地区
				if(reporttype.equals("1_2")) {
					temp = "sellMonthReport1_2";
					tempList = sellMonthReportDetailMapper.selectReportDetail(reportuuid);
					if (!tempList.isEmpty()) {
						for (SellMonthReportDetail detail : tempList) {
							BigDecimal oneW = new BigDecimal("10000");
							BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
							BigDecimal valueAmountTotal = detail.getValueAmountTotal();
							BigDecimal balanceAmt = detail.getBalanceAmt();
							if(saleAmountTotal != null) {
								detail.setSaleAmountTotal(saleAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}else{
								detail.setSaleAmountTotal(BigDecimal.ZERO);
							}
							if(valueAmountTotal != null) {
								detail.setValueAmountTotal(valueAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}
							else{
								detail.setValueAmountTotal(BigDecimal.ZERO);
							}
							if(balanceAmt != null) {
								detail.setBalanceAmt(balanceAmt.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}
							else{
								detail.setBalanceAmt(BigDecimal.ZERO);
							}
						}
					}
					data.put("dataList2", tempList);
					data.put("data", dateTempList);
				}
				//分销售渠道
				if(reporttype.equals("1_3")) {
					temp = "sellMonthReport1_3";
					tempList = sellMonthReportDetailMapper.selectReportDetail(reportuuid);
					if (!tempList.isEmpty()) {
						for (SellMonthReportDetail detail : tempList) {
							BigDecimal oneW = new BigDecimal("10000");
							BigDecimal saleAmountTotal = detail.getSaleAmountTotal();
							BigDecimal valueAmountTotal = detail.getValueAmountTotal();
							BigDecimal balanceAmt = detail.getBalanceAmt();
							if(saleAmountTotal != null) {
								detail.setSaleAmountTotal(saleAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}else{
								detail.setSaleAmountTotal(BigDecimal.ZERO);
							}
							if(valueAmountTotal != null) {
								detail.setValueAmountTotal(valueAmountTotal.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}
							else{
								detail.setValueAmountTotal(BigDecimal.ZERO);
							}
							if(balanceAmt != null) {
								detail.setBalanceAmt(balanceAmt.divide(oneW,6, BigDecimal.ROUND_HALF_UP));
							}
							else{
								detail.setBalanceAmt(BigDecimal.ZERO);
							}
						}
					}
					data.put("dataList3", tempList);
					data.put("data", dateTempList);
				}
		File depositfile = TisUtil.createFile(data, "pbc/" +temp, fileName);
		return this.priView(depositfile, true,reporttype);
	}
	//预览导出
	public String priView( File downloadFile, boolean deleteAfterExport,String reporttype){
		String rs =  XlsxToHtml.readExcelToHtmlThree(downloadFile,true);
		if (deleteAfterExport) {
			downloadFile.delete();
		}
		return rs;
	}

	/**
	 * 手工处理
	 * <一句话功能简述> <功能详细描述>
	 * @see
	 * @since 1.0
	 */
	public void updateSumStaus(Map<String, Object> map) {
		dealSearchParam(map);
		List<SellMonthReport> chbRegistList  = new ArrayList<SellMonthReport>();
		if(map.get("flag") != null && "01".equals(map.get("flag"))){
			//按条件查询全部
			chbRegistList = sellMonthReportMapper.searchPage(map);
		}else{
			chbRegistList = (List<SellMonthReport>)map.get("reportList");
		}
		Iterator<SellMonthReport> it=chbRegistList.iterator();
		while(it.hasNext()){
			SellMonthReport reportList =  it.next();
			if(!reportList.getSendStatus().equals("01")){
				it.remove();
			}
		}
		if (!chbRegistList.isEmpty() && chbRegistList.size() != 0) {
			for (int i = 0 ; i < chbRegistList.size() ; i++) {
				String termid = chbRegistList.get(i).getTermid();
				sellMonthReportMapper.updateInterfSubmitAndSeqBatch(termid);
			}
			}
	}

	/**
	 * 已手工处理的数据将报送状态改为未报送
	 *
	 * @see
	 * @since 1.0
	 */
	public void cancelMannul(Map<String, Object> map) {
		dealSearchParam(map);
		List<SellMonthReport> list = new ArrayList<SellMonthReport>();
		if(map.get("flag") != null && "01".equals(map.get("flag"))){
			list = sellMonthReportMapper.searchPage(map);
		}else{
			list = (List<SellMonthReport>) map.get("reportList");
		}
		if (!list.isEmpty() && list.size() != 0) {
			for (int i = 0 ; i < list.size() ; i++) {
				String termid = list.get(i).getTermid();
				sellMonthReportMapper.cancelMannultRegist(termid);
			}
		}
	}


	/**
	 * 数据检查
	 * @param checkKeys 检查数据的关键字
	 * @return 检查结果
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> checkData(String checkKeys){
		List<String> keys = com.joyintech.framework.util.StringUtil.splitToList(GlobalConstant.COMMA, checkKeys);
		keys.remove(com.joyintech.framework.util.StringUtil.EMPTY);
		String termid = keys.get(0);
		String investorReportUUID = null;
		String areaReportUUID = null;
		String channelReportUUID = null;
		//以投资者类型
		List<SellMonthReportDetail> investorTypeList=new ArrayList<SellMonthReportDetail>();
		//以地区类型
		List<SellMonthReportDetail> areaList=new ArrayList<SellMonthReportDetail>();
		//以销售渠道类型
		List<SellMonthReportDetail> channelList=new ArrayList<SellMonthReportDetail>();
		//查询当前期数的reportuuid主键
		List<SellMonthReport> reportuuidList = sellMonthReportDetailMapper.selectReportuuid(termid);
		//获取当前期数的销售月报各个详细信息
		for(SellMonthReport monthReport :reportuuidList) {
			String reporttype = monthReport.getReporttype();
			//以投资者类型
			if(StringUtil.equals("1_1",reporttype)) {
				investorTypeList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
				if(StringUtil.isNotEmpty(investorTypeList) && investorTypeList.size() >0){
					investorReportUUID = investorTypeList.get(0).getReportuuid();
				}
			}
			//以地区类型
			if(StringUtil.equals("1_2",reporttype)) {
				areaList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
				if(StringUtil.isNotEmpty(areaList) && areaList.size() > 0){
					areaReportUUID = areaList.get(0).getReportuuid();
				}
			}
			//以销售渠道类型
			if(StringUtil.equals("1_3",reporttype)) {
				channelList = sellMonthReportDetailMapper.selectReportDetail(monthReport.getReportuuid());
				if(StringUtil.isNotEmpty(channelList) && channelList.size() > 0){
					channelReportUUID = channelList.get(0).getReportuuid();
				}
			}
		}
		List<ExcelValidDto> checkResult = new ArrayList<>();
		this.checkInvestorType(checkResult,investorTypeList,termid);
		this.checkArea(checkResult,areaList,termid);
		this.checkChannel(checkResult,channelList,termid);
		this.checkAllList(checkResult,investorTypeList,areaList,channelList,termid);
		if(StringUtil.isNotEmpty(checkResult) && checkResult.size() > 0){
			for(ExcelValidDto dto : checkResult){
				if(StringUtil.isEmpty(dto.getErrorInfo())) {
					dto.setCheckResult(DictCodeListExt2.检查结果_检查不通过.getCodeName());
				}else{
					dto.setCheckResult(DictCodeListExt2.检查结果_检查通过.getCodeName());
				}
			}
		}
		//数据检查结果入库
		if(StringUtil.isNotEmpty(checkResult) && checkResult.size() > 0){
			List<CheckReport001Dto> resultList = new ArrayList<>();
			for(ExcelValidDto dto : checkResult){
				CheckReport001Dto chbReportCheck = new CheckReport001Dto();
				chbReportCheck.setCheckSeqno(IDGenerator.generate());
				chbReportCheck.setRepCode(DictCodeListExt2.报表名称_理财业务销售数据月度统计表登记.getValue());
				chbReportCheck.setRepName(DictCodeListExt2.报表名称_理财业务销售数据月度统计表登记.getCodeName());
				chbReportCheck.setValidKey(dto.getValidKey());
				if(StringUtil.equals("存在警示信息项",dto.getCheckResult())) {
					chbReportCheck.setCheckResult("03");
				}else if(StringUtil.equals("检查不通过",dto.getCheckResult())){
					chbReportCheck.setCheckResult("02");
				}else{
					chbReportCheck.setCheckResult("01");
				}
				chbReportCheck.setErrorInfo(dto.getErrorInfo());
				chbReportCheck.setWarnInfo(dto.getWarnInfo());
				chbReportCheck.setDataTime(new Date());
				chbReportCheck.setOrgCode(CommonUtil.getOrgCode());
				chbReportCheck.setCreateDept(CommonUtil.getDeptCode());
				chbReportCheck.setUpdateUser(CommonUtil.getUserName());
				chbReportCheck.setCreateUser(CommonUtil.getUserName());
				chbReportCheck.setUpdateTime(new Date());
				chbReportCheck.setCreateTime(new Date());
				if(dto.getValidKey().contains("1_1")){
					chbReportCheck.setRefseqno(investorReportUUID);
				}else if(dto.getValidKey().contains("1_2")){
					chbReportCheck.setRefseqno(areaReportUUID);
				}else if(dto.getValidKey().contains("1_3")){
					chbReportCheck.setRefseqno(channelReportUUID);
				}
				resultList.add(chbReportCheck);
			}
			chbReportCheckMapper.batchInsert(resultList);
		}
		return checkResult;
	}


	/**
	 * 以投资者类型check
	 *
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> checkInvestorType(List<ExcelValidDto> checkResult,List<SellMonthReportDetail> investorTypeList,String termid) {
		if(!StringUtil.isNotEmpty(investorTypeList) || investorTypeList.size() == 0){
			checkResult.add(new ExcelValidDto("期数:" + termid , String.valueOf(0), "当前期数，以投资者类型的销售月报为空！",null));
			return null;
		}
		Map<String,SellMonthReportDetail> checkMap = new HashMap<String,SellMonthReportDetail>();
		for(SellMonthReportDetail dto : investorTypeList){
			String key = dto.getPrdRegistCode() + "|" + dto.getHolderType();
			if(!StringUtil.isNotEmpty(checkMap.get(key))){
				checkMap.put(key,dto);
			}else{
				checkResult.add(new ExcelValidDto("报表类型：" +dto.getReporttype() + "期数:" + termid + "产品登记编码：" + dto.getPrdRegistCode(), String.valueOf(0), "存在投资者类型重复",null));
			}
		}
		return checkResult;
	}
	/**
	 * 以地区类型check
	 *
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> checkArea(List<ExcelValidDto> checkResult,List<SellMonthReportDetail> areaList,String termid) {
		if(!StringUtil.isNotEmpty(areaList) || areaList.size() == 0){
			checkResult.add(new ExcelValidDto("期数:" + termid , String.valueOf(0), "当前期数，以地区类型的销售月报为空！",null));
			return null;
		}
		Map<String,SellMonthReportDetail> checkMap = new HashMap<String,SellMonthReportDetail>();
		for(SellMonthReportDetail dto : areaList){
			String key = dto.getPrdRegistCode() + "|" + dto.getAreaCode();
			if(!StringUtil.isNotEmpty(checkMap.get(key))){
				checkMap.put(key,dto);
			}else{
				checkResult.add(new ExcelValidDto("报表类型：" +dto.getReporttype() + "期数:" + termid + "产品登记编码：" + dto.getPrdRegistCode(), String.valueOf(0), "存在地区类型重复",null));
			}
		}
		return checkResult;
	}
	/**
	 * 以销售渠道类型check
	 *
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> checkChannel(List<ExcelValidDto> checkResult,List<SellMonthReportDetail> channelList,String termid) {
		if(!StringUtil.isNotEmpty(channelList) || channelList.size() == 0){
			checkResult.add(new ExcelValidDto("期数:" + termid , String.valueOf(0), "当前期数，以销售渠道类型的销售月报为空！",null));
			return null;
		}
		Map<String,SellMonthReportDetail> checkMap = new HashMap<String,SellMonthReportDetail>();
		for(SellMonthReportDetail dto : channelList){
			String key = dto.getPrdRegistCode() + "|" + dto.getInstitutionCode();
			if(!StringUtil.isNotEmpty(checkMap.get(key))){
				checkMap.put(key,dto);
			}else{
				checkResult.add(new ExcelValidDto("报表类型：" +dto.getReporttype() + "期数:" + termid + "产品登记编码：" + dto.getPrdRegistCode(), String.valueOf(0), "存在销售机构代码重复",null));
			}
		}
		return checkResult;
	}

	/**
	 * 表间check
	 *
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> checkAllList(List<ExcelValidDto> checkResult,List<SellMonthReportDetail> investorTypeList,List<SellMonthReportDetail> areaList,List<SellMonthReportDetail> channelList,String termid) {
		if(!StringUtil.isNotEmpty(investorTypeList) || !StringUtil.isNotEmpty(areaList) || !StringUtil.isNotEmpty(channelList)
		|| investorTypeList.size() == 0 || areaList.size() == 0 || channelList.size() == 0){
			return null;
		}
		//总销售金额
		BigDecimal investorTypeSaleAmountTotal = this.SaleAmountTotal(investorTypeList);
		BigDecimal areaSaleAmountTotal = this.SaleAmountTotal(areaList);
		BigDecimal channelSaleAmountTotal = this.SaleAmountTotal(channelList);
		//净销售金额
		BigDecimal investorTypeValueAmountTotal = this.ValueAmountTotal(investorTypeList);
		BigDecimal areaValueAmountTotal = this.ValueAmountTotal(areaList);
		BigDecimal channelValueAmountTotal = this.ValueAmountTotal(channelList);
		//持有余额
		BigDecimal investorTypeBalanceAmt = this.BalanceAmt(investorTypeList);
		BigDecimal areaBalanceAmt = this.BalanceAmt(areaList);
		BigDecimal channelBalanceAmt = this.BalanceAmt(channelList);
		//取得非保本理财业务月度统计表
   		String termId = termid.replace("-","");
		int year =  Integer.parseInt(termId.substring(0,4));
		int month =  Integer.parseInt(termId.substring(4));
		String dateEnd = this.getLastDayOfMonth1(year,month);
		String dateStart = this.getFirstDayOfMonth1(year,month);
		ChbMonthReportDetail monthReport = sellMonthReportMapper.getMonthReportDetailSum(termId,dateStart,dateEnd);
		BigDecimal prodamount = null;
		BigDecimal prodnetamount = null;
		BigDecimal balance = null;
		if(StringUtil.isNotEmpty(monthReport)) {
			 prodamount = monthReport.getProdamount();
			 prodnetamount = monthReport.getProdnetamount();
			 balance = monthReport.getBalance();
		}else{
			ExcelValidDto checkWarn = new ExcelValidDto("期数:" + termid, String.valueOf(0), null,"当前期数非保本理财业务月度统计表（理财子公司）的产品情况不存在！");
			checkWarn.setCheckResult("存在警示信息项");
			checkResult.add(checkWarn);
		}

		//check总销售金额
		if(StringUtil.isNotEmpty(investorTypeSaleAmountTotal) && StringUtil.isNotEmpty(areaSaleAmountTotal) && StringUtil.isNotEmpty(channelSaleAmountTotal)){
			if(investorTypeSaleAmountTotal.compareTo(areaSaleAmountTotal) != 0 || investorTypeSaleAmountTotal.compareTo(channelSaleAmountTotal) != 0 || areaSaleAmountTotal.compareTo(channelSaleAmountTotal) != 0){
				checkResult.add(new ExcelValidDto("期数:" + termid, String.valueOf(0), "表间之间的总销售金额列总和存在不同，请核对！",null));
			}
		}
		//check净销售金额
		if(StringUtil.isNotEmpty(investorTypeValueAmountTotal) && StringUtil.isNotEmpty(areaValueAmountTotal) && StringUtil.isNotEmpty(channelValueAmountTotal)){
			if(investorTypeValueAmountTotal.compareTo(areaValueAmountTotal) != 0 || investorTypeValueAmountTotal.compareTo(channelValueAmountTotal) != 0 || areaValueAmountTotal.compareTo(channelValueAmountTotal) != 0){
				checkResult.add(new ExcelValidDto("期数:" + termid, String.valueOf(0), "表间之间的净销售金额列总和存在不同，请核对！",null));
			}
		}
		//check持有余额
		if(StringUtil.isNotEmpty(investorTypeBalanceAmt) && StringUtil.isNotEmpty(areaBalanceAmt) && StringUtil.isNotEmpty(channelBalanceAmt)){
			if(investorTypeBalanceAmt.compareTo(areaBalanceAmt) != 0 || investorTypeBalanceAmt.compareTo(channelBalanceAmt) != 0 || areaBalanceAmt.compareTo(channelBalanceAmt) != 0){
				checkResult.add(new ExcelValidDto("期数:" + termid, String.valueOf(0), "表间之间的持有余额列总和存在不同，请核对！",null));
			}
		}

		//check 理财子公司月报与销售月报的值
		if(StringUtil.isNotEmpty(prodamount) && StringUtil.isNotEmpty(prodnetamount) && StringUtil.isNotEmpty(balance)){
			if(prodamount.compareTo(investorTypeSaleAmountTotal) != 0 || prodamount.compareTo(areaSaleAmountTotal) != 0 || prodamount.compareTo(channelSaleAmountTotal) != 0){
				checkResult.add(new ExcelValidDto("期数:" + termid, String.valueOf(0), "总销售金额列总和与理财子非保本理财业务月度统计表的本期总募集金额合计值存在不同，请核对！",null));
			}
			if(prodnetamount.compareTo(investorTypeValueAmountTotal) != 0 || prodnetamount.compareTo(areaValueAmountTotal) != 0 || prodnetamount.compareTo(channelValueAmountTotal) != 0){
				checkResult.add(new ExcelValidDto("期数:" + termid, String.valueOf(0), "净销售金额列总和与理财子非保本理财业务月度统计表的本期净募集金额合计值存在不同，请核对！",null));
			}
			if(balance.compareTo(investorTypeBalanceAmt) != 0 || balance.compareTo(areaBalanceAmt) != 0 || balance.compareTo(channelBalanceAmt) != 0){
				checkResult.add(new ExcelValidDto("期数:" + termid, String.valueOf(0), "持有余额列总和与理财子非保本理财业务月度统计表的期末余额合计值存在不同，请核对！",null));
			}
		}
		return checkResult;
	}

	/**
	 * 取得总销售金额
	 * @param List
	 * @return
	 */
	public BigDecimal SaleAmountTotal(List<SellMonthReportDetail> List) {
		BigDecimal money = new BigDecimal(BigInteger.ZERO);
		if(StringUtil.isNotEmpty(List)){
			for(SellMonthReportDetail dto : List){
				BigDecimal SaleAmountTotal = dto.getSaleAmountTotal();
				if(!StringUtil.isNotEmpty(SaleAmountTotal)){
					SaleAmountTotal = new BigDecimal(BigInteger.ZERO);
				}
				money = SaleAmountTotal.add(money);
			}
		}
		return money;
	}

	/**
	 * 取得净销售金额
	 * @param List
	 * @return
	 */
	public BigDecimal ValueAmountTotal(List<SellMonthReportDetail> List) {
		BigDecimal money = new BigDecimal(BigInteger.ZERO);
		if(StringUtil.isNotEmpty(List)){
			for(SellMonthReportDetail dto : List){
				BigDecimal ValueAmountTotal = dto.getValueAmountTotal();
				if(!StringUtil.isNotEmpty(ValueAmountTotal)){
					ValueAmountTotal = new BigDecimal(BigInteger.ZERO);
				}
				money = ValueAmountTotal.add(money);
			}
		}
		return money;
	}

	/**
	 * 取得持有余额
	 * @param List
	 * @return
	 */
	public BigDecimal BalanceAmt(List<SellMonthReportDetail> List) {
		BigDecimal money = new BigDecimal(BigInteger.ZERO);
		if(StringUtil.isNotEmpty(List)){
			for(SellMonthReportDetail dto : List){
				BigDecimal BalanceAmt = dto.getBalanceAmt();
				if(!StringUtil.isNotEmpty(BalanceAmt)){
					BalanceAmt = new BigDecimal(BigInteger.ZERO);
				}
				money = BalanceAmt.add(money);
			}
		}
		return money;
	}

	/**
	 * 获取指定年月的最后一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth1(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month); //设置当前月的上一个月
		int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
		cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}

	/**
	 * 获取指定年月的第一天
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth1(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1); //设置当前月
		int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
		cal.set(Calendar.DAY_OF_MONTH, lastDay); //上月的第一天减去1就是当月的最后一天
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}
}
