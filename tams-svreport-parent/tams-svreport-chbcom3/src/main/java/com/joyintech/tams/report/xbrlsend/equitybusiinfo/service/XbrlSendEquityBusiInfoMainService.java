/*
 * 文件名：EquityBusiInfoAutoSendCBMainService.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：刘二通
 * 修改时间：2017年11月14日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */

package com.joyintech.tams.report.xbrlsend.equitybusiinfo.service;

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
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dao.EquityBusiInfoRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.dto.EquityBusiInfoRegist001Dto;
import com.joyintech.tams.report.submitmanagement.equitybusiinforegist.service.EquityBusiInfoRegist001Service;
import com.joyintech.tams.report.xbrlsend.base.service.XbrlSendBaseService;
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
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.joyintech.tams.report.xbrlsend.base.xbrlStringUtils.getDataNotNull;

/**
 * 业务明细数据自动检查报送 业务service
 *
 * @author 刘二通
 * @version 1.0 2017年11月14日
 * @see XbrlSendEquityBusiInfoMainService
 * @since 1.0
 */
@Service
public class XbrlSendEquityBusiInfoMainService extends XbrlSendBaseService<EquityBusiInfoRegist001Dto> {


	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * EquityBusiInfo001Service
     */
    @Autowired
    private EquityBusiInfoRegist001Service equityBusiInfoRegist001Service;
    /**
     * 投资者明细信息登记mapper
     */
    @Autowired
    private EquityBusiInfoRegist001Mapper equityBusiInfoRegist001Mapper;


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

        setBatchBeanName(BatchBeanName.投资者明细报送);
		batchStartTime = DateUtil.now();

		ChbInfoReportXbrl xbrlData = super.getDatFile(DictCodeListExt2.报表名称_投资者明细信息登记.getValue(),batchExecuteInfo.getExecuteDate(), null);
		if(null==xbrlData){
			return;
		}
        //无需进行befcheck
		logInfo("InvestorsHoldInfo处理开始");
		ChbInfoReportXbrl xbrl  = this.getDataBuildFile(null, true,batchExecuteInfo,xbrlData);
		logInfo("InvestorsHoldInfo处理结束");

		logger.info("报送Xbrl文件处理开始：" + DateUtil.getCurrentDateTime());
        super.sendFile(xbrl);
		logger.info("报送Xbrl文件处理结束：" + DateUtil.getCurrentDateTime());


		logInfoAutoSendResult(batchStartTime,  keyCount,
				sendKeyListCount, validRstListCount);
		logInfoBatchEnd();
    }

	@Override
	public List<EquityBusiInfoRegist001Dto> beforeCheck(SysBatchExecInfo batchExecuteInfo) {
		//投资者明细的数据检查在 [投资者明细信息数据检查批处理] 批处理中 为保证 直连与xbrl的统一.不进行处理
		return null;
	}

	@Override
	public ChbInfoReportXbrl getDataBuildFile(List<EquityBusiInfoRegist001Dto> dtoList, boolean isSendAllData, SysBatchExecInfo batchExecuteInfo,ChbInfoReportXbrl xbrl) throws IOException {
		String paraCodes = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.SYS_PARA_IS_SEND_EQUDET_WHEN_DEVIATION);
		Map<String, Object> map=new HashMap<String, Object>();

		map.put("holdDateStart", DateUtil.format(batchExecuteInfo.getExecuteDate()));
		map.put("holdDateEnd", DateUtil.format(batchExecuteInfo.getExecuteDate()));
		map.put("sendStatus", "01");
		Date startDate;
		List<Date> days;

		startDate=batchExecuteInfo.getExecuteDate();
		days=new ArrayList<Date>();
		days.add(startDate);

		//删除之前先将数据入His表 留痕处理
		map.put("sendStatus", null);
		map.put("sendStatusList", null);
		map.put("holdDateStart", null);
		map.put("holdDateEnd", null);
		int count=equityBusiInfoRegist001Mapper.selectCountSizeWithSameKey(map);
		if(count>0){
			equityBusiInfoRegist001Mapper.insertEquDetRegistHis(map);
			equityBusiInfoRegist001Mapper.deleteEquDetSubmitRegist(map);
		}

		//按日期循环报送
		//调用投资者份额检查批处理   (配置到任务组中)
		//查询出对应日期所有的登记银行代码(按登记银行代码分次报  光大个性化不考虑)
		List<String> bankCodes=equityBusiInfoRegist001Mapper.selectAllBanksByDate(startDate);
		if(StringUtil.isEmptyList(bankCodes)){
			logInfoNoHandleData(DateUtil.now());
		}
		for(String bank:bankCodes){
			//投资者份额检查不通过的是否报送 01 是  02 否
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("holdDate", startDate);
			//xbrl保证与查询的数据相同
			xbrl.setDataDate(startDate);
			int dataCountPerTime = Integer.valueOf(
					CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_SEND_CB_DATA_COUNT_PER_TIME));
			int dataCountPerDay = Integer.valueOf(
					CacheUtil.getSysParam(ReportGlobalConstant.SYS_PARA_SENDCB_MAX_COUNT));

			logInfo("开始按批次处理明细信息数据，每批次处理数据条数：" + dataCountPerTime);
			paramMap.put("dataCountPerTime", dataCountPerTime);
			paramMap.put("bankcode", bank);
			paramMap.put("paraCodes", paraCodes);
			paramMap.put("dataCountPerDay", dataCountPerDay);

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
		}

		return xbrl;
	}

	@Override
	public ChbInfoReportXbrl buildFileAndUpdateFlag(List<EquityBusiInfoRegist001Dto> registList, boolean isSendAllData, boolean isFirstFlag, ChbInfoReportXbrl xbrl) throws IOException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		// 元素名称
		String title = "ORG_CD"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CONTRACT_NUM"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"EC_TXN_SERIALNO"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"FINA_ACCT_NUM"
				+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_ID"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_ID_SEND"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUST_NAME" + ReportGlobalConstant.XBRL_VERTICAL_LINE+"TRANS_SERIAL_NO"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CUR_DEPOSIT_ACCT_NUM"
				+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"ACCOUNT_BRANCH"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"ACCOUNT_BRANCH_NAME" + ReportGlobalConstant.XBRL_VERTICAL_LINE+"ACC_REGION"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"AGENT_SALE_IND"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"AGENT_ORG_CD"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"AGENT_ORG_NAME"
				+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"AGENT_ORG_CBRC"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"PROD_REG_ID"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"BUSINESS_TYPE_CD"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"BUSINESS_ORG_CBRC"
				+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"TXN_DT"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"TXN_TIME"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"CURRENCY_CD"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"TXN_AMT"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"TXN_AMT_CNY"
				+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"PROD_UNIT_NAV"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"TXN_SHARE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"TXN_FEE"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"TXN_CHANNEL_CD"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"TXN_TELLER_ID"
				+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"REMARK"+ ReportGlobalConstant.XBRL_VERTICAL_LINE+"RECORD_ID";
		// 元素字符串结果集
		StringBuffer info = new StringBuffer("");
		if(isFirstFlag){
			info.append(title);
			info.append('\n');
		}

		List<String> registSeq = new ArrayList<String>();
		Map<String, String> registSeqKeyMap = new HashMap<String, String>();
		List<EquityBusiInfoRegist001Dto> unSubmitSeqList = new ArrayList<EquityBusiInfoRegist001Dto>();
		if (CollectionUtils.isNotEmpty(registList)) {

			xbrl.setCheckTotalCount((null!=xbrl.getCheckTotalCount()?xbrl.getCheckTotalCount(): BigDecimal.ZERO)
					.add(new BigDecimal(registList.size())));

			List<String> sequenceList = CommonUtil.getBatchSequenceIndexData(DataIndexSequenceType.中债登记管理_明细, registList.size());
			int index = 0;

			for (EquityBusiInfoRegist001Dto dto : registList) {
				// 针对取消手工处理再报送
				String registSeqNo = "";
				String currRegistSeq = "";
				if(DictCodeListExt2.报送反馈状态_发送失败.getValue().equals(dto.getSubmitFeedbackStatus())
						|| DictCodeListExt2.报送反馈状态_中债退回.getValue().equals(dto.getSubmitFeedbackStatus())){
//                    registSeqNo = CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理);
					registSeqNo = CommonUtil.getSequenceStr(sequenceList, index);
					index++;
					//将报送流水为空的放入list
					currRegistSeq = registSeqNo;
					dto.setSumitSeq(currRegistSeq);
					unSubmitSeqList.add(dto);
				}
				else if (StringUtil.isEmpty(dto.getSumitSeq())) {
//                    registSeqNo = CommonUtil.getSequenceIndexData(DataIndexSequenceType.中债登记管理);
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
				registSeqKeyMap.put(currRegistSeq, dto.getBusiSeqno() + "," + dto.getRegCode() + ","
						+ dto.getRegistBankCode() + "," +dto.getIndentifyMark());
				String  prdvalue=dto.getProdValue()==null?"":dto.getProdValue().setScale(5, RoundingMode.HALF_UP).toString();
				String feeAmt = null!=dto.getFeeAmt()?dto.getFeeAmt().setScale(2,RoundingMode.HALF_UP)+"":"";
				String value = dto.getRegistBankCode()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getContractNo()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getBusiSeqno()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+dto.getFinAcc()
						+ ReportGlobalConstant.XBRL_VERTICAL_LINE + dto.getCustContractNo()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getIndentifyMark()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getCustName()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getTradeSeq()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getFundAcc()
						+ ReportGlobalConstant.XBRL_VERTICAL_LINE + dto.getFundAccCode()+ ReportGlobalConstant.XBRL_VERTICAL_LINE + dto.getFundAccName() + ReportGlobalConstant.XBRL_VERTICAL_LINE + dto.getFundAccPlace()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ getDataNotNull(dto.getIsConsign())+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ (dto.getAgencyCode()==null?"":dto.getAgencyCode())+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ (dto.getAgencyName()==null?"":dto.getAgencyName())
						+ ReportGlobalConstant.XBRL_VERTICAL_LINE + (dto.getAgencyFsa()==null?"":dto.getAgencyFsa())+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getRegCode()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getBusiType()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ getDataNotNull(dto.getBusiFsa())
						+ ReportGlobalConstant.XBRL_VERTICAL_LINE + sdf.format(dto.getEquityConfDate())+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getShareConfDatetime()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getCcy()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getAmount().setScale(2,RoundingMode.HALF_UP)
						+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getAmountCny().setScale(2,RoundingMode.HALF_UP) + ReportGlobalConstant.XBRL_VERTICAL_LINE + prdvalue+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getShares().setScale(5,RoundingMode.HALF_UP)+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ feeAmt
						+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ dto.getChannel()+ ReportGlobalConstant.XBRL_VERTICAL_LINE+ getDataNotNull(dto.getTranTellName()) + ReportGlobalConstant.XBRL_VERTICAL_LINE + (dto.getRemark()==null?"":dto.getRemark()) + ReportGlobalConstant.XBRL_VERTICAL_LINE + registSeqNo;
				info.append(value);
				info.append('\n');
			}
		}


		// 更新报送状态表
		// modify by etliu 20181011 start
		if (!isSendAllData) {
			equityBusiInfoRegist001Service.doUpdateSubmtitStatus(registList, isSendAllData, unSubmitSeqList);
		}

		//文件推送至xbrl文件
		super.pushDataToFile(info,super.getSendFile(xbrl,isFirstFlag));

		//推送xbrl文件后修改数据状态
		super.afterSendStatus(this.buildStatus(registList,xbrl.getReportNo(),isSendAllData),isSendAllData,  xbrl);
		return xbrl;
	}

	@Override
	public List<ChbSubmitStatus> buildStatus(List<EquityBusiInfoRegist001Dto> registList,String reportNo,boolean isSendAllData) {
		List<ChbSubmitStatus> chbSubmitStatusList = null;
		if(!isSendAllData){
			chbSubmitStatusList = new ArrayList<>();
			for (EquityBusiInfoRegist001Dto seq : registList) {
				// 中债报送状态
				ChbSubmitStatus submitStatus = new ChbSubmitStatus();
				// 报送序列号
				submitStatus.setSeqno(seq.getSumitSeq());
				// 报送编码
				submitStatus.setReportNo(reportNo);
				// 报送流水号
				submitStatus.setSubmitNum(seq.getSumitSeq());
				// 业务类型
				submitStatus.setSubmitType(DictCodeListExt2.报表名称_投资者明细信息登记.getValue());
				//识别标识  通过流水号获取
				submitStatus.setUniqueIdenti(seq.getBusiSeqno() + "," + seq.getRegCode() + ","
						+ seq.getRegistBankCode() + "," +seq.getIndentifyMark());
				submitStatus.setEquityConfDate(seq.getEquityConfDate());
				submitStatus.setSubmitFeedbackStatus(DictCodeListExt2.报送反馈状态_发送中.getValue());
				// 有效
				submitStatus.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
				chbSubmitStatusList.add(submitStatus);
			}
		}
		return chbSubmitStatusList;
	}



	/**
     * 单批次处理明细信息数据
     *
     * @see
     * @since 1.0
     */
    private boolean handleDataPerTime(int handleIndex, Map<String, Object> paramMap, boolean isSendAllData,ChbInfoReportXbrl file) throws IOException {
        logInfo("处理开始，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        boolean hasData = true;
        Date batchStartTime = DateUtil.now();
        // 清空数据报送临时表
        logInfo("开始清空数据报送临时表");
        equityBusiInfoRegist001Mapper.trunequdetSubTempTable();
        logInfo("清空数据报送临时表结束");

        // 将待处理数据的报送流水号插入临时表
        logInfo("开始将待处理数据的登记序号插入临时表");
        equityBusiInfoRegist001Mapper.insertUnregistCheckOkDataToTempTable(paramMap);
        logInfo("将待处理数据的登记序号插入临时表结束");

        // 查询待报送数据
        logInfo("开始查询待报送数据");
        List<EquityBusiInfoRegist001Dto> unregistDataList = equityBusiInfoRegist001Mapper.selectAllUnregistCheckOkData();
        logInfo("查询待报送数据结束，数据条数：" + unregistDataList.size());
        if (StringUtil.isEmptyList(unregistDataList)) {
            logInfo("已无待报送数据，处理结束，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			logInfoNoHandleData(DateUtil.now());
            return false;
        }
        //查询出所有未报送数据条数   
        Date cdate=(Date)paramMap.get("holdDate");
        int allUnregistDataCount=equityBusiInfoRegist001Mapper.selectAllDataUnSendCount(cdate,paramMap.get("bankcode").toString());
        logInfoAutoSendResult(batchStartTime, allUnregistDataCount,
            allUnregistDataCount-unregistDataList.size(), unregistDataList.size());
        // 将待报送数据批量更新报送状态
        logInfo("开始将待报送数据批量更新报送状态");
        equityBusiInfoRegist001Mapper.updateSubmitStatus();
        logInfo("将待报送数据批量更新报送状态结束");
        equityBusiInfoRegist001Service.dealDataList(unregistDataList, false);

        this.buildFileAndUpdateFlag(unregistDataList,true,handleIndex==1?true:false
				,file);
        unregistDataList.clear();
        logInfo("处理结束，批次：" + handleIndex + "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return hasData;
    }

   
}