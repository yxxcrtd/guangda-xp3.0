package com.joyintech.tams.svreport.gd.risk.valtblimport.dto;

import com.joyintech.tams.common.base.entity.ChbAssetInfo;

import java.util.Date;

/**
 * 批量上传文件资产信息
 *
 * @author swwan
 * @className ZipFileAssetInfoDto
 * @date 2021/6/18 15:40
 */
public class ZipFileAssetInfoDto {
    public ZipFileAssetInfoDto() {
    }

    public ZipFileAssetInfoDto(String fileName, ChbAssetInfo info, Date date) {
        this.fileName = fileName;
        this.info = info;
        this.date = date;
    }

    private String fileName;
    private ChbAssetInfo info;
    private Date date;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ChbAssetInfo getInfo() {
        return info;
    }

    public void setInfo(ChbAssetInfo info) {
        this.info = info;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
