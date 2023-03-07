/*
 * 文件名：AutoSendCBBaseService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2017年12月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.xbrlsend.base.service;

import com.joyintech.component.svrreport.chb.contant.ChbConstant;
import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.common.db.SvrDBUtil;
import com.joyintech.tams.common.base.dao.ChbAutosendBatchLogMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.cnbond.dao.CnBondInterfMapper;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.xbrlsend.base.dao.GzFileUtils;
import com.joyintech.tams.report.xbrlsend.base.dao.XbrlSendBaseMapper;
import com.joyintech.tams.report.xbrlsend.base.dao.SftpFileUtils;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import org.apache.commons.collections.CollectionUtils;
import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 中债自动检查报送共通基础service
 *
 * @author 刘二通
 * @version 1.0 2017年12月9日
 * @see XbrlSendBaseService
 * @since 1.0
 */
@Service
public abstract class XbrlSendBaseService<T> {

    /**
     * 批处理名称
     */
    private BatchBeanName batchBeanName;

    @Autowired
    private SftpFileUtils sftpFileUtils;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * DBUtil
     */
    @Autowired
    private SvrDBUtil dbUtil;

    /**
     * ChbAutosendBatchLogMapper
     */
    @Autowired
    private ChbAutosendBatchLogMapper chbAutosendBatchLogMapper;

    @Autowired
    private XbrlSendBaseMapper xbrlSendBaseMapper;


    @Autowired
    private CnBondInterfMapper cnBondInterfMapper;

    /**
     * 写日志
     *
     * @param logStr 日志内容
     * @see
     * @since 1.0
     */
    public void logInfo(String logStr) {
        logger.info("【" + batchBeanName.getBatchName() + "】" + logStr + " ： "
                    + DateUtil.getCurrentDateTime());
    }

    /***
     * 统一入口
     * @param batchExecuteInfo
     */
    public abstract void autoSendCB(SysBatchExecInfo batchExecuteInfo) throws Exception;


    /**
     * 数据检查
     * @param batchExecuteInfo
     * @return
     */
    public abstract List<T> beforeCheck(SysBatchExecInfo batchExecuteInfo) ;


    /**
     * 获取数据并且组装文件
     * @param dtoList
     * @param isSendAllData
     */
    public abstract ChbInfoReportXbrl getDataBuildFile(List<T> dtoList, boolean isSendAllData,SysBatchExecInfo batchExecuteInfo,ChbInfoReportXbrl xbrl) throws IOException;

    /**
     * 文件组装详细方法
     * @param registList 要报送的信息
     * @param isSendAllData 是否推送全部数据
     * @throws IOException
     */
    public abstract ChbInfoReportXbrl buildFileAndUpdateFlag(List<T> registList, boolean isSendAllData,boolean isFirstFlag,ChbInfoReportXbrl xbrl) throws IOException;



    public abstract List<ChbSubmitStatus> buildStatus(List<T> registList,String reportNo,boolean isSendAllData);

    /**
     * 数据状态更新
     * @param chbSubmitStatusList  前台界面才会提供该数据  批处理在表中
     * @param isSend  是批处理报送还是前台报送  批处理true 前台false
     * @param isSend  报送信息.包含总条数等等的
     * @return
     */
    public Boolean afterSendStatus(List<ChbSubmitStatus> chbSubmitStatusList,Boolean isSend, ChbInfoReportXbrl xbrl){
        logger.info("CnBondInterfService insertChbSubmitStatusListByPart处理开始：" + DateUtil.getCurrentDateTime());


        if(xbrl.getSubmitType().equals(DictCodeListExt2.报表名称_投资者明细信息登记.getValue())){
            if(!isSend){
                this.insertChbEquDetSubmitStatusListByPart(chbSubmitStatusList);
            }else{
                xbrlSendBaseMapper.insertChbEquDetSubmitStatusList(xbrl,DictCodeListExt2.报送反馈状态_发送中.getValue());
            }
        }else if(xbrl.getSubmitType().equals(DictCodeListExt2.报表名称_投资者持有信息登记.getValue())){
            if(!isSend){
                this.insertChbSubmitStatusListByPart(chbSubmitStatusList);
            }else{
                xbrlSendBaseMapper.insertChbSubmitStatusList(xbrl,DictCodeListExt2.报送反馈状态_发送中.getValue());
            }

        }else if(xbrl.getSubmitType().equals(DictCodeListExt2.报表名称_投资者身份信息登记.getValue())){
            this.insertChbSubmitStatusListByPart(chbSubmitStatusList);
        }
        logger.info("CnBondInterfService insertChbSubmitStatusListByPart处理结束：" + DateUtil.getCurrentDateTime());

        return true;
    }




    /**
     * 分批登录中债报送状态数据
     *
     * @param chbInfoReportList 中债报送状态数据
     * @see
     * @since 1.0
     */
    public void insertChbEquDetSubmitStatusListByPart(List<ChbSubmitStatus> chbSubmitStatusList) {
        if (StringUtil.isEmptyList(chbSubmitStatusList)) {
            return;
        }

        int maxDataCountOneTime = 100;

        // 报送数据
        int dataCount = chbSubmitStatusList.size();
        if (dataCount <= maxDataCountOneTime) {
            cnBondInterfMapper.insertChbEquDetSubmitStatusList(chbSubmitStatusList);
        }
        else {
            // 分批处理
            List<ChbSubmitStatus> newDataList = new ArrayList<ChbSubmitStatus>();
            newDataList.addAll(chbSubmitStatusList);

            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                cnBondInterfMapper.insertChbEquDetSubmitStatusList(
                        newDataList.subList(0, maxDataCountOneTime));
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newDataList)) {
                cnBondInterfMapper.insertChbEquDetSubmitStatusList(newDataList);
            }
        }
    }

    /**
     * 分批登录中债报送状态数据
     *
     * @param chbInfoReportList 中债报送状态数据
     * @see
     * @since 1.0
     */
    private void insertChbSubmitStatusListByPart(List<ChbSubmitStatus> chbSubmitStatusList) {
        if (StringUtil.isEmptyList(chbSubmitStatusList)) {
            return;
        }

        /***三期CHB_SUBMIT_STATUS去除改造  同批次要么身份要么持有,不会同时进  20220310**/
        String SubmitsType = chbSubmitStatusList.get(0).getSubmitType();
        /***三期CHB_SUBMIT_STATUS去除改造  同批次要么身份要么持有,不会同时进 20220310**/

        int maxDataCountOneTime = 100;

        // 报送数据
        int dataCount = chbSubmitStatusList.size();
        if (dataCount <= maxDataCountOneTime) {
            cnBondInterfMapper.insertChbSubmitStatusList(chbSubmitStatusList,SubmitsType);
        }
        else {
            // 分批处理
            List<ChbSubmitStatus> newDataList = new ArrayList<ChbSubmitStatus>();
            newDataList.addAll(chbSubmitStatusList);

            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                cnBondInterfMapper.insertChbSubmitStatusList(
                        newDataList.subList(0, maxDataCountOneTime),SubmitsType);
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newDataList)) {
                cnBondInterfMapper.insertChbSubmitStatusList(newDataList,SubmitsType);
            }
        }
    }


    /**
     * 统一执行推送操作
     * @param xbrl  推送文件
     * @throws IOException
     */
    @Transactional
    public void sendFile(ChbInfoReportXbrl xbrl) throws Exception {
        String gzPath =  getSendPath(xbrl);

        File datFile = new File(gzPath);
        //生成gz文件
        if(datFile.exists()){
            GzFileUtils.doGzipFile(getSendPath(xbrl),gzPath+".gz");
            SysUploadFileInfo file = new SysUploadFileInfo();
            file.setPath(gzPath+".gz");
            sftpFileUtils.upload(file,SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_XBRL_SEND_PATH));
            SysUploadFileInfo fileok = new SysUploadFileInfo();
            File okFile = new File(gzPath.replace(".dat","")+".ok");
            okFile.createNewFile();
            fileok.setPath(okFile.getPath());
            sftpFileUtils.upload(fileok,SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_XBRL_SEND_PATH));
            //记录文件入库
            xbrlSendBaseMapper.insert(xbrl);
        }

    }


    /**
     *  获取xbrl信息
     * @param InterfCode
     * @param execDate
     * @param uplodResult
     * @return
     */
    public ChbInfoReportXbrl getMaxXbrlDataByValueDate(String InterfCode,List<Date> execDate,String checkResult){
        return xbrlSendBaseMapper.getMaxXbrlDataByValueDate(InterfCode,execDate,checkResult);
    }

    /**
     *  获取xbrl信息
     * @param InterfCode
     * @param execDate
     * @param uplodResult
     * @return
     */
    public ChbInfoReportXbrl getErrorData(String InterfCode,List<Date> execDate,List<EquityInfoRegist001Dto> dataList){
        return xbrlSendBaseMapper.getErrorData(InterfCode,execDate,dataList);
    }


    /**
     * 统一执行文件推送
     * 当日同类型 并且反馈状态为   报送反馈状态_发送中/报送反馈状态_发送成功/报送反馈状态_审核文件下载失败  则返回NULL。需要将xbrl报送暂停。待数据处理后才可以继续报送下去
     * @param InterfCode  接口编号
     * @throws IOException
     */
    public ChbInfoReportXbrl getDatFile(String InterfCode,Date execDate,String uplodResult){
        //获取当前xbrl状态
        ChbInfoReportXbrl xbrl = this.getMaxXbrlDataByValueDate(InterfCode, Arrays.asList(execDate),uplodResult);
        String fileCode = "";
        switch (InterfCode){
            //身份信息
            case "14" : fileCode = ChbConstant.FILE_201_TYPE; break;
            //持有信息
            case "16" : fileCode = ChbConstant.FILE_202_TYPE; break;
            //明细信息
            case "26" : fileCode = ChbConstant.FILE_203_TYPE; break;
        }

        String chbCode = "01";
        String xbrlCode = "001";
        if(StringUtil.isNotEmpty(xbrl)){
            String type = xbrl.getCheckResult();
            //报送状态为空则代表xbrl发送中
            if(StringUtil.isNotEmpty(type)){
                switch (type){
                    //报送反馈状态_发送失败 -- 重新报送 中债批次沿用，报送批次加1  ※只有前台可以使用
//                case "02" :
//                    chbCode = xbrl.getChbIntCode();
//                    xbrlCode = String.format("%03d", Integer.parseInt(xbrl.getXbrlIntCode()) + 1);
//                    break;

                    //中债Xbrl状态_中债通过 -- 中债批次+1，报送批次首次
                    //中债Xbrl状态_中债退回 -- 中债批次+1，报送批次首次
                    case "01" :
                    case "03" :
                    case "04" :
                    case "05" :
                        chbCode = String.format("%02d", Integer.parseInt(xbrl.getChbIntCode()) + 1);
                        break;

                    //中债Xbrl状态_xbrl异常反馈 --中债批次沿用。报送批次+1
                    case "02" :
                        chbCode = xbrl.getChbIntCode();
                        xbrlCode = String.format("%03d", Integer.parseInt(xbrl.getXbrlIntCode()) + 1);
                        execDate = xbrl.getSendTime();

                        //重新发送批次后将上批次的报送信息修改掉
                        xbrl.setUploadResult(DictCodeList.是否_中债_是.getValue());
                        xbrlSendBaseMapper.updateByPrimaryKeySelective(xbrl);
                        break;
                    //中债Xbrl状态_xbrl发送中 - 不应报送
                    //中债Xbrl状态_xbrl通过 - 不应报送
                    default:  return null;
                }
            }else {
                return null;
            }
        }

        String dataFilePath = DateUtil.format(execDate,"yyyyMMdd") + GlobalConstant.SPLIT +
                fileCode  + GlobalConstant.SPLIT + chbCode + GlobalConstant.SPLIT+xbrlCode +".dat";

        ChbInfoReportXbrl xbrlNew = new ChbInfoReportXbrl();
        xbrlNew.setReportNo(Sid.nextShort(20));
        xbrlNew.setSendTime(execDate);
        xbrlNew.setRepMesNo(Sid.nextShort(20));
        xbrlNew.setSubmitType(InterfCode);
        xbrlNew.setUploadResult(DictCodeListExt3.中债Xbrl状态_xbrl发送中.getValue());
        xbrlNew.setChbIntCode(chbCode);
        xbrlNew.setXbrlIntCode(xbrlCode);
        xbrlNew.setUploadFileId(dataFilePath.substring(0,15));
        xbrlNew.setCheckFileId(dataFilePath);

        return xbrlNew;
    }


    public String getSendPath(ChbInfoReportXbrl xbrl){
        return SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_XBRL_SEND_PATH) + File.separator + "file" + File.separator +  DateUtil.format(new Date(),"yyyyMMdd")  + File.separator + xbrl.getCheckFileId();
    }
    public File getSendFile(ChbInfoReportXbrl xbrl,Boolean isFirstFlag){
        File file = new File(this.getSendPath(xbrl));
        if(file.exists()&&isFirstFlag){
            file.delete();
        }
        return file;
    }

    /**
     * 写入文件流程
     * @param stringBuffer
     * @param file
     * @throws IOException
     */
    public void pushDataToFile(StringBuffer stringBuffer,File file) throws IOException {
        FileWriter fo = null;
        BufferedWriter writer = null;
        try {
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            fo = new FileWriter(file,true);
            writer = new BufferedWriter(fo);
            writer.write(stringBuffer.toString());
            writer.flush();
        }catch (Exception e){
            logger.error("文件推送失败",e);
            throw new IOException(e);
        }finally {
            writer.flush();
            writer.close();
            fo.close();
        }
    }


    /**
     * 输出日志： 批处理开始
     *
     * @see
     * @since 1.0
     */
    public void logInfoBatchStart() {
        logInfo("批处理开始");
    }

    /**
     * 输出日志： 自动检查处理开始
     *
     * @see
     * @since 1.0
     */
    public void logInfoAutoCheckStart() {
        logInfo("自动检查处理开始");
    }

    /**
     * 输出日志： 无待处理数据, 批处理结束
     *
     * @param batchStartTime 批处理开始时间
     * @see
     * @since 1.0
     */
    public void logInfoNoHandleData(Date batchStartTime) {
        String logStr = "无待处理数据, 批处理结束";
        logInfo(logStr);
        insertResultLog(logStr, batchStartTime);
    }

    /**
     * 输出日志： 自动检查处理结果
     *
     * @param checkDataCount 检查数据条数
     * @param checkNGDataCount 检查不过数据条数
     *
     * @see
     * @since 1.0
     */
    public void logInfoAutoCheckResult(int checkDataCount, int checkNGDataCount) {
        String logStr = "自动检查处理结束, 共检查数据 " + checkDataCount + " 条, 检查不过数据 " + checkNGDataCount
                        + " 条";
        logInfo(logStr);
    }

    /**
     * 输出日志： 自动报送处理开始
     *
     * @see
     * @since 1.0
     */
    public void logInfoAutoSendStart() {
        logInfo("自动报送处理开始");
    }

    /**
     * 输出日志： 无检查通过数据, 批处理结束
     *
     * @param batchStartTime 批处理开始时间
     * @param checkDataCount 检查数据条数
     * @param checkNGDataCount 检查不过数据条数
     * @see
     * @since 1.0
     */
    public void logInfoNoCheckOKData(Date batchStartTime,
                                     int checkDataCount, int checkNGDataCount) {
        logInfo("无检查通过数据, 批处理结束");
        String logStr = "共检查数据 " + checkDataCount + " 条, 检查不过数据 " + checkNGDataCount
            + " 条, 无检查通过数据, 批处理结束";
        insertResultLog(logStr, batchStartTime);
    }

    /**
     * 输出日志： 自动报送处理结果
     *
     * @param batchStartTime 批处理开始时间
     * @param checkDataCount 检查数据条数
     * @param checkNGDataCount 检查不过数据条数
     * @param sendDataCount 报送数据条数
     * @see
     * @since 1.0
     */
    public void logInfoAutoSendResult(Date batchStartTime,
                                      int checkDataCount, int checkNGDataCount,
                                      int sendDataCount) {
        logInfo("自动报送处理结束, 共报送数据 " + sendDataCount + " 条 ");
        insertResultLog("共检查数据 " + checkDataCount + " 条, 检查不过数据 " + checkNGDataCount + " 条, 共报送数据 "
                        + sendDataCount + " 条 ", batchStartTime);
    }

    /**
     * 输出日志： 批处理结束
     *
     * @see
     * @since 1.0
     */
    public void logInfoBatchEnd() {
        logInfo("批处理结束");
    }

    /**
     * 将处理结果写入日志表
     *
     * @param logStr 日志内容
     * @param batchStartTime 批处理执行开始时间
     * @see
     * @since 1.0
     */
    public void insertResultLog(String logStr, Date batchStartTime) {
        ChbAutosendBatchLog batchLog = new ChbAutosendBatchLog();
        batchLog.setBatchExeId(dbUtil.getSequenceIndexData(DataIndexSequenceType.批处理用));
        batchLog.setTaskId(batchBeanName.getTaskId());
        batchLog.setStartTime(batchStartTime);
        batchLog.setEndTime(DateUtil.now());
        batchLog.setBatchResult(logStr);

        chbAutosendBatchLogMapper.insert(batchLog);
    }

    /**
     * 批处理异常结束时，写日志到日志表
     *
     * @param batchStartTime 批处理执行开始时间
     * @see
     * @since 1.0
     */
    public void insertResultLogException(Date batchStartTime) {
        insertResultLog("执行出现异常, 具体信息请联系管理员查看后台日志", batchStartTime);
    }

    /**
     * 获取 batchBeanName
     * @return batchBeanName.
     */
    public BatchBeanName getBatchBeanName() {
        return batchBeanName;
    }

    /**
     * 设置 batchBeanName
     * @param batchBeanName batchBeanName
     */
    public void setBatchBeanName(BatchBeanName batchBeanName) {
        this.batchBeanName = batchBeanName;
    }
}