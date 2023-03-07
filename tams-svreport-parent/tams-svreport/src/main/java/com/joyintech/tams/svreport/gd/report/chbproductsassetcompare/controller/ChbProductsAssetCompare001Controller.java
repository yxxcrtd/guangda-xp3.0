package com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.*;
import com.joyintech.tams.report.gd.report.chbproductsassetcompare.dto.ChbProductsAssetCompare001Dto;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.dao.ChbProductsAssetCompare001Mapper;
import com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.service.ChbProductsAssetCompare001Service;
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
import com.joyintech.framework.grid.Page;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Controller
@PageId("ChbProductsAssetCompare001")
@RequestMapping("/svreport/gd/report/chbproductsassetcompare/ChbProductsAssetCompare001")
public class ChbProductsAssetCompare001Controller extends BaseController {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChbProductsAssetCompare001Service chbProductsAssetCompare001Service;

    @Autowired
    private ChbProductsAssetCompare001Mapper chbProductsAssetCompare001Mapper;

    @Override
    public String init(Model model) {
        return super.init(model);
    }

    @RequestMapping("findPage")
    @ResponseBody
    public Page<ChbProductsAssetCompare001Dto> findPage() {
        return chbProductsAssetCompare001Service.findPage(RequestUtil.getPageParam());
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
        List<ChbProductsAssetCompare001Dto> tempList=new ArrayList<ChbProductsAssetCompare001Dto>();
        List<ChbProductsAssetCompare001Dto> tempList1=new ArrayList<ChbProductsAssetCompare001Dto>();
        List<ChbProductsAssetCompare001Dto> tempList2=new ArrayList<ChbProductsAssetCompare001Dto>();
        List<ChbProductsAssetCompare001Dto> tempList3=new ArrayList<ChbProductsAssetCompare001Dto>();
        ChbProductsAssetCompare001Dto nullDate = new ChbProductsAssetCompare001Dto();
        
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
                tempList = chbProductsAssetCompare001Service.queryAllList(params);
                tempList1 = chbProductsAssetCompare001Service.queryAllList1(params);
                tempList.forEach(dto -> {
                    dto.setDateStatusStr(dto.getDateStatusStr());
                });

                tempList1.forEach(entity -> {
                    if (entity.getTotalShares() != null && entity.getTotalAmt() != null) {

                        BigDecimal difference = BigDecimal.ZERO;
                        entity.setDifference(difference);
                        if (null!=entity.getTotalShares() && entity.getTotalShares().compareTo(BigDecimal.ZERO)!=0) {
                            BigDecimal differenceRate = difference.divide(entity.getTotalShares(), 2, BigDecimal.ROUND_HALF_UP);

                            entity.setDifferenceRate(String.valueOf(differenceRate.abs()));
                        } else {
                            entity.setDifferenceRate(null);
                        }
                    } else {
                        entity.setDifference(BigDecimal.ZERO);
                        entity.setDifferenceRate(null);
                    }
                });

                if (CollectionUtils.isNotEmpty(tempList)) {
                    lastday = tempList.get(0).getDateStatus();
                } else {
                    // 无数据
                    lastday = calendar.getTime();
                }
            }
            else {
                // 有选中行,有参数
                lastday = calendar.getTime();
                keys = params.get("keys").toString();
                tempList = JsonUtils.json2List(keys, ChbProductsAssetCompare001Dto.class);
                List<ChbProductsAssetCompare001Dto> tempParams1 = JsonUtils.json2List(keys, ChbProductsAssetCompare001Dto.class);
                Map<String, Object> param1s = new HashMap<String, Object>();
                param1s.put("dateStatus",tempParams1.get(0).getDateStatus());
                tempList1 = chbProductsAssetCompare001Service.queryAllList1(params);
                tempList1.forEach(entity -> {
                    if (entity.getTotalShares() != null && entity.getTotalAmt() != null) {

                        BigDecimal difference = entity.getTotalShares().subtract(entity.getTotalAmt());
                        entity.setDifference(difference);
                        if (difference != BigDecimal.ZERO && entity.getTotalShares() != null) {
                            BigDecimal differenceRate = difference.divide(entity.getTotalShares(), 2, BigDecimal.ROUND_HALF_UP);

                            entity.setDifferenceRate(String.valueOf(differenceRate.abs()));
                        } else {
                            entity.setDifferenceRate(null);
                        }
                    } else {
                        entity.setDifference(BigDecimal.ZERO);
                        entity.setDifferenceRate(null);
                    }
                });
            }
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            params.put("dateStatus",DateUtil.format(lastday));
            if (!params.containsKey("keys")) {
                tempList = chbProductsAssetCompare001Service.queryAllList(params);

                tempList1 = chbProductsAssetCompare001Service.queryAllList1(params);
                tempList.forEach(dto -> {
                    dto.setDateStatusStr(dto.getDateStatusStr());
                });
                tempList1.forEach(entity -> {
                    if (entity.getTotalShares() != null && entity.getTotalAmt() != null) {

                        BigDecimal difference = entity.getTotalShares().subtract(entity.getTotalAmt());
                        entity.setDifference(difference);
                        if (difference != BigDecimal.ZERO && entity.getTotalShares() != null) {
                            BigDecimal differenceRate = difference.divide(entity.getTotalShares(), 2, BigDecimal.ROUND_HALF_UP);

                            entity.setDifferenceRate(String.valueOf(differenceRate.abs()));
                        } else {
                            entity.setDifferenceRate(null);
                        }
                    } else {
                        entity.setDifference(BigDecimal.ZERO);
                        entity.setDifferenceRate(null);
                    }
                });
            } else {
                keys = params.get("keys").toString();
                tempList = JsonUtils.json2List(keys, ChbProductsAssetCompare001Dto.class);
                tempList1 = JsonUtils.json2List(keys, ChbProductsAssetCompare001Dto.class);
            }
        }
        if(!StringUtil.isEmptyList(tempList)) {
            data.put("dataList", tempList);
            tempList2.add(tempList.get(0));
            data.put("dataList2",tempList2);
        }else{
            tempList.add(nullDate);
            data.put("dataList", tempList);
            nullDate.setDateStatusStr(DateUtil.format(lastday, DateUtil.DEFAULT_DATE_FORMAT_EN));
            tempList.add(nullDate);
            data.put("dataList2",tempList);
        }
        if(!StringUtil.isEmptyList(tempList1)) {
            data.put("dataList1", tempList1);
            tempList3.add(tempList.get(0));
            data.put("dataList3",tempList3);
        }else{
            tempList1.add(nullDate);
            data.put("dataList1", tempList1);
        }

        File depositfile = TisUtil.createFile(data,"pbc/" +"ChbProductsAssetCompare_export", "理财产品资产对照表"+ DateUtil.format(lastday,DateUtil.DEFAULT_OTHER_FORMAT)+".xls");
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
        return chbProductsAssetCompare001Service.checkExport(param);
    }

    /**
     * 启动批处理导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "batchExport")
    public AjaxResult batchExport(@RequestParam Map<String, Object> param) {
        return chbProductsAssetCompare001Service.batchExport(param);
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
        if (chbProductsAssetCompare001Mapper.countExportNum(param) == 0) {
            JoyinMessage ms = new JoyinMessage("当前日期无数据，请确认是否导出？", MessageType.确认消息);
            joyinMessages.add(ms);
            wr.setJoyinMessages(joyinMessages);
        }
        return wr;
    }
}
