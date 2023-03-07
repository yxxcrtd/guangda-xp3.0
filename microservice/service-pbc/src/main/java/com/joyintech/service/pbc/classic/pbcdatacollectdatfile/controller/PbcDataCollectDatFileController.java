package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.controller;


import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.tams.common.base.dao.PbcDataCollectDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcDataCollectDatFile;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.pbcdatacollectdatfile.PbcDataCollectDatFileService;
import com.joyintech.tams.systemtools.util.RedisUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcDataCollectDatFileController
 * @since 1.0
 */
@RestController
@RequestMapping("/PbcDataCollectDatFile")
public class PbcDataCollectDatFileController {


	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(PbcDataCollectDatFileController.class);
    /**
     * service
     */
    @Autowired
    private PbcDataCollectDatFileService pbcDataCollectDatFileService;

	@Autowired
	private PbcDataCollectDatFileMapper pbcDataCollectDatFileMapper;

	@Autowired
	private List<PbcColDatFactoryServer> abstractsevices;

	@Autowired
	private RedisUtil redisUtil;

 
	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<PbcDataCollectDatFile> searchPage(@RequestBody Map<String, Object> param) {
		Page<PbcDataCollectDatFile> page = pbcDataCollectDatFileService.searchPage(param);
		return page;
	}


	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("getPListByUUid")
	public Map<String,List> getPListByUUid(@RequestBody Map<String, Object> param) {

		List<PbcDataCollectDatFile> datList = pbcDataCollectDatFileMapper.searchPage(param);
		Map<String,List> dataList = new HashMap<>();
		//2.8.2 获取要下载的文件详情，修改状态并组装成文件
		if(StringUtil.isNotEmpty(datList)){
			//        遍历（dat详情集合）
			Map<String,List<PbcDataCollectDatFile>> colMap = datList.stream().collect(
					JoyinCollectors.groupingBy(
							x -> x.getReportName()));
			for(Map.Entry<String,List<PbcDataCollectDatFile>> col: colMap.entrySet()){
				for(PbcColDatFactoryServer service:abstractsevices){
					if(service.getPClass(col.getKey())!=null){
						for(PbcDataCollectDatFile dto : col.getValue()){
							if(null!=dataList.get(col.getKey())){
								dataList.get(col.getKey()).addAll(service.getPListByUUid(dto.getUuid()));
							}else{
								dataList.put(col.getKey(),service.getPListByUUid(dto.getUuid()));
							}
						}
						break;
					}
				}
			}
		}
		return dataList;
	}


	/**
	 *
	 * 获取模板及编号
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@PostMapping("getConfigData")
	public Map<String,String> getConfigData(@RequestBody Map<String, Object> param) {
		Map<String,String> resultMap = new HashMap<>();
		for(PbcColDatFactoryServer service:abstractsevices){
			resultMap.put(service.getDatCode(),service.getPExportConfig());
		}
		return resultMap;
	}

	public void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<String> astValueList = JsonUtils.json2List(keyList,
						String.class);
				pageParam.put("datuuidList", astValueList);
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			String reportName = (String) pageParam.get("reportName");
			// 将报表名称当做多选下拉框处理
			if (StringUtil.isNotBlank(reportName)) {
				pageParam.put("reportNameList", StringUtil.splitToList(GlobalConstant.COMMA, reportName));
			}
		} else {
			// 报送类型
			String reportName = (String) pageParam.get("reportName");
			// 将报表名称当做多选下拉框处理
			if (StringUtil.isNotBlank(reportName)) {
				pageParam.put("reportNameList", StringUtil.splitToList(GlobalConstant.COMMA, reportName));
			}

		}
	}

	@PostMapping("downBeforeCheck/{page}/{length}")
	public Page<ExcelValidDto> downBeforeCheck(@RequestBody Map<String, Object> param) {
		dealSearchParam(param);

		List<PbcDataCollectDatFile> datList = pbcDataCollectDatFileMapper.searchPage(param);

		List<ExcelValidDto> validDtoList = new ArrayList<>();

		if(StringUtil.isNotEmpty(datList)){
			//        遍历（dat详情集合）
			Map<String,List<PbcDataCollectDatFile>> colMap = datList.stream().collect(
					JoyinCollectors.groupingBy(
							x -> x.getReportName()));
			for(Map.Entry<String,List<PbcDataCollectDatFile>> col: colMap.entrySet()){
				for(PbcColDatFactoryServer service:abstractsevices){
					if(service.getPClass(col.getKey())!=null){
						validDtoList.addAll(service.DownBefCheckMethod(col.getValue(), DictCodeListExt2.数据校验规则大类_报送前检查.getValue(),service.getKeyList(service.getPExportConfig()),false));
						break;
					}
				}
			}
		}

		Page<ExcelValidDto> result = new Page<>();
		result.setRoot(validDtoList);
		return result;
	}



	@PostMapping("downZipCheckData")
	public List<ExcelValidDto> downZipCheckData(@RequestBody Map<String, Object> param){
		//        2.8.1   获取要下载的dat详情
	//        定义 dat详情集合 = 根据前台传入数据获取对应的dat信息
	//        如果有勾选，则只查勾选数据，如果没有勾选数据。则根据查询条件获取
		List<PbcDataCollectDatFile> datList = pbcDataCollectDatFileMapper.searchPage(param);
		List<ExcelValidDto> validDtoList = new ArrayList<>();

		if(StringUtil.isNotEmpty(datList)){
			//        遍历（dat详情集合）
			Map<String,List<PbcDataCollectDatFile>> colMap = datList.stream().collect(
					JoyinCollectors.groupingBy(
							x -> x.getReportName()));
			for(Map.Entry<String,List<PbcDataCollectDatFile>> col: colMap.entrySet()){
				for(PbcColDatFactoryServer service:abstractsevices){
					if(service.getPClass(col.getKey())!=null){
						validDtoList.addAll(service.getPDataCheck(col.getValue(),"02"));
						break;
					}
				}
			}
		}

		return validDtoList;
	}

	/**
	 * 创建压缩文件
	 * @param param 页面参数
	 * @return 压缩文件之地
	 */
	@PostMapping("createZipFile")
	public Map<String,String> createZipFile(@RequestBody Map<String, Object> param) {
		Map<String,String> filMsgMap = new HashMap<>();
		Set<File> datFileList = new HashSet<>();

		//        定义 压缩包文件 = 文件名（人行DAT数据文件_系统日期（XXXX年XX月XX日格式））
		File zipFile =  new SafelyFile(SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH")
				+"人行DAT数据文件_"+ DateUtil.format(new Date(), DateUtil.DEFAULT_DATE_FORMAT_CN)+".zip");
//        2.8.1   获取要下载的dat详情
//        定义 dat详情集合 = 根据前台传入数据获取对应的dat信息
//        如果有勾选，则只查勾选数据，如果没有勾选数据。则根据查询条件获取
		List<PbcDataCollectDatFile> datList = pbcDataCollectDatFileMapper.searchPage(param);

		//2.8.2 获取要下载的文件详情，修改状态并组装成文件
		if(StringUtil.isNotEmpty(datList)){
			//        遍历（dat详情集合）
			Map<String,List<PbcDataCollectDatFile>> colMap = datList.stream().collect(
					JoyinCollectors.groupingBy(
							x -> x.getReportName()));
			for(Map.Entry<String,List<PbcDataCollectDatFile>> col: colMap.entrySet()){
				for(PbcColDatFactoryServer service:abstractsevices){
					if(service.getPClass(col.getKey())!=null){
						datFileList.addAll(service.datFileDown(col.getValue()));
						break;
					}
				}
			}
		}
		//        2.8.3 组装压缩包
		if(StringUtil.isNotEmpty(datFileList)){
			// 最终文件路径
			this.genFinalCompressFile(datFileList,zipFile);
		}

        //return zipFile;
		filMsgMap.put("fileName",zipFile.getName());
		filMsgMap.put("absoulutePath",zipFile.getAbsolutePath());
		return filMsgMap;
	}


	/**
	 * 创建压缩文件
	 * @param param 页面参数
	 * @return 压缩文件之地
	 */
	@PostMapping("downloadExcel")
	public Map<String,String> downloadExcel(@RequestBody Map<String, Object> param) {
		Map<String,String> filMsgMap = new HashMap<>();
		Set<File> datFileList = new HashSet<>();

		//        定义 压缩包文件 = 文件名（人行DAT数据文件_系统日期（XXXX年XX月XX日格式））
		File zipFile =  new SafelyFile(SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH")
				+"人行DAT数据对应详情文件_"+ DateUtil.format(new Date(), DateUtil.DEFAULT_DATE_FORMAT_CN)+".zip");
//        2.8.1   获取要下载的dat详情
//        定义 dat详情集合 = 根据前台传入数据获取对应的dat信息
//        如果有勾选，则只查勾选数据，如果没有勾选数据。则根据查询条件获取
		List<PbcDataCollectDatFile> datList = pbcDataCollectDatFileMapper.searchPage(param);

		//2.8.2 获取要下载的文件详情，修改状态并组装成文件
		if(StringUtil.isNotEmpty(datList)){
			//        遍历（dat详情集合）
			Map<String,List<PbcDataCollectDatFile>> colMap = datList.stream().collect(
					JoyinCollectors.groupingBy(
							x -> x.getReportName()));
			for(Map.Entry<String,List<PbcDataCollectDatFile>> col: colMap.entrySet()){
				for(PbcColDatFactoryServer service:abstractsevices){
					if(service.getPClass(col.getKey())!=null){
						datFileList.addAll(service.datExcelDown(col.getValue()));
						break;
					}
				}
			}
		}
		//        2.8.3 组装压缩包
		if(StringUtil.isNotEmpty(datFileList)){
			// 最终文件路径
			this.genFinalCompressFile(datFileList,zipFile);
		}

		//return zipFile;
		filMsgMap.put("fileName",zipFile.getName());
		filMsgMap.put("absoulutePath",zipFile.getAbsolutePath());
		return filMsgMap;
	}





	/**
	 * 将手工生成的压缩文件进一步压缩，并返回最终文件路径
	 * @param zipFile 要压缩的文件列表
	 * @param outFile 最后的压缩文件名
	 * @return
	 */
	public File genFinalCompressFile(Set<File> zipFile,File outFile) {
		// 压缩文件并删除原文件
		try {
			ZipFileUtil.compressZip(outFile, true, zipFile.toArray(new File[zipFile.size()]));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outFile;
	}

//	@PostMapping("checkExcel")
//	public List<ExcelValidDto> checkExcel(@RequestBody Map<String, Object> param) {
//		//AjaxResult result = new AjaxResult();
//		for(PbcColDatFactoryServer service:abstractsevices){
//			//获取报表编号
//			String reportName = null;
//			if(service.getPClass((String)param.get("reportName"))!=null){
//				//1.读取excel数据校验数据
//				List<ExcelValidDto> errList = service.excelImpCheckData(new File((String)param.get("fileName")));//ExcelUtil.getDataList1(fileName);
//				return errList;
//			}
//		}
//		return null;
//	}

	@PostMapping("importExcel")
	public List<ExcelValidDto> importExcel(@RequestBody Map<String, Object> param) {
		//AjaxResult result = new AjaxResult();
		List<ExcelValidDto> errorList = new ArrayList<>();
		//获取报表编号
		String reportName = (String)param.get("reportName");
		for(PbcColDatFactoryServer service:abstractsevices){
			if(service.getPClass(reportName)!=null){
                errorList = service.excelImpCheckData(reportName,(String) param.get("fileDate"), new File((String) param.get("fileName")));
                if(StringUtil.isEmptyList(errorList)) {
                    if (!service.excelImpData(reportName,(String) param.get("fileDate"), new File((String) param.get("fileName")))) {
                        ExcelValidDto excelValidDto = new ExcelValidDto();
                        String errorMsg = "批量保存失败";
                        excelValidDto.setErrorInfo(errorMsg);
                        errorList.add(excelValidDto);
                    }
                }
				break;
			}
		}
		return errorList;
	}


	/***
	 * 入库
	 * @param param
	 */
	@PostMapping("importData")
	public List<com.joyintech.tams.systemtools.dto.ExcelValidDto> importData (@RequestBody Map<String, Object> param) {
		// RHPTSJ-958 导入成功只有 上传成功 无 导入成功提示
		Boolean flag = false;
		//获取报表编号
		String reportName = (String)param.get("reportName");
		for(PbcColDatFactoryServer service:abstractsevices){
			if(service.getPClass(reportName)!=null){
				flag = service.excelImpData(reportName,(String) param.get("fileDate"), new File((String) param.get("fileName")));
				break;
			}
		}
		if (flag) {
			// 提示导入成功
			return new ArrayList<>();
		} else {
			// 导入报错 原逻辑 上传成功
			return null;
		}
	}

	/***
	 * 校验功能
	 * @param param
	 * @return
	 */
	@PostMapping("importExcel2")
	public List<ExcelValidDto> importExcel2(@RequestBody Map<String, Object> param) {
		List<ExcelValidDto> errorList = new ArrayList<>();
		//获取报表编号
		String reportName = (String)param.get("reportName");
		for(PbcColDatFactoryServer service:abstractsevices){
			if(service.getPClass(reportName)!=null){
				errorList = service.excelImpCheckData(reportName,(String) param.get("fileDate"), new File((String) param.get("fileName")));
				break;
			}
		}
		return errorList;
	}

    @PostMapping("priview")
    public String priview(@RequestBody Map<String, Object> param) {
        //获取报表编号
        String reportName = (String)param.get("reportName");
        for(PbcColDatFactoryServer service:abstractsevices){
            if(service.getPClass(reportName)!=null){
                return service.priviewDetail(param);
            }
        }
        return null;
    }

	@PostMapping("reportDetete")
	public void reportDetete(@RequestBody Map<String, Object> param) {
		//获取报表编号
		String reportName = (String)param.get("reportName");
		for(PbcColDatFactoryServer service:abstractsevices){
			if(service.getPClass(reportName)!=null){
				 service.dodelete(param);
			}
		}
	}

}
