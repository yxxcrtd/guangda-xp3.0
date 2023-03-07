package com.joyintech.tams.svreport.specificcounterparty.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 特定目的载体对手方信息维护 微服务Service
 *
 * @author yangxianjiu
 * @date 2022/11/1
 */
@Service
public class PbcSpecificCounterPartyMService extends BaseService<PbcSpecificCounterParty001Dto> {

    /**
     * 分页查询
     * @param pageParam 查询条件
     * @return 数据
     */
    public Page<PbcSpecificCounterParty001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/selectForPage",
                pageParam, PbcSpecificCounterParty001Dto.class);
    }

    /**
     * 双击详情
     * @param param
     * @return
     */
    public PbcSpecificCounterParty001Dto selectDetail(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/selectDetail",
                param, PbcSpecificCounterParty001Dto.class);
    }

    /**
     * 获取 资产要素 资产代码 / 特定目的持有 识别标识
     * @param param
     * @return
     */
    public List<SuggestDto> queryAssetCodeIndentifyMark(Map<String, Object> param) {
        return restTemplate.postForList(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/queryAssetCodeIndentifyMark",
                param, SuggestDto.class);
    }

    /**
     * 保存
     * @param dto
     * @return
     */
    public Integer save(PbcSpecificCounterParty001Dto dto) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/save",
                dto, Integer.class);
    }

    /**
     * 删除
     * @param dto
     * @return
     */
    public Integer delete(PbcSpecificCounterParty001Dto dto) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/delete",
                dto, Integer.class);
    }

    /**
     * 导入数据文件之前检查数据
     * 不用微服务调用 错误信息提示会 导致http响应码500
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("fileName", fileName);
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/checkConfirmMsgForImportData",
                map, AjaxResult.class);
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
        return restTemplate.postForList(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/importData",
                map, ExcelValidDto.class);
    }

    /**
     * 获取 交易对手机构名称
     * @param param
     * @return
     */
    public Map<String, Object> getPartyOrgNameByAssetCode(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/getPartyOrgNameByAssetCode",
                param, Map.class);

    }

    /**
     * 获取 交易对手产品名称
     * @param param
     * @return
     */
    public Map<String, Object> getPartyProdNameByAssetCode(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/getPartyProdNameByAssetCode",
                param, Map.class);
    }

    /**
     * 获取 交易对手产品种类
     * @param param
     * @return
     */
    public Map<String, Object> getPartyProdTypeByAssetCode(Map<String, Object> param) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/PbcSpecificCounterParty/getPartyProdTypeByAssetCode",
                param, Map.class);
    }
}
