package com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_COL_REPORT_BALANCE  表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2022-11-08
 */
public class PbcColReportAssetDebtDetailDto {
	 /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 属性代码
     */
    private String propertycode;


    /**
     * 主表关联id
     */
    private String reportuuid;

    /**
     * 本位币
     */
    private  BigDecimal  bCny;

    /**
     * 原币
     */
    private  BigDecimal  oCny;


    /**
     * 折人民币
     */
    private  BigDecimal  othToCny;


    public String getDetailuuid() {
        return detailuuid;
    }

    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid;
    }

    public String getPropertycode() {
        return propertycode;
    }

    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode;
    }

    public String getReportuuid() {
        return reportuuid;
    }

    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid;
    }

    public BigDecimal getbCny() {
        return bCny;
    }

    public void setbCny(BigDecimal bCny) {
        this.bCny = bCny;
    }

    public BigDecimal getoCny() {
        return oCny;
    }

    public void setoCny(BigDecimal oCny) {
        this.oCny = oCny;
    }

    public BigDecimal getOthToCny() {
        return othToCny;
    }

    public void setOthToCny(BigDecimal othToCny) {
        this.othToCny = othToCny;
    }
}