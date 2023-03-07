package com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

@TableName("ZG05_PBC_COL_REPORT")
public class ZG05PbcColPrdAssetDebtDto extends PbcColReport implements ITableRow {

    private DecimalFormat df2 = new DecimalFormat("0.00");

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
     * 有效状态
     */
    private String statusStr;

    /**
     * 报送状态
     */
    @DictFormat(DictCode.报送状态)
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
     * 币种
     */
    @DictFormat(DictCode.币种_人行)
    private  String  ccy;

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
    /**
     * 人行产品代码+币种
     */
    private  String  pbcProdidAndCcy;
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

    public Date getPbcSubmitDeadline() {
        return pbcSubmitDeadline;
    }

    public void setPbcSubmitDeadline(Date pbcSubmitDeadline) {
        this.pbcSubmitDeadline = pbcSubmitDeadline;
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
     * @param  机构代码
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
        return null !=statusDate? DateUtil.formatDateTime(statusDate):null;
    }
    public String getSubmitTimeStr(){
        return null !=submitTime?DateUtil.formatDateTime(submitTime):null;
    }
    public String getChbSubmitDeadlineStr(){
        return null !=pbcSubmitDeadline?DateUtil.format(pbcSubmitDeadline):null;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }


    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getPbcProdidAndCcy() {
        return pbcProdidAndCcy;
    }

    public void setPbcProdidAndCcy(String pbcProdidAndCcy) {
        this.pbcProdidAndCcy = pbcProdidAndCcy;
    }

    /**
     * 币种
     */
    private  String  ccyStr;

    /**
     * 数据类型
     */
    @DictFormat(DictCode.数据类型_人行)
    private  String statusType;

    /**
     * 数据类型
     */
    private  String statusTypeStr;

    private  BigDecimal  a0000;

    private  BigDecimal  a1000;

    private  BigDecimal  a2000;

    private  BigDecimal  a2100;

    private  BigDecimal  a2110;

    private  BigDecimal  a2120;

    private  BigDecimal  a2200;

    private  BigDecimal  a2210;

    private  BigDecimal  a2220;

    private  BigDecimal  a3000;

    private  BigDecimal  a3100;

    private  BigDecimal  a3200;

    private  BigDecimal  a4000;

    private  BigDecimal  a4100;

    private  BigDecimal  a4200;

    private  BigDecimal  a4300;

    private  BigDecimal  a4400;

    private  BigDecimal  a4500;

    private  BigDecimal  a4600;

    private  BigDecimal  a4700;

    private  BigDecimal  a4800;

    private  BigDecimal  a5000;

    private  BigDecimal  a5100;

    private  BigDecimal  a5200;

    private  BigDecimal  a5210;

    private  BigDecimal  a5220;

    private  BigDecimal  a5230;

    private  BigDecimal  a5240;

    private  BigDecimal  a5250;

    private  BigDecimal  a5260;

    private  BigDecimal  a5261;

    private  BigDecimal  a5270;

    private  BigDecimal  a5271;

    private  BigDecimal  a5272;

    private  BigDecimal  a5273;

    private  BigDecimal  a5274;

    private  BigDecimal  a5275;

    private  BigDecimal  a5276;

    private  BigDecimal  a5277;

    private  BigDecimal  a5278;

    private  BigDecimal  a5279;

    private  BigDecimal  a527a;

    private  BigDecimal  a5280;

    private  BigDecimal  a6000;

    private  BigDecimal  a6100;

    private  BigDecimal  a6200;

    private  BigDecimal  a6300;

    private  BigDecimal  a6400;

    private  BigDecimal  a6500;

    private  BigDecimal  a6600;

    private  BigDecimal  a6700;

    private  BigDecimal  a7000;

    private  BigDecimal  a7100;

    private  BigDecimal  a7110;

    private  BigDecimal  a7120;

    private  BigDecimal  a7200;

    private  BigDecimal  a7210;

    private  BigDecimal  a7220;

    private  BigDecimal  a7230;

    private  BigDecimal  a7240;

    private  BigDecimal  a7250;

    private  BigDecimal  a7260;

    private  BigDecimal  a7270;

    private  BigDecimal  a7280;

    private  BigDecimal  a7290;

    private  BigDecimal  a72a0;

    private  BigDecimal  a7300;

    private  BigDecimal  a7310;

    private  BigDecimal  a7320;

    private  BigDecimal  a7400;

    private  BigDecimal  a8000;

    private  BigDecimal  a8100;

    private  BigDecimal  a8200;

    private  BigDecimal  a9000;

    private  BigDecimal  aa000;

    private  BigDecimal  ab000;

    private  BigDecimal  ac000;

    private  BigDecimal  d0000;

    private  BigDecimal  b0000;

    private  BigDecimal  b1000;

    private  BigDecimal  b1100;

    private  BigDecimal  b1200;

    private  BigDecimal  b1210;

    private  BigDecimal  b1220;

    private  BigDecimal  b1230;

    private  BigDecimal  b1240;

    private  BigDecimal  b1250;

    private  BigDecimal  b1260;

    private  BigDecimal  b1261;

    private  BigDecimal  b1270;

    private  BigDecimal  b1271;

    private  BigDecimal  b1272;

    private  BigDecimal  b1273;

    private  BigDecimal  b1274;

    private  BigDecimal  b1275;

    private  BigDecimal  b1276;

    private  BigDecimal  b1277;

    private  BigDecimal  b1278;

    private  BigDecimal  b1279;

    private  BigDecimal  b127a;

    private  BigDecimal  b1280;

    private  BigDecimal  b2000;

    private  BigDecimal  b3000;

    private  BigDecimal  b3100;

    private  BigDecimal  b3200;

    private  BigDecimal  b4000;

    private  BigDecimal  b5000;

    private  BigDecimal  c0000;

    private  BigDecimal  c1000;

    private  BigDecimal  c1100;

    private  BigDecimal  c1110;

    private  BigDecimal  c1120;

    private  BigDecimal  c1130;

    private  BigDecimal  c1140;

    private  BigDecimal  c1150;

    private  BigDecimal  c1160;

    private  BigDecimal  c1170;

    private  BigDecimal  c1171;

    private  BigDecimal  c1172;

    private  BigDecimal  c1173;

    private  BigDecimal  c1174;

    private  BigDecimal  c1175;

    private  BigDecimal  c1176;

    private  BigDecimal  c1177;

    private  BigDecimal  c1178;

    private  BigDecimal  c1179;

    private  BigDecimal  c117a;

    private  BigDecimal  c1180;

    private  BigDecimal  c1200;

    private  BigDecimal  c1210;

    private  BigDecimal  c1220;

    private  BigDecimal  c1230;

    private  BigDecimal  c1240;

    private  BigDecimal  c1250;

    private  BigDecimal  c1260;

    private  BigDecimal  c1270;

    private  BigDecimal  c1271;

    private  BigDecimal  c1272;

    private  BigDecimal  c1273;

    private  BigDecimal  c1274;

    private  BigDecimal  c1275;

    private  BigDecimal  c1276;

    private  BigDecimal  c1277;

    private  BigDecimal  c1278;

    private  BigDecimal  c1279;

    private  BigDecimal  c127a;

    private  BigDecimal  c1280;

    private  BigDecimal  c3000;

    private  BigDecimal  c4000;

    private String datuuid;


    public String getDatuuid() {
        return datuuid;
    }

    public void setDatuuid(String datuuid) {
        this.datuuid = datuuid;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public BigDecimal getA0000() {
        return a0000;
    }

    public void setA0000(BigDecimal a0000) {
        this.a0000 = a0000;
    }

    public BigDecimal getA1000() {
        return a1000;
    }

    public void setA1000(BigDecimal a1000) {
        this.a1000 = a1000;
    }

    public BigDecimal getA2000() {
        return a2000;
    }

    public void setA2000(BigDecimal a2000) {
        this.a2000 = a2000;
    }

    public BigDecimal getA2100() {
        return a2100;
    }

    public void setA2100(BigDecimal a2100) {
        this.a2100 = a2100;
    }

    public BigDecimal getA2110() {
        return a2110;
    }

    public void setA2110(BigDecimal a2110) {
        this.a2110 = a2110;
    }

    public BigDecimal getA2120() {
        return a2120;
    }

    public void setA2120(BigDecimal a2120) {
        this.a2120 = a2120;
    }

    public BigDecimal getA2200() {
        return a2200;
    }

    public void setA2200(BigDecimal a2200) {
        this.a2200 = a2200;
    }

    public BigDecimal getA2210() {
        return a2210;
    }

    public void setA2210(BigDecimal a2210) {
        this.a2210 = a2210;
    }

    public BigDecimal getA2220() {
        return a2220;
    }

    public void setA2220(BigDecimal a2220) {
        this.a2220 = a2220;
    }

    public BigDecimal getA3000() {
        return a3000;
    }

    public void setA3000(BigDecimal a3000) {
        this.a3000 = a3000;
    }

    public BigDecimal getA3100() {
        return a3100;
    }

    public void setA3100(BigDecimal a3100) {
        this.a3100 = a3100;
    }

    public BigDecimal getA3200() {
        return a3200;
    }

    public void setA3200(BigDecimal a3200) {
        this.a3200 = a3200;
    }

    public BigDecimal getA4000() {
        return a4000;
    }

    public void setA4000(BigDecimal a4000) {
        this.a4000 = a4000;
    }

    public BigDecimal getA4100() {
        return a4100;
    }

    public void setA4100(BigDecimal a4100) {
        this.a4100 = a4100;
    }

    public BigDecimal getA4200() {
        return a4200;
    }

    public void setA4200(BigDecimal a4200) {
        this.a4200 = a4200;
    }

    public BigDecimal getA4300() {
        return a4300;
    }

    public void setA4300(BigDecimal a4300) {
        this.a4300 = a4300;
    }

    public BigDecimal getA4400() {
        return a4400;
    }

    public void setA4400(BigDecimal a4400) {
        this.a4400 = a4400;
    }

    public BigDecimal getA4500() {
        return a4500;
    }

    public void setA4500(BigDecimal a4500) {
        this.a4500 = a4500;
    }

    public BigDecimal getA4600() {
        return a4600;
    }

    public void setA4600(BigDecimal a4600) {
        this.a4600 = a4600;
    }

    public BigDecimal getA4700() {
        return a4700;
    }

    public void setA4700(BigDecimal a4700) {
        this.a4700 = a4700;
    }

    public BigDecimal getA4800() {
        return a4800;
    }

    public void setA4800(BigDecimal a4800) {
        this.a4800 = a4800;
    }

    public BigDecimal getA5000() {
        return a5000;
    }

    public void setA5000(BigDecimal a5000) {
        this.a5000 = a5000;
    }

    public BigDecimal getA5100() {
        return a5100;
    }

    public void setA5100(BigDecimal a5100) {
        this.a5100 = a5100;
    }

    public BigDecimal getA5200() {
        return a5200;
    }

    public void setA5200(BigDecimal a5200) {
        this.a5200 = a5200;
    }

    public BigDecimal getA5210() {
        return a5210;
    }

    public void setA5210(BigDecimal a5210) {
        this.a5210 = a5210;
    }

    public BigDecimal getA5220() {
        return a5220;
    }

    public void setA5220(BigDecimal a5220) {
        this.a5220 = a5220;
    }

    public BigDecimal getA5230() {
        return a5230;
    }

    public void setA5230(BigDecimal a5230) {
        this.a5230 = a5230;
    }

    public BigDecimal getA5240() {
        return a5240;
    }

    public void setA5240(BigDecimal a5240) {
        this.a5240 = a5240;
    }

    public BigDecimal getA5250() {
        return a5250;
    }

    public void setA5250(BigDecimal a5250) {
        this.a5250 = a5250;
    }

    public BigDecimal getA5260() {
        return a5260;
    }

    public void setA5260(BigDecimal a5260) {
        this.a5260 = a5260;
    }

    public BigDecimal getA5261() {
        return a5261;
    }

    public void setA5261(BigDecimal a5261) {
        this.a5261 = a5261;
    }

    public BigDecimal getA5270() {
        return a5270;
    }

    public void setA5270(BigDecimal a5270) {
        this.a5270 = a5270;
    }

    public BigDecimal getA5271() {
        return a5271;
    }

    public void setA5271(BigDecimal a5271) {
        this.a5271 = a5271;
    }

    public BigDecimal getA5272() {
        return a5272;
    }

    public void setA5272(BigDecimal a5272) {
        this.a5272 = a5272;
    }

    public BigDecimal getA5273() {
        return a5273;
    }

    public void setA5273(BigDecimal a5273) {
        this.a5273 = a5273;
    }

    public BigDecimal getA5274() {
        return a5274;
    }

    public void setA5274(BigDecimal a5274) {
        this.a5274 = a5274;
    }

    public BigDecimal getA5275() {
        return a5275;
    }

    public void setA5275(BigDecimal a5275) {
        this.a5275 = a5275;
    }

    public BigDecimal getA5276() {
        return a5276;
    }

    public void setA5276(BigDecimal a5276) {
        this.a5276 = a5276;
    }

    public BigDecimal getA5277() {
        return a5277;
    }

    public void setA5277(BigDecimal a5277) {
        this.a5277 = a5277;
    }

    public BigDecimal getA5278() {
        return a5278;
    }

    public void setA5278(BigDecimal a5278) {
        this.a5278 = a5278;
    }

    public BigDecimal getA5279() {
        return a5279;
    }

    public void setA5279(BigDecimal a5279) {
        this.a5279 = a5279;
    }

    public BigDecimal getA527a() {
        return a527a;
    }

    public void setA527a(BigDecimal a527a) {
        this.a527a = a527a;
    }

    public BigDecimal getA5280() {
        return a5280;
    }

    public void setA5280(BigDecimal a5280) {
        this.a5280 = a5280;
    }

    public BigDecimal getA6000() {
        return a6000;
    }

    public void setA6000(BigDecimal a6000) {
        this.a6000 = a6000;
    }

    public BigDecimal getA6100() {
        return a6100;
    }

    public void setA6100(BigDecimal a6100) {
        this.a6100 = a6100;
    }

    public BigDecimal getA6200() {
        return a6200;
    }

    public void setA6200(BigDecimal a6200) {
        this.a6200 = a6200;
    }

    public BigDecimal getA6300() {
        return a6300;
    }

    public void setA6300(BigDecimal a6300) {
        this.a6300 = a6300;
    }

    public BigDecimal getA6400() {
        return a6400;
    }

    public void setA6400(BigDecimal a6400) {
        this.a6400 = a6400;
    }

    public BigDecimal getA6500() {
        return a6500;
    }

    public void setA6500(BigDecimal a6500) {
        this.a6500 = a6500;
    }

    public BigDecimal getA6600() {
        return a6600;
    }

    public void setA6600(BigDecimal a6600) {
        this.a6600 = a6600;
    }

    public BigDecimal getA6700() {
        return a6700;
    }

    public void setA6700(BigDecimal a6700) {
        this.a6700 = a6700;
    }

    public BigDecimal getA7000() {
        return a7000;
    }

    public void setA7000(BigDecimal a7000) {
        this.a7000 = a7000;
    }

    public BigDecimal getA7100() {
        return a7100;
    }

    public void setA7100(BigDecimal a7100) {
        this.a7100 = a7100;
    }

    public BigDecimal getA7110() {
        return a7110;
    }

    public void setA7110(BigDecimal a7110) {
        this.a7110 = a7110;
    }

    public BigDecimal getA7120() {
        return a7120;
    }

    public void setA7120(BigDecimal a7120) {
        this.a7120 = a7120;
    }

    public BigDecimal getA7200() {
        return a7200;
    }

    public void setA7200(BigDecimal a7200) {
        this.a7200 = a7200;
    }

    public BigDecimal getA7210() {
        return a7210;
    }

    public void setA7210(BigDecimal a7210) {
        this.a7210 = a7210;
    }

    public BigDecimal getA7220() {
        return a7220;
    }

    public void setA7220(BigDecimal a7220) {
        this.a7220 = a7220;
    }

    public BigDecimal getA7230() {
        return a7230;
    }

    public void setA7230(BigDecimal a7230) {
        this.a7230 = a7230;
    }

    public BigDecimal getA7240() {
        return a7240;
    }

    public void setA7240(BigDecimal a7240) {
        this.a7240 = a7240;
    }

    public BigDecimal getA7250() {
        return a7250;
    }

    public void setA7250(BigDecimal a7250) {
        this.a7250 = a7250;
    }

    public BigDecimal getA7260() {
        return a7260;
    }

    public void setA7260(BigDecimal a7260) {
        this.a7260 = a7260;
    }

    public BigDecimal getA7270() {
        return a7270;
    }

    public void setA7270(BigDecimal a7270) {
        this.a7270 = a7270;
    }

    public BigDecimal getA7280() {
        return a7280;
    }

    public void setA7280(BigDecimal a7280) {
        this.a7280 = a7280;
    }

    public BigDecimal getA7290() {
        return a7290;
    }

    public void setA7290(BigDecimal a7290) {
        this.a7290 = a7290;
    }

    public BigDecimal getA72a0() {
        return a72a0;
    }

    public void setA72a0(BigDecimal a72a0) {
        this.a72a0 = a72a0;
    }

    public BigDecimal getA7300() {
        return a7300;
    }

    public void setA7300(BigDecimal a7300) {
        this.a7300 = a7300;
    }

    public BigDecimal getA7310() {
        return a7310;
    }

    public void setA7310(BigDecimal a7310) {
        this.a7310 = a7310;
    }

    public BigDecimal getA7320() {
        return a7320;
    }

    public void setA7320(BigDecimal a7320) {
        this.a7320 = a7320;
    }

    public BigDecimal getA7400() {
        return a7400;
    }

    public void setA7400(BigDecimal a7400) {
        this.a7400 = a7400;
    }

    public BigDecimal getA8000() {
        return a8000;
    }

    public void setA8000(BigDecimal a8000) {
        this.a8000 = a8000;
    }

    public BigDecimal getA8100() {
        return a8100;
    }

    public void setA8100(BigDecimal a8100) {
        this.a8100 = a8100;
    }

    public BigDecimal getA8200() {
        return a8200;
    }

    public void setA8200(BigDecimal a8200) {
        this.a8200 = a8200;
    }

    public BigDecimal getA9000() {
        return a9000;
    }

    public void setA9000(BigDecimal a9000) {
        this.a9000 = a9000;
    }

    public BigDecimal getAa000() {
        return aa000;
    }

    public void setAa000(BigDecimal aa000) {
        this.aa000 = aa000;
    }

    public BigDecimal getAb000() {
        return ab000;
    }

    public void setAb000(BigDecimal ab000) {
        this.ab000 = ab000;
    }

    public BigDecimal getAc000() {
        return ac000;
    }

    public void setAc000(BigDecimal ac000) {
        this.ac000 = ac000;
    }

    public BigDecimal getD0000() {
        return d0000;
    }

    public void setD0000(BigDecimal d0000) {
        this.d0000 = d0000;
    }

    public BigDecimal getB0000() {
        return b0000;
    }

    public void setB0000(BigDecimal b0000) {
        this.b0000 = b0000;
    }

    public BigDecimal getB1000() {
        return b1000;
    }

    public void setB1000(BigDecimal b1000) {
        this.b1000 = b1000;
    }

    public BigDecimal getB1100() {
        return b1100;
    }

    public void setB1100(BigDecimal b1100) {
        this.b1100 = b1100;
    }

    public BigDecimal getB1200() {
        return b1200;
    }

    public void setB1200(BigDecimal b1200) {
        this.b1200 = b1200;
    }

    public BigDecimal getB1210() {
        return b1210;
    }

    public void setB1210(BigDecimal b1210) {
        this.b1210 = b1210;
    }

    public BigDecimal getB1220() {
        return b1220;
    }

    public void setB1220(BigDecimal b1220) {
        this.b1220 = b1220;
    }

    public BigDecimal getB1230() {
        return b1230;
    }

    public void setB1230(BigDecimal b1230) {
        this.b1230 = b1230;
    }

    public BigDecimal getB1240() {
        return b1240;
    }

    public void setB1240(BigDecimal b1240) {
        this.b1240 = b1240;
    }

    public BigDecimal getB1250() {
        return b1250;
    }

    public void setB1250(BigDecimal b1250) {
        this.b1250 = b1250;
    }

    public BigDecimal getB1260() {
        return b1260;
    }

    public void setB1260(BigDecimal b1260) {
        this.b1260 = b1260;
    }

    public BigDecimal getB1261() {
        return b1261;
    }

    public void setB1261(BigDecimal b1261) {
        this.b1261 = b1261;
    }

    public BigDecimal getB1270() {
        return b1270;
    }

    public void setB1270(BigDecimal b1270) {
        this.b1270 = b1270;
    }

    public BigDecimal getB1271() {
        return b1271;
    }

    public void setB1271(BigDecimal b1271) {
        this.b1271 = b1271;
    }

    public BigDecimal getB1272() {
        return b1272;
    }

    public void setB1272(BigDecimal b1272) {
        this.b1272 = b1272;
    }

    public BigDecimal getB1273() {
        return b1273;
    }

    public void setB1273(BigDecimal b1273) {
        this.b1273 = b1273;
    }

    public BigDecimal getB1274() {
        return b1274;
    }

    public void setB1274(BigDecimal b1274) {
        this.b1274 = b1274;
    }

    public BigDecimal getB1275() {
        return b1275;
    }

    public void setB1275(BigDecimal b1275) {
        this.b1275 = b1275;
    }

    public BigDecimal getB1276() {
        return b1276;
    }

    public void setB1276(BigDecimal b1276) {
        this.b1276 = b1276;
    }

    public BigDecimal getB1277() {
        return b1277;
    }

    public void setB1277(BigDecimal b1277) {
        this.b1277 = b1277;
    }

    public BigDecimal getB1278() {
        return b1278;
    }

    public void setB1278(BigDecimal b1278) {
        this.b1278 = b1278;
    }

    public BigDecimal getB1279() {
        return b1279;
    }

    public void setB1279(BigDecimal b1279) {
        this.b1279 = b1279;
    }

    public BigDecimal getB127a() {
        return b127a;
    }

    public void setB127a(BigDecimal b127a) {
        this.b127a = b127a;
    }

    public BigDecimal getB1280() {
        return b1280;
    }

    public void setB1280(BigDecimal b1280) {
        this.b1280 = b1280;
    }

    public BigDecimal getB2000() {
        return b2000;
    }

    public void setB2000(BigDecimal b2000) {
        this.b2000 = b2000;
    }

    public BigDecimal getB3000() {
        return b3000;
    }

    public void setB3000(BigDecimal b3000) {
        this.b3000 = b3000;
    }

    public BigDecimal getB3100() {
        return b3100;
    }

    public void setB3100(BigDecimal b3100) {
        this.b3100 = b3100;
    }

    public BigDecimal getB3200() {
        return b3200;
    }

    public void setB3200(BigDecimal b3200) {
        this.b3200 = b3200;
    }

    public BigDecimal getB4000() {
        return b4000;
    }

    public void setB4000(BigDecimal b4000) {
        this.b4000 = b4000;
    }

    public BigDecimal getB5000() {
        return b5000;
    }

    public void setB5000(BigDecimal b5000) {
        this.b5000 = b5000;
    }

    public BigDecimal getC0000() {
        return c0000;
    }

    public void setC0000(BigDecimal c0000) {
        this.c0000 = c0000;
    }

    public BigDecimal getC1000() {
        return c1000;
    }

    public void setC1000(BigDecimal c1000) {
        this.c1000 = c1000;
    }

    public BigDecimal getC1100() {
        return c1100;
    }

    public void setC1100(BigDecimal c1100) {
        this.c1100 = c1100;
    }

    public BigDecimal getC1110() {
        return c1110;
    }

    public void setC1110(BigDecimal c1110) {
        this.c1110 = c1110;
    }

    public BigDecimal getC1120() {
        return c1120;
    }

    public void setC1120(BigDecimal c1120) {
        this.c1120 = c1120;
    }

    public BigDecimal getC1130() {
        return c1130;
    }

    public void setC1130(BigDecimal c1130) {
        this.c1130 = c1130;
    }

    public BigDecimal getC1140() {
        return c1140;
    }

    public void setC1140(BigDecimal c1140) {
        this.c1140 = c1140;
    }

    public BigDecimal getC1150() {
        return c1150;
    }

    public void setC1150(BigDecimal c1150) {
        this.c1150 = c1150;
    }

    public BigDecimal getC1160() {
        return c1160;
    }

    public void setC1160(BigDecimal c1160) {
        this.c1160 = c1160;
    }

    public BigDecimal getC1170() {
        return c1170;
    }

    public void setC1170(BigDecimal c1170) {
        this.c1170 = c1170;
    }

    public BigDecimal getC1171() {
        return c1171;
    }

    public void setC1171(BigDecimal c1171) {
        this.c1171 = c1171;
    }

    public BigDecimal getC1172() {
        return c1172;
    }

    public void setC1172(BigDecimal c1172) {
        this.c1172 = c1172;
    }

    public BigDecimal getC1173() {
        return c1173;
    }

    public void setC1173(BigDecimal c1173) {
        this.c1173 = c1173;
    }

    public BigDecimal getC1174() {
        return c1174;
    }

    public void setC1174(BigDecimal c1174) {
        this.c1174 = c1174;
    }

    public BigDecimal getC1175() {
        return c1175;
    }

    public void setC1175(BigDecimal c1175) {
        this.c1175 = c1175;
    }

    public BigDecimal getC1176() {
        return c1176;
    }

    public void setC1176(BigDecimal c1176) {
        this.c1176 = c1176;
    }

    public BigDecimal getC1177() {
        return c1177;
    }

    public void setC1177(BigDecimal c1177) {
        this.c1177 = c1177;
    }

    public BigDecimal getC1178() {
        return c1178;
    }

    public void setC1178(BigDecimal c1178) {
        this.c1178 = c1178;
    }

    public BigDecimal getC1179() {
        return c1179;
    }

    public void setC1179(BigDecimal c1179) {
        this.c1179 = c1179;
    }

    public BigDecimal getC117a() {
        return c117a;
    }

    public void setC117a(BigDecimal c117a) {
        this.c117a = c117a;
    }

    public BigDecimal getC1180() {
        return c1180;
    }

    public void setC1180(BigDecimal c1180) {
        this.c1180 = c1180;
    }

    public BigDecimal getC1200() {
        return c1200;
    }

    public void setC1200(BigDecimal c1200) {
        this.c1200 = c1200;
    }

    public BigDecimal getC1210() {
        return c1210;
    }

    public void setC1210(BigDecimal c1210) {
        this.c1210 = c1210;
    }

    public BigDecimal getC1220() {
        return c1220;
    }

    public void setC1220(BigDecimal c1220) {
        this.c1220 = c1220;
    }

    public BigDecimal getC1230() {
        return c1230;
    }

    public void setC1230(BigDecimal c1230) {
        this.c1230 = c1230;
    }

    public BigDecimal getC1240() {
        return c1240;
    }

    public void setC1240(BigDecimal c1240) {
        this.c1240 = c1240;
    }

    public BigDecimal getC1250() {
        return c1250;
    }

    public void setC1250(BigDecimal c1250) {
        this.c1250 = c1250;
    }

    public BigDecimal getC1260() {
        return c1260;
    }

    public void setC1260(BigDecimal c1260) {
        this.c1260 = c1260;
    }

    public BigDecimal getC1270() {
        return c1270;
    }

    public void setC1270(BigDecimal c1270) {
        this.c1270 = c1270;
    }

    public BigDecimal getC1271() {
        return c1271;
    }

    public void setC1271(BigDecimal c1271) {
        this.c1271 = c1271;
    }

    public BigDecimal getC1272() {
        return c1272;
    }

    public void setC1272(BigDecimal c1272) {
        this.c1272 = c1272;
    }

    public BigDecimal getC1273() {
        return c1273;
    }

    public void setC1273(BigDecimal c1273) {
        this.c1273 = c1273;
    }

    public BigDecimal getC1274() {
        return c1274;
    }

    public void setC1274(BigDecimal c1274) {
        this.c1274 = c1274;
    }

    public BigDecimal getC1275() {
        return c1275;
    }

    public void setC1275(BigDecimal c1275) {
        this.c1275 = c1275;
    }

    public BigDecimal getC1276() {
        return c1276;
    }

    public void setC1276(BigDecimal c1276) {
        this.c1276 = c1276;
    }

    public BigDecimal getC1277() {
        return c1277;
    }

    public void setC1277(BigDecimal c1277) {
        this.c1277 = c1277;
    }

    public BigDecimal getC1278() {
        return c1278;
    }

    public void setC1278(BigDecimal c1278) {
        this.c1278 = c1278;
    }

    public BigDecimal getC1279() {
        return c1279;
    }

    public void setC1279(BigDecimal c1279) {
        this.c1279 = c1279;
    }

    public BigDecimal getC127a() {
        return c127a;
    }

    public void setC127a(BigDecimal c127a) {
        this.c127a = c127a;
    }

    public BigDecimal getC1280() {
        return c1280;
    }

    public void setC1280(BigDecimal c1280) {
        this.c1280 = c1280;
    }

    public BigDecimal getC3000() {
        return c3000;
    }

    public void setC3000(BigDecimal c3000) {
        this.c3000 = c3000;
    }

    public BigDecimal getC4000() {
        return c4000;
    }

    public void setC4000(BigDecimal c4000) {
        this.c4000 = c4000;
    }


    public String getCcyStr() {
        return ccyStr;
    }

    public void setCcyStr(String ccyStr) {
        this.ccyStr = ccyStr;
    }

    public String getStatusTypeStr() {
        return statusTypeStr;
    }

    public void setStatusTypeStr(String statusTypeStr) {
        this.statusTypeStr = statusTypeStr;
    }

    public String getA0000Str() {
        return a0000==null?"":df2.format(a0000);
    }

    public String getA1000Str() {
        return a1000==null?"":df2.format(a1000);
    }

    public String getA2000Str() {
        return a2000==null?"":df2.format(a2000);
    }

    public String getA2100Str() {
        return a2100==null?"":df2.format(a2100);
    }

    public String getA2110Str() {
        return a2110==null?"":df2.format(a2110);
    }

    public String getA2120Str() {
        return a2120==null?"":df2.format(a2120);
    }

    public String getA2200Str() {
        return a2200==null?"":df2.format(a2200);
    }

    public String getA2210Str() {
        return a2210==null?"":df2.format(a2210);
    }

    public String getA2220Str() {
        return a2220==null?"":df2.format(a2220);
    }

    public String getA3000Str() {
        return a3000==null?"":df2.format(a3000);
    }

    public String getA3100Str() {
        return a3100==null?"":df2.format(a3100);
    }

    public String getA3200Str() {
        return a3200==null?"":df2.format(a3200);
    }

    public String getA4000Str() {
        return a4000==null?"":df2.format(a4000);
    }

    public String getA4100Str() {
        return a4100==null?"":df2.format(a4100);
    }

    public String getA4200Str() {
        return a4200==null?"":df2.format(a4200);
    }

    public String getA4300Str() {
        return a4300==null?"":df2.format(a4300);
    }

    public String getA4400Str() {
        return a4400==null?"":df2.format(a4400);
    }

    public String getA4500Str() {
        return a4500==null?"":df2.format(a4500);
    }

    public String getA4600Str() {
        return a4600==null?"":df2.format(a4600);
    }

    public String getA4700Str() {
        return a4700==null?"":df2.format(a4700);
    }

    public String getA4800Str() {
        return a4800==null?"":df2.format(a4800);
    }

    public String getA5000Str() {
        return a5000==null?"":df2.format(a5000);
    }

    public String getA5100Str() {
        return a5100==null?"":df2.format(a5100);
    }

    public String getA5200Str() {
        return a5200==null?"":df2.format(a5200);
    }

    public String getA5210Str() {
        return a5210==null?"":df2.format(a5210);
    }

    public String getA5220Str() {
        return a5220==null?"":df2.format(a5220);
    }

    public String getA5230Str() {
        return a5230==null?"":df2.format(a5230);
    }

    public String getA5240Str() {
        return a5240==null?"":df2.format(a5240);
    }

    public String getA5250Str() {
        return a5250==null?"":df2.format(a5250);
    }

    public String getA5260Str() {
        return a5260==null?"":df2.format(a5260);
    }

    public String getA5261Str() {
        return a5261==null?"":df2.format(a5261);
    }

    public String getA5270Str() {
        return a5270==null?"":df2.format(a5270);
    }

    public String getA5271Str() {
        return a5271==null?"":df2.format(a5271);
    }

    public String getA5272Str() {
        return a5272==null?"":df2.format(a5272);
    }

    public String getA5273Str() {
        return a5273==null?"":df2.format(a5273);
    }

    public String getA5274Str() {
        return a5274==null?"":df2.format(a5274);
    }

    public String getA5275Str() {
        return a5275==null?"":df2.format(a5275);
    }

    public String getA5276Str() {
        return a5276==null?"":df2.format(a5276);
    }

    public String getA5277Str() {
        return a5277==null?"":df2.format(a5277);
    }

    public String getA5278Str() {
        return a5278==null?"":df2.format(a5278);
    }

    public String getA5279Str() {
        return a5279==null?"":df2.format(a5279);
    }

    public String getA527aStr() {
        return a527a==null?"":df2.format(a527a);
    }

    public String getA5280Str() {
        return a5280==null?"":df2.format(a5280);
    }

    public String getA6000Str() {
        return a6000==null?"":df2.format(a6000);
    }

    public String getA6100Str() {
        return a6100==null?"":df2.format(a6100);
    }

    public String getA6200Str() {
        return a6200==null?"":df2.format(a6200);
    }

    public String getA6300Str() {
        return a6300==null?"":df2.format(a6300);
    }

    public String getA6400Str() {
        return a6400==null?"":df2.format(a6400);
    }

    public String getA6500Str() {
        return a6500==null?"":df2.format(a6500);
    }

    public String getA6600Str() {
        return a6600==null?"":df2.format(a6600);
    }

    public String getA6700Str() {
        return a6700==null?"":df2.format(a6700);
    }

    public String getA7000Str() {
        return a7000==null?"":df2.format(a7000);
    }

    public String getA7100Str() {
        return a7100==null?"":df2.format(a7100);
    }

    public String getA7110Str() {
        return a7110==null?"":df2.format(a7110);
    }

    public String getA7120Str() {
        return a7120==null?"":df2.format(a7120);
    }

    public String getA7200Str() {
        return a7200==null?"":df2.format(a7200);
    }

    public String getA7210Str() {
        return a7210==null?"":df2.format(a7210);
    }

    public String getA7220Str() {
        return a7220==null?"":df2.format(a7220);
    }

    public String getA7230Str() {
        return a7230==null?"":df2.format(a7230);
    }

    public String getA7240Str() {
        return a7240==null?"":df2.format(a7240);
    }

    public String getA7250Str() {
        return a7250==null?"":df2.format(a7250);
    }

    public String getA7260Str() {
        return a7260==null?"":df2.format(a7260);
    }

    public String getA7270Str() {
        return a7270==null?"":df2.format(a7270);
    }

    public String getA7280Str() {
        return a7280==null?"":df2.format(a7280);
    }

    public String getA7290Str() {
        return a7290==null?"":df2.format(a7290);
    }

    public String getA72a0Str() {
        return a72a0==null?"":df2.format(a72a0);
    }

    public String getA7300Str() {
        return a7300==null?"":df2.format(a7300);
    }

    public String getA7310Str() {
        return a7310==null?"":df2.format(a7310);
    }

    public String getA7320Str() {
        return a7320==null?"":df2.format(a7320);
    }

    public String getA7400Str() {
        return a7400==null?"":df2.format(a7400);
    }

    public String getA8000Str() {
        return a8000==null?"":df2.format(a8000);
    }

    public String getA8100Str() {
        return a8100==null?"":df2.format(a8100);
    }

    public String getA8200Str() {
        return a8200==null?"":df2.format(a8200);
    }

    public String getA9000Str() {
        return a9000==null?"":df2.format(a9000);
    }

    public String getAa000Str() {
        return aa000==null?"":df2.format(aa000);
    }

    public String getAb000Str() {
        return ab000==null?"":df2.format(ab000);
    }

    public String getAc000Str() {
        return ac000==null?"":df2.format(ac000);
    }

    public String getD0000Str() {
        return d0000==null?"":df2.format(d0000);
    }

    public String getB0000Str() {
        return b0000==null?"":df2.format(b0000);
    }

    public String getB1000Str() {
        return b1000==null?"":df2.format(b1000);
    }

    public String getB1100Str() {
        return b1100==null?"":df2.format(b1100);
    }

    public String getB1200Str() {
        return b1200==null?"":df2.format(b1200);
    }

    public String getB1210Str() {
        return b1210==null?"":df2.format(b1210);
    }

    public String getB1220Str() {
        return b1220==null?"":df2.format(b1220);
    }

    public String getB1230Str() {
        return b1230==null?"":df2.format(b1230);
    }

    public String getB1240Str() {
        return b1240==null?"":df2.format(b1240);
    }

    public String getB1250Str() {
        return b1250==null?"":df2.format(b1250);
    }

    public String getB1260Str() {
        return b1260==null?"":df2.format(b1260);
    }

    public String getB1261Str() {
        return b1261==null?"":df2.format(b1261);
    }

    public String getB1270Str() {
        return b1270==null?"":df2.format(b1270);
    }

    public String getB1271Str() {
        return b1271==null?"":df2.format(b1271);
    }

    public String getB1272Str() {
        return b1272==null?"":df2.format(b1272);
    }

    public String getB1273Str() {
        return b1273==null?"":df2.format(b1273);
    }

    public String getB1274Str() {
        return b1274==null?"":df2.format(b1274);
    }

    public String getB1275Str() {
        return b1275==null?"":df2.format(b1275);
    }

    public String getB1276Str() {
        return b1276==null?"":df2.format(b1276);
    }

    public String getB1277Str() {
        return b1277==null?"":df2.format(b1277);
    }

    public String getB1278Str() {
        return b1278==null?"":df2.format(b1278);
    }

    public String getB1279Str() {
        return b1279==null?"":df2.format(b1279);
    }

    public String getB127aStr() {
        return b127a==null?"":df2.format(b127a);
    }

    public String getB1280Str() {
        return b1280==null?"":df2.format(b1280);
    }

    public String getB2000Str() {
        return b2000==null?"":df2.format(b2000);
    }

    public String getB3000Str() {
        return b3000==null?"":df2.format(b3000);
    }

    public String getB3100Str() {
        return b3100==null?"":df2.format(b3100);
    }

    public String getB3200Str() {
        return b3200==null?"":df2.format(b3200);
    }

    public String getB4000Str() {
        return b4000==null?"":df2.format(b4000);
    }

    public String getB5000Str() {
        return b5000==null?"":df2.format(b5000);
    }

    public String getC0000Str() {
        return c0000==null?"":df2.format(c0000);
    }

    public String getC1000Str() {
        return c1000==null?"":df2.format(c1000);
    }

    public String getC1100Str() {
        return c1100==null?"":df2.format(c1100);
    }

    public String getC1110Str() {
        return c1110==null?"":df2.format(c1110);
    }

    public String getC1120Str() {
        return c1120==null?"":df2.format(c1120);
    }

    public String getC1130Str() {
        return c1130==null?"":df2.format(c1130);
    }

    public String getC1140Str() {
        return c1140==null?"":df2.format(c1140);
    }

    public String getC1150Str() {
        return c1150==null?"":df2.format(c1150);
    }

    public String getC1160Str() {
        return c1160==null?"":df2.format(c1160);
    }

    public String getC1170Str() {
        return c1170==null?"":df2.format(c1170);
    }

    public String getC1171Str() {
        return c1171==null?"":df2.format(c1171);
    }

    public String getC1172Str() {
        return c1172==null?"":df2.format(c1172);
    }

    public String getC1173Str() {
        return c1173==null?"":df2.format(c1173);
    }

    public String getC1174Str() {
        return c1174==null?"":df2.format(c1174);
    }

    public String getC1175Str() {
        return c1175==null?"":df2.format(c1175);
    }

    public String getC1176Str() {
        return c1176==null?"":df2.format(c1176);
    }

    public String getC1177Str() {
        return c1177==null?"":df2.format(c1177);
    }

    public String getC1178Str() {
        return c1178==null?"":df2.format(c1178);
    }

    public String getC1179Str() {
        return c1179==null?"":df2.format(c1179);
    }

    public String getC117aStr() {
        return c117a==null?"":df2.format(c117a);
    }

    public String getC1180Str() {
        return c1180==null?"":df2.format(c1180);
    }

    public String getC1200Str() {
        return c1200==null?"":df2.format(c1200);
    }

    public String getC1210Str() {
        return c1210==null?"":df2.format(c1210);
    }

    public String getC1220Str() {
        return c1220==null?"":df2.format(c1220);
    }

    public String getC1230Str() {
        return c1230==null?"":df2.format(c1230);
    }

    public String getC1240Str() {
        return c1240==null?"":df2.format(c1240);
    }

    public String getC1250Str() {
        return c1250==null?"":df2.format(c1250);
    }

    public String getC1260Str() {
        return c1260==null?"":df2.format(c1260);
    }

    public String getC1270Str() {
        return c1270==null?"":df2.format(c1270);
    }

    public String getC1271Str() {
        return c1271==null?"":df2.format(c1271);
    }

    public String getC1272Str() {
        return c1272==null?"":df2.format(c1272);
    }

    public String getC1273Str() {
        return c1273==null?"":df2.format(c1273);
    }

    public String getC1274Str() {
        return c1274==null?"":df2.format(c1274);
    }

    public String getC1275Str() {
        return c1275==null?"":df2.format(c1275);
    }

    public String getC1276Str() {
        return c1276==null?"":df2.format(c1276);
    }

    public String getC1277Str() {
        return c1277==null?"":df2.format(c1277);
    }

    public String getC1278Str() {
        return c1278==null?"":df2.format(c1278);
    }

    public String getC1279Str() {
        return c1279==null?"":df2.format(c1279);
    }

    public String getC127aStr() {
        return c127a==null?"":df2.format(c127a);
    }

    public String getC1280Str() {
        return c1280==null?"":df2.format(c1280);
    }

    public String getC3000Str() {
        return c3000==null?"":df2.format(c3000);
    }

    public String getC4000Str() {
        return c4000==null?"":df2.format(c4000);
    }

    private String dataKey;

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /***数据主键**/
    public String getDataKey(){
        return "产品代码:"+this.getPbcPordIdOut()+";币种:"+this.getCcy()+";数据类型:"+this.getStatusType()
                +";统计年月:"+ this.getTermid();
    }
}
