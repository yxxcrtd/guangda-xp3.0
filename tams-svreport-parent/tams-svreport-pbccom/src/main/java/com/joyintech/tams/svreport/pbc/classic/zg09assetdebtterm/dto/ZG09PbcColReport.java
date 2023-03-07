package com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * 资产负债剩余主表
 * @TableName zg09_pbc_col_report
 */
@TableName("ZG09_PBC_COL_REPORT")
public class ZG09PbcColReport implements Serializable, ITableRow {

    private DecimalFormat df2 = new DecimalFormat("0.00");

    /**
     * 主键
     */
    @BusinessKey("REPORTUUID")
    private String reportuuid;

    /**
     * 期数
     */
    private String termid;

    /**
     * 报表类型:
     */
    private String reporttype;

    /**
     * 开始日期:
     */
    private Date startdate;

    /**
     * 结束日期:
     */
    private Date enddate;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 发行机构名称
     */
    private String issuePartyName;

    /**
     * 制表日期
     */
    private Date reportDate;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 中债报送截止日
     */
    private Date pbcSubmitDeadline;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 有效状态
     */
    private String status;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 产品品种
     */
    @DictFormat(DictCode.人行_产品种类)
    private String pbcProdType;

    /**
     * G000A_资产方-债务工具-总计
     */
    private BigDecimal g000a;

    /**
     * G000B_资产方-股权-总计
     */
    private BigDecimal g000b;

    /**
     * G000C_资产方-特定目的载体份额-总计
     */
    private BigDecimal g000c;

    /**
     * G000D_负债及权益方-债务工具-总计
     */
    private BigDecimal g000d;

    /**
     * G000E_负债及权益方-实收本金-总计
     */
    private BigDecimal g000e;

    /**
     * G100A_资产方-债务工具-1个月（含）以下
     */
    private BigDecimal g100a;

    /**
     * G100B_资产方-股权-1个月（含）以下
     */
    private BigDecimal g100b;

    /**
     * G100C_资产方-特定目的载体份额-1个月（含）以下
     */
    private BigDecimal g100c;

    /**
     * G100D_负债及权益方-债务工具-1个月（含）以下
     */
    private BigDecimal g100d;

    /**
     * G100E_负债及权益方-实收本金-1个月（含）以下
     */
    private BigDecimal g100e;

    /**
     * G200A_资产方-债务工具-1-3个月（含）
     */
    private BigDecimal g200a;

    /**
     * G200B_资产方-股权-1-3个月（含）
     */
    private BigDecimal g200b;

    /**
     * G200C_资产方-特定目的载体份额-1-3个月（含）
     */
    private BigDecimal g200c;

    /**
     * G200D_负债及权益方-债务工具-1-3个月（含）
     */
    private BigDecimal g200d;

    /**
     * G200E_负债及权益方-实收本金-1-3个月（含）
     */
    private BigDecimal g200e;

    /**
     * G300A_资产方-债务工具-3-6个月（含）
     */
    private BigDecimal g300a;

    /**
     * G300B_资产方-股权-3-6个月（含）
     */
    private BigDecimal g300b;

    /**
     * G300C_资产方-特定目的载体份额-3-6个月（含）
     */
    private BigDecimal g300c;

    /**
     * G300D_负债及权益方-债务工具-3-6个月（含）
     */
    private BigDecimal g300d;

    /**
     * G300E_负债及权益方-实收本金-3-6个月（含）
     */
    private BigDecimal g300e;

    /**
     * G400A_资产方-债务工具-6-12个月（含）
     */
    private BigDecimal g400a;

    /**
     * G400B_资产方-股权-6-12个月（含）
     */
    private BigDecimal g400b;

    /**
     * G400C_资产方-特定目的载体份额-6-12个月（含）
     */
    private BigDecimal g400c;

    /**
     * G400D_负债及权益方-债务工具-6-12个月（含）
     */
    private BigDecimal g400d;

    /**
     * G400E_负债及权益方-实收本金-6-12个月（含）
     */
    private BigDecimal g400e;

    /**
     * G500A_资产方-债务工具-1-2年（含）
     */
    private BigDecimal g500a;

    /**
     * G500B_资产方-股权-1-2年（含）
     */
    private BigDecimal g500b;

    /**
     * G500C_资产方-特定目的载体份额-1-2年（含）
     */
    private BigDecimal g500c;

    /**
     * G500D_负债及权益方-债务工具-1-2年（含）
     */
    private BigDecimal g500d;

    /**
     * G500E_负债及权益方-实收本金-1-2年（含）
     */
    private BigDecimal g500e;

    /**
     * G600A_资产方-债务工具-2-3年（含）
     */
    private BigDecimal g600a;

    /**
     * G600B_资产方-股权-2-3年（含）
     */
    private BigDecimal g600b;

    /**
     * G600C_资产方-特定目的载体份额-2-3年（含）
     */
    private BigDecimal g600c;

    /**
     * G600D_负债及权益方-债务工具-2-3年（含）
     */
    private BigDecimal g600d;

    /**
     * G600E_负债及权益方-实收本金-2-3年（含）
     */
    private BigDecimal g600e;

    /**
     * G700A_资产方-债务工具-3年以上
     */
    private BigDecimal g700a;

    /**
     * G700B_资产方-股权-3年以上
     */
    private BigDecimal g700b;

    /**
     * G700C_资产方-特定目的载体份额-3年以上
     */
    private BigDecimal g700c;

    /**
     * G700D_负债及权益方-债务工具-3年以上
     */
    private BigDecimal g700d;

    /**
     * G700E_负债及权益方-实收本金-3年以上
     */
    private BigDecimal g700e;

    /**
     * G800A_资产方-债务工具-无固定期限
     */
    private BigDecimal g800a;

    /**
     * G800B_资产方-股权-无固定期限
     */
    private BigDecimal g800b;

    /**
     * G800C_资产方-特定目的载体份额-无固定期限
     */
    private BigDecimal g800c;

    /**
     * G800D_负债及权益方-债务工具-无固定期限
     */
    private BigDecimal g800d;

    /**
     * G800E_负债及权益方-实收本金-无固定期限
     */
    private BigDecimal g800e;

    /**
     * G00001_附报-金融机构表内资产余额（本外币）
     */
    private BigDecimal g00001;

    /**
     * G00002_附报-金融机构表内金融资产余额（本外币）
     */
    private BigDecimal g00002;

    private static final long serialVersionUID = 1L;

    private String datuuid;

    public String getDatuuid() {
        return datuuid;
    }

    public void setDatuuid(String datuuid) {
        this.datuuid = datuuid;
    }

    /**
     * 主键
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 主键
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid;
    }

    /**
     * 期数
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 期数
     */
    public void setTermid(String termid) {
        this.termid = termid;
    }

    /**
     * 报表类型:
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型:
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    /**
     * 开始日期:
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 开始日期:
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 结束日期:
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 结束日期:
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    /**
     * 发行机构名称
     */
    public String getIssuePartyName() {
        return issuePartyName;
    }

    /**
     * 发行机构名称
     */
    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName;
    }

    /**
     * 制表日期
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * 制表日期
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 报送日期
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 中债报送截止日
     */
    public Date getPbcSubmitDeadline() {
        return pbcSubmitDeadline;
    }

    /**
     * 中债报送截止日
     */
    public void setPbcSubmitDeadline(Date pbcSubmitDeadline) {
        this.pbcSubmitDeadline = pbcSubmitDeadline;
    }

    /**
     * 行内报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 行内报送截止日
     */
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 有效状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 有效状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    /**
     * 创建部门
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    /**
     * 产品品种
     */
    public String getPbcProdType() {
        return pbcProdType;
    }

    /**
     * 产品品种
     */
    public void setPbcProdType(String pbcProdType) {
        this.pbcProdType = pbcProdType;
    }

    public String getStatusStr(){
        return SvrDataUtil.getNameFromDataDict(DictCode.报送数据状态, this.getStatus());
    }

    public String getSendStatusStr(){ return SvrDataUtil.getNameFromDataDict(DictCode.报送状态, this.getSendStatus());
    }

    /**
     * G000A_资产方-债务工具-总计
     */
    public BigDecimal getG000a() {
        return g000a;
    }

    /**
     * G000A_资产方-债务工具-总计
     */
    public void setG000a(BigDecimal g000a) {
        this.g000a = g000a;
    }

    /**
     * G000B_资产方-股权-总计
     */
    public BigDecimal getG000b() {
        return g000b;
    }

    /**
     * G000B_资产方-股权-总计
     */
    public void setG000b(BigDecimal g000b) {
        this.g000b = g000b;
    }

    /**
     * G000C_资产方-特定目的载体份额-总计
     */
    public BigDecimal getG000c() {
        return g000c;
    }

    /**
     * G000C_资产方-特定目的载体份额-总计
     */
    public void setG000c(BigDecimal g000c) {
        this.g000c = g000c;
    }

    /**
     * G000D_负债及权益方-债务工具-总计
     */
    public BigDecimal getG000d() {
        return g000d;
    }

    /**
     * G000D_负债及权益方-债务工具-总计
     */
    public void setG000d(BigDecimal g000d) {
        this.g000d = g000d;
    }

    /**
     * G000E_负债及权益方-实收本金-总计
     */
    public BigDecimal getG000e() {
        return g000e;
    }

    /**
     * G000E_负债及权益方-实收本金-总计
     */
    public void setG000e(BigDecimal g000e) {
        this.g000e = g000e;
    }

    /**
     * G100A_资产方-债务工具-1个月（含）以下
     */
    public BigDecimal getG100a() {
        return g100a;
    }

    /**
     * G100A_资产方-债务工具-1个月（含）以下
     */
    public void setG100a(BigDecimal g100a) {
        this.g100a = g100a;
    }

    /**
     * G100B_资产方-股权-1个月（含）以下
     */
    public BigDecimal getG100b() {
        return g100b;
    }

    /**
     * G100B_资产方-股权-1个月（含）以下
     */
    public void setG100b(BigDecimal g100b) {
        this.g100b = g100b;
    }

    /**
     * G100C_资产方-特定目的载体份额-1个月（含）以下
     */
    public BigDecimal getG100c() {
        return g100c;
    }

    /**
     * G100C_资产方-特定目的载体份额-1个月（含）以下
     */
    public void setG100c(BigDecimal g100c) {
        this.g100c = g100c;
    }

    /**
     * G100D_负债及权益方-债务工具-1个月（含）以下
     */
    public BigDecimal getG100d() {
        return g100d;
    }

    /**
     * G100D_负债及权益方-债务工具-1个月（含）以下
     */
    public void setG100d(BigDecimal g100d) {
        this.g100d = g100d;
    }

    /**
     * G100E_负债及权益方-实收本金-1个月（含）以下
     */
    public BigDecimal getG100e() {
        return g100e;
    }

    /**
     * G100E_负债及权益方-实收本金-1个月（含）以下
     */
    public void setG100e(BigDecimal g100e) {
        this.g100e = g100e;
    }

    /**
     * G200A_资产方-债务工具-1-3个月（含）
     */
    public BigDecimal getG200a() {
        return g200a;
    }

    /**
     * G200A_资产方-债务工具-1-3个月（含）
     */
    public void setG200a(BigDecimal g200a) {
        this.g200a = g200a;
    }

    /**
     * G200B_资产方-股权-1-3个月（含）
     */
    public BigDecimal getG200b() {
        return g200b;
    }

    /**
     * G200B_资产方-股权-1-3个月（含）
     */
    public void setG200b(BigDecimal g200b) {
        this.g200b = g200b;
    }

    /**
     * G200C_资产方-特定目的载体份额-1-3个月（含）
     */
    public BigDecimal getG200c() {
        return g200c;
    }

    /**
     * G200C_资产方-特定目的载体份额-1-3个月（含）
     */
    public void setG200c(BigDecimal g200c) {
        this.g200c = g200c;
    }

    /**
     * G200D_负债及权益方-债务工具-1-3个月（含）
     */
    public BigDecimal getG200d() {
        return g200d;
    }

    /**
     * G200D_负债及权益方-债务工具-1-3个月（含）
     */
    public void setG200d(BigDecimal g200d) {
        this.g200d = g200d;
    }

    /**
     * G200E_负债及权益方-实收本金-1-3个月（含）
     */
    public BigDecimal getG200e() {
        return g200e;
    }

    /**
     * G200E_负债及权益方-实收本金-1-3个月（含）
     */
    public void setG200e(BigDecimal g200e) {
        this.g200e = g200e;
    }

    /**
     * G300A_资产方-债务工具-3-6个月（含）
     */
    public BigDecimal getG300a() {
        return g300a;
    }

    /**
     * G300A_资产方-债务工具-3-6个月（含）
     */
    public void setG300a(BigDecimal g300a) {
        this.g300a = g300a;
    }

    /**
     * G300B_资产方-股权-3-6个月（含）
     */
    public BigDecimal getG300b() {
        return g300b;
    }

    /**
     * G300B_资产方-股权-3-6个月（含）
     */
    public void setG300b(BigDecimal g300b) {
        this.g300b = g300b;
    }

    /**
     * G300C_资产方-特定目的载体份额-3-6个月（含）
     */
    public BigDecimal getG300c() {
        return g300c;
    }

    /**
     * G300C_资产方-特定目的载体份额-3-6个月（含）
     */
    public void setG300c(BigDecimal g300c) {
        this.g300c = g300c;
    }

    /**
     * G300D_负债及权益方-债务工具-3-6个月（含）
     */
    public BigDecimal getG300d() {
        return g300d;
    }

    /**
     * G300D_负债及权益方-债务工具-3-6个月（含）
     */
    public void setG300d(BigDecimal g300d) {
        this.g300d = g300d;
    }

    /**
     * G300E_负债及权益方-实收本金-3-6个月（含）
     */
    public BigDecimal getG300e() {
        return g300e;
    }

    /**
     * G300E_负债及权益方-实收本金-3-6个月（含）
     */
    public void setG300e(BigDecimal g300e) {
        this.g300e = g300e;
    }

    /**
     * G400A_资产方-债务工具-6-12个月（含）
     */
    public BigDecimal getG400a() {
        return g400a;
    }

    /**
     * G400A_资产方-债务工具-6-12个月（含）
     */
    public void setG400a(BigDecimal g400a) {
        this.g400a = g400a;
    }

    /**
     * G400B_资产方-股权-6-12个月（含）
     */
    public BigDecimal getG400b() {
        return g400b;
    }

    /**
     * G400B_资产方-股权-6-12个月（含）
     */
    public void setG400b(BigDecimal g400b) {
        this.g400b = g400b;
    }

    /**
     * G400C_资产方-特定目的载体份额-6-12个月（含）
     */
    public BigDecimal getG400c() {
        return g400c;
    }

    /**
     * G400C_资产方-特定目的载体份额-6-12个月（含）
     */
    public void setG400c(BigDecimal g400c) {
        this.g400c = g400c;
    }

    /**
     * G400D_负债及权益方-债务工具-6-12个月（含）
     */
    public BigDecimal getG400d() {
        return g400d;
    }

    /**
     * G400D_负债及权益方-债务工具-6-12个月（含）
     */
    public void setG400d(BigDecimal g400d) {
        this.g400d = g400d;
    }

    /**
     * G400E_负债及权益方-实收本金-6-12个月（含）
     */
    public BigDecimal getG400e() {
        return g400e;
    }

    /**
     * G400E_负债及权益方-实收本金-6-12个月（含）
     */
    public void setG400e(BigDecimal g400e) {
        this.g400e = g400e;
    }

    /**
     * G500A_资产方-债务工具-1-2年（含）
     */
    public BigDecimal getG500a() {
        return g500a;
    }

    /**
     * G500A_资产方-债务工具-1-2年（含）
     */
    public void setG500a(BigDecimal g500a) {
        this.g500a = g500a;
    }

    /**
     * G500B_资产方-股权-1-2年（含）
     */
    public BigDecimal getG500b() {
        return g500b;
    }

    /**
     * G500B_资产方-股权-1-2年（含）
     */
    public void setG500b(BigDecimal g500b) {
        this.g500b = g500b;
    }

    /**
     * G500C_资产方-特定目的载体份额-1-2年（含）
     */
    public BigDecimal getG500c() {
        return g500c;
    }

    /**
     * G500C_资产方-特定目的载体份额-1-2年（含）
     */
    public void setG500c(BigDecimal g500c) {
        this.g500c = g500c;
    }

    /**
     * G500D_负债及权益方-债务工具-1-2年（含）
     */
    public BigDecimal getG500d() {
        return g500d;
    }

    /**
     * G500D_负债及权益方-债务工具-1-2年（含）
     */
    public void setG500d(BigDecimal g500d) {
        this.g500d = g500d;
    }

    /**
     * G500E_负债及权益方-实收本金-1-2年（含）
     */
    public BigDecimal getG500e() {
        return g500e;
    }

    /**
     * G500E_负债及权益方-实收本金-1-2年（含）
     */
    public void setG500e(BigDecimal g500e) {
        this.g500e = g500e;
    }

    /**
     * G600A_资产方-债务工具-2-3年（含）
     */
    public BigDecimal getG600a() {
        return g600a;
    }

    /**
     * G600A_资产方-债务工具-2-3年（含）
     */
    public void setG600a(BigDecimal g600a) {
        this.g600a = g600a;
    }

    /**
     * G600B_资产方-股权-2-3年（含）
     */
    public BigDecimal getG600b() {
        return g600b;
    }

    /**
     * G600B_资产方-股权-2-3年（含）
     */
    public void setG600b(BigDecimal g600b) {
        this.g600b = g600b;
    }

    /**
     * G600C_资产方-特定目的载体份额-2-3年（含）
     */
    public BigDecimal getG600c() {
        return g600c;
    }

    /**
     * G600C_资产方-特定目的载体份额-2-3年（含）
     */
    public void setG600c(BigDecimal g600c) {
        this.g600c = g600c;
    }

    /**
     * G600D_负债及权益方-债务工具-2-3年（含）
     */
    public BigDecimal getG600d() {
        return g600d;
    }

    /**
     * G600D_负债及权益方-债务工具-2-3年（含）
     */
    public void setG600d(BigDecimal g600d) {
        this.g600d = g600d;
    }

    /**
     * G600E_负债及权益方-实收本金-2-3年（含）
     */
    public BigDecimal getG600e() {
        return g600e;
    }

    /**
     * G600E_负债及权益方-实收本金-2-3年（含）
     */
    public void setG600e(BigDecimal g600e) {
        this.g600e = g600e;
    }

    /**
     * G700A_资产方-债务工具-3年以上
     */
    public BigDecimal getG700a() {
        return g700a;
    }

    /**
     * G700A_资产方-债务工具-3年以上
     */
    public void setG700a(BigDecimal g700a) {
        this.g700a = g700a;
    }

    /**
     * G700B_资产方-股权-3年以上
     */
    public BigDecimal getG700b() {
        return g700b;
    }

    /**
     * G700B_资产方-股权-3年以上
     */
    public void setG700b(BigDecimal g700b) {
        this.g700b = g700b;
    }

    /**
     * G700C_资产方-特定目的载体份额-3年以上
     */
    public BigDecimal getG700c() {
        return g700c;
    }

    /**
     * G700C_资产方-特定目的载体份额-3年以上
     */
    public void setG700c(BigDecimal g700c) {
        this.g700c = g700c;
    }

    /**
     * G700D_负债及权益方-债务工具-3年以上
     */
    public BigDecimal getG700d() {
        return g700d;
    }

    /**
     * G700D_负债及权益方-债务工具-3年以上
     */
    public void setG700d(BigDecimal g700d) {
        this.g700d = g700d;
    }

    /**
     * G700E_负债及权益方-实收本金-3年以上
     */
    public BigDecimal getG700e() {
        return g700e;
    }

    /**
     * G700E_负债及权益方-实收本金-3年以上
     */
    public void setG700e(BigDecimal g700e) {
        this.g700e = g700e;
    }

    /**
     * G800A_资产方-债务工具-无固定期限
     */
    public BigDecimal getG800a() {
        return g800a;
    }

    /**
     * G800A_资产方-债务工具-无固定期限
     */
    public void setG800a(BigDecimal g800a) {
        this.g800a = g800a;
    }

    /**
     * G800B_资产方-股权-无固定期限
     */
    public BigDecimal getG800b() {
        return g800b;
    }

    /**
     * G800B_资产方-股权-无固定期限
     */
    public void setG800b(BigDecimal g800b) {
        this.g800b = g800b;
    }

    /**
     * G800C_资产方-特定目的载体份额-无固定期限
     */
    public BigDecimal getG800c() {
        return g800c;
    }

    /**
     * G800C_资产方-特定目的载体份额-无固定期限
     */
    public void setG800c(BigDecimal g800c) {
        this.g800c = g800c;
    }

    /**
     * G800D_负债及权益方-债务工具-无固定期限
     */
    public BigDecimal getG800d() {
        return g800d;
    }

    /**
     * G800D_负债及权益方-债务工具-无固定期限
     */
    public void setG800d(BigDecimal g800d) {
        this.g800d = g800d;
    }

    /**
     * G800E_负债及权益方-实收本金-无固定期限
     */
    public BigDecimal getG800e() {
        return g800e;
    }

    /**
     * G800E_负债及权益方-实收本金-无固定期限
     */
    public void setG800e(BigDecimal g800e) {
        this.g800e = g800e;
    }

    /**
     * G00001_附报-金融机构表内资产余额（本外币）
     */
    public BigDecimal getG00001() {
        return g00001;
    }

    /**
     * G00001_附报-金融机构表内资产余额（本外币）
     */
    public void setG00001(BigDecimal g00001) {
        this.g00001 = g00001;
    }

    /**
     * G00002_附报-金融机构表内金融资产余额（本外币）
     */
    public BigDecimal getG00002() {
        return g00002;
    }

    /**
     * G00002_附报-金融机构表内金融资产余额（本外币）
     */
    public void setG00002(BigDecimal g00002) {
        this.g00002 = g00002;
    }

    public String getG000aStr() {
        return g000a==null?"":df2.format(g000a);
    }

    public String getG000bStr() {
        return g000b==null?"":df2.format(g000b);
    }

    public String getG000cStr() {
        return g000c==null?"":df2.format(g000c);
    }

    public String getG000dStr() {
        return g000d==null?"":df2.format(g000d);
    }

    public String getG000eStr() {
        return g000e==null?"":df2.format(g000e);
    }

    public String getG100aStr() {
        return g100a==null?"":df2.format(g100a);
    }

    public String getG100bStr() {
        return g100b==null?"":df2.format(g100b);
    }

    public String getG100cStr() {
        return g100c==null?"":df2.format(g100c);
    }

    public String getG100dStr() {
        return g100d==null?"":df2.format(g100d);
    }

    public String getG100eStr() {
        return g100e==null?"":df2.format(g100e);
    }

    public String getG200aStr() {
        return g200a==null?"":df2.format(g200a);
    }

    public String getG200bStr() {
        return g200b==null?"":df2.format(g200b);
    }

    public String getG200cStr() {
        return g200c==null?"":df2.format(g200c);
    }

    public String getG200dStr() {
        return g200d==null?"":df2.format(g200d);
    }

    public String getG200eStr() {
        return g200e==null?"":df2.format(g200e);
    }

    public String getG300aStr() {
        return g300a==null?"":df2.format(g300a);
    }

    public String getG300bStr() {
        return g300b==null?"":df2.format(g300b);
    }

    public String getG300cStr() {
        return g300c==null?"":df2.format(g300c);
    }

    public String getG300dStr() {
        return g300d==null?"":df2.format(g300d);
    }

    public String getG300eStr() {
        return g300e==null?"":df2.format(g300e);
    }

    public String getG400aStr() {
        return g400a==null?"":df2.format(g400a);
    }

    public String getG400bStr() {
        return g400b==null?"":df2.format(g400b);
    }

    public String getG400cStr() {
        return g400c==null?"":df2.format(g400c);
    }

    public String getG400dStr() {
        return g400d==null?"":df2.format(g400d);
    }

    public String getG400eStr() {
        return g400e==null?"":df2.format(g400e);
    }

    public String getG500aStr() {
        return g500a==null?"":df2.format(g500a);
    }

    public String getG500bStr() {
        return g500b==null?"":df2.format(g500b);
    }

    public String getG500cStr() {
        return g500c==null?"":df2.format(g500c);
    }

    public String getG500dStr() {
        return g500d==null?"":df2.format(g500d);
    }

    public String getG500eStr() {
        return g500e==null?"":df2.format(g500e);
    }

    public String getG600aStr() {
        return g600a==null?"":df2.format(g600a);
    }

    public String getG600bStr() {
        return g600b==null?"":df2.format(g600b);
    }

    public String getG600cStr() {
        return g600c==null?"":df2.format(g600c);
    }

    public String getG600dStr() {
        return g600d==null?"":df2.format(g600d);
    }

    public String getG600eStr() {
        return g600e==null?"":df2.format(g600e);
    }

    public String getG700aStr() {
        return g700a==null?"":df2.format(g700a);
    }

    public String getG700bStr() {
        return g700b==null?"":df2.format(g700b);
    }

    public String getG700cStr() {
        return g700c==null?"":df2.format(g700c);
    }

    public String getG700dStr() {
        return g700d==null?"":df2.format(g700d);
    }

    public String getG700eStr() {
        return g700e==null?"":df2.format(g700e);
    }

    public String getG800aStr() {
        return g800a==null?"":df2.format(g800a);
    }

    public String getG800bStr() {
        return g800b==null?"":df2.format(g800b);
    }

    public String getG800cStr() {
        return g800c==null?"":df2.format(g800c);
    }

    public String getG800dStr() {
        return g800d==null?"":df2.format(g800d);
    }

    public String getG800eStr() {
        return g800e==null?"":df2.format(g800e);
    }

    public String getG00001Str() {
        return g00001==null?"":df2.format(g00001);
    }

    public String getG00002Str() {
        return g00002==null?"":df2.format(g00002);
    }

    public String getPbcProdTypeStr() {
        return getPbcProdType() == null ? "" : SvrDataUtil.getZZDictCodeAndName(DictCode.人行_产品种类, getPbcProdType());
    }

    private String dataKey;

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /***数据主键*  发行机构代码;统计年月*/
    public String getDataKey(){
        return "发行机构代码:"+this.getIssuePartyId()+";统计年月:"+ this.getTermid();
    }

}