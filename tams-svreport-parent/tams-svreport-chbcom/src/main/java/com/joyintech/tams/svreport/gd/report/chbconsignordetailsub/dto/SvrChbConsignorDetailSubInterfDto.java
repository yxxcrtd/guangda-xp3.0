package com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangxianjiu
 * @date 2022/04/06
 */
public class SvrChbConsignorDetailSubInterfDto extends BaseEntity {
    /**
     * 主键uuid
     */
    private String registSeqno;

    /**
     * 最后持仓日期
     */
    private Date lastHoldDay;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品登记编码
     */
    private String prdRegistCode;

    /**
     * 管理人
     */
    private String manager;

    /**
     * 产品类型
     */
    private String prodType;

    /**
     * 产品投资性质
     */
    private String prodInvestType;

    /**
     * 募集方式
     */
    private String raiseType;

    /**
     * 期限（年）
     */
    private String termid;

    /**
     * 产品规模（元）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal prodScale;

    /**
     * 银行间债券市场持仓面额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal inbankBondmarkHoldDenomina;

    /**
     * 银行间债券市场持仓占比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal inbankBondmarkHoldRatio;

    /**
     * 投资人名称
     */
    private String holderName;

    /**
     * 投资人类型
     */
    private String holderType;

    /**
     * 投资金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investAmount;

    /**
     * 投资比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investRatio;

    /**
     * 二级投资人名称
     */
    private String twoHolderName;

    /**
     * 二级投资人类型
     */
    private String twoHolderType;

    /**
     * 二级投资人投资金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal twoInvestAmount;

    /**
     * 二级投资人投资比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal twoInvestRatio;

    /**
     * 产品份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodShare;

    /**
     * 投资人份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal holderShare;

    /**
     * 备注
     */
    private String remark;

    /**
     * 中债报送截止日
     */
    private Date chbSubmitDeadline;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 报送时间
     */
    private Date submitTime;

    /**
     * 报送流水号
     */
    private String sumitSeq;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 反馈状态
     */
    private String submitFeedbackStatus;

    /**
     * 报送反馈消息
     */
    private String submitFeedbackMsg;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 产品运作模式
     */
    private String operateMode;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qty;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getOperateMode() {
        return operateMode;
    }

    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 主键uuid
     * @return 主键uuid
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 主键uuid
     * @param registSeqno 主键uuid
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 取得 最后持仓日期
     * @return 最后持仓日期
     */
    public Date getLastHoldDay() {
        return lastHoldDay;
    }

    /**
     * 设置 最后持仓日期
     * @param lastHoldDay 最后持仓日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLastHoldDay(Date lastHoldDay) {
        this.lastHoldDay = lastHoldDay;
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 产品登记编码
     * @return 产品登记编码
     */
    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    /**
     * 设置 产品登记编码
     * @param prdRegistCode 产品登记编码
     */
    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode == null ? null : prdRegistCode.trim();
    }

    /**
     * 取得 管理人
     * @return 管理人
     */
    public String getManager() {
        return manager;
    }

    /**
     * 设置 管理人
     * @param manager 管理人
     */
    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    /**
     * 取得 产品类型
     * @return 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * 设置 产品类型
     * @param prodType 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType == null ? null : prodType.trim();
    }

    /**
     * 取得 产品投资性质
     * @return 产品投资性质
     */
    public String getProdInvestType() {
        return prodInvestType;
    }

    public String getProdInvestTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资性质,this.getProdInvestType());
    }

    /**
     * 设置 产品投资性质
     * @param prodInvestType 产品投资性质
     */
    public void setProdInvestType(String prodInvestType) {
        this.prodInvestType = prodInvestType == null ? null : prodInvestType.trim();
    }

    /**
     * 取得 募集方式
     * @return 募集方式
     */
    public String getRaiseType() {
        return raiseType;
    }

    public String getRaiseTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.募集方式_中债,this.getRaiseType());
    }

    /**
     * 设置 募集方式
     * @param raiseType 募集方式
     */
    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType == null ? null : raiseType.trim();
    }

    /**
     * 取得 期限（年）
     * @return 期限（年）
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 设置 期限（年）
     * @param termid 期限（年）
     */
    public void setTermid(String termid) {
        this.termid = termid == null ? null : termid.trim();
    }

    /**
     * 取得 产品规模（元）
     * @return 产品规模（元）
     */
    public BigDecimal getProdScale() {
        return prodScale;
    }

    /**
     * 设置 产品规模（元）
     * @param prodScale 产品规模（元）
     */
    public void setProdScale(BigDecimal prodScale) {
        this.prodScale = prodScale;
    }

    /**
     * 取得 银行间债券市场持仓面额
     * @return 银行间债券市场持仓面额
     */
    public BigDecimal getInbankBondmarkHoldDenomina() {
        return inbankBondmarkHoldDenomina;
    }

    /**
     * 设置 银行间债券市场持仓面额
     * @param inbankBondmarkHoldDenomina 银行间债券市场持仓面额
     */
    public void setInbankBondmarkHoldDenomina(BigDecimal inbankBondmarkHoldDenomina) {
        this.inbankBondmarkHoldDenomina = inbankBondmarkHoldDenomina;
    }

    /**
     * 取得 银行间债券市场持仓占比
     * @return 银行间债券市场持仓占比
     */
    public BigDecimal getInbankBondmarkHoldRatio() {
        return inbankBondmarkHoldRatio;
    }

    /**
     * 设置 银行间债券市场持仓占比
     * @param inbankBondmarkHoldRatio 银行间债券市场持仓占比
     */
    public void setInbankBondmarkHoldRatio(BigDecimal inbankBondmarkHoldRatio) {
        this.inbankBondmarkHoldRatio = inbankBondmarkHoldRatio;
    }

    /**
     * 取得 投资人名称
     * @return 投资人名称
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * 设置 投资人名称
     * @param holderName 投资人名称
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName == null ? null : holderName.trim();
    }

    /**
     * 取得 投资人类型
     * @return 投资人类型
     */
    public String getHolderType() {
        return holderType;
    }

    /**
     * 设置 投资人类型
     * @param holderType 投资人类型
     */
    public void setHolderType(String holderType) {
        this.holderType = holderType == null ? null : holderType.trim();
    }

    /**
     * 取得 投资金额（元）
     * @return 投资金额（元）
     */
    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    /**
     * 设置 投资金额（元）
     * @param investAmount 投资金额（元）
     */
    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    /**
     * 取得 投资比例
     * @return 投资比例
     */
    public BigDecimal getInvestRatio() {
        return investRatio;
    }

    /**
     * 设置 投资比例
     * @param investRatio 投资比例
     */
    public void setInvestRatio(BigDecimal investRatio) {
        this.investRatio = investRatio;
    }

    /**
     * 取得 二级投资人名称
     * @return 二级投资人名称
     */
    public String getTwoHolderName() {
        return twoHolderName;
    }

    /**
     * 设置 二级投资人名称
     * @param twoHolderName 二级投资人名称
     */
    public void setTwoHolderName(String twoHolderName) {
        this.twoHolderName = twoHolderName == null ? null : twoHolderName.trim();
    }

    /**
     * 取得 二级投资人类型
     * @return 二级投资人类型
     */
    public String getTwoHolderType() {
        return twoHolderType;
    }

    /**
     * 设置 二级投资人类型
     * @param twoHolderType 二级投资人类型
     */
    public void setTwoHolderType(String twoHolderType) {
        this.twoHolderType = twoHolderType == null ? null : twoHolderType.trim();
    }

    /**
     * 取得 二级投资人投资金额（元）
     * @return 二级投资人投资金额（元）
     */
    public BigDecimal getTwoInvestAmount() {
        return twoInvestAmount;
    }

    /**
     * 设置 二级投资人投资金额（元）
     * @param twoInvestAmount 二级投资人投资金额（元）
     */
    public void setTwoInvestAmount(BigDecimal twoInvestAmount) {
        this.twoInvestAmount = twoInvestAmount;
    }

    /**
     * 取得 二级投资人投资比例
     * @return 二级投资人投资比例
     */
    public BigDecimal getTwoInvestRatio() {
        return twoInvestRatio;
    }

    /**
     * 设置 二级投资人投资比例
     * @param twoInvestRatio 二级投资人投资比例
     */
    public void setTwoInvestRatio(BigDecimal twoInvestRatio) {
        this.twoInvestRatio = twoInvestRatio;
    }

    /**
     * 取得 产品份额
     * @return 产品份额
     */
    public BigDecimal getProdShare() {
        return prodShare;
    }

    /**
     * 设置 产品份额
     * @param prodShare 产品份额
     */
    public void setProdShare(BigDecimal prodShare) {
        this.prodShare = prodShare;
    }

    /**
     * 取得 投资人份额
     * @return 投资人份额
     */
    public BigDecimal getHolderShare() {
        return holderShare;
    }

    /**
     * 设置 投资人份额
     * @param holderShare 投资人份额
     */
    public void setHolderShare(BigDecimal holderShare) {
        this.holderShare = holderShare;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 中债报送截止日
     * @return 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 设置 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 取得 报送时间
     * @return 报送时间
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 报送时间
     * @param submitTime 报送时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 取得 报送流水号
     * @return 报送流水号
     */
    public String getSumitSeq() {
        return sumitSeq;
    }

    /**
     * 设置 报送流水号
     * @param sumitSeq 报送流水号
     */
    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq == null ? null : sumitSeq.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 反馈状态
     * @return 反馈状态
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 设置 反馈状态
     * @param submitFeedbackStatus 反馈状态
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
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
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }
}