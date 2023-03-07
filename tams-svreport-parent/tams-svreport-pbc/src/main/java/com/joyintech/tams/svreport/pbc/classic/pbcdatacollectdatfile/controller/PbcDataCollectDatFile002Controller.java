package com.joyintech.tams.svreport.pbc.classic.pbcdatacollectdatfile.controller;


import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.PbcDataCollectDatFile;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.generationcheck.dto.PbcReportGenerationCheck001Dto;
import com.joyintech.tams.svreport.generationcheck.service.PbcReportGenerationCheck001Service;
import com.joyintech.tams.svreport.pbc.classic.pbcdatacollectdatfile.PbcDataCollectDatFileService;
import com.joyintech.tams.svreport.pbc.classic.pbcdatacollectdatfile.service.PbcDataCollectDatFileMService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 人行报送文件管理界面Controller
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcDataCollectDatFile002Controller
 * @since 1.0
 */
@Controller
@PageId("PbcDataCollectDatFile002")
@RequestMapping("/pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile002")
public class PbcDataCollectDatFile002Controller extends BaseController{


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * service
     */
    @Autowired
    private PbcDataCollectDatFileMService pbcDataCollectDatFileMService;

	@Autowired
	private PbcReportGenerationCheck001Service pbcReportGenerationCheck001Service;

    /**
	 * {@inheritDoc}
	 */
    @RequestMapping(PAGE_INIT)
	@Override
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.大数据采集平台报表类型,DictCode.报送状态);
        model.addAttribute("sysdateStrStart", DateUtil.format(DateUtil.addDays(DateUtil.platDateFromCache(),-5), "yyyy-MM-dd"));
		model.addAttribute("sysdateStrEnd", DateUtil.format(DateUtil.platDateFromCache(), "yyyy-MM-dd"));

		model.addAttribute("keyList",getParam("keyList"));
		model.addAttribute("reportName",getParam("reportName"));
		model.addAttribute("createDateStart",getParam("createDateStart"));
		model.addAttribute("createDateEnd",getParam("createDateEnd"));
		model.addAttribute("reportStatus",getParam("reportStatus"));
		return super.init(model);
	}

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<ExcelValidDto> searchPage() {
		Map<String,Object> searchParam = RequestUtil.getPageParam();
		Page<ExcelValidDto> page = pbcDataCollectDatFileMService
				.downBeforeCheck(searchParam);
		return page;
	}


	/**
	 * 下载压缩文件
	 * @return 压缩文件
	 */
	@ResponseBody
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download() throws IOException {
		Map<String, Object> params = RequestUtil.getPageParam();
		List<ExcelValidDto>   listvalid=new ArrayList<>();
		if(params.get("datass")!=null){
			  listvalid= JsonUtils.json2List(params.get("datass").toString(),ExcelValidDto.class) ;
		}
		String  ruleid="";
		if(CollectionUtils.isNotEmpty(listvalid)){
			ruleid=listvalid.get(listvalid.size()-1).getErrorInfo();
			ruleid=ruleid.substring(ruleid.indexOf("【")+1,ruleid.indexOf("】"));
		}

		List<PbcReportGenerationCheck001Dto> tempList = Lists.newArrayList();
		// 查询导出
		tempList = pbcReportGenerationCheck001Service.searchExportHtml(ruleid);
		String reportName="";
		if(CollectionUtils.isNotEmpty(tempList)){
			//获取报表名称
			 reportName=tempList.get(0).getReportName();

		}
		File   outFile = pbcReportGenerationCheck001Service.makeFile(
				"/com/joyintech/tams/svreport/generationcheck/conf/export_conf.xml", tempList);

		String FileName =reportName+ "导入校验详细内容" + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(new Date())
				+ GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

		return ExportUtil.export(FileName, outFile, true);

	}

}
