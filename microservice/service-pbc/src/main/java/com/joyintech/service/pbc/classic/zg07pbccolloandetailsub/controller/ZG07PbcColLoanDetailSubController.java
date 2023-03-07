package com.joyintech.service.pbc.classic.zg07pbccolloandetailsub.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.service.ZG07PbcColLoanDetailSub001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ZG07 除回购和拆解外贷款明细登记表
 *
 * @author yangxianjiu
 * @date 2022/11/9
 */
@RestController
@RequestMapping("/ZG07PbcColLoanDetailSubController")
public class ZG07PbcColLoanDetailSubController {

    @Autowired
    private ZG07PbcColLoanDetailSub001Service pbcColLoanDetailSub001Service;

    /**
     * 分页查询
     * @param pageParam
     * @param page
     * @param length
     * @return
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<ZG07PbcColLoanDetailSubDto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") int page, @PathVariable("length") int length){
        return pbcColLoanDetailSub001Service.selectForPage(pageParam, page, length);
    }

    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return pbcColLoanDetailSub001Service.checkData(map);
    }

    /**
     * 手工处理
     * @param param
     * @return
     */
    @PostMapping("doManual")
    public Integer doManual(@RequestBody Map<String, Object> param) {
        return pbcColLoanDetailSub001Service.doManual(param);
    }

    /**
     * 取消手工处理
     * @param param
     * @return
     */
    @PostMapping("cancelManual")
    public String cancelManual(@RequestBody Map<String, Object> param) {
        return pbcColLoanDetailSub001Service.cancelManual(param);
    }

    /**
     * 获取资产代码
     * @param param
     * @param page
     * @param length
     * @return
     */
    @PostMapping("/getAssetCode/{page}/{length}")
    public Page<SelectOptionDto> getAssetCode(@RequestBody Map<String, Object> param, @PathVariable("page") int page, @PathVariable("length") int length){
        param.put("page", page);
        param.put("length", length);
        return pbcColLoanDetailSub001Service.getAssetCode(param, page, length);
    }

    /**
     * 导出 根据查询参数
     * @param map
     * @return
     */
    @PostMapping("searchByExcel")
    public List<ZG07PbcColLoanDetailSubDto> searchByExcel(@RequestBody Map<String, Object> map) {
        return pbcColLoanDetailSub001Service.searchByExcel(map);
    }

    /**
     * 导出 根据选中行
     * @param list
     * @return
     */
    @PostMapping("searchByUuid")
    public List<ZG07PbcColLoanDetailSubDto> searchByUuid(@RequestBody List<String> list) {
        return pbcColLoanDetailSub001Service.searchByUuid(list);
    }

}
