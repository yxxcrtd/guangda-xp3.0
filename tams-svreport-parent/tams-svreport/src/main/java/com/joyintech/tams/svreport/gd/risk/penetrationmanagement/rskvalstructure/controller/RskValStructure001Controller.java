/*
 * 文件名：RskValStructure001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.gd.risk.penetrationmanagement.rskvalstructure.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.svreport.gd.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;
import com.joyintech.tams.svreport.gd.risk.penetrationmanagement.rskvalstructure.service.RskValStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 估值表指标结构映射 查询
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年12月10日
 * @see RskValStructure001Controller
 * @since 1.0
 */
@Controller
@PageId("RskValStructure001")
@RequestMapping("/svreport/gd/risk/penetrationmanagement/rskvalstructure/RskValStructure001")
public class RskValStructure001Controller extends BaseController {
    /**
     * rskValStructureService
     */
    @Autowired
    private RskValStructureService rskValStructureService;
    @Override
    public String init(Model model) {
        return super.init(model);
    }
    
    /**
     * 
     * 查询映射名称
     * @return String
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("/getStructName")
    public String getStructName(Model model) {
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("list", rskValStructureService.getStructName(map));
        return "svreport/gd/risk/penetrationmanagement/rskvalstructure/RskValStructure004";
    }
    /**
     * 
     * 估值表指标结构映射 查询
     * <功能详细描述>
     * @return Page<FinPayPlanAdjustmentDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskValStructureDto> selectForPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        //估值表导入结构映射初始化 
        if(StringUtil.isNotEmpty(this.getParam("structMapId"))){
            param.put("structId", this.getParam("structMapId"));
        }
        if(param.containsKey("structId")){
            // modify by swwwan HXTICM-924 导入估值表后将底下指标项、资产科目、汇总科目映射进行排序，再删除文件，导入新的文件时会报错 20210701 start
            String keyStr = (String)param.get("key");
            if (StringUtil.isNotEmpty(keyStr)) {
                if (!StringUtil.equals(keyStr, "SRC_COL_INDEX") && !StringUtil.equals(keyStr, "SRC_COL_DATA_TYPE") && !StringUtil.equals(keyStr, "TGT_INDEX")) {
                    param.remove("key");
                }
            }
            // modify by swwwan HXTICM-924 20210701 end
            return rskValStructureService.selectForPage(param);
        }
        return new Page<RskValStructureDto>();
    }
    /**
     * 新建按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) RskValStructureDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
}
