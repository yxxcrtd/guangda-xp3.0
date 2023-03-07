package com.joyintech.tams.svreport.submitbefore.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.submitbefore.dto.PbcSubmitBeforeCheck001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 报送前检查
 *
 * @author yangxianjiu
 * @date 2022/11/29
 */
@Service
public class PbcSubmitBeforeCheckMService extends BaseService<PbcSubmitBeforeCheck001Dto> {

    /**
     * 分页查询
     * @param pageParam 查询条件
     * @return 数据
     */
    public Page<PbcSubmitBeforeCheck001Dto> searchPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSubmitBeforeCheck/searchPage",
                pageParam, PbcSubmitBeforeCheck001Dto.class);
    }
}
