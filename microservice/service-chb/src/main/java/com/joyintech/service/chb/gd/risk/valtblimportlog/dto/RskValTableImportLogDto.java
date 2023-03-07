package com.joyintech.service.chb.gd.risk.valtblimportlog.dto;

import com.joyintech.tams.common.base.entity.RskValtblAutoImportLog;


public class RskValTableImportLogDto extends RskValtblAutoImportLog {

    private String ptlName;

    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPtlName() {
        return ptlName;
    }

    public void setPtlName(String ptlName) {
        this.ptlName = ptlName;
    }
}
