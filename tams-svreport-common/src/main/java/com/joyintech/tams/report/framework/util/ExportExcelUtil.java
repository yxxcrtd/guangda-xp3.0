package com.joyintech.tams.report.framework.util;

import com.joyintech.framework.util.StringUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;

public class ExportExcelUtil {

    /**
     * 设置第一行样式
     */
    public static XSSFCellStyle getXSSFHeaderCellStyle(XSSFWorkbook sxssfWorkbook) {
        XSSFCellStyle style = sxssfWorkbook.createCellStyle();
        Font font = sxssfWorkbook.createFont();
        // 字体粗细
        font.setBold(true);
        // 将字体应用到样式上面
        style.setFont(font);
        // 是否自动换行
        style.setWrapText(false);
        // 水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        return style;
    }

    /**
     * flag 保留几位有效小数
     * 设置数据行样式
     */
    public static XSSFCellStyle getXSSFCellStyle(XSSFWorkbook workbook, String flag) {
        XSSFCellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        // 字体
        font.setFontHeightInPoints((short) 10);
        font.setFontName("宋体");
        // 将字体应用到样式上面
        style.setFont(font);
        // 是否自动换行
        style.setWrapText(false);
        // 水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        XSSFDataFormat format = workbook.createDataFormat();
        if (StringUtil.equals("5", flag)) {
            style.setDataFormat(format.getFormat("0.00000_ "));
        } else if (StringUtil.equals("2", flag)) {
            style.setDataFormat(format.getFormat("0.00_ "));
        } else if (StringUtil.equals("8", flag)) {
            style.setDataFormat(format.getFormat("0.00000000_ "));
        }
        else if (ObjectUtils.nullSafeEquals(flag, "text")) {
            style.setDataFormat(format.getFormat("@"));
        }
        return style;
    }
}
