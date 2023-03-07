package com.joyintech.tams.common.svreport.controller;

import com.joyintech.framework.base.BaseController;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
/**
 * @Description 人行报送信息登记导出公共类,代码复用
 * @Date 16:17 2023/1/16
 **/
public abstract class AbstractCustomerDataExport<T> extends BaseController {




    /**
     * 文件输出流
     * @param response
     * @param fileName
     * @return
     * @throws Exception
     */
    protected OutputStream getOutputStream(HttpServletResponse response, String fileName) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        return response.getOutputStream();
    }


    /**
     *
     * @param workbook
     * @param sheetNum
     * @param sheetTitle
     * @param headers
     * @param lists
     * @throws Exception
     */
    protected  void exportExcel(XSSFWorkbook workbook, int sheetNum, String sheetTitle, String[] headers, List<T> lists) throws Exception {
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置数据列的宽度
        sheet.setDefaultColumnWidth((short) 20);

        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell((short) i);

            // 设置第一行样式
            cell.setCellStyle(getXSSFHeaderCellStyle(workbook));

            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }

        // 设置数据行样式
//        XSSFCellStyle style1 = getXSSFCellStyle(workbook);

        // 遍历集合数据，产生数据行
        if (null != lists) {
            // 从第二行开始输出数据
            handleCellData(lists,workbook,sheet);
        }
    }




    /**
     * 设置第一行样式
     * @param sxssfWorkbook
     * @return
     */
    protected  XSSFCellStyle getXSSFHeaderCellStyle(XSSFWorkbook sxssfWorkbook) {
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
     * @param sxssfWorkbook
     * @return
     */
    protected static XSSFCellStyle getXSSFCellStyle(XSSFWorkbook sxssfWorkbook) {
        XSSFCellStyle style = sxssfWorkbook.createCellStyle();
        Font font = sxssfWorkbook.createFont();
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
        return style;
    }

    /**
     * 设置数据的方法，留给继承类的子类进行实现
     * @param lists
     * @param row
     * @param style1
     * @param sheet
     */
    protected abstract void handleCellData(List<T> lists,XSSFWorkbook workbook,XSSFSheet sheet);
}
