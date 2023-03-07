package com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.controller.AbstractCustomerDataExport;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.framework.util.ExportExcelUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.service.ZG07PbcColLoanDetailSubMService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.utils.Lists;
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
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ZG07 除回购和拆解外贷款明细登记表
 *
 * @author yangxianjiu
 * @date 2022/11/9
 */
@PageId("ZG07PbcColLoanDetailSub001")
@Controller
@RequestMapping("/pbc/zg07pbccolloandetailsub/ZG07PbcColLoanDetailSub001")
public class ZG07PbcColLoanDetailSub001Controller extends AbstractCustomerDataExport<ZG07PbcColLoanDetailSubDto> {

    //身份证正则
    private static String cardReg="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    @Autowired
    private ZG07PbcColLoanDetailSubMService pbcColLoanDetailSubMService;

    @RequestMapping(PAGE_INIT)
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
        super.setSelectOptionToModel(model, DictCode.贷款种类, DictCode.人行报送状态);
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<ZG07PbcColLoanDetailSubDto> selectForPage(){
        return pbcColLoanDetailSubMService.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 手工处理
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("doManual")
    public AjaxResult doManual(@RequestBody Map<String, Object> map) {
        pbcColLoanDetailSubMService.doManual(map);
        return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
    }

    /**
     * 取消手工处理
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("cancelManual")
    public AjaxResult cancelManual(@RequestBody Map<String, Object> map) {
        String result = pbcColLoanDetailSubMService.cancelManual(map);
        if ("fail".equals(result)) {
            AjaxResult ajaxresult = new AjaxResult("存在未报送的数据，无法撤销", MessageType.错误消息);
            ajaxresult.setStatuCode(HttpStatus.OK.value());
            return ajaxresult;
        }
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
        return pbcColLoanDetailSubMService.reportCheck(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export(HttpServletResponse response) throws IOException {
        String keyList = this.getParam("keyList");
        String issuePartyId =  CommonUtil.selectPbcBankCode(null);

        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<ZG07PbcColLoanDetailSubDto> dtoList = Lists.newArrayList();
        if (!StringUtil.isNotEmpty(keyList)) {
            //则默认根据当前查询条件对系统中报送状态为“未报送”或反馈状态为“发送失败”“发送异常”“中债退回”的数据进行检索导出
            dtoList = pbcColLoanDetailSubMService.searchByExcel(paraMap);
        } else {
            List<String> list = Arrays.asList(keyList.split(","));
            dtoList = pbcColLoanDetailSubMService.searchByUuid(list);
        }
        // 借款人类型 1 住户，借款人代码 加密
        dtoList.stream().forEach(item->{
            if (StringUtil.isNotBlank(item.getBorrowerType()) && StringUtil.equals("1", item.getBorrowerType())){
                if (StringUtil.isNotBlank(item.getBorrowerId())){
                    item.setBorrowerId(backCardChange(item.getBorrowerId()));
                }
            }
        });
        File outFile = pbcColLoanDetailSubMService.makeFile(
                "/com/joyintech/tams/svreport/pbc/classic/zg07pbccolloandetailsub/conf/export_conf.xml", dtoList);

        String termid=null;
        if(dtoList !=null && dtoList.size()>0){
            termid=dtoList.get(0).getTermid();
        }else {
            termid=(String) paraMap.get("termid");

        }
        String date= DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(termid.length()-2)),"yyyyMMdd");

        String excelFileName = issuePartyId + GlobalConstant.UNDER_LINE + "ZG07" + GlobalConstant.UNDER_LINE
                + date
                + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        Integer exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "10" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

        // 总的list拆分
        List<List<ZG07PbcColLoanDetailSubDto>> dtoLists = com.google.common.collect.Lists.partition(dtoList, exportCountPerSheet);

        OutputStream out = null;
        try {
            out = getOutputStream(response, excelFileName);
            String[] headers = { "产品代码","贷款种类","贷款转让方机构代码","贷款合同原始发放机构代码","贷款合同原始发放机构所在地代码","借款人类型","地区代码","借款人代码","行业信息","企业出资人经济成分","企业规模","贷款借据编码","贷款产品类别","贷款实际投向","贷款发放日期","贷款到期日期","贷款展期到期日期","利率是否固定","利率水平","贷款担保方式","贷款质量","贷款状态","贷款转让折扣率","原始合同币种","原始合同金额","原始合同金额折人民币","贷款余额币种","贷款余额","贷款余额折人民币"};

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
     * 获取资产代码
     * @return
     */
    @ResponseBody
    @RequestMapping("getAssetCode")
    public Page<SelectOptionDto> getAssetCode(){
        return pbcColLoanDetailSubMService.getAssetCode(RequestUtil.getPageParam());
    }

    /**
     * 身份证转换方法
     * @param cardCode
     * @return
     */
    public static String backCardChange(String cardCode){
        String back = "";
        //若证件号码为空，则按空值报送
        if(StringUtil.isNotEmpty(cardCode)){
            Pattern pattern = Pattern.compile(cardReg);
            Matcher m = pattern.matcher(cardCode);
            //个人身份证号码加密规则如下
            //若身份证件号码中有英文字母，应先统一转换成大写英文字母；
            cardCode = cardCode.toUpperCase();
            if(m.matches()){
                //变形后的身份证件号码（46 字符） = 身份证件号码前 14 个字节（14 位数字） + MD5(18 位身份证件号码)（32 字符，英文按小写输出）
                back = cardCode.substring(0, 14) + DigestUtils.md5Hex(cardCode).toLowerCase();
            }else{
                //变形后的证件号码（32 字符） = MD5(证件号码)（32 字符，英文按小写输出）
                back = DigestUtils.md5Hex(cardCode).toLowerCase();
            }
        }
        return back;
    }

    @Override
    protected void handleCellData(List<ZG07PbcColLoanDetailSubDto> lists,XSSFWorkbook workbook, XSSFSheet sheet) {
        // 从第二行开始输出数据
        int index = 1;
        XSSFCellStyle style1 = ExportExcelUtil.getXSSFCellStyle(workbook,"5");
        XSSFCellStyle style8 = ExportExcelUtil.getXSSFCellStyle(workbook,"8");
        XSSFCellStyle style2 = ExportExcelUtil.getXSSFCellStyle(workbook,"2");
        XSSFCellStyle stylet = ExportExcelUtil.getXSSFCellStyle(workbook,"t");
        XSSFCellStyle styleText = ExportExcelUtil.getXSSFCellStyle(workbook, "text");
        for (ZG07PbcColLoanDetailSubDto m : lists) {
            XSSFRow row = sheet.createRow(index);
            // 列索引
            int cellIndex = 0;

            XSSFCell cell0 = row.createCell((short) cellIndex + 0);
            cell0.setCellStyle(styleText);
            cell0.setCellValue(m.getPbcProdId());

            XSSFCell cell1 = row.createCell((short) cellIndex + 1);
            cell1.setCellStyle(styleText);
            cell1.setCellValue(m.getLoansType());

            XSSFCell cell2 = row.createCell((short) cellIndex + 2);
            cell2.setCellStyle(styleText);
            cell2.setCellValue(m.getLoanTransferorId());

            XSSFCell cell3 = row.createCell((short) cellIndex + 3);
            cell3.setCellStyle(styleText);
            cell3.setCellValue(m.getLoanContractOrgId());

            XSSFCell cell4 = row.createCell((short) cellIndex + 4);
            cell4.setCellStyle(styleText);
            cell4.setCellValue(m.getLoanContractOrgCode());

            XSSFCell cell5 = row.createCell((short) cellIndex + 5);
            cell5.setCellStyle(styleText);
            cell5.setCellValue(m.getBorrowerType());

            XSSFCell cell6 = row.createCell((short) cellIndex + 6);
            cell6.setCellStyle(styleText);
            cell6.setCellValue(m.getBorrowerCounty());

            XSSFCell cell7 = row.createCell((short) cellIndex + 7);
            cell7.setCellStyle(styleText);
            cell7.setCellValue(m.getBorrowerId());

            XSSFCell cell8 = row.createCell((short) cellIndex + 8);
            cell8.setCellStyle(styleText);
            cell8.setCellValue(m.getIndustryInfo());

            XSSFCell cell9 = row.createCell((short) cellIndex + 9);
            cell9.setCellStyle(styleText);
            cell9.setCellValue(m.getEnterpriseInvestorType());

            XSSFCell cell10 = row.createCell((short) cellIndex + 10);
            cell10.setCellStyle(styleText);
            cell10.setCellValue(m.getEnterpriseScale());

            XSSFCell cell11 = row.createCell((short) cellIndex + 11);
            cell11.setCellStyle(styleText);
            cell11.setCellValue(m.getLoanBasisId());

            XSSFCell cell12 = row.createCell((short) cellIndex + 12);
            cell12.setCellStyle(styleText);
            cell12.setCellValue(m.getLoanPrdType());

            XSSFCell cell13 = row.createCell((short) cellIndex + 13);
            cell13.setCellStyle(styleText);
            cell13.setCellValue(m.getLoanActualInvest());

            XSSFCell cell14 = row.createCell((short) cellIndex + 14);
            cell14.setCellStyle(styleText);
            cell14.setCellValue(m.getLoanVdateStr());

            XSSFCell cell15 = row.createCell((short) cellIndex + 15);
            cell15.setCellStyle(styleText);
            cell15.setCellValue(m.getLoanMdateStr());

            XSSFCell cell16 = row.createCell((short) cellIndex + 16);
            cell16.setCellStyle(styleText);
            cell16.setCellValue(m.getLoanExtensionDueDateStr());

            XSSFCell cell17 = row.createCell((short) cellIndex + 17);
            cell17.setCellStyle(styleText);
            cell17.setCellValue(m.getRateFixed());

            XSSFCell cell18 = row.createCell((short) cellIndex + 18);
            cell18.setCellStyle(style1);
            if (!ObjectUtils.nullSafeEquals(m.getRateLevelStr(), "") && !ObjectUtils.nullSafeEquals(m.getRateLevelStr(), null)) {
                cell18.setCellValue(Double.parseDouble(m.getRateLevelStr()));
            }

            XSSFCell cell19 = row.createCell((short) cellIndex + 19);
            cell19.setCellStyle(styleText);
            cell19.setCellValue(m.getLoanGuaranteeMode());

            XSSFCell cell20 = row.createCell((short) cellIndex + 20);
            cell20.setCellStyle(styleText);
            cell20.setCellValue(m.getLoanQuality());

            XSSFCell cell21 = row.createCell((short) cellIndex + 21);
            cell21.setCellStyle(styleText);
            cell21.setCellValue(m.getLoanState());

            XSSFCell cell22 = row.createCell((short) cellIndex + 22);
            cell22.setCellStyle(style1);
            if (!ObjectUtils.nullSafeEquals(m.getLoanTransferorRateStr(), "") && !ObjectUtils.nullSafeEquals(m.getLoanTransferorRateStr(), null)) {
                cell22.setCellValue(Double.parseDouble(m.getLoanTransferorRateStr()));
            }

            XSSFCell cell23 = row.createCell((short) cellIndex + 23);
            cell23.setCellStyle(styleText);
            cell23.setCellValue(m.getOrigCcy());

            XSSFCell cell24 = row.createCell((short) cellIndex + 24);
            cell24.setCellStyle(style2);
            if (!ObjectUtils.nullSafeEquals(m.getOrigContractAmountStr(), "") && !ObjectUtils.nullSafeEquals(m.getOrigContractAmountStr(), null)) {
                cell24.setCellValue(Double.parseDouble(m.getOrigContractAmountStr()));
            }

            XSSFCell cell25 = row.createCell((short) cellIndex + 25);
            cell25.setCellStyle(style2);
            if (!ObjectUtils.nullSafeEquals(m.getOrigContractAmountCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getOrigContractAmountCnyStr(), null)) {
                cell25.setCellValue(Double.parseDouble(m.getOrigContractAmountCnyStr()));
            }

            XSSFCell cell26 = row.createCell((short) cellIndex + 26);
            cell26.setCellStyle(styleText);
            cell26.setCellValue(m.getAmtCcy());

            XSSFCell cell27 = row.createCell((short) cellIndex + 27);
            cell27.setCellStyle(style2);
            if (!ObjectUtils.nullSafeEquals(m.getContractAmountStr(), "") && !ObjectUtils.nullSafeEquals(m.getContractAmountStr(), null)) {
                cell27.setCellValue(Double.parseDouble(m.getContractAmountStr()));
            }

            XSSFCell cell28 = row.createCell((short) cellIndex + 28);
            cell28.setCellStyle(style2);
            if (!ObjectUtils.nullSafeEquals(m.getContractAmountCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getContractAmountCnyStr(), null)) {
                cell28.setCellValue(Double.parseDouble(m.getContractAmountCnyStr()));
            }

            index++;
        }
    }
}
