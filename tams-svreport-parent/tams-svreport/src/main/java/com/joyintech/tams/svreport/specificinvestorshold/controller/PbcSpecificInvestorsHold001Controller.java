package com.joyintech.tams.svreport.specificinvestorshold.controller;


import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.svreport.specificinvestorshold.dto.PbcSpecificInvestorsHold001Dto;
import com.joyintech.tams.svreport.specificinvestorshold.service.PbcSpecificInvestorsHold001Service;
import com.joyintech.tams.svreport.specificinvestorshold.service.PbcSpecificInvestorsHoldMService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 特定目的载体持有信息维护(投资者)
 *
 * @author yangxianjiu
 * @date 2022/11/3
 */
@PageId("PbcSpecificInvestorsHold001")
@Controller
@RequestMapping("/svreport/specificinvestorshold/PbcSpecificInvestorsHold001")
public class PbcSpecificInvestorsHold001Controller extends BaseController {

    @Autowired
    private PbcSpecificInvestorsHoldMService pbcSpecificInvestorsHoldMService;

    @Autowired
    private PbcSpecificInvestorsHold001Service pbcSpecificInvestorsHold001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        model.addAttribute("holdDataStart", DateUtil.format(DateUtil.platDateFromCache()));
        model.addAttribute("holdDataEnd", DateUtil.format(DateUtil.platDateFromCache()));
        this.setSelectOptionToModel(model, DictCode.维护数据状态);
        return super.init(model);
    }

    /**
     * 分页查询
     *
     * @return 查询数据
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<PbcSpecificInvestorsHold001Dto> selectForPage() {
        return pbcSpecificInvestorsHoldMService.selectForPage(RequestUtil.getPageParam());
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
    public AjaxResult delete(@RequestBody PbcSpecificInvestorsHold001Dto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Integer result = pbcSpecificInvestorsHoldMService.delete(dto);
        return new AjaxResult("删除成功");
    }

    /**
     * excel 导出
     *
     * @return 导出数据
     * @throws Exception 异常
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        Map<String, Object> params = RequestUtil.getPageParam();
        return pbcSpecificInvestorsHold001Service.export(this.getPageId(), params);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    @ResponseBody
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(String fileName) throws Exception {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("fileName", fileName);
        return pbcSpecificInvestorsHold001Service.checkConfirmMsgForImportData(map);
    }


    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    @ResponseBody
    @RequestMapping("importData")
    public List<ExcelValidDto> importData(String fileName) throws Exception {
        return pbcSpecificInvestorsHoldMService.importData(fileName);
    }


}
