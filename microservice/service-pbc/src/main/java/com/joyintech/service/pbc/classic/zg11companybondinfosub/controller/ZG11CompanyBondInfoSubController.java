package com.joyintech.service.pbc.classic.zg11companybondinfosub.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11CompanyBondInfoSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.service.ZG11CompanyBondInfoSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ZG11CompanyBondInfoSubController")
public class ZG11CompanyBondInfoSubController {


    @Autowired
    private ZG11CompanyBondInfoSubService zg11CompanyBondInfoSubService;


    @PostMapping("findPage/{page}/{length}")
    public Page<ZG11CompanyBondInfoSubDto> searchPage(@RequestBody Map<String, Object> param) {
        Page<ZG11CompanyBondInfoSubDto> page = zg11CompanyBondInfoSubService.searchPage(param);
        return page;
    }


    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return zg11CompanyBondInfoSubService.checkData(map);
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
        zg11CompanyBondInfoSubService.doManul(map);
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
        return zg11CompanyBondInfoSubService.doCancelManul(map);
    }

    @PostMapping("searchByUuid")
    public List<ZG11CompanyBondInfoSubDto> searchByUuid(@RequestBody List<String> list) {
        return zg11CompanyBondInfoSubService.searchByUuid(list);
    }

    @PostMapping("searchByExcel")
    public List<ZG11CompanyBondInfoSubDto> searchByExcel(@RequestBody Map<String, Object> map) {
        return zg11CompanyBondInfoSubService.searchByExcel(map);
    }


}
