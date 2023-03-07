package com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.dto;


import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dto.ChbBranchNetworkKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import org.apache.commons.lang3.StringUtils;


public class ChbAssetMapping extends ChbAssetMappingKey {

    private String uuid;

    private String assetName;

    @NotEmpty(fieldName = "策略专户系统代码", groups = {ValidateGroupEdit.class})
    private String sadasAssetCode;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getSadasAssetCode() {
        return sadasAssetCode;
    }

    public void setSadasAssetCode(String sadasAssetCode) {
        this.sadasAssetCode = sadasAssetCode;
    }
}