package com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.report.gd.report.chbfinanceproductssub.dto.ChbFinanceProductsSub001Dto;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.dao.ChbFinanceProductsSub001Mapper;
import com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.service.ChbFinanceProductsSub001Service;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.*;

@Controller
@PageId("ChbFinanceProductsSub001")
@RequestMapping("/svreport/gd/report/chbfinanceproductssub/ChbFinanceProductsSub001")
public class ChbFinanceProductsSub001Controller extends BaseController {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChbFinanceProductsSub001Service chbFinanceProductsSub001Service;

    @Autowired
    private ChbFinanceProductsSub001Mapper chbFinanceProductsSub001Mapper;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @RequestMapping("findPage")
    @ResponseBody
    public Page<ChbFinanceProductsSub001Dto> findPage() {
        return chbFinanceProductsSub001Service.findPage(RequestUtil.getPageParam());
    }
    /**
     * 导出
     * @return
     * @throws Exception
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
            throws Exception {
        List<File> sourceFiles = new ArrayList<>();
        Map<String,Object> data  = new HashMap<String,Object>();
        List<ChbFinanceProductsSub001Dto> tempList=new ArrayList<ChbFinanceProductsSub001Dto>();
        List<ChbFinanceProductsSub001Dto> tempList1=new ArrayList<ChbFinanceProductsSub001Dto>();
        List<ChbFinanceProductsSub001Dto> tempList2=new ArrayList<ChbFinanceProductsSub001Dto>();
        ChbFinanceProductsSub001Dto nullDate = new ChbFinanceProductsSub001Dto();

        Map<String, Object> params= RequestUtil.getPageParam();
        String keys = this.getParam("keys");
        if (keys != null) {
            params.put("keys", keys);
        }

        Date sysDate =  DateUtil.platDateFromCache();// 获取平台日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysDate);
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        Date lastday = calendar.getTime();
        // 有导出参数
        if (params.containsKey("dateStatus")) {
            // 获取当前月的最后一天
            String dateStatus = params.get("dateStatus").toString();
            calendar.setTime(DateUtil.parse(dateStatus+"-01"));

            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH,0);
            params.put("dateStatus", DateUtil.format(calendar.getTime()));

            // 无选中行，有参数
            if (!params.containsKey("keys")) {
                tempList = chbFinanceProductsSub001Service.queryAllList(params);
                tempList.forEach(dto -> {
                    dto.setRaiseVdateStr(dto.getRaiseVdateStr());
                    dto.setRaiseEnddateStr(dto.getRaiseEnddateStr());
                    dto.setVdateStr(dto.getVdateStr());
                    dto.setMdateStr(dto.getMdateStr());
                    dto.setDateStatusStr(dto.getDateStatusStr());
                    dto.setContModeStr( dto.getContModeStr());
                    dto.setProfitTypeStr(dto.getProfitTypeStr());
                    dto.setOperateModeStr(dto.getOperateModeStr());
                    dto.setCmmodeStr( dto.getCmmodeStr());
                    dto.setInvAssetTypeStr( dto.getInvAssetTypeStr());
                    dto.setCustomerTypeStr( dto.getCustomerTypeStr());
                    dto.setInvestAraeStr(dto.getInvestAraeStr());
                });
                if (CollectionUtils.isNotEmpty(tempList)) {
                    lastday = tempList.get(0).getDateStatus();
                } else {
                    // 无数据
                    lastday = calendar.getTime();
                }
            }else {
                // 有选中行,有参数
                lastday = calendar.getTime();
                keys = params.get("keys").toString();
                tempList = JsonUtils.json2List(keys, ChbFinanceProductsSub001Dto.class);;
            }
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            params.put("dateStatus",DateUtil.format(lastday));
            if (!params.containsKey("keys")) {
                tempList = chbFinanceProductsSub001Service.queryAllList(params);
                tempList.forEach(dto -> {
                    dto.setRaiseVdateStr(dto.getRaiseVdateStr());
                    dto.setRaiseEnddateStr(dto.getRaiseEnddateStr());
                    dto.setVdateStr(dto.getVdateStr());
                    dto.setMdateStr(dto.getMdateStr());
                    dto.setDateStatusStr(dto.getDateStatusStr());
                    dto.setContModeStr( dto.getContModeStr());
                    dto.setProfitTypeStr(dto.getProfitTypeStr());
                    dto.setOperateModeStr(dto.getOperateModeStr());
                    dto.setCmmodeStr( dto.getCmmodeStr());
                    dto.setInvAssetTypeStr( dto.getInvAssetTypeStr());
                    dto.setCustomerTypeStr( dto.getCustomerTypeStr());
                    dto.setInvestAraeStr(dto.getInvestAraeStr());
                });
            }else {
                keys = params.get("keys").toString();
                tempList = JsonUtils.json2List(keys, ChbFinanceProductsSub001Dto.class);;
            }
        }
        if(!StringUtil.isEmptyList(tempList)) {
            data.put("dataList", tempList);
            tempList1.add(tempList.get(0));
            data.put("dataList1",tempList1);
            data.put("dataList2",tempList1);
        }else{
            tempList.add(nullDate);
            data.put("dataList", tempList);
            nullDate.setDateStatusStr(DateUtil.format(lastday, DateUtil.DEFAULT_DATE_FORMAT_EN));
            tempList.add(nullDate);
            data.put("dataList1",tempList);
            data.put("dataList2",tempList);
        }

        File depositfile = TisUtil.createFile(data,"pbc/" +"ChbFinanceProductsSub_export", "理财产品登记表"+ DateUtil.format(lastday,DateUtil.DEFAULT_OTHER_FORMAT)+".xls");
        sourceFiles.add(depositfile);
        File outFile = null;
        outFile = sourceFiles.get(0);
        return ExportUtil.export(outFile.getName()
                , outFile, true);
    }

    /**
     * 导出检查
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkExport")
    public AjaxResult checkExport(@RequestBody Map<String, Object> param) {
        return chbFinanceProductsSub001Service.checkExport(param);
    }

    /**
     * 启动批处理导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "batchExport")
    public AjaxResult batchExport(@RequestParam Map<String, Object> param) {
        return chbFinanceProductsSub001Service.batchExport(param);
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
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();
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
            calendar.setTime(com.joyintech.framework.util.DateUtil.parse(dateStatus + "-01"));
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            param.put("dateStatus", DateUtil.format(calendar.getTime()));
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            param.put("dateStatus", DateUtil.format(lastday));
        }
        if (chbFinanceProductsSub001Mapper.countExportNum(param) == 0) {
            JoyinMessage ms = new JoyinMessage("当前日期无数据，请确认是否导出？", MessageType.确认消息);
            joyinMessages.add(ms);
            wr.setJoyinMessages(joyinMessages);
        }
        return wr;
    }
}
