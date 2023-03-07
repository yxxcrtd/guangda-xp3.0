package com.joyintech.tams.svreport.gd.risk.rskbotdetailreport.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.gd.risk.rskbotdetailreport.dto.RskValBotDetailReport001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RskValBotDetailReport001Service  extends CommonExportService<RskValBotDetailReport001Dto> {

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/svreport/gd/risk/rskbotdetailreport/conf/export_conf.xml")
                .dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RskValBotDetailReport001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 导出
     * @param pageParam
     * @return
     */
    private Page<RskValBotDetailReport001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValBotDetailReport/selectData",
                pageParam, RskValBotDetailReport001Dto.class);
    }

}
