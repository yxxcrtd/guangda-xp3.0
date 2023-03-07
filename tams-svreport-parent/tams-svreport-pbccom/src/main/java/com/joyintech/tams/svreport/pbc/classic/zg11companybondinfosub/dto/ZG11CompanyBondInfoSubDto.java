package com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.PbcColReport;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@TableName("ZG11_PBC_COL_REPORT")
public class ZG11CompanyBondInfoSubDto extends PbcColReport implements ITableRow {

    private DecimalFormat df2 = new DecimalFormat("0.00");

    private String format(BigDecimal amt){
        return amt == null ? "":df2.format(amt);
    }

    /**
     * 主键
     */
    private String reportuuid;

    /**
     * 期数
     */
    @BusinessKey("TERMID")
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
     * 制表日期
     */
    private Date reportDate;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 人行报送截止日
     */
    private Date pbcSubmitDeadline;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 返回信息
     */
    private String submitFeedbackStatus;

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
     * 发行机构代码
     */
    @BusinessKey("ISSUE_PARTY_ID")
    private String issuePartyId;

    /**
     * 发行机构名称
     */
    private String issuePartyName;

    /**
     * 产品品种
     */
    @BusinessKey("PROD_VARIETY")
    private String prodVariety;

    /**
     * c00000_企业债券余额总计
     */
    private BigDecimal c00000;

    /**
     * c01000_企业债券余额总计-大型企业
     */
    private BigDecimal c01000;

    /**
     * c02000_企业债券余额总计-中型企业
     */
    private BigDecimal c02000;

    /**
     * c03000_企业债券余额总计-小型企业
     */
    private BigDecimal c03000;

    /**
     * c04000_企业债券余额总计-微型企业
     */
    private BigDecimal c04000;

    /**
     * c10000_农、林、牧、渔业
     */
    private BigDecimal c10000;

    /**
     * c11000_农、林、牧、渔业-大型企业
     */
    private BigDecimal c11000;

    /**
     * c12000_农、林、牧、渔业-中型企业
     */
    private BigDecimal c12000;

    /**
     * c13000_农、林、牧、渔业-小型企业
     */
    private BigDecimal c13000;

    /**
     * c14000_农、林、牧、渔业-微型企业
     */
    private BigDecimal c14000;

    /**
     * c20000_采矿业
     */
    private BigDecimal c20000;

    /**
     * c21000_采矿业-大型企业
     */
    private BigDecimal c21000;

    /**
     * c22000_采矿业-中型企业
     */
    private BigDecimal c22000;

    /**
     * c23000_采矿业-小型企业
     */
    private BigDecimal c23000;

    /**
     * c24000_采矿业-微型企业
     */
    private BigDecimal c24000;

    /**
     * c30000_制造业
     */
    private BigDecimal c30000;

    /**
     * c31000_制造业-大型企业
     */
    private BigDecimal c31000;

    /**
     * c32000_制造业-中型企业
     */
    private BigDecimal c32000;

    /**
     * c33000_制造业-小型企业
     */
    private BigDecimal c33000;

    /**
     * c34000_制造业-微型企业
     */
    private BigDecimal c34000;

    /**
     * c40000_电力、热力、燃气及水生产和供应业
     */
    private BigDecimal c40000;

    /**
     * c41000_电力、热力、燃气及水生产和供应业-大型企业
     */
    private BigDecimal c41000;

    /**
     * c42000_电力、热力、燃气及水生产和供应业-中型企业
     */
    private BigDecimal c42000;

    /**
     * c43000_电力、热力、燃气及水生产和供应业-小型企业
     */
    private BigDecimal c43000;

    /**
     * c44000_电力、热力、燃气及水生产和供应业-微型企业
     */
    private BigDecimal c44000;

    /**
     * c50000_建筑业
     */
    private BigDecimal c50000;

    /**
     * c51000_建筑业-大型企业
     */
    private BigDecimal c51000;

    /**
     * c52000_建筑业-中型企业
     */
    private BigDecimal c52000;

    /**
     * c53000_建筑业-小型企业
     */
    private BigDecimal c53000;

    /**
     * c54000_建筑业-微型企业
     */
    private BigDecimal c54000;

    /**
     * c60000_批发和零售业
     */
    private BigDecimal c60000;

    /**
     * c61000_批发和零售业-大型企业
     */
    private BigDecimal c61000;

    /**
     * c62000_批发和零售业-中型企业
     */
    private BigDecimal c62000;

    /**
     * c63000_批发和零售业-小型企业
     */
    private BigDecimal c63000;

    /**
     * c64000_批发和零售业-微型企业
     */
    private BigDecimal c64000;

    /**
     * c70000_交通运输、仓储和邮政业
     */
    private BigDecimal c70000;

    /**
     * c71000_交通运输、仓储和邮政业-大型企业
     */
    private BigDecimal c71000;

    /**
     * c72000_交通运输、仓储和邮政业-中型企业
     */
    private BigDecimal c72000;

    /**
     * c73000_交通运输、仓储和邮政业-小型企业
     */
    private BigDecimal c73000;

    /**
     * c74000_交通运输、仓储和邮政业-微型企业
     */
    private BigDecimal c74000;

    /**
     * c80000_住宿和餐饮业
     */
    private BigDecimal c80000;

    /**
     * c81000_住宿和餐饮业-大型企业
     */
    private BigDecimal c81000;

    /**
     * c82000_住宿和餐饮业-中型企业
     */
    private BigDecimal c82000;

    /**
     * c83000_住宿和餐饮业-小型企业
     */
    private BigDecimal c83000;

    /**
     * c84000_住宿和餐饮业-微型企业
     */
    private BigDecimal c84000;

    /**
     * c90000_信息传输、软件和信息技术服务业
     */
    private BigDecimal c90000;

    /**
     * c91000_信息传输、软件和信息技术服务业-大型企业
     */
    private BigDecimal c91000;

    /**
     * c92000_信息传输、软件和信息技术服务业-中型企业
     */
    private BigDecimal c92000;

    /**
     * c93000_信息传输、软件和信息技术服务业-小型企业
     */
    private BigDecimal c93000;

    /**
     * c94000_信息传输、软件和信息技术服务业-微型企业
     */
    private BigDecimal c94000;

    /**
     * ca0000_金融业
     */
    private BigDecimal ca0000;

    /**
     * ca1000_金融业-大型企业
     */
    private BigDecimal ca1000;

    /**
     * ca2000_金融业-中型企业
     */
    private BigDecimal ca2000;

    /**
     * ca3000_金融业-小型企业
     */
    private BigDecimal ca3000;

    /**
     * ca4000_金融业-微型企业
     */
    private BigDecimal ca4000;

    /**
     * cb0000_房地产业
     */
    private BigDecimal cb0000;

    /**
     * cb1000_房地产业-大型企业
     */
    private BigDecimal cb1000;

    /**
     * cb2000_房地产业-中型企业
     */
    private BigDecimal cb2000;

    /**
     * cb3000_房地产业-小型企业
     */
    private BigDecimal cb3000;

    /**
     * cb4000_房地产业-微型企业
     */
    private BigDecimal cb4000;

    /**
     * cc0000_租赁和商务服务业
     */
    private BigDecimal cc0000;

    /**
     * cc1000_租赁和商务服务业-大型企业
     */
    private BigDecimal cc1000;

    /**
     * cc2000_租赁和商务服务业-中型企业
     */
    private BigDecimal cc2000;

    /**
     * cc3000_租赁和商务服务业-小型企业
     */
    private BigDecimal cc3000;

    /**
     * cc4000_租赁和商务服务业-微型企业
     */
    private BigDecimal cc4000;

    /**
     * cd0000_科学研究和技术服务业
     */
    private BigDecimal cd0000;

    /**
     * cd1000_科学研究和技术服务业-大型企业
     */
    private BigDecimal cd1000;

    /**
     * cd2000_科学研究和技术服务业-中型企业
     */
    private BigDecimal cd2000;

    /**
     * cd3000_科学研究和技术服务业-小型企业
     */
    private BigDecimal cd3000;

    /**
     * cd4000_科学研究和技术服务业-微型企业
     */
    private BigDecimal cd4000;

    /**
     * ce0000_水利、环境和公共设施管理业
     */
    private BigDecimal ce0000;

    /**
     * ce1000_水利、环境和公共设施管理业-大型企业
     */
    private BigDecimal ce1000;

    /**
     * ce2000_水利、环境和公共设施管理业-中型企业
     */
    private BigDecimal ce2000;

    /**
     * ce3000_水利、环境和公共设施管理业-小型企业
     */
    private BigDecimal ce3000;

    /**
     * ce4000_水利、环境和公共设施管理业-微型企业
     */
    private BigDecimal ce4000;

    /**
     * cf0000_居民服务、修理和其他服务业
     */
    private BigDecimal cf0000;

    /**
     * cf1000_居民服务、修理和其他服务业-大型企业
     */
    private BigDecimal cf1000;

    /**
     * cf2000_居民服务、修理和其他服务业-中型企业
     */
    private BigDecimal cf2000;

    /**
     * cf3000_居民服务、修理和其他服务业-小型企业
     */
    private BigDecimal cf3000;

    /**
     * cf4000_居民服务、修理和其他服务业-微型企业
     */
    private BigDecimal cf4000;

    /**
     * cg0000_教育
     */
    private BigDecimal cg0000;

    /**
     * cg1000_教育-大型企业
     */
    private BigDecimal cg1000;

    /**
     * cg2000_教育-中型企业
     */
    private BigDecimal cg2000;

    /**
     * cg3000_教育-小型企业
     */
    private BigDecimal cg3000;

    /**
     * cg3000_教育-小型企业
     */
    private BigDecimal cg4000;

    /**
     * ch0000_卫生和社会工作
     */
    private BigDecimal ch0000;

    /**
     * ch1000_卫生和社会工作-大型企业
     */
    private BigDecimal ch1000;

    /**
     * ch2000_卫生和社会工作-中型企业
     */
    private BigDecimal ch2000;

    /**
     * ch3000_卫生和社会工作-小型企业
     */
    private BigDecimal ch3000;

    /**
     * ch4000_卫生和社会工作-微型企业
     */
    private BigDecimal ch4000;

    /**
     * ci0000_文化、体育和娱乐业
     */
    private BigDecimal ci0000;

    /**
     * ci1000_文化、体育和娱乐业-大型企业
     */
    private BigDecimal ci1000;

    /**
     * ci2000_文化、体育和娱乐业-中型企业
     */
    private BigDecimal ci2000;

    /**
     * ci3000_文化、体育和娱乐业-小型企业
     */
    private BigDecimal ci3000;

    /**
     * ci4000_文化、体育和娱乐业-微型企业
     */
    private BigDecimal ci4000;

    /**
     * cj0000_公共管理、社会保障和社会组织
     */
    private BigDecimal cj0000;

    /**
     * cj1000_公共管理、社会保障和社会组织-大型企业
     */
    private BigDecimal cj1000;

    /**
     * cj2000_公共管理、社会保障和社会组织-中型企业
     */
    private BigDecimal cj2000;

    /**
     * cj3000_公共管理、社会保障和社会组织-小型企业
     */
    private BigDecimal cj3000;

    /**
     * cj4000_公共管理、社会保障和社会组织-微型企业
     */
    private BigDecimal cj4000;

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

    public Date getPbcSubmitDeadline() {
        return pbcSubmitDeadline;
    }

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
     * 返回信息
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 返回信息
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus;
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

    public String getSendStatusStr() {
        return SvrDataUtil.getNameFromDataDict(DictCode.报送状态,sendStatus);
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
     * 产品品种
     */
    public String getProdVariety() {
        return prodVariety;
    }
    public String getProdVarietyStr() {
        if(StringUtil.isEmpty(prodVariety)){
            return "";
        }
        return prodVariety+" "+SvrDataUtil.getNameFromDataDict(DictCode.人行_产品种类,prodVariety);
    }

    /**
     * 产品品种
     */
    public void setProdVariety(String prodVariety) {
        this.prodVariety = prodVariety;
    }

    /**
     * c00000_企业债券余额总计
     */
    public BigDecimal getC00000() {
        return c00000;
    }

    /**
     * c00000_企业债券余额总计
     */
    public void setC00000(BigDecimal c00000) {
        this.c00000 = c00000;
    }

    /**
     * c01000_企业债券余额总计-大型企业
     */
    public BigDecimal getC01000() {
        return c01000;
    }

    /**
     * c01000_企业债券余额总计-大型企业
     */
    public void setC01000(BigDecimal c01000) {
        this.c01000 = c01000;
    }

    /**
     * c02000_企业债券余额总计-中型企业
     */
    public BigDecimal getC02000() {
        return c02000;
    }

    /**
     * c02000_企业债券余额总计-中型企业
     */
    public void setC02000(BigDecimal c02000) {
        this.c02000 = c02000;
    }

    /**
     * c03000_企业债券余额总计-小型企业
     */
    public BigDecimal getC03000() {
        return c03000;
    }

    /**
     * c03000_企业债券余额总计-小型企业
     */
    public void setC03000(BigDecimal c03000) {
        this.c03000 = c03000;
    }

    /**
     * c04000_企业债券余额总计-微型企业
     */
    public BigDecimal getC04000() {
        return c04000;
    }

    /**
     * c04000_企业债券余额总计-微型企业
     */
    public void setC04000(BigDecimal c04000) {
        this.c04000 = c04000;
    }

    /**
     * c10000_农、林、牧、渔业
     */
    public BigDecimal getC10000() {
        return c10000;
    }

    /**
     * c10000_农、林、牧、渔业
     */
    public void setC10000(BigDecimal c10000) {
        this.c10000 = c10000;
    }

    /**
     * c11000_农、林、牧、渔业-大型企业
     */
    public BigDecimal getC11000() {
        return c11000;
    }

    /**
     * c11000_农、林、牧、渔业-大型企业
     */
    public void setC11000(BigDecimal c11000) {
        this.c11000 = c11000;
    }

    /**
     * c12000_农、林、牧、渔业-中型企业
     */
    public BigDecimal getC12000() {
        return c12000;
    }

    /**
     * c12000_农、林、牧、渔业-中型企业
     */
    public void setC12000(BigDecimal c12000) {
        this.c12000 = c12000;
    }

    /**
     * c13000_农、林、牧、渔业-小型企业
     */
    public BigDecimal getC13000() {
        return c13000;
    }

    /**
     * c13000_农、林、牧、渔业-小型企业
     */
    public void setC13000(BigDecimal c13000) {
        this.c13000 = c13000;
    }

    /**
     * c14000_农、林、牧、渔业-微型企业
     */
    public BigDecimal getC14000() {
        return c14000;
    }

    /**
     * c14000_农、林、牧、渔业-微型企业
     */
    public void setC14000(BigDecimal c14000) {
        this.c14000 = c14000;
    }

    /**
     * c20000_采矿业
     */
    public BigDecimal getC20000() {
        return c20000;
    }

    /**
     * c20000_采矿业
     */
    public void setC20000(BigDecimal c20000) {
        this.c20000 = c20000;
    }

    /**
     * c21000_采矿业-大型企业
     */
    public BigDecimal getC21000() {
        return c21000;
    }

    /**
     * c21000_采矿业-大型企业
     */
    public void setC21000(BigDecimal c21000) {
        this.c21000 = c21000;
    }

    /**
     * c22000_采矿业-中型企业
     */
    public BigDecimal getC22000() {
        return c22000;
    }

    /**
     * c22000_采矿业-中型企业
     */
    public void setC22000(BigDecimal c22000) {
        this.c22000 = c22000;
    }

    /**
     * c23000_采矿业-小型企业
     */
    public BigDecimal getC23000() {
        return c23000;
    }

    /**
     * c23000_采矿业-小型企业
     */
    public void setC23000(BigDecimal c23000) {
        this.c23000 = c23000;
    }

    /**
     * c24000_采矿业-微型企业
     */
    public BigDecimal getC24000() {
        return c24000;
    }

    /**
     * c24000_采矿业-微型企业
     */
    public void setC24000(BigDecimal c24000) {
        this.c24000 = c24000;
    }

    /**
     * c30000_制造业
     */
    public BigDecimal getC30000() {
        return c30000;
    }

    /**
     * c30000_制造业
     */
    public void setC30000(BigDecimal c30000) {
        this.c30000 = c30000;
    }

    /**
     * c31000_制造业-大型企业
     */
    public BigDecimal getC31000() {
        return c31000;
    }

    /**
     * c31000_制造业-大型企业
     */
    public void setC31000(BigDecimal c31000) {
        this.c31000 = c31000;
    }

    /**
     * c32000_制造业-中型企业
     */
    public BigDecimal getC32000() {
        return c32000;
    }

    /**
     * c32000_制造业-中型企业
     */
    public void setC32000(BigDecimal c32000) {
        this.c32000 = c32000;
    }

    /**
     * c33000_制造业-小型企业
     */
    public BigDecimal getC33000() {
        return c33000;
    }

    /**
     * c33000_制造业-小型企业
     */
    public void setC33000(BigDecimal c33000) {
        this.c33000 = c33000;
    }

    /**
     * c34000_制造业-微型企业
     */
    public BigDecimal getC34000() {
        return c34000;
    }

    /**
     * c34000_制造业-微型企业
     */
    public void setC34000(BigDecimal c34000) {
        this.c34000 = c34000;
    }

    /**
     * c40000_电力、热力、燃气及水生产和供应业
     */
    public BigDecimal getC40000() {
        return c40000;
    }

    /**
     * c40000_电力、热力、燃气及水生产和供应业
     */
    public void setC40000(BigDecimal c40000) {
        this.c40000 = c40000;
    }

    /**
     * c41000_电力、热力、燃气及水生产和供应业-大型企业
     */
    public BigDecimal getC41000() {
        return c41000;
    }

    /**
     * c41000_电力、热力、燃气及水生产和供应业-大型企业
     */
    public void setC41000(BigDecimal c41000) {
        this.c41000 = c41000;
    }

    /**
     * c42000_电力、热力、燃气及水生产和供应业-中型企业
     */
    public BigDecimal getC42000() {
        return c42000;
    }

    /**
     * c42000_电力、热力、燃气及水生产和供应业-中型企业
     */
    public void setC42000(BigDecimal c42000) {
        this.c42000 = c42000;
    }

    /**
     * c43000_电力、热力、燃气及水生产和供应业-小型企业
     */
    public BigDecimal getC43000() {
        return c43000;
    }

    /**
     * c43000_电力、热力、燃气及水生产和供应业-小型企业
     */
    public void setC43000(BigDecimal c43000) {
        this.c43000 = c43000;
    }

    /**
     * c44000_电力、热力、燃气及水生产和供应业-微型企业
     */
    public BigDecimal getC44000() {
        return c44000;
    }

    /**
     * c44000_电力、热力、燃气及水生产和供应业-微型企业
     */
    public void setC44000(BigDecimal c44000) {
        this.c44000 = c44000;
    }

    /**
     * c50000_建筑业
     */
    public BigDecimal getC50000() {
        return c50000;
    }

    /**
     * c50000_建筑业
     */
    public void setC50000(BigDecimal c50000) {
        this.c50000 = c50000;
    }

    /**
     * c51000_建筑业-大型企业
     */
    public BigDecimal getC51000() {
        return c51000;
    }

    /**
     * c51000_建筑业-大型企业
     */
    public void setC51000(BigDecimal c51000) {
        this.c51000 = c51000;
    }

    /**
     * c52000_建筑业-中型企业
     */
    public BigDecimal getC52000() {
        return c52000;
    }

    /**
     * c52000_建筑业-中型企业
     */
    public void setC52000(BigDecimal c52000) {
        this.c52000 = c52000;
    }

    /**
     * c53000_建筑业-小型企业
     */
    public BigDecimal getC53000() {
        return c53000;
    }

    /**
     * c53000_建筑业-小型企业
     */
    public void setC53000(BigDecimal c53000) {
        this.c53000 = c53000;
    }

    /**
     * c54000_建筑业-微型企业
     */
    public BigDecimal getC54000() {
        return c54000;
    }

    /**
     * c54000_建筑业-微型企业
     */
    public void setC54000(BigDecimal c54000) {
        this.c54000 = c54000;
    }

    /**
     * c60000_批发和零售业
     */
    public BigDecimal getC60000() {
        return c60000;
    }

    /**
     * c60000_批发和零售业
     */
    public void setC60000(BigDecimal c60000) {
        this.c60000 = c60000;
    }

    /**
     * c61000_批发和零售业-大型企业
     */
    public BigDecimal getC61000() {
        return c61000;
    }

    /**
     * c61000_批发和零售业-大型企业
     */
    public void setC61000(BigDecimal c61000) {
        this.c61000 = c61000;
    }

    /**
     * c62000_批发和零售业-中型企业
     */
    public BigDecimal getC62000() {
        return c62000;
    }

    /**
     * c62000_批发和零售业-中型企业
     */
    public void setC62000(BigDecimal c62000) {
        this.c62000 = c62000;
    }

    /**
     * c63000_批发和零售业-小型企业
     */
    public BigDecimal getC63000() {
        return c63000;
    }

    /**
     * c63000_批发和零售业-小型企业
     */
    public void setC63000(BigDecimal c63000) {
        this.c63000 = c63000;
    }

    /**
     * c64000_批发和零售业-微型企业
     */
    public BigDecimal getC64000() {
        return c64000;
    }

    /**
     * c64000_批发和零售业-微型企业
     */
    public void setC64000(BigDecimal c64000) {
        this.c64000 = c64000;
    }

    /**
     * c70000_交通运输、仓储和邮政业
     */
    public BigDecimal getC70000() {
        return c70000;
    }

    /**
     * c70000_交通运输、仓储和邮政业
     */
    public void setC70000(BigDecimal c70000) {
        this.c70000 = c70000;
    }

    /**
     * c71000_交通运输、仓储和邮政业-大型企业
     */
    public BigDecimal getC71000() {
        return c71000;
    }

    /**
     * c71000_交通运输、仓储和邮政业-大型企业
     */
    public void setC71000(BigDecimal c71000) {
        this.c71000 = c71000;
    }

    /**
     * c72000_交通运输、仓储和邮政业-中型企业
     */
    public BigDecimal getC72000() {
        return c72000;
    }

    /**
     * c72000_交通运输、仓储和邮政业-中型企业
     */
    public void setC72000(BigDecimal c72000) {
        this.c72000 = c72000;
    }

    /**
     * c73000_交通运输、仓储和邮政业-小型企业
     */
    public BigDecimal getC73000() {
        return c73000;
    }

    /**
     * c73000_交通运输、仓储和邮政业-小型企业
     */
    public void setC73000(BigDecimal c73000) {
        this.c73000 = c73000;
    }

    /**
     * c74000_交通运输、仓储和邮政业-微型企业
     */
    public BigDecimal getC74000() {
        return c74000;
    }

    /**
     * c74000_交通运输、仓储和邮政业-微型企业
     */
    public void setC74000(BigDecimal c74000) {
        this.c74000 = c74000;
    }

    /**
     * c80000_住宿和餐饮业
     */
    public BigDecimal getC80000() {
        return c80000;
    }

    /**
     * c80000_住宿和餐饮业
     */
    public void setC80000(BigDecimal c80000) {
        this.c80000 = c80000;
    }

    /**
     * c81000_住宿和餐饮业-大型企业
     */
    public BigDecimal getC81000() {
        return c81000;
    }

    /**
     * c81000_住宿和餐饮业-大型企业
     */
    public void setC81000(BigDecimal c81000) {
        this.c81000 = c81000;
    }

    /**
     * c82000_住宿和餐饮业-中型企业
     */
    public BigDecimal getC82000() {
        return c82000;
    }

    /**
     * c82000_住宿和餐饮业-中型企业
     */
    public void setC82000(BigDecimal c82000) {
        this.c82000 = c82000;
    }

    /**
     * c83000_住宿和餐饮业-小型企业
     */
    public BigDecimal getC83000() {
        return c83000;
    }

    /**
     * c83000_住宿和餐饮业-小型企业
     */
    public void setC83000(BigDecimal c83000) {
        this.c83000 = c83000;
    }

    /**
     * c84000_住宿和餐饮业-微型企业
     */
    public BigDecimal getC84000() {
        return c84000;
    }

    /**
     * c84000_住宿和餐饮业-微型企业
     */
    public void setC84000(BigDecimal c84000) {
        this.c84000 = c84000;
    }

    /**
     * c90000_信息传输、软件和信息技术服务业
     */
    public BigDecimal getC90000() {
        return c90000;
    }

    /**
     * c90000_信息传输、软件和信息技术服务业
     */
    public void setC90000(BigDecimal c90000) {
        this.c90000 = c90000;
    }

    /**
     * c91000_信息传输、软件和信息技术服务业-大型企业
     */
    public BigDecimal getC91000() {
        return c91000;
    }

    /**
     * c91000_信息传输、软件和信息技术服务业-大型企业
     */
    public void setC91000(BigDecimal c91000) {
        this.c91000 = c91000;
    }

    /**
     * c92000_信息传输、软件和信息技术服务业-中型企业
     */
    public BigDecimal getC92000() {
        return c92000;
    }

    /**
     * c92000_信息传输、软件和信息技术服务业-中型企业
     */
    public void setC92000(BigDecimal c92000) {
        this.c92000 = c92000;
    }

    /**
     * c93000_信息传输、软件和信息技术服务业-小型企业
     */
    public BigDecimal getC93000() {
        return c93000;
    }

    /**
     * c93000_信息传输、软件和信息技术服务业-小型企业
     */
    public void setC93000(BigDecimal c93000) {
        this.c93000 = c93000;
    }

    /**
     * c94000_信息传输、软件和信息技术服务业-微型企业
     */
    public BigDecimal getC94000() {
        return c94000;
    }

    /**
     * c94000_信息传输、软件和信息技术服务业-微型企业
     */
    public void setC94000(BigDecimal c94000) {
        this.c94000 = c94000;
    }

    /**
     * ca0000_金融业
     */
    public BigDecimal getCa0000() {
        return ca0000;
    }

    /**
     * ca0000_金融业
     */
    public void setCa0000(BigDecimal ca0000) {
        this.ca0000 = ca0000;
    }

    /**
     * ca1000_金融业-大型企业
     */
    public BigDecimal getCa1000() {
        return ca1000;
    }

    /**
     * ca1000_金融业-大型企业
     */
    public void setCa1000(BigDecimal ca1000) {
        this.ca1000 = ca1000;
    }

    /**
     * ca2000_金融业-中型企业
     */
    public BigDecimal getCa2000() {
        return ca2000;
    }

    /**
     * ca2000_金融业-中型企业
     */
    public void setCa2000(BigDecimal ca2000) {
        this.ca2000 = ca2000;
    }

    /**
     * ca3000_金融业-小型企业
     */
    public BigDecimal getCa3000() {
        return ca3000;
    }

    /**
     * ca3000_金融业-小型企业
     */
    public void setCa3000(BigDecimal ca3000) {
        this.ca3000 = ca3000;
    }

    /**
     * ca4000_金融业-微型企业
     */
    public BigDecimal getCa4000() {
        return ca4000;
    }

    /**
     * ca4000_金融业-微型企业
     */
    public void setCa4000(BigDecimal ca4000) {
        this.ca4000 = ca4000;
    }

    /**
     * cb0000_房地产业
     */
    public BigDecimal getCb0000() {
        return cb0000;
    }

    /**
     * cb0000_房地产业
     */
    public void setCb0000(BigDecimal cb0000) {
        this.cb0000 = cb0000;
    }

    /**
     * cb1000_房地产业-大型企业
     */
    public BigDecimal getCb1000() {
        return cb1000;
    }

    /**
     * cb1000_房地产业-大型企业
     */
    public void setCb1000(BigDecimal cb1000) {
        this.cb1000 = cb1000;
    }

    /**
     * cb2000_房地产业-中型企业
     */
    public BigDecimal getCb2000() {
        return cb2000;
    }

    /**
     * cb2000_房地产业-中型企业
     */
    public void setCb2000(BigDecimal cb2000) {
        this.cb2000 = cb2000;
    }

    /**
     * cb3000_房地产业-小型企业
     */
    public BigDecimal getCb3000() {
        return cb3000;
    }

    /**
     * cb3000_房地产业-小型企业
     */
    public void setCb3000(BigDecimal cb3000) {
        this.cb3000 = cb3000;
    }

    /**
     * cb4000_房地产业-微型企业
     */
    public BigDecimal getCb4000() {
        return cb4000;
    }

    /**
     * cb4000_房地产业-微型企业
     */
    public void setCb4000(BigDecimal cb4000) {
        this.cb4000 = cb4000;
    }

    /**
     * cc0000_租赁和商务服务业
     */
    public BigDecimal getCc0000() {
        return cc0000;
    }

    /**
     * cc0000_租赁和商务服务业
     */
    public void setCc0000(BigDecimal cc0000) {
        this.cc0000 = cc0000;
    }

    /**
     * cc1000_租赁和商务服务业-大型企业
     */
    public BigDecimal getCc1000() {
        return cc1000;
    }

    /**
     * cc1000_租赁和商务服务业-大型企业
     */
    public void setCc1000(BigDecimal cc1000) {
        this.cc1000 = cc1000;
    }

    /**
     * cc2000_租赁和商务服务业-中型企业
     */
    public BigDecimal getCc2000() {
        return cc2000;
    }

    /**
     * cc2000_租赁和商务服务业-中型企业
     */
    public void setCc2000(BigDecimal cc2000) {
        this.cc2000 = cc2000;
    }

    /**
     * cc3000_租赁和商务服务业-小型企业
     */
    public BigDecimal getCc3000() {
        return cc3000;
    }

    /**
     * cc3000_租赁和商务服务业-小型企业
     */
    public void setCc3000(BigDecimal cc3000) {
        this.cc3000 = cc3000;
    }

    /**
     * cc4000_租赁和商务服务业-微型企业
     */
    public BigDecimal getCc4000() {
        return cc4000;
    }

    /**
     * cc4000_租赁和商务服务业-微型企业
     */
    public void setCc4000(BigDecimal cc4000) {
        this.cc4000 = cc4000;
    }

    /**
     * cd0000_科学研究和技术服务业
     */
    public BigDecimal getCd0000() {
        return cd0000;
    }

    /**
     * cd0000_科学研究和技术服务业
     */
    public void setCd0000(BigDecimal cd0000) {
        this.cd0000 = cd0000;
    }

    /**
     * cd1000_科学研究和技术服务业-大型企业
     */
    public BigDecimal getCd1000() {
        return cd1000;
    }

    /**
     * cd1000_科学研究和技术服务业-大型企业
     */
    public void setCd1000(BigDecimal cd1000) {
        this.cd1000 = cd1000;
    }

    /**
     * cd2000_科学研究和技术服务业-中型企业
     */
    public BigDecimal getCd2000() {
        return cd2000;
    }

    /**
     * cd2000_科学研究和技术服务业-中型企业
     */
    public void setCd2000(BigDecimal cd2000) {
        this.cd2000 = cd2000;
    }

    /**
     * cd3000_科学研究和技术服务业-小型企业
     */
    public BigDecimal getCd3000() {
        return cd3000;
    }

    /**
     * cd3000_科学研究和技术服务业-小型企业
     */
    public void setCd3000(BigDecimal cd3000) {
        this.cd3000 = cd3000;
    }

    /**
     * cd4000_科学研究和技术服务业-微型企业
     */
    public BigDecimal getCd4000() {
        return cd4000;
    }

    /**
     * cd4000_科学研究和技术服务业-微型企业
     */
    public void setCd4000(BigDecimal cd4000) {
        this.cd4000 = cd4000;
    }

    /**
     * ce0000_水利、环境和公共设施管理业
     */
    public BigDecimal getCe0000() {
        return ce0000;
    }

    /**
     * ce0000_水利、环境和公共设施管理业
     */
    public void setCe0000(BigDecimal ce0000) {
        this.ce0000 = ce0000;
    }

    /**
     * ce1000_水利、环境和公共设施管理业-大型企业
     */
    public BigDecimal getCe1000() {
        return ce1000;
    }

    /**
     * ce1000_水利、环境和公共设施管理业-大型企业
     */
    public void setCe1000(BigDecimal ce1000) {
        this.ce1000 = ce1000;
    }

    /**
     * ce2000_水利、环境和公共设施管理业-中型企业
     */
    public BigDecimal getCe2000() {
        return ce2000;
    }

    /**
     * ce2000_水利、环境和公共设施管理业-中型企业
     */
    public void setCe2000(BigDecimal ce2000) {
        this.ce2000 = ce2000;
    }

    /**
     * ce3000_水利、环境和公共设施管理业-小型企业
     */
    public BigDecimal getCe3000() {
        return ce3000;
    }

    /**
     * ce3000_水利、环境和公共设施管理业-小型企业
     */
    public void setCe3000(BigDecimal ce3000) {
        this.ce3000 = ce3000;
    }

    /**
     * ce4000_水利、环境和公共设施管理业-微型企业
     */
    public BigDecimal getCe4000() {
        return ce4000;
    }

    /**
     * ce4000_水利、环境和公共设施管理业-微型企业
     */
    public void setCe4000(BigDecimal ce4000) {
        this.ce4000 = ce4000;
    }

    /**
     * cf0000_居民服务、修理和其他服务业
     */
    public BigDecimal getCf0000() {
        return cf0000;
    }

    /**
     * cf0000_居民服务、修理和其他服务业
     */
    public void setCf0000(BigDecimal cf0000) {
        this.cf0000 = cf0000;
    }

    /**
     * cf1000_居民服务、修理和其他服务业-大型企业
     */
    public BigDecimal getCf1000() {
        return cf1000;
    }

    /**
     * cf1000_居民服务、修理和其他服务业-大型企业
     */
    public void setCf1000(BigDecimal cf1000) {
        this.cf1000 = cf1000;
    }

    /**
     * cf2000_居民服务、修理和其他服务业-中型企业
     */
    public BigDecimal getCf2000() {
        return cf2000;
    }

    /**
     * cf2000_居民服务、修理和其他服务业-中型企业
     */
    public void setCf2000(BigDecimal cf2000) {
        this.cf2000 = cf2000;
    }

    /**
     * cf3000_居民服务、修理和其他服务业-小型企业
     */
    public BigDecimal getCf3000() {
        return cf3000;
    }

    /**
     * cf3000_居民服务、修理和其他服务业-小型企业
     */
    public void setCf3000(BigDecimal cf3000) {
        this.cf3000 = cf3000;
    }

    /**
     * cf4000_居民服务、修理和其他服务业-微型企业
     */
    public BigDecimal getCf4000() {
        return cf4000;
    }

    /**
     * cf4000_居民服务、修理和其他服务业-微型企业
     */
    public void setCf4000(BigDecimal cf4000) {
        this.cf4000 = cf4000;
    }

    /**
     * cg0000_教育
     */
    public BigDecimal getCg0000() {
        return cg0000;
    }

    /**
     * cg0000_教育
     */
    public void setCg0000(BigDecimal cg0000) {
        this.cg0000 = cg0000;
    }

    /**
     * cg1000_教育-大型企业
     */
    public BigDecimal getCg1000() {
        return cg1000;
    }

    /**
     * cg1000_教育-大型企业
     */
    public void setCg1000(BigDecimal cg1000) {
        this.cg1000 = cg1000;
    }

    /**
     * cg2000_教育-中型企业
     */
    public BigDecimal getCg2000() {
        return cg2000;
    }

    /**
     * cg2000_教育-中型企业
     */
    public void setCg2000(BigDecimal cg2000) {
        this.cg2000 = cg2000;
    }

    /**
     * cg3000_教育-小型企业
     */
    public BigDecimal getCg3000() {
        return cg3000;
    }

    /**
     * cg3000_教育-小型企业
     */
    public void setCg3000(BigDecimal cg3000) {
        this.cg3000 = cg3000;
    }

    /**
     * cg3000_教育-小型企业
     */
    public BigDecimal getCg4000() {
        return cg4000;
    }

    /**
     * cg3000_教育-小型企业
     */
    public void setCg4000(BigDecimal cg4000) {
        this.cg4000 = cg4000;
    }

    /**
     * ch0000_卫生和社会工作
     */
    public BigDecimal getCh0000() {
        return ch0000;
    }

    /**
     * ch0000_卫生和社会工作
     */
    public void setCh0000(BigDecimal ch0000) {
        this.ch0000 = ch0000;
    }

    /**
     * ch1000_卫生和社会工作-大型企业
     */
    public BigDecimal getCh1000() {
        return ch1000;
    }

    /**
     * ch1000_卫生和社会工作-大型企业
     */
    public void setCh1000(BigDecimal ch1000) {
        this.ch1000 = ch1000;
    }

    /**
     * ch2000_卫生和社会工作-中型企业
     */
    public BigDecimal getCh2000() {
        return ch2000;
    }

    /**
     * ch2000_卫生和社会工作-中型企业
     */
    public void setCh2000(BigDecimal ch2000) {
        this.ch2000 = ch2000;
    }

    /**
     * ch3000_卫生和社会工作-小型企业
     */
    public BigDecimal getCh3000() {
        return ch3000;
    }

    /**
     * ch3000_卫生和社会工作-小型企业
     */
    public void setCh3000(BigDecimal ch3000) {
        this.ch3000 = ch3000;
    }

    /**
     * ch4000_卫生和社会工作-微型企业
     */
    public BigDecimal getCh4000() {
        return ch4000;
    }

    /**
     * ch4000_卫生和社会工作-微型企业
     */
    public void setCh4000(BigDecimal ch4000) {
        this.ch4000 = ch4000;
    }

    /**
     * ci0000_文化、体育和娱乐业
     */
    public BigDecimal getCi0000() {
        return ci0000;
    }

    /**
     * ci0000_文化、体育和娱乐业
     */
    public void setCi0000(BigDecimal ci0000) {
        this.ci0000 = ci0000;
    }

    /**
     * ci1000_文化、体育和娱乐业-大型企业
     */
    public BigDecimal getCi1000() {
        return ci1000;
    }

    /**
     * ci1000_文化、体育和娱乐业-大型企业
     */
    public void setCi1000(BigDecimal ci1000) {
        this.ci1000 = ci1000;
    }

    /**
     * ci2000_文化、体育和娱乐业-中型企业
     */
    public BigDecimal getCi2000() {
        return ci2000;
    }

    /**
     * ci2000_文化、体育和娱乐业-中型企业
     */
    public void setCi2000(BigDecimal ci2000) {
        this.ci2000 = ci2000;
    }

    /**
     * ci3000_文化、体育和娱乐业-小型企业
     */
    public BigDecimal getCi3000() {
        return ci3000;
    }

    /**
     * ci3000_文化、体育和娱乐业-小型企业
     */
    public void setCi3000(BigDecimal ci3000) {
        this.ci3000 = ci3000;
    }

    /**
     * ci4000_文化、体育和娱乐业-微型企业
     */
    public BigDecimal getCi4000() {
        return ci4000;
    }

    /**
     * ci4000_文化、体育和娱乐业-微型企业
     */
    public void setCi4000(BigDecimal ci4000) {
        this.ci4000 = ci4000;
    }

    /**
     * cj0000_公共管理、社会保障和社会组织
     */
    public BigDecimal getCj0000() {
        return cj0000;
    }

    /**
     * cj0000_公共管理、社会保障和社会组织
     */
    public void setCj0000(BigDecimal cj0000) {
        this.cj0000 = cj0000;
    }

    /**
     * cj1000_公共管理、社会保障和社会组织-大型企业
     */
    public BigDecimal getCj1000() {
        return cj1000;
    }

    /**
     * cj1000_公共管理、社会保障和社会组织-大型企业
     */
    public void setCj1000(BigDecimal cj1000) {
        this.cj1000 = cj1000;
    }

    /**
     * cj2000_公共管理、社会保障和社会组织-中型企业
     */
    public BigDecimal getCj2000() {
        return cj2000;
    }

    /**
     * cj2000_公共管理、社会保障和社会组织-中型企业
     */
    public void setCj2000(BigDecimal cj2000) {
        this.cj2000 = cj2000;
    }

    /**
     * cj3000_公共管理、社会保障和社会组织-小型企业
     */
    public BigDecimal getCj3000() {
        return cj3000;
    }

    /**
     * cj3000_公共管理、社会保障和社会组织-小型企业
     */
    public void setCj3000(BigDecimal cj3000) {
        this.cj3000 = cj3000;
    }

    /**
     * cj4000_公共管理、社会保障和社会组织-微型企业
     */
    public BigDecimal getCj4000() {
        return cj4000;
    }

    /**
     * cj4000_公共管理、社会保障和社会组织-微型企业
     */
    public void setCj4000(BigDecimal cj4000) {
        this.cj4000 = cj4000;
    }

    public String getC00000Str() {
        return format(c00000);
    }

    public String getC01000Str() {
        return format(c01000);
    }

    public String getC02000Str() {
        return format(c02000);
    }

    public String getC03000Str() {
        return format(c03000);
    }

    public String getC04000Str() {
        return format(c04000);
    }

    public String getC10000Str() {
        return format(c10000);
    }

    public String getC11000Str() {
        return format(c11000);
    }

    public String getC12000Str() {
        return format(c12000);
    }

    public String getC13000Str() {
        return format(c13000);
    }

    public String getC14000Str() {
        return format(c14000);
    }

    public String getC20000Str() {
        return format(c20000);
    }

    public String getC21000Str() {
        return format(c21000);
    }

    public String getC22000Str() {
        return format(c22000);
    }

    public String getC23000Str() {
        return format(c23000);
    }

    public String getC24000Str() {
        return format(c24000);
    }

    public String getC30000Str() {
        return format(c30000);
    }

    public String getC31000Str() {
        return format(c31000);
    }

    public String getC32000Str() {
        return format(c32000);
    }

    public String getC33000Str() {
        return format(c33000);
    }

    public String getC34000Str() {
        return format(c34000);
    }

    public String getC40000Str() {
        return format(c40000);
    }

    public String getC41000Str() {
        return format(c41000);
    }

    public String getC42000Str() {
        return format(c42000);
    }

    public String getC43000Str() {
        return format(c43000);
    }

    public String getC44000Str() {
        return format(c44000);
    }

    public String getC50000Str() {
        return format(c50000);
    }

    public String getC51000Str() {
        return format(c51000);
    }

    public String getC52000Str() {
        return format(c52000);
    }

    public String getC53000Str() {
        return format(c53000);
    }

    public String getC54000Str() {
        return format(c54000);
    }

    public String getC60000Str() {
        return format(c60000);
    }

    public String getC61000Str() {
        return format(c61000);
    }

    public String getC62000Str() {
        return format(c62000);
    }

    public String getC63000Str() {
        return format(c63000);
    }

    public String getC64000Str() {
        return format(c64000);
    }

    public String getC70000Str() {
        return format(c70000);
    }

    public String getC71000Str() {
        return format(c71000);
    }

    public String getC72000Str() {
        return format(c72000);
    }

    public String getC73000Str() {
        return format(c73000);
    }

    public String getC74000Str() {
        return format(c74000);
    }

    public String getC80000Str() {
        return format(c80000);
    }

    public String getC81000Str() {
        return format(c81000);
    }

    public String getC82000Str() {
        return format(c82000);
    }

    public String getC83000Str() {
        return format(c83000);
    }

    public String getC84000Str() {
        return format(c84000);
    }

    public String getC90000Str() {
        return format(c90000);
    }

    public String getC91000Str() {
        return format(c91000);
    }

    public String getC92000Str() {
        return format(c92000);
    }

    public String getC93000Str() {
        return format(c93000);
    }

    public String getC94000Str() {
        return format(c94000);
    }

    public String getCa0000Str() {
        return format(ca0000);
    }

    public String getCa1000Str() {
        return format(ca1000);
    }

    public String getCa2000Str() {
        return format(ca2000);
    }

    public String getCa3000Str() {
        return format(ca3000);
    }

    public String getCa4000Str() {
        return format(ca4000);
    }

    public String getCb0000Str() {
        return format(cb0000);
    }

    public String getCb1000Str() {
        return format(cb1000);
    }

    public String getCb2000Str() {
        return format(cb2000);
    }

    public String getCb3000Str() {
        return format(cb3000);
    }

    public String getCb4000Str() {
        return format(cb4000);
    }

    public String getCc0000Str() {
        return format(cc0000);
    }

    public String getCc1000Str() {
        return format(cc1000);
    }

    public String getCc2000Str() {
        return format(cc2000);
    }

    public String getCc3000Str() {
        return format(cc3000);
    }

    public String getCc4000Str() {
        return format(cc4000);
    }

    public String getCd0000Str() {
        return format(cd0000);
    }

    public String getCd1000Str() {
        return format(cd1000);
    }

    public String getCd2000Str() {
        return format(cd2000);
    }

    public String getCd3000Str() {
        return format(cd3000);
    }

    public String getCd4000Str() {
        return format(cd4000);
    }

    public String getCe0000Str() {
        return format(ce0000);
    }

    public String getCe1000Str() {
        return format(ce1000);
    }

    public String getCe2000Str() {
        return format(ce2000);
    }

    public String getCe3000Str() {
        return format(ce3000);
    }

    public String getCe4000Str() {
        return format(ce4000);
    }

    public String getCf0000Str() {
        return format(cf0000);
    }

    public String getCf1000Str() {
        return format(cf1000);
    }

    public String getCf2000Str() {
        return format(cf2000);
    }

    public String getCf3000Str() {
        return format(cf3000);
    }

    public String getCf4000Str() {
        return format(cf4000);
    }

    public String getCg0000Str() {
        return format(cg0000);
    }

    public String getCg1000Str() {
        return format(cg1000);
    }

    public String getCg2000Str() {
        return format(cg2000);
    }

    public String getCg3000Str() {
        return format(cg3000);
    }

    public String getCg4000Str() {
        return format(cg4000);
    }

    public String getCh0000Str() {
        return format(ch0000);
    }

    public String getCh1000Str() {
        return format(ch1000);
    }

    public String getCh2000Str() {
        return format(ch2000);
    }

    public String getCh3000Str() {
        return format(ch3000);
    }

    public String getCh4000Str() {
        return format(ch4000);
    }

    public String getCi0000Str() {
        return format(ci0000);
    }

    public String getCi1000Str() {
        return format(ci1000);
    }

    public String getCi2000Str() {
        return format(ci2000);
    }

    public String getCi3000Str() {
        return format(ci3000);
    }

    public String getCi4000Str() {
        return format(ci4000);
    }

    public String getCj0000Str() {
        return format(cj0000);
    }

    public String getCj1000Str() {
        return format(cj1000);
    }

    public String getCj2000Str() {
        return format(cj2000);
    }

    public String getCj3000Str() {
        return format(cj3000);
    }

    public String getCj4000Str() {
        return format(cj4000);
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
