package com.joyintech.tams.report.gd.report.chbassetselementssub.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class ChbAssetsElementsSub001Dto extends BaseEntity {
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

    private String dateStatusStr;

    public void setDateStatusStr(String dateStatusStr) {
        this.dateStatusStr = dateStatusStr;
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

    /**
     * 按照格式构建一条记录
     * @return
     */
    public String buildReportStr(){
        String separator = "|+|";

        StringBuffer sb = new StringBuffer();
        sb.append(nullCheck(getAssetId())).append(separator);
        sb.append(nullCheck(getAssetName())).append(separator);
        sb.append(nullCheck(getInformationAssetsId())).append(separator);
        sb.append(nullCheck(getAssetFlag())).append(separator);
        sb.append(nullCheck(getFinancierName())).append(separator);
        sb.append(nullCheck(getIndustryId())).append(separator);
        sb.append(nullCheck(getCcy())).append(separator);
        sb.append(nullCheck(getHoldAmount())).append(separator);
        sb.append(nullCheck(getOnPassageAmt())).append(separator);
        sb.append(nullCheck(getHoldQtyAmount())).append(separator);
        sb.append(nullCheck(getBondsman())).append(separator);
        sb.append(nullCheck(getFundsUseRemark())).append(separator);
        sb.append(nullCheck(getPaymentRemark())).append(separator);
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