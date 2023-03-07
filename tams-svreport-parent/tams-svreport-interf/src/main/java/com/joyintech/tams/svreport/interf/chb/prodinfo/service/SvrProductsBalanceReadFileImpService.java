package com.joyintech.tams.svreport.interf.chb.prodinfo.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.constant.InterfConstants;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.interf.chb.proddeclare.dto.ChbPrdDeclareInfoDto;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.SvrChbProductsBalanceSubMapper;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dto.SvrChbProductsBalanceSub001Dto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 产品要素接口文件解析并入库
 * 
 * @author yuluan
 *
 */
@Service
public class SvrProductsBalanceReadFileImpService extends FileImpService {
	/**
	 * CHB_PRD_DEAL_NAME
	 */
	// private static final String CHB_PRD_BASEINFO = "CHB_PRD_BASEINFO";
	/**
	 * 业务主键
	 */
	private static final String BUSINESSKEY_CHB_PRD_BASEINFO = "INDENTIFY_MARK,CCY,FUND_ACC_PLACE,AGENCY_CODE,ACCOUNT";
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 数据批量一次性insert条数(单条数据字段较多)
	 */
	@Value("${application.reportInsertLongCount}")
	private int reportInsertLongCount;
	/**
	 * 上游接口文件编码格式
	 */
	@Value("${application.reportInterfFileEncoding}")
	public String reportInterfFileEncoding;
	/**
	 * 上游接口解析分割符
	 */
	@Value("${application.reportInterfSplitCharacter}")
	private String reportInterfSplitCharacter;

	/**
	 * 上游接口解析读取文件处理最大线程数
	 */
	@Value("${application.interfMaxCorePoolSize}")
	private int interfMaxCorePoolSize;
	/**
	 * 上游接口解析读取文件处理,单次sqlldr处理最大数据条数
	 */
	@Value("${application.reportInterfOneTimeSqlLdrDataCount}")
	private int reportInterfOneTimeSqlLdrDataCount;

	/**
	 * 上游接口解析读取文件处理,单次sqlldr处理最大数据条数
	 */
	@Value("${application.reportInterfOneTimeSqlLdrCommitCount}")
	private int reportInterfOneTimeSqlLdrCommitCount;
	/**
	 * sqlldr用数据库连接
	 */
	@Value("${spring.datasource.reportSqlldrDatabaseName}")
	private String reportSqlldrDatabaseName;

	@Autowired
	private BatchInsertService batchInsertService;

	private static final String CHB_PRODUCTS_BALANCE_SUB_NAME = "CHB_PRODUCTS_BALANCE_SUB";
	@Autowired
	private SvrChbProductsBalanceSubMapper chbProductsBalanceSubMapper;

	void deleteByData(String date){
		try{
			chbProductsBalanceSubMapper.deleteMoreThenDate(DateUtil.addMonthDateStr(date,-3),DateUtil.parse(date));
		}catch (Exception e){
			logger.error("清除审计署历史数据失败！",e);
		}
	}

	void insertInfoBatch(List<SvrChbProductsBalanceSub001Dto> listNE) {
		ImpTable table = new ImpTable(CHB_PRODUCTS_BALANCE_SUB_NAME);
		table.setColumns(ImportGenerator.createImpTableColumns(CHB_PRODUCTS_BALANCE_SUB_NAME));

		batchInsertService.exeBatchldr(this.getSysPara(null,
				null, GlobalConstant.COM_PROP_ACCESS_EXPORTPATH), listNE, table);
		logger.debug("审计署要素插入结束--------------" + new Date());
	}

	@Override
	public String saveData(List<Map<String, String>> listData, String saveFlag, String dataSource) {
		// 转dto
		List<SvrChbProductsBalanceSub001Dto> listNE  = (List<SvrChbProductsBalanceSub001Dto>) convertMapToEntity(listData,SvrChbProductsBalanceSub001Dto.class, dataSource);
		listNE.stream().forEach(dto-> {
			dto.setRegistSeqno(IDGenerator.generate(36));
			dto.setStatusBatch("1");
		});
		//公共入库方法
		insertInfoBatch(listNE);

		return "success";
	}

	// @Override
	public String importDB(List<Map<String, String>> listData, InterfFile interfFile, String date) {
//		List<Map<String, String>> listA =
		String result="success";
//		checkDataCodeCheck(listData, interfFile.getFilename(), ChbCheckTableInfo.个人发行登记表INFO);
		checkData(listData, interfFile.getFilename(),
		InterfDefine.审计署接口数据.getCode(), InterfDefine.审计署接口数据.getCode(),
		BUSINESSKEY_CHB_PRD_BASEINFO, BUSINESSKEY_CHB_PRD_BASEINFO);
//		listData.addAll(listA);
		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.审计署接口数据.getCode());
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
            return "fail";
        }
        String saveFlag = interfConfig.getContentType();
        if(!listData.isEmpty()){
			deleteByData(date);
        	result=saveData(listData, saveFlag, interfFile.getDataSource());
        }
        // 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.审计署接口数据.getCode());
		return result;
	}

	@Override
	public String importDB(List<ExcelValidDto> listExcel, List<Map<String, String>> listData, InterfFile interfFile, String date) {

		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.审计署接口数据.getCode());
		if (interfConfig == null){
			logger.info("CHB_INTERF_CONFIG表中无配置项！");
			return "fail";
		}

		String saveFlag = interfConfig.getContentType();
		String result = "";
		if(listData.size()>0){
			deleteByData(date);
			result=saveData(listData, listExcel == null?saveFlag:InterfConstants.SAVEFLAG_ADD, listExcel == null? "I" : "D");
		}
		// 更新系统配置时间
		ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.审计署接口数据.getCode());
		return result;
	}
}
