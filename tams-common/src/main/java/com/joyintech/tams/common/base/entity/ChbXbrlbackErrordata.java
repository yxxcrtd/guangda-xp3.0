package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbXbrlbackErrordata extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 批次号
     */
    private String repMesNo;

    /**
     * 报送类型
     */
    private String submitType;

    /**
     * 报送反馈状态
     */
    private String submitFeedbackStatus;

    /**
     * 报送编码
     */
    private String reportNo;

    /**
     * 报送反馈消息
     */
    private String submitFeedbackMsg;

    /**
     * 唯一标识1 - 身份(证件号)，持有(识别标识)，明细(交易流水号)
     */
    private String uniqueIdenti1;

    /**
     * 唯一标识2 - 身份(证件类型)，持有/明细(产品登记编码)
     */
    private String uniqueIdenti2;

    /**
     * 唯一标识3 - 持有(持有日期)，
     */
    private String uniqueIdenti3;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 批次号
     * @return 批次号
     */
    public String getRepMesNo() {
        return repMesNo;
    }

    /**
     * 设置 批次号
     * @param repMesNo 批次号
     */
    public void setRepMesNo(String repMesNo) {
        this.repMesNo = repMesNo == null ? null : repMesNo.trim();
    }

    /**
     * 取得 报送类型
     * @return 报送类型
     */
    public String getSubmitType() {
        return submitType;
    }

    /**
     * 设置 报送类型
     * @param submitType 报送类型
     */
    public void setSubmitType(String submitType) {
        this.submitType = submitType == null ? null : submitType.trim();
    }

    /**
     * 取得 报送反馈状态
     * @return 报送反馈状态
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 设置 报送反馈状态
     * @param submitFeedbackStatus 报送反馈状态
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }

    /**
     * 取得 报送编码
     * @return 报送编码
     */
    public String getReportNo() {
        return reportNo;
    }

    /**
     * 设置 报送编码
     * @param reportNo 报送编码
     */
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
    }

    /**
     * 取得 报送反馈消息
     * @return 报送反馈消息
     */
    public String getSubmitFeedbackMsg() {
        return submitFeedbackMsg;
    }

    /**
     * 设置 报送反馈消息
     * @param submitFeedbackMsg 报送反馈消息
     */
    public void setSubmitFeedbackMsg(String submitFeedbackMsg) {
        this.submitFeedbackMsg = submitFeedbackMsg == null ? null : submitFeedbackMsg.trim();
    }

    /**
     * 取得 唯一标识1 - 身份(证件号)，持有(识别标识)，明细(交易流水号)
     * @return 唯一标识1 - 身份(证件号)，持有(识别标识)，明细(交易流水号)
     */
    public String getUniqueIdenti1() {
        return uniqueIdenti1;
    }

    /**
     * 设置 唯一标识1 - 身份(证件号)，持有(识别标识)，明细(交易流水号)
     * @param uniqueIdenti1 唯一标识1 - 身份(证件号)，持有(识别标识)，明细(交易流水号)
     */
    public void setUniqueIdenti1(String uniqueIdenti1) {
        this.uniqueIdenti1 = uniqueIdenti1 == null ? null : uniqueIdenti1.trim();
    }

    /**
     * 取得 唯一标识2 - 身份(证件类型)，持有/明细(产品登记编码)
     * @return 唯一标识2 - 身份(证件类型)，持有/明细(产品登记编码)
     */
    public String getUniqueIdenti2() {
        return uniqueIdenti2;
    }

    /**
     * 设置 唯一标识2 - 身份(证件类型)，持有/明细(产品登记编码)
     * @param uniqueIdenti2 唯一标识2 - 身份(证件类型)，持有/明细(产品登记编码)
     */
    public void setUniqueIdenti2(String uniqueIdenti2) {
        this.uniqueIdenti2 = uniqueIdenti2 == null ? null : uniqueIdenti2.trim();
    }

    /**
     * 取得 唯一标识3 - 持有(持有日期)，
     * @return 唯一标识3 - 持有(持有日期)，
     */
    public String getUniqueIdenti3() {
        return uniqueIdenti3;
    }

    /**
     * 设置 唯一标识3 - 持有(持有日期)，
     * @param uniqueIdenti3 唯一标识3 - 持有(持有日期)，
     */
    public void setUniqueIdenti3(String uniqueIdenti3) {
        this.uniqueIdenti3 = uniqueIdenti3 == null ? null : uniqueIdenti3.trim();
    }
}