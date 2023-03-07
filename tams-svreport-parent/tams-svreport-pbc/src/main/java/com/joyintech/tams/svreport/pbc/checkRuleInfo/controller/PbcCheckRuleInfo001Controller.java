package com.joyintech.tams.svreport.pbc.checkRuleInfo.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.dto.PbcCheckRuleInfo001;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.service.PbcCheckRuleInfo001Service;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.service.PbcCheckRuleInfoMService;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @Author:yangkunfeng
 * @Date:2022/11/30 17:49
 * @Description:数据校验规则
 */
@PageId("PbcCheckRuleInfo001")
@Controller
@RequestMapping("/svreport/checkRule/PbcCheckRuleInfo001")
public class PbcCheckRuleInfo001Controller extends BaseController {

    @Autowired
    private PbcCheckRuleInfo001Service pbcCheckRuleInfo001Service;

    @Autowired
    private PbcCheckRuleInfoMService pbcCheckRuleInfoMService;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        super.setSelectOptionToModel(model,DictCode.数据校验规则类型, DictCode.数据校验规则大类,
                DictCode.数据校验规则标识, DictCode.数据校验规则状态,DictCode.大数据采集平台报表类型);
        return super.init(model);
    }

    /**
     * 分页查询
     *
     * @return 查询数据
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<PbcCheckRuleInfo001> selectForPage() {
        return pbcCheckRuleInfoMService.selectForPage(RequestUtil.getPageParam());
    }


    /**
     * 删除
     *
     * @param dto 数据对象
     * @param br
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody PbcCheckRuleInfo001 dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Integer result = pbcCheckRuleInfoMService.delete(dto);
        return new AjaxResult("删除成功");
    }

    /**
     * excel 导出
     *
     * @return 导出数据
     * @throws Exception 异常
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws IOException {
        Map<String, Object> params = RequestUtil.getPageParam();
        List<PbcCheckRuleInfo001> list = Lists.newArrayList();
        return pbcCheckRuleInfo001Service.export(this.getPageId(), params);
    }

    @ResponseBody
    @RequestMapping("effitive")
    public AjaxResult effitive(@RequestBody Map<String,Object> param){
        pbcCheckRuleInfoMService.updateStatus(param);

        return new AjaxResult("保存成功");

    }
}
