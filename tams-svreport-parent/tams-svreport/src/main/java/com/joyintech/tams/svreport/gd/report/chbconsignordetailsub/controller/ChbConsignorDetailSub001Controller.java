package com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.controller;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.gd.report.chbconsignordetailsub.dto.ChbConsignorDetailSub001Dto;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.service.ChbConsignorDetailSub001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 委托人明细数据登记表
 * @author yangxianjiu
 * @date 2022/03/29
 */
@Controller
@PageId("ChbConsignorDetailSub001")
@RequestMapping("/svreport/gd/report/chbconsignordetailsub/ChbConsignorDetailSub001")
public class ChbConsignorDetailSub001Controller extends BaseController {

    //
    @Autowired
    private ChbConsignorDetailSub001Service chbConsignorDetailSub001Service;

    /**
     * 初始化
     * @param model
     * @return
     */
    @RequestMapping(PAGE_INIT)
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.募集方式_中债, DictCode.月份,DictCode.投资人类型);
        return super.init(model);
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @PostMapping("selectForPage")
    public Page<ChbConsignorDetailSub001Dto> selectForPage() {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        //处理月份
        this.dealMonth(pageParam);
        return chbConsignorDetailSub001Service.selectForPage(pageParam);
    }

    /**
     *
     * @return
     */
    @ResponseBody
    @GetMapping("queryProdInfo")
    public List<SuggestDto> queryProdInfo(){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("prodId",RequestUtil.getPageParam().get("param"));
        return chbConsignorDetailSub001Service.queryProdInfo(paramMap);
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
        Map<String, Object> params= RequestUtil.getPageParam();
        String keys = this.getParam("keys");
        if (keys != null) {
            params.put("keys", keys);
        }
        //处理月份
        if(StringUtil.isNotEmpty(params.get("month"))) {
            this.dealMonth(params);
        }else{
            //获取平台日
            Date sysDate = DateUtil.platDateFromCache();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String lastDayHold = sdf.format(sysDate);
            //获得第一个-的位置
            int index=lastDayHold.indexOf("-");
            //根据第一个点的位置 获得第二个-的位置
            int index1=lastDayHold.indexOf("-", index+1);
            //获取平台日的月份
            String month = lastDayHold.substring(index+1,index1);;
            params.put("month",month);
            this.dealMonth(params);
        }
        List<ChbConsignorDetailSub001Dto> listdto = new ArrayList<ChbConsignorDetailSub001Dto>() ;
        String lastday = " ";
        if(StringUtil.isNotEmpty(params.get("month"))) {
            String monthString = params.get("month").toString();
            //获取平台日
            Date sysDate = DateUtil.platDateFromCache();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sysDate);
            int year = calendar.get(Calendar.YEAR);
            int month =  Integer.parseInt(monthString);
            lastday = String.valueOf(year) + "年" + String.valueOf(month) + "月";
        }else{
            //获取平台日
            Date sysDate = DateUtil.platDateFromCache();
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.MONTH_DATE_FORMAT_CN);
            lastday = sdf.format(sysDate);
        }

        // 未选择数据
        if (keys == null) {
            listdto = chbConsignorDetailSub001Service.queryAllList(params);
        } else {
            // 选择数据
            listdto = JsonUtils.json2List(keys, ChbConsignorDetailSub001Dto.class);
        }


        // 导出报送模板
        String filePath = "pbc/" +"ChbConsignorDetailSub001";
        // 导出
        if (params.containsKey("export")){
            filePath = "pbc/" +"ChbConsignorDetailSub001Export";
        }
        if(StringUtil.isEmptyList(listdto)){
            listdto.add(new ChbConsignorDetailSub001Dto());
        }

            data.put("dataList", listdto);

        File depositfile = TisUtil.createFile(data,filePath, "委托人明细数据"+ "_" +lastday+".xls");
        sourceFiles.add(depositfile);
        File outFile = null;
        outFile = sourceFiles.get(0);
        return ExportUtil.export(outFile.getName()
                , outFile, true);
    }

    /**
     * 处理月份取得当前月份的最后一天
     * @param paramMap
     * @return
     */
    public void dealMonth(Map<String, Object> paramMap) {
        String monthString = paramMap.get("month").toString();
        //获取平台日
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysDate);
        int year = calendar.get(Calendar.YEAR);
        int month =  Integer.parseInt(monthString);
        String lastDayHold = this.getLastDayOfMonth1(year,month);
        paramMap.put("lastDayHold",lastDayHold);
    }


    /**
     * 获取指定年月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month); //设置当前月的上一个月
        int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
        cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 导出检查
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkExport")
    public AjaxResult checkExport(@RequestBody Map<String, Object> param) {
        //处理月份
        if(StringUtil.isNotEmpty(param.get("month"))) {
            this.dealMonth(param);
        }else{
            //获取平台日
            Date sysDate = DateUtil.platDateFromCache();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String lastDayHold = sdf.format(sysDate);
            //获得第一个-的位置
            int index=lastDayHold.indexOf("-");
            //根据第一个点的位置 获得第二个-的位置
            int index1=lastDayHold.indexOf("-", index+1);
            //获取平台日的月份
            String month = lastDayHold.substring(index+1,index1);;
            param.put("month",month);
            this.dealMonth(param);
        }
        return chbConsignorDetailSub001Service.checkExport(param);
    }

    /**
     * 批处理导出
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "batchExport")
    public AjaxResult batchExport(@RequestParam Map<String, Object> param) {
        //处理月份
        if(StringUtil.isNotEmpty(param.get("month"))) {
            this.dealMonth(param);
        }else{
            //获取平台日
            Date sysDate = DateUtil.platDateFromCache();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String lastDayHold = sdf.format(sysDate);
            //获得第一个-的位置
            int index=lastDayHold.indexOf("-");
            //根据第一个点的位置 获得第二个-的位置
            int index1=lastDayHold.indexOf("-", index+1);
            //获取平台日的月份
            String month = lastDayHold.substring(index+1,index1);;
            param.put("month",month);
            this.dealMonth(param);
        }
        return chbConsignorDetailSub001Service.batchExport(param);
    }
}
