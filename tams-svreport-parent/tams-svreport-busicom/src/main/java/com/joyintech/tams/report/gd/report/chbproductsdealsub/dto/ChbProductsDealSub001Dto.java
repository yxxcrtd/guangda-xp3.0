package com.joyintech.tams.report.gd.report.chbproductsdealsub.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 理财产品交易明细登记表Dto
 * @author yangxianjiu
 * @date 2022/03/30
 */
@TableName("CHB_PRODUCTS_DEAL_SUB")
public class ChbProductsDealSub001Dto extends BaseEntity {

    /**
     * 主键uuid
     */
    private String registSeqno;

    /**
     * 数据批次
     */
    private String statusBatch;

    /**
     * 客户编号
     */
    private String custContractNo;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 理财产品编号
     */
    private String account;

    /**
     * 客户账号
     */
    private String fundAcc;

    /**
     * 理财账号
     */
    private String finAcc;

    /**
     * 交易日期
     */
    private Date equityConfDate;

    private String equityConfDateStr;

    /**
     * 币种代码
     */
    private String ccy;

    /**
     * 交易份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal shares;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 折人民币交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amountCny;

    /**
     * 交易费用金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmt;

    /**
     * 理财产品变更申请类型代码信息
     */
    private String busiType;

    private String busiTypeStr;

    /**
     * 交易渠道类型代码信息
     */
    private String channel;

    private String channelStr;

    /**
     * 交易流水号
     */
    private String busiSeqno;

    /**
     * 机构编号
     */
    private String orgId;

    /**
     * 银行标识号
     */
    private String bankMark;

    /**
     * 数据时点
     */
    private Date dateStatus;

    private String dateStatusStr;

    /**
     * 组别
     */
    private String groupId;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 数据日期
     */
    private String dataDate;

    public void setEquityConfDateStr(String equityConfDateStr) {
        this.equityConfDateStr = equityConfDateStr;
    }

    public void setBusiTypeStr(String busiTypeStr) {
        this.busiTypeStr = busiTypeStr;
    }

    public void setChannelStr(String channelStr) {
        this.channelStr = channelStr;
    }

    public void setDateStatusStr(String dateStatusStr) {
        this.dateStatusStr = dateStatusStr;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
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
     * 取得 数据批次
     * @return 数据批次
     */
    public String getStatusBatch() {
        return statusBatch;
    }

    /**
     * 设置 数据批次
     * @param statusBatch 数据批次
     */
    public void setStatusBatch(String statusBatch) {
        this.statusBatch = statusBatch == null ? null : statusBatch.trim();
    }

    /**
     * 取得 客户编号
     * @return 客户编号
     */
    public String getCustContractNo() {
        return custContractNo;
    }

    /**
     * 设置 客户编号
     * @param custContractNo 客户编号
     */
    public void setCustContractNo(String custContractNo) {
        this.custContractNo = custContractNo == null ? null : custContractNo.trim();
    }

    /**
     * 取得 客户名称
     * @return 客户名称
     */
    public String getCustName() {
        return custName;
    }

    /**
     * 设置 客户名称
     * @param custName 客户名称
     */
    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    /**
     * 取得 理财产品编号
     * @return 理财产品编号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 理财产品编号
     * @param account 理财产品编号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 取得 客户账号
     * @return 客户账号
     */
    public String getFundAcc() {
        return fundAcc;
    }

    /**
     * 设置 客户账号
     * @param fundAcc 客户账号
     */
    public void setFundAcc(String fundAcc) {
        this.fundAcc = fundAcc == null ? null : fundAcc.trim();
    }

    /**
     * 取得 理财账号
     * @return 理财账号
     */
    public String getFinAcc() {
        return finAcc;
    }

    /**
     * 设置 理财账号
     * @param finAcc 理财账号
     */
    public void setFinAcc(String finAcc) {
        this.finAcc = finAcc == null ? null : finAcc.trim();
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public Date getEquityConfDate() {
        return equityConfDate;
    }

    public String getEquityConfDateStr() {
        return DateUtil.format(this.getEquityConfDate(),DateUtil.DEFAULT_OTHER_FORMAT);
    }

    /**
     * 设置 交易日期
     * @param equityConfDate 交易日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEquityConfDate(Date equityConfDate) {
        this.equityConfDate = equityConfDate;
    }

    /**
     * 取得 币种代码
     * @return 币种代码
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种代码
     * @param ccy 币种代码
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 交易份额
     * @return 交易份额
     */
    public BigDecimal getShares() {
        return shares;
    }

    /**
     * 设置 交易份额
     * @param shares 交易份额
     */
    public void setShares(BigDecimal shares) {
        this.shares = shares;
    }

    /**
     * 取得 交易金额
     * @return 交易金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 交易金额
     * @param amount 交易金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 折人民币交易金额
     * @return 折人民币交易金额
     */
    public BigDecimal getAmountCny() {
        return amountCny;
    }

    /**
     * 设置 折人民币交易金额
     * @param amountCny 折人民币交易金额
     */
    public void setAmountCny(BigDecimal amountCny) {
        this.amountCny = amountCny;
    }

    /**
     * 取得 交易费用金额
     * @return 交易费用金额
     */
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置 交易费用金额
     * @param feeAmt 交易费用金额
     */
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * 取得 理财产品变更申请类型代码信息
     * @return 理财产品变更申请类型代码信息
     */
    public String getBusiType() {
        return busiType;
    }

    public String getBusiTypeStr() {
        return busiTypeStr;
    }

    /**
     * 设置 理财产品变更申请类型代码信息
     * @param busiType 理财产品变更申请类型代码信息
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 交易渠道类型代码信息
     * @return 交易渠道类型代码信息
     */
    public String getChannel() {
        return channel;
    }

    public String getChannelStr() {
        return channelStr;
    }

    /**
     * 设置 交易渠道类型代码信息
     * @param channel 交易渠道类型代码信息
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * 取得 交易流水号
     * @return 交易流水号
     */
    public String getBusiSeqno() {
        return busiSeqno;
    }

    /**
     * 设置 交易流水号
     * @param busiSeqno 交易流水号
     */
    public void setBusiSeqno(String busiSeqno) {
        this.busiSeqno = busiSeqno == null ? null : busiSeqno.trim();
    }

    /**
     * 取得 机构编号
     * @return 机构编号
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置 机构编号
     * @param orgId 机构编号
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 取得 银行标识号
     * @return 银行标识号
     */
    public String getBankMark() {
        return bankMark;
    }

    /**
     * 设置 银行标识号
     * @param bankMark 银行标识号
     */
    public void setBankMark(String bankMark) {
        this.bankMark = bankMark == null ? null : bankMark.trim();
    }

    /**
     * 取得 数据时点
     * @return 数据时点
     */
    public Date getDateStatus() {
        return dateStatus;
    }

    public String getDateStatusStr() {
        return DateUtil.format(this.getDateStatus(),DateUtil.DEFAULT_OTHER_FORMAT);
    }

    /**
     * 设置 数据时点
     * @param dateStatus 数据时点
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDateStatus(Date dateStatus) {
        this.dateStatus = dateStatus;
    }

    /**
     * 取得 组别
     * @return 组别
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置 组别
     * @param groupId 组别
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
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

    // ==================== 编码转名称 开始 ====================

    /**
     * 取得 交易渠道类型代码信息
     * @return 交易渠道类型代码信息
     */
    public String getChannelName() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资者明细渠道, this.getChannel());
    }

    // ==================== 编码转名称 结束 ====================

    /**
     * 按照格式构建一条记录
     * @return
     */
    public String buildReportStr(){
        String separator = "|+|";

        StringBuffer sb = new StringBuffer();
        sb.append(nullCheck(getCustContractNo())).append(separator);
        sb.append(nullCheck(getCustName())).append(separator);
        sb.append(nullCheck(getAccount())).append(separator);
        sb.append(nullCheck(getFundAcc())).append(separator);
        sb.append(nullCheck(getFinAcc())).append(separator);
        sb.append(nullCheck(getEquityConfDateStr())).append(separator);
        sb.append(nullCheck(getCcy())).append(separator);
        sb.append(nullCheck(getShares())).append(separator);
        sb.append(nullCheck(getAmount())).append(separator);
        sb.append(nullCheck(getAmountCny())).append(separator);
        sb.append(nullCheck(getFeeAmt())).append(separator);
        sb.append(nullCheck(getBusiType())).append(separator);
        sb.append(nullCheck(getChannelName())).append(separator); // 交易渠道类型代码信息(金审为中文含义)
        sb.append(nullCheck(getBusiSeqno())).append(separator);
        sb.append(nullCheck(getOrgId())).append(separator);
        sb.append(nullCheck(getBankMark())).append(separator);
        sb.append(nullCheck(getDateStatusStr())).append("|");

        return sb.toString();
    }

    /**
     * 空值检查
     * @param obj
     * @return
     */
    public String nullCheck(Object obj){
        if(obj == null){
            return "";
        }
        return obj.toString();
    }
}
