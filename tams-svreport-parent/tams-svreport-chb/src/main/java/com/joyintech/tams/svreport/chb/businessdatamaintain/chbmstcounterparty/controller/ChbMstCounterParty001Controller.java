package com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.controller;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.dto.ChbBokSubjectDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstbondcreditrating.dto.ChbMstBondCreditRatingDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.dto.ChbMstCounterPartyDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.service.ChbMstCounterPartyService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cff
 * @date 2022-05-17 
 */
@Controller
@PageId("ChbMstCounterParty001")
@RequestMapping("/report/chb/businessdatamaintain/chbmstcounterparty/ChbMstCounterParty001")
public class ChbMstCounterParty001Controller extends BaseController {
    @Autowired
    private ChbMstCounterPartyService chbMstCounterPartyService;
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易对手类别,DictCode.客户二级类型,DictCode.维护数据状态);

        return super.init(model);
    }

    /**
     * 交易对手名称
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCustomerName")
    public Page<SelectOptionDto> getCustomerName()
    {
        return chbMstCounterPartyService.getCustomerName(RequestUtil.getPageParam());
    }

    /**
     * 交易对手名称
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCounterName")
    public Page<SelectOptionDto> getCounterName()
    {
        return chbMstCounterPartyService.getCounterName(RequestUtil.getPageParam());
    }

    /**
     * 检索页面列表
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<ChbMstCounterPartyDto> selectAll()
    {
        return chbMstCounterPartyService.selectForPage(RequestUtil.getPageParam());
    }
    /**
     *  删除
     * @param dto 页面选中的数据
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody  ChbMstCounterPartyDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<ChbMstCounterPartyDto> processParameter = new ProcessParameter<ChbMstCounterPartyDto>();
        WorkFlowParameter<ChbMstCounterPartyDto> params = new WorkFlowParameter<ChbMstCounterPartyDto>();
        params.setPageId("ChbMstCounterParty002");
        params.setElementId("ChbMstCounterParty002_btn_delete");
        params.setBusinessCode(item -> item.getCounterId());
        params.setBusinessType(DictCodeList.共通业务类型_交易对手信息.getValue());
        params.setComment(entity -> this.getMessage("ChbMstCounterParty001.counterId") + "("
                + entity.getCounterId() + ")"
                + this.getMessage("ChbMstCounterParty001.counterType")
                + "(" + entity.getCounterType() + ")"
                + this.getMessage("ChbMstCounterParty001.counterName")
                + "(" + entity.getCounterName() + ")");
        processParameter.setWorkFlowParameter(params);
        dto.getSelectedItem().forEach(e->{
            e.setCreateUser(CommonUtil.getUserName());
            e.setCreateTime(DateUtil.now());
            e.setUpdateUser(CommonUtil.getUserName());
            e.setUpdateTime(DateUtil.now());
        });
        String msg = chbMstCounterPartyService.start(processParameter,dto.getSelectedItem().toArray(new ChbMstCounterPartyDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);

    }
    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        List<ChbMstCounterPartyDto> list = null;
        //选中行
        if(StringUtils.isNotEmpty((String)params.get("selectData"))){
            List<Map> selectData = JSONArray.parseArray((String) params.get("selectData"), Map.class);
            List<String> uuid = selectData.stream().map(e -> (String)e.get("uuid")).collect(Collectors.toList());
            list = chbMstCounterPartyService.selectBykeys(uuid);
        }else {
            params.put("counterTypes", StringUtil.isNotEmpty(params.get("counterType"))?(params.get("counterType").toString().split(",")):null);
            params.put("counterNames", StringUtil.isNotEmpty(params.get("counterName"))?(params.get("counterName").toString().split(",")):null);
            params.put("counterType2s", StringUtil.isNotEmpty(params.get("counterType2"))?(params.get("counterType2").toString().split(",")):null);
            list = chbMstCounterPartyService.selectAll(params);
        }
//        list.forEach(item->{
////            item.setGradeDateStr(DateUtil.format(item.getGradeDate(),"yyyy-MM-dd"));
//            item.setUpdateTimeStr(DateUtil.format(item.getUpdateTime(),"yyyy-MM-dd"));
//        });
        File outFile = chbMstCounterPartyService.makeFile(
                "/com/joyintech/tams/svreport/chb/businessdatamaintain/chbmstcounterparty/conf/export_conf.xml",
                list);
        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date())
                        + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT
                        + FileNameUtil.getFileExtName(outFile),
                outFile, true);

    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping(value = "checkConfirmMsgForImportData", method = RequestMethod.POST)
    public AjaxResult checkConfirmMsgForImportData(String fileName,String tableName) throws Exception {
        return chbMstCounterPartyService.checkConfirmMsgForImportData(fileName,tableName);
    }

    @ResponseBody
    @RequestMapping("/importFileSave")
    public List<com.joyintech.tams.systemtools.dto.ExcelValidDto> importData(String fileName,String tableName) throws Exception {
        return this.chbMstCounterPartyService.importData(fileName,tableName);
    }
}
