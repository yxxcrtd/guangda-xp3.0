package com.joyintech.tams.common.svreport.datacheck.beforeinsert.util;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dao.CommonCodeCheckDao;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dao.CommonCodeCheckMapper;
import com.joyintech.tams.systemtools.util.RedisUtil;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 枚举表达式
 */
public class EnumerateExpression extends EnumUtils {

    /**
     * 日志
     */
    protected static Logger logger = LoggerFactory.getLogger(EnumerateExpression.class);

    public enum FundStatusEnum {

        PERMIL("‰", "千分比"),

        PERCENT("%", "百分比");


        private final String status;

        private final String description;

        FundStatusEnum(String status, String description) {

            this.status = status;

            this.description = description;

        }

        public String status() {

            return status;

        }

        public String description() {

            return description;

        }
    }

    /**
     * 判断是否不包否千分号与百分号
     *
     * @param target
     * @return
     */
    public static Map<String, Object> permilIsValid(Object target) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        if (Objects.isNull(target) || StringUtils.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            return map;
        }
        if(!(permilIsValidEnum(String.valueOf(target)))){
            flag = true;
        }
        map.put("flag", flag);
        if (!flag) {
            map.put("data", target);
        }
        return map;
    }

    public static boolean permilIsValidEnum(String target){
        boolean flag = false;
        if(!StringUtil.isEmpty(target)){
            for (FundStatusEnum fundStatusEnum : FundStatusEnum.values()) {
                if (target.contains(fundStatusEnum.status())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }



    /**
     * 借款人代码应当符合编码规范时应当符合编码规范社会信用代码要求长度只能是18位，并且只能是数字或大写英文组合
     *
     * @param target
     * @return
     */
    public static Map<String, Object> insitituteCodeValid(Object target,Object condvalue) {
        if(ObjectUtils.isEmpty(condvalue)){
            return ExpressionUtil.resultMap(true,String.valueOf(target));
        }
        if(!ObjectUtils.nullSafeEquals("2",condvalue)&&
                !ObjectUtils.nullSafeEquals("3",condvalue)){
            return ExpressionUtil.resultMap(true,String.valueOf(target));
        }

        Map<String, Object> map = new HashMap<>();
        if(Objects.isNull(target) || StringUtils.isEmpty(String.valueOf(target))){
            return ExpressionUtil.resultMap(false,String.valueOf(target));
        }
        Pattern p = Pattern.compile("^[0-9A-Z]{18}");
        Matcher m = p.matcher(String.valueOf(target));
        boolean match = m.find();
        if (String.valueOf(target).length() == 18 && match) {
            return ExpressionUtil.resultMap(true,null);
        }
        return ExpressionUtil.resultMap(false,String.valueOf(target));
    }

    /**
     * DS07114
     * 当 obj1 等于 target 不校验
     * 校验 借款人代码 是统一社会信用代码(借款人类型为 3:非金融性企业)字符长度应为字符18，
     * @param target
     * @return
     */
    public static Map<String, Object> insitituteCodeValid3(Object obj1, String tar, Object target) {
        Map<String, Object> map = new HashMap<>();
        // 前置条件为空 不校验
        if (Objects.isNull(obj1) || !StringUtil.isNotEmpty(obj1)) {
            return ExpressionUtil.resultMap(true, target);
        }
        // 不满足前置条件 不校验
        if (!StringUtil.equals(obj1, tar)) {
            return ExpressionUtil.resultMap(true, target);
        }
        if (Objects.isNull(target) || StringUtils.isEmpty(String.valueOf(target))) {
            return ExpressionUtil.resultMap(false, String.valueOf(target));
        }
        Pattern p = Pattern.compile("^[0-9A-Z]{18}");
        Matcher m = p.matcher(String.valueOf(target));
        boolean match = m.find();
        if (String.valueOf(target).length() == 18 && match) {
            return ExpressionUtil.resultMap(true, null);
        }
        return ExpressionUtil.resultMap(false, String.valueOf(target));
    }

    /**
     * itemCode是否是正确的dictCode数据字典范围值
     *
     * @param dictCode 数据字典类型
     * @param itemCode 参数
     * @return check结果
     */
    public static Map<String, Object> isErrorDictCode(String dictCode, Object itemCode) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        map.put("flag", flag);
        map.put("data", "itemCode:" + itemCode + ", dictCode is null");
        if (StringUtils.isEmpty(dictCode)) {
            return map;
        }

        if (StringUtil.isNotEmpty(itemCode)) {
            List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
            if (!(null == items || items.stream().filter(item -> StringUtil.equals(item.getItemCode(), itemCode)).findFirst().orElse(null) == null)) {
                flag = true;
            }
        }
        //值为null或者空应该返回true
        if (Objects.isNull(itemCode) || StringUtil.isEmpty(String.valueOf(itemCode))){
            flag = true;
        }

        map.put("flag", flag);
        if (!flag) {
            map.put("data", itemCode);
        }
        return map;
    }

    /**
     * itemCode是否是正确的dictCode数据字典范围值
     *dictCode可配多个用','隔开
     * @param dictCode 数据字典类型
     * @param itemCode 参数
     * @return check结果
     */
    public static Map<String, Object> isErrorMoreDictCode(String dictCode, Object itemCode) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        map.put("flag", flag);
        map.put("data", "itemCode:" + itemCode + ", dictCode is null");
        if (StringUtils.isEmpty(dictCode)) {
            return map;
        }

        if (StringUtil.isNotEmpty(itemCode)) {
            for(String dCode : dictCode.split(",")) {
                List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dCode);
                if (!(null == items || items.stream().filter(item -> StringUtil.equals(item.getItemCode(), itemCode)).findFirst().orElse(null) == null)) {
                    flag = true;
                    break;
                }
            }
        }
        //值为null或者空应该返回true
        if (Objects.isNull(itemCode) || StringUtil.isEmpty(String.valueOf(itemCode))){
            flag = true;
        }

        map.put("flag", flag);
        if (!flag) {
            map.put("data", itemCode);
        }
        return map;
    }
    /**
     * itemCode截取前subStr位后是否是正确的dictCode数据字典范围值
     *
     * @param dictCode 数据字典类型
     * @param itemCode 参数
     * @return check结果
     */
    public static Map<String, Object> isErrorDictCodeSubStr(String dictCode, Object itemCode,String subStr) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", dictCode + "," + itemCode.toString() + "," +subStr);
        try {
            itemCode = itemCode.toString().substring(Integer.parseInt(subStr));         
        }catch (Exception e){
            map.put("flag", false);
            return map; 
        }
        return isErrorDictCode(dictCode,itemCode);
    }

    /**
     * 先判断值obj1是否等于obj2，再判断itemCode是否是正确的dictCode数据字典范围值
     *
     * @param obj1 判断类型1
     * @param obj2 参数
     * @param dictCodeList 数据字典类型
     * @param itemCode 参数
     * @return check结果
     */
    public static Map<String, Object> isErrorDictCodeObj(String dictCodeList, Object itemCode, Object obj1, String obj2) {
        if (!StringUtil.equals(String.valueOf(obj1), obj2)) {
            return ExpressionUtil.resultMap(true, null);
        }
        Boolean flag = false;
        for (String dictCode : dictCodeList.split(",")) {
            List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
            if (!(null == items || items.stream().filter(item -> StringUtil.equals(item.getItemCode(), String.valueOf(itemCode))).findFirst().orElse(null) == null)) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        if (!flag) {
            return ExpressionUtil.resultMap(false, String.valueOf(itemCode));
        }
        return ExpressionUtil.resultMap(true, null);
    }

    /**
     * 判断conditionValue集合（中间用逗号分开）是否正确的dictCode数据字典范围值
     *
     * @param dictCode 数据字典类型
     * @param conditionValue 参数
     *
     */
    public static Map<String, Object> judgeErrorDictCode(String dictCode, Object conditionValue) {
        if(conditionValue == null){
            return ExpressionUtil.resultMap(true,null);
        }
        if(StringUtil.isEmpty(dictCode)){
            return ExpressionUtil.resultMap(false,"脚本配置错误");
        }
        Boolean flag  = true;
        List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
        for (String d : String.valueOf(conditionValue).split(",")) {
            if (!(null == items || items.stream().filter(item -> StringUtil.equals(item.getItemCode(), d)).findFirst().orElse(null) == null)) {
                flag = true;
            }else{
                flag = false;
                break;
            }
        }
        if(!flag){
            return ExpressionUtil.resultMap(false,String.valueOf(conditionValue));
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 先判断值obj1是否等于obj2，判断conditionValue集合（中间用逗号分开）是否正确的数据字典范围值
     *
     * @param obj1 判断类型1
     * @param obj2 参数
     * @param dictCode 数据字典类型
     * @param conditionValue 参数
     * @return check结果
     */
    public static Map<String, Object> judgeErrorDictCodeobj(String dictCode, Object conditionValue, Object obj1, Object obj2) {
        if(obj1 == null){
            return ExpressionUtil.resultMap(true,null);
        }
        if(StringUtil.equals(String.valueOf(obj1),obj2)){
            if(conditionValue == null){
                return ExpressionUtil.resultMap(false,String.valueOf(conditionValue));
            }
            Boolean flag  = true;
            List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
            for (String d : String.valueOf(conditionValue).split(",")) {
                if (!(null == items || items.stream().filter(item -> StringUtil.equals(item.getItemCode(), d)).findFirst().orElse(null) == null)) {
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
            if(!flag){
                return ExpressionUtil.resultMap(false,String.valueOf(conditionValue));
            }
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 判断obj1等于obj2，同时obj3等于obj4校验通过！
     * @param obj1
     * @param obj2
     * @param obj3
     * @param obj4
     * @return
     */
    public static Map<String, Object> judgeErrorobj(Object obj1, String obj2, Object obj3, String obj4) {
        if(obj1 == null){
            return ExpressionUtil.resultMap(true,null);
        }
        if(StringUtil.equals(String.valueOf(obj1),obj2)){
            if(StringUtil.equals(String.valueOf(obj3),obj4)){
                return ExpressionUtil.resultMap(true,null);
            }
            return ExpressionUtil.resultMap(false,String.valueOf(obj3));
        }else{
            return ExpressionUtil.resultMap(true,null);
        }
    }

    /**
     * 先判断值obj1是否等于obj2，如果不等于 再判断itemCode是否是正确的dictCode数据字典范围值
     * dictCode可以有多个用","隔开
     * @param obj1 判断类型1
     * @param obj2 参数
     * @param dictCodeList 数据字典类型
     * @param itemCode 参数
     * @return check结果
     */
    public static Map<String, Object> isNotErrorDictCodeObj(String dictCodeList, Object itemCode, Object obj1, String obj2) {
        if(StringUtil.equals(String.valueOf(obj1),obj2)){
            return ExpressionUtil.resultMap(true,null);
        }
        Boolean flag = false;
        for (String dictCode : dictCodeList.split(",")) {
            List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
            if (!(null == items || items.stream().filter(item -> StringUtil.equals(item.getItemCode(), String.valueOf(itemCode))).findFirst().orElse(null) == null)) {
                flag = true;
                break;
            }else{
                flag = false;
            }
        }
        if(!flag){
            return ExpressionUtil.resultMap(false,String.valueOf(itemCode));
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * DS07070
     * 校验 借款人类型 非个人(住户) 的
     * @param dictCodeList
     * @param itemCode
     * @param obj1
     * @param obj2
     * @return
     */
    public static Map<String, Object> isNotErrorDictCodeObj2(String dictCodeList, Object itemCode, Object obj1, String obj2, String str1) {
        // 借款人类型 非个人(住户) 的 不校验
        if (Objects.isNull(obj1) || !StringUtil.isNotEmpty(obj1)) {
            return ExpressionUtil.resultMap(true, null);
        }
        if (StringUtil.equals(obj1, str1)) {
            return ExpressionUtil.resultMap(true, null);
        }
        if (StringUtil.equals(String.valueOf(obj1), obj2)) {
            return ExpressionUtil.resultMap(true, null);
        }
        Boolean flag = false;
        for (String dictCode : dictCodeList.split(",")) {
            List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
            if (!(null == items || items.stream().filter(item -> StringUtil.equals(item.getItemCode(), String.valueOf(itemCode))).findFirst().orElse(null) == null)) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        if (!flag) {
            return ExpressionUtil.resultMap(false, String.valueOf(itemCode));
        }
        return ExpressionUtil.resultMap(true, null);
    }

    //DS07113
    public static Map<String, Object> isNotErrorDictCodeObj3(String dictCodeList, Object itemCode, Object obj1, String obj2) {
        //境外不校验
        if(ObjectUtils.nullSafeEquals("6",obj1)){
            return ExpressionUtil.resultMap(true,null);
        }
        if (!StringUtil.isNotEmpty(itemCode)){
            return ExpressionUtil.resultMap(true,null);
        }
        if(StringUtil.equals(String.valueOf(obj1),obj2)){
            return ExpressionUtil.resultMap(true,null);
        }
        Boolean flag = false;
        for (String dictCode : dictCodeList.split(",")) {
            List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(dictCode);
            if (!(null == items || items.stream().filter(item -> StringUtil.equals(item.getItemCode(), String.valueOf(itemCode))).findFirst().orElse(null) == null)) {
                flag = true;
                break;
            }else{
                flag = false;
            }
        }
        if(!flag){
            return ExpressionUtil.resultMap(false,String.valueOf(itemCode));
        }
        return ExpressionUtil.resultMap(true,null);
    }
    /**
     *用于判断  当itemCode存在于itemCodeList时，a1是否为空
     * @param itemCode   前置条件key
     * @param a1         后置条件
     * @param itemCodeList 前置条件符合的集合
     * @return 为空返回true 否则返回false
     */
    public static Map<String, Object> manyDictIsNull(Object itemCode, Object a1, List<String> itemCodeList) {
        if(itemCode == null){
            return ExpressionUtil.resultMap(true,null);
        }
        if(itemCodeList == null || itemCodeList.size() == 0){
            return ExpressionUtil.resultMap(false,"校验脚本配置错误");
        }
        if(itemCodeList.contains(itemCode)){
            if(a1 != null){
                return ExpressionUtil.resultMap(false,String.valueOf(a1));
            }else{
                return ExpressionUtil.resultMap(true,null);
            }
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 当a1 属于 a2时
     * target应当符合编码规范时应当符合编码规范社会信用代码要求长度只能是18位，并且只能是数字或大写英文组合
     * @param target
     * @return
     */
    public static Map<String, Object> insitituteCodeValid2(Object a1,String a2,Object target) {
        if(!StringUtil.isNotEmpty(a1) || !a2.contains(String.valueOf(a1))){
            return ExpressionUtil.resultMap(true,null);
        }
        Pattern p = Pattern.compile("^[0-9A-Z]{18}");
        Matcher m = p.matcher(String.valueOf(target));
        boolean match = m.find();
        if (String.valueOf(target).length() == 18 && match) {
            return ExpressionUtil.resultMap(true,null);
        }
        return ExpressionUtil.resultMap(false,String.valueOf(target));
    }

    /***
     * 返回数据结果集
     * @param sql
     * @return
     */
    @Cacheable("jdbc_query")
    public static List<List<String>> getQuery(String sql) {
        // TODO
//        CommonCodeCheckDao commonCodeCheckDao = (CommonCodeCheckDao) SpringContextUtil.getBean(CommonCodeCheckDao.class);
//
//        List<List<String>> result = null;
//        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
//        try {
//            result = commonCodeCheckDao.selectList(sql);
//        } catch (Exception e) {
//        } finally {
//            DynamicDataSourceHolder.clearDataSource();
//        }

        // 表格数据
        List<List<String>> rows = new ArrayList<>();
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil.getBean(JdbcTemplate.class);
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            ps = conn.prepareStatement(sql);
        } catch (SQLException e1) {
            // logger.error(e1.getMessage(),e1);
        }
        ResultSet rs = null;
        try {
            if (ps != null) {
                rs = ps.executeQuery();
                // 子表的一行数据
                List<String> row = new ArrayList<>();
                while (rs != null && rs.next()) {
                    row = new ArrayList<>();
                    // 遍历获取每列数据
                    for (int i = 1; i < 50; i++) {
                        try {
                            // 遍历超越列数则异常，跳出for循环
                            row.add(rs.getString(i) == null ? "" : rs.getString(i));
                        } catch (Exception e) {
                            break;
                        }
                    }
                    rows.add(row);
                }
            }
        } catch (SQLException e1) {
            //  logger.error(e1.getMessage(),e1);
        } finally {
            if (null != ps) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    //      logger.error(e.getMessage(),e);
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    //      logger.error(e.getMessage(),e);
                }
            }
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    //    logger.error(e.getMessage(),e);
                }
            }
        }
        return rows;
    }

    /**
     * 替换sql中参数
     *
     * @param sql
     * @param params 报表参数
     * @return 替换参数后可执行的sql
     */
    public static String replaceSqlParams(String sql, Map<String, Object> params) {
        for (String key : params.keySet()) {
            sql = sql.replaceAll(Pattern.quote("#" + key.trim()), "" + java.util.regex.Matcher.quoteReplacement((String) params.get(key)) + "");
        }
        return sql;
    }

    /**
     * 返回集合
     * @param sql
     * @param proId   --产品代码
     * @param termId  --期次
     * @param buyplace  --地区代码
     * @return
     */
    public static List<String> sqlValue(Object sql, Object proId, Object termId,Object buyplace,Object loanBasisId,Object ccy,Object customerType1) {
        String sql1 = String.valueOf(sql);
        List<String>  collect4 = new ArrayList<String>();
        if (StringUtil.isNotEmpty(sql1) && StringUtil.upperCase(sql1).indexOf("SELECT") != -1) {
            List<String>  collect3 = new ArrayList<String>();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("pbcProdId", String.valueOf(proId));
            if(StringUtil.isNotEmpty(termId) && !StringUtil.equals(termId,"null")) {
                params.put("termid", String.valueOf(termId));
            }
            if(StringUtil.isNotEmpty(buyplace)&& !StringUtil.equals(buyplace,"null")) {
                params.put("buyplace", String.valueOf(buyplace));
            }
            if(StringUtil.isNotEmpty(loanBasisId)&& !StringUtil.equals(loanBasisId,"null")) {
                params.put("loanBasisId", String.valueOf(loanBasisId));
            }
            if(StringUtil.isNotEmpty(ccy)&& !StringUtil.equals(ccy,"null")) {
                params.put("ccy", String.valueOf(ccy));
            }
            if(StringUtil.isNotEmpty(customerType1)&& !StringUtil.equals(customerType1,"null")) {
                params.put("customerType1", String.valueOf(customerType1));
            }
            //替换sql变量
            sql1 = replaceSqlParams(String.valueOf(sql1), params);
            // System.out.println("sql1===========>"+sql1);
            //查找sql数据
            List<List<String>> list = getQueryFromRedis(sql1);
            //合并sql集合
            Stream<String> integerStream = list.stream().flatMap(Collection::stream);
            collect3 = integerStream.collect(Collectors.toList());
            if(!collect3.isEmpty()){
                collect4.add((String) collect3.get(0));
            }else{
                collect4.add("");
            }
        }else{
            collect4.add(sql1);
        }
        return collect4;
    }
    /**
     * 判断是否是SQL语句,并返回一条数据
     * @param sql
     * @return
     */
    public static String sqlValueOne(String sql,String proId,String termId,String buyplace){
        String value = sql;
        List<String> list = sqlValue(sql,proId,termId,buyplace,null,null,null);
        if(!list.isEmpty()){
            value =   list.get(0);
        }
        return value;
    }

    public static String sqlValueOne2(String sql,String proId,String termId,String loanBasisId){
        String value = sql;
        List<String> list = sqlValue(sql,proId,termId,null,loanBasisId,null,null);
        if(!list.isEmpty()){
            value =   list.get(0);
        }
        return value;
    }

    public static String sqlValueOne3(String sql,String proId,String termId,String buyplace,String ccy,String customerType1){
        String value = sql;
        List<String> list = sqlValue(sql,proId,termId,buyplace,null,ccy,customerType1);
        if(!list.isEmpty()){
            value =   list.get(0);
        }
        return value;
    }

    // 缓存sql查询结果
    private static List<List<String>> getQueryFromRedis(String sql) {
        List<List<String>> list = null;
        RedisUtil redisUtil = (RedisUtil) SpringContextUtil.getBean(RedisUtil.class);
        if (ObjectUtils.nullSafeEquals(redisUtil.get(sql), null)) {
            logger.debug("redis中没有数据！");
            list = EnumerateExpression.getQuery(sql);

            // 根据配置设置过期时间
            Long expireSecond = Long.parseLong(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.REDIS_CONFIG_EXPIRE_SECOND) ? SVReportGlobalConstant.REDIS_CONFIG_EXPIRE_SECOND_VALUE : SvrDataUtil.getSysParam(SVReportGlobalConstant.REDIS_CONFIG_EXPIRE_SECOND));
            redisUtil.set(sql, list, expireSecond);
            return list;
        } else {
            logger.debug("redis命中了");
            list = (List<List<String>>) redisUtil.get(sql);
        }
        return list;
    }




}
