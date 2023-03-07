package com.joyintech.tams.svreport.gd.risk.valtblassetcheck.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.gd.risk.valtblassetcheck.dto.RskValAssetCheckDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class RskValAssetCheckService extends CommonExportService<RskValAssetCheckDto>{

    private Logger logger = LoggerFactory.getLogger(RskValAssetCheckService.class);
    @Autowired
    private ProgressBarService progressBarService;


    public Page<RskValAssetCheckDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValAssetCheck/findPage",
                pageParam, RskValAssetCheckDto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/svreport/gd/risk/valtblassetcheck/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RskValAssetCheckDto> selectPageData(Map<String, Object> selectParam) {
        return selecForPage(selectParam);
    }

    public Page<RskValAssetCheckDto> selecForPage(Map<String, Object> parameters) {
        if (StringUtil.isNotEmpty(parameters.get("groupId"))) {
            parameters.put("groupId", StringUtil.splitToList(null, parameters.get("groupId").toString()));
        }
        String selectData = (String) parameters.get("selectData");
        if (StringUtil.isNotEmpty(selectData)){
            Page<RskValAssetCheckDto> page = new Page<>();
            try {
                page.setRoot(JsonUtils.json2List(selectData,RskValAssetCheckDto.class));
                return page;
            }catch (IOException e){
                logger.error(e.getMessage(),e);
            }
        }
        parameters.put("loginUserName", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValAssetCheck/findPage",
                parameters, RskValAssetCheckDto.class);
    }

}
