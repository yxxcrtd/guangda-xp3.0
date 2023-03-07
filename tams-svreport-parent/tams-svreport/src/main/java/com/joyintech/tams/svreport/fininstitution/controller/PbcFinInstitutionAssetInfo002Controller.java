package com.joyintech.tams.svreport.fininstitution.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.svreport.fininstitution.dto.PbcFinInstitutionAssetInfo001Dto;
import com.joyintech.tams.svreport.fininstitution.service.PbcFinInstitutionAssetInfoMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 表内资产数据维护
 *
 * @author yangxianjiu
 * @date 2022/11/23
 */
@PageId("PbcFinInstitutionAssetInfo002")
@Controller
@RequestMapping("/svreport/fininstitution/PbcFinInstitutionAssetInfo002")
public class PbcFinInstitutionAssetInfo002Controller extends BaseController {

    @Autowired
    private PbcFinInstitutionAssetInfoMService pbcFinInstitutionAssetInfoMService;


    @Override
    public String init(Model model) {
        String pbcUuid = getParam("pbcUuid");
        PbcFinInstitutionAssetInfo001Dto dto = new PbcFinInstitutionAssetInfo001Dto();
        if (!StringUtil.isBlank(pbcUuid)) {
            // 查询数据
            dto = pbcFinInstitutionAssetInfoMService.selectDetail(RequestUtil.getPageParam());
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);
        return super.init(model);
    }

    /**
     * 保存时的check
     *
     * @param dto    检验输入参数
     * @param result 错误参数
     */
    @ResponseBody
    @PostMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) PbcFinInstitutionAssetInfo001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存/修改
     *
     * @param dto 传入参数
     * @return 返回结果
     * @throws Exception 异常
     */
    @ResponseBody
    @PostMapping(PAGE_SAVE)
    public AjaxResult save(@RequestBody PbcFinInstitutionAssetInfo001Dto dto) throws Exception {
        Integer result = pbcFinInstitutionAssetInfoMService.save(dto);
        return new AjaxResult("操作成功");
    }


}
