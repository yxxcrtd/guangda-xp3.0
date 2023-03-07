package com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.framework.util.ExportExcelUtil;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author liuyu
 * @version 1.0 2019年6月17日
 * @see ZG01PbcPrdBaseinfoSubMService
 * @since 1.0
 */
@Service
public class ZG01PbcPrdBaseinfoSubMService  extends CommonExportService<ZG01PbcPrdBaseinfoSub> {
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   public Page<ZG01PbcPrdBaseinfoSub> searchPage(Map<String, Object> param) {
       return restTemplate.postForPage(
               SvrServiceApplication.PBC_SERVICE_NAME + "/ZG01PbcPrdBaseinfoSub/findPage",param,
               ZG01PbcPrdBaseinfoSub.class);
   }


    /**
     * 人行产品代码下拉框初始值
     * @param param
     *
     * @return 人行产品代码下拉框初始值
     */
    @SuppressWarnings("unchecked")
    public List<SelectOptionDto> getPbcProdId(Map<String, Object> param) {
        return restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSub/getPbcProdId", param,
                List.class);
    }

   
   public String recalReport(Map<String, Object> map){
       return restTemplate.postForObject(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSub/recalReport",map,
               String.class);
   }
   /**
    * 数据检查
    * @param map 查询条件
    * @return 检查结果
    * @see
    * @since 1.0
    */
   public List<ExcelValidDto> reportCheck(Map<String,Object> map) {
	   return restTemplate.postForList(
	       		SvrServiceApplication.PBC_SERVICE_NAME + "/ZG01PbcPrdBaseinfoSub/reportCheck", map,
	       		ExcelValidDto.class);
   }


   //用到的


    /**
     * 手工处理功能
     *
     * @return 提示信息
     * @param map
     *            条件map
     * @seea
     * @since 1.0
     */
    public void updateSumStaus(Map<String, Object> map) {
        restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG01PbcPrdBaseinfoSub/updateSumStaus",map,
                Integer.class);
    }

    /**
     * 取消手工处理功能
     *
     * @return 提示信息
     * @param map
     *            条件map
     * @seea
     * @since 1.0
     */
    public String cancelMannul(Map<String, Object> map) {
        return restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG01PbcPrdBaseinfoSub/cancelMannul",map,
                String.class);
    }


    public List<ZG01PbcPrdBaseinfoSub> searchByExcel(Map<String, Object> paraMap) {
        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG01PbcPrdBaseinfoSub/searchByExcel",paraMap,
                ZG01PbcPrdBaseinfoSub.class);
    }

    public List<ZG01PbcPrdBaseinfoSub> searchByUuid(List<String> list) {
        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG01PbcPrdBaseinfoSub/searchByUuid",list,
                ZG01PbcPrdBaseinfoSub.class);
    }

    // 导出 Excel
    public void exportExcel(XSSFWorkbook workbook, int sheetNum, String sheetTitle, String[] headers, List<ZG01PbcPrdBaseinfoSub> lists) throws Exception {
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        // 设置数据列的宽度
        sheet.setDefaultColumnWidth((short) 20);

        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell((short) i);

            // 设置第一行样式
            cell.setCellStyle(ExportExcelUtil.getXSSFHeaderCellStyle(workbook));

            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text.toString());
        }

        // 设置数据行样式
        XSSFCellStyle style1 = ExportExcelUtil.getXSSFCellStyle(workbook,"text");

        // 遍历集合数据，产生数据行
        if (null != lists) {
            // 从第二行开始输出数据
            int index = 1;
            for (ZG01PbcPrdBaseinfoSub m : lists) {
                row = sheet.createRow(index);
                // 列索引
                int cellIndex = 0;

                XSSFCell cell0 = row.createCell((short) cellIndex);
                cell0.setCellStyle(style1);
                cell0.setCellValue(m.getInfoType());

                XSSFCell cell1 = row.createCell((short) cellIndex + 1);
                cell1.setCellStyle(style1);
                cell1.setCellValue(m.getPbcProdId());

                XSSFCell cell2 = row.createCell((short) cellIndex + 2);
                cell2.setCellStyle(style1);
                cell2.setCellValue(m.getProdName());

                XSSFCell cell3 = row.createCell((short) cellIndex + 3);
                cell3.setCellStyle(style1);
                cell3.setCellValue(m.getIssuePartyId());

                XSSFCell cell4 = row.createCell((short) cellIndex + 4);
                cell4.setCellStyle(style1);
                cell4.setCellValue(m.getIssuePartyName());

                XSSFCell cell5 = row.createCell((short) cellIndex + 5);
                cell5.setCellStyle(style1);
                cell5.setCellValue(m.getPbcProdType());

                XSSFCell cell6 = row.createCell((short) cellIndex + 6);
                cell6.setCellStyle(style1);
                cell6.setCellValue(m.getIvstorientation());

                XSSFCell cell7 = row.createCell((short) cellIndex + 7);
                cell7.setCellStyle(style1);
                cell7.setCellValue(m.getProdBrand());

                XSSFCell cell8 = row.createCell((short) cellIndex + 8);
                cell8.setCellStyle(style1);
                cell8.setCellValue(m.getProdStage().toString());

                XSSFCell cell9 = row.createCell((short) cellIndex + 9);
                cell9.setCellStyle(style1);
                cell9.setCellValue(m.getProdId());

                XSSFCell cell10 = row.createCell((short) cellIndex + 10);
                cell10.setCellStyle(style1);
                cell10.setCellValue(m.getRaiseCcy());

                XSSFCell cell11 = row.createCell((short) cellIndex + 11);
                cell11.setCellStyle(style1);
                cell11.setCellValue(m.getCashPrinCcy());

                XSSFCell cell12 = row.createCell((short) cellIndex + 12);
                cell12.setCellStyle(style1);
                cell12.setCellValue(m.getCashProfCcy());

                XSSFCell cell13 = row.createCell((short) cellIndex + 13);
                cell13.setCellStyle(style1);
                cell13.setCellValue(m.getRaiseType());

                XSSFCell cell14 = row.createCell((short) cellIndex + 14);
                cell14.setCellStyle(style1);
                cell14.setCellValue(m.getManageType());

                XSSFCell cell15 = row.createCell((short) cellIndex + 15);
                cell15.setCellStyle(style1);
                cell15.setCellValue(m.getOperateMode());

                XSSFCell cell16 = row.createCell((short) cellIndex + 16);
                cell16.setCellStyle(style1);
                cell16.setCellValue(m.getRaiseVdateStr());

                XSSFCell cell17 = row.createCell((short) cellIndex + 17);
                cell17.setCellStyle(style1);
                cell17.setCellValue(m.getRaiseEnddateStr());

                XSSFCell cell18 = row.createCell((short) cellIndex + 18);
                cell18.setCellStyle(style1);
                cell18.setCellValue(m.getTermRightFlag());

                XSSFCell cell19 = row.createCell((short) cellIndex + 19);
                cell19.setCellStyle(style1);
                cell19.setCellValue(m.getCusRedeemFlag());

                XSSFCell cell20 = row.createCell((short) cellIndex + 20);
                cell20.setCellStyle(style1);
                cell20.setCellValue(m.getCreditFlag());

                XSSFCell cell21 = row.createCell((short) cellIndex + 21);
                cell21.setCellStyle(style1);
                cell21.setCellValue(m.getCreditType());

                XSSFCell cell22 = row.createCell((short) cellIndex + 22);
                cell22.setCellStyle(style1);
                cell22.setCellValue(m.getCreditMode());

                XSSFCell cell23 = row.createCell((short) cellIndex + 23);
                cell23.setCellStyle(style1);
                cell23.setCellValue(m.getInDepCode());

                XSSFCell cell24 = row.createCell((short) cellIndex + 24);
                cell24.setCellStyle(style1);
                cell24.setCellValue(m.getOutDepCtry());

                XSSFCell cell25 = row.createCell((short) cellIndex + 25);
                cell25.setCellStyle(style1);
                cell25.setCellValue(m.getOutDepName());

                XSSFCell cell26 = row.createCell((short) cellIndex + 26);
                cell26.setCellStyle(style1);
                cell26.setCellValue(m.getOutDepName());

                XSSFCell cell27 = row.createCell((short) cellIndex + 27);
                cell27.setCellStyle(style1);
                cell27.setCellValue(m.getVdateStr());

                XSSFCell cell28 = row.createCell((short) cellIndex + 28);
                cell28.setCellStyle(style1);
                cell28.setCellValue(m.getCdateStr());

                XSSFCell cell29 = row.createCell((short) cellIndex + 29);
                cell29.setCellStyle(style1);
                cell29.setCellValue(m.getMdateStr());

                XSSFCell cell30 = row.createCell((short) cellIndex + 30);
                cell30.setCellStyle(style1);
                cell30.setCellValue(m.getFiduciaryDuty());

                XSSFCell cell31 = row.createCell((short) cellIndex + 31);
                cell31.setCellStyle(style1);
                cell31.setCellValue(m.getIsConstracte());

                XSSFCell cell32 = row.createCell((short) cellIndex + 32);
                cell32.setCellStyle(style1);
                cell32.setCellValue(m.getTransferType());

                XSSFCell cell33 = row.createCell((short) cellIndex + 33);
                cell33.setCellStyle(style1);
                cell33.setCellValue(m.getCashManaCate());

                XSSFCell cell34 = row.createCell((short) cellIndex + 34);
                cell34.setCellStyle(style1);
                cell34.setCellValue(m.getCrossBorderFlag());

                XSSFCell cell35 = row.createCell((short) cellIndex + 35);
                cell35.setCellStyle(style1);
                cell35.setCellValue(m.getIsPropertyFlag());

                XSSFCell cell36 = row.createCell((short) cellIndex + 36);
                cell36.setCellStyle(style1);
                cell36.setCellValue(m.getBaseInfoSign());

                XSSFCell cell37 = row.createCell((short) cellIndex + 37);
                cell37.setCellStyle(style1);
                cell37.setCellValue(m.getChangeReason());

                XSSFCell cell38 = row.createCell((short) cellIndex + 38);
                cell38.setCellStyle(style1);
                cell38.setCellValue(m.getBackup1());

                XSSFCell cell39 = row.createCell((short) cellIndex + 39);
                cell39.setCellStyle(style1);
                cell39.setCellValue(m.getBackup1());

                XSSFCell cell40 = row.createCell((short) cellIndex + 40);
                cell40.setCellStyle(style1);
                cell40.setCellValue(m.getBackup1());

                XSSFCell cell41 = row.createCell((short) cellIndex + 41);
                cell41.setCellStyle(style1);
                cell41.setCellValue(m.getBackup1());

                XSSFCell cell42 = row.createCell((short) cellIndex + 42);
                cell42.setCellStyle(style1);
                cell42.setCellValue(m.getBackup1());

                index++;
            }
        }
    }
}
