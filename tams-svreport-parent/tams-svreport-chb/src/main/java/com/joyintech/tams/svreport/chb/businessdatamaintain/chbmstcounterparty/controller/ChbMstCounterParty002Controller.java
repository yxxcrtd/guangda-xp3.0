package com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.dto.ChbMstCounterPartyDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.service.ChbMstCounterPartyService;
import com.joyintech.tams.svreport.chb.common.util.ErrdataConvertUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-04-27 14:21
 */
@Controller
@PageId("ChbMstCounterParty002")
@RequestMapping("/report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty002")
public class ChbMstCounterParty002Controller extends BaseController {
    @Autowired
    private ChbMstCounterPartyService chbMstCounterPartyService;
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易对手类别,DictCode.客户二级类型,DictCode.关联交易情况);
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String uuid = getParam("uuid");
        String flag = getParam("info");
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            ChbMstCounterPartyDto dto = chbMstCounterPartyService.getBussinessEntityByWorkflowId(
                    workflowId);
            model.addAttribute("pageModel",dto.getPageModel());
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
        else if (WorkFlowUtil.isNotFromToDoList() &&StringUtil.isNotEmpty(uuid) ) {
            //通过主键查询数据
            ChbMstCounterPartyDto dto = null;
            try {
                if("INFO".equals(flag)) {
                    dto = chbMstCounterPartyService.selectByKey(uuid);
                }else{
                    ChbMstCounterPartyErrodataWithBLOBs errorDto=chbMstCounterPartyService.selectErrorByKey(uuid);
                    dto=ErrdataConvertUtil.errdataChbMstCounterPartyConvert(dto,errorDto);
                    dto.setUuid(errorDto.getUuid());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute(GlobalConstant.ENTITY, dto);
            model.addAttribute("Counter_Name1",dto.getCounterName());
            model.addAttribute("Counter_Name5",dto.getCounterName());

        }
        return super.init(model);
    }

    /**
     * 根据交易对手名称查找交易对手代码
     * @param counterName
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectCounterIdByCounterName")
    public  Map<String, String> selectCounterIdByCounterName(String counterName){
        return chbMstCounterPartyService.selectCounterIdByCounterName(counterName);
    }

    /**
     * 产品下拉框数据选择
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodId")
    public List<SuggestDto> getCustomerName(){
        return chbMstCounterPartyService.getFinprodId(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("/getRivalPartyName")
    public List<SuggestDto> getRivalPartyName(){
        return chbMstCounterPartyService.getRivalPartyName(RequestUtil.getPageParam());
    }

    @ResponseBody
    @RequestMapping("/selectRivalPartyType")
    public Map<String,String> selectRivalPartyType(String counterName){
        return chbMstCounterPartyService.selectRivalPartyType(counterName);
    }

    /**
     * 保存按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) ChbMstCounterPartyDto dto,
                            BindingResult result) {

        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 数据保存操作
     * @param dto 请求实体类
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody ChbMstCounterPartyDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<ChbMstCounterPartyDto> processParameter = new ProcessParameter<ChbMstCounterPartyDto>();
        WorkFlowParameter<ChbMstCounterPartyDto> params = new WorkFlowParameter<ChbMstCounterPartyDto>();
        params.setPageId("ChbMstCounterParty002");
        params.setElementId("ChbMstCounterParty002_btn_submit");
        params.setBusinessCode(item -> item.getCounterId());
        params.setBusinessType(DictCodeList.共通业务类型_交易对手信息.getValue());
        params.setComment(entity -> this.getMessage("ChbMstCounterParty001.counterId") + "("
                + entity.getCounterId() + ")"
                + this.getMessage("ChbMstCounterParty001.counterName")
                + "(" + entity.getCounterName1() + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);

        dto.setCreateTime(DateUtil.now());
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateTime(DateUtil.now());
        dto.setUpdateUser(CommonUtil.getUserName());
        // 流程开始
        String msg = chbMstCounterPartyService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
}
