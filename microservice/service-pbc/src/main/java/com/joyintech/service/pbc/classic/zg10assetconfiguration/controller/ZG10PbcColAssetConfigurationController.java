package com.joyintech.service.pbc.classic.zg10assetconfiguration.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto.ZG10PbcColReportDto;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.service.ZG10PbcColAssetConfigurationSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ZG10PbcColAssetConfigurationController")
public class ZG10PbcColAssetConfigurationController {


    @Autowired
    private ZG10PbcColAssetConfigurationSubService zG10PbcColAssetConfigurationSubService;


    @PostMapping("findPage/{page}/{length}")
    public Page<ZG10PbcColReportDto> searchPage(@RequestBody Map<String, Object> param) {
        Page<ZG10PbcColReportDto> page = zG10PbcColAssetConfigurationSubService.searchPage(param);
        return page;
    }

    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return zG10PbcColAssetConfigurationSubService.checkData(map);
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
        zG10PbcColAssetConfigurationSubService.doManul(map);
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
        return zG10PbcColAssetConfigurationSubService.doCancelManul(map);
    }

    @PostMapping("searchByUuid")
    public List<ZG10PbcColReportDto> searchByUuid(@RequestBody List<String> list) {
        return zG10PbcColAssetConfigurationSubService.searchByUuid(list);
    }

    @PostMapping("searchByExcel")
    public List<ZG10PbcColReportDto> searchByExcel(@RequestBody Map<String, Object> map) {
        return zG10PbcColAssetConfigurationSubService.searchByExcel(map);
    }


}
