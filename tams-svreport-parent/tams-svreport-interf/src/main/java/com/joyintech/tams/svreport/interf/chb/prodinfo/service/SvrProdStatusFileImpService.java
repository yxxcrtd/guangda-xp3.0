package com.joyintech.tams.svreport.interf.chb.prodinfo.service;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.ChbPrdresistCodeMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.chb.proddeclare.dao.ChbPrdDeclareInfoMapper;
import com.joyintech.tams.svreport.interf.chb.proddeclare.dto.ChbPrdDeclareInfoDto;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdBaseinfoInterfMapper;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdStatusInterfMapper;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class SvrProdStatusFileImpService extends FileImpService {
	/**
	 * CHB_PRD_DEAL_NAME
	 */
	// private static final String CHB_PRD_BASEINFO = "CHB_PRD_BASEINFO";
	/**
	 * 业务主键
	 */
	private static final String BUSINESSKEY_CHB_PRD_BASEINFO = "PROD_ID";
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
	private SqlSessionTemplate template;

	@Autowired
	private ChbPrdStatusInterfMapper chbPrdStatusInterfMapper;


	private void insertDataMerge(List<ChbPrdStatusGd> assetInfoList) {
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			int count = 0;
			logger.debug("产品状态要素插入开始--------------" + new Date());
			ChbPrdStatusInterfMapper batchMapper = session.getMapper(ChbPrdStatusInterfMapper.class);
			int batchCount = reportInsertLongCount == 0 ? 50 : reportInsertLongCount;// 每批commit的个数
			int batchLastIndex = batchCount;// 每批最后一个的下标
			for (int index = 0; index < assetInfoList.size();) {
				if (batchLastIndex > assetInfoList.size() - 1) {
					batchLastIndex = assetInfoList.size();
					batchMapper.insertBatchList(assetInfoList.subList(index, batchLastIndex));
					break;// 数据插入完毕,退出循环
				} else {
					batchMapper.insertBatchList(assetInfoList.subList(index, batchLastIndex));
					index = batchLastIndex;// 设置下一批下标
					batchLastIndex = index + batchCount;
				}
				count++;
				// 每20次提交
				if (count % 20 == 0) {
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
				}
			}
			session.commit();
			// 清理缓存，防止溢出
			session.clearCache();
		} finally {
			session.close();
		}
		logger.debug("产品状态要素插入结束--------------" + new Date());
	}

	@Override
	public String saveData(List<Map<String, String>> listData, String saveFlag, String dataSource) {
		// 转dto
		List<ChbPrdStatusGd> ChbPrdStatusGdList  = (List<ChbPrdStatusGd>) convertMapToEntity(listData, ChbPrdStatusGd.class, dataSource);

		//清除历史数据
		List<Date> deleteList = ChbPrdStatusGdList.stream().map(ChbPrdStatusGd::getPosDate).collect(Collectors.toList());
		for(Date date : deleteList){
			chbPrdStatusInterfMapper.deleteByDate(date);
		}
		if (CollectionUtils.isNotEmpty(ChbPrdStatusGdList)) {
			insertDataMerge(ChbPrdStatusGdList);
		}
		return "success";
	}

	// @Override
	public String importDB(List<Map<String, String>> listData, InterfFile interfFile, String date) {
		String result="success";
//		checkDataCodeCheck(listData, interfFile.getFilename(), ChbCheckTableInfo.个人发行登记表INFO);

		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.产品状态信息接口数据.getCode());
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
            return "fail";
        }
        String saveFlag = interfConfig.getContentType();
        if(!listData.isEmpty()){
        	result=saveData(listData, saveFlag, interfFile.getDataSource());
        }
        // 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.产品状态信息接口数据.getCode());
		return result;
	}

	@Override
	public String importDB(List<ExcelValidDto> listExcel, List<Map<String, String>> listData, InterfFile interfFile, String date) {

		ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.产品状态信息接口数据.getCode());
		if (interfConfig == null){
			logger.info("CHB_INTERF_CONFIG表中无配置项！");
			return "fail";
		}
		checkImportData(listExcel,listData, interfFile.getFilename(), ChbCheckTableInfo.产品状态信息,interfConfig.getCoverFlag(), listExcel == null? "I" : "D");

		String result = "";
		if(listData.size()>0){
			result=saveData(listData, interfConfig.getCoverFlag(),listExcel == null? "I" : "D");
		}
		// 更新系统配置时间
		ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.产品状态信息接口数据.getCode());
		return result;
	}
}
