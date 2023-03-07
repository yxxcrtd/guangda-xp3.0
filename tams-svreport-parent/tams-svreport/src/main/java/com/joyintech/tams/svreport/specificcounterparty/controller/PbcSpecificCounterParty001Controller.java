package com.joyintech.tams.svreport.specificcounterparty.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import com.joyintech.tams.svreport.specificcounterparty.service.PbcSpecificCounterParty001Service;
import com.joyintech.tams.svreport.specificcounterparty.service.PbcSpecificCounterPartyMService;
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
 * 特定目的载体对手方信息维护
 *
 * @author yangxianjiu
 * @date 2022/11/1
 */
@PageId("PbcSpecificCounterParty001")
@Controller
@RequestMapping("/svreport/specificcounterparty/PbcSpecificCounterParty001")
public class PbcSpecificCounterParty001Controller extends BaseController {

    @Autowired
    private PbcSpecificCounterPartyMService pbcSpecificCounterPartyMService;

    @Autowired
    private PbcSpecificCounterParty001Service pbcSpecificCounterParty001Service;

    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        List<? extends DataDictItem> items = CacheUtil.getDictCode(DictCode.人行_资产负债项目.getValue()).getItems();
        List<SelectOptionDto> tempList = Lists.newArrayList();
        //
        items.forEach(item -> {
            if (PbcSpecificCounterParty002Controller.PBC_ASSET_TYPE.contains(item.getItemCode())) {
                SelectOptionDto selectOptionDto = new SelectOptionDto();
                selectOptionDto.setId(item.getItemCode());
                selectOptionDto.setText(item.getItemValue());
                selectOptionDto.setCol(DictCode.人行_资产负债项目.getValue());
                tempList.add(selectOptionDto);
            }
        });
        model.addAttribute(DictCode.人行_资产负债项目.getValue(), JsonUtils.object2Json(tempList));
        this.setSelectOptionToModel(model, DictCode.维护数据状态, DictCode.人行_产品种类);
        return super.init(model);
    }

    /**
     * 分页查询
     *
     * @return 查询数据
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<PbcSpecificCounterParty001Dto> selectForPage() {
        return pbcSpecificCounterPartyMService.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 获取 资产要素 资产代码
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("queryAssetCode")
    public List<SuggestDto> queryAssetCode(String param) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("param", param);
        map.put("flag", "asset");
        return pbcSpecificCounterPartyMService.queryAssetCodeIndentifyMark(map);
    }

    /**
     * 获取 特定目的持有 识别标识
     *
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("queryIndentifyMark")
    public List<SuggestDto> queryIndentifyMark(String param) {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("param", param);
        map.put("flag", "mark");
        return pbcSpecificCounterPartyMService.queryAssetCodeIndentifyMark(map);
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
    public AjaxResult delete(@RequestBody PbcSpecificCounterParty001Dto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        Integer result = pbcSpecificCounterPartyMService.delete(dto);
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
        List<PbcSpecificCounterParty001Dto> list = Lists.newArrayList();
        return pbcSpecificCounterParty001Service.export(this.getPageId(), params);
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
        HashMap<String, Object> param = Maps.newHashMap();
        param.put("fileName", fileName);
        return pbcSpecificCounterParty001Service.checkConfirmMsgForImportData(param);
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
        return pbcSpecificCounterPartyMService.importData(fileName);
    }


}
