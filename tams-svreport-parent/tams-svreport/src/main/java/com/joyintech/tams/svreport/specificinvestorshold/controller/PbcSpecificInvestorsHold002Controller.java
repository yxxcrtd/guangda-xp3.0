package com.joyintech.tams.svreport.specificinvestorshold.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.specificinvestorshold.dto.PbcSpecificInvestorsHold001Dto;
import com.joyintech.tams.svreport.specificinvestorshold.service.PbcSpecificInvestorsHoldMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 特定目的载体持有信息维护(投资者)
 *
 * @author yangxianjiu
 * @date 2022/11/3
 */
@PageId("PbcSpecificInvestorsHold002")
@Controller
@RequestMapping("/svreport/specificinvestorshold/PbcSpecificInvestorsHold002")
public class PbcSpecificInvestorsHold002Controller extends BaseController {

    public static final List<String> HOLDER_TYPE_LIST = Arrays.asList("17", "18", "19", "20", "21", "22");

    @Autowired
    private PbcSpecificInvestorsHoldMService pbcSpecificInvestorsHoldMService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String pbcUuid = getParam("uuid");
        PbcSpecificInvestorsHold001Dto dto = new PbcSpecificInvestorsHold001Dto();
        if (!StringUtil.isBlank(pbcUuid)) {
            // 查询数据
            dto = pbcSpecificInvestorsHoldMService.selectDetail(RequestUtil.getPageParam());
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);

        List<? extends DataDictItem> items = CacheUtil.getDictCode(DictCode.投资者类别.getValue()).getItems();
        List<SelectOptionDto> tempList = Lists.newArrayList();
        //
        items.forEach(item -> {
            if (HOLDER_TYPE_LIST.contains(item.getItemCode())) {
                SelectOptionDto selectOptionDto = new SelectOptionDto();
                selectOptionDto.setId(item.getItemCode());
                selectOptionDto.setText(item.getItemValue());
                selectOptionDto.setCol(DictCode.投资者类别.getValue());
                tempList.add(selectOptionDto);
            }
        });
        model.addAttribute(DictCode.投资者类别.getValue(), JsonUtils.object2Json(tempList));
        super.setSelectOptionToModel(model, DictCode.币种_中债);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) PbcSpecificInvestorsHold001Dto dto, BindingResult result) {
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) PbcSpecificInvestorsHold001Dto dto) throws Exception {
        Integer result = pbcSpecificInvestorsHoldMService.save(dto);
        return new AjaxResult("保存成功");
    }

    /**
     * 获取 内部产品代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProdInsId")
    public Page<SelectOptionDto> getProdInsId() {
        return pbcSpecificInvestorsHoldMService.getProdInsId(RequestUtil.getPageParam());
    }

    /**
     * 获取 人行产品代码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getPbcProdIdByAccount")
    public Map<String, Object> getPbcProdIdByAccount() {
        return pbcSpecificInvestorsHoldMService.getPbcProdIdByAccount(RequestUtil.getPageParam());
    }

    /**
     * 识别标识 与 投资者名称 校重
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getIndentifyMarkAndHoldName")
    public Integer getIndentifyMarkAndHoldName() {
        return pbcSpecificInvestorsHoldMService.getIndentifyMarkAndHoldName(RequestUtil.getPageParam());
    }

    /**
     * 获取 身份登记 识别标识
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getIndentifyMark")
    public List<SuggestDto> getIndentifyMark() {
        return pbcSpecificInvestorsHoldMService.getIndentifyMark(RequestUtil.getPageParam());
    }

    /**
     * 获取 投资者 类别
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getHolderTypeByIndentifyMark")
    public Map<String, Object> getHolderTypeByIndentifyMark() {
        return pbcSpecificInvestorsHoldMService.getHolderTypeByIndentifyMark(RequestUtil.getPageParam());
    }

}
