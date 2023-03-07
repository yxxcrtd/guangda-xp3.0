package com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ZG06PbcColAssetUsufructSubBatchDto {
    /*明细统计uuid*/
    private String detailUuid;

    /*人行产品代码*/
    private String pbcProdId;

    /*期数*/
    private String termId;

    /*资产负债项目*/
    private String assetLiabilities;

    /*资产收益权内部编码*/
    private String assetUsufructCode;

    /*基础资产出让机构名称*/
    private String assetTransferName;

    /*基础资产出让机构代码*/
    private String assetTransferId;

    /*基础资产出让机构类型*/
    private String assetInvest;

    /*基础资产出让机构行业*/
    private String assetTransferIindu;

    /*基础资产出让机构注册地区*/
    private String assetTransfer;

    /*基础资产出让机构经济成分*/
    private String economicComposition;

    /*基础资产出让机构规模*/
    private String assetTransferScale;

    /*转让起始日期*/
    private Date transferVdate;

    /*转让预计终止日期*/
    private Date transferEstiMdate;

    /*转让展期到期日期*/
    private Date transferExtdueMdate;

    /*转让实际终止日期*/
    private Date transferActMdate;

    /*基础资产类型*/
    private String basicAssetType;

    /*基础资产原始协议币种*/
    private String assetsOrigCcy;

    /*基础资产原始协议金额*/
    private BigDecimal assetsOrigAmt;

    /*基础资产原始协议金额折人民币*/
    private BigDecimal assetsOrigAmtCny;

    /*基础资产转让币种*/
    private String currencyTransferCcy;

    /*基础资产转让金额*/
    private BigDecimal currencyTransferAmt;

    /*基础资产转让金额折人民币*/
    private BigDecimal currencyTransferAmtCny;

    /*出让机构出表标识*/
    private String labelTransferorMark;

    /*出让机构回购标识*/
    private String repurchaseMark;

    /*利率是否固定*/
    private String rateFixedFlag;

    /*利率水平*/
    private BigDecimal assetRateLevel;

    /*担保方式*/
    private String guaranteeMethod;

    /*基础资产投向部门*/
    private String assetsInvDepa;

    /*基础资产期末币种*/
    private String currencyEperiodCcy;

    /*基础资产期末余额*/
    private BigDecimal currencyAmt;

    /*基础资产期末余额折人民币*/
    private BigDecimal currencyAmtCny;

    /*报送日期*/
    private Date submitTime;

    /*人行报送截止日*/
    private Date pbcSubmitDeadline;

    /*行内报送截止日*/
    private Date submitDeadline;

    /*有效状态*/
    private String status;

    /*报送状态*/
    private String sendStatus;

    /*数据日期*/
    private Date statusDate;

    /*创建人*/
    private String createUser;

    /*创建时间*/
    private Date createTime;

    /*更新人*/
    private String updateUser;

    /*更新时间*/
    private Date updateTime;

    /*机构代码*/
    private String orgCode;

    /*部门代码*/
    private String deptCode;

    /*创建部门*/
    private String createDept;

    /*内部产品代码*/
    private String prodId;

    /*资产代码*/
    private String assetCode;

    private String datuuid;

    public String getDetailUuid() {
        return detailUuid;
    }

    public void setDetailUuid(String detailUuid) {
        this.detailUuid = detailUuid;
    }

    public String getPbcProdId() {
        return pbcProdId;
    }

    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getAssetLiabilities() {
        return assetLiabilities;
    }

    public void setAssetLiabilities(String assetLiabilities) {
        this.assetLiabilities = assetLiabilities;
    }

    public String getAssetUsufructCode() {
        return assetUsufructCode;
    }

    public void setAssetUsufructCode(String assetUsufructCode) {
        this.assetUsufructCode = assetUsufructCode;
    }

    public String getAssetTransferName() {
        return assetTransferName;
    }

    public void setAssetTransferName(String assetTransferName) {
        this.assetTransferName = assetTransferName;
    }

    public String getAssetTransferId() {
        return assetTransferId;
    }

    public void setAssetTransferId(String assetTransferId) {
        this.assetTransferId = assetTransferId;
    }

    public String getAssetInvest() {
        return assetInvest;
    }

    public void setAssetInvest(String assetInvest) {
        this.assetInvest = assetInvest;
    }

    public String getAssetTransferIindu() {
        return assetTransferIindu;
    }

    public void setAssetTransferIindu(String assetTransferIindu) {
        this.assetTransferIindu = assetTransferIindu;
    }

    public String getAssetTransfer() {
        return assetTransfer;
    }

    public void setAssetTransfer(String assetTransfer) {
        this.assetTransfer = assetTransfer;
    }

    public String getEconomicComposition() {
        return economicComposition;
    }

    public void setEconomicComposition(String economicComposition) {
        this.economicComposition = economicComposition;
    }

    public String getAssetTransferScale() {
        return assetTransferScale;
    }

    public void setAssetTransferScale(String assetTransferScale) {
        this.assetTransferScale = assetTransferScale;
    }

    public Date getTransferVdate() {
        return transferVdate;
    }

    public void setTransferVdate(Date transferVdate) {
        this.transferVdate = transferVdate;
    }

    public Date getTransferEstiMdate() {
        return transferEstiMdate;
    }

    public void setTransferEstiMdate(Date transferEstiMdate) {
        this.transferEstiMdate = transferEstiMdate;
    }

    public Date getTransferExtdueMdate() {
        return transferExtdueMdate;
    }

    public void setTransferExtdueMdate(Date transferExtdueMdate) {
        this.transferExtdueMdate = transferExtdueMdate;
    }

    public Date getTransferActMdate() {
        return transferActMdate;
    }

    public void setTransferActMdate(Date transferActMdate) {
        this.transferActMdate = transferActMdate;
    }

    public String getBasicAssetType() {
        return basicAssetType;
    }

    public void setBasicAssetType(String basicAssetType) {
        this.basicAssetType = basicAssetType;
    }

    public String getAssetsOrigCcy() {
        return assetsOrigCcy;
    }

    public void setAssetsOrigCcy(String assetsOrigCcy) {
        this.assetsOrigCcy = assetsOrigCcy;
    }

    public BigDecimal getAssetsOrigAmt() {
        return assetsOrigAmt;
    }

    public void setAssetsOrigAmt(BigDecimal assetsOrigAmt) {
        this.assetsOrigAmt = assetsOrigAmt;
    }

    public BigDecimal getAssetsOrigAmtCny() {
        return assetsOrigAmtCny;
    }

    public void setAssetsOrigAmtCny(BigDecimal assetsOrigAmtCny) {
        this.assetsOrigAmtCny = assetsOrigAmtCny;
    }

    public String getCurrencyTransferCcy() {
        return currencyTransferCcy;
    }

    public void setCurrencyTransferCcy(String currencyTransferCcy) {
        this.currencyTransferCcy = currencyTransferCcy;
    }

    public BigDecimal getCurrencyTransferAmt() {
        return currencyTransferAmt;
    }

    public void setCurrencyTransferAmt(BigDecimal currencyTransferAmt) {
        this.currencyTransferAmt = currencyTransferAmt;
    }

    public BigDecimal getCurrencyTransferAmtCny() {
        return currencyTransferAmtCny;
    }

    public void setCurrencyTransferAmtCny(BigDecimal currencyTransferAmtCny) {
        this.currencyTransferAmtCny = currencyTransferAmtCny;
    }

    public String getLabelTransferorMark() {
        return labelTransferorMark;
    }

    public void setLabelTransferorMark(String labelTransferorMark) {
        this.labelTransferorMark = labelTransferorMark;
    }

    public String getRepurchaseMark() {
        return repurchaseMark;
    }

    public void setRepurchaseMark(String repurchaseMark) {
        this.repurchaseMark = repurchaseMark;
    }

    public String getRateFixedFlag() {
        return rateFixedFlag;
    }

    public void setRateFixedFlag(String rateFixedFlag) {
        this.rateFixedFlag = rateFixedFlag;
    }

    public BigDecimal getAssetRateLevel() {
        return assetRateLevel;
    }

    public void setAssetRateLevel(BigDecimal assetRateLevel) {
        this.assetRateLevel = assetRateLevel;
    }

    public String getGuaranteeMethod() {
        return guaranteeMethod;
    }

    public void setGuaranteeMethod(String guaranteeMethod) {
        this.guaranteeMethod = guaranteeMethod;
    }

    public String getAssetsInvDepa() {
        return assetsInvDepa;
    }

    public void setAssetsInvDepa(String assetsInvDepa) {
        this.assetsInvDepa = assetsInvDepa;
    }

    public String getCurrencyEperiodCcy() {
        return currencyEperiodCcy;
    }

    public void setCurrencyEperiodCcy(String currencyEperiodCcy) {
        this.currencyEperiodCcy = currencyEperiodCcy;
    }

    public BigDecimal getCurrencyAmt() {
        return currencyAmt;
    }

    public void setCurrencyAmt(BigDecimal currencyAmt) {
        this.currencyAmt = currencyAmt;
    }

    public BigDecimal getCurrencyAmtCny() {
        return currencyAmtCny;
    }

    public void setCurrencyAmtCny(BigDecimal currencyAmtCny) {
        this.currencyAmtCny = currencyAmtCny;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getPbcSubmitDeadline() {
        return pbcSubmitDeadline;
    }

    public void setPbcSubmitDeadline(Date pbcSubmitDeadline) {
        this.pbcSubmitDeadline = pbcSubmitDeadline;
    }

    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getDatuuid() {
        return datuuid;
    }

    public void setDatuuid(String datuuid) {
        this.datuuid = datuuid;
    }

    private String dataKey;

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /***数据主键**/
    public String getDataKey(){
        return "产品代码:"+this.getPbcPordIdOut()+";资产负债项目:"+this.getAssetLiabilities()+";资产收益权内部编码:"+this.getAssetUsufructCode()
                +";统计年月:"+ this.getTermId();
    }
    /**
     *报送检查用人行产品代码字段
     */
    private  String  pbcPordIdOut;

    public String getPbcPordIdOut() {
        return pbcPordIdOut;
    }

    public void setPbcPordIdOut(String pbcPordIdOut) {
        this.pbcPordIdOut = pbcPordIdOut;
    }
}
