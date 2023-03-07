package com.joyintech.tams.report.submitmaintenance.chbinvestholdinfo.service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSub;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoTmp;
import com.joyintech.tams.common.base.entity.ChbPrdresistCode;
import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.common.util.DateCalcUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.constant.InterfConstant;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpColumn;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.report.investormerger.dao.ChbInvestholdInfoCheckDtoMapper;
import com.joyintech.tams.report.investormerger.dto.ChbInvestholdInfoCheckDto;
import com.joyintech.tams.report.submitmaintenance.chbinvestholdinfo.dao.ChbInvestHoldInfoTmpMpper;
import com.joyintech.tams.report.submitmaintenance.equityinfo.util.EquityInfoCheckUtil;
import com.joyintech.tams.report.submitmaintenance.equityinfo.validate.EquityInfoValid;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dao.InvestorsHoldInfo001Mapper;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001Dto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001FileReadParaDto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfoDistinctService;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * 功能:投资者持有信息处理tmp数据
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrChbInvestHoldTmpDBInterf extends BaseService{

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static int addPart = 1;
	private static String lastDate= "";
	/**
	 * 未报送
	 */
	private static final String SEND_STATUS_01 = DictCodeListExt2.报送状态_未报送.getValue();
	/**
	 * 接口报送
	 */
	private static final String SEND_STATUS_02 = DictCodeListExt2.报送状态_接口报送.getValue();
	/**
	 * 手工处理
	 */
	private static final String SEND_STATUS_03 = DictCodeListExt2.报送状态_手工处理.getValue();

	@Autowired
	private ChbInvestHoldInfoTmpMpper chbInvestHoldInfoTmpMpper;
	@Autowired
	private ChbInvestholdInfoCheckDtoMapper chbInvestholdInfoCheckDtoMapper;
	/**
	 * 投资者身份标识 合并处理
	 */
	@Autowired
	private InvestorsHoldInfoDistinctService investorsHoldInfoDistinctService;
	@Autowired
	private InvestorsHoldInfo001Mapper investorsHoldInfo001Mapper;
	/**
	 * 中债基础数据mapper
	 */
	@Autowired
	private BaseMapper baseMapper;
	@Autowired
	private BatchInsertService batchInsertService;

	/**
	 * 数据批量一次查询或者删除条数
	 */
	@Value("${application.reportSelectOrDeleteCount}")
	private String reportSelectOrDeleteCount;

	/**
	 * 长sql最大一次插入或者更新条数
	 */
	@Value("${application.reportInsertLongCount}")
	public  String reportInsertLongCount;

	public void generateData(Date batchDate,String saveFlag) {
		//查询当日有无未合并持有数据接入tmp
		int num = chbInvestHoldInfoTmpMpper.checkTmpNum(DateUtil.format(batchDate));
		if(num == 0){
			return;
		}
		List<ChbInvestholdInfoTmp> allList = new ArrayList<>();
		List<ChbInvestholdInfoTmp> threeInOne = new ArrayList<>();
		List<ChbInvestholdInfoTmp> notThreeInOne = new ArrayList<>();
		//当日首次接入
		logger.info("获取三合一数据与非三合一数据开始：" + new Date());
		if(StringUtil.equals(saveFlag,"true")){
			allList = chbInvestHoldInfoTmpMpper.getThreeInOneTmpList(DateUtil.format(batchDate));
			Map<String,List<ChbInvestholdInfoTmp>> allListMap = allList.stream().collect(
					JoyinCollectors.groupingBy(item->item.getIndentifyMark()+":"+item.getProdid()+":"+item.getCcy()));
			for(List<ChbInvestholdInfoTmp> list:allListMap.values()){
				if(list.size() > 1){
					//三合一数据
					threeInOne.addAll(list);
				}else if(list.size() == 1){
					//非三合一数据
					notThreeInOne.addAll(list);
				}
			}
			logger.info("三合一持有数据：" + threeInOne.size() + "条");
			logger.info("非三合一持有数据：" + notThreeInOne.size() + "条");
		}else{//当日非首次接入
			//获取今日刚接入未合并处理数据
			List<ChbInvestholdInfoTmp> keyList = chbInvestHoldInfoTmpMpper.getDataKey(DateUtil.format(batchDate));
			//获取三合一数据
			allList = chbInvestHoldInfoTmpMpper.getThreeInOneTmpList2(DateUtil.format(batchDate),keyList);
			Map<String,List<ChbInvestholdInfoTmp>> allListMap = allList.stream().collect(
					JoyinCollectors.groupingBy(item->item.getIndentifyMark()+":"+item.getProdid()+":"+item.getCcy()));
			for(List<ChbInvestholdInfoTmp> list:allListMap.values()){
				if(list.size() > 1){
					//三合一数据
					threeInOne.addAll(list);
				}else if(list.size() == 1){
					//非三合一数据
					notThreeInOne.addAll(list);
				}
			}
			logger.info("三合一持有数据：" + threeInOne.size() + "条");
			logger.info("非三合一持有数据：" + notThreeInOne.size() + "条");
		}
		logger.info("获取三合一数据与非三合一数据结束：" + new Date());
		InvestorsHoldInfo001FileReadParaDto paraDto = this.buildFileReadParaDto(batchDate);
		// 查询数据库表字段
		List<String> listCloumn = baseMapper.getTableColumnList("CHB_INVESTHOLD_TMP");


		//对非三合一数据进行处理 -start
		// 非三合一数据转变dto结构
		List<Map<String, String>> notThreeInOneMap = SvrMaptoEntityUtil.convertEntityToMap(notThreeInOne, listCloumn);
		//处理check使用的字段与数据库中字段不匹配问题
		for(Map<String,String> investMap : notThreeInOneMap){
			//处理产品代码
			String account = investMap.get("PRODID");
			investMap.remove("PRODID");
			investMap.put("ACCOUNT",account);
			//处理产品登记编码
			String regCode = investMap.get("PRD_REGIST_CODE");
			investMap.remove("PRD_REGIST_CODE");
			investMap.put("REG_CODE",regCode);
		}
		List<InvestorsHoldInfo001Dto> listE = new ArrayList<>();
		List<InvestorsHoldInfo001Dto> listA = new ArrayList<>();
		List<ChbPrdresistCode> prodRegInfoList = this.getProdRegInfoListFromFile(notThreeInOneMap, paraDto);
		//对非三合一数据进行处理 -end
		//对三合一数据进行处理 -start
		//三合一数据转变dto结构
		List<Map<String, String>> threeInOneMap = SvrMaptoEntityUtil.convertEntityToMap(threeInOne, listCloumn);
		//处理check使用的字段与数据库中字段不匹配问题
		for(Map<String,String> investMap : threeInOneMap){
			//处理产品代码
			String account = investMap.get("PRODID");
			investMap.remove("PRODID");
			investMap.put("ACCOUNT",account);
			//处理产品登记编码
			String regCode = investMap.get("PRD_REGIST_CODE");
			investMap.remove("PRD_REGIST_CODE");
			investMap.put("REG_CODE",regCode);
		}
		List<InvestorsHoldInfo001Dto> threeInOneE = new ArrayList<>();
		List<InvestorsHoldInfo001Dto> threeInOneA = new ArrayList<>();
		// 通过文件或者产品登记编码
		List<ChbPrdresistCode> prodRegInfoList1 = this.getProdRegInfoListFromFile(threeInOneMap, paraDto);
		//对三合一数据进行处理 -end

		//异步check三合一数据与非三合一数据
		logger.info("持有数据check规范性开始："+ new Date());
		CompletableFuture<Boolean> future1 = CompletableFuture.supplyAsync(() -> {
			checkDataInfo(notThreeInOneMap,false,listE,listA,prodRegInfoList,paraDto,null,null,null);
			return true;
		});
		CompletableFuture<Boolean> future2 = CompletableFuture.supplyAsync(() -> {
			checkDataInfo(threeInOneMap,false,threeInOneE,threeInOneA,prodRegInfoList1,paraDto,null,null,null);
			return true;
		});
		try{
			future1.get();
			future2.get();
		}catch (Exception e) {
			logger.error("error:",e);
		}
		logger.info("持有数据check规范性结束："+ new Date());

		//处理三合一的待处理数据
		if(CollectionUtils.isNotEmpty(threeInOneA)){
			Iterator<InvestorsHoldInfo001Dto> iterator = threeInOneE.iterator();
			List<InvestorsHoldInfo001Dto> afterDealAList = new ArrayList<>();
			for(InvestorsHoldInfo001Dto dto :threeInOneA){
				String key = dto.getRegistBankCode() + GlobalConstant.UNDER_LINE
						+ dto.getIndentifyMark()+ GlobalConstant.UNDER_LINE
						+ dto.getPrdRegistCode() + GlobalConstant.UNDER_LINE
						+ dto.getCcy()+ GlobalConstant.UNDER_LINE
						+ com.joyintech.framework.util.DateUtil.format(dto.getHoldDate());
				while(iterator.hasNext()){
					InvestorsHoldInfo001Dto action = iterator.next();
					//如果是识别标识合并的地方有了，则剔除。
					String actionKey = action.getRegistBankCode() + GlobalConstant.UNDER_LINE
							+ action.getIndentifyMark()+ GlobalConstant.UNDER_LINE
							+ action.getPrdRegistCode() + GlobalConstant.UNDER_LINE
							+ action.getCcy()+ GlobalConstant.UNDER_LINE
							+ com.joyintech.framework.util.DateUtil.format(action.getHoldDate());
					if(StringUtil.equals(key,actionKey)){
						afterDealAList.add(action);
						iterator.remove();
					}
				}
			}
			threeInOneA.addAll(afterDealAList);
		}

		//转变三合一数据的数据结构
		// 查询数据库表字段
		logger.info("三合一持有有效数据条数："+ threeInOneE.size());
		logger.info("三合一持有待处理数据条数："+ threeInOneA.size());
		List<String> listCloumnCheck = baseMapper.getTableColumnList("CHB_INVESTHOLD_INFO_CHECK");
		List<Map<String, String>> threeInOneEMap = new ArrayList<>();
		List<Map<String, String>> threeInOneAMap = new ArrayList<>();
		List<ChbInvestholdInfoCheckDto> threeInOneEList = new ArrayList<>();
		List<ChbInvestholdInfoCheckDto> threeInOneAList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(threeInOneE)) {
			threeInOneEMap = SvrMaptoEntityUtil.convertEntityToMap(threeInOneE, listCloumnCheck);
			threeInOneEList = (List<ChbInvestholdInfoCheckDto>) convertMapToEntity(threeInOneEMap,ChbInvestholdInfoCheckDto.class);
			threeInOneEList.stream().forEach(
					item ->{
						item.setOperatestatus(DictCodeListExt2.处理状态_中债_已合并.getValue());
						item.setStatus(DictCodeListExt2.数据状态_有效.getValue());
					}
			);
		}
		if(CollectionUtils.isNotEmpty(threeInOneA)) {
			threeInOneAMap = SvrMaptoEntityUtil.convertEntityToMap(threeInOneA, listCloumnCheck);
			threeInOneAList = (List<ChbInvestholdInfoCheckDto>) convertMapToEntity(threeInOneAMap, ChbInvestholdInfoCheckDto.class);
			threeInOneAList.stream().forEach(
					item ->{
						item.setOperatestatus(DictCodeListExt2.处理状态_中债_未合并.getValue());
						item.setStatus(DictCodeListExt2.数据状态_待处理.getValue());
					}
			);
		}
		//处理三合一持有并入库
		List<ChbInvestholdInfoCheckDto> listIncheck = new ArrayList<>();
		listIncheck.addAll(threeInOneEList);
		listIncheck.addAll(threeInOneAList);
		logger.info("三合一持有数据入check表开始："+ new Date());
		threeInCheck(listIncheck);
		logger.info("三合一持有数据入check表结束："+ new Date());

		//处理三合一持有的有效数据
		//处理后有效的三合一数据map
		logger.info("合并三合一数据的有效数据与待处理数据开始："+ new Date());
		Map<String,InvestorsHoldInfo001Dto> threeInOneMapE = new HashMap<>();
		//处理有效的三合一数据
		CompletableFuture<Boolean> threeInOneEFuture = CompletableFuture.supplyAsync(() -> {
			dealThreeInOneE(threeInOneE,threeInOneMapE);
			return true;
		});

		//处理待处理的三合一数据
		Map<String,InvestorsHoldInfo001Dto> threeInOneMapA = new HashMap<>();
		//处理有效的三合一数据
		CompletableFuture<Boolean> threeInOneAFuture = CompletableFuture.supplyAsync(() -> {
			dealThreeInOneA(threeInOneA,threeInOneMapA);
			return true;
		});
		try{
			threeInOneEFuture.get();
			threeInOneAFuture.get();
		}catch (Exception e) {
			logger.error("error:",e);
		}
		logger.info("合并三合一数据的有效数据与待处理数据开始："+ new Date());


		//整合三合一持有与非三合一持有待处理数据
		CompletableFuture<Boolean> listAFuture = CompletableFuture.supplyAsync(() -> {
			for(InvestorsHoldInfo001Dto investDtoA : threeInOneMapA.values()){
				listA.add(investDtoA);
			}
			return true;
		});
		//整合三合一持有与非三合一持有有效数据
		CompletableFuture<Boolean> listEFuture = CompletableFuture.supplyAsync(() -> {
			for(InvestorsHoldInfo001Dto investDtoE : threeInOneMapE.values()){
				listE.add(investDtoE);
			}
			return true;
		});
		try{
			listAFuture.get();
			listEFuture.get();
		}catch (Exception e) {
			logger.error("error:",e);
		}


		//sub与info表的数据入库
		logger.info("开始入库info与sub并且更新tmp表数据为已合并：" + new Date());
		CompletableFuture<Boolean> future3 = CompletableFuture.supplyAsync(() -> {
			if (listE.size() > 0 || listA.size() > 0) {
				afterCheckDealNew(listE, listA, null, null, null, null, paraDto, null);
			}
			return true;
		});

		//更新tmp表中所有数据为已合并
		CompletableFuture<Boolean> future5 = CompletableFuture.supplyAsync(() -> {
			chbInvestHoldInfoTmpMpper.updateTmpOperatestatus();
			return true;
		});
		String ftpType = this.getParaVauleByparaCode(ReportGlobalConstant.SYS_INVESTHOLD_TMP_DELETE);
		if(!StringUtil.isNotEmpty(ftpType)){
			ftpType = "7";
		}
		int day = Integer.valueOf(ftpType).intValue();
		int negativeVal = (~(day - 1));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(DateUtil.format(batchDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, negativeVal);
		String strTime2 = DateUtil.format(cal.getTime());
		CompletableFuture<Boolean> future6 = CompletableFuture.supplyAsync(() -> {
			chbInvestHoldInfoTmpMpper.deleteTmpByBatchDate(strTime2);
			return true;
		});
		try{
			future3.get();
			future5.get();
			future6.get();
		}catch (Exception e) {
			logger.error("error:",e);
		}
		logger.info("接入入库info与sub并且更新tmp表数据为已合并：" + new Date());
	}

	/**
	 *三合一数据入库
	 */
	public void threeInCheck(List<ChbInvestholdInfoCheckDto> listIncheck){
		if(CollectionUtils.isNotEmpty(listIncheck)) {
			if (!StringUtil.isEmptyList(listIncheck) && listIncheck.size() > 1000) {
				chbInvestholdInfoCheckDtoMapper.truncateInvestholdTmp();
				// 先删后插
				investorsHoldInfoDistinctService.saveChbInvestholdInfoCheckTmpByCheck(listIncheck);
				investorsHoldInfoDistinctService.deleteChbInvestholdCheckByTmp();
				// 插入
				investorsHoldInfoDistinctService.saveChbInvestholdInfoCheckByMySql(listIncheck);
			} else {
				investorsHoldInfoDistinctService.deleteChbHoldInfoCheckDataByPart(listIncheck);
				investorsHoldInfoDistinctService.insertChbHoldInfoCheckDataByPart(listIncheck);
			}
		}
	}

	/**
	 * 投资者持有信息上游接口接入多线程处理，可以事先查询的内容预加载，避免循环中重复查询
	 *
	 * @param batchDate 跑批日
	 * @return InvestorsHoldInfo001FileReadParaDto
	 * @see
	 * @since 1.0
	 */
	private InvestorsHoldInfo001FileReadParaDto buildFileReadParaDto(Date batchDate) {
		InvestorsHoldInfo001FileReadParaDto paraDto = new InvestorsHoldInfo001FileReadParaDto();

		// 获取所有的登记银行编码
		Map<String, String> registMap = chbInvestHoldInfoTmpMpper.getAllRegistBankCodeSet().stream()
				.collect(JoyinCollectors.toMap(x -> x.get("REGIST_BANK_CODE"), x -> x.get("ORG_CODE")));
		paraDto.setRegistMap(registMap);

		// 获取所有的产品登记编码
		Map<String, String> prdRegistMap = chbInvestHoldInfoTmpMpper.getAllRegistProdCodeSet().stream()
				.collect(JoyinCollectors.toMap(x -> x.get("PRODID"), x -> x.get("REG_CODE")));
		paraDto.setPrdRegistMap(prdRegistMap);

		// 当天的持有数据是否存在
		boolean isTodayDataExist = true;
		try {
			if (StringUtil.isNotEmpty(batchDate)) {
				int  count = chbInvestHoldInfoTmpMpper.selectInvestHoldInfoDataCount(batchDate);
				if (count <= 0) {
					isTodayDataExist = false;
				}
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		paraDto.setTodayDataExist(isTodayDataExist);

		return paraDto;
	}


	/**
	 * 检查数据是否合法 <功能详细描述>
	 *
	 * @param mapList
	 *            待检查数据
	 * @param isExcel
	 *            区分数据源
	 * @param listE
	 *            用来存储数据（检查通过的容器）
	 * @param listA
	 *            用来存储数据（检查不通过待处理的容器）
	 * @param prodRegInfoList
	 *            用来存储数据（系统中不存在的产品登记编码信息）
	 * @param paraDto
	 *            paraDto
	 * @return 检查结果
	 * @see
	 * @since 1.0
	 */
	public List<ExcelValidDto> checkDataInfo(List<Map<String, String>> mapList, boolean isExcel,
											 List<InvestorsHoldInfo001Dto> listE, List<InvestorsHoldInfo001Dto> listA,
											 List<ChbPrdresistCode> prodRegInfoList, InvestorsHoldInfo001FileReadParaDto paraDto, String bankCodeA, String deptCode, String orgCode ) {

		logger.info("checkDataInfo处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
		//将接口接入的数据输出到日志文件中
		String checkDataLogFilePath = SvrDataUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, InterfConstant.SQLLDR_LOG_FILEPATH);
		File file = new SafelyFile(checkDataLogFilePath+File.separator+"check_data_log.txt");
		if(file.exists()){
			file = new SafelyFile(checkDataLogFilePath+File.separator+"check_data_log_"+System.currentTimeMillis()+".txt");
		}
		StringBuffer messageInfo = new StringBuffer();

		List<ExcelValidDto> result = new ArrayList<>();
		if (mapList.isEmpty()) {

		} else {
			if (isExcel) {
				//判断导入的数据是否存在重复的主键
				for(int i = 0; i < mapList.size(); i++) {
					Map<String, String> checkMapI = mapList.get(i);
					if(StringUtil.isEmpty(checkMapI.get("REGIST_BANK_CODE"))||StringUtil.isEmpty(checkMapI.get("ACCOUNT"))
							||StringUtil.isEmpty(checkMapI.get("INDENTIFY_MARK"))||StringUtil.isEmpty(checkMapI.get("HOLD_DATE"))
							||StringUtil.isEmpty(checkMapI.get("CCY"))||StringUtil.isEmpty(checkMapI.get("AGENCY_CODE"))){
						continue;
					}
					if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
						if(checkMapI.get("ORG_CODE")!=null) {
							checkMapI.put("ORG_CODE", "Z70024");
						}
					}
					for(int j = i + 1; j< mapList.size(); j++) {
						Map<String, String> checkMapJ = mapList.get(j);
						if(checkMapI.get("REGIST_BANK_CODE").equals(checkMapJ.get("REGIST_BANK_CODE")) && checkMapI.get("ACCOUNT").equals(checkMapJ.get("ACCOUNT"))
								&& checkMapI.get("INDENTIFY_MARK").equals(checkMapJ.get("INDENTIFY_MARK"))
								&& checkMapI.get("HOLD_DATE").equals(checkMapJ.get("HOLD_DATE"))
								&& checkMapI.get("CCY").equals(checkMapJ.get("CCY"))&& checkMapI.get("AGENCY_CODE").equals(checkMapJ.get("AGENCY_CODE"))) {
							result.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(j + 2),
									this.getMessage("REPORT_COMMON_FILE_E_0027", String.valueOf(i + 2)+","+String.valueOf(j + 2))));
						}
					}
				}
			}

			String bankCode;
			if (isExcel) {
				bankCode = bankCodeA;
			} else {
				bankCode = "";
			}

			boolean isNeed = true;

			// 获取所有的登记银行编码
			Map<String, String> registMap = new HashMap<String, String>();
			// 获取所有的产品登记编码
			Map<String, String> prdRegistMap = new HashMap<String, String>();
			if (paraDto != null && paraDto.getRegistMap() != null) {
				registMap = paraDto.getRegistMap();
			} else {
				registMap = investorsHoldInfo001Mapper.getAllRegistBankCodeSet().stream()
						.collect(JoyinCollectors.toMap(x -> x.get("REGIST_BANK_CODE"), x -> x.get("ORG_CODE")));
			}
			if (paraDto != null && paraDto.getPrdRegistMap() != null) {
				prdRegistMap = paraDto.getPrdRegistMap();
			} else {
				prdRegistMap = investorsHoldInfo001Mapper.getAllRegistProdCodeSet().stream()
						.collect(JoyinCollectors.toMap(x -> x.get("PRODID"), x -> x.get("REG_CODE")));
			}

			// 汇率mapper
			Map<String, BigDecimal> rateMap = new HashMap<>();
			int rowId = 1;
			if (prodRegInfoList == null) {
				prodRegInfoList = new ArrayList<ChbPrdresistCode>();
			}

			List<String> allInvestor = new ArrayList<String>();
			if(isExcel){
				allInvestor = investorsHoldInfo001Mapper.selectAllInvestor();
			}
			for (Map<String, String> item : mapList) {
				List<String> message = new ArrayList<>();
				InvestorsHoldInfo001Dto dto = new InvestorsHoldInfo001Dto();
				String registBankCode = item.get("REGIST_BANK_CODE");
				// 登记银行代码校验
				if (StringUtil.isEmpty(registBankCode)) {
					message.add(this.getMessage("REPORT_COMMON_E_0057", "登记银行代码"));
					isNeed = false;
				}
				// excel中的登记银行代码必须与当前导入人的登记银行代码一致!
				if (isExcel) {
					if (!StringUtil.equals(bankCode, registBankCode)) {
						message.add("登记银行代码必须与当前用户的登记银行代码一致!");
					}
					else if (EquityInfoCheckUtil.findPatern(EquityInfoValid.PATTERN_ALL, registBankCode)) {
						message.add(this.getMessage("CHB_EQUITYINFO_E_035", "登记银行代码"));
					}
					else {
						dto.setRegistBankCode(registBankCode);
						dto.setOrgCode(orgCode);
					}
				}
				// 上游接口 为空或不存在直接移除
				else {
					if (!registMap.containsKey(registBankCode)) {
						isNeed = false;
						message.add("登记银行代码在机构管理中不存在！");
					} else {
						dto.setRegistBankCode(registBankCode);
						if (item.containsKey("ORG_CODE") && StringUtil.isNotEmpty(item.get("ORG_CODE"))) {
							dto.setOrgCode(item.get("ORG_CODE"));
						} else {
							// 未传机构代码 数据字段遗失不保存
							isNeed = false;
						}
					}
				}

				// 识别标识校验
				String indentifyMark = item.get("INDENTIFY_MARK");
				if (StringUtil.isEmpty(indentifyMark)) {
					isNeed = false;
					message.add(this.getMessage("REPORT_COMMON_E_0057", "识别标识"));
				} else if (!Pattern.compile("[^\u4e00-\u9fa5]+").matcher(indentifyMark).matches()) {
					isNeed = false;
					message.add(this.getMessage("CHB_EQUITYINFO_E_012", "识别标识"));
				} else if (EquityInfoCheckUtil.getByteLength(indentifyMark) > 30) {
					isNeed = false;
					message.add(this.getMessage("REPORT_COMMON_E_0002", "识别标识", 30));
				}
				else if(isExcel && (!allInvestor.contains(indentifyMark))){
					isNeed = false;
					message.add(this.getMessage("CHB_INVESTHOLD_INDENTIFYMARK"));
				}
				else if (EquityInfoCheckUtil.findPatern(EquityInfoValid.PATTERN_ALL, indentifyMark)) {
					message.add(this.getMessage("CHB_EQUITYINFO_E_035", "识别标识"));
				}
				else {
					dto.setIndentifyMark(indentifyMark);
				}
				// 持有日期校验
				String holdDate = item.get("HOLD_DATE");
				Date currDate = com.joyintech.framework.util.DateUtil.now();//当前日期
				if (StringUtil.isEmpty(holdDate)) {
					isNeed = false;
					message.add(this.getMessage("REPORT_COMMON_E_0057", "持有日期"));
				} else if (holdDate.length() < 8) {
					isNeed = false;
					message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "持有日期"));
				} else if (!com.joyintech.framework.util.DateUtil.isDateStr(holdDate, com.joyintech.framework.util.DateUtil.DEFAULT_OTHER_FORMAT,
						com.joyintech.framework.util.DateUtil.DEFAULT_ANOTHER_FORMAT, com.joyintech.framework.util.DateUtil.DEFAULT_DATE_FORMAT_EN)) {
					isNeed = false;
					message.add(this.getMessage("REPORT_COMMON_FILE_E_0013", "持有日期"));
				} else if (com.joyintech.framework.util.DateUtil.isDateStr(holdDate, com.joyintech.framework.util.DateUtil.DEFAULT_OTHER_FORMAT)
						&& !isDateE(holdDate, com.joyintech.framework.util.DateUtil.DEFAULT_OTHER_FORMAT)) {
					isNeed = false;
					message.add(this.getMessage("CHB_INVESTHOLD_CHECKHOLDDATE"));
				} else if (com.joyintech.framework.util.DateUtil.isDateStr(holdDate, com.joyintech.framework.util.DateUtil.DEFAULT_ANOTHER_FORMAT)
						&& !isDateE(holdDate, com.joyintech.framework.util.DateUtil.DEFAULT_ANOTHER_FORMAT)) {
					isNeed = false;
					message.add(this.getMessage("CHB_INVESTHOLD_CHECKHOLDDATE"));
				} else if (com.joyintech.framework.util.DateUtil.isDateStr(holdDate, com.joyintech.framework.util.DateUtil.DEFAULT_DATE_FORMAT_EN)
						&& !isDateE(holdDate, com.joyintech.framework.util.DateUtil.DEFAULT_DATE_FORMAT_EN)) {
					isNeed = false;
					message.add(this.getMessage("CHB_INVESTHOLD_CHECKHOLDDATE"));
				}
				else if (EquityInfoCheckUtil.findPatern(EquityInfoValid.PATTERN_ALL, holdDate)) {
					message.add(this.getMessage("CHB_EQUITYINFO_E_035", "持有日期"));
				}
				else if(com.joyintech.framework.util.DateUtil.compareDate(currDate, com.joyintech.framework.util.DateUtil.parse(holdDate))<0){
					isNeed = false;
					message.add(this.getMessage("CHB_INVESTHOLD_CHECKHOLDDATE_2"));
				}else {
					dto.setHoldDate(com.joyintech.framework.util.DateUtil.parse(holdDate));
				}
				// 币种校验
				String ccy = item.get("CCY");
				if (StringUtil.isEmpty(ccy)) {
					isNeed = false;
					message.add(getMessage("REPORT_COMMON_E_0001", "币种"));
				} else {
					Map<String, String> dictCodeMap = SysDataUtil.getSysDictDataMap(DictCode.币种_中债);
					String[] codeList = StringUtil.split(ccy, StringUtil.SPACE);
					if (!dictCodeMap.containsKey(codeList[0])) {
						isNeed = false;
						message.add(getMessage("CHBCONNECT_E_0002", "币种"));
					}
					else if (EquityInfoCheckUtil.findPatern(EquityInfoValid.PATTERN_ALL, ccy)) {
						message.add(this.getMessage("CHB_EQUITYINFO_E_035", "币种"));
					}
					else {
						dto.setCcy(codeList[0]);
					}
				}
				// 持有份额
				String qty = item.get("QTY");
				String qtyInfo = checkMoney(qty, "持有份额", 13, 5, true);
				if (StringUtil.isNotEmpty(qtyInfo)) {
					message.add(qtyInfo);
				} else {
					dto.setQty(new BigDecimal(qty));
				}

				// 持有金额
				String amount = item.get("AMOUNT");
				String amountInfo = checkMoney(amount, "持有金额", 13, 2, true);
				if (StringUtil.isNotEmpty(amountInfo)) {
					message.add(amountInfo);
				} else {
					dto.setAmount(new BigDecimal(amount));
				}

				// 折算人民币金额（元）
				if (item.containsKey("CNY_AMT")) {
					String cnyAmt = item.get("CNY_AMT");
					String cnyAmtInfo = checkMoney(cnyAmt, "折算人民币金额（元）", 13, 2, false);
					if (StringUtil.isNotEmpty(cnyAmtInfo)) {
						message.add(cnyAmtInfo);
					} else if (StringUtil.isNotEmpty(cnyAmt)) {
						dto.setCnyAmt(new BigDecimal(cnyAmt));
					}
				}
				//如果币种是人民币时，持有金额和折算人民币金额必须保持一致
				if (isExcel) {
					try {
						String[] codeList1 = StringUtil.split(ccy, StringUtil.SPACE);
						BigDecimal amount1 = new BigDecimal(item.get("AMOUNT"));
						BigDecimal cnyAmt1 = new BigDecimal(item.get("CNY_AMT"));
						if (StringUtil.equals(codeList1[0], DictCodeListExt1.币种_中债_人民币.getValue())) {
							if(amount1 != null || cnyAmt1 != null){
								if (cnyAmt1.compareTo(amount1) != 0) {
									message.add(getMessage("REPORT_COMMON_FILE_E_0025"));
								}
							}
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}

				/**机构和部门代码 为空时，给默认值**/
				if (StringUtil.isEmpty(item.get("ORG_CODE"))) {
					item.put("ORG_CODE", SvrDataUtil.getSysParam("CHB_ORG_CODE"));
				}
				if (StringUtil.isEmpty(item.get("DEPT_CODE"))) {
					item.put("DEPT_CODE", SvrDataUtil.getSysParam("CHB_DEPT_CODE"));
				}

				// 部门代码
				if (item.containsKey("DEPT_CODE") || StringUtil.isNotEmpty(item.get("DEPT_CODE"))) {
					dto.setDeptCode(item.get("DEPT_CODE"));
				}
				// 接入检查结果
				if (item.containsKey("AGENT") || StringUtil.isNotEmpty(item.get("AGENT"))) {
					dto.setAgent(item.get("AGENT"));
				}

				// 接入检查结果
				if (item.containsKey("INTERF_CHECK_RESULT") || StringUtil.isNotEmpty(item.get("INTERF_CHECK_RESULT"))) {
					dto.setInterfCheckResult(item.get("INTERF_CHECK_RESULT"));
				}
				// XBRL反馈消息
				if (item.containsKey("XBRL_BACK_RESULT") || StringUtil.isNotEmpty(item.get("XBRL_BACK_RESULT"))) {
					dto.setXbrlBackResult(item.get("XBRL_BACK_RESULT"));
				}
				// 代销机构代码
				if (item.containsKey("AGENCY_CODE") || StringUtil.isNotEmpty(item.get("AGENCY_CODE"))) {
					dto.setAgencyCode(item.get("AGENCY_CODE"));
				}
				// 账号开户所在地
				if (item.containsKey("FUND_ACC_PLACE") || StringUtil.isNotEmpty(item.get("FUND_ACC_PLACE"))) {
					dto.setFundAccPlace(item.get("FUND_ACC_PLACE"));
				}
				// 创建部门
				if (isExcel) {
					String createDept = deptCode != null ? deptCode
							: orgCode;
					dto.setCreateDept(createDept);
					dto.setDeptCode(deptCode);
				} else {
					String createDept = StringUtil.isNotEmpty(item.get("DEPT_CODE")) ? item.get("DEPT_CODE")
							: item.get("ORG_CODE");
					dto.setCreateDept(createDept);
				}
				String account = item.get("ACCOUNT");

				//新增产品登记编码格式校验
				String regCode = item.get("REG_CODE");
				if (StringUtil.isNotEmpty(regCode) && !regCode.matches("^[0-9a-zA-Z]{8}[ABC]?[0-9]{6}")){
					message.add(this.getMessage("CHB_INVESTHOLD_CHECKREGCODE"));
				}
				else if (StringUtil.isNotEmpty(regCode) && EquityInfoCheckUtil.findPatern(EquityInfoValid.PATTERN_ALL, regCode)) {
					message.add(this.getMessage("CHB_EQUITYINFO_E_035", "产品登记编码"));
				}

				// 新加产品代码字段
				dto.setProdid(account);

				if (StringUtil.isEmpty(account)) {
					isNeed = false;
					message.add(this.getMessage("CHB_INVESTHOLD_CHECKPROD"));
				}
				else {
					if (!prdRegistMap.containsKey(account)) {
						dto.setPrdRegistCode(item.get("REG_CODE"));
						if (!StringUtil.isNotEmpty(item.get("REG_CODE"))) {
							message.add(this.getMessage("CHB_INVESTHOLD_CHECKPROD"));
						} else if (StringUtil.isNotEmpty(item.get("REG_CODE")) && item.get("REG_CODE").length() != 14
								&& item.get("REG_CODE").length() != 15) {
							message.add("产品登记编码的长度为14或15位！");
						} else if(StringUtil.isNotEmpty(item.get("REG_CODE")) && !Pattern.compile("^[0-9a-zA-Z]{8}[ABC]?[0-9]{6}").matcher(item.get("REG_CODE")).matches()){
							message.add(this.getMessage("REPORT_COMMON_E_0067", "产品登记编码"));
						}
					} else {
						if (StringUtil.isEmpty(prdRegistMap.get(account)) && StringUtil.isEmpty(item.get("REG_CODE"))) {
							message.add(this.getMessage("CHB_INVESTHOLD_CHECKPROD"));
						} else if (StringUtil.isNotEmpty(item.get("REG_CODE")) && item.get("REG_CODE").length() != 14
								&& item.get("REG_CODE").length() != 15) {
							message.add("产品登记编码的长度为14或15位！");
						} else if (StringUtil.equals(prdRegistMap.get(account), item.get("REG_CODE"))
								|| StringUtil.isEmpty(item.get("REG_CODE"))) {
							dto.setPrdRegistCode(prdRegistMap.get(account));
						} else if (StringUtil.isEmpty(prdRegistMap.get(account))) {
							dto.setPrdRegistCode(item.get("REG_CODE"));
						} else {
							dto.setPrdRegistCode(item.get("REG_CODE"));
							message.add(this.getMessage("REPORT_REG_MISMACTH"));
						}
					}
				}

				dto.setRowId(String.valueOf(rowId));
				// 折算人民币处理
				if (dto.getCnyAmt() == null) {
					if (StringUtil.equals(dto.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue())) {
						dto.setCnyAmt(dto.getAmount());
					} else {
						String key = com.joyintech.framework.util.DateUtil.format(dto.getHoldDate()) + dto.getCcy();
						if (rateMap.containsKey(key)) {
							dto.setCnyAmt(ArithmeticUtil.multiply(rateMap.get(key), dto.getAmount()));
						} else {
							Map<String, Object> temp = new HashMap<>();
							// 原币种
							temp.put("CCY_ORIGINAL", dto.getCcy());
							// 持有日期
							temp.put("HOLDDATE", dto.getHoldDate());
							// 折算币种
							temp.put("CCY_CONVERT", DictCodeListExt1.币种_中债_人民币.getValue());
							// 汇率计算规则
							temp.put("pricetype",SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE));
							BigDecimal rate = investorsHoldInfo001Mapper.getRateNearByDate(temp);
							if (rate != null) {
								dto.setCnyAmt(ArithmeticUtil.multiply(rate, dto.getAmount()));
								dto.setCnyAmt(dto.getCnyAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
								rateMap.put(key, rate);
							} else {
								message.add("汇率不存在！");
							}
						}
					}
				}
//				}
				//反馈状态是发送成功不能修改
				if (isExcel) {
					try {
						List<ChbInvestholdInfoSub> subListUp = new ArrayList<>();
						ChbInvestholdInfoSub subDto = new ChbInvestholdInfoSub();
						String[] codeList = StringUtil.split(ccy, StringUtil.SPACE);
						subDto.setIndentifyMark(item.get("INDENTIFY_MARK"));
						subDto.setProdid(item.get("ACCOUNT"));
						subDto.setCcy(codeList[0]);
						subDto.setHoldDate(com.joyintech.framework.util.DateUtil.parse(item.get("HOLD_DATE")));
						subDto.setRegistBankCode(item.get("REGIST_BANK_CODE"));
						subListUp.add(subDto);
						List<InvestorsHoldInfoRegist001Dto> submitFeedbackStatusList = this
								.getSubmitFeedbackStatusList(subListUp);
						if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(submitFeedbackStatusList)) {
							for (InvestorsHoldInfoRegist001Dto action : submitFeedbackStatusList) {
								if ("01".equals(action.getSubmitFeedbackStatus())
										|| "02".equals(action.getSubmitFeedbackStatus())
										|| "05".equals(action.getSubmitFeedbackStatus())
										|| StringUtil.isBlank(action.getSubmitFeedbackStatus())) {
									message.add("该数据存在中债报送流程中的数据，不允许修改，请稍后再试！");
								}
							}
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
				// 判断为空check校验通过
				if (message.isEmpty()) {
					dto.setInterfCheckResult(" ");
					listE.add(dto);
				} else {
					// 存在错误信息 判断业务主键是否完整
					if (isNeed && !isExcel) {
						try {
							String checkMess = "";
							for (String msg : message) {
								checkMess += msg;
							}
							if (checkMess.length() > 200) {
								checkMess = checkMess.substring(0, 200);
							}
							dto.setInterfCheckResult(checkMess);
						}
						catch (Exception e) {
							logger.error(e.getMessage(), e);
						}

						listA.add(dto);
					}

					// 打印检查MESSAGE
					try {
						logger.error("识别标识[{}] 产品[{}]检查错误消息如下：" , indentifyMark,account);
						messageInfo.append("识别标识[" + indentifyMark + "] 产品[" + account + "]检查错误消息如下：").append("\n");
						for (String msg : message) {
							logger.error(msg);
							if(!isExcel){
								messageInfo.append(msg).append("\n");
							}
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
					// 生成错误信息提醒
					if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
						result.add(new ExcelValidDto(item.get("INDENTIFY_MARK")+"|"+item.get("ACCOUNT")
								+"|"+item.get("HOLD_DATE")
								+"|"+item.get("CCY")+"|"+item.get("QTY")+"|"+item.get("AMOUNT"), String.valueOf(rowId+1),
								StringUtil.joinString(message, "<br>")));
					}else{
						result.add(new ExcelValidDto(indentifyMark, String.valueOf(rowId + 1),
								StringUtil.joinString(message, "<br>")));
					}
				}
				rowId++;
			}
		}
		OutputStreamWriter osw = null;
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(file);
			osw = new OutputStreamWriter(fout);
			osw.append(messageInfo.toString()).append("\n");
			osw.flush();
		} catch (Exception e1) {
			logger.error(e1.getMessage(),e1);
		}
		finally{
			if(fout !=null ){
				try {
					fout.close();
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}

			if(osw!=null){
				try {
					osw.close();
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
		logger.info("checkDataInfo处理结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());

		return result;
	}

	/**
	 * 持有信息修改之前查询反馈状态
	 * <功能详细描述>
	 * @param
	 * @see
	 * @since 1.0
	 */
	public List<InvestorsHoldInfoRegist001Dto>  getSubmitFeedbackStatusList(List<ChbInvestholdInfoSub> subListUp){
		if(!StringUtil.isEmptyList(subListUp) && subListUp.size()>1000){
			chbInvestholdInfoCheckDtoMapper.truncateInvestholdTmp();
			investorsHoldInfoDistinctService.saveChbInvestholdInfoCheckTmpBySub(subListUp);
			return investorsHoldInfo001Mapper.getSubmitFeedbackStatusListNew();
		}else{
			return investorsHoldInfo001Mapper.getSubmitFeedbackStatusList(subListUp);
		}
	}

	/**
	 * 判断代码是否合法，返回错误信息
	 *
	 * @param code
	 *            代码
	 * @param intlen
	 *            长度
	 * @param declen
	 *            长度
	 * @param cloumnTitle
	 *            列名
	 * @param isRequired
	 *            是否必须
	 * @return 错误信息
	 */
	private String checkMoney(String code, String cloumnTitle, int intlen, int declen, boolean isRequired) {
		String message = null;
		// 必须验证
		if (StringUtil.isEmpty(code)) {
			if (isRequired) {
				message = this.getMessage("REPORT_COMMON_E_0001", cloumnTitle);
				return message;
			} else {
				return null;
			}
		}
		// 属性验证
		if (!CommonUtil.isFloatNumeric(code)) {
			message = this.getMessage("REPORT_COMMON_E_0003", cloumnTitle);
		}
		// add by etliu 20180912 start
		else if (StringUtil.isNotEmpty(code)
				&& EquityInfoCheckUtil.findPatern(EquityInfoValid.PATTERN_ALL, code)) {
			message = this.getMessage("CHB_EQUITYINFO_E_035", cloumnTitle);
		}
		// add by etliu 20180912 end
		else {
			BigDecimal num = new BigDecimal(code);
			if (num.compareTo(BigDecimal.ZERO) <= 0) {
				message = this.getMessage("REPORT_COMMON_E_0024", cloumnTitle, "0");
			}

			// 长度验证
			if ((num.precision() - num.scale()) > intlen || num.scale() > declen) {
				message = this.getMessage("REPORT_ASSETBOND_E_001", cloumnTitle, intlen, declen);
			}
		}
		return message;
	}

	/**
	 * 传入日期，日期是否符合日期规范
	 *
	 * @param
	 *
	 * @param pattern
	 *            日期转换
	 * @return
	 */
	public static boolean isDateE(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			format.setLenient(false);
			format.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 获取文件中的所有产品代码与产品登记编码的对应关系
	 *
	 * @param equityList 文件中的数据
	 * @param paraDto paraDto
	 * @see
	 * @since 1.0
	 */
	private List<ChbPrdresistCode> getProdRegInfoListFromFile(List<Map<String, String>> equityList, InvestorsHoldInfo001FileReadParaDto paraDto) {
		List<ChbPrdresistCode> prodRegInfoList = new ArrayList<>();

		// 获取系统中存在的所有的产品登记编码
		Map<String, String> prdRegistMap = paraDto.getPrdRegistMap();

		Map<String, String> tempDataKeyMap = new HashMap<String, String>();
		for (Map<String, String> dataMap : equityList) {
			String account = dataMap.get("ACCOUNT");
			String regCode = dataMap.get("REG_CODE");
			String orgCode="";
			if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
				orgCode = dataMap.get("ORG_CODE")==null ? "C10403" :dataMap.get("ORG_CODE");
			}else{
				/*add by xuyang for 持有信息解析时，产品登记编码入库机构代码为空给默认值  徽商个性化 20200822 begin*/
				orgCode = dataMap.get("ORG_CODE")==null ? CacheUtil.getSysParam("CHB_ORG_CODE") :dataMap.get("ORG_CODE");
			}
			/*add by xuyang for 持有信息解析时，产品登记编码入库机构代码为空给默认值  徽商个性化 20200822 end*/
			if (StringUtil.isNotBlank(account)) {
				if (!prdRegistMap.containsKey(account) && !tempDataKeyMap.containsKey(account)) {
					// 产品登记编码信息
					ChbPrdresistCode prodRegInfoDto = new ChbPrdresistCode();
					prodRegInfoDto.setProdid(account);
					prodRegInfoDto.setRegCode(regCode);
					prodRegInfoDto.setOrgCode(orgCode);
					prodRegInfoDto.setDeptCode(null);
					prodRegInfoDto.setCreateDept(null);
					prodRegInfoList.add(prodRegInfoDto);

					tempDataKeyMap.put(account, account);
				}
			}
		}

		return prodRegInfoList;
	}

	/**
	 * 对于校验通过的数据进行后续处理 <功能详细描述>
	 *
	 * @param listE
	 *            校验通过数据
	 * @param type
	 *            操作的类型
	 * @param subTable
	 *            子表
	 * @param table
	 *            表
	 * @param tmpPath
	 *            临时路径
	 * @param paraDto
	 *            投资者持有信息上游接口接入多线程处理，可以事先查询的内容dto
	 * @see
	 * @since 1.0
	 */
	public void afterCheckDealNew(List<InvestorsHoldInfo001Dto> listE, List<InvestorsHoldInfo001Dto> listA, String type,
								  String tmpPath, ImpTable table, ImpTable subTable, InvestorsHoldInfo001FileReadParaDto paraDto, List<ChbInvestholdInfoCheckDto> mergelist) {

		boolean isDB2DataBase = CommonUtil.isDB2DataBase();

		logger.info("afterCheckDeal待维护数据插入数据库处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());


		// 待维护数据插入数据库
		if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(listA)) {

			Iterator<InvestorsHoldInfo001Dto> iterator = listA.iterator();

			while(iterator.hasNext()){
				InvestorsHoldInfo001Dto action = iterator.next();
				//如果是识别标识合并的地方有了，则剔除。
				String actionKey = action.getRegistBankCode() + GlobalConstant.UNDER_LINE
						+ action.getIndentifyMark()+ GlobalConstant.UNDER_LINE
						+ action.getPrdRegistCode() + GlobalConstant.UNDER_LINE
						+ action.getCcy()+ GlobalConstant.UNDER_LINE
						+ com.joyintech.framework.util.DateUtil.format(action.getHoldDate());

				action.setDataSource(type);
				action.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
			}


			if(!StringUtil.isEmptyList(listA) && listA.size()>1000){
				chbInvestholdInfoCheckDtoMapper.truncateInvestholdTmp();
				// 先删后插
				investorsHoldInfoDistinctService.saveChbInvestholdInfoCheckTmpByInfo(listA);
				investorsHoldInfoDistinctService.deleteChbInvestholdInfoByTmp();
				// 插入
				investorsHoldInfoDistinctService.saveChbInvestholdInfo(listA);
			}else{
				investorsHoldInfoDistinctService.insertChbHoldInfoDataByPart(listA);
			}
		}
		logger.info("afterCheckDeal待维护数据插入数据库处理结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());

		if (org.apache.commons.collections4.CollectionUtils.isEmpty(listE)) {
			return;
		}

		// 画面导入及新增修改时，处理数据库中存在的删除数据
		if (StringUtil.equals(type, DictCodeListExt2.数据来源_EXCEL导入.getValue())
				|| StringUtil.equals(type, DictCodeListExt2.数据来源_系统新建.getValue())) {

			if(!StringUtil.isEmptyList(listE) && listE.size()>1000){
				chbInvestholdInfoCheckDtoMapper.truncateInvestholdTmp();
				investorsHoldInfoDistinctService.saveChbInvestholdInfoCheckTmpByInfo(listE);

				investorsHoldInfo001Mapper.handleInfoDeleteDataNew();
				investorsHoldInfo001Mapper.handleSubDeleteDataNew();
			}else{
				investorsHoldInfoDistinctService.handleStatusDeleteData(listE);
			}
		}

		// 查询导入文件中的数据在数据库中已经存在的数据
		logger.info("selectAllHoldInfoData处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
		Map<String, InvestorsHoldInfo001Dto> allHoldInfoDataMap = new HashMap<String, InvestorsHoldInfo001Dto>();
		if ((paraDto != null && paraDto.isTodayDataExist())||paraDto==null) {
			if(!StringUtil.isEmptyList(listE) && listE.size()>1000){
				allHoldInfoDataMap = selectAllHoldInfoDataForTmp();
			}else{
				allHoldInfoDataMap = selectAllHoldInfoDataMutiThread(listE);
			}
		}
		logger.info("selectAllHoldInfoData结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());

		// 查询导入文件中的数据在数据库中报送表已经存在的数据
		logger.info("selectAllSubHoldInfoData处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
		Map<String, ChbInvestholdInfoSub> allSubHoldInfoDataMap = new HashMap<String, ChbInvestholdInfoSub>();
		// 维护表中没有数据的情况，登记表肯定没有数据，就不用再查询了
		if (allHoldInfoDataMap.size() > 0) {
			if(!StringUtil.isEmptyList(listE) && listE.size()>1000){
				allSubHoldInfoDataMap = selectAllSubHoldInfoDataForTmp();
			}else{
				allSubHoldInfoDataMap = selectAllSubHoldInfoData(listE);
			}
		}
		logger.info("selectAllSubHoldInfoData结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());

		// 存在维护表数据
		// 插入操作
		List<InvestorsHoldInfo001Dto> listIn = new ArrayList<>();
		// 更新操作
		List<InvestorsHoldInfo001Dto> listUp = new ArrayList<>();
		// 插入操作
		List<ChbInvestholdInfoSub> subListInsert = new ArrayList<>();
		// 更新操作
		List<ChbInvestholdInfoSub> subListUpdate = new ArrayList<>();

		logger.info("登记表数据整理处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
		Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();

		List<String> sequenceList = null;

		if (listE != null && listE.size() > 0) {
			Date hdate = listE.get(0).getHoldDate();
			sequenceList = CommonUtil.getBatchSequenceIndexDatawithDate(DataIndexSequenceType.中债登记管理, listE.size(),hdate);
		}
		int index = 0;

		for (InvestorsHoldInfo001Dto action : listE) {

			// 用来区分的key(登记表用)
			String dataKey = action.getRegistBankCode() + GlobalConstant.UNDER_LINE
					+ action.getIndentifyMark()+ GlobalConstant.UNDER_LINE
					+ action.getPrdRegistCode() + GlobalConstant.UNDER_LINE
					+ action.getCcy()+ GlobalConstant.UNDER_LINE
					+ com.joyintech.framework.util.DateUtil.format(action.getHoldDate());

			//数据对比，当无数据变化时，跳过不对登记表进行处理  begin
			ChbInvestholdInfoSub chbInvestholdInfoSubTemp = allSubHoldInfoDataMap.get(dataKey+GlobalConstant.UNDER_LINE+DictCodeListExt2.报送状态_接口报送.getValue());
			if(chbInvestholdInfoSubTemp == null){
				chbInvestholdInfoSubTemp = allSubHoldInfoDataMap.get(dataKey+GlobalConstant.UNDER_LINE+DictCodeListExt2.报送状态_手工处理.getValue());
			}
			if(chbInvestholdInfoSubTemp != null && action.getAmount().compareTo(chbInvestholdInfoSubTemp.getAmount()) == 0
					&& action.getQty().compareTo(chbInvestholdInfoSubTemp.getQty()) == 0
					&& action.getCnyAmt().compareTo(chbInvestholdInfoSubTemp.getCnyAmt()) == 0){
				continue;
			}

			ChbInvestholdInfoSub chbInvestholdInfoSub = new ChbInvestholdInfoSub();
			BeanUtil.copyPropertiesIgnoreNull(chbInvestholdInfoSub, action);
			// 检查序列号设置初始值
			chbInvestholdInfoSub.setCheckSeqno("0");
			action.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());

			String subStatus = StringUtil.EMPTY;
			chbInvestholdInfoSub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
			chbInvestholdInfoSub.setStatusDate(new Date());
			if (allHoldInfoDataMap.containsKey(dataKey)) {
				listUp.add(action);
			} else {
				listIn.add(action);
			}
			if (allSubHoldInfoDataMap.containsKey(dataKey + GlobalConstant.UNDER_LINE + SEND_STATUS_02)
					|| allSubHoldInfoDataMap.containsKey(dataKey + GlobalConstant.UNDER_LINE + SEND_STATUS_03)) {
				subStatus = DictCodeListExt2.报送数据状态_修改.getValue();
			} else {
				subStatus = DictCodeListExt2.报送数据状态_新建.getValue();
			}
			// 数据状态
			chbInvestholdInfoSub.setStatus(subStatus);
			// 产品代码
			chbInvestholdInfoSub.setProdid(action.getProdid());
			if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
				chbInvestholdInfoSub.setChbSubmitDeadline(DateCalcUtil.getEndOfDay(com.joyintech.framework.util.DateUtil.addDay(chbInvestholdInfoSub.getHoldDate(), 1)));
				chbInvestholdInfoSub.setSubmitDeadline(DateCalcUtil.getEndOfDay(com.joyintech.framework.util.DateUtil.addDay(chbInvestholdInfoSub.getHoldDate(), 1)));
			}else{
				// 当前批次报送时点集合
				List<Date> dateList = null;
				// 报送时点通用方法
				dateList = ChbDeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_投资者持有信息登记.getValue(), subStatus, dateMap,
						chbInvestholdInfoSub.getHoldDate());
				if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(dateList)) {
					chbInvestholdInfoSub.setChbSubmitDeadline(dateList.get(0));
					chbInvestholdInfoSub.setSubmitDeadline(dateList.get(1));
				}
			}
			if (allSubHoldInfoDataMap.containsKey(dataKey + GlobalConstant.UNDER_LINE + SEND_STATUS_01)) {
				chbInvestholdInfoSub.setRegistSeqno(allSubHoldInfoDataMap
						.get(dataKey + GlobalConstant.UNDER_LINE + SEND_STATUS_01).getRegistSeqno());

				// add by etliu 20180829 start
					chbInvestholdInfoSub.setSumitSeq(chbInvestholdInfoSub.getRegistSeqno());
				// add by etliu 20180829 end
				subListUpdate.add(chbInvestholdInfoSub);
			} else {
				if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
					chbInvestholdInfoSub.setRegistSeqno(getId());
				}else{
					chbInvestholdInfoSub.setRegistSeqno(
							CommonUtil.getSequenceStr(sequenceList, index)
					);
					index++;
				}
					chbInvestholdInfoSub.setSumitSeq(chbInvestholdInfoSub.getRegistSeqno());
				subListInsert.add(chbInvestholdInfoSub);
			}
		}
		logger.info("登记表数据整理处理结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());

		if (tmpPath == null) {
			if (listIn != null && listIn.size() > 0) {
				if(listIn.size()>1000){
					chbInvestholdInfoCheckDtoMapper.truncateInvestholdTmp();
					// 先删后插
					investorsHoldInfoDistinctService.saveChbInvestholdInfoCheckTmpByInfo(listIn);
					investorsHoldInfoDistinctService.deleteChbInvestholdInfoByTmp();
					// 插入
					investorsHoldInfoDistinctService.saveChbInvestholdInfo(listIn);
				}else{
					investorsHoldInfoDistinctService.insertChbHoldInfoDataByPart(listIn);
				}
			}
			if (subListInsert != null && subListInsert.size() > 0) {
				insertChbSubHoldInfoDataByPart(subListInsert);
			}
		} else {
			logger.info("createDataFile处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
			// TODO
			createDataFile(tmpPath, listIn, subListInsert, table.getColumns(), subTable.getColumns());
			logger.info("createDataFile处理结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
		}
		// 更新数据库
		if (subListUpdate != null && subListUpdate.size() > 0) {
			logger.info("更新登记表信息处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
			insertChbSubHoldInfoDataByPart(subListUpdate);
			logger.info("更新登记表信息处理结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());

		}
		if (listUp != null && listUp.size() > 0) {
			logger.info("insertChbHoldInfoDataByPart处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
			if(listUp.size()>1000){
				chbInvestholdInfoCheckDtoMapper.truncateInvestholdTmp();
				// 先删后插
				investorsHoldInfoDistinctService.saveChbInvestholdInfoCheckTmpByInfo(listUp);
				investorsHoldInfoDistinctService.deleteChbInvestholdInfoByTmp();
				// 插入
				investorsHoldInfoDistinctService.saveChbInvestholdInfo(listUp);
			}else{
				investorsHoldInfoDistinctService.insertChbHoldInfoDataByPart(listUp);
			}
			logger.info("insertChbHoldInfoDataByPart处理结束  " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
		}
		chbInvestholdInfoCheckDtoMapper.truncateInvestholdTmp();
	}

	/**
	 *  多线程查询 50最优
	 * @param
	 * @return
	 */
	private Map<String, InvestorsHoldInfo001Dto> selectAllHoldInfoDataForTmp() {

		List<InvestorsHoldInfo001Dto> resultList = investorsHoldInfo001Mapper.selectAllHoldInfoDataNew() ;

		Map<String, InvestorsHoldInfo001Dto> resMap = StringUtil.isEmptyList(resultList)
				? new HashMap<String, InvestorsHoldInfo001Dto>()
				: resultList.stream().collect(JoyinCollectors.toMap(
				x -> x.getRegistBankCode() + GlobalConstant.UNDER_LINE
						+ x.getIndentifyMark()+ GlobalConstant.UNDER_LINE
						+ x.getPrdRegistCode() + GlobalConstant.UNDER_LINE
						+ x.getCcy() + GlobalConstant.UNDER_LINE
						+ com.joyintech.framework.util.DateUtil.format(x.getHoldDate()),
				x -> x));

		resultList.clear();
		return resMap;
	}

	/**
	 * 分批登记数据插入数据库 <功能详细描述>
	 *
	 * @param subList
	 *            待处理数据
	 * @see
	 * @since 1.0
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
	public void insertChbSubHoldInfoDataByPart(List<ChbInvestholdInfoSub> subList) {
		List<ChbInvestholdInfoSub> subListNew = subList.stream().filter(sub -> "02".equals(sub.getStatus())).collect(Collectors.toList());


		if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(subListNew)) {
			List<InvestorsHoldInfoRegist001Dto> submitFeedbackStatusList = this.getSubmitFeedbackStatusList(subListNew);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(submitFeedbackStatusList)) {
				for (InvestorsHoldInfoRegist001Dto action : submitFeedbackStatusList) {
					if ("03".equals(action.getSubmitFeedbackStatus())) {
						if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(subList)) {
							Iterator<ChbInvestholdInfoSub> it = subList.iterator();
							while (it.hasNext()) {
								ChbInvestholdInfoSub dto = it.next();
								if (dto.getRegistBankCode().equals(action.getRegistBankCode())
										&& dto.getProdid().equals(action.getProdid())
										&& dto.getCcy().equals(action.getCcy())
										&& dto.getIndentifyMark().equals(action.getIndentifyMark())
										&& sdf.format(dto.getHoldDate()).equals(sdf.format(action.getHoldDate()))
										&& (dto.getAmount().compareTo(action.getAmount()) == 0)
										&& (dto.getQty().compareTo(action.getQty()) == 0)
										&& (dto.getCnyAmt().compareTo(action.getCnyAmt()) == 0)) {
									it.remove();
								}
							}
						}
					}
				}
			}
		}
		if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(subList)) {
			if(subList.size()>1000){
				chbInvestholdInfoCheckDtoMapper.truncateInvestholdTmp();
				// 先删后插
				investorsHoldInfoDistinctService.saveChbInvestholdInfoCheckTmpBySub(subList);

				investorsHoldInfoDistinctService.deleteChbInvestholdInfoSubByTmp();
				// 插入
				investorsHoldInfoDistinctService.saveChbInvestholdInfoSub(subList);
			}else {
				int maxDataCountOneTime = this.parsToInt(reportInsertLongCount, "1");
				int dataCount = subList.size();
				if (dataCount <= maxDataCountOneTime) {
					logger.info("单次执行insertChbSubHoldInfoData处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
					try {
						investorsHoldInfoDistinctService.insertChbSubHoldInfoData(subList);
					}
					catch (Exception e){
						logger.error(e.getMessage(),e);
					}
					logger.info("单次执行insertChbSubHoldInfoData处理结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
					return;
				}

				List<ChbInvestholdInfoSub> newDataList = new ArrayList<ChbInvestholdInfoSub>();
				newDataList.addAll(subList);

				// 分批处理
				int part = dataCount / maxDataCountOneTime; // 分批数
				for (int i = 0; i < part; i++) {
					logger.info("单次执行insertChbSubHoldInfoData处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
					investorsHoldInfoDistinctService.insertChbSubHoldInfoData(newDataList.subList(0, maxDataCountOneTime));
					newDataList.subList(0, maxDataCountOneTime).clear();
					logger.info("单次执行insertChbSubHoldInfoData处理结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
				}
				// 最后剩下的数据
				if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newDataList)) {
					logger.info("单次执行insertChbSubHoldInfoData处理开始 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
					investorsHoldInfoDistinctService.insertChbSubHoldInfoData(newDataList);
					logger.info("单次执行insertChbSubHoldInfoData处理结束 " + com.joyintech.framework.util.DateUtil.getCurrentDateTime());
				}
			}
		}
	}

	/**
	 * 配置项中非空处理
	 * @param
	 * @param flag
	 * @return
	 */
	public int parsToInt(String count,String flag){
		if(StringUtil.isNotEmpty(count)&&Integer.parseInt(count)<=0){
			if("1".equals(flag)){
				return 20;
			}
			else if("1".equals(flag)){
				return 500;
			}
			else {
				return 100;
			}
		}
		else{
			return Integer.parseInt(count);
		}
	}

	/**
	 * 创建数据文件
	 *
	 * @param path
	 *            文件路径
	 * @param list
	 *            对象列表
	 * @param subList
	 *            对象列表
	 * @param columns
	 *            数据库表对应的列
	 * @param subColumns
	 */
	private void createDataFile(String path, List<InvestorsHoldInfo001Dto> list, List<ChbInvestholdInfoSub> subList,
								List<ImpColumn> columns, List<ImpColumn> subColumns) {
		List<String> lines = new ArrayList<>(list.size());
		List<String> subLines = new ArrayList<>(subList.size());
		list.forEach(item -> lines.add(ImportGenerator.buildTxt(item, columns)));
		subList.forEach(item -> subLines.add(ImportGenerator.buildTxt(item, subColumns)));
		try {
			if (list.size() > 0) {
				FileUtils.writeLines(new SafelyFile(path + "/CHB_INVESTHOLD_INFO" + GlobalConstant.UNDER_LINE
						+ UUID.randomUUID().toString() + ".tmp"), "UTF-8", lines, true);
			}
			if (subList.size() > 0) {
				FileUtils.writeLines(new SafelyFile(path + "/CHB_INVESTHOLD_INFO_SUB" + GlobalConstant.UNDER_LINE
						+ UUID.randomUUID().toString() + ".tmp"), "UTF-8", subLines, true);
			}
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public static synchronized String getId() {
		Long nowStr = Long.valueOf(System.currentTimeMillis());
		if (lastDate.equals(nowStr.toString())) {
			addPart += 1;
		} else {
			addPart = 1;
			lastDate = nowStr.toString();
		}

		return "R0" + nowStr.toString() + String.format("%05d", new Object[] { Integer.valueOf(addPart) });
	}

	/**
	 *  多线程查询 50最优
	 * @param list
	 * @return
	 */
	private Map<String, InvestorsHoldInfo001Dto> selectAllHoldInfoDataMutiThread(List<InvestorsHoldInfo001Dto> list) {

		List<InvestorsHoldInfo001Dto> resultList =
				Collections.synchronizedList(new ArrayList<InvestorsHoldInfo001Dto>());

		if (null != list && !list.isEmpty()) {

			List<Callable<Boolean>> tasks = new ArrayList<>();
			ExecutorService service = new JoyinThreadPoolExecutor(4);
			int start = 0;
			int end = 0;
			int size = list.size();
			int max = (int) Math.ceil((double) size / 50);
			// 信号量
			CountDownLatch countDown = new CountDownLatch(max);

			for (int i = 0; i < max; i++) {
				start = end;

				end = Math.min(size, 50 * (i + 1));

				List<InvestorsHoldInfo001Dto> subList = list.subList(start, end);

				// 提交任务
				tasks.add(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						resultList.addAll(investorsHoldInfo001Mapper.selectAllHoldInfoData(subList));
						countDown.countDown();// 个数-1
						return true;
					}
				});
			}
			// 添加线程等待转换线执行
			try {
				service.invokeAll(tasks);
				countDown.await();
				service.shutdown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Map<String, InvestorsHoldInfo001Dto> resMap = StringUtil.isEmptyList(resultList)
				? new HashMap<String, InvestorsHoldInfo001Dto>()
				: resultList.stream().collect(JoyinCollectors.toMap(
				x -> x.getRegistBankCode() + GlobalConstant.UNDER_LINE
						+ x.getIndentifyMark()+ GlobalConstant.UNDER_LINE
						+ x.getPrdRegistCode() + GlobalConstant.UNDER_LINE
						+ x.getCcy() + GlobalConstant.UNDER_LINE
						+ com.joyintech.framework.util.DateUtil.format(x.getHoldDate()),
				x -> x));

		resultList.clear();
		return resMap;
	}

	private Map<String, ChbInvestholdInfoSub> selectAllSubHoldInfoDataForTmp() {
		List<ChbInvestholdInfoSub> allDataList =investorsHoldInfo001Mapper.selectAllSubHoldInfoDataNew();

		Map<String, ChbInvestholdInfoSub> allHoldInfoDataMap = StringUtil.isEmptyList(allDataList)
				? new HashMap<String, ChbInvestholdInfoSub>()
				: allDataList.stream()
				.collect(JoyinCollectors.toMap(x -> x.getRegistBankCode() + GlobalConstant.UNDER_LINE
						+ x.getIndentifyMark() + GlobalConstant.UNDER_LINE
						+ x.getPrdRegistCode() + GlobalConstant.UNDER_LINE
						+ x.getCcy() + GlobalConstant.UNDER_LINE
						+ com.joyintech.framework.util.DateUtil.format(x.getHoldDate())+ GlobalConstant.UNDER_LINE
						+ x.getSendStatus(), x -> x));

		return allHoldInfoDataMap;
	}

	/**
	 * 查询维护表中存在的相关数据 <功能详细描述>
	 *
	 * @param listE
	 *            数据集合
	 * @return 匹配的数据
	 * @see
	 * @since 1.0
	 */
	private Map<String, ChbInvestholdInfoSub> selectAllSubHoldInfoData(List<InvestorsHoldInfo001Dto> listE) {
		// 查询导入文件中的数据在数据库中已经存在的数据
		List<ChbInvestholdInfoSub> allDataList = new ArrayList<ChbInvestholdInfoSub>();

		int maxDataCountOneTime = this.parsToInt(reportSelectOrDeleteCount, "2");
		int dataCount = listE.size();
		if (dataCount <= maxDataCountOneTime) {
			allDataList = investorsHoldInfo001Mapper.selectAllSubHoldInfoData(listE);
		} else {
			List<InvestorsHoldInfo001Dto> newKeyList = new ArrayList<InvestorsHoldInfo001Dto>();
			newKeyList.addAll(listE);

			// 分批处理
			int part = dataCount / maxDataCountOneTime; // 分批数
			for (int i = 0; i < part; i++) {
				logger.info("查询selectAllSubHoldInfoData开始"+ com.joyintech.framework.util.DateUtil.getCurrentDateTime());
				allDataList.addAll(investorsHoldInfo001Mapper
						.selectAllSubHoldInfoData(newKeyList.subList(0, maxDataCountOneTime)));
				newKeyList.subList(0, maxDataCountOneTime).clear();
				logger.info("查询selectAllSubHoldInfoData结束"+ com.joyintech.framework.util.DateUtil.getCurrentDateTime());
			}
			// 最后剩下的数据
			if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newKeyList)) {
				logger.info("查询selectAllSubHoldInfoData开始"+ com.joyintech.framework.util.DateUtil.getCurrentDateTime());
				allDataList.addAll(investorsHoldInfo001Mapper.selectAllSubHoldInfoData(newKeyList));
				logger.info("查询selectAllSubHoldInfoData结束"+ com.joyintech.framework.util.DateUtil.getCurrentDateTime());
			}
		}

		Map<String, ChbInvestholdInfoSub> allHoldInfoDataMap = StringUtil.isEmptyList(allDataList)
				? new HashMap<String, ChbInvestholdInfoSub>()
				: allDataList.stream()
				.collect(JoyinCollectors.toMap(x -> x.getRegistBankCode() + GlobalConstant.UNDER_LINE
						+ x.getIndentifyMark() + GlobalConstant.UNDER_LINE
						+ x.getPrdRegistCode() + GlobalConstant.UNDER_LINE
						+ x.getCcy() + GlobalConstant.UNDER_LINE
						+ com.joyintech.framework.util.DateUtil.format(x.getHoldDate())+ GlobalConstant.UNDER_LINE
						+ x.getSendStatus(), x -> x));

		return allHoldInfoDataMap;
	}

	/**
	 *
	 * @param dataList
	 * @param clz
	 * @return
	 */
	public List<? extends BaseEntity> convertMapToEntity(List<Map<String, String>> dataList, Class clz) {
		// 返回对象list
		List<BaseEntity> rs = new ArrayList<>();

		for (Map<String, String> map : dataList) {
			BaseEntity baseEntity = null;
			try {
				baseEntity  = (BaseEntity) clz.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			baseEntity.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
			for (String key : map.keySet()) {
				// 空数据和错误数据置为待处理
				if(key==null||key.equals("ERRMSG")){
					baseEntity.setStatus(DictCodeListExt1.维护数据状态_待处理.getValue());
					continue;
				}
				// 获取map值
				String value = map.get(key);
				// 判断是mysql数据库时。对空字符串进行加工转为null
				if(BaseCommonCheckService.isMYSQLDataBase()){
					if(StringUtil.isEmpty(value)){
						value = null;
					}
				}

				// 反射通过字段名寻找字段
				Field field = ReflectionUtils.findField(clz, subBlank(key.toLowerCase()));

				if(field==null){
					continue;
				}
				field.setAccessible(true);
				Type type = field.getGenericType();
				if("java.lang.String".equals(type.getTypeName())){
					try {
						field.set(baseEntity, value);
					} catch ( IllegalAccessException e) {
						logger.error(e.getMessage(),e);
					}
				}else if ("java.math.BigDecimal".equals(type.getTypeName())) {
					try {
						value = valueWipePercent(value);
						field.set(baseEntity ,(value == null || "".equals(value)) ? null : new BigDecimal(value));
					} catch ( IllegalAccessException e) {
						logger.error(e.getMessage(),e);
					}
				}
				else if ("java.lang.Integer".equals(type.getTypeName())) {
					try {
						value = valueWipePercent(value);
						field.set(baseEntity ,(value == null || "".equals(value)) ? 0 : Integer.valueOf(value));
					} catch (IllegalAccessException e) {
						logger.error(e.getMessage(),e);
					}
				}
				else if ("java.lang.Short".equals(type.getTypeName())) {
					try {
						value = valueWipePercent(value);
						field.set(baseEntity, (value == null || "".equals(value)) ? 0 : Short.valueOf(value));
					} catch ( IllegalAccessException e) {
						logger.error(e.getMessage(),e);
					}
				} else if ("java.lang.Long".equals(type.getTypeName())) {
					try {
						value = valueWipePercent(value);
						field.set(baseEntity, (value == null || "".equals(value)) ? 0 : Long.valueOf(value));
					} catch ( IllegalAccessException e) {
						logger.error(e.getMessage(),e);
					}
				} else if ("java.util.Date".equals(type.getTypeName())) {
					if (value != null&&""!=value) {
						try {
							field.set(baseEntity, DateUtil.parse(value));
						} catch ( IllegalAccessException e) {
							logger.error(e.getMessage(),e);
						}
					}
				} else{
					try {
						field.set(baseEntity, value);
					} catch ( IllegalAccessException e) {
						logger.error(e.getMessage(),e);
					}
				}
			}
			// 创建人
			baseEntity.setCreateUser("interf");
			// 更新人
			baseEntity.setUpdateUser("interf");
			if(baseEntity.getCreateTime() == null){
				baseEntity.setCreateTime(new Date());
			}
			if(baseEntity.getUpdateTime() == null){
				baseEntity.setUpdateTime(new Date());
			}
			// 创建人
			baseEntity.setCreateDept(CommonUtil.getDeptCode());

			rs.add(baseEntity);
		}
		return rs;
	}

	/**
	 * 功能:将表字段转换成属性字段
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：weiyuqiang 2019年4月29日
	 * @param str
	 * @return
	 * String
	 */
	public String subBlank(String str) {
		if(str==null){
			return null;
		}
		String[] strarray = str.split("_");
		if(strarray.length == 0){
			return null;
		}
		String result = strarray[0];
		for (int i = 1; i < strarray.length; i++)
			result = result + (new StringBuilder()).append(Character.toUpperCase(strarray[i].charAt(0))).append(strarray[i].substring(1)).toString();
		return result;
	}

	/**
	 * 去除百分号计算
	 * @param value
	 */
	public String valueWipePercent(String value) {
		if(StringUtil.isNotEmpty(value)){
			int cishu = this.ciShu(value,"%");
			if(cishu==1){
				value = value.replace("%","");
				BigDecimal bd = new BigDecimal(value);
				bd = bd.divide(new BigDecimal(100));
				value = String.valueOf(bd);
			}
		}
		return value;
	}

	/**
	 * 判断str1中包含str2的个数
	 * @param str1
	 * @param str2
	 * @return counter
	 */
	public static int ciShu(String str1,String str2){
		int counter = 0;
		for(int i = 0;i < str1.length() ;i++) {
			int index = str1.indexOf(str2, i);
			if (i == index) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * 根据系统参数主键查询参数值
	 *
	 * @param paraCode 参数code
	 * @return 参数值
	 * @see
	 * @since 1.0
	 */
	protected String getParaVauleByparaCode(String paraCode) {
		return getSysPara(DictCodeListExt1.子系统代码_接口.getValue(),null,
				paraCode);
	}

	/**
	 * 根据系统参数主键查询参数值
	 *
	 * @param paraCode 参数code
	 * @return 参数值
	 * @see
	 * @since 1.0
	 */
	protected String getSysPara(String sysCode, String moduleCode, String paraCode) {
		String paraCodes = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, paraCode);
		return paraCodes;
	}


	public void dealThreeInOneE(List<InvestorsHoldInfo001Dto> threeInOneE,Map<String,InvestorsHoldInfo001Dto> threeInOneMapE) {
		if(CollectionUtils.isNotEmpty(threeInOneE)){
			for(InvestorsHoldInfo001Dto tmp:threeInOneE){
				String key = tmp.getIndentifyMark() + tmp.getProdid() + tmp.getHoldDate() + tmp.getRegistBankCode() + tmp.getCcy();
				if(threeInOneMapE.get(key) == null){
					tmp.setStatus(DictCodeListExt2.数据状态_有效.getValue());
					threeInOneMapE.put(key,tmp);
				}else{
					//处理金额
					if(threeInOneMapE.get(key).getAmount() != null && tmp.getAmount() != null) {
						threeInOneMapE.get(key).setAmount(threeInOneMapE.get(key).getAmount().add(tmp.getAmount()));
					}else if(threeInOneMapE.get(key).getAmount() == null){
						threeInOneMapE.get(key).setAmount(tmp.getAmount());
					}
					//处理份额
					if(threeInOneMapE.get(key).getQty() != null && tmp.getQty() != null) {
						threeInOneMapE.get(key).setQty(threeInOneMapE.get(key).getQty().add(tmp.getQty()));
					}else if(threeInOneMapE.get(key).getQty() == null){
						threeInOneMapE.get(key).setQty(tmp.getQty());
					}
					//处理折算人民币金额
					if(threeInOneMapE.get(key).getCnyAmt() != null && tmp.getCnyAmt() != null) {
						threeInOneMapE.get(key).setCnyAmt(threeInOneMapE.get(key).getCnyAmt().add(tmp.getCnyAmt()));
					}else if(threeInOneMapE.get(key).getCnyAmt() == null){
						threeInOneMapE.get(key).setCnyAmt(tmp.getCnyAmt());
					}
				}
			}
		}
	}


	public void dealThreeInOneA(List<InvestorsHoldInfo001Dto> threeInOneA,Map<String,InvestorsHoldInfo001Dto> threeInOneMapA) {
		if(CollectionUtils.isNotEmpty(threeInOneA)){
			for(InvestorsHoldInfo001Dto tmp:threeInOneA){
				String key = tmp.getIndentifyMark() + tmp.getProdid() + tmp.getHoldDate() + tmp.getRegistBankCode() + tmp.getCcy();
				if(threeInOneMapA.get(key) == null){
					tmp.setStatus(DictCodeListExt2.数据状态_待处理.getValue());
					threeInOneMapA.put(key,tmp);
				}else{
					//处理金额
					if(threeInOneMapA.get(key).getAmount() != null && tmp.getAmount() != null) {
						threeInOneMapA.get(key).setAmount(threeInOneMapA.get(key).getAmount().add(tmp.getAmount()));
					}else if(threeInOneMapA.get(key).getAmount() == null){
						threeInOneMapA.get(key).setAmount(tmp.getAmount());
					}
					//处理份额
					if(threeInOneMapA.get(key).getQty() != null && tmp.getQty() != null) {
						threeInOneMapA.get(key).setQty(threeInOneMapA.get(key).getQty().add(tmp.getQty()));
					}else if(threeInOneMapA.get(key).getQty() == null){
						threeInOneMapA.get(key).setQty(tmp.getQty());
					}
					//处理折算人民币金额
					if(threeInOneMapA.get(key).getCnyAmt() != null && tmp.getCnyAmt() != null) {
						threeInOneMapA.get(key).setCnyAmt(threeInOneMapA.get(key).getCnyAmt().add(tmp.getCnyAmt()));
					}else if(threeInOneMapA.get(key).getCnyAmt() == null){
						threeInOneMapA.get(key).setCnyAmt(tmp.getCnyAmt());
					}
				}
			}
		}
	}
}
