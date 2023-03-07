package com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dto;


import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import org.apache.commons.lang3.StringUtils;


public class ChbBranchNetwork extends ChbBranchNetworkKey {

    /**
     * 网点名称
     */
    @NotEmpty(fieldName = "网点名称", groups = {ValidateGroupEdit.class})
    private String branchName;

    @NotEmpty(fieldName = "网点所属监管码值", groups = {ValidateGroupEdit.class})
    private String branchFsa;

    @NotEmpty(fieldName = "网点地区码值", groups = {ValidateGroupEdit.class})
    private String networkNo;

    /**
     * 备注
     */
    private String remark;


    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBranchFsa() {
         return branchFsa;
    }

    public String getBranchFsaStr() {
        return StringUtils.isBlank(branchFsa)?null: SysDataUtil.getNameFromDataDict(DictCode.监管机构2, this.branchFsa);
    }

    public void setBranchFsa(String branchFsa) {
        this.branchFsa = branchFsa;
    }

    public String getNetworkNo() {
        return networkNo;
    }

    public String getNetworkNoStr() {
        return StringUtils.isBlank(networkNo)?null:SysDataUtil.getNameFromDataDict(DictCode.销售区域_中债, this.networkNo);
    }

    public void setNetworkNo(String networkNo) {
        this.networkNo = networkNo;
    }
}