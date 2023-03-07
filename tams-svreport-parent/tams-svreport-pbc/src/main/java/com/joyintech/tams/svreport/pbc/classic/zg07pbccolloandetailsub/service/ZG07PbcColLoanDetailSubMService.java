package com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ZG07 除回购和拆解外贷款明细登记表 微服务 Service
 *
 * @author yangxianjiu
 * @date 2022/11/9
 */
@Service("ZG07PbcColLoanDetailSubMService")
public class ZG07PbcColLoanDetailSubMService extends CommonExportService<ZG07PbcColLoanDetailSubDto> {

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    public Page<ZG07PbcColLoanDetailSubDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(SvrServiceApplication.PBC_SERVICE_NAME + "/ZG07PbcColLoanDetailSubController/selectForPage", pageParam,
                ZG07PbcColLoanDetailSubDto.class);
    }

    /**
     * 获取资产代码
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getAssetCode(Map<String, Object> param) {
        return restTemplate.postForPage(SvrServiceApplication.PBC_SERVICE_NAME + "/ZG07PbcColLoanDetailSubController/getAssetCode", param,
                SelectOptionDto.class);
    }

    /**
     * 数据检查
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public List<ExcelValidDto> reportCheck(Map<String,Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG07PbcColLoanDetailSubController/reportCheck", map,
                ExcelValidDto.class);
    }

    /**
     * 手工处理
     * @param param
     */
    public Integer doManual(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.PBC_SERVICE_NAME + "/ZG07PbcColLoanDetailSubController/doManual", param,
                Integer.class);
    }

    /**
     * 取消手工处理
     * @param param
     * @return
     */
    public String cancelManual(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.PBC_SERVICE_NAME + "/ZG07PbcColLoanDetailSubController/cancelManual", param,
                String.class);
    }

    /**
     * 导出 根据查询参数
     * @param param
     * @return
     */
    public List<ZG07PbcColLoanDetailSubDto> searchByExcel(Map<String, Object> param) {
        return restTemplate.postForList(SvrServiceApplication.PBC_SERVICE_NAME + "/ZG07PbcColLoanDetailSubController/searchByExcel", param,
                ZG07PbcColLoanDetailSubDto.class);
    }

    /**
     * 导出 有选中行
     * @param list
     * @return
     */
    public List<ZG07PbcColLoanDetailSubDto> searchByUuid(List<String> list) {
        return restTemplate.postForList(SvrServiceApplication.PBC_SERVICE_NAME + "/ZG07PbcColLoanDetailSubController/searchByUuid", list,
                ZG07PbcColLoanDetailSubDto.class);
    }


}
