package com.joyintech.tams.svreport.fininstitution.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.fininstitution.dto.PbcFinInstitutionAssetInfo001Dto;
import com.joyintech.tams.svreport.specificinvestorshold.dto.PbcSpecificInvestorsHold001Dto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表内资产数据维护
 *
 * @author yangxianjiu
 * @date 2022/11/23
 */
@Service
public class PbcFinInstitutionAssetInfoMService extends BaseService<PbcFinInstitutionAssetInfo001Dto> {

    /**
     * 分页查询
     * @param pageParam 查询条件
     * @return 数据
     */
    public Page<PbcFinInstitutionAssetInfo001Dto> searchPage(Map<String, Object> pageParam) {
            return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcFinInstitutionAssetInfo/searchPage",
                    pageParam, PbcFinInstitutionAssetInfo001Dto.class);
    }

    /**
     * 详情
     * @param param
     * @return
     */
    public PbcFinInstitutionAssetInfo001Dto selectDetail(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcFinInstitutionAssetInfo/selectDetail",
                param, PbcFinInstitutionAssetInfo001Dto.class);
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    public Integer delete(PbcFinInstitutionAssetInfo001Dto dto) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcFinInstitutionAssetInfo/delete",
                dto, Integer.class);
    }

    /**
     * 保存
     *
     * @param dto
     * @return
     */
    public Integer save(PbcFinInstitutionAssetInfo001Dto dto) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcFinInstitutionAssetInfo/save",
                dto, Integer.class);
    }

    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("fileName", fileName);
        return restTemplate.postForList(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcFinInstitutionAssetInfo/importData",
                map, ExcelValidDto.class);
    }
}
