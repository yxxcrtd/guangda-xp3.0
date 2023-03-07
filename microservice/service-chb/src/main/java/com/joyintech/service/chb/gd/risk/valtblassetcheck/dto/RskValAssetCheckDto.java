package com.joyintech.service.chb.gd.risk.valtblassetcheck.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class RskValAssetCheckDto extends BaseEntity {

    /**资管计划代码*/
    private String ptlId;

    /**资管计划名称*/
    private String ptlName;

    /**持仓日期*/
    private Date valDate;

    /**穿透前净资产*/
    private BigDecimal smv;

    /**穿透后总资产*/
    private BigDecimal assetAmt;

    /**穿透后总资产*/
    private BigDecimal odAmt;

    /**检验结果*/
    private BigDecimal checkAmt;

    public String getPtlId() {
        return ptlId;
    }

    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    public String getPtlName() {
        return ptlName;
    }

    public void setPtlName(String ptlName) {
        this.ptlName = ptlName;
    }

    public Date getValDate() {
        return valDate;
    }

    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    public BigDecimal getSmv() {
        return smv;
    }

    public void setSmv(BigDecimal smv) {
        this.smv = smv;
    }

    public BigDecimal getAssetAmt() {
        return assetAmt;
    }

    public void setAssetAmt(BigDecimal assetAmt) {
        this.assetAmt = assetAmt;
    }

    public BigDecimal getOdAmt() {
        return odAmt;
    }

    public void setOdAmt(BigDecimal odAmt) {
        this.odAmt = odAmt;
    }

    public BigDecimal getCheckAmt() {
        return checkAmt;
    }

    public void setCheckAmt(BigDecimal checkAmt) {
        this.checkAmt = checkAmt;
    }
}
