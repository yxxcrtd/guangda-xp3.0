package com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.controller;

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
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto.ZG06PbcAssetSusfructSub;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.serivce.ZG06PbcAssetSusfructSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.service.ZG06PbcAssetSusfructSubMService;
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
 * <p>人行报送信息登记-ZG06资产收益权明细登记Controller</p>
 * @author liuyu
 * @创建时间 2019年6月4日 下午4:57:28
 */
@Controller
@PageId("ZG06PbcAssetSusfructSub")
@RequestMapping("/pbc/zg06assetusufructsub/ZG06PbcAssetSusfructSub")
public class ZG06PbcAssetSusfructSub001Controller extends AbstractCustomerDataExport<ZG06PbcAssetSusfructSub> {
	/**
	 * 产品初始募集登记service
	 */
	@Autowired
	private ZG06PbcAssetSusfructSubService001 zg06PbcAssetSusfructSubService001;
	@Autowired
	private ZG06PbcAssetSusfructSubMService zg06PbcAssetSusfructSubMService;

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
			if(StringUtil.isNotEmpty(map.get("submitFeedbackStatus"))){
            	model.addAttribute("submitFeedbackStatus", map.get("submitFeedbackStatus").toString().trim());
            }
			model.addAttribute("searchDataStart", map.get("searchDataStart"));
			model.addAttribute("searchDataEnd", map.get("searchDataEnd"));
			model.addAttribute("isMain", "01");
		}
		//RHPTSJ-369
    	super.setSelectOptionToModel(model,	DictCode.报送数据状态,DictCode.报送状态,DictCode.人行_资产负债项目,
				DictCode.人行产品类型,DictCode.人行产品运作模式,DictCode.资产负债项目);
        return super.init(model);
    }
    
    /**
     * 产品终止登记查询
     * @return
     */
    @ResponseBody
    @RequestMapping("searchPage")
    public Page<ZG06PbcAssetSusfructSub> searchPage(){
    	Map<String,Object> searchParam = RequestUtil.getPageParam();
    	Page<ZG06PbcAssetSusfructSub> page = zg06PbcAssetSusfructSubMService.searchPage(searchParam);;
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
		return zg06PbcAssetSusfructSubMService.reportCheck(RequestUtil.getPageParam());
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
		List<ZG06PbcAssetSusfructSub> dtoList = zg06PbcAssetSusfructSubService001.setDataList(RequestUtil.getParameters(getRequest()));
		File outFile = zg06PbcAssetSusfructSubService001.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/zg06assetusufructsub/conf/export_conf.xml", dtoList);
		String termid=null;
		String issuePartyId =  CommonUtil.selectPbcBankCode(null);

		if(dtoList !=null && dtoList.size()>0){
			termid=dtoList.get(0).getTermId();
		}else {
			termid=(String) RequestUtil.getParameters(getRequest()).get("termId");

		}
		String date= DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(termid.length()-2)),"yyyyMMdd");

		String excelFileName = issuePartyId + GlobalConstant.UNDER_LINE + "ZG06" + GlobalConstant.UNDER_LINE
				+ date
				+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

		Integer exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "10" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

		// 总的list拆分
		List<List<ZG06PbcAssetSusfructSub>> dtoLists = Lists.partition(dtoList, exportCountPerSheet);

		OutputStream out = null;

		try {
			out = getOutputStream(response, excelFileName);
			String[] headers = { "产品代码","资产负债项目","资产收益权内部编码","基础资产出让机构名称","基础资产出让机构代码","基础资产出让机构类型","基础资产出让机构行业","基础资产出让机构注册地区","基础资产出让机构经济成分","基础资产出让机构规模","转让起始日期","转让预计终止日期","转让展期到期日期","转让实际终止日期","基础资产类型","基础资产原始协议币种","基础资产原始协议金额","基础资产原始协议金额折人民币","基础资产转让币种","基础资产转让金额","基础资产转让金额折人民币","出让机构出表标识","出让机构回购标识","利率是否固定","利率水平","担保方式","基础资产投向部门","基础资产期末币种","基础资产期末余额","基础资产期末余额折人民币"};

			// 创建多个Sheet
			XSSFWorkbook workbook = new XSSFWorkbook();
			for (int i = 0; i < dtoLists.size(); i++) {
				exportExcel(workbook, i, "Sheet-" + i, headers, dtoLists.get(i));
			}

			// 写入数据，关闭流
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Excel导出时出现错误：" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(out);
		}
		return null;
	}



	/**
	 * 手工处理
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doManul")
	public AjaxResult manul(@RequestBody Map<String,Object> map) {
		zg06PbcAssetSusfructSubMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}
	/**
	 * 取消手工处理
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("cancelManul")
	public AjaxResult cancelManul(@RequestBody Map<String,Object> map) {
		String result = zg06PbcAssetSusfructSubMService.cancelMannul(map);
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
		return zg06PbcAssetSusfructSubMService.getPbcProdId(RequestUtil.getPageParam());
	}

	@Override
	protected void handleCellData(List<ZG06PbcAssetSusfructSub> lists, XSSFWorkbook workbook, XSSFSheet sheet) {
		// 从第二行开始输出数据
		XSSFCellStyle style1 = ExportExcelUtil.getXSSFCellStyle(workbook,"5");
		XSSFCellStyle style8 = ExportExcelUtil.getXSSFCellStyle(workbook,"8");
		XSSFCellStyle style2 = ExportExcelUtil.getXSSFCellStyle(workbook,"2");
		XSSFCellStyle stylet = ExportExcelUtil.getXSSFCellStyle(workbook,"t");
		XSSFCellStyle styleText = ExportExcelUtil.getXSSFCellStyle(workbook, "text");
		int index = 1;
		for (ZG06PbcAssetSusfructSub m : lists) {
			XSSFRow row = sheet.createRow(index);
			// 列索引
			int cellIndex = 0;

			XSSFCell cell0 = row.createCell((short) cellIndex + 0);
			cell0.setCellStyle(styleText);
			cell0.setCellValue(m.getPbcProdId());

			XSSFCell cell1 = row.createCell((short) cellIndex + 1);
			cell1.setCellStyle(styleText);
			cell1.setCellValue(m.getAssetLiabilities());

			XSSFCell cell2 = row.createCell((short) cellIndex + 2);
			cell2.setCellStyle(styleText);
			cell2.setCellValue(m.getAssetUsufructCode());

			XSSFCell cell3 = row.createCell((short) cellIndex + 3);
			cell3.setCellStyle(styleText);
			cell3.setCellValue(m.getAssetTransferName());

			XSSFCell cell4 = row.createCell((short) cellIndex + 4);
			cell4.setCellStyle(styleText);
			cell4.setCellValue(m.getAssetTransferId());

			XSSFCell cell5 = row.createCell((short) cellIndex + 5);
			cell5.setCellStyle(styleText);
			cell5.setCellValue(m.getAssetInvest());

			XSSFCell cell6 = row.createCell((short) cellIndex + 6);
			cell6.setCellStyle(styleText);
			cell6.setCellValue(m.getAssetTransferIindu());

			XSSFCell cell7 = row.createCell((short) cellIndex + 7);
			cell7.setCellStyle(styleText);
			cell7.setCellValue(m.getAssetTransfer());

			XSSFCell cell8 = row.createCell((short) cellIndex + 8);
			cell8.setCellStyle(styleText);
			cell8.setCellValue(m.getEconomicComposition());

			XSSFCell cell9 = row.createCell((short) cellIndex + 9);
			cell9.setCellStyle(styleText);
			cell9.setCellValue(m.getAssetTransferScale());

			XSSFCell cell10 = row.createCell((short) cellIndex + 10);
			cell10.setCellStyle(styleText);
			cell10.setCellValue(m.getTransferVdateStr());

			XSSFCell cell11 = row.createCell((short) cellIndex + 11);
			cell11.setCellStyle(styleText);
			cell11.setCellValue(m.getTransferEstiMdateStr());

			XSSFCell cell12 = row.createCell((short) cellIndex + 12);
			cell12.setCellStyle(styleText);
			cell12.setCellValue(m.getTransferExtdueMdateStr());

			XSSFCell cell13 = row.createCell((short) cellIndex + 13);
			cell13.setCellStyle(styleText);
			cell13.setCellValue(m.getTransferActMdateStr());

			XSSFCell cell14 = row.createCell((short) cellIndex + 14);
			cell14.setCellStyle(styleText);
			cell14.setCellValue(m.getBasicAssetType());

			XSSFCell cell15 = row.createCell((short) cellIndex + 15);
			cell15.setCellStyle(styleText);
			cell15.setCellValue(m.getAssetsOrigCcy());

			XSSFCell cell16 = row.createCell((short) cellIndex + 16);
			cell16.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getAssetsOrigAmtStr(), "") && !ObjectUtils.nullSafeEquals(m.getAssetsOrigAmtStr(), null)) {
				cell16.setCellValue(Double.parseDouble(m.getAssetsOrigAmtStr()));
			}

			XSSFCell cell17 = row.createCell((short) cellIndex + 17);
			cell17.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getAssetsOrigAmtCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getAssetsOrigAmtCnyStr(), null)) {
				cell17.setCellValue(Double.parseDouble(m.getAssetsOrigAmtCnyStr()));
			}

			XSSFCell cell18 = row.createCell((short) cellIndex + 18);
			cell18.setCellStyle(styleText);
			cell18.setCellValue(m.getCurrencyTransferCcy());

			XSSFCell cell19 = row.createCell((short) cellIndex + 19);
			cell19.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCurrencyTransferAmtStr(), "") && !ObjectUtils.nullSafeEquals(m.getCurrencyTransferAmtStr(), null)) {
				cell19.setCellValue(Double.parseDouble(m.getCurrencyTransferAmtStr()));
			}

			XSSFCell cell20 = row.createCell((short) cellIndex + 20);
			cell20.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCurrencyTransferAmtCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getCurrencyTransferAmtCnyStr(), null)) {
				cell20.setCellValue(Double.parseDouble(m.getCurrencyTransferAmtCnyStr()));
			}

			XSSFCell cell21 = row.createCell((short) cellIndex + 21);
			cell21.setCellStyle(styleText);
			cell21.setCellValue(m.getLabelTransferorMark());

			XSSFCell cell22 = row.createCell((short) cellIndex + 22);
			cell22.setCellStyle(styleText);
			cell22.setCellValue(m.getRepurchaseMark());

			XSSFCell cell23 = row.createCell((short) cellIndex + 23);
			cell23.setCellStyle(styleText);
			cell23.setCellValue(m.getRateFixedFlag());

			XSSFCell cell24 = row.createCell((short) cellIndex + 24);
			cell24.setCellStyle(style1);
			if (!ObjectUtils.nullSafeEquals(m.getAssetRateLevelStr(), "") && !ObjectUtils.nullSafeEquals(m.getAssetRateLevelStr(), null)) {
				cell24.setCellValue(Double.parseDouble(m.getAssetRateLevelStr()));
			}

			XSSFCell cell25 = row.createCell((short) cellIndex + 25);
			cell25.setCellStyle(styleText);
			cell25.setCellValue(m.getGuaranteeMethod());

			XSSFCell cell26 = row.createCell((short) cellIndex + 26);
			cell26.setCellStyle(styleText);
			cell26.setCellValue(m.getAssetsInvDepa());

			XSSFCell cell27 = row.createCell((short) cellIndex + 27);
			cell27.setCellStyle(styleText);
			cell27.setCellValue(m.getCurrencyEperiodCcy());

			XSSFCell cell28 = row.createCell((short) cellIndex + 28);
			cell28.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCurrencyAmtStr(), "") && !ObjectUtils.nullSafeEquals(m.getCurrencyAmtStr(), null)) {
				cell28.setCellValue(Double.parseDouble(m.getCurrencyAmtStr()));
			}

			XSSFCell cell29 = row.createCell((short) cellIndex + 29);
			cell29.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCurrencyAmtCnyStr(), "") && !ObjectUtils.nullSafeEquals(m.getCurrencyAmtCnyStr(), null)) {
				cell29.setCellValue(Double.parseDouble(m.getCurrencyAmtCnyStr()));
			}

			index++;
		}
	}
}
