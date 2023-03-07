package com.joyintech.tams.report.gd.report.chbproductsassetcompare.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class ChbProductsAssetCompare001Dto extends BaseEntity {
    /**
     * 主键uuid
     */
    private String registSeqno;

    /**
     * 数据批次
     */
    private String statusBatch;

    /**
     * 理财产品编号
     */
    private String account;

    /**
     * 投资标的编号
     */
    private String assetId;

    /**
     * 折人民币持仓金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal holdQtyAmount;

    /**
     * 持仓份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal holdShares;

    /**
     * 银行标识号
     */
    private String bankMark;

    /**
     * 数据时点
     */
    private Date dateStatus;

    /**
     * 组别
     */
    private String groupId;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 持仓份额总和
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalShares;

    /**
     * 实际募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalAmt;

    /**
     * 差额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal difference;

    /**
     * 差异率
     */
    private String differenceRate;

    /**
     *现金所占费用
     */
    private BigDecimal cashRate;

    private String dateStatusStr;

    public void setDateStatusStr(String dateStatusStr) {
        this.dateStatusStr = dateStatusStr;
    }

    public BigDecimal getCashRate() {
        return cashRate;
    }

    public void setCashRate(BigDecimal cashRate) {
        this.cashRate = cashRate;
    }

    public BigDecimal getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(BigDecimal totalShares) {
        this.totalShares = totalShares;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }


    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public String getDifferenceRate() {
        return differenceRate;
    }


    public void setDifferenceRate(String differenceRate) {
        this.differenceRate = differenceRate;
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
     * 取得 投资标的编号
     * @return 投资标的编号
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 投资标的编号
     * @param assetId 投资标的编号
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 折人民币持仓金额
     * @return 折人民币持仓金额
     */
    public BigDecimal getHoldQtyAmount() {
        return holdQtyAmount;
    }

    /**
     * 设置 折人民币持仓金额
     * @param holdQtyAmount 折人民币持仓金额
     */
    public void setHoldQtyAmount(BigDecimal holdQtyAmount) {
        this.holdQtyAmount = holdQtyAmount;
    }

    /**
     * 取得 持仓份额
     * @return 持仓份额
     */
    public BigDecimal getHoldShares() {
        return holdShares;
    }

    /**
     * 设置 持仓份额
     * @param holdShares 持仓份额
     */
    public void setHoldShares(BigDecimal holdShares) {
        this.holdShares = holdShares;
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

    /**
     * 按照格式构建一条记录
     * @return
     */
    public String buildReportStr(){
        String separator = "|+|";

        StringBuffer sb = new StringBuffer();
        sb.append(nullCheck(getAccount())).append(separator);
        sb.append(nullCheck(getAssetId())).append(separator);
        sb.append(nullCheck(getHoldQtyAmount())).append(separator);
        sb.append(nullCheck(getHoldShares())).append(separator);
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