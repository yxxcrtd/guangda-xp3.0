package com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.dto;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.BaseChbEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;



public class ChbAssetMappingKey extends BaseChbEntity {
    /**
     * 网点编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "报送系统资产代码", groups = {ValidateGroupEdit.class})
    private String svrAssetCode;

    public String getSvrAssetCode() {
        return svrAssetCode;
    }

    public void setSvrAssetCode(String svrAssetCode) {
        this.svrAssetCode = svrAssetCode;
    }
}