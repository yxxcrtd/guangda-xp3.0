package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangxianjiu
 * @data 2022/11/24
 */
public class PbcTargetInfoErrordata extends BaseEntity {
    /**
     * 主键
     */
    private String uuid;

    /**
     * 内部产品代码
     */
    private String account;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 人行产品代码
     */
    private String pbcProdId;

    /**
     * 投资者名称
     */
    private String holderName;

    /**
     * 投资者类别
     */
    private String holderType;

    /**
     * 持有日期
     */
    private Date holdDate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 持有份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal qty;

    /**
     * 持有金额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 主键
     * @param uuid 主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 内部产品代码
     * @return 内部产品代码
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 内部产品代码
     * @param account 内部产品代码
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 取得 识别标识
     * @return 识别标识
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 识别标识
     * @param indentifyMark 识别标识
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
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
     * 取得 投资者名称
     * @return 投资者名称
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * 设置 投资者名称
     * @param holderName 投资者名称
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName == null ? null : holderName.trim();
    }

    /**
     * 取得 投资者类别
     * @return 投资者类别
     */
    public String getHolderType() {
        return holderType;
    }

    /**
     * 设置 投资者类别
     * @param holderType 投资者类别
     */
    public void setHolderType(String holderType) {
        this.holderType = holderType == null ? null : holderType.trim();
    }

    /**
     * 取得 持有日期
     * @return 持有日期
     */
    public Date getHoldDate() {
        return holdDate;
    }

    /**
     * 设置 持有日期
     * @param holdDate 持有日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
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
     * 取得 持有份额
     * @return 持有份额
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 持有份额
     * @param qty 持有份额
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 取得 持有金额
     * @return 持有金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 持有金额
     * @param amount 持有金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}