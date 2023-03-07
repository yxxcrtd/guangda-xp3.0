package com.joyintech.tams.common.svreport.datacheck.beforeinsert.util;

import com.google.common.collect.Lists;
import com.joyintech.framework.util.StringUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.xmlbeans.GDurationBuilder.stripTrailingZeros;

/**
 * 金额表达式
 */
public class BigDecimalExpression {
    public static String greaterStr = ">";
    public static String equalStr = "=";
    public static String lessStr = "<";
    public static String greaterEqualStr = ">=";
    public static String lessEqualStr = "<=";

    //参数转换
    public static BigDecimal convertToBigdecimal(Object arg0) {
        if(arg0 instanceof String){
            return new BigDecimal(arg0.toString());
        }else if(arg0 instanceof Long){
            return new BigDecimal((Long) arg0);
        }else if(arg0 instanceof BigDecimal){
            return (BigDecimal) arg0;
        }
        else{
            return null;
        }

    }

    // 判断字符串是不是数字（包含负值、小数点）
    public static boolean isNumeric(Object strObj) {
        String str = "";
        if(strObj == null ){
            return false;
        }
        str = strObj.toString();
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        Matcher match = pattern.matcher(str);
        if (match.matches()) {
            return true;
        } else {
            return false;
        }
    }


    //根据参数值list获取 加/减 汇总值
    public static Map<String,Object> dealCountByStr(List<String> list,BigDecimal countNumber) {
        Map<String, Object> map = new HashMap<>();
        for (String s : list) {
            countNumber = countNumber.add(convertToBigdecimal(s));
        }
        map.put("countNumber",countNumber);
        map.put("flag",true);
        return map;
    }

    //在范围之内  包含 target1,target2  例如 0<= target <= 30
    public static Map<String, Object> betweenIn(Object target, Object target1, Object target2){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target) || StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        if (Objects.isNull(target2)){
            map.put("data",target2);
            return map;
        }
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        BigDecimal t2 = new BigDecimal("0");
        boolean flag2 = true;
        String tar = String.valueOf(target);
        try {
            t = convertToBigdecimal(tar);
        } catch (Exception e) {
            data = tar;
            flag2=false;
        }
        String tar1 = String.valueOf(target1);
        try {
            t1 = convertToBigdecimal(tar1);
        } catch (Exception e) {
            data = tar1;
            flag2=false;
        }
        String tar2 = String.valueOf(target2);
        try {
            t2 = convertToBigdecimal(tar2);
        } catch (Exception e) {
            data = tar2;
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)||Objects.isNull(t2)){
            flag2 = false;
        }
        if (flag2){
            if (t.compareTo(t1)==0 || t.compareTo(t2) == 0){
                flag = true;
            }else {
                //业务逻辑校验 target1<=target<=target2
                flag = t.compareTo(t1) > 0 && t.compareTo(t2) < 0;
            }
            data = tar;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //在范围之内  包含 不包含 target1,target2  例如 0< target < 30
    public static Map<String, Object> betweenOut(Object target, Object target1, Object target2){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target)||StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        if (Objects.isNull(target2)){
            map.put("data",target2);
            return map;
        }
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        BigDecimal t2 = new BigDecimal("0");
        boolean flag2 = true;
        String tar = String.valueOf(target);
        try {
            t = convertToBigdecimal(tar);
        } catch (Exception e) {
            data = tar;
            flag2=false;
        }
        String ta1 = String.valueOf(target1);
        String ta2 = String.valueOf(target2);
        try {
            t1 = convertToBigdecimal(ta1);
        } catch (Exception e) {
            data = ta1;
            flag2=false;
        }
        try {
            t2 = convertToBigdecimal(ta2);
        } catch (Exception e) {
            data = ta2;
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)||Objects.isNull(t2)){
            flag2 = false;
        }
        if (flag2){
            //业务逻辑校验 target1< target< target2
            flag = t.compareTo(t1) > 0 && t.compareTo(t2) < 0;
            data = tar;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //大于等于某值
    public static Map<String, Object> greaterOrEqual(Object target,Object target1){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target) || StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            map.put("data",target);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        String tar = String.valueOf(target);
        try {
            t = convertToBigdecimal(tar);
        } catch (Exception e) {
            data = tar;
            flag2=false;
        }
        String tar1 = String.valueOf(target1);
        try {
            t1 = convertToBigdecimal(tar1);
        } catch (Exception e) {
            data = tar1;
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)){
            flag2 = false;
        }
        if (flag2){
            flag = t.compareTo(t1) > 0 || t.compareTo(t1) == 0;
            data = tar;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //当...为... 某值与某值相等
    public static Map<String, Object> conditionEqual(Object target,Object target1,Object target2,Object target3){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target) || StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target1) || StringUtil.isEmpty(String.valueOf(target1))){
            return map;
        }
        //当target!=target1时，无需进行下一步校验
        if (!String.valueOf(target).equals(String.valueOf(target1))){
            map.put("flag",true);
            return map;
        }

        if (!StringUtil.isNotEmpty(target2)&&!StringUtil.isNotEmpty(target3)){
            map.put("flag",true);
            return map;
        }

        if (Objects.isNull(target2) || Objects.isNull(target3)){
            map.put("data",null);
            return map;
        }
        String tar = String.valueOf(target);
        String tar1 = String.valueOf(target1);
        String tar2 = String.valueOf(target2);
        String tar3 = String.valueOf(target3);

        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(tar2);
        } catch (Exception e) {
            data = tar2;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(tar3);
        } catch (Exception e) {
            data = tar3;
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)){
            flag2 = false;
        }
        if (flag2){
            data = tar;
            if (StringUtils.equals(tar,tar1)){
                if (t.compareTo(t1) == 0){
                    flag = true;
                }
                data = tar2;
            }else{
                flag = true;
            }
        }
        map.put("flag",flag);
        map.put("data",data);
        return map;
    }

    //当...为... 某值在某个范围
    public static Map<String, Object> conditionBetweenIn(Object target,Object target1,Object target2,Object target3,Object target4){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (!StringUtil.isNotEmpty(target)){
            map.put("flag",true);
            return map;
        }
        if (!StringUtil.isNotEmpty(target1) || !StringUtil.isNotEmpty(target2) || !StringUtil.isNotEmpty(target3) || !StringUtil.isNotEmpty(target4)){
            map.put("data",null);
            return map;
        }
        //参数转换
        String tar = String.valueOf(target);
        String tar1 = String.valueOf(target1);
        String tar2 = String.valueOf(target2);
        String tar3 = String.valueOf(target3);
        String tar4 = String.valueOf(target4);

        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        BigDecimal t2 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(tar2);
        } catch (Exception e) {
            data = tar2;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(tar3);
        } catch (Exception e) {
            data = tar3;
            flag2=false;
        }
        try {
            t2 = convertToBigdecimal(tar4);
        } catch (Exception e) {
            data = tar4;
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)||Objects.isNull(t2)){
            flag2 = false;
        }
        if (flag2){
            data = tar;
            if (StringUtils.equals(tar,tar1)){
                if (t.compareTo(t1)==0 || t.compareTo(t2) == 0){
                    flag = true;
                }else{
                    flag = t.compareTo(t1) > 0 && t.compareTo(t2) < 0;
                }
                data = tar2;
            }else {
                flag = true;
            }
        }
        map.put("flag",flag);
        map.put("data",data);
        return map;
    }

    /**
     *
     * @param sqlObject sql语句
     * @param pbcProdId 人行产品代码
     * @param target1   校验的值
     * @return
     */
    public static Map<String, Object> notBeEndOfSql(Object sqlObject,Object pbcProdId,Object target1){
        if(pbcProdId == null || target1 == null){
            return ExpressionUtil.resultMap(true,null);
        }
        BigDecimal target= convertToBigdecimal(target1);
        if(new BigDecimal(BigInteger.ZERO).compareTo(target) == 0){
            Object target2=EnumerateExpression.sqlValueOne(String.valueOf(sqlObject),String.valueOf(pbcProdId),null,null);
            if(new BigDecimal(BigInteger.ZERO).compareTo(convertToBigdecimal(target2)) == 0){
                return ExpressionUtil.resultMap(false,String.valueOf(target1));
            }
        }
        return ExpressionUtil.resultMap(true,null);
    }

    //两值相等
    public static Map<String, Object> beEqual(Object target,Object target1){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if(Objects.isNull(target)&&Objects.isNull(target1)){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target)){
            map.put("data",target);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        //参数转换
        String tar = String.valueOf(target);
        String tar1 = String.valueOf(target1);
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(tar);
        } catch (Exception e) {
            data = tar;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(tar1);
        } catch (Exception e) {
            data = tar1;
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)){
            flag2 = false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2
            flag = t.compareTo(t1) == 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //为人名币时 两值相等, target满足为CNY 则 target1 target2需要相等
    /*public static Map<String, Object> beEqual(String target ,String target1,String target2){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
    }*/

    //大于某值校验
    public static Map<String,Object> greaterTo(Object target,Object target1){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target) || StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data","比较参数为空！");
            return map;
        }
        //参数转换
        String tar = String.valueOf(target);
        String tar1 = String.valueOf(target1);
        data = tar;
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(tar);
        } catch (Exception e) {
            data = tar;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(tar1);
        } catch (Exception e) {
            data = tar1;
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)){
            flag2 = false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2
            flag = t.compareTo(t1) > 0;
            data = tar;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }


    public static Map<String,Object> greaterToSql(Object target,Object target1,Object pbcProdId){
        target=EnumerateExpression.sqlValueOne(String.valueOf(target),String.valueOf(pbcProdId),null,null);
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target) || StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data","比较参数为空！");
            return map;
        }
        //参数转换
        String tar = String.valueOf(target);
        String tar1 = String.valueOf(target1);
        data = tar;
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(tar);
        } catch (Exception e) {
            data = tar;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(tar1);
        } catch (Exception e) {
            data = tar1;
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)){
            flag2 = false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2
            flag = t.compareTo(t1) > 0;
            data = tar;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //金额不等于0校验
    public static Map<String,Object> notEqualsZero(Object target){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target)){
            map.put("data",target);
            return map;
        }
        String tar = String.valueOf(target);
        data = tar;
        BigDecimal t = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(tar);
        } catch (Exception e) {
            data = tar;
            flag2=false;
        }
        if (Objects.isNull(t)){
            flag2 = false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2
            flag = t.compareTo(new BigDecimal("0")) == 0;
            flag = !flag;
            data = tar;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    /**
     * 当sqlObj查到的数据等于param时，tagValue不应有数
     * @param pbcProdId
     * @param tagValue
     * @param sqlObj
     * @param param
     * @return
     */
    public static Map<String,Object> isZeroOrNullOfsql(Object pbcProdId,Object tagValue,Object sqlObj,Object param) {
        //sql查询参数为空不进行校验
        if(pbcProdId == null){
            return ExpressionUtil.resultMap(true,null);
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(sqlObj),String.valueOf(pbcProdId),null,null);
        if(StringUtil.equals(target,String.valueOf(param))){
            if(tagValue == null || StringUtil.equals(String.valueOf(tagValue),"")){
                return ExpressionUtil.resultMap(true,null);
            }
            if(isZeroOrNull(String.valueOf(tagValue))){
                return ExpressionUtil.resultMap(true,null);
            }
            return ExpressionUtil.resultMap(false,String.valueOf(tagValue));
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 当sqlObj查到的数据等于param时，tagValue应该大于0
     * @param pbcProdId
     * @param tagValue
     * @param sqlObj
     * @param param
     * @return
     */
    public static Map<String,Object> notZeroOrNullOfsql(Object pbcProdId,Object tagValue,Object sqlObj,Object param) {
        //sql查询参数为空不进行校验
        if(pbcProdId == null){
            return ExpressionUtil.resultMap(true,null);
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(sqlObj),String.valueOf(pbcProdId),null,null);
        if(StringUtil.equals(target,String.valueOf(param))){
            if(ObjectUtils.isEmpty(tagValue) || StringUtil.equals(String.valueOf(tagValue),"")){
                return ExpressionUtil.resultMap(false,String.valueOf(tagValue));
            }
            if(!ObjectUtils.isEmpty(tagValue)) {
                BigDecimal tagValueBigDecimal = convertToBigdecimal(tagValue);
                if (tagValueBigDecimal == null) {
                    return ExpressionUtil.resultMap(false, String.valueOf(tagValue));
                }else{
                    if (BigDecimal.ZERO.compareTo(tagValueBigDecimal) > -1) {
                        return ExpressionUtil.resultMap(false, String.valueOf(tagValue));
                    }
                }
            }else{
                return ExpressionUtil.resultMap(false, String.valueOf(tagValue));
            }
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 当sqlObj查到的数据等于param时，tagValue应该大于0
     * @param pbcProdId
     * @param tagValue
     * @param sqlObj
     * @param param
     * @return
     */
    public static Map<String,Object> notZeroOrNullOfsql173(Object pbcProdId,Object tagValue,Object sqlObj,Object param,Object ccy,Object str1) {
        //sql查询参数为空不进行校验
        if(pbcProdId == null){
            return ExpressionUtil.resultMap(true,null);
        }
        //不满足条件,不进行校验
        if(!ObjectUtils.nullSafeEquals(ccy,str1)){
            return ExpressionUtil.resultMap(true,null);
        }
        //不满足条件，不走此校验
        if(!ObjectUtils.nullSafeEquals(ccy,str1)){
            return ExpressionUtil.resultMap(true,null);
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(sqlObj),String.valueOf(pbcProdId),null,null);
        if(StringUtil.equals(target,String.valueOf(param))){
            if(ObjectUtils.isEmpty(tagValue) || StringUtil.equals(String.valueOf(tagValue),"")){
                return ExpressionUtil.resultMap(false,String.valueOf(tagValue));
            }
            if(!ObjectUtils.isEmpty(tagValue)) {
                BigDecimal tagValueBigDecimal = convertToBigdecimal(tagValue);
                if (tagValueBigDecimal == null) {
                    return ExpressionUtil.resultMap(false, String.valueOf(tagValue));
                }else{
                    if (BigDecimal.ZERO.compareTo(tagValueBigDecimal) > -1) {
                        return ExpressionUtil.resultMap(false, String.valueOf(tagValue));
                    }
                }
            }else{
                return ExpressionUtil.resultMap(false, String.valueOf(tagValue));
            }
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 当sqlObj执行结果等于list1中数值时，当tagValue>0 list2中的值也>0。
     * @param pbcProdId
     * @param tagValue
     * @param list1
     * @param sqlObj
     * @param list2
     * @return
     */
    public static Map<String,Object> biggerThenZero(Object pbcProdId,Object tagValue,List<Object> list1,Object sqlObj,List<Object> list2) {
        //sql查询参数为空不进行校验
        if(pbcProdId == null){
            return ExpressionUtil.resultMap(true,null);
        }
        if(!ObjectUtils.isEmpty(tagValue)) {
            BigDecimal tagValueBigDecimal = convertToBigdecimal(tagValue);
            if (tagValueBigDecimal == null) {
                return ExpressionUtil.resultMap(true, null);
            }else {
                if (BigDecimal.ZERO.compareTo(tagValueBigDecimal) > -1) {
                    return ExpressionUtil.resultMap(true, null);
                }
            }
        }else {
            return ExpressionUtil.resultMap(true,null);
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(sqlObj),String.valueOf(pbcProdId),null,null);
        for(Object a:list1) {
            //当sqlObj执行结果等于list1中数值时
            if (StringUtil.equals(String.valueOf(target),String.valueOf(a))) {
                for (Object b:list2){
                    if(!ObjectUtils.isEmpty(b)) {
                        BigDecimal bBigDecimal = convertToBigdecimal(b);
                        if (bBigDecimal == null) {
                            return ExpressionUtil.resultMap(false, String.valueOf(b));
                        }else{
                            if (BigDecimal.ZERO.compareTo(bBigDecimal) > -1) {
                                return ExpressionUtil.resultMap(false, String.valueOf(b));
                            }
                        }
                    }else{
                        return ExpressionUtil.resultMap(false,String.valueOf(b));
                    }
                }
            }
        }
        return ExpressionUtil.resultMap(true,null);
    }

    //金额不等于0校验,且为空不校验
    public static Map<String,Object> notEqualsZero2(Object target){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target)){
            map.put("flag", true);
            map.put("data",target);
            return map;
        }
        String tar = String.valueOf(target);
        data = tar;
        BigDecimal t = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(tar);
        } catch (Exception e) {
            data = tar;
            flag2=false;
        }
        if (Objects.isNull(t)){
            flag2 = false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2
            flag = t.compareTo(new BigDecimal("0")) == 0;
            flag = !flag;
            data = tar;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    /**
     * list1的和 - val <= 合计 <= list1 + val
     * @param list1
     * @param list2
     * @param total
     * @param val
     * @return
     */
    public static Map<String, Object> betweenIn2(List<Object> list1, List<Object> list2, Object total, String val) {
        String total1 ="";
        if(ObjectUtils.isEmpty(total)){
            total1="0";
        }else {
            total1=String.valueOf(total);
        }
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        map.put("flag", flag);
        // 判断两个list是否为空
        if (0 == list1.size() || 0 == list2.size()) {
            map.put("flag", false);
            map.put("data", "参数不能为空");
        }

        // 判断两个list的对数是否相等
        if (list1.size() != list2.size()) {
            map.put("flag", false);
            map.put("data", "数据与运算符个数不匹配");
        }

        // 存放计算结果
        BigDecimal result = new BigDecimal(0);

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if (i == j) {
                    // 加法
                    if ("+".equals(String.valueOf(list2.get(j)))) {
                        if(list1.get(i) != null && !StringUtil.equals(list1.get(i),"null")) {
                            result = result.add(new BigDecimal(String.valueOf(list1.get(i))));
                        }
                    }

                    // 减法
                    if ("-".equals(String.valueOf(list2.get(j)))) {
                        if(list1.get(i) != null && !StringUtil.equals(list1.get(i),"null")) {
                            result = result.subtract(new BigDecimal(String.valueOf(list1.get(i))));
                        }
                    }

                    // 乘法
                    if ("*".equals(String.valueOf(list2.get(j)))) {
                        if(list1.get(i) != null && !StringUtil.equals(list1.get(i),"null")) {
                            result = result.multiply(new BigDecimal(String.valueOf(list1.get(i))));
                        }
                    }

                    // 除法
                    if ("/".equals(String.valueOf(list2.get(j)))) {
                        if(list1.get(i) != null && !StringUtil.equals(list1.get(i),"null")) {
                            result = result.divide(new BigDecimal(String.valueOf(list1.get(i))), 2, BigDecimal.ROUND_HALF_UP);
                        }
                    }
                }
            }
        }

        // 存放计算结果
        BigDecimal result1 = result.subtract(new BigDecimal(val));
        BigDecimal result2 = result.add(new BigDecimal(val));

        // <= 合计 <=
        if ((result2.compareTo(new BigDecimal(total1)) > 0 || result2.compareTo(new BigDecimal(total1)) == 0)
                && (new BigDecimal(total1).compareTo(result1) > 0 || new BigDecimal(total1).compareTo(result1) == 0)) {
            return ExpressionUtil.resultMap(true, "合计值:" + total + ",误差值:" + val);
        }
        return ExpressionUtil.resultMap(false, "合计值:" + total + ",误差值:" + val);
    }

    // 多个 BigDecimal 数字值相加、减、乘、除，并判断与统计值是否相等（不计算非数字的参数）
    public static Map<String, Object> multiAvlAdd(Object total, List<Object> list1, List<Object> list2) {
        String total1 ="";
        if(ObjectUtils.isEmpty(total)){
            total1="0";
        }else {
            total1=String.valueOf(total);
        }
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        map.put("flag", flag);

        // 判断两个list是否为空
        if (0 == list1.size() || 0 == list2.size()) {
            map.put("flag", false);
            map.put("data", "参数不能为空");
        }

        // 判断两个list的对数是否相等
        if (list1.size() != list2.size()) {
            map.put("flag", false);
            map.put("data", "数据与运算符个数不匹配");
        }

        try {
            // 存放计算结果
            BigDecimal result = new BigDecimal(0);

            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (i == j) {
                        // 加法
                        if ("+".equals(String.valueOf(list2.get(j)))) {
                            if(list1.get(i) != null) {
                                result = result.add(new BigDecimal(String.valueOf(list1.get(i))));
                            }
                        }

                        // 减法
                        if ("-".equals(String.valueOf(list2.get(j)))) {
                            if(list1.get(i) != null) {
                                result = result.subtract(new BigDecimal(String.valueOf(list1.get(i))));
                            }
                        }

                        // 乘法
                        if ("*".equals(String.valueOf(list2.get(j)))) {
                            if(list1.get(i) != null) {
                                result = result.multiply(new BigDecimal(String.valueOf(list1.get(i))));
                            }
                        }

                        // 除法
                        if ("/".equals(String.valueOf(list2.get(j)))) {
                            if(list1.get(i) != null) {
                                result = result.divide(new BigDecimal(String.valueOf(list1.get(i))), 2, BigDecimal.ROUND_HALF_UP);
                            }
                        }
                    }
                }
            }

            BigDecimal totalBd = new BigDecimal(total1);
            map.put("flag", totalBd.compareTo(result) == 0 ? true : false);
            map.put("data", result);
        } catch (Exception e) {
            map.put("flag", false);
            map.put("data", list1 + " - " + list2);
        }
        return map;
    }

    // 多个 BigDecimal 数字值相加、减、乘、除，并判断与统计值是否相等（不计算非数字的参数）
    public static Map<String, Object> multiAvlAddZg05(Object total, List<Object> list1, List<Object> list2,Object str1) {
        String total1 ="";
        if(ObjectUtils.isEmpty(total)){
            total1="0";
        }else {
            total1=String.valueOf(total);
        }
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        map.put("flag", flag);

        //条件=空
        if(ObjectUtils.isEmpty(str1)){
            map.put("flag", true);
            return map;
        }
        //条件类型判断
//        if(!ObjectUtils.nullSafeEquals("3",str1)){
//            map.put("flag", true);
//            return map;
//        }

        // 判断两个list是否为空
        if (0 == list1.size() || 0 == list2.size()) {
            map.put("flag", false);
            map.put("data", "参数不能为空");
        }

        // 判断两个list的对数是否相等
        if (list1.size() != list2.size()) {
            map.put("flag", false);
            map.put("data", "数据与运算符个数不匹配");
        }

        try {
            // 存放计算结果
            BigDecimal result = new BigDecimal(0);

            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (i == j) {
                        // 加法
                        if ("+".equals(String.valueOf(list2.get(j)))) {
                            if(list1.get(i) != null) {
                                result = result.add(new BigDecimal(String.valueOf(list1.get(i))));
                            }
                        }

                        // 减法
                        if ("-".equals(String.valueOf(list2.get(j)))) {
                            if(list1.get(i) != null) {
                                result = result.subtract(new BigDecimal(String.valueOf(list1.get(i))));
                            }
                        }

                        // 乘法
                        if ("*".equals(String.valueOf(list2.get(j)))) {
                            if(list1.get(i) != null) {
                                result = result.multiply(new BigDecimal(String.valueOf(list1.get(i))));
                            }
                        }

                        // 除法
                        if ("/".equals(String.valueOf(list2.get(j)))) {
                            if(list1.get(i) != null) {
                                result = result.divide(new BigDecimal(String.valueOf(list1.get(i))), 2, BigDecimal.ROUND_HALF_UP);
                            }
                        }
                    }
                }
            }

            BigDecimal totalBd = new BigDecimal(total1);
            map.put("flag", totalBd.compareTo(result) == 0 ? true : false);
            map.put("data", result);
        } catch (Exception e) {
            map.put("flag", false);
            map.put("data", list1 + " - " + list2);
        }
        return map;
    }
    public static Map<String, Object> numberListCompare(String str1, String str2,String compareStr) {
        Map<String, Object> map = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        if(StringUtil.isEmpty(str1) || StringUtil.isEmpty(str2) || StringUtil.isEmpty(compareStr)){
            map.put("data", "str1=" + str1 + ", str2=" + str2 + ", compareStr="+compareStr);
            map.put("flag", false);
            return map;
        }else{
            list1 = StringUtil.splitToList(",", str1);
            list2 = StringUtil.splitToList(",", str2);
            return numberListCompare(list1, list2, compareStr);
        }
    }


    // 多个 BigDecimal 值的 加/减 汇总值 与 另外的多个 BigDecimal 值的 加/减 汇总值 比较
    public static Map<String, Object> numberListCompare(List<String> list1, List<String> list2,String compareStr) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> countMap = new HashMap<>();
        boolean flag;
        map.put("data", list1.toString() + "," + list2.toString());
        try {
            BigDecimal sum1 = new BigDecimal(0);
            BigDecimal sum2 = new BigDecimal(0);
            countMap = dealCountByStr(list1,sum1);
            Boolean resultFlag1 = countMap.get("flag") == null ? false : (boolean) countMap.get("flag");
            if (resultFlag1){
                sum1 = (BigDecimal) countMap.get("countNumber");     
            }
            countMap = dealCountByStr(list2,sum2);
            Boolean resultFlag2 = countMap.get("flag") == null ? false : (boolean) countMap.get("flag");
            if (resultFlag2){
                sum2 = (BigDecimal) countMap.get("countNumber");
            }
            if (resultFlag1 && resultFlag2){
                //根据传入map中的比较符号进行比较
                if (StringUtil.equals(compareStr,equalStr)){
                    if (sum1.compareTo(sum2) == 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else if(StringUtil.equals(compareStr,greaterStr)){
                    if (sum1.compareTo(sum2) > 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else if(StringUtil.equals(compareStr,lessStr)){
                    if (sum1.compareTo(sum2) < 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else if(StringUtil.equals(compareStr,greaterEqualStr)){
                    if (sum1.compareTo(sum2) >= 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else if(StringUtil.equals(compareStr,lessEqualStr)){
                    if (sum1.compareTo(sum2) <= 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else{
                    flag = false;    
                }
            }else {
                flag = false;    
            }
            map.put("flag", flag);
        } catch (Exception e) {
            flag = false;
            map.put("flag", flag);
        }
        return map;
    }


    // 1个list的汇总值 与其他两个list的汇总值进行比较
    public static Map<String, Object> numberListsCompare(List<String> list1, List<String> list2,String compareStr1, 
                                                         List<String> list3,String compareStr2) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> countMap = new HashMap<>();
        boolean flag;
        map.put("data", list1.toString() + "," + list2.toString()+ "," + list3.toString());
        try {
            BigDecimal sum1 = new BigDecimal(0);
            BigDecimal sum2 = new BigDecimal(0);
            BigDecimal sum3 = new BigDecimal(0);
            countMap = dealCountByStr(list1,sum1);
            Boolean resultFlag1 = countMap.get("flag") == null ? false : (boolean) countMap.get("flag");
            if (resultFlag1){
                sum1 = (BigDecimal) countMap.get("countNumber");
            }
            countMap = dealCountByStr(list2,sum2);
            Boolean resultFlag2 = countMap.get("flag") == null ? false : (boolean) countMap.get("flag");
            if (resultFlag2){
                sum2 = (BigDecimal) countMap.get("countNumber");
            }
            countMap = dealCountByStr(list3,sum3);
            Boolean resultFlag3 = countMap.get("flag") == null ? false : (boolean) countMap.get("flag");
            if (resultFlag3){
                sum3 = (BigDecimal) countMap.get("countNumber");
            }
            if (resultFlag1 && resultFlag2 && resultFlag3){
                //根据传入map中的比较符号进行比较
                if (StringUtil.equals(compareStr1,equalStr)){
                    if (sum1.compareTo(sum2) == 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else if(StringUtil.equals(compareStr1,greaterStr)){
                    if (sum1.compareTo(sum2) > 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else if(StringUtil.equals(compareStr1,lessStr)){
                    if (sum1.compareTo(sum2) < 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else if(StringUtil.equals(compareStr1,greaterEqualStr)){
                    if (sum1.compareTo(sum2) >= 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else if(StringUtil.equals(compareStr1,lessEqualStr)){
                    if (sum1.compareTo(sum2) <= 0){
                        flag = true;
                    }else{
                        flag = false;
                    }
                }else{
                    flag = false;
                }
                if (flag){
                    //当第一次比较通过后，进行第二次比较
                    if (StringUtil.equals(compareStr2,equalStr)){
                        if (sum1.compareTo(sum3) == 0){
                            flag = true;
                        }else{
                            flag = false;
                        }
                    }else if(StringUtil.equals(compareStr2,greaterStr)){
                        if (sum1.compareTo(sum3) > 0){
                            flag = true;
                        }else{
                            flag = false;
                        }
                    }else if(StringUtil.equals(compareStr2,lessStr)){
                        if (sum1.compareTo(sum3) < 0){
                            flag = true;
                        }else{
                            flag = false;
                        }
                    }else if(StringUtil.equals(compareStr2,greaterEqualStr)){
                        if (sum1.compareTo(sum3) >= 0){
                            flag = true;
                        }else{
                            flag = false;
                        }
                    }else if(StringUtil.equals(compareStr2,lessEqualStr)){
                        if (sum1.compareTo(sum3) <= 0){
                            flag = true;
                        }else{
                            flag = false;
                        }
                    }else{
                        flag = false;
                    }
                }
            }else {
                flag = false;
            }
            map.put("flag", flag);
        } catch (Exception e) {
            flag = false;
            map.put("flag", flag);
        }
        return map;
    }


    /**
     * target小于target1时 返回true
     * target大于target1时 返回false
     * @param targetObj
     * @param target1Obj
     * @return
     */
    public static Map<String,Object> lessTo(Object targetObj,Object target1Obj){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(targetObj) || Objects.isNull(target1Obj)){
            map.put("data",targetObj);
            return map;
        }
        data = targetObj.toString();
        String target = targetObj.toString();
        String target1 = target1Obj.toString();

        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target.toString();
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            data = target1.toString();
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)){
            flag2 = false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2
            flag = t.compareTo(t1) < 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //对于有符号的数据转化成数字进行比较，将replaceStr替换为"", 如果转换后的 num2<=num1<=num3 返回true
    public static Map<String, Object> conditionBetweenInWithoutString(String num1,String num2,String num3,String replaceStr) {
        Map<String, Object> map = new HashMap<>();
        boolean flag;
        map.put("data", num1 + "," + num2 + "," + num3);
        try {
            num1 = num1.replace(replaceStr,"");
            num2 = num2.replace(replaceStr,"");
            num3 = num3.replace(replaceStr,"");
            BigDecimal bigDecimal1 = new BigDecimal(num1);
            BigDecimal bigDecimal2 = new BigDecimal(num2);
            BigDecimal bigDecimal3 = new BigDecimal(num3);
            if (bigDecimal1.compareTo(bigDecimal2) >=0 && bigDecimal1.compareTo(bigDecimal3) <=0){
                flag = true;
            }else {
                flag = false;
            }
            map.put("flag",flag);
        } catch (Exception e) {
            flag = false;
            map.put("flag", flag);
        }
        return map;
    }

    //判断数据是否同向(同正或者同负),如果包含符号则传入replace,不包含符号可以随意传入一个值，比如%
    public static Map<String, Object> isSyntropy(String num1, String num2, String replaceStr) {
        Map<String, Object> map = new HashMap<>();
        boolean flag;
        map.put("data", num1 + "," + num2);

        try {
            replaceStr = replaceStr == null ? "%" : replaceStr;
            num1 = num1.replace(replaceStr,"");
            num2 = num2.replace(replaceStr,"");
            BigDecimal bigDecimal1 = convertToBigdecimal(num1);
            BigDecimal bigDecimal2 = convertToBigdecimal(num2);

            // 增加非空判断
            if (bigDecimal1 == null || bigDecimal2 == null) {
                return ExpressionUtil.resultMap(true, "参数num1或参数num2为空了");
            }

            if (bigDecimal1.compareTo(BigDecimal.ZERO) == bigDecimal2.compareTo(BigDecimal.ZERO)){
                flag = true;    
            }else {
                flag = false;
            }
            map.put("flag",flag);
        } catch (Exception e) {
            flag = false;
            map.put("flag", flag);
        }
        return map;
    }

    //判断数字长度 totalLength是总长度，decimalLength 小数位长度
    public static Map<String,Object> checkNumLenByParam(Object num, int  totalLength,int decimalLength){
        // 字符串的num
        String numString = "";

        Map<String, Object> map = new HashMap<>();
        //如果传入空值，则直接校验通过
        if(Objects.isNull(num) || StringUtil.isEmpty(String.valueOf(num))){
            map.put("flag",true);
            return map;
        }

        if (num instanceof String) {
//            numString = stripTrailingZeros(String.valueOf(num));
            numString = String.valueOf(num);
        }
        if (num instanceof BigDecimal) {
//            numString = stripTrailingZeros(String.valueOf(new BigDecimal(String.valueOf(num))));
            numString = String.valueOf(new BigDecimal(String.valueOf(num)));
        }


        boolean flag = false;
        if (!StringUtil.isNotEmpty(num)) {
            map.put("flag", true);
            return map;
        }
        try {
            BigDecimal bigDecimalNum = new BigDecimal(numString);
            List<String> numList = StringUtil.splitToList(".", numString);
            if (!StringUtil.isEmptyList(numList)) {
                if (numList.size() == 1) {
                    flag = numList.get(0).length() <= (totalLength-decimalLength);
                } else {
                    flag = numList.get(0).length() <= (totalLength-decimalLength) && numList.get(1).length() <= (decimalLength);
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            flag = false;
        }
        map.put("flag", flag);
        if (!flag) {
            map.put("data", numString);
        }
        return map;

    }

    //当...不等于... 某值在某个范围 target != target1   target3<=target2<=target4
    public static Map<String, Object> conditionBetweenIn2(Object target,Object target1,Object target2,Object target3,Object target4){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (!StringUtil.isNotEmpty(target)){
            map.put("flag",true);
            return map;
        }
        if (!StringUtil.isNotEmpty(target1) || !StringUtil.isNotEmpty(target2) || !StringUtil.isNotEmpty(target3) || !StringUtil.isNotEmpty(target4)){
            map.put("data",null);
            return map;
        }
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        BigDecimal t2 = new BigDecimal("0");
        boolean flag2 = true;
        String tar = String.valueOf(target);
        String tar1 = String.valueOf(target1);
        String tar2 = String.valueOf(target2);
        String tar3 = String.valueOf(target3);
        String tar4 = String.valueOf(target4);
        try {
            t = convertToBigdecimal(tar2);
        } catch (Exception e) {
            data = tar2;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(tar3);
        } catch (Exception e) {
            data = tar3;
            flag2=false;
        }
        try {
            t2 = convertToBigdecimal(tar4);
        } catch (Exception e) {
            data = tar4;
            flag2=false;
        }
        if (Objects.isNull(t)||Objects.isNull(t1)||Objects.isNull(t2)){
            flag2 = false;
        }
        if (flag2){
            data = tar;
            if (!StringUtils.equals(tar,tar1)){
                if (t.compareTo(t1)==0 || t.compareTo(t2) == 0){
                    flag = true;
                }else{
                    flag = t.compareTo(t1) > 0 && t.compareTo(t2) < 0;
                }
                data = tar2;
            }else{
                flag = true;
            }
        }
        map.put("flag",flag);
        map.put("data",data);
        return map;
    }

    /**
     *  是否为空或为零，是的话 返回true
     * @param target
     * @return
     */
    public static Boolean isZeroOrNull(String target) {
        if (ObjectUtils.nullSafeEquals(target, null)) {
            return true;
        }

        try {
            BigDecimal val = convertToBigdecimal(target);

            // 增加 val 空判断
            if (val == null) {
                return true;
            }

            return val.compareTo(BigDecimal.ZERO) == 0;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     *  传值是否大于0
     * @param target
     * @return
     */
    public static Boolean greaterThen0(String target) {
        if (ObjectUtils.nullSafeEquals(target, null)) {
            return false;
        }

        try {
            BigDecimal val = convertToBigdecimal(target);

            // 增加 val 空判断
            if (val == null) {
                return false;
            }

            return val.compareTo(BigDecimal.ZERO) > 0;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 如果为0 则通过  返回true
     * 如果不为0 则需要提示 返回false
     * @param targetObj
     * @return
     */
    public static Map<String, Object> notZero(Object targetObj) {
        if(targetObj == null){
            return ExpressionUtil.resultMap(false,targetObj);
        }
        String target = targetObj.toString();

        if(isZeroOrNull(target)){
            return ExpressionUtil.resultMap(false,target);
        }else{
            return ExpressionUtil.resultMap(true,target);
        }
    }

    //DB0174
    public static Map<String, Object> notZero174(Object targetObj,Object targetObj2) {

        if(!ObjectUtils.isEmpty(targetObj)||!ObjectUtils.isEmpty(targetObj2)){
            return ExpressionUtil.resultMap(false,targetObj.toString()+","+targetObj2.toString());
        }else {
            return ExpressionUtil.resultMap(true,null);

        }
    }

    /**
     * 如果为0或者为空 则通过  返回true
     * 如果不为0 则需要提示 返回false
     * @param targetObj
     * @return
     */
    public static Map<String, Object> notZero2(Object targetObj) {
        if(targetObj == null){
            return ExpressionUtil.resultMap(true,targetObj);
        }
        String target = targetObj.toString();

        if(isZeroOrNull(target)){
            return ExpressionUtil.resultMap(true,target);
        }else{
            return ExpressionUtil.resultMap(false,target);
        }
    }


    /**
     * 计算收益率
     * @param thisValBig   当期值
     * @param lastValBig   上期值
     * @param roundType 尾数处理方式
     * @return
     */
    private static BigDecimal calYield( BigDecimal thisValBig,BigDecimal lastValBig,Integer roundType) {
        if(thisValBig == null || lastValBig == null || lastValBig.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO;
        }
        if(roundType == null){
            roundType = BigDecimal.ROUND_HALF_UP;
        }
        return thisValBig.subtract(lastValBig).divide(lastValBig,4,roundType);
    }


    /**
     *  增长率大于lineNum时 返回false
     * @param thisValListStrObj   当期值
     * @param lastValListStrObj   上期值
     * @param lineNumObj   上限值
     * @param isABS   增长率是否取绝对值
     * @return
     */
    private static  Map<String, Object> compareYield(Object thisValListStrObj,Object lastValListStrObj,Object lineNumObj,Boolean isABS) {
        if(thisValListStrObj == null){
            return ExpressionUtil.resultMap(true,thisValListStrObj);
        }
        if(lastValListStrObj == null){
            return ExpressionUtil.resultMap(true,thisValListStrObj);
        }
        String thisValListStr = thisValListStrObj.toString();
        String lastValListStr = lastValListStrObj.toString();

        List<String>  thisValList =  com.joyintech.framework.util.StringUtil.splitToList(",",thisValListStr);
        List<String>  lastValList =  com.joyintech.framework.util.StringUtil.splitToList(",",lastValListStr);

        BigDecimal thisValBig = BigDecimal.ZERO;
        BigDecimal lastValBig = BigDecimal.ZERO;
        try {
            for (String thisVal: thisValList) {
                if(!StringUtil.isEmpty(thisVal)){
                    thisValBig = thisValBig.add(convertToBigdecimal(thisVal));
                }
            }
            for (String lastVal: lastValList) {
                if(!StringUtil.isEmpty(lastVal)){
                    lastValBig = lastValBig.add(convertToBigdecimal(lastVal));
                }
            }
        } catch (Exception e) {
            return ExpressionUtil.resultMap(true,null);
        }

        BigDecimal yield = calYield(thisValBig, lastValBig, BigDecimal.ROUND_HALF_UP);

        yield = isABS ? yield.abs() : yield;

        return lessTo(yield,lineNumObj);
    }


    /**
     * 增长率大于lineNum时 返回false(增长率绝对值)
     * @param thisValListStrObj
     * @param lastValListStrObj
     * @param lineNumObj
     * @return
     */
    public static Map<String, Object> compareYieldABS(Object thisValListStrObj,Object lastValListStrObj,Object lineNumObj) {
        return compareYield(thisValListStrObj,lastValListStrObj,lineNumObj,true);
    }

    /**
     * 增长率大于lineNum时 返回false(增长率非绝对值)
     * @param thisValListStrObj
     * @param lastValListStrObj
     * @param lineNumObj
     * @return
     */
    public static Map<String, Object> compareYieldNotABS(Object thisValListStrObj,Object lastValListStrObj,Object lineNumObj) {
        return compareYield(thisValListStrObj,lastValListStrObj,lineNumObj,false);
    }

    /**
     *  tag1不为0，tagList中全都为0 则需要提示 返回false
     *  其余情况 返回true
     * @param tag1Obj
     * @param tagListStrObj
     * @return
     */
    public static Map<String, Object> notZeroEqual(Object tag1Obj ,Object tagListStrObj,Object termid) {
        tag1Obj = EnumerateExpression.sqlValueOne(String.valueOf(tag1Obj),null,String.valueOf(termid),null);

        if(tag1Obj == null){
            return ExpressionUtil.resultMap(true,tag1Obj);
        }
        String tag1 = tag1Obj.toString();
        if(!isZeroOrNull(tag1)){
            if(tagListStrObj == null){
                return ExpressionUtil.resultMap(true,null);
            }
            String tagListStr = tagListStrObj.toString();

            List<String>  tagList =  com.joyintech.framework.util.StringUtil.splitToList(",",tagListStr);
            for (String tag: tagList) {
                if(!isZeroOrNull(tag)){//如果存在不为0的，则通过校验
                    return ExpressionUtil.resultMap(true,null);
                }
            }
            return ExpressionUtil.resultMap(false,tag1);
        }else{
            return ExpressionUtil.resultMap(true,null);
        }
    }

    /**
     *  tag1为0或为空，tagListStrObj不为0或空 则需要提示 返回false
     *  其余情况 返回true
     * @param tag1Obj
     * @param tagListStrObj
     * @return
     */
    public static Map<String, Object> zeroEqual(Object tag1Obj ,Object tagListStrObj,Object termid) {
        tag1Obj = EnumerateExpression.sqlValueOne(String.valueOf(tag1Obj),null,String.valueOf(termid),null);
        //tag1Obj为空，为"" ， 为0时返回true
        if(!ObjectUtils.isEmpty(tag1Obj) && !StringUtil.equals(String.valueOf(tag1Obj) ,"")){
            BigDecimal tag1ObjBigDecimal = convertToBigdecimal(tag1Obj);
            if(tag1ObjBigDecimal == null){
                return ExpressionUtil.resultMap(true, null);
            }else {
                if (BigDecimal.ZERO.compareTo(tag1ObjBigDecimal) != 0) {
                    return ExpressionUtil.resultMap(true, null);
                }
            }
        }

        if(!StringUtil.equals(String.valueOf(tagListStrObj),"") && !ObjectUtils.isEmpty(tagListStrObj)){
            BigDecimal zeroEqualDecimal = convertToBigdecimal(tagListStrObj);
            if(zeroEqualDecimal == null ){
                return ExpressionUtil.resultMap(true,null);
            }else {
                if (BigDecimal.ZERO.compareTo(zeroEqualDecimal) != 0) {
                    return ExpressionUtil.resultMap(false, String.valueOf(tagListStrObj));
                }
                return ExpressionUtil.resultMap(true, null);
            }
        }else{
            return ExpressionUtil.resultMap(true,null);
        }
    }

    /**
     * 当 tag1-tag4有值时，tag5为空
     * @param tag1 条件字段1
     * @param tag2 条件字段2
     * @param tag3 条件字段3
     * @param tag4 条件字段4
     * @param tag5 目标字段
     * @return
     */
    public static Map<String, Object> paramNotNullTarget(Object tag1 ,Object tag2,Object tag3,Object tag4,Object tag5) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(tag1) || Objects.isNull(tag2) || Objects.isNull(tag3) || Objects.isNull(tag4)){
            map.put("flag",true);
            return map;
        }
        BigDecimal t1 = new BigDecimal("0");
        BigDecimal t2 = new BigDecimal("0");
        BigDecimal t3 = new BigDecimal("0");
        BigDecimal t4 = new BigDecimal("0");
        String s1 = String.valueOf(tag1);
        String s2 = String.valueOf(tag2);
        String s3 = String.valueOf(tag3);
        String s4 = String.valueOf(tag4);
        try {
            t1 = convertToBigdecimal(s1);
        } catch (Exception e) {
            map.put("data",s1);
            return map;
        }
        try {
            t2 = convertToBigdecimal(s2);
        } catch (Exception e) {
            map.put("data",s2);
            return map;
        }
        try {
            t3 = convertToBigdecimal(s3);
        } catch (Exception e) {
            map.put("data",s3);
            return map;
        }
        try {
            t4 = convertToBigdecimal(s4);
        } catch (Exception e) {
            map.put("data",s4);
            return map;
        }
        if (Objects.isNull(t1)||Objects.isNull(t2)||Objects.isNull(t3)||Objects.isNull(t4)){
            return map;
        }
        //业务校验 tag5 应为空
        flag = Objects.isNull(tag5);
        if (Objects.nonNull(tag5)){
            flag = StringUtils.isEmpty(String.valueOf(tag5));
        }
        map.put("flag",flag);
        map.put("data",tag5);
        return map;
    }

    /**
     * 两个参数正负符号必须相同
     * @param tag
     * @param tag1
     * @param tag2
     * @param tag3
     * @param mapList
     * @return
     */
    public static Map<String, Object> compareSymbol(Object tag, Object tag1 ,Object tag2,Object tag3, List<Map<String,Object>> mapList){
        //如果主要维度为空则不进行校验
        if(ObjectUtils.isEmpty(tag) || ObjectUtils.isEmpty(tag1)){
            return ExpressionUtil.resultMap(true,null);
        }

        if(ObjectUtils.isEmpty(tag2) || ObjectUtils.isEmpty(tag3)){
            return ExpressionUtil.resultMap(false,"脚本配置错误");
        }
        String tagString = String.valueOf(tag);//pbcProdId的vaule
        String tag1String = String.valueOf(tag1);//customProfit的value
        String tag2String = String.valueOf(tag2);//cusYearYield
        String tag3String = String.valueOf(tag3);//pbcProdId

        //获取mapList中tag3String与tagString相等的且tag2String有值的
        Map<String,Object> tagMap = new HashMap<>();
        for(Map<String,Object> tempMap:mapList){
            if(StringUtil.equals(String.valueOf(tempMap.get(tag3String)),tagString)
                    && !StringUtil.equals(String.valueOf(tempMap.get(tag2String)),"null")
                    && !StringUtil.equals(String.valueOf(tempMap.get(tag2String)),"")){
                tagMap = tempMap;
            }
        }
        //如果没有对应的数值就不进行校验
        if(tagMap == null || tagMap.size() == 0){
            return ExpressionUtil.resultMap(true,null);
        }

        BigDecimal tag1BigDecimal = convertToBigdecimal(tag1String);
        if(!ObjectUtils.isEmpty(tagMap.get(tag2String))){
            BigDecimal tag2BigDecimal = convertToBigdecimal(tagMap.get(tag2String));
            if(tag2BigDecimal == null && tag1BigDecimal == null){
                return ExpressionUtil.resultMap(true,null);
            }else if(tag2BigDecimal == null || tag1BigDecimal == null){
                return ExpressionUtil.resultMap(false,  tag1String + "&"+ String.valueOf(tagMap.get(tag2String)));
            }else if(tag2BigDecimal != null && tag1BigDecimal != null){
                if (BigDecimal.ZERO.compareTo((tag2BigDecimal.multiply(tag1BigDecimal))) > 0) {
                    return ExpressionUtil.resultMap(false, tag1String + "&" + String.valueOf(tagMap.get(tag2String)));
                }
            }
        }
        return ExpressionUtil.resultMap(true,null);
    }

//    public static Map<String, Object> notEqual(String tag1ListStr,String tag2ListStr){
//        List<String>  tag1List =  com.joyintech.framework.util.StringUtil.splitToList(",",tag1ListStr);
//        List<String>  tag2List =  com.joyintech.framework.util.StringUtil.splitToList(",",tag2ListStr);
//        BigDecimal tag1 = BigDecimal.ZERO;
//        BigDecimal tag2 = BigDecimal.ZERO;
//        for (String tag: tag1List) {
//            if(isZeroOrNull(tag)){
//                continue;
//            }
//            tag1 = tag1.add(convertToBigdecimal(tag));
//        }
//    }

    /**
     *  增强版 大于等于判断
     * @param targetObj
     * @param target1Obj
     * @param target2Obj
     * @param target3Obj
     * @return
     */
    public static Map<String, Object> greaterOrEqualPlus(Object targetObj,Object target1Obj,Object target2Obj,Object target3Obj){
        if (!StringUtil.isNotEmpty(targetObj)){
            return ExpressionUtil.resultMap(true,targetObj);
        }
        if(!isNumeric(targetObj)){
            return ExpressionUtil.resultMap(false,targetObj);
        }
        if(!isNumeric(target1Obj) && !isNumeric(target2Obj) &&!isNumeric(target3Obj)){//不可同时为空
            return ExpressionUtil.resultMap(false,targetObj);
        }

        BigDecimal sumTars = sumTarget(target1Obj, target2Obj, target3Obj);

        return greaterOrEqual(targetObj,sumTars.toString());
    }


    public static Map<String, Object> greaterOrEqualPlusSql(Object targetObj,Object target1Obj,Object target2Obj,Object target3Obj){

        targetObj=EnumerateExpression.sqlValueOne(String.valueOf(targetObj),null,String.valueOf(target3Obj),null);


        if (!StringUtil.isNotEmpty(targetObj)){
            return ExpressionUtil.resultMap(true,targetObj);
        }
        if(!isNumeric(targetObj)){
            return ExpressionUtil.resultMap(false,targetObj);
        }
        if(!isNumeric(target1Obj) && !isNumeric(target2Obj)){//不可同时为空
            return ExpressionUtil.resultMap(false,targetObj);
        }

        BigDecimal sumTars = sumTarget(target1Obj, target2Obj);

        return greaterOrEqual(targetObj,sumTars.toString());
    }



    /**
     *  增强版 大于等于判断
     * @param targetObj
     * @param target1Obj
     * @param target2Obj
     * @param target3Obj
     * @return
     */
    public static Map<String, Object> greaterOrEqualPlus(Object targetObj,Object target1Obj,Object target2Obj,Object target3Obj, Object target4Obj){
        if(!isNumeric(targetObj)){
            return ExpressionUtil.resultMap(false,targetObj);
        }
        if(!isNumeric(target1Obj) && !isNumeric(target2Obj) && !isNumeric(target3Obj) && !isNumeric(target4Obj)){//不可同时为空
            return ExpressionUtil.resultMap(false,targetObj);
        }

        BigDecimal sumTars = sumTarget(target1Obj, target2Obj, target3Obj, target4Obj);

        return greaterOrEqual(targetObj,sumTars.toString());
    }

    /**
     *  汇总参数数值，至少返回0
     * @param targets
     * @return
     */
    private static BigDecimal sumTarget(Object ... targets) {
        BigDecimal sumTars = BigDecimal.ZERO;
        for (Object tarObj : targets) {
            if(tarObj == null){
                continue;
            }
            String tar = tarObj.toString();
            try {
                sumTars = sumTars.add(convertToBigdecimal(tar));
            } catch (Exception e) {
                //TODO  nothing todo
            }
        }
        return sumTars;
    }


    /**
     *
     *  如果 condition 与 conditionValue 相等，sourceVal > 0 ,剩下3个参数相加也应该>0,否则返回false
     *
     *      如果 sourceVal >0 ,
     *          如果 target1+target2+target3 > 0
     *              返回 true
     *          否则
     *              返回 false
     *      否则
     *        返回 true
     *
     * @param conditionObj         待判断的字符串
     * @param conditionValueObj    核准字符串
     * @param sourceValObj         条件数值 > 0
     * @param target1Obj            待判断数值1
     * @param target2Obj            待判断数值2
     * @param target3Obj            待判断数值3
     * @return
     */
    public static Map<String,Object> judgeValueGreater0(Object conditionObj,Object conditionValueObj,Object sourceValObj
            ,Object target1Obj,Object target2Obj,Object target3Obj) {
        if(conditionObj == null || conditionValueObj == null || !isNumeric(sourceValObj)){
            return ExpressionUtil.resultMap(false,conditionObj);
        }
        if(!isNumeric(target1Obj) && !isNumeric(target2Obj) &&!isNumeric(target3Obj)){//不可同时为空
            return ExpressionUtil.resultMap(false,sourceValObj);
        }
        String condition = conditionObj.toString();
        String conditionValue = conditionValueObj.toString();
        String sourceVal = sourceValObj.toString();

        if(condition.equals(conditionValue)){
            if(greaterThen0(sourceVal) ){
                BigDecimal sumTarget = sumTarget(target1Obj, target2Obj, target3Obj);
                if(sumTarget.compareTo(BigDecimal.ZERO) > 0){
                    return ExpressionUtil.resultMap(true,condition);
                }else{
                    return ExpressionUtil.resultMap(false,sourceVal);
                }
            }else{
                return ExpressionUtil.resultMap(true,condition);
            }
        }else{
            return ExpressionUtil.resultMap(true,condition);
        }

    }

    //DB0196
    public static Map<String,Object> judgeValueGreater0196(Object obj1,Object obj2,Object obj3
            ,Object obj4,Object obj5,Object obj6) {

        //数据类型不等于 1 ，则不校验
        if(!ObjectUtils.nullSafeEquals("1",obj4)){
            return ExpressionUtil.resultMap(true,String.valueOf(obj2)+","+String.valueOf(obj3));
        }
        String  targetvalue = EnumerateExpression.sqlValueOne3(String.valueOf(obj1),String.valueOf(obj5),null,String.valueOf(obj5),String.valueOf(obj6),null);

        //obj1 执行为空的话
        if(ObjectUtils.isEmpty(targetvalue)){
            return ExpressionUtil.resultMap(true,String.valueOf(obj2)+","+String.valueOf(obj3));
        }

        if(ObjectUtils.isEmpty(obj2)&&ObjectUtils.isEmpty(obj3)){
            return ExpressionUtil.resultMap(false,String.valueOf(obj2)+","+String.valueOf(obj3));
        }else {
            return ExpressionUtil.resultMap(true,null);

        }

    }

    //DB0199
    public static Map<String,Object> judgeValueGreater0199(Object obj1,Object obj2,Object obj3,Object obj3a,Object obj3b,Object obj3c,Object obj3d
            ,Object obj4,Object obj5,Object obj6) {

        //数据类型不等于 1 ，则不校验
        if(!ObjectUtils.nullSafeEquals("1",obj4)){
            return ExpressionUtil.resultMap(true,null);
        }
        String  targetvalue = EnumerateExpression.sqlValueOne3(String.valueOf(obj1),String.valueOf(obj5),String.valueOf(obj5),String.valueOf(obj5),String.valueOf(obj6),null);

        //obj1 执行为空的话
        if(ObjectUtils.isEmpty(targetvalue)){
            return ExpressionUtil.resultMap(true,null);
        }

        if(ObjectUtils.isEmpty(obj2)&&ObjectUtils.isEmpty(obj3)&&ObjectUtils.isEmpty(obj3a)
                &&ObjectUtils.isEmpty(obj3b)&&ObjectUtils.isEmpty(obj3c)&&ObjectUtils.isEmpty(obj3d)){
            return ExpressionUtil.resultMap(false,String.valueOf(obj2)+","+String.valueOf(obj3)+","+String.valueOf(obj3a)
                    +","+String.valueOf(obj3b)+","+String.valueOf(obj3c)+","+String.valueOf(obj3d));
        }else {
            return ExpressionUtil.resultMap(true,null);

        }

    }

    /**
     *  （只比较BWB）
     *  如果 condition 与 conditionValue 相等，sourceVal > 0 ,剩下3个参数相加也应该>0,否则返回false
     *
     *      如果 sourceVal >0 ,
     *          如果 target1+target2+target3 > 0
     *              返回 true
     *          否则
     *              返回 false
     *      否则
     *        返回 true
     *
     * @param conditionObj         待判断的字符串
     * @param conditionValueObj    核准字符串
     * @param sourceValObj         条件数值 > 0
     * @param target1Obj            待判断数值1
     * @param target2Obj            待判断数值2
     * @param target3Obj            待判断数值3
     * @return
     */
    public static Map<String,Object> judgeBWBValueGreater0(String ccy,Object conditionObj,Object conditionValueObj,Object sourceValObj
            ,Object target1Obj,Object target2Obj,Object target3Obj) {
        if(ccy == null || !"BWB".equals(ccy)){
            return ExpressionUtil.resultMap(true,target1Obj);
        }else{
            return judgeValueGreater0(conditionObj,conditionValueObj,sourceValObj,target1Obj,target2Obj,target3Obj);
        }

    }
    /**
     *      当币种为BWB时，进行校验
     *      用以下公式的结果与lineNum 进行比较，如果小于lineNum，返回true
     *      (当今数值 - 原数值) / 原数值  --结果取绝对值
     * @param ccy               币种
     * @param thisValListStrObj    当今数值
     * @param target1Obj           原数值1
     * @param target2Obj           原数值2
     * @param target3Obj           原数值3
     * @param lineNumObj           上限值
     * @return
     */
    public static Map<String,Object> compareBWBYieldABSPlus(String ccy,Object thisValListStrObj,Object target1Obj,Object target2Obj,Object target3Obj,Object lineNumObj,Object strp,Object strt){

        thisValListStrObj = EnumerateExpression.sqlValueOne(String.valueOf(thisValListStrObj),String.valueOf(strp),String.valueOf(strt),String.valueOf(strt));

        if(!"BWB".equals(ccy)){
            return ExpressionUtil.resultMap(true,thisValListStrObj);
        }

        if(!isNumeric(thisValListStrObj)){
            return ExpressionUtil.resultMap(false,thisValListStrObj);
        }
        if(!isNumeric(target1Obj) && !isNumeric(target2Obj) &&!isNumeric(target3Obj)){//不可同时为空
            return ExpressionUtil.resultMap(false,thisValListStrObj);
        }
        BigDecimal sumTarget = sumTarget(target1Obj, target2Obj, target3Obj);

        return compareYieldABS(thisValListStrObj,sumTarget.toString(),lineNumObj);

    }


    public static Map<String,Object> compareBWBYieldABSPlus01(String ccy,Object thisValListStrObj,Object target1Obj,Object target2Obj,Object target3Obj,Object lineNumObj){


        if(!"BWB".equals(ccy)){
            return ExpressionUtil.resultMap(true,thisValListStrObj);
        }

        if(!isNumeric(thisValListStrObj)){
            return ExpressionUtil.resultMap(false,thisValListStrObj);
        }
        if(!isNumeric(target1Obj) && !isNumeric(target2Obj) &&!isNumeric(target3Obj)){//不可同时为空
            return ExpressionUtil.resultMap(false,thisValListStrObj);
        }
        BigDecimal sumTarget = sumTarget(target1Obj, target2Obj, target3Obj);

        return compareYieldABS(thisValListStrObj,sumTarget.toString(),lineNumObj);

    }


    //计算占比且占比不能超过某个值 超过返回false 不超过或等于返回true；num1Str 除数，num2Str 被除数
    public static Map<String, Object> percentCompare(Object num1Str,Object num2Str,Object compareNumStr){
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        if (StringUtil.equals(String.valueOf(num1Str),"null")|| StringUtil.equals(String.valueOf(num2Str),"null")){
            map.put("flag",false);
            map.put("data","被除数或除数为空");
            return map;
        }
        if (StringUtil.equals("0",String.valueOf(num2Str)) || StringUtil.equals("0.00",String.valueOf(num2Str))){
            map.put("flag",true);
            return map;
        }

        BigDecimal num1 = BigDecimal.ZERO;
        BigDecimal num2 = BigDecimal.ZERO;
        BigDecimal compareNum = convertToBigdecimal(compareNumStr);
        BigDecimal p = new BigDecimal(100);
        try {
            num1 = convertToBigdecimal(num1Str);
            num2 = convertToBigdecimal(num2Str);

            // 增加非空判断
            if (num1 == null || num2 == null) {
                return ExpressionUtil.resultMap(true, "参数num1Str或参数num2Str为空了");
            }

            BigDecimal percent = num1.divide(num2).multiply(p);
            flag = percent.compareTo(compareNum) <= 0;
        } catch (Exception e){
            flag = false;
        }
        map.put("flag",flag);
        map.put("data",compareNumStr);
        return map;
    }

    //校验数值长度 小数点前面最大长度20，小数点后保留两位
    public static Map<String, Object> checkNumLen(Object numStrp){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        try {
            if(Objects.nonNull(numStrp)){
                String numStr = String.valueOf(numStrp);
                int beforeLen = numStr.substring(0,numStr.indexOf(".")).length();
                int afterLen = numStr.substring(numStr.indexOf(".")+1,numStr.length()).length();
                if(beforeLen <= 20 && afterLen == 2){
                    flag = true;
                }
            }
        } catch (Exception e) {
            flag = false;
        }
        map.put("flag",flag);
        map.put("data",numStrp);
        return map;
    }



    /**
     * 在集合mapList中 相同主键c1下,条件字段tar1==条件值tar2时,字段tar3之和应该等于 条件字段tar1！=条件值tar2时，字段tar3之和
     * @param c1 可用逗号分割成多个
     * @param tar1
     * @param tar2
     * @param tar3
     * @param mapList
     * @return
     */
    public static Map<String,Object> conditionSumEquals(String c1, String tar1, String tar2, String tar3, List<Map<String,Object>> mapList){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (StringUtils.isEmpty(c1) || CollectionUtils.isEmpty(mapList)){
            return map;
        }
        //根据主键赛选出不同的产品
        List<String>  conditionValueList =  StringUtil.splitToList(",",c1);

        //根据主键c1去重后的list
        List<Map<String, Object>> mapList2 = mapList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparing((o) -> o.get(c1)+";"))), ArrayList::new));

        for (Map<String, Object> map1 : mapList2) {
            BigDecimal sum1 = new BigDecimal("0");
            BigDecimal sum2 = new BigDecimal("0");
            for (Map<String, Object> map2 : mapList) {
                if (map2.get(c1).equals(map1.get(c1))){
                    if (map2.get(tar1).equals(tar2)){
                        String o = (String) map2.get(tar3);
                        BigDecimal bigDecimal = new BigDecimal("0");
                        try {
                            bigDecimal = convertToBigdecimal(o);
                        } catch (Exception e) {
                            map.put("data",o);
                            return map;
                        }
                        sum1 = sum1.add(bigDecimal);
                    }else if (!map2.get(tar1).equals(tar2)){
                        String o = (String) map2.get(tar3);
                        BigDecimal bigDecimal = new BigDecimal("0");
                        try {
                            bigDecimal = convertToBigdecimal(o);
                        } catch (Exception e) {
                            map.put("data",o);
                            return map;
                        }
                        sum2 = sum2.add(bigDecimal);
                    }
                }
            }
            if (sum1.compareTo(sum2)!=0){
                map.put("data",map1.get(c1));
                return map;
            }
        }
        map.put("flag",true);
        return map;
    }

    /**
     * 在集合mapList中 相同主键c1下,条件字段tar1_1==条件值tar2_1时  字段tar3之和应该等于 条件字段tar1_2=条件值tar2_2时，字段tar3之和
     * @param c1 可用逗号分割成多个
     * @param tar1 可用逗号分割成多个
     * @param tar2 可用逗号分割成多个
     * @param tar3
     * @param mapList
     * @return
     */
    public static Map<String,Object> conditionSumEquals2(String c1, String tar1, String tar2, String tar3, List<Map<String,Object>> mapList){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (StringUtils.isEmpty(c1) || CollectionUtils.isEmpty(mapList)){
            return map;
        }
        //根据主键赛选出不同的产品
        List<String>  c1s =  StringUtil.splitToList(",",c1);
        //拆分条件字段 条件值
        List<String> tar1s = StringUtil.splitToList(",", tar1);
        List<String> tar2s = StringUtil.splitToList(",", tar2);

        //根据主键c1去重后的list
        List<Map<String, Object>> mapList2 = mapList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparing((o) -> o.get(c1)+";"))), ArrayList::new));

        for (Map<String, Object> map1 : mapList2) {
            BigDecimal sum1 = new BigDecimal("0");
            BigDecimal sum2 = new BigDecimal("0");
            for (Map<String, Object> map2 : mapList) {
                if (map2.get(c1s.get(0)).equals(map1.get("0")) && map2.get(c1s.get(1)).equals(map1.get(c1s.get(1)))){
                    if (map2.get(tar1s.get(0)).equals(tar2s.get(0))){
                        String o = (String) map2.get(tar3);
                        BigDecimal bigDecimal = new BigDecimal("0");
                        try {
                            bigDecimal = convertToBigdecimal(o);
                        } catch (Exception e) {
                            map.put("data",o);
                            return map;
                        }
                        sum1 = sum1.add(bigDecimal);
                    }else if (map2.get(tar1s.get(1)).equals(tar2s.get(1))){
                        String o = (String) map2.get(tar3);
                        BigDecimal bigDecimal = new BigDecimal("0");
                        try {
                            bigDecimal = convertToBigdecimal(o);
                        } catch (Exception e) {
                            map.put("data",o);
                            return map;
                        }
                        sum2 = sum2.add(bigDecimal);
                    }
                }
            }
            if (sum1.compareTo(sum2)!=0){
                map.put("data",map1.get(c1));
                return map;
            }
        }
        map.put("flag",true);
        return map;
    }

    /**
     * 指定主键c1下，c2中的值在maplist中只能报送一条
     * @param c1
     * @param c2
     * @param mapList
     * @return
     */
    public static Map<String,Object> conditionEffective(String c1,String c2,List<Map<String,Object>> mapList){
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String data = "";
        map.put("flag", flag);
        if (StringUtils.isEmpty(c1) || CollectionUtils.isEmpty(mapList)){
            return map;
        }
        //根据主键赛选出不同的产品
        List<String>  conditionValueList =  StringUtil.splitToList(",",c2);

        //根据主键c1去重后的list
        List<Map<String, Object>> mapList2 = mapList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparing((o) -> o.get(c1)+";"))), ArrayList::new));

        for (Map<String, Object> map1 : mapList2) {
            int count = 0;
            for (Map<String, Object> map2 : mapList) {
                if (map2.get(c1).equals(map1.get(c1))){
                    boolean flag2 = true;
                    for (String s : conditionValueList) {
                        if (StringUtils.isEmpty((String)map2.get(s))){
                            flag2 = false;
                        }
                    }
                    if (flag2){
                        count = count+1;
                    }
                }
            }
            if (count!=1){
                map.put("flag",false);
                map.put("data",map1.get(c1));
                break;
            }
        }
        return map;
    }

    /**
     *两个值，如果相等，则返回false,不相等，则返回true;
     * @param target 当前参数
     * @param target1 SQL查询的数据数据
     * @return
     */
    public static Map<String, Object>  sqlbenotEqual(String target,String target1){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target)){
            map.put("data",target+","+target1);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target+","+target1);
            return map;
        }
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = addBigdecimalList(target);
        } catch (Exception e) {
            data = target.toString();
            flag2=false;
        }
        try {
            t1 =addBigdecimalList(target1);
        } catch (Exception e) {
            data = target1.toString();
            flag2=false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2
            flag = t.compareTo(t1) != 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }
    /**
     *两个值，如果第一个值大于第二值则，返回false,否则返回true;
     * @param target 当前参数
     * @param target1 SQL查询的数据数据
     * @return
     */
    public static Map<String, Object>  sqlGreaterThanEqual(Object target,Object target1){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target)){
            map.put("data",target+","+target1);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target+","+target1);
            return map;
        }
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = addBigdecimalList(String.valueOf(target));
        } catch (Exception e) {
            data = target.toString();
            flag2=false;
        }
        try {
            t1 =addBigdecimalList(String.valueOf(target1));
        } catch (Exception e) {
            data = target1.toString();
            flag2=false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2
            flag = t.compareTo(t1) < 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    public static BigDecimal  addBigdecimalList(String target){
        List<String>  thisValList =  com.joyintech.framework.util.StringUtil.splitToList(",",target);
        BigDecimal thisValBig = BigDecimal.ZERO;
        for (String thisVal: thisValList) {
            if(!StringUtil.isEmpty(thisVal)){
                thisValBig = thisValBig.add(convertToBigdecimal(thisVal));
            }
        }
        return thisValBig;
    }


    /**
     * 
     * @param c1 主键，用逗号分隔
     * @param tar1 条件字段的key
     * @param tar2 需要进行汇总判断的条件
     * @param tar2 需要进行汇总判断的数据条件
     * @param mapList
     * @return
     */
    public static Map<String,Object> conditionSumAllEquals(String c1, String tar1, String tar2,String tar3,List<Map<String,Object>> mapList){
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String data = "";
        map.put("flag", flag);
        map.put("data", c1+","+tar1+","+tar2+","+tar3 + mapList.toString());
        try {
            //根据主键筛选出对应的主键维度
            List<String>  c1s =  StringUtil.splitToList(",",c1);

            //根据对应的主键将符合的数据放入同一个list,根据主键放入map
            Map<String,List<Map<String,Object>>> listMap = new HashMap<>();
            for (Map<String, Object> stringObjectMap : mapList) {
                String key = "";
                for (String s : c1s) {
                    if (StringUtil.isNotEmpty(stringObjectMap.get(s))){
                        key = key + stringObjectMap.get(s);
                    }
                }
                if (StringUtil.isNotEmpty(key)) {
                    if(listMap.get(key) == null){
                        List<Map<String,Object>> newLIst = new ArrayList<>();
                        newLIst.add(stringObjectMap);
                        listMap.put(key,newLIst);
                    }else{
                        listMap.get(key).add(stringObjectMap);
                    }
                }
            }

            //循环map每个维度的数据进行校验
            for (String s : listMap.keySet()) {
                BigDecimal inNumber = BigDecimal.ZERO;
                BigDecimal notInNumber = BigDecimal.ZERO;
                for (Map<String, Object> stringObjectMap : listMap.get(s)) {
                    String tar1Str =  stringObjectMap.get(tar1) == null? "" : stringObjectMap.get(tar1).toString();
                    if (StringUtil.equals(tar1Str,tar2)){
                        inNumber = inNumber.add(stringObjectMap.get(tar3) == null? BigDecimal.ZERO : new BigDecimal(stringObjectMap.get(tar3).toString()));
                    }else{
                        notInNumber = notInNumber.add(stringObjectMap.get(tar3) == null? BigDecimal.ZERO : new BigDecimal(stringObjectMap.get(tar3).toString()));
                    }
                }
                data = data + s+","+ inNumber.toString()+","+ notInNumber.toString()+";";
                if (flag && inNumber.compareTo(notInNumber) != 0){
                    flag = false;    
                }
            }    
        }catch (Exception e){
            flag = false;
            map.put("flag", flag);
            return map;    
        }
        
        map.put("flag",flag);
        map.put("data",data);
        return map;
    }

    public static void main(String[] args) {
        System.out.println(betweenIn("2.555","2",3));
    }

    /**
     *sql语句执行的结果与 本期数据 根据keyObj汇总的值作比较  等于返回false
     * @param sqlObj     sql语句
     * @param termidObj  期数  用于填补sql语句的参数
     * @param keyObj     汇总值的key
     * @param mapList    本期数据
     * @return
     */
    public static Map<String, Object> notBeEqualOfSql(Object sqlObj,Object termidObj,Object keyObj,List<Map<String,Object>> mapList){
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(sqlObj),null,String.valueOf(termidObj),null);
        if(target == null || StringUtil.equals(target,"")){
            return ExpressionUtil.resultMap(true,null);
        }
        BigDecimal tatol = null;
        for(Map<String,Object> tempMap :mapList){
            if(tempMap.get(String.valueOf(keyObj)) != null){
                if(tatol == null){
                    tatol = convertToBigdecimal(tempMap.get(String.valueOf(keyObj)));
                }else{
                    tatol = tatol.add(convertToBigdecimal(tempMap.get(String.valueOf(keyObj))));
                }
            }
        }
        return notBeEqual(tatol,target);
    }

    /**
     * 不相等 则返回true
     * 报错 返回
     * @param target1Obj
     * @param target2Obj
     * @return
     */
    public static Map<String, Object> notBeEqual(Object target1Obj,Object target2Obj){
        try {
            if(target1Obj == null || target2Obj == null){
                return ExpressionUtil.resultMap(true,target1Obj);
            }
            String target1 = target1Obj.toString();
            String target2 = target2Obj.toString();

            BigDecimal target1Big = convertToBigdecimal(target1);
            BigDecimal target2Big = convertToBigdecimal(target2);

            // 增加非空判断
            if (target1Big == null || target2Big == null) {
                return ExpressionUtil.resultMap(true, "参数target1Big或参数target2Big为空了");
            }

            if(target1Big.compareTo(target2Big) != 0){
                return ExpressionUtil.resultMap(true,target1);
            }else{
                return ExpressionUtil.resultMap(false,target1);
            }

        } catch (Exception e) {
            return ExpressionUtil.resultMap(true,target1Obj);
        }
    }


    /**
     * 对币种为BWB的数据进行，条件判断 condition == conditionValue
     *      成立，则对target1、target2 比较， 不相等 则返回true
     *      不成立 直接返回true
     * @param target1Obj
     * @param target2Obj
     * @return
     */
    public static Map<String, Object> notBeEqualBWBCond(Object conditionObj,Object conditionValueObj,String ccy,Object target1Obj,Object target2Obj){
        if(!"BWB".equals(ccy)){
            return ExpressionUtil.resultMap(true,conditionObj);
        }
        if(conditionObj == null || conditionValueObj == null ){
            return ExpressionUtil.resultMap(false,conditionObj);
        }
        String condition = conditionObj.toString();
        String conditionValue = conditionValueObj.toString();

        if(condition.equals(conditionValue)){
            return notBeEqual(target1Obj,target2Obj);
        }else {
            return ExpressionUtil.resultMap(true,condition);
        }
    }
    /**
     * 条件判断 condition == conditionValue
     *      成立，则对target1、target2 比较， 不相等 则返回true
     *      不成立 直接返回true
     * @param target1Obj
     * @param target2Obj
     * @return
     */
    public static Map<String, Object> notBeEqualCond(Object conditionObj,Object conditionValueObj,Object target1Obj,Object target2Obj){
        if(conditionObj == null || conditionValueObj == null ){
            return ExpressionUtil.resultMap(false,conditionObj);
        }
        String condition = conditionObj.toString();
        String conditionValue = conditionValueObj.toString();

        if(condition.equals(conditionValue)){
            return notBeEqual(target1Obj,target2Obj);
        }else {
            return ExpressionUtil.resultMap(true,condition);
        }
    }


    /**
     * 条件判断 condition == conditionValue 时对BWB币种进行增长率判断
     *      <lineNum 时 返回true
     *
     * @param conditionObj
     * @param conditionValueObj
     * @param ccy
     * @param thisValStrObj
     * @param target1Obj
     * @param target2Obj
     * @param target3Obj
     * @param lineNumObj
     * @return
     */
    public static Map<String, Object> compareBWBYieldABSCond(Object conditionObj,Object conditionValueObj,String ccy,Object thisValStrObj,Object target1Obj,Object target2Obj,Object target3Obj,Object lineNumObj,Object strp,
            Object strt){
        if(conditionObj == null || conditionValueObj == null ){
            return ExpressionUtil.resultMap(false,conditionObj);
        }
        String condition = conditionObj.toString();
        String conditionValue = conditionValueObj.toString();

        if(condition.equals(conditionValue)){
            return compareBWBYieldABSPlus(ccy,thisValStrObj,target1Obj,target2Obj,target3Obj,lineNumObj,strp,strt);
        }else {
            return ExpressionUtil.resultMap(true,condition);
        }
    }

    public static Map<String, Object> compareBWBYieldABSCond01(Object conditionObj,Object conditionValueObj,String ccy,Object thisValStrObj,Object target1Obj,Object target2Obj,Object target3Obj,Object lineNumObj,Object objter){

        conditionObj  = EnumerateExpression.sqlValueOne(String.valueOf(conditionObj),String.valueOf(target2Obj),null,null);
        target1Obj= EnumerateExpression.sqlValueOne(String.valueOf(target1Obj),String.valueOf(target2Obj),String.valueOf(objter),null);

        if(conditionObj == null || conditionValueObj == null ){
            return ExpressionUtil.resultMap(false,conditionObj);
        }
        String condition = conditionObj.toString();
        String conditionValue = conditionValueObj.toString();

        if(condition.equals(conditionValue)){
            return compareBWBYieldABSPlus01(ccy,thisValStrObj,target1Obj,target2Obj,target3Obj,lineNumObj);
        }else {
            return ExpressionUtil.resultMap(true,condition);
        }
    }



    /**
     * 条件判断： condition == conditionValue 判断
     *  是否为空或为零，是的话 返回true
     * @param targetObj
     * @return
     */
    public static Map<String, Object> notZeroCond (Object conditionObj,Object conditionValueObj,Object targetObj) {
        if(conditionObj == null || conditionValueObj == null ){
            return ExpressionUtil.resultMap(false,conditionObj);
        }
        String condition = conditionObj.toString();
        String conditionValue = conditionValueObj.toString();

        if(condition.equals(conditionValue)){
            return notZero(targetObj);
        }else {
            return ExpressionUtil.resultMap(true,condition);
        }
    }

    /**
     * 条件判断：如果ccy为BWB，conditionObj == conditionValueObj，判断 sourceValObj/(target1Obj - target2Obj)
     *      如果 >0 ,返回true
     * @param ccy
     * @param conditionObj
     * @param conditionValueObj
     * @param
     * @param target1Obj
     * @param target2Obj
     * @param lineNumObj
     * @return
     */
    public static Map<String,Object> judgeBWBValueCompare(String ccy,Object conditionObj,Object conditionValueObj
            ,Object addVal1Obj,Object addVal2Obj,Object addVal3Obj,Object addVal4Obj,Object addVal5Obj,Object addVal6Obj,Object addVal7Obj
            ,Object subVal1Obj,Object subVal2Obj,Object subVal3Obj,Object subVal4Obj,Object subVal5Obj,Object subVal6Obj
            ,Object target1Obj,Object target2Obj,Object lineNumObj) {
        if(ccy == null || !"BWB".equals(ccy)){
            return ExpressionUtil.resultMap(true,target1Obj);
        }else {
            if (conditionObj == null || conditionValueObj == null || !isNumeric(lineNumObj)) {
                return ExpressionUtil.resultMap(false, conditionObj);
            }
            if (!isNumeric(target1Obj) && !isNumeric(target2Obj)) {//不可同时为空
                return ExpressionUtil.resultMap(false, lineNumObj);
            }
            String condition = conditionObj.toString();
            String conditionValue = conditionValueObj.toString();
            String target1 = target1Obj.toString();
            String target2 = target2Obj.toString();
            String lineNum = lineNumObj.toString();
            if (!condition.equals(conditionValue)) {
                return ExpressionUtil.resultMap(true, target1Obj);
            } else {
                BigDecimal target1Big = null;
                BigDecimal target2Big = null;
                BigDecimal lineNumBig = null;
                BigDecimal fenzi = null;
                try {
                    BigDecimal addSum = sumTarget(addVal1Obj, addVal2Obj, addVal3Obj, addVal4Obj, addVal5Obj, addVal6Obj, addVal7Obj);
                    BigDecimal subSum = sumTarget(subVal1Obj, subVal2Obj, subVal3Obj, subVal4Obj, subVal5Obj, subVal6Obj);
                    fenzi = addSum.subtract(subSum);

                    target1Big = convertToBigdecimal(target1);
                    target2Big = convertToBigdecimal(target2);
                    lineNumBig = convertToBigdecimal(lineNum);
                } catch (Exception e) {
                    return ExpressionUtil.resultMap(false, lineNumObj);
                }

                // 增加非空判断
                if (target1Big == null) {
                    return ExpressionUtil.resultMap(true, "参数target1Big为空了");
                }

                BigDecimal fenmu = target1Big.subtract(target2Big);
                if(fenzi.divide(fenmu,8,BigDecimal.ROUND_HALF_UP).compareTo(lineNumBig) > 0){
                    return ExpressionUtil.resultMap(true,target1Obj);
                }else{
                    return ExpressionUtil.resultMap(false,lineNumObj);
                }
            }
        }

    }
    /**
     *  条件判断是否 大于0
     *  如果 condition1 > condition1 ，sourceVal > 0 ,剩下3个参数相加也应该>0,否则返回false
     *
     *      如果 sourceVal >0 ,
     *          如果 target1+target2+target3 > 0
     *              返回 true
     *          否则
     *              返回 false
     *      否则
     *        返回 true
     *
     * @param condition1Obj         待判断的字符串
     * @param condition2Obj         核准字符串
     * @param sourceValObj          条件数值 > 0
     * @param target1Obj            待判断数值1
     * @param target2Obj            待判断数值2
     * @param target3Obj            待判断数值3
     * @return
     */
    public static Map<String,Object> compareValueGreater0(Object condition1Obj,Object condition2Obj,Object sourceValObj
            ,Object target1Obj,Object target2Obj,Object target3Obj) {
        if(condition1Obj == null || condition2Obj == null || !isNumeric(sourceValObj)){
            return ExpressionUtil.resultMap(false,condition1Obj);
        }
        if(!isNumeric(target1Obj) && !isNumeric(target2Obj) &&!isNumeric(target3Obj)){//不可同时为空
            return ExpressionUtil.resultMap(false,target1Obj);
        }
        String condition1 = condition1Obj.toString();
        String condition2 = condition2Obj.toString();
        String sourceVal = sourceValObj.toString();
        String target1 = target1Obj.toString();
        String target2 = target2Obj.toString();
        String target3 = target3Obj.toString();

        try {
            BigDecimal condition1Big = convertToBigdecimal(condition1);
            BigDecimal condition2Big = convertToBigdecimal(condition2);

            // 增加非空判断
            if (condition1Big == null) {
                return ExpressionUtil.resultMap(true, "参数condition1Big为空了");
            }

            if(condition1Big.compareTo(condition2Big) > 0){
                if(greaterThen0(sourceVal) ){
                    BigDecimal sumTarget = sumTarget(target1, target2, target3);
                    if(sumTarget.compareTo(BigDecimal.ZERO) > 0){
                        return ExpressionUtil.resultMap(true,target1);
                    }else{
                        return ExpressionUtil.resultMap(false,target1);
                    }
                }else{
                    return ExpressionUtil.resultMap(true,target1);
                }
            }else{
                return ExpressionUtil.resultMap(true,target1);
            }
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false,target1);
        }

    }

    /**
     * DB0171
     *  币种为BWB  条件判断是否 大于0
     *  如果 condition1 > condition1 ，sourceVal > 0 ,剩下3个参数相加也应该>0,否则返回false
     *
     *      如果 sourceVal >0 ,
     *          如果 target1+target2+target3 > 0
     *              返回 true
     *          否则
     *              返回 false
     *      否则
     *        返回 true
     *
     *        当资管产品存续期募集信息中币种为“BWB”的“期末产品金额”合计值>资产负债信息表币种为“BWB”的 C0000_权益合计余额，则币种为 “BWB” 的( C4000_其他综合收益 + C3000_未分配利润 )余额>0。
     *
     * @param ccy         币种
     * @param condition1Obj         sql
     * @param condition2Obj         c0000
     * @param sourceValObj          条件数值 > 0
     * @param target1Obj            待判断数值1 c4000
     * @param target2Obj            待判断数值2 c3000
     * @param target3Obj            待判断数值3  产品代码
     * @return
     */
    public static Map<String, Object> compareBWBValueGreater0(String ccy, Object condition1Obj, Object condition2Obj, Object sourceValObj
            , Object target1Obj, Object target2Obj, Object target3Obj) {
        // 币种 为空 或不为 BWB  或产品代码为空 不校验
        if (ccy == null || target3Obj == null || !"BWB".equals(ccy)) {
            return ExpressionUtil.resultMap(true, target1Obj);
        } else {
            // 产品存续期募集
            String target = EnumerateExpression.sqlValueOne(String.valueOf(condition1Obj), String.valueOf(target3Obj), null, null);
            // 期末产品金额
            BigDecimal endamtsum = convertToBigdecimal(target);
            //
            BigDecimal c0000 = convertToBigdecimal(condition2Obj);

            if (endamtsum != null && c0000 != null) {
                if (endamtsum.compareTo(c0000) > 0) {
                    BigDecimal c4000 = convertToBigdecimal(target1Obj);
                    BigDecimal c3000 = convertToBigdecimal(target2Obj);
                    // (C4000_其他综合收益 + C3000_未分配利润) 余额>0
                    if (Objects.isNull(c4000) || Objects.isNull(c3000)) {
                        return ExpressionUtil.resultMap(false, target1Obj);
                    } else {
                        if (c4000.add(c3000).compareTo(BigDecimal.ZERO) <= 0) {
                            // 不满足条件
                            return ExpressionUtil.resultMap(false, target1Obj);
                        } else {
                            // 满足条件
                            return ExpressionUtil.resultMap(true, target1Obj);
                        }
                    }
                } else {
                    // 前置条件不满足
                    return ExpressionUtil.resultMap(true, target1Obj);
                }
            } else {
                // 前置条件不满足
                return ExpressionUtil.resultMap(true, target1Obj);
            }
            //return compareValueGreater0(condition1Obj,condition2Obj,sourceValObj,target1Obj,target2Obj,target3Obj);
        }

    }

    /**
     * 条件判断 target1是否大于target2 ，如果target1 > target2 返回true
     *      条件1：condition == conditionValue
     *      条件2：condition2 为空或为0
     *
     * @param conditionObj
     * @param conditionValueObj
     * @param condition2Obj
     * @param target1Obj
     * @param target2Obj
     * @return
     */
    public static Map<String,Object> judgeGreaterOrEqual(Object conditionObj,Object conditionValueObj,Object condition2Obj
            ,Object target1Obj,Object target2Obj) {
        if(conditionObj == null || conditionValueObj == null || !isNumeric(target1Obj) || !isNumeric(target2Obj)){
            return ExpressionUtil.resultMap(false,target1Obj);
        }
        //target1Obj target2Obj如果没值 直接返回false
        if(!StringUtil.isNotEmpty(target1Obj) && !StringUtil.isNotEmpty(target2Obj)){
            return ExpressionUtil.resultMap(false,target1Obj);
        }
        //target1Obj有值 target2Obj没值 返回true
        if(!StringUtil.isNotEmpty(target2Obj)){
            return ExpressionUtil.resultMap(true,target1Obj);
        }
        String condition = conditionObj.toString();
        String conditionValue = conditionValueObj.toString();
        String condition2 = condition2Obj.toString();
        String target1 = target1Obj.toString();
        String target2 = target2Obj.toString();

        try {
            if(condition.equals(conditionValue)){
                if(isZeroOrNull(condition2) ){
                    BigDecimal target1Big = convertToBigdecimal(target1);
                    BigDecimal target2Big = convertToBigdecimal(target2);
                    if(Objects.nonNull(target1Big) && Objects.nonNull(target2Big) &&target1Big.compareTo(target2Big) > 0){
                        return ExpressionUtil.resultMap(true,target1);
                    }else{
                        return ExpressionUtil.resultMap(false,target1);
                    }
                }else{
                    return ExpressionUtil.resultMap(true,target1);
                }
            }else{
                return ExpressionUtil.resultMap(true,target1);
            }
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false,target1);
        }
    }

    /**
     * 币种BWB
     * 满足条件后进行判断 target1是否大于target2 ，如果target1 > target2 返回true
     *      条件1：condition == conditionValue
     *      条件2：condition2 为空或为0
     *
     * @param conditionObj
     * @param conditionValueObj
     * @param condition2Obj
     * @param target1Obj
     * @param target2Obj
     * @return
     */
    public static Map<String,Object> judgeBWBGreaterOrEqual(String ccy,Object conditionObj,Object conditionValueObj,Object condition2Obj
            ,Object target1Obj,Object target2Obj) {
        if(ccy == null || !"BWB".equals(ccy)){
            return ExpressionUtil.resultMap(true,target1Obj);
        }else{
            return judgeGreaterOrEqual(conditionObj,conditionValueObj,condition2Obj,target1Obj,target2Obj);
        }
    }

    /**
     * 币种为BWB的时候 ，校验target 和 target1是否相等
     * @param ccy
     * @param target
     * @param target1
     * @return
     */
    public static Map<String, Object> beEqualBWB(String ccy,Object target,Object target1){
        if("BWB".equals(ccy)){
            return  beEqual(target,target1);
        }else{
            return ExpressionUtil.resultMap(true,target);
        }

    }

    /**
     *ZG05特殊方法当
     */
    public static Map<String, Object> CNYEqual(Object Amt,String pbcProdId,String ccy,String statusType,String Str1,List<Map<String,Object>> mapList){
        if(StringUtil.isEmpty(pbcProdId) || StringUtil.isEmpty(ccy) || StringUtil.isEmpty(statusType)){
            return ExpressionUtil.resultMap(false,"币种、产品代码或者数据类型为空");
        }
        if(StringUtil.equals(ccy,"CNY") && StringUtil.equals(statusType,"1")){
            if(Amt == null){
                return ExpressionUtil.resultMap(true,null);
            }
            Map<String,Object> map2 = new HashMap<>();
            BigDecimal Amt1 = new BigDecimal(String.valueOf(Amt));//本条数据数据状态为1且币种为CNY的数据金额
            for(Map<String,Object> mapTmp:mapList){
                if(mapTmp.get("pbcProdId") == null){
                    continue;
                }
                String mapPbcProdId = String.valueOf(mapTmp.get("pbcProdId"));
                if(mapTmp.get("pbcProdId") != null) {
                    if (StringUtil.equals(mapPbcProdId, pbcProdId) && StringUtil.equals(String.valueOf(mapTmp.get("statusType")), "2")) {
                        map2 = mapTmp;//去取同产品代码下数据状态为2的数据
                    }
                }
            }

            //取出数据类型为2 的map后进行金额比较
            if(map2 != null && map2.size() > 0){
                if(map2.get(Str1) != null){
                    BigDecimal Amt2 = new BigDecimal(String.valueOf(map2.get(Str1)));
                    if(Amt2.compareTo(Amt1) != 0){
                        return ExpressionUtil.resultMap(false,String.valueOf(Amt));
                    }else{
                        return ExpressionUtil.resultMap(true,null);
                    }
                }else{
                    return ExpressionUtil.resultMap(false,"此产品对应的数据类型为2的数据"+Str1 + "为空！");
                }
            }else{
                return ExpressionUtil.resultMap(false,"此产品为没有数据类型为2的数据");
            }
        }else{
            return ExpressionUtil.resultMap(true,null);
        }
    }

    /**
     * 同一str1下，PARAMS01的币种为'BWB'的数据应等于其他各币种（数据类型=2时）数据之和
     * @param Str1     维度主key
     * @param PARAMS01 被检查的金额key
     * @param PARAMS02 Str1对应的key
     * @param mapList
     * @return
     */
    public static Map<String, Object> BWBEqualOfPbcprodId(Object Str1,String PARAMS01,String PARAMS02,List<Map<String,Object>> mapList){
        if(StringUtil.isEmpty(String.valueOf(Str1)) || StringUtil.equals(String.valueOf(Str1),"null")){
            return ExpressionUtil.resultMap(true,null);
        }

        Map<String,Object> BWBMap = new HashMap<>();
        List<Map<String,Object>> mapStatusTypeList = Lists.newArrayList();
        for(Map<String,Object> mapTmp:mapList){
            String PARAMS02Value = String.valueOf(mapTmp.get(PARAMS02));//Str1对应的key取值
            String statusType = String.valueOf(mapTmp.get("statusType"));
            String ccy = String.valueOf(mapTmp.get("ccy"));
            if(mapTmp.get(PARAMS02) == null){
                continue;
            }
            if(StringUtil.equals(String.valueOf(Str1),PARAMS02Value) && StringUtil.equals(ccy,"BWB")){
                BWBMap = mapTmp;
            }
            if(StringUtil.equals(String.valueOf(Str1),PARAMS02Value) && StringUtil.equals(statusType,"2") && !StringUtil.equals(ccy,"BWB")){
                mapStatusTypeList.add(mapTmp);
            }
        }
        if(BWBMap == null || BWBMap.size() == 0){
            return ExpressionUtil.resultMap(false,"本产品未有BWB数据");
        }
        if(!CollectionUtils.isNotEmpty(mapStatusTypeList)){
            return ExpressionUtil.resultMap(false,"本产品未有数据类型为2的非本位币数据");
        }
        if(BWBMap.get(PARAMS01) == null){
            return ExpressionUtil.resultMap(true,null);
        }
        BigDecimal Amt = new BigDecimal(String.valueOf(BWBMap.get(PARAMS01))); //bwb的值
        BigDecimal Amttotal = new BigDecimal(BigInteger.ZERO); //汇总值

        for(Map<String,Object> tempMap:mapStatusTypeList){
            if(tempMap.get(PARAMS01) != null){
                Amttotal = Amttotal.add(new BigDecimal(String.valueOf(tempMap.get(PARAMS01))));
            }
        }
        if(Amttotal.compareTo(Amt) != 0){
            return ExpressionUtil.resultMap(false,"BWB值：" + Amt + "其他汇总值：" + Amttotal);
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 根据产品代码 同 币种，全国 “ param3 ”数据≠各省级“ param3 ”数据和！
     * @param pbcProdId 产品代码 当前 产品代码
     * @param ccy 币种 当前 币种
     * @param buyPlace 地区 当前 地区
     * @param param1 产品代码 key
     * @param param2 币种 key
     * @param param3 求和字段 key
     * @param mapList
     * @return
     */
    public static Map<String, Object> ccyEqualSumOfPbcProdId(Object pbcProdId, Object ccy, Object buyPlace, Object param1, Object param2, Object param3,List<Map<String,Object>> mapList){
        if (null == pbcProdId) {
            return ExpressionUtil.resultMap(true, "产品代码为空");
        }
        if (null == ccy) {
            return ExpressionUtil.resultMap(true, "币种为空");
        }
        if (null == buyPlace) {
            return ExpressionUtil.resultMap(true, "地区为空");
        }
        if (!StringUtil.isNotEmpty(String.valueOf(buyPlace))
                || StringUtil.equals(String.valueOf(buyPlace), "null")
                || !StringUtil.equals(String.valueOf(buyPlace), "000000")) {
            return ExpressionUtil.resultMap(true, "地区不为000000");
        }

        // 筛选当前产品产品数据 币种 分组 地区
        //Map<String, List<Map<String, Object>>> groupMap = mapList.stream().filter(e -> StringUtil.equals(e.get(param1.toString()), pbcProdId)).collect(Collectors.groupingBy(e -> e.get(param2.toString()) + e.get("buyPlace").toString()));
        Map<String, List<Map<String, Object>>> groupMap1 = mapList.stream()
                .filter(e -> StringUtil.equals(e.get(param1.toString()), pbcProdId))
                .filter(e -> StringUtil.equals(e.get(param2.toString()), ccy))
                .collect(Collectors.groupingBy(e -> e.get("buyPlace").toString()));
        // 当前 币种 存在 全国数据
        if (!groupMap1.containsKey("000000")) {
            return ExpressionUtil.resultMap(true, "当前币种没有地区为000000的数据");
        }
        // 相同 产品代码 相同 币种 地区 为 000000 的数据
        List<Map<String, Object>> sum00000Map = Lists.newArrayList();
        // 相同 产品代码 相同 币种 地区 不为 000000 的数据
        List<Map<String, Object>> sumNot00000Map = Lists.newArrayList();
//        for (Map<String, Object> map : mapList) {
//            if (StringUtil.equals(map.get(param1.toString()), pbcProdId)) {
//                if (StringUtil.equals(map.get(param2.toString()), ccy)) {
//                    if (StringUtil.equals(map.get("buyPlace"), "000000")) {
//                        sum00000Map.add(map);
//                    } else {
//                        sumNot00000Map.add(map);
//                    }
//                }
//            }
//        }
        for (String key : groupMap1.keySet()) {
            if (StringUtil.equals("000000", key)) {
                sum00000Map.addAll(groupMap1.get(key));
            } else {
                sumNot00000Map.addAll(groupMap1.get(key));
            }
        }

        if (sum00000Map.size() == 0) {
            return ExpressionUtil.resultMap(true, "当前币种没有地区为000000的数据");
        }
        BigDecimal ccyField000000 = new BigDecimal("0");
        BigDecimal ccyFieldNot000000 = new BigDecimal("0");

        List<BigDecimal> collect1 = new ArrayList<>();
        List<BigDecimal> collect2 = new ArrayList<>();
        try {
            collect1 = sum00000Map.stream().map(e -> new BigDecimal(e.get(param3.toString()).toString())).collect(Collectors.toList());
            collect2 = sumNot00000Map.stream().map(e -> new BigDecimal(e.get(param3.toString()).toString())).collect(Collectors.toList());
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false, "");
        }
        //  000000总和
        for (BigDecimal decimal : collect1) {
            ccyField000000 = ccyField000000.add(decimal);
        }
        // 不为 000000 总和
        for (BigDecimal decimal : collect2) {
            ccyFieldNot000000 = ccyFieldNot000000.add(decimal);
        }
        if (ccyField000000.equals(ccyFieldNot000000)) {
            return ExpressionUtil.resultMap(true, "相等");
        }
        return ExpressionUtil.resultMap(false, ccyField000000+"≠"+ccyFieldNot000000);
    }

    /**
     * 同一str1下，PARAMS01的币种为'BWB'的数据应等于其他各币种数据之和
     * @param Str1     维度主key
     * @param PARAMS01 被检查的金额key
     * @param PARAMS02 Str1对应的key
     * @param mapList
     * @return
     */
    public static Map<String, Object> BWBEqualOfPbcprodId2(Object Str1,String PARAMS01,String PARAMS02,List<Map<String,Object>> mapList){
        if(StringUtil.isEmpty(String.valueOf(Str1)) || StringUtil.equals(String.valueOf(Str1),"null")){
            return ExpressionUtil.resultMap(false,String.valueOf(Str1));
        }

        Map<String,Object> BWBMap = new HashMap<>();
        List<Map<String,Object>> mapStatusTypeList = Lists.newArrayList();
        for(Map<String,Object> mapTmp:mapList){
            String PARAMS02Value = String.valueOf(mapTmp.get(PARAMS02));//Str1对应的key取值
            String ccy = String.valueOf(mapTmp.get("ccy"));
            if(mapTmp.get(PARAMS02) == null){
                continue;
            }
            if(StringUtil.equals(String.valueOf(Str1),PARAMS02Value) && StringUtil.equals(ccy,"BWB")){
                BWBMap = mapTmp;
            }
            if(StringUtil.equals(String.valueOf(Str1),PARAMS02Value) && !StringUtil.equals(ccy,"BWB")&& StringUtil.isNotEmpty(ccy)&& !StringUtil.equals(ccy,"null")){
                mapStatusTypeList.add(mapTmp);
            }
        }
        if(BWBMap == null || BWBMap.size() == 0){
            return ExpressionUtil.resultMap(false,"本产品未有BWB数据");
        }
        if(!CollectionUtils.isNotEmpty(mapStatusTypeList)){
            return ExpressionUtil.resultMap(false,"本产品未有非本位币数据");
        }
        if(BWBMap.get(PARAMS01) == null){
            return ExpressionUtil.resultMap(false,"本产品BWB数据的" + PARAMS01 + "为空");
        }
        BigDecimal Amt = new BigDecimal(String.valueOf(BWBMap.get(PARAMS01))); //bwb的值
        BigDecimal Amttotal = new BigDecimal(BigInteger.ZERO); //汇总值

        for(Map<String,Object> tempMap:mapStatusTypeList){
            if(tempMap.get(PARAMS01) != null){
                Amttotal = Amttotal.add(new BigDecimal(String.valueOf(tempMap.get(PARAMS01))));
            }
        }
        if(Amttotal.compareTo(Amt) != 0){
            return ExpressionUtil.resultMap(false,"BWB值：" + Amt + "其他汇总值：" + Amttotal);
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 相同的PARAMS02、PARAMS03，地区为“000000”的“当期申购金额折人民币”数据≠各省级币种为“BWB”的“PARAMS01”数据和！
     * @param Str1      PARAMS02对应的实际值
     * @param Str2      PARAMS03对应的实际值
     * @param PARAMS01   要统计值的参数
     * @param PARAMS02、PARAMS03    维度
     * @return
     */
    public static Map<String, Object> BWBEqualOfPbcprodId3(Object Str1,Object Str2,String PARAMS01,String PARAMS02,String PARAMS03,List<Map<String,Object>> mapList){
        //维护为空不校验
        if(StringUtil.isEmpty(String.valueOf(Str1)) || StringUtil.equals(String.valueOf(Str1),"null")){
            return ExpressionUtil.resultMap(true,PARAMS02+"为空"+String.valueOf(Str1));
        }
        //维护为空不校验
        if(StringUtil.isEmpty(String.valueOf(Str2)) || StringUtil.equals(String.valueOf(Str2),"null")){
            return ExpressionUtil.resultMap(true,PARAMS03+"为空"+String.valueOf(Str1));
        }

        List<Map<String,Object>> listBWBMap = new ArrayList<>();
        List<Map<String,Object>> mapStatusTypeList = Lists.newArrayList();
        for(Map<String,Object> mapTmp:mapList){
            String PARAMS02Value = String.valueOf(mapTmp.get(PARAMS02));
            String PARAMS03Value = String.valueOf(mapTmp.get(PARAMS03));
            String ccy = String.valueOf(mapTmp.get("ccy"));
            String buyPlace = String.valueOf(mapTmp.get("buyPlace"));
            if(mapTmp.get(PARAMS02) == null||mapTmp.get(PARAMS03)== null){
                continue;
            }
            if(StringUtil.equals(String.valueOf(Str1),PARAMS02Value) &&StringUtil.equals(String.valueOf(Str2),PARAMS03Value)&&StringUtil.equals(ccy,"BWB")
                    && StringUtil.equals(buyPlace,"000000")){
                listBWBMap.add(mapTmp);
            }
            if(StringUtil.equals(String.valueOf(Str1),PARAMS02Value) &&StringUtil.equals(String.valueOf(Str2),PARAMS03Value)&&StringUtil.equals(ccy,"BWB")
                    && !StringUtil.equals(buyPlace,"000000")&& StringUtil.isNotEmpty(buyPlace)&& !StringUtil.equals(buyPlace,"null")){
                mapStatusTypeList.add(mapTmp);
            }
        }
        if(!CollectionUtils.isNotEmpty(listBWBMap)){
            return ExpressionUtil.resultMap(false,"本产品和本客户类型未有地区’000000‘且币种为‘BWB’的数据");
        }
//        if(!CollectionUtils.isNotEmpty(mapStatusTypeList)){
//            return ExpressionUtil.resultMap(false,"本产品和本客户类型未有各省的非本位币数据");
//        }

        BigDecimal Amt = new BigDecimal(BigInteger.ZERO);
        for(Map<String,Object> tMap:listBWBMap){
            if(tMap.get(PARAMS01) != null){
                Amt=Amt.add(new BigDecimal(String.valueOf(tMap.get(PARAMS01))));
            }
        }
        BigDecimal Amttotal = new BigDecimal(BigInteger.ZERO); //汇总值
        if(CollectionUtils.isNotEmpty(mapStatusTypeList)){
            for(Map<String,Object> tempMap:mapStatusTypeList){
                if(tempMap.get(PARAMS01) != null){
                    Amttotal = Amttotal.add(new BigDecimal(String.valueOf(tempMap.get(PARAMS01))));
                }
            }
        }

        if(Amttotal.compareTo(Amt) != 0){
            return ExpressionUtil.resultMap(false,"’000000‘值：" + Amt + "其他汇总值：" + Amttotal);
        }
        return ExpressionUtil.resultMap(true,null);
    }
    /**
     * 校验与list1的PARAMS01相等的数据下，PARAMS02=PARAMS03时PARAMS04等于  PARAMS02！=PARAMS03的PARAMS04值
     * @param list1     本条数据
     * @param PARAMS01  主键‘,’分割
     * @param PARAMS02  维度 比如ccy或者地区
     * @param PARAMS03  维度等于的值比如BWB或者000000
     * @param PARAMS04  要校验哪一个字段的值
     * @param mapList   全部数据
     * @return
     */
    public  static Map<String, Object> EqualParamsOfKey(Map<String,Object> list1,String PARAMS01,String PARAMS02,String PARAMS03,String PARAMS04,List<Map<String,Object>> mapList,Object svalue){
        if(ObjectUtils.isEmpty(svalue)){
            return ExpressionUtil.resultMap(true, null);
        }
        String Key = "";

        //RHPTSJ-902 地区为000000的也要校验  start
//        if(StringUtil.equals(PARAMS02,"ccy")){
//            if(StringUtil.equals(String.valueOf(list1.get("buyPlace")),"000000")) {
//                return ExpressionUtil.resultMap(true, null);//币种维度时，地区为000000的不校验
//            }
//        }

//        if(StringUtil.equals(PARAMS02,"buyPlace")){
//            if(StringUtil.equals(String.valueOf(list1.get("ccy")),"BWB")) {
//                return ExpressionUtil.resultMap(true, null);//地区维度时，币种为BWB的不校验
//            }
//        }
        //RHPTSJ-902 地区为000000的也要校验  end

        for (String d : PARAMS01.split(",")) {
            if(list1.get(d) != null){
                Key = Key + (String.valueOf(list1.get(d)));
            }else{
                return ExpressionUtil.resultMap(false,"效验值所在的维度的值为空");
            }
        }

        List<Map<String,Object>> KeyMapList = Lists.newArrayList();
        for(Map<String,Object> tempMap : mapList){
            String tempMapKey = "";
            for (String d : PARAMS01.split(",")) {
                if(list1.get(d) != null){
                    tempMapKey = tempMapKey + (String.valueOf(tempMap.get(d)));
                }
            }
            if(StringUtil.equals(Key,tempMapKey)){
                KeyMapList.add(tempMap);
            }
        }

        //PARAMS02 = PARAMS03的数组
        List<Map<String,Object>> equalParams03List = Lists.newArrayList();
        //PARAMS02 != PARAMS03的数组
        List<Map<String,Object>> notEqualParams03List = Lists.newArrayList();
        for(Map<String,Object> tempMap : KeyMapList){
            if(tempMap.get(PARAMS02) != null && StringUtil.equals(tempMap.get(PARAMS02),PARAMS03)){
                equalParams03List.add(tempMap);
            }else{
                notEqualParams03List.add(tempMap);
            }
        }
        //PARAMS02 = PARAMS03时 PARAMS04的汇总值
        BigDecimal equalParams03total = new BigDecimal(BigInteger.ZERO);
        for(Map<String,Object> equalParams03:equalParams03List){
            if(equalParams03.get(PARAMS04) != null){
                equalParams03total = equalParams03total.add(new BigDecimal(String.valueOf(equalParams03.get(PARAMS04))));
            }
        }
        //PARAMS02 ！= PARAMS03时 PARAMS04的汇总值
        BigDecimal notEqualParams03total = new BigDecimal(BigInteger.ZERO);
        for(Map<String,Object> notEqualParams03:notEqualParams03List){
            if(notEqualParams03.get(PARAMS04) != null){
                notEqualParams03total= notEqualParams03total.add(new BigDecimal(String.valueOf(notEqualParams03.get(PARAMS04))));
            }
        }
        if(equalParams03total.compareTo(notEqualParams03total) != 0){
            return ExpressionUtil.resultMap(false,String.valueOf(equalParams03total) + "&" + String.valueOf(notEqualParams03total));
        }
        return ExpressionUtil.resultMap(true,null);
    }


    /**
     * DB0172 的校验
     * 资管产品基本信息中“产品类型”为“固定收益类”的产品，资产负债信息中币种为“BWB”的
     * （“A1000_现金”+“A2000_存款”+“A3000_可转让存单”+“A4000_债务证券”+“A5000_贷款”+“A6000_资产收益权”+“A9000_应收账款”）余额>0。
     *
     * @param ccy
     * @param sqlObj
     * @param addVal1Obj
     * @param addVal2Obj
     * @param addVal3Obj
     * @param addVal4Obj
     * @param addVal5Obj
     * @param addVal6Obj
     * @param addVal7Obj
     * @return
     */
    public static Map<String,Object> checkDB0172(Object ccy,Object pbcProdId,Object sqlObj
            ,Object addVal1Obj ,Object addVal2Obj,Object addVal3Obj,Object addVal4Obj,Object addVal5Obj,Object addVal6Obj,Object addVal7Obj) {
        if(ccy == null || !"BWB".equals(ccy) || pbcProdId==null){
            return ExpressionUtil.resultMap(true,null);
        }else{
            if(Objects.isNull(addVal1Obj) && Objects.isNull(addVal2Obj) && Objects.isNull(addVal3Obj)
                    && Objects.isNull(addVal4Obj) && Objects.isNull(addVal5Obj) && Objects.isNull(addVal6Obj)
                    && Objects.isNull(addVal7Obj) ){//不可同时为空
                return ExpressionUtil.resultMap(false,"所有数据为空");
            }
            BigDecimal addSum = sumTarget(addVal1Obj, addVal2Obj, addVal3Obj, addVal4Obj, addVal5Obj, addVal6Obj, addVal7Obj);
            String  target = EnumerateExpression.sqlValueOne(String.valueOf(sqlObj),String.valueOf(pbcProdId),null,null);
            if(!StringUtil.equals(target,"") && StringUtil.isNotEmpty(target) && StringUtil.equals(target,"1")){
                if(addSum == null){
                    return ExpressionUtil.resultMap(false,String.valueOf(addSum));
                }else {
                    if (BigDecimal.ZERO.compareTo(addSum) > -1) {
                        return ExpressionUtil.resultMap(false, String.valueOf(addSum));
                    }
                }
            }
            return ExpressionUtil.resultMap(true,null);
        }
    }

    /**
     * DB0181ToDB0183 的校验
     * 资管产品基本信息中“产品类型”为 “addVal3Obj” 的产品，资产负债信息中币种为“BWB”的
     * （list1,list2）余额/ （“A0000_资产合计”-“B0000_负债合计”）余额*100%>80%
     *
     * @param ccy
     * @param sqlObj
     * @param list1
     * @param list2
     * @param param4
     * @param a0000
     * @param b0000
     * @return
     */
    public static Map<String,Object> checkDB0181ToDB0183(Object ccy,Object pbcProdId,Object sqlObj
            ,List<Object> list1, List<Object> list2,Object param4,Object a0000,Object b0000) {
        if(list1 == null || list2 == null ){
            return ExpressionUtil.resultMap(false,"脚本配置错误");
        }
        if(a0000 == null && b0000 == null){
            return ExpressionUtil.resultMap(true,null);
        }
        if(ccy == null || !"BWB".equals(ccy) || pbcProdId==null){
            return ExpressionUtil.resultMap(true,null);
        }else{
            String  target = EnumerateExpression.sqlValueOne(String.valueOf(sqlObj),String.valueOf(pbcProdId),null,null);
            if(!StringUtil.equals(target,"") && StringUtil.isNotEmpty(target) && StringUtil.equals(target,String.valueOf(param4))){
                BigDecimal Sum1 = multiAvl(list1,list2);
                BigDecimal Sum2 = sumTarget(a0000, b0000);
                if(Sum1 == null || Sum2 == null){
                    return ExpressionUtil.resultMap(false,String.valueOf(Sum1) + "&" + String.valueOf(Sum2));
                }else {
                    Boolean flag1 = BigDecimal.ZERO.compareTo(Sum1) == 0 || BigDecimal.ZERO.compareTo(Sum2) == 0;
                    if (flag1) {
                        return ExpressionUtil.resultMap(false, String.valueOf(Sum1) + "/" + String.valueOf(Sum2) + "无效");
                    }
                    BigDecimal divideBigDecimal = Sum1.divide(Sum2, 3, RoundingMode.HALF_UP);
                    BigDecimal divideBigDecimal1 = convertToBigdecimal("0.8");
                    Boolean flag2 = false;
                    if(divideBigDecimal1 != null) {
                        flag2 = divideBigDecimal1.compareTo(divideBigDecimal) > -1;
                    }
                    if (flag2) {
                        return ExpressionUtil.resultMap(false, String.valueOf(Sum1) + "/" + String.valueOf(Sum2) + "=" + String.valueOf(Sum1.divide(Sum2, 2, RoundingMode.HALF_UP)));
                    }
                }
            }
            return ExpressionUtil.resultMap(true,null);
        }
    }

    /**
     * DB0187
     *
     * 货基或现金管理类产品为“否”的产品，存续期募集信息中币种为“BWB”的“期末产品金额”合计值与该产品资产负债信息表币种为“BWB”的C1000_实收本金余额不应该相等
     * param01,'2',ccy,param02,c1000,pbcProdId
     *
     * @param sql1  zg01 sql
     * @param str1  zg01  货基或现金管理类产品为“否”
     * @param ccy  zg05 币种
     * @param sql2 zg04 sql
     * @param obj1 c1000
     * @param pbcProdId 产品代码
     * @return
     */
    public static Map<String, Object> checkDB0187(Object sql1, String str1, String ccy, Object sql2, Object obj1, Object pbcProdId) {
        // 产品代码为空 或 币种为空 或 币种不为 BWB 不校验
        if (pbcProdId == null || ccy == null || !"BWB".equals(ccy)) {
            return ExpressionUtil.resultMap(true, null);
        } else {
            String tar1 = EnumerateExpression.sqlValueOne(String.valueOf(sql1), String.valueOf(pbcProdId), null, null);
            if (str1.equals(tar1)) {
                // 存续期募集信息中币种为“BWB”的“期末产品金额”合计值
                String tar2 = EnumerateExpression.sqlValueOne(String.valueOf(sql2), String.valueOf(pbcProdId), null, null);
                // 期末产品金额合计 为空 校验通过      c1000 为空
                if (Objects.isNull(tar2) || Objects.isNull(obj1)) {
                    return ExpressionUtil.resultMap(true, obj1);
                } else {
                    BigDecimal endamtsum = convertToBigdecimal(tar2);
                    BigDecimal c1000 = convertToBigdecimal(obj1);
                    if (Objects.isNull(endamtsum) || Objects.isNull(c1000)) {
                        return ExpressionUtil.resultMap(true, obj1);
                    } else {
                        // 相等 提示错误信息
                        if (endamtsum.compareTo(c1000) == 0) {
                            return ExpressionUtil.resultMap(false, obj1);
                        } else {
                            return ExpressionUtil.resultMap(true, obj1);
                        }
                    }
                }
            } else {
                // 前置条件不满足
                // 该产品没有 货基或现金管理类 为 否
                return ExpressionUtil.resultMap(true, obj1);
            }
        }
    }

    /**
     * DB0190
     *
     * 净值型产品，存续期募集信息中币种为“BWB”,地区为“000000“，期末产品金额”合计与该产品币种为“BWB”的C1000_实收本金余额不应相等
     * param01,'1',ccy,param02,c1000,pbcProdId
     *
     * @param sql1  zg01 sql
     * @param str1  zg01  净值型产品为
     * @param ccy  zg05 币种
     * @param sql2 zg04 sql
     * @param obj1 c1000
     * @param pbcProdId 产品代码
     * @return
     */
    public static Map<String, Object> checkDB0190(Object sql1, String str1, String ccy, Object sql2, Object obj1, Object pbcProdId) {
        // 产品代码为空 或 币种为空 或 币种不为 BWB 不校验
        if (pbcProdId == null || ccy == null || !"BWB".equals(ccy)) {
            return ExpressionUtil.resultMap(true, null);
        } else {
            String tar1 = EnumerateExpression.sqlValueOne(String.valueOf(sql1), String.valueOf(pbcProdId), null, null);
            if (tar1.isEmpty()) {
                // 前置条件不满足
                // 该产品不是 净值型产品
                return ExpressionUtil.resultMap(true, obj1);
            }
            if (str1.equals(tar1)) {
                // 存续期募集信息中币种为“BWB”的“期末产品金额”合计
                String tar2 = EnumerateExpression.sqlValueOne(String.valueOf(sql2), String.valueOf(pbcProdId), null, null);
                // 期末产品金额合计 为空 校验通过      c1000 为空
                if (Objects.isNull(tar2) || Objects.isNull(obj1)) {
                    return ExpressionUtil.resultMap(true, obj1);
                } else {
                    BigDecimal endamtsum = convertToBigdecimal(tar2);
                    BigDecimal c1000 = convertToBigdecimal(obj1);
                    if (Objects.isNull(endamtsum) || Objects.isNull(c1000)) {
                        return ExpressionUtil.resultMap(true, obj1);
                    } else {
                        // 相等 提示错误信息
                        if (endamtsum.compareTo(c1000) == 0) {
                            return ExpressionUtil.resultMap(false, obj1);
                        } else {
                            return ExpressionUtil.resultMap(true, obj1);
                        }
                    }
                }
            } else {
                // 前置条件不满足
                // 该产品不是 净值型产品
                return ExpressionUtil.resultMap(true, obj1);
            }
        }
    }

    /**
     * DB0193 的校验
     *
     * 1、（差值/实收本金A ）绝对值*100%大于>5%；
     * 其中：
     * 2、差值 = （期末产品份额金融机构-实收本金A）
     * 3、期末产品份额金融机构 = 存续期募集信息中，币种为“BWB”，客户类型为“金融机构实体”的“期末产品份额”；
     * 4、实收本金A = 资产负债信息表，产品币种为“BWB”（“C1140_银行业存款类金融机构”+“C1150_银行业非存款类金融机构”+“C1160_非银行业金融机构”+“C1240_银行业存款类金融机构”+“C1250_银行业非存款类金融机构”+“C1260_非银行业金融机构”）合计值；
     *
     * @param ccy
     * @param thisValListStrObj
     * @param addVal1Obj
     * @param addVal2Obj
     * @param addVal3Obj
     * @param addVal4Obj
     * @param addVal5Obj
     * @param addVal6Obj
     * @param lineNumObj
     * @return
     */
    public static Map<String,Object> checkDB0193(String ccy,Object thisValListStrObj
            ,Object addVal1Obj,Object addVal2Obj,Object addVal3Obj,Object addVal4Obj,Object addVal5Obj,Object addVal6Obj
            ,Object lineNumObj,Object strp,Object strt) {

        thisValListStrObj = EnumerateExpression.sqlValueOne(String.valueOf(thisValListStrObj),String.valueOf(strp),String.valueOf(strt),String.valueOf(strt));

        if(!"BWB".equals(ccy)){
            return ExpressionUtil.resultMap(true,thisValListStrObj);
        }

        if(!isNumeric(thisValListStrObj)){
            return ExpressionUtil.resultMap(false,thisValListStrObj);
        }
        BigDecimal addSum = sumTarget(addVal1Obj, addVal2Obj, addVal3Obj, addVal4Obj, addVal5Obj, addVal6Obj);

        return compareYieldABS(thisValListStrObj,addSum,lineNumObj);
    }

    /**
     * DB0199的校验：
     * 存续期募集信息中客户类型为“金融机构实体”的“期末产品金额”有数，则资产负债信息中同币种（数据类型=1）
     *  C1140_银行业存款类金融机构、C1150_银行业非存款类金融机构、C1160_非银行业金融机构、C1240_银行业存款类金融机构、C1250_银行业非存款类金融机构、C1260_非银行业金融机构
     *  余额应至少1个有数
     * @param conditionObj
     * @param conditionValueObj
     * @param sourceValObj
     * @param addVal1Obj
     * @param addVal2Obj
     * @param addVal3Obj
     * @param addVal4Obj
     * @param addVal5Obj
     * @param addVal6Obj
     * @return
     */
    public static Map<String,Object> checkDB0199(Object conditionObj,Object conditionValueObj,Object sourceValObj
            ,Object addVal1Obj,Object addVal2Obj,Object addVal3Obj,Object addVal4Obj,Object addVal5Obj,Object addVal6Obj) {
        if(conditionObj == null || conditionValueObj == null || !isNumeric(sourceValObj)){
            return ExpressionUtil.resultMap(false,conditionObj);
        }
        BigDecimal addSum = sumTarget(addVal1Obj, addVal2Obj, addVal3Obj, addVal4Obj, addVal5Obj, addVal6Obj);
        return judgeValueGreater0(conditionObj,conditionValueObj,sourceValObj,addSum,0,0);
    }


    /**
     * DB0218
     *
     * 净值型产品，存续期募集信息中币种为“BWB”,地区不为“000000“，期末产品金额”合计与该产品币种为“BWB”的C1000_实收本金余额不应相等
     * param01,'1',ccy,param02,c1000,pbcProdId
     *
     * @param sql1  zg01 sql
     * @param str1  zg01  净值型产品 为
     * @param ccy  zg05 币种
     * @param sql2 zg04 sql
     * @param obj1 c1000
     * @param pbcProdId 产品代码
     * @return
     */
    public static Map<String, Object> checkDB0218(Object sql1, String str1, String ccy, Object sql2, Object obj1, Object pbcProdId) {
        // 产品代码为空 或 币种为空 或 币种不为 BWB 不校验
        if (pbcProdId == null || ccy == null || !"BWB".equals(ccy)) {
            return ExpressionUtil.resultMap(true, null);
        } else {
            String tar1 = EnumerateExpression.sqlValueOne(String.valueOf(sql1), String.valueOf(pbcProdId), null, null);
            if (tar1.isEmpty()) {
                // 前置条件不满足
                // 该产品不是 净值型产品
                return ExpressionUtil.resultMap(true, obj1);
            }
            if (str1.equals(tar1)) {
                // 存续期募集信息中币种为“BWB”的“期末产品金额”合计
                String tar2 = EnumerateExpression.sqlValueOne(String.valueOf(sql2), String.valueOf(pbcProdId), null, null);
                // 期末产品金额合计 为空 校验通过      c1000 为空
                if (Objects.isNull(tar2) || Objects.isNull(obj1)) {
                    return ExpressionUtil.resultMap(true, obj1);
                } else {
                    BigDecimal endamtsum = convertToBigdecimal(tar2);
                    BigDecimal c1000 = convertToBigdecimal(obj1);
                    if (Objects.isNull(endamtsum) || Objects.isNull(c1000)) {
                        return ExpressionUtil.resultMap(true, obj1);
                    } else {
                        // 相等 提示错误信息
                        if (endamtsum.compareTo(c1000) == 0) {
                            return ExpressionUtil.resultMap(false, obj1);
                        } else {
                            return ExpressionUtil.resultMap(true, obj1);
                        }
                    }
                }
            } else {
                // 前置条件不满足
                // 该产品不是 净值型产品
                return ExpressionUtil.resultMap(true, obj1);
            }
        }
    }

    /**
     *
     * DB0224
     * 00001_附报-金融机构表内资产余额（本外币）（本期）/00001_附报-金融机构表内资产余额（本外币）（上期）>2
     * or 00001_附报-金融机构表内资产余额（本外币）（本期）= 00001_附报-金融机构表内资产余额（本外币）（上期）
     *
     * @param sql1
     * @param termid
     * @param g00001
     * @return
     */
    public static Map<String, Object> checkDB0224(Object sql1, String termid, Object g00001) {
        // 期数为空
        if (StringUtil.isEmpty(termid)){
            return ExpressionUtil.resultMap(true, null);
        }
        String last = EnumerateExpression.sqlValueOne(String.valueOf(sql1), null, termid, null);
        // 上期为空 // 资产余额为空
        if (Objects.isNull(last) || !StringUtil.isNotEmpty(last) || Objects.isNull(g00001) || !StringUtil.isNotEmpty(g00001)) {
            return ExpressionUtil.resultMap(true, null);
        } else {
            // 本期
            BigDecimal b1 = convertToBigdecimal(g00001);
            // 上期
            BigDecimal b2 = convertToBigdecimal(last);
            // 除数为零
            if (Objects.isNull(b1) || Objects.isNull(b2)) {
                return ExpressionUtil.resultMap(true, null);
            } else {
                // 本期等于上期
                if (b1.compareTo(b2) == 0) {
                    return ExpressionUtil.resultMap(false, g00001);
                } else {
                    // 除数为 0
                    if (b2.compareTo(BigDecimal.ZERO) == 0) {
                        return ExpressionUtil.resultMap(true, null);
                    }
                    // 大于2 或者 本期等于上期
                    if (b1.divide(b2).compareTo(BigDecimal.valueOf(2)) > 0) {
                        ExpressionUtil.resultMap(false, g00001);
                    } else {
                        return ExpressionUtil.resultMap(true, null);
                    }
                }
            }
        }
        return ExpressionUtil.resultMap(false, g00001);
    }

    /**
     *
     * DB0225
     * 00002_附报-金融机构表内金融资产余额（本外币）（本期）/00002_附报-金融机构表内金融资产余额（本外币）（上期）>2
     * or 00002_附报-金融机构表内金融资产余额（本外币）（本期）= 00002_附报-金融机构表内金融资产余额（本外币）（上期）
     *
     * @param sql1
     * @param termid
     * @param g00002
     * @return
     */
    public static Map<String, Object> checkDB0225(Object sql1, String termid, Object g00002) {
        // 期数为空
        if (StringUtil.isEmpty(termid)){
            return ExpressionUtil.resultMap(true, null);
        }
        String last = EnumerateExpression.sqlValueOne(String.valueOf(sql1), null, termid, null);
        // 上期为空 // 表内金融资产余额为空
        if (Objects.isNull(last) || !StringUtil.isNotEmpty(last) || Objects.isNull(g00002) || !StringUtil.isNotEmpty(g00002)) {
            return ExpressionUtil.resultMap(true, null);
        } else {
            // 本期
            BigDecimal b1 = convertToBigdecimal(g00002);
            // 上期
            BigDecimal b2 = convertToBigdecimal(last);
            // 除数为零
            if (Objects.isNull(b1) || Objects.isNull(b2)) {
                return ExpressionUtil.resultMap(true, null);
            } else {
                // 本期等于上期
                if (b1.compareTo(b2) == 0) {
                    return ExpressionUtil.resultMap(false, g00002);
                } else {
                    // 除数为 0
                    if (b2.compareTo(BigDecimal.ZERO) == 0) {
                        return ExpressionUtil.resultMap(true, null);
                    }
                    // 大于2 或者 本期等于上期
                    if (b1.divide(b2).compareTo(BigDecimal.valueOf(2)) > 0) {
                        ExpressionUtil.resultMap(false, g00002);
                    } else {
                        return ExpressionUtil.resultMap(true, null);
                    }
                }
            }
        }
        return ExpressionUtil.resultMap(false, g00002);
    }


    /**
     *两个值，如果相等，则返回false,不相等，则返回true;
     * @param str1 第一个为传的参数
     * @param str2 第二个为传的sql语句
     * @param str3 第三个为sql语句用到的参数（产品代码）
     * @param str4 第四个为sql语句用到的参数（时间）
     * @return
     */
    public static Map<String, Object>  sqlbenotEqualsql(Object str1,Object str2,Object str3,Object str4,Object str5){
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target)){
            map.put("data",target+","+target1);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target+","+target1);
            return map;
        }
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = addBigdecimalList(target);
        } catch (Exception e) {
            data = target.toString();
            flag2=false;
        }
        try {
            t1 =addBigdecimalList(target1);
        } catch (Exception e) {
            data = target1.toString();
            flag2=false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2

            // 增加非空判断
            if (t == null) {
                return ExpressionUtil.resultMap(true, "参数t为空了");
            }
            flag = t.compareTo(t1) != 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }
    /**
     *两个值，如果第一个值大于第二值则，返回true,否则返回false;
     * @param target 当前参数
     * @param
     * @return
     */
    public static Map<String, Object>  sqlGreaterThanEqualsql(Object target,Object str2,Object str3,Object str4,Object str5){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        //如果校验的目标字段是空
        if (Objects.isNull(target)){
            map.put("flag", true);
            return map;
        }
        //如果不等于全国地区
        if(!ObjectUtils.nullSafeEquals("000000",str5)){
            map.put("flag", true);
            return map;
        }
        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        //校验其它表的值为空
        if (Objects.isNull(target1)){
            map.put("flag", true);
            map.put("data",target+","+target1);
            return map;
        }

        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = addBigdecimalList(String.valueOf(target));
        } catch (Exception e) {
            data = target.toString();
            flag2=false;
        }
        try {
            t1 =addBigdecimalList(target1);
        } catch (Exception e) {
            data = target1.toString();
            flag2=false;
        }
        if (flag2){
            flag = t.compareTo(t1) >= 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //两值相等
    public static Map<String, Object> beEqualsql(Object str1,Object str2,Object str3,Object str4,Object str5){
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if(Objects.isNull(target)&&Objects.isNull(target1)){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target)){
            map.put("data",target);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        //参数转换
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            data = target1;
            flag2=false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2

            // 增加非空判断
            if (t == null || t1 == null) {
                return ExpressionUtil.resultMap(true, "参数t、t1为空了");
            }

            flag = t.compareTo(t1) == 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }


    //两值相等 --区分币种
    public static Map<String, Object> beEqualsql42(Object str1,Object str2,Object str3,Object str4,Object str5,Object str6){
        if(!ObjectUtils.nullSafeEquals(str5,str6)){
            return ExpressionUtil.resultMap(true,null);
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if(Objects.isNull(target)&&Objects.isNull(target1)){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target)){
            map.put("data",target);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        //参数转换
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            data = target1;
            flag2=false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2

            // 增加非空判断
            if (t == null || t1 == null) {
                return ExpressionUtil.resultMap(true, "参数t、t1为空了");
            }

            flag = t.compareTo(t1) == 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //两值相等
    public static Map<String, Object> beEqualsqlBWB(Object str1,Object str2,Object str3,Object str4,Object str5,Object str6){
        //只校验BWB
        if(!StringUtil.equals(String.valueOf(str6),"BWB")){
            return ExpressionUtil.resultMap(true,null);
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if(Objects.isNull(target)&&Objects.isNull(target1)){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target)){
            map.put("data",target);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        //参数转换
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            data = target1;
            flag2=false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2

            // 增加非空判断
            if (t == null || t1 == null) {
                return ExpressionUtil.resultMap(true, "参数t、t1为空了");
            }

            flag = t.compareTo(t1) == 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }


    /**
     *同币种及期数下，sql1与sql2结果值是否相等
     * @param sql1
     * @param sql2
     * @param targe1 配置成期数
     * @param targe2 配置成币种
     * @param targe3 数据类型
     * @return
     */
    public static Map<String, Object> beEqualOfSqlByCcy(Object sql1,Object sql2,Object targe1,Object targe2,Object targe3){
        if(Objects.isNull(targe1) || Objects.isNull(targe2) || Objects.isNull(targe3)){
            return ExpressionUtil.resultMap(true,null);
        }
        if(!StringUtil.equals(String.valueOf(targe3),"1")){
            return ExpressionUtil.resultMap(true,null);
        }
        //BWB不校验
        if(StringUtil.equals(String.valueOf(targe2),"BWB")){
            return ExpressionUtil.resultMap(true,null);
        }
        String  target = EnumerateExpression.sqlValueOne3(String.valueOf(sql1),null,String.valueOf(targe1),null,String.valueOf(targe2),null);
        String  target1 = EnumerateExpression.sqlValueOne3(String.valueOf(sql2),null,String.valueOf(targe1),null,String.valueOf(targe2),null);

        if(Objects.isNull(target)&&Objects.isNull(target1)){
            return ExpressionUtil.resultMap(true,null);
        }
        if (Objects.isNull(target)){
            return ExpressionUtil.resultMap(false,String.valueOf(target)+ "&" +String.valueOf(target1));
        }
        if (Objects.isNull(target1)){
            return ExpressionUtil.resultMap(false,String.valueOf(target)+ "&" +String.valueOf(target1));
        }
        //参数转换
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false,String.valueOf(target)+ "&" +String.valueOf(target1));
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false,String.valueOf(target)+ "&" +String.valueOf(target1));
        }
        //业务逻辑校验 target1<=target<=target2

        // 增加非空判断
        if (t == null || t1 == null) {
            return ExpressionUtil.resultMap(true, "参数t、t1为空了");
        }

        Boolean flag = t.compareTo(t1) == 0;
        return ExpressionUtil.resultMap(flag,String.valueOf(target)+ "&" +String.valueOf(target1));
    }

    /**
     *同币种及期数下，sql1与sql2结果值是否相等
     * @param sql1
     * @param sql2
     * @param targe1 配置成期数
     * @param targe2 配置成币种
     * @return
     */
    public static Map<String, Object> beEqualOfSqlByBWBCcy(Object sql1,Object sql2,Object targe1,Object targe2){
        if(Objects.isNull(targe1) || Objects.isNull(targe2)){
            return ExpressionUtil.resultMap(true,null);
        }
        //只校验BWB
        if(!StringUtil.equals(String.valueOf(targe2),"BWB")){
            return ExpressionUtil.resultMap(true,null);
        }
        String  target = EnumerateExpression.sqlValueOne3(String.valueOf(sql1),null,String.valueOf(targe1),null,String.valueOf(targe2),null);
        String  target1 = EnumerateExpression.sqlValueOne3(String.valueOf(sql2),null,String.valueOf(targe1),null,String.valueOf(targe2),null);

        if(Objects.isNull(target)&&Objects.isNull(target1)){
            return ExpressionUtil.resultMap(true,null);
        }
        if (Objects.isNull(target)){
            return ExpressionUtil.resultMap(false,String.valueOf(target)+ "&" +String.valueOf(target1));
        }
        if (Objects.isNull(target1)){
            return ExpressionUtil.resultMap(false,String.valueOf(target)+ "&" +String.valueOf(target1));
        }
        //参数转换
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false,String.valueOf(target)+ "&" +String.valueOf(target1));
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false,String.valueOf(target)+ "&" +String.valueOf(target1));
        }
        //业务逻辑校验 target1<=target<=target2

        // 增加非空判断
        if (t == null || t1 == null) {
            return ExpressionUtil.resultMap(true, "参数t、t1为空了");
        }

        Boolean flag = t.compareTo(t1) == 0;
        return ExpressionUtil.resultMap(flag,String.valueOf(target)+ "&" +String.valueOf(target1));
    }

    //校验DB0058
    public static Map<String, Object> beEqualsqlDb58(Object str1,Object str2,Object str3,Object str4,Object str5){
        Map<String, Object> map = new HashMap<>();
        //不满足条件，不走此校验
        if(!ObjectUtils.nullSafeEquals(str3,str5)){
            map.put("flag",true);
            return map;
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if(Objects.isNull(target)&&Objects.isNull(target1)){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target)){
            map.put("data",target);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        //参数转换
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            data = target1;
            flag2=false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2

            // 增加非空判断
            if (t == null || t1 == null) {
                return ExpressionUtil.resultMap(true, "参数t、t1为空了");
            }

            flag = t.compareTo(t1) == 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }


    /**
     *
     * @param str1
     * @param str2
     * @param str3
     * @param str4
     * @param str5
     * @return
     */
    public static Map<String, Object> beEqualsqlDb56(Object str1,Object str2,Object str3,Object str4,Object str5,Object str6,Object str7){
        Map<String, Object> map = new HashMap<>();
        if(!ObjectUtils.nullSafeEquals(str6,str7)){
            map.put("flag",true);
            return map;
        }
        //sql计算出来的值
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        //str2+str5 合计值
        String  target1 = null;
        BigDecimal str2BigDecimal = convertToBigdecimal(str2);
        BigDecimal str5BigDecimal = convertToBigdecimal(str5);
        if(str2BigDecimal != null && str5BigDecimal != null){
            target1 =String.valueOf(str2BigDecimal.add(str5BigDecimal));
        }

        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if(Objects.isNull(target)&&Objects.isNull(target1)){
            map.put("flag",true);
            return map;
        }
        if (Objects.isNull(target)){
            map.put("data",target);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        //参数转换
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            data = target1;
            flag2=false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2

            // 增加非空判断
            if (t == null || t1 == null) {
                return ExpressionUtil.resultMap(true, "参数t、t1为空了");
            }

            flag = t.compareTo(t1) == 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
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
    public static Map<String, Object> beEqualsql2(Object str1,Object str2,Object str3,Object str4,Object str5,Object str6,Object str7){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if(ObjectUtils.isEmpty(str1)){
            map.put("flag",true);
            return map;
        }
        String  target = String.valueOf(str1);
        String  target1 = EnumerateExpression.sqlValueOne3(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5),String.valueOf(str6),String.valueOf(str7));
        if(ObjectUtils.isEmpty(target1)){
            map.put("flag",true);
            return map;
        }
        //参数转换
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            data = target1;
            flag2=false;
        }
        if (flag2){
            //业务逻辑校验 target1<=target<=target2

            // 增加非空判断
            if (t == null || t1 == null) {
                return ExpressionUtil.resultMap(true, "参数t、t1为空了");
            }

            flag = t.compareTo(t1) == 0;
            data = target.toString();
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //大于等于某值
    public static Map<String, Object> greaterOrEqualsql(Object str1,Object str2,Object str3,Object str4,Object str5){
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target) || StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            map.put("data",target);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
            map.put("flag", flag);
            map.put("data", data);
            return map;
        }
        try {
            t1 = convertToBigdecimal(target1);
        } catch (Exception e) {
            data = target1;
            flag2=false;
            map.put("flag", flag);
            map.put("data", data);
            return map;
        }

        // 增加非空判断
        if (t == null) {
            return ExpressionUtil.resultMap(true, "参数t为空了");
        }

        if (flag2){
            flag = t.compareTo(t1) > 0 || t.compareTo(t1) == 0;
            data = target;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }


    //DB0170
    public static Map<String, Object> greaterOrEqualsql170(Object str1,Object str2,Object str3,Object str4,Object str5,Object str6,Object str7){
        Map<String, Object> map = new HashMap<>();
        //不满足条件，不走此校验
        if(!ObjectUtils.nullSafeEquals(str3,str5)){
            map.put("flag",true);
            return map;
        }
        //str7不为空，且不等于0的时候，不校验
        if(!ObjectUtils.isEmpty(str7)){
            BigDecimal b7=convertToBigdecimal(str7);
            if(b7 != null) {
                if (b7.compareTo(BigDecimal.ZERO) != 0) {
                    map.put("flag", true);
                    return map;
                }
            }
        }

        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str4),String.valueOf(str4),String.valueOf(str5));
        if(ObjectUtils.isEmpty(target1)){
            map.put("flag",true);
            return map;
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str4),String.valueOf(str4),String.valueOf(str5));
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target) || StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            return map;
        }

        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
            map.put("flag", flag);
            map.put("data", data);
            return map;
        }
        try {
            if(ObjectUtils.isEmpty(str6)||ObjectUtils.nullSafeEquals("null",String.valueOf(str6))){
                t1=BigDecimal.ZERO;
            }else{
                t1 = convertToBigdecimal(str6);
            }
        } catch (Exception e) {
            data = String.valueOf(str6);
            flag2=false;
            map.put("flag", flag);
            map.put("data", data);
            return map;
        }

        // 增加非空判断
        if (t == null) {
            return ExpressionUtil.resultMap(true, "参数t为空了");
        }

        if (flag2){
            flag = t.compareTo(t1) > 0 || t.compareTo(t1) == 0;
            data = target;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }

    //DB0186
    public static Map<String, Object> greaterOrEqualsql186(Object str1,Object str2,Object str3,Object str4,Object str5,Object str6){
        Map<String, Object> map = new HashMap<>();
        //不满足条件，不走此校验
        if(!ObjectUtils.nullSafeEquals(str3,str5)){
            map.put("flag",true);
            return map;
        }

        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str4),String.valueOf(str4),String.valueOf(str5));
        if(ObjectUtils.isEmpty(target1)){
            map.put("flag",true);
            return map;
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str4),String.valueOf(str4),String.valueOf(str5));
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target) || StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            return map;
        }

        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
            map.put("flag", flag);
            map.put("data", data);
            return map;
        }
        try {
            if(ObjectUtils.isEmpty(str6)||ObjectUtils.nullSafeEquals("null",String.valueOf(str6))){
                t1=BigDecimal.ZERO;
            }else{
                t1 = convertToBigdecimal(str6);
            }
        } catch (Exception e) {
            data = String.valueOf(str6);
            flag2=false;
            map.put("flag", flag);
            map.put("data", data);
            return map;
        }

        // 增加非空判断
        if (t == null) {
            return ExpressionUtil.resultMap(true, "参数t为空了");
        }

        if (flag2){
            flag = t.compareTo(t1) > 0 || t.compareTo(t1) == 0;
            data = target;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }


    //DB0068
    public static Map<String, Object> greaterOrEqualsql68(Object str1,Object str2,Object str3,Object str4,Object str5){
        Map<String, Object> map = new HashMap<>();
        //不满足条件，不走此校验
        if(!ObjectUtils.nullSafeEquals(str3,str5)){
            map.put("flag",true);
            return map;
        }
        String  target = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(str2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        boolean flag = false;
        String data = "";
        map.put("flag", flag);
        if (Objects.isNull(target) || StringUtil.isEmpty(String.valueOf(target))){
            map.put("flag",true);
            map.put("data",target);
            return map;
        }
        if (Objects.isNull(target1)){
            map.put("data",target1);
            return map;
        }
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        boolean flag2 = true;
        try {
            t1 = convertToBigdecimal(target);
        } catch (Exception e) {
            data = target;
            flag2=false;
            map.put("flag", flag);
            map.put("data", data);
            return map;
        }
        try {
            t = convertToBigdecimal(target1);
        } catch (Exception e) {
            data = target1;
            flag2=false;
            map.put("flag", flag);
            map.put("data", data);
            return map;
        }

        // 增加非空判断
        if (t == null) {
            return ExpressionUtil.resultMap(true, "参数t为空了");
        }

        if (flag2){
            flag = t.compareTo(t1) > 0 || t.compareTo(t1) == 0;
            data = target;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }
    /**
     * 增长率大于lineNum时 返回false(增长率非绝对值)
     * @param thisValListStrObj
     * @param
     * @param lineNumObj
     * @return
     */
    public static Map<String, Object> compareYieldNotABSsql(Object thisValListStrObj,Object lastValListStrObj1,Object lineNumObj,Object str3,Object str4,Object str5) {
        String  lastValListStrObj = EnumerateExpression.sqlValueOne(String.valueOf(lastValListStrObj1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));

        return compareYield(thisValListStrObj,lastValListStrObj,lineNumObj,false);
    }

    /**
     * DB0031 校验
     */
    public static Map<String, Object> compareYieldNotABSsql31(Object thisValListStrObj,Object lastValListStrObj1,Object lineNumObj,Object str3,Object str4,Object str5) {
        String  lastValListStrObj = EnumerateExpression.sqlValueOne3(String.valueOf(lastValListStrObj1),String.valueOf(str3),String.valueOf(str4),null,String.valueOf(str5),null);

        return compareYield(thisValListStrObj,lastValListStrObj,lineNumObj,false);
    }

    /**
     * target下四个值不能等于0
     * @param target1
     * @param target2
     * @param target3
     * @param target4
     * @return
     */
    public static Map<String,Object> notEqualsZeroFour(Object target,Object target1,Object target2,Object target3,Object target4,Object sqlParam){
        String  lastValListStrObj = EnumerateExpression.sqlValueOne(String.valueOf(sqlParam),String.valueOf(target),null,null);
        //运作模式不为1或者3即 净值型 就不进行检查
        if(!StringUtil.equals(lastValListStrObj,"1") && !StringUtil.equals(lastValListStrObj,"3")){
            return ExpressionUtil.resultMap(true, null);
        }
        //无查询结果不进行检查
        if(StringUtil.isEmpty(lastValListStrObj) || StringUtil.equals(lastValListStrObj,"") || StringUtil.equals(lastValListStrObj,"null")){
            return ExpressionUtil.resultMap(true, null);
        }
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String data = "";
        map.put("flag", flag);

        if (Objects.isNull(target1)&&Objects.isNull(target2)&&Objects.isNull(target3)&&Objects.isNull(target4)){
            flag = true;
            map.put("flag", flag);
            return map;
        }

        if (Objects.isNull(target1)||Objects.isNull(target2)||Objects.isNull(target3)||Objects.isNull(target4)){
            flag = false;
            map.put("flag", flag);
            return map;
        }
        String tar = String.valueOf(target1);
        data = tar;
        BigDecimal t = new BigDecimal("0");
        BigDecimal t1 = new BigDecimal("0");
        BigDecimal t2 = new BigDecimal("0");
        BigDecimal t3 = new BigDecimal("0");

        try {
            t = convertToBigdecimal(target1);
            t1 = convertToBigdecimal(target2);
            t2 = convertToBigdecimal(target3);
            t3 = convertToBigdecimal(target4);
        } catch (Exception e) {
//            data = tar;
            flag=false;
        }

        // 增加非空判断
        if (t == null || t1 == null || t2 == null || t3 == null) {
            return ExpressionUtil.resultMap(true, "参数t、t1、t2、t3为空了");
        }

        //业务逻辑校验 target1<=target<=target2
        if(t.compareTo(new BigDecimal("0")) == 0 ||t1.compareTo(new BigDecimal("0")) == 0||t2.compareTo(new BigDecimal("0")) == 0||t3.compareTo(new BigDecimal("0")) == 0){
            flag=false;
        }else{
            flag=true;
        }
        map.put("flag", flag);
        map.put("data", data);
        return map;
    }


    /**
     *  DB0018 上期末份额+当期申购份额-当期兑付/赎回份额-期末产品份额=0 不等于返回 false
     * @param str1
     * @param str2 当期申购份额
     * @param str3 当期兑付/赎回份额
     * @param str4 期末产品份额
     * @param str6 产品代码
     * @param str7 期数
     * @param str8 地区
     * @return
     */
    public static Map<String, Object> compareCheckSql(Object str1,Object str2,Object str3,Object str4, Object str6,Object str7,Object str8) {
        if (str8 == null || str7 == null || str6 == null){
            return ExpressionUtil.resultMap(true, "查询项为空");
        }
        String str11 = EnumerateExpression.sqlValueOne(String.valueOf(str1),String.valueOf(str6),String.valueOf(str7),String.valueOf(str8));
        BigDecimal str111 = BigDecimal.ZERO;
        BigDecimal str112 = BigDecimal.ZERO;
        BigDecimal str113 = BigDecimal.ZERO;
        BigDecimal str114 = BigDecimal.ZERO;
        try {
            if("".equals(str11)){
                str111=  convertToBigdecimal(0);
            } else{
                str111=  convertToBigdecimal(str11);
            }
            str112 = convertToBigdecimal(str2);
            str113 = convertToBigdecimal(str3);
            str114 = convertToBigdecimal(str4);

            // 增加非空判断
            if (str111 == null) {
                return ExpressionUtil.resultMap(true, "参数str111为空了");
            }

            if((str111.add(str112).subtract(str113).subtract(str114)).compareTo(BigDecimal.ZERO) != 0){
                return ExpressionUtil.resultMap(false,str111.add(str112).subtract(str113).subtract(str114));
            }
        } catch (Exception e) {
            return ExpressionUtil.resultMap(true,null);
        }
        return ExpressionUtil.resultMap(true,null);
    }
    /**
     * 增长率大于lineNum时 返回false(增长率非绝对值)
     *
     * @param
     * @param
     * @param lineNumObj
     * @return
     */
    public static Map<String, Object> compareYieldNotABSsqlOne(Object str1, Object str2, Object lineNumObj, Object str3, Object str4, Object str5) {
        if(str3 == null){
            return ExpressionUtil.resultMap(false,str3);
        }
        if(str4 == null){
            return ExpressionUtil.resultMap(false,str4);
        }
        if(str5 == null){
            return ExpressionUtil.resultMap(true,str5);
        }
        String lastValListStrObj  = EnumerateExpression.sqlValueOne(String.valueOf(str1), String.valueOf(str3), String.valueOf(str4), String.valueOf(str5));
        String valListStrObj = EnumerateExpression.sqlValueOne(String.valueOf(str2), String.valueOf(str3), String.valueOf(str4), String.valueOf(str5));
        BigDecimal[] lastVal =lastValList(lastValListStrObj,valListStrObj);
        return compareYieldOne(String.valueOf(lastVal[0]), String.valueOf(lastVal[1]), lineNumObj, false);
    }

    /**
     * 上期期末产品金额+当期申购金额-当期兑付金额
     * @param valListStrObj  上期期末产品金额
     * @param lastValListStrObj1 期末产品金额，当期申购金额，当期兑付金额
     * @return
     */
    public static BigDecimal[] lastValList(String valListStrObj, String lastValListStrObj1) {
        BigDecimal lastVal[]= new BigDecimal[2];
        String value[] = lastValListStrObj1.split(";");
        String endamtsumlast = valListStrObj;
        String endamtsum = value[0];
        String raising_amtsum = value[1];
        String redamt = value[2];
        //上期期末产品金额+当期申购金额-当期兑付金额

        // 增加非空判断
        if (endamtsumlast == null || raising_amtsum == null || redamt == null) {
            return lastVal;
        }

        BigDecimal endamtsumlastBD = convertToBigdecimal(endamtsumlast);
        BigDecimal raisingAmtsumBD = convertToBigdecimal(raising_amtsum);
        BigDecimal redamtBD = convertToBigdecimal(redamt);
        // 增加非空判断 能否返回lastVal?
        if (endamtsumlastBD == null) {
            return lastVal;
        }
        lastVal[0]= new BigDecimal(endamtsum);

        lastVal[1] = (endamtsumlastBD.add(raisingAmtsumBD)).subtract(redamtBD);
        // lastVal[0] = (convertToBigdecimal(endamtsumlast).add(convertToBigdecimal(raising_amtsum))).subtract(convertToBigdecimal(redamt));

        return lastVal;
    }

    /**
     *  增长率大于lineNum时 返回false
     * @param thisValListStrObj   当期值
     * @param lastValListStrObj   上期值
     * @param lineNumObj   上限值
     * @param isABS   增长率是否取绝对值
     * @return
     */
    private static  Map<String, Object> compareYieldOne(Object thisValListStrObj,Object lastValListStrObj,Object lineNumObj,Boolean isABS) {
        if(thisValListStrObj == null){
            return ExpressionUtil.resultMap(false,thisValListStrObj);
        }
        if(lastValListStrObj == null){
            return ExpressionUtil.resultMap(true,thisValListStrObj);
        }
        String thisValListStr = thisValListStrObj.toString();
        String lastValListStr = lastValListStrObj.toString();
        BigDecimal thisValBig = BigDecimal.ZERO;
        BigDecimal lastValBig = BigDecimal.ZERO;
        thisValBig = thisValBig.add(convertToBigdecimal(thisValListStrObj));
        lastValBig = lastValBig.add(convertToBigdecimal(lastValListStrObj));

        BigDecimal yield = calYield(thisValBig, lastValBig, BigDecimal.ROUND_HALF_UP);

        yield = isABS ? yield.abs() : yield;

        return lessTo(yield,lineNumObj);
    }

    /**
     * DB0207
     *
     * @param tag1Obj sql语句
     * @param target  产品代码
     * @param target1 比较值
     * @return
     */
    public static Map<String, Object> sqlGreaterTo(Object tag1Obj, Object target, Object target1) {
        // 产品代码 比较值为空
        if (target == null || target1 == null) {
            return ExpressionUtil.resultMap(true, "产品代码为空");
        }

        tag1Obj = EnumerateExpression.sqlValueOne(String.valueOf(tag1Obj), String.valueOf(target), null, null);

        if (tag1Obj == null) {
            return ExpressionUtil.resultMap(true, tag1Obj);
        }
        //String tag1 = tag1Obj.toString();
        Map<String, Object> map = greaterTo(tag1Obj, target1);
        return ExpressionUtil.resultMap((Boolean) map.get("flag"), target);
    }

    /**
     * DB0213
     * 增长率大于lineNum时 返回false(增长率非绝对值)
     * @param thisValListStrObj 本期
     * @param lastValListStrObj 上期 (sql)
     * @param obj 期数
     * @param lineNumObj 目标值
     * @return
     */
    public static Map<String, Object> sqlCompareYieldNotABS(Object thisValListStrObj, Object lastValListStrObj, Object obj, Object lineNumObj) {
        if (obj == null) {
            return ExpressionUtil.resultMap(true, "期数为空");
        }
        lastValListStrObj = EnumerateExpression.sqlValueOne(String.valueOf(lastValListStrObj), null, String.valueOf(obj), null);

        // (c04000_企业债券余额总计-微型企业(本期) - c04000_企业债券余额总计-微型企业(上期）)/c04000_企业债券余额总计-微型企业（上期）*100%>20%

        if (thisValListStrObj == null || !StringUtil.isNotEmpty(thisValListStrObj)) {
            return ExpressionUtil.resultMap(true, "本期为空");
        }

        if (lastValListStrObj == null || !StringUtil.isNotEmpty(lastValListStrObj)) {
            return ExpressionUtil.resultMap(true, "上期为空");
        }
        // 本期
        BigDecimal thisVal = convertToBigdecimal(thisValListStrObj);
        // 上期
        BigDecimal lastVal = convertToBigdecimal(lastValListStrObj);
        // 百分比
        BigDecimal divide = BigDecimal.ZERO;
        try {
            // 增加非空判断
            if (thisVal == null) {
                return ExpressionUtil.resultMap(true, "参数thisValListStrObj为空了");
            }
            divide = thisVal.subtract(lastVal).divide(lastVal).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (ArithmeticException e) {
            ;
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false, thisVal);
        }
        // 小于目标值
        return ExpressionUtil.resultMap((Boolean) lessTo(divide, lineNumObj).get("flag"), thisValListStrObj);
    }

    /**
     * DB0212
     *
     * @param tag1Obj sql语句
     * @param target  比较值
     * @param target1 期数
     * @return
     */
    public static Map<String, Object> sqlEqualsTo(Object tag1Obj, Object target, Object target1) {
        // 比较值为空
        if (target == null || target1 == null) {
            return ExpressionUtil.resultMap(true, "目标为空");
        }

        tag1Obj = EnumerateExpression.sqlValueOne(String.valueOf(tag1Obj), null, String.valueOf(target1), null);

        if (tag1Obj == null) {
            return ExpressionUtil.resultMap(true, tag1Obj);
        }
        //String tag1 = tag1Obj.toString();
        Map<String, Object> map = beEqual(tag1Obj, target);
        return ExpressionUtil.resultMap((Boolean) map.get("flag"),tag1Obj+","+target);
    }

    /**
     *多值运算
     * @param list1
     * @param list2
     * @return
     */
    public static BigDecimal multiAvl(List<Object> list1, List<Object> list2) {
        // 存放计算结果
        BigDecimal result = new BigDecimal(0);
        try {
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (i == j) {
                        // 加法
                        if ("+".equals(String.valueOf(list2.get(j)))) {
                            if (list1.get(i) != null) {
                                result = result.add(new BigDecimal(String.valueOf(list1.get(i))));
                            }
                        }

                        // 减法
                        if ("-".equals(String.valueOf(list2.get(j)))) {
                            if (list1.get(i) != null) {
                                result = result.subtract(new BigDecimal(String.valueOf(list1.get(i))));
                            }
                        }

                        // 乘法
                        if ("*".equals(String.valueOf(list2.get(j)))) {
                            if (list1.get(i) != null) {
                                result = result.multiply(new BigDecimal(String.valueOf(list1.get(i))));
                            }
                        }

                        // 除法
                        if ("/".equals(String.valueOf(list2.get(j)))) {
                            if (list1.get(i) != null) {
                                result = result.divide(new BigDecimal(String.valueOf(list1.get(i))), 2, BigDecimal.ROUND_HALF_UP);
                            }
                        }
                    }
                }
            }
        }catch (Exception e) {
            return BigDecimal.ZERO;
        }
        return result;
    }

}
