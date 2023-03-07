package com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.dto.ChbAssetMappingDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.service.ChbAssetMappingService;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dto.ChbBranchNetworkDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 资产代码匹配映射管理
 * 
 * @author zhoubin
 * @version 1.0 2022年05月16日
 * @see ChbAssetMapping001Controller
 */
@Controller
@PageId("ChbAssetMapping001")
@RequestMapping("/chb/businessdatamaintain/chbassetmapping/ChbAssetMapping001")
public class ChbAssetMapping001Controller extends BaseController {
    /**
     * 资产代码匹配映射管理service
     */
    @Resource
    private ChbAssetMappingService chbAssetMappingService;
    /**
     * 
     * 查询页面初始化
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {

        return super.init(model);
    }
    /**
     * 资产代码匹配映射管理查询
     * @return 返回查询结果
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ChbAssetMappingDto> selectAll(){
        Page<ChbAssetMappingDto> page = chbAssetMappingService.selectForPage(
            RequestUtil.getParameters(getRequest()));
        return page;
    }

    /**
     * 按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupDelete.class}) ChbAssetMappingDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除
     * @param dto
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody ChbAssetMappingDto dto) throws Exception {
        chbAssetMappingService.delete(dto);
        return new AjaxResult("操作成功");
    }


    /**
     * 导出信息excel
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export(){
        return chbAssetMappingService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
    

}
