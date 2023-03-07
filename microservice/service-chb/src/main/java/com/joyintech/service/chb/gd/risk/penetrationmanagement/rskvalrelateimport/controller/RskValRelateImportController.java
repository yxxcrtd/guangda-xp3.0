/*
 * 文件名：RskValRelateImportController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalrelateimport.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImportDto;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalrelateimport.service.RskValRelateImportService;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dongzhiling
 * @version 1.0 2018年12月20日
 * @see RskValRelateImportController
 * @since 1.0
 */
@RestController("RskValRelateImportCtl_dtx")
@RequestMapping("/RskValRelateImport")
public class RskValRelateImportController {
    
    /**
     * RskValRelateImportService
     */
    @Autowired
    private RskValRelateImportService rskValRelateImportService;
    
    /**
     * 
     * 画面查询方法
     * <功能详细描述>
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<RskValRelateImportDto> selectForPage(@RequestBody Map<String, Object> param,
                                                     @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskValRelateImportService.selectForPage(page, length, param);
    }
    
    /**
     * <一句话功能简述> 指标结构映射联想下拉框
     * 
     * @param map
     * @return 指标结构映射信息
     * @see
     * @since 1.0
     */
    @PostMapping("queryStructMapId")
    public List<SelectOptionDto> queryStructMapId(@RequestBody Map<String, Object> param) {
        return rskValRelateImportService.queryStructMapId(param);
    }
    
    /**
     * <一句话功能简述> 汇总科目映射联想下拉框
     * 
     * @param map
     * @return 汇总科目映射信息
     * @see
     * @since 1.0
     */
    @PostMapping("querySumsubMapId")
    public List<SelectOptionDto> querySumsubMapId(@RequestBody Map<String, Object> param) {
        return rskValRelateImportService.querySumsubMapId(param);
    }
    
    /**
     * <一句话功能简述> 资产科目映射联想下拉框
     * 
     * @param map
     * @return 资产科目映射信息
     * @see
     * @since 1.0
     */
    @PostMapping("queryAssetSubMapId")
    public List<SelectOptionDto> queryAssetSubMapId(@RequestBody Map<String, Object> param) {
        return rskValRelateImportService.queryAssetSubMapId(param);
    }
    
    /**
     * 
     * <一句话功能简述>
     * 新建插入操作
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public int insert(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.insert(dto);
    }
    
    /**
     * <一句话功能简述> 更新操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public int update(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.update(dto);
    }
    
    /**
     * <一句话功能简述> 删除操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public int delete(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.delete(dto);
    }
    
    /**
     * <一句话功能简述> 编辑按钮按下，查询相关信息
     * 
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectInfoById")
    public RskValRelateImportDto selectInfoById(@RequestBody String ptlId) {
        return rskValRelateImportService.selectInfoById(ptlId);
    }

    /**
     * 组合代码联想下拉框
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("queryProdIdInfo/{page}/{length}")
    public Page<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param,@PathVariable("page") int page,
                                                 @PathVariable("length") int length) {
        return rskValRelateImportService.queryProdIdInfo(page,length,param);
    }

    /**
     * 查询所有资产要素信息
     *
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("queryAssetInfo")
    public List<ChbAssetInfo> queryAssetInfo() {
        return rskValRelateImportService.queryAssetInfo();
    }

    /**
     * 查询所有资产要素完整信息
     *
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectAllAsset")
    public List<ChbAssetInfo> selectAllAsset() {
        return rskValRelateImportService.selectAllAsset();
    }
    
    /**
     * <一句话功能简述> 批量删除操作
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteList")
    public int deleteList(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.deleteList(dto);
    }
    
    /**
     * <一句话功能简述> 组合名称重复校验
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("checkGroupName")
    public int checkGroupName(@RequestBody RskValRelateImportDto dto) {
        return rskValRelateImportService.checkGroupName(dto);
    }

}
