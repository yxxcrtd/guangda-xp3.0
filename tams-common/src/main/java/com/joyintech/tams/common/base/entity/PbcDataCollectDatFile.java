package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;

public class PbcDataCollectDatFile extends BaseEntity {
    /**
     * 主键uuid
     */
    private String uuid;
    /**
     * Dat文件名称
     */
    private String datFileName;
    /**
     * Dat文件名称md5
     */
    private String datFileNameMd5;
    /**
     * 报表名称 type
     */
    private String reportName ;

    /**
     * DAT明细数
     */
    private Integer pibDetailCnt;

    private Date createDate;
    /**
     * 报送日期
     */
    private Date reportDate;

    /**
     * 报送状态
     */
    private String reportStatus;


    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 发行机构名称
     */
    private String issuePartyName;

    /**
     * 备注
     */
    private String remark;
    //生成日期起止
    private String createDateStart;

    private String createDateEnd;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDatFileName() {
        return datFileName;
    }

    public void setDatFileName(String datFileName) {
        this.datFileName = datFileName;
    }

    public String getDatFileNameMd5() {
        return datFileNameMd5;
    }

    public void setDatFileNameMd5(String datFileNameMd5) {
        this.datFileNameMd5 = datFileNameMd5;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Integer getPibDetailCnt() {
        return pibDetailCnt;
    }

    public void setPibDetailCnt(Integer pibDetailCnt) {
        this.pibDetailCnt = pibDetailCnt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * zip包状态
     * @return  zip包状态
     */
    public String getReportNameStr() {
        if(StringUtil.isNotEmpty(this.getReportName())){
            return SysDataUtil.getNameFromDataDict(DictCode.大数据采集平台报表类型, this.getReportName());
        }
        return "" ;
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getReportStatusStr() {
        if(StringUtil.isNotEmpty(this.getReportStatus())){
            return SysDataUtil.getNameFromDataDict(DictCode.报送状态, this.getReportStatus());
        }
        return "" ;
    }

    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }
}