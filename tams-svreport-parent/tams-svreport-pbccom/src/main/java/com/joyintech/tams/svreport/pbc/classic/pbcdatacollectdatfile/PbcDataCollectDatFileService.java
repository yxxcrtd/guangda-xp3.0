/*
 * 文件名：PbcSendZipDatFileService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：meifaxin
 * 修改时间：2021年07月28日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.pbc.classic.pbcdatacollectdatfile;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.PbcDataCollectDatFileMapper;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcDataCollectDatFile;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.pbc.assetdebtdat.service.*;
import com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.*;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *
 * 功能:人行报送文件管理service
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2021年7月27日
 * @修改记录：
 */
@Service
public class PbcDataCollectDatFileService extends CommonExportService<PbcDataCollectDatFile> {



	@Autowired
	private PbcDataCollectDatFileMapper pbcDataCollectDatFileMapper;

	@Autowired
	private PbcZipDataFileService pbcZipDataFileService;

	private Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * 查询页面数据
	 *
	 * @param pageParam
	 *            查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	public Page<PbcDataCollectDatFile> searchPage(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
//		String orgCode = SecurityUtil.getLoginUser().getOrgCode();
//		pageParam.put("orgCode",orgCode);
		if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
				&& pageParam.get(GlobalConstant.PARAM_LENGTH) != null){
			int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
			int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
			Page<PbcDataCollectDatFile> page = queryForPage(
					pagenum,legth,()->pbcDataCollectDatFileMapper.searchPage(pageParam));
			return page;
		} else {
			Page<PbcDataCollectDatFile> page = queryForPage(PbcDataCollectDatFileMapper.class, "searchPage",
					pageParam);
			return page;
		}
	}
//
//	public String priview(String pageid,Map<String, Object> selectParam) {
//		//获取报表类型  根据不同的报表类型 查询不同的明细和对应的 模板数据
//		String reportName=selectParam.get("reportName").toString();
//		String message="";
//		if(reportName.equals(DictCodeListExt2.采集平台资管产品基本信息.getValue())){
//			// 设置table预览大小
//			selectParam.put("tableWidth", 120);
//			//message=pbcPrdBaseInfoSubDatService.priview(selectParam);
//		}else if(reportName.equals(DictCodeListExt2.采集平台资管产品初始募集信息.getValue())){
//            // 设置table预览大小
//            selectParam.put("tableWidth", 120);
//        }else if(reportName.equals(DictCodeListExt2.采集平台资管产品终止信息.getValue())){
//            // 设置table预览大小
//            selectParam.put("tableWidth", 120);
//        }else if(reportName.equals(DictCodeListExt2.采集平台资管产品存续期募集信息.getValue())){
//
//        }else if(reportName.equals(DictCodeListExt2.采集平台资管产品资产负债信息.getValue())){
//
//        }else if(reportName.equals(DictCodeListExt2.采集平台资产收益权明细信息.getValue())){
//
//        }else if(reportName.equals(DictCodeListExt2.采集平台除回购和拆借外贷款明细信息.getValue())){
//
//        }else if(reportName.equals(DictCodeListExt2.采集平台特定目的载体交易对手明细信息.getValue())){
//
//        }else if(reportName.equals(DictCodeListExt2.采集平台资产负债剩余期限信息.getValue())){
//
//        }else if(reportName.equals(DictCodeListExt2.采集平台债券等资产配置情况信息.getValue())){
//
//        }else if(reportName.equals(DictCodeListExt2.采集平台企业债券分行业企业规模情况信息.getValue())){
//
//        }
//		return message;
//	}
//
	/**
	 * 处理查询前的参数 <功能详细描述>
	 *
	 * @param pageParam
	 * @see
	 * @since 1.0
	 */
	public void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<PbcDataCollectDatFile> astValueList = JsonUtils.json2List(keyList,
						PbcDataCollectDatFile.class);
				pageParam.put("datuuidList", astValueList);
//				if (!CollectionUtils.isEmpty(astValueList)){
//					pageParam.put("uuid", astValueList.get(0).getUuid());
//				}
			} catch (IOException e) {
//				e.printStackTrace();
				logger.error(e.getMessage(),e);
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
//
//	/**
//	 * 下载zip文件 <功能详细描述>
//	 *
//	 * @param pageParam
//	 * @see
//	 * @since 1.0
//	 */
//
//	public ResponseEntity<byte[]> downloadZipFile(Map<String, Object> pageParam){
//		//查询数据
//		String zipFileName = "zip下载"+ DateUtil.getSysDate() +".zip";
//		String exportFilePath = SvrDataUtil.getSysParam( GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
//		// 如果生成excel文件个数大于1，生成压缩文件
//		File outFile =  new File(exportFilePath + File.separator + zipFileName);
//		//需要导出的excel文件集合
//		List<File> sourceFiles = new ArrayList<File>();
//
//		for(int i=0;i<2;i++) {
//
//			Map<String, Object> dataParam = new HashMap<String, Object>();
//			// 明细数据
//			dataParam.put("detail", 1);
//			File file = TisUtil.createFile(dataParam, "EmployeeInfoReg001", "从业人员信息登记_(" + i + ").xls");
//			sourceFiles.add(file);
//		}
//		// 导出路径不存在则重新生成
//		if (!outFile.getParentFile().exists()) {
//			outFile.getParentFile().mkdirs();
//		}
//		try {
//			ZipFileUtil.compressZip(outFile, true, sourceFiles.toArray(new File[] {}));
//		}catch (Exception e) {
//			// 压缩文件失败后，删除所有临时文件
//			for (File file1 : sourceFiles) {
//				if (file1.exists()) {
//					file1.delete();
//				}
//			}
//			logger.error(e.getMessage(),e);
//		}
//		return ExportUtil.export(zipFileName, outFile, false);
//
//	}
//
//	/**
//	 * 下载压缩文件
//	 * @param param 页面参数
//	 * @return 压缩文件
//	 */
//	public ResponseEntity<byte[]> download(Map<String, Object> param) {
//		String finalFile = (String) param.get("result");
//		File sourceFiles = new File(finalFile);
//		// 下载手工生成的文件并删除服务器中的文件
//		return ExportUtil.export(sourceFiles.getName(), sourceFiles, true);
//	}


	/*
	删除
	 */
   public void  doDelete(Map<String, Object> param){
    	String  uuid=param.get("uuid").toString();
    	//1.删除 pbcDataCollectDatFileMapper

	    //2.更新uuid为空



  }

}
