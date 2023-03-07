package com.joyintech.service.svrcom.submitbefore;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.submitbefore.dto.PbcSubmitBeforeCheck001Dto;
import com.joyintech.tams.svreport.submitbefore.service.PbcSubmitBeforeCheck001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 报送前检查
 *
 * @author yangxianjiu
 * @date 2022/11/29
 */
@RestController
@RequestMapping("/PbcSubmitBeforeCheck")
public class PbcSubmitBeforeCheckController {

    @Autowired
    private PbcSubmitBeforeCheck001Service pbcSubmitBeforeCheck001Service;

    /**
     * 分页查询
     *
     * @param pageParam
     * @param page      页
     * @param length
     * @return
     */
    @PostMapping("/searchPage/{page}/{length}")
    public Page<PbcSubmitBeforeCheck001Dto> searchPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") Integer page, @PathVariable("length") Integer length) {
        return pbcSubmitBeforeCheck001Service.searchPage(page, length, pageParam);
    }

}