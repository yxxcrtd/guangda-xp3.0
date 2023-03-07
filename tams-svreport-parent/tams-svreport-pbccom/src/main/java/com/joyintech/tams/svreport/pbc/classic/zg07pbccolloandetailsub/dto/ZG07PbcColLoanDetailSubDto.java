package com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * ZG07 除回购和拆解外贷款明细登记表
 *
 * @author yangxianjiu
 * @date 2022/11/9
 */
@TableName("PBC_COL_LOAN_DETAIL_SUB")
public class ZG07PbcColLoanDetailSubDto extends BaseEntity {

    /**
     * 数据格式化
     */
    private DecimalFormat df2 = new DecimalFormat("0.00");

    private DecimalFormat df5 = new DecimalFormat("0.00000");

    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 期数
     */
    private String termid;

    /**
     * 人行产品代码
     */
    @BusinessKey("PBC_PROD_ID")
    private String pbcProdId;

    /**
     * 贷款借据编码
     */
    private String loanBasisId;

    /**
     * 数据状态
     */
    @BusinessKey("STATUS")
    private String status;

    /**
     * 资产代码
     */
    @BusinessKey("ASSET_CODE")
    private String assetCode;

    /**
     * 贷款转让方机构代码
     */
    private String loanTransferorId;

    /**
     * 贷款合同原始发放机构代码
     */
    private String loanContractOrgId;

    /**
     * 贷款合同原始发放机构所在地代码
     */
    @DictFormat(DictCode.贷款发放所在地)
    private String loanContractOrgCode;

    /**
     * 借款人类型
     */
    @DictFormat(DictCode.借款人类型)
    private String borrowerType;

    /**
     * 地区代码
     */
    @DictFormat(DictCode.贷款发放所在地)
    private String borrowerCounty;

    /**
     * 借款人代码
     */
    private String borrowerId;

    /**
     * 行业信息
     */
    @DictFormat(DictCode.行业门内编码)
    private String industryInfo;

    /**
     * 企业出资人经济成分
     */
    @DictFormat(DictCode.控股类型)
    private String enterpriseInvestorType;

    /**
     * 企业规模
     */
    @DictFormat(DictCode.企业规模)
    private String enterpriseScale;

    /**
     * 贷款产品类别
     */
    @DictFormat(DictCode.贷款产品类别)
    private String loanPrdType;

    /**
     * 贷款实际投向
     */
    @DictFormat(DictCode.贷款实际投向)
    private String loanActualInvest;

    /**
     * 贷款发放日期
     */
    private Date loanVdate;

    /**
     * 贷款到期日期
     */
    private Date loanMdate;

    /**
     * 贷款展期到期日期
     */
    private Date loanExtensionDueDate;

    /**
     * 利率是否固定
     */
    @DictFormat(DictCode.利率是否固定)
    private String rateFixed;

    /**
     * 利率水平
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateLevel;

    /**
     * 贷款种类
     */
    @DictFormat(DictCode.贷款种类)
    private String loansType;

    /**
     * 贷款担保方式
     */
    @DictFormat(DictCode.贷款担保方式)
    private String loanGuaranteeMode;

    /**
     * 贷款质量
     */
    @DictFormat(DictCode.贷款质量)
    private String loanQuality;

    /**
     * 贷款状态
     */
    @DictFormat(DictCode.贷款状态)
    private String loanState;

    /**
     * 贷款转让折扣率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal loanTransferorRate;

    /**
     * 原始合同币种
     */
    @DictFormat(DictCode.币种_人行)
    private String origCcy;

    /**
     * 原始合同金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal origContractAmount;

    /**
     * 原始合同金额折人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal origContractAmountCny;

    /**
     * 贷款余额币种
     */
    @DictFormat(DictCode.币种_人行)
    private String amtCcy;

    /**
     * 贷款余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal contractAmount;

    /**
     * 贷款余额折人民币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal contractAmountCny;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 人行报送截止日
     */
    private Date pbcSubmitDeadline;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 内部产品代码
     */
    private String prodId;

    private String datuuid;

    /**
     * 取得 明细统计uuid
     * @return 明细统计uuid
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 设置 明细统计uuid
     * @param detailuuid 明细统计uuid
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    /**
     * 取得 期数
     * @return 期数
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 设置 期数
     * @param termid 期数
     */
    public void setTermid(String termid) {
        this.termid = termid == null ? null : termid.trim();
    }

    /**
     * 取得 人行产品代码
     * @return 人行产品代码
     */
    public String getPbcProdId() {
        return pbcProdId;
    }

    /**
     * 设置 人行产品代码
     * @param pbcProdId 人行产品代码
     */
    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId == null ? null : pbcProdId.trim();
    }

    /**
     * 取得 贷款借据编码
     * @return 贷款借据编码
     */
    public String getLoanBasisId() {
        return loanBasisId;
    }

    /**
     * 设置 贷款借据编码
     * @param loanBasisId 贷款借据编码
     */
    public void setLoanBasisId(String loanBasisId) {
        this.loanBasisId = loanBasisId == null ? null : loanBasisId.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 贷款转让方机构代码
     * @return 贷款转让方机构代码
     */
    public String getLoanTransferorId() {
        return loanTransferorId;
    }

    /**
     * 设置 贷款转让方机构代码
     * @param loanTransferorId 贷款转让方机构代码
     */
    public void setLoanTransferorId(String loanTransferorId) {
        this.loanTransferorId = loanTransferorId == null ? null : loanTransferorId.trim();
    }

    /**
     * 取得 贷款合同原始发放机构代码
     * @return 贷款合同原始发放机构代码
     */
    public String getLoanContractOrgId() {
        return loanContractOrgId;
    }

    /**
     * 设置 贷款合同原始发放机构代码
     * @param loanContractOrgId 贷款合同原始发放机构代码
     */
    public void setLoanContractOrgId(String loanContractOrgId) {
        this.loanContractOrgId = loanContractOrgId == null ? null : loanContractOrgId.trim();
    }

    /**
     * 取得 贷款合同原始发放机构所在地代码
     * @return 贷款合同原始发放机构所在地代码
     */
    public String getLoanContractOrgCode() {
        return loanContractOrgCode;
    }

    /**
     * 设置 贷款合同原始发放机构所在地代码
     * @param loanContractOrgCode 贷款合同原始发放机构所在地代码
     */
    public void setLoanContractOrgCode(String loanContractOrgCode) {
        this.loanContractOrgCode = loanContractOrgCode == null ? null : loanContractOrgCode.trim();
    }

    /**
     * 取得 借款人类型
     * @return 借款人类型
     */
    public String getBorrowerType() {
        return borrowerType;
    }

    /**
     * 设置 借款人类型
     * @param borrowerType 借款人类型
     */
    public void setBorrowerType(String borrowerType) {
        this.borrowerType = borrowerType == null ? null : borrowerType.trim();
    }

    /**
     * 取得 地区代码
     * @return 地区代码
     */
    public String getBorrowerCounty() {
        return borrowerCounty;
    }

    /**
     * 设置 地区代码
     * @param borrowerCounty 地区代码
     */
    public void setBorrowerCounty(String borrowerCounty) {
        this.borrowerCounty = borrowerCounty == null ? null : borrowerCounty.trim();
    }

    /**
     * 取得 借款人代码
     * @return 借款人代码
     */
    public String getBorrowerId() {
        return borrowerId;
    }

    /**
     * 设置 借款人代码
     * @param borrowerId 借款人代码
     */
    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId == null ? null : borrowerId.trim();
    }

    /**
     * 取得 行业信息
     * @return 行业信息
     */
    public String getIndustryInfo() {
        return industryInfo;
    }

    /**
     * 设置 行业信息
     * @param industryInfo 行业信息
     */
    public void setIndustryInfo(String industryInfo) {
        this.industryInfo = industryInfo == null ? null : industryInfo.trim();
    }

    /**
     * 取得 企业出资人经济成分
     * @return 企业出资人经济成分
     */
    public String getEnterpriseInvestorType() {
        return enterpriseInvestorType;
    }

    /**
     * 设置 企业出资人经济成分
     * @param enterpriseInvestorType 企业出资人经济成分
     */
    public void setEnterpriseInvestorType(String enterpriseInvestorType) {
        this.enterpriseInvestorType = enterpriseInvestorType == null ? null : enterpriseInvestorType.trim();
    }

    /**
     * 取得 企业规模
     * @return 企业规模
     */
    public String getEnterpriseScale() {
        return enterpriseScale;
    }

    /**
     * 设置 企业规模
     * @param enterpriseScale 企业规模
     */
    public void setEnterpriseScale(String enterpriseScale) {
        this.enterpriseScale = enterpriseScale == null ? null : enterpriseScale.trim();
    }

    /**
     * 取得 贷款产品类别
     * @return 贷款产品类别
     */
    public String getLoanPrdType() {
        return loanPrdType;
    }

    /**
     * 设置 贷款产品类别
     * @param loanPrdType 贷款产品类别
     */
    public void setLoanPrdType(String loanPrdType) {
        this.loanPrdType = loanPrdType == null ? null : loanPrdType.trim();
    }

    /**
     * 取得 贷款实际投向
     * @return 贷款实际投向
     */
    public String getLoanActualInvest() {
        return loanActualInvest;
    }

    /**
     * 设置 贷款实际投向
     * @param loanActualInvest 贷款实际投向
     */
    public void setLoanActualInvest(String loanActualInvest) {
        this.loanActualInvest = loanActualInvest == null ? null : loanActualInvest.trim();
    }

    /**
     * 取得 贷款发放日期
     * @return 贷款发放日期
     */
    public Date getLoanVdate() {
        return loanVdate;
    }

    /**
     * 设置 贷款发放日期
     * @param loanVdate 贷款发放日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLoanVdate(Date loanVdate) {
        this.loanVdate = loanVdate;
    }

    /**
     * 取得 贷款到期日期
     * @return 贷款到期日期
     */
    public Date getLoanMdate() {
        return loanMdate;
    }

    /**
     * 设置 贷款到期日期
     * @param loanMdate 贷款到期日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLoanMdate(Date loanMdate) {
        this.loanMdate = loanMdate;
    }

    /**
     * 取得 贷款展期到期日期
     * @return 贷款展期到期日期
     */
    public Date getLoanExtensionDueDate() {
        return loanExtensionDueDate;
    }

    /**
     * 设置 贷款展期到期日期
     * @param loanExtensionDueDate 贷款展期到期日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLoanExtensionDueDate(Date loanExtensionDueDate) {
        this.loanExtensionDueDate = loanExtensionDueDate;
    }

    /**
     * 取得 利率是否固定
     * @return 利率是否固定
     */
    public String getRateFixed() {
        return rateFixed;
    }

    /**
     * 设置 利率是否固定
     * @param rateFixed 利率是否固定
     */
    public void setRateFixed(String rateFixed) {
        this.rateFixed = rateFixed == null ? null : rateFixed.trim();
    }

    /**
     * 取得 利率水平
     * @return 利率水平
     */
    public BigDecimal getRateLevel() {
        return rateLevel;
    }

    /**
     * 设置 利率水平
     * @param rateLevel 利率水平
     */
    public void setRateLevel(BigDecimal rateLevel) {
        this.rateLevel = rateLevel;
    }

    /**
     * 取得 贷款种类
     * @return 贷款种类
     */
    public String getLoansType() {
        return loansType;
    }

    /**
     * 设置 贷款种类
     * @param loansType 贷款种类
     */
    public void setLoansType(String loansType) {
        this.loansType = loansType == null ? null : loansType.trim();
    }

    /**
     * 取得 贷款担保方式
     * @return 贷款担保方式
     */
    public String getLoanGuaranteeMode() {
        return loanGuaranteeMode;
    }

    /**
     * 设置 贷款担保方式
     * @param loanGuaranteeMode 贷款担保方式
     */
    public void setLoanGuaranteeMode(String loanGuaranteeMode) {
        this.loanGuaranteeMode = loanGuaranteeMode == null ? null : loanGuaranteeMode.trim();
    }

    /**
     * 取得 贷款质量
     * @return 贷款质量
     */
    public String getLoanQuality() {
        return loanQuality;
    }

    /**
     * 设置 贷款质量
     * @param loanQuality 贷款质量
     */
    public void setLoanQuality(String loanQuality) {
        this.loanQuality = loanQuality == null ? null : loanQuality.trim();
    }

    /**
     * 取得 贷款状态
     * @return 贷款状态
     */
    public String getLoanState() {
        return loanState;
    }

    /**
     * 设置 贷款状态
     * @param loanState 贷款状态
     */
    public void setLoanState(String loanState) {
        this.loanState = loanState == null ? null : loanState.trim();
    }

    /**
     * 取得 贷款转让折扣率
     * @return 贷款转让折扣率
     */
    public BigDecimal getLoanTransferorRate() {
        return loanTransferorRate;
    }

    /**
     * 设置 贷款转让折扣率
     * @param loanTransferorRate 贷款转让折扣率
     */
    public void setLoanTransferorRate(BigDecimal loanTransferorRate) {
        this.loanTransferorRate = loanTransferorRate;
    }

    /**
     * 取得 原始合同币种
     * @return 原始合同币种
     */
    public String getOrigCcy() {
        return origCcy;
    }

    /**
     * 设置 原始合同币种
     * @param origCcy 原始合同币种
     */
    public void setOrigCcy(String origCcy) {
        this.origCcy = origCcy == null ? null : origCcy.trim();
    }

    /**
     * 取得 原始合同金额
     * @return 原始合同金额
     */
    public BigDecimal getOrigContractAmount() {
        return origContractAmount;
    }

    /**
     * 设置 原始合同金额
     * @param origContractAmount 原始合同金额
     */
    public void setOrigContractAmount(BigDecimal origContractAmount) {
        this.origContractAmount = origContractAmount;
    }

    /**
     * 取得 原始合同金额折人民币
     * @return 原始合同金额折人民币
     */
    public BigDecimal getOrigContractAmountCny() {
        return origContractAmountCny;
    }

    /**
     * 设置 原始合同金额折人民币
     * @param origContractAmountCny 原始合同金额折人民币
     */
    public void setOrigContractAmountCny(BigDecimal origContractAmountCny) {
        this.origContractAmountCny = origContractAmountCny;
    }

    /**
     * 取得 贷款余额币种
     * @return 贷款余额币种
     */
    public String getAmtCcy() {
        return amtCcy;
    }

    /**
     * 设置 贷款余额币种
     * @param amtCcy 贷款余额币种
     */
    public void setAmtCcy(String amtCcy) {
        this.amtCcy = amtCcy == null ? null : amtCcy.trim();
    }

    /**
     * 取得 贷款余额
     * @return 贷款余额
     */
    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    /**
     * 设置 贷款余额
     * @param contractAmount 贷款余额
     */
    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    /**
     * 取得 贷款余额折人民币
     * @return 贷款余额折人民币
     */
    public BigDecimal getContractAmountCny() {
        return contractAmountCny;
    }

    /**
     * 设置 贷款余额折人民币
     * @param contractAmountCny 贷款余额折人民币
     */
    public void setContractAmountCny(BigDecimal contractAmountCny) {
        this.contractAmountCny = contractAmountCny;
    }

    /**
     * 取得 报送日期
     * @return 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置 报送日期
     * @param submitTime 报送日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 取得 人行报送截止日
     * @return 中债报送截止日
     */
    public Date getPbcSubmitDeadline() {
        return pbcSubmitDeadline;
    }

    /**
     * 设置 人行报送截止日
     * @param pbcSubmitDeadline 人行报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPbcSubmitDeadline(Date pbcSubmitDeadline) {
        this.pbcSubmitDeadline = pbcSubmitDeadline;
    }

    /**
     * 取得 行内报送截止日
     * @return 行内报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 设置 行内报送截止日
     * @param submitDeadline 行内报送截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
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
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    /**
     * 取得 内部产品代码
     * @return 内部产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 内部产品代码
     * @param prodId 内部产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getDatuuid() {
        return datuuid;
    }

    public void setDatuuid(String datuuid) {
        this.datuuid = datuuid;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getSendStatusStr() {
        return getSendStatus() == null ? "" : SvrDataUtil.getZZDictCodeAndName(DictCode.人行报送状态, this.getSendStatus());
    }

    public String getOrigCcyStr() {
        return getOrigCcy() == null ? "" : SvrDataUtil.getZZDictCodeAndName(DictCode.币种_人行, this.getOrigCcy());
    }

    public String getAmtCcyStr() {
        return getAmtCcy() == null ? "" : SvrDataUtil.getZZDictCodeAndName(DictCode.币种_人行, this.getAmtCcy());
    }

    public String getLoanVdateStr() {
        return getLoanVdate() == null ? "" : DateUtil.format(getLoanVdate());
    }

    public String getLoanMdateStr() {
        return getLoanMdate() == null ? "" : DateUtil.format(getLoanMdate());
    }

    public String getLoanExtensionDueDateStr() {
        return getLoanExtensionDueDate() == null ? "" : DateUtil.format(getLoanExtensionDueDate());
    }

    // 原始合同金额
    public String getOrigContractAmountStr(){
        return getOrigContractAmount() == null ? "" : df2.format(getOrigContractAmount());
    }

    //  原始合同金额折人民币
    public String getOrigContractAmountCnyStr() {
         return origContractAmountCny==null?"":df2.format(origContractAmountCny);
    }

    // 贷款余额
    public String getContractAmountStr() {
        return contractAmount==null?"":df2.format(contractAmount);
    }

    // 贷款余额折人民币
    public String getContractAmountCnyStr() {
        return contractAmountCny==null?"":df2.format(contractAmountCny);
    }

    // 贷款转让折扣率
    public String getLoanTransferorRateStr() {
        return loanTransferorRate==null?"":df5.format(loanTransferorRate);
    }

    // 利率水平
    public String getRateLevelStr() {
        return rateLevel==null?"":df5.format(rateLevel);
    }

    // 借款人类型
    public String getBorrowerTypeStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.借款人类型, getBorrowerType());
    }

    // 行业信息
    public String getIndustryInfoStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.行业门内编码, getIndustryInfo());
    }

    // 企业出资人经济成分
    public String getEnterpriseInvestorTypeStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.控股类型, getEnterpriseInvestorType());
    }

    // 企业规模
    public String getEnterpriseScaleStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.企业规模, getEnterpriseScale());
    }

    // 贷款产品类别
    public String getLoanPrdTypeStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.贷款产品类别, getLoanPrdType());
    }

    // 利率是否固定
    public String getRateFixedStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.利率是否固定, getRateFixed());
    }

    // 贷款种类
    public String getLoansTypeStr() {
        return getLoansType() == null ? "" : SvrDataUtil.getZZDictCodeAndName(DictCode.贷款种类, this.getLoansType());
    }

    // 贷款担保方式
    public String getLoanGuaranteeModeStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.贷款担保方式, getLoanGuaranteeMode());
    }

    // 贷款质量
    public String getLoanQualityStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.贷款质量, getLoanQuality());
    }

    // 贷款状态
    public String getLoanStateStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.贷款状态, getLoanState());
    }

    // 贷款合同原始发放机构所在地代码
    public String getLoanContractOrgCodeStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.贷款发放所在地, getLoanContractOrgCode());
    }

    // 地区代码
    public String getBorrowerCountyStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.贷款发放所在地, getBorrowerCounty());
    }

    // 贷款实际投向
    public String getLoanActualInvestStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.贷款实际投向, getLoanActualInvest());
    }

    private String dataKey;

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /***数据主键**/
    public String getDataKey(){
        return "产品代码:"+this.getPbcPordIdOut()+";贷款借据编码:"+this.getLoanBasisId()
                +";统计年月:"+ this.getTermid();
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