package com.joyintech.tams.svreport.pbc.checkRuleInfo.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.dto.PbcCheckRuleInfo001;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.service.PbcCheckRuleInfoMService;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author:yangkunfeng
 * @Date:2022/11/30 17:50
 * @Description:
 */
@PageId("PbcCheckRuleInfo002")
@Controller
@RequestMapping("/svreport/checkRule/PbcCheckRuleInfo002")
public class PbcCheckRuleInfo002Controller extends BaseController {

    @Autowired
    private PbcCheckRuleInfoMService pbcCheckRuleInfoMService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {

        String ruleId = getParam("ruleId");
        PbcCheckRuleInfo001 dto = new PbcCheckRuleInfo001();
        if (!StringUtil.isBlank(ruleId)) {
            // 查询数据
            dto = pbcCheckRuleInfoMService.selectDetail(RequestUtil.getPageParam());
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);

        super.setSelectOptionToModel(model,DictCode.数据校验规则类型, DictCode.数据校验规则大类,
                DictCode.数据校验规则标识, DictCode.数据校验规则状态,DictCode.大数据采集平台报表类型,DictCode.数据校验规则方法);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) PbcCheckRuleInfo001 dto, BindingResult result) {
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
    public AjaxResult save(@RequestBody PbcCheckRuleInfo001 dto) throws Exception {
        Integer result = pbcCheckRuleInfoMService.save(dto);
        return new AjaxResult("保存成功");
    }
}
