package com.joyintech.service.chb.gd.risk.valtblassetcheck.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.chb.gd.risk.valtblassetcheck.dto.RskValAssetCheckDto;
import com.joyintech.service.chb.gd.risk.valtblassetcheck.service.RskValAssetCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("rskValAssetCheck_dtx")
@RequestMapping("/RskValAssetCheck")
public class RskValAssetCheckController {

    @Autowired
    private RskValAssetCheckService rskValAssetCheckService;

    /**
     * 查询
     * @param param
     * @param page
     * @param length
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskValAssetCheckDto> selectAll(@RequestBody Map<String, Object> param,
                                               @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return rskValAssetCheckService.selectForPage(page, length, param);
    }

}
