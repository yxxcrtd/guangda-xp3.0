package com.joyintech.tams.svreport.gd.risk.valtblimportlog.dto;

import com.joyintech.tams.common.base.entity.RskValtblAutoImportLog;

import java.util.List;

public class RskValTableImportLogDto extends RskValtblAutoImportLog {


    private String ptlName;
    private String statusName;

    private List<RskValTableImportLogDto> dtoList;

    public List<RskValTableImportLogDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<RskValTableImportLogDto> dtoList) {
        this.dtoList = dtoList;
    }

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
