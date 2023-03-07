package com.joyintech.service.pbc.classic.zg09assetdebtterm.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dto.ZG09PbcColReport;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.service.ZG09PbcAssetDebtTermSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ZG09PbcAssetDebtTermController")
public class ZG09PbcAssetDebtTermController {


    @Autowired
    private ZG09PbcAssetDebtTermSubService zg09PbcAssetDebtTermSubService;


    @PostMapping("findPage/{page}/{length}")
    public Page<ZG09PbcColReport> searchPage(@RequestBody Map<String, Object> param) {
        Page<ZG09PbcColReport> page = zg09PbcAssetDebtTermSubService.searchPage(param);
        return page;
    }

    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return zg09PbcAssetDebtTermSubService.checkData(map);
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
        zg09PbcAssetDebtTermSubService.doManul(map);
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
        return zg09PbcAssetDebtTermSubService.doCancelManul(map);
    }

    @PostMapping("searchByUuid")
    public List<ZG09PbcColReport> searchByUuid(@RequestBody List<String> list) {
        return zg09PbcAssetDebtTermSubService.searchByUuid(list);
    }

    @PostMapping("searchByExcel")
    public List<ZG09PbcColReport> searchByExcel(@RequestBody Map<String, Object> map) {
        return zg09PbcAssetDebtTermSubService.searchByExcel(map);
    }


}
