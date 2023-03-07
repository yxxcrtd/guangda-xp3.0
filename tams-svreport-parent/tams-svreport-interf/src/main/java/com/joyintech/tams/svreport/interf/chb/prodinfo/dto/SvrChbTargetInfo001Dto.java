package com.joyintech.tams.svreport.interf.chb.prodinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 理财产品账户余额登记表Dto
 * @author yangxianjiu
 * @date 2022/03/30
 */
public class SvrChbTargetInfo001Dto extends BaseEntity {
    /**
     * uuid
     */
    private String uuid;

    /**
     * 产品代码
     */
    private String account;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 投资者名称
     */
    private String holderName;

    /**
     * 投资者类别
     */
    private String holderType;
    /**
     * 数据日期
     */
    private Date holdDate;


    /**
     * 币种
     */
    private String ccy;

    /**
     * 持有份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qty;
    /**
     * 持有金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 人行产品代码
     */
    private String pbcProdId;

    public String getPbcProdId() {
        return pbcProdId;
    }

    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIndentifyMark() {
        return indentifyMark;
    }

    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderType() {
        return holderType;
    }

    public void setHolderType(String holderType) {
        this.holderType = holderType;
    }

    public Date getHoldDate() {
        return holdDate;
    }

    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}