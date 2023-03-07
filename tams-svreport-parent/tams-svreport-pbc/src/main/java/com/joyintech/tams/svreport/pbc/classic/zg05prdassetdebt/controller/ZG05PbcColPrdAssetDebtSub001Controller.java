package com.joyintech.tams.svreport.pbc.classic.zg05prdassetdebt.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ExportUtil;
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
import com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service.PbcPrdAssetDebtSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.service.ZG05PbcColPrdAssetDebtSubService;
import com.joyintech.tams.svreport.pbc.classic.zg05prdassetdebt.service.ZG05PbcColPrdAssetDebtSubMService;
import com.joyintech.tams.svreport.pbc.report.service.PbcMonthReport001Service;
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
 * 人行采集平台报送信息登记-ZG05 资管产品资产负债信息Controller
 *
 * @author dxy
 * @创建时间 2022年11月7日
 */
@Controller
@PageId("ZG05PbcColPrdAssetDebtSub")
@RequestMapping("/pbc/zg05prdassetdebt/ZG05PbcColPrdAssetDebtSub")
public class ZG05PbcColPrdAssetDebtSub001Controller extends AbstractCustomerDataExport<ZG05PbcColPrdAssetDebtDto> {
	/**
	 * 资产负债信息service
	 */
	@Autowired
	private PbcPrdAssetDebtSubService001 pbcPrdAssetDebtSubService001;
	  /**
     * service
     */
    @Autowired
    private PbcMonthReport001Service pbcMonthReport001Service;
    
    /**
     * 微服务service
     */
    @Autowired
    private ZG05PbcColPrdAssetDebtSubMService zg05PbcColPrdAssetDebtSubMService;

	@Autowired
	private ZG05PbcColPrdAssetDebtSubService zG05PbcColPrdAssetDebtSubService;


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
	public Page<ZG05PbcColPrdAssetDebtDto> searchPage() {
		Map<String, Object> searchParam = RequestUtil.getPageParam();

		Page<ZG05PbcColPrdAssetDebtDto> page = zg05PbcColPrdAssetDebtSubMService.searchPage(searchParam);

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
		return zg05PbcColPrdAssetDebtSubMService.reportCheck(RequestUtil.getPageParam());
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
		List<ZG05PbcColPrdAssetDebtDto> dtoList;
		String issuePartyId =  CommonUtil.selectPbcBankCode(null);

		if (!StringUtil.isNotEmpty(datas)) {
			//则默认根据当前查询条件对系统中报送状态为“未报送”的数据进行检索导出
			dtoList = zg05PbcColPrdAssetDebtSubMService.searchByExcel(paraMap);
		} else {
			List<String> list = Arrays.asList(datas.split(","));
			dtoList = zg05PbcColPrdAssetDebtSubMService.searchByUuid(list);
		}


		File outFile = zG05PbcColPrdAssetDebtSubService.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/zg05prdassetdebt/conf/export_conf.xml",
				dtoList);

		String termid=null;
		if(dtoList !=null && dtoList.size()>0){
			termid=dtoList.get(0).getTermid();
		}else {
			termid=(String) paraMap.get("enddate");
		}
		String date= DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(termid.length()-2)),"yyyyMMdd");
//		return ExportUtil.export(issuePartyId+GlobalConstant.UNDER_LINE+"ZG05"+GlobalConstant.UNDER_LINE
//						+ date
//						+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//				outFile, true);
		String excelFileName = issuePartyId + GlobalConstant.UNDER_LINE + "ZG05" + GlobalConstant.UNDER_LINE
				+ date
				+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

		Integer exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "20" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

		// 总的list拆分
		List<List<ZG05PbcColPrdAssetDebtDto>> dtoLists = Lists.partition(dtoList, exportCountPerSheet);

		OutputStream out = null;

		try {
			out = getOutputStream(response, excelFileName);
			String[] headers = { "产品代码","币种","数据类型","A0000_资产合计","A1000_现金","A2000_存款","A2100_境内存款","A2110_活期存款","A2120_定期和其他存款","A2200_境外存款","A2210_活期存款","A2220_定期和其他存款","A3000_可转让存单","A3100_同业存单","A3200_大额存单","A4000_债务证券","A4100_政府债券","A4200_中央银行债券","A4300_政策性金融债券","A4400_其他金融债券","A4500_企业债券","A4600_票据","A4700_资产支持证券","A4800_境外债券","A5000_贷款","A5100_除回购和拆借外贷款","A5200_回购和拆借","A5210_住户","A5220_广义政府","A5230_非金融企业","A5240_银行业存款类金融机构","A5250_银行业非存款类金融机构","A5260_非银行业金融机构","A5261_其中：中央交易对手方","A5270_特定目的载体","A5271_银行非保本理财","A5272_信托公司资管产品","A5273_证券公司及其子公司资管产品","A5274_基金管理公司及其子公司专户","A5275_期货公司及其子公司资管产品","A5276_保险资管产品","A5277_金融资产投资公司资管产品","A5278_公募基金","A5279_私募机构私募基金","A527a_其他特定目的载体","A5280_境外","A6000_资产收益权","A6100_贷款收益权","A6200_债务证券收益权","A6300_应收账款收益权","A6400_其他债权收益权","A6500_股票收益权","A6600_其他股权收益权","A6700_物权资产收益权","A7000_股权及特定目的载体份额","A7100_股票","A7110_非金融企业股票","A7120_金融机构股票","A7200_特定目的载体份额","A7210_银行非保本理财","A7220_信托公司资管产品","A7230_证券公司及其子公司资管产品","A7240_基金管理公司及其子公司专户","A7250_期货公司及其子公司资管产品","A7260_保险资管产品","A7270_金融资产投资公司资管产品","A7280_公募基金","A7290_私募机构私募基金","A72a0_其他特定目的载体","A7300_其他股权","A7310_非金融企业股权","A7320_金融机构股权","A7400_境外","A8000_金融衍生品","A8100_境内","A8200_境外","A9000_应收账款","AA000_黄金","AB000_非金融资产","AC000_资产减值准备（减）","D0000_负债及权益合计","B0000_负债合计","B1000_贷款","B1100_除回购和拆借外贷款","B1200_回购和拆借","B1210_住户","B1220_广义政府","B1230_非金融企业","B1240_银行业存款类金融机构","B1250_银行业非存款类金融机构","B1260_非银行业金融机构","B1261_其中：中央交易对手方","B1270_特定目的载体","B1271_银行非保本理财","B1272_信托公司资管产品","B1273_证券公司及其子公司资管产品","B1274_基金管理公司及其子公司专户","B1275_期货公司及其子公司资管产品","B1276_保险资管产品","B1277_金融资产投资公司资管产品","B1278_公募基金","B1279_私募机构私募基金","B127a_其他特定目的载体","B1280_境外","B2000_资产支持证券","B3000_金融衍生品","B3100_境内","B3200_境外","B4000_应付账款","B5000_其他负债","C0000_权益合计","C1000_实收本金","C1100_优先级","C1110_住户","C1120_广义政府","C1130_非金融企业","C1140_银行业存款类金融机构","C1150_银行业非存款类金融机构","C1160_非银行业金融机构","C1170_特定目的载体","C1171_银行非保本理财","C1172_信托公司资管产品","C1173_证券公司及其子公司资管产品","C1174_基金管理公司及其子公司专户","C1175_期货公司及其子公司资管产品","C1176_保险资管产品","C1177_金融资产投资公司资管产品","C1178_公募基金","C1179_私募机构私募基金","C117a_其他特定目的载体","C1180_境外","C1200_劣后级","C1210_住户","C1220_广义政府","C1230_非金融企业","C1240_银行业存款类金融机构","C1250_银行业非存款类金融机构","C1260_非银行业金融机构","C1270_特定目的载体","C1271_银行非保本理财","C1272_信托公司资管产品","C1273_证券公司及其子公司资管产品","C1274_基金管理公司及其子公司专户","C1275_期货公司及其子公司资管产品","C1276_保险资管产品","C1277_金融资产投资公司资管产品","C1278_公募基金","C1279_私募机构私募基金","C127a_其他特定目的载体","C1280_境外","C3000_未分配利润","C4000_其他综合收益"};

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
		zg05PbcColPrdAssetDebtSubMService.updateSumStaus(map);
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
		String result = zg05PbcColPrdAssetDebtSubMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}


	@Override
	protected void handleCellData(List<ZG05PbcColPrdAssetDebtDto> lists,XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFCellStyle style2 = ExportExcelUtil.getXSSFCellStyle(workbook, "2");
		XSSFCellStyle styleText = ExportExcelUtil.getXSSFCellStyle(workbook, "text");
		XSSFRow row;
		// 从第二行开始输出数据
		int index = 1;
		for (ZG05PbcColPrdAssetDebtDto m : lists) {
			row = sheet.createRow(index);
			// 列索引
			int cellIndex = 0;

			XSSFCell cell0 = row.createCell((short) cellIndex + 0);
			cell0.setCellStyle(styleText);
			cell0.setCellValue(m.getPbcProdId());

			XSSFCell cell1 = row.createCell((short) cellIndex + 1);
			cell1.setCellStyle(styleText);
			cell1.setCellValue(m.getCcy());

			XSSFCell cell2 = row.createCell((short) cellIndex + 2);
			cell2.setCellStyle(styleText);
			cell2.setCellValue(m.getStatusType());

			XSSFCell cell3 = row.createCell((short) cellIndex + 3);
			cell3.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA0000Str(), null)) {
				cell3.setCellValue(Double.parseDouble(m.getA0000Str()));
			}

			XSSFCell cell4 = row.createCell((short) cellIndex + 4);
			cell4.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA1000Str(), null)) {
				cell4.setCellValue(Double.parseDouble(m.getA1000Str()));
			}

			XSSFCell cell5 = row.createCell((short) cellIndex + 5);
			cell5.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA2000Str(), null)) {
				cell5.setCellValue(Double.parseDouble(m.getA2000Str()));
			}

			XSSFCell cell6 = row.createCell((short) cellIndex + 6);
			cell6.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA2100Str(), "") && !ObjectUtils.nullSafeEquals(m.getA2100Str(), null)) {
				cell6.setCellValue(Double.parseDouble(m.getA2100Str()));
			}

			XSSFCell cell7 = row.createCell((short) cellIndex + 7);
			cell7.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA2110Str(), "") && !ObjectUtils.nullSafeEquals(m.getA2110Str(), null)) {
				cell7.setCellValue(Double.parseDouble(m.getA2110Str()));
			}

			XSSFCell cell8 = row.createCell((short) cellIndex + 8);
			cell8.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA2120Str(), "") && !ObjectUtils.nullSafeEquals(m.getA2120Str(), null)) {
				cell8.setCellValue(Double.parseDouble(m.getA2120Str()));
			}

			XSSFCell cell9 = row.createCell((short) cellIndex + 9);
			cell9.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA2200Str(), "") && !ObjectUtils.nullSafeEquals(m.getA2200Str(), null)) {
				cell9.setCellValue(Double.parseDouble(m.getA2200Str()));
			}

			XSSFCell cell10 = row.createCell((short) cellIndex + 10);
			cell10.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA2210Str(), "") && !ObjectUtils.nullSafeEquals(m.getA2210Str(), null)) {
				cell10.setCellValue(Double.parseDouble(m.getA2210Str()));
			}

			XSSFCell cell11 = row.createCell((short) cellIndex + 11);
			cell11.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA2220Str(), "") && !ObjectUtils.nullSafeEquals(m.getA2220Str(), null)) {
				cell11.setCellValue(Double.parseDouble(m.getA2220Str()));
			}

			XSSFCell cell12 = row.createCell((short) cellIndex + 12);
			cell12.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA3000Str(), null)) {
				cell12.setCellValue(Double.parseDouble(m.getA3000Str()));
			}

			XSSFCell cell13 = row.createCell((short) cellIndex + 13);
			cell13.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA3100Str(), "") && !ObjectUtils.nullSafeEquals(m.getA3100Str(), null)) {
				cell13.setCellValue(Double.parseDouble(m.getA3100Str()));
			}

			XSSFCell cell14 = row.createCell((short) cellIndex + 14);
			cell14.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA3200Str(), "") && !ObjectUtils.nullSafeEquals(m.getA3200Str(), null)) {
				cell14.setCellValue(Double.parseDouble(m.getA3200Str()));
			}

			XSSFCell cell15 = row.createCell((short) cellIndex + 15);
			cell15.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA4000Str(), null)) {
				cell15.setCellValue(Double.parseDouble(m.getA4000Str()));
			}


			XSSFCell cell16 = row.createCell((short) cellIndex + 16);
			cell16.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA4100Str(), "") && !ObjectUtils.nullSafeEquals(m.getA4100Str(), null)) {
				cell16.setCellValue(Double.parseDouble(m.getA4100Str()));
			}

			XSSFCell cell17 = row.createCell((short) cellIndex + 17);
			cell17.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA4200Str(), "") && !ObjectUtils.nullSafeEquals(m.getA4200Str(), null)) {
				cell17.setCellValue(Double.parseDouble(m.getA4200Str()));
			}

			XSSFCell cell18 = row.createCell((short) cellIndex + 18);
			cell18.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA4300Str(), "") && !ObjectUtils.nullSafeEquals(m.getA4300Str(), null)) {
				cell18.setCellValue(Double.parseDouble(m.getA4300Str()));
			}

			XSSFCell cell19 = row.createCell((short) cellIndex + 19);
			cell19.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA4400Str(), "") && !ObjectUtils.nullSafeEquals(m.getA4400Str(), null)) {
				cell19.setCellValue(Double.parseDouble(m.getA4400Str()));
			}

			XSSFCell cell20 = row.createCell((short) cellIndex + 20);
			cell20.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA4500Str(), "") && !ObjectUtils.nullSafeEquals(m.getA4500Str(), null)) {
				cell20.setCellValue(Double.parseDouble(m.getA4500Str()));
			}

			XSSFCell cell21 = row.createCell((short) cellIndex + 21);
			cell21.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA4600Str(), "") && !ObjectUtils.nullSafeEquals(m.getA4600Str(), null)) {
				cell21.setCellValue(Double.parseDouble(m.getA4600Str()));
			}

			XSSFCell cell22 = row.createCell((short) cellIndex + 22);
			cell22.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA4700Str(), "") && !ObjectUtils.nullSafeEquals(m.getA4700Str(), null)) {
				cell22.setCellValue(Double.parseDouble(m.getA4700Str()));
			}

			XSSFCell cell23 = row.createCell((short) cellIndex + 23);
			cell23.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA4800Str(), "") && !ObjectUtils.nullSafeEquals(m.getA4800Str(), null)) {
				cell23.setCellValue(Double.parseDouble(m.getA4800Str()));
			}

			XSSFCell cell24 = row.createCell((short) cellIndex + 24);
			cell24.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5000Str(), null)) {
				cell24.setCellValue(Double.parseDouble(m.getA5000Str()));
			}

			XSSFCell cell25 = row.createCell((short) cellIndex + 25);
			cell25.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5100Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5100Str(), null)) {
				cell25.setCellValue(Double.parseDouble(m.getA5100Str()));
			}

			XSSFCell cell26 = row.createCell((short) cellIndex + 26);
			cell26.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5200Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5200Str(), null)) {
				cell26.setCellValue(Double.parseDouble(m.getA5200Str()));
			}

			XSSFCell cell27 = row.createCell((short) cellIndex + 27);
			cell27.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5210Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5210Str(), null)) {
				cell27.setCellValue(Double.parseDouble(m.getA5210Str()));
			}

			XSSFCell cell28 = row.createCell((short) cellIndex + 28);
			cell28.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5220Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5220Str(), null)) {
				cell28.setCellValue(Double.parseDouble(m.getA5220Str()));
			}

			XSSFCell cell29 = row.createCell((short) cellIndex + 29);
			cell29.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5230Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5230Str(), null)) {
				cell29.setCellValue(Double.parseDouble(m.getA5230Str()));
			}

			XSSFCell cell30 = row.createCell((short) cellIndex + 30);
			cell30.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5240Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5240Str(), null)) {
				cell30.setCellValue(Double.parseDouble(m.getA5240Str()));
			}

			XSSFCell cell31 = row.createCell((short) cellIndex + 31);
			cell31.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5250Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5250Str(), null)) {
				cell31.setCellValue(Double.parseDouble(m.getA5250Str()));
			}

			XSSFCell cell32 = row.createCell((short) cellIndex + 32);
			cell32.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5260Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5260Str(), null)) {
				cell32.setCellValue(Double.parseDouble(m.getA5260Str()));
			}

			XSSFCell cell33 = row.createCell((short) cellIndex + 33);
			cell33.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5261Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5261Str(), null)) {
				cell33.setCellValue(Double.parseDouble(m.getA5261Str()));
			}

			XSSFCell cell34 = row.createCell((short) cellIndex + 34);
			cell34.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5270Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5270Str(), null)) {
				cell34.setCellValue(Double.parseDouble(m.getA5270Str()));
			}

			XSSFCell cell35 = row.createCell((short) cellIndex + 35);
			cell35.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5271Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5271Str(), null)) {
				cell35.setCellValue(Double.parseDouble(m.getA5271Str()));
			}

			XSSFCell cell36 = row.createCell((short) cellIndex + 36);
			cell36.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5272Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5272Str(), null)) {
				cell36.setCellValue(Double.parseDouble(m.getA5272Str()));
			}

			XSSFCell cell37 = row.createCell((short) cellIndex + 37);
			cell37.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5273Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5273Str(), null)) {
				cell37.setCellValue(Double.parseDouble(m.getA5273Str()));
			}

			XSSFCell cell38 = row.createCell((short) cellIndex + 38);
			cell38.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5274Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5274Str(), null)) {
				cell38.setCellValue(Double.parseDouble(m.getA5274Str()));
			}

			XSSFCell cell39 = row.createCell((short) cellIndex + 39);
			cell39.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5275Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5275Str(), null)) {
				cell39.setCellValue(Double.parseDouble(m.getA5275Str()));
			}

			XSSFCell cell40 = row.createCell((short) cellIndex + 40);
			cell40.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5276Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5276Str(), null)) {
				cell40.setCellValue(Double.parseDouble(m.getA5276Str()));
			}

			XSSFCell cell41 = row.createCell((short) cellIndex + 41);
			cell41.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5277Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5277Str(), null)) {
				cell41.setCellValue(Double.parseDouble(m.getA5277Str()));
			}

			XSSFCell cell42 = row.createCell((short) cellIndex + 42);
			cell42.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5278Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5278Str(), null)) {
				cell42.setCellValue(Double.parseDouble(m.getA5278Str()));
			}

			XSSFCell cell43 = row.createCell((short) cellIndex + 43);
			cell43.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5279Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5279Str(), null)) {
				cell43.setCellValue(Double.parseDouble(m.getA5279Str()));
			}

			XSSFCell cell44 = row.createCell((short) cellIndex + 44);
			cell44.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA527aStr(), "") && !ObjectUtils.nullSafeEquals(m.getA527aStr(), null)) {
				cell44.setCellValue(Double.parseDouble(m.getA527aStr()));
			}

			XSSFCell cell45 = row.createCell((short) cellIndex + 45);
			cell45.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA5280Str(), "") && !ObjectUtils.nullSafeEquals(m.getA5280Str(), null)) {
				cell45.setCellValue(Double.parseDouble(m.getA5280Str()));
			}

			XSSFCell cell46 = row.createCell((short) cellIndex + 46);
			cell46.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA6000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA6000Str(), null)) {
				cell46.setCellValue(Double.parseDouble(m.getA6000Str()));
			}

			XSSFCell cell47 = row.createCell((short) cellIndex + 47);
			cell47.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA6100Str(), "") && !ObjectUtils.nullSafeEquals(m.getA6100Str(), null)) {
				cell47.setCellValue(Double.parseDouble(m.getA6100Str()));
			}

			XSSFCell cell48 = row.createCell((short) cellIndex + 48);
			cell48.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA6200Str(), "") && !ObjectUtils.nullSafeEquals(m.getA6200Str(), null)) {
				cell48.setCellValue(Double.parseDouble(m.getA6200Str()));
			}

			XSSFCell cell49 = row.createCell((short) cellIndex + 49);
			cell49.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA6300Str(), "") && !ObjectUtils.nullSafeEquals(m.getA6300Str(), null)) {
				cell49.setCellValue(Double.parseDouble(m.getA6300Str()));
			}

			XSSFCell cell50 = row.createCell((short) cellIndex + 50);
			cell50.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA6400Str(), "") && !ObjectUtils.nullSafeEquals(m.getA6400Str(), null)) {
				cell50.setCellValue(Double.parseDouble(m.getA6400Str()));
			}

			XSSFCell cell51 = row.createCell((short) cellIndex + 51);
			cell51.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA6500Str(), "") && !ObjectUtils.nullSafeEquals(m.getA6500Str(), null)) {
				cell51.setCellValue(Double.parseDouble(m.getA6500Str()));
			}

			XSSFCell cell52 = row.createCell((short) cellIndex + 52);
			cell52.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA6600Str(), "") && !ObjectUtils.nullSafeEquals(m.getA6600Str(), null)) {
				cell52.setCellValue(Double.parseDouble(m.getA6600Str()));
			}

			XSSFCell cell53 = row.createCell((short) cellIndex + 53);
			cell53.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA6700Str(), "") && !ObjectUtils.nullSafeEquals(m.getA6700Str(), null)) {
				cell53.setCellValue(Double.parseDouble(m.getA6700Str()));
			}

			XSSFCell cell54 = row.createCell((short) cellIndex + 54);
			cell54.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7000Str(), null)) {
				cell54.setCellValue(Double.parseDouble(m.getA7000Str()));
			}

			XSSFCell cell55 = row.createCell((short) cellIndex + 55);
			cell55.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7100Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7100Str(), null)) {
				cell55.setCellValue(Double.parseDouble(m.getA7100Str()));
			}

			XSSFCell cell56 = row.createCell((short) cellIndex + 56);
			cell56.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7110Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7110Str(), null)) {
				cell56.setCellValue(Double.parseDouble(m.getA7110Str()));
			}

			XSSFCell cell57 = row.createCell((short) cellIndex + 57);
			cell57.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7120Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7120Str(), null)) {
				cell57.setCellValue(Double.parseDouble(m.getA7120Str()));
			}

			XSSFCell cell58 = row.createCell((short) cellIndex + 58);
			cell58.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7200Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7200Str(), null)) {
				cell58.setCellValue(Double.parseDouble(m.getA7200Str()));
			}

			XSSFCell cell59 = row.createCell((short) cellIndex + 59);
			cell59.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7210Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7210Str(), null)) {
				cell59.setCellValue(Double.parseDouble(m.getA7210Str()));
			}

			XSSFCell cell60 = row.createCell((short) cellIndex + 60);
			cell60.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7220Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7220Str(), null)) {
				cell60.setCellValue(Double.parseDouble(m.getA7220Str()));
			}

			XSSFCell cell61 = row.createCell((short) cellIndex + 61);
			cell61.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7230Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7230Str(), null)) {
				cell61.setCellValue(Double.parseDouble(m.getA7230Str()));
			}

			XSSFCell cell62 = row.createCell((short) cellIndex + 62);
			cell62.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7240Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7240Str(), null)) {
				cell62.setCellValue(Double.parseDouble(m.getA7240Str()));
			}

			XSSFCell cell63 = row.createCell((short) cellIndex + 63);
			cell63.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7250Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7250Str(), null)) {
				cell63.setCellValue(Double.parseDouble(m.getA7250Str()));
			}

			XSSFCell cell64 = row.createCell((short) cellIndex + 64);
			cell64.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7260Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7260Str(), null)) {
				cell64.setCellValue(Double.parseDouble(m.getA7260Str()));
			}

			XSSFCell cell65 = row.createCell((short) cellIndex + 65);
			cell65.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7270Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7270Str(), null)) {
				cell65.setCellValue(Double.parseDouble(m.getA7270Str()));
			}

			XSSFCell cell66 = row.createCell((short) cellIndex + 66);
			cell66.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7280Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7280Str(), null)) {
				cell66.setCellValue(Double.parseDouble(m.getA7280Str()));
			}

			XSSFCell cell67 = row.createCell((short) cellIndex + 67);
			cell67.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7290Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7290Str(), null)) {
				cell67.setCellValue(Double.parseDouble(m.getA7290Str()));
			}

			XSSFCell cell68 = row.createCell((short) cellIndex + 68);
			cell68.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA72a0Str(), "") && !ObjectUtils.nullSafeEquals(m.getA72a0Str(), null)) {
				cell68.setCellValue(Double.parseDouble(m.getA72a0Str()));
			}

			XSSFCell cell69 = row.createCell((short) cellIndex + 69);
			cell69.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7300Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7300Str(), null)) {
				cell69.setCellValue(Double.parseDouble(m.getA7300Str()));
			}

			XSSFCell cell70 = row.createCell((short) cellIndex + 70);
			cell70.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7310Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7310Str(), null)) {
				cell70.setCellValue(Double.parseDouble(m.getA7310Str()));
			}

			XSSFCell cell71 = row.createCell((short) cellIndex + 71);
			cell71.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7320Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7320Str(), null)) {
				cell71.setCellValue(Double.parseDouble(m.getA7320Str()));
			}

			XSSFCell cell72 = row.createCell((short) cellIndex + 72);
			cell72.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA7400Str(), "") && !ObjectUtils.nullSafeEquals(m.getA7400Str(), null)) {
				cell72.setCellValue(Double.parseDouble(m.getA7400Str()));
			}

			XSSFCell cell73 = row.createCell((short) cellIndex + 73);
			cell73.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA8000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA8000Str(), null)) {
				cell73.setCellValue(Double.parseDouble(m.getA8000Str()));
			}

			XSSFCell cell74 = row.createCell((short) cellIndex + 74);
			cell74.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA8100Str(), "") && !ObjectUtils.nullSafeEquals(m.getA8100Str(), null)) {
				cell74.setCellValue(Double.parseDouble(m.getA8100Str()));
			}

			XSSFCell cell75 = row.createCell((short) cellIndex + 75);
			cell75.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA8200Str(), "") && !ObjectUtils.nullSafeEquals(m.getA8200Str(), null)) {
				cell75.setCellValue(Double.parseDouble(m.getA8200Str()));
			}

			XSSFCell cell76 = row.createCell((short) cellIndex + 76);
			cell76.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getA9000Str(), "") && !ObjectUtils.nullSafeEquals(m.getA9000Str(), null)) {
				cell76.setCellValue(Double.parseDouble(m.getA9000Str()));
			}

			XSSFCell cell77 = row.createCell((short) cellIndex + 77);
			cell77.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getAa000Str(), "") && !ObjectUtils.nullSafeEquals(m.getAa000Str(), null)) {
				cell77.setCellValue(Double.parseDouble(m.getAa000Str()));
			}

			XSSFCell cell78 = row.createCell((short) cellIndex + 78);
			cell78.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getAb000Str(), "") && !ObjectUtils.nullSafeEquals(m.getAb000Str(), null)) {
				cell78.setCellValue(Double.parseDouble(m.getAb000Str()));
			}

			XSSFCell cell79 = row.createCell((short) cellIndex + 79);
			cell79.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getAc000Str(), "") && !ObjectUtils.nullSafeEquals(m.getAc000Str(), null)) {
				cell79.setCellValue(Double.parseDouble(m.getAc000Str()));
			}

			XSSFCell cell80 = row.createCell((short) cellIndex + 80);
			cell80.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getD0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getD0000Str(), null)) {
				cell80.setCellValue(Double.parseDouble(m.getD0000Str()));
			}

			XSSFCell cell81 = row.createCell((short) cellIndex + 81);
			cell81.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getB0000Str(), null)) {
				cell81.setCellValue(Double.parseDouble(m.getB0000Str()));
			}

			XSSFCell cell82 = row.createCell((short) cellIndex + 82);
			cell82.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1000Str(), null)) {
				cell82.setCellValue(Double.parseDouble(m.getB1000Str()));
			}

			XSSFCell cell83 = row.createCell((short) cellIndex + 83);
			cell83.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1100Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1100Str(), null)) {
				cell83.setCellValue(Double.parseDouble(m.getB1100Str()));
			}

			XSSFCell cell84 = row.createCell((short) cellIndex + 84);
			cell84.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1200Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1200Str(), null)) {
				cell84.setCellValue(Double.parseDouble(m.getB1200Str()));
			}

			XSSFCell cell85 = row.createCell((short) cellIndex + 85);
			cell85.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1210Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1210Str(), null)) {
				cell85.setCellValue(Double.parseDouble(m.getB1210Str()));
			}

			XSSFCell cell86 = row.createCell((short) cellIndex + 86);
			cell86.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1220Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1220Str(), null)) {
				cell86.setCellValue(Double.parseDouble(m.getB1220Str()));
			}

			XSSFCell cell87 = row.createCell((short) cellIndex + 87);
			cell87.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1230Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1230Str(), null)) {
				cell87.setCellValue(Double.parseDouble(m.getB1230Str()));
			}

			XSSFCell cell88 = row.createCell((short) cellIndex + 88);
			cell88.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1240Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1240Str(), null)) {
				cell88.setCellValue(Double.parseDouble(m.getB1240Str()));
			}

			XSSFCell cell89 = row.createCell((short) cellIndex + 89);
			cell89.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1250Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1250Str(), null)) {
				cell89.setCellValue(Double.parseDouble(m.getB1250Str()));
			}

			XSSFCell cell90 = row.createCell((short) cellIndex + 90);
			cell90.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1260Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1260Str(), null)) {
				cell90.setCellValue(Double.parseDouble(m.getB1260Str()));
			}

			XSSFCell cell91 = row.createCell((short) cellIndex + 91);
			cell91.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1261Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1261Str(), null)) {
				cell91.setCellValue(Double.parseDouble(m.getB1261Str()));
			}

			XSSFCell cell92 = row.createCell((short) cellIndex + 92);
			cell92.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1270Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1270Str(), null)) {
				cell92.setCellValue(Double.parseDouble(m.getB1270Str()));
			}

			XSSFCell cell93 = row.createCell((short) cellIndex + 93);
			cell93.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1271Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1271Str(), null)) {
				cell93.setCellValue(Double.parseDouble(m.getB1271Str()));
			}

			XSSFCell cell94 = row.createCell((short) cellIndex + 94);
			cell94.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1272Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1272Str(), null)) {
				cell94.setCellValue(Double.parseDouble(m.getB1272Str()));
			}

			XSSFCell cell95 = row.createCell((short) cellIndex + 95);
			cell95.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1273Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1273Str(), null)) {
				cell95.setCellValue(Double.parseDouble(m.getB1273Str()));
			}

			XSSFCell cell96 = row.createCell((short) cellIndex + 96);
			cell96.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1274Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1274Str(), null)) {
				cell96.setCellValue(Double.parseDouble(m.getB1274Str()));
			}

			XSSFCell cell97 = row.createCell((short) cellIndex + 97);
			cell97.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1275Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1275Str(), null)) {
				cell97.setCellValue(Double.parseDouble(m.getB1275Str()));
			}

			XSSFCell cell98 = row.createCell((short) cellIndex + 98);
			cell98.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1276Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1276Str(), null)) {
				cell98.setCellValue(Double.parseDouble(m.getB1276Str()));
			}

			XSSFCell cell99 = row.createCell((short) cellIndex + 99);
			cell99.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1277Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1277Str(), null)) {
				cell99.setCellValue(Double.parseDouble(m.getB1277Str()));
			}

			XSSFCell cell100 = row.createCell((short) cellIndex + 100);
			cell100.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1278Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1278Str(), null)) {
				cell100.setCellValue(Double.parseDouble(m.getB1278Str()));
			}

			XSSFCell cell101 = row.createCell((short) cellIndex + 101);
			cell101.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1279Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1279Str(), null)) {
				cell101.setCellValue(Double.parseDouble(m.getB1279Str()));
			}

			XSSFCell cell102 = row.createCell((short) cellIndex + 102);
			cell102.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB127aStr(), "") && !ObjectUtils.nullSafeEquals(m.getB127aStr(), null)) {
				cell102.setCellValue(Double.parseDouble(m.getB127aStr()));
			}

			XSSFCell cell103 = row.createCell((short) cellIndex + 103);
			cell103.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB1280Str(), "") && !ObjectUtils.nullSafeEquals(m.getB1280Str(), null)) {
				cell103.setCellValue(Double.parseDouble(m.getB1280Str()));
			}

			XSSFCell cell104 = row.createCell((short) cellIndex + 104);
			cell104.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getB2000Str(), null)) {
				cell104.setCellValue(Double.parseDouble(m.getB2000Str()));
			}

			XSSFCell cell105 = row.createCell((short) cellIndex + 105);
			cell105.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getB3000Str(), null)) {
				cell105.setCellValue(Double.parseDouble(m.getB3000Str()));
			}

			XSSFCell cell106 = row.createCell((short) cellIndex + 106);
			cell106.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB3100Str(), "") && !ObjectUtils.nullSafeEquals(m.getB3100Str(), null)) {
				cell106.setCellValue(Double.parseDouble(m.getB3100Str()));
			}

			XSSFCell cell107 = row.createCell((short) cellIndex + 107);
			cell107.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB3200Str(), "") && !ObjectUtils.nullSafeEquals(m.getB3200Str(), null)) {
				cell107.setCellValue(Double.parseDouble(m.getB3200Str()));
			}

			XSSFCell cell108 = row.createCell((short) cellIndex + 108);
			cell108.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getB4000Str(), null)) {
				cell108.setCellValue(Double.parseDouble(m.getB4000Str()));
			}

			XSSFCell cell109 = row.createCell((short) cellIndex + 109);
			cell109.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getB5000Str(), "") && !ObjectUtils.nullSafeEquals(m.getB5000Str(), null)) {
				cell109.setCellValue(Double.parseDouble(m.getB5000Str()));
			}

			XSSFCell cell110 = row.createCell((short) cellIndex + 110);
			cell110.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC0000Str(), null)) {
				cell110.setCellValue(Double.parseDouble(m.getC0000Str()));
			}

			XSSFCell cell111 = row.createCell((short) cellIndex + 111);
			cell111.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1000Str(), null)) {
				cell111.setCellValue(Double.parseDouble(m.getC1000Str()));
			}

			XSSFCell cell112 = row.createCell((short) cellIndex + 112);
			cell112.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1100Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1100Str(), null)) {
				cell112.setCellValue(Double.parseDouble(m.getC1100Str()));
			}

			XSSFCell cell113 = row.createCell((short) cellIndex + 113);
			cell113.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1110Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1110Str(), null)) {
				cell113.setCellValue(Double.parseDouble(m.getC1110Str()));
			}

			XSSFCell cell114 = row.createCell((short) cellIndex + 114);
			cell114.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1120Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1120Str(), null)) {
				cell114.setCellValue(Double.parseDouble(m.getC1120Str()));
			}

			XSSFCell cell115 = row.createCell((short) cellIndex + 115);
			cell115.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1130Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1130Str(), null)) {
				cell115.setCellValue(Double.parseDouble(m.getC1130Str()));
			}

			XSSFCell cell116 = row.createCell((short) cellIndex + 116);
			cell116.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1140Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1140Str(), null)) {
				cell116.setCellValue(Double.parseDouble(m.getC1140Str()));
			}

			XSSFCell cell117 = row.createCell((short) cellIndex + 117);
			cell117.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1150Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1150Str(), null)) {
				cell117.setCellValue(Double.parseDouble(m.getC1150Str()));
			}

			XSSFCell cell118 = row.createCell((short) cellIndex + 118);
			cell118.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1160Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1160Str(), null)) {
				cell118.setCellValue(Double.parseDouble(m.getC1160Str()));
			}

			XSSFCell cell119 = row.createCell((short) cellIndex + 119);
			cell119.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1170Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1170Str(), null)) {
				cell119.setCellValue(Double.parseDouble(m.getC1170Str()));
			}

			XSSFCell cell120 = row.createCell((short) cellIndex + 120);
			cell120.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1171Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1171Str(), null)) {
				cell120.setCellValue(Double.parseDouble(m.getC1171Str()));
			}

			XSSFCell cell121 = row.createCell((short) cellIndex + 121);
			cell121.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1172Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1172Str(), null)) {
				cell121.setCellValue(Double.parseDouble(m.getC1172Str()));
			}

			XSSFCell cell122 = row.createCell((short) cellIndex + 122);
			cell122.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1173Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1173Str(), null)) {
				cell122.setCellValue(Double.parseDouble(m.getC1173Str()));
			}

			XSSFCell cell123 = row.createCell((short) cellIndex + 123);
			cell123.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1174Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1174Str(), null)) {
				cell123.setCellValue(Double.parseDouble(m.getC1174Str()));
			}

			XSSFCell cell124 = row.createCell((short) cellIndex + 124);
			cell124.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1175Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1175Str(), null)) {
				cell124.setCellValue(Double.parseDouble(m.getC1175Str()));
			}

			XSSFCell cell125 = row.createCell((short) cellIndex + 125);
			cell125.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1176Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1176Str(), null)) {
				cell125.setCellValue(Double.parseDouble(m.getC1176Str()));
			}

			XSSFCell cell126 = row.createCell((short) cellIndex + 126);
			cell126.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1177Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1177Str(), null)) {
				cell126.setCellValue(Double.parseDouble(m.getC1177Str()));
			}

			XSSFCell cell127 = row.createCell((short) cellIndex + 127);
			cell127.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1178Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1178Str(), null)) {
				cell127.setCellValue(Double.parseDouble(m.getC1178Str()));
			}

			XSSFCell cell128 = row.createCell((short) cellIndex + 128);
			cell128.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1179Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1179Str(), null)) {
				cell128.setCellValue(Double.parseDouble(m.getC1179Str()));
			}

			XSSFCell cell129 = row.createCell((short) cellIndex + 129);
			cell129.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC117aStr(), "") && !ObjectUtils.nullSafeEquals(m.getC117aStr(), null)) {
				cell129.setCellValue(Double.parseDouble(m.getC117aStr()));
			}

			XSSFCell cell130 = row.createCell((short) cellIndex + 130);
			cell130.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1180Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1180Str(), null)) {
				cell130.setCellValue(Double.parseDouble(m.getC1180Str()));
			}

			XSSFCell cell131 = row.createCell((short) cellIndex + 131);
			cell131.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1200Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1200Str(), null)) {
				cell131.setCellValue(Double.parseDouble(m.getC1200Str()));
			}

			XSSFCell cell132 = row.createCell((short) cellIndex + 132);
			cell132.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1210Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1210Str(), null)) {
				cell132.setCellValue(Double.parseDouble(m.getC1210Str()));
			}

			XSSFCell cell133 = row.createCell((short) cellIndex + 133);
			cell133.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1220Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1220Str(), null)) {
				cell133.setCellValue(Double.parseDouble(m.getC1220Str()));
			}

			XSSFCell cell134 = row.createCell((short) cellIndex + 134);
			cell134.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1230Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1230Str(), null)) {
				cell134.setCellValue(Double.parseDouble(m.getC1230Str()));
			}

			XSSFCell cell135 = row.createCell((short) cellIndex + 135);
			cell135.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1240Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1240Str(), null)) {
				cell135.setCellValue(Double.parseDouble(m.getC1240Str()));
			}

			XSSFCell cell136 = row.createCell((short) cellIndex + 136);
			cell136.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1250Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1250Str(), null)) {
				cell136.setCellValue(Double.parseDouble(m.getC1250Str()));
			}

			XSSFCell cell137 = row.createCell((short) cellIndex + 137);
			cell137.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1260Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1260Str(), null)) {
				cell137.setCellValue(Double.parseDouble(m.getC1260Str()));
			}

			XSSFCell cell138 = row.createCell((short) cellIndex + 138);
			cell138.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1270Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1270Str(), null)) {
				cell138.setCellValue(Double.parseDouble(m.getC1270Str()));
			}

			XSSFCell cell139 = row.createCell((short) cellIndex + 139);
			cell139.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1271Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1271Str(), null)) {
				cell139.setCellValue(Double.parseDouble(m.getC1271Str()));
			}

			XSSFCell cell140 = row.createCell((short) cellIndex + 140);
			cell140.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1272Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1272Str(), null)) {
				cell140.setCellValue(Double.parseDouble(m.getC1272Str()));
			}

			XSSFCell cell141 = row.createCell((short) cellIndex + 141);
			cell141.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1273Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1273Str(), null)) {
				cell141.setCellValue(Double.parseDouble(m.getC1273Str()));
			}

			XSSFCell cell142 = row.createCell((short) cellIndex + 142);
			cell142.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1274Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1274Str(), null)) {
				cell142.setCellValue(Double.parseDouble(m.getC1274Str()));
			}

			XSSFCell cell143 = row.createCell((short) cellIndex + 143);
			cell143.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1275Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1275Str(), null)) {
				cell143.setCellValue(Double.parseDouble(m.getC1275Str()));
			}

			XSSFCell cell144 = row.createCell((short) cellIndex + 144);
			cell144.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1276Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1276Str(), null)) {
				cell144.setCellValue(Double.parseDouble(m.getC1276Str()));
			}

			XSSFCell cell145 = row.createCell((short) cellIndex + 145);
			cell145.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1277Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1277Str(), null)) {
				cell145.setCellValue(Double.parseDouble(m.getC1277Str()));
			}

			XSSFCell cell146 = row.createCell((short) cellIndex + 146);
			cell146.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1278Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1278Str(), null)) {
				cell146.setCellValue(Double.parseDouble(m.getC1278Str()));
			}

			XSSFCell cell147 = row.createCell((short) cellIndex + 147);
			cell147.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1279Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1279Str(), null)) {
				cell147.setCellValue(Double.parseDouble(m.getC1279Str()));
			}

			XSSFCell cell148 = row.createCell((short) cellIndex + 148);
			cell148.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC127aStr(), "") && !ObjectUtils.nullSafeEquals(m.getC127aStr(), null)) {
				cell148.setCellValue(Double.parseDouble(m.getC127aStr()));
			}

			XSSFCell cell149 = row.createCell((short) cellIndex + 149);
			cell149.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC1280Str(), "") && !ObjectUtils.nullSafeEquals(m.getC1280Str(), null)) {
				cell149.setCellValue(Double.parseDouble(m.getC1280Str()));
			}

			XSSFCell cell150 = row.createCell((short) cellIndex + 150);
			cell150.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC3000Str(), null)) {
				cell150.setCellValue(Double.parseDouble(m.getC3000Str()));
			}

			XSSFCell cell151 = row.createCell((short) cellIndex + 151);
			cell151.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC4000Str(), null)) {
				cell151.setCellValue(Double.parseDouble(m.getC4000Str()));
			}

			index++;
		}

	}
}
