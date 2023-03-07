package com.joyintech.tams.svreport.pbc.pbcDataCheck;

import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.background.component.common.pack.common.security.UserUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.PbcReportGenerationCheckDetailMapper;
import com.joyintech.tams.common.base.entity.PbcReportGenerationCheckDetail;
import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.ExpressionUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.generationcheck.dao.PbcReportGenerationCheck001Mapper;
import com.joyintech.tams.svreport.generationcheck.dto.PbcReportGenerationCheck001Dto;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.dao.PbcCheckRuleInfo001Mapper;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.dto.PbcCheckRuleInfo001;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dao.ZG04PbcProdRaiseSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import com.joyintech.tams.svreport.submitbefore.dao.PbcSubmitBeforeCheck001Mapper;
import com.joyintech.tams.svreport.submitbefore.dto.PbcSubmitBeforeCheck001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.BigDecimalExpression.notZero;

/***
 * 人行数据校验公共类
 */
@Component
public class PbcDataCheckUtil {

    @Autowired
    private PbcCheckRuleInfo001Mapper pbcCheckRuleInfo001Mapper;

    @Autowired
    private PbcSubmitBeforeCheck001Mapper pbcSubmitBeforeCheck001Mapper;

    @Autowired
    private  PbcReportGenerationCheck001Mapper  pbcReportGenerationCheck001Mapper;

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private ZG04PbcProdRaiseSubMapper zg04pbcProdRaiseSubMapper;

    //固定值1
    private static final String  STATIC_ONE = "1";

    @Autowired
    private BatchInsertService batchInsertService;

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 用于批处理
     * @param datas 数据列表
     * @param datasKey 转换key列表.暂未使用
     * @param tableCode 报表编号
     * @param needEnityToMap 是否需要转map
     * @param ruleContent 规则大类
     * @param clz class类.转换用
     * @return
     */
    public <T> List<T> getTrueData(List datas, String backKey, String tableCode, Boolean needEnityToMap, String ruleContent, Class clz) {
        try {
            // 2.1调用共通 将list<接口表DTO>转换成List<Map<Key, Object>>格式
            List listMap = new ArrayList<>();
            if (needEnityToMap) {
                // 增加有父类字段 无法赋值情况
                Field[] declaredFields = clz.getSuperclass().getDeclaredFields();
                List<String> listColumn = new ArrayList<>();
                for (Field field : declaredFields) {
                    listColumn.add(field.getName());
                }
                Field[] fields = clz.getDeclaredFields();
                for (Field field : fields) {
                    listColumn.add(field.getName());
                }
                listMap.addAll(convertEntityToMap(datas, listColumn));
            } else {
                listMap.addAll(datas);
            }
            Map<String, Object> resultMap = checkData(listMap, null, tableCode, needEnityToMap, backKey, ruleContent);

            //List<T> dataLis = (List<T>) resultMap.get("resutlData");
            List<Map<String,Object>> dataList = (List<Map<String,Object>>) resultMap.get("resutlData");
            List backList = new ArrayList();
            for(Map<String,Object> data : dataList){
                backList.add(JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(data)), clz));
            }
            return backList;
        } catch (Exception e) {
            logger.info("报表生成校验 错误", e.getMessage());
        }
        return new ArrayList<>();
    }


    /**
     * 用于前台登记页面的数据检查
     * @param datas 数据列表
     * @param datasKey 转换key列表.暂未使用
     * @param tableCode 报表编号
     * @param needEnityToMap 是否需要转map
     * @param ruleContent 规则大类
     * @param clz class类.转换用
     * @return
     */
    public List<ExcelValidDto> getVailDateFromPage(List datas, String backKey, String tableCode, Boolean needEnityToMap, String ruleContent, Class clz) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            // 2.1调用共通 将list<接口表DTO>转换成List<Map<Key, Object>>格式
            List listMap = new ArrayList<>();
            if (needEnityToMap) {
                // 增加有父类字段 无法赋值情况
                Field[] declaredFields = clz.getSuperclass().getDeclaredFields();
                List<String> listColumn = new ArrayList<>();
                for (Field field : declaredFields) {
                    listColumn.add(field.getName());
                }
                Field[] fields = clz.getDeclaredFields();
                for (Field field : fields) {
                    listColumn.add(field.getName());
                }
                listMap.addAll(convertEntityToMap(datas, listColumn));
            } else {
                listMap.addAll(datas);
            }
            resultMap = checkData(listMap, null, tableCode, needEnityToMap, backKey, ruleContent);


        } catch (Exception e) {
            logger.info("报表生成校验 错误", e.getMessage());
        }
        return  null!=resultMap.get("checkDtos")?(List<ExcelValidDto> )resultMap.get("checkDtos"):null;
    }


    /**
     * 调用返回excel的 用于excel导入界面
     * @param datas 数据列表
     * @param datasKey 转换key列表.暂未使用
     * @param tableCode 报表编号
     * @param checkFlag 是否保存
     * @param backKey 返回编号
     * @param ruleContent 规则分类
     * @param needEnityToMap 是否需要转Map(未启用)
     * @param needBack (是否需要返回)
     * @return
     */
    public List<ExcelValidDto> getVailDate(List datas,List<String> datasKey, String tableCode,Boolean checkFlag,String backKey,String ruleContent,Boolean needEnityToMap,Boolean needBack){

        // 2.1调用共通 将list<接口表DTO>转换成List<Map<Key, Object>>格式
        List<Map<String, Object>> listMap = new ArrayList<>();
        if(needEnityToMap){
            listMap = SvrMaptoEntityUtil.convertEntityToMap(datas, datasKey);
        }else{
            listMap.addAll(datas);
        }
        Map<String,Object> resultMap = checkData(datas,datasKey,tableCode,checkFlag,backKey, ruleContent);
        List<ExcelValidDto> backdataLis = new ArrayList<>();

        List<ExcelValidDto> dataLis = null!=resultMap.get("checkDtos")?(List<ExcelValidDto>)resultMap.get("checkDtos"):new ArrayList<>();
        //是否需要返回值
        if(needBack){
            //需要则全部返回
            backdataLis.addAll(dataLis);
        }else{
            //不需要则只将错误数据组装返回
            List<ExcelValidDto> befCheckList = dataLis.stream().filter(item -> {
                return StringUtil.isNotEmpty(item.getWarnInfo())||StringUtil.isNotEmpty(item.getErrorInfo());}).collect(Collectors.toList());
            if(!StringUtil.isEmptyList(befCheckList)){
                backdataLis.addAll(befCheckList);
            }
        }
        return backdataLis;
    }

    /**
     * 数据校验入口
     * @param datas  校验数据
     * @param datasKey  废弃字段
     * @param tableCode 报表编号
     * @param checkFlag 是否需要入库错误信息
     * @param backKey
     * @param ruleContent
     * @return
     */
    public Map<String,Object> checkData(List datas,List<String> datasKey, String tableCode,Boolean checkFlag,String backKey,String ruleContent) {
        Map<String,Object > dataMap = new HashMap<>();
        // 判断传入的数据是否为空
//        if(CollectionUtils.isEmpty(datas)) {
//            return dataMap;
//        }
        //报表名称
        dataMap.put("tableDataName",tableCode);
        //规则大类(生成检查1   报送前检查3)
        dataMap.put("ruleClassify",ruleContent);
        //暂时不用吧.规则检查
//        dataMap.put("ruleIdentify","");
        //数据有效
        dataMap.put("isstart",DictCodeListExt2.数据校验规则状态_启动.getValue());
        List<PbcCheckRuleInfo001> checkList = pbcCheckRuleInfo001Mapper.searchPage(dataMap);

        dataMap = checkData(checkFlag, datas, checkList,tableCode,backKey,ruleContent);

        return dataMap;
    }

    /**
     * 单条的校验方法
     * @param checkFlag 暂时无用
     * @param infoList 校验的数据
     * @param checkList 校验的内容
     * @param tableCode 报表编号
     * @param backKey  页面查询用的主键方法
     * @param ruleContent  规则大类
     * @param backMapList 批处理需要返回的成功数据
     * @param checkDtos   ※返回的数据类
     * @param befCheckDtos 批量校验的数据回传
     */
    public boolean dataBuild(boolean checkFlag,List<Map<String,Object>> allinfoList,List<Map<String,Object>> infoList,List<PbcCheckRuleInfo001> checkList,String tableCode,String backKey,String ruleContent
            ,List<Map<String, Object>> backMapList,List<PbcSubmitBeforeCheck001Dto> checkDtos,Map<String,List<PbcReportGenerationCheckDetail>> befCheckDtos,
                             Map<String,Map<String,Object>> zg01Map,List<String>  prodList,List<String>  prodList2,List<String>  prodList3,
                             List<String>  prodList4,List<String>  prodList5){

        if(!StringUtil.isEmptyList(infoList)) {

            long startTime = SystemClock.now();
            logger.info("dataBuild校验开始-----{},当前check数量为:{},当前数据数量为:{}", DateUtil.getCurrentDateTime(), infoList.size(),checkList.size());

            for (int i = 0; i < infoList.size(); i++) {
                Map<String,Object> map = infoList.get(i);
                String Uuid = IDGenerator.generate(36);
                List<PbcReportGenerationCheckDetail> detailList = new ArrayList<>();
                for (PbcCheckRuleInfo001 dto : checkList) {
                    //DS04023的特殊处理，只有产品有到期交易再走
//                    if("DS04023".equals(dto.getRuleId())&&!prodList.contains(map.get("pbcProdId").toString())){
//                        notZero(map.get("endamtsum"));
//                        continue;
//                    }
                    //
                    if (("DS04014".equals(dto.getRuleId()) || "DS04009".equals(dto.getRuleId())||"DB0024".equals(dto.getRuleId())) && !prodList2.contains(map.get("pbcProdId").toString())) {
                        continue;
                    }
                    if("DB0023".equals(dto.getRuleId()) && !prodList3.contains(map.get("pbcProdId").toString())){
                        continue;
                    }
                    if("DB0025".equals(dto.getRuleId()) && prodList4.contains(map.get("pbcProdId").toString())){
                        continue;
                    }
                    //未到期的产品不校验
                    if("DS04023".equals(dto.getRuleId())&& !prodList4.contains(map.get("pbcProdId").toString())){
                        continue;
                    }
                    //非当期成立的产品不校验
                    if(("DB0015".equals(dto.getRuleId()) || ("DB0016".equals(dto.getRuleId()) ))&& !prodList5.contains(map.get("pbcProdId").toString())){
                        continue;
                    }
                    Boolean falg = false;
                    String catchError = null;
                    Object data = "";
                    try {
                        Map<String, Object> resultData = new HashMap<>();
                        //DS04023的特殊处理，只有产品有到期交易再走
//                        if("DS04023".equals(dto.getRuleId())&&!prodList4.contains(map.get("pbcProdId").toString())){
//                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
//                        }
                        if ("11".equals(dto.getRuleMethod())) {
                            List<Object> list1 = new ArrayList<>();//字段值集合
                            List<Object> list2 = new ArrayList<>();//运算符号集合
                            String keyStr1 = dto.getPARAMS01();//字段
                            String keyStr2 = dto.getPARAMS02();//运算符号
                            String keyStr3 = dto.getPARAMS03();
                            if(keyStr1!=null && !StringUtil.equals(keyStr1,"")) {
                                for (String d : keyStr1.split(",")) {
                                    list1.add(map.get(d));
                                }
                            }
                            if(keyStr2!=null && !StringUtil.equals(keyStr2,"")) {
                                for (String d : keyStr2.split(",")) {
                                    list2.add(d);
                                }
                            }
                            map.put("valList_M", list1);
                            map.put("valList_N", list2);
                            map.put("str_m", keyStr1);
                            map.put("str_n", keyStr2);
                            map.put("str_o", keyStr3);
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                        } else if ("12".equals(dto.getRuleMethod())) {
                            String param01 = dto.getPARAMS01();
                            String param02 = dto.getPARAMS02();
                            String param03 = dto.getPARAMS03();
                            String param04 = dto.getPARAMS04();
//                            List<Map<String, Object>> mapList = allinfoList;
                            map.put("param01", param01);
                            map.put("param02", param02);
                            map.put("param03", param03);
                            map.put("c1", param01);
                            map.put("tar1", param02);
                            map.put("tar2", param03);
                            map.put("tar3", param04);
                            map.put("mapList", allinfoList);
                            map.put("staticone", STATIC_ONE);//增加固定值1，用于表间判断《是否存在》
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                            map.remove("mapList");
                        } else if ("13".equals(dto.getRuleMethod())) {
                            String param01 = dto.getPARAMS01();
                            String param02 = dto.getPARAMS02();
                            String param03 = dto.getPARAMS03();
                            String param04 = dto.getPARAMS04();
                            map.put("c1", param01);
                            map.put("tar1", param02);
                            map.put("tar2", param03);
                            map.put("tar3", param04);
                            map.put("mapList", allinfoList);
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                            map.remove("mapList");
                        } else if ("14".equals(dto.getRuleMethod())) {
                            String param01 = dto.getPARAMS01();
                            String param02 = dto.getPARAMS02();
                            map.put("c1", param01);
                            map.put("c2", param02);
                            map.put("mapList", allinfoList);
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                            map.remove("mapList");
                        } else if ("15".equals(dto.getRuleMethod())) {
                            // 枚举值校验
                            String param01 = dto.getPARAMS01();
                            map.put(param01, param01);
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                        } else if ("16".equals(dto.getRuleMethod())) {
                            // 枚举值校验 （当key1=param01，key2必须等于param02）
                            String param01 = dto.getPARAMS01();
                            String param02 = dto.getPARAMS02();
                            map.put("str_m", param01);
                            map.put("str_n", param02);
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                        } else if ("17".equals(dto.getRuleMethod())) {
                            String param01 = dto.getPARAMS01();
                            String param02 = dto.getPARAMS02();
                            String param03 = dto.getPARAMS03();
                            map.put("str_1", param01);
                            map.put("str_2", param02);
                            map.put("str_3", param03);
                            map.put("mapList", allinfoList);
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                            map.remove("mapList");
                            map.remove("str_1");
                            map.remove("str_2");
                            map.remove("str_3");
                        } else if ("18".equals(dto.getRuleMethod())) {
                            // 变更
                            if ("2".equals(map.get("infoType"))) {
                                // DS01150
                                if ("DS01150".equals(dto.getRuleId())) {
                                    if (!zg01Map.containsKey(map.get(dto.getPARAMS01()))) {
                                        resultData.put("flag", false);
                                        resultData.put("data", map.get(dto.getPARAMS01()));
                                    } else {
                                        resultData.put("flag", true);
                                    }
                                } else {
                                    // 存在
                                    if (zg01Map.containsKey(map.get(dto.getPARAMS01()))) {
                                        resultData = checkZG01Data(dto, map, zg01Map);
                                    } else {
                                        // 存在变更没有 上期
                                        resultData.put("flag", false);
                                        resultData.put("data", map.get(dto.getCheckedFiledValue()));
                                    }
                                }
                            } else {
                                // 新增
                                resultData.put("flag", true);
                            }
                        } else if ("19".equals(dto.getRuleMethod())) {
                            // 变更
                            if ("2".equals(map.get("infoType"))) {
                                // 存在
                                if (zg01Map.containsKey(map.get(dto.getPARAMS01()))) {
                                    resultData = checkZG01DataChange(dto, map, zg01Map);
                                } else {
                                    // 存在变更没有 上期
                                    resultData.put("flag", false);
                                    resultData.put("data", map.get(dto.getCheckedFiledValue()));
                                }
                            } else {
                                // 新增
                                resultData.put("flag", true);
                            }
                        } else if ("20".equals(dto.getRuleMethod())) {
                            String param01 = dto.getPARAMS01();
                            String param02 = dto.getPARAMS02();
                            String param03 = dto.getPARAMS03();
                            map.put("str_1", param01);
                            map.put("str_2", param02);
                            map.put("str_3", param03);
                            map.put("mapList", allinfoList);
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                            map.remove("mapList");
                            map.remove("str_1");
                            map.remove("str_2");
                            map.remove("str_3");
                        }  else if ("21".equals(dto.getRuleMethod())) {
                            String param01 = dto.getPARAMS01();
                            String param02 = dto.getPARAMS02();
                            String param03 = dto.getPARAMS03();
                            String param04 = dto.getPARAMS04();
                            map.put("str_1", param01);
                            map.put("str_2", param02);
                            map.put("str_3", param03);
                            map.put("str_4", param04);
                            map.put("TempMap", map);
                            map.put("mapList", allinfoList);
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                            map.remove("TempMap");
                            map.remove("mapList");
                            map.remove("str_1");
                            map.remove("str_2");
                            map.remove("str_3");
                            map.remove("str_4");
                        }else if ("22".equals(dto.getRuleMethod())) {
                            String param01 = dto.getPARAMS01();
                            String param02 = dto.getPARAMS02();
                            String param03 = dto.getPARAMS03();
                            String param04 = dto.getPARAMS04();
                            List<Object> list1 = new ArrayList<>();//字段值集合
                            List<Object> list2 = new ArrayList<>();//运算符号集合
                            if(param02!=null && !StringUtil.equals(param02,"")) {
                                for (String d : param02.split(",")) {
                                    list1.add(map.get(d));
                                }
                            }
                            if(param03!=null && !StringUtil.equals(param03,"")) {
                                for (String d : param03.split(",")) {
                                    list2.add(d);
                                }
                            }
                            map.put("str_1", param01);
                            map.put("str_2", param02);
                            map.put("str_3", param03);
                            map.put("str_4", param04);
                            map.put("valList_M", list1);
                            map.put("valList_N", list2);
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                            map.remove("str_1");
                            map.remove("str_2");
                            map.remove("str_3");
                            map.remove("str_4");
                            map.remove("valList_M");
                            map.remove("valList_N");
                        }else{
                            //默认校验方式
                            resultData = (Map) ExpressionUtil.execute(dto.getRuleParam(), map);
                        }

                        //字段一定有值.报错则认为方法有错.返回false
                        falg = (Boolean) resultData.get("flag");
                        //为false有值.true没值
                        data = resultData.get("data");
                    } catch (Exception e) {
                        logger.error("数据检查错误,记录错误信息:", e);
                        catchError = e.getMessage();
                    } finally {
                        //组装返回信息(目前需求要求正反都返回.目前组装先做)
                        detailList.add(buildResultData(falg, Uuid, dto, catchError, data,i,String.valueOf(map.get("befCheckDataId"))));
                    }
                }

                //处理本条数据所有的处理信息
                checkDtos.add(createChbInterfErrdata(detailList, backKey, map, Uuid, tableCode, backMapList, befCheckDtos,i));
            }

            long endTime = SystemClock.now();
            logger.info("dataBuild校验结束-----，耗时: " + (endTime - startTime) / 1000 + "秒（" + (endTime - startTime) + "毫秒）");

            return true;
        }

        return false;
    }

    /**
     * 校验 ZG01 上期数据
     *
     * @param dto      校验规则配置
     * @param map      当条数据
     * @param zg01Data 上期源数据
     * @return 校验结果
     */
    public Map<String, Object> checkZG01Data(PbcCheckRuleInfo001 dto, Map<String, Object> map, Map<String, Map<String, Object>> zg01Data) {
        Map<String, Object> resultMap = new HashMap<>();
        boolean flag = false;
        // 校验key
        String filedValue = dto.getCheckedFiledValue();
        // 人行产品代码
        String params01 = dto.getPARAMS01();
        // 源数据
        String strTarget = "";
        // 当前数据
        String dataTarget = "";
        if (zg01Data != null && zg01Data.size() > 0) {
            // 不为空  都为空时 不校验
            if (!StringUtil.isNotEmpty(((Map) zg01Data.get(map.get(params01))).get(filedValue)) && !StringUtil.isNotEmpty(map.get(filedValue))) {
                flag = true;
                resultMap.put("data", map.get(filedValue));
            } else {
                // 上期数据
                if (((Map) zg01Data.get(map.get(params01))).get(filedValue) instanceof Date) {
                    strTarget = DateUtil.format((Date) ((Map) zg01Data.get(map.get(params01))).get(filedValue));
                } else if (((Map) zg01Data.get(map.get(params01))).get(filedValue) instanceof BigDecimal) {
                    strTarget = String.valueOf(new BigDecimal(String.valueOf(((Map) zg01Data.get(map.get(params01))).get(filedValue))));
                } else {
                    strTarget = String.valueOf(((Map) zg01Data.get(map.get(params01))).get(filedValue));
                }
                // 当条数据
                if (map.get(filedValue) instanceof Date) {
                    dataTarget = DateUtil.format((Date) map.get(filedValue));
                } else if (map.get(filedValue) instanceof BigDecimal) {
                    dataTarget = String.valueOf(new BigDecimal(String.valueOf(map.get(filedValue))));
                } else {
                    dataTarget = String.valueOf(map.get(filedValue));
                }
                if (StringUtil.equals(dataTarget, strTarget)) {
                    flag = true;
                } else {
                    resultMap.put("data", dataTarget);
                }
            }
        }
        resultMap.put("flag", flag);
        return resultMap;
    }

    /**
     * 相同产代码 并 修改类型 为 变更 当前数据 与上期数据 至少有一处发生变更
     *
     * @param dto
     * @param map
     * @param zg01Data
     * @return
     */
    public Map<String, Object> checkZG01DataChange(PbcCheckRuleInfo001 dto, Map<String, Object> map, Map<String, Map<String, Object>> zg01Data) {
        Map<String, Object> resultMap = new HashMap<>();
        boolean flag = true;
        // 人行产品代码
        String params01 = dto.getPARAMS01();
        Map<String, Object> map1 = (Map<String, Object>) zg01Data.get(map.get(params01));
        // 相同
        if (compareIssueRegData(map, map1)) {
            flag = false;
            resultMap.put("data", map.get(dto.getCheckedFiledValue()));
        }
        resultMap.put("flag", flag);
        return resultMap;
    }

    /**
     * 变更数据 至少有一处发生变更
     *
     * @param sourceMap
     * @param targetmap
     * @return
     */
    public boolean compareIssueRegData(Map<String, Object> sourceMap, Map<String, Object> targetmap) {
        ZG01PbcPrdBaseinfoSub compare1 = genIssueDto(sourceMap);
        ZG01PbcPrdBaseinfoSub compare2 = genIssueDto(targetmap);
        return JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2));
    }

    /**
     * 生成需要比较的 数据
     *
     * @param map 源数据
     * @return 生成数据
     */
    private ZG01PbcPrdBaseinfoSub genIssueDto(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("prodName", map.get("prodName"));
//        gendto.setProdName(dto.getProdName());
        resultMap.put("pbcProdType", map.get("pbcProdType"));
//        gendto.setPbcProdType(dto.getPbcProdType());
        resultMap.put("ivstorientation", map.get("ivstorientation"));
//        gendto.setIvstorientation(dto.getIvstorientation());
        resultMap.put("prodBrand", map.get("prodBrand"));
//        gendto.setProdBrand(dto.getProdBrand());
        if (map.get("prodStage") != null && StringUtil.isNotEmpty(map.get("prodStage"))) {
            resultMap.put("prodStage", (new BigDecimal(map.get("prodStage").toString())).setScale(0, BigDecimal.ROUND_HALF_UP));
        }
        //resultMap.put("prodStage", map.get("prodStage"));
//        gendto.setProdStage(dto.getProdStage());
        resultMap.put("raiseCcy", map.get("raiseCcy"));
//        gendto.setRaiseCcy(dto.getRaiseCcy());
        resultMap.put("cashPrinCcy", map.get("cashPrinCcy"));
//        gendto.setCashPrinCcy(dto.getCashPrinCcy());
        resultMap.put("cashProfCcy", map.get("cashProfCcy"));
//        gendto.setCashProfCcy(dto.getCashProfCcy());
        resultMap.put("raiseType", map.get("raiseType"));
//        gendto.setRaiseType(dto.getRaiseType());
        resultMap.put("manageType", map.get("manageType"));
//        gendto.setManageType(dto.getManageType());
        resultMap.put("operateMode", map.get("operateMode"));
//        gendto.setOperateMode(dto.getOperateMode());
        resultMap.put("raiseVdate", map.get("raiseVdate"));
//        gendto.setRaiseVdate(dto.getRaiseVdate());
        resultMap.put("raiseEnddate", map.get("raiseEnddate"));
//        gendto.setRaiseEnddate(dto.getRaiseEnddate());
        resultMap.put("termRightFlag", map.get("termRightFlag"));
//        gendto.setTermRightFlag(dto.getTermRightFlag());
        resultMap.put("cusRedeemFlag", map.get("cusRedeemFlag"));
//        gendto.setCusRedeemFlag(dto.getCusRedeemFlag());
        resultMap.put("creditFlag", map.get("creditFlag"));
//        gendto.setCreditFlag(dto.getCreditFlag());
        resultMap.put("creditType", map.get("creditType"));
//        gendto.setCreditType(dto.getCreditType());
        resultMap.put("creditMode", map.get("creditMode"));
//        gendto.setCreditMode(dto.getCreditMode());
        resultMap.put("inDepName", map.get("inDepName"));
//        gendto.setInDepName(dto.getInDepName());
        resultMap.put("inDepCode", map.get("inDepCode"));
//        gendto.setInDepCode(dto.getInDepCode());
        resultMap.put("outDepCtry", map.get("outDepCtry"));
//        gendto.setOutDepCtry(dto.getOutDepCtry());
        resultMap.put("outDepName", map.get("outDepName"));
//        gendto.setOutDepName(dto.getOutDepName());
        resultMap.put("vdate", map.get("vdate"));
//        gendto.setVdate(dto.getVdate());
        resultMap.put("mdate", map.get("mdate"));
//        gendto.setMdate(dto.getMdate());
        resultMap.put("fiduciaryDuty", map.get("fiduciaryDuty"));
//        gendto.setFiduciaryDuty(dto.getFiduciaryDuty());
        resultMap.put("isConstracte", map.get("isConstracte"));
//        gendto.setIsConstracte(dto.getIsConstracte());
        resultMap.put("transferType", map.get("transferType"));
//        gendto.setTransferType(dto.getTransferType());
        resultMap.put("cashManaCate", map.get("cashManaCate"));
//        gendto.setCashManaCate(dto.getCashManaCate());
        resultMap.put("crossBorderFlag", map.get("crossBorderFlag"));
//        gendto.setCrossBorderFlag(dto.getCrossBorderFlag());
        resultMap.put("isPropertyFlag", map.get("isPropertyFlag"));
//        gendto.setIsPropertyFlag(dto.getIsPropertyFlag());
        resultMap.put("baseInfoSign", map.get("baseInfoSign"));
//        gendto.setBaseInfoSign(dto.getBaseInfoSign());
        return JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(resultMap)), ZG01PbcPrdBaseinfoSub.class);
    }


    /**
     * 特殊校验前处理的校验方法
     * @param infoList 业务数据
     * @param checkList 校验数据
     * @return
     */
    public Map<String,List<PbcReportGenerationCheckDetail>> befdataBuild(List<Map<String,Object>> infoList,List<PbcCheckRuleInfo001> checkList){
        Map<String,List<PbcReportGenerationCheckDetail> > checkDtos = Collections.synchronizedMap(new HashMap<>());
        for(PbcCheckRuleInfo001 check:checkList){
            //方法-主键重复校验 - 将传入的所有数据根据给到的数据进行业务主键进行拼接.循环触发.返回单条的错误记录.之后通过dataKeys进行合并
            if("10".equals(check.getRuleMethod())){
                //校验内容方法
                Set<String> keyDate = new HashSet<>();
                List<PbcReportGenerationCheckDetail> datailList = new ArrayList<>();
                if(!StringUtil.isEmptyList(infoList)){
                    for(int i=0; i<infoList.size();i++){
                        StringBuffer sb = new StringBuffer();
                        //拼接传过来的业务主键
                        int num=check.getRuleParam().split(",").length;
                        int j=1;
                        for(String ke : check.getRuleParam().split(",")){
                            sb.append(null!=infoList.get(i).get(ke)?infoList.get(i).get(ke):"");
                            if(j!=num){
                                sb.append("-");
                                j++;
                            }
                        }
                        String befCheckDataId = sb.toString() + String.valueOf(i);
                        infoList.get(i).put("befCheckDataId",befCheckDataId);
                        //如果有重复数据.记一条失败的.如果没有.就记一条成功的
                        if(!keyDate.add(sb.toString())){
                            datailList.add(buildResultData(false,null,check,null,sb.toString(),i,befCheckDataId));
                        }else{
                            datailList.add(buildResultData(true,null,check,null,sb.toString(),i,befCheckDataId));
                        }
                    }
                    //返回以行号为标记的重复主键错误信息
                    if(StringUtil.isNotEmpty(datailList)){
                        checkDtos = datailList.stream().collect(JoyinCollectors.groupingBy(
                                x -> x.getBefCheckDataId()));
                    }
                }
            }
        }
        return checkDtos;
    }

    /**
     *绑定性较强只能代码校验的规则
     * @param infoList
     * @param checkList
     * @param checkDtos
     * @return
     */
    public void aboutEndDayDataBuild(List<Map<String,Object>> infoList,List<PbcCheckRuleInfo001> checkList,List<PbcSubmitBeforeCheck001Dto> checkDtos){
        List<PbcSubmitBeforeCheck001Dto> checkDealList = new ArrayList<>();
        //漏报的产品代码
        List<String> notProdList = new ArrayList<>();
        //如果检查数据为空就不进行检查
        if(!StringUtil.isEmptyList(infoList)){
            //获取期数termId 及 期数月末termIdLastDay
            String termId = infoList.get(0).get("termid") == null ?(infoList.get(0).get("termId") == null ? String.valueOf(infoList.get(0).get("raisingDate")) : String.valueOf(infoList.get(0).get("termId"))):String.valueOf(infoList.get(0).get("termid"));
            if(StringUtil.isEmpty(termId) || StringUtil.equals(termId,"null") || StringUtil.equals(termId,"")){
                return;
            }
            int year = Integer.valueOf(termId.substring(0,4));
            int month= Integer.valueOf(termId.substring(4,6));
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,year);
            cal.set(Calendar.MONTH,month-1);
            int LastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH,LastDay);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String termIdLastDay = sdf.format(cal.getTime());

            //校验数据的所有产品代码
            List<String> infoListProdId = new ArrayList<>();
            for(Map<String,Object> info:infoList){
                infoListProdId.add(String.valueOf(info.get("pbcProdId")));
            }

//            List<String> AllZG01ProdId = pbcSubmitBeforeCheck001Mapper.selectAllZG01ProdId();
            for (PbcCheckRuleInfo001 check : checkList) {
                //DB0026  ZG04  过了预期终止日期尚未终止的资管产品没有报送募集信息,可能漏报产品终止信息
                if ("DB0026".equals(check.getRuleId())) {
                    //根据期数月末取出过了预期终止日期尚未终止的资管产品且未报产品终止信息的产品
                    List<String> AllProdId = pbcSubmitBeforeCheck001Mapper.selectNotSubmitProdId(termIdLastDay);
                    for(String pbcProdId:AllProdId){
                        if(!infoListProdId.contains(pbcProdId)){
                            if(!notProdList.contains(pbcProdId)){
                                notProdList.add(pbcProdId);
                            }
                        }
                    }

                    //如果有存在这种产品就塞到checkDealList中
                    if(notProdList.size() >0){
                        for(String prodId:notProdList){
                            String aboutOtherData = "ZG01,ZG03";
                            //组装错误信息
                            buildErrmsg(false,check,prodId,termId,checkDealList,aboutOtherData);
                        }
                    }
                }
                //DB0038  ZG05  过了预期终止日期、尚未终止的资管产品、没有报送资产负债信息,可能漏报产品终止信息
                if ("DB0038".equals(check.getRuleId())) {
                    //根据期数月末取出过了预期终止日期尚未终止的资管产品且未报产品终止信息的产品
                    List<String> AllProdId = pbcSubmitBeforeCheck001Mapper.selectNotSubmitProdId(termIdLastDay);
                    for(String pbcProdId:AllProdId){
                        if(!infoListProdId.contains(pbcProdId)){
                            if(!notProdList.contains(pbcProdId)){
                                notProdList.add(pbcProdId);
                            }
                        }
                    }
                    //如果有存在这种产品就塞到checkDealList中
                    if(notProdList.size() >0){
                        for(String prodId:notProdList){
                            String aboutOtherData = "ZG01,ZG03";
                            //组装错误信息
                            buildErrmsg(false,check,prodId,termId,checkDealList,aboutOtherData);
                        }
                    }
                }
                //DB0039  ZG05  过了预期终止日期3个工作日后、尚未终止的资管产品没有报送资产负债信息,可能漏报产品终止信息
                if ("DB0039".equals(check.getRuleId())) {
                    List<String> AllProdId = pbcSubmitBeforeCheck001Mapper.selectNotSubmit3DayProdId(termIdLastDay);
                    for(String pbcProdId:AllProdId){
                        if(!infoListProdId.contains(pbcProdId)){
                            if(!notProdList.contains(pbcProdId)){
                                notProdList.add(pbcProdId);
                            }
                        }
                    }
                    //如果有存在这种产品就塞到checkDealList中
                    if(notProdList.size() >0){
                        for(String prodId:notProdList){
                            //关联表
                            String aboutOtherData = "ZG01,ZG03";
                            //组装错误信息
                            buildErrmsg(false,check,prodId,termId,checkDealList,aboutOtherData);
                        }
                    }
                }
            }
            //此处校验错误信息全部处理
            if(!CollectionUtils.isEmpty(checkDealList)){
                for(PbcSubmitBeforeCheck001Dto tmpList:checkDealList){
                    String DataId = tmpList.getDataId();
                    Boolean checkFlag = false;
                    for(PbcSubmitBeforeCheck001Dto tmp:checkDtos){
                        String tmpDataId = tmp.getDataId();
                        if (StringUtil.equals(DataId,tmpDataId)){
                            checkFlag = true;
                        }
                    }
                    if(!checkFlag){
                        checkDtos.add(tmpList);
                    }else {
                        checkDtos.stream().forEach(tmp ->{
                            String tmpDataId = tmp.getDataId();
                            if(StringUtil.equals(DataId,tmpDataId)){
                                if(!CollectionUtils.isEmpty(tmpList.getDetailList())) {
                                    tmpList.getDetailList().get(0).setCheckUuid(tmp.getDataUuid());

                                    tmp.getDetailList().add(tmpList.getDetailList().get(0));

                                    if(StringUtil.isEmpty(tmp.getErrorInfo()) || StringUtil.equals(tmp.getErrorInfo(),"null")) {
                                        tmp.setErrorInfo(tmpList.getErrorInfo());
                                    }else if(!StringUtil.isEmpty(tmpList.getErrorInfo()) && !StringUtil.equals(tmpList.getErrorInfo(),"null")){
                                        tmp.setErrorInfo(tmp.getErrorInfo() + ";" + tmpList.getErrorInfo());
                                    }

                                    if(StringUtil.isEmpty(tmp.getWarnInfo()) || StringUtil.equals(tmp.getWarnInfo(),"null")) {
                                        tmp.setWarnInfo(tmpList.getWarnInfo());
                                    }else if(!StringUtil.isEmpty(tmpList.getWarnInfo()) && !StringUtil.equals(tmpList.getWarnInfo(),"null")){
                                        tmp.setWarnInfo(tmp.getWarnInfo() + ";" + tmpList.getWarnInfo());
                                    }
                                }
                            }
                        });
                    }
                }
            }

        }
    }

    public void listFunctionBuild(List<ZG04PbcProdRaiseSub> infoList, List<PbcCheckRuleInfo001> checkList, List<PbcSubmitBeforeCheck001Dto> checkDtos, String termId){
        //漏报的产品代码
        List<String> notProdList = new ArrayList<>();
        //如果检查数据为空就不进行检查
        if(!StringUtil.isEmptyList(infoList)){
            //获取期数
            for (PbcCheckRuleInfo001 check : checkList) {
                if ("DB0020".equals(check.getRuleId())) {
                    //关联表
                    String aboutOtherData = "ZG01,ZG03";
                    //如果有错误信息就塞到checkDtos中
                    if(infoList.size() >0){
                        for(ZG04PbcProdRaiseSub sub:infoList){
                            //组装错误信息
                            buildErrmsg(false,check,sub.getPbcProdId(),termId,checkDtos,aboutOtherData);
                        }
                    }else{
                        buildErrmsg(true,check,null,termId,checkDtos,aboutOtherData);
                    }
                }
            }
        }
    }

    public void listTerminationBuild(List<ZG04PbcProdRaiseSub> infoList, List<PbcCheckRuleInfo001> checkList, List<PbcSubmitBeforeCheck001Dto> checkDtos, String termId){
        //漏报的产品代码
        List<String> notProdList = new ArrayList<>();
        //如果检查数据为空就不进行检查
        if(!StringUtil.isEmptyList(infoList)){
            //获取期数
            for (PbcCheckRuleInfo001 check : checkList) {
                if ("DB0021".equals(check.getRuleId())) {
                    //关联表
                    String aboutOtherData = "ZG03";
                    //如果有错误信息就塞到checkDtos中
                    if(infoList.size() >0){
                        for(ZG04PbcProdRaiseSub sub:infoList){
                            //组装错误信息
                            buildErrmsg(false,check,sub.getPbcProdId(),termId,checkDtos,aboutOtherData);
                        }
                    }else{
                        buildErrmsg(true,check,null,termId,checkDtos,aboutOtherData);
                    }
                }
            }
        }
    }






    //组装错误信息
    public void buildErrmsg (Boolean flag,PbcCheckRuleInfo001 check, String prodId , String termId,List<PbcSubmitBeforeCheck001Dto> checkDtos,String aboutOtherData){
        PbcSubmitBeforeCheck001Dto checkErrmsg = new PbcSubmitBeforeCheck001Dto();
        List<PbcReportGenerationCheckDetail> detailList = new ArrayList<>();
        PbcReportGenerationCheckDetail detail = new PbcReportGenerationCheckDetail();
        String DatUUid = IDGenerator.generate(36);

        //组装校验信息的明细信息
        detail.setUuid(IDGenerator.generate(36));
        detail.setRuleId(check.getRuleId());
        detail.setCheckFlag(flag?"0":"1");
        detail.setCheckResult(flag?"成功":"【"+check.getRuleId()+"】"+check.getRulePrompt()+"["+prodId+"]");
        detail.setCheckUuid(DatUUid);
        detail.setRuleClassify(check.getRuleClassify());
        detail.setRuleIdentify(check.getRuleIdentify());
        detailList.add(detail);

        //组装校验结果的主信息
        checkErrmsg.setDataUuid(DatUUid);
        checkErrmsg.setDataId("产品代码"+prodId+";统计年月"+termId+";关联表："+ aboutOtherData);
        checkErrmsg.setReportName(check.getTableDataName());
        if(StringUtil.equals(check.getRuleIdentify(),"3") && !flag){
            checkErrmsg.setWarnInfo("【"+check.getRuleId()+"】"+check.getRulePrompt()+"["+prodId+"]");
        }else if(StringUtil.equals(check.getRuleIdentify(),"2") && !flag){
            checkErrmsg.setErrorInfo("【"+check.getRuleId()+"】"+check.getRulePrompt()+"["+prodId+"]");
        }
        checkErrmsg.setRowNo("产品代码"+prodId+";统计年月"+termId);
        checkErrmsg.setDetailList(detailList);
        checkErrmsg.setStatus("E");
        HashMap<String, Object> map = new HashMap<>();
        //ZHXPCP-489
        map.put("pbcProdId",prodId);
        map.put("termid",termId);
        checkErrmsg.setOrgData(JsonUtils.object2Json(map));
        checkErrmsg.setCheckDate(new Date());
        checkDtos.add(checkErrmsg);
    }

    /**
     * 数据校验
     * @param infoList 解析的数据list
     * @return 返回待处理数据
     */
    public Map<String,Object> checkData(boolean checkFlag, List<Map<String,Object>> infoList,List<PbcCheckRuleInfo001> checkList,String tableCode,String backKey,String ruleContent){
        Map<String,Object> resutlMap = new HashMap<>();

        List<Map<String, Object>> backMapList = Collections.synchronizedList(new ArrayList<>());
        //校验内容方法
        List<PbcSubmitBeforeCheck001Dto> checkDtos = Collections.synchronizedList(new ArrayList<>());
        //不需要每个数据都执行.总计执行一次即可的校验
        List<PbcCheckRuleInfo001> befCheckList = checkList.stream().filter(item -> {
            return StringUtil.equals("10", item.getRuleMethod()); }).collect(Collectors.toList());
        Map<String,List<PbcReportGenerationCheckDetail>> befCheckDtos = befdataBuild(infoList,befCheckList);

        //关于产品预期终止日的校验（并且校验对象不存在于infoList中）
        List<PbcCheckRuleInfo001> aboutEndDayCheckList = checkList.stream().filter(item -> {
            return StringUtil.equals("30", item.getRuleMethod()); }).collect(Collectors.toList());

        List<PbcCheckRuleInfo001> CheckList = checkList.stream().filter(item -> {
            return StringUtil.equals("31", item.getRuleMethod()); }).collect(Collectors.toList());

        //校验没过的直接放到checkDtos中
        if(!CollectionUtils.isEmpty(aboutEndDayCheckList)) {
            aboutEndDayDataBuild(infoList, aboutEndDayCheckList, checkDtos);
        }

        //需要每个数据都执行.每条数据都要校验一次
        List<PbcCheckRuleInfo001> dataCheckList = checkList.stream().filter(item -> {
            return (!StringUtil.equals("10", item.getRuleMethod()) && !StringUtil.equals("30", item.getRuleMethod())
            && !StringUtil.equals("31", item.getRuleMethod())
            ); }).collect(Collectors.toList());
        //对本次check进行编译,提高check执行性能
//        dataCheckList.forEach(dto -> ExpressionUtil.compile(dto.getRuleParam(),true));

        // 校验ZG01
        Map<String,Map<String,Object>> zg01Map = new HashMap<>();
        List<String>  prodList = new ArrayList<>();
        List<String>  prodList2 = new ArrayList<>();
        List<String>  prodList3 = new ArrayList<>();
        List<String>  prodList4 = new ArrayList<>();
        List<String>  prodList5 = new ArrayList<>();

        if("ZG01".equals(tableCode)){
            // 查询所有上期数据
            List<Map<String,Object>> listterm = pbcSubmitBeforeCheck001Mapper.selectZg01ListTer();
            if(!CollectionUtils.isEmpty(listterm)){
                zg01Map = listterm.stream().collect( JoyinCollectors.toMap(x -> x.get("pbcProdId").toString(), x -> x));
            }
        }
        //ZG04
        if("ZG04".equals(tableCode)){
            //期数不为空
            if(!StringUtil.isEmptyList(infoList)){
                String termId=infoList.get(0).get("termId")==null?infoList.get(0).get("raisingDate").toString():infoList.get(0).get("termId").toString();
                //查询当期所有到期的产品
                prodList = pbcSubmitBeforeCheck001Mapper.selectHaveDate(termId);
                //查询当期所有封闭式非成立月产品
                prodList2 = pbcSubmitBeforeCheck001Mapper.selectNoVdate(termId);
                //查询当期封闭式，客户赎回标识=无，非终止月份
                prodList3 = pbcSubmitBeforeCheck001Mapper.selectNoMdate(termId);
                //查询当期，发生终止的产品
                prodList4 = pbcSubmitBeforeCheck001Mapper.selecthMdate(termId);
                //查询当期，成立的产品
                prodList5 = pbcSubmitBeforeCheck001Mapper.selectVdateList(termId);
            }
        }


        //ZG04特殊校验
        if(!CollectionUtils.isEmpty(infoList)) {
            checkSpecialZg04Rule(infoList, tableCode, CheckList, checkDtos);
        }
        String startDate = DateUtil.getCurrentDateTime();

        logger.info("整体数据校验开始-----"+startDate);

        /**开启线程池校验  start*/
        //100组走一个线程
        int size = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.PBC_CHECK_POOL_SIZE_GRP) ? SVReportGlobalConstant.CHECK_POOL_SIZE_GRP : SvrDataUtil.getSysParam(SVReportGlobalConstant.PBC_CHECK_POOL_SIZE_GRP));
        long fileCount = (long)Math.ceil((double)infoList.size()/size);
        boolean success = true;
        int maxCorePoolSize = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.PBC_MAX_CHECK_POOL_SIZE) ? SVReportGlobalConstant.MAX_CHECK_POOL_SIZE : SvrDataUtil.getSysParam(SVReportGlobalConstant.PBC_MAX_CHECK_POOL_SIZE));
        ExecutorService service = new JoyinThreadPoolExecutor(maxCorePoolSize);
        List<Callable<Boolean>> tasks = new ArrayList<>();
        if (!CollectionUtils.isEmpty(infoList)){
            int start = 0;
            int end = 0;
            List<Map<String,Object>>  cpmaplist=copyMap(infoList);
            List<String> finalProdList = prodList2;
            List<String> finalProdList1 = prodList;
            List<String> finalProdList2 = prodList3;
            Map<String, Map<String, Object>> finalZg01Map = zg01Map;
            List<String> finalProdList3 = prodList4;
            List<String> finalProdList4 = prodList5;
            for (int i = 0; i < fileCount; i++ ){
                start = end;
                end = Math.min(infoList.size(), size * (i + 1));
                List<Map<String, Object>> subList = infoList.subList(start, end);

                tasks.add(new Callable<Boolean>() {
                    @Override
                    public Boolean call() {
                        try {
                            return  dataBuild(checkFlag,cpmaplist,subList,dataCheckList,tableCode,backKey,ruleContent,backMapList,checkDtos,befCheckDtos,
                                    finalZg01Map, finalProdList1, finalProdList, finalProdList2, finalProdList3,finalProdList4);
                        } catch (Exception e) {
                            logger.info(e.getMessage(),e);
                            return false;
                        }
                    }
                });

            }
        }
        // 为线程池添加任务
        List<Future<Boolean>> taskResult = null;
        try {
            taskResult = service.invokeAll(tasks);
        } catch (InterruptedException e1) {
            logger.error(e1.getMessage(),e1);
            Thread.currentThread().interrupt();
            throw new ExecutorException(e1);
        }
        for (Future<Boolean> res : taskResult) {
            try {
                success = success && res.get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error(e.getMessage(),e);
                Thread.currentThread().interrupt();
                throw new ExecutorException(e);
            }
        }
        // 任务结束，停止线程池的所有线程
        service.shutdown();
        /**开启线程池校验  end*/
        //批处理会进 前台也会进
        long spendTime = DateUtil.getBetweenDate(startDate,DateUtil.getCurrentDateTime(), DateUtil.DurationType.秒);
        String endDate = DateUtil.getCurrentDateTime();
        logger.info("整体数据校验结束-----" + endDate +",总计耗时:" + spendTime);

        if(!checkDtos.isEmpty()){
            //塞入同一时间
            Date  nowDate=new Date();
            if(!checkFlag){
                //前台导入的，塞一个标识
                checkDtos.forEach(
                        item ->{
                            item.setInFlag("02");
                            item.setUpdateTime(nowDate);
                        }
                );

            }

            //根据业务主键和类型进行删除
            List<String>  listMap=infoList.stream().map(map -> {
                return null!=map.get("dataKey")?map.get("dataKey").toString():"";
            }).collect(Collectors.toList());
            if(!checkFlag){
                //删除页面校验的错误信息
                deletAllErroData2(listMap, tableCode,ruleContent);

                 //删除复杂代码校验多出来的规则
                List<String>  listcheck=checkDtos.stream().map(item -> {
                    return null!=item.getDataId()?item.getDataId():"";
                }).collect(Collectors.toList());
                deletAllErroDataMore(listcheck, tableCode,ruleContent);
            }else {
                //删除批处理校验的错误信息
                deletAllErroData(listMap, tableCode,ruleContent);
            }
//            if(checkFlag){
                //错误信息插入
                insertErroData(checkDtos,ruleContent);
                resutlMap.put("resutlData",backMapList);
//            }
        }
        //界面才会进
        List<ExcelValidDto> dtoList = new ArrayList<>();
        for(int i=0;i<checkDtos.size();i++) {
            PbcSubmitBeforeCheck001Dto dto =checkDtos.get(i);
            dtoList.add(new ExcelValidDto(dto.getDataId(), dto.getRowNo(), dto.getErrorInfo(),
                    dto.getWarnInfo()));
        }
        dtoList = dtoList.stream().sorted(Comparator.comparing(ExcelValidDto::getValidKey)).collect(Collectors.toList());
        resutlMap.put("checkDtos",dtoList);

        return resutlMap;
    }

    /**
     * ZG04规则的特殊校验方法
     * @param infoList
     * @param tableCode
     * @param CheckList
     * @param checkDtos
     */
    public  void   checkSpecialZg04Rule(List<Map<String,Object>> infoList,String tableCode,List<PbcCheckRuleInfo001> CheckList,
                                        List<PbcSubmitBeforeCheck001Dto> checkDtos){


        //DB0020,DB0021 无数据时
        //查询所有当期在运行的产品
        if("ZG04".equals(tableCode) && infoList.get(0).get("pbcProdId")==null
                && infoList.get(0).get("buyPlace")==null
                && infoList.get(0).get("customerType1")==null
                && infoList.get(0).get("ccy")==null
                && infoList.size()==1){
            //当期运行的产品 mdate>raisingDate
            List<ZG04PbcProdRaiseSub> listFunction = zg04pbcProdRaiseSubMapper.searchList((String) infoList.get(0).get("raisingDate"));
            String termId=(String) infoList.get(0).get("raisingDate");
            //当期终止的产品 mdate<=raisingDate
            List<ZG04PbcProdRaiseSub> listTermination = zg04pbcProdRaiseSubMapper.searchListTermination((String) infoList.get(0).get("raisingDate"));
            if(infoList.get(0).get("pbcProdId")==null
                    && infoList.get(0).get("buyPlace")==null
                    && infoList.get(0).get("customerType1")==null
                    && infoList.get(0).get("ccy")==null
                    && infoList.size()==1){
                infoList.clear();
            }
            if(!StringUtil.isEmptyList(listFunction) && StringUtil.isEmptyList(infoList)){
                listFunctionBuild(listFunction,CheckList,checkDtos,termId);
            }
            if(!StringUtil.isEmptyList(listTermination) && StringUtil.isEmptyList(infoList)){
                listTerminationBuild(listTermination,CheckList,checkDtos,termId);
            }
        }

        if(!StringUtil.isEmptyList(infoList) && infoList.size()>0){
            if("ZG04".equals(tableCode) && infoList.get(0).get("pbcProdId")!=null){
                HashMap<String, String> map = new HashMap<>();
                for (Map<String, Object> objectMap : infoList) {
                    if(!objectMap.get("pbcProdId").equals((String)map.get(objectMap.get("pbcProdId")))){
                        map.put((String) objectMap.get("pbcProdId"),(String)objectMap.get("pbcProdId"));
                    }
                }
                //当期运行的产品 mdate>raisingDate
                List<ZG04PbcProdRaiseSub> listFunction = zg04pbcProdRaiseSubMapper.searchList((String) infoList.get(0).get("raisingDate"));
                Iterator<ZG04PbcProdRaiseSub> itFunction = listFunction.iterator();
                while (itFunction.hasNext()){
                    ZG04PbcProdRaiseSub sub = itFunction.next();
                    if(sub.getPbcProdId().equals(map.get(sub.getPbcProdId()))){
                        itFunction.remove();
                    }
                }

                String termId=(String) infoList.get(0).get("raisingDate");
                //当期终止的产品 mdate<=raisingDate
                List<ZG04PbcProdRaiseSub> listTermination = zg04pbcProdRaiseSubMapper.searchListTermination((String) infoList.get(0).get("raisingDate"));
                Iterator<ZG04PbcProdRaiseSub> it = listTermination.iterator();
                while (it.hasNext()){
                    ZG04PbcProdRaiseSub sub = it.next();
                    if(sub.getPbcProdId().equals(map.get(sub.getPbcProdId()))){
                        it.remove();
                    }
                }

                if(!StringUtil.isEmptyList(listFunction)){
                    listFunctionBuild(listFunction,CheckList,checkDtos,termId);
                }
                if(!StringUtil.isEmptyList(listTermination)){
                    listTerminationBuild(listTermination,CheckList,checkDtos,termId);
                }
            }
        }
    }


    /**
     * 页面导入校验信息的删除
     * @param erroList
     * @param tableName
     * @param ruleContent
     */
    public void deletAllErroData2(List<String> erroList,String tableName,String ruleContent) {
        try {
            if(DictCodeListExt2.数据校验规则大类_报送前检查.getValue().equals(ruleContent)){
                logger.info("错误明细信息数据删除开始--------------" + new Date());
                deleteDetailBeforeBatchByPart(erroList,tableName);
                logger.info("错误明细信息数据删除结束--------------" + new Date());

                logger.info("错误信息数据删除开始--------------" + new Date());
                deleteBeforeCheckBatchByPart(erroList,tableName);
                logger.info("错误信息数据删除结束--------------" + new Date());
            }
        }
        catch (Exception e) {
            logger.info("异常" + e.getMessage(),e);
            logger.error("SQL执行异常：", e);
        }
    }

    /**
     * 删除复杂校验多出来的规则
     * @param erroList
     * @param tableName
     * @param ruleContent
     */
    public void deletAllErroDataMore(List<String> erroList,String tableName,String ruleContent) {
        try {
            if(DictCodeListExt2.数据校验规则大类_报送前检查.getValue().equals(ruleContent)){
                logger.info("错误明细信息数据删除开始--------------" + new Date());
                deleteDetailBeforeBatchByPart(erroList,tableName);
                logger.info("错误明细信息数据删除结束--------------" + new Date());

                logger.info("错误信息数据删除开始--------------" + new Date());
                deleteBeforeCheckBatchByPart(erroList,tableName);
                logger.info("错误信息数据删除结束--------------" + new Date());
            }
        }
        catch (Exception e) {
            logger.info("异常" + e.getMessage(),e);
            logger.error("SQL执行异常：", e);
        }
    }

    /**start 新增删除逻辑  add  by  dongxiangyang 20201017    */
    public void deletAllErroData(List<String> erroList,String tableName,String ruleContent) {
        try {
            if(DictCodeListExt2.数据校验规则大类_报送前检查.getValue().equals(ruleContent)){
                logger.info("错误明细信息数据删除开始--------------" + new Date());
                deleteDetailBeforeBatchByPart(erroList,tableName);
                logger.info("错误明细信息数据删除结束--------------" + new Date());

                logger.info("错误信息数据删除开始--------------" + new Date());
                deleteBeforeCheckBatchByPart(erroList,tableName);
                logger.info("错误信息数据删除结束--------------" + new Date());
            }else{
                logger.info("错误明细信息数据删除开始--------------" + new Date());
                deleteDetailCheckBatchByPart(erroList,tableName);
                logger.info("错误明细信息数据删除结束--------------" + new Date());

                logger.info("错误信息数据删除开始--------------" + new Date());
                deleteGenerationCheckBatchByPart(erroList,tableName);
                logger.info("错误信息数据删除结束--------------" + new Date());
            }
        }
        catch (Exception e) {
            logger.info("异常" + e.getMessage(),e);
            logger.error("SQL执行异常：", e);
        }
    }

    private void deleteBeforeCheckBatchByPart(List<String> subList,String tableName) {
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(subList)) {

            // 主键去重
            subList = subList.stream().distinct().collect(Collectors.toList());

            int maxDataCountOneTime = 100;
            int dataCount = subList.size();
            if (dataCount <= maxDataCountOneTime) {
                pbcReportGenerationCheck001Mapper.deleteByBeforeBatchList(subList,tableName);
                return;
            }
            List<String> newDataList = new ArrayList<String>();
            newDataList.addAll(subList);

            // 分批处理
            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                pbcReportGenerationCheck001Mapper.deleteByBeforeBatchList(
                        newDataList.subList(0, maxDataCountOneTime),tableName);
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newDataList)) {
                pbcReportGenerationCheck001Mapper.deleteByBeforeBatchList(newDataList,tableName);
            }
        }
    }
    private void deleteDetailBeforeBatchByPart(List<String> subList,String tableName) {
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(subList)) {

            // 主键去重
            subList = subList.stream().distinct().collect(Collectors.toList());

            int maxDataCountOneTime = 100;
            int dataCount = subList.size();
            if (dataCount <= maxDataCountOneTime) {
                pbcReportGenerationCheck001Mapper.deleteByDetailBeBatchList(subList,tableName);
                return;
            }
            List<String> newDataList = new ArrayList<String>();
            newDataList.addAll(subList);

            // 分批处理
            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                pbcReportGenerationCheck001Mapper.deleteByDetailBeBatchList(
                        newDataList.subList(0, maxDataCountOneTime),tableName);
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newDataList)) {
                pbcReportGenerationCheck001Mapper.deleteByDetailBeBatchList(newDataList,tableName);
            }
        }
    }
    private void deleteGenerationCheckBatchByPart(List<String> subList,String tableName) {
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(subList)) {

            // 主键去重
            subList = subList.stream().distinct().collect(Collectors.toList());

            int maxDataCountOneTime = 100;
            int dataCount = subList.size();
            if (dataCount <= maxDataCountOneTime) {
                pbcReportGenerationCheck001Mapper.deleteByBatchList(subList,tableName);
                return;
            }

            List<String> newDataList = new ArrayList<String>();
            newDataList.addAll(subList);

            // 分批处理
            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                pbcReportGenerationCheck001Mapper.deleteByBatchList(
                        newDataList.subList(0, maxDataCountOneTime),tableName);
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newDataList)) {
                pbcReportGenerationCheck001Mapper.deleteByBatchList(newDataList,tableName);
            }
        }
    }

    private void deleteDetailCheckBatchByPart(List<String> subList,String tableName) {
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(subList)) {

            // 主键去重
            subList = subList.stream().distinct().collect(Collectors.toList());

            int maxDataCountOneTime = 100;
            int dataCount = subList.size();
            if (dataCount <= maxDataCountOneTime) {
                pbcReportGenerationCheck001Mapper.deleteDetailByBatchList(subList,tableName);
                return;
            }
            List<String> newDataList = new ArrayList<String>();
            newDataList.addAll(subList);

            // 分批处理
            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                pbcReportGenerationCheck001Mapper.deleteDetailByBatchList(
                        newDataList.subList(0, maxDataCountOneTime),tableName);
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newDataList)) {
                pbcReportGenerationCheck001Mapper.deleteDetailByBatchList(newDataList,tableName);
            }
        }
    }
    /**
     * 错误信息处理
     * @param flag 成功失败  表内标记为0-成功 1 失败
     * @param DatUUid 校验数据的组ID
     * @param dto  规则编号
     * @param ErrorStr 如果报错..则将错误信息的代码返回
     * @param data 失败数据会返回错误信息
     * @param index 列编号.用来匹配公共方法的
     * @return
     */
    public PbcReportGenerationCheckDetail buildResultData(Boolean flag,String DatUUid,PbcCheckRuleInfo001 dto,String ErrorStr,Object data,int index,String befCheckDataId){
        PbcReportGenerationCheckDetail detail = new PbcReportGenerationCheckDetail();
        detail.setUuid(IDGenerator.generate(36));
        detail.setRuleId(dto.getRuleId());
        detail.setCheckFlag(flag?"0":"1");
        detail.setCheckResult(StringUtil.isNotEmpty(ErrorStr)?ErrorStr:(flag?"成功":"【"+dto.getRuleId()+"】"+dto.getRulePrompt()+"["+data+"]"));
        detail.setCheckUuid(DatUUid);
        detail.setDataKey(String.valueOf(index + 2));
        detail.setBefCheckDataId(befCheckDataId);
        //规则类型传递
        detail.setRuleClassify(dto.getRuleClassify());
        detail.setRuleIdentify(dto.getRuleIdentify());
        return detail;
    }


    /**
     * 根据错误消息创建需要入库的Dto
     *

     * @param detailList 处理的结果list
     * @param backKey   用来回查的key
     * @param map   当条数据
     * @param Uuid  为详情定义的业务主键
     * @param tableCode  报表编号
     * @param backMapList  反馈的数据集
     * @param befCheckDtos 先执行的校验数据
     * @param index  查询的标识行号
     * @return 需要入库的Dto
     */
    public PbcSubmitBeforeCheck001Dto createChbInterfErrdata(List<PbcReportGenerationCheckDetail> detailList,String backKey,Map<String,Object> map,
                                                             String Uuid,String tableCode,List<Map<String, Object>> backMapList,Map<String,List<PbcReportGenerationCheckDetail>> befCheckDtos,int index) {
        StringBuffer errorInfo = new StringBuffer();
        StringBuffer warnInfo = new StringBuffer();
        //
        if (!StringUtil.isEmptyList(detailList)) {
            //组装错误提前批次的错误数据  因为无论是否成功.都会有detailList,除非一个校验都没有
            if(StringUtil.isNotEmpty(befCheckDtos)){
                List<PbcReportGenerationCheckDetail> befList = befCheckDtos.get(String.valueOf(map.get("befCheckDataId")));
                if(!StringUtil.isEmptyList(befList)){
                    for(PbcReportGenerationCheckDetail pbcReportGenerationCheckDetail : befList){
                        pbcReportGenerationCheckDetail.setCheckUuid(Uuid);
                    }
                    detailList.addAll(befList);
                }
            }
            //拿出所有失败的校验数据进行拼接
            List<PbcReportGenerationCheckDetail> tmpList = detailList.stream().filter(item -> {
                return item.getCheckFlag().equals("1");
            }).collect(Collectors.toList());
            if(!StringUtil.isEmptyList(tmpList)){
                for(PbcReportGenerationCheckDetail detail : tmpList){
                    //页面导入部分调用的都是生成检查
                    if(DictCodeListExt2.数据校验规则大类_报表生成检查.getValue().equals(detail.getRuleClassify())){
                        errorInfo.append(detail.getCheckResult()+";");
                    }else{
                        //非页面导入部分可能会出校验错误或者预警错误
                        if(DictCodeListExt2.数据校验规则标识_校验错误.getValue().equals(detail.getRuleIdentify())){
                            errorInfo.append(detail.getCheckResult()+";");
                        }else{
                            warnInfo.append(detail.getCheckResult()+";");
                        }

                    }
                }
            }else{
                //返回成功数据
                backMapList.add(map);
            }
        } else {
            //没校验返回成功数据
            backMapList.add(map);
        }
        // 拼装错误信息
        PbcSubmitBeforeCheck001Dto errodata = new PbcSubmitBeforeCheck001Dto();
        errodata.setDetailList(detailList);
        // 主键uuid
        errodata.setDataUuid(Uuid);
        //数据检查
        errodata.setCheckDate(new Date());
        // 业务主键
        errodata.setDataId(null!=map.get("dataKey")?map.get("dataKey").toString():"");

        errodata.setFlagUuid(backKey);
        //错误数据
        errodata.setOrgData(JsonUtils.object2Json(map));

        errodata.setReportName(tableCode);
        // 错误提示信息

        errodata.setErrorInfo(errorInfo.toString());
        errodata.setWarnInfo(warnInfo.toString());
        errodata.setErrMsg(errodata.getErrorInfo()+errodata.getWarnInfo());
        // 数据状态
        errodata.setStatus("E");
        errodata.setCreateUser("system");
        errodata.setUpdateUser("system");
        errodata.setCreateTime(new Date());
        errodata.setUpdateTime(new Date());
        errodata.setRowNo(map.get("dataKey").toString());
        return errodata;
    }


    private void insertErroData(List<PbcSubmitBeforeCheck001Dto> erroList,String ruleContent) {
        List<PbcReportGenerationCheckDetail> details = new ArrayList<>();
        try {
            logger.debug("错误信息数据插入开始--------------" + new Date());
            //需求要求报送前后分表.表字段基本一致
            if(DictCodeListExt2.数据校验规则大类_报送前检查.getValue().equals(ruleContent)) {
                for(PbcSubmitBeforeCheck001Dto single: erroList){
                    details.addAll(single.getDetailList());
                }
                logger.info("报送生成错误信息插入开始--------------" + DateUtil.getCurrentDateTime());
                insertBeforeCheckByPart(erroList);
                logger.info("报送生成错误信息插入结束--------------" + DateUtil.getCurrentDateTime());

                logger.info("错误明细信息数据插入开始--------------" + DateUtil.getCurrentDateTime());
                insertGenerationBeforeDetailByPart(details);
                logger.info("错误明细信息数据插入结束--------------" +DateUtil.getCurrentDateTime());
            }else{
                for(PbcReportGenerationCheck001Dto single: erroList){
                    details.addAll(single.getDetailList());
                }
                logger.info("报送生成错误信息插入开始--------------" + DateUtil.getCurrentDateTime());
                insertGenerationCheckByPart(erroList);
                logger.info("报送生成错误信息插入结束--------------" + DateUtil.getCurrentDateTime());

                //最后再入库详细
                logger.info("错误明细信息数据插入开始--------------" + DateUtil.getCurrentDateTime());
                insertGenerationDetailByPart(details);
                logger.info("错误明细信息数据插入结束--------------" +DateUtil.getCurrentDateTime());
            }

        }
        catch (Exception e) {
            logger.info("异常" + e.getMessage(),e);
            logger.info("SQL执行异常：", e);
        }
        logger.debug("错误信息数据插入结束--------------" + new Date());
    }

    private void insertBeforeCheckByPart(List<PbcSubmitBeforeCheck001Dto > dataList) {
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(dataList)) {

            // 主键去重
            dataList = dataList.stream().distinct().collect(Collectors.toList());

            if(dataList.size() < 500){
                int maxDataCountOneTime = 100;
                int dataCount = dataList.size();
                if (dataCount <= maxDataCountOneTime) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLBeforecheckist(dataList);
                    return;
                }

                List<PbcSubmitBeforeCheck001Dto > newDataList = new ArrayList<PbcSubmitBeforeCheck001Dto>();
                newDataList.addAll(dataList);

                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLBeforecheckist(newDataList.subList(0, maxDataCountOneTime));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newDataList)) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLBeforecheckist(newDataList);
                }

            }else{
                batchInsertData("PBC_SUBMIT_BEFORE_CHECK",dataList);
            }
        }
    }

    private void insertGenerationCheckByPart(List<PbcSubmitBeforeCheck001Dto > dataList) {
        logger.debug(DateUtil.getCurrentDateTime() + "============== batchInsertData =======begin=======" + dataList.size());

        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(dataList)) {

            //校验通过的数据不入主表
            Iterator<PbcSubmitBeforeCheck001Dto> itFunction = dataList.iterator();
            while (itFunction.hasNext()){
                PbcSubmitBeforeCheck001Dto sub = itFunction.next();
                List<PbcReportGenerationCheckDetail>  detailsPass =sub.getDetailList().stream().filter(item -> {
                    return item.getCheckFlag().equals("1");
                }).collect(Collectors.toList());

                if(detailsPass.size()==0){
                    itFunction.remove();
                }
            }


            if(dataList.size() < 500){
                int maxDataCountOneTime = 100;
                int dataCount = dataList.size();
                if (dataCount <= maxDataCountOneTime) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLGcheckist(dataList);
                    return;
                }

                List<PbcSubmitBeforeCheck001Dto > newDataList = new ArrayList<PbcSubmitBeforeCheck001Dto>();
                newDataList.addAll(dataList);

                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLGcheckist(newDataList.subList(0, maxDataCountOneTime));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newDataList)) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLGcheckist(newDataList);
                }
            }else{
                logger.debug(DateUtil.getCurrentDateTime() + "============== batchInsertData ======= 1 =======" + dataList.size());
                batchInsertData("PBC_REPORT_GENERATION_CHECK",dataList);
                logger.debug(DateUtil.getCurrentDateTime() + "============== batchInsertData ======= 2 =======" + dataList.size());
            }
        }

        logger.debug(DateUtil.getCurrentDateTime() + "============== batchInsertData =======end=======" + dataList.size());
    }


    /**
     * 大数据量入库
     * @param tableName
     * @param dataList
     */
    public void batchInsertData(String tableName , List<? extends Object> dataList){
        try {
            //sqlloader插入
            ImpTable table = new ImpTable(tableName);
            table.setColumns(ImportGenerator.createImpTableColumns(table.getName()));
            // 临时文件存放目录
            String path = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);

            batchInsertService.exeBatchldr(path, dataList, table);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void insertGenerationDetailByPart(List<PbcReportGenerationCheckDetail > dataList) {
        //新增只插入错误信息的记录
        dataList = dataList.stream().filter(item -> {
            return item.getCheckFlag().equals("1");
        }).collect(Collectors.toList());

        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(dataList)) {
            if(dataList.size() < 500){
                int maxDataCountOneTime = 500;
                int dataCount = dataList.size();
                if (dataCount <= maxDataCountOneTime) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLGDetailist(dataList);
                    return;
                }

                List<PbcReportGenerationCheckDetail > newDataList = new ArrayList<PbcReportGenerationCheckDetail>();
                newDataList.addAll(dataList);

                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLGDetailist(newDataList.subList(0, maxDataCountOneTime));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newDataList)) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLGDetailist(newDataList);
                }
            }else{
                batchInsertData("PBC_REPORT_GENERAT_CHECKDETAIL",dataList);
            }
        }
    }


    private void insertGenerationBeforeDetailByPart(List<PbcReportGenerationCheckDetail > dataList) {
        //新增只插入错误信息的记录
        dataList = dataList.stream().filter(item -> {
            return item.getCheckFlag().equals("1");
        }).collect(Collectors.toList());

        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(dataList)) {
            if(dataList.size() < 500){
                int maxDataCountOneTime = 500;
                int dataCount = dataList.size();
                if (dataCount <= maxDataCountOneTime) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLGBeforeDetailist(dataList);
                    return;
                }

                List<PbcReportGenerationCheckDetail > newDataList = new ArrayList<PbcReportGenerationCheckDetail>();
                newDataList.addAll(dataList);

                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLGBeforeDetailist(newDataList.subList(0, maxDataCountOneTime));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(newDataList)) {
                    pbcReportGenerationCheck001Mapper.insertByBatchLGBeforeDetailist(newDataList);
                }
            }else{
                batchInsertData("PBC_GENERAT_B_CHECKDETAIL",dataList);
            }
        }
    }

    /**
     * entity 转换 map
     *
     * @param listClass
     * @param listCloumn
     * @param <T>
     * @return
     */
    public <T> List<Map<String, Object>> convertEntityToMap(List<T> listClass, List<String> listCloumn) {
        //定义返回值 list
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            //对输入的数据类型进行循环处理
            for (T clazz : listClass) {
                Map<String, Object> map = new HashMap<>();
                //对数据库字段进行循环处理
                for (String str : listCloumn) {
                    //根据数据库字段映射类属性
                    Field field = ReflectionUtils.findField(clazz.getClass(), str);
                    field.setAccessible(true);
                    try{
                        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz.getClass());
                        Method getMethod = pd.getReadMethod();// 获得get方法
                        map.put(str, getMethod.invoke(clazz));
                    }catch (Exception e){
                        logger.debug("该字段在所转换的实体类中没有，对象名：{}，字段名：{}", clazz.getClass().getName(), str);
                        continue;
                    }
                }
                list.add(map);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * map转换工具
     * @param mapList
     * @return
     */
    public static List<Map<String, Object>>  copyMap(List<Map<String, Object>> mapList) {
        List<Map<String, Object>> tempList = Lists.newArrayList();
        for (Map<String, Object> stringObjectMap : mapList) {
            Map<String, Object> tempMap = Maps.newHashMap();
            tempMap.putAll(stringObjectMap);
            tempList.add(tempMap);
        }
        return tempList;
    }
}
