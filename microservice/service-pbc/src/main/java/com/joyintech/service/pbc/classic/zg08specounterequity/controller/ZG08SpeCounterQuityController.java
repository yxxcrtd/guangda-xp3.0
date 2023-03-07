package com.joyintech.service.pbc.classic.zg08specounterequity.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.service.ZG08SpecCounterQuity001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ZG08SpeCounterQuity")
public class ZG08SpeCounterQuityController {

    @Autowired
    private ZG08SpecCounterQuity001Service zg08SpecCounterQuity001Service;

    @PostMapping("findPage/{page}/{length}")
    public Page<ZG08SpeCounterQuityDto> searchPage(@RequestBody Map<String, Object> param) {
        Page<ZG08SpeCounterQuityDto> page = zg08SpecCounterQuity001Service.searchPage(param);
        return page;
    }


    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return zg08SpecCounterQuity001Service.checkData(map);
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

    @PostMapping("updateSumStaus")
    public void updateSumStaus(@RequestBody Map<String, Object> map) {
        zg08SpecCounterQuity001Service.doManul(map);
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

    @PostMapping("cancelMannul")
    public String cancelMannul(@RequestBody Map<String, Object> map) {
        return zg08SpecCounterQuity001Service.doCancelManul(map);
    }

    @PostMapping("searchByUuid")
    public List<ZG08SpeCounterQuityDto> searchByUuid(@RequestBody List<String> list) {
        return zg08SpecCounterQuity001Service.searchByUuid(list);
    }

    @PostMapping("searchByExcel")
    public List<ZG08SpeCounterQuityDto> searchByExcel(@RequestBody Map<String, Object> map) {
        return zg08SpecCounterQuity001Service.searchByExcel(map);
    }

    /**
     * 产品下拉框
     * @return 产品代码和名称列表
     */
    @PostMapping("getProd")
    public List<SuggestDto> getProd(@RequestBody  String param) {
        return zg08SpecCounterQuity001Service.queryPrdNameList(param);
    }

    @PostMapping("getCounterProd")
    public List<SuggestDto> getCounterProd(@RequestBody  String param) {
        return zg08SpecCounterQuity001Service.getCounterProdList(param);
    }
}
