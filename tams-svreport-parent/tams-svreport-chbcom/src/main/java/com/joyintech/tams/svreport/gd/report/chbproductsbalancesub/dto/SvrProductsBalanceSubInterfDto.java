package com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangxianjiu
 * @date 2022/03/31
 */
public class SvrProductsBalanceSubInterfDto extends BaseEntity {
    /**
     * 主键uuid
     */
    private String registSeqno;

    /**
     * 数据批次
     */
    private String statusBatch;

    /**
     * 理财产品编号
     */
    private String account;

    /**
     * 客户编号
     */
    private String indentifyMark;

    /**
     * 客户账号
     */
    private String fundAcc;

    /**
     * 理财账号
     */
    private String finAcc;

    /**
     * 折人民币账户余额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal amountCny;

    /**
     * 机构编号
     */
    private String isConsign;

    /**
     * 银行标识号
     */
    private String bankMark;

    /**
     * 数据时点
     */
    private Date dateStatus;

    /**
     * 组别
     */
    private String groupId;

    /**
     * 机构代码
     */
    private String orgCode;


    /**
     * 取得 主键uuid
     * @return 主键uuid
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 主键uuid
     * @param registSeqno 主键uuid
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 取得 数据批次
     * @return 数据批次
     */
    public String getStatusBatch() {
        return statusBatch;
    }

    /**
     * 设置 数据批次
     * @param statusBatch 数据批次
     */
    public void setStatusBatch(String statusBatch) {
        this.statusBatch = statusBatch == null ? null : statusBatch.trim();
    }

    /**
     * 取得 理财产品编号
     * @return 理财产品编号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 理财产品编号
     * @param account 理财产品编号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 取得 客户编号
     * @return 客户编号
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 客户编号
     * @param indentifyMark 客户编号
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
    }

    /**
     * 取得 客户账号
     * @return 客户账号
     */
    public String getFundAcc() {
        return fundAcc;
    }

    /**
     * 设置 客户账号
     * @param fundAcc 客户账号
     */
    public void setFundAcc(String fundAcc) {
        this.fundAcc = fundAcc == null ? null : fundAcc.trim();
    }

    /**
     * 取得 理财账号
     * @return 理财账号
     */
    public String getFinAcc() {
        return finAcc;
    }

    /**
     * 设置 理财账号
     * @param finAcc 理财账号
     */
    public void setFinAcc(String finAcc) {
        this.finAcc = finAcc == null ? null : finAcc.trim();
    }

    /**
     * 取得 折人民币账户余额
     * @return 折人民币账户余额
     */
    public BigDecimal getAmountCny() {
        return amountCny;
    }

    /**
     * 设置 折人民币账户余额
     * @param amountCny 折人民币账户余额
     */
    public void setAmountCny(BigDecimal amountCny) {
        this.amountCny = amountCny;
    }

    /**
     * 取得 机构编号
     * @return 机构编号
     */
    public String getIsConsign() {
        return isConsign;
    }

    /**
     * 设置 机构编号
     * @param isConsign 机构编号
     */
    public void setIsConsign(String isConsign) {
        this.isConsign = isConsign == null ? null : isConsign.trim();
    }

    /**
     * 取得 银行标识号
     * @return 银行标识号
     */
    public String getBankMark() {
        return bankMark;
    }

    /**
     * 设置 银行标识号
     * @param bankMark 银行标识号
     */
    public void setBankMark(String bankMark) {
        this.bankMark = bankMark == null ? null : bankMark.trim();
    }

    /**
     * 取得 数据时点
     * @return 数据时点
     */
    public Date getDateStatus() {
        return dateStatus;
    }

    /**
     * 设置 数据时点
     * @param dateStatus 数据时点
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDateStatus(Date dateStatus) {
        this.dateStatus = dateStatus;
    }

    /**
     * 取得 组别
     * @return 组别
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置 组别
     * @param groupId 组别
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
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
        this.orgCode = orgCode == null ? null : "";
    }


}