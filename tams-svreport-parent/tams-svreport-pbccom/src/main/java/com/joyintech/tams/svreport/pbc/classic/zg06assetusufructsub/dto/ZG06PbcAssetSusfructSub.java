package com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;

/**
 * 描述:PBC_COL_ASSET_USUFRUCT_SUB
 * @version
 * @author:  Administrator
 * @创建时间: 2019-06-05
 */
@TableName("PBC_COL_ASSET_USUFRUCT_SUB")
public class ZG06PbcAssetSusfructSub extends BaseEntity {


    private DecimalFormat df1 = new DecimalFormat("0.00000");
    private DecimalFormat df2 = new DecimalFormat("0.00");
    /**
     * 明细统计uuid
     */
    @BusinessKey("DETAILUUID")
    private String detailUuid;

    /**
     * 人行产品代码
     */
    @BusinessKey("PBC_PROD_ID")
    private String pbcProdId;

    /**
     * 人行产品代码
     */
    private String pbcProdIdShow;

    public String getPbcProdIdShow() {
        return pbcProdIdShow;
    }

    public void setPbcProdIdShow(String pbcProdIdShow) {
        this.pbcProdIdShow = pbcProdIdShow;
    }

    /**
     * 期数
     */
    private String termId;

    /**
     * 资产代码
     */
    @BusinessKey("ASSET_CODE")
    private String assetCode;

    /**
     * 资产负债项目，枚举值参见CUSTOMER_TYPE1
     */
    @BusinessKey("PBC_ASSET_TYPE")
    @DictFormat(DictCode.人行_资产负债项目)
    private String assetLiabilities;

    /**
     * 资产收益权内部编码
     */
    private String assetUsufructCode;

    /**
     * 基础资产出让机构名称
     */
    private String assetTransferName;

    /**
     * 基础资产出让机构代码
     */
    private String assetTransferId;

    /**
     * 基础资产出让机构类型
     */
    private String assetInvest;

    /**
     * 基础资产出让机构行业
     */
    private String assetTransferIindu;

    /**
     * 基础资产出让机构注册地区
     */
    private String assetTransfer;

    /**
     * 基础资产出让机构经济成分
     */
    private String economicComposition;

    /**
     * 基础资产出让机构规模
     */
    private String assetTransferScale;

    /**
     * 转让起始日期
     */
    private Date transferVdate;

    /**
     * 转让预计终止日期
     */
    private Date transferEstiMdate;

    /**
     * 转让展期到期日期
     */
    private Date transferExtdueMdate;

    /**
     * 转让实际终止日期
     */
    private Date transferActMdate;

    /**
     * 基础资产类型
     */
    private String basicAssetType;

    /**
     * 基础资产原始协议币种
     */
    private String assetsOrigCcy;

    /**
     * 基础资产原始协议金额
     */
    private BigDecimal assetsOrigAmt;

    /**
     * 基础资产原始协议金额折人民币
     */
    private BigDecimal assetsOrigAmtCny;

    /**
     * 基础资产转让币种
     */
    private String currencyTransferCcy;

    /**
     * 基础资产转让金额
     */
    private BigDecimal currencyTransferAmt;

    /**
     * 基础资产转让金额折人民币
     */
    private BigDecimal currencyTransferAmtCny;

    /**
     * 出让机构出表标识
     */
    private String labelTransferorMark;

    /**
     * 出让机构回购标识
     */
    private String repurchaseMark;

    /**
     * 利率是否固定
     */
    private String rateFixedFlag;

    /**
     * 利率水平
     */
    private BigDecimal assetRateLevel;

    /**
     * 担保方式
     */
    private String guaranteeMethod;

    /**
     * 基础资产投向部门
     */
    private String assetsInvDepa;

    /**
     * 基础资产期末币种
     */
    private String currencyEperiodCcy;

    /**
     * 基础资产期末余额
     */
    private BigDecimal currencyAmt;

    /**
     * 基础资产期末余额折人民币
     */
    private BigDecimal currencyAmtCny;
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
     * 数据状态
     */
    private String status;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 产品内部代码
     */
    private String prodId;

    private String datuuid;

    public String getDatuuid() {
        return datuuid;
    }

    public void setDatuuid(String datuuid) {
        this.datuuid = datuuid;
    }

    public String getDetailUuid() {
        return detailUuid;
    }

    public void setDetailUuid(String detailUuid) {
        this.detailUuid = detailUuid;
    }

    /**
     * 人行产品代码
     * @return PBC_PROD_ID 人行产品代码
     */
    public String getPbcProdId() {
        return pbcProdId;
    }

    /**
     * 人行产品代码
     * @param pbcProdId 人行产品代码
     */
    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId == null ? null : pbcProdId.trim();
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetLiabilities() {
        return assetLiabilities;
    }

    public void setAssetLiabilities(String assetLiabilities) {
        this.assetLiabilities = assetLiabilities;
    }

    public String getAssetLiabilitiesStr() {
        return assetLiabilities+" "+SysDataUtil.getNameFromDataDict(DictCode.人行_资产负债项目,assetLiabilities);
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

    public String getTransferVdateStr() {
        return null!=transferVdate?DateUtil.format(transferVdate):null;
    }

    public Date getTransferEstiMdate() {
        return transferEstiMdate;
    }

    public void setTransferEstiMdate(Date transferEstiMdate) {
        this.transferEstiMdate = transferEstiMdate;
    }

    public String getTransferEstiMdateStr() {
        return null!=transferEstiMdate?DateUtil.format(transferEstiMdate):null;
    }

    public Date getTransferExtdueMdate() {
        return transferExtdueMdate;
    }

    public void setTransferExtdueMdate(Date transferExtdueMdate) {
        this.transferExtdueMdate = transferExtdueMdate;
    }

    public String getTransferExtdueMdateStr() {
        return null!=transferExtdueMdate?DateUtil.format(transferExtdueMdate):null;
    }

    public Date getTransferActMdate() {
        return transferActMdate;
    }

    public void setTransferActMdate(Date transferActMdate) {
        this.transferActMdate = transferActMdate;
    }

    public String getTransferActMdateStr() {
        return null!=transferActMdate?DateUtil.format(transferActMdate):null;
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

    public String getLabelTransferorMarkStr() {
        return labelTransferorMark+" "+SysDataUtil.getNameFromDataDict(DictCode.人行是否,labelTransferorMark);
    }

    public String getRepurchaseMark() {
        return repurchaseMark;
    }

    public void setRepurchaseMark(String repurchaseMark) {
        this.repurchaseMark = repurchaseMark;
    }

    public String getRepurchaseMarkStr() {
        return repurchaseMark+" "+SysDataUtil.getNameFromDataDict(DictCode.人行是否,repurchaseMark);
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

    public Date getPbcSubmitDeadline() {
        return pbcSubmitDeadline;
    }

    public void setPbcSubmitDeadline(Date pbcSubmitDeadline) {
        this.pbcSubmitDeadline = pbcSubmitDeadline;
    }

    /**
     * 报送状态
     * @return SEND_STATUS 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 报送日期
     * @return SUBMIT_TIME 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 报送日期
     * @param submitTime 报送日期
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 数据日期
     * @return STATUS_DATE 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 行内报送截止日
     * @return SUBMIT_DEADLINE 行内报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 行内报送截止日
     * @param submitDeadline 行内报送截止日
     */
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 数据状态
     * @return STATUS 数据状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 数据状态
     * @param status 数据状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 创建人
     * @return CREATE_USER 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 机构代码
     * @return ORG_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 创建部门
     * @return CREATE_DEPT 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     * @param createDept 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间
     * @return CREATE_TIME 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     * @return UPDATE_USER 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间
     * @return UPDATE_TIME 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     *
     * @return
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * @param prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getStatusStr(){
        return SvrDataUtil.getNameFromDataDict(DictCode.报送数据状态, this.getStatus());
    }
    public String getSendStatusStr(){
        return SvrDataUtil.getNameFromDataDict(DictCode.报送状态, this.getSendStatus());
    }

    public String getStatusDateStr(){
        return null!=statusDate?DateUtil.format(statusDate):null;
    }
    public String getSubmitTimeStr(){
        return null!=submitTime?DateUtil.format(submitTime):null;
    }


    public String getAssetsOrigAmtStr()    {return assetsOrigAmt==null?"":df2.format(assetsOrigAmt);}
    public String getAssetsOrigAmtCnyStr()    {return assetsOrigAmtCny==null?"":df2.format(assetsOrigAmtCny);}
    public String getCurrencyTransferAmtStr()    {return currencyTransferAmt==null?"":df2.format(currencyTransferAmt);}
    public String getCurrencyTransferAmtCnyStr()    {return currencyTransferAmtCny==null?"":df2.format(currencyTransferAmtCny);}
    public String getCurrencyAmtStr()    {return currencyAmt==null?"":df2.format(currencyAmt);}
    public String getCurrencyAmtCnyStr()    {return currencyAmtCny==null?"":df2.format(currencyAmtCny);}
    public String getAssetRateLevelStr()    {return assetRateLevel==null?"":df1.format(assetRateLevel);}
    public String getAssetInvestStr()    {
        return assetInvest==null?"":assetInvest+" "+SysDataUtil.getNameFromDataDict(DictCode.数据采集平台客户类型,assetInvest.trim());
    }
    public String getEconomicCompositionStr()    {
        return economicComposition==null?"":economicComposition+" "+SysDataUtil.getNameFromDataDict(DictCode.经济成分二级,economicComposition.trim());
    }
    public String getAssetTransferStr()    {

        if(assetTransfer!=null&&SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_市,assetTransfer.trim())!=null){
            return assetTransfer==null?"":assetTransfer+" "+SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_市,assetTransfer.trim());
        }else if(assetTransfer!=null&&SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_省,assetTransfer.trim())!=null){
            return assetTransfer==null?"":assetTransfer+" "+SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_省,assetTransfer.trim());
        }else if(assetTransfer!=null&&SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_县,assetTransfer.trim())!=null){
            return assetTransfer==null?"":assetTransfer+" "+SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_县,assetTransfer.trim());
        } else if(assetTransfer!=null&&SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区,assetTransfer.trim())!=null){
            return assetTransfer==null?"":assetTransfer+" "+SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区,assetTransfer.trim());
        }else {
            return "";
        }

    }
    public String getAssetTransferIinduStr()    {
        return assetTransferIindu==null?"":assetTransferIindu+" "+SysDataUtil.getNameFromDataDict(DictCode.行业门内编码,assetTransferIindu.trim());
    }
    public String getAssetTransferScaleStr()    {
        return assetTransferScale==null?"":assetTransferScale+" "+SysDataUtil.getNameFromDataDict(DictCode.企业规模,assetTransferScale.trim());
    }
    public String getBasicAssetTypeStr()    {
        return basicAssetType==null?"":basicAssetType+" "+ SysDataUtil.getNameFromDataDict(DictCode.基础资产类型,basicAssetType.trim());
    }
    public String getAssetsOrigCcyStr()    {
        return assetsOrigCcy==null?"":assetsOrigCcy+" "+SysDataUtil.getNameFromDataDict(DictCode.币种_人行,assetsOrigCcy.trim());
    }
    public String getCurrencyTransferCcyStr()    {
        return currencyTransferCcy==null?"":currencyTransferCcy+" "+SysDataUtil.getNameFromDataDict(DictCode.币种_人行,currencyTransferCcy.trim());
    }
    public String getCurrencyEperiodCcyStr()    {
        return currencyEperiodCcy==null?"":currencyEperiodCcy+" "+SysDataUtil.getNameFromDataDict(DictCode.币种_人行,currencyEperiodCcy.trim());
    }
    public String getRateFixedFlagStr()    {
        return rateFixedFlag==null?"":rateFixedFlag+" "+ SysDataUtil.getNameFromDataDict(DictCode.利率是否固定,rateFixedFlag.trim());
    }
    public String getAssetsInvDepaStr()    {
        return assetsInvDepa==null?"":assetsInvDepa+" "+ SysDataUtil.getNameFromDataDict(DictCode.数据采集平台客户类型,assetsInvDepa.trim());
    }
    public String getGuaranteeMethodStr()    {
        return guaranteeMethod==null?"":guaranteeMethod+" "+ SysDataUtil.getNameFromDataDict(DictCode.贷款担保方式_资产,guaranteeMethod.trim());
    }

    private String dataKey;

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /***数据主键**/
    public String getDataKey(){//资产负债项目;资产收益权内部编码
        return "产品代码:"+this.getPbcProdId() +";资产负债项目:"+this.getAssetLiabilities() +";资产收益权内部编码:"+this.getAssetUsufructCode() +";统计年月:"+ this.getTermId();
    }

}