package com.joyintech.tams.svreport.pbc.classic.exportzgutil;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;

public final class ExportZgUtil {

    // 文件输出流
    public static OutputStream getOutputStream(HttpServletResponse response, String fileName) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        return response.getOutputStream();
    }

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
     * 设置数据行样式
     */
    public static XSSFCellStyle getXSSFCellStyle(XSSFWorkbook workbook) {
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
        style.setDataFormat(format.getFormat("0.00000_ "));
        return style;
    }
}
