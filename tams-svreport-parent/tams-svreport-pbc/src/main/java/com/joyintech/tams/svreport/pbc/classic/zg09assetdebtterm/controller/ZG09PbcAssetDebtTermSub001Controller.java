package com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.grid.Page;
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
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dto.ZG09PbcColReport;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.service.ZG09PbcAssetDebtTermSubMService;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.service.ZG09PbcAssetDebtTermSubService;
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
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 
 * 人行采集平台报送信息登记-ZG09 资管产品资产负债信息Controller
 *
 * @author dxy
 * @创建时间 2022年11月7日
 */
@Controller
@PageId("ZG09PbcAssetDebtTermSub")
@RequestMapping("/pbc/zg09assetdebtterm/ZG09PbcAssetDebtTermSub")
public class ZG09PbcAssetDebtTermSub001Controller extends AbstractCustomerDataExport<ZG09PbcColReport> {
    
    /**
     * 微服务service
     */
    @Autowired
    private ZG09PbcAssetDebtTermSubMService zg09PbcAssetDebtTermSubMService;

	@Autowired
	private ZG09PbcAssetDebtTermSubService zg09PbcAssetDebtTermSubService;


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
		super.setSelectOptionToModel(model, DictCode.报送数据状态, DictCode.人行报送状态, DictCode.人行报送反馈状态, DictCode.人行产品类型,
				DictCode.人行产品运作模式,DictCode.币种_人行,DictCode.数据类型_人行);
		return super.init(model);
	}

	/**
	 * 资产负债信息查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("searchPage")
	public Page<ZG09PbcColReport> searchPage() {
		Map<String, Object> searchParam = RequestUtil.getPageParam();

		Page<ZG09PbcColReport> page = zg09PbcAssetDebtTermSubMService.searchPage(searchParam);

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
		return zg09PbcAssetDebtTermSubMService.reportCheck(RequestUtil.getPageParam());
	}


	/**
	 *
	 * 业务画面导出事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> pedestrianExport(HttpServletResponse response) throws IOException {
		String datas = this.getParam("keyList");
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		List<ZG09PbcColReport> dtoList;
		String issuePartyId =  CommonUtil.selectPbcBankCode(null);

		if (!StringUtil.isNotEmpty(datas)) {
			//则默认根据当前查询条件对系统中报送状态为“未报送”的数据进行检索导出
			dtoList = zg09PbcAssetDebtTermSubMService.searchByExcel(paraMap);
		} else {
			List<String> list = Arrays.asList(datas.split(","));
			dtoList = zg09PbcAssetDebtTermSubMService.searchByUuid(list);
		}

		File outFile = zg09PbcAssetDebtTermSubService.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/zg09assetdebtterm/conf/export_conf.xml",
				dtoList);
		String termid=null;
		if(dtoList !=null && dtoList.size()>0){
			termid=dtoList.get(0).getTermid();
		}else {
			termid=(String) paraMap.get("enddate");

		}
		String date= DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(termid.length()-2)),"yyyyMMdd");
		String excelFileName = issuePartyId + GlobalConstant.UNDER_LINE + "ZG09" + GlobalConstant.UNDER_LINE
				+ date
				+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);
		Integer exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "10" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

		// 总的list拆分
		List<List<ZG09PbcColReport>> dtoLists = com.google.common.collect.Lists.partition(dtoList, exportCountPerSheet);


		OutputStream out = null;
		try {
			out = getOutputStream(response, excelFileName);
			String[] headers = { "发行机构代码","产品品种","G000A_资产方-债务工具-总计","G000B_资产方-股权-总计","G000C_资产方-特定目的载体份额-总计","G000D_负债及权益方-债务工具-总计","G000E_负债及权益方-实收本金-总计","G100A_资产方-债务工具-1个月（含）以下","G100B_资产方-股权-1个月（含）以下","G100C_资产方-特定目的载体份额-1个月（含）以下","G100D_负债及权益方-债务工具-1个月（含）以下","G100E_负债及权益方-实收本金-1个月（含）以下","G200A_资产方-债务工具-1-3个月（含）","G200B_资产方-股权-1-3个月（含）","G200C_资产方-特定目的载体份额-1-3个月（含）","G200D_负债及权益方-债务工具-1-3个月（含）","G200E_负债及权益方-实收本金-1-3个月（含）","G300A_资产方-债务工具-3-6个月（含）","G300B_资产方-股权-3-6个月（含）","G300C_资产方-特定目的载体份额-3-6个月（含）","G300D_负债及权益方-债务工具-3-6个月（含）","G300E_负债及权益方-实收本金-3-6个月（含）","G400A_资产方-债务工具-6-12个月（含）","G400B_资产方-股权-6-12个月（含）","G400C_资产方-特定目的载体份额-6-12个月（含）","G400D_负债及权益方-债务工具-6-12个月（含）","G400E_负债及权益方-实收本金-6-12个月（含）","G500A_资产方-债务工具-1-2年（含）","G500B_资产方-股权-1-2年（含）","G500C_资产方-特定目的载体份额-1-2年（含）","G500D_负债及权益方-债务工具-1-2年（含）","G500E_负债及权益方-实收本金-1-2年（含）","G600A_资产方-债务工具-2-3年（含）","G600B_资产方-股权-2-3年（含）","G600C_资产方-特定目的载体份额-2-3年（含）","G600D_负债及权益方-债务工具-2-3年（含）","G600E_负债及权益方-实收本金-2-3年（含）","G700A_资产方-债务工具-3年以上","G700B_资产方-股权-3年以上","G700C_资产方-特定目的载体份额-3年以上","G700D_负债及权益方-债务工具-3年以上","G700E_负债及权益方-实收本金-3年以上","G800A_资产方-债务工具-无固定期限","G800B_资产方-股权-无固定期限","G800C_资产方-特定目的载体份额-无固定期限","G800D_负债及权益方-债务工具-无固定期限","G800E_负债及权益方-实收本金-无固定期限","00001_附报-金融机构表内资产余额（本外币）","00002_附报-金融机构表内金融资产余额（本外币）"};

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
	 * 手工处理
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doManul")
	public AjaxResult manul(@RequestBody Map<String,Object> map) {
		zg09PbcAssetDebtTermSubMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}

	/**
	 * 取消手工处理
	 * 
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("cancelManul")
	public AjaxResult cancelManul(@RequestBody Map<String,Object> map) {
		String result = zg09PbcAssetDebtTermSubMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}


	@Override
	protected void handleCellData(List<ZG09PbcColReport> lists, XSSFWorkbook workbook, XSSFSheet sheet) {

		XSSFCellStyle style1 = ExportExcelUtil.getXSSFCellStyle(workbook,"5");
		XSSFCellStyle style8 = ExportExcelUtil.getXSSFCellStyle(workbook,"8");
		XSSFCellStyle style2 = ExportExcelUtil.getXSSFCellStyle(workbook,"2");
		XSSFCellStyle stylet = ExportExcelUtil.getXSSFCellStyle(workbook,"t");
		XSSFCellStyle styleText = ExportExcelUtil.getXSSFCellStyle(workbook, "text");

		// 从第二行开始输出数据
		int index = 1;
		for (ZG09PbcColReport m : lists) {
			XSSFRow row = sheet.createRow(index);
			// 列索引
			int cellIndex = 0;

			XSSFCell cell0 = row.createCell((short) cellIndex + 0);
			cell0.setCellStyle(styleText);
			cell0.setCellValue(m.getIssuePartyId());

			XSSFCell cell1 = row.createCell((short) cellIndex + 1);
			cell1.setCellStyle(styleText);
			cell1.setCellValue(m.getPbcProdType());

			XSSFCell cell2 = row.createCell((short) cellIndex + 2);
			cell2.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG000aStr(), "") && !ObjectUtils.nullSafeEquals(m.getG000aStr(), null)) {
				cell2.setCellValue(Double.parseDouble(m.getG000aStr()));
			}

			XSSFCell cell3 = row.createCell((short) cellIndex + 3);
			cell3.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG000bStr(), "") && !ObjectUtils.nullSafeEquals(m.getG000bStr(), null)) {
				cell3.setCellValue(Double.parseDouble(m.getG000bStr()));
			}

			XSSFCell cell4 = row.createCell((short) cellIndex + 4);
			cell4.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG000cStr(), "") && !ObjectUtils.nullSafeEquals(m.getG000cStr(), null)) {
				cell4.setCellValue(Double.parseDouble(m.getG000cStr()));
			}

			XSSFCell cell5 = row.createCell((short) cellIndex + 5);
			cell5.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG000dStr(), "") && !ObjectUtils.nullSafeEquals(m.getG000dStr(), null)) {
				cell5.setCellValue(Double.parseDouble(m.getG000dStr()));
			}

			XSSFCell cell6 = row.createCell((short) cellIndex + 6);
			cell6.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG000eStr(), "") && !ObjectUtils.nullSafeEquals(m.getG000eStr(), null)) {
				cell6.setCellValue(Double.parseDouble(m.getG000eStr()));
			}

			XSSFCell cell7 = row.createCell((short) cellIndex + 7);
			cell7.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG100aStr(), "") && !ObjectUtils.nullSafeEquals(m.getG100aStr(), null)) {
				cell7.setCellValue(Double.parseDouble(m.getG100aStr()));
			}

			XSSFCell cell8 = row.createCell((short) cellIndex + 8);
			cell8.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG100bStr(), "") && !ObjectUtils.nullSafeEquals(m.getG100bStr(), null)) {
				cell8.setCellValue(Double.parseDouble(m.getG100bStr()));
			}

			XSSFCell cell9 = row.createCell((short) cellIndex + 9);
			cell9.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG100cStr(), "") && !ObjectUtils.nullSafeEquals(m.getG100cStr(), null)) {
				cell9.setCellValue(Double.parseDouble(m.getG100cStr()));
			}

			XSSFCell cell10 = row.createCell((short) cellIndex + 10);
			cell10.setCellStyle(style2);
			cell10.setCellValue(m.getG100dStr());

			XSSFCell cell11 = row.createCell((short) cellIndex + 11);
			cell11.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG100eStr(), "") && !ObjectUtils.nullSafeEquals(m.getG100eStr(), null)) {
				cell11.setCellValue(Double.parseDouble(m.getG100eStr()));
			}

			XSSFCell cell12 = row.createCell((short) cellIndex + 12);
			cell12.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG200aStr(), "") && !ObjectUtils.nullSafeEquals(m.getG200aStr(), null)) {
				cell12.setCellValue(Double.parseDouble(m.getG200aStr()));
			}

			XSSFCell cell13 = row.createCell((short) cellIndex + 13);
			cell13.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG200bStr(), "") && !ObjectUtils.nullSafeEquals(m.getG200bStr(), null)) {
				cell13.setCellValue(Double.parseDouble(m.getG200bStr()));
			}

			XSSFCell cell14 = row.createCell((short) cellIndex + 14);
			cell14.setCellStyle(style2);
			cell14.setCellValue(m.getG200cStr());

			XSSFCell cell15 = row.createCell((short) cellIndex + 15);
			cell15.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG200dStr(), "") && !ObjectUtils.nullSafeEquals(m.getG200dStr(), null)) {
				cell15.setCellValue(Double.parseDouble(m.getG200dStr()));
			}

			XSSFCell cell16 = row.createCell((short) cellIndex + 16);
			cell16.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG200eStr(), "") && !ObjectUtils.nullSafeEquals(m.getG200eStr(), null)) {
				cell16.setCellValue(Double.parseDouble(m.getG200eStr()));
			}

			XSSFCell cell17 = row.createCell((short) cellIndex + 17);
			cell17.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG300aStr(), "") && !ObjectUtils.nullSafeEquals(m.getG300aStr(), null)) {
				cell17.setCellValue(Double.parseDouble(m.getG300aStr()));
			}

			XSSFCell cell18 = row.createCell((short) cellIndex + 18);
			cell18.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG300bStr(), "") && !ObjectUtils.nullSafeEquals(m.getG300bStr(), null)) {
				cell18.setCellValue(Double.parseDouble(m.getG300bStr()));
			}

			XSSFCell cell19 = row.createCell((short) cellIndex + 19);
			cell19.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG300cStr(), "") && !ObjectUtils.nullSafeEquals(m.getG300cStr(), null)) {
				cell19.setCellValue(Double.parseDouble(m.getG300cStr()));
			}

			XSSFCell cell20 = row.createCell((short) cellIndex + 20);
			cell20.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG300dStr(), "") && !ObjectUtils.nullSafeEquals(m.getG300dStr(), null)) {
				cell20.setCellValue(Double.parseDouble(m.getG300dStr()));
			}

			XSSFCell cell21 = row.createCell((short) cellIndex + 21);
			cell21.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG300eStr(), "") && !ObjectUtils.nullSafeEquals(m.getG300eStr(), null)) {
				cell21.setCellValue(Double.parseDouble(m.getG300eStr()));
			}

			XSSFCell cell22 = row.createCell((short) cellIndex + 22);
			cell22.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG400aStr(), "") && !ObjectUtils.nullSafeEquals(m.getG400aStr(), null)) {
				cell22.setCellValue(Double.parseDouble(m.getG400aStr()));
			}

			XSSFCell cell23 = row.createCell((short) cellIndex + 23);
			cell23.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG400bStr(), "") && !ObjectUtils.nullSafeEquals(m.getG400bStr(), null)) {
				cell23.setCellValue(Double.parseDouble(m.getG400bStr()));
			}

			XSSFCell cell24 = row.createCell((short) cellIndex + 24);
			cell24.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG400cStr(), "") && !ObjectUtils.nullSafeEquals(m.getG400cStr(), null)) {
				cell24.setCellValue(Double.parseDouble(m.getG400cStr()));
			}

			XSSFCell cell25 = row.createCell((short) cellIndex + 25);
			cell25.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG400dStr(), "") && !ObjectUtils.nullSafeEquals(m.getG400dStr(), null)) {
				cell25.setCellValue(Double.parseDouble(m.getG400dStr()));
			}

			XSSFCell cell26 = row.createCell((short) cellIndex + 26);
			cell26.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG400eStr(), "") && !ObjectUtils.nullSafeEquals(m.getG400eStr(), null)) {
				cell26.setCellValue(Double.parseDouble(m.getG400eStr()));
			}

			XSSFCell cell27 = row.createCell((short) cellIndex + 27);
			cell27.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG500aStr(), "") && !ObjectUtils.nullSafeEquals(m.getG500aStr(), null)) {
				cell27.setCellValue(Double.parseDouble(m.getG500aStr()));
			}

			XSSFCell cell28 = row.createCell((short) cellIndex + 28);
			cell28.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG500bStr(), "") && !ObjectUtils.nullSafeEquals(m.getG500bStr(), null)) {
				cell28.setCellValue(Double.parseDouble(m.getG500bStr()));
			}

			XSSFCell cell29 = row.createCell((short) cellIndex + 29);
			cell29.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG500cStr(), "") && !ObjectUtils.nullSafeEquals(m.getG500cStr(), null)) {
				cell29.setCellValue(Double.parseDouble(m.getG500cStr()));
			}

			XSSFCell cell30 = row.createCell((short) cellIndex + 30);
			cell30.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG500dStr(), "") && !ObjectUtils.nullSafeEquals(m.getG500dStr(), null)) {
				cell30.setCellValue(Double.parseDouble(m.getG500dStr()));
			}

			XSSFCell cell31 = row.createCell((short) cellIndex + 31);
			cell31.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG500eStr(), "") && !ObjectUtils.nullSafeEquals(m.getG500eStr(), null)) {
				cell31.setCellValue(Double.parseDouble(m.getG500eStr()));
			}

			XSSFCell cell32 = row.createCell((short) cellIndex + 32);
			cell32.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG600aStr(), "") && !ObjectUtils.nullSafeEquals(m.getG600aStr(), null)) {
				cell32.setCellValue(Double.parseDouble(m.getG600aStr()));
			}

			XSSFCell cell33 = row.createCell((short) cellIndex + 33);
			cell33.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG600bStr(), "") && !ObjectUtils.nullSafeEquals(m.getG600bStr(), null)) {
				cell33.setCellValue(Double.parseDouble(m.getG600bStr()));
			}

			XSSFCell cell34 = row.createCell((short) cellIndex + 34);
			cell34.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG600cStr(), "") && !ObjectUtils.nullSafeEquals(m.getG600cStr(), null)) {
				cell34.setCellValue(Double.parseDouble(m.getG600cStr()));
			}

			XSSFCell cell35 = row.createCell((short) cellIndex + 35);
			cell35.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG600dStr(), "") && !ObjectUtils.nullSafeEquals(m.getG600dStr(), null)) {
				cell35.setCellValue(Double.parseDouble(m.getG600dStr()));
			}

			XSSFCell cell36 = row.createCell((short) cellIndex + 36);
			cell36.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG600eStr(), "") && !ObjectUtils.nullSafeEquals(m.getG600eStr(), null)) {
				cell36.setCellValue(Double.parseDouble(m.getG600eStr()));
			}

			XSSFCell cell37 = row.createCell((short) cellIndex + 37);
			cell37.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG700aStr(), "") && !ObjectUtils.nullSafeEquals(m.getG700aStr(), null)) {
				cell37.setCellValue(Double.parseDouble(m.getG700aStr()));
			}

			XSSFCell cell38 = row.createCell((short) cellIndex + 38);
			cell38.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG700bStr(), "") && !ObjectUtils.nullSafeEquals(m.getG700bStr(), null)) {
				cell38.setCellValue(Double.parseDouble(m.getG700bStr()));
			}

			XSSFCell cell39 = row.createCell((short) cellIndex + 39);
			cell39.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG700cStr(), "") && !ObjectUtils.nullSafeEquals(m.getG700cStr(), null)) {
				cell39.setCellValue(Double.parseDouble(m.getG700cStr()));
			}

			XSSFCell cell40 = row.createCell((short) cellIndex + 40);
			cell40.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG700dStr(), "") && !ObjectUtils.nullSafeEquals(m.getG700dStr(), null)) {
				cell40.setCellValue(Double.parseDouble(m.getG700dStr()));
			}

			XSSFCell cell41 = row.createCell((short) cellIndex + 41);
			cell41.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG700eStr(), "") && !ObjectUtils.nullSafeEquals(m.getG700eStr(), null)) {
				cell41.setCellValue(Double.parseDouble(m.getG700eStr()));
			}

			XSSFCell cell42 = row.createCell((short) cellIndex + 42);
			cell42.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG800aStr(), "") && !ObjectUtils.nullSafeEquals(m.getG800aStr(), null)) {
				cell42.setCellValue(Double.parseDouble(m.getG800aStr()));
			}

			XSSFCell cell43 = row.createCell((short) cellIndex + 43);
			cell43.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG800bStr(), "") && !ObjectUtils.nullSafeEquals(m.getG800bStr(), null)) {
				cell43.setCellValue(Double.parseDouble(m.getG800bStr()));
			}

			XSSFCell cell44 = row.createCell((short) cellIndex + 44);
			cell44.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG800cStr(), "") && !ObjectUtils.nullSafeEquals(m.getG800cStr(), null)) {
				cell44.setCellValue(Double.parseDouble(m.getG800cStr()));
			}

			XSSFCell cell45 = row.createCell((short) cellIndex + 45);
			cell45.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG800dStr(), "") && !ObjectUtils.nullSafeEquals(m.getG800dStr(), null)) {
				cell45.setCellValue(Double.parseDouble(m.getG800dStr()));
			}

			XSSFCell cell46 = row.createCell((short) cellIndex + 46);
			cell46.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG800eStr(), "") && !ObjectUtils.nullSafeEquals(m.getG800eStr(), null)) {
				cell46.setCellValue(Double.parseDouble(m.getG800eStr()));
			}

			XSSFCell cell47 = row.createCell((short) cellIndex + 47);
			cell47.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG00001Str(), "") && !ObjectUtils.nullSafeEquals(m.getG00001Str(), null)) {
				cell47.setCellValue(Double.parseDouble(m.getG00001Str()));
			}

			XSSFCell cell48 = row.createCell((short) cellIndex + 48);
			cell48.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getG00002Str(), "") && !ObjectUtils.nullSafeEquals(m.getG00002Str(), null)) {
				cell48.setCellValue(Double.parseDouble(m.getG00002Str()));
			}
			index++;
		}
	}
}
