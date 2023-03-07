package com.joyintech.tams.common.svreport.datacheck.beforeinsert.util;

import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 时间日期表达式
 */
public class DateExpression extends DateUtils {

    // 日期1 < 日期2，返回：-1; 0:相等; 日期1 > 日期2，返回：1;
    public static int compareTo(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

    // 比较2个字符串日期
    public static int compareToStringDate(String date1, String date2) {
        return date1.compareTo(date2);
    }

    // 判断一个字符串是不是有效的日期
    public static boolean isValidDate(String arg0) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = sdf.parse(arg0);
            return arg0.equals(sdf.format(date));
        } catch (ParseException e) {
            try {
                Date date = sdf2.parse(arg0);
                boolean equals = arg0.equals(sdf2.format(date));
                return equals;
            }catch (ParseException e2){
                return false;
            }
        }
    }

    // 判断参数日期与当前系统日期对比（日期格式:yyyy-MM-dd）
    public static Map<String, Object> compareWithSysdate(Object arg0, String flag) {
        String str = "";
        if (!StringUtil.isNotEmpty(arg0)) {
             return ExpressionUtil.resultMap(true, "arg0是空值");
        }
        if (StringUtils.isEmpty(flag)) {
            return ExpressionUtil.resultMap(false, "运算符不能为空");
        }
        if (arg0 instanceof Date){
            str = DateUtil.format((Date) arg0);
        } else {
            str = String.valueOf(arg0);
        }
        // 业务比较
        Date curDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String data = str;

        try {
            Date arg0Date = sdf.parse(str);

            // 大于
            if (">".equals(flag) &&  1 == compareTo(arg0Date, curDate)) {
                return ExpressionUtil.resultMap(true, data);
            }

            // 等于
            if ("=".equals(flag) &&  0 == compareTo(arg0Date, curDate)) {
                return ExpressionUtil.resultMap(true, data);
            }

            // 小于
            if ("<".equals(flag) &&  -1 == compareTo(arg0Date, curDate)) {
                return ExpressionUtil.resultMap(true, data);
            }

            // 大于等于
            if (">=".equals(flag) &&  -1 != compareTo(arg0Date, curDate)) {
                return ExpressionUtil.resultMap(true, data);
            }

            // 小于等于
            if ("<=".equals(flag) &&  1 != compareTo(arg0Date, curDate)) {
                return ExpressionUtil.resultMap(true, data);
            }
        } catch (ParseException e) {
            return ExpressionUtil.resultMap(false, "日期参数格式不正确");
        }
        return ExpressionUtil.resultMap(false, data);
    }

    // 日期格式必须为：YYYY-MM-DD 或 YYYY/MM/DD，范围在1800-01-01到2100-12-31之间
    public static Map<String, Object> betweenDate(Object date) {
        //null或空值放行
        if (ObjectUtils.nullSafeEquals(date, null)) {
            return ExpressionUtil.resultMap(true, "date值为空");
        }

        if (StringUtils.isEmpty(date.toString())) {
            return ExpressionUtil.resultMap(true, "date值为空");
        }

        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        map.put("flag", flag);
        map.put("data", date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date1 = sdf.parse("1800-01-01");
            Date date2 = sdf.parse("2100-12-31");

            String target = null;
            Date parseDate = null;
            if (date instanceof Date) {
                target = sdf.format(date);
                map.put("flag", isValidDate(sdf.format(date)) && (date1.before(sdf.parse(target)) || date1.equals(sdf.parse(target)))&& (sdf.parse(target).before(date2)||sdf.parse(target).equals(date2)));
            } else {
                try {
                    parseDate = sdf.parse(String.valueOf(date));
                } catch (Exception e) {
                    parseDate = sdf2.parse(String.valueOf(date));
                }
                map.put("flag", isValidDate(String.valueOf(date)) && (date1.before(parseDate)||date1.equals(parseDate)) && (parseDate.before(date2)||date2.equals(parseDate)));
            }

        } catch (ParseException e) {
            flag = false;
            //throw new RuntimeException(e);
            map.put("flag",flag);
            map.put("data",date);
            return map;
        }
        return map;
    }

    /**
     * 根据两个日期，取得相隔的天数。日期格式：yyyy-MM-dd，计算是否超过指定天数，超过返回true，小于返回false
     *
     * @param date1 开始日期
     * @param date2 结束日期
     * @return 相隔的天数
     */
    public static Map<String, Object> getBetweenDayNumber(Object date1, Object date2, Object days) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        long betweenDays = 0;
        if (null == date2) {
            return ExpressionUtil.resultMap(true, "date2 为空");
        }
        if (null == date1) {
            return ExpressionUtil.resultMap(true, "date1 为空");
        }
        String str1 = "";
        String str2 = "";
        // 结束日期
        if (date2 instanceof Date) {
            str2 = DateUtil.format((Date) date2);
        } else {
            str2 = date2.toString();
        }
        // 开始日期
        if (date1 instanceof Date) {
            str1 = DateUtil.format((Date) date1);
        } else {
            str1 = date1.toString();
        }
        if (StringUtil.isNotEmpty(str1) && StringUtil.isNotEmpty(str2)) {
            betweenDays = getBetweenDate(str1, str2, DateUtil.DurationType.天);
            int newDays = Integer.parseInt(days.toString());
            flag = betweenDays - newDays >= 0;
        } else {
            flag = true;
        }
        return ExpressionUtil.resultMap(flag, betweenDays);
    }

    public static long getBetweenDate(String begin, String end, DateUtil.DurationType type) {
        // 根据兩個个日期，取得相隔的天数
        LocalDateTime beginDate = dateToLocalDate(DateUtil.parse(begin));
        LocalDateTime endDate = dateToLocalDate(DateUtil.parse(end));
        // 计算区间天数
        long result = -1;
        switch (type) {
            case 秒:
                result = ChronoUnit.SECONDS.between(beginDate, endDate);
                break;
            case 分钟:
                result = ChronoUnit.MINUTES.between(beginDate, endDate);
                break;
            case 小时:
                result = ChronoUnit.HOURS.between(beginDate, endDate);
                break;
            case 天:
                result = ChronoUnit.DAYS.between(beginDate, endDate);
                break;
            case 月:
                result = ChronoUnit.MONTHS.between(beginDate, endDate);
                break;
            case 年:
                result = ChronoUnit.YEARS.between(beginDate, endDate);
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * java.util.Date 转为 java.time.LocalDateTime <br>
     * 日期装java8日期对象
     * @param date 日期
     * @return LocalDate
     */
    private static LocalDateTime dateToLocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public enum DurationType {
        /**
         *
         */
        秒,
        /**
         *
         */
        分钟,
        /**
         *
         */
        小时,
        /**
         *
         */
        天,
        /**
         *
         */
        月,
        /**
         *
         */
        年;
    }

    // 获取某一个月的最后一天（注意日期的参数格式是：yyyy-MM）
//    public static Map<String, Object> getLastDayOfMonth(String yearAndMonth) {
////    public static Map<String, Object> getBetweenDayHasCondition(String condition,String beginDate,String endDate) {
//        Map<String, Object> map = new HashMap<>();
//        boolean flag = true;
//        map.put("data", condition + ","+ beginDate + "," + endDate);
//        try {
//            if (condition != null){
//                if (DateUtil.getBetweenDayNumber(DateUtil.parse(beginDate),DateUtil.parse(endDate)) <= 90){
//                    flag = false;
//                }
//            }else{
//                flag = false;
//            }
//            map.put("flag", flag);
//        } catch (Exception e) {
//            map.put("flag", false);
//        }
//        return map;
//    }

    // 指定日期与某月的最后一天比较（注意日期的参数格式是：yyyy-MM）
    public static Map<String, Object> compareYearAndMonth(String arg0, String yearAndMonth, String flg) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        map.put("flag", flag);
        map.put("data", yearAndMonth);
        String wrongFormat = yearAndMonth + "不是正确的yyyy-MM格式！";
        String lastDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date date = sdf.parse(yearAndMonth);
            if (!yearAndMonth.equals(sdf.format(date))) {
                map.put("data", wrongFormat);
                return map;
            }
            LocalDate localDate = LocalDate.parse(yearAndMonth + "-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
            lastDateString = lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // 转日期类型
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf2.parse(arg0);
            Date date2 = sdf2.parse(lastDateString);

            // 大于
            if (">".equals(flg)) {
                map.put("flag", 1 == compareTo(date1, date2) ? true : false);
            }

            // 等于
            if ("=".equals(flg)) {
                map.put("flag", 0 == compareTo(date1, date2) ? true : false);
            }

            // 小于
            if ("<".equals(flg)) {
                map.put("flag", -1 == compareTo(date1, date2) ? true : false);
            }

            // 大于等于
            if (">=".equals(flg)) {
                map.put("flag", -1 != compareTo(date1, date2) ? true : false);
            }

            // 小于等于
            if ("<=".equals(flg)) {
                map.put("flag", 1 != compareTo(date1, date2) ? true : false);
            }
            map.put("data", lastDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        } catch (ParseException e) {
            map.put("flag", false);
            map.put("data", wrongFormat);
        }

        map.put("data", "参数日期:" + arg0 + " " + flg  + " 特定日期:" + lastDateString);
        return map;
    }

    // 日期1 < 日期2，返回：-1; 0:相等; 日期1 > 日期2，返回：1;
    // 获取某一个月的最后一天（注意日期的参数格式是：yyyy-MM）
    public static Map<String, Object> getBetweenDayHasCondition(String condition,String beginDate,String endDate) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        map.put("data", condition + ","+ beginDate + "," + endDate);
        try {
            if (condition != null){
                if (DateUtil.getBetweenDayNumber(DateUtil.parse(beginDate),DateUtil.parse(endDate)) <= 90){
                    flag = false;
                }
            }else{
                flag = false;
            }
            map.put("flag", flag);
        } catch (Exception e) {
            map.put("flag", false);
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(dateCompare("2020-01-01", "2020-01-02"));
    }

    // 比较两个日期的大小。date2 > date1 返回true，否则返回false
    public static Map<String, Object> dateCompare(Object date1, Object date2) {
        if (null == date1 || null == date2) {
            return ExpressionUtil.resultMap(true, "date1或data2为空了");
        }
        Date date1Date = null;
        Date date2Date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            try {
                if (date1 instanceof Date) {
                    date1Date = (Date) date1;
                } else {
                    date1Date = sdf.parse(String.valueOf(date1));
                }
            } catch (Exception e) {
                date1Date = sdf.parse(String.valueOf(date1));
            }
            try {
                if (date2 instanceof Date) {
                    date2Date = (Date) date2;
                } else {
                    date2Date = sdf.parse(String.valueOf(date2));
                }
            } catch (Exception e) {
                date2Date = sdf.parse(String.valueOf(date1));
            }
            return ExpressionUtil.resultMap(DateUtil.compareDate(date2Date, date1Date) >= 0,  String.valueOf(date2));

        } catch (Exception e) {
        }
        return ExpressionUtil.resultMap(false, sdf.format(date2));
    }

    //当满足条件时，date1>=date2返回true，否则返回false
    public static Map<String, Object> dateCompareByParam(Object listStr, String str2, Object date1, Object date2) {
        if (ObjectUtils.nullSafeEquals(str2, null)) {
            return ExpressionUtil.resultMap(true, "str2参数为空");
        }
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = "";
        String strDate2 = "";
        try {
            if (date1 instanceof Date){
                strDate1 = sdf.format((Date) date1);
            } else {
                strDate1 = String.valueOf(date1);
            }

            if (date2 instanceof Date){
                strDate2 = sdf.format((Date) date2);
            } else {
                strDate2 = String.valueOf(date2);
            }

//            Date newdate1 = sdf.parse(strDate1);
//            Date newdate2 = sdf.parse(strDate2);
            // 前置条件
            if(listStr.equals(str2)){
                if (DateUtil.compareDate(strDate1, strDate2) != -1) {
                    flag = true;
                }
            } else {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", strDate1);
        return map;
    }

    //当 obj1 = obj2 满足条件时，date1 > date2返回true，否则返回false
    public static Map<String, Object> dateCompareByParam2(Object obj1, Object obj2, Object date1, Object date2) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String strTarget1 = String.valueOf(obj1);
        String strTarget2 = String.valueOf(obj2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = "";
        String strDate2 = "";
        try {
            if (date1 instanceof Date){
                strDate1 = sdf.format((Date) date1);
            } else {
                strDate1 = String.valueOf(date1);
            }

            if (date2 instanceof Date){
                strDate2 = sdf.format((Date) date2);
            } else {
                strDate2 = String.valueOf(date2);
            }
            // obj1 = obj2 前置条件不满足 返回 true
            if(!StringUtil.equals(strTarget1, strTarget2)){
                flag = true;
            } else {
                if (DateUtil.compareDate(strDate1, strDate2) > 0){
                    flag = true;
                }
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", date2);
        return map;
    }

    //date1<=date2 返回true，否则返回false
    public static Map<String, Object> dateCompareTo(Object date1, Object date2) {
        if (!StringUtil.isNotEmpty(date1) || !StringUtil.isNotEmpty(date2)) {
            return ExpressionUtil.resultMap(false, "date1 或 date2 参数为空！");
        }
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tarDate1 = "";
        String tarDate2 = "";
        try {
            if (date1 instanceof Date){
                tarDate1 = sdf.format((Date) date1);
            } else {
                tarDate1 = String.valueOf(date1);
            }

            if (date2 instanceof Date){
                tarDate2 = sdf.format((Date) date2);
            } else {
                tarDate2 = String.valueOf(date2);
            }

            Date newdate1 = sdf.parse(tarDate1);
            Date newdate2 = sdf.parse(tarDate2);
            if(compareTo(newdate1,newdate2) != 1){
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", tarDate1);
        return map;
    }

    //date1>=date2 返回true，否则返回false
    public static Map<String, Object> dateCompareTo2(Object date1, Object date2) {
        if (!StringUtil.isNotEmpty(date1) || !StringUtil.isNotEmpty(date2)) {
            return ExpressionUtil.resultMap(false, "date1 或 date2 参数为空！");
        }

        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tarDate1 = "";
        String tarDate2 = "";
        try {

            if (date1 instanceof Date){
                tarDate1 = sdf.format((Date) date1);
            } else {
                tarDate1 = String.valueOf(date1);
            }

            if (date2 instanceof Date){
                tarDate2 = sdf.format((Date) date2);
            } else {
                tarDate2 = String.valueOf(date2);
            }

            Date newdate1 = sdf.parse(tarDate1);
            Date newdate2 = sdf.parse(tarDate2);
            if(compareTo(newdate1,newdate2) != -1){
                flag = true;
            }
        } catch (ParseException e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", tarDate1);
        return map;
    }

    //DS06086 特殊处理，日期为空不校验
    public static Map<String, Object> dateCompareTo3(Object date1, Object date2) {
        if (!StringUtil.isNotEmpty(date1) || !StringUtil.isNotEmpty(date2)) {
            return ExpressionUtil.resultMap(true, "date1 或 date2 参数为空！");
        }

        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tarDate1 = "";
        String tarDate2 = "";
        try {

            if (date1 instanceof Date){
                tarDate1 = sdf.format((Date) date1);
            } else {
                tarDate1 = String.valueOf(date1);
            }

            if (date2 instanceof Date){
                tarDate2 = sdf.format((Date) date2);
            } else {
                tarDate2 = String.valueOf(date2);
            }

            Date newdate1 = sdf.parse(tarDate1);
            Date newdate2 = sdf.parse(tarDate2);
            if(compareTo(newdate1,newdate2) != -1){
                flag = true;
            }
        } catch (ParseException e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", tarDate1);
        return map;
    }

    //比较日期大小 date1>date2 返回true
    public static Map<String, Object> dateCompareBig(String date1, String date2) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newdate1 = sdf.parse(date1);
            Date newdate2 = sdf.parse(date2);
            if(compareTo(newdate1,newdate2) == 1){
                flag = true;
            }
        } catch (ParseException e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", date2);
        return map;
    }

    //比较日期大小 date1<date2 返回true
    public static Map<String, Object> dateCompareSmall(String date1, String date2) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newdate1 = sdf.parse(date1);
            Date newdate2 = sdf.parse(date2);
            if(compareTo(newdate1,newdate2) == -1){
                flag = true;
            }
        } catch (ParseException e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", date2);
        return map;
    }

    //判断两个日期是否相等 相等返回true
    public static Map<String, Object> dateCompareEq(String date1, String date2) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newdate1 = sdf.parse(date1);
            Date newdate2 = sdf.parse(date2);
            if(compareTo(newdate1,newdate2) == 0){
                flag = true;
            }
        } catch (ParseException e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", date2);
        return map;
    }

    //当结束日期不为空时，date1<=date2 返回true
    public static Map<String, Object> dateCompareIfNotEmpty(Object date1, Object date2) {
        Map<String, Object> map = new HashMap<>();
        boolean flag = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newdate1 = null;
        Date newdate2 = null;
        if ( null == date1){
            return ExpressionUtil.resultMap(true, "date1 为空");
        }
        if ( null == date2){
            return ExpressionUtil.resultMap(true, "date2 为空");
        }
        try {
            if (date1 instanceof Date) {
                newdate1 = (Date) date1;
            } else {
                newdate1 = sdf.parse(date1.toString());
            }
            if (date2 instanceof Date) {
                newdate2 = (Date) date2;
            } else {
                newdate2 = sdf.parse(date2.toString());
            }
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false, "");
        }

        try {
            if (compareTo(newdate1, newdate2) == 1) {
                flag = false;
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", date2);
        return map;
    }
    //比较日期大小 date1<date2 返回true
    public static Map<String, Object> dateCompareSmallsql(Object date1, Object date2,Object str3,Object str4,Object str5) {
        Date newdate1 = null;
        Date newdate2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if ( null == date1){
            return ExpressionUtil.resultMap(true, "date1 为空");
        }
        if ( null == date2){
            return ExpressionUtil.resultMap(true, "date2 为空");
        }
        Map<String, Object> map = new HashMap<>();
        try {
            if (date1 instanceof Date) {
                newdate1 = (Date) date1;
            } else {
                String newdate1Str=EnumerateExpression.sqlValueOne(String.valueOf(date1),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
                if(ObjectUtils.isEmpty(newdate1Str)){
                    map.put("flag", true);
                    return map;
                }
                newdate1 = sdf.parse(newdate1Str);
            }
            if (date2 instanceof Date) {
                newdate2 = (Date) date2;
            } else {
                String newdate2Str=EnumerateExpression.sqlValueOne(String.valueOf(date2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
                if(ObjectUtils.isEmpty(newdate2Str)){
                    map.put("flag", true);
                    return map;
                }
                newdate2 = sdf.parse(newdate2Str);
            }
        } catch (Exception e) {
            return ExpressionUtil.resultMap(false, "");
        }

        boolean flag = false;
        try {
            if(compareTo(newdate1,newdate2) == -1){
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", sdf.format(newdate2));
        return map;
    }

    //date1>=date2 返回true，否则返回false
    public static Map<String, Object> dateCompareTosql(Object date1, Object date2,Object str3,Object str4,Object str5) {
        Date newdate1 = null;
        String  target1 = EnumerateExpression.sqlValueOne(String.valueOf(date2),String.valueOf(str3),String.valueOf(str4),String.valueOf(str5));
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        if("".equals(target1)){
            flag = true;
            map.put("flag", flag);
            map.put("data", target1);
            return map;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (date1 instanceof Date) {
                newdate1 = (Date) date1;
            } else {
                newdate1 = sdf.parse(date1.toString());
            }
//             newdate1 = sdf.parse(String.valueOf(date1));
            Date newdate2 = sdf.parse(target1);
            if(compareTo(newdate1,newdate2) >= 0){
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", target1);
        return map;
    }

    /**
     * date1 > date2 返回true，否则返回false
     * @param date1
     * @param date2
     * @param dateFormat 比较的日期格式
     * @return
     */
    public static Map<String, Object> dateCompareGreaterTo(Object date1, Object date2, String dateFormat) {
        if (!StringUtil.isNotEmpty(date1) || !StringUtil.isNotEmpty(date2)) {
            return ExpressionUtil.resultMap(true, "date1 或 date2 参数为空！");
        }

        if (StringUtil.isEmpty(dateFormat)) {
            return ExpressionUtil.resultMap(false, "脚本配置错误");
        }
        // 返回结果
        String result = "";
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        String tarDate2 = "";
        try {
            tarDate2 = String.valueOf(date2);
            String ttdate= com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(tarDate2.substring(0,4)),Integer.parseInt(tarDate2.substring(tarDate2.length()-2)),"yyyyMMdd");
            Date newdate1 = (Date) date1;
            Date newdate2=DateUtil.parse(ttdate);
            if(newdate1.compareTo(newdate2)>0){
                flag = true;
            }
            result=DateUtil.format(newdate1);
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", result);
        return map;
    }

    /**
     * date1 <= date2 返回true，否则返回false
     * @param date1
     * @param date2
     * @param dateFormat 比较的日期格式
     * @return
     */
    public static Map<String, Object> dateCompareLessTo(Object date1, Object date2, String dateFormat) {
        if (!StringUtil.isNotEmpty(date1) || !StringUtil.isNotEmpty(date2)) {
            return ExpressionUtil.resultMap(true, "date1 或 date2 参数为空！");
        }

        if (StringUtil.isEmpty(dateFormat)) {
            return ExpressionUtil.resultMap(false, "脚本配置错误");
        }

        // 返回结果
        String result = "";
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String tarDate1 = "";
        String tarDate2 = "";
        try {
            if (date1 instanceof Date){
                tarDate1 = sdf.format((Date) date1);
                result = DateUtil.format((Date) date1);
            } else {
                tarDate1 = String.valueOf(date1);
                result = String.valueOf(date1);
            }

            if (date2 instanceof Date){
                tarDate2 = sdf.format((Date) date2);
            } else {
                tarDate2 = String.valueOf(date2);
            }

            Date newdate1 = sdf.parse(tarDate1);
            Date newdate2 = sdf.parse(tarDate2);
            if(compareTo(newdate1,newdate2) != 1){
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", result);
        return map;
    }

    public static Map<String, Object> compareValueByCondSql(Object conditionParam001, String conditionParam02, Object date1, Object param01,Object pbcProdId,Object termid,Object loanBasisId) {
        if (ObjectUtils.nullSafeEquals(conditionParam02, null)) {
            return ExpressionUtil.resultMap(true, "conditionParam02参数为空");
        }
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = "";
        String strDate2 = "";
        try {
            if (date1 instanceof Date){
                strDate1 = sdf.format((Date) date1);
            } else {
                strDate1 = String.valueOf(date1);
            }

            strDate2 = EnumerateExpression.sqlValueOne2(String.valueOf(param01),String.valueOf(pbcProdId),String.valueOf(termid),String.valueOf(loanBasisId));
            if (ObjectUtils.isEmpty(strDate2)) {
                map.put("flag", true);
                return map;
            }
            // 前置条件
            if(conditionParam001.equals(conditionParam02)){
                if (DateUtil.compareDate(strDate2, strDate1) != -1) {
                    flag = true;
                }
            } else {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", strDate2);
        return map;
    }

        public  static Date getLastDayOfMonth(int year,int month) throws ParseException {
        Calendar cale = Calendar.getInstance();

        cale.set(Calendar.YEAR,year);//赋值年份
        cale.set(Calendar.MONTH, month);//赋值月份
        int lastDay = cale.getActualMaximum(Calendar.DAY_OF_MONTH);//获取月最大天数
        cale.set(Calendar.DAY_OF_MONTH, lastDay);//设置日历中月份的最大天数
        Date date=    cale.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(date);
        return sdf.parse(str);
    }
    //当满足条件时，date1>=date2返回true，否则返回false
    public static Map<String, Object> dateCompareByParam1(Object listStr, String str2, Object date1, Object date2,String dateFormat) {
        if (ObjectUtils.nullSafeEquals(str2, null)) {
            return ExpressionUtil.resultMap(true, "str2参数为空");
        }
        Map<String, Object> map = new HashMap<>();
        boolean flag = false;
        try {
            // 前置条件
            if(listStr.equals(str2)){
                return dateCompareGreaterTo1(date1,date2,dateFormat);
            } else {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", String.valueOf(date1));
        return map;
    }


    /**
     * date1 >= date2 返回true，否则返回false
     * @param date1
     * @param date2
     * @param dateFormat 比较的日期格式
     * @return
     */
    public static Map<String, Object> dateCompareGreaterTo1(Object date1, Object date2, String dateFormat) {
        if (!StringUtil.isNotEmpty(date1) || !StringUtil.isNotEmpty(date2)) {
            return ExpressionUtil.resultMap(true, "date1 或 date2 参数为空！");
        }

        if (StringUtil.isEmpty(dateFormat)) {
            return ExpressionUtil.resultMap(false, "脚本配置错误");
        }
        // 返回结果
        String result = "";
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        boolean flag = false;
        String tarDate1 = "";
        String tarDate2 = "";
        try {

            if (date1 instanceof Date){
                tarDate1 = sdf.format((Date) date1);
                result = DateUtil.format((Date) date1);
            } else {
                tarDate1 = String.valueOf(date1);
                result = String.valueOf(date1);
            }

            if (date2 instanceof Date){
                tarDate2 = sdf.format((Date) date2);
            } else {
                tarDate2 = String.valueOf(date2);
            }

            Date newdate1 = sdf.parse(tarDate1);
            Date newdate2 = sdf.parse(tarDate2);
            if(newdate1.compareTo(newdate2)>=0){
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }

        map.put("flag", flag);
        map.put("data", result);
        return map;
    }
}
