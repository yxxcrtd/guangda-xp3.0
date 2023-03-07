package com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.controller;

import com.google.common.collect.Lists;
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
import com.joyintech.tams.report.framework.util.ExportExcelUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.service.ZG04PbcPrdCashRaiseSubMService;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.service.ZG04PbcPrdCashRaiseSubService001;
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
import java.util.List;
import java.util.Map;

/**
* 
* <p>人行报送信息登记-产品资金募集信息Controller</p>
* @author liuyu
* @创建时间 2019年6月4日 下午4:57:28
*/
@Controller
@PageId("ZG04PbcPrdCashRaiseSub")
@RequestMapping("/pbc/zg04prdcashraisesub/ZG04PbcPrdCashRaiseSub")
public class ZG04PbcPrdCashRaiseSub001Controller extends AbstractCustomerDataExport<ZG04PbcProdRaiseSub> {
	/**
	 * 产品资金募集信息service
	 */
	@Autowired
	private ZG04PbcPrdCashRaiseSubService001 zg04PbcPrdCashRaiseSubService001;
	
	/**
	 * 微服务service
	 */
	@Autowired
	private ZG04PbcPrdCashRaiseSubMService zg04PbcPrdCashRaiseSubMService;


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
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
    	super.setSelectOptionToModel(model,	DictCode.报送数据状态,DictCode.人行报送状态,DictCode.人行报送反馈状态,DictCode.人行产品类型,DictCode.人行产品运行方式);
        return super.init(model);
    }
    
    /**
     * 产品资金募集信息查询
     * @return
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ZG04PbcProdRaiseSub> searchPage(){
    	Map<String,Object> searchParam = RequestUtil.getPageParam();
    	Page<ZG04PbcProdRaiseSub> page = zg04PbcPrdCashRaiseSubMService.searchPage(searchParam);;
		return page;
    	
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
		return zg04PbcPrdCashRaiseSubMService.reportCheck(RequestUtil.getPageParam());
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
		List<ZG04PbcProdRaiseSub> dtoList = zg04PbcPrdCashRaiseSubService001.setDataList(RequestUtil.getParameters(getRequest()));

		String termid=null;
		String issuePartyId =  CommonUtil.selectPbcBankCode(null);

		if(dtoList !=null && dtoList.size()>0){
			termid=dtoList.get(0).getRaisingDate();
		}else {
			termid=(String)  RequestUtil.getParameters(getRequest()).get("raisingDate");
		}
		String date= DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(4,6)),"yyyyMMdd");

		File outFile = zg04PbcPrdCashRaiseSubService001.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/zg04prdcashraisesub/conf/export_conf.xml", dtoList);
		String excelFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG04"+GlobalConstant.UNDER_LINE + date + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

		//		return ExportUtil.export(issuePartyId+GlobalConstant.UNDER_LINE+"ZG04"+GlobalConstant.UNDER_LINE
//						+ date
//						+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//				outFile, true);


		Integer exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "20" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

		// 总的list拆分
		List<List<ZG04PbcProdRaiseSub>> dtoLists = Lists.partition(dtoList, exportCountPerSheet);

		OutputStream out = null;
		try {
			out = getOutputStream(response, excelFileName);
			String[] headers = { "产品代码", "地区", "客户类型", "币种", "当期申购金额", "当期申购金额折人民币", "当期申购份额", "当期兑付/赎回金额","当期兑付/赎回金额折人民币","当期兑付/赎回份额" , "期末产品金额", "期末产品金额折人民币", "期末产品份额", "净值型产品期末净值", "净值型产品期末净值折人民币", "净值型产品期末累计净值", "净值型产品期末累计净值折人民币", "产品期末业绩表现" };

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


	@Override
	protected void handleCellData(List<ZG04PbcProdRaiseSub> lists,XSSFWorkbook workbook, XSSFSheet sheet) {
		// 从第二行开始输出数据
		XSSFCellStyle style1 = ExportExcelUtil.getXSSFCellStyle(workbook,"5");
		XSSFCellStyle style8 = ExportExcelUtil.getXSSFCellStyle(workbook,"8");
		XSSFCellStyle style2 = ExportExcelUtil.getXSSFCellStyle(workbook,"2");
		XSSFCellStyle stylet = ExportExcelUtil.getXSSFCellStyle(workbook,"t");
		XSSFCellStyle styleText = ExportExcelUtil.getXSSFCellStyle(workbook, "text");

			int index = 1;
			for (ZG04PbcProdRaiseSub m : lists) {
				XSSFRow row = sheet.createRow(index);
				// 列索引
				int cellIndex = 0;

				XSSFCell cell0 = row.createCell((short) cellIndex);
				cell0.setCellStyle(styleText);
				cell0.setCellValue(m.getPbcProdId());

				XSSFCell cell1 = row.createCell((short) cellIndex + 1);
				cell1.setCellStyle(styleText);
				cell1.setCellValue(m.getBuyPlace());

				XSSFCell cell2 = row.createCell((short) cellIndex + 2);
				cell2.setCellStyle(styleText);
				cell2.setCellValue(m.getCustomerType1());

				XSSFCell cell3 = row.createCell((short) cellIndex + 3);
				cell3.setCellStyle(styleText);
				cell3.setCellValue(m.getCcy());

				XSSFCell cell4 = row.createCell((short) cellIndex + 4);
				cell4.setCellStyle(style2);
				if (!ObjectUtils.nullSafeEquals(m.getRaisingAmtsumStr(), "") && !ObjectUtils.nullSafeEquals(m.getRaisingAmtsumStr(), null)) {
					cell4.setCellValue(Double.parseDouble(m.getRaisingAmtsumStr()));
				}

				XSSFCell cell5 = row.createCell((short) cellIndex + 5);
				cell5.setCellStyle(style2);
				if (!ObjectUtils.nullSafeEquals(m.getRaisingAmtsumCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getRaisingAmtsumCnyStr(), null)) {
					cell5.setCellValue(Double.parseDouble(m.getRaisingAmtsumCnyStr()));
				}
				XSSFCell cell6 = row.createCell((short) cellIndex + 6);
				cell6.setCellStyle(style2);
				if (!ObjectUtils.nullSafeEquals(m.getRaisingQtyStr(), "") && !ObjectUtils.nullSafeEquals(m.getRaisingQtyStr(), null)) {
					cell6.setCellValue(Double.parseDouble(m.getRaisingQtyStr()));
				}

				XSSFCell cell7 = row.createCell((short) cellIndex + 7);
				cell7.setCellStyle(style2);
				if (!ObjectUtils.nullSafeEquals(m.getRedamtStr(), "") && !ObjectUtils.nullSafeEquals(m.getRedamtStr(), null)) {
					cell7.setCellValue(Double.parseDouble(m.getRedamtStr()));
				}

				XSSFCell cell8 = row.createCell((short) cellIndex + 8);
				cell8.setCellStyle(style2);
				if (!ObjectUtils.nullSafeEquals(m.getRedamtCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getRedamtCnyStr(), null)) {
					cell8.setCellValue(Double.parseDouble(m.getRedamtCnyStr()));
				}

				XSSFCell cell9 = row.createCell((short) cellIndex + 9);
				cell9.setCellStyle(style2);
				if (!ObjectUtils.nullSafeEquals(m.getRedqtyStr(), "") && !ObjectUtils.nullSafeEquals(m.getRedqtyStr(), null)) {
					cell9.setCellValue(Double.parseDouble(m.getRedqtyStr()));
				}


				XSSFCell cell10 = row.createCell((short) cellIndex + 10);
				cell10.setCellStyle(style2);
				if (!ObjectUtils.nullSafeEquals(m.getEndamtsumStr(), "") && !ObjectUtils.nullSafeEquals(m.getEndamtsumStr(), null)) {
					cell10.setCellValue(Double.parseDouble(m.getEndamtsumStr()));
				}

				XSSFCell cell11 = row.createCell((short) cellIndex + 11);
				cell11.setCellStyle(style2);
				if (!ObjectUtils.nullSafeEquals(m.getEndamtsumCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getEndamtsumCnyStr(), null)) {
					cell11.setCellValue(Double.parseDouble(m.getEndamtsumCnyStr()));
				}

				XSSFCell cell12 = row.createCell((short) cellIndex + 12);
				cell12.setCellStyle(style2);
				if (!ObjectUtils.nullSafeEquals(m.getEndqtyStr(), "") && !ObjectUtils.nullSafeEquals(m.getEndqtyStr(), null)) {
					cell12.setCellValue(Double.parseDouble(m.getEndqtyStr()));
				}

				XSSFCell cell13 = row.createCell((short) cellIndex + 13);
				cell13.setCellStyle(style8);
				if (!ObjectUtils.nullSafeEquals(m.getProdValueStr(), "") && !ObjectUtils.nullSafeEquals(m.getProdValueStr(), null)) {
					cell13.setCellValue(Double.parseDouble(m.getProdValueStr()));
				}

				XSSFCell cell14 = row.createCell((short) cellIndex + 14);
				cell14.setCellStyle(style8);
				if (!ObjectUtils.nullSafeEquals(m.getProdValueCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getProdValueCnyStr(), null)) {
					cell14.setCellValue(Double.parseDouble(m.getProdValueCnyStr()));
				}

				XSSFCell cell15 = row.createCell((short) cellIndex + 15);
				cell15.setCellStyle(style8);
				if (!ObjectUtils.nullSafeEquals(m.getUnitValueTotalStr(), "") && !ObjectUtils.nullSafeEquals(m.getUnitValueTotalStr(), null)) {
					cell15.setCellValue(Double.parseDouble(m.getUnitValueTotalStr()));
				}

				XSSFCell cell16 = row.createCell((short) cellIndex + 16);
				cell16.setCellStyle(style8);
				if (!ObjectUtils.nullSafeEquals(m.getUnitValueTotalCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getUnitValueTotalCnyStr(), null)) {
					cell16.setCellValue(Double.parseDouble(m.getUnitValueTotalCnyStr()));
				}

				XSSFCell cell17 = row.createCell((short) cellIndex + 17);
				cell17.setCellStyle(style1);
				if (!ObjectUtils.nullSafeEquals(m.getProdEndYieldStr(), "") && !ObjectUtils.nullSafeEquals(m.getProdEndYieldStr(), null)) {
					cell17.setCellValue(Double.parseDouble(m.getProdEndYieldStr()));
				}
				index++;
			}
	}


	/**
	 * 手工处理
	 * @param map
	 * @return manul
	 */
	@ResponseBody
	@RequestMapping("doManul")
	public AjaxResult manul(@RequestBody Map<String,Object> map) {
		zg04PbcPrdCashRaiseSubMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	/**
	 * 取消手工处理
	 * @param map
	 * @return cancelManul
	 */
	@ResponseBody
	@RequestMapping("cancelManul")
	public AjaxResult cancelManul(@RequestBody Map<String,Object> map) {
		String result = zg04PbcPrdCashRaiseSubMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}

	/**
	 * 人行产品代码下拉框
	 */
	@RequestMapping("/getPbcProdId")
	@ResponseBody
	public List<SelectOptionDto> getPbcProdId() {
		return zg04PbcPrdCashRaiseSubMService.getPbcProdId(RequestUtil.getPageParam());
	}

	/**
	 * 人行产品代码下拉框
	 */
	@RequestMapping("/getProdID")
	@ResponseBody
	public List<SelectOptionDto> getProdID() {
		return zg04PbcPrdCashRaiseSubMService.getProdId(RequestUtil.getPageParam());
	}
}
