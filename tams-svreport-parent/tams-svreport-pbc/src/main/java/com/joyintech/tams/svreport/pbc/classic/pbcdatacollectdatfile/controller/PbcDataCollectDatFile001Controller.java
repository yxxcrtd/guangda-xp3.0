package com.joyintech.tams.svreport.pbc.classic.pbcdatacollectdatfile.controller;


import com.joyintech.background.component.common.pack.common.security.UserUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.common.base.entity.PbcDataCollectDatFile;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.pbc.classic.pbcdatacollectdatfile.PbcDataCollectDatFileService;
import com.joyintech.tams.svreport.pbc.classic.pbcdatacollectdatfile.service.PbcDataCollectDatFileMService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 人行报送文件管理界面Controller
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcDataCollectDatFile001Controller
 * @since 1.0
 */
@Controller
@PageId("PbcDataCollectDatFile")
@RequestMapping("/pbc/classic/pbcdatacollectdatfile/PbcDataCollectDatFile")
public class PbcDataCollectDatFile001Controller extends BaseController{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RedisUtil redisUtil;

    /**
     * service
     */
    @Autowired
    private PbcDataCollectDatFileMService pbcDataCollectDatFileMService;
	/**
	 * service
	 */
	@Autowired
	private PbcDataCollectDatFileService pbcDataCollectDatFileService;

    /**
	 * {@inheritDoc}
	 */
    @RequestMapping(PAGE_INIT)
	@Override
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.大数据采集平台报表类型,DictCode.人行报送状态);
        model.addAttribute("sysdateStrStart", DateUtil.format(DateUtil.addDays(DateUtil.platDateFromCache(),-5), "yyyy-MM-dd"));
		model.addAttribute("sysdateStrEnd", DateUtil.format(DateUtil.platDateFromCache(), "yyyy-MM-dd"));
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
	public Page<PbcDataCollectDatFile> searchPage() {
		Map<String,Object> searchParam = RequestUtil.getPageParam();
		Page<PbcDataCollectDatFile> page = pbcDataCollectDatFileMService
				.searchPage(searchParam);
		return page;
	}

	/**
	 * 查询列表
	 *
	 * @return DtPage
	 */
	@ResponseBody
	@RequestMapping(value ="/preView")
	public String preView(HttpServletRequest request, HttpServletResponse response) {
		String htmlExcel = pbcDataCollectDatFileMService.priview(this.getPageId(), RequestUtil.getPageParam());
		if ("fail".equals(htmlExcel)){
			return "fail";
		}
		response.reset();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		InputStream res = new ByteArrayInputStream(htmlExcel.getBytes(Charset.forName("UTF-8")));
		byte[] buf = new byte[1024*10];
		int len = 0;
		OutputStream out;
		try {
			out = response.getOutputStream();
			while ((len = res.read(buf)) != -1)
				out.write(buf, 0, len);
			out.flush();
			res.close();
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			return "fail";
		}
		return "success";
	}

	/**
	 * 生成压缩文件
	 * @return 压缩文件存放地址
	 */
//	@ResponseBody
//	@RequestMapping("/createZipFile")
//	public AjaxResult createZipFile() {
//		Map<String, Object> param = RequestUtil.getPageParam();
//		return pbcDataCollectDatFileMService.createZipFile(param);
//	}

	/**
	 * 下载压缩文件
	 * @return 压缩文件
	 */
	@ResponseBody
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download() {
		Map<String, Object> param = RequestUtil.getPageParam();
		return pbcDataCollectDatFileMService.download(param);
	}
	/**
	 * 下载压缩文件
	 * @return 压缩文件
	 */
	@ResponseBody
	@RequestMapping("/downloadExcel")
	public ResponseEntity<byte[]> downloadExcel() {
		Map<String, Object> param = RequestUtil.getPageParam();
		return pbcDataCollectDatFileMService.downloadExcel(param);
	}


	@ResponseBody
	@RequestMapping("/checkCount")
	public Boolean getCount(@RequestBody Map<String, Object> map) {
		Boolean flag=true;
		map.remove("keyList");
		Page<PbcDataCollectDatFile> page = pbcDataCollectDatFileMService
				.searchPage(map);
		List<PbcDataCollectDatFile> dtoList = page.getRoot();
		if(dtoList.size()>20){
			flag=false;
		}
		return flag;
	}



    /**
     *
     * 业务画面基本信息和终止信息导入检查事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkExcel")
    public AjaxResult checkExcel(String fileName) {
        return pbcDataCollectDatFileMService.checkExcel(fileName);
    }

    /**
     *
     * 业务基本信息和终止信息导入事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("importExcel")
    public List<ExcelValidDto> importExcel(String fileName) {
		//  写缓存
		redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_IMPORT_START, DateUtil.getCurrentDateTime() +  " - " + SVReportGlobalConstant.SVR_RDS_PROGRESS_IMPORT_START_TXT, 10 * 60L);

		return pbcDataCollectDatFileMService.importExcel2(fileName);
    }

	/**
	 * Ajax 获取导入的解析信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getImportExcelInfo")
	public AjaxResult getImportExcelInfo() {
		return getRedisValue(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_IMPORT_START);
	}

	/**
	 * Ajax 获取Excel上传信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUploadExcelInfo")
	public AjaxResult getUploadExcelInfo() {
		return getRedisValue(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_EXCEL_UPLOAD);
	}

	/**
	 * Ajax 获取Excel数据检查开始
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCheckDataInfo")
	public AjaxResult getCheckDataInfo() {
		return getRedisValue(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_CHECKDATA);
	}

	/**
	 * Ajax 获取Excel数据检查开始
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCheckDataEndInfo")
	public AjaxResult getCheckDataEndInfo() {
		return getRedisValue(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_CHECKDATA_END);
	}

	/**
	 * 单个数据检查开始
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getWriteDataInfo")
	public AjaxResult getWriteDataInfo() {
		return getRedisValue(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_WRITE_DATA);
	}

	// 获取redis值
	private AjaxResult getRedisValue(String redisKey) {
		Object redisValue = redisUtil.get(redisKey);
		return new AjaxResult(ObjectUtils.nullSafeEquals(redisValue, null) ? "" : String.valueOf(redisValue));
	}

	/**
	 *
	 * 业务基本信息和终止信息导入事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("importData")
	public List<ExcelValidDto> importData(String fileName) {
		return pbcDataCollectDatFileMService.importData(fileName);
	}

	/**
	 *
	 * 人行报送文件管理信息删除操作
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("delete")
	public AjaxResult deleteData() {
		AjaxResult wb = new AjaxResult(this.getMessage("REPORT_COMMON_I_0007"));
		pbcDataCollectDatFileMService.deleteData(RequestUtil.getPageParam());
		return  wb;
	}

}
