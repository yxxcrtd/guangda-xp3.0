package com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
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
 * 网点信息映射表管理
 *
 * @author zhoubin
 * @version 1.0 2022年5月16日
 * @see ChbBranchNetwork002Controller
 * @since 1.0
 */

@Controller
@PageId("ChbBranchNetwork002")
@RequestMapping("/chb/businessdatamaintain/chbbranchnetwork/ChbBranchNetwork002")
public class ChbBranchNetwork002Controller extends BaseController {

    /**
     * 网点信息映射表管理service
     */
    @Resource
    private ChbBranchNetworkService chbBranchNetworkService;

    /**
     * 初始化新建页面
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.监管机构2, DictCode.销售区域_中债);
        String branchNo = getParam("branchNo");
        ChbBranchNetworkDto dto = null;
        // 修改时
        if (StringUtil.isNotBlank(branchNo)) {
            Map<String, Object> pageParam = new HashMap<>(1);
            pageParam.put("branchNo", branchNo);
            List<ChbBranchNetworkDto> selectAllList = chbBranchNetworkService.selectAllList(pageParam);
            if (CollectionUtils.isNotEmpty(selectAllList)) {
                dto = selectAllList.get(0);
            }
            model.addAttribute("entity", dto);
        }  else {
            dto = new ChbBranchNetworkDto();
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
            ValidateGroupEdit.class}) ChbBranchNetworkDto dto, BindingResult result) {
        //新建时判断业务之间是否存在
        if (!Objects.equals("1", dto.getPageModel())) {
            Integer count = chbBranchNetworkService.getChbBranchNetworkByKey(dto);
            if (count > 0) {
                List<ValidMessage> messages = new ArrayList<>(1);
                messages.add(new ValidMessage("网点编号["+dto.getBranchNo()+"]已存在,不能提交!"));
                AjaxResult m = new AjaxResult();
                m.setErrorList(messages);
                return m;
            }
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
            ValidateGroupEdit.class}) ChbBranchNetworkDto dto) throws Exception {
        chbBranchNetworkService.submit(dto);
        return new AjaxResult("操作成功");

    }

}
