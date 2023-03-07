package com.joyintech.tams.systemtools.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @ClassName: ToStringUtil
 * @Description: 将数据转换成字符串
 * @Author: liubaoquan
 * @Date: 2022-09-22 12:58
 */
public class ToStringUtil {

    public static String toString(BigDecimal bd) {
        if (bd == null) {
            return StringUtils.EMPTY;
        }

        return bd.toPlainString();
    }

}
