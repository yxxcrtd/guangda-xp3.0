package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RskValtblAutoImportLog extends BaseEntity {
    /**
     * id
     */
    private String id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 资管计划
     */
    private String ptlId;

    /**
     * 估值日期
     */
    private Date valDate;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 导入描述
     */
    private byte[] message;

    /**
     * 取得 id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 id
     * @param id id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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
     * 取得 资管计划
     * @return 资管计划
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 资管计划
     * @param ptlId 资管计划
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId == null ? null : ptlId.trim();
    }

    /**
     * 取得 估值日期
     * @return 估值日期
     */
    public Date getValDate() {
        return valDate;
    }

    /**
     * 设置 估值日期
     * @param valDate 估值日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    /**
     * 取得 文件路径
     * @return 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置 文件路径
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * 取得 导入描述
     * @return 导入描述
     */
    public byte[] getMessage() {
        return message;
    }

    /**
     * 设置 导入描述
     * @param message 导入描述
     */
    public void setMessage(byte[] message) {
        this.message = message;
    }
}