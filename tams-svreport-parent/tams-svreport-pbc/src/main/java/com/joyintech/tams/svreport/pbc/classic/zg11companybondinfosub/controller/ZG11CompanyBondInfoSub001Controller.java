package com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.controller;

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
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11CompanyBondInfoSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.service.ZG11CompanyBondInfoSub001Service;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.service.ZG11CompanyBondInfoSubService;
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
 * 人行采集平台报送信息登记-ZG11 企业债券分行业、企业规模情况信息Controller
 *
 * @author chqf
 * @创建时间 2022年11月15日
 */
@Controller
@PageId("ZG11CompanyBondInfoSub")
@RequestMapping("/pbc/zg11companybondinfosub/ZG11CompanyBondInfoSub")
public class ZG11CompanyBondInfoSub001Controller extends AbstractCustomerDataExport<ZG11CompanyBondInfoSubDto> {

    /**
     * 微服务service
     */
    @Autowired
    private ZG11CompanyBondInfoSubService zg11CompanyBondInfoSubService;

	@Autowired
	private ZG11CompanyBondInfoSub001Service zG11CompanyBondInfoSub001Service;


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
	public Page<ZG11CompanyBondInfoSubDto> searchPage() {
		Map<String, Object> searchParam = RequestUtil.getPageParam();
		Page<ZG11CompanyBondInfoSubDto> page = zG11CompanyBondInfoSub001Service.searchPage(searchParam);
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
		return zG11CompanyBondInfoSub001Service.reportCheck(RequestUtil.getPageParam());
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
		List<ZG11CompanyBondInfoSubDto> dtoList;
		String issuePartyId =  CommonUtil.selectPbcBankCode(null);

		if (!StringUtil.isNotEmpty(datas)) {
			//则默认根据当前查询条件对系统中报送状态为“未报送”的数据进行检索导出
			dtoList = zG11CompanyBondInfoSub001Service.searchByExcel(paraMap);
		} else {
			List<String> list = Arrays.asList(datas.split(","));
			dtoList = zG11CompanyBondInfoSub001Service.searchByUuid(list);
		}
		File outFile = zg11CompanyBondInfoSubService.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/zg11companybondinfosub/conf/export_conf.xml",
				dtoList);
		String termid=null;
		if(dtoList !=null && dtoList.size()>0){
			termid=dtoList.get(0).getTermid();
		}else {
			termid=(String) paraMap.get("enddate");

		}
		String date= DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(termid.length()-2)),"yyyyMMdd");
		String excelFileName = issuePartyId + GlobalConstant.UNDER_LINE + "ZG11" + GlobalConstant.UNDER_LINE
				+ date
				+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);


		Integer exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "10" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

		// 总的list拆分
		List<List<ZG11CompanyBondInfoSubDto>> dtoLists = com.google.common.collect.Lists.partition(dtoList, exportCountPerSheet);


		OutputStream out = null;
		try {
			out = getOutputStream(response, excelFileName);
			String[] headers = { "发行机构代码","产品品种","c00000_企业债券余额总计","c01000_企业债券余额总计-大型企业","c02000_企业债券余额总计-中型企业","c03000_企业债券余额总计-小型企业","c04000_企业债券余额总计-微型企业","c10000_农、林、牧、渔业","c11000_农、林、牧、渔业-大型企业","c12000_农、林、牧、渔业-中型企业","c13000_农、林、牧、渔业-小型企业","c14000_农、林、牧、渔业-微型企业","c20000_采矿业","c21000_采矿业-大型企业","c22000_采矿业-中型企业","c23000_采矿业-小型企业","c24000_采矿业-微型企业","c30000_制造业","c31000_制造业-大型企业","c32000_制造业-中型企业","c33000_制造业-小型企业","c34000_制造业-微型企业","c40000_电力、热力、燃气及水生产和供应业","c41000_电力、热力、燃气及水生产和供应业-大型企业","c42000_电力、热力、燃气及水生产和供应业-中型企业","c43000_电力、热力、燃气及水生产和供应业-小型企业","c44000_电力、热力、燃气及水生产和供应业-微型企业","c50000_建筑业","c51000_建筑业-大型企业","c52000_建筑业-中型企业","c53000_建筑业-小型企业","c54000_建筑业-微型企业","c60000_批发和零售业","c61000_批发和零售业-大型企业","c62000_批发和零售业-中型企业","c63000_批发和零售业-小型企业","c64000_批发和零售业-微型企业","c70000_交通运输、仓储和邮政业","c71000_交通运输、仓储和邮政业-大型企业","c72000_交通运输、仓储和邮政业-中型企业","c73000_交通运输、仓储和邮政业-小型企业","c74000_交通运输、仓储和邮政业-微型企业","c80000_住宿和餐饮业","c81000_住宿和餐饮业-大型企业","c82000_住宿和餐饮业-中型企业","c83000_住宿和餐饮业-小型企业","c84000_住宿和餐饮业-微型企业","c90000_信息传输、软件和信息技术服务业","c91000_信息传输、软件和信息技术服务业-大型企业","c92000_信息传输、软件和信息技术服务业-中型企业","c93000_信息传输、软件和信息技术服务业-小型企业","c94000_信息传输、软件和信息技术服务业-微型企业","ca0000_金融业","ca1000_金融业-大型企业","ca2000_金融业-中型企业","ca3000_金融业-小型企业","ca4000_金融业-微型企业","cb0000_房地产业","cb1000_房地产业-大型企业","cb2000_房地产业-中型企业","cb3000_房地产业-小型企业","cb4000_房地产业-微型企业","cc0000_租赁和商务服务业","cc1000_租赁和商务服务业-大型企业","cc2000_租赁和商务服务业-中型企业","cc3000_租赁和商务服务业-小型企业","cc4000_租赁和商务服务业-微型企业","cd0000_科学研究和技术服务业","cd1000_科学研究和技术服务业-大型企业","cd2000_科学研究和技术服务业-中型企业","cd3000_科学研究和技术服务业-小型企业","cd4000_科学研究和技术服务业-微型企业","ce0000_水利、环境和公共设施管理业","ce1000_水利、环境和公共设施管理业-大型企业","ce2000_水利、环境和公共设施管理业-中型企业","ce3000_水利、环境和公共设施管理业-小型企业","ce4000_水利、环境和公共设施管理业-微型企业","cf0000_居民服务、修理和其他服务业","cf1000_居民服务、修理和其他服务业-大型企业","cf2000_居民服务、修理和其他服务业-中型企业","cf3000_居民服务、修理和其他服务业-小型企业","cf4000_居民服务、修理和其他服务业-微型企业","cg0000_教育","cg1000_教育-大型企业","cg2000_教育-中型企业","cg3000_教育-小型企业","cg4000_教育-微型企业","ch0000_卫生和社会工作","ch1000_卫生和社会工作-大型企业","ch2000_卫生和社会工作-中型企业","ch3000_卫生和社会工作-小型企业","ch4000_卫生和社会工作-微型企业","ci0000_文化、体育和娱乐业","ci1000_文化、体育和娱乐业-大型企业","ci2000_文化、体育和娱乐业-中型企业","ci3000_文化、体育和娱乐业-小型企业","ci4000_文化、体育和娱乐业-微型企业","cj0000_公共管理、社会保障和社会组织","cj1000_公共管理、社会保障和社会组织-大型企业","cj2000_公共管理、社会保障和社会组织-中型企业","cj3000_公共管理、社会保障和社会组织-小型企业","cj4000_公共管理、社会保障和社会组织-微型企业"};

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
		zG11CompanyBondInfoSub001Service.updateSumStaus(map);
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
		String result = zG11CompanyBondInfoSub001Service.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("有未报送的数据",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}


	@Override
	protected void handleCellData(List<ZG11CompanyBondInfoSubDto> lists, XSSFWorkbook workbook, XSSFSheet sheet) {

		XSSFCellStyle style1 = ExportExcelUtil.getXSSFCellStyle(workbook,"5");
		XSSFCellStyle style8 = ExportExcelUtil.getXSSFCellStyle(workbook,"8");
		XSSFCellStyle style2 = ExportExcelUtil.getXSSFCellStyle(workbook,"2");
		XSSFCellStyle stylet = ExportExcelUtil.getXSSFCellStyle(workbook,"t");
		XSSFCellStyle styleText = ExportExcelUtil.getXSSFCellStyle(workbook, "text");

		// 从第二行开始输出数据
		int index = 1;
		for (ZG11CompanyBondInfoSubDto m : lists) {
			XSSFRow row = sheet.createRow(index);
			// 列索引
			int cellIndex = 0;

			XSSFCell cell0 = row.createCell((short) cellIndex + 0);
			cell0.setCellStyle(styleText);
			cell0.setCellValue(m.getIssuePartyId());

			XSSFCell cell1 = row.createCell((short) cellIndex + 1);
			cell1.setCellStyle(styleText);
			cell1.setCellValue(m.getProdVariety());

			XSSFCell cell2 = row.createCell((short) cellIndex + 2);
			cell2.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC00000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC00000Str(), null)) {
				cell2.setCellValue(Double.parseDouble(m.getC00000Str()));
			}

			XSSFCell cell3 = row.createCell((short) cellIndex + 3);
			cell3.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC01000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC01000Str(), null)) {
				cell3.setCellValue(Double.parseDouble(m.getC01000Str()));
			}

			XSSFCell cell4 = row.createCell((short) cellIndex + 4);
			cell4.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC02000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC02000Str(), null)) {
				cell4.setCellValue(Double.parseDouble(m.getC02000Str()));
			}

			XSSFCell cell5 = row.createCell((short) cellIndex + 5);
			cell5.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC03000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC03000Str(), null)) {
				cell5.setCellValue(Double.parseDouble(m.getC03000Str()));
			}

			XSSFCell cell6 = row.createCell((short) cellIndex + 6);
			cell6.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC04000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC04000Str(), null)) {
				cell6.setCellValue(Double.parseDouble(m.getC04000Str()));
			}

			XSSFCell cell7 = row.createCell((short) cellIndex + 7);
			cell7.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC10000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC10000Str(), null)) {
				cell7.setCellValue(Double.parseDouble(m.getC10000Str()));
			}

			XSSFCell cell8 = row.createCell((short) cellIndex + 8);
			cell8.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC11000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC11000Str(), null)) {
				cell8.setCellValue(Double.parseDouble(m.getC11000Str()));
			}

			XSSFCell cell9 = row.createCell((short) cellIndex + 9);
			cell9.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC12000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC12000Str(), null)) {
				cell9.setCellValue(Double.parseDouble(m.getC12000Str()));
			}

			XSSFCell cell10 = row.createCell((short) cellIndex + 10);
			cell10.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC13000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC13000Str(), null)) {
				cell10.setCellValue(Double.parseDouble(m.getC13000Str()));
			}

			XSSFCell cell11 = row.createCell((short) cellIndex + 11);
			cell11.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC14000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC14000Str(), null)) {
				cell11.setCellValue(Double.parseDouble(m.getC14000Str()));
			}

			XSSFCell cell12 = row.createCell((short) cellIndex + 12);
			cell12.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC20000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC20000Str(), null)) {
				cell12.setCellValue(Double.parseDouble(m.getC20000Str()));
			}

			XSSFCell cell13 = row.createCell((short) cellIndex + 13);
			cell13.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC21000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC21000Str(), null)) {
				cell13.setCellValue(Double.parseDouble(m.getC21000Str()));
			}

			XSSFCell cell14 = row.createCell((short) cellIndex + 14);
			cell14.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC22000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC22000Str(), null)) {
				cell14.setCellValue(Double.parseDouble(m.getC22000Str()));
			}

			XSSFCell cell15 = row.createCell((short) cellIndex + 15);
			cell15.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC23000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC23000Str(), null)) {
				cell15.setCellValue(Double.parseDouble(m.getC23000Str()));
			}

			XSSFCell cell16 = row.createCell((short) cellIndex + 16);
			cell16.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC24000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC24000Str(), null)) {
				cell16.setCellValue(Double.parseDouble(m.getC24000Str()));
			}

			XSSFCell cell17 = row.createCell((short) cellIndex + 17);
			cell17.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC30000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC30000Str(), null)) {
				cell17.setCellValue(Double.parseDouble(m.getC30000Str()));
			}

			XSSFCell cell18 = row.createCell((short) cellIndex + 18);
			cell18.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC31000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC31000Str(), null)) {
				cell18.setCellValue(Double.parseDouble(m.getC31000Str()));
			}

			XSSFCell cell19 = row.createCell((short) cellIndex + 19);
			cell19.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC32000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC32000Str(), null)) {
				cell19.setCellValue(Double.parseDouble(m.getC32000Str()));
			}

			XSSFCell cell20 = row.createCell((short) cellIndex + 20);
			cell20.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC33000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC33000Str(), null)) {
				cell20.setCellValue(Double.parseDouble(m.getC33000Str()));
			}

			XSSFCell cell21 = row.createCell((short) cellIndex + 21);
			cell21.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC34000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC34000Str(), null)) {
				cell21.setCellValue(Double.parseDouble(m.getC34000Str()));
			}

			XSSFCell cell22 = row.createCell((short) cellIndex + 22);
			cell22.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC40000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC40000Str(), null)) {
				cell22.setCellValue(Double.parseDouble(m.getC40000Str()));
			}

			XSSFCell cell23 = row.createCell((short) cellIndex + 23);
			cell23.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC41000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC41000Str(), null)) {
				cell23.setCellValue(Double.parseDouble(m.getC41000Str()));
			}

			XSSFCell cell24 = row.createCell((short) cellIndex + 24);
			cell24.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC42000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC42000Str(), null)) {
				cell24.setCellValue(Double.parseDouble(m.getC42000Str()));
			}

			XSSFCell cell25 = row.createCell((short) cellIndex + 25);
			cell25.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC43000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC43000Str(), null)) {
				cell25.setCellValue(Double.parseDouble(m.getC43000Str()));
			}

			XSSFCell cell26 = row.createCell((short) cellIndex + 26);
			cell26.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC44000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC44000Str(), null)) {
				cell26.setCellValue(Double.parseDouble(m.getC44000Str()));
			}

			XSSFCell cell27 = row.createCell((short) cellIndex + 27);
			cell27.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC50000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC50000Str(), null)) {
				cell27.setCellValue(Double.parseDouble(m.getC50000Str()));
			}

			XSSFCell cell28 = row.createCell((short) cellIndex + 28);
			cell28.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC51000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC51000Str(), null)) {
				cell28.setCellValue(Double.parseDouble(m.getC51000Str()));
			}

			XSSFCell cell29 = row.createCell((short) cellIndex + 29);
			cell29.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC52000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC52000Str(), null)) {
				cell29.setCellValue(Double.parseDouble(m.getC52000Str()));
			}

			XSSFCell cell30 = row.createCell((short) cellIndex + 30);
			cell30.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC53000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC53000Str(), null)) {
				cell30.setCellValue(Double.parseDouble(m.getC53000Str()));
			}

			XSSFCell cell31 = row.createCell((short) cellIndex + 31);
			cell31.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC54000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC54000Str(), null)) {
				cell31.setCellValue(Double.parseDouble(m.getC54000Str()));
			}

			XSSFCell cell32 = row.createCell((short) cellIndex + 32);
			cell32.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC60000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC60000Str(), null)) {
				cell32.setCellValue(Double.parseDouble(m.getC60000Str()));
			}

			XSSFCell cell33 = row.createCell((short) cellIndex + 33);
			cell33.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC61000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC61000Str(), null)) {
				cell33.setCellValue(Double.parseDouble(m.getC61000Str()));
			}

			XSSFCell cell34 = row.createCell((short) cellIndex + 34);
			cell34.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC62000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC62000Str(), null)) {
				cell34.setCellValue(Double.parseDouble(m.getC62000Str()));
			}

			XSSFCell cell35 = row.createCell((short) cellIndex + 35);
			cell35.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC63000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC63000Str(), null)) {
				cell35.setCellValue(Double.parseDouble(m.getC63000Str()));
			}

			XSSFCell cell36 = row.createCell((short) cellIndex + 36);
			cell36.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC64000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC64000Str(), null)) {
				cell36.setCellValue(Double.parseDouble(m.getC64000Str()));
			}

			XSSFCell cell37 = row.createCell((short) cellIndex + 37);
			cell37.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC70000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC70000Str(), null)) {
				cell37.setCellValue(Double.parseDouble(m.getC70000Str()));
			}

			XSSFCell cell38 = row.createCell((short) cellIndex + 38);
			cell38.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC71000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC71000Str(), null)) {
				cell38.setCellValue(Double.parseDouble(m.getC71000Str()));
			}

			XSSFCell cell39 = row.createCell((short) cellIndex + 39);
			cell39.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC72000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC72000Str(), null)) {
				cell39.setCellValue(Double.parseDouble(m.getC72000Str()));
			}

			XSSFCell cell40 = row.createCell((short) cellIndex + 40);
			cell40.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC73000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC73000Str(), null)) {
				cell40.setCellValue(Double.parseDouble(m.getC73000Str()));
			}

			XSSFCell cell41 = row.createCell((short) cellIndex + 41);
			cell41.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC74000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC74000Str(), null)) {
				cell41.setCellValue(Double.parseDouble(m.getC74000Str()));
			}

			XSSFCell cell42 = row.createCell((short) cellIndex + 42);
			cell42.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC80000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC80000Str(), null)) {
				cell42.setCellValue(Double.parseDouble(m.getC80000Str()));
			}

			XSSFCell cell43 = row.createCell((short) cellIndex + 43);
			cell43.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC81000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC81000Str(), null)) {
				cell43.setCellValue(Double.parseDouble(m.getC81000Str()));
			}

			XSSFCell cell44 = row.createCell((short) cellIndex + 44);
			cell44.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC82000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC82000Str(), null)) {
				cell44.setCellValue(Double.parseDouble(m.getC82000Str()));
			}

			XSSFCell cell45 = row.createCell((short) cellIndex + 45);
			cell45.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC83000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC83000Str(), null)) {
				cell45.setCellValue(Double.parseDouble(m.getC83000Str()));
			}

			XSSFCell cell46 = row.createCell((short) cellIndex + 46);
			cell46.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC84000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC84000Str(), null)) {
				cell46.setCellValue(Double.parseDouble(m.getC84000Str()));
			}

			XSSFCell cell47 = row.createCell((short) cellIndex + 47);
			cell47.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC90000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC90000Str(), null)) {
				cell47.setCellValue(Double.parseDouble(m.getC90000Str()));
			}

			XSSFCell cell48 = row.createCell((short) cellIndex + 48);
			cell48.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC91000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC91000Str(), null)) {
				cell48.setCellValue(Double.parseDouble(m.getC91000Str()));
			}

			XSSFCell cell49 = row.createCell((short) cellIndex + 49);
			cell49.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC92000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC92000Str(), null)) {
				cell49.setCellValue(Double.parseDouble(m.getC92000Str()));
			}

			XSSFCell cell50 = row.createCell((short) cellIndex + 50);
			cell50.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC93000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC93000Str(), null)) {
				cell50.setCellValue(Double.parseDouble(m.getC93000Str()));
			}

			XSSFCell cell51 = row.createCell((short) cellIndex + 51);
			cell51.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getC94000Str(), "") && !ObjectUtils.nullSafeEquals(m.getC94000Str(), null)) {
				cell51.setCellValue(Double.parseDouble(m.getC94000Str()));
			}

			XSSFCell cell52 = row.createCell((short) cellIndex + 52);
			cell52.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCa0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCa0000Str(), null)) {
				cell52.setCellValue(Double.parseDouble(m.getCa0000Str()));
			}

			XSSFCell cell53 = row.createCell((short) cellIndex + 53);
			cell53.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCa1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCa1000Str(), null)) {
				cell53.setCellValue(Double.parseDouble(m.getCa1000Str()));
			}

			XSSFCell cell54 = row.createCell((short) cellIndex + 54);
			cell54.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCa2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCa2000Str(), null)) {
				cell54.setCellValue(Double.parseDouble(m.getCa2000Str()));
			}

			XSSFCell cell55 = row.createCell((short) cellIndex + 55);
			cell55.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCa3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCa3000Str(), null)) {
				cell55.setCellValue(Double.parseDouble(m.getCa3000Str()));
			}

			XSSFCell cell56 = row.createCell((short) cellIndex + 56);
			cell56.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCa4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCa4000Str(), null)) {
				cell56.setCellValue(Double.parseDouble(m.getCa4000Str()));
			}

			XSSFCell cell57 = row.createCell((short) cellIndex + 57);
			cell57.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCb0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCb0000Str(), null)) {
				cell57.setCellValue(Double.parseDouble(m.getCb0000Str()));
			}

			XSSFCell cell58 = row.createCell((short) cellIndex + 58);
			cell58.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCb1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCb1000Str(), null)) {
				cell58.setCellValue(Double.parseDouble(m.getCb1000Str()));
			}

			XSSFCell cell59 = row.createCell((short) cellIndex + 59);
			cell59.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCb2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCb2000Str(), null)) {
				cell59.setCellValue(Double.parseDouble(m.getCb2000Str()));
			}

			XSSFCell cell60 = row.createCell((short) cellIndex + 60);
			cell60.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCb3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCb3000Str(), null)) {
				cell60.setCellValue(Double.parseDouble(m.getCb3000Str()));
			}

			XSSFCell cell61 = row.createCell((short) cellIndex + 61);
			cell61.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCb4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCb4000Str(), null)) {
				cell61.setCellValue(Double.parseDouble(m.getCb4000Str()));
			}

			XSSFCell cell62 = row.createCell((short) cellIndex + 62);
			cell62.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCc0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCc0000Str(), null)) {
				cell62.setCellValue(Double.parseDouble(m.getCc0000Str()));
			}

			XSSFCell cell63 = row.createCell((short) cellIndex + 63);
			cell63.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCc1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCc1000Str(), null)) {
				cell63.setCellValue(Double.parseDouble(m.getCc1000Str()));
			}

			XSSFCell cell64 = row.createCell((short) cellIndex + 64);
			cell64.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCc2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCc2000Str(), null)) {
				cell64.setCellValue(Double.parseDouble(m.getCc2000Str()));
			}

			XSSFCell cell65 = row.createCell((short) cellIndex + 65);
			cell65.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCc3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCc3000Str(), null)) {
				cell65.setCellValue(Double.parseDouble(m.getCc3000Str()));
			}

			XSSFCell cell66 = row.createCell((short) cellIndex + 66);
			cell66.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCc4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCc4000Str(), null)) {
				cell66.setCellValue(Double.parseDouble(m.getCc4000Str()));
			}

			XSSFCell cell67 = row.createCell((short) cellIndex + 67);
			cell67.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCd0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCd0000Str(), null)) {
				cell67.setCellValue(Double.parseDouble(m.getCd0000Str()));
			}

			XSSFCell cell68 = row.createCell((short) cellIndex + 68);
			cell68.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCd1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCd1000Str(), null)) {
				cell68.setCellValue(Double.parseDouble(m.getCd1000Str()));
			}

			XSSFCell cell69 = row.createCell((short) cellIndex + 69);
			cell69.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCd2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCd2000Str(), null)) {
				cell69.setCellValue(Double.parseDouble(m.getCd2000Str()));
			}

			XSSFCell cell70 = row.createCell((short) cellIndex + 70);
			cell70.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCd3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCd3000Str(), null)) {
				cell70.setCellValue(Double.parseDouble(m.getCd3000Str()));
			}

			XSSFCell cell71 = row.createCell((short) cellIndex + 71);
			cell71.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCd4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCd4000Str(), null)) {
				cell71.setCellValue(Double.parseDouble(m.getCd4000Str()));
			}

			XSSFCell cell72 = row.createCell((short) cellIndex + 72);
			cell72.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCe0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCe0000Str(), null)) {
				cell72.setCellValue(Double.parseDouble(m.getCe0000Str()));
			}

			XSSFCell cell73 = row.createCell((short) cellIndex + 73);
			cell73.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCe1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCe1000Str(), null)) {
				cell73.setCellValue(Double.parseDouble(m.getCe1000Str()));
			}

			XSSFCell cell74 = row.createCell((short) cellIndex + 74);
			cell74.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCe2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCe2000Str(), null)) {
				cell74.setCellValue(Double.parseDouble(m.getCe2000Str()));
			}

			XSSFCell cell75 = row.createCell((short) cellIndex + 75);
			cell75.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCe3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCe3000Str(), null)) {
				cell75.setCellValue(Double.parseDouble(m.getCe3000Str()));
			}

			XSSFCell cell76 = row.createCell((short) cellIndex + 76);
			cell76.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCe4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCe4000Str(), null)) {
				cell76.setCellValue(Double.parseDouble(m.getCe4000Str()));
			}

			XSSFCell cell77 = row.createCell((short) cellIndex + 77);
			cell77.setCellStyle(style2);
			cell77.setCellValue(m.getCf0000Str());
			if (!ObjectUtils.nullSafeEquals(m.getCf0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCf0000Str(), null)) {
				cell77.setCellValue(Double.parseDouble(m.getCf0000Str()));
			}

			XSSFCell cell78 = row.createCell((short) cellIndex + 78);
			cell78.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCf1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCf1000Str(), null)) {
				cell78.setCellValue(Double.parseDouble(m.getCf1000Str()));
			}

			XSSFCell cell79 = row.createCell((short) cellIndex + 79);
			cell79.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCf2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCf2000Str(), null)) {
				cell79.setCellValue(Double.parseDouble(m.getCf2000Str()));
			}

			XSSFCell cell80 = row.createCell((short) cellIndex + 80);
			cell80.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCf3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCf3000Str(), null)) {
				cell80.setCellValue(Double.parseDouble(m.getCf3000Str()));
			}

			XSSFCell cell81 = row.createCell((short) cellIndex + 81);
			cell81.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCf4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCf4000Str(), null)) {
				cell81.setCellValue(Double.parseDouble(m.getCf4000Str()));
			}

			XSSFCell cell82 = row.createCell((short) cellIndex + 82);
			cell82.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCg0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCg0000Str(), null)) {
				cell82.setCellValue(Double.parseDouble(m.getCg0000Str()));
			}

			XSSFCell cell83 = row.createCell((short) cellIndex + 83);
			cell83.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCg1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCg1000Str(), null)) {
				cell83.setCellValue(Double.parseDouble(m.getCg1000Str()));
			}

			XSSFCell cell84 = row.createCell((short) cellIndex + 84);
			cell84.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCg2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCg2000Str(), null)) {
				cell84.setCellValue(Double.parseDouble(m.getCg2000Str()));
			}

			XSSFCell cell85 = row.createCell((short) cellIndex + 85);
			cell85.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCg3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCg3000Str(), null)) {
				cell85.setCellValue(Double.parseDouble(m.getCg3000Str()));
			}

			XSSFCell cell86 = row.createCell((short) cellIndex + 86);
			cell86.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCg4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCg4000Str(), null)) {
				cell86.setCellValue(Double.parseDouble(m.getCg4000Str()));
			}

			XSSFCell cell87 = row.createCell((short) cellIndex + 87);
			cell87.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCh0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCh0000Str(), null)) {
				cell87.setCellValue(Double.parseDouble(m.getCh0000Str()));
			}

			XSSFCell cell88 = row.createCell((short) cellIndex + 88);
			cell88.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCh1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCh1000Str(), null)) {
				cell88.setCellValue(Double.parseDouble(m.getCh1000Str()));
			}

			XSSFCell cell89 = row.createCell((short) cellIndex + 89);
			cell89.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCh2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCh2000Str(), null)) {
				cell89.setCellValue(Double.parseDouble(m.getCh2000Str()));
			}

			XSSFCell cell90 = row.createCell((short) cellIndex + 90);
			cell90.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCh3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCh3000Str(), null)) {
				cell90.setCellValue(Double.parseDouble(m.getCh3000Str()));
			}
			XSSFCell cell91 = row.createCell((short) cellIndex + 91);
			cell91.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCh4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCh4000Str(), null)) {
				cell91.setCellValue(Double.parseDouble(m.getCh4000Str()));
			}

			XSSFCell cell92 = row.createCell((short) cellIndex + 92);
			cell92.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCi0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCi0000Str(), null)) {
				cell92.setCellValue(Double.parseDouble(m.getCi0000Str()));
			}

			XSSFCell cell93 = row.createCell((short) cellIndex + 93);
			cell93.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCi1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCi1000Str(), null)) {
				cell93.setCellValue(Double.parseDouble(m.getCi1000Str()));
			}

			XSSFCell cell94 = row.createCell((short) cellIndex + 94);
			cell94.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCi2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCi2000Str(), null)) {
				cell94.setCellValue(Double.parseDouble(m.getCi2000Str()));
			}

			XSSFCell cell95 = row.createCell((short) cellIndex + 95);
			cell95.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCi3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCi3000Str(), null)) {
				cell95.setCellValue(Double.parseDouble(m.getCi3000Str()));
			}

			XSSFCell cell96 = row.createCell((short) cellIndex + 96);
			cell96.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCi4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCi4000Str(), null)) {
				cell96.setCellValue(Double.parseDouble(m.getCi4000Str()));
			}

			XSSFCell cell97 = row.createCell((short) cellIndex + 97);
			cell97.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCj0000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCj0000Str(), null)) {
				cell97.setCellValue(Double.parseDouble(m.getCj0000Str()));
			}

			XSSFCell cell98 = row.createCell((short) cellIndex + 98);
			cell98.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCj1000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCj1000Str(), null)) {
				cell98.setCellValue(Double.parseDouble(m.getCj1000Str()));
			}

			XSSFCell cell99 = row.createCell((short) cellIndex + 99);
			cell99.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCj2000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCj2000Str(), null)) {
				cell99.setCellValue(Double.parseDouble(m.getCj2000Str()));
			}

			XSSFCell cell100 = row.createCell((short) cellIndex + 100);
			cell100.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCj3000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCj3000Str(), null)) {
				cell100.setCellValue(Double.parseDouble(m.getCj3000Str()));
			}

			XSSFCell cell101 = row.createCell((short) cellIndex + 101);
			cell101.setCellStyle(style2);
			if (!ObjectUtils.nullSafeEquals(m.getCj4000Str(), "") && !ObjectUtils.nullSafeEquals(m.getCj4000Str(), null)) {
				cell101.setCellValue(Double.parseDouble(m.getCj4000Str()));
			}

			index++;
		}
	}
}
