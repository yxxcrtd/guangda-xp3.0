/*
 * 文件名：EquityInfoAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：刘二通
 * 修改时间：2017年12月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.xbrlsend.equityinfo.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbInfoReportXbrl;
import com.joyintech.tams.common.base.entity.ChbSubmitStatus;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfoDistinctService;
import com.joyintech.tams.report.submitmanagement.equityinforegist.common.EquityInfoRegistCommonUtil;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.equityinforegist.service.EquityInfoRegist001Service;
import com.joyintech.tams.report.xbrlsend.base.service.XbrlSendBaseService;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 投资者身份信息数据自动检查报送 业务service
 *
 * @author 刘二通
 * @version 1.0 2017年12月9日
 * @see XbrlSendEquityInfoMainService
 * @since 1.0
 */
@Service
public class XbrlSendEquityInfoMainService extends XbrlSendBaseService<EquityInfoRegist001Dto> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * EquityInfoRegist001Service
     */
    @Autowired
    private EquityInfoRegist001Service equityInfoRegist001Service;
    @Autowired
    private EquityInfoDistinctService equityInfoDistinctService;


    Date batchStartTime;
    int keyCount;
    int validRstListCount;
    int sendKeyListCount;

    /**
     * 自动检查报送
     *
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void autoSendCB(SysBatchExecInfo batchExecuteInfo) throws Exception {

        setBatchBeanName(BatchBeanName.投资者身份报送);
        batchStartTime = DateUtil.now();


        ChbInfoReportXbrl xbrlData = super.getDatFile(DictCodeListExt2.报表名称_投资者身份信息登记.getValue(),batchExecuteInfo.getExecuteDate(), null);

        if(null==xbrlData){
            return;
        }

        //数据检查并反馈成功的数据
        List<EquityInfoRegist001Dto> list =this.beforeCheck(batchExecuteInfo);

        sendKeyListCount = list.size();

        logInfo("sendEquityHoldCHB处理开始");
        ChbInfoReportXbrl xbrl = this.getDataBuildFile(list, true,batchExecuteInfo,xbrlData);
        logInfo("sendEquityHoldCHB处理结束");


        logger.info("报送Xbrl文件处理开始：" + DateUtil.getCurrentDateTime());
        super.sendFile(xbrl);
        logger.info("报送Xbrl文件处理结束：" + DateUtil.getCurrentDateTime());

        logInfoAutoSendResult(batchStartTime, keyCount, validRstListCount,
                sendKeyListCount);

        logInfoBatchEnd();
    }

    @Override
    public List<EquityInfoRegist001Dto> beforeCheck(SysBatchExecInfo batchExecuteInfo) {
        logInfoBatchStart();
        // 剔除检查不通过的数据(主键：识别标识)
        List<EquityInfoRegist001Dto> sendKeyList = new ArrayList<EquityInfoRegist001Dto>();
        equityInfoDistinctService.checkBeforeSubmit();
        logInfoAutoCheckStart();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("statusListStr", DictCodeListExt2.报送数据状态_新建.getValue() + GlobalConstant.COMMA
                + DictCodeListExt2.报送数据状态_修改.getValue());
//            String keyListStr = equityInfoRegist001Service.getKeyList(param);
        logInfo("getAllUnSubmit查询全量数据开始");
        List<EquityInfoRegist001Dto> list =equityInfoRegist001Service.getAllUnSubmit(param);
        logInfo("getAllUnSubmit查询全量数据结束");
        if (CollectionUtils.isEmpty(list)) {
            logInfoNoHandleData(batchStartTime);
            return sendKeyList;
        }
        keyCount = list.size();
        logInfo("sendAllDataBeforeCheck 报送前检查开始");
        List<ExcelValidDto> validRstList = equityInfoRegist001Service.sendAllDataBeforeCheck(list);
        logInfo("sendAllDataBeforeCheck 报送前检查结束");
        logInfoAutoCheckResult(keyCount, validRstList.size());
        logInfoAutoSendStart();
        validRstListCount = validRstList.size();
        if (keyCount == validRstList.size()) {
            logInfoNoCheckOKData(batchStartTime, keyCount, validRstList.size());
            return null;
        }
        Map<String, String> errorDataKeyMap = validRstList.stream().collect(
                JoyinCollectors.toMap(x -> x.getValidKey(), x -> x.getValidKey()));

        if (errorDataKeyMap.size() > 0) {
            for (EquityInfoRegist001Dto equityInfoRegist001Dto : list) {
                if (!errorDataKeyMap.containsKey(equityInfoRegist001Dto.getIndentifyMark())) {
                    sendKeyList.add(equityInfoRegist001Dto);
                }
            }
        }
        else {
            sendKeyList.addAll(list);
        }
        if (StringUtil.isEmptyList(sendKeyList)) {
            logInfoNoCheckOKData(batchStartTime, keyCount, validRstList.size());
            return null;
        }
        return sendKeyList;
    }

    @Override
    public ChbInfoReportXbrl getDataBuildFile(List<EquityInfoRegist001Dto> registList, boolean isSendAllData, SysBatchExecInfo batchExecuteInfo,ChbInfoReportXbrl xbrl) throws IOException {
        //当日身份为空。。但是xbrl仍然要求推送一个空的身份文件
        if (!StringUtil.isEmptyList(registList)) {
            // 处理已经报送的数据（将已报送数据移至履历表，并删除登记表数据）
            logger.info("handleAlreadySubmitData处理开始：" + DateUtil.getCurrentDateTime());
            equityInfoRegist001Service.handleAlreadySubmitData(registList);
            logger.info("handleAlreadySubmitData处理结束：" + DateUtil.getCurrentDateTime());
        }

        //身份正常只会单次报送不需要分批次灌入
        return this.buildFileAndUpdateFlag(registList, isSendAllData,
                true,xbrl);
    }

    @Override
    public ChbInfoReportXbrl buildFileAndUpdateFlag(List<EquityInfoRegist001Dto> registList, boolean isSendAllData, boolean isFirstFlag, ChbInfoReportXbrl xbrl) throws IOException {
        logger.info("拼装报送对象处理开始：" + DateUtil.getCurrentDateTime());
        String defaultPhone = "11111111111";
        String empt="无";
        String defaultTel="11111111";
        String defaultEmail="11@11.com";
        //元素名称
        String title = "BANK_CODE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"OWER_FLAG"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_BANK_NM"
                + ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_BANK_CD"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"INSIDE_OVERSEA_IND"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_COUNTRY"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"DATA_TYPE"
                + ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_ID_OLD"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_ID"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_TYPE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"IDETITY_TYPE"
                + ReportGlobalConstant.XBRL_VERTICAL_LINE+"ORG_IDETITY_TYPE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"QT_IDETITY_TYPE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"IDETITY_CODE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"SPV_ACCT_BANK"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"QT_ACCT_BANK"
                //去掉spv登记编码
                + ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_NAME"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"SEX"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"INV_STYLE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"TEL"
                + ReportGlobalConstant.XBRL_VERTICAL_LINE+"LANDLINE_TEL"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"MAIL_NO"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"MARK"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"DJ_NO";
        //元素字符串结果集
        StringBuffer info = new StringBuffer("");
        if(isFirstFlag){
            info.append(title);
            info.append('\n');
        }

        List<String> registSeq = new ArrayList<String>();
        Map<String, String> registSeqKeyMap = new HashMap<String, String>();
        BigDecimal totalCount = BigDecimal.ZERO;
        //报送表中无报送流水号list
        List<EquityInfoRegist001Dto> unSubmitSeqList = new ArrayList<EquityInfoRegist001Dto>();
        if(CollectionUtils.isNotEmpty(registList)){
            //赋值总条数
            totalCount = new BigDecimal(registList.size());
            //仅支持录入11 位定长数字，不得填写“无”。如无手机号码，应统一填写“11111111111”（11个 1）。
            //            不得填写“无”，如无固定电话，应统一填写“11111111”（8 个1）。
            //            不得填写“无”，如无固定电话，应统一填写“11@11.com”。

            List<String> sequenceList = CommonUtil.getBatchSequenceIndexData(DataIndexSequenceType.中债登记管理, registList.size());
            int index = 0;
            for (EquityInfoRegist001Dto dto : registList) {
                //针对取消手工处理再报送
                String currRegistSeq = "";
                if(DictCodeListExt2.报送反馈状态_发送失败.getValue().equals(dto.getSubmitFeedbackStatusCode())
                        || DictCodeListExt2.报送反馈状态_中债退回.getValue().equals(dto.getSubmitFeedbackStatusCode())){
//                   currRegistSeq = CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理);
                    currRegistSeq = CommonUtil.getSequenceStr(sequenceList, index);
                    index++;
                    registSeq.add(currRegistSeq);
                    //将报送流水为空的放入list
                    dto.setSumitSeq(currRegistSeq);
                    unSubmitSeqList.add(dto);
                }
                else if(StringUtil.isEmpty(dto.getSumitSeq())){
                    currRegistSeq = CommonUtil.getSequenceStr(sequenceList, index);
                    index++;
                    registSeq.add(currRegistSeq);
                    //将报送流水为空的放入list
                    dto.setSumitSeq(currRegistSeq);
                    unSubmitSeqList.add(dto);
                }
                else{
                    registSeq.add(dto.getSumitSeq());
                    currRegistSeq = dto.getSumitSeq();
                }
                String  value = "";
                //当为识别标识变更时
                if(dto.getType().equals(
                        EquityInfoRegistCommonUtil.getDictCodeAndName(DictCode.数据类型_中债, DictCodeListExt2.数据类型_中债_识别标识变更.getValue()))){
                    value = dto.getBankCode()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getIsBelongBank()
                            + ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getTheBankName()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getTheBankCode()
                            + ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getHolderMark()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getHolderRegion()
                            + ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getType()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getOrgIndentifyMark()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getIndentifyMark()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getHolderType()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getPersonalIdentifyType()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getInstitutionIdentifyType()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getOtherIdentifyName()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getIdentifyNumber()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getSpvCapAccBank()
                            + ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getOtherCapAccBank()+ ReportGlobalConstant.XBRL_VERTICAL_LINE/*+dto.getSpvRegistCode()+"|"*/
                            //要求原则上境内投资者名称中不得含有空格。
                            +(dto.getHolderName().replaceAll(Pattern.quote(StringUtil.EMPTY), ""))+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getHolderSex()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getHolderRiskPrefer()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getHolderCellphone()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getHolderTelephone()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getHolderEmail()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getRemark()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getSumitSeq();
                }
                else {
                    value = dto.getBankCode()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getIsBelongBank()
                            + ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getTheBankName()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getTheBankCode()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getHolderMark()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getHolderRegion()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getType()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getIndentifyMark()
                            + ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getHolderType()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getPersonalIdentifyType()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getInstitutionIdentifyType()
                            + ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getOtherIdentifyName()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getIdentifyNumber()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getSpvCapAccBank()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getOtherCapAccBank()+ ReportGlobalConstant.XBRL_VERTICAL_LINE/*+dto.getSpvRegistCode()+"|"*/
                            //要求原则上境内投资者名称中不得含有空格。
                            +(dto.getHolderName().replaceAll(Pattern.quote(StringUtil.EMPTY), ""))+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getHolderSex()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getHolderRiskPrefer()+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +(StringUtil.equals(dto.getHolderCellphone(), empt)||StringUtil.isEmpty(dto.getHolderCellphone())?defaultPhone:dto.getHolderCellphone())+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +(StringUtil.equals(dto.getHolderTelephone(), empt)||StringUtil.isEmpty(dto.getHolderTelephone())?defaultTel:dto.getHolderTelephone())+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +(StringUtil.equals(dto.getHolderEmail(), empt)||StringUtil.isEmpty(dto.getHolderEmail())?defaultEmail:dto.getHolderEmail())+ ReportGlobalConstant.XBRL_VERTICAL_LINE
                            +dto.getRemark()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getSumitSeq();
                }
                info.append(value);
                info.append('\n');
                registSeqKeyMap.put(currRegistSeq, dto.getIndentifyMark());
            }
        }

        xbrl.setCheckTotalCount(totalCount);
        logger.info("拼装报送对象处理结束：" + DateUtil.getCurrentDateTime());
        //更新报送状态表
        logger.info("doUpdateSubmtitStatus处理开始：" + DateUtil.getCurrentDateTime());
        equityInfoRegist001Service.doUpdateSubmtitStatus(registList,unSubmitSeqList, isSendAllData);
        logger.info("doUpdateSubmtitStatus处理结束：" + DateUtil.getCurrentDateTime());

        //文件推送至xbrl文件
        super.pushDataToFile(info,super.getSendFile(xbrl,isFirstFlag));

        //推送xbrl文件后修改数据状态
        super.afterSendStatus(this.buildStatus(registList,xbrl.getReportNo(),isSendAllData),isSendAllData,xbrl);
        return xbrl;
    }

    @Override
    public List<ChbSubmitStatus> buildStatus(List<EquityInfoRegist001Dto> registList, String reportNo,boolean isSendAllData) {
        List<ChbSubmitStatus> chbSubmitStatusList = new ArrayList<>();
        for (EquityInfoRegist001Dto seq : registList) {
            // 中债报送状态
            ChbSubmitStatus submitStatus = new ChbSubmitStatus();
            // 报送序列号
            submitStatus.setSeqno(seq.getSumitSeq());
            // 报送编码
            submitStatus.setReportNo(reportNo);
            // 报送流水号
            submitStatus.setSubmitNum(seq.getSumitSeq());
            // 业务类型
            submitStatus.setSubmitType(DictCodeListExt2.报表名称_投资者身份信息登记.getValue());
            //识别标识  通过流水号获取
            submitStatus.setUniqueIdenti(seq.getIndentifyMark());
            submitStatus.setEquityConfDate(DateUtil.now());
            submitStatus.setSubmitFeedbackStatus(DictCodeListExt2.报送反馈状态_发送中.getValue());
            // 有效
            submitStatus.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            chbSubmitStatusList.add(submitStatus);
        }
        return chbSubmitStatusList;
    }

}
