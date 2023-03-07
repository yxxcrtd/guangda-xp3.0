package com.joyintech.tams.svreport.interf.chb.prodinfo.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class PbcSaleSvrreport extends BaseEntity {

    /**
     * 下发日期
     */
    private Date senddate;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 法人行
     */
    private String issuemember;

    /**
     * 客户类型
     */
    private String cusType;

    /**
     * 是否其他机构代销
     */
    private String isConsignment;

    /**
     * 行政区域代码
     */
    private String areaCode;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 认购金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal prinamt;

    /**
     * 认购金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamtRmb;

    /**
     * 认购确认份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamtConfirm;

    /**
     * 认购客户数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamtNumber;

    /**
     * 申购金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sgAmt;

    /**
     * 申购金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sgAmtRmb;

    /**
     * 申购确认份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sgConfirmAmt;

    /**
     * 赎回兑付金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shAmt;

    /**
     * 赎回兑付金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shAmtRmb;

    /**
     * 赎回兑付本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shPrincipal;

    /**
     * 赎回兑付本金转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shPrincipalRmb;

    /**
     * 赎回兑付份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shQty;

    /**
     * 到期兑付金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmt;

    /**
     * 到期兑付金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmtRmb;

    /**
     * 到期兑付本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmtPrincipal;

    /**
     * 到期兑付本金转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmtPrincipalRmb;

    /**
     * 到期兑付份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expireAmtQty;

    /**
     * 红利再投资金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rebonusAmt;

    /**
     * 红利再投资金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rebonusAmtRmb;

    /**
     * 红利再投资份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rebonusAmtQty;

    /**
     * 现金分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bonusAmt;

    /**
     * 现金分红金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bonusAmtRmb;

    /**
     * 份额强增金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyAddAmt;

    /**
     * 份额强增金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyAddAmtRmb;

    /**
     * 强增份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyAdd;

    /**
     * 强减本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyMinusPrincipal;

    /**
     * 强减本金转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyMinusPrincipalRmb;

    /**
     * 强减份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qtyMinus;

    /**
     * 保有量本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saveAmt;


    /**
     * 保有量金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saveAmtRmb;

    /**
     * 保有量份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saveQty;

    /**
     * 保有量客户数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saveCusNum;

    /**
     * 汇率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal exchangeRate;

    /**
     * 非交易过户出份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal outTradeQtr;
    /**
     * 非交易过户出金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal outTradeAmt;
    /**
     * 非交易过户出金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal outTradeAmtRmb;
    /**
     * 非交易过户出本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal outTradePrincipal;
    /**
     * 非交易过户出本金转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal outTradePrincipalRmb;
    /**
     * 非交易过户入金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal inTradeAmt;
    /**
     * 非交易过户入金额转人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal inTradeAmtRmb;
    /**
     * 非交易过户入份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal inTradeQtr;


    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getIssuemember() {
        return issuemember;
    }

    public void setIssuemember(String issuemember) {
        this.issuemember = issuemember;
    }

    public String getCusType() {
        return cusType;
    }

    public void setCusType(String cusType) {
        this.cusType = cusType;
    }

    public String getIsConsignment() {
        return isConsignment;
    }

    public void setIsConsignment(String isConsignment) {
        this.isConsignment = isConsignment;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getPrinamt() {
        return prinamt;
    }

    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }

    public BigDecimal getPrinamtRmb() {
        return prinamtRmb;
    }

    public void setPrinamtRmb(BigDecimal prinamtRmb) {
        this.prinamtRmb = prinamtRmb;
    }

    public BigDecimal getPrinamtConfirm() {
        return prinamtConfirm;
    }

    public void setPrinamtConfirm(BigDecimal prinamtConfirm) {
        this.prinamtConfirm = prinamtConfirm;
    }

    public BigDecimal getPrinamtNumber() {
        return prinamtNumber;
    }

    public void setPrinamtNumber(BigDecimal prinamtNumber) {
        this.prinamtNumber = prinamtNumber;
    }

    public BigDecimal getSgAmt() {
        return sgAmt;
    }

    public void setSgAmt(BigDecimal sgAmt) {
        this.sgAmt = sgAmt;
    }

    public BigDecimal getSgAmtRmb() {
        return sgAmtRmb;
    }

    public void setSgAmtRmb(BigDecimal sgAmtRmb) {
        this.sgAmtRmb = sgAmtRmb;
    }

    public BigDecimal getSgConfirmAmt() {
        return sgConfirmAmt;
    }

    public void setSgConfirmAmt(BigDecimal sgConfirmAmt) {
        this.sgConfirmAmt = sgConfirmAmt;
    }

    public BigDecimal getShAmt() {
        return shAmt;
    }

    public void setShAmt(BigDecimal shAmt) {
        this.shAmt = shAmt;
    }

    public BigDecimal getShAmtRmb() {
        return shAmtRmb;
    }

    public void setShAmtRmb(BigDecimal shAmtRmb) {
        this.shAmtRmb = shAmtRmb;
    }

    public BigDecimal getShPrincipal() {
        return shPrincipal;
    }

    public void setShPrincipal(BigDecimal shPrincipal) {
        this.shPrincipal = shPrincipal;
    }

    public BigDecimal getShPrincipalRmb() {
        return shPrincipalRmb;
    }

    public void setShPrincipalRmb(BigDecimal shPrincipalRmb) {
        this.shPrincipalRmb = shPrincipalRmb;
    }

    public BigDecimal getShQty() {
        return shQty;
    }

    public void setShQty(BigDecimal shQty) {
        this.shQty = shQty;
    }

    public BigDecimal getExpireAmt() {
        return expireAmt;
    }

    public void setExpireAmt(BigDecimal expireAmt) {
        this.expireAmt = expireAmt;
    }

    public BigDecimal getExpireAmtRmb() {
        return expireAmtRmb;
    }

    public void setExpireAmtRmb(BigDecimal expireAmtRmb) {
        this.expireAmtRmb = expireAmtRmb;
    }

    public BigDecimal getExpireAmtPrincipal() {
        return expireAmtPrincipal;
    }

    public void setExpireAmtPrincipal(BigDecimal expireAmtPrincipal) {
        this.expireAmtPrincipal = expireAmtPrincipal;
    }

    public BigDecimal getExpireAmtPrincipalRmb() {
        return expireAmtPrincipalRmb;
    }

    public void setExpireAmtPrincipalRmb(BigDecimal expireAmtPrincipalRmb) {
        this.expireAmtPrincipalRmb = expireAmtPrincipalRmb;
    }

    public BigDecimal getExpireAmtQty() {
        return expireAmtQty;
    }

    public void setExpireAmtQty(BigDecimal expireAmtQty) {
        this.expireAmtQty = expireAmtQty;
    }

    public BigDecimal getRebonusAmt() {
        return rebonusAmt;
    }

    public void setRebonusAmt(BigDecimal rebonusAmt) {
        this.rebonusAmt = rebonusAmt;
    }

    public BigDecimal getRebonusAmtRmb() {
        return rebonusAmtRmb;
    }

    public void setRebonusAmtRmb(BigDecimal rebonusAmtRmb) {
        this.rebonusAmtRmb = rebonusAmtRmb;
    }

    public BigDecimal getRebonusAmtQty() {
        return rebonusAmtQty;
    }

    public void setRebonusAmtQty(BigDecimal rebonusAmtQty) {
        this.rebonusAmtQty = rebonusAmtQty;
    }

    public BigDecimal getBonusAmt() {
        return bonusAmt;
    }

    public void setBonusAmt(BigDecimal bonusAmt) {
        this.bonusAmt = bonusAmt;
    }

    public BigDecimal getBonusAmtRmb() {
        return bonusAmtRmb;
    }

    public void setBonusAmtRmb(BigDecimal bonusAmtRmb) {
        this.bonusAmtRmb = bonusAmtRmb;
    }

    public BigDecimal getQtyAddAmt() {
        return qtyAddAmt;
    }

    public void setQtyAddAmt(BigDecimal qtyAddAmt) {
        this.qtyAddAmt = qtyAddAmt;
    }

    public BigDecimal getQtyAddAmtRmb() {
        return qtyAddAmtRmb;
    }

    public void setQtyAddAmtRmb(BigDecimal qtyAddAmtRmb) {
        this.qtyAddAmtRmb = qtyAddAmtRmb;
    }

    public BigDecimal getQtyAdd() {
        return qtyAdd;
    }

    public void setQtyAdd(BigDecimal qtyAdd) {
        this.qtyAdd = qtyAdd;
    }

    public BigDecimal getQtyMinusPrincipal() {
        return qtyMinusPrincipal;
    }

    public void setQtyMinusPrincipal(BigDecimal qtyMinusPrincipal) {
        this.qtyMinusPrincipal = qtyMinusPrincipal;
    }

    public BigDecimal getQtyMinusPrincipalRmb() {
        return qtyMinusPrincipalRmb;
    }

    public void setQtyMinusPrincipalRmb(BigDecimal qtyMinusPrincipalRmb) {
        this.qtyMinusPrincipalRmb = qtyMinusPrincipalRmb;
    }

    public BigDecimal getQtyMinus() {
        return qtyMinus;
    }

    public void setQtyMinus(BigDecimal qtyMinus) {
        this.qtyMinus = qtyMinus;
    }

    public BigDecimal getSaveAmt() {
        return saveAmt;
    }

    public void setSaveAmt(BigDecimal saveAmt) {
        this.saveAmt = saveAmt;
    }

    public BigDecimal getSaveAmtRmb() {
        return saveAmtRmb;
    }

    public void setSaveAmtRmb(BigDecimal saveAmtRmb) {
        this.saveAmtRmb = saveAmtRmb;
    }

    public BigDecimal getSaveQty() {
        return saveQty;
    }

    public void setSaveQty(BigDecimal saveQty) {
        this.saveQty = saveQty;
    }

    public BigDecimal getSaveCusNum() {
        return saveCusNum;
    }

    public void setSaveCusNum(BigDecimal saveCusNum) {
        this.saveCusNum = saveCusNum;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getOutTradeQtr() {
        return outTradeQtr;
    }

    public void setOutTradeQtr(BigDecimal outTradeQtr) {
        this.outTradeQtr = outTradeQtr;
    }

    public BigDecimal getOutTradeAmt() {
        return outTradeAmt;
    }

    public void setOutTradeAmt(BigDecimal outTradeAmt) {
        this.outTradeAmt = outTradeAmt;
    }

    public BigDecimal getOutTradeAmtRmb() {
        return outTradeAmtRmb;
    }

    public void setOutTradeAmtRmb(BigDecimal outTradeAmtRmb) {
        this.outTradeAmtRmb = outTradeAmtRmb;
    }

    public BigDecimal getOutTradePrincipal() {
        return outTradePrincipal;
    }

    public void setOutTradePrincipal(BigDecimal outTradePrincipal) {
        this.outTradePrincipal = outTradePrincipal;
    }

    public BigDecimal getOutTradePrincipalRmb() {
        return outTradePrincipalRmb;
    }

    public void setOutTradePrincipalRmb(BigDecimal outTradePrincipalRmb) {
        this.outTradePrincipalRmb = outTradePrincipalRmb;
    }

    public BigDecimal getInTradeAmt() {
        return inTradeAmt;
    }

    public void setInTradeAmt(BigDecimal inTradeAmt) {
        this.inTradeAmt = inTradeAmt;
    }

    public BigDecimal getInTradeAmtRmb() {
        return inTradeAmtRmb;
    }

    public void setInTradeAmtRmb(BigDecimal inTradeAmtRmb) {
        this.inTradeAmtRmb = inTradeAmtRmb;
    }

    public BigDecimal getInTradeQtr() {
        return inTradeQtr;
    }

    public void setInTradeQtr(BigDecimal inTradeQtr) {
        this.inTradeQtr = inTradeQtr;
    }
}
