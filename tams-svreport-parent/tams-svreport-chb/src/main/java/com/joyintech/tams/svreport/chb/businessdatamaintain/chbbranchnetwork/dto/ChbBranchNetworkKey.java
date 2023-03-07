package com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dto;

import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.BaseChbEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;



public class ChbBranchNetworkKey extends BaseChbEntity {
    /**
     * 网点编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    @NotEmpty(fieldName = "网点编号", groups = {ValidateGroupEdit.class})
    private String branchNo;

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }
}