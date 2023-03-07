package com.joyintech.tams.svreport.gd.risk.valtblimport.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.gd.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.tams.svreport.gd.risk.valtblimport.dto.RskValtblAssetSumDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RskValTableQueryService extends CommonExportService<RskValtblAssetSumDto>{

    public Page<RskValtblAssetSumDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValTableQuery/findPage",
                pageParam, RskValtblAssetSumDto.class);
    }

    public List<RskValtblAssetSubDto> selectRskValTableDetaile(Map<String, Object> param) {
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValTableQuery/selectRskValTableDetaile",
                param, RskValtblAssetSubDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
            exportParam.config("/com/joyintech/tams/svreport/gd/risk/valtblimport/conf/export_conf.xml")
                    .dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RskValtblAssetSumDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
}
