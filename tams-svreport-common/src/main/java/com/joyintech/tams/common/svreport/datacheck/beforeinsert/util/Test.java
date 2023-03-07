package com.joyintech.tams.common.svreport.datacheck.beforeinsert.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static String lengthExpression = "string.length(arg0)";
    public static String length2Expression = "str_kit.compareLength(arg0, 5)";
    public static String notEmptyExpression = "str_kit.isNotEmpty(arg0)";
    public static String beEmptyExpression = "str_kit.beEmpty(arg0)";
    public static String isValidDateExpression = "date_kit.isValidDate(arg0)";
    public static String betweenDateExpression = "date_kit.betweenDate(arg0)";
    public static String isInListExpression = "list_kit.isInList(arg1, arg2)";
    public static String multiAvlAddExpression = "big_decimal_kit.multiAvlAdd(arg0, list1, list2)";
    public static String numberListCompareExpression = "big_decimal_kit.numberListCompare(list1, list2,compareStr)";
    public static String numberListsCompareExpression = "big_decimal_kit.numberListsCompare(list1,list2,compareStr1,list3,compareStr2)";
    public static String dateCompareByParam = "date_kit.dateCompareByParam(arg1,arg2,arg3,arg4)";
    public static String isEmptyByList1 = "list_kit.isEmptyByList1(arg1,arg2,arg3)";
    public static String compareByListParam1 = "list_kit.compareByListParam1(arg1,arg2,arg3,arg4,arg5,arg6)";
    public static String dateCompareTo2 = "date_kit.dateCompareTo2(arg0,arg1)";
    public static String dateCompareTo = "date_kit.dateCompareTo(arg0,arg1)";
    public static String isEmpayByFlag = "str_kit.isEmpayByFlag(arg0, arg1)";
    public static String percentCompare = "big_decimal_kit.percentCompare(arg1, arg2, arg3)";
    //public static String dateCompareTo = "date_kit.dateCompareByParam(arg0,arg1)";
    
    public static String checkNumLen = "big_decimal_kit.checkNumLen(arg1)";
    public static String conditionSumAllEqualsExpression = "big_decimal_kit.conditionSumAllEquals(c1,tar1,tar2,tar3,mapList)";

    public static String conditionBetweenInWithoutStringExpression = "big_decimal_kit.conditionBetweenInWithoutString(num1,num2,num3,replaceStr)";
    public static String isSyntropyExpression = "big_decimal_kit.isSyntropy(num1,num2,replaceStr)";
    public static String getBetweenDayHasConditionExpression = "date_kit.getBetweenDayHasCondition(condition,beginDate,endDate)";
    public static String conditionValueCompareExpression = "str_kit.conditionValueCompare(condition,conditionValue,target1,targetValue1,target2,targetValue2)";
    public static String conditionListCompareExpression = "str_kit.conditionListCompare(condition,conditionValue,list1,list2)";
    public static String conditionListValueExpression = "str_kit.conditionListValue(conditionList,aimValue)";
    public static String compareYearAndMonthValueExpression = "date_kit.compareYearAndMonth(arg0, arg1, arg2)";
    public static String compareDateExpression = "date_kit.compareToStringDate(arg1, arg2)";

    public static String dateCompare3 = "date_kit.dateCompare3(arg1, arg2)";

    public static String judgeValueByCond = "str_kit.judgeValueByCond(str1,str2,str3,str4)";

    public static String compareListByParam = "list_kit.compareListByParam(str1,str2,str3,str4)";

    public static String compareList = "list_kit.compareList(str1,str2)";

    public static String listConditionExistsExpression = "str_kit.listConditionExists(param01, param02,param03,mapList)";
    public static String manyDictIsNullExpression = "enum_kit.manyDictIsNull(condValue, itemCode1,itemCode2,list1,list2)";

    public static String compareWithSysdateExpression = "date_kit.compareWithSysdate(arg1, arg2)";
    public static String isErrorDictCodeSubStrExpression = "enum_kit.isErrorDictCodeSubStr(dictCode, itemCode,subStr)";

    public static String compareListByDict = "str_kit.compareListByDict(list1,list2,str3)";

    public static String isErrorDictCodeObj = "enum_kit.isErrorDictCodeObj(str1, str2,str3, str4)";

    public static String betweenIn2Expression = "big_decimal_kit.betweenIn2(list1, list2, total, val)";




    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

//         Map<String, Object> map1 = new HashMap<>();
//        map1.put("AUD","2");
//        map1.put("AUD","2");
//        map1.put("CAD","3");
//        map1.put("CHF","4");
//        List<Map<String, Object>> list1 = new ArrayList<>();
//        list1.add(map1);
//
//
//        Map<String, Object> map2 = new HashMap<>();
//        map2.put("AUD","1");
//        map2.put("CAD","1");
//        map2.put("CAD","1");
//        map2.put("CHF","1");
//        List<Map<String, Object>> list2 = new ArrayList<>();
//        list2.add(map2);
//
//        map.put("list1", list1);
//        map.put("list2", list2);
//        map.put("str3", "CCY");
//        System.out.println(ExpressionUtil.execute(compareListByDict, map));

        /*List<String> list1 = new ArrayList<>();
        list1.add("10");
        list1.add("10");
        list1.add("10");

        List<String> list22 = new ArrayList<>();
        list22.add("+");
        list22.add("+");
        list22.add("+");
        map.put("list1", list1);
        map.put("list2", list22);
        map.put("total", "50");
        map.put("val", "10");
        System.out.println(ExpressionUtil.execute(betweenIn2Expression, map));*/

        /*map.put("str1", "CCY");
        map.put("str2", "cny");
        map.put("str3", "2");
        map.put("str4", "2");
        System.out.println(ExpressionUtil.execute(isErrorDictCodeObj, map));*/

        /*map.put("str1", "1");
        map.put("str2", "1");
        map.put("str3", "2");
        map.put("str4", "2");
        System.out.println(ExpressionUtil.execute(judgeValueByCond, map));*/


        /*map.put("arg1", "2022-11-11");
        map.put("arg1", "2022-12-03");
        map.put("arg2", "<");
        System.out.println(ExpressionUtil.execute(compareWithSysdateExpression, map));

        map.put("arg1", "2022-12-11");
        map.put("arg2", "2022-12-02");
        System.out.println(ExpressionUtil.execute(dateCompare3, map));

        map.put("arg0", "hello");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");


        map.put("arg1", "100,0000000000000000.32");
        System.out.println(ExpressionUtil.execute(checkNumLen, map));*/

        /*map.put("arg0", "2020-05-13");
        map.put("arg1", "2020-03-13");
        System.out.println(ExpressionUtil.execute(dateCompareTo, map));*/

        /*List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");

        map.put("arg1", list2);
        map.put("arg2", "3");
        map.put("arg3", "1");
        map.put("arg4", "1");
        map.put("arg5", "2");
        map.put("arg6", "2");*/
        //System.out.println(ExpressionUtil.execute(dateCompareByParam, map));
        //System.out.println(ExpressionUtil.execute(isEmptyByList1, map));
        //System.out.println(ExpressionUtil.execute(compareByListParam1, map));

        /*map.put("arg0", "hello");
        System.out.println(ExpressionUtil.execute(lengthExpression, map));

        map.put("arg0", "hello word");
        System.out.println(ExpressionUtil.execute(length2Expression, map));

        String targetStr3 = "1";
        map.put("arg0", targetStr3);
        System.out.println(ExpressionUtil.execute(notEmptyExpression, map));

        String targetStr4 = "";
        map.put("arg0", targetStr4);
        System.out.println(ExpressionUtil.execute(beEmptyExpression, map));

        map.put("arg0", "2020-03-13");
        System.out.println(ExpressionUtil.execute(isValidDateExpression, map));

        map.put("arg0", "2100-03-13");
        System.out.println(ExpressionUtil.execute(betweenDateExpression, map));

        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        list.add("a3");

        map.put("arg1", list);
        map.put("arg2", "a12");
        System.out.println(ExpressionUtil.execute(isInListExpression, map));

        List<String> numList1 = new ArrayList<>();
        List<String> numList2 = new ArrayList<>();
        List<String> numList3 = new ArrayList<>();

        numList1.add("5");

        numList2.add("2");
        numList2.add("3");
        numList2.add("1");

        numList3.add("2");
        numList3.add("5");
        numList3.add("1");

        map.put("list1", numList1);
        map.put("list2", numList2);
        map.put("list3", numList3);
        map.put("compareStr", ">=");
        map.put("compareStr1", ">=");
        map.put("compareStr2", "<=");

        System.out.println(ExpressionUtil.execute(numberListCompareExpression, map));

        System.out.println(ExpressionUtil.execute(numberListEqualsOrGreaterExpression, map));*/
        //System.out.println(ExpressionUtil.execute(numberListsCompareExpression, map));

        /*map.put("num1","-2%");
        map.put("num2","-1%");
        map.put("num3","30%");
        map.put("replaceStr","%");
        System.out.println(ExpressionUtil.execute(conditionBetweenInWithoutStringExpression, map));

        System.out.println(ExpressionUtil.execute(isSyntropyExpression, map));


        map.put("condition","20220221");
        map.put("beginDate","20220221");
        map.put("endDate","20230225");

        System.out.println(ExpressionUtil.execute(getBetweenDayHasConditionExpression, map));*/
        /*map.put("condition","01");
        map.put("conditionValue","02");
        map.put("target1","01");
        map.put("targetValue1","02");
        map.put("target2","01");
        map.put("targetValue2","02");

        System.out.println(ExpressionUtil.execute(conditionValueCompareExpression, map));*/

        /*List<String> strList1 = new ArrayList<>();
        strList1.add("2");
        strList1.add("2");
        List<String> strList2 = new ArrayList<>();
        strList2.add("2");
        strList2.add("1");

        map.put("condition","2");
        map.put("conditionValue","2");
        map.put("list1",strList1);
        map.put("list2",strList2);


        System.out.println(ExpressionUtil.execute(conditionListCompareExpression, map));*/
        /*map.put("conditionList",strList2);
        map.put("aimValue","1");
        System.out.println(ExpressionUtil.execute(conditionListValueExpression, map));

        map.put("arg0", "2020-01-21");
        map.put("arg1", "2020-01");
        map.put("arg2", ">=");
        System.out.println(ExpressionUtil.execute(compareYearAndMonthValueExpression, map));




        List<Object> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("null");

        *//*List<Object> list2 = new ArrayList<>();
        list2.add("+");
        list2.add("+");
        list2.add("+");
        list2.add("/");*//*

        map.put("arg0", "1");
        map.put("list1", list1);
        map.put("list2", list2);

        System.out.println(ExpressionUtil.execute(multiAvlAddExpression, map));*/
        /*List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("id","id1");
        map1.put("ccy","CNY");
        Map<String,Object> map2 = new HashMap<>();
        map2.put("id","id2");
        map2.put("ccy","CNY");
        mapList.add(map1);
        mapList.add(map2);
        map.put("param01", "id");
        map.put("param02", "ccy");
        map.put("param03", "CNY");
        map.put("mapList", mapList);

        System.out.println(ExpressionUtil.execute(listConditionExistsExpression, map));*/

        /*List<Object> emlist1 = new ArrayList<>();
        List<Object> emlist2 = new ArrayList<>();

        emlist1.add("1");
        emlist2.add("1");
        map.put("condValue", "");
        map.put("itemCode1", "1");
        map.put("itemCode2", "2");
        map.put("list1", emlist1);
        map.put("list2", emlist2);

        System.out.println(ExpressionUtil.execute(manyDictIsNullExpression, map));

        map.put("dictCode", "AAA");
        map.put("itemCode", "000123");
        map.put("subStr", "3");
        System.out.println(ExpressionUtil.execute(isErrorDictCodeSubStrExpression, map));*/
        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> map2 = new HashMap<>();
        map1.put("prodId","aa");
        map1.put("ccy","CNY");
        map1.put("place","AAA");
        map1.put("number","1000");
        map2.put("prodId","aa");
        map2.put("ccy","BBW");
        map2.put("number","1000");
        map2.put("place","BBB");
        map.put("c1", "prodId,place");     
        map.put("tar1", "ccy");
        map.put("tar2", "CNY");
        map.put("tar3", "number");
        mapList.add(map1);
        mapList.add(map2);
        map.put("mapList",mapList);
        System.out.println(ExpressionUtil.execute(conditionSumAllEqualsExpression, map));
    }

}
