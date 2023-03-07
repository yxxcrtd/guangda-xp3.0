package com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.exportzgutil.ExportZgUtil;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubMService;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service.ZG01PbcPrdBaseinfoSubMService;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 *
 * <p>人行报送信息登记-产品基本信息信息Controller</p>
 * @author liuyu
 * @创建时间 2019年6月4日 下午4:57:28
 */
@Controller
@PageId("ZG01PbcPrdBaseinfoSub")
@RequestMapping("/pbc/zg01prdbaseinfosub/ZG01PbcPrdBaseinfoSub")
public class ZG01PbcPrdBaseinfoSub001Controller extends BaseController {


	/**
	 * 微服务service
	 */
	@Autowired
	private ZG01PbcPrdBaseinfoSubMService zg01PbcPrdBaseinfoSubMService;

	private String[] headers = { "信息类型", "产品代码", "产品名称", "发行机构代码", "发行机构名称", "产品品种", "产品类型","产品品牌","产品期次","发行机构内部产品代码"
			,"募集资金币种", "兑付本金币种","兑付收益币种", "募集方式","管理方式","运行方式","募集起始日期","募集结束日期","发行机构提前终止权标识"
			,"客户赎回权标识","产品增信标识","增信机构类型","增信形式","境内托管机构代码","境外托管机构国别代码","境外托管机构名称","产品起始日期","产品变更日期","产品预计终止日期"
			,"受托职责","分级产品标识","收益权转让产品标识","货基或现金管理类产品标识","跨境理财通","以财产权信托名义开展的资金信托",
			"基本信息公开标识","变更原因","备用字段1","备用字段2","备用字段3","备用字段4","备用字段5"};


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
		super.setSelectOptionToModel(model,	DictCode.报送数据状态,DictCode.人行报送状态,DictCode.人行报送反馈状态,
				DictCode.新人行产品运作模式,DictCode.人行投资性质,DictCode.信息类型);
		return super.init(model);
	}


	@ResponseBody
	@RequestMapping("searchPage")
	public Page<ZG01PbcPrdBaseinfoSub> searchPage(){
		Map<String,Object> searchParam = RequestUtil.getPageParam();
		Page<ZG01PbcPrdBaseinfoSub> page = zg01PbcPrdBaseinfoSubMService.searchPage(searchParam);;
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
		return zg01PbcPrdBaseinfoSubMService.reportCheck(RequestUtil.getPageParam());
	}

	/**
	 * 手工处理
	 * @param updateList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("doManul")
	public AjaxResult manul(@RequestBody Map<String, Object> map) {
		zg01PbcPrdBaseinfoSubMService.updateSumStaus(map);
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
	}

	/**
	 * 取消手工处理
	 * @param updateList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("cancelManul")
	public AjaxResult cancelManul(@RequestBody Map<String,Object> map) {
		String result = zg01PbcPrdBaseinfoSubMService.cancelMannul(map);
		if("fail".equals(result)){
			AjaxResult ajaxresult =  new AjaxResult("存在未报送的数据,不可取消手工处理",MessageType.错误消息);
			ajaxresult.setStatuCode(HttpStatus.OK.value());
			return ajaxresult;
		}
		return new AjaxResult(this.getMessage("REPORT_COMMON_I_0008"));
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
		String issuePartyId =  CommonUtil.selectPbcBankCode(null);
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		List<ZG01PbcPrdBaseinfoSub> dtoList;

		if (!StringUtil.isNotEmpty(datas)) {
			//则默认根据当前查询条件对系统中报送状态为“未报送”或反馈状态为“发送失败”“发送异常”“中债退回”的数据进行检索导出
			dtoList = zg01PbcPrdBaseinfoSubMService.searchByExcel(paraMap);
		} else {
			List<String> list = Arrays.asList(datas.split(","));
			dtoList = zg01PbcPrdBaseinfoSubMService.searchByUuid(list);
		}
		Date maxDate=new Date();
		List<Date> dateList=new ArrayList<>();
		if(!StringUtil.isEmptyList(dtoList) && dtoList.size()>0){
			dtoList.forEach(
					item ->{
						if("2".equals(item.getInfoType())){
							//变更日期
							dateList.add(item.getCdate());
						}else{
							//起息日
							dateList.add(item.getVdate());
						}
					}
			);
			 maxDate = Collections.max(dateList);
		}else {
			 maxDate=new Date();
		}

		File outFile = zg01PbcPrdBaseinfoSubMService.makeFile(
				"/com/joyintech/tams/svreport/pbc/classic/zg01prdbaseinfosub/conf/export_conf.xml",
				dtoList);
		/*return ExportUtil.export(issuePartyId+GlobalConstant.UNDER_LINE+"ZG01"+GlobalConstant.UNDER_LINE
						+ DateUtil.format(maxDate,"yyyyMMdd")
						+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
				outFile, true);*/

		String excelFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG01"+GlobalConstant.UNDER_LINE + DateUtil.format(maxDate,"yyyyMMdd") + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

		int exportCountPerSheet = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET) ? "20" : SvrDataUtil.getSysParam(SVReportGlobalConstant.EXPORT_COUNT_PER_SHEET));

		// 总的list拆分
		List<List<ZG01PbcPrdBaseinfoSub>> dtoLists = Lists.partition(dtoList, exportCountPerSheet);

		OutputStream out = null;
		try {
			out = ExportZgUtil.getOutputStream(response, excelFileName);

			// 创建多个Sheet
			XSSFWorkbook workbook = new XSSFWorkbook();
			for (int i = 0; i < dtoLists.size(); i++) {
				zg01PbcPrdBaseinfoSubMService.exportExcel(workbook, i, "Sheet-" + i, headers, dtoLists.get(i));
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

}
