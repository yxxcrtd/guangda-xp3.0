package com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ZG08SpecCounterQuityMService extends CommonExportService<ZG08SpeCounterQuityDto> {


    /*分页查询*/
    public Page<ZG08SpeCounterQuityDto> searchPage(Map<String, Object> param) {
        return restTemplate.postForPage(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG08SpeCounterQuity/findPage",param,
                ZG08SpeCounterQuityDto.class);
    }

    /**
     * 手工处理功能
     *
     * @return 提示信息
     * @param map
     *            条件map
     * @seea
     * @since 1.0
     */
    public void updateSumStaus(Map<String, Object> map) {
        restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG08SpeCounterQuity/updateSumStaus",map,
                Integer.class);
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
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG08SpeCounterQuity/reportCheck", map,
                ExcelValidDto.class);
    }

    /**
     * 取消手工处理功能
     *
     * @return 提示信息
     * @param map
     *            条件map
     * @seea
     * @since 1.0
     */
    public String cancelMannul(Map<String, Object> map) {
        return restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG08SpeCounterQuity/cancelMannul",map,
                String.class);
    }

    public List<ZG08SpeCounterQuityDto> searchByUuid(List<String> list) {
        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG08SpeCounterQuity/searchByUuid",list,
                ZG08SpeCounterQuityDto.class);
    }

    public List<ZG08SpeCounterQuityDto> searchByExcel(Map<String, Object> paraMap) {
        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG08SpeCounterQuity/searchByExcel",paraMap,
                ZG08SpeCounterQuityDto.class);
    }

    /**
     * 产品下拉框
     * @return 产品代码和名称列表
     */
    public List<SuggestDto> getProd(String param) {
        if(param==null||"".equals(param)){
            return new ArrayList<SuggestDto>();
        }
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ZG08SpeCounterQuity/getProd", param,
                SuggestDto.class);
    }

    public List<SuggestDto> getCounterProd(String param) {
        if(param==null||"".equals(param)){
            return new ArrayList<SuggestDto>();
        }
        return restTemplate.postForList(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/ZG08SpeCounterQuity/getCounterProd", param,
                SuggestDto.class);
    }
}
