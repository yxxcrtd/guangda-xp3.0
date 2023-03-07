package com.joyintech.service.chb.monthreport.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.chb.monthreport.dto.SellMonthReport;
import com.joyintech.tams.svreport.chb.monthreport.service.SellMonthReport001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("SellMonthReportCtr_dtx")
@RequestMapping("/SellMonthReport")
public class SellMonthReportController {

    /**
     * service
     */
    @Autowired
    private SellMonthReport001Service sellMonthReport001Service;

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<SellMonthReport> searchPage(@RequestBody Map<String, Object> param) {
        Page<SellMonthReport> page = sellMonthReport001Service.searchPage(param);
        return page;
    }
    /**
     * 手工处理功能
     *
     * @return 提示信息
     * @param map 条件map
     * @seea
     * @since 1.0
     */
    @PostMapping("updateSumStaus")
    public void updateSumStaus(@RequestBody Map<String, Object> map) {
        sellMonthReport001Service.updateSumStaus(map);
    }

    /**
     * 取消手工处理功能
     *
     * @return 提示信息
     * @param map 条件map
     * @seea
     * @since 1.0
     */
    @PostMapping("cancelMannul")
    public void cancelMannul(@RequestBody Map<String, Object> map) {
        sellMonthReport001Service.cancelMannul(map);
    }

    /**
     * 报送检查
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String,Object> map) {
        String keyList = null;
        Map<String, Object> param = map;
        if(param.containsKey("checkKeys")){
            keyList=(String)param.get("checkKeys");
        }
        return sellMonthReport001Service.checkData(keyList);
    }
}
