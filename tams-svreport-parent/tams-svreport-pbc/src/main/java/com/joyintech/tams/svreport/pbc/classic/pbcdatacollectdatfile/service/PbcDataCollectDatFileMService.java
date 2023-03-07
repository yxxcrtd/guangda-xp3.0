package com.joyintech.tams.svreport.pbc.classic.pbcdatacollectdatfile.service;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.file.SafelyFile;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.PbcDataCollectDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcDataCollectDatFile;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.interf.common.util.CnBondUtil;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service.ZG01PbcPrdBaseinfoSubMService;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dto.ZG02PbcPrdInitRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.serivce.ZG02PbcPrdInitRaiseSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.serivce.ZG03PrdBaseinfoEndSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.service.ZG04PbcPrdCashRaiseSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.service.ZG05PbcColPrdAssetDebtSubService;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto.ZG06PbcAssetSusfructSub;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.serivce.ZG06PbcAssetSusfructSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.service.ZG07PbcColLoanDetailSubMService;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.service.ZG08SpecCounterQuityMService;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dto.ZG09PbcColReport;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.service.ZG09PbcAssetDebtTermSubService;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto.ZG10PbcColReportDto;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.service.ZG10PbcColAssetConfigurationSubService;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11CompanyBondInfoSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.service.ZG11CompanyBondInfoSubService;
import com.joyintech.tams.systemtools.dto.CommonAjaxResult;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcDataCollectDatFileMService
 * @since 1.0
 */
@Service
public class PbcDataCollectDatFileMService extends BaseService<PbcDataCollectDatFile>{

	private static final String TIPS_SHEET_NAME_ERROR = "文件名称不规范，请确认文件名称后再重新导入";
    private static final Pattern DATE_PATTERN  = Pattern.compile("(\\d{1,4}[-|\\/|年|]\\d{1,2}[-|\\/|月|]?(\\d{1,2}([日|号|])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?)?(\\s)*(PM|AM)?)");
    private static final Pattern DATE_PATTERN1  = Pattern.compile("(\\d{1,4}[-|\\/|-|]\\d{1,2}[-|\\/|-|]?(\\d{1,2}([.|])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?)?(\\s)*(PM|AM)?)");
    private static final Pattern DATE_PATTERN2  = Pattern.compile("(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.])");
    //月报
    private static List<String> ZG_MONTH= Arrays.asList("ZG04","ZG05","ZG06","ZG07","ZG08","ZG09","ZG10","ZG11");

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PbcDataCollectDatFileMService.class);

    private static final String TIPS_FILE_NAME_ERROR = "文件名称不规范，请确认文件名称后，重新导入。";

    @Autowired
    private PbcDataCollectDatFileMapper pbcDataCollectDatFileMapper;

    @Autowired
    private ZG01PbcPrdBaseinfoSubMService zg01PbcPrdBaseinfoSubMService;

    @Autowired
    private ZG02PbcPrdInitRaiseSubService001 zg02PbcPrdInitRaiseSubService001;

    @Autowired
    private ZG03PrdBaseinfoEndSubService001 zg03PrdBaseinfoEndSubService001;

    @Autowired
    private ZG04PbcPrdCashRaiseSubService001 zg04PbcPrdCashRaiseSubService001;

    @Autowired
    private ZG05PbcColPrdAssetDebtSubService zG05PbcColPrdAssetDebtSubService;

    @Autowired
    private ZG06PbcAssetSusfructSubService001 zg06PbcAssetSusfructSubService001;

    @Autowired
    private ZG07PbcColLoanDetailSubMService zg07PbcColLoanDetailSubMService;

    @Autowired
    private ZG08SpecCounterQuityMService zg08SpecCounterQuityMService;

    @Autowired
    private ZG09PbcAssetDebtTermSubService zg09PbcAssetDebtTermSubService;

    @Autowired
    private ZG10PbcColAssetConfigurationSubService zG10PbcColAssetConfigurationSubService;

    @Autowired
    private ZG11CompanyBondInfoSubService zg11CompanyBondInfoSubService;


    /**
     *
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    public Page<PbcDataCollectDatFile> searchPage(Map<String, Object> param) {
        // 报送类型
        String reportName = (String) param.get("reportName");
        // 将报表名称当做多选下拉框处理
        if (StringUtil.isNotBlank(reportName)) {
            param.put("reportNameList", StringUtil.splitToList(GlobalConstant.COMMA, reportName));
        }
        return restTemplate.postForPage(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/findPage",param,
                PbcDataCollectDatFile.class);
    }

    /**
     *
     * 业务导入检查事件
     *
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult checkExcel(String fileName) {
        String reportName =getReportName(fileName);
        AjaxResult ajaxResult =new AjaxResult();
       if(reportName==null){
           return new AjaxResult(TIPS_SHEET_NAME_ERROR,MessageType.错误消息);
        }

        String[] filenames =fileName.split("/|\\\\");
        String  fileDateStr=null;

        if(!filenames[filenames.length-1].startsWith(CommonUtil.selectPbcBankCode(null))){
            return new AjaxResult("文件名称格式不规范",MessageType.错误消息);
        }

        Matcher m1 = DATE_PATTERN.matcher(filenames[filenames.length-1]);
        if(StringUtil.isNotEmpty(filenames[filenames.length-1])){
            fileDateStr=filenames[filenames.length-1].substring(20,28);
        }

        Date fileDate =null ;
        try {
            if(!StringUtil.isEmpty(fileDateStr)) {
                fileDate = DateUtil.parseDate(fileDateStr,
                        new String[]{"yyyy-MM-dd HH:mm:ss", "yyyyMMdd", "yyyy-MM", "yyyyMM", "yyyy-MM-dd",
                                "yyyy-MM-dd HH:mm:ss.0", "yyyy年MM月", "yyyy年MM月dd日"});
            }
        }catch (ParseException e) {
            return new AjaxResult("文件名称日期格式不规范",MessageType.错误消息);

        }
        if (fileDate ==null){
            return new AjaxResult("文件名称中需包含日期",MessageType.错误消息);
        }
        //校验月报的日期是否是月末
        if(filenames[filenames.length-1].contains("ZG04")||
                filenames[filenames.length-1].contains("ZG05")||
                filenames[filenames.length-1].contains("ZG06")||
                filenames[filenames.length-1].contains("ZG07")||
                filenames[filenames.length-1].contains("ZG08")||
                filenames[filenames.length-1].contains("ZG09")||
                filenames[filenames.length-1].contains("ZG10")||
                filenames[filenames.length-1].contains("ZG11")){

            Boolean flag=isLastDayOfMonth(fileDate);
            if(!flag){
                return new AjaxResult("文件名称中日期需要为月末日期",MessageType.错误消息);
            }
        }






        // 防止在进行导入前check时进行了两次的弹框
        ajaxResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
        return ajaxResult;
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importExcel(String fileName) {
        String reportName =getReportName(fileName);
        Map<String,Object> fileMsgMap = new HashMap<>();
        fileMsgMap.put("fileName",fileName);
        fileMsgMap.put("reportName",reportName);
        String[] filenames =fileName.split("/|\\\\");
        String  fileDateStr=null;
//        String  fileNa=filenames[filenames.length-1];
//        String  fdate=fileNa.substring(fileNa.lastIndexOf(')')+1,fileNa.lastIndexOf('_')).
//                replace("年","").replace("月","").replace("日","");
        Matcher m1 = DATE_PATTERN.matcher(filenames[filenames.length-1]);
        if(m1.find()){
            fileDateStr = m1.group().trim();
        }
        Date fileDate =null ;
        try {
            if(!StringUtil.isEmpty(fileDateStr)) {
                fileDate = DateUtil.parseDate(fileDateStr,
                        new String[]{"yyyy-MM-dd HH:mm:ss", "yyyyMMdd", "yyyy-MM", "yyyyMM", "yyyy-MM-dd",
                                "yyyy-MM-dd HH:mm:ss.0", "yyyy年MM月", "yyyy年MM月dd日"});
            }
        }catch (ParseException e) {
            throw new BusinessException("Date ParseException");
        }
        if(ZG_MONTH.contains(reportName)){//月报特殊处理
            fileDate = DateUtil.getLastDay(fileDate);
        }
        //fdate=getLastDay(Integer.parseInt(fdate.substring(0,4)),Integer.parseInt(fdate.substring(4,6)),"yyyyMMdd");
        fileMsgMap.put("fileDate",DateUtil.format(fileDate,"yyyyMMdd"));

        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/importExcel",fileMsgMap,
                ExcelValidDto.class);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        String reportName =getReportName(fileName);
        Map<String,Object> fileMsgMap = new HashMap<>();
        fileMsgMap.put("fileName",fileName);
        fileMsgMap.put("reportName",reportName);
        String[] filenames =fileName.split("/|\\\\");
        String  fileDateStr=null;

        Matcher m1 = DATE_PATTERN.matcher(filenames[filenames.length-1]);
        if(StringUtil.isNotEmpty(filenames[filenames.length-1])){
            fileDateStr=filenames[filenames.length-1].substring(20,28);
        }
//        if(m1.find()){
//            fileDateStr = m1.group().trim();
//        }
        Date fileDate =null ;
        try {
            if(!StringUtil.isEmpty(fileDateStr)) {
                fileDate = DateUtil.parseDate(fileDateStr,
                        new String[]{"yyyy-MM-dd HH:mm:ss", "yyyyMMdd", "yyyy-MM", "yyyyMM", "yyyy-MM-dd",
                                "yyyy-MM-dd HH:mm:ss.0", "yyyy年MM月", "yyyy年MM月dd日"});
            }
        }catch (ParseException e) {
            throw new BusinessException("Date ParseException");
        }
        if(ZG_MONTH.contains(reportName)){//月报特殊处理
            fileDate = DateUtil.getLastDay(fileDate);
        }

        fileMsgMap.put("fileDate",DateUtil.format(fileDate,"yyyyMMdd"));

        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/importData",fileMsgMap,
                ExcelValidDto.class);
    }
    /**
     * 数据删除
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public void  deleteData(Map<String,Object> map) {
           restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/reportDetete", map,
                Integer.class);
    }


    public List<ExcelValidDto> importExcel2(String fileName) {
        String reportName =getReportName(fileName);
        Map<String,Object> fileMsgMap = new HashMap<>();
        fileMsgMap.put("fileName",fileName);
        fileMsgMap.put("reportName",reportName);
        String[] filenames =fileName.split("/|\\\\");
        String  fileDateStr=null;
        Matcher m1 = DATE_PATTERN.matcher(filenames[filenames.length-1]);
        if(StringUtil.isNotEmpty(filenames[filenames.length-1])){
            fileDateStr=filenames[filenames.length-1].substring(20,28);
        }
//        if(m1.find()){
//            fileDateStr = m1.group().trim();
//        }
        Date fileDate =null ;
        try {
            if(!StringUtil.isEmpty(fileDateStr)) {
                fileDate = DateUtil.parseDate(fileDateStr,
                        new String[]{"yyyy-MM-dd HH:mm:ss", "yyyyMMdd", "yyyy-MM", "yyyyMM", "yyyy-MM-dd",
                                "yyyy-MM-dd HH:mm:ss.0", "yyyy年MM月", "yyyy年MM月dd日"});
            }
        }catch (ParseException e) {
            throw new BusinessException("Date ParseException");
        }
        if(ZG_MONTH.contains(reportName)){//月报特殊处理
            fileDate = DateUtil.getLastDay(fileDate);
        }
        fileMsgMap.put("fileDate",DateUtil.format(fileDate,"yyyyMMdd"));

        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/importExcel2",fileMsgMap,
                ExcelValidDto.class);
    }

    /**
     * 生成压缩文件
     * @return 压缩文件存放地址
     */
    public AjaxResult createZipFile(Map<String, Object> param) {
        CommonAjaxResult ajaxResult = restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/createZipFile", param,
                CommonAjaxResult.class);
        Object data = ajaxResult.getData();
        String url = "";
        String flag = "";
        if(data != null){
            Map<String,String> rstMap = (HashMap)data;
            url = rstMap.get("result");
            flag = rstMap.get("flag");
        }
        ajaxResult.setData(url);
        if(CollectionUtils.isNotEmpty(ajaxResult.getErrorList()) && "NO_PBC_CODE".equals(flag)){
            List<ValidMessage> errorList = ajaxResult.getErrorList();
            StringBuilder builder = new StringBuilder();
            builder.append("以下内部产品代码没有对应的人行产品代码:<br>");
            for (ValidMessage message : errorList) {
                builder.append(message.getMessage() + "<br>");
            }
            ajaxResult.setMessage(builder.toString());
            ajaxResult.setMessageType(MessageType.错误消息);
            ajaxResult.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ajaxResult;
        }
        return ajaxResult;
    }


    /**
     * 获取数据检查
     * @return 压缩文件存放地址
     */
    public Page<ExcelValidDto> downBeforeCheck(Map<String, Object> param) {
        return restTemplate.postForPage(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/downBeforeCheck",param,
                ExcelValidDto.class);
    }


    /**
     * 生成压缩文件
     * @return 压缩文件存放地址
     */
    public AjaxResult createZipExcel(Map<String, Object> param) {
        CommonAjaxResult ajaxResult = restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/createZipExcel", param,
                CommonAjaxResult.class);
        Object data = ajaxResult.getData();
        String url = "";
        String flag = "";
        if(data != null){
            Map<String,String> rstMap = (HashMap)data;
            url = rstMap.get("result");
            flag = rstMap.get("flag");
        }
        ajaxResult.setData(url);
        if(CollectionUtils.isNotEmpty(ajaxResult.getErrorList()) && "NO_PBC_CODE".equals(flag)){
            List<ValidMessage> errorList = ajaxResult.getErrorList();
            StringBuilder builder = new StringBuilder();
            builder.append("以下内部产品代码没有对应的人行产品代码:<br>");
            for (ValidMessage message : errorList) {
                builder.append(message.getMessage() + "<br>");
            }
            ajaxResult.setMessage(builder.toString());
            ajaxResult.setMessageType(MessageType.错误消息);
            ajaxResult.setStatuCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ajaxResult;
        }
        return ajaxResult;
    }





    private String getReportName(String fileName){
        if(fileName.contains(DictCodeListExt2.采集平台资管产品基本信息.getValue())){
            return DictCodeListExt2.采集平台资管产品基本信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台资管产品初始募集信息.getValue())){
            return DictCodeListExt2.采集平台资管产品初始募集信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台资管产品终止信息.getValue())){
            return DictCodeListExt2.采集平台资管产品终止信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台资管产品存续期募集信息.getValue())){
            return DictCodeListExt2.采集平台资管产品存续期募集信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台资管产品资产负债信息.getValue())){
            return DictCodeListExt2.采集平台资管产品资产负债信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台资产收益权明细信息.getValue())){
            return DictCodeListExt2.采集平台资产收益权明细信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台除回购和拆借外贷款明细信息.getValue())){
            return DictCodeListExt2.采集平台除回购和拆借外贷款明细信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台特定目的载体交易对手明细信息.getValue())){
            return DictCodeListExt2.采集平台特定目的载体交易对手明细信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台资产负债剩余期限信息.getValue())){
            return DictCodeListExt2.采集平台资产负债剩余期限信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台债券等资产配置情况信息.getValue())){
            return DictCodeListExt2.采集平台债券等资产配置情况信息.getValue();
        }else if(fileName.contains(DictCodeListExt2.采集平台企业债券分行业企业规模情况信息.getValue())){
            return DictCodeListExt2.采集平台企业债券分行业企业规模情况信息.getValue();
        }
        return null;
    }


    /**
     * 文件组装方式
     * @param dataList 数据
     * @param getFilePath  目录
     * @param reportName 报表编号
     * @return
     */
    public Map<String,File> getExcelFileList(List dataList,String getFilePath,String reportName){
        Map<String,File> resultMap = new HashMap<>();
        File outFile = null;
        String downloadFileName = "";
        String issuePartyId =  CommonUtil.selectPbcBankCode(null);
        //日期取值，文件名称导出按照对应数据日期导出，不应该按照当前日期
        String termid = "";//yyyyMM
        List<Map<String,Object>> pdtoList=(List<Map<String,Object>>)dataList;
        if(pdtoList !=null && pdtoList.size()>0){
            if(pdtoList.get(0).get("TERMID")!=null){
                termid=pdtoList.get(0).get("TERMID").toString();
            }
        }
        String date = DateUtil.format(new Date());
        // 日报没有 termid
        if (!StringUtil.equals(DictCodeListExt2.采集平台资管产品基本信息.getValue(), reportName)
                && !StringUtil.equals(DictCodeListExt2.采集平台资管产品初始募集信息.getValue(), reportName)
                && !StringUtil.equals(DictCodeListExt2.采集平台资管产品终止信息.getValue(), reportName)) {
            date = com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(termid.substring(0, 4)), Integer.parseInt(termid.substring(4, 6)), "yyyyMMdd");
        }

        if (StringUtil.equals(DictCodeListExt2.采集平台资管产品基本信息.getValue(), reportName)) {
            Date maxDate=new Date();
            List<Date> dateList=new ArrayList<>();
            List<Map<String,Object>> dtoList=(List<Map<String,Object>>)dataList;
            if(!StringUtil.isEmptyList(dtoList) && dtoList.size()>0){
                for(int i=0;i<dtoList.size();i++){
                    if("2".equals(dtoList.get(i).get("INFO_TYPE"))){
                        //变更日期
                        dateList.add(DateUtil.parse(dtoList.get(i).get("CDATESTR").toString()));
                    }else {
                        //起息日
                        dateList.add(DateUtil.parse(dtoList.get(i).get("VDATESTR").toString()));
                    }

                }
                maxDate = Collections.max(dateList);
            }else {
                maxDate=new Date();
            }
            outFile = zg01PbcPrdBaseinfoSubMService.makeFile(getFilePath, backDataEntity(dataList,ZG01PbcPrdBaseinfoSub.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG01"+GlobalConstant.UNDER_LINE
                    + DateUtil.format(maxDate,"yyyyMMdd") + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台资管产品初始募集信息.getValue(), reportName)) {
            Date maxDate=new Date();
            List<Date> dateList=new ArrayList<>();
            List<Map<String,Object>> dtoList=(List<Map<String,Object>>)dataList;
            if(!StringUtil.isEmptyList(dtoList) && dtoList.size()>0){
                for(int i=0;i<dtoList.size();i++){
                        if(dtoList.get(i).get("STATUS_DATESTR")!=null){
                            dateList.add(DateUtil.parse(dtoList.get(i).get("STATUS_DATESTR").toString()));
                        }
                }
                maxDate = Collections.max(dateList);
            }else {
                maxDate=new Date();
            }
            outFile = zg02PbcPrdInitRaiseSubService001.makeFile(getFilePath, backDataEntity(dataList,ZG02PbcPrdInitRaiseSub.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG02"+GlobalConstant.UNDER_LINE
                    + DateUtil.format(maxDate,"yyyyMMdd") + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台资管产品终止信息.getValue(), reportName)) {
            Date maxDate=new Date();
            List<Date> dateList=new ArrayList<>();
            List<Map<String,Object>> dtoList=(List<Map<String,Object>>)dataList;
            if(!StringUtil.isEmptyList(dtoList) && dtoList.size()>0){
                for(int i=0;i<dtoList.size();i++){
                    if(dtoList.get(i).get("STATUS_DATESTR")!=null){
                        dateList.add(DateUtil.parse(dtoList.get(i).get("STATUS_DATESTR").toString()));
                    }
                }
                maxDate = Collections.max(dateList);
            }else {
                maxDate=new Date();
            }
            outFile = zg03PrdBaseinfoEndSubService001.makeFile(getFilePath, backDataEntity(dataList,ZG03PbcPrdBaseinfoEndSub.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG03"+GlobalConstant.UNDER_LINE
                    + DateUtil.format(maxDate,"yyyyMMdd") + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台资管产品存续期募集信息.getValue(), reportName)) {

            outFile = zg04PbcPrdCashRaiseSubService001.makeFile(getFilePath, backDataEntity(dataList,ZG04PbcProdRaiseSub.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG04"+GlobalConstant.UNDER_LINE
                    + date + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台资管产品资产负债信息.getValue(), reportName)) {
            outFile = zG05PbcColPrdAssetDebtSubService.makeFile(getFilePath, backDataEntity(dataList,ZG05PbcColPrdAssetDebtDto.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG05"+GlobalConstant.UNDER_LINE
                    + date + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台资产收益权明细信息.getValue(), reportName)) {
            outFile = zg06PbcAssetSusfructSubService001.makeFile(getFilePath, backDataEntity(dataList,ZG06PbcAssetSusfructSub.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG06"+GlobalConstant.UNDER_LINE
                    + date + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台除回购和拆借外贷款明细信息.getValue(), reportName)) {
            outFile = zg07PbcColLoanDetailSubMService.makeFile(getFilePath, backDataEntity(dataList,ZG07PbcColLoanDetailSubDto.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG07"+GlobalConstant.UNDER_LINE
                    + date + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台特定目的载体交易对手明细信息.getValue(), reportName)) {
            outFile = zg08SpecCounterQuityMService.makeFile(getFilePath, backDataEntity(dataList,ZG08SpeCounterQuityDto.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG08"+GlobalConstant.UNDER_LINE
                    + date+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台资产负债剩余期限信息.getValue(), reportName)) {
            outFile = zg09PbcAssetDebtTermSubService.makeFile(getFilePath, backDataEntity(dataList,ZG09PbcColReport.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG09"+GlobalConstant.UNDER_LINE
                    + date + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台债券等资产配置情况信息.getValue(), reportName)) {
            outFile = zG10PbcColAssetConfigurationSubService.makeFile(getFilePath, backDataEntity(dataList,ZG10PbcColReportDto.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG10"+GlobalConstant.UNDER_LINE
                    + date + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        } else if (StringUtil.equals(DictCodeListExt2.采集平台企业债券分行业企业规模情况信息.getValue(), reportName)) {
            outFile = zg11CompanyBondInfoSubService.makeFile(getFilePath, backDataEntity(dataList,ZG11CompanyBondInfoSubDto.class));
            downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG11"+GlobalConstant.UNDER_LINE
                    + date + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

        }
        resultMap.put(downloadFileName,outFile);
        return resultMap;
    }


    public List backDataEntity(List<Map<String,Object>> dataList,Class cls){
        List backList = new ArrayList();
        for(Map<String,Object> data : dataList){
            backList.add(JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(data)),cls));
        }
        return backList;
    }



    /**
     * 预览数据
     * @param pageId
     * @param param
     * @return
     */
    public String priview(String pageId, Map<String, Object> param) {
        return restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/priview", param,
                String.class);
    }

    /**
     * 下载压缩文件
     * @param param 页面参数
     * @return 压缩文件
     */
    public ResponseEntity<byte[]> download(Map<String, Object> param) {
        dealSearchParam(param);
        // 下载手工生成的文件并删除服务器中的文件
        Map<String,String> fileMsg = restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/createZipFile", param,
                Map.class);
        return ExportUtil.export(fileMsg.get("fileName"), new File(fileMsg.get("absoulutePath")), true);
    }

    /**
     * 下载压缩文件
     * @param param 页面参数
     * @return 压缩文件
     */
    public ResponseEntity<byte[]> downloadExcel(Map<String, Object> param) {
        dealSearchParam(param);

        Map<String,File> excelFile = new HashMap<>();
        // 下载手工生成的文件并删除服务器中的文件
        Map<String,List> dataList = restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/getPListByUUid", param,
                Map.class);

        Map<String,String> paramMap = restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcDataCollectDatFile/getConfigData", param,
                Map.class);
        for(Map.Entry<String,List> data:dataList.entrySet()){
            if(CollectionUtils.isNotEmpty(data.getValue())) {
                excelFile.putAll(getExcelFileList(data.getValue(), paramMap.get(data.getKey()), data.getKey()));
            }
        }

        List<File> fileLists =  new ArrayList<>();
        List<String> fileNames = new ArrayList<>();
        for(Map.Entry<String,File> file : excelFile.entrySet()){
            fileLists.add(file.getValue());
            fileNames.add(file.getKey());
        }

        File zipFile =  new SafelyFile(SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH")+"人行Excel数据文件_"+ DateUtil.format(new Date(), DateUtil.DEFAULT_DATE_FORMAT_CN)+".zip");

        CnBondUtil.compress("人行Excel数据文件_"+ DateUtil.format(new Date(), DateUtil.DEFAULT_DATE_FORMAT_CN)+".zip",SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH") , fileLists,fileNames);

        return ExportUtil.export(zipFile.getName(), zipFile, true);
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

    /**
     * 根据年月获取月末最后一天日期
     * @param year
     * @param month
     * @return
     */
    public   String getLastDay(int year,int month,String format) {
        Calendar cale = Calendar.getInstance();

        cale.set(Calendar.YEAR,year);//赋值年份
        cale.set(Calendar.MONTH, month-1);//赋值月份
        int lastDay = cale.getActualMaximum(Calendar.DAY_OF_MONTH);//获取月最大天数
        cale.set(Calendar.DAY_OF_MONTH, lastDay);//设置日历中月份的最大天数
        SimpleDateFormat sdf = new SimpleDateFormat(format);	//格式化日期yyyy-MM-dd
        String lastDayOfMonth = sdf.format(cale.getTime());
        return lastDayOfMonth;
    }

    /**
     * 判断给定日期是否是当月的最后一天
     * @param date
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) {
        //1、创建日历类
        Calendar calendar = Calendar.getInstance();
        //2、设置当前传递的时间，不设就是当前系统日期
        calendar.setTime(date);
        //3、data的日期是N，那么N+1【假设当月是30天，30+1=31，如果当月只有30天，那么最终结果为1，也就是下月的1号】
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        //4、判断是否是当月最后一天【1==1那么就表明当天是当月的最后一天返回true】
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }else{
            return false;
        }
    }
}
