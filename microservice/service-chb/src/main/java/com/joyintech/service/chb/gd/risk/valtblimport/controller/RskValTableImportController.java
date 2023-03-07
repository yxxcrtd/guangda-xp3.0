/*
 * 文件名：RskValTableImportController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.chb.gd.risk.valtblimport.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.chb.gd.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.chb.gd.risk.valtblimport.service.RskValTableImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *  估值表导入
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValTableImportController
 * @since 1.0
 */
@RestController("rskValTableImportCtl_dtx")
@RequestMapping("/RskValTableImport")
public class RskValTableImportController {

    @Autowired
    private RskValTableImportService rskValTableImportService;
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("saveValTbl")
    public void saveValTbl(@RequestBody RskValtblAssetSubDto dto){
        rskValTableImportService.saveValTbl(dto);
    }
    
    /**
     * 估值表科目数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectCount")
    public int selectCount(@RequestBody Map<String,Object> paramMap){
        return rskValTableImportService.selectCount(paramMap);
    }

    /**
     *  查询无需特殊处理的汇总项科目代码
     *
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectSumSubjectMapping")
    public List<String> selectSumSubjectMapping(){
        return rskValTableImportService.selectSumSubjectMapping();
    }
    
}
