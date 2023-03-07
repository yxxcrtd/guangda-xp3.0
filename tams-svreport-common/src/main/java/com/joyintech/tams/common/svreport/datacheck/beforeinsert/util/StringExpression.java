package com.joyintech.tams.common.svreport.datacheck.beforeinsert.util;

import com.google.common.collect.Lists;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 字符串表达式
 */
public class
StringExpression {

    // 必须为空
    public static Map<String,Object> beEmpty(Object target) {

        Map<String,Object> map = new HashMap<>();
        boolean flag = !StringUtil.isNotEmpty(target);
        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;
    }
    // 全部字段必须为空
    public static Map<String,Object> beEmptyAll(Object target,Object target1,Object target2) {

        boolean flag=false;
        Map<String,Object> map = new HashMap<>();
        if(!StringUtil.isNotEmpty(target)&&
                !StringUtil.isNotEmpty(target1)&&
                !StringUtil.isNotEmpty(target2)){
            flag=true;
        }
        map.put("flag",flag);
        if (!flag){
            map.put("data",target+","+target1+","+target2);
        }
        return map;
    }


    // 不能为空
    public static Map<String,Object> isNotEmpty(Object target) {
        String strTarget = String.valueOf(target);

        if (ObjectUtils.isEmpty(target) || StringUtil.equals(strTarget,"null")) {
            return ExpressionUtil.resultMap(false, target);
        }

        // 需要判断是不是 String、Date类型
        if (target instanceof String) {
            strTarget = String.valueOf(target);
        }
        if (target instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            strTarget = sdf.format(target);
        }

        // 返回
        return ExpressionUtil.resultMap(StringUtils.isNotEmpty(strTarget), strTarget);
    }

    // 大于等于
    public static Map<String, Object> compareLength(Object target, int configLength) {
        String strTarget = "";

        // 判断是不是字符串
        if (target instanceof String) {
            strTarget = String.valueOf(target);
        }

        if (target instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            strTarget = sdf.format(target);
        }

        Map<String,Object> map = new HashMap<>();
        boolean flag = false;

        if(StringUtils.isBlank(strTarget)) {
            //为空或者null应该返回true
            map.put("flag",true);
            map.put("data",target);
            return map;
        }
        int targetLength = strTarget.length();

        // 大于等于的时候表达式成立
        if (targetLength <= configLength) {
            flag =  true;
        }

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;
    }

    // 如果obj1 = obj1Value， 判断target的长度为configLength或者configLength2，满足为true，不满足为false
    public static Map<String, Object> compareLengthByCond(Object obj1, Object obj1Value, Object target, Object configLength, Object configLength2) {
        if (null == obj1 || null == obj1Value || null == target) {
            return ExpressionUtil.resultMap(true, obj1);
        }

        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        //assetsInvest 若基础资产出让机构类型字段不为1：住户、 6：境外时，基础资产出让机构代码
        //人行客户类型 "1","单位" "2","个人" "3","单位及个人" "4","住户" "5","广义政府" "6","非金融企业" 7 "金融机构（实体）" "8","特定目的载体" "9","境外"
        String strTartget = String.valueOf(target);
        int len1 = 0;
        int len2 = 0;

        // 前置条件不满足
        if (!StringUtil.equals(obj1.toString(), obj1Value.toString())){
            return ExpressionUtil.resultMap(true, obj1);
        }

        if(StringUtil.equals(String.valueOf(obj1), String.valueOf(obj1Value))){
            len1 = Integer.parseInt(String.valueOf(configLength));
            len2 = Integer.parseInt(String.valueOf(configLength2));
            //基础资产出让机构代码 assetTransferId  基础资产出让方为个人的，证件代码脱敏后的长度应该为32位或46位
            if(strTartget.length() == len1 || strTartget.length() == len2){
                flag = true;
            }
        }else if(StringUtil.equals(String.valueOf(obj1), "5")){
            len1 = Integer.parseInt(String.valueOf(configLength));
            //基础资产出让机构代码 assetTransferId  基础资产出让方为个人的，证件代码脱敏后的长度应该为32位或46位
            if(strTartget.length() == len1){
                flag = true;
            }
        }else if(StringUtil.equals(String.valueOf(obj1), "5") || StringUtil.equals(String.valueOf(obj1), "7") || StringUtil.equals(String.valueOf(obj1), "6")){
            len1 = Integer.parseInt(String.valueOf(configLength));
            //基础资产出让机构代码 assetTransferId  基础资产出让方为个人的，证件代码脱敏后的长度应该为32位或46位
            if(strTartget.length() == len1){
                flag = true;
            }
        }else{
            flag = true;
        }
        map.put("data", obj1);
        map.put("flag",flag);
        return map;
    }

    //当condition = conditionValue时，target 不能为空,不为空返回true
    public static Map<String,Object> isNotEmptyByCond(Object condition,Object conditionValue,Object target) {
        //如果前置条件的值为null就不校验此项
        if (Objects.isNull(condition)){
            return ExpressionUtil.resultMap(true,null);
        }

        if(Objects.isNull(conditionValue)){
            return ExpressionUtil.resultMap(false,"脚本配置错误");
        }
        String strConditionValue = String.valueOf(condition);
        List<String>  conditionValueList =  StringUtil.splitToList(",", conditionValue.toString());


        if (conditionValueList.contains(strConditionValue)){
            if(Objects.isNull(target)){
                return ExpressionUtil.resultMap(false,String.valueOf(target));
            }
            return ExpressionUtil.resultMap(true,null);
        }else{
            //前置条件不满足就不进行效验
            return ExpressionUtil.resultMap(true,null);
        }
    }

    //当condition = conditionValue时，target 是否为空,为空返回true
    public static Map<String,Object> beEmptyByCond(Object condition,String conditionValue,Object target){
        //如果前置条件的值为null就不校验此项
        if (Objects.isNull(condition)){
            return ExpressionUtil.resultMap(true,null);
        }

        if(Objects.isNull(conditionValue)){
            return ExpressionUtil.resultMap(false,"脚本配置错误");
        }
        String strConditionValue = String.valueOf(condition);
        List<String>  conditionValueList =  StringUtil.splitToList(",",conditionValue);


        if (conditionValueList.contains(strConditionValue)){
            if(Objects.isNull(target)){
                return ExpressionUtil.resultMap(true,null);
            }
            return ExpressionUtil.resultMap(false,String.valueOf(target));
        }else{
            //前置条件不满足就不进行效验
            return ExpressionUtil.resultMap(true,null);
        }
    }

    //条件判断，当condition不为空的时候，target也不应该为空

    public static Map<String,Object> isNotEmptyByCond2(Object condition, Object target){
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;

        if (!StringUtil.isNotEmpty(condition)){
            flag = true;
            map.put("flag",flag);
            map.put("data",target);
            return map;
        }

        flag = StringUtil.isNotEmpty(target);

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;
    }

    //条件判断 当condition等于conditionValue时，target是否等于targetValue,等于返回true
    public static Map<String,Object> judgeValueByCond(Object condition, Object conditionValue,Object target, Object targetValue){
        List<String>  conditionValueList =  StringUtil.splitToList(",",conditionValue.toString());
        List<String>  targetValueList =  StringUtil.splitToList(",", targetValue.toString());
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        boolean firstFlag = false;//判断前置条件是否正确

        //当为空时并不提示校验
        if (!StringUtil.isNotEmpty(condition)
                ||!StringUtil.isNotEmpty(target)
                ||StringUtil.isEmptyList(conditionValueList)
                ||StringUtil.isEmptyList(targetValueList)){
            map.put("flag",true);
            return map;
        }

        //当前置条件对的上时才进行下一步
        for(String key:conditionValueList){
            if(StringUtil.equals(key,condition)){
                firstFlag = true;
            }
        }

        if(firstFlag){
            for(String value:targetValueList){
                if(StringUtil.equals(value,target)){
                    flag = true;
                }
            }
        }else{//前置条件对不上就证明不是此条效验
            map.put("flag",true);
            return map;
        }

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;
    }

    //条件判断 当condition等于conditionValue时，target是否等于targetValue,不等于返回true
    public static Map<String, Object> judgeValueByCond2(Object condition, Object conditionValue, Object target, Object targetValue){
        if (null == target) {
            return ExpressionUtil.resultMap(false, "target不能为空！");
        }

        List<String>  conditionValueList =  StringUtil.splitToList(",", String.valueOf(conditionValue));
        List<String>  targetValueList =  StringUtil.splitToList(",", targetValue.toString());
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if (StringUtil.isBlank(String.valueOf(condition))
                ||StringUtil.isBlank(target.toString())
                ||StringUtil.isEmptyList(conditionValueList)
                ||StringUtil.isEmptyList(targetValueList)){
            //前置条件当为空时 并不提示 校验
            map.put("flag",true);
            map.put("data",target);
            return map;
        }

        if (conditionValueList.contains(condition)){
            flag = !targetValueList.contains(target);
        } else {
            flag = true;
        }

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;
    }

    //DB0208
    //条件判断 当condition等于conditionValue时，target是否等于targetValue,不等于返回true
    public static Map<String, Object> judgeValueByCond02(Object condition, Object conditionValue, Object target, Object targetValue,Object pbcProdId){
        if (null == target) {
            return ExpressionUtil.resultMap(false, "target不能为空！");
        }

         condition = EnumerateExpression.sqlValueOne(String.valueOf(condition),String.valueOf(pbcProdId),null,null);

        List<String>  conditionValueList =  StringUtil.splitToList(",", String.valueOf(conditionValue));
        List<String>  targetValueList =  StringUtil.splitToList(",", targetValue.toString());
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if (StringUtil.isBlank(String.valueOf(condition))
                ||StringUtil.isBlank(target.toString())
                ||StringUtil.isEmptyList(conditionValueList)
                ||StringUtil.isEmptyList(targetValueList)){
            //前置条件当为空时 并不提示 校验
            map.put("flag",true);
            map.put("data",target);
            return map;
        }

        if (conditionValueList.contains(condition)){
            flag = !targetValueList.contains(target);
        } else {
            flag = true;
        }

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;
    }



    // 募集方式为公开募集的产品（raiseType = 2），产品基本信息公开标识不能选择否（baseInfoSign != 2）
    // 1：是2：否 募集方式为非公开募集的资管产品基本信息公开标识可填报 “1：是”或“2：否”， 如填报“2：否”，需同时提交说明。
    // 其他资管产品基本信息公开标识均应填报“1：是”。
    public static Map<String, Object> judgeValueByCond3(Object obj1, Object obj1Value, Object obj2, Object obj2Value) {
        if (null == obj1 || null == obj1Value || null == obj2 || null == obj2Value) {
            return ExpressionUtil.resultMap(true, "参数[raiseType、baseInfoSign]不能为空");
        }

        // 判断是否公开募集的产品（raiseType = 2）
        if (!StringUtil.equals(obj1.toString(), obj1Value.toString())) {
            if (StringUtil.equals(obj1.toString(), "1")) {
                // 募集方式 为1 ,产品基本信息公开标识 为1
                if (StringUtil.equals(obj2.toString(), "1")) {
                    return ExpressionUtil.resultMap(true, "");
                } else {
                    return ExpressionUtil.resultMap(false, obj2);
                }
            }
            return ExpressionUtil.resultMap(true, "");
        }

        // 募集方式 非公开募集 2 , 产品基本信息公开标识 可为1 或 2
        if (StringUtil.equals(obj1.toString(), obj1Value.toString())) {
            if (StringUtil.equals(obj2.toString(), obj2Value.toString()) || StringUtil.equals(obj2.toString(), "2")){
                return ExpressionUtil.resultMap(true, obj2);
            } else {
                return ExpressionUtil.resultMap(false, obj2);
            }
        }
        return ExpressionUtil.resultMap(true, obj2);
    }

    // DS01097 当  creditMode 包含 1, creditType 包含 4 时，；
    public static Map<String, Object> judgeValueByCond4(Object obj1, Object obj1Value, Object obj2, Object obj2Value) {
        if (null == obj1) {
            return ExpressionUtil.resultMap(true, "参数[creditType、creditMode]不能为空");
        }

        if (null == obj2) {
            return ExpressionUtil.resultMap(true, "参数[creditType、creditMode]不能为空");
        }

        if (!(StringUtils.containsAny(obj1.toString(), ",") && StringUtils.containsAny(obj1.toString(), obj1Value.toString()))) {
            return ExpressionUtil.resultMap(true, "");
        }

        String returnString = obj1 + ":" + obj1Value + "-" + obj2 + ":" + obj2Value;

        // 当 creditType 包含 2 时，creditMode 也必须包含 4
        if ((StringUtils.containsAny(obj1.toString(), ",") && StringUtils.containsAny(obj2.toString(), ",")) && (StringUtils.containsAny(obj1.toString(), obj1Value.toString()) && StringUtils.containsAny(obj2.toString(), obj2Value.toString()))) {
            return ExpressionUtil.resultMap(true, returnString);
        }

        // 返回false
        return ExpressionUtil.resultMap(false, obj2);
    }

    // DS01098 当 creditMode = 1时， creditType = 4 ，
    public static Map<String, Object> judgeValueByCond5(Object obj1, Object obj1Value, Object obj2, Object obj2Value) {
        if (null == obj1) {
            return ExpressionUtil.resultMap(true, "参数[creditType、creditMode]不能为空");
        }

        if (null == obj2) {
            return ExpressionUtil.resultMap(true, "参数[creditType、creditMode]不能为空");
        }
        // 存在 分隔符
        if ((StringUtils.containsAny(obj1.toString(), ",") && StringUtils.containsAny(obj2.toString(), ","))) {
            return ExpressionUtil.resultMap(true, "");
        }

        if (!StringUtil.equals(String.valueOf(obj1), String.valueOf(obj1Value))) {
            return ExpressionUtil.resultMap(true, "");
        }

        String returnString = obj1 + ":" + obj1Value + "-" + obj2 + ":" + obj2Value;

        // 当 creditType = 2 时，creditMode 必须 = 4
        if ((!StringUtils.containsAny(obj1.toString(), ",") && !StringUtils.containsAny(obj2.toString(), ",")) && (StringUtil.equals(obj1.toString(), obj1Value.toString()) && StringUtil.equals(obj2.toString(), obj2Value.toString()))) {
            return ExpressionUtil.resultMap(true, returnString);
        }

        // 返回false
        return ExpressionUtil.resultMap(false, obj2);
    }

    // DS01146 信息类型选择变更资管产品基本信息，应经人民银行同意后报送。当infoType等于1就返回true
    public static Map<String, Object> judgeValueByCond6(Object obj1, Object obj2) {
        if (!ObjectUtils.nullSafeEquals(obj1, null) && !ObjectUtils.nullSafeEquals(obj2, null) && StringUtil.equals(obj1, obj2) ) {
            return ExpressionUtil.resultMap(true, "infoType等于1");
        } else {
            return ExpressionUtil.resultMap(false, "infoType不等于1");
        }
    }

    //判断字段是否是某个字段，不是的话返回true
    public static Map<String,Object> isNotEqualsStr(Object target,String targetValue){
        if(StringUtil.equals(String.valueOf(target),targetValue)){
            return ExpressionUtil.resultMap(false,String.valueOf(target));
        }
        return ExpressionUtil.resultMap(true,null);
    }

    //当str1为空的时候，str2不能为空
    public static Map<String,Object> isEmptyByCond3(Object str1,Object str2){
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;

        if(ObjectUtils.isEmpty(str1) && ObjectUtils.isEmpty(str2)){
            flag = false;
        }

        map.put("flag",flag);
        map.put("data",str2);
        return map;
    }

    // 当str1不为空的时候，str2应为空
    public static Map<String,Object> isNotEmptyByCond3(Object str1, Object str2) {
        Boolean flag = true;
        if(StringUtil.isNotEmpty(str1)){
            flag = !StringUtil.isNotEmpty(str2);
        }
        return ExpressionUtil.resultMap(flag, str1+","+str2);
    }

    // 判断两个字符串对象是否相等，相等返回true；否则返回false
    public static Map<String, Object> isEqualsTwoString(Object arg1, Object arg2) {
        // 为空不校验
        return ExpressionUtil.resultMap(ObjectUtils.isEmpty(arg2) || ObjectUtils.nullSafeEquals(arg1, arg2) ? true : false, "2个参数值:" + arg1 + "," + arg2);
    }

    //判断两个字段是否相等 相等返回false，不相等返回true
    public static Map<String,Object> isEquals(String str1,String str2){
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;

        if (str1.equals(str2)){
            flag = false;
        }

        map.put("flag",flag);
        map.put("data",str2);
        return map;
    }

    //判断两个字段是否相等 相等返回true，不相等返回false
    public static Map<String,Object> isnotEquals(String str1,String str2){
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;

        if (str1.equals(str2)){
            flag = true;
        }
        map.put("flag",flag);
        map.put("data",str2);
        return map;
    }
    //根据标志判断参数是否为空，标志Y、N Y：需进行判断是否为空，为空返回true 不为空返回false； N：不需判断是否为空
    public static Map<String,Object> isEmpayByFlag(String flag1,String param){
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;

        if ("Y".equals(flag1) && StringUtil.isNotEmpty(param)){
            flag = false;
        }

        map.put("flag",flag);
        map.put("data",param);
        return map;
    }

    //条件判断 当condition等于conditionValue时，target1不等于targetValue1 且 target2不等于targetValue2 返回true
    public static Map<String, Object> conditionValueCompare(Object condition, Object conditionValue,
                                                            Object target1, Object targetValue1, Object target2, Object targetValue2) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        if (StringUtil.equals(String.valueOf(condition), String.valueOf(conditionValue))) {
            if (!StringUtil.equals(String.valueOf(target1), String.valueOf(targetValue1))
                    && !StringUtil.equals(String.valueOf(target2), String.valueOf(targetValue2))) {
                flag = true;
            } else {
                flag = false;
            }
        } else {
            flag = true;
        }

        map.put("flag", flag);
        map.put("data", condition);
        return map;
    }



    //条件判断 当condition等于conditionValue时，list1中的数据与 list2中的数据是否完全一致 一致则返回true
    public static Map<String,Object> conditionListCompare(String condition,String conditionValue,
                                                           List<String> list1,List<String> list2){
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;
        if (StringUtil.equals(condition,conditionValue)){
            if (list1.size() == list2.size()){
                int i = 0;
                for (String s : list1) {
                    if (!StringUtil.equals(s,list2.get(i))){
                        flag = false;
                        break;
                    }
                    i++;
                }        
            }else{
                flag = false;
            }
        }

        map.put("flag",flag);
        map.put("data",condition + "," + conditionValue + "," + list1.toString() + "," + list2.toString());
        return map;
    }

    //conditionList中的数据不为空时，aimValue也不为空，则返回false，否则返回true
    public static Map<String,Object> conditionListValue(List<String> conditionList,String aimValue){
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;
        map.put("data",conditionList.toString() + "," + aimValue);
        try{
            for (String s : conditionList) {
                if (StringUtil.isNotEmpty(s) && StringUtil.isNotEmpty(aimValue)){
                    flag = false;
                    map.put("flag",flag);
                    return map;
                }   
            }     
        }catch (Exception e){
            flag = false;
        }
        map.put("flag",flag);
        return map;
    }

    // 当 obj1... 有数时，obj0 为空！
    public static Map<String,Object> conditionListValue2(Object obj0, Object obj1, Object obj2, Object obj3, Object obj4) {
        if (ObjectUtils.nullSafeEquals(obj1, null) || ObjectUtils.nullSafeEquals(obj2, null) || ObjectUtils.nullSafeEquals(obj3, null) || ObjectUtils.nullSafeEquals(obj4, null)) {
            return ExpressionUtil.resultMap((!ObjectUtils.nullSafeEquals(obj0, null) || !ObjectUtils.nullSafeEquals(obj0, "")) ? true : false, "参数1=" + obj0);
        } else {
            return ExpressionUtil.resultMap((ObjectUtils.nullSafeEquals(obj0, null) || ObjectUtils.nullSafeEquals(obj0, "")) ? true : false, "参数1=" + obj0);
        }
    }

    // 当 obj1... 有数时，obj0 为空！
    public static Map<String,Object> conditionListValue3(Object obj0, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        if (ObjectUtils.nullSafeEquals(obj1, null) || ObjectUtils.nullSafeEquals(obj2, null) ||
                ObjectUtils.nullSafeEquals(obj3, null) || ObjectUtils.nullSafeEquals(obj4, null) ||
                ObjectUtils.nullSafeEquals(obj5, null) || ObjectUtils.nullSafeEquals(obj6, null) ||
                ObjectUtils.nullSafeEquals(obj7, null) || ObjectUtils.nullSafeEquals(obj8, null) ||
                ObjectUtils.nullSafeEquals(obj9, null)) {
            return ExpressionUtil.resultMap((!ObjectUtils.nullSafeEquals(obj0, null) || !ObjectUtils.nullSafeEquals(obj0, "")) ? true : false, "参数1=" + obj0);
        } else {
            return ExpressionUtil.resultMap((ObjectUtils.nullSafeEquals(obj0, null) || ObjectUtils.nullSafeEquals(obj0, "")) ? true : false, "参数1=" + obj0);
        }
    }

    /**
     * 相同的产品代码、地区和客户类型下，
     * 币种为'BWB'的 当期申购金额折人民币 应 等于除'BWB'外的其他各币种当期申购金额折人民币之和!
     *
     * @param obj1 当条数据
     * @param obj2 主键集合
     * @param obj3 校验字段
     * @param obj4 固定值
     * @param obj5 需要求和的 主键
     * @param mapList 所有数据
     * @return
     */
    public static Map<String,Object> conditionListValue4(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, List<Map<String, Object>> mapList) {
        Map<String, Object> map = (Map<String, Object>) obj1;
        List<String> keys = StringUtil.splitToList(",", String.valueOf(obj2));
        String fieldName = String.valueOf(obj3);
        String fieldValue = String.valueOf(obj4);
        String sumField = String.valueOf(obj5);
        // 本条数据主键
        StringBuffer sb = new StringBuffer();
        for (String s : keys) {
            sb.append(String.valueOf(map.get(s)));
        }
        for (Map<String, Object> objectMap : mapList) {
            for (String key : keys) {
                ;
            }
        }
        return ExpressionUtil.resultMap(false,"ture");
    }

    /**
     * 相同的产品代码、地区和客户类型下，
     * 币种为'BWB'的 当期申购金额折人民币 应 等于除'BWB'外的其他各币种当期申购金额折人民币之和!
     *
     * @param obj1 当条数据
     * @param obj2 主键集合
     * @param obj3 校验字段
     * @param obj4 固定值
     * @param obj5 某项和
     * @param obj6 所有数据
     * @return
     */
    public static Map<String,Object> conditionListValue5(Object obj1, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {

        return ExpressionUtil.resultMap(false,"ture");
    }

    public static void main(String[] args) {
        System.out.println(conditionListValue2("", "1", "1", "1", "1"));
    }

    //条件判断 当condition等于conditionValue时，target1等于targetValue1 且 target2等于targetValue2 返回true
    public static Map<String,Object> conditionValueCompare2(Object condition,Object conditionValue,
                                                           Object target1,Object targetValue1,Object target2,Object targetValue2){
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if (StringUtil.equals(condition,conditionValue)){
            if(StringUtil.equals(target1,targetValue1) && StringUtil.equals(target2,targetValue2)){
                flag = true;
            }else{
                flag = false;
            }
        }else{
            flag = true;
        }

        map.put("flag",flag);
        map.put("data",condition + "," + conditionValue + "," + target1 + "," + targetValue1
                + "," + target2 + "," + targetValue2);
        return map;
    }


    //param1,param2,param3任一字段非空，target必t填
    public static Map<String,Object> isNotEmptyByThreeParam(Object param1,Object param2,Object param3,Object target){

        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        try{
            if (StringUtil.isNotEmpty(param1)
                    ||StringUtil.isNotEmpty(param2)
                    ||StringUtil.isNotEmpty(param3)){
                if (StringUtil.isNotEmpty(target)){
                    flag = true;
                    map.put("flag",flag);
                    return map;
                }

            } else {
                flag = true;
            }
        }catch (Exception e){
            flag = false;
        }

        map.put("flag",flag);
        map.put("data",target);
        return map;

    }

    //param1,param2任一字段非空，target必填
    public static Map<String,Object> isNotEmptyByTwoParam(Object param1,Object param2,Object target){

        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        try{
            if (StringUtil.isNotEmpty(param1)
                    ||StringUtil.isNotEmpty(param2)
            ){
                if (StringUtil.isNotEmpty(target)){
                    flag = true;
                    map.put("flag",flag);
                    return map;
                }

            } else {
                flag = true;
            }
        }catch (Exception e){
            flag = false;
        }

        map.put("flag",flag);
        map.put("data",target);
        return map;

    }

    //条件判断 当condition等于conditionValue时，target是否等于targetValue,等于返回true

    /**
     *  条件判断 当condition等于conditionValue时，比较thisDate 和 lastDate
     *     当 thisDate 小于 lastDate 返回 true
     * @param condition
     * @param conditionValue
     * @param thisDateStr
     * @param lastDateStr
     * @return
     */
    public static Map<String,Object> compareValueByCond(String condition,String conditionValue,Object thisDateStr,String lastDateStr){
        List<String>  conditionValueList =  StringUtil.splitToList(",",conditionValue);
        if (StringUtil.isBlank(condition) || StringUtil.isEmptyList(conditionValueList)){
            return ExpressionUtil.resultMap(false,thisDateStr);
        }

        if (conditionValueList.contains(condition)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(lastDateStr == null || "".equals(lastDateStr)){
                return ExpressionUtil.resultMap(true,thisDateStr);
            }
            try {
                if(thisDateStr instanceof Date){
                    thisDateStr=sdf.format((Date)thisDateStr);
                }
                Date thisDate =  sdf.parse((String) thisDateStr);
                Date lastDate =  sdf.parse(lastDateStr);
                if(DateExpression.compareTo(thisDate,lastDate) < 0){
                    return ExpressionUtil.resultMap(true,thisDateStr);
                }else{
                    return ExpressionUtil.resultMap(false,thisDateStr);
                }
            } catch (ParseException e) {
                return ExpressionUtil.resultMap(true,thisDateStr);
            }
        }else{
            return ExpressionUtil.resultMap(true,thisDateStr);
        }
    }


    /**
     * 条件判断 当condition等于conditionValue,tag1 大于 0时，判断taglist 的值
     * 如果其中有小于0的 则返回false
     * @param condition
     * @param conditionValue
     * @param tag1
     * @param taglist
     * @return
     */
    public static Map<String,Object> compareZeroByCond(String condition,String conditionValue,String tag1,List<String> taglist){
        List<String>  conditionValueList =  StringUtil.splitToList(",",conditionValue);
        if (StringUtil.isBlank(condition) || StringUtil.isEmptyList(conditionValueList)
                || StringUtil.isBlank(tag1) || StringUtil.isEmptyList(taglist) ){
            return ExpressionUtil.resultMap(false,tag1);
        }

        if (conditionValueList.contains(condition) && BigDecimalExpression.greaterThen0(tag1)){
            for (String tag : taglist) {
                if(!BigDecimalExpression.greaterThen0(tag)){
                    return ExpressionUtil.resultMap(false,tag);
                }
            }
            return ExpressionUtil.resultMap(true,null);
        }else{
            return ExpressionUtil.resultMap(true,null);
        }
    }

    /**
     * 判断 交易对手产品代码第7位为"1"的，交易对手产品种类应为“银行非保本理财"，满足为true，不满足false
     * @param arg1
     * @param arg2
     * @return
     */
    public static Map<String,Object> judgePartyProdType(Object arg1, Object arg2, Object arg3){
        if (ObjectUtils.isEmpty(arg1)  || (!ObjectUtils.isEmpty(arg1) && String.valueOf(arg1).length()<7)){
            return ExpressionUtil.resultMap(true,null);
        }
        String type = String.valueOf(arg1).substring(6,7);
        if (StringUtil.equals(type,String.valueOf(arg3))){
            if(StringUtil.equals(String.valueOf(arg2),String.valueOf(arg3))){
                return ExpressionUtil.resultMap(true,null);
            }
            return ExpressionUtil.resultMap(false,arg1+","+arg2);
        }else{
            return ExpressionUtil.resultMap(true,null);
        }
    }

    //param01配置单个参数维度的参数key，param02是被校验的参数key，param03是被校验参数应该等于的值，mapList为所有需要校验的数据list,
    // 只要 所有param01维度下都有一个param02作为key的值等于 param03 ,则返回true,否则返回false
    public static Map<String,Object> listConditionExists(Object str1,String param01,String param02,String param03,List<Map<String,Object>> mapList){
        if (null == mapList) {
            return ExpressionUtil.resultMap(false, "mapList为空");
        }
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;
        map.put("data",mapList.toString());
        try{
            String dataString = "";
            Map<String,Boolean> resultMap = new HashMap<>();
            List<Map<String,Object>> mapListOfParam01 = Lists.newArrayList();

            //将与本条数据param01相等的数据取出
            for (Map<String, Object> stringObjectMap : mapList) {
                String param01Str = (String) stringObjectMap.get(param01);
                if(StringUtil.equals(String.valueOf(str1),param01Str)){
                    mapListOfParam01.add(stringObjectMap);
                }
            }

            //判断param01维度下， param02为param03的数据至少有一条
            for (Map<String, Object> stringObjectMap : mapListOfParam01) {
                String param01Str = (String) stringObjectMap.get(param01);
                String param02Str = (String) stringObjectMap.get(param02);
                if (StringUtil.equals(param02Str,param03)){
                    resultMap.put(param02Str,true);
                }else {
                    if (resultMap.get(param02Str) == null){
                        resultMap.put(param02Str,false);
                    }
                }
                dataString = dataString + param01Str + "," + param02Str + ";";
            }
            if(resultMap.get(param03) == null){
                flag =false;
            }
            map.put("data",dataString);
            map.put("flag",flag);
            return map;
        }catch (Exception e){
            flag = false;
        }
        map.put("flag",flag);
        return map;
    }

    /**
     * 该报表 每个产品代码下 至少 有一条 币种 不为 'BWB' 的数据
     *                                       'pbcProdId', 'ccy', 'BWB'
     * str_kit.listConditionExists(pbcProdId,str_1,str_2,str_3,mapList)
     * 每个产品代码下至少有一条币种为BWB的数据
     *
     * @param str1 本条数据 产品代码
     * @param param01 产品代码 key
     * @param param02 条件值 key1
     * @param param03 不等于的常量值
     * @param mapList 所有数据
     * @return
     */
    public static Map<String, Object> listConditionExistsNotEquals(Object str1, String param01, String param02, String param03, List<Map<String, Object>> mapList) {
        if (null == mapList) {
            return ExpressionUtil.resultMap(false, "mapList为空");
        }
        // 产品代码为空 不校验
        if (Objects.isNull(str1)) {
            return ExpressionUtil.resultMap(true, "pbcProdId为空");
        }
        // 过滤 产品代码 及
        Map<Object, List<Map<String, Object>>> collect = mapList.stream()
                // 产品代码
                .filter(e -> StringUtil.isNotEmpty(e.get(param01)) && StringUtil.equals(str1, e.get(param01)))
                .filter(e -> StringUtil.isNotEmpty(e.get(param02)) && !StringUtil.equals(param03, e.get(param02)))
                .collect(Collectors.groupingBy(k -> k.get(param01)));

        // 不存在 满足该规则的数据
        if (!collect.containsKey(str1)) {
            return ExpressionUtil.resultMap(false, str1);
        }
        // 存在当前产品代码 并且 至少有一条
        if (collect.get(str1) != null && collect.get(str1).size() >= 1) {
            return ExpressionUtil.resultMap(true, "");
        }
        // 校验不通过
        return ExpressionUtil.resultMap(false, str1);
    }

    //根据数据字段分别统计两个list中的值是否相等 两个list分别是全国的和地区的
    public static Map<String, Object> compareListByDict(List<Map<String, Object>> list1, List<Map<String, Object>> list2, String keyValue) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String data = keyValue;

        List<String> dictItemList = new ArrayList<>();
        List<? extends DataDictItem> items = SysDataUtil.getDataDictItem(keyValue);
        if(StringUtil.isNotEmpty(items)){
            for (DataDictItem channel : items) {
                dictItemList.add(channel.getItemCode());
            }

            //list1金额汇总
            Map<String, Object> map1 = new HashMap<>();
            for(int i=0; i<dictItemList.size(); i++){
                String item = dictItemList.get(i).toString();
                BigDecimal oldAmt = new BigDecimal(map1.get(item)==null?"0":map1.get(item).toString());
                for(Map<String, Object> listMap : list1){
                    BigDecimal amt = new BigDecimal(listMap.get(item).toString());
                    map1.put(item, oldAmt.add(amt));
                }
            }

            //list2金额汇总
            Map<String, Object> map2 = new HashMap<>();
            for(int i=0; i<dictItemList.size(); i++){
                String item = dictItemList.get(i).toString();
                BigDecimal oldAmt = new BigDecimal(map2.get(item)==null?"0":map2.get(item).toString());
                for(Map<String, Object> listMap2 : list2){
                    BigDecimal amt = new BigDecimal(listMap2.get(item).toString());
                    map1.put(item, oldAmt.add(amt));
                }
            }

            //金额比较
            for(int i=0; i<dictItemList.size(); i++){
                String item = dictItemList.get(i).toString();
                BigDecimal list1Amt = new BigDecimal(map1.get(item)==null?"":map1.get(item).toString());
                BigDecimal list2Amt = new BigDecimal(map2.get(item)==null?"":map2.get(item).toString());
                if(list1Amt.compareTo(list2Amt)==0){
                    flag = true;
                }else{
                    flag = false;
                    data =  item+"["+list1Amt+"]"+"["+list2Amt+"]";
                }
            }
        }


        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    /**
     * str1 = str2  str3=str4时  target必须有值
     * @param str1
     * @param str2
     * @param str3
     * @param str4
     * @param target
     * @return
     */
    public static Map<String,Object> moreConditionNotNull(String str1,String str2,String str3,String str4,String target){
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        map.put("data",target);
        if (StringUtils.isEmpty(str1)||
                StringUtils.isEmpty(str2) ||
        StringUtils.isEmpty(str3)||
        StringUtils.isEmpty(str4)){
            map.put("flag",flag);
            return map;
        }
        if (StringUtils.isEmpty(target)){
            flag = true;
        }
        map.put("flag",flag);
        return map;
    }

    /**
     * s1 = s2 时,s3的长度应该为s4
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @return
     */
    public static Map<String,Object> condtionLenth(Object s1, Object s2, Object s3, Object s4){
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str3 = "";
        String str4 = "";
        map.put("data",s3);
        if (!StringUtil.isNotEmpty(s1) || !StringUtil.isNotEmpty(s2)) {
            map.put("flag", true);
            return map;
        }
        // 前置条件不满足 应返回 true
        if(!StringUtils.equals(String.valueOf(s1),String.valueOf((s2)))){
            map.put("flag", true);
            return map;
        }
        if (!StringUtil.isNotEmpty(s3) || !StringUtil.isNotEmpty(s4)) {
            map.put("flag", false);
            return map;
        }
        if (StringUtils.equals(String.valueOf(s1),String.valueOf(s2))){
            try {
                try {
                    if (s3 instanceof Date) {
                        str3 = sdf.format((Date) s3);
                    } else {
                        str3 = String.valueOf(s3);
                    }
                } catch (Exception e) {
                    str3 = "";
                }

                try {
                    if (s4 instanceof Date) {
                        str4 = sdf.format((Date) s4);
                    } else {
                        str4 = String.valueOf(s4);
                    }
                } catch (Exception e) {
                    str4 = "";
                }
                flag = String.valueOf(str3).length() == Integer.parseInt(String.valueOf(str4));
            } catch (NumberFormatException e) {
                return map;
            }
        }
        map.put("flag",flag);
        return map;
    }

    /**
     * s1 = s2 时,s3的长度应该为s4中的值
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @return
     */
    public static Map<String,Object> compareLenthMore(Object s1, Object s2, Object s3, Object s4){
        if (ObjectUtils.isEmpty(s1)){
            return ExpressionUtil.resultMap(true, null);
        }
        if(ObjectUtils.isEmpty(s2) || ObjectUtils.isEmpty(s4)){
            return ExpressionUtil.resultMap(false, "脚本配置错误");
        }
        List<String>  conditionValueList =  StringUtil.splitToList(",", s4.toString());
        if (StringUtils.equals(String.valueOf(s1), String.valueOf(s2))){
            Boolean flag = false;
            for (String s : conditionValueList) {
                if (String.valueOf(s3).length() == Integer.parseInt(s)){
                    flag = true;
                }
            }
            if(!flag){
                return ExpressionUtil.resultMap(false, String.valueOf(s3));
            }
        }
        return ExpressionUtil.resultMap(true, null);
    }

    //等于  target 的长度必须等于 length
    public static Map<String, Object> compareEqualToLength(Object target, int configLength) {
        String strTarget = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (target instanceof Date) {
                strTarget = sdf.format((Date) target);
            } else {
                strTarget = String.valueOf(target);
            }
        } catch (Exception e){
            strTarget = String.valueOf(target);
        }
        if(strTarget.contains("INS")&&strTarget.length()>15){
            strTarget=strTarget.replace("INS","");
        }
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;

        if(StringUtils.isBlank(strTarget)||"null".equals(strTarget)) {
            //为空或者null应该返回true
            map.put("flag", true);
            map.put("data", target);
            return map;
        }
        int targetLength = strTarget.length();

        // 等于的时候表达式成立
        if (targetLength == configLength) {
            flag =  true;
        }

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;
    }

    //不等于0值
    public static Map<String, Object> notCompareZero(Object target) {
        if(StringUtil.equals(String.valueOf(target),"0")){
            return ExpressionUtil.resultMap(false,String.valueOf(target));
        }
        return ExpressionUtil.resultMap(true,null);
    }
    /***
     * 判断两个字段是否相等 相等返回true，不相等返回false
     * @param str1 第一个为传的参数
     * @param str2 第二个为传的sql语句
     * @param str3 第三个为sql语句用到的参数（产品代码）
     * @param str4 第四个为sql语句用到的参数（时间）
     * @return
     */
    public static Map<String,Object> isnotEqualssql(Object str1,Object str2,Object str3,Object str4,Object str5){
        String  strTarget1 = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  strTarget2 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if("".equals(strTarget2)){
            flag = true;
            map.put("flag", flag);
            map.put("data", strTarget2);
            return map;
        }
        if (strTarget1.equals(strTarget2)){
            flag = true;
        }
        map.put("flag",flag);
        map.put("data",strTarget2);
        return map;
    }


    public static Map<String,Object> isnotEqualssqlDB07(Object str1,Object str2,Object str3,Object str4,Object str5){
        String  strTarget1 = String.valueOf(str1);
        String  strTarget2 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        List<String>  conditionValueList =  StringUtil.splitToList(",", strTarget2);
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;
        if(strTarget1.equals("BWB") || StringUtil.isEmpty(strTarget1) || strTarget1.equals("null")){
            flag = true;
            map.put("flag", flag);
            return map;
        }
        if("".equals(strTarget2)){
            flag = true;
            map.put("flag", flag);
            map.put("data", strTarget2);
            return map;
        }

        if (!conditionValueList.contains(strTarget1)){
            flag =  false;
        }

        map.put("flag",flag);
        map.put("data",strTarget1);
        return map;
    }

    /***
     * 判断两个值是否相等 相等返回true，不相等返回false
     * @param str1 第一个为传的值
     * @param str2 第二个为传的sql语句
     * @param str3 第三个为sql语句用到的参数（产品代码）
     * @param str4 第四个为sql语句用到的参数（时间）
     * @param str5 第四个为sql语句用到的参数（销售区域）
     * @param str6 第四个为sql语句用到的参数（币种）
     * @param str7 第四个为sql语句用到的参数（客户类型）
     * @return
     */
    public static Map<String,Object> isnotEqualssql2(Object str1,Object str2,Object str3,Object str4,Object str5,Object str6,Object str7){
        String  strTarget1 = String.valueOf(str1);
        String  strTarget2 = EnumerateExpression.sqlValueOne3(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5),String.valueOf(str6),String.valueOf(str7));
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if("".equals(strTarget2)){
            flag = true;
            map.put("flag", flag);
            map.put("data", strTarget2);
            return map;
        }
        if (strTarget1.equals(strTarget2)){
            flag = true;
        }
        map.put("flag",flag);
        map.put("data",strTarget2);
        return map;
    }


    /***
     * 判断两个字段是否相等 相等返回false，不相等返回true
     * @param str1 第一个为传的参数
     * @param str2 第二个为传的sql语句
     * @param str3 第三个为sql语句用到的参数（产品代码）
     * @param str4 第四个为sql语句用到的参数（时间）
     * @return
     */
    public static Map<String,Object> isEqualssql(Object str1,Object str2,Object str3,Object str4,Object str5){
        String  strTarget1 = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  strTarget2 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;
        if("".equals(strTarget2)){
            flag = true;
            map.put("flag", flag);
            map.put("data", strTarget2);
            return map;
        }
        if (strTarget1.equals(strTarget2)){
            flag = false;
        }
        map.put("flag",flag);
        map.put("data",strTarget2);
        return map;
    }

    /**
     * //条件判断 当condition等于conditionValue时，target是否等于targetValue,不等于返回true
     * @param condition
     * @param conditionValue
     * @param target
     * @param targetValue
     * @param str3 第三个为sql语句用到的参数（产品代码）
     * @param str4 第四个为sql语句用到的参数（时间）
     * @return
     */
    public static Map<String, Object> judgeValueByCond2sql(Object condition, Object conditionValue, Object target, Object targetValue,Object str3,Object str4,Object str5){
        String  strTarget1 = EnumerateExpression.sqlValueOne(String.valueOf(conditionValue),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  strTarget2 = EnumerateExpression.sqlValueOne(String.valueOf(targetValue),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
//        if (null == target) {
//            return ExpressionUtil.resultMap(false, "target不能为空！");
//        }
        if("0".equals(strTarget1) || StringUtil.isEmpty(strTarget1)){
            return ExpressionUtil.resultMap(true, "查询不到数据！");
        }
        List<String>  conditionValueList =  StringUtil.splitToList(",", strTarget1);
        List<String>  targetValueList =  StringUtil.splitToList(",", strTarget2);
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if("".equals(String.valueOf(conditionValue))){
            flag = true;
            map.put("flag", flag);
            return map;
        }
        if (StringUtil.isBlank(String.valueOf(condition))
                ||StringUtil.isBlank(target.toString())
                ||StringUtil.isEmptyList(conditionValueList)
                ||StringUtil.isEmptyList(targetValueList)){
            map.put("flag",flag);
            map.put("data",target);
            return map;
        }

        if (conditionValueList.contains(condition)){
            flag =  !(String.valueOf(targetValueList.get(0)).equals(target));
        }

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;

    }

    /**
     * DB0034
     *str3  产品代码的值
     * conditionValue  sql语句
     *str5 产品代码字符串
     * targetValue 客户类型字符串
     */
    public static Map<String, Object> judgeValueByCondsql34(Object conditionValue,  Object targetValue,Object str3,List<Map<String, Object>>  mapList,Object str5){
        //执行sql语句
        String  strTarget1 = EnumerateExpression.sqlValueOne(String.valueOf(conditionValue),String.valueOf(str3),null,null);

        if("0".equals(strTarget1) || StringUtil.isEmpty(strTarget1)){
            return ExpressionUtil.resultMap(true, "查询不到数据！");
        }
        if (null == mapList) {
            return ExpressionUtil.resultMap(false, "mapList为空");
        }
        Set<String> strlist=new HashSet<>();
        for(Map<String, Object>  sm:mapList){
            if(ObjectUtils.nullSafeEquals(str3,sm.get(String.valueOf(str5)))&&
                    !ObjectUtils.isEmpty(sm.get(String.valueOf(targetValue)))){
                strlist.add(sm.get(String.valueOf(targetValue)).toString());
            }
        }
        Map<String,Object> map = new HashMap<>();
        StringBuilder ss=new StringBuilder();
        boolean flag = false;
        if(strlist.size()>1){
            flag = false;
            map.put("flag", flag);
            for(String str:strlist){
                ss.append(str);
                ss.append(",");
            }
            map.put("data", ss.toString());
            return map;
        }else {
            flag = true;
            map.put("flag", flag);
        }
        return map;

    }
    /**
     * //条件判断 当condition等于conditionValue时，target是否等于targetValue,不等于返回false
     * @param condition
     * @param conditionValue
     * @param target
     * @param targetValue
     * @param str3 第三个为sql语句用到的参数（产品代码）
     * @param str4 第四个为sql语句用到的参数（时间）
     * @return
     */
    public static Map<String, Object> judgeValueByCond7sql(Object condition, Object conditionValue, Object target, Object targetValue,Object str3,Object str4,Object str5){
        String  strTarget1 = EnumerateExpression.sqlValueOne(String.valueOf(conditionValue),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  strTarget2 = EnumerateExpression.sqlValueOne(String.valueOf(targetValue),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
//        if (null == target) {
//            return ExpressionUtil.resultMap(false, "target不能为空！");
//        }
        if("0".equals(strTarget1)){
            return ExpressionUtil.resultMap(true, "查询不到数据！");
        }
        List<String>  conditionValueList =  StringUtil.splitToList(",", strTarget1);
        List<String>  targetValueList =  StringUtil.splitToList(",", strTarget2);
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if("".equals(String.valueOf(conditionValue))){
            flag = true;
            map.put("flag", flag);
            return map;
        }
        if (StringUtil.isBlank(String.valueOf(condition))
                ||StringUtil.isBlank(target.toString())
                ||StringUtil.isEmptyList(conditionValueList)
                ||StringUtil.isEmptyList(targetValueList)){
            map.put("flag",flag);
            map.put("data",target);
            return map;
        }

        if (conditionValueList.contains(condition)){
            flag =  (String.valueOf(targetValueList.get(0)).equals(target));
        }

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;

    }


    //判断字段是否是某个字段，不是的话返回false
    public static Map<String,Object> isEqualsStrsql(Object target,Object targetValue,Object str3,Object str4,Object str5){
       if(StringUtil.isEmpty((CharSequence) str5)){
           return ExpressionUtil.resultMap(true,null);
       }
        String  strTarget1 = EnumerateExpression.sqlValueOne(String.valueOf(target),String.valueOf(str3),null,String.valueOf(str5));
        String  strTarget2 = EnumerateExpression.sqlValueOne(String.valueOf(targetValue),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        if(StringUtil.equals(strTarget1,strTarget2)){
            return ExpressionUtil.resultMap(true,null);
        }
        return ExpressionUtil.resultMap(false,String.valueOf(strTarget1));
    }


    /**
     * DB0027  当condition等于conditionValue时，target是否等于targetValue,不等于返回true
     * @param condition 字段 值
     * @param conditionValue sql 值
     * @param target 字段 值
     * @param targetValue  sql 值
     * @param str3 产品代码
     * @param str4 期数
     * @param str5 地区
     * @return
     */
    public static Map<String, Object> judgeValueByCond3sql(Object condition, Object conditionValue,
                                                           Object target, Object targetValue,Object str3,Object str4,Object str5){

        if (ObjectUtils.isEmpty(str5)){
            return ExpressionUtil.resultMap(true, "地区不为空");
        }

        String strTarget1 = EnumerateExpression.sqlValueOne(String.valueOf(conditionValue),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String strTarget2 = EnumerateExpression.sqlValueOne(String.valueOf(targetValue),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
//        if (null == target) {
//            return ExpressionUtil.resultMap(false, "target不能为空！");
//        }
        if("0".equals(strTarget1)){
            return ExpressionUtil.resultMap(true, "查询不到数据！");
        }
//        String conditionValueList = strTarget1;
//        String targetValueList =  StringUtil.splitToList(",", strTarget2);
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if("".equals(String.valueOf(conditionValue))){
            flag = true;
            map.put("flag", flag);
            return map;
        }
        if (StringUtil.isBlank(String.valueOf(condition))
                ||StringUtil.isBlank(target.toString())){
            map.put("flag",flag);
            map.put("data",target);
            return map;
        }
        // 前置条件 及 有该产品
        if (strTarget1.equals(condition)){
            flag = !(strTarget2.equals(target));
        } else {
            return ExpressionUtil.resultMap(true, "前置条件不满足");
        }

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;

    }

    /**
     * DB0028 只校验地区为空的 当condition等于conditionValue时，target是否等于targetValue,不等于返回true
     * @param condition 字段 值
     * @param conditionValue sql 值
     * @param target 字段 值
     * @param targetValue  sql 值
     * @param str3 产品代码
     * @param str4 期数
     * @param str5 地区
     * @return
     */
    public static Map<String, Object> judgeValueByCond4sql(Object condition, Object conditionValue,
                                                           Object target, Object targetValue,Object str3,Object str4,Object str5){
        if (ObjectUtils.isEmpty(str5)){
            return ExpressionUtil.resultMap(true, "地区不为空");
        }

        String strTarget1 = EnumerateExpression.sqlValueOne(String.valueOf(conditionValue),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String strTarget2 = EnumerateExpression.sqlValueOne(String.valueOf(targetValue),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
//        if (null == target) {
//            return ExpressionUtil.resultMap(false, "target不能为空！");
//        }
        if("0".equals(strTarget1)){
            return ExpressionUtil.resultMap(true, "查询不到数据！");
        }
//        String conditionValueList = strTarget1;
//        String targetValueList =  StringUtil.splitToList(",", strTarget2);
        Map<String,Object> map = new HashMap<>();
        boolean flag = false;
        if("".equals(String.valueOf(conditionValue))){
            flag = true;
            map.put("flag", flag);
            return map;
        }
        if (StringUtil.isBlank(String.valueOf(condition))
                ||StringUtil.isBlank(target.toString())){
            map.put("flag",flag);
            map.put("data",target);
            return map;
        }
        // 前置条件 及 有该产品
        if (strTarget1.equals(condition)){
            flag = !(strTarget2.equals(target));
        } else {
            return ExpressionUtil.resultMap(true, "前置条件不满足");
        }

        map.put("flag",flag);
        if (!flag){
            map.put("data",target);
        }
        return map;

    }
}
