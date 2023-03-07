package com.joyintech.tams.common.svreport.datacheck.beforeinsert.util;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 唯一性校验表达式
 */
public class UniqueExpression {
    public static String uniqueOneExpression = "unique_kit.isStrUniqueList(arg0, arg1)";
    public static String uniqueTwoExpression = "unique_kit.isStrUniqueList(arg0, arg1, arg2)";
    public static String uniqueThreeExpression = "unique_kit.isStrUniqueList(arg0, arg1, arg2, arg3)";
    public static String uniqueFourExpression = "unique_kit.isStrUniqueList(arg0, arg1, arg2, arg3, arg4)";
    public static String leastOneBWBExpression = "unique_kit.isLeastOneBWBList(arg0, arg1, agr2)";

    public static String uniformityExpression = "unique_kit.isStrUniformity(arg0, arg1)";
    public static String moneyUniformityExpression = "unique_kit.isMoneyUniformity(arg0, arg1)";
    public static String moneyAreaUniformityExpression = "unique_kit.isMoneyAreaUniformity(arg0, arg1, arg2, arg3)";
    public static String moneyCcyUniformityExpression = "unique_kit.isMoneyCcyUniformity(arg0, arg1, arg2, arg3)";
    public static String judgePartyProdTypeExpression = "str_kit.judgePartyProdType(arg1, arg2)";

    public static String BigExpression = "big_decimal_kit.beEqual(arg1, arg2)";


    /**
     * 判断arg0组合字符串 在List中是否唯一或者不存在，满足则为true，不满足false
     * @param arg0
     * @param list
     * @return
     */
    public static Map<String, Object> isStrUniqueList(String arg0, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String result = arg0 ;
        map.put("flag", flag);
        map.put("data", "arg0="+arg0);
        try {
//          map.put("flag", list.contains(result) ? false : true);
            map.put("flag", findStrCount(list, result)!=1 ? false : true);
        } catch (Exception e) {
            flag = false;
        }
        return map;
    }

    /**
     * 校验arg0是否与系统参数一致
     * @param arg0
     * @return
     */
    public static  Map<String, Object> equalsSysParaValue(Object arg0,String sysPara) {
        if(arg0==null || StringUtil.equals(String.valueOf(arg0),"null")){
            return ExpressionUtil.resultMap(true,null);
        }
        String sysParaValue = CacheUtil.getSysParam(sysPara);
        if(!StringUtil.equals(String.valueOf(arg0),sysParaValue)){
            return ExpressionUtil.resultMap(false,String.valueOf(arg0));
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 判断arg0+arg1组合字符串 在List中是否唯一或者不存在，满足则为true，不满足false
     * @param arg0
     * @param arg1
     * @param list
     * @return
     */
    public static Map<String, Object> isStrUniqueList(String arg0, String arg1, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String result = arg0 + arg1;
        map.put("flag", flag);
        map.put("data", "arg0="+arg0+",arg1="+arg1);
        try {
//            map.put("flag", list.contains(result) ? false : true);
            map.put("flag", findStrCount(list, result)!=1 ? false : true);
        } catch (Exception e) {
            flag = false;
        }
        return map;
    }

    /**
     * 判断arg0+arg1+arg3 组合字符串 在List中是否唯一或者不存在，满足则为true，不满足false
     * @param arg0
     * @param arg1
     * @param arg2
     * @param list
     * @return
     */
    public static Map<String, Object> isStrUniqueList(String arg0, String arg1, String arg2, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String result = arg0 + arg1 + arg2;
        map.put("flag", flag);
        map.put("data", "arg0="+arg0+",arg1="+arg1+",arg2="+arg2);
        try {
//            map.put("flag", list.contains(result) ? false : true);
            map.put("flag", findStrCount(list, result)!=1 ? false : true);
        } catch (Exception e) {
            flag = false;
        }
        return map;
    }

    //

    /**
     * 判断arg0+arg1+arg3+arg4 组合字符串 在List中是否唯一或者不存在，满足则为true，不满足false
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param list
     * @return
     */
    public static Map<String, Object> isStrUniqueList(String arg0, String arg1, String arg2, String arg3, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String result = arg0 + arg1 + arg2 + arg3;
        map.put("flag", flag);
        map.put("data", "arg0="+arg0+",arg1="+arg1+",arg2="+arg2+",arg3="+arg3);
        try {
//            map.put("flag", list.contains(result) ? false : true);
            map.put("flag", findStrCount(list, result)!=1 ? false : true);
        } catch (Exception e) {
            flag = false;
        }
        return map;
    }

    /**
     * 判断如果arg0="BWB",则判断arg1在list中至少有一条,满足true，不满足false
     * @param arg0 产品代码
     * @param list 产品代码+币种的list
     * @return
     */
    public static Map<String, Object> isLeastOneBWBList(String arg0, String arg1, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        if(!StringUtil.equals(arg0, "BWB")){
            map.put("flag", false);
            map.put("data", " arg0="+arg0+",arg1="+arg1);
        }else{
            String result = arg1 + arg0;
            map.put("flag", flag);
            map.put("data", " arg0="+arg0+",arg1="+arg1);
            try {
//            map.put("flag", list.contains(result) ? false : true);
                map.put("flag", findStrCount(list, result)>=1 ? true : false);
            } catch (Exception e) {
                flag = false;
            }
        }

        return map;
    }

    /**
     * 判断如果arg0的前6位，arg1的前6位是否一致，满足true，不满足false
     *
     * @param arg0
     * @param arg1
     * @return
     */
    public static Map<String, Object> isStrUniformity(Object arg0, Object arg1) {
        if(StringUtil.equals(String.valueOf(arg0),"null") && StringUtil.equals(String.valueOf(arg1),"null")){
            return ExpressionUtil.resultMap(true,null);
        }
        if(String.valueOf(arg0).length() < 6){
            return ExpressionUtil.resultMap(false,String.valueOf(arg0) + "长度小于6位");
        }
        if(String.valueOf(arg1).length() < 6){
            return ExpressionUtil.resultMap(false,String.valueOf(arg1) + "长度小于6位");
        }
        if(StringUtil.equals(String.valueOf(arg0).substring(0,6), String.valueOf(arg1).substring(0,6))){
            return ExpressionUtil.resultMap(true,null);
        }
        return ExpressionUtil.resultMap(false,String.valueOf(arg0) + "&" + String.valueOf(arg1));
    }

    //参数转换
    public static BigDecimal convertToBigdecimal(String arg0) {
        return new BigDecimal(arg0);
    }

    //根据参数值list获取 加/减 汇总值
    public static BigDecimal dealCountByStr(List<String> list) {
        BigDecimal countNumber = new BigDecimal(0);
        for (String s : list) {
            countNumber = countNumber.add(convertToBigdecimal(s));
        }
        return countNumber;
    }

    /**
     * 相同的产品代码、地区和客户类型下，币种为'BWB'的初始募集金额折人民币应等于除'BWB'外的其他各币种初始募集金额折人民币之和
     * @param arg0 产品代码
     * @param arg1 地区
     * @param arg2 客户类型  arg3 币种 arg4 初始募集金额折人民币
     * @param list key:arg0+arg1+arg2+arg3+"_"+arg4 产品代码+地区+客户类型+币种+"_"+初始募集金额折人民币
     * @return
     */
    public static Map<String, Object> isMoneyCcyUniformity(String arg0, String arg1, String arg2, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String ccy = "BWB";
        map.put("data", "arg0="+arg0+",arg1="+arg1+",arg2="+arg2);
        if(!list.isEmpty()){
            String key = arg0+arg1+arg2+ccy;
            String keyAll = arg0+arg1+arg2;
            //List<String> BWBList = list.stream().filter(a->a.get(key).contains("BWB")).collect(Collectors.toList());
            List<String> BWBList = new ArrayList<>();
            List<String> noBWBList = new ArrayList<>();
            BigDecimal BWBResult = new BigDecimal(0);
            BigDecimal noBWBResult = new BigDecimal(0);
            for(String str : list){
                if(str.contains(key)){
                    BWBList.add(str.substring(str.lastIndexOf("_")+1) !=null ? str.substring(str.lastIndexOf("_")+1): "0");
                }else {
                    if(str.contains(keyAll)){
                        noBWBList.add(str.substring(str.lastIndexOf("_")+1) !=null ? str.substring(str.lastIndexOf("_")+1) : "0");
                    }
                }
            }
            BWBResult = dealCountByStr(BWBList);
            noBWBResult = dealCountByStr(noBWBList);

            if(BWBResult.compareTo(noBWBResult) == 0){
                map.put("flag", true);
            }else{
                map.put("flag", false);
            }
        } else {
            map.put("flag", false);
        }

        return map;
    }

    /**
     * 相同的产品代码、币种和客户类型下，地区为'000000'的初始募集金额折人民币应等于除'000000'外的其他各地区的初始募集金额折人民币之和
     * @param arg0 产品代码
     * @param arg1 币种
     * @param arg2 客户类型  arg3 地区 arg4 初始募集金额折人民币
     * @param list key:arg0+arg1+arg2+arg3+"_"+arg4 产品代码+币种+客户类型+地区+"_"+初始募集金额折人民币
     * @return
     */
    public static Map<String, Object> isMoneyAreaUniformity(String arg0, String arg1, String arg2, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String area = "000000";
        map.put("data", "arg0="+arg0+",arg1="+arg1+",arg2="+arg2);
        if(!list.isEmpty()){
            String key = arg0+arg1+arg2+area;//产品代码+币种+客户类型+地区
            String keyAll = arg0+arg1+arg2;//产品代码+币种+客户类型
            //List<String> BWBList = list.stream().filter(a->a.get(key).contains("BWB")).collect(Collectors.toList());
            List<String> areaList = new ArrayList<>();
            List<String> noAreaList = new ArrayList<>();
            BigDecimal areaResult = new BigDecimal(0);
            BigDecimal noAreaResult = new BigDecimal(0);
            for(String str : list){
                if(str.contains(key)){
                    areaList.add(str.substring(str.lastIndexOf("_")+1) !=null ? str.substring(str.lastIndexOf("_")+1): "0");
                }else {
                    if(str.contains(keyAll)){
                        noAreaList.add(str.substring(str.lastIndexOf("_")+1) !=null ? str.substring(str.lastIndexOf("_")+1) : "0");
                    }
                }
            }
            areaResult = dealCountByStr(areaList);
            noAreaResult = dealCountByStr(noAreaList);

            if(areaResult.compareTo(noAreaResult) == 0){
                map.put("flag", true);
            }else{
                map.put("flag", false);
            }
        } else {
            map.put("flag", false);
        }

        return map;
    }

    /**
     * 同一个产品代码下，指标A0000_资产合计的币种为'BWB'的数据应等于其他各币种（数据类型=2时）数据之和
     * @param arg0 产品代码 arg1 币种 arg2 初始募集金额折人民币
     * @param
     * @param list key:arg0+arg1+"_"+arg2 产品代码+币种+"_"+A0000_资产合计
     * @return
     */
    public static Map<String, Object> isMoneyUniformity(String arg0, List<String> list) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String ccy = "BWB";
        map.put("data", "arg0="+arg0);
        if(!list.isEmpty()){
            String key = arg0+ccy;
            String keyAll = arg0;
            List<String> BWBList = new ArrayList<>();
            List<String> noBWBList = new ArrayList<>();
            BigDecimal BWBResult = new BigDecimal(0);
            BigDecimal noBWBResult = new BigDecimal(0);
            for(String str : list){
                if(str.contains(key)){
                    BWBList.add(str.substring(str.lastIndexOf("_")+1) !=null ? str.substring(str.lastIndexOf("_")+1): "0");
                }else {
                    if(str.contains(keyAll)){
                        noBWBList.add(str.substring(str.lastIndexOf("_")+1) !=null ? str.substring(str.lastIndexOf("_")+1) : "0");
                    }
                }
            }
            BWBResult = dealCountByStr(BWBList);
            noBWBResult = dealCountByStr(noBWBList);

            if(BWBResult.compareTo(noBWBResult) == 0){
                map.put("flag", true);
            }else{
                map.put("flag", false);
            }
        } else {
            map.put("flag", false);
        }

        return map;
    }

    /**
     * 相同的产品代码下，只可报送1条产品期末业绩表现数据！
     * @param arg0 产品代码
     * @param arg1 校验值
     * @param param1 主键
     * @param param2 主键
     * @param mapList 所有数据
     * @return 校验结果
     */
    public static Map<String, Object> listConditionOne(Object arg0, Object arg1, Object param1, Object param2, List<Map<String, Object>> mapList) {
        if (ObjectUtils.isEmpty(arg0)){
            return ExpressionUtil.resultMap(true, "arg0 为空");
        }
        // 筛选 当前产品代码 并且 校验有数的 数据
        Map<String, List<Map<String, Object>>> collect = mapList.stream()
                .filter(e -> StringUtil.isNotEmpty(e.get(param2.toString())))
                .collect(Collectors.groupingBy(e -> e.get(param1.toString()).toString()));

        // 没有该产品的数据
        if (!collect.containsKey(arg0.toString())){
            return ExpressionUtil.resultMap(true, arg0);
        }
        // 最多报送一条
        if (collect.get(arg0.toString()).size() > 1) {
            return ExpressionUtil.resultMap(false, arg0);
        }
        return ExpressionUtil.resultMap(true, "");
    }

    /**
     * 相同的产品代码、币种下 ，只可报送1条净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币有值的数据！
     * @param arg0 产品代码
     * @param arg1 币种
     * @param param1 主键 key
     * @param param2 主键 key
     * @param param3 字段组合
     * @param mapList 所有数据
     * @return 校验结果
     */
    public static Map<String, Object> listConditionOneByTwoParam(Object arg0, Object arg1, Object param1, Object param2, Object param3, List<Map<String, Object>> mapList) {
        // 前置条件为空 不进行校验
        if (ObjectUtils.isEmpty(arg0)) {
            return ExpressionUtil.resultMap(true, arg0);
        }
        if (ObjectUtils.isEmpty(arg1)) {
            return ExpressionUtil.resultMap(true, arg1);
        }
        // 脚本配置错误
        List<String> list = StringUtil.splitToList(GlobalConstant.COMMA, param3.toString());
        if (null == list || list.size() != 4) {
            return ExpressionUtil.resultMap(false, arg1);
        }
        // 根据代码 和 币种分组 并 字段组合 任意一个有数
        Map<String, List<Map<String, Object>>> collect = mapList.stream()
                .filter(e -> StringUtil.isNotEmpty(e.get(list.get(0))) || StringUtil.isNotEmpty(e.get(list.get(1))) || StringUtil.isNotEmpty(e.get(list.get(2))) || StringUtil.isNotEmpty(e.get(list.get(3))))
                .collect(Collectors.groupingBy(e -> String.valueOf(e.get(String.valueOf(param1))) + String.valueOf(e.get(String.valueOf(param2)))));
        // 当前数据 主键组合
        String keys = String.valueOf(arg0) + String.valueOf(arg1);
        // 不存在不校验
        if (!collect.containsKey(keys)) {
            return ExpressionUtil.resultMap(true, arg0);
        }
        if (collect.get(keys).size() > 1) {
            return ExpressionUtil.resultMap(false, String.valueOf(arg0) + String.valueOf(arg1));
        }
        return ExpressionUtil.resultMap(true, String.valueOf(arg0) + String.valueOf(arg1));
    }

    /**
     * 相同的产品代码、地区、币种、客户类型下，
     *      pbcProdId,buyPlace,ccy,customerType1
     * 只可报送1条当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额有值的数据！
     *             raisingAmtsum,raisingAmtsumCny,raisingQty,redamt,redamtCny,redqty,endamtsum,endamtsumCny,endqty
     * @param arg0 产品代码
     * @param arg1 地区
     * @param arg2 币种
     * @param arg3 客户类型
     * @param param1 主键组合 key
     * @param param2 字段组合 key
     * @param mapList 所有数据
     * @return 校验结果
     */
    public static Map<String, Object> listConditionOneByFourParam(Object arg0, Object arg1, Object arg2, Object arg3, Object param1, Object param2, List<Map<String, Object>> mapList) {
        // 前置条件存在 空数据
        // 脚本配置错误
        List<String> list1 = StringUtil.splitToList(GlobalConstant.COMMA, param1.toString());
        if (null == list1 || list1.size() != 4) {
            return ExpressionUtil.resultMap(false, arg1);
        }
        // 脚本配置错误
        List<String> list2 = StringUtil.splitToList(GlobalConstant.COMMA, param2.toString());
        if (null == list2 || list2.size() != 9) {
            return ExpressionUtil.resultMap(false, arg1);
        }
        // 根据 产品代码、地区、币种、客户类型 分组 并 字段组合 任意一个有数
        Map<String, List<Map<String, Object>>> collect = mapList.stream()
                .filter(e ->
                        StringUtil.isNotEmpty(e.get(list2.get(0)))
                                || StringUtil.isNotEmpty(e.get(list2.get(1)))
                                || StringUtil.isNotEmpty(e.get(list2.get(2)))
                                || StringUtil.isNotEmpty(e.get(list2.get(3)))
                                || StringUtil.isNotEmpty(e.get(list2.get(4)))
                                || StringUtil.isNotEmpty(e.get(list2.get(5)))
                                || StringUtil.isNotEmpty(e.get(list2.get(6)))
                                || StringUtil.isNotEmpty(e.get(list2.get(7)))
                                || StringUtil.isNotEmpty(e.get(list2.get(8))))
                .collect(Collectors.groupingBy(e -> String.valueOf(e.get(list1.get(0)))
                        + String.valueOf(e.get(list1.get(1)))
                        + String.valueOf(e.get(list1.get(2)))
                        + String.valueOf(e.get(list1.get(3)))));
        // 当前数据 主键组合
        String keys = String.valueOf(arg0) + String.valueOf(arg1) + String.valueOf(arg2) + String.valueOf(arg3);
        // 不存在不校验
        if (!collect.containsKey(keys)) {
            return ExpressionUtil.resultMap(true, arg0);
        }
        if (collect.get(keys).size() > 1) {
            return ExpressionUtil.resultMap(false, String.valueOf(arg0) + String.valueOf(arg1) + String.valueOf(arg2) + String.valueOf(arg3));
        }
        return ExpressionUtil.resultMap(true, "");
    }

    /**
     * DS04132
     *
     * 当期申购金额、当期申购份额、当期兑付/赎回金额、当期兑付/赎回份额、期末产品金额、期末产品份额任何一个字段有数时，
     *  raisingAmtsum,raisingQty,redamt,redqty,endamtsum,endqty
     * 该报表每个产品代码下至少有2条
     * “当期申购金额、当期申购金额折人民币、当期申购份额、当期兑付/赎回金额、当期兑付/赎回金额折人民币、当期兑付/赎回份额、期末产品金额、期末产品金额折人民币、期末产品份额”均不为空的数据
     *  raisingAmtsum,raisingAmtsumCny,raisingQty,redamt,redamtCny,redqty,endamtsum,endamtsumCny,endqty
     *
     *
     * @param arg0 当前 产品代码
     * @param param1 产品代码 key
     * @param param2 有数 key
     * @param param3 均不为空 key
     * @param mapList 所有数据
     * @return
     */
    public static Map<String, Object> listConditionTwoByNineParam(Object arg0, Object param1, Object param2, Object param3, List<Map<String, Object>> mapList) {
        // 脚本配置错误
        List<String> list1 = StringUtil.splitToList(GlobalConstant.COMMA, param2.toString());
        if (null == list1 || list1.size() != 6) {
            return ExpressionUtil.resultMap(false, arg0);
        }

        // 脚本配置错误
        List<String> list2 = StringUtil.splitToList(GlobalConstant.COMMA, param3.toString());
        if (null == list2 || list2.size() != 9) {
            return ExpressionUtil.resultMap(false, arg0);
        }

        // 产品代码为空 不校验
        if (Objects.isNull(arg0)) {
            return ExpressionUtil.resultMap(true, "pbcProdId为空");
        }

        // 过滤 当前的产品代码 并且
        List<Map<String, Object>> tempList = mapList.stream()
                // 过滤 产品代码
                .filter(e -> StringUtil.isNotEmpty(e.get(param1)) && StringUtil.equals(arg0, e.get(param1)))
                // 过滤 任一不为空的
                .filter(e ->
                        StringUtil.isNotEmpty(e.get(list1.get(0)))
                                || StringUtil.isNotEmpty(e.get(list1.get(1)))
                                || StringUtil.isNotEmpty(e.get(list1.get(2)))
                                || StringUtil.isNotEmpty(e.get(list1.get(3)))
                                || StringUtil.isNotEmpty(e.get(list1.get(4)))
                                || StringUtil.isNotEmpty(e.get(list1.get(5)))
                )
                .collect(Collectors.toList());

        // 前提校验不满足 应校验通过
        if (CollectionUtils.isEmpty(tempList)) {
            return ExpressionUtil.resultMap(true, arg0);
        }
        // 需要校验的 条件
        Map<Object, List<Map<String, Object>>> collect = tempList.stream()
                .filter(e ->
                        StringUtil.isNotEmpty(e.get(list2.get(0)))
                                && StringUtil.isNotEmpty(e.get(list2.get(1)))
                                && StringUtil.isNotEmpty(e.get(list2.get(2)))
                                && StringUtil.isNotEmpty(e.get(list2.get(3)))
                                && StringUtil.isNotEmpty(e.get(list2.get(4)))
                                && StringUtil.isNotEmpty(e.get(list2.get(5)))
                                && StringUtil.isNotEmpty(e.get(list2.get(6)))
                                && StringUtil.isNotEmpty(e.get(list2.get(7)))
                                && StringUtil.isNotEmpty(e.get(list2.get(8)))
                )
                .collect(Collectors.groupingBy(e -> e.get(param1)));
        // 不存在 校验错误
        if (!collect.containsKey(arg0)) {
            return ExpressionUtil.resultMap(false, arg0);
        }
        // 至少有两条 校验通过
        if (collect.get(arg0) != null && collect.get(arg0).size() >= 2) {
            return ExpressionUtil.resultMap(true, arg0);
        }
        return ExpressionUtil.resultMap(false, arg0);
    }

    /**
     * DS04133
     * 净值型产品期末净值、净值型产品期末累计净值任意一个字段有数时，
     * prodValue,unitValueTotal
     * 该报表每个净值型产品代码下至少有2条
     * “净值型产品期末净值、净值型产品期末净值折人民币、净值型产品期末累计净值、净值型产品期末累计净值折人民币” 均 不为空的数据
     *  prodValue,prodValueCny,unitValueTotal,unitValueTotalCny
     *
     * @param arg0 本条数据 产品代码
     * @param param1 产品代码 key
     * @param param2 任一有数 key
     * @param param3 均不为空 key
     * @param mapList 所有数据
     * @return
     */
    public static Map<String, Object> listConditionTwoByFourParam(Object arg0, Object param1, Object param2, Object param3, List<Map<String, Object>> mapList) {
        // 脚本配置错误
        List<String> list1 = StringUtil.splitToList(GlobalConstant.COMMA, param2.toString());
        if (null == list1 || list1.size() != 2) {
            return ExpressionUtil.resultMap(false, arg0);
        }

        // 脚本配置错误
        List<String> list2 = StringUtil.splitToList(GlobalConstant.COMMA, param3.toString());
        if (null == list2 || list2.size() != 4) {
            return ExpressionUtil.resultMap(false, arg0);
        }

        // 产品代码为空 不校验
        if (Objects.isNull(arg0)) {
            return ExpressionUtil.resultMap(true, "pbcProdId为空");
        }

        // 过滤 当前的产品代码 并且
        List<Map<String, Object>> tempList = mapList.stream()
                // 过滤 产品代码
                .filter(e -> StringUtil.isNotEmpty(e.get(param1)) && StringUtil.equals(arg0, e.get(param1)))
                // 过滤 任一不为空的
                .filter(e ->
                        StringUtil.isNotEmpty(e.get(list1.get(0)))
                                || StringUtil.isNotEmpty(e.get(list1.get(1)))
                )
                .collect(Collectors.toList());

        // 不满足前置条件 应该 校验通过
        if (CollectionUtils.isEmpty(tempList)) {
            return ExpressionUtil.resultMap(true, arg0);
        }

        Map<Object, List<Map<String, Object>>> collect = tempList.stream()
                // 过滤 均不为空的数据
                .filter(e ->
                        StringUtil.isNotEmpty(e.get(list2.get(0)))
                                && StringUtil.isNotEmpty(e.get(list2.get(1)))
                                && StringUtil.isNotEmpty(e.get(list2.get(2)))
                                && StringUtil.isNotEmpty(e.get(list2.get(3)))
                )
                .collect(Collectors.groupingBy(e -> e.get(param1)));

        // 不存在 校验错误
        if (!collect.containsKey(arg0)) {
            return ExpressionUtil.resultMap(false, arg0);
        }
        // 至少有两条 校验通过
        if (collect.get(arg0) != null && collect.get(arg0).size() >= 2) {
            return ExpressionUtil.resultMap(true, arg0);
        }
        return ExpressionUtil.resultMap(false, arg0);
    }


    /**
     * 根据指定字符串，查询出现的次数
     *
     * @param keyList
     * @param keywords
     * @return
     */
    public static int findStrCount(List<String> keyList, String keywords) {
        int count = 0;
        if(keyList.isEmpty() || StringUtil.isEmpty(keywords)){
            return count;
        }
        for (String keyVal : keyList) {
            if (keywords.equals(keyVal)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 所有产品数据中，不能存在多条记录
     * @param list  多条数据
     * @param obj0  产品代码 = pbcProdId
     * @param obj1  产品收益率 -> 兑付客户收益率 cusYearYield
     * @return
     */
    public static Map<String, Object> notMultiInList1(List<Map<String, Object>> list, String obj0, Object obj1, Object obj3, Object obj4) {
        // 如果产品产品数据为空的时候不校验，返回true
        if (list.size() == 0) {
            return ExpressionUtil.resultMap(true, "产品数据为空");
        }

        // 如果产品产品代码为空的时候不校验，返回true
        if (ObjectUtils.nullSafeEquals(obj0, null)) {
            return ExpressionUtil.resultMap(true, "产品代码为空");
        }

        // 获取同一产品的list
        List<Map<String, Object>> pbcProdIdList = new ArrayList();
        for (Map<String, Object> map : list) {
                if (ObjectUtils.nullSafeEquals(obj0, map.get(String.valueOf(obj3)))) {
                    pbcProdIdList.add(map);
            }
        }

        int sum = 0;
        for (Map<String, Object> map : pbcProdIdList) {
            if (!ObjectUtils.isEmpty(map.get(obj4))) {
                sum += 1;
            }
        }

        return ExpressionUtil.resultMap(2 > sum ? true : false, "产品代码:" + obj0);
    }

    /**
     * 所有产品数据中，不能存在多条记录
     * @param list  多条数据
     * @param obj0  产品代码 = pbcProdId
     * @param obj1  币种 ccy
     * @return
     */
    public static Map<String, Object> notMultiInList2(List<Map<String, Object>> list, String obj0, Object obj1, String obj2, Object obj3) {
        // 如果产品产品代码为空的时候不校验，返回true
        if (ObjectUtils.nullSafeEquals(obj3, null)) {
            return ExpressionUtil.resultMap(true, "币种为空");
        }
        // 如果产品产品数据为空的时候不校验，返回true
        if (list.size() == 0) {
            return ExpressionUtil.resultMap(true, "产品数据为空");
        }

        // 如果产品产品代码为空的时候不校验，返回true
        if (ObjectUtils.nullSafeEquals(obj0, null)) {
            return ExpressionUtil.resultMap(true, "产品代码为空");
        }

        // 获取同一产品的list
        List<Map<String, Object>> pbcProdIdList = new ArrayList();
        for (Map<String, Object> map : list) {
            if(ObjectUtils.nullSafeEquals(map.get(obj2), null)){
                continue;
            }
                if (ObjectUtils.nullSafeEquals(obj0, map.get(obj1))) {
                    pbcProdIdList.add(map);
                }
        }

        // 主键组合
        String keys = obj0.toString() + obj3.toString();

        // 产品代码 + 币种的组合
        Map<String, List<Map<String, Object>>> collect = pbcProdIdList.stream().collect(Collectors.groupingBy(e -> e.get(obj1).toString() + e.get(obj2).toString().toString()));

        // 没有就不校验
        if (!collect.containsKey(keys)) {
            return ExpressionUtil.resultMap(true, obj1);
        }

        return ExpressionUtil.resultMap(2 > collect.get(keys).size() ? true : false, obj0.toString() + obj1.toString());
    }

    /**
     * 终止的产品必须报送1条兑付客户收益数据
     * @param list  多条数据
     * @param obj0  兑付客户收益 cusYearYield
     * @return
     */
    public static Map<String, Object> notMultiInList3(List<Map<String, Object>> list, Object obj0) {
        // 如果产品产品数据为空的时候不校验，返回true
        if (list.size() == 0) {
            return ExpressionUtil.resultMap(true, "产品数据为空");
        }

        int sum = 0;
        for (Map<String, Object> map : list) {
            if (!ObjectUtils.isEmpty(map.get(obj0))) {
                sum += 1;
            }
        }

        return ExpressionUtil.resultMap(0 < sum ? true : false, "obj0:" + obj0);
    }

    /**
     * list中至少报送一条  obj0 有数的数据
     * @param list
     * @param obj0
     * @return
     */
    public static Map<String, Object> notMultiInList4(Object objp, Object obj1, List<Map<String, Object>> list,Object obj,Object obj0) {
        // 如果产品产品数据为空的时候不校验，返回true
        if (list.size() == 0) {
            return ExpressionUtil.resultMap(true, "产品数据为空");
        }
        // 筛选 当前产品代码  数据
        Map<String, List<Map<String, Object>>> collect = list.stream()
                .filter(e -> StringUtil.isNotEmpty(e.get(obj.toString())))
                .collect(Collectors.groupingBy(e -> e.get(obj.toString()).toString()));

        if(!collect.containsKey(objp)){
            return ExpressionUtil.resultMap(true, objp+"产品数据为空");
        }

        int sum = 0;
        try {
        for (Map<String, Object> map : collect.get(objp)) {
            if (!ObjectUtils.isEmpty(map.get(obj0))) {
               BigDecimal  objovalue=new BigDecimal(String.valueOf(map.get(obj0)));
               if(objovalue.compareTo(BigDecimal.ZERO)>=0){
                   sum += 1;
               }
            }
        }
        }catch (Exception e) {
            return ExpressionUtil.resultMap(false, obj1);
        }
        return ExpressionUtil.resultMap(0 < sum ? true : false, obj1);
    }

    /**
     * list中BWB,'000000'至少报送一条  obj0 >0的数据
     * @param list
     * @param obj0
     * @return
     */
    public static Map<String, Object> notMultiInList4bwb(Object objp, Object obj1, Object objccy,Object objbuy,List<Map<String, Object>> list,Object obj,Object obj0,Object str3,Object str4) {
        // 如果产品产品数据为空的时候不校验，返回true
        if (list.size() == 0) {
            return ExpressionUtil.resultMap(true, "产品数据为空");
        }
        //不等于BWB,000000不校验
        if(!(ObjectUtils.nullSafeEquals("BWB",objccy)&&ObjectUtils.nullSafeEquals("000000",objbuy))){
            return ExpressionUtil.resultMap(true, null);
        }
        // 筛选 当前产品代码  数据
        Map<String, List<Map<String, Object>>> collect = list.stream()
                .filter(e -> StringUtil.isNotEmpty(e.get(obj.toString())))
                .collect(Collectors.groupingBy(e -> e.get(obj.toString()).toString()));

        if(!collect.containsKey(objp)){
            return ExpressionUtil.resultMap(true, objp+"产品数据为空");
        }

        int sum = 0;
        try {
            for (Map<String, Object> map : collect.get(objp)) {
                if(!(ObjectUtils.nullSafeEquals("BWB",map.get(str3))&&ObjectUtils.nullSafeEquals("000000",map.get(str4)))){
                    continue;
                }
                if (!ObjectUtils.isEmpty(map.get(obj0))) {
                    BigDecimal  objovalue=new BigDecimal(String.valueOf(map.get(obj0)));
                    if(objovalue.compareTo(BigDecimal.ZERO)>0){
                        sum += 1;
                    }
                }

            }
        }catch (Exception e) {
            return ExpressionUtil.resultMap(false, obj1);
        }
        return ExpressionUtil.resultMap(0 < sum ? true : false, obj1);
    }

}
