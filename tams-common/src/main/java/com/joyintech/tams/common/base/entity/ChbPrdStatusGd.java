package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

public class ChbPrdStatusGd extends BaseEntity {
    /**
     * null
     */
    private String uuid;

    /**
     * null
     */
    private String issuePartyId;

    /**
     * null
     */
    private Date posDate;

    /**
     * null
     */
    private String prodId;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal nHldmkvLocalZchj;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal nHldmkvLocalZcjz;

    /**
     * null
     */
    private String orgCode;

    /**
     * null
     */
    private String deptCode;

    public String  getkey(){
        return this.getProdId()+"_"+ DateUtil.format(this.getPosDate());
    }

    /**
     * 取得 null
     * @return null
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 null
     * @param uuid null
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 null
     * @param issuePartyId null
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getPosDate() {
        return posDate;
    }

    /**
     * 设置 null
     * @param posDate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPosDate(Date posDate) {
        this.posDate = posDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 null
     * @param prodId null
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getnHldmkvLocalZchj() {
        return nHldmkvLocalZchj;
    }

    /**
     * 设置 null
     * @param nHldmkvLocalZchj null
     */
    public void setnHldmkvLocalZchj(BigDecimal nHldmkvLocalZchj) {
        this.nHldmkvLocalZchj = nHldmkvLocalZchj;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getnHldmkvLocalZcjz() {
        return nHldmkvLocalZcjz;
    }

    /**
     * 设置 null
     * @param nHldmkvLocalZcjz null
     */
    public void setnHldmkvLocalZcjz(BigDecimal nHldmkvLocalZcjz) {
        this.nHldmkvLocalZcjz = nHldmkvLocalZcjz;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 null
     * @param orgCode null
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 null
     * @param deptCode null
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }
}