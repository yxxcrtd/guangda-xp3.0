package com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class SvrChbPrdPeriodyieldDto extends BaseEntity {
    /**
     * 交易编号
     */
    private String seqno;

    /**
     * 父产品代码
     */
    private String fprodId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 变动日期
     */
    private Date changeDate;

    /**
     * 变动类型 01:净值变动 02:收益率变动
     */
    private String changType;

    /**
     * 变动值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal prodValue;

    /**
     * 单位累计净值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal unitValueTotal;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 撤销日
     */
    private Date revDate;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 备注
     */
    private String remark;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 累计万份收益
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tdyUnitValueSum;

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 交易编号
     * @param seqno 交易编号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 父产品代码
     * @return 父产品代码
     */
    public String getFprodId() {
        return fprodId;
    }

    /**
     * 设置 父产品代码
     * @param fprodId 父产品代码
     */
    public void setFprodId(String fprodId) {
        this.fprodId = fprodId == null ? null : fprodId.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 变动日期
     * @return 变动日期
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * 设置 变动日期
     * @param changeDate 变动日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * 取得 变动类型 01:净值变动 02:收益率变动
     * @return 变动类型 01:净值变动 02:收益率变动
     */
    public String getChangType() {
        return changType;
    }

    /**
     * 设置 变动类型 01:净值变动 02:收益率变动
     * @param changType 变动类型 01:净值变动 02:收益率变动
     */
    public void setChangType(String changType) {
        this.changType = changType == null ? null : changType.trim();
    }

    /**
     * 取得 变动值
     * @return 变动值
     */
    public BigDecimal getProdValue() {
        return prodValue;
    }

    /**
     * 设置 变动值
     * @param prodValue 变动值
     */
    public void setProdValue(BigDecimal prodValue) {
        this.prodValue = prodValue;
    }

    /**
     * 取得 单位累计净值
     * @return 单位累计净值
     */
    public BigDecimal getUnitValueTotal() {
        return unitValueTotal;
    }

    /**
     * 设置 单位累计净值
     * @param unitValueTotal 单位累计净值
     */
    public void setUnitValueTotal(BigDecimal unitValueTotal) {
        this.unitValueTotal = unitValueTotal;
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
     * 取得 撤销日
     * @return 撤销日
     */
    public Date getRevDate() {
        return revDate;
    }

    /**
     * 设置 撤销日
     * @param revDate 撤销日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRevDate(Date revDate) {
        this.revDate = revDate;
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 设置 数据来源
     * @param dataSource 数据来源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 累计万份收益
     * @return 累计万份收益
     */
    public BigDecimal getTdyUnitValueSum() {
        return tdyUnitValueSum;
    }

    /**
     * 设置 累计万份收益
     * @param tdyUnitValueSum 累计万份收益
     */
    public void setTdyUnitValueSum(BigDecimal tdyUnitValueSum) {
        this.tdyUnitValueSum = tdyUnitValueSum;
    }
}