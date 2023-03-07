package com.joyintech.tams.svreport.specificcounterparty.controller;

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
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import com.joyintech.tams.svreport.specificcounterparty.service.PbcSpecificCounterPartyMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 特定目的载体对手方信息维护
 *
 * @author yangxianjiu
 * @date 2022/11/1
 */
@PageId("PbcSpecificCounterParty002")
@Controller
@RequestMapping("/svreport/specificcounterparty/PbcSpecificCounterParty002")
public class PbcSpecificCounterParty002Controller extends BaseController {

    /**
     *
     */
    public static final List<String> PBC_ASSET_TYPE = Arrays.asList("A5200", "A7200", "B1200", "C1100", "C1200");

    @Autowired
    private PbcSpecificCounterPartyMService pbcSpecificCounterPartyMService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {

        String pbcUuid = getParam("pbcUuid");
        PbcSpecificCounterParty001Dto dto = new PbcSpecificCounterParty001Dto();
        if (!StringUtil.isBlank(pbcUuid)) {
            // 查询数据
            dto = pbcSpecificCounterPartyMService.selectDetail(RequestUtil.getPageParam());
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);

        List<? extends DataDictItem> items = CacheUtil.getDictCode(DictCode.人行_资产负债项目.getValue()).getItems();
        List<SelectOptionDto> tempList = Lists.newArrayList();
        //
        items.forEach(item -> {
            if (PBC_ASSET_TYPE.contains(item.getItemCode())) {
                SelectOptionDto selectOptionDto = new SelectOptionDto();
                selectOptionDto.setId(item.getItemCode());
                selectOptionDto.setText(item.getItemValue());
                selectOptionDto.setCol(DictCode.人行_资产负债项目.getValue());
                tempList.add(selectOptionDto);
            }
        });
        model.addAttribute(DictCode.人行_资产负债项目.getValue(), JsonUtils.object2Json(tempList));

        super.setSelectOptionToModel(model, DictCode.人行_产品种类);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) PbcSpecificCounterParty001Dto dto, BindingResult result) {
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
    public AjaxResult save(@RequestBody PbcSpecificCounterParty001Dto dto) throws Exception {
        Integer result = pbcSpecificCounterPartyMService.save(dto);
        return new AjaxResult("保存成功");
    }

    /**
     * 获取 交易对手 机构名称
     * @return
     */
    @ResponseBody
    @RequestMapping("getPartyOrgNameByAssetCode")
    public Map<String, Object> getPartyOrgNameByAssetCode(){
        return pbcSpecificCounterPartyMService.getPartyOrgNameByAssetCode(RequestUtil.getPageParam());
    }

    /**
     * 获取 交易对手 产品名称
     * @return
     */
    @ResponseBody
    @RequestMapping("getPartyProdNameByAssetCode")
    public Map<String, Object> getPartyProdNameByAssetCode(){
        return pbcSpecificCounterPartyMService.getPartyProdNameByAssetCode(RequestUtil.getPageParam());
    }


    /**
     * 获取 交易对手产品种类
     * @return
     */
    @ResponseBody
    @RequestMapping("getPartyProdTypeByAssetCode")
    public Map<String, Object> getPartyProdTypeByAssetCode(){
        return pbcSpecificCounterPartyMService.getPartyProdTypeByAssetCode(RequestUtil.getPageParam());
    }
}
