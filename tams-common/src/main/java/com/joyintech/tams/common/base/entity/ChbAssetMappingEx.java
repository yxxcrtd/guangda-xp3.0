package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbAssetMappingEx extends BaseEntity {
    /**
     * null
     */
    private String uuid;

    /**
     * null
     */
    private String svrAssetCode;

    /**
     * null
     */
    private String sadasAssetCode;

    /**
     * 取得 null
     * @return null
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 null
     * @param uuid null
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSvrAssetCode() {
        return svrAssetCode;
    }

    /**
     * 设置 null
     * @param svrAssetCode null
     */
    public void setSvrAssetCode(String svrAssetCode) {
        this.svrAssetCode = svrAssetCode == null ? null : svrAssetCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getSadasAssetCode() {
        return sadasAssetCode;
    }

    /**
     * 设置 null
     * @param sadasAssetCode null
     */
    public void setSadasAssetCode(String sadasAssetCode) {
        this.sadasAssetCode = sadasAssetCode == null ? null : sadasAssetCode.trim();
    }
}