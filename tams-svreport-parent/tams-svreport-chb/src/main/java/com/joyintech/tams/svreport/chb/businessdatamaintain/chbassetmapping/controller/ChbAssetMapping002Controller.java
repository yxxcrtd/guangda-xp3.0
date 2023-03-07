package com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.dto.ChbAssetMappingDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.service.ChbAssetMappingService;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dto.ChbBranchNetworkDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.service.ChbBranchNetworkService;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * 资产代码匹配映射管理
 *
 * @author zhoubin
 * @version 1.0 2022年5月16日
 * @see ChbAssetMapping002Controller
 * @since 1.0
 */

@Controller
@PageId("ChbAssetMapping002")
@RequestMapping("/chb/businessdatamaintain/chbassetmapping/ChbAssetMapping002")
public class ChbAssetMapping002Controller extends BaseController {

    /**
     * 资产代码匹配映射管理service
     */
    @Resource
    private ChbAssetMappingService chbAssetMappingService;

    /**
     * 初始化新建页面
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        String uuid = getParam("uuid");
        ChbAssetMappingDto dto = null;
        // 修改时
        if (StringUtil.isNotBlank(uuid)) {
            Map<String, Object> pageParam = new HashMap<>(1);
            pageParam.put("uuid", uuid);
            List<ChbAssetMappingDto> selectAllList = chbAssetMappingService.selectAllList(pageParam);
            if (CollectionUtils.isNotEmpty(selectAllList)) {
                dto = selectAllList.get(0);
            }
            model.addAttribute("entity", dto);
        }  else {
            dto = new ChbAssetMappingDto();
            model.addAttribute("entity", dto);
        }
        return super.init(model);
    }

    /**
     * 保存时的check
     *
     * @param dto    检验输入参数
     * @param result 错误参数
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class}) ChbAssetMappingDto dto, BindingResult result) {
        String  message = chbAssetMappingService.getChbAssetMappingByKey(dto);
        if(StringUtil.isNotEmpty(message)){
            List<ValidMessage> messages = new ArrayList<>(1);
            messages.add(new ValidMessage(message));
            AjaxResult m = new AjaxResult();
            m.setErrorList(messages);
            return m;
        }
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 保存/修改
     *
     * @param dto 传入参数
     * @return 返回结果
     * @throws WorkFlowException 抛出异常
     */
    @ResponseBody
    @RequestMapping("submit")
    public AjaxResult submit(@RequestBody  @Validated({
            ValidateGroupEdit.class}) ChbAssetMappingDto dto) throws Exception {
        chbAssetMappingService.submit(dto);
        return new AjaxResult("操作成功");

    }


}
