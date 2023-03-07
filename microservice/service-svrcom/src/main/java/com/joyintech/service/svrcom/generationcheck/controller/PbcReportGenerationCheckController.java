package com.joyintech.service.svrcom.generationcheck.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.generationcheck.dto.PbcReportGenerationCheck001Dto;
import com.joyintech.tams.svreport.generationcheck.service.PbcReportGenerationCheck001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 报表生成检查
 *
 * @author yangxianjiu
 * @date 2022/11/28
 */
@RestController
@RequestMapping("/PbcReportGenerationCheck")
public class PbcReportGenerationCheckController {

    @Autowired
    private PbcReportGenerationCheck001Service pbcReportGenerationCheck001Service;

    /**
     * 分页查询
     *
     * @param pageParam
     * @param page      页
     * @param length
     * @return
     */
    @PostMapping("/searchPage/{page}/{length}")
    public Page<PbcReportGenerationCheck001Dto> searchPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") Integer page, @PathVariable("length") Integer length) {
        return pbcReportGenerationCheck001Service.searchPage(page, length, pageParam);
    }


}
