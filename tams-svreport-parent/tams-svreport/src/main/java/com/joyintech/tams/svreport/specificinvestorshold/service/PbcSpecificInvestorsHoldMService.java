package com.joyintech.tams.svreport.specificinvestorshold.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.specificinvestorshold.dto.PbcSpecificInvestorsHold001Dto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 特定目的载体持有信息维护(投资者)
 *
 * @author yangxianjiu
 * @date 2022/11/4
 */
@Service
public class PbcSpecificInvestorsHoldMService extends BaseService<PbcSpecificInvestorsHold001Dto> {

    /**
     * 分页查询
     *
     * @param pageParam 查询条件
     * @return 数据
     */
    public Page<PbcSpecificInvestorsHold001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/selectForPage",
                pageParam, PbcSpecificInvestorsHold001Dto.class);
    }

    /**
     * 双击详情
     *
     * @param param
     * @return
     */
    public PbcSpecificInvestorsHold001Dto selectDetail(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/selectDetail",
                param, PbcSpecificInvestorsHold001Dto.class);
    }


    /**
     * 保存
     *
     * @param dto
     * @return
     */
    public Integer save(PbcSpecificInvestorsHold001Dto dto) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/save",
                dto, Integer.class);
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    public Integer delete(PbcSpecificInvestorsHold001Dto dto) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/delete",
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
        return restTemplate.postForList(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/importData",
                map, ExcelValidDto.class);
    }

    /**
     * 获取 内部产品代码
     *
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getProdInsId(Map<String, Object> param) {
        return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/getProdInsId",
                param, SelectOptionDto.class);
    }

    /**
     * 获取 产品代码
     *
     * @param param
     * @return
     */
    public Map<String, Object> getPbcProdIdByAccount(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/getPbcProdIdByAccount",
                param, Map.class);
    }

    /**
     * 识别标识 与 投资者名称 校重
     *
     * @param param
     * @return
     */
    public Integer getIndentifyMarkAndHoldName(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/getIndentifyMarkAndHoldName",
                param, Integer.class);
    }

    /**
     * 获取 身份登记 识别标识
     *
     * @param param
     * @return
     */
    public List<SuggestDto> getIndentifyMark(Map<String, Object> param) {
        return restTemplate.postForList(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/getIndentifyMark",
                param, SuggestDto.class);
    }

    /**
     * 获取 投资者 类别
     *
     * @param param
     * @return
     */
    public Map<String, Object> getHolderTypeByIndentifyMark(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificInvestorsHold/getHolderTypeByIndentifyMark",
                param, Map.class);
    }
}
