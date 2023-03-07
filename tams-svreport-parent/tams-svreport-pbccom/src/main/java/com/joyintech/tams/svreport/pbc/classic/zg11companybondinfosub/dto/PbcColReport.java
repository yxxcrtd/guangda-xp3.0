package com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.system.base.entity.SysInstitution;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述:PBC_COL_REPORT表的实体类
 * @version
 * @author:
 * @创建时间: 2022-11-07
 */
@TableName("PBC_REPORT")
public class PbcColReport extends BaseEntity implements Comparable<PbcColReport>{
	/**
     * 主键
     */
    private String reportuuid;
    /**
     * 上期报表类型的对应主建
     */
    private String lastreportuuid;
    /**
     * 期数
     */
    private String termid;

    /**
     * 报表类型:
     */
    private String reporttype;

    /**
     * 开始日期
     */
    private Date startdate;

    /**
     * 结束日期
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
    private Date chbSubmitDeadline;

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
     * 有效状态
     */
    private String statusStr;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 报送状态
     */
    private String sendStatusStr;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 机构代码
     */
    private String orgCode;


    /**
     * 部门代码
     */
    private String deptCode;
    /**
     * 产品id
     */
    private String prodId;

    /**
     * 是否生成报表
     */
    private boolean isGen;
    
    /**
     * 人行产品代码
     */
    private String pbcProdId;
    
    /**
     * 人行资产池登记主键uuid
     */
    private String  registSeqno;


    /**
     * null
     * @return REPORTUUID null
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * null
     * @param reportuuid null
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    /**
     * 期数
     * @return TERMID 期数
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 期数
     * @return TERMID 期数
     */
    public String getTermReport() {
        return termid+reporttype;
    }

    /**
     * 期数
     * @param termid 期数
     */
    public void setTermid(String termid) {
        this.termid = termid == null ? null : termid.trim();
    }

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     * @return REPORTTYPE 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     * @param reporttype 报表类型：PROD-产品，BALANCE-资产负债表，DEPOSIT-结构性存款
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 开始日期
     * @return STARTDATE 开始日期
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 开始日期
     * @param startdate 开始日期
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 结束日期
     * @return ENDDATE 结束日期
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * 结束日期
     * @param enddate 结束日期
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * 发行机构代码
     * @return ISSUE_PARTY_ID 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 发行机构名称
     * @return ISSUE_PARTY_NAME 发行机构名称
     */
    public String getIssuePartyName() {
        return issuePartyName;
    }

    /**
     * 发行机构名称
     * @param issuePartyName 发行机构名称
     */
    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName == null ? null : issuePartyName.trim();
    }

    /**
     * 报送日期
     * @return SUBMIT_TIME 报送日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 报送日期
     * @param submitTime 报送日期
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 中债报送截止日
     * @return CHB_SUBMIT_DEADLINE 中债报送截止日
     */
    public Date getChbSubmitDeadline() {
        return chbSubmitDeadline;
    }

    /**
     * 中债报送截止日
     * @param chbSubmitDeadline 中债报送截止日
     */
    public void setChbSubmitDeadline(Date chbSubmitDeadline) {
        this.chbSubmitDeadline = chbSubmitDeadline;
    }

    /**
     * 行内报送截止日
     * @return SUBMIT_DEADLINE 行内报送截止日
     */
    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    /**
     * 行内报送截止日
     * @param submitDeadline 行内报送截止日
     */
    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    /**
     * 返回信息
     * @return SUBMIT_FEEDBACK_STATUS 返回信息
     */
    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    /**
     * 返回信息
     * @param submitFeedbackStatus 返回信息
     */
    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus == null ? null : submitFeedbackStatus.trim();
    }

    /**
     * 有效状态
     * @return STATUS 有效状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 有效状态
     * @param status 有效状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 报送状态
     * @return SEND_STATUS 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 数据日期
     * @return STATUS_DATE 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }



    /**
     * 机构代码
     * @return ORD_CODE 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码
     * @param ORG_CODE 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }



	public String getStatusStr(){
		return SvrDataUtil.getNameFromDataDict(DictCode.报送数据状态, this.getStatus());
	}
	
	public String getSendStatusStr(){ return SvrDataUtil.getNameFromDataDict(DictCode.报送状态, this.getSendStatus());
	}
	
	public String getSubmitFeedbackStatusStr(){
		return SvrDataUtil.getNameFromDataDict(DictCode.人行报送反馈状态, this.getSubmitFeedbackStatus());
	}
	
	public String getTermname(){
		if(termid==null) return null;
		return termid.substring(0, 4)+"年"+termid.substring(4, 6)+"月";
	}
	
	public String getReportname(){
	   
		return "表"+reporttype+":"+SvrDataUtil.getNameFromDataDict(DictCode.人行报表名称,reporttype)+"_"+this.getTermname();

	}


	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

    public boolean isGen() {
        return isGen;
    }

    public void setGen(boolean isGen) {
        this.isGen = isGen;
    }

    public String getPbcProdId() {
        return pbcProdId;
    }

    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public void setSendStatusStr(String sendStatusStr) {
        this.sendStatusStr = sendStatusStr;
    }

    public String getRegistSeqno() {
		return registSeqno;
	}

	public void setRegistSeqno(String registSeqno) {
		this.registSeqno = registSeqno;
	}

    public String getLastreportuuid() {
        return lastreportuuid;
    }

    public void setLastreportuuid(String lastreportuuid) {
        this.lastreportuuid = lastreportuuid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(PbcColReport o) {
        if(o.getTermid() !=null&&this.getTermid()!=null) {
            if(this.getTermid().compareTo(o.getTermid())==0) {
                return this.getReportname().compareTo(o.getReportname());
            }else {
                return   this.getTermid().compareTo(o.getTermid());
            }
        }else {
            return 0  ;
        }
    }

    public String getStatusDateStr(){
        return null !=statusDate?DateUtil.formatDateTime(statusDate):null;
    }
    public String getSubmitTimeStr(){
        return null !=submitTime?DateUtil.formatDateTime(submitTime):null;
    }
    public String getChbSubmitDeadlineStr(){
        return null !=chbSubmitDeadline?DateUtil.format(chbSubmitDeadline):null;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }


}