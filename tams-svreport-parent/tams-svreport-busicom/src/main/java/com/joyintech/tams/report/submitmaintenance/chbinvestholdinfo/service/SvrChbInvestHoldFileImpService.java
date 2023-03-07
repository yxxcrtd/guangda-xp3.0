package com.joyintech.tams.report.submitmaintenance.chbinvestholdinfo.service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbFileReadMonitorInfo;
import com.joyintech.tams.common.base.entity.ChbInvestholdInfoTmp;
import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.TxtParseUtil;
import com.joyintech.tams.report.common.XmlParseUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.report.submitmaintenance.chbinvestholdinfo.dao.ChbInvestHoldInfoTmpMpper;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SvrChbInvestHoldFileImpService  {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;

    /**
     * 上游接口解析分割符
     */
    @Value("${application.reportInterfSplitCharacter}")
    private String reportInterfSplitCharacter;

    /**
     * 上游接口文件编码格式
     */
    @Value("${application.reportInterfFileEncoding}")
    public String reportInterfFileEncoding;


    /**
     * mybatis的sqlsession管理核心
     */
    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private BatchInsertService batchInsertService;


    /**
     * 执行步骤
     * @param infoList
     * @param tmpPath
     * @param table
     * @param subTable
     * @param saveFlag
     * @param
     * @return
     */
    private boolean excuteInfoByStep(List<Map<String,String>> infoList, String tmpPath, ImpTable table,
                                     ImpTable subTable, String saveFlag) {
        //投资者持有信息接口数据
        List<ChbInvestholdInfoTmp> chbInvestHoldInfoList;
        //处理TXT文件中与数据库中字段不匹配问题
        for(Map<String,String> investMap : infoList){
            //处理产品代码
            String account = investMap.get("ACCOUNT");
            investMap.remove("ACCOUNT");
            investMap.put("PRODID",account);
            //处理产品登记编码
            String regCode = investMap.get("REG_CODE");
            investMap.remove("REG_CODE");
            investMap.put("PRD_REGIST_CODE",regCode);
        }
        String bankCode = CommonUtil.selectBankCode();
        chbInvestHoldInfoList = (List<ChbInvestholdInfoTmp>) convertMapToEntity(infoList,ChbInvestholdInfoTmp.class);
        for(ChbInvestholdInfoTmp info:chbInvestHoldInfoList){
            //入库tmp表前均处理成未合并
            info.setOperateStatus(DictCodeListExt2.处理状态_中债_未合并.getValue());
            //处理数据状态
            if(!StringUtil.isNotEmpty(info.getStatus())){
                info.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            }
            //处理数据来源
            if(!StringUtil.isNotEmpty(info.getDataSource())){
                info.setDataSource("inter");
            }
            //如果上游没有填写本机构代码，进行补充
            if(!StringUtil.isNotEmpty(info.getAgencyCode())){
                info.setAgencyCode(bankCode);
            }
            //处理创建者
            if(!StringUtil.isNotEmpty(info.getCreateUser())){
                info.setCreateUser("inter");
            }
        }
        //tmp数据入库
        try {
            if (CollectionUtils.isNotEmpty(chbInvestHoldInfoList)) {
                // 临时文件存放目录
                String localPath = this.getSysPara(null,
                        null, GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
                logger.info("投资者持有信息接口数据插入tmp开始--------------" + new Date());
                insertDataMerge(chbInvestHoldInfoList, saveFlag, table, localPath);
                logger.info("投资者持有信息接口数据插入tmp结束--------------" + new Date());
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return false;
        }
        return true;
    }

    /**
     * 入库
     * @param chbInvestholdInfoTmpsList 入库数据
     * @param saveFlag 是第一次接入还是再次接入
     * @param table sqlldr入库表数据
     * @param localPath sqlldr入库文件临时保存地址
     */
    private void insertDataMerge(List<ChbInvestholdInfoTmp> chbInvestholdInfoTmpsList, String saveFlag, ImpTable table,String localPath) {
        //跑批日第一次接入持有信息全量插入
        if(StringUtil.equals(saveFlag,"true")) {
            if(chbInvestholdInfoTmpsList.size() < 1000) {
                SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
                try {
                    int count = 0;
                    ChbInvestHoldInfoTmpMpper batchMapper = session.getMapper(ChbInvestHoldInfoTmpMpper.class);
                    int batchCount = reportInsertLongCount == 0 ? 50 : reportInsertLongCount;
                    int batchLastIndex = batchCount;
                    for (int index = 0; index < chbInvestholdInfoTmpsList.size(); ) {
                        if (batchLastIndex > chbInvestholdInfoTmpsList.size() - 1) {
                            batchLastIndex = chbInvestholdInfoTmpsList.size();
                            batchMapper.insert(chbInvestholdInfoTmpsList.subList(index, batchLastIndex));
                            break;
                        } else {
                            batchMapper.insert(chbInvestholdInfoTmpsList.subList(index, batchLastIndex));
                            index = batchLastIndex;
                            batchLastIndex = index + batchCount;
                        }
                        count++;
                        if (count % 20 == 0) {
                            session.commit();
                            session.clearCache();
                        }
                    }
                    session.commit();
                    session.clearCache();
                } finally {
                    session.close();
                }
            }else{
                //大于1000条，sqlldr方式入库
                batchInsertService.exeBatchldr(localPath,chbInvestholdInfoTmpsList,table);
            }
        }else{
            //非第一次接入：增量接入
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try {
                int count = 0;
                ChbInvestHoldInfoTmpMpper batchMapper = session.getMapper(ChbInvestHoldInfoTmpMpper.class);
                int batchCount = reportInsertLongCount == 0 ? 50 : reportInsertLongCount;
                int batchLastIndex = batchCount;
                for (int index = 0; index < chbInvestholdInfoTmpsList.size(); ) {
                    if (batchLastIndex > chbInvestholdInfoTmpsList.size() - 1) {
                        batchLastIndex = chbInvestholdInfoTmpsList.size();
                        batchMapper.mergeInfoList(chbInvestholdInfoTmpsList.subList(index, batchLastIndex));
                        break;
                    } else {
                        batchMapper.mergeInfoList(chbInvestholdInfoTmpsList.subList(index, batchLastIndex));
                        index = batchLastIndex;
                        batchLastIndex = index + batchCount;
                    }
                    count++;
                    if (count % 20 == 0) {
                        session.commit();
                        session.clearCache();
                    }
                }
                session.commit();
                session.clearCache();
            } finally {
                session.close();
            }
        }
    }

    public String saveDataInTmp(List<Map<String, String>> chbSalerStlDealInfoList, String saveFlag) {
        //解析成功或失败的标识
        ImpTable table = new ImpTable("CHB_INVESTHOLD_TMP");
        table.setColumns(ImportGenerator.createImpTableColumns("CHB_INVESTHOLD_TMP"));
        boolean success = excuteInfoByStep(chbSalerStlDealInfoList, null, table, null, saveFlag);
        if (success) {
            return "success";
        }
        return "fail";
    }

    public String importTmp(List<Map<String, String>> chbInvestHoldList, String saveFlag) {
        if (CollectionUtils.isEmpty(chbInvestHoldList)){
            return "success";
        }
        //保存至投资者持有tmp表
        String saveResult = saveDataInTmp(chbInvestHoldList, saveFlag);
        return saveResult;
    }

    /**
     * 从接口中获取投资者持有信息（数据开关关闭不覆盖）
     * <ul>
     * <li>1.调用共通方法解析传入的接口文件</li>
     * <li>2.遍历解析后的数据，逐条插入验证（暂定）</li>
     * <li>3.验证中债需要验证的模块，通过则赋有效状态；不通过，则显示待处理状态</li>
     * <li>4.验证通过后，判断是否新增数据
     * <ul>
     * <li>4.1 若为新增数据，则直接insert</li>
     * <li>4.2 若非新增数据，则需读取是否覆盖开关</li>
     * <li>4.2.1 若开关打开，只更新数据配置项中的字段 4.2.2</li>
     * </ul>
     * </li>
     * <li>若开关关闭，且数据库中为接口维护，则全量更新；若数据库中为手动维护，则不更新</li>此条规则去掉
     * </ul>
     *
     * @param in
     *            输入流
     * @param interType
     *            来源系统
     * @return string 处理成功失败
     * @param tmpPath
     *            临时存放路径
     * @param suffix
     *            文件后缀名
     * @param monitorInfoDto
     *            读取上游文件监控信息dto
     */
    public String getInvestorsHoldInfoByInterf(InputStream in, String interType, String tmpPath, String suffix,
                                               ChbFileReadMonitorInfo monitorInfoDto,String saveFlag,String realName) {
        List<Map<String, String>> equityList = null;
        if ("xml".equals(suffix)) {
            try {
                equityList = XmlParseUtil.parse(in);

                if(StringUtil.isEmptyList(equityList)){
                    return "success";
                }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.error(e.getMessage(),e);
                return "fail";
            }
        } else if ("txt".equals(suffix)) {
            try {
                equityList = TxtParseUtil.parse(in, reportInterfFileEncoding, reportInterfSplitCharacter);

                if(StringUtil.isEmptyList(equityList)){
                    return "success";
                }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                logger.error(e.getMessage(),e);
                return "fail";
            }
        }
        logger.info("解析xml文件存入List完成后equityList长度："+equityList.size());
        String[] result = {};
        // 解析成功或失败的标识
        boolean success = true;

        if (CollectionUtils.isNotEmpty(equityList)) {

            //去重前处理行号与文件名称
            int dataRowNo = 2;
            for(Map<String, String> map :equityList){
                String str = String.valueOf(dataRowNo);
                map.put("DATA_ROW_NO",str);
                map.put("FILE_NAME",realName);
                dataRowNo = dataRowNo + 1;
            }

            // 设置 文件数据条数
            monitorInfoDto.setFileDataCount(BigDecimal.valueOf(equityList.size()));
            //根据五要素去重
            logger.info("去重前的数据数量："+equityList.size());
            equityList = equityList.stream().collect(Collectors.collectingAndThen(
                    Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(p -> p.get("REGIST_BANK_CODE")+"|"+p.get("ACCOUNT")+"|"+p.get("INDENTIFY_MARK")+"|"+p.get("HOLD_DATE")+"|"+p.get("CCY")+"|"+p.get("AGENCY_CODE") + "|" + p.get("FUND_ACC_PLACE")
                    ))),ArrayList::new));
            logger.info("去重后的数据数量："+equityList.size());
            //保存至tmp表
            try {
                this.importTmp(equityList,saveFlag);
            }catch (Exception e){
                logger.error(e.getMessage(),e);
                success = false;
            }
        }

        // 数据解析完成，并导入数据库成功后，将ftp文件修改名称
        if (success) {
            return "success";
        }
        // 设置 文件处理结果说明
        monitorInfoDto.setFileHandleResultMsg("文件解析失败");
        return "fail";
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
    protected String getSysPara(String sysCode, String moduleCode, String paraCode) {
        String paraCodes = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, paraCode);
        return paraCodes;

    }
}
