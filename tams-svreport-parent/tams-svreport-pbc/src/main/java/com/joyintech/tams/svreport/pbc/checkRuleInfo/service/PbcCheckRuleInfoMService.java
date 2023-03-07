package com.joyintech.tams.svreport.pbc.checkRuleInfo.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.dto.PbcCheckRuleInfo001;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:yangkunfeng
 * @Date:2022/11/30 19:16
 * @Description:
 */
@Service
public class PbcCheckRuleInfoMService extends CommonExportService<PbcCheckRuleInfo001> {
    public Integer delete(PbcCheckRuleInfo001 dto) {
        return restTemplate.postForObject(SvrServiceApplication.PBC_SERVICE_NAME + "/PbcCheckRule/delete",
                dto, Integer.class);
    }

    public Integer save(PbcCheckRuleInfo001 dto) {
        return restTemplate.postForObject(SvrServiceApplication.PBC_SERVICE_NAME + "/PbcCheckRule/save",
                dto, Integer.class);
    }

    public Page<PbcCheckRuleInfo001> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(SvrServiceApplication.PBC_SERVICE_NAME + "/PbcCheckRule/selectForPage",
                pageParam, PbcCheckRuleInfo001.class);
    }

    public PbcCheckRuleInfo001 selectDetail(Map<String, Object> pageParam) {
        return restTemplate.postForObject(SvrServiceApplication.PBC_SERVICE_NAME + "/PbcCheckRule/selectDetail",
                pageParam, PbcCheckRuleInfo001.class);
    }

    public void updateStatus(Map<String, Object> param) {
        restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcCheckRule/effitive",
                param, Integer.class);
    }
}
