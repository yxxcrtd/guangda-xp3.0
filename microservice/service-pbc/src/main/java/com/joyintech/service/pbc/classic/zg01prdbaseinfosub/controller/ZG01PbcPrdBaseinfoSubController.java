package com.joyintech.service.pbc.classic.zg01prdbaseinfosub.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service.ZG01PbcPrdBaseinfoSubService001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 
* 资管产品基本信息controller。
* @ClassName: PbcPrdBaseinfoSubController 
* @author liuyu
* @date 2019年6月17日 上午10:38:00 
*
 */
@RestController
@RequestMapping("/ZG01PbcPrdBaseinfoSub")
public class ZG01PbcPrdBaseinfoSubController {


    /**
     * 资管产品基本信息service
     */
    @Autowired
    private ZG01PbcPrdBaseinfoSubService001 zg01PbcPrdBaseinfoSubService001;



    /**
     *
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ZG01PbcPrdBaseinfoSub> searchPage(@RequestBody Map<String, Object> param) {
        Page<ZG01PbcPrdBaseinfoSub> page = zg01PbcPrdBaseinfoSubService001.searchPage(param);
        return page;
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
        zg01PbcPrdBaseinfoSubService001.doManul(map);
    }



    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return zg01PbcPrdBaseinfoSubService001.checkData(map);
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
        return zg01PbcPrdBaseinfoSubService001.doCancelManul(map);
    }

    @PostMapping("searchByExcel")
    public List<ZG01PbcPrdBaseinfoSub> searchByExcel(@RequestBody Map<String, Object> map) {
        return zg01PbcPrdBaseinfoSubService001.searchByExcel(map);
    }

    @PostMapping("searchByUuid")
    public List<ZG01PbcPrdBaseinfoSub> searchByUuid(@RequestBody List<String> list) {
        return zg01PbcPrdBaseinfoSubService001.searchByUuid(list);
    }

}
