package com.joyintech.tams.svreport.pbc.classic.zgdatfile.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.report.common.base.annotate.TableName;

import java.util.Date;

/**
 * @ClassName PbcDataCollectDatFileDto
 * @Description: 采集平台人行dat文件生成主表
 * @Author yanya
 * @Date 2022-11-12
 * @Version V1.0
 **/
@TableName("PBC_DATA_COLLECT_DAT_FILE")
public class PbcDataCollectDatFileDto extends BaseEntity {
    private String uuid;//主键
    private String datFileName;//Dat文件名称
    private String datFileNameMd5;//文件名称MD5码
    private String reportName;//生成日期
    private Date createDate;//生成日期
    private Date reportDate;//报送日期
    private String reportStatus;//报送状态
    //private String filepath;//文件存放路径
    private String remark;//备注

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

//    public String getFilepath() {
//        return filepath;
//    }
//
//    public void setFilepath(String filepath) {
//        this.filepath = filepath;
//    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
