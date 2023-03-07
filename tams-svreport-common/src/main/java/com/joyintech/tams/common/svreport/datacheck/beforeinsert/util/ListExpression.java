package com.joyintech.tams.common.svreport.datacheck.beforeinsert.util;

import com.joyintech.framework.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 列表表达式
 */
public class ListExpression {

    // 判断一个值是否在List中
    public static Map<String, Object> isInList(List<String> list, String arg0) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        map.put("flag", flag);
        map.put("data", arg0);
        try {
            map.put("flag", list.contains(arg0) ? true : false);
        } catch (Exception e) {
            flag = false;
        }
        return map;
    }

    public static Map<String, Object> compareStr(String arg0, String arg1) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        char i = arg0.charAt(6);
        if(!arg1.equals(i)){
            flag = false;
        }
        map.put("flag", flag);
        map.put("data", arg1);
        return map;
    }

    //当传入list的值不为空时，arg0必填
    public static Map<String,Object> isEmptyByList(List<String> list, String arg0){
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        if(list.size()>0 && StringUtil.isEmpty(arg0)){
            flag = false;
        }
        map.put("flag", flag);
        map.put("data", arg0);
        return map;
    }

    //当满足条件时，字段应为空
    public static Map<String,Object> isEmptyByList1(Object list, Object arg0, String arg1){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        if (!StringUtil.isNotEmpty(list)){
            return map;
        }
        if (!StringUtil.isNotEmpty(arg0)){
            map.put("flag",true);
            return map;
        }
        List<String>  conditionValueList =  StringUtil.splitToList(",", String.valueOf(list));
        if(!conditionValueList.contains(String.valueOf(arg0)) || (conditionValueList.contains(String.valueOf(arg0)) && StringUtil.isEmpty(arg1))){
            flag = true;
        }

        map.put("flag", flag);
        map.put("data", arg1);
        return map;
    }

    //当满足条件时，判断目标字段是否等于目标值。相等返回true
    public static Map<String,Object> compareByListParam(String list, Object arg0, Object arg1, String arg2){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        if (!StringUtil.isNotEmpty(list)){
            return ExpressionUtil.resultMap(false,"脚本配置错误");
        }
        if (!StringUtil.isNotEmpty(arg0)){
            return ExpressionUtil.resultMap(true,null);
        }
        List<String>  conditionValueList =  StringUtil.splitToList(",", list);
        if(!conditionValueList.contains(String.valueOf(arg0)) || (conditionValueList.contains(String.valueOf(arg0)) && StringUtil.equals(String.valueOf(arg1),arg2))){
            return ExpressionUtil.resultMap(true,null);
        }
        return ExpressionUtil.resultMap(false,String.valueOf(arg1));
    }

    //当满足条件时，判断目标字段是否等于目标值。相等返回true
    public static Map<String,Object> compareByListParam1(List<String> list, String arg0, String arg1, String arg2, String arg3, String arg4){
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        if(!list.contains(arg0) || (list.contains(arg0) && arg1.equals(arg2) && arg3.equals(arg4))){
            flag = true;
        }

        map.put("flag", flag);
        map.put("data", arg1);
        return map;
    }

    // 判断一个值是否在List中
    public static Map<String, Object> isNotInList(Object list, Object arg0) {
        if (!StringUtil.isNotEmpty(list) && StringUtil.equals(String.valueOf(arg0),"null")){
            return ExpressionUtil.resultMap(false,"脚本配置错误");
        }
        if (!StringUtil.isNotEmpty(arg0) && StringUtil.equals(String.valueOf(arg0),"null")){
            return ExpressionUtil.resultMap(true,null);
        }
        List<String>  conditionValueList =  StringUtil.splitToList(",", String.valueOf(list));
        if(conditionValueList.contains(String.valueOf(arg0))){
            return ExpressionUtil.resultMap(false,String.valueOf(arg0));
        }
        return ExpressionUtil.resultMap(true,null);
    }

    /**
     * 判断一个值是否在List中(第一个参数用逗号进行隔开，并转成list)
     * @param tagListStr(参数用逗号进行隔开)
     * @param arg0
     * @return
     */
    public static Map<String, Object> sqlisInList(String tagListStr, String arg0) {
        List<String>  tagList =  com.joyintech.framework.util.StringUtil.splitToList(",",tagListStr);
        return isInList(tagList,  arg0);
    }

    /**
     *
     * @param list
     * @param paramValue 需校验字段
     * @param flag1 Y、N
     * @param paramValue2
     * @return
     */
    //flag1：Y，N 判断一个值是否在list里面，如果不在，判断flag1是否为Y 是返回true，否返回false；如果在list里面并且flag1为N，判断paramValue2是否为空，不为空返回true，为空返回false
    public static Map<String, Object> compareListByParam(List<String> list, String paramValue, String flag1, String paramValue2) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;

        try {
            if(!list.contains(paramValue) && "N".equals(flag1)){
                flag = false;
                map.put("flag", flag);
                map.put("data", flag1);
                return map;
            }
            if(list.contains(paramValue) && "N".equals(flag1) && StringUtil.isEmpty(paramValue2)){
                flag = false;
                map.put("flag", flag);
                map.put("data", paramValue2);
                return map;
            }
        } catch (Exception e) {
            flag = false;
        }
        map.put("flag", flag);
        map.put("data", paramValue);
        return map;
    }

    //判断list1中的数据是否在list2中存在 list1:本期数据，list2:上期数据
    public static Map<String, Object> compareList(List<Object> list1, List<Object> list2) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        String value = "";
        for (int i = 0; i < list1.size(); i++) {
            String list1Str = String.valueOf(list1.get(i));
            String isExits = "N";
            for (int j = 0; j < list2.size(); j++) {
                if(list1Str.equals(String.valueOf(list2.get(j)))){
                    isExits = "Y";
                }
            }
            if("N".equals(isExits)){
                flag = false;
                value = list1Str;
                break;
            }
        }
        map.put("flag", flag);
        map.put("data", value);
        return map;
    }



}
