package com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.controller.AbstractCustomerDataExport;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.framework.util.ExportExcelUtil;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dto.ZG02PbcPrdInitRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.serivce.ZG02PbcPrdInitRaiseSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.service.ZG02PbcPrdInitRaiseSubMService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.*;

/**
 * <p>人行报送信息登记-ZG02产品初始募集登记Controller</p>
 *
 * @author liuyu
 * @创建时间 2019年6月4日 下午4:57:28
 */
@Controller
@PageId("ZG02PbcPrdInitRaiseSub")
@RequestMapping("/pbc/zg02prdinitraisesub/ZG02PbcPrdInitRaiseSub")
public class ZG02PbcPrdInitRaiseSub001Controller extends AbstractCustomerDataExport<ZG02PbcPrdInitRaiseSub> {
    /**
     * 产品初始募集登记service
     */
    @Autowired
    private ZG02PbcPrdInitRaiseSubService001 zg02PbcPrdInitRaiseSubService001;
    @Autowired
    private ZG02PbcPrdInitRaiseSubMService zg02PbcPrdInitRaiseSubMService;

    /**
     * 页面初始化
     */
    @Override
    public String init(Model model) {
        Map<String, Object> map = RequestUtil.getPageParam();
        if (StringUtil.isNotEmpty(map.get("submitStatus"))) {
            model.addAttribute("submitStatus", map.get("submitStatus").toString().trim());
            if (StringUtil.isNotEmpty(map.get("status"))) {
                model.addAttribute("status", map.get("status").toString().trim());
            }
            if (StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))) {
                model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
            model.addAttribute("searchDataStart", map.get("searchDataStart"));
            model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
            model.addAttribute("isMain", "01");
        }
        super.setSelectOptionToModel(model, DictCode.报送数据状态, DictCode.报送状态, DictCode.人行报送反馈状态, DictCode.人行产品类型, DictCode.人行产品运作模式);
        return super.init(model);
    }

    /**
     * 产品终止登记查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ZG02PbcPrdInitRaiseSub> searchPage() {
        Map<String, Object> searchParam = RequestUtil.getPageParam();
        Page<ZG02PbcPrdInitRaiseSub> page = zg02PbcPrdInitRaiseSubMService.searchPage(searchParam);
        ;
        return page;

    }

    /**
     * 导出文件
     *
     * @return 导出的excel
     * @see
     * @since 1.6.0
     */
    @ResponseBody
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export(HttpServletResponse response) {
        List<ZG02PbcPrdInitRaiseSub> dtoList = zg02PbcPrdInitRaiseSubService001.setDataList(RequestUtil.getParameters(getRequest()));
        String issuePartyId = CommonUtil.selectPbcBankCode(null);
        Date maxDate = new Date();
        List<Date> dateList = new ArrayList<>();
        if (!StringUtil.isEmptyList(dtoList) && dtoList.size() > 0) {
            dtoList.forEach(
                    item -> {
                        item.getStatusDate();
                        dateList.add(item.getStatusDate());
                    }
            );
            maxDate = Collections.max(dateList);
        } else {
            maxDate = new Date();
        }
        File outFile = zg02PbcPrdInitRaiseSubService001.makeFile(
                "/com/joyintech/tams/svreport/pbc/classic/zg02prdinitraisesub/conf/export_conf.xml", dtoList);

        String excelFileName = issuePartyId + GlobalConstant.UNDER_LINE + "ZG02" + GlobalConstant.UNDER_LINE
                + DateUtil.format(maxDate, "yyyyMMdd")
                + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        Integer exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "20" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

        // 总的list拆分
        List<List<ZG02PbcPrdInitRaiseSub>> dtoLists = Lists.partition(dtoList, exportCountPerSheet);

        OutputStream out = null;
        try {
            out = getOutputStream(response, excelFileName);
            String[] headers = {"产品代码", "币种", "地区", "客户类型", "初始募集金额", "初始募集金额折人民币", "初始募集份额"};

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
//		return ExportUtil.export(issuePartyId+GlobalConstant.UNDER_LINE+"ZG02"+GlobalConstant.UNDER_LINE
//						+ DateUtil.format(maxDate,"yyyyMMdd")
//						+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//				outFile, true);

    }

    /**
     * 手工处理
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("doManul")
    public AjaxResult manul(@RequestBody Map<String, Object> map) {
        zg02PbcPrdInitRaiseSubMService.updateSumStaus(map);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }

    /**
     * 取消手工处理
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("cancelManul")
    public AjaxResult cancelManul(@RequestBody Map<String, Object> map) {
        String result = zg02PbcPrdInitRaiseSubMService.cancelMannul(map);
        if ("fail".equals(result)) {
            AjaxResult ajaxresult = new AjaxResult("有未报送的数据", MessageType.错误消息);
            ajaxresult.setStatuCode(HttpStatus.OK.value());
            return ajaxresult;
        }
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }

    /**
     * 数据检查
     *
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("reportCheck")
    public List<ExcelValidDto> reportCheck() {
        return zg02PbcPrdInitRaiseSubMService.reportCheck(RequestUtil.getPageParam());
    }

    /**
     * 人行产品代码下拉框
     */
    @RequestMapping("/getPbcProdId")
    @ResponseBody
    public List<SelectOptionDto> getPbcProdId() {
        return zg02PbcPrdInitRaiseSubMService.getPbcProdId(RequestUtil.getPageParam());
    }

    /**
     * 人行产品代码下拉框
     */
    @RequestMapping("/getProdID")
    @ResponseBody
    public List<SelectOptionDto> getProdID() {
        return zg02PbcPrdInitRaiseSubMService.getProdId(RequestUtil.getPageParam());
    }

    @Override
    protected void handleCellData(List<ZG02PbcPrdInitRaiseSub> lists, XSSFWorkbook workbook, XSSFSheet sheet) {
        // 从第二行开始输出数据
        // 设置数据行样式

        XSSFCellStyle style1 = ExportExcelUtil.getXSSFCellStyle(workbook, "5");
        XSSFCellStyle style2 = ExportExcelUtil.getXSSFCellStyle(workbook, "2");
        XSSFCellStyle styleText = ExportExcelUtil.getXSSFCellStyle(workbook, "text");
        int index = 1;
        // 从第二行开始输出数据
        for (ZG02PbcPrdInitRaiseSub m : lists) {
            XSSFRow row = sheet.createRow(index);
            // 列索引
            int cellIndex = 0;

            XSSFCell cell0 = row.createCell((short) cellIndex);
            cell0.setCellStyle(styleText);
            cell0.setCellValue(m.getPbcProdId());

            XSSFCell cell1 = row.createCell((short) cellIndex + 1);
            cell1.setCellStyle(styleText);
            cell1.setCellValue(m.getCcy());

            XSSFCell cell2 = row.createCell((short) cellIndex + 2);
            cell2.setCellStyle(styleText);
            cell2.setCellValue(m.getBuyPlace());

            XSSFCell cell3 = row.createCell((short) cellIndex + 3);
            cell3.setCellStyle(styleText);
            cell3.setCellValue(m.getCustomerType1());

            XSSFCell cell4 = row.createCell((short) cellIndex + 4);
            cell4.setCellStyle(style2);
            if (!ObjectUtils.nullSafeEquals(m.getInitRaisingAmtsumStr(), "") && !ObjectUtils.nullSafeEquals(m.getInitRaisingAmtsumStr(), null)) {
                cell4.setCellValue(Double.parseDouble(m.getInitRaisingAmtsumStr()));
            }

            XSSFCell cell5 = row.createCell((short) cellIndex + 5);
            cell5.setCellStyle(style2);
            if (!ObjectUtils.nullSafeEquals(m.getInitRaisingAmtsumcnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getInitRaisingAmtsumcnyStr(), null)) {
                cell5.setCellValue(Double.parseDouble(m.getInitRaisingAmtsumcnyStr()));
            }

            XSSFCell cell6 = row.createCell((short) cellIndex + 6);
            cell6.setCellStyle(style2);
            cell6.setCellValue(Double.parseDouble(m.getInitRaisingQtyStr()));

            index++;
        }
    }
}
