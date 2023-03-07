package com.joyintech.tams.svreport.gd.report.chbproductsdealsub.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.gd.report.chbproductsdealsub.dto.ChbProductsDealSub001Dto;
import com.joyintech.tams.report.submitmaintenance.salerinstinfo.dto.SalerInstInfoDto;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dao.ChbProductsDealSubMapper;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.service.ChbProductsDealSub001Service;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;

/**
 * 理财产品交易明细登记表
 * @author yangxianjiu
 * @date 2022/03/30
 */
@Controller
@PageId("ChbProductsDealSub001")
@RequestMapping("/svreport/gd/report/chbproductsdealsub/ChbProductsDealSub001")
public class ChbProductsDealSub001Controller extends BaseController {

    @Autowired
    private ChbProductsDealSub001Service chbProductsDealSub001Service;

    @Autowired
    private ChbProductsDealSubMapper chbProductsDealSubMapper;

    /**
     * 初始化
     * @param model
     * @return
     */
    @RequestMapping(PAGE_INIT)
    @Override
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @PostMapping("selectForPage")
    public Page<ChbProductsDealSub001Dto> selectForPage() {
        return chbProductsDealSub001Service.selectForPage(RequestUtil.getPageParam());
    }

    /**
     * 导出
     * @return
     * @throws Exception
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        String keys = this.getParam("keys");
        if (keys != null) {
            params.put("keys", keys);
        }
        return chbProductsDealSub001Service.download(params);
    }

    /**
     * 导出检查
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkExport")
    public AjaxResult checkExport(@RequestBody Map<String, Object> param) {
        return chbProductsDealSub001Service.checkExport(param);
    }

    /**
     * 启动批处理导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "batchExport")
    public AjaxResult batchExport(@RequestParam Map<String, Object> param) {
        return chbProductsDealSub001Service.batchExport(param);
    }


    /**
     * 导出check
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "Check")
    public AjaxResult Check(@RequestBody Map<String, Object> param) {
        AjaxResult wr = new AjaxResult();
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        // 获取平台日期
        Date sysDate = DateUtil.platDateFromCache();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysDate);
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        Date lastday = calendar.getTime();
        // 有导出日期
        if (StringUtil.isNotEmpty(param.get("dateStatus"))) {
            // 获取当前月的最后一天
            String dateStatus = param.get("dateStatus").toString();
            calendar.setTime(DateUtil.parse(dateStatus + "-01"));
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            param.put("dateStatus", dateStatus);
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            param.put("dateStatus", DateUtil.format(lastday));
        }
            if (chbProductsDealSubMapper.countExportNum(param) == 0) {
                JoyinMessage ms = new JoyinMessage("当前日期无数据，请确认是否导出？", MessageType.确认消息);
                joyinMessages.add(ms);
                wr.setJoyinMessages(joyinMessages);
            }
        return wr;
    }
}
