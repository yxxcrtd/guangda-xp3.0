package com.joyintech.tams.svreport.gd.risk.penetrationmanagement.rskvalrelateimport.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 资产查询
 *
 * @author swwan
 * @className RskValRelateImportCommonService
 * @date 2021/6/23 16:54
 */
@Service
public class RskValRelateImportCommonService extends BaseService {

    /**
     * <一句话功能简述>
     * 组合代码联想下拉框
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdIdInfo(Map<String, Object> param) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValRelateImport/queryProdIdInfo",
                param, SelectOptionDto.class);
    }
}
