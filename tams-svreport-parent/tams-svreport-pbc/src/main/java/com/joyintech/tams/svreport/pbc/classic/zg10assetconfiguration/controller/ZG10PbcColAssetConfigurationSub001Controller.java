package com.joyintech.tams.svreport.pbc.classic.zg10assetconfiguration.controller;

import com.google.common.collect.Lists;
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
import com.joyintech.tams.svreport.pbc.classic.zg10assetconfiguration.service.ZG10PbcColAssetConfigurationSubMService;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto.ZG10PbcColReportDto;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.service.ZG10PbcColAssetConfigurationSubService;
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
@PageId("ZG10PbcColAssetConfigurationSub")
@RequestMapping("/pbc/zg10assetconfiguration/ZG10PbcColAssetConfigurationSub")
public class ZG10PbcColAssetConfigurationSub001Controller extends AbstractCustomerDataExport<ZG10PbcColReportDto> {
    
    /**
     * 微服务service
     */
    @Autowired
    private ZG10PbcColAssetConfigurationSubMService zg10PbcColAssetConfigurationSubMService;

	@Autowired
	private ZG10PbcColAssetConfigurationSubService zG10PbcColAssetConfigurationSubService;


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
	public Page<ZG10PbcColReportDto> searchPage() {
		Map<String, Object> searchParam = RequestUtil.getPageParam();

		Page<ZG10PbcColReportDto> page = zg10PbcColAssetConfigurationSubMService.searchPage(searchParam);

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
		return zg10PbcColAssetConfigurationSubMService.reportCheck(RequestUtil.getPageParam());
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
		List<ZG10PbcColReportDto> dtoList;
		String issuePartyId =  CommonUtil.selectPbcBankCode(null);

		if (!StringUtil.isNotEmpty(datas)) {
			//则默认根据当前查询条件对系统中报送状态为“未报送”的数据进行检索导出
			dtoList = zg10PbcColAssetConfigurationSubMService.searchByExcel(paraMap);
		} else {
			List<String> list = Arrays.asList(datas.split(","));
			dtoList = zg10PbcColAssetConfigurationSubMService.searchByUuid(list);
		}

		File outFile = zG10PbcColAssetConfigurationSubService.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/zg10assetconfiguration/conf/export_conf.xml",
				dtoList);
		String termid=null;
		if(dtoList !=null && dtoList.size()>0){
			termid=dtoList.get(0).getTermid();
		}else {
			termid=(String) paraMap.get("enddate");

		}
		String date= DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(termid.length()-2)),"yyyyMMdd");
		String excelFileName = issuePartyId + GlobalConstant.UNDER_LINE + "ZG10" + GlobalConstant.UNDER_LINE
				+ date
				+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

		Integer exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "10" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

		List<List<ZG10PbcColReportDto>> dtoLists = Lists.partition(dtoList, exportCountPerSheet);

//		return ExportUtil.export(issuePartyId+GlobalConstant.UNDER_LINE+"ZG10"+GlobalConstant.UNDER_LINE
//						+ date
//						+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
//				outFile, true);

		OutputStream out = null;
		try {
			out = getOutputStream(response, excelFileName);
			String[] headers = { "发行机构代码","产品品种","h10000_一、债务证券（按评级分类）","h11000_其中：政策性金融债券","h11100_AA+（含）以上","h11200_AA+以下","h11300_无评级","h12000_其他金融债券","h12100_AA+（含）以上","h12200_AA+以下","h12300_无评级","h13000_企业债券","h13100_AA+（含）以上","h13200_AA+以下","h13300_无评级","h14000_资产支持证券","h14100_AA+（含）以上","h14200_AA+以下","h14300_无评级","h20000_二、企业债券按券种分类","h21000_其中：企业债","h22000_公司债","h23000_非金融企业债务融资工具","h30000_三、银行资本补充工具合计","h31000_其中：优先股","h32000_永续债（债性）","h33000_永续债（股性）","h34000_二级资本债","h40000_四、债券逆回购","h41000_住户","h42000_广义政府","h43000_非金融企业","h44000_银行业存款类金融机构","h45000_银行业非存款类金融机构","h46000_非银行业金融机构","h46100_其中：中央交易对手方","h47000_特定目的载体","h47100_银行非保本理财","h47200_信托公司资管产品","h47300_证券公司及其子公司资管产品","h47400_基金管理公司及其子公司专户","h47500_期货公司及其子公司资管产品","h47600_保险资管产品","h47700_金融资产投资公司资管产品","h47800_公募基金","h47900_私募机构私募基金","h47a00_其他特定目的载体","h48000_境外","h50000_五、资产减值准备","h51000_存款","h52000_可转让存单","h53000_债务证券","h54000_除回购和拆借外贷款","h55000_回购和拆借","h56000_资产收益权","h57000_股权及特定目的载体份额","h58000_金融衍生品","h59000_应收账款","h5a000_其他"};

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
		zg10PbcColAssetConfigurationSubMService.updateSumStaus(map);
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
		String result = zg10PbcColAssetConfigurationSubMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}


	@Override
	protected void handleCellData(List<ZG10PbcColReportDto> lists, XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFCellStyle style2 = ExportExcelUtil.getXSSFCellStyle(workbook, "2");
		XSSFCellStyle styleText = ExportExcelUtil.getXSSFCellStyle(workbook, "text");

		// 从第二行开始输出数据
		int index = 1;
		for (ZG10PbcColReportDto m : lists) {
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
			if (!ObjectUtils.nullSafeEquals(m.geth10000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth10000Str(), null)) {
				cell2.setCellValue(Double.parseDouble(m.geth10000Str()));
			}

			XSSFCell cell3 = row.createCell((short) cellIndex + 3);
			cell3.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth11000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth11000Str(), null)) {
				cell3.setCellValue(Double.parseDouble(m.geth11000Str()));

			}

			XSSFCell cell4 = row.createCell((short) cellIndex + 4);
			cell4.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth11100Str(), "") && !ObjectUtils.nullSafeEquals(m.geth11100Str(), null)) {
				cell4.setCellValue(Double.parseDouble(m.geth11100Str()));
			}

			XSSFCell cell5 = row.createCell((short) cellIndex + 5);
			cell5.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth11200Str(), "") && !ObjectUtils.nullSafeEquals(m.geth11200Str(), null)) {
				cell5.setCellValue(Double.parseDouble(m.geth11200Str()));
			}

			XSSFCell cell6 = row.createCell((short) cellIndex + 6);
			cell6.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth11300Str(), "") && !ObjectUtils.nullSafeEquals(m.geth11300Str(), null)) {
				cell6.setCellValue(Double.parseDouble(m.geth11300Str()));
			}

			XSSFCell cell7 = row.createCell((short) cellIndex + 7);
			cell7.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth12000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth12000Str(), null)) {
				cell7.setCellValue(Double.parseDouble(m.geth12000Str()));
			}

			XSSFCell cell8 = row.createCell((short) cellIndex + 8);
			cell8.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth12100Str(), "") && !ObjectUtils.nullSafeEquals(m.geth12100Str(), null)) {
				cell8.setCellValue(Double.parseDouble(m.geth12100Str()));
			}

			XSSFCell cell9 = row.createCell((short) cellIndex + 9);
			cell9.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth12200Str(), "") && !ObjectUtils.nullSafeEquals(m.geth12200Str(), null)) {
				cell9.setCellValue(Double.parseDouble(m.geth12200Str()));
			}

			XSSFCell cell10 = row.createCell((short) cellIndex + 10);
			cell10.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth12300Str(), "") && !ObjectUtils.nullSafeEquals(m.geth12300Str(), null)) {
				cell10.setCellValue(Double.parseDouble(m.geth12300Str()));
			}

			XSSFCell cell11 = row.createCell((short) cellIndex + 11);
			cell11.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth13000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth13000Str(), null)) {
				cell11.setCellValue(Double.parseDouble(m.geth13000Str()));
			}

			XSSFCell cell12 = row.createCell((short) cellIndex + 12);
			cell12.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth13100Str(), "") && !ObjectUtils.nullSafeEquals(m.geth13100Str(), null)) {
				cell12.setCellValue(Double.parseDouble(m.geth13100Str()));
			}

			XSSFCell cell13 = row.createCell((short) cellIndex + 13);
			cell13.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth13200Str(), "") && !ObjectUtils.nullSafeEquals(m.geth13200Str(), null)) {
				cell13.setCellValue(Double.parseDouble(m.geth13200Str()));
			}

			XSSFCell cell14 = row.createCell((short) cellIndex + 14);
			cell14.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth13300Str(), "") && !ObjectUtils.nullSafeEquals(m.geth13300Str(), null)) {
				cell14.setCellValue(Double.parseDouble(m.geth13300Str()));
			}

			XSSFCell cell15 = row.createCell((short) cellIndex + 15);
			cell15.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth14000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth14000Str(), null)) {
				cell15.setCellValue(Double.parseDouble(m.geth14000Str()));
			}

			XSSFCell cell16 = row.createCell((short) cellIndex + 16);
			cell16.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth14100Str(), "") && !ObjectUtils.nullSafeEquals(m.geth14100Str(), null)) {
				cell16.setCellValue(Double.parseDouble(m.geth14100Str()));
			}

			XSSFCell cell17 = row.createCell((short) cellIndex + 17);
			cell17.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth14200Str(), "") && !ObjectUtils.nullSafeEquals(m.geth14200Str(), null)) {
				cell17.setCellValue(Double.parseDouble(m.geth14200Str()));
			}

			XSSFCell cell18 = row.createCell((short) cellIndex + 18);
			cell18.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth14300Str(), "") && !ObjectUtils.nullSafeEquals(m.geth14300Str(), null)) {
				cell18.setCellValue(Double.parseDouble(m.geth14300Str()));
			}

			XSSFCell cell19 = row.createCell((short) cellIndex + 19);
			cell19.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth20000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth20000Str(), null)) {
				cell19.setCellValue(Double.parseDouble(m.geth20000Str()));
			}

			XSSFCell cell20 = row.createCell((short) cellIndex + 20);
			cell20.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth21000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth21000Str(), null)) {
				cell20.setCellValue(Double.parseDouble(m.geth21000Str()));
			}

			XSSFCell cell21 = row.createCell((short) cellIndex + 21);
			cell21.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth22000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth22000Str(), null)) {
				cell21.setCellValue(m.geth22000Str());
			}

			XSSFCell cell22 = row.createCell((short) cellIndex + 22);
			cell22.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth23000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth23000Str(), null)) {
				cell22.setCellValue(Double.parseDouble(m.geth23000Str()));
			}

			XSSFCell cell23 = row.createCell((short) cellIndex + 23);
			cell23.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth30000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth30000Str(), null)) {
				cell23.setCellValue(Double.parseDouble(m.geth30000Str()));
			}

			XSSFCell cell24 = row.createCell((short) cellIndex + 24);
			cell24.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth31000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth31000Str(), null)) {
				cell24.setCellValue(Double.parseDouble(m.geth31000Str()));
			}

			XSSFCell cell25 = row.createCell((short) cellIndex + 25);
			cell25.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth32000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth32000Str(), null)) {
				cell25.setCellValue(Double.parseDouble(m.geth32000Str()));
			}

			XSSFCell cell26 = row.createCell((short) cellIndex + 26);
			cell26.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth33000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth33000Str(), null)) {
				cell26.setCellValue(Double.parseDouble(m.geth33000Str()));
			}

			XSSFCell cell27 = row.createCell((short) cellIndex + 27);
			cell27.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth34000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth34000Str(), null)) {
				cell27.setCellValue(Double.parseDouble(m.geth34000Str()));
			}

			XSSFCell cell28 = row.createCell((short) cellIndex + 28);
			cell28.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth40000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth40000Str(), null)) {
				cell28.setCellValue(Double.parseDouble(m.geth40000Str()));
			}

			XSSFCell cell29 = row.createCell((short) cellIndex + 29);
			cell29.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth41000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth41000Str(), null)) {
				cell29.setCellValue(Double.parseDouble(m.geth41000Str()));
			}

			XSSFCell cell30 = row.createCell((short) cellIndex + 30);
			cell30.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth42000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth42000Str(), null)) {
				cell30.setCellValue(Double.parseDouble(m.geth42000Str()));
			}

			XSSFCell cell31 = row.createCell((short) cellIndex + 31);
			cell31.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth43000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth43000Str(), null)) {
				cell31.setCellValue(Double.parseDouble(m.geth43000Str()));
			}

			XSSFCell cell32 = row.createCell((short) cellIndex + 32);
			cell32.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth44000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth44000Str(), null)) {
				cell32.setCellValue(Double.parseDouble(m.geth44000Str()));
			}

			XSSFCell cell33 = row.createCell((short) cellIndex + 33);
			cell33.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth45000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth45000Str(), null)) {
				cell33.setCellValue(Double.parseDouble(m.geth45000Str()));
			}

			XSSFCell cell34 = row.createCell((short) cellIndex + 34);
			cell34.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth46000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth46000Str(), null)) {
				cell34.setCellValue(Double.parseDouble(m.geth46000Str()));
			}

			XSSFCell cell35 = row.createCell((short) cellIndex + 35);
			cell35.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth46100Str(), "") && !ObjectUtils.nullSafeEquals(m.geth46100Str(), null)) {
				cell35.setCellValue(Double.parseDouble(m.geth46100Str()));
			}

			XSSFCell cell36 = row.createCell((short) cellIndex + 36);
			cell36.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47000Str(), null)) {
				cell36.setCellValue(Double.parseDouble(m.geth47000Str()));
			}

			XSSFCell cell37 = row.createCell((short) cellIndex + 37);
			cell37.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47100Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47100Str(), null)) {
				cell37.setCellValue(Double.parseDouble(m.geth47100Str()));
			}

			XSSFCell cell38 = row.createCell((short) cellIndex + 38);
			cell38.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47200Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47200Str(), null)) {
				cell38.setCellValue(Double.parseDouble(m.geth47200Str()));
			}

			XSSFCell cell39 = row.createCell((short) cellIndex + 39);
			cell39.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47300Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47300Str(), null)) {
				cell39.setCellValue(Double.parseDouble(m.geth47300Str()));
			}

			XSSFCell cell40 = row.createCell((short) cellIndex + 40);
			cell40.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47400Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47400Str(), null)) {
				cell40.setCellValue(Double.parseDouble(m.geth47400Str()));
			}

			XSSFCell cell41 = row.createCell((short) cellIndex + 41);
			cell41.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47500Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47500Str(), null)) {
				cell41.setCellValue(Double.parseDouble(m.geth47500Str()));
			}

			XSSFCell cell42 = row.createCell((short) cellIndex + 42);
			cell42.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47600Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47600Str(), null)) {
				cell42.setCellValue(Double.parseDouble(m.geth47600Str()));
			}

			XSSFCell cell43 = row.createCell((short) cellIndex + 43);
			cell43.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47700Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47700Str(), null)) {
				cell43.setCellValue(Double.parseDouble(m.geth47700Str()));
			}

			XSSFCell cell44 = row.createCell((short) cellIndex + 44);
			cell44.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47800Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47800Str(), null)) {
				cell44.setCellValue(Double.parseDouble(m.geth47800Str()));
			}

			XSSFCell cell45 = row.createCell((short) cellIndex + 45);
			cell45.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47900Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47900Str(), null)) {
				cell45.setCellValue(Double.parseDouble(m.geth47900Str()));
			}

			XSSFCell cell46 = row.createCell((short) cellIndex + 46);
			cell46.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth47a00Str(), "") && !ObjectUtils.nullSafeEquals(m.geth47a00Str(), null)) {
				cell46.setCellValue(Double.parseDouble(m.geth47a00Str()));
			}

			XSSFCell cell47 = row.createCell((short) cellIndex + 47);
			cell47.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth48000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth48000Str(), null)) {
				cell47.setCellValue(Double.parseDouble(m.geth48000Str()));
			}

			XSSFCell cell48 = row.createCell((short) cellIndex + 48);
			cell48.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth50000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth50000Str(), null)) {
				cell48.setCellValue(Double.parseDouble(m.geth50000Str()));
			}

			XSSFCell cell49 = row.createCell((short) cellIndex + 49);
			cell49.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth51000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth51000Str(), null)) {
				cell49.setCellValue(Double.parseDouble(m.geth51000Str()));
			}

			XSSFCell cell50 = row.createCell((short) cellIndex + 50);
			cell50.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth52000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth52000Str(), null)) {
				cell50.setCellValue(Double.parseDouble(m.geth52000Str()));
			}

			XSSFCell cell51 = row.createCell((short) cellIndex + 51);
			cell51.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth53000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth53000Str(), null)) {
				cell51.setCellValue(Double.parseDouble(m.geth53000Str()));
			}

			XSSFCell cell52 = row.createCell((short) cellIndex + 52);
			cell52.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth54000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth54000Str(), null)) {
				cell52.setCellValue(Double.parseDouble(m.geth54000Str()));
			}

			XSSFCell cell53 = row.createCell((short) cellIndex + 53);
			cell53.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth55000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth55000Str(), null)) {
				cell53.setCellValue(Double.parseDouble(m.geth55000Str()));
			}

			XSSFCell cell54 = row.createCell((short) cellIndex + 54);
			cell54.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth56000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth56000Str(), null)) {
				cell54.setCellValue(Double.parseDouble(m.geth56000Str()));
			}

			XSSFCell cell55 = row.createCell((short) cellIndex + 55);
			cell55.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth57000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth57000Str(), null)) {
				cell55.setCellValue(Double.parseDouble(m.geth57000Str()));
			}

			XSSFCell cell56 = row.createCell((short) cellIndex + 56);
			cell56.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth58000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth58000Str(), null)) {
				cell56.setCellValue(Double.parseDouble(m.geth58000Str()));
			}

			XSSFCell cell57 = row.createCell((short) cellIndex + 57);
			cell57.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth59000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth59000Str(), null)) {
				cell57.setCellValue(Double.parseDouble(m.geth59000Str()));
			}

			XSSFCell cell58 = row.createCell((short) cellIndex + 58);
			cell58.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.geth5a000Str(), "") && !ObjectUtils.nullSafeEquals(m.geth5a000Str(), null)) {
				cell58.setCellValue(Double.parseDouble(m.geth5a000Str()));
			}

			index++;
		}
	}
}
