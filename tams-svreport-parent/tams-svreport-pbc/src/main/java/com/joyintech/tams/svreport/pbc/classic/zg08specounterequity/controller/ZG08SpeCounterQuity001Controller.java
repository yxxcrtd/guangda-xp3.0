package com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.controller.AbstractCustomerDataExport;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.framework.util.ExportExcelUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.service.ZG08SpecCounterQuityMService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@PageId("ZG08SpeCounterQuity")
@RequestMapping("/pbc/zg08specounterequity/ZG08SpeCounterQuity")
public class ZG08SpeCounterQuity001Controller extends AbstractCustomerDataExport<ZG08SpeCounterQuityDto> {

    @Autowired
    private ZG08SpecCounterQuityMService zg08SpecCounterQuityMService;

    @Override
    public String init(Model model) {
        Map<String, Object> map = RequestUtil.getPageParam();
        if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
            model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
            if (StringUtil.isNotEmpty(map.get("status"))) {
                model.addAttribute("status", map.get("status").toString().trim());
            }
            if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
                model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
            model.addAttribute("searchDataStart", map.get("searchDataStart"));
            model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
            model.addAttribute("isMain", "01");
        }
        super.setSelectOptionToModel(model,	DictCode.人行_产品种类,DictCode.ZG08_人行_资产负债项目, DictCode.人行报送状态);
        return super.init(model);
    }

    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ZG08SpeCounterQuityDto> searchPage(){
        Map<String,Object> searchParam = RequestUtil.getPageParam();
        Page<ZG08SpeCounterQuityDto> page = zg08SpecCounterQuityMService.searchPage(searchParam);;
        return page;
    }

    /**
     * 手工处理
     *
     * @param updateList
     * @return
     */
    @ResponseBody
    @RequestMapping("doManul")
    public AjaxResult manul(@RequestBody Map<String, Object> map) {
        zg08SpecCounterQuityMService.updateSumStaus(map);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }

    /**
     * 数据检查
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("reportCheck")
    public List<ExcelValidDto> reportCheck() {
        return zg08SpecCounterQuityMService.reportCheck(RequestUtil.getPageParam());
    }

    /**
     * 取消手工处理
     *
     * @param updateList
     * @return
     */
    @ResponseBody
    @RequestMapping("cancelManul")
    public AjaxResult cancelManul(@RequestBody Map<String, Object> map) {
        String result = zg08SpecCounterQuityMService.cancelMannul(map);
        if ("fail".equals(result)) {
            AjaxResult ajaxresult = new AjaxResult("存在未报送的数据，无法撤销", MessageType.错误消息);
            ajaxresult.setStatuCode(HttpStatus.OK.value());
            return ajaxresult;
        }
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }

    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> pedestrianExport(HttpServletResponse response) throws IOException {
        String datas = this.getParam("keyList");
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<ZG08SpeCounterQuityDto> dtoList;
        String issuePartyId =  CommonUtil.selectPbcBankCode(null);

        if (!StringUtil.isNotEmpty(datas)) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("termId",paraMap.get("termId"));
            map.put("sendStatus",paraMap.get("sendStatus"));
            dtoList = zg08SpecCounterQuityMService.searchByExcel(map);
        } else {
            List<String> list = Arrays.asList(datas.split(","));
            dtoList = zg08SpecCounterQuityMService.searchByUuid(list);
        }

        File outFile = zg08SpecCounterQuityMService.makeFile(
                "/com/joyintech/tams/svreport/pbc/classic/zg08specounterequity/conf/export_conf.xml",
                dtoList);

        String termid=null;
        if(dtoList !=null && dtoList.size()>0){
            termid=dtoList.get(0).getTermid();
        }else {
            termid=(String) paraMap.get("termId");

        }

        String date= DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(termid.length()-2)),"yyyyMMdd");

        String excelFileName = issuePartyId + GlobalConstant.UNDER_LINE + "ZG08" + GlobalConstant.UNDER_LINE
                + date
                + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        Integer exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "10" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

        // 总的list拆分
        List<List<ZG08SpeCounterQuityDto>> dtoLists = com.google.common.collect.Lists.partition(dtoList, exportCountPerSheet);


        OutputStream out = null;
        try {
            out = getOutputStream(response, excelFileName);
            String[] headers = { "产品代码","资产负债项目","交易对手产品种类","交易对手机构编码","交易对手产品代码","币种","期末金额","期末金额折人民币"};

            // 创建多个Sheet
            XSSFWorkbook workbook = new XSSFWorkbook();
            for (int i = 0; i < dtoLists.size(); i++) {
                exportExcel(workbook, i, "Sheet-" + i, headers, dtoLists.get(i));
            }

            // 写入数据，关闭流
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            throw new BusinessException("Excel导出时出现错误：" + e.getMessage());
        } finally {
            IOUtils.closeQuietly(out);
        }
        return null;
    }


    /**
     * 产品下拉框
     * @return 产品代码和名称列表
     */
    @RequestMapping(value = "getProd", method = RequestMethod.GET)
    @ResponseBody
    public List<SuggestDto> getProd(String param) {
        return zg08SpecCounterQuityMService.getProd(param);
    }

    /**
     * 产品下拉框
     * @return 产品代码和名称列表
     */
    @RequestMapping(value = "getCounterProd", method = RequestMethod.GET)
    @ResponseBody
    public List<SuggestDto> getCounterProd(String param) {
        return zg08SpecCounterQuityMService.getCounterProd(param);
    }

    @Override
    protected void handleCellData(List<ZG08SpeCounterQuityDto> lists, XSSFWorkbook workbook, XSSFSheet sheet) {
        XSSFCellStyle style1 = ExportExcelUtil.getXSSFCellStyle(workbook,"5");
        XSSFCellStyle style8 = ExportExcelUtil.getXSSFCellStyle(workbook,"8");
        XSSFCellStyle style2 = ExportExcelUtil.getXSSFCellStyle(workbook,"2");
        XSSFCellStyle stylet = ExportExcelUtil.getXSSFCellStyle(workbook,"t");
        XSSFCellStyle styleText = ExportExcelUtil.getXSSFCellStyle(workbook, "text");

        // 从第二行开始输出数据
        int index = 1;
        for (ZG08SpeCounterQuityDto m : lists) {
            XSSFRow row = sheet.createRow(index);
            // 列索引
            int cellIndex = 0;

            XSSFCell cell0 = row.createCell((short) cellIndex + 0);
            cell0.setCellStyle(styleText);
            cell0.setCellValue(m.getPbcProdId());

            XSSFCell cell1 = row.createCell((short) cellIndex + 1);
            cell1.setCellStyle(styleText);
            cell1.setCellValue(m.getAssetType());

            XSSFCell cell2 = row.createCell((short) cellIndex + 2);
            cell2.setCellStyle(styleText);
            cell2.setCellValue(m.getPrrtyProdType());

            XSSFCell cell3 = row.createCell((short) cellIndex + 3);
            cell3.setCellStyle(styleText);
            cell3.setCellValue(m.getPrrtyOrgCode());

            XSSFCell cell4 = row.createCell((short) cellIndex + 4);
            cell4.setCellStyle(styleText);
            cell4.setCellValue(m.getPrrtyProdId());

            XSSFCell cell5 = row.createCell((short) cellIndex + 5);
            cell5.setCellStyle(styleText);
            cell5.setCellValue(m.getCcy());

            XSSFCell cell6 = row.createCell((short) cellIndex + 6);
            cell6.setCellStyle(style2);
            if (!ObjectUtils.nullSafeEquals(m.getAmtStr(), "") && !ObjectUtils.nullSafeEquals(m.getAmtStr(), null)) {
                cell6.setCellValue(Double.parseDouble(m.getAmtStr()));
            }

            XSSFCell cell7 = row.createCell((short) cellIndex + 7);
            cell7.setCellStyle(style2);
            if (!ObjectUtils.nullSafeEquals(m.getAmtCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getAmtCnyStr(), null)) {
                cell7.setCellValue(Double.parseDouble(m.getAmtCnyStr()));
            }

            index++;
        }
    }
}
