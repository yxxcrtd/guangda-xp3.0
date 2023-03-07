/*
 * 文件名：java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年6月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.pbc.classic.zgdatfile.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.seq.service.SysSeqService;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 详细DAT文件生成器
 * 文件存放：rootdir+组织机构代码+日期+压缩文件代码+序列(超过5M递增)+明细文件代码
 * @author Administrator
 * @version 1.0 2019年6月10日
 * @see PbcZGDatFileHelper
 * @since 1.0
 */
@Component
public class PbcZGDatFileHelper {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(PbcZGDatFileHelper.class);


	public static final String DATAFILE_TYPE_ZG01 = "ZG01";//人行资管产品基本信息（ZG01）dat文件
	public static final String DATAFILE_TYPE_ZG02 = "ZG02";//人行资管产品初始募集信息（ZG02）dat文件
	public static final String DATAFILE_TYPE_ZG03 = "ZG03";//人行资管产品基本信息（ZG03）dat文件
	public static final String DATAFILE_TYPE_ZG04 = "ZG04";//人行资管产品终止信息（ZG04）dat文件
	public static final String DATAFILE_TYPE_ZG05 = "ZG05";//人行资管产品资产负债信息（ZG05）dat文件
	public static final String DATAFILE_TYPE_ZG06 = "ZG06";//人行资产收益权明细信息（ZG06）dat文件
	public static final String DATAFILE_TYPE_ZG07 = "ZG07";//人行除回购和拆借外贷款明细信息（ZG07）dat文件
	public static final String DATAFILE_TYPE_ZG08 = "ZG08";//人行资管产品基本信息（ZG08）dat文件
	public static final String DATAFILE_TYPE_ZG09 = "ZG09";//人行特定目的载体交易对手明细信息（ZG09）dat文件
	public static final String DATAFILE_TYPE_ZG10 = "ZG10";//人行债券等资产配置情况信息（ZG10）dat文件生成
	public static final String DATAFILE_TYPE_ZG11 = "ZG11";//人行企业债券分行业、企业规模情况信息（ZG11）dat文件


	public static final int INIT_LIST = 11;//初始化大小

	public static final String SYS_PARAM_FOLDER_PATH = "SVR_PBC_DAT_FILEPATH";//dat文件路径系统参数

	public static final String PATH_FILE_NAME_KEY = "pathfilenameDatakeylist";//文件路径key

	public static final String TEMPORARY_FILE_PATH = "temporary";//手工生成文件路径

	public static final String DATA_KEY = "datakey";//数据主键

	public static final String 下划线 = "_";//下划线
	public static final String 斜杠 = "/";//斜杠
	public static final String 竖线 = "|";//竖线
	public static final String 文件后缀 = ".dat";//文件后缀
	public static final String 检验文件后缀 = ".log";//检验文件后缀
	public static final String ZIP = ".zip";//检验文件后缀

	public static final String 文件编码格式 = "UTF-8";//默认文件编码格式

	private static SysSeqService sysSeqService;

	@Autowired
	public void setSysSeqService(SysSeqService sysSeqLogic) {
		PbcZGDatFileHelper.sysSeqService = sysSeqLogic;
	}
	private static SysSeqService getSysSeqService() {
		if(PbcZGDatFileHelper.sysSeqService == null) {
			PbcZGDatFileHelper.sysSeqService = SpringContextUtil.getBean(SysSeqService.class);
		}
		return PbcZGDatFileHelper.sysSeqService;
	}

	/**
	 * 生成文件(包含check文件)
	 * 注意这里可能不需要 手工的处理 manualFlag 给false就行
	 * @param datas
	 * @param type
	 * @param seqDate
	 * @param orgCode
	 * @param seqMap
	 * @param manualFlag
	 * @param fileDataMap 【dat文件生成留痕】保存文件和数据主键list
	 * @return
	 */
	public static List<String> genFile(List<LinkedHashMap<String,String>> datas, String type, Date seqDate, String orgCode,
										Map<String, List<String>> seqMap, boolean manualFlag, Map<String,Object> fileDataMap) {

		//【dat文件生成留痕】数据的key集合
		List<String> dataValueList = new ArrayList<String>();
		//【dat文件生成留痕】key=目录名文件名，value=文件内数据的key集合
		Map<String,List<String>> pathfilenameDatakeylistMap;
		if(null == fileDataMap.get(PATH_FILE_NAME_KEY)) {
			pathfilenameDatakeylistMap = new HashMap<String,List<String>>();
			fileDataMap.put(PATH_FILE_NAME_KEY, pathfilenameDatakeylistMap);
		}else {
			pathfilenameDatakeylistMap = (Map<String, List<String>>) fileDataMap.get(PATH_FILE_NAME_KEY);
		}
		String rootdir = SvrDataUtil.getSysParam(SYS_PARAM_FOLDER_PATH);
		// 手工生成的文件存入temporary文件夹
		if (manualFlag) {
			rootdir += TEMPORARY_FILE_PATH + File.separator;
		}
		List<String> result = new ArrayList<String>();

		int num = 1;
		//序列Code
		String seqCode = orgCode + 下划线 + type;
		// 手工生成用独立序列
		if (manualFlag) {
			seqCode += (下划线 + TEMPORARY_FILE_PATH);
		}
//		//压缩包名称
//		String filePath = type + 下划线 + orgCode + 下划线 + dateStr;
		//机构 + 类型 + 日期 ，如：C1010211000012_ZG01_20220612.dat
		String fileName = createPbcFileName(orgCode, type, seqDate);
		//3位顺序号
		String seq = getSeqByNum(fileName, num, seqMap, seqCode, seqDate);
		//dat文件写入
		BufferedWriter writer = null;
		FileOutputStream fos = null;
		FileOutputStream fosx = null;
		long fileSize = 0;//文件大小
		int fileRow = 0;//
		// 人行dat生成文件编码
		String SVR_DAT_FILE_ENCODE = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.SVR_DAT_FILE_ENCODE);
		if (StringUtil.isEmpty(SVR_DAT_FILE_ENCODE)) {
			SVR_DAT_FILE_ENCODE = 文件编码格式;
		}
		String 文件路径 = rootdir + 斜杠 + seq;
		try {
			//String fullPath = rootdir + filePath + 下划线 + seq;
			String fullPath = 文件路径 + 斜杠 + fileName;
			result.add(fullPath + 文件后缀);
			createFolder(rootdir, seq, fullPath, 文件后缀);
			File file = null;
			file = new SafelyFile(fullPath + 文件后缀);
			fos = new FileOutputStream(file,true);
			writer = new BufferedWriter(new OutputStreamWriter(fos, SVR_DAT_FILE_ENCODE));

			for (LinkedHashMap<String,String> data : datas ) {
				StringBuffer sb = new StringBuffer();
				for (Entry<String,String>e : data.entrySet() ) {
					if(e.getValue() != null) {
						//【dat文件生成留痕】将每条数据的主键（不为空）保存到list中;"datakey"的值不写入文件。
						if(StringUtil.equals(DATA_KEY,e.getKey())) {
							if(!StringUtils.isEmpty(e.getKey())) {
								dataValueList.add(e.getValue());
							}
						}else {
							sb.append(e.getValue()+ 竖线);
						}
					}
					else {
						sb.append(竖线);
					}
				}
				writer.write(sb.substring(0, sb.length()-1));//删除最后一个“|”
				writer.flush();
				writer.newLine();
				fileSize = file.length();
				fileRow++;
				// 【dat文件生成留痕】key=目录名文件名，value=数据的key集合
				if (!pathfilenameDatakeylistMap.containsKey(fullPath + 文件后缀)) {
					pathfilenameDatakeylistMap.put(fullPath + 文件后缀, dataValueList);
				}
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (fos != null){
					fos.close();
				}
				if (fosx != null){
					fosx.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
		genCheckFile(result, 文件路径, fileName, fileSize, fileRow, SVR_DAT_FILE_ENCODE);
		return result;
	}

	/**
	 * 生成check文件
	 * @param result
	 * @param filePath
	 * @param fileName
	 * @param fileSize
	 * @param fileRow
	 * @param fileEncode
	 */
	public static void genCheckFile(List<String> result, String filePath, String fileName, long fileSize, int fileRow, String fileEncode) {
		//dat文件写入
		BufferedWriter writer = null;
		FileOutputStream fos = null;
		FileOutputStream fosx = null;
		// 人行dat生成文件编码
		if(StringUtil.isEmpty(fileEncode)) {
			String SVR_DAT_FILE_ENCODE = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.SVR_DAT_FILE_ENCODE);
			if (StringUtil.isEmpty(SVR_DAT_FILE_ENCODE)) {
				SVR_DAT_FILE_ENCODE = 文件编码格式;
			}
			fileEncode = SVR_DAT_FILE_ENCODE;
		}
		try {
			//检验文件样例
			/**
			文件名
			文件 MD5 码
			文件大小（字节）
			文件创建完成时间（YYYY-MM-DD HH:MM:SS）
			文件记录数（行数）**/
			String fullPath = filePath + 斜杠 + fileName;
			createFolder(filePath, null, fullPath, 检验文件后缀);
			File file = new SafelyFile(fullPath + 检验文件后缀);
			result.add(fullPath + 检验文件后缀);
			fos = new FileOutputStream(file,true);
			writer = new BufferedWriter(new OutputStreamWriter(fos, fileEncode));
			writer.write(fileName + 文件后缀);
			writer.newLine();
			writer.write(DigestUtils.md5Hex(fileName + 文件后缀));
			writer.newLine();
			writer.write(String.valueOf(fileSize));
			writer.newLine();
			writer.write(DateUtil.format(new Date(), DateUtil.DEFAULT_DATETIME_FORMAT));
			writer.newLine();
			writer.write(fileRow);
			writer.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (fos != null){
					fos.close();
				}
				if (fosx != null){
					fosx.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
	}

	/**
	 * 生成zip文件
	 * @param files
	 * @param type
	 * @param seqDate
	 * @param orgCode
	 * @param manualFlag
	 * @param seqMap
	 * @param fileDataMap
	 * @return
	 */
	private String genFinalCompressFile(List<String> files, String type, Date seqDate, String orgCode, boolean manualFlag, Map<String, List<String>> seqMap, Map<String,Object> fileDataMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		int size = files.size();
		// 未生辰数据文件,不需要继续压缩
		if (size < 1) {
			return "";
		}
		File[] allFiles = new SafelyFile[size];
		String rootdir = SvrDataUtil.getSysParam(SYS_PARAM_FOLDER_PATH);
		// 手工生成的文件存入temporary文件夹
		if (manualFlag) {
			rootdir += TEMPORARY_FILE_PATH + File.separator;
		}
		List<String> result = new ArrayList<String>();

		int num = 1;
		//序列Code
		String seqCode = orgCode + 下划线 + type;
		// 手工生成用独立序列
		if (manualFlag) {
			seqCode += (下划线 + TEMPORARY_FILE_PATH);
		}
		String fileName = createPbcFileName(orgCode, type, seqDate);
		//3位顺序号
		String seq = getSeqByNum(fileName, num, seqMap, seqCode, seqDate);
		String 文件路径 = rootdir + 斜杠 + seq;
		String fullPath = 文件路径 + 斜杠 + fileName;
		File outFile =  new SafelyFile(fullPath + ZIP);
		for (int i = 0; i < size; i ++) {
			allFiles[i] = new SafelyFile(files.get(i));
		}
		// 压缩文件并删除原文件
		try {
			ZipFileUtil.compressZip(outFile, true, allFiles);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}

		//【dat文件生成留痕】数据的key集合
		List<String> dataValueList = new ArrayList<String>();
		//【dat文件生成留痕】key=目录名文件名，value=文件内数据的key集合
		Map<String,List<String>> pathfilenameDatakeylistMap;
		if(null == fileDataMap.get(PATH_FILE_NAME_KEY)) {
			pathfilenameDatakeylistMap = new HashMap<String,List<String>>();
			fileDataMap.put(PATH_FILE_NAME_KEY, pathfilenameDatakeylistMap);
		}else {
			pathfilenameDatakeylistMap = (Map<String, List<String>>) fileDataMap.get(PATH_FILE_NAME_KEY);
		}
		return fullPath + ZIP;
	}

	/**
	 * 生成文件名，不包含后缀
	 * @param orgCode
	 * @param zgType
	 * @param seqDate
	 * @return
	 */
	public static String createPbcFileName(String orgCode, String zgType, Date seqDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DEFAULT_OTHER_FORMAT);
		String dateStr = sdf.format(seqDate);
		String fileName = orgCode  + 下划线 + zgType + 下划线 + dateStr;
		return fileName;
	}

	/**
	 * 生成文件名，不包含后缀
	 * @param orgCode
	 * @param zgType
	 * @param seqDate
	 * @return
	 */
	public static String createPbcFileName(String orgCode, String zgType, Date seqDate, String suffix) {
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DEFAULT_OTHER_FORMAT);
		String dateStr = sdf.format(seqDate);
		String fileName = orgCode  + 下划线 + zgType + 下划线 + dateStr + suffix;
		return fileName;
	}

	/**
	 * 创建文件夹
	 * @param folderName
	 * @param type
	 */
	public static void createFolder(String folderName, String type){
		//生成文件夹
		File fp = new SafelyFile(folderName);
		if (!fp.exists()) {
			fp.mkdirs();
		}
		File file = new SafelyFile(folderName + File.separator + type + 文件后缀);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
	}

	/**
	 * 创建文件夹及文件
	 * @param rootDir
	 * @param seqCode
	 * @param fullpath
	 * @param suffix
	 */
	public static void createFolder(String rootDir, String seqCode, String fullpath, String suffix){
		//生成文件夹
		File fp = new SafelyFile(rootDir);
		if (!fp.exists()) {
			fp.mkdirs();
		}
		if(StringUtil.isNotEmpty(seqCode)) {
			File fpdir = new SafelyFile(rootDir + 斜杠 + seqCode);
			if (!fpdir.exists()) {
				fpdir.mkdirs();
			}
		}
		File file = new SafelyFile(fullpath + suffix);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
		}
	}

	/**
	 * 获取文
	 * @param filePath
	 * @param num
	 * @param seqMap
	 * @param seqCode
	 * @param seqDate
	 * @return
	 */
	public static String getSeqByNum(String filePath,int num,Map<String,List<String>> seqMap,String seqCode,Date seqDate){
		String seq = "";
		if(seqMap.get(filePath) != null&&((List<String>) seqMap.get(filePath)).size() >= num){
			List<String> seqList = (List<String>) seqMap.get(filePath);
			seq = seqList.get(num-1);
		}
		else{
			if(seqMap.get(filePath) == null){
				List<String> seqList = new ArrayList<String>();
				seq = String.format("%03d", PbcZGDatFileHelper.getSysSeqService().getSysSeq(seqCode, seqDate, null));
				seqList.add(seq);
				seqMap.put(filePath, seqList);
			}else{
				List<String> seqList = (List<String>) seqMap.get(filePath);
				seq = String.format("%03d", PbcZGDatFileHelper.getSysSeqService().getSysSeq(seqCode, seqDate, null));
				seqList.add(seq);
			}
		}
		return seq;
	}



	/**
	 * 生成文件标志码map
	 * @return 文件标志码map
	 */
	public static Map<String, String> getFileCodeMap() {
		Map<String, String> fileCodeMap = new HashMap<String, String>(INIT_LIST);
		fileCodeMap.put(DATAFILE_TYPE_ZG01, DATAFILE_TYPE_ZG01);
		fileCodeMap.put(DATAFILE_TYPE_ZG02, DATAFILE_TYPE_ZG02);
		fileCodeMap.put(DATAFILE_TYPE_ZG03, DATAFILE_TYPE_ZG03);
		fileCodeMap.put(DATAFILE_TYPE_ZG04, DATAFILE_TYPE_ZG04);
		fileCodeMap.put(DATAFILE_TYPE_ZG05, DATAFILE_TYPE_ZG05);
		fileCodeMap.put(DATAFILE_TYPE_ZG06, DATAFILE_TYPE_ZG06);
		fileCodeMap.put(DATAFILE_TYPE_ZG07, DATAFILE_TYPE_ZG07);
		fileCodeMap.put(DATAFILE_TYPE_ZG08, DATAFILE_TYPE_ZG08);
		fileCodeMap.put(DATAFILE_TYPE_ZG09, DATAFILE_TYPE_ZG09);
		fileCodeMap.put(DATAFILE_TYPE_ZG10, DATAFILE_TYPE_ZG10);
		fileCodeMap.put(DATAFILE_TYPE_ZG11, DATAFILE_TYPE_ZG11);
		return fileCodeMap;
	}

	/**
	 * 根据前台传入的文件标志码字符串组装实际所需文件标志码集合
	 * @param fileCodeIds 根据前台传入的文件标志码字符串
	 * @return 实际所需文件标志码集合
	 */
	public static List<String> buildFileCodeList(String fileCodeIds) {
		List<String> fileCodeList = new ArrayList<String>(INIT_LIST);
		if (StringUtil.isNotEmpty(fileCodeIds)) {
			String[] fileCodeArr = fileCodeIds.split(",");
			fileCodeList = Arrays.asList(fileCodeArr);
		}
		return fileCodeList;
	}


}
