package com.joyintech.service.pbc.classic.zg05prdassetdebt.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.service.ZG05PbcColPrdAssetDebtSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ZG05PbcColPrdAssetDebtController")
public class ZG05PbcColPrdAssetDebtController {


    @Autowired
    private ZG05PbcColPrdAssetDebtSubService zG05PbcColPrdAssetDebt001Service;


    @PostMapping("findPage/{page}/{length}")
    public Page<ZG05PbcColPrdAssetDebtDto> searchPage(@RequestBody Map<String, Object> param) {
        Page<ZG05PbcColPrdAssetDebtDto> page = zG05PbcColPrdAssetDebt001Service.searchPage(param);
        return page;
    }

    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return zG05PbcColPrdAssetDebt001Service.checkData(map);
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
        zG05PbcColPrdAssetDebt001Service.doManul(map);
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
        return zG05PbcColPrdAssetDebt001Service.doCancelManul(map);
    }

    @PostMapping("searchByUuid")
    public List<ZG05PbcColPrdAssetDebtDto> searchByUuid(@RequestBody List<String> list) {
        return zG05PbcColPrdAssetDebt001Service.searchByUuid(list);
    }

    @PostMapping("searchByExcel")
    public List<ZG05PbcColPrdAssetDebtDto> searchByExcel(@RequestBody Map<String, Object> map) {
        return zG05PbcColPrdAssetDebt001Service.searchByExcel(map);
    }


}
