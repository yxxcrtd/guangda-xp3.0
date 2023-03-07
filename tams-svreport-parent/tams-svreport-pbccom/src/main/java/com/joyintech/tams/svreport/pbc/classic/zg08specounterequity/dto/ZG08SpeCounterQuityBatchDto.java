package com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

public class ZG08SpeCounterQuityBatchDto extends BaseEntity {

    /*明细统计uuid*/
    private String detailuuid;

    /*人行产品代码*/
    private String pbcProdId;

    /*期数*/
    private String termid;

    /*资产负债类别*/
    //@DictFormat(DictCode.资产负债项目)
    private String assetType;

    /*交易对手产品种类*/
    //@DictFormat(DictCode.交易对手产品种类)
    private String prrtyProdType;

    /*交易对手机构代码*/
    private String prrtyOrgCode;

    /*交易对手产品代码*/
    private String prrtyProdId;

    /*币种*/
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

    /*人行一级分类*/
    private String pbcAssetType1;

    /*人行二级分类*/
    private String pbcAssetType2;

    /*人行三级分类*/
    private String pbcAssetType3;

    /*中债资产负债二级类别*/
    private String chbAssetType2;

    private String chbHolderType;

    public String getChbHolderType() {
        return chbHolderType;
    }

    public void setChbHolderType(String chbHolderType) {
        this.chbHolderType = chbHolderType;
    }

    public String getPbcAssetType1() {
        return pbcAssetType1;
    }

    public void setPbcAssetType1(String pbcAssetType1) {
        this.pbcAssetType1 = pbcAssetType1;
    }

    public String getPbcAssetType2() {
        return pbcAssetType2;
    }

    public void setPbcAssetType2(String pbcAssetType2) {
        this.pbcAssetType2 = pbcAssetType2;
    }

    public String getPbcAssetType3() {
        return pbcAssetType3;
    }

    public void setPbcAssetType3(String pbcAssetType3) {
        this.pbcAssetType3 = pbcAssetType3;
    }

    public String getChbAssetType2() {
        return chbAssetType2;
    }

    public void setChbAssetType2(String chbAssetType2) {
        this.chbAssetType2 = chbAssetType2;
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

    private String dataKey;

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /***数据主键*  产品代码;资产负债项目;交易对手产品种类;交易对手产品代码;统计年月*/
    public String getDataKey(){
        return "产品代码:"+this.getPbcPordIdOut()+";资产负债项目:"+this.getAssetType()+";交易对手产品种类:"+this.getPrrtyProdType()
                +";交易对手产品代码:"+ this.getPrrtyProdId()+";统计年月:"+ this.getTermid();
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
