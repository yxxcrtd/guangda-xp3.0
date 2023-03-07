package com.joyintech.tams.svreport.gd.report.chbassetselementssub.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class ChbAssetsElementsSub extends BaseEntity {
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
    private String assetId;

    /**
     * 投资标的名称
     */
    private String assetName;

    /**
     * 资管资产类型代码
     */
    private String informationAssetsId;

    /**
     * 非标准债权资产标志
     */
    private String assetFlag;

    /**
     * 融资人名称
     */
    private String financierName;

    /**
     * 行业代码
     */
    private String industryId;

    /**
     * 币种代码
     */
    private String ccy;

    /**
     * 持仓金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal holdAmount;

    /**
     * 在途资金金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal onPassageAmt;

    /**
     * 折人民币持仓金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal holdQtyAmount;

    /**
     * 担保人名称
     */
    private String bondsman;

    /**
     * 资金用途描述
     */
    private String fundsUseRemark;

    /**
     * 还款来源描述
     */
    private String paymentRemark;

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

    /**
     * 组别
     */
    private String groupId;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 资产一级分类
     */
    private String orgAssetType1;

    /**
     * 发行人代码
     */
    private String chbIssuerId;

    /**
     * 融资人代码
     */
    private String chbFinancierId;

    /**
     * 管理人代码
     */
    private String chbManagerId;

    /**
     * 资产/负债二级分类
     */
    private String assetType2;

    private String assetCode;

    private String tradeCounter;

    private String customerId;

    /**
     * 起息日
     */
    private Date vDate;

    /**
     * 到期日
     */
    private Date mDate;

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getTradeCounter() {
        return tradeCounter;
    }

    public void setTradeCounter(String tradeCounter) {
        this.tradeCounter = tradeCounter;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getIndustry2() {
        return industry2;
    }

    public void setIndustry2(String industry2) {
        this.industry2 = industry2;
    }

    private String industry2;



    public String getOrgAssetType1() {
        return orgAssetType1;
    }

    public void setOrgAssetType1(String orgAssetType1) {
        this.orgAssetType1 = orgAssetType1;
    }

    public String getChbIssuerId() {
        return chbIssuerId;
    }

    public void setChbIssuerId(String chbIssuerId) {
        this.chbIssuerId = chbIssuerId;
    }

    public String getChbFinancierId() {
        return chbFinancierId;
    }

    public void setChbFinancierId(String chbFinancierId) {
        this.chbFinancierId = chbFinancierId;
    }

    public String getChbManagerId() {
        return chbManagerId;
    }

    public void setChbManagerId(String chbManagerId) {
        this.chbManagerId = chbManagerId;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
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
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 理财产品编号
     * @param assetId 理财产品编号
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 投资标的名称
     * @return 投资标的名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 投资标的名称
     * @param assetName 投资标的名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    /**
     * 取得 资管资产类型代码
     * @return 资管资产类型代码
     */
    public String getInformationAssetsId() {
        return informationAssetsId;
    }

    /**
     * 设置 资管资产类型代码
     * @param informationAssetsId 资管资产类型代码
     */
    public void setInformationAssetsId(String informationAssetsId) {
        this.informationAssetsId = informationAssetsId == null ? null : informationAssetsId.trim();
    }

    /**
     * 取得 非标准债权资产标志
     * @return 非标准债权资产标志
     */
    public String getAssetFlag() {
        return assetFlag;
    }

    /**
     * 设置 非标准债权资产标志
     * @param assetFlag 非标准债权资产标志
     */
    public void setAssetFlag(String assetFlag) {
        this.assetFlag = assetFlag == null ? null : assetFlag.trim();
    }

    /**
     * 取得 融资人名称
     * @return 融资人名称
     */
    public String getFinancierName() {
        return financierName;
    }

    /**
     * 设置 融资人名称
     * @param financierName 融资人名称
     */
    public void setFinancierName(String financierName) {
        this.financierName = financierName == null ? null : financierName.trim();
    }

    /**
     * 取得 行业代码
     * @return 行业代码
     */
    public String getIndustryId() {
        return industryId;
    }

    /**
     * 设置 行业代码
     * @param industryId 行业代码
     */
    public void setIndustryId(String industryId) {
        this.industryId = industryId == null ? null : industryId.trim();
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
     * 取得 持仓金额
     * @return 持仓金额
     */
    public BigDecimal getHoldAmount() {
        return holdAmount;
    }

    /**
     * 设置 持仓金额
     * @param holdAmount 持仓金额
     */
    public void setHoldAmount(BigDecimal holdAmount) {
        this.holdAmount = holdAmount;
    }

    /**
     * 取得 在途资金金额
     * @return 在途资金金额
     */
    public BigDecimal getOnPassageAmt() {
        return onPassageAmt;
    }

    /**
     * 设置 在途资金金额
     * @param onPassageAmt 在途资金金额
     */
    public void setOnPassageAmt(BigDecimal onPassageAmt) {
        this.onPassageAmt = onPassageAmt;
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
     * 取得 担保人名称
     * @return 担保人名称
     */
    public String getBondsman() {
        return bondsman;
    }

    /**
     * 设置 担保人名称
     * @param bondsman 担保人名称
     */
    public void setBondsman(String bondsman) {
        this.bondsman = bondsman == null ? null : bondsman.trim();
    }

    /**
     * 取得 资金用途描述
     * @return 资金用途描述
     */
    public String getFundsUseRemark() {
        return fundsUseRemark;
    }

    /**
     * 设置 资金用途描述
     * @param fundsUseRemark 资金用途描述
     */
    public void setFundsUseRemark(String fundsUseRemark) {
        this.fundsUseRemark = fundsUseRemark == null ? null : fundsUseRemark.trim();
    }

    /**
     * 取得 还款来源描述
     * @return 还款来源描述
     */
    public String getPaymentRemark() {
        return paymentRemark;
    }

    /**
     * 设置 还款来源描述
     * @param paymentRemark 还款来源描述
     */
    public void setPaymentRemark(String paymentRemark) {
        this.paymentRemark = paymentRemark == null ? null : paymentRemark.trim();
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

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}