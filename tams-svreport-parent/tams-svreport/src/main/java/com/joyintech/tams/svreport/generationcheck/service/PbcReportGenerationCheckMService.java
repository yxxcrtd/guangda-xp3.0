package com.joyintech.tams.svreport.generationcheck.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.generationcheck.dto.PbcReportGenerationCheck001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 报表生成检查
 *
 * @author yangxianjiu
 * @date 2022/11/28
 */
@Service
public class PbcReportGenerationCheckMService extends BaseService<PbcReportGenerationCheck001Dto> {

    /**
     * 分页查询
     * @param pageParam 查询条件
     * @return 数据
     */
    public Page<PbcReportGenerationCheck001Dto> searchPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcReportGenerationCheck/searchPage",
                pageParam, PbcReportGenerationCheck001Dto.class);
    }

}
