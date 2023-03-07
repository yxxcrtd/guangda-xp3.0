package com.joyintech.tams.svreport.loaninfomanagement.service;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.handler.JoyinBookConfig;
import com.joyintech.framework.export.handler.JoyinCellConfig;
import com.joyintech.framework.export.handler.JoyinSheetConfig;
import com.joyintech.framework.export.handler.ReportCfgHandler;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dao.ChbInfoMgrResultConfigMapper;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.ChbAssetInfoSub001Dto;
import com.joyintech.tams.svreport.chb.common.util.DuplicateDataUtil;
import com.joyintech.tams.svreport.interf.chb.loandetailinfo.service.SvrLoanDetailInfoFileImpService;
import com.joyintech.tams.svreport.loaninfomanagement.dao.OperationalLoanInfo001Mapper;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;

/**
 * 贷款明细信息维护 Service
 * @author hpp
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationalLoanInfoMService extends CommonExportService<OperationalLoanInfo001Dto>{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OperationalLoanInfo001Mapper operationalLoanInfo001Mapper;

	@Autowired
	private ChbInfoMgrResultConfigMapper chbInfoMgrResultConfigMapper;

	@Autowired
	private SvrLoanDetailInfoFileImpService svrLoanDetailInfoFileImpService;

	/**
	 *
	 */
	private static final String TMP_DIR = "/tpl/";

	/**
	 * 进度条接口
	 */
	@Autowired
	private ProgressBarService progressBarService;

	/**
	 * 进度条ID
	 */
	private static final String PROCESS_KEY = "exportProcessBar";
	/**
	 * 根据前台传入的条件动态查询结果
	 * 
	 * @param param 前台条件组成的map
	 * @return 动态结果
	 */
	@SuppressWarnings("unchecked")
	public String queryData(Map<String, Object> param) {
		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/queryData",param,
				String.class);
	}

	/**
	 * 根据前台传入的条件动态查询结果
	 *
	 * @param param 前台条件组成的map
	 * @return 动态结果
	 */
	@SuppressWarnings("unchecked")
	public Page<OperationalLoanInfo001Dto> findPage(Map<String, Object> param) {
		return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/findPage",
				param,OperationalLoanInfo001Dto.class);
	}

	/**
	 * 根据主键删除数据
	 * @param param
	 */
	public void doDelete(Map<String, Object> param){
		restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/doDelete", 
				param, Integer.class);
	}
	
	/**
	 * 根据主键查询资产收益权贷款明细基本信息表数据
	 * @param map
	 * @return
	 */
	public OperationalLoanInfo001Dto selectByPrimaryKey(Map<String, Object> map){
		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/selectByPrimaryKey", map, 
				OperationalLoanInfo001Dto.class);
	}
	
	/**
	 * 查询产品资产代码
	 * @param map
	 * @return
	 */
	public Page<SelectOptionDto> queryPbcAssetCode(Map<String, Object> map){
		return restTemplate.postForPage(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/queryPbcAssetCode", map, 
				SelectOptionDto.class);
	}
	public String queryPbcAssetType(Map<String, Object> map){
		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/queryPbcAssetType", map, 
				String.class);
	}
	
	/**
	 * 保存提交
	 * @param dto
	 */
	public void doSave(OperationalLoanInfo001Dto dto){
		restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/doSave", dto, Integer.class);
	}
	
	public int checkAssetCodeExist(String assetCode){
		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/checkAssetCodeExist", assetCode, Integer.class);
	}
	
	/**
	 * 根据计划名称查询计划id以及待维护表ID
	 * @param pid
	 * @return
	 */
	public OperationalLoanInfo001Dto queryPlanInfo(String pid){
		return restTemplate.postForObject(
				SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/queryPlanInfo", pid, OperationalLoanInfo001Dto.class);
	}
	
	/**
     * 
     * 省市县联动
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     */
    public Page<SelectOptionDto> getAddressSelectOption(Map<String,Object> param) {
        return restTemplate.postForPage(
        		SvrServiceApplication.COMMON_SERVICE_NAME + "/OperationalLoanInfo/getAddressSelectOption",
            param, SelectOptionDto.class);
    }

	/**
	 * 贷款及资产收益权明细信息维护导出Excel
	 */
	public ResponseEntity<byte[]> export2(String pageId, Map<String, Object> selectParam) {
		//查出数据
		deaSearchParam(selectParam);
		// RHPTSJ-136 start
		List<OperationalLoanInfo001Dto> dataList = new ArrayList<>();
		// 选中的数据
		String selectedData =  null;

		if(selectParam.containsKey("selectedData") && (selectedData = (String)selectParam.get("selectedData")) != null){
			Map<String, Object> params = new HashMap<>();
			params.put("assetType08","08");
			// 含有选中的数据
			List<Map> waitForHandleData = JSONArray.parseArray(selectedData, Map.class);
			// 过滤出主键集合，查询对应的数据
			List<OperationalLoanInfo001Dto> satisfiedData = waitForHandleData.stream()
					.map(i -> (String) i.getOrDefault("assetUuid", null))
					.filter(StringUtil::isNotEmpty)
					.map(i -> {
						params.put("assetUuid", i);
						List<OperationalLoanInfo001Dto> result = operationalLoanInfo001Mapper.findPage(params);
						// 由于带着主键去查，要么是一条，要么是查询不到
						return result == null && result.size() > 0 ? null : result.get(0);
					}).filter(i -> i != null).collect(Collectors.toList());
			dataList.addAll(satisfiedData);
		}else{
			// 查出满足条件的全部数据
			dataList = operationalLoanInfo001Mapper.findPage(selectParam);
		}
		// RHPTSJ-136 end

		//先默认一个名词，避免出现名字为NULL
		String exportFileName = "资产收益权明细信息(ZG06)";
		// 计数器，记载有多少资产池
		int count = 0;
		// 去重：若勾选了记录，按照勾选的进行导出,注意若同一个资产池若有2条记录，则导出修改未报送那条
		List<OperationalLoanInfo001Dto> list1= new ArrayList<OperationalLoanInfo001Dto>();
		//getAssetType
		Map<String,List<OperationalLoanInfo001Dto>> groupByMap = dataList.stream()
				.collect(Collectors.groupingBy(OperationalLoanInfo001Dto::getLoanFlag));
		//存在多种类型的贷款
		if(!groupByMap.isEmpty() && groupByMap.size() > 1){
			count = groupByMap.size();
		}
		// 当超过一个资产池的情况
		if(count > 1) {
			// 导出文件
			List<File> sourceFiles = new ArrayList<>();

			for(Map.Entry<String, List<OperationalLoanInfo001Dto>> ey : groupByMap.entrySet()){
				File outFile = null;
				if(StringUtil.equals("02",ey.getKey())){
					exportFileName="除回购和拆借外贷款明细信息(ZG07)"+ GlobalConstant.UNDER_LINE
							+ DateUtil.format(new Date());
					outFile = makeFile2(
							"/com/joyintech/tams/svreport/loaninfomanagement/conf/export_confloan.xml", ey.getValue(),exportFileName,"xlsx",true);

				}else{
					exportFileName="资产收益权明细信息(ZG06)"+ GlobalConstant.UNDER_LINE
							+ DateUtil.format(new Date());
					outFile = makeFile2(
							"/com/joyintech/tams/svreport/loaninfomanagement/conf/export_conf.xml", ey.getValue(),exportFileName,"xlsx",true);
				}
				sourceFiles.add(outFile);
			}
			// 生成压缩包
			File outFile = null;
			String exportFilePath = SvrDataUtil.getSysParam(
					GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
			String zipFileName = " 贷款及资产收益权明细信息("+ com.joyintech.tams.report.interf.common.constant.DateUtil.format(new Date())+").zip";
			outFile = new SafelyFile(exportFilePath + File.separator + zipFileName);
			// 导出路径不存在则重新生成
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			try {
				ZipFileUtil.compressZip(outFile, true, sourceFiles.toArray(new SafelyFile[] {}));
			}
			catch (Exception e) {
				// 压缩文件失败后，删除所有临时文件
				for (File file : sourceFiles) {
					if (file.exists()) {
						file.delete();
					}
				}
				logger.error(e.getMessage(),e);
			}
			return ExportUtil.export(outFile.getName(), outFile, true);
		}
		// 当只有一种类型时候
		File outFile = null;
		for(Map.Entry<String, List<OperationalLoanInfo001Dto>> ey : groupByMap.entrySet()){
			if(StringUtil.equals("02",ey.getKey())){
				exportFileName="除回购和拆借外贷款明细信息(ZG07)"+ GlobalConstant.UNDER_LINE
						+ DateUtil.format(new Date());
				outFile = makeFile2(
						"/com/joyintech/tams/svreport/loaninfomanagement/conf/export_confloan.xml", ey.getValue(),exportFileName,"xlsx",true);
			}else{
				exportFileName="资产收益权明细信息(ZG06)"+ GlobalConstant.UNDER_LINE
						+ DateUtil.format(new Date());
				outFile = makeFile2(
						"/com/joyintech/tams/svreport/loaninfomanagement/conf/export_conf.xml", ey.getValue(),exportFileName,"xlsx",true);
			}
		}
		return ExportUtil.export(outFile.getName(), outFile, true);
	}

	public ResponseEntity<byte[]> export01(String pageId, Map<String, Object> selectParam) {

		//先默认一个名词，避免出现名字为NULL
		String exportFileName = "资产收益权明细信息(ZG06)";
		// 导出文件
		List<File> sourceFiles = new ArrayList<>();
		File outFile = null;
		File outFile1 = null;

		exportFileName="除回购和拆借外贷款明细信息(ZG07)导入模板";
		outFile = makeFile2(
				"/com/joyintech/tams/svreport/loaninfomanagement/conf/export_confloan.xml", null,exportFileName,"xlsx",true);
		exportFileName="资产收益权明细信息(ZG06)导入模板";
		outFile1 = makeFile2(
				"/com/joyintech/tams/svreport/loaninfomanagement/conf/export_conf.xml", null,exportFileName,"xlsx",true);

		sourceFiles.add(outFile);
		sourceFiles.add(outFile1);
		// 生成压缩包
		String exportFilePath = SvrDataUtil.getSysParam(
				GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
		String zipFileName = " 贷款及资产收益权明细信息("+ com.joyintech.tams.report.interf.common.constant.DateUtil.format(new Date())+").zip";
		outFile = new SafelyFile(exportFilePath + File.separator + zipFileName);
		// 导出路径不存在则重新生成
		if (!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}
		try {
			ZipFileUtil.compressZip(outFile, true, sourceFiles.toArray(new SafelyFile[] {}));
		}
		catch (Exception e) {
			// 压缩文件失败后，删除所有临时文件
			for (File file : sourceFiles) {
				if (file.exists()) {
					file.delete();
				}
			}
			logger.error(e.getMessage(),e);
			}
		return ExportUtil.export(outFile.getName(), outFile, true);
	}


	/**
	 * 查询参数处理
	 * @param searchParam
	 */
	private void deaSearchParam(Map<String, Object> searchParam) {
		//01收益权,02贷款
		searchParam.put("assetType08","08");
	}

	/**
	 * 根据配置文件生成报表文件
	 *
	 * @param configFile 配置文件
	 * @param contents 需要被生成的报表内容
	 * @return 报表文件
	 */
	private File makeFile2(String configFile, List<OperationalLoanInfo001Dto> contents,String name, String fileType,Boolean isHasName){
		if (null == contents) {
			contents = Collections.emptyList();
		}
		if(StringUtils.isBlank(name)) {
			isHasName = false;
			// 模板文件后缀名
			fileType = GlobalConstant.POINT + fileType;
		}else {
			fileType = name + GlobalConstant.POINT + fileType;
		}
		beforeExprot();
		Map<String, String> cacheValue = CacheUtil.getAppCache(GlobalConstant.SYS_PARA_KEY, Map.class);
		cacheValue.put(GlobalConstant.EXPORT_EXCEL_TYPE, DictCodeList.是否_中债_否.getValue());
		CacheUtil.putAppCache(GlobalConstant.SYS_PARA_KEY, cacheValue);
		// 读取配置文件
		JoyinBookConfig reportConfig = this.getConfig(configFile);
		// 获取工作簿
		Workbook wb = null;
		try{
			wb = this.getWorkbook(reportConfig);
		}
		catch (IOException e){
			logger.error(e.getMessage(),e);
			throw new BusinessException(e);
		}

		// 如果导出数据大于系统参数设置的excel行数
		int count = StringUtil.integerValueOf(
				CacheUtil.getSysParam(GlobalConstant.EXPORT_EXCEL_ROW_COUNT), 50000);
		File targetFile = null;
		List<File> fileList = new ArrayList<>(contents.size() / count + 1);
		beforeCreateExcel(contents);
		try {
			while (contents.size() > count) {
				List<?> subList = contents.subList(0, count);
				contents = contents.subList(count, contents.size());
				fileList.add(this.makeSingleFile(wb, reportConfig, null, fileType, subList, new LongAdder(), 0L, count,isHasName));
			}
			fileList.add(this.makeSingleFile(wb, reportConfig, null, fileType, contents, new LongAdder(), 0L, count,isHasName));
		}
		catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		finally {
			try {
				if (null != wb) {
					wb.close();
				}
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		// 如果只有一个excel文件，不需要生成压缩文件
		if (fileList.size() == 1) {
			targetFile = fileList.get(0);
		}
		else {
			String exportFilePath = CacheUtil.getSysParam(
					GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
			String zipFileName = FileNameUtil.createFileName("zip");
			targetFile = new SafelyFile(exportFilePath + File.separator + zipFileName);
			try {
				ZipFileUtil.compressZip(targetFile, true, fileList.toArray(new SafelyFile[] {}));
			}
			catch (Exception e) {
				// 压缩文件失败后，删除所有临时文件
				for(File file : fileList) {
					if(file.exists()) {
						file.delete();
					}
				}
				logger.error(e.getMessage(),e);
			}
		}
		afterExprot(null, contents.size());
		Map<String, String> cacheValue2 = CacheUtil.getAppCache(GlobalConstant.SYS_PARA_KEY, Map.class);
		cacheValue2.put(GlobalConstant.EXPORT_EXCEL_TYPE, DictCodeList.是否_中债_是.getValue());
		CacheUtil.putAppCache(GlobalConstant.SYS_PARA_KEY, cacheValue2);

		return targetFile;
	}

	/**
	 * 解析配置文件
	 *
	 * @param configFile 配置文件
	 * @return JoyinBookConfig
	 */
	public JoyinBookConfig getConfig(String configFile){
		ReportCfgHandler handler = new ReportCfgHandler();
		// 读取配置文件
		JoyinBookConfig reportConfig = null;
		try {
			reportConfig = handler.readConfig(configFile);
		}
		catch (Exception e){
			logger.error(e.getMessage(),e);
			throw new BusinessException(e);
		}
		return reportConfig;
	}

	/**
	 * 获取配置文件指向的工作簿
	 *
	 * @param reportConfig 配置文件
	 * @return Workbook对象
	 * @throws IOException 异常
	 */
	public Workbook getWorkbook(JoyinBookConfig reportConfig)
			throws IOException{
		// 工作表对象
		Workbook wb = null;
//		String EXPORT_EXCEL_TYPE = CacheUtil.getSysParam(GlobalConstant.EXPORT_EXCEL_TYPE);
		// 模板文件后缀名
		String fileType = GlobalConstant.FILE_POSTFIX_XLSX;
		String fileName = StringUtil.splitToList(GlobalConstant.POINT, reportConfig.getTempleteFile()).get(0);
//		if(StringUtil.equals(EXPORT_EXCEL_TYPE, DictCodeList.是否_中债_否.getValue())){
//			fileType = GlobalConstant.FILE_POSTFIX_XLS;
//		}
		// 从配置文件中获取模板文件  以及文件类型
		String tempFilePath = fileName + fileType;
		File templeteFile = new SafelyFile(buildTempleteFilePath(tempFilePath));
		reportConfig.setTempleteFile(fileName + fileType);
		// 模板输入流
		InputStream stream = null;
		try{
			if (templeteFile.exists()) {
				stream = new FileInputStream(templeteFile);
			}
			else {
				stream = CommonExportService.class.getResourceAsStream(
						TMP_DIR + tempFilePath);
			}
			// 工作表对象初始化
			if (StringUtil.equals(fileType, GlobalConstant.FILE_POSTFIX_XLS)) {
				wb = new HSSFWorkbook(stream);
			}
			else if (StringUtil.equals(fileType, GlobalConstant.FILE_POSTFIX_XLSX)) {
				wb = new XSSFWorkbook(stream);
			}
			else {
				throw new BusinessException("不是一个有效的Excel文件");
			}
		}finally {
			if(stream != null){
				stream.close();
			}
		}

		return wb;
	}

	/**
	 * 根据配置文件生成报表文件
	 *
	 * @param wb 工作簿对象
	 * @param reportConfig 配置文件
	 * @param pageId 画面ID
	 * @param fileType 文件类型
	 * @param contents 需要被生成的报表内容
	 * @param current 已导出条数
	 * @param total 需要被导出的总条数
	 * @param sheetMaxRowCount 一个Sheet中最大行
	 * @return 报表文件
	 * @throws IOException 异常
	 */
	public File makeSingleFile(Workbook wb, JoyinBookConfig reportConfig, String pageId, String fileType,
							   List<?> contents, LongAdder current, long total, int sheetMaxRowCount,Boolean isHasName)
			throws IOException {
		ReportCfgHandler handler = new ReportCfgHandler();
		wb.close();
		wb = getWorkbook(reportConfig);
		// 配置文件中的工作表列表
		List<JoyinSheetConfig> sheetList = reportConfig.getSheetConfList();
		// 循环处理每个Sheet
		for (int i = 0; i < sheetList.size(); i++ ) {
			// 获取配置文件中的工作表配置
			JoyinSheetConfig sheetCfg = sheetList.get(i);
			// javaBean
			String beanName = sheetCfg.getJavaBean();
			// 获取配置文件中每行对应的单元格配置
			List<JoyinCellConfig> cellList = sheetCfg.getCellConfList();
			// 获取模板文件中的sheet页
			Sheet sheet = wb.getSheetAt(i);
			// 模板文件填充的开始行，默认从第二行开始。第一行预留为标题行
			int index = sheetCfg.getRowStart();

//			index ++;

			// // 模板文件当前sheet中的最大行
			// int maxRowNum = sheet.getLastRowNum() - sheet.getFirstRowNum();
			// // 模板文件当前sheet中的最大列
			// int maxColNum = sheet.getRow(sheet.getFirstRowNum()).getPhysicalNumberOfCells();
			// 模板文件最后一行
			Row lastRow = sheet.getRow(sheet.getLastRowNum());
			for (Object dto : contents) {
				Map<String, Object> map = BeanUtil.object2Map(dto);
				Row row = sheet.getRow(index++ );
				if (row == null) {
					handler.copyRow(wb, lastRow, sheet.createRow(index - 1), false);
					row = sheet.getRow(index - 1);
				}

				for (int ci = 0; ci < cellList.size(); ci++ ) {
					JoyinCellConfig cfg = cellList.get(ci);
					// 获取单元格
					Cell cell = row.getCell(ci, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					String propName = cfg.getBeanPropName();
					if (StringUtil.isNotEmpty(propName)) {
						Cell hCell = lastRow.getCell(ci);
						if (null == cell && null != hCell) {
							cell = row.createCell(ci);
							handler.copyCell(wb, hCell, cell, false);
							setCellStyle(wb, cell.getCellStyle());
						}
						Object value = map.get(propName);
						if (cell!=null && StringUtil.isNotEmpty(value)) {
							if (value instanceof Date) {
								cell.setCellValue(com.joyintech.framework.util.DateUtil.formatDateTime((Date)value));
							}
							else if (value instanceof String) {
								if (StringUtil.isNotEmpty(value)) {
									value = StringUtil.getLimitLengthString(value.toString(),
											16383, StringUtil.EMPTY);
								}
								cell.setCellValue((String)value);
							}
							else if (value instanceof Boolean) {
								cell.setCellValue((Boolean)value);
							}
							else if (value instanceof Calendar) {
								cell.setCellValue((Calendar)value);
							}
							else if (value instanceof RichTextString) {
								cell.setCellValue((RichTextString)value);
							}
							else if (value instanceof Double) {
								cell.setCellValue((Double)value);
							}
							else if (value instanceof BigDecimal) {
								BigDecimal cellValue = (BigDecimal)value;
								cell.setCellValue(cellValue.doubleValue());
							}
							else if (value instanceof Long) {
								Long cellValue = (Long)value;
								cell.setCellValue(cellValue.doubleValue());
							}
							else if (value instanceof Integer) {
								Integer cellValue = (Integer)value;
								cell.setCellValue(cellValue.doubleValue());
							}
							// 自定义的特殊处理
							setCellValue(beanName, cell, propName, value);
						}
					}
				}
				if(null != pageId && total > 0) {
					current.increment();
					progressBarService.updatePercent(pageId, PROCESS_KEY, current.doubleValue() / total, false);
				}
			}
		}

		// 生成后的文件名
		String outFileName = FileNameUtil.createFileName(fileType);
		if(isHasName) {
			if(StringUtil.isNotEmpty(fileType)) {
				outFileName = fileType;
			}
		}
		// 从系统配置参数中获取文件存放路径
		String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
		File file = new SafelyFile(exportFilePath + File.separator + outFileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream fileOutputStream = null;
		try{
			fileOutputStream = new FileOutputStream(file);
			wb.write(fileOutputStream);
			return file;
		}finally {
			if (fileOutputStream != null){
				fileOutputStream.close();
			}
		}

	}

	public AjaxResult checkConfirmMsgForImportData(String fileName) throws FileNotFoundException {
		AjaxResult webResult = new AjaxResult();
		if (fileName.indexOf("除回购和拆借外贷款明细信息") == -1 && fileName.indexOf("资产收益权明细信息") == -1){
			webResult.setMessage(this.getMessage("ImportFileName"), MessageType.错误消息);
			return webResult;
		}
		String tableName = "CHB_LOAN_DETAIL_INFO";
		if(fileName.indexOf("资产收益权明细信息") != -1 ){
			tableName = "CHB_LOAN_DETAIL_INFO_QY";
		}
		List<Map<String, String>> list = dataMapAnalysis(fileName,tableName);
		if(list.size()<1){
			webResult.setMessage("请勿上传空文件或导入表格中资产代码在系统中不存在", MessageType.错误消息);
			return webResult;
		}

		//贷款收益权 检查文件里的资产收益权内部编码是否重复
		if(StringUtil.equals("CHB_LOAN_DETAIL_INFO_QY",tableName)){
			Map<String,List<Map<String, String>>> listmap=new HashMap<>();
			for(Map<String, String> nmap:list){
				if(StringUtil.isNotEmpty(nmap.get("ASSET_USUFRUCT_CODE"))){
					if(listmap.containsKey(nmap.get("ASSET_USUFRUCT_CODE"))){
						listmap.get(nmap.get("ASSET_USUFRUCT_CODE")).add(nmap);
					}else{
						List<Map<String, String>>  listm=new ArrayList<>();
						listm.add(nmap);
						listmap.put(nmap.get("ASSET_USUFRUCT_CODE"),listm);
					}
				}
			}

			List<String>  listkey=new ArrayList<>();
			for(String key:listmap.keySet()){
				if(listmap.get(key).size()>1){
					listkey.add(key);
				}
			}
			if(listkey.size()>0){
				webResult.setMessage("资产收益权内部编码文件内重复"+JsonUtils.object2Json(listkey), MessageType.错误消息);
				return webResult;
			}

		}


		//查过1000条分页查询，按998条分页
		List<List<Map<String, String>>> partitionList = Lists.partition(list, 998);
		for(List<Map<String, String>> partition:partitionList){
			for(Map<String, String> strmap: partition){
				List<String> assetUsufructCodeList = operationalLoanInfo001Mapper.selectAssetUsufructCodeList(strmap);
				if(assetUsufructCodeList.size() >0){
					webResult.setMessage("资产收益权内部编码已存在"+JsonUtils.object2Json(assetUsufructCodeList), MessageType.错误消息);
					return webResult;
				}
			}
		}

		int count =0;
		for (List<Map<String, String>> partition:partitionList) {
			int i =operationalLoanInfo001Mapper.selectValidRecords(partition);
			count=count+i;
		}
		if(count>0){
			String[] checkEuplicatedKeys = ChbCheckTableInfo.贷款及资产收益权明细表.getCheckEuplicatedKeys();
			int dup = DuplicateDataUtil.countDuplicatedData(list, checkEuplicatedKeys);
			webResult.setMessage(this.getMessage("SvrDuplicateMessage",dup,count), MessageType.确认消息);
		}
		return webResult;
	}

	private List<Map<String, String>> dataMapAnalysis(String fileName,String tableName) throws FileNotFoundException {
		String prefix = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
		if(!StringUtil.startsWith(fileName, prefix)
				&& !StringUtil.startsWith(fileName, prefix.replace("/", File.separator))) {
			fileName = prefix + File.separator + fileName;
		}
		com.joyintech.framework.util.ExcelUtil excelUtil = com.joyintech.framework.util.ExcelUtil.build(fileName, null);

		List<List<String>> dataList = excelUtil.readToList(0);
		List<String> headList = dataList.get(0);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tableId", tableName);
		param.put("flag", false);
		List<ChbInfoMgrResultConfig> chbInfoMgrResultConfigList = chbInfoMgrResultConfigMapper.queryCloumnMapping(param);
		Map<String,String> map = new HashMap<>();
		chbInfoMgrResultConfigList.forEach(ele->map.put(ele.getColumnName().trim().replace("（","(").replace("）",")"),ele.getRealColumnId()));
		List<String> columnList = new ArrayList<>();
		for(int i=0;i<headList.size();++i){
			String columnName = headList.get(i);
			String temp = columnName.trim().replace("（","(").replace("）",")");
			columnName= map.get(temp);
			columnList.add(i,columnName);
		}
		//全部资产list
		List<OperationalLoanInfo001Dto> assetList = operationalLoanInfo001Mapper.selectAllAssetCode();
		Map<String, OperationalLoanInfo001Dto> collect = assetList.stream().collect(Collectors.toMap(e -> e.getAssetCode(), v -> v, (k1, k2) -> k1));
		List<Map<String,String>> list = new ArrayList<>();
		for(int i=1;i<dataList.size();++i){
			Map<String,String> subMap = new HashMap<>();
			List<String> subList = dataList.get(i);
			for(int j=0;j<subList.size();++j){
				if(StringUtil.isEmpty(subMap.get(columnList.get(j)))){
					subMap.put(columnList.get(j),subList.get(j));
				}
			}
			if (!collect.containsKey(subMap.get("ASSET_CODE"))){
				continue;
			}
			// 资产收益权 根据资产代码 映射 人行三级分类 及 资产负债项目
			OperationalLoanInfo001Dto code = collect.get(subMap.get("ASSET_CODE"));
			if ("CHB_LOAN_DETAIL_INFO_QY".equals(tableName)) {
				if ("Q01".equals(code.getPbcAssetType())) {
					subMap.put("ASSET_TYPE", "01");
					subMap.put("ASSETS_LIABILITIES", "A6100");
				} else if ("Q02".equals(code.getPbcAssetType())) {
					subMap.put("ASSET_TYPE", "02");
					subMap.put("ASSETS_LIABILITIES", "A6200");
				} else if ("Q03".equals(code.getPbcAssetType())) {
					subMap.put("ASSET_TYPE", "03");
					subMap.put("ASSETS_LIABILITIES", "A6300");
				} else if ("Q04".equals(code.getPbcAssetType())) {
					subMap.put("ASSET_TYPE", "04");
					subMap.put("ASSETS_LIABILITIES", "A6400");
				} else if ("Q05".equals(code.getPbcAssetType())) {
					subMap.put("ASSET_TYPE", "05");
					subMap.put("ASSETS_LIABILITIES", "A6500");
				} else if ("Q06".equals(code.getPbcAssetType())) {
					subMap.put("ASSET_TYPE", "06");
					subMap.put("ASSETS_LIABILITIES", "A6600");
				} else if ("Q07".equals(code.getPbcAssetType())) {
					subMap.put("ASSET_TYPE", "07");
					subMap.put("ASSETS_LIABILITIES", "A6700");
				}
			}else{
				//资产收益权
				subMap.put("ASSET_TYPE", "R02");
			}
			subMap.put("ASSET_UUID",IDGenerator.generate(36));
			subMap.put("CREATE_USER", LoginUserUtil.getUserName());
			subMap.put("UPDATE_USER",LoginUserUtil.getUserName());
			list.add(subMap);
		}

		for (ChbInfoMgrResultConfig config : chbInfoMgrResultConfigList) {
			if(!StringUtils.isEmpty(config.getResultdictCode())){
				for (Map<String, String> m : list) {
					if(m.get(config.getRealColumnId())!=null){
						if(config.getRealColumnId().equals("ASSET_TYPE")){
							if ("CHB_LOAN_DETAIL_INFO_QY".equals(tableName)) {
								continue;
							}
							if(StringUtil.equals(m.get("ASSET_TYPE").split(" ")[0],"R02")){
								m.put(config.getRealColumnId(),"08");
							}else{
								m.put(config.getRealColumnId(),m.get("ASSET_TYPE").substring(1,3));
							}
						} else if (config.getRealColumnId().equals("ASSETS_INV_DEPA")) {
							// 多选特殊处理
							if (m.get(config.getRealColumnId()) != null) {
								try {
									StringBuffer sb = new StringBuffer();
									String[] split = m.get(config.getRealColumnId()).split(",");
									Stream.of(split).forEach(e -> sb.append(e.split(" ")[0]).append(","));
									sb.deleteCharAt(sb.length() - 1);
									m.put(config.getRealColumnId(), sb.toString());
								} catch (Exception e) {
									logger.error(e.getMessage(), e);
								}
							}
						} else {
							m.put(config.getRealColumnId(), m.get(config.getRealColumnId()).split(" ")[0]);
						}
					}

				}
			}
		}
		return list;
	}

	public List<ExcelValidDto> importFileSave(String fileName) throws Exception{
		String tableName = "CHB_LOAN_DETAIL_INFO";
		if(fileName.indexOf("资产收益权明细信息") != -1 ){
			tableName = "CHB_LOAN_DETAIL_INFO_QY";
		}
		List<Map<String, String>> list = dataMapAnalysis(fileName,tableName);
		List<ExcelValidDto> listExcel = new ArrayList<ExcelValidDto>();
		svrLoanDetailInfoFileImpService.importDB(listExcel,list,new InterfFile(),null);
		if (CollectionUtils.isNotEmpty(listExcel)){
			listExcel = listExcel.stream().sorted(Comparator.comparing(e->Integer.parseInt(e.getRowNum()))).collect(Collectors.toList());
		}
		return listExcel;

	}
//	@Override
//	protected void buildExportParam(ExportParam exportParam) {
//		exportParam.config(
//				"/com/joyintech/tams/invest/trade/trdrepotrade/conf/export_conf.xml").dataSource(
//				DataSourceType.MASERT);
//	}
}
