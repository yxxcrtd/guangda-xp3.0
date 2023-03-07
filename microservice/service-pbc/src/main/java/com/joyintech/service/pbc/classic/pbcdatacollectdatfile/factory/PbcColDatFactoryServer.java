package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory;

import com.google.common.collect.Lists;
import com.joyintech.background.component.common.pack.common.security.UserUtil;
import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.export.handler.JoyinBookConfig;
import com.joyintech.framework.export.handler.JoyinCellConfig;
import com.joyintech.framework.export.handler.JoyinSheetConfig;
import com.joyintech.framework.export.handler.ReportCfgHandler;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.ZipFileUtil;
import com.joyintech.tams.common.base.dao.PbcDataCollectDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcDataCollectDatFile;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.report.home.dto.VbusinessHomeDto;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.submitmaintenance.equityinfo.util.FileIO;
import com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode001Mapper;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dto.ProPeoplebankcodeManagement001Dto;
import com.joyintech.tams.svreport.generationcheck.dao.PbcReportGenerationCheck001Mapper;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.dao.PbcZGDatFileMapper;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.dto.PbcDataCollectDatFileDto;
import com.joyintech.tams.svreport.pbc.common.util.XlsxToHtml;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tams.systemtools.util.RedisUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName: PbcColDatFactionServer
 * @Description:
 * @Author: liubaoquan
 * @Date: 20221112 17:03
 */
@Service
@Transactional
public abstract class PbcColDatFactoryServer<T> {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //换行符
    private static String newline = "\r\n";
    //替换符
    private static String subStitutor = " ";
    //分隔符
    private static String separator = "|";
    //换行符
    private static String dataSeparator = ",";
    //身份证正则
    private static String cardReg="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PbcZGDatFileMapper pbcZGDatFileMapper;

    @Autowired
    private PbcDataCheckUtil pbcDataCheckUtil;

    @Autowired
    private SysInstRegBankCode001Mapper sysInstRegBankCode001Mapper;
    /**
     * 1.1工厂实例化(String 报表编号)
     * @param reportNo
     * @return
     */
    public abstract PbcColDatFactoryServer getPClass(String reportNo);

    /**
     * 1.2获取对应报表的模板xml（）
     * @return
     */
    public abstract String getPExportConfig();

    /**
     * 1.3获数据核查是否有相同主键方法（List<Map<String,Object>> 实体数据）
     * @return
     */
    public abstract List<ExcelValidDto> getPSameKeyData(List<Map<String,Object>> resultMap);


    /**
     * 1.4登记报表数据批量入库方法（List<Map<String,Object>> 实体数据，String uuid）
     * @return
     */
    public abstract Boolean batchPInsert(List<Map<String,Object>> dataList, String UUid,String fileDate);

    /**
     * 1.5实体私有数据检查（List<Map<String,Object>> 实体数据）
     * @return
     */
    public abstract List<ExcelValidDto>  getPDataCheck(List<Map<String,Object>> dataList,String type);


    public abstract void batchDelete(String UUid);

    /**
     * 1.6生成dat文件方法（List<Map<String，Object>>）
     * @return
     */
    public abstract String buildPDatFile(List<Map<String,Object>> dataList);

    /**
     * 1.7根据前端传入主键获取详情方法(String  uuid)
     * @return
     */
    public abstract List<Map<String,Object>> getPListByUUid(String uuid);


    /**
     * 1.8实体数据对比标红（List<Map<String，Object>>,String uuid）
     * @return
     */
    public abstract List<Map<String,Object>> getPDefDataList(List<Map<String,Object>> dataList,String uuid);

    /**
     * 1.9更新对应登记表数据状态(Map<String,Object>)
     * @return
     */
    public abstract Boolean updatePDataStatus(List<Map<String,Object>> dataList);

    /**
     * 1.10更新当前dat登记表数据状态(String uuid)
     * @return
     */
    public abstract Boolean updatePDatDataStatus(String uuid);

    /***
     * 1.11 html预览界面宽度
     * @return
     */
    public abstract int getTableWidth();

    /**
     * 获取例外字段
     * @return
     */
    public abstract Map<String,String> getDefMap();



    public abstract String getDatCode();

    @Autowired
    private PbcDataCollectDatFileMapper pbcDataCollectDatFileMapper;

    @Autowired
    private PbcReportGenerationCheck001Mapper pbcReportGenerationCheck001Mapper;

    /**
     * 身份证转换方法
     * @param cardCode
     * @return
     */
    public static String backCardChange(String cardCode){
        String back = "";
        //若证件号码为空，则按空值报送
        if(StringUtil.isNotEmpty(cardCode)){
            Pattern pattern = Pattern.compile(cardReg);
            Matcher m = pattern.matcher(cardCode);
            //个人身份证号码加密规则如下
            //若身份证件号码中有英文字母，应先统一转换成大写英文字母；
            cardCode = cardCode.toUpperCase();
            if(m.matches()){
                //变形后的身份证件号码（46 字符） = 身份证件号码前 14 个字节（14 位数字） + MD5(18 位身份证件号码)（32 字符，英文按小写输出）
                back = cardCode.substring(0, 14) + DigestUtils.md5Hex(cardCode).toLowerCase();
            }else{
                //变形后的证件号码（32 字符） = MD5(证件号码)（32 字符，英文按小写输出）
                back = DigestUtils.md5Hex(cardCode).toLowerCase();
            }
        }

        return back;
    }



    /**
     *
     * 2.1解析excel文件公共方法
     * @param fileName  导入文件
     * @param configFile  登记模板路径
     * @return
     */
    public  List<Map<String,Object>>   analysiExcelData(String fileName, String  configFile,String fileDate,String reportCode,Boolean flag){

        //定义返回对象
        List<Map<String,Object>>   mapresult=new ArrayList<>();
        List<String> listStr = getKeyList(configFile);
        com.joyintech.framework.util.ExcelUtil excelUtil = null;
        try {
            excelUtil = com.joyintech.framework.util.ExcelUtil.build(fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 读取第一个sheet
        List<List<String>> dataList = excelUtil.readToList(0);
        //从第二行开始
        int n=2;
        for(int i=1;i<dataList.size();++i){
            Map<String,Object> subMap = new HashMap<>();
            List<String> subList = dataList.get(i);
            if(subList.size()>listStr.size()){
                return null;
            }
            for(int j=0;j<subList.size();++j){
                subMap.put(listStr.get(j),StringUtil.isNotEmpty(subList.get(j))?subList.get(j):null);
            }
            subMap.put("dataKey",n);
            //默认值赋值
            subMap.put("sendStatus", "01");
            subMap.put("status", "01");
            subMap.put("updateUser", "system");
            subMap.put("createUser", "system");
            subMap.put("termId", fileDate.substring(0,6));
            mapresult.add(subMap);
            n++;

        }
        if(flag){
            dataRefresh(mapresult,reportCode,fileDate);
        }
        return mapresult;
    }

    /**
     *
     * @param mapResult
     * @return
     */
    public List<Map<String,Object>> dataRefresh(List<Map<String,Object>> mapResult,String reportCode,String fileDate){
        Set<String> pbcProdIdList = mapResult.stream().map(e -> StringUtil.isNotEmpty(e.get("pbcProdId"))?e.get("pbcProdId").toString():"").collect(Collectors.toSet());
        //根据传入的人行代码获取产品代码转换并传入
        String  reportDataCode = "";
        switch (reportCode){
            case  "ZG01"  : reportDataCode = DictCodeListExt2.报表名称_ZG01资管产品基本信息.getValue(); break;
            case  "ZG02"  : reportDataCode = DictCodeListExt2.报表名称_ZG02资管产品初始募集信息.getValue(); break;
            case  "ZG03"  : reportDataCode = DictCodeListExt2.报表名称_ZG03资管产品终止信息.getValue(); break;
            case  "ZG04"  : reportDataCode = DictCodeListExt2.报表名称_ZG04资管产品存续期募集信息.getValue(); break;
            case  "ZG05"  : reportDataCode = DictCodeListExt2.报表名称_ZG05资管产品资产负债信息.getValue(); break;
            case  "ZG06"  : reportDataCode = DictCodeListExt2.报表名称_ZG06资产收益权明细信息.getValue(); break;
            case  "ZG07"  : reportDataCode = DictCodeListExt2.报表名称_ZG07除回购和拆解外贷款明细登记.getValue(); break;
            case  "ZG08"  : reportDataCode = DictCodeListExt2.报表名称_ZG08特定目的载体交易对手明细信息.getValue(); break;
            case  "ZG09"  : reportDataCode = DictCodeListExt2.报表名称_ZG09资产负债剩余期限信息.getValue(); break;
            case  "ZG10"  : reportDataCode = DictCodeListExt2.报表名称_ZG10债券等资产配置情况信息登记.getValue(); break;
            case  "ZG11"  : reportDataCode = DictCodeListExt2.报表名称_ZG11企业债券分行业企业规模情况信息.getValue(); break;
        }


        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        List<Date> dateList = DeadlineUtil.getChbEndDate(reportDataCode,
                DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
                DateUtil.parse(fileDate), SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);


        if(null!=pbcProdIdList
                &&pbcProdIdList.size()>0
                &&!(pbcProdIdList.size()==1&&pbcProdIdList.contains(""))){
            Map<String,String> prodMap = new HashMap<>();
            List<Map<String,String>>  prodList =  pbcDataCollectDatFileMapper.getProdIdList(pbcProdIdList);
                for(Map<String,String> map  : prodList){
                    prodMap.put(map.get("PBC_CODE"), map.get("PROD_ID"));
                }
                for(Map<String,Object> map  :mapResult){
                    //如果产品代码为空就补充进去
                    if(!StringUtil.isNotEmpty(map.get("prodId"))){
                        map.put("prodId",prodMap.get(map.get("pbcProdId")));
                    }
                    map.put("pbcSubmitDeadline", dateList.get(0));
                    map.put("submitDeadline", dateList.get(1));
                }
        }
        for(Map<String,Object> map  :mapResult){
            map.put("pbcSubmitDeadline", dateList.get(0));
            map.put("submitDeadline", dateList.get(1));
        }

        return mapResult;
    }


    public List<String> getKeyList(String configFile){
        //解析xml模板的字段
        JoyinBookConfig reportConfig = this.getConfig(configFile);
        List<JoyinCellConfig> sheetConfList=reportConfig.getSheetConfList().get(0).getCellConfList();
        //根据order正序排序
        sheetConfList.sort(Comparator.comparing(JoyinCellConfig::getOrder));
        //定义字段List<String>
        List<String>  listStr=new ArrayList<>();
        //解析文件 xml文件中包含部分Str。全部替换掉才是正常要导入的值
        for(int i=0;i<sheetConfList.size();i++){
            listStr.add(sheetConfList.get(i).getBeanPropName().replace("Str", ""));
        }
        return listStr;
    }

    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("#");
//                        IF【入参.特殊数字集合】包含该字段

        decimalFormat.format(new BigDecimal("19.123"));
    }
    private static boolean isAllASCII(String input) {
        boolean isASCII = true;
        for (int i = 0; i < input.length(); i++) {
            int c = input.charAt(i);
            if (c > 0x7F) {
                isASCII = false;
                break;
            }
        }
        return isASCII;
    }
    /**
     * 2.2数据校验公共方法
     * @param listdata
     * @return
     */
    public  List<ExcelValidDto>  checkDataCommon(List<Map<String,Object>>  listdata, String reportCode){

        List<ExcelValidDto> checkResult = new ArrayList<>();
        SysInstRegBankCode001Dto dto = sysInstRegBankCode001Mapper.getBaseBankData();
        //2.2.1 处理不合规数据为空格
        handleData(listdata);
        //2.2.2 判断人行发行机构编码是否与当前机构相同
        for(int i=0;i<listdata.size();i++) {
            Map<String,Object>  limap=listdata.get(i);
            if(StringUtil.equals(reportCode, "ZG09") || StringUtil.equals(reportCode, "ZG10") || StringUtil.equals(reportCode, "ZG11")){
                continue;
            }
            if(StringUtil.isNotEmpty(limap.get("issuePartyId"))){

                if(!StringUtil.equals(dto.getPbcBankCode(),limap.get("issuePartyId"))){
                    checkResult.add(new ExcelValidDto(null, String.valueOf(i + 2), "导入发行机构代码与当前机构不一致。"
                            + "[issuePartyId="+limap.get("issuePartyId")+"]",
                            null));
                }

                if(null!=limap.get("issuePartyName")&&!StringUtil.equals(dto.getOrgName(),limap.get("issuePartyName"))){
                    checkResult.add(new ExcelValidDto(null, String.valueOf(i + 2), "导入发行机构名称与当前机构不一致。"
                            + "[issuePartyName="+limap.get("issuePartyName")+"]",
                            null));
                }
            }
        }
        return  checkResult;
    }


    /**
     * 2.21 不合规数据处理
     * @param listdata
     */
   public  void    handleData(List<Map<String,Object>>  listdata){
       // 2.21 不合规数据处理
       for(int i=0;i<listdata.size();i++){
           Map<String,Object>  limap=listdata.get(i);
           for(String aa:limap.keySet()){
               if(StringUtil.isNotEmpty(limap.get(aa))){
                   String  input=limap.get(aa).toString();
                   for (int j = 0; j < input.length(); j++) {
                       int c = input.charAt(j);
                       if (c == 0x7F||(c<0x1F&&c>0x00)) {
                           input.replace(input.charAt(j), ' ');
                           limap.put(aa,input);
                       }
                   }
               }

           }
       }
   }


    /**
     * 2.3dat文件入库操作（文件日期，报表编号）
     * @param fileDate
     * @param code
     * @return
     */
    public   String    insertDbDat(String  fileDate,String  code){
        //dat ZG01,ZG02,ZG03生成的为导入excel的日期
//        if(StringUtil.equals(code,"ZG01")||
//                StringUtil.equals(code,"ZG02")||
//                StringUtil.equals(code,"ZG03")){
//            //生成日期
//            fileDate=DateUtil.format(new Date(),"yyyyMMdd");
//        }
        //2.3.1 定义dat信息表
        PbcDataCollectDatFileDto pdto=new PbcDataCollectDatFileDto();
        String  datName="";
        if(StringUtil.equals(DictCodeListExt2.采集平台资管产品基本信息.getValue(),code)||
                StringUtil.equals(DictCodeListExt2.采集平台资管产品初始募集信息.getValue(),code)||
                StringUtil.equals(DictCodeListExt2.采集平台资管产品终止信息.getValue(),code)){
            datName=CommonUtil.selectPbcBankCode(null)+"_"+code+"_"+fileDate+".dat";
        }else {
            fileDate=getLastDay(Integer.parseInt(fileDate.substring(0,4)),Integer.parseInt(fileDate.substring(4,6)),"yyyyMMdd");
            datName=CommonUtil.selectPbcBankCode(null)+"_"+code+"_"+fileDate+".dat";
        }
        //文件名称
        pdto.setDatFileName(datName);
        //报表名称
        pdto.setReportName(code);
        pdto.setDatFileNameMd5(DigestUtils.md5Hex(datName));
        //生成日期
        pdto.setCreateDate(DateUtil.now());

        //报送状态
        pdto.setReportStatus(DictCodeListExt2.报送状态_未报送.getValue());
        //uudi
        pdto.setUuid(IDGenerator.generate(36));
        //插入信息表
        pbcZGDatFileMapper.deletePbcDataCollectDatFileByName(datName);
        pbcZGDatFileMapper.insertPbcDataCollectDatFile(pdto);
        return  pdto.getUuid();
    }

    public String  md5ByFileContent(File file){
        String md5keyString="";
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
            byte[] secretBytes = MessageDigest.getInstance("md5").digest(filecontent);
            md5keyString= DatatypeConverter.printHexBinary(secretBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return  md5keyString;
    }




    /**
     * 解析xml
     * @param configFile
     * @return
     */
  public JoyinBookConfig getConfig(String configFile) {
        ReportCfgHandler handler = new ReportCfgHandler();
        JoyinBookConfig reportConfig = null;

        try {
            if (StringUtil.isNotEmpty(configFile)) {
                reportConfig = handler.readConfig(configFile);
            } else {
                String columnJson = RequestUtil.getParam("colsData");
                if (StringUtil.isNotEmpty(columnJson)) {
                    reportConfig = new JoyinBookConfig();
                    List<Map> coslData = JsonUtils.json2List(columnJson, Map.class);
                    List<JoyinSheetConfig> sheetConfList = new ArrayList(coslData.size());
                    Iterator var7 = coslData.iterator();

                    while(var7.hasNext()) {
                        Map<String, Object> tab = (Map)var7.next();
                        JoyinSheetConfig joyinSheetConfig = new JoyinSheetConfig();
                        List<JoyinCellConfig> cellConfList = new ArrayList();
                        List<Map> cols = (List)tab.get("cols");
                        int i = 1;
                        Iterator var13 = cols.iterator();

                        while(var13.hasNext()) {
                            Map map = (Map)var13.next();
                            JoyinCellConfig joyinCellConfig = new JoyinCellConfig();
                            joyinCellConfig.setBeanPropName((String)map.get("field"));
                            joyinCellConfig.setOrder(i++);
                            joyinCellConfig.setName((String)map.get("name"));
                            cellConfList.add(joyinCellConfig);
                        }

                        joyinSheetConfig.setCellConfList(cellConfList);
                        joyinSheetConfig.setName(StringUtil.trim((String)tab.get("name")));
                        sheetConfList.add(joyinSheetConfig);
                    }

                    reportConfig.setSheetConfList(sheetConfList);
                }
            }

            return reportConfig;
        } catch (Exception var16) {
            throw new BusinessException(var16);
        }
    }

    /**
     * 2.4			excel检查入口(File 导入文件)
     * @return
     */
    @Transactional
    public List<ExcelValidDto> excelImpCheckData(String reportCode,String fileDate,File file){
//        2.4.1		定义 错误信息集合
        List<ExcelValidDto> validDtoList = new ArrayList<>();
        fileDate = DateUtil.format(DateUtil.parse(fileDate),DateUtil.DEFAULT_OTHER_FORMAT);
//        2.4.4		定义 数据集合 = 2.1 解析excel文件公共方法（【入参.导入文件】，1.2 获取对应报表的模板xml()）
        List<Map<String,Object>> dataList= new ArrayList<>();
        try {
           dataList = analysiExcelData(file.getAbsolutePath(),getPExportConfig(),fileDate,reportCode,false);
        }
        catch (Exception e1){
            validDtoList.add(new ExcelValidDto("", "", "模板文件中存在公式，请调整文件"));
            return validDtoList;
        }
//        IF(数据集合不为空)
        if(CollectionUtils.isNotEmpty(dataList)){

            // redis中写入读取excel成功的信息
            redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_EXCEL_UPLOAD, com.joyintech.framework.util.DateUtil.getCurrentDateTime() +  " - " + SVReportGlobalConstant.SVR_RDS_PROGRESS_EXCEL_UPLOAD_TXT + String.valueOf((dataList.size())), 10 * 60L);

            List<ExcelValidDto> backList = new ArrayList<>();
            //        数据集合 = 2.21 不合规数据处理(数据集合)())；
            handleData(dataList);
            //        2.4.5		错误信息集合.addALL(1.3 数据核查是否有相同主键方法（数据集合）)；
            List<ExcelValidDto> sameList = getPSameKeyData(dataList);
            backList.addAll(null!=sameList?sameList:new ArrayList<>());
            //        2.4.6		错误信息集合.addALL(2.2 数据校验公共方法(数据集合)())；
            List<ExcelValidDto> checkDataCommon = checkDataCommon(dataList, reportCode);
            backList.addAll(null!=checkDataCommon?checkDataCommon:new ArrayList<>());
            //        2.4.7		错误信息集合.addALL(1.5 数据校验私有方法(数据集合)())；


            // List拆分

            int groupSize = 750;
            Map<String, List<Map<String, Object>>> pbcProdId = dataList.stream().collect(Collectors.groupingBy(e -> String.valueOf(e.get("pbcProdId"))));
//            List<List<Map<String, Object>>> dtoLists = Lists.partition(dataList, groupSize);
            List<List<Map<String, Object>>> dtoLists = new ArrayList<>();
            List<Map<String, Object>> tempList = new ArrayList<>();
            int listCount = 0;
            for (Map.Entry<String, List<Map<String, Object>>> entry : pbcProdId.entrySet()) {
                // 不同 产品代码 批次合并
                if (tempList.size() + entry.getValue().size() > groupSize) {
                    List<Map<String, Object>> list = new ArrayList<>(tempList);
                    dtoLists.add(list);
                    tempList.clear();
                }
                tempList.addAll(entry.getValue());
                listCount += entry.getValue().size();
            }
            // 剩余数据
            if (CollectionUtils.isNotEmpty(tempList)){
                dtoLists.add(tempList);
            }

            // redis中写入校验开始信息
            String startDate = com.joyintech.framework.util.DateUtil.getCurrentDateTime();
            redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_CHECKDATA, startDate +  " - " + SVReportGlobalConstant.SVR_RDS_PROGRESS_CHECKDATA_TXT, 10 * 60L);

            /**add by  ZHXPCP-502  dxy  20230221 start*/
            deleteErrMsg();
            /**add by  ZHXPCP-502  dxy  20230221 end*/

            for (int i = 0; i < dtoLists.size(); i++) {
                List<ExcelValidDto> pDataCheck = dataCheckMethod(dtoLists.get(i), DictCodeListExt2.数据校验规则大类_报表生成检查.getValue(),null,false);

                backList.addAll(null!=pDataCheck?pDataCheck:new ArrayList<>());

                Map<String,List<ExcelValidDto>> dataMap = backList.stream().collect(Collectors.groupingBy(item -> item.getRowNum()));

                for(Map.Entry<String,List<ExcelValidDto>> dat: dataMap.entrySet()){
                    String errorInfo ="";
                    String warnInfo = "";
                    for(ExcelValidDto data : dat.getValue()){
                        errorInfo+= null!=data.getErrorInfo()?data.getErrorInfo():"";
                        warnInfo+=null!=data.getWarnInfo()?data.getWarnInfo():"";
                    }
                    validDtoList.add(new ExcelValidDto("", dat.getKey(), errorInfo, warnInfo));
                }
                validDtoList = validDtoList.stream().sorted((validDto1, validDto2) ->
                {if(Integer.parseInt(StringUtil.equals(validDto1.getRowNum(), "") ? "1" : validDto1.getRowNum()) < Integer.parseInt(StringUtil.equals(validDto2.getRowNum(), "") ? "1" : validDto2.getRowNum())){
                    return -1;
                }else {
                    return 1;
                }
                }).collect(Collectors.toList());

                // redis中写入单组校验
                int checkTotal = (i + 1) * groupSize > dataList.size() ? dataList.size() : (i + 1) * groupSize;
                redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_WRITE_DATA, com.joyintech.framework.util.DateUtil.getCurrentDateTime() +  " - " + SVReportGlobalConstant.SVR_RDS_PROGRESS_WRITE_DATA_TXT + "<span style='color: #00F;'>" + checkTotal + "</span>", 10 * 60L);
            }

            // redis中写入校验结束信息
            long spendTime = com.joyintech.framework.util.DateUtil.getBetweenDate(startDate, com.joyintech.framework.util.DateUtil.getCurrentDateTime(), com.joyintech.framework.util.DateUtil.DurationType.秒);
            redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_CHECKDATA_END, com.joyintech.framework.util.DateUtil.getCurrentDateTime() +  " - " + SVReportGlobalConstant.SVR_RDS_PROGRESS_CHECKDATA_END_TXT + spendTime + com.joyintech.framework.util.DateUtil.DurationType.秒, 10 * 60L);

        }else{
            //        错误信息集合(new 错误信息（错误级别.错误信息，"文件解析失败，请检查数据文件是否合规"）)
            validDtoList.add(new ExcelValidDto("", "", "模板不正确，请检查数据文件是否合规"));
        }

        // 如果有错误，则清空redis信息，避免弹出解析信息
        if (validDtoList.size() > 0) {
            cleanRedisCheckData();
        }

        return validDtoList;
    }

    /***
     * 删除上次导入的错误信息
     */
    public  void  deleteErrMsg(){
        logger.info("错误明细信息数据删除开始--------------" + new Date());
        pbcReportGenerationCheck001Mapper.deleteDetailByFlag(getDatCode());
        logger.info("错误明细信息数据删除结束--------------" + new Date());
        logger.info("错误信息数据删除开始--------------" + new Date());
        pbcReportGenerationCheck001Mapper.deleteByFlag(getDatCode());
        logger.info("错误信息数据删除结束--------------" + new Date());
    }


    /**
     * 2.4			excel导入入口(File 导入文件)
     * @return
     */
    @Transactional
    public Boolean excelImpData(String reportCode,String fileDate,File file){
//        2.5.1		定义 入库状态 = TRUE
        boolean flag = true;
        try {
        fileDate = DateUtil.format(DateUtil.parse(fileDate),DateUtil.DEFAULT_OTHER_FORMAT);
//        2.5.4		定义 数据集合 = 2.1 解析excel文件公共方法（【入参.导入文件】，1.2 获取对应报表的模板xml()）
        List<Map<String,Object>> dataList = analysiExcelData(file.getAbsolutePath(),getPExportConfig(),fileDate,reportCode,true);
//        IF(数据集合不为空)

            if(CollectionUtils.isNotEmpty(dataList)){
    //        数据集合 = 2.21 不合规数据处理(数据集合)())；
                handleData(dataList);
    //            定义  uuid = 2.3  dat文件入库操作（【入参.导入文件】.文件名称）
                String uuid = insertDbDat(fileDate,reportCode);
    //            调用 1.4 登记报表数据批量入库方法（【2.4.2 数据集合】，uuid）
                flag = batchPInsert(dataList,uuid,fileDate);

            }else{
    //            入库状态 = FALSE
                flag = false;
            }
        }catch (Exception e){
            logger.error("批量入库失败,请检查", e);
            flag = false;
        }finally {

            // 清空redis信息
            cleanRedisCheckData();

            return flag;
        }
    }

    public void cleanRedisCheckData() {
        redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_IMPORT_START,  "", 10 * 60L);
        redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_EXCEL_UPLOAD,  "", 10 * 60L);
        redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_CHECKDATA,  "", 10 * 60L);
        redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_WRITE_DATA,  "", 10 * 60L);
        redisUtil.set(LoginUserUtil.getUserName() + SVReportGlobalConstant.SVR_RDS_PROGRESS_CHECKDATA_END,  "", 10 * 60L);
    }


    /**
     * 2.6			根据传入数据生成excel并生成预览HTML界面(List<Map> 数据集合 ，报表模板xml)
     * @param data
     * @param tableWidth 表格宽度默认100%
     * @param sheetNum sheet页数默认1
     * @return
     */
    public String priview(Map<String,Object> data,Integer tableWidth,Integer sheetNum) {
        String template = getConfig(getPExportConfig()).getTempleteFile();

        // 查询不到数据
        if (data.get("report") == null){
            return "fail";
        }
        // 表格宽度默认100%
        if (null==tableWidth){
            tableWidth = 100;
        }
        // sheet页数默认1
        if (null==sheetNum){
            sheetNum = 1;
        }
        // 生成临时文件
        String tempFileName = template + new Date().getTime();
        File defile = TisUtil.createFileIfCellRedDat(data, getKeyList(getPExportConfig()),getDefMap(),template.replace(".xlsx", "").replace(".xls", ""), tempFileName);

        return XlsxToHtml.readExcelToHtmlStyle(defile, true, true,sheetNum,tableWidth);
    }



    /**
     *  2.7			界面预览按钮 （Map<String,Object>）
     * @return
     */
    public String priviewDetail(Map<String,Object> resutlMap){
//        2.7.1   定义 数据集合 = 执行【1.7 根据前端传入主键获取详情方法】
        List<Map<String,Object>> resultData = getPListByUUid((String) resutlMap.get("uuid"));
        defoultListMap(resultData);
        Map dataMap = new HashMap();
        dataMap.put("report", columnKeyChange(resultData,getKeyList(getPExportConfig())));
//        2.7.2   执行方法【2.6 根据传入数据生成excel并生成预览HTML界面(2.7.1数据集合，1.2 获取对应报表的模板xml())】
        return priview(dataMap,getTableWidth(),null);
    }

    /**
     * 2.8			dat文件下载入口 （Map<String,Object>）																			返回值:File
     * @return
     */
    @Transactional
    public List<File> datFileDown(List<PbcDataCollectDatFile> datList){
        List<File> datFileList = new ArrayList<>();

        //2.8.2 获取要下载的文件详情，修改状态并组装成文件
        if(StringUtil.isNotEmpty(datList)){
            //        遍历（dat详情集合）
            for(PbcDataCollectDatFile dat:datList){
                //        定义  list1 = 调用【1.7 根据前端传入主键获取详情方法(dat详情uuid)】
                List resultList = getPListByUUid(dat.getUuid());
                defoultListMap(resultList);
                resultList = columnKeyChange(resultList,getKeyList(getPExportConfig()));
                //        定义 文件1 = 文件名（dat详情.Dat文件名称）
                File datFile= new File(SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH")+dat.getDatFileName());
                //        文件1写入（【1.6  生成dat文件方法(list1)】）内容
                String  datStr = buildPDatFile(resultList);
                FileIO.write(datFile.getPath(), datStr, "UTF-8");
                //        定义 文件2 = 【2.9 生成dat对应.log方法（dat详情，文件1）】
                //        2.9.1  生成。log文件名
                //        定义 新建文件 = 【dat详情.Dat文件名称】替换.dat为.log
                File logFile= new File(SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH")+dat.getDatFileName().replaceAll(".dat", ".log"));
                FileIO.write(logFile.getPath(), buildLogFile(dat,datStr,datFile), "UTF-8");
                //        文件集合.add(文件1，文件2)
                datFileList.add(datFile);
                datFileList.add(logFile);
                //        执行【1.9 更新对应登记表数据状态(list1)】
                if(CollectionUtils.isNotEmpty(resultList)){
                    updatePDataStatus(resultList);
                }

                //        执行【1.10 更新当前dat登记表数据状态(dat详情.uuid)】
                updatePDatDataStatus(dat.getUuid());
                //        执行【2.10 更新dat详情表(dat详情.uuid)】

                updateDatStatus(dat.getUuid());
            }
        }
        return datFileList;
    }



    @Transactional
    public List<File> datExcelDown(List<PbcDataCollectDatFile> datList){
        List<File> datFileList = new ArrayList<>();

        //2.8.2 获取要下载的文件详情，修改状态并组装成文件
        if(StringUtil.isNotEmpty(datList)){
            //        遍历（dat详情集合）
            for(PbcDataCollectDatFile dat:datList){
                //        定义  list1 = 调用【1.7 根据前端传入主键获取详情方法(dat详情uuid)】
                List resultList = getPListByUUid(dat.getUuid());
                defoultListMap(resultList);
                resultList = columnKeyChange(resultList,getKeyList(getPExportConfig()));
                //sheng
//                datFileList.add(downExcelFile(resultList));

            }
        }
        return datFileList;
    }



    @Transactional
    public List<File> excelFileDown(List<PbcDataCollectDatFile> datList){
        List<File> datFileList = new ArrayList<>();

        //2.8.2 获取要下载的文件详情，修改状态并组装成文件
        if(StringUtil.isNotEmpty(datList)){
            //        遍历（dat详情集合）
            for(PbcDataCollectDatFile dat:datList){
                //        定义  list1 = 调用【1.7 根据前端传入主键获取详情方法(dat详情uuid)】
                List resultList = getPListByUUid(dat.getUuid());
                defoultListMap(resultList);
                resultList = columnKeyChange(resultList,getKeyList(getPExportConfig()));
                //        定义 文件1 = 文件名（dat详情.Dat文件名称）
                File datFile= new File(SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH")+dat.getDatFileName());
                //        文件1写入（【1.6  生成dat文件方法(list1)】）内容
                String  datStr = buildPDatFile(resultList);
                FileIO.write(datFile.getPath(), datStr, "UTF-8");
                //        定义 文件2 = 【2.9 生成dat对应.log方法（dat详情，文件1）】
                //        2.9.1  生成。log文件名
                //        定义 新建文件 = 【dat详情.Dat文件名称】替换.dat为.log
                File logFile= new File(SvrDataUtil.getSysParam("SVR_PBC_DAT_FILEPATH")+dat.getDatFileName().replaceAll(".dat", ".log"));
                FileIO.write(logFile.getPath(), buildLogFile(dat,datStr,datFile), "UTF-8");
                //        文件集合.add(文件1，文件2)
                datFileList.add(datFile);
                datFileList.add(logFile);
                //        执行【1.9 更新对应登记表数据状态(list1)】
                if(CollectionUtils.isNotEmpty(resultList)){
                    updatePDataStatus(resultList);
                }

                //        执行【1.10 更新当前dat登记表数据状态(dat详情.uuid)】
                updatePDatDataStatus(dat.getUuid());
                //        执行【2.10 更新dat详情表(dat详情.uuid)】

                updateDatStatus(dat.getUuid());
            }
        }
        return datFileList;
    }

    /**
     * 生成dat对应.log方法 （dat详情,File dat文件）
     * @param datFile
     * @param dat
     * @return
     */
    public String buildLogFile(PbcDataCollectDatFile datFile,String dat,File file){

//        2.9.2 文件写入
            StringBuffer stb = new StringBuffer("");
            stb.append(datFile.getDatFileName());
            stb.append(newline);
//            stb.append(DigestUtils.md5Hex(datFile.getDatFileName()));
            stb.append(md5ByFileContent(file));
            stb.append(newline);
            stb.append(dat.getBytes(StandardCharsets.UTF_8).length);
            stb.append(newline);
            stb.append(DateUtil.format(datFile.getCreateDate(),DateUtil.DEFAULT_DATETIME_FORMAT));
            stb.append(newline);
            stb.append(countStr(dat,newline));
        return stb.toString();
    }

    /**
     * 更新dat详情表 （uuid）
     * @return
     */
    public int updateDatStatus(String uuid){
        return pbcDataCollectDatFileMapper.updateDatStatus(uuid);
    }

    /**
     * 2.11公共组装 （List<Map<String，Object>> 业务数据，List<String> 文件顺序，Map 特殊数字集合）
     * @param dataList
     * @param keysList
     * @param defMap
     * @return
     */
    public String buildDatFileData(List<Map<String,Object>> dataList,List<String> keysList,Map<String,String> defMap){
//        2.11.1 定义 StringBuff sf
        StringBuffer sf = new StringBuffer();

        //2.11.2 遍历  业务数据
        for(Map<String,Object> map : dataList){
            for(String key:keysList){
                Object data = map.get(key);
                if(StringUtil.isNotEmpty(data)){
                    if(data instanceof String){
                        sf.append(data.toString().trim());
                    }else if(data instanceof Date){
                        sf.append(DateUtil.format((Date)data));
                    }else if(data instanceof BigDecimal){
                        DecimalFormat decimalFormat = new DecimalFormat("###0.00");
//                        IF【入参.特殊数字集合】包含该字段     													※特殊数字集合可能为空 自行判空处理
                        if(null!=defMap&&null!=defMap.get(key)){
                            decimalFormat = new DecimalFormat(defMap.get(key));
                        }
                        sf.append(decimalFormat.format(data));
                    }
                }
                sf.append(separator);
            }
            //删除末尾最后一个"|"
            sf.deleteCharAt(sf.length()-1);
            sf.append(newline);
        }
        return sf.toString();
    }


    /**
     * 数据检查公共方法
     * @param dataList 数据
     * @param type  校验方式
     * @param dataKey 业务主键
     * @return
     */
    public List<ExcelValidDto> dataCheckMethod(List dataList,String type,List<String> dataKey,Boolean needEnityToMap ){
        return pbcDataCheckUtil.getVailDate(dataList,dataKey,getDatCode(),false,null,type,needEnityToMap,false);
    }


    /**
     * 数据检查公共方法
     * @param dataList 数据
     * @param type  校验方式
     * @param dataKey 业务主键
     * @return
     */
    public List<ExcelValidDto> DownBefCheckMethod(List<PbcDataCollectDatFile> dataList,String type,List<String> dataKey,Boolean needEnityToMap ){
        List<ExcelValidDto> excelValidDtoList = new ArrayList<>();

        //2.8.2 获取要下载的文件详情，修改状态并组装成文件
        if(StringUtil.isNotEmpty(dataList)){
            for(PbcDataCollectDatFile dat:dataList){
                // 查询所有数据
                List resultList = getPListByUUid(dat.getUuid());

                List<List<Map<String,Object>>> dtoLists = Lists.partition(resultList, 500);
                for (int i = 0; i < dtoLists.size(); i++) {
                    excelValidDtoList.addAll(pbcDataCheckUtil.getVailDate(columnKeyChange(dtoLists.get(i), getKeyList(getPExportConfig())), dataKey, getDatCode(), false, null, type, needEnityToMap, true));
                }
            }
        }
        return excelValidDtoList;
    }


    /**
     * 公共更名处理 不考虑空进来
     * @param dataList 原数据
     * @param keys  xml中的key组合
     */
    public static List<Map<String,Object>> columnKeyChange(List<Map<String,Object>> dataList,List<String> keys){
        List<Map<String,Object>> newList = new ArrayList<>();
            Map<String,String> dataListKey = new HashMap<>();
        if(!StringUtil.isEmptyList(dataList)){
            Map<String,Object> data = dataList.get(0);
            for(String  key : data.keySet()){
                //获取当前字段去除_并小写
                String upperCase =StringUtil.upperCase(key.replaceAll("_",""));
                for(String dk : keys){
                    //将keys的字符小写并且判断是否有对应
                    if(upperCase.equals(StringUtil.upperCase(dk))){
                        dataListKey.put(key,dk);
                        break;
                    }
                }
            }
            //换成新的map返回
            for(Map<String, Object> val : dataList){
                Map<String, Object> newVal = new HashMap<>();
                for(Map.Entry<String,Object> entry :val.entrySet()){
                    if(null!=dataListKey.get(entry.getKey())){
                        newVal.put(dataListKey.get(entry.getKey()),entry.getValue());
                    }
                }
                //特殊处理期次数据
                newVal.put("termId", val.get("TERMID"));
                newVal.put("termid", val.get("TERMID"));
                newVal.put("prodId", val.get("PROD_ID"));
                newVal.put("dataKey",val.get("dataKey"));
                newVal.put("statusDate",val.get("STATUS_DATE"));
                newList.add(newVal);
            }
        }
        return newList;
    }



    public static int countStr(String longStr, String mixStr) {
        //如果确定传入的字符串不为空，可以把下面这个判断去掉，提高执行效率
        int count = 0;
        int index = 0;
        while((index = longStr.indexOf(mixStr,index))!= -1){
            index = index + mixStr.length();
            count++;
        }
        return count;
    }

    /**
     * 将手工生成的压缩文件进一步压缩，并返回最终文件路径
     * @param zipFile 要压缩的文件列表
     * @param outFile 最后的压缩文件名
     * @return
     */
    public File genFinalCompressFile(File[] zipFile,File outFile) {
        // 压缩文件并删除原文件
        try {
            ZipFileUtil.compressZip(outFile, true, zipFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outFile;
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
     * 处理人行产品代码字段
     * @param resultData
     */
    private void  defoultListMap(List<Map<String,Object>> resultData ){
        //查询所有的人行产品内外部代码
     List<ProPeoplebankcodeManagement001Dto>   pbclist= pbcZGDatFileMapper.selectAllPbcId();
     Map<String,ProPeoplebankcodeManagement001Dto>  pbcmap=pbclist.stream().collect(
             JoyinCollectors.toMap(x -> x.getPbcCode(), x -> x));
     for(Map<String,Object> map:resultData){
         if(map.containsKey("PBC_PROD_ID")){
             if(pbcmap.containsKey(map.get("PBC_PROD_ID"))&&pbcmap.get(map.get("PBC_PROD_ID")).getPbcProjectCodeExternal()!=null){
                 map.put("PBC_PROD_ID",pbcmap.get(map.get("PBC_PROD_ID")).getPbcProjectCodeExternal());
             }
         }
     }
    }


    /**
     * 删除操作
     * @param resutlMap
     */
    public  void dodelete(Map<String,Object> resutlMap){

        if(StringUtil.isNotEmpty((String) resutlMap.get("uuid"))){
            //1.删除主表信息
            pbcDataCollectDatFileMapper.deleteByPrimaryKey((String) resutlMap.get("uuid"));
            //2.更新明细表datuuid 为空
            batchDelete((String) resutlMap.get("uuid"));
        }

    }
}
