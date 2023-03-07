/*
 * 文件名：RskValSumname001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalsumname.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalsumname.service.RskValSumname001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月17日
 * @see RskValSumname001Controller
 * @since 1.0
 */
@RestController("backRskValSumname001Controller")
@RequestMapping("/RskValSumname001")
public class RskValSumname001Controller {
    /**
     *rskValSumname001Service
     */
    @Autowired
    private RskValSumname001Service rskValSumname001Service;
    /**
     * 查询映射名称
     *
     * @param map 查询的参数
     * @return 申报附件方案名称
     * @see
     * @since 1.0
     */
    @PostMapping("/getSumName")
    List<RskValSumnameDto> getSumName(@RequestBody Map<String,Object> map){
        return rskValSumname001Service.getSumName(map);
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
    public Page<RskValSumnameDto> selectForPage(@RequestBody Map<String, Object> param,
         @PathVariable("page") int page, @PathVariable("length") int length) throws Exception{
        return rskValSumname001Service.selectForPage(page, length, param);
    }
    
    @GetMapping("selectForList")
    public List<RskValSumnameDto> selectForList(String sumId){
        return rskValSumname001Service.selectForList(sumId);
    }
    @PostMapping("getCountById")
    public boolean getCountById(@RequestBody RskValSumnameDto dto){
        return rskValSumname001Service.getCountById(dto);
    }
    
}
