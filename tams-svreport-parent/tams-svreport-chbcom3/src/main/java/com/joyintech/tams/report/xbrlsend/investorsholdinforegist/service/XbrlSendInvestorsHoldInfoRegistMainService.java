/*
 * 文件名：InvestorsHoldInfoRegistAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月14日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.xbrlsend.investorsholdinforegist.service;

import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbInfoReportXbrl;
import com.joyintech.tams.common.base.entity.ChbSubmitStatus;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.service.InvestorsHoldInfoDistinctService;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dto.EquityInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.service.InvestorsHoldInfoRegist001Service;
import com.joyintech.tams.report.xbrlsend.base.service.XbrlSendBaseService;
import com.joyintech.tams.report.xbrlsend.investorsholdinforegist.dao.InvestorsHoldInfoXbrlMapper;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 投资者持有信息数据自动检查报送 业务service
 * @author zuoyinyi
 * @version 1.0 2018年5月14日
 * @see XbrlSendInvestorsHoldInfoRegistMainService
 * @since 1.0
 */
@Service
public class XbrlSendInvestorsHoldInfoRegistMainService extends XbrlSendBaseService<InvestorsHoldInfoRegist001Dto> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * InvestorsHoldInfoRegist001Service
     */
    @Autowired
    private InvestorsHoldInfoRegist001Service investorsHoldInfoRegist001Service;


    /**
     * InvestorsHoldInfoRegistAutoSendCBMapper
     */
    @Autowired
    private InvestorsHoldInfoXbrlMapper autoSendCBMapper;
    @Autowired
    private InvestorsHoldInfoDistinctService investorsHoldInfoDistinctService;

    Date batchStartTime;
    int keyCount;
    int validRstListCount;
    int sendKeyListCount;


    /**
     * 自动检查报送
     *
     * @param  batchExecuteInfo 持有日期
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void autoSendCB(SysBatchExecInfo batchExecuteInfo) throws Exception {

        setBatchBeanName(BatchBeanName.投资者持有报送);
        batchStartTime = DateUtil.now();
        ChbInfoReportXbrl datfile = super.getDatFile(DictCodeListExt2.报表名称_投资者持有信息登记.getValue(),batchExecuteInfo.getExecuteDate(), null);
        if(null==datfile){
            return;
        }

        logInfoBatchStart();
        //批处理数据检查
        this.beforeCheck(batchExecuteInfo);
        logInfo("InvestorsHoldInfo处理开始");
        ChbInfoReportXbrl xbrl   = this.getDataBuildFile(null, true,batchExecuteInfo,datfile);
        logInfo("InvestorsHoldInfo处理结束");

        logger.info("报送Xbrl文件处理开始：" + DateUtil.getCurrentDateTime());
        super.sendFile(xbrl);
        logger.info("报送Xbrl文件处理结束：" + DateUtil.getCurrentDateTime());




        logInfoAutoSendResult(batchStartTime,  keyCount,
            sendKeyListCount, validRstListCount);
        logInfoBatchEnd();

    }

    @Override
    public List beforeCheck(SysBatchExecInfo batchExecuteInfo) {
        investorsHoldInfoDistinctService.checkBeforeSubmit();
        // 查询所有未报送数据条数
        logInfo("查询所有未报送数据条数开始");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("holdDate", DateUtil.format(batchExecuteInfo.getExecuteDate()));
        int  keyCount = autoSendCBMapper.selectAllUnregistDataCount(paramMap);
        logInfo("查询所有未报送数据条数结束，结果条数：" +  keyCount);

        if ( keyCount <= 0) {
            logInfoNoHandleData(batchStartTime);
            return null;
        }

        //add by liubin start  查询未报送数据中根据中债五要素判断是否有重复数据
        logInfo("查询未报送数据中根据中债五要素判断是否有重复数据开始...");
        List<String> repeatHoldInfoData = autoSendCBMapper.selectUnregistDataRepeatDataByFiveElem(paramMap);
        logInfo("查询未报送数据中根据中债五要素判断是否有重复数据开始结束，数据条数："+repeatHoldInfoData.size());
        paramMap.put("repeatHoldInfoData", repeatHoldInfoData);
        //add by liubin end

        // 查询所有未报送、但对应身份信息未报送数据条数
        logInfo("查询所有未报送、但对应身份信息未报送数据条数开始");
        int sendKeyListCount = autoSendCBMapper.selectAllUnregistCheckNgDataCount(
                paramMap);
        logInfo("查询所有未报送、但对应身份信息未报送数据条数结束，结果条数：" + sendKeyListCount);

        int validRstListCount =  keyCount - sendKeyListCount;
        if (validRstListCount <= 0) {
            logInfoNoCheckOKData(batchStartTime,  keyCount,  keyCount);
            return null;
        }
        return null;
    }

    @Override
    public ChbInfoReportXbrl getDataBuildFile(List<InvestorsHoldInfoRegist001Dto> dtoList, boolean isSendAllData, SysBatchExecInfo batchExecuteInfo,ChbInfoReportXbrl xbrl) throws IOException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("holdDate", DateUtil.format(batchExecuteInfo.getExecuteDate()));
        xbrl.setDataDate(batchExecuteInfo.getExecuteDate());
        //删除之前先将数据入His表 add by yuluan 2019-03-29
        autoSendCBMapper.insertIntoHisAllAlreadySubmitRegistData(paramMap);
        // 删除重复已报送数据
        logInfo("开始删除重复已报送数据");
        autoSendCBMapper.deleteAllAlreadySubmitRegistData(paramMap);
        logInfo("删除重复已报送数据结束");

        int dataCountPerTime = Integer.valueOf(
                CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME));

        logInfo("开始按批次处理持有信息数据，每批次处理数据条数：" + dataCountPerTime);
        paramMap.put("dataCountPerTime", dataCountPerTime);

        // 处理批次
        int handleIndex = 1;
        while (true) {
            // 查询符合条件的待报送数据（前500000条）
            boolean hasData = this.handleDataPerTime(handleIndex, paramMap,isSendAllData,xbrl);
            if (!hasData) {
                break;
            }
            handleIndex++;
        }

        logInfo("开始组装gz文件并且推送!");


        return xbrl;
    }


    //个性化组装文件
    private boolean handleDataPerTime(int handleIndex, Map<String, Object> paramMap, boolean isSendAllData,ChbInfoReportXbrl xbrl) throws IOException {
        logInfo("处理开始，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        boolean hasData = true;

        // 清空数据报送临时表
        logInfo("开始清空数据报送临时表");
        autoSendCBMapper.truncateSubTempTable();
        logInfo("清空数据报送临时表结束");

        // 将待处理数据的报送流水号插入临时表
        logInfo("开始将待处理数据的登记序号插入临时表");
        autoSendCBMapper.insertUnregistCheckOkDataToTempTable(paramMap);
        logInfo("将待处理数据的登记序号插入临时表结束");

        // 查询待报送数据
        logInfo("开始查询待报送数据");
        List<InvestorsHoldInfoRegist001Dto> unregistDataList = autoSendCBMapper.selectAllUnregistCheckOkData();
        logInfo("查询待报送数据结束，数据条数：" + unregistDataList.size());
        if (StringUtil.isEmptyList(unregistDataList)) {
            logInfo("已无待报送数据，处理结束，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return false;
        }

        // 将待报送数据批量更新报送状态
        logInfo("开始将待报送数据批量更新报送状态");
        autoSendCBMapper.updateSubmitStatus();
        logInfo("将待报送数据批量更新报送状态结束");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("batchExeNeedSendDataList", unregistDataList);

        Map<String, List<InvestorsHoldInfoRegist001Dto>> registMap = investorsHoldInfoRegist001Service.getSubmitList(
                param);
        Iterator<Map.Entry<String, List<InvestorsHoldInfoRegist001Dto>>> entries = registMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, List<InvestorsHoldInfoRegist001Dto>> entry = entries.next();
            List<InvestorsHoldInfoRegist001Dto> registList = entry.getValue();
            //光大个性化不考虑代销其他行数据..entries应只有一个key handleIndex ==1 即代表第一批50W数据的文件组装
            this.buildFileAndUpdateFlag(registList, isSendAllData , handleIndex==1?true:false
                    ,xbrl);
        }
        unregistDataList.clear();
        logInfo("处理结束，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return hasData;
    }

    @Override
    public ChbInfoReportXbrl buildFileAndUpdateFlag(List<InvestorsHoldInfoRegist001Dto> registList, boolean isSendAllData, boolean isFirstFlag, ChbInfoReportXbrl xbrl) throws IOException {
        logger.info("拼装报送对象处理开始：" + DateUtil.getCurrentDateTime());
        //元素名称
        String title = "BANK_CODE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"PRODUCT_CODE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE + "CUST_ID"
                + ReportGlobalConstant.XBRL_VERTICAL_LINE + "CUST_DATE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CURRENCY_CD"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_SHARE"
                + ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_AMT"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_AMT_CNY"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"DJ_NO";

        //元素字符串结果集
        StringBuffer info = new StringBuffer("");
        if(isFirstFlag){
            info.append(title);
            info.append('\n');
        }
        List<String> registSeq = new ArrayList<String>();
        Map<String, String> registSeqKeyMap = new HashMap<String, String>();

        //报送表中无报送流水号list
        List<InvestorsHoldInfoRegist001Dto> unSubmitSeqList = new ArrayList<InvestorsHoldInfoRegist001Dto>();
        if (CollectionUtils.isNotEmpty(registList)) {
            xbrl.setCheckTotalCount((null!=xbrl.getCheckTotalCount()?xbrl.getCheckTotalCount(): BigDecimal.ZERO)
                    .add(new BigDecimal(registList.size())));
            List<String> sequenceList = CommonUtil.getBatchSequenceIndexData(DataIndexSequenceType.中债登记管理,
                    registList.size());
            int index = 0;
            for (InvestorsHoldInfoRegist001Dto dto : registList) {
                // 针对取消手工处理再报送
                String registSeqNo = "";
                String currRegistSeq = "";
                if (DictCodeListExt2.报送反馈状态_发送失败.getValue().equals(dto.getSubmitFeedbackStatus())
                        || DictCodeListExt2.报送反馈状态_中债退回.getValue().equals(dto.getSubmitFeedbackStatus())) {
                    // registSeqNo =
                    // CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理);
                    registSeqNo = CommonUtil.getSequenceStr(sequenceList, index);
                    index++;
                    // 将报送流水为空的放入list
                    currRegistSeq = registSeqNo;
                    dto.setSumitSeq(currRegistSeq);
                    unSubmitSeqList.add(dto);
                } else if (StringUtil.isEmpty(dto.getSumitSeq())) {
                    // registSeqNo =
                    // CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理);
                    registSeqNo = CommonUtil.getSequenceStr(sequenceList, index);
                    index++;
                    dto.setSumitSeq(registSeqNo);
                    currRegistSeq = registSeqNo;
                    unSubmitSeqList.add(dto);
                } else {
                    registSeqNo = dto.getSumitSeq();
                    currRegistSeq = registSeqNo;
                }
                registSeq.add(registSeqNo);
                registSeqKeyMap.put(currRegistSeq, dto.getOrgCode() + "," + dto.getIndentifyMark() + ","
                        + dto.getPrdRegistCode() + "," + DateUtil.format(dto.getHoldDate()) + "," + dto.getCcy());

                String value =  dto.getRegistBankCode() + ReportGlobalConstant.XBRL_VERTICAL_LINE + dto.getPrdRegistCode() + ReportGlobalConstant.XBRL_VERTICAL_LINE
                        + dto.getIndentifyMark() + ReportGlobalConstant.XBRL_VERTICAL_LINE + DateUtil.format(dto.getHoldDate()) + ReportGlobalConstant.XBRL_VERTICAL_LINE + dto.getCcy() + ReportGlobalConstant.XBRL_VERTICAL_LINE
                        + dto.getQty() + ReportGlobalConstant.XBRL_VERTICAL_LINE + dto.getAmount() + ReportGlobalConstant.XBRL_VERTICAL_LINE + dto.getCnyAmt() + ReportGlobalConstant.XBRL_VERTICAL_LINE + registSeqNo;
                info.append(value);
                info.append('\n');
            }
        }
        // 更新报送状态表
        // modify by etliu 20181011 start
        //this.doUpdateSubmtitStatus(registList, isSendAllData, unSubmitSeqList);
        if (!isSendAllData) {
            investorsHoldInfoRegist001Service.doUpdateSubmtitStatus(registList, isSendAllData, unSubmitSeqList);
        }
        //文件推送至xbrl文件
        super.pushDataToFile(info,super.getSendFile(xbrl,isFirstFlag));
        //推送xbrl文件后修改数据状态
        super.afterSendStatus(this.buildStatus(registList,xbrl.getReportNo(),isSendAllData),isSendAllData,xbrl);
        return xbrl;
    }

    @Override
    public List<ChbSubmitStatus> buildStatus(List<InvestorsHoldInfoRegist001Dto> registList, String reportNo,boolean isSendAllData) {
        List<ChbSubmitStatus> chbSubmitStatusList = null;
        if(!isSendAllData){
            chbSubmitStatusList = new ArrayList<>();
            for (InvestorsHoldInfoRegist001Dto seq : registList) {
                // 中债报送状态
                ChbSubmitStatus submitStatus = new ChbSubmitStatus();
                // 报送序列号
                submitStatus.setSeqno(seq.getSumitSeq());
                // 报送编码
                submitStatus.setReportNo(reportNo);
                // 报送流水号
                submitStatus.setSubmitNum(seq.getSumitSeq());
                // 业务类型
                submitStatus.setSubmitType(DictCodeListExt2.报表名称_投资者持有信息登记.getValue());
                //识别标识  通过流水号获取
                submitStatus.setUniqueIdenti(seq.getIndentifyMark());
                submitStatus.setEquityConfDate(seq.getHoldDate());
                submitStatus.setSubmitFeedbackStatus(DictCodeListExt2.报送反馈状态_发送中.getValue());
                // 有效
                submitStatus.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                chbSubmitStatusList.add(submitStatus);
            }
        }
        return chbSubmitStatusList;
    }

    /**
     *
     * 主键分解重组
     * @param validKey 主键
     * @see
     * @since 1.0
     */
    private String generateKeys(InvestorsHoldInfoRegist001Dto dto){
        if(dto!=null){
            return "产品登记编码 "+dto.getPrdRegistCode()+GlobalConstant.COMMA
                    +"识别标识 "+dto.getIndentifyMark()+GlobalConstant.COMMA
                    +"持有日期 "+DateUtil.format(dto.getHoldDate(),"yyyy-MM-dd")+GlobalConstant.COMMA
                    +"币种  "+dto.getCcy()+GlobalConstant.COMMA
                    +"登记银行代码  "+dto.getRegistBankCode();
        }
        return null;
    }
}
