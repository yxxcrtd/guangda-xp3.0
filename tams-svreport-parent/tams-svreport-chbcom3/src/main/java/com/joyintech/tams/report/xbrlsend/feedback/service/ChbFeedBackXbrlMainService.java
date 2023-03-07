/*
 * 文件名：InvestorsHoldInfoRegistAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月14日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.xbrlsend.feedback.service;

import com.joyintech.component.svrreport.chb.contant.ChbConstant;
import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.component.svrreport.chb.dto.ChbSubmitStatusDto;
import com.joyintech.component.svrreport.chb.feedbackmsg.dao.ChbFeedbackMsgComponentMapper;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbEquHoldMapper;
import com.joyintech.tams.common.base.dao.ChbEquSubMapper;
import com.joyintech.tams.common.base.entity.ChbInfoReportXbrl;
import com.joyintech.tams.common.base.entity.ChbMonthAssetPositiondetail;
import com.joyintech.tams.common.base.entity.ChbXbrlbackErrordata;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper;
import com.joyintech.tams.report.xbrlsend.base.dao.SftpFileUtils;
import com.joyintech.tams.report.xbrlsend.base.dao.XbrlSendBaseMapper;
import com.joyintech.tams.report.xbrlsend.feedback.dao.ChbFeedBackXbrlMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 反馈数据处理
 * @author zuoyinyi
 * @version 1.0 2018年5月14日
 * @see ChbFeedBackXbrlMainService
 * @since 1.0
 */
@Service
public class ChbFeedBackXbrlMainService extends CommonExportService<ChbInfoReportXbrl> {


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 上游接口文件编码格式
     */
    @Value("${application.reportInterfFileEncoding}")
    protected String reportInterfFileEncoding;


    @Autowired
    private XbrlSendBaseMapper xbrlSendBaseMapper;

    @Autowired
    private SftpFileUtils sftpFileUtils;

    @Autowired
    private ChbFeedbackMsgComponentMapper chbFeedbackMsgComponentMapper;

    @Autowired
    private EquityInfoRegist001Mapper equityInfoRegist001Mapper;




    /**
     * 查询待处理反馈文件列表
     * @return
     */
    public void getMessageSigle(){

        logger.info("开始待处理文件列表");
        List<File> fileList = this.searchBackFile(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_XBRL_FEEDBACK_PATH),true);

        for(File file :fileList){
            String  fileName = file.getName();
            String[] fileNames = fileName.split("-");
            Map<String,List<ChbXbrlbackErrordata>> resultMap = getFileData(file);
            if(null==resultMap){
                logger.info(fileName+"文件未找到对应的报送信息.跳过处理");
                continue;
            }
            if(resultMap.containsKey(DictCodeListExt3.中债Xbrl状态_xbrl通过.getValue())){
                //xbrl通过代表中债发送成功
                dataBackStatus(fileNames[1],DictCodeListExt2.报送反馈状态_发送成功.getValue(),resultMap.get(DictCodeListExt3.中债Xbrl状态_xbrl通过.getValue()),true);
            }else if(resultMap.containsKey(DictCodeListExt3.中债Xbrl状态_xbrl校验失败.getValue())){
                //xbrl失败代表部分数据错误。需要修复报送反馈状态_发送中
                dataBackStatus(fileNames[1],DictCodeListExt2.报送反馈状态_发送中.getValue(),resultMap.get(DictCodeListExt3.中债Xbrl状态_xbrl校验失败.getValue()),false);
            }else if(resultMap.containsKey(DictCodeListExt3.中债Xbrl状态_中债退回.getValue())){
                //xbrl反馈中债退回状态也是中债退回
                dataBackStatus(fileNames[1],DictCodeListExt2.报送反馈状态_中债退回.getValue(),resultMap.get(DictCodeListExt3.中债Xbrl状态_中债退回.getValue()),true);
            }else if(resultMap.containsKey(DictCodeListExt3.中债Xbrl状态_中债通过.getValue())){
                //xbrl中债通过同步代表中债通过
                dataBackStatus(fileNames[1],DictCodeListExt2.报送反馈状态_中债通过.getValue(),resultMap.get(DictCodeListExt3.中债Xbrl状态_中债通过.getValue()),true);
            }else if(resultMap.containsKey(DictCodeListExt3.中债Xbrl状态_xbrl异常反馈.getValue())){
                //异常反馈代表发送失败
                dataBackStatus(fileNames[1],DictCodeListExt2.报送反馈状态_发送失败.getValue(),resultMap.get(DictCodeListExt3.中债Xbrl状态_xbrl异常反馈.getValue()),true);
            }
            //处理完毕后将文件挪至his目录中
            this.mvFilePath(file);
        }

    }


    public void dataBackStatus(String typeCode,String statusCode,List<ChbXbrlbackErrordata> listData,boolean isEquData){

        //获取批次号。相同批次一定同一个reportNO
        String reportNo = listData.get(0).getReportNo();

        // 更新中债报送结果
        ChbSubmitStatusDto status = new ChbSubmitStatusDto();
        // 报送编码
        status.setReportNo(reportNo);
        // 反馈状态
        status.setSubmitFeedbackStatus(statusCode);

        int insertCount = listData.size();
        int maxDataCountOneTime = 900;

        //报送状态分批处理
        List<ChbXbrlbackErrordata> newDataList = new ArrayList<ChbXbrlbackErrordata>();
        newDataList.addAll(listData);
        // 分批存入
        int part = insertCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
            this.batchInsertErrorData(typeCode,statusCode,newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            this.batchInsertErrorData(typeCode,statusCode,newDataList);
        }


        //投资者身份信息
        if(ChbConstant.FILE_201_TYPE.equals(typeCode)||(ChbConstant.FILE_202_TYPE.equals(typeCode)&&isEquData)){
            if(ChbConstant.FILE_201_TYPE.equals(typeCode)){
                status.setFlag(DictCodeList.报表名称_投资者身份信息登记.getValue());
            }else{
                status.setFlag(DictCodeList.报表名称_投资者持有信息登记.getValue());
            }
            // 更新中债三期反馈状态表
            if(DictCodeListExt3.中债Xbrl状态_中债退回.getValue().equals(statusCode)){
                status.setSubmitFeedbackStatus(DictCodeListExt3.中债Xbrl状态_中债通过.getValue());
                chbFeedbackMsgComponentMapper.updateChbSubmitStatus(status);
                status.setSubmitFeedbackStatus(statusCode);
                status.setSubmitSeqList(listData.stream().map(string -> string.getSeqno()).collect(Collectors.toList()));
            }

            if(DictCodeListExt2.报送反馈状态_发送中.getValue().equals(statusCode)&&ChbConstant.FILE_201_TYPE.equals(typeCode)){
                newDataList.clear();
                newDataList.addAll(listData);
                // 分批存入
                for (int i = 0; i < part; i++ ) {
                    this.batchInsertEquSubData(newDataList.subList(0, maxDataCountOneTime));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                    this.batchInsertEquSubData(newDataList);
                }
            }else {
                chbFeedbackMsgComponentMapper.updateChbSubmitStatus(status);
            }
        //投资者明细信息
        }else if(ChbConstant.FILE_203_TYPE.equals(typeCode)&&isEquData){
            // 更新中债三期反馈状态表
            if(DictCodeListExt3.中债Xbrl状态_中债退回.getValue().equals(statusCode)){
                status.setSubmitFeedbackStatus(DictCodeListExt3.中债Xbrl状态_中债通过.getValue());
                chbFeedbackMsgComponentMapper.updateChbEquDetSubmitStatus(status);
                status.setSubmitFeedbackStatus(statusCode);
                status.setSubmitSeqList(listData.stream().map(string -> string.getSeqno()).collect(Collectors.toList()));
            }
            // 更新中债三期反馈状态表
            chbFeedbackMsgComponentMapper.updateChbEquDetSubmitStatus(status);

        }
    }


    public void batchInsertErrorData(String typeCode,String statusCode,List<ChbXbrlbackErrordata> listData){
        if(DictCodeListExt2.报送反馈状态_发送中.getValue().equals(statusCode)||
                DictCodeListExt2.报送反馈状态_中债退回.getValue().equals(statusCode)){
            //将错误数据批量插入
            xbrlSendBaseMapper.batchInsertData(listData);

            //为xbrl表的业务主键添加数据
            xbrlSendBaseMapper.updateChbXbrlBackBatch(listData,typeCode);
        }
    }

    public void batchInsertEquSubData(List<ChbXbrlbackErrordata> listData){
        //先将基本信息的数据修改为待处理（先后顺序不可调整）
        equityInfoRegist001Mapper.updateChbEquHoldByXbrlErrorDate(listData);
        //再将登记信息删除
        equityInfoRegist001Mapper.deleteXbrlBackSubData(listData);
    }


    /**
     * 根据。ok文件或者对应的处理信息
     * @param file
     * @return
     */
    public Map<String,List<ChbXbrlbackErrordata>> getFileData(File file){
        Map<String,List<ChbXbrlbackErrordata>>  resultMap = new HashMap<>();
        String backStatus = null;
        String updateStatus = null;

        File datFile = getDatFile(file);
        if(file.getName().endsWith(".bad")){
            backStatus = DictCodeListExt3.中债Xbrl状态_xbrl异常反馈.getValue();
            updateStatus = DictCodeList.是否_中债_是.getValue();
        }else if(file.getName().endsWith(".ok")){
            String[] nameS = file.getName().split("-");
            //文件分割为3，代表xbrl验证通过。报送中债中
            if(nameS.length==3){
                backStatus = DictCodeListExt3.中债Xbrl状态_xbrl通过.getValue();
                //文件分割为5.代表预校验错误
            }else if(nameS.length==5){
                backStatus = DictCodeListExt3.中债Xbrl状态_xbrl校验失败.getValue();
            }else if(nameS.length==4){
                updateStatus = DictCodeList.是否_中债_是.getValue();
                if(null!=datFile){
                    backStatus = DictCodeListExt3.中债Xbrl状态_中债退回.getValue();
                }else {
                    backStatus = DictCodeListExt3.中债Xbrl状态_中债通过.getValue();
                }
            }
        }
        List<ChbXbrlbackErrordata> errorList = new ArrayList<>();
        //获取文件对应的报送信息
        ChbInfoReportXbrl xbrl = xbrlSendBaseMapper.getChbInfoReportXbrlByFileId(file.getName().substring(0,15));

        if(null==xbrl){
            return null;
        }
        //xbrl成功及中债通过无对应的dat文件。直接跳过
        BigDecimal errorCount = BigDecimal.ZERO;

        if(null!=datFile&&datFile.exists()){
            //读取文件中内容
            List<String> dataList = new ArrayList<>();
            FileInputStream in = null;
            String line = null;
            try{
                in = new FileInputStream(datFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, StringUtil.getOrElse(reportInterfFileEncoding, "UTF-8")));
                while ((line = reader.readLine()) != null) {
                    dataList.add(line);
                }
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        logger.error(e.getMessage(),e);
                    }
                }
            }

            //将报送文件信息转换为错误信息
            if(!backStatus.equals(DictCodeListExt3.中债Xbrl状态_xbrl异常反馈.getValue())){
                for(String dataStr: dataList){
                    String[] errorStr = dataStr.split("\\|\\+\\|");
                    ChbXbrlbackErrordata data = new ChbXbrlbackErrordata();
                    //将报送流水号赋值。在每个批处理任务中单独将主键信息赋值
                    data.setSeqno(errorStr[0]);
                    data.setReportNo(xbrl.getReportNo());
                    data.setSubmitFeedbackMsg(errorStr[1]);
                    data.setSubmitFeedbackStatus(backStatus);
                    errorList.add(data);
                    errorCount = errorCount.add(BigDecimal.ONE);
                }
            }else{
                for(String dataStr: dataList) {
                    ChbXbrlbackErrordata data = new ChbXbrlbackErrordata();
                    //将报送流水号赋值。在每个批处理任务中单独将主键信息赋值
                    data.setReportNo(xbrl.getReportNo());
                    data.setSubmitFeedbackMsg(dataStr);
                    data.setSubmitFeedbackStatus(backStatus);
                    errorList.add(data);
                    errorCount = errorCount.add(BigDecimal.ONE);
                }
            }
        }else{
            ChbXbrlbackErrordata data = new ChbXbrlbackErrordata();
            //将报送流水号赋值。在每个批处理任务中单独将主键信息赋值
            data.setReportNo(xbrl.getReportNo());
            errorList.add(data);
        }
        //将当前批次的状态字段赋值

        xbrl.setCheckSuccessCount(xbrl.getCheckTotalCount().subtract(errorCount));
        xbrl.setCheckFailedCount(errorCount);
        xbrl.setCheckResult(backStatus);
        xbrl.setUploadResult(updateStatus);
        xbrlSendBaseMapper.updateByPrimaryKeySelective(xbrl);

        resultMap.put(backStatus,errorList);
        return resultMap;
    }
    /**
     * 获取。ok对应的dat文件
     * @param okfile
     * @return
     */
    public File getDatFile(File okfile){
        File baseFile = new File(okfile.getPath().substring(0,okfile.getPath().length()-3)+".dat");
        if (baseFile.exists()) {
            return baseFile;
        }else {
            return null;
        }
    }

    /**
     * 获取目录下所有文件（不获取文件夹）
     * @param directoryPath
     * @return
     */
    public List<File> searchBackFile(String directoryPath,Boolean getOk){
        List<File> list = new ArrayList<>();
        //远程服务器拉取数据
        sftpFileUtils.getFileListByPath(directoryPath);
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }

        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                list.addAll(searchBackFile(file.getAbsolutePath(),getOk));
            } else {
                //如果是.dat文件结尾。不取。待方法内部直接获得
                if((file.getName().endsWith(".dat"))&&getOk){
                    continue;
                }
                list.add(file);
            }
        }
        return list;
    }

    /**
     * 备份文件至his目录
     * @param dataFile
     */
    public void mvFilePath(File dataFile){
        try {
            FileUtils.moveFileToDirectory(dataFile, new File(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_XBRL_FEEDBACK_HIS_PATH) ), true);
            File datFile = new File(dataFile.getPath().replace("ok","dat"));
            if(datFile.exists()){
                FileUtils.moveFileToDirectory(datFile, new File(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_XBRL_FEEDBACK_HIS_PATH) ), true);
            }
        }catch (Exception e){
            logger.error("反馈文件处理结束文件迁移异常，异常内容：" + e.getMessage());
        }
    }

}
