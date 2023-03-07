package com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@TableName("PBC_COL_SPE_COUNTER_PARTY_SUB")
public class ZG08SpeCounterQuityDto extends BaseEntity implements ITableRow {

    private DecimalFormat df2 = new DecimalFormat("0.00");

    /*明细统计uuid*/
    private String detailuuid;

    /*人行产品代码*/
    @BusinessKey("PBC_PROD_ID")
    private String pbcProdId;

    /*期数*/
    @BusinessKey("TERMID")
    private String termid;

    /*资产负债类别*/
    //@DictFormat(DictCode.资产负债项目)
    @BusinessKey("ASSET_TYPE")
    private String assetType;

    /*交易对手产品种类*/
    //@DictFormat(DictCode.交易对手产品种类)
    @BusinessKey("PRRTY_PROD_TYPE")
    private String prrtyProdType;

    /*交易对手机构代码*/
    @BusinessKey("PRRTY_ORG_CODE")
    private String prrtyOrgCode;

    /*交易对手产品代码*/
    @BusinessKey("PRRTY_PROD_ID")
    private String prrtyProdId;

    /*币种*/
    @BusinessKey("CCY")
    private String ccy;

    /*期末金额*/
    private BigDecimal amt;

    /*期末金额折人民币*/
    private BigDecimal amtCny;

    /*报送日期*/
    private Date submitTime;

    /*中债报送截止日*/
    private Date pbcSubmitDeadline;

    /*行内报送截止日*/
    private Date submitDeadline;

    /*有效状态*/
    private String status;

    /*报送状态*/
    //@DictFormat(DictCode.人行报送状态)
    private String sendStatus;

    /*数据日期*/
    private Date statusDate;

    /*机构代码*/
    private String orgCode;

    /*部门代码*/
    private String deptCode;

    /*创建部门*/
    private String createDept;

    /*内部产品代码*/
    private String prodId;

    private String datuuid;

    public String getDatuuid() {
        return datuuid;
    }

    public void setDatuuid(String datuuid) {
        this.datuuid = datuuid;
    }

    public String getDetailuuid() {
        return detailuuid;
    }

    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid;
    }

    public String getPbcProdId() {
        return pbcProdId;
    }

    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId;
    }

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getPrrtyProdType() {
        return prrtyProdType;
    }

    public void setPrrtyProdType(String prrtyProdType) {
        this.prrtyProdType = prrtyProdType;
    }

    public String getPrrtyOrgCode() {
        return prrtyOrgCode;
    }

    public void setPrrtyOrgCode(String prrtyOrgCode) {
        this.prrtyOrgCode = prrtyOrgCode;
    }

    public String getPrrtyProdId() {
        return prrtyProdId;
    }

    public void setPrrtyProdId(String prrtyProdId) {
        this.prrtyProdId = prrtyProdId;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getAmtCny() {
        return amtCny;
    }

    public void setAmtCny(BigDecimal amtCny) {
        this.amtCny = amtCny;
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

    @Override
    public String getStatus() {
        return status;
    }

    @Override
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

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getSendStatusStr(){
        return SvrDataUtil.getZZDictCodeAndName(DictCode.人行报送状态, this.getSendStatus());
    }

    public String getAssetTypeStr(){
        return SvrDataUtil.getZZDictCodeAndName(DictCode.人行_资产负债项目, this.getAssetType());
    }

    public String getPrrtyProdTypeStr(){
        return SvrDataUtil.getZZDictCodeAndName(DictCode.人行_产品种类, this.getPrrtyProdType());
    }

    public String getAmtStr(){
        return df2.format(this.getAmt()!=null?this.getAmt():BigDecimal.ZERO);
    }

    public String getAmtCnyStr(){
        return df2.format(this.getAmtCny()!=null?this.getAmtCny():BigDecimal.ZERO);
    }

    public String getStatusStr(){
        return SvrDataUtil.getNameFromDataDict(DictCode.报送数据状态, this.getStatus());
    }

    public String getCcyStr(){
        return SvrDataUtil.getZZDictCodeAndName(DictCode.币种_中债, this.getCcy());
    }

    private String dataKey;

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /***数据主键*  产品代码;资产负债项目;交易对手产品种类;交易对手产品代码;统计年月*/
    public String getDataKey(){
        return "产品代码:"+this.getPbcProdId()+";资产负债项目:"+this.getAssetType()+";交易对手产品种类:"+this.getPrrtyProdType()
                +";交易对手产品代码:"+ this.getPrrtyProdId()+";统计年月:"+ this.getTermid();
    }
}
