package com.joyintech.tams.svreport.interf.chb.prodinfo.service;

import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPbcSaleSvrreportMpper;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dto.PbcSaleSvrreport;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class SvrPbcSaleSvrreportFileImpService extends FileImpService {

    /**
     * 接口文件名称类型
     */
    private static final String[] FILENAME_LIST = {InterfDefine.人行中债销售区域报送数据.getCode()};

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 重复性检查字段
     */
    private static final String[] DUPLICATIED_LIST = {"ASSET_CODE"};

    /**
     * 业务主键
     */
    private static final String BUSSINESSKEY = "ASSET_CODE";

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

    /**
     * mybatis的sqlsession管理核心
     */
    @Autowired
    private SqlSessionTemplate template;

    /**
     * 基础共通mapper
     */
    @Autowired
    private BaseMapper baseMapper;

    @Autowired
    private ChbPbcSaleSvrreportMpper chbPbcSaleSvrreportMpper;

    /**
     * 执行步骤
     * @param infoList
     * @param tmpPath
     * @param table
     * @param subTable
     * @param saveFlag
     * @param dataSource
     * @return
     */
    private boolean excuteInfoByStep(List<Map<String,String>> infoList, String tmpPath, ImpTable table,
                                     ImpTable subTable, String saveFlag, String dataSource) {
        //监管客户组别区域报送数据
        List<PbcSaleSvrreport> saleCustSvrreportList;

        saleCustSvrreportList = (List<PbcSaleSvrreport>) convertMapToEntity(infoList, PbcSaleSvrreport.class, dataSource);

        if (saleCustSvrreportList != null && saleCustSvrreportList.size() > 0) {
            insertDataMerge(saleCustSvrreportList, saveFlag);
        }
        return true;
    }

    /**
     * 入库
     * @param saleCustSvrreportList
     * @param saveFlag
     */
    private void insertDataMerge(List<PbcSaleSvrreport> saleCustSvrreportList, String saveFlag) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try{
            int count = 0;
            logger.debug("人行中债销售区域报送数据插入开始--------------" + new Date());
            ChbPbcSaleSvrreportMpper batchMapper = session.getMapper(ChbPbcSaleSvrreportMpper.class);
            int batchCount = reportInsertLongCount==0?50:reportInsertLongCount;
            int batchLastIndex = batchCount;
            for(int index = 0; index < saleCustSvrreportList.size();){
                if(batchLastIndex > saleCustSvrreportList.size()-1){
                    batchLastIndex = saleCustSvrreportList.size();
                    batchMapper.mergeInfoList(saleCustSvrreportList.subList(index,batchLastIndex));
                    break;
                }else{
                    batchMapper.mergeInfoList(saleCustSvrreportList.subList(index,batchLastIndex));
                    index = batchLastIndex;
                    batchLastIndex = index + batchCount;
                }
                count ++;
                if (count % 20 ==0 ){
                    session.commit();
                    session.clearCache();
                }
            }
            logger.debug("人行中债销售区域报送数据插入结束--------------" + new Date());
            session.commit();
            session.clearCache();
        }finally {
            session.close();
        }
        logger.debug("人行中债销售区域报送数据插入结束--------------" + new Date());
    }

    
    @Override
    public String saveData(List<Map<String, String>> saleCustSvrreportList, String saveFlag, String dataSource) {
        //解析成功或失败的标识
        boolean success = excuteInfoByStep(saleCustSvrreportList, null, null, null, saveFlag, dataSource);
        if (success) {
            return "success";
        }
        return "fail";
    }

    @Override
    public String importDB(List<Map<String, String>> saleCustSvrreportList, InterfFile interfFile, String date) {
        String result = "";
        logger.debug("人行中债销售区域报送数据校验----开始-----:" +  df.format(new Date()));
        //校验方法修改
        checkDataCodeCheck(saleCustSvrreportList, interfFile.getFilename(), ChbCheckTableInfo.人行中债销售区域报送信息);
        logger.debug("人行中债销售区域报送数据校验----结束-----:" +  df.format(new Date()));
        if(CollectionUtils.isEmpty(saleCustSvrreportList)){
            return "success";
        }
        ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(InterfDefine.人行中债销售区域报送数据.getCode());
        if (interfConfig == null){
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
            return "fail";
        }
        String saveFlag = interfConfig.getContentType();
        logger.debug("批量人行中债销售区域报送数据信息----开始-----:" +  df.format(new Date()));
        result = saveData(saleCustSvrreportList,saveFlag,interfFile.getDataSource());
        logger.debug("批量人行中债销售区域报送数据信息----开始-----:" +  df.format(new Date()));
        // 更新系统配置时间
        ChbInterfUtil.updateInterfTimeBySysdate( InterfDefine.人行中债销售区域报送数据.getCode());
        return result;
    }



}
