/*
 * 文件名：RskValStructureController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalstructure.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalstructure.service.RskValStructuresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月11日
 * @see RskValStructureController
 * @since 1.0
 */
@RestController
@RequestMapping("/RskValStructure")
public class RskValStructureController {
    /**
     *rskValStructuresService
     */
    @Autowired
    private RskValStructuresService rskValStructuresService;
    /**
     * 查询映射名称
     *
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    @PostMapping("/getStructName")
    List<RskValStructureDto> getStructName(@RequestBody Map<String,Object> map){
        return rskValStructuresService.getStructName(map);
    }
    /**
     * 画面查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @PostMapping("selectForPage/{page}/{length}")
    public Page<RskValStructureDto> selectForPage(@RequestBody Map<String, Object> param,
         @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return rskValStructuresService.selectForPage(page, length, param);
    }
    
    @GetMapping("selectForList")
    public List<RskValStructureDto> selectForList(String structId){
        return rskValStructuresService.selectForList(structId);
    }
    @PostMapping("getCountById")
    public boolean getCountById(@RequestBody RskValStructureDto dto){
        return rskValStructuresService.getCountById(dto);
    }
}
