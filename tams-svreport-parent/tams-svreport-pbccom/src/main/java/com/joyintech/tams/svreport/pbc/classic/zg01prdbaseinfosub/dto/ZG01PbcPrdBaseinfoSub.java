package com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.common.util.SysDataUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 描述:PBC_COL_PRD_BASEINFO_SUB表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-06-05
 */
@TableName("PBC_COL_PRD_BASEINFO_SUB")
public class ZG01PbcPrdBaseinfoSub extends BaseEntity{
    /**
     * 主键uuid
     */
    private String registSeqno;

    /**
     * 人行产品代码
     */
    private String pbcProdId;

    /**
     * 发行机构代码
     */

    private String issuePartyId;

    /**
     * 发行机构名称
     */
    private String issuePartyName;

    /**
     * 产品品种
     */
    @DictFormat(DictCode.人行_产品种类)
    private String pbcProdType;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 产品类型
     */
    @DictFormat(DictCode.人行投资性质)
    private String ivstorientation;

    /**
     * 产品品牌
     */
    private String prodBrand;

    /**
     * 产品期次
     */
    private BigDecimal prodStage;

    /**
     * 理财产品代码
     */
    @BusinessKey("PROD_ID")
    private String prodId;

    /**
     * 信息类型
     */

    @DictFormat(DictCode.信息类型)
    private String infoType;

    /**
     * 募集币种
     */
    @DictFormat(DictCode.币种_中债)
    private String raiseCcy;

    /**
     * 兑付本金币种
     */
    @DictFormat(DictCode.币种_中债)
    private String cashPrinCcy;

    /**
     * 兑付收益币种
     */
    @DictFormat(DictCode.币种_中债)
    private String cashProfCcy;


    /**
     * 募集方式，1-公募，2-私募
     */
    @DictFormat(DictCode.新人行募集方式)
    private String raiseType;

    /**
     * 管理方式，1-集合管理/2-单独管理
     */
    @DictFormat(DictCode.人行管理类型)
    private String manageType;

    /**
     * 受托职责，1-主动管理业务/2-被动管理业务
     */
    @DictFormat(DictCode.人行受托职责)
    private String fiduciaryDuty;


    /**
     * 运行方式，1-开放式净值型/2-开放式非净值型/3一封闭式净值型/4-封闭式非净值型
     */
    @DictFormat(DictCode.新人行产品运作模式)
    private String operateMode;

    /**
     * 募集起始日期
     */
    private Date raiseVdate;

    /**
     * 募集结束日期
     */
    private Date raiseEnddate;

    /**
     * 发行机构提前终止权标识，1-有，2-无
     */
    @DictFormat(DictCode.人行有无)
    private String termRightFlag;

    /**
     * 客户赎回权标识，1-有，2-无
     */
    @DictFormat(DictCode.人行有无)
    private String cusRedeemFlag;

    /**
     * 产品增信标识，1-是，2-否
     */
    @DictFormat(DictCode.人行是否)
    private String creditFlag;

    @DictFormat(DictCode.人行增信机构类型)
    private String creditType;

    /**
     * 增信形式，1-内部增级，2-外部增级
     */
    @DictFormat(DictCode.人行增信形式)
    private String creditMode;

    /**
     * 境内托管机构名称
     */
    @DictFormat(DictCode.境内托管机构名称)
    private String inDepName;

    /**
     * 境内托管机构代码
     */
    private String inDepCode;

    /**
     * 境外托管机构国别代码
     */
    private String outDepCtry;

    /**
     * 境外托管机构名称
     */
    private String outDepName;


    /**
     * 产品起始日期
     */
    private Date vdate;

    /**
     * 产品变更日期
     */
    private Date cdate;

    /**
     * 产品预计终止日期
     */
    private Date mdate;


    /**
     * 分级产品标识，1-是，2-否
     */
    @DictFormat(DictCode.人行是否)
    private String isConstracte;

    /**
     * 收益权转让产品标识，1-是，2-否
     */
    @DictFormat(DictCode.人行是否)
    private String transferType;

    /**
     * 货基或现金管理类产品标识
     *
     */
    @DictFormat(DictCode.人行是否)
    private String  cashManaCate;

    /**
     * 跨境理财通标识
     *
     */
    @DictFormat(DictCode.人行是否)
    private  String crossBorderFlag;

    /**
     * 以财产权信托名义开展的资金信托标识
     *
     */
    @DictFormat(DictCode.人行是否)
    private  String isPropertyFlag;

    /**
     * 基本信息公开标识
     *
     */
    @DictFormat(DictCode.人行是否)
    private  String baseInfoSign;


    /**
     * 变更原因
     *
     */
    private  String changeReason;

    /**
     * 基本信息变更原因
     *
     */
    private  String infoReason;
    /**
     * 申报信息变更原因
     *
     */
    private  String dnfoReason;

    /**
     * 基本信息变更时间
     *
     */
    private Date updateTimeInfo;

    /**
     * 申报信息变更时间
     *
     */
    private Date updateTimeDnfo;

    public Date getUpdateTimeInfo() {
        return updateTimeInfo;
    }

    public void setUpdateTimeInfo(Date updateTimeInfo) {
        this.updateTimeInfo = updateTimeInfo;
    }

    public Date getUpdateTimeDnfo() {
        return updateTimeDnfo;
    }

    public void setUpdateTimeDnfo(Date updateTimeDnfo) {
        this.updateTimeDnfo = updateTimeDnfo;
    }

    public String getInfoReason() {
        return infoReason;
    }

    public void setInfoReason(String infoReason) {
        this.infoReason = infoReason;
    }

    public String getDnfoReason() {
        return dnfoReason;
    }

    public void setDnfoReason(String dnfoReason) {
        this.dnfoReason = dnfoReason;
    }

    //备用字段
    private String backup1;
    private String backup2;
    private String backup3;
    private String backup4;
    private String backup5;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 报送日期
     */
    private Date submitTime;

    /**
     * 报送反馈状态
     */
    @DictFormat(DictCode.人行报送反馈状态)
    private String submitFeedbackStatus;

    /**
     * 报送流水号
     */
    private String sumitSeq;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 人行报送截止日
     */
    private Date pbcSubmitDeadline;

    /**
     * 行内报送截止日
     */
    private Date submitDeadline;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 机构代码
     */
    private String orgCode;

    /**
     * 创建部门
     */
    private String createDept;

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

    //收益类型
    private String profitType;

    //基本信息状态
    private String statusInfo;

    //申报信息状态
    private String statusDnfo;

    private String datuuid;

    /**
     *报送检查用人行产品代码字段
     */
    private  String  pbcPordIdOut;

    public String getPbcPordIdOut() {
        return pbcPordIdOut;
    }

    public void setPbcPordIdOut(String pbcPordIdOut) {
        this.pbcPordIdOut = pbcPordIdOut;
    }

    public String getDatuuid() {
        return datuuid;
    }

    public void setDatuuid(String datuuid) {
        this.datuuid = datuuid;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getStatusDnfo() {
        return statusDnfo;
    }

    public void setStatusDnfo(String statusDnfo) {
        this.statusDnfo = statusDnfo;
    }

    //人行登记机构代码
    private String registBankCode;

    public String getRegistBankCode() {
        return registBankCode;
    }

    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    private List<String> prodList;

    public String getRegistSeqno() {
        return registSeqno;
    }

    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno;
    }

    public String getPbcProdId() {
        return pbcProdId;
    }

    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId;
    }

    public String getIssuePartyId() {
        return issuePartyId;
    }

    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    public String getIssuePartyName() {
        return issuePartyName;
    }

    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName;
    }

    public String getPbcProdType() {
        return pbcProdType;
    }

    public void setPbcProdType(String pbcProdType) {
        this.pbcProdType = pbcProdType;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getIvstorientation() {
        return ivstorientation;
    }

    public void setIvstorientation(String ivstorientation) {
        this.ivstorientation = ivstorientation;
    }

    public String getProdBrand() {
        return prodBrand;
    }

    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand;
    }

    public BigDecimal getProdStage() {
        return prodStage;
    }

    public void setProdStage(BigDecimal prodStage) {
        this.prodStage = prodStage;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getRaiseCcy() {
        return raiseCcy;
    }

    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy;
    }

    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy;
    }

    public String getCashProfCcy() {
        return cashProfCcy;
    }

    public void setCashProfCcy(String cashProfCcy) {
        this.cashProfCcy = cashProfCcy;
    }

    public String getRaiseType() {
        return raiseType;
    }

    public void setRaiseType(String raiseType) {
        this.raiseType = raiseType;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getFiduciaryDuty() {
        return fiduciaryDuty;
    }

    public void setFiduciaryDuty(String fiduciaryDuty) {
        this.fiduciaryDuty = fiduciaryDuty;
    }

    public String getOperateMode() {
        return operateMode;
    }

    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode;
    }

    public Date getRaiseVdate() {
        return raiseVdate;
    }

    public void setRaiseVdate(Date raiseVdate) {
        this.raiseVdate = raiseVdate;
    }

    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    public String getTermRightFlag() {
        return termRightFlag;
    }

    public void setTermRightFlag(String termRightFlag) {
        this.termRightFlag = termRightFlag;
    }

    public String getCusRedeemFlag() {
        return cusRedeemFlag;
    }

    public void setCusRedeemFlag(String cusRedeemFlag) {
        this.cusRedeemFlag = cusRedeemFlag;
    }

    public String getCreditFlag() {
        return creditFlag;
    }

    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getCreditMode() {
        return creditMode;
    }

    public void setCreditMode(String creditMode) {
        this.creditMode = creditMode;
    }

    public String getInDepName() {
        return inDepName;
    }

    public void setInDepName(String inDepName) {
        this.inDepName = inDepName;
    }

    public String getInDepCode() {
        return inDepCode;
    }

    public void setInDepCode(String inDepCode) {
        this.inDepCode = inDepCode;
    }

    public String getOutDepCtry() {
        return outDepCtry;
    }

    public void setOutDepCtry(String outDepCtry) {
        this.outDepCtry = outDepCtry;
    }

    public String getOutDepName() {
        return outDepName;
    }

    public void setOutDepName(String outDepName) {
        this.outDepName = outDepName;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getIsConstracte() {
        return isConstracte;
    }

    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getCashManaCate() {
        return cashManaCate;
    }

    public void setCashManaCate(String cashManaCate) {
        this.cashManaCate = cashManaCate;
    }

    public String getCrossBorderFlag() {
        return crossBorderFlag;
    }

    public void setCrossBorderFlag(String crossBorderFlag) {
        this.crossBorderFlag = crossBorderFlag;
    }

    public String getIsPropertyFlag() {
        return isPropertyFlag;
    }

    public void setIsPropertyFlag(String isPropertyFlag) {
        this.isPropertyFlag = isPropertyFlag;
    }

    public String getBaseInfoSign() {
        return baseInfoSign;
    }

    public void setBaseInfoSign(String baseInfoSign) {
        this.baseInfoSign = baseInfoSign;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getBackup1() {
        return backup1;
    }

    public void setBackup1(String backup1) {
        this.backup1 = backup1;
    }

    public String getBackup2() {
        return backup2;
    }

    public void setBackup2(String backup2) {
        this.backup2 = backup2;
    }

    public String getBackup3() {
        return backup3;
    }

    public void setBackup3(String backup3) {
        this.backup3 = backup3;
    }

    public String getBackup4() {
        return backup4;
    }

    public void setBackup4(String backup4) {
        this.backup4 = backup4;
    }

    public String getBackup5() {
        return backup5;
    }

    public void setBackup5(String backup5) {
        this.backup5 = backup5;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }

    public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
        this.submitFeedbackStatus = submitFeedbackStatus;
    }

    public String getSumitSeq() {
        return sumitSeq;
    }

    public void setSumitSeq(String sumitSeq) {
        this.sumitSeq = sumitSeq;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getPbcSubmitDeadline() {
        return pbcSubmitDeadline;
    }

    public void setPbcSubmitDeadline(Date pbcSubmitDeadline) {
        this.pbcSubmitDeadline = pbcSubmitDeadline;
    }

    public Date getSubmitDeadline() {
        return submitDeadline;
    }

    public void setSubmitDeadline(Date submitDeadline) {
        this.submitDeadline = submitDeadline;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getProdList() {
        return prodList;
    }

    public void setProdList(List<String> prodList) {
        this.prodList = prodList;
    }

    public String getStatusStr(){
        return SvrDataUtil.getZZDictCodeAndName(DictCode.报送数据状态, this.getStatus());
    }

    public String getSendStatusStr(){
        return SvrDataUtil.getZZDictCodeAndName(DictCode.人行报送状态, this.getSendStatus());
    }



    public String getFiduciaryDutyStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.人行管理方式,this.getFiduciaryDuty());
    }



    private String vdateStr;
    public String getVdateStr() {
        return DateUtil.format(this.getVdate());
    }

    public String getCdateStr() {
        return DateUtil.format(this.getCdate());
    }

    public String getMdateStr() {
        return DateUtil.format(this.getMdate());
    }

    public String getRaiseVdateStr() {
        return DateUtil.format(this.getRaiseVdate());
    }

    public String getRaiseEnddateStr() {
        return DateUtil.format(this.getRaiseEnddate());
    }

    private String dataKey;

    public void setVdateStr(String vdateStr) {
        this.vdateStr = vdateStr;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /***数据主键**/
    public String getDataKey(){
        return "产品代码:"+this.getPbcPordIdOut()+";信息类型:"+this.getInfoType()+";发行机构代码:"+this.getIssuePartyId()
                +";起息日:"+DateUtil.format(this.getVdate())+";数据状态:"+this.getStatus();
    }
}