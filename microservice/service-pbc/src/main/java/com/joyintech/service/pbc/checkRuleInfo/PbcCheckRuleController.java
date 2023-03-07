package com.joyintech.service.pbc.checkRuleInfo;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.dto.PbcCheckRuleInfo001;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.service.PbcCheckRuleInfo001Service;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author:yangkunfeng
 * @Date:2022/11/30 20:11
 * @Description:
 */
@RestController
@RequestMapping("/PbcCheckRule")
public class PbcCheckRuleController {
    @Autowired
    private PbcCheckRuleInfo001Service pbcCheckRuleInfo001Service;

    /**
     * 分页查询
     *
     * @param pageParam
     * @param page      页
     * @param length
     * @return
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<PbcCheckRuleInfo001> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") Integer page, @PathVariable("length") Integer length) {
        return pbcCheckRuleInfo001Service.selectForPage(page, length, pageParam);
    }
    /**
     * 保存
     *
     * @param dto
     */
    @PostMapping("save")
    public Integer save(@RequestBody PbcCheckRuleInfo001 dto) {
        return pbcCheckRuleInfo001Service.save(dto);
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PostMapping("delete")
    public Integer delete(@RequestBody PbcCheckRuleInfo001 dto) {
        return pbcCheckRuleInfo001Service.delete(dto);
    }

    @PostMapping("selectDetail")
    public PbcCheckRuleInfo001 selectDetail(@RequestBody Map<String, Object> paramMap) {
        return pbcCheckRuleInfo001Service.selectDetail(paramMap);
    }

    @PostMapping("effitive")
    public void effitive(@RequestBody Map<String,Object> param){
        pbcCheckRuleInfo001Service.updateStatus(param);
    }
}
