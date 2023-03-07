package com.joyintech.tams.svreport.gd.risk.valtblassetcheck.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.svreport.gd.risk.valtblassetcheck.dto.RskValAssetCheckDto;
import com.joyintech.tams.svreport.gd.risk.valtblassetcheck.service.RskValAssetCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@PageId("RskValAssetCheck001")
@RequestMapping("/svreport/gd/risk/valtblassetcheck/RskValAssetCheck001")
public class RskValAssetCheck001Controller extends BaseController {

    @Autowired
    private RskValAssetCheckService rskValAssetCheckService;
    @Override
    public String init(Model model){
        return super.init(model);
    }

    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<RskValAssetCheckDto> selectAll()
            throws Exception {
        Page<RskValAssetCheckDto> page = rskValAssetCheckService.selectForPage(
                RequestUtil.getParameters(getRequest()));
        return page;
    }

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return rskValAssetCheckService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }


}
