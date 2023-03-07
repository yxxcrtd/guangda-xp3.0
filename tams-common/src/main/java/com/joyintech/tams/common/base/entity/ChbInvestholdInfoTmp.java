package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ChbInvestholdInfoTmp extends BaseEntity {
    /**
     * 登记银行代码 
     */
    private String registBankCode;

    /**
     * 识别标识 
     */
    private String indentifyMark;

    /**
     * 合并后识别标识 
     */
    private String mergerIndentifyMark;

    /**
     * 产品登记编码 
     */
    private String prdRegistCode;

    /**
     * 持有日期 
     */
    private Date holdDate;

    /**
     * 币种 
     */
    private String ccy;

    /**
     * 金额 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 份额 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal qty;

    /**
     * 折算人民币金额（元）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cnyAmt;

    /**
     * 备注 
     */
    private String remark;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 处理状态（01 未合并，02 已合并，数据字典：SVR_OPERATE_STATUS）
     */
    private String operateStatus;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 机构代码 
     */
    private String orgCode;

    /**
     * 部门代码 
     */
    private String deptCode;

    /**
     * 经办人 
     */
    private String agent;

    /**
     * 产品代码 
     */
    private String prodid;

    /**
     * 接入检查结果 
     */
    private String interfCheckResult;

    /**
     * 代销机构代码 
     */
    private String agencyCode;

    /**
     * 账号开户所在地 
     */
    private String fundAccPlace;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 数据行号
     */
    private String dataRowNo;

    /**
     * 取得 登记银行代码 
     * @return 登记银行代码 
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 登记银行代码 
     * @param registBankCode 登记银行代码 
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
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
     * 取得 合并后识别标识 
     * @return 合并后识别标识 
     */
    public String getMergerIndentifyMark() {
        return mergerIndentifyMark;
    }

    /**
     * 设置 合并后识别标识 
     * @param mergerIndentifyMark 合并后识别标识 
     */
    public void setMergerIndentifyMark(String mergerIndentifyMark) {
        this.mergerIndentifyMark = mergerIndentifyMark == null ? null : mergerIndentifyMark.trim();
    }

    /**
     * 取得 产品登记编码 
     * @return 产品登记编码 
     */
    public String getPrdRegistCode() {
        return prdRegistCode;
    }

    /**
     * 设置 产品登记编码 
     * @param prdRegistCode 产品登记编码 
     */
    public void setPrdRegistCode(String prdRegistCode) {
        this.prdRegistCode = prdRegistCode == null ? null : prdRegistCode.trim();
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
     * 取得 金额 
     * @return 金额 
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 金额 
     * @param amount 金额 
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 份额 
     * @return 份额 
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 份额 
     * @param qty 份额 
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 取得 折算人民币金额（元）
     * @return 折算人民币金额（元）
     */
    public BigDecimal getCnyAmt() {
        return cnyAmt;
    }

    /**
     * 设置 折算人民币金额（元）
     * @param cnyAmt 折算人民币金额（元）
     */
    public void setCnyAmt(BigDecimal cnyAmt) {
        this.cnyAmt = cnyAmt;
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
     * 取得 处理状态（01 未合并，02 已合并，数据字典：SVR_OPERATE_STATUS）
     * @return 处理状态（01 未合并，02 已合并，数据字典：SVR_OPERATE_STATUS）
     */
    public String getOperateStatus() {
        return operateStatus;
    }

    /**
     * 设置 处理状态（01 未合并，02 已合并，数据字典：SVR_OPERATE_STATUS）
     * @param operateStatus 处理状态（01 未合并，02 已合并，数据字典：SVR_OPERATE_STATUS）
     */
    public void setOperateStatus(String operateStatus) {
        this.operateStatus = operateStatus == null ? null : operateStatus.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
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
     * 取得 经办人 
     * @return 经办人 
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置 经办人 
     * @param agent 经办人 
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    /**
     * 取得 产品代码 
     * @return 产品代码 
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 设置 产品代码 
     * @param prodid 产品代码 
     */
    public void setProdid(String prodid) {
        this.prodid = prodid == null ? null : prodid.trim();
    }

    /**
     * 取得 接入检查结果 
     * @return 接入检查结果 
     */
    public String getInterfCheckResult() {
        return interfCheckResult;
    }

    /**
     * 设置 接入检查结果 
     * @param interfCheckResult 接入检查结果 
     */
    public void setInterfCheckResult(String interfCheckResult) {
        this.interfCheckResult = interfCheckResult == null ? null : interfCheckResult.trim();
    }

    /**
     * 取得 代销机构代码 
     * @return 代销机构代码 
     */
    public String getAgencyCode() {
        return agencyCode;
    }

    /**
     * 设置 代销机构代码 
     * @param agencyCode 代销机构代码 
     */
    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode == null ? null : agencyCode.trim();
    }

    /**
     * 取得 账号开户所在地 
     * @return 账号开户所在地 
     */
    public String getFundAccPlace() {
        return fundAccPlace;
    }

    /**
     * 设置 账号开户所在地 
     * @param fundAccPlace 账号开户所在地 
     */
    public void setFundAccPlace(String fundAccPlace) {
        this.fundAccPlace = fundAccPlace == null ? null : fundAccPlace.trim();
    }

    /**
     * 取得 文件名称
     * @return 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名称
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 数据行号
     * @return 数据行号
     */
    public String getDataRowNo() {
        return dataRowNo;
    }

    /**
     * 设置 数据行号
     * @param dataRowNo 数据行号
     */
    public void setDataRowNo(String dataRowNo) {
        this.dataRowNo = dataRowNo == null ? null : dataRowNo.trim();
    }
}