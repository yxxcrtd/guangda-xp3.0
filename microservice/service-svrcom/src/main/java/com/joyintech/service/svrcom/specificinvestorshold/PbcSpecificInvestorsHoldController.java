package com.joyintech.service.svrcom.specificinvestorshold;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.svreport.specificinvestorshold.dto.PbcSpecificInvestorsHold001Dto;
import com.joyintech.tams.svreport.specificinvestorshold.service.PbcSpecificInvestorsHold001Service;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 特定目的载体持有信息维护(投资者)
 *
 * @author yangxianjiu
 * @date 2022/11/4
 */
@RestController
@RequestMapping("/PbcSpecificInvestorsHold")
public class PbcSpecificInvestorsHoldController {

    @Autowired
    private PbcSpecificInvestorsHold001Service pbcSpecificInvestorsHold001Service;

    /**
     * 分页查询
     *
     * @param pageParam
     * @param page      页
     * @param length
     * @return
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<PbcSpecificInvestorsHold001Dto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") Integer page, @PathVariable("length") Integer length) {
        return pbcSpecificInvestorsHold001Service.selectForPage(page, length, pageParam);
    }

    /**
     * 双击详情
     *
     * @param paramMap
     * @return
     */
    @PostMapping("selectDetail")
    public PbcSpecificInvestorsHold001Dto selectDetail(@RequestBody Map<String, Object> paramMap) {
        return pbcSpecificInvestorsHold001Service.selectDetail(paramMap);
    }

    /**
     * 保存
     *
     * @param dto
     */
    @PostMapping("save")
    public Integer save(@RequestBody PbcSpecificInvestorsHold001Dto dto) {
        return pbcSpecificInvestorsHold001Service.save(dto);
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PostMapping("delete")
    public Integer delete(@RequestBody PbcSpecificInvestorsHold001Dto dto) {
        return pbcSpecificInvestorsHold001Service.delete(dto);
    }

    /**
     * 导入数据文件
     *
     * @param param 文件名
     * @return 校验结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) throws Exception {
        return pbcSpecificInvestorsHold001Service.importData(param);
    }

    @PostMapping("/getProdInsId/{page}/{length}")
    public Page<SelectOptionDto> getProdInsId(@RequestBody Map<String, Object> param, @PathVariable("page") Integer page, @PathVariable("length") Integer length) {
        return pbcSpecificInvestorsHold001Service.getProdInsId(page, length, param);
    }

    /**
     * 获取 人行产品代码
     *
     * @param param
     * @return
     */
    @PostMapping("getPbcProdIdByAccount")
    public Map<String, Object> getPbcProdIdByAccount(@RequestBody Map<String, Object> param) {
        return pbcSpecificInvestorsHold001Service.getPbcProdIdByAccount(param);
    }

    /**
     * 识别标识 与 投资者名称 校重
     *
     * @param param
     * @return
     */
    @PostMapping("getIndentifyMarkAndHoldName")
    public Integer getIndentifyMarkAndHoldName(@RequestBody Map<String, Object> param) {
        return pbcSpecificInvestorsHold001Service.getIndentifyMarkAndHoldName(param);
    }

    /**
     * 获取 身份登记 识别标识
     *
     * @param param
     * @return
     */
    @PostMapping("/getIndentifyMark")
    public List<SuggestDto> getIndentifyMark(@RequestBody Map<String, Object> param) {
        return pbcSpecificInvestorsHold001Service.getIndentifyMark(param);
    }

    /**
     * 获取 投资者 类别
     *
     * @param param
     * @return
     */
    @PostMapping("getHolderTypeByIndentifyMark")
    public Map<String, Object> getHolderTypeByIndentifyMark(@RequestBody Map<String, Object> param) {
        return pbcSpecificInvestorsHold001Service.getHolderTypeByIndentifyMark(param);
    }
}
