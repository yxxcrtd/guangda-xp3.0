package com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.batch.batchstart.service.SysBatchStart001Service;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.dao.ChbProductsBalanceSubMapper;
import com.joyintech.tams.report.gd.report.chbproductsbalancesub.dto.ChbProductsBalanceSub001Dto;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dao.ChbProductsDealSubMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 理财产品账户余额登记表
 * @author yangxianjiu
 * @date 2022/03/30
 */
@Service("ChbProductsBalanceSub001Service")
public class ChbProductsBalanceSub001Service extends CommonExportService<ChbProductsBalanceSub001Dto> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    //
    @Autowired
    private ChbProductsBalanceSubMapper chbProductsBalanceSubMapper;

    /**
     * 批启动
     */
    @Autowired
    private SysBatchStart001Service sysBatchStart001Service;

    public Page<ChbProductsBalanceSub001Dto> selectForPage(Map<String, Object> pageParam) {
        try {
            Integer page = Integer.parseInt(pageParam.get("page").toString());
            Integer length = Integer.parseInt(pageParam.get("length").toString());
            if (pageParam.containsKey("dateStatus")){
                String dateStatus = pageParam.get("dateStatus").toString();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(DateUtil.parse(dateStatus+"-01"));
                // 获取当前月的最后一天
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH,0);
                pageParam.put("dateStatus",DateUtil.format(calendar.getTime()));
            }
            Page<ChbProductsBalanceSub001Dto> dtoPage = super.queryForPage(page,length,() -> chbProductsBalanceSubMapper.selectForPage(pageParam));
            dtoPage.getRoot().forEach(dto -> {
                // 折人民币账户余额
                dto.setAmountCny(dto.getAmountCny() == null ? null : dto.getAmountCny().setScale(2, BigDecimal.ROUND_HALF_UP));
            });
            return dtoPage;
        } catch (Exception e){
            logger.info("理财产品账户余额登记表查询错误",e);
        }
        return new Page<ChbProductsBalanceSub001Dto>();
    }

    public List<ChbProductsBalanceSub001Dto> queryAllList(Map<String, Object> params) {
        List<ChbProductsBalanceSub001Dto> list = chbProductsBalanceSubMapper.selectForPage(params);
        list.forEach(dto -> {
            // 折人民币账户余额
            dto.setAmountCny(dto.getAmountCny().setScale(2, BigDecimal.ROUND_HALF_UP));
        });
        return list;
    }

    /**
     * 导出
     * @param params
     * @return
     * @throws IOException
     */
    public ResponseEntity<byte[]> download(Map<String, Object> params) throws IOException {
        // 导出Map
        Map<String,Object> data  = new HashMap<String,Object>();
        // File List
        List<File> sourceFiles = new ArrayList<>();
        List<ChbProductsBalanceSub001Dto> listdto = null ;
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
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
                listdto = chbProductsBalanceSubMapper.selectForPage(params);
                if (CollectionUtils.isNotEmpty(listdto)) {
                    lastday = listdto.get(0).getDateStatus();
                } else {
                    // 无数据
                    lastday = calendar.getTime();
                }
            } else {
                // 有选中行,有参数
                lastday = calendar.getTime();
                String keys = params.get("keys").toString();
                listdto = JsonUtils.json2List(keys, ChbProductsBalanceSub001Dto.class);;
            }
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            params.put("dateStatus", DateUtil.format(lastday));
            // 无选中行,无参数
            if (!params.containsKey("keys")) {
                listdto = chbProductsBalanceSubMapper.selectForPage(params);
            } else {
                // 有选中行，无参数
                String keys = params.get("keys").toString();
                listdto = JsonUtils.json2List(keys, ChbProductsBalanceSub001Dto.class);;
            }
        }
        // 统计日期
        for (ChbProductsBalanceSub001Dto dto : listdto) {
            dto.setDataDate(DateUtil.format(lastday, DateUtil.DEFAULT_DATE_FORMAT_EN));
        }
        if(CollectionUtils.isNotEmpty(listdto)) {
            data.put("dataList", listdto);
            List<ChbProductsBalanceSub001Dto> listdto1 = new ArrayList<ChbProductsBalanceSub001Dto>();
            listdto1.add(listdto.get(0));
            data.put("dataList1", listdto1);
        } else {
            ChbProductsBalanceSub001Dto chbProductsBalanceSub001Dto = new ChbProductsBalanceSub001Dto();
            chbProductsBalanceSub001Dto.setDataDate(DateUtil.format(lastday, DateUtil.DEFAULT_DATE_FORMAT_EN));
            // 无数据
            listdto.add(new ChbProductsBalanceSub001Dto());
            data.put("dataList", listdto);
            data.put("dataList1", listdto);
        }

        File depositfile = TisUtil.createFile(data,"pbc/" +"ChbProductsBalanceSub001_export", "理财产品账户余额表"
                + DateUtil.format(lastday, DateUtil.DEFAULT_OTHER_FORMAT)+ ".xls");
        sourceFiles.add(depositfile);
        File outFile = null;
        outFile = sourceFiles.get(0);
        return ExportUtil.export(outFile.getName()
                , outFile, true);
    }

    /**
     * 批导出启动
     * @param param
     * @return
     */
    public AjaxResult batchExport(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        // 是否未执行或正在执行中
        int count = chbProductsBalanceSubMapper.getNoendCountByTaskBeanName(BatchBeanName.理财产品账户余额表批导出.getTaskId());
        if (count > 0) {
            ajaxResult.setMessage("理财产品账户余额表批导出处理正在执行中或待执行！", MessageType.错误消息);
            ajaxResult.setData(false);
            return ajaxResult;
        }
        // 获取平台日期
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysDate);
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        Date lastday = calendar.getTime();

        ChbSubinfoBatchLog log = new ChbSubinfoBatchLog();
        log.setBatchExeId(IDGenerator.generate());
        log.setTaskId(BatchBeanName.理财产品账户余额表批导出.getTaskId());
        // 有导出日期
        if (StringUtil.isNotEmpty(param.get("dateStatus"))) {
            // 获取当前月的最后一天
            String dateStatus = param.get("dateStatus").toString();
            calendar.setTime(com.joyintech.framework.util.DateUtil.parse(dateStatus + "-01"));
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            log.setBusiConfirmDate(DateUtil.format(calendar.getTime()));
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            log.setBusiConfirmDate(DateUtil.format(lastday));
        }

        log.setOperator(LoginUserUtil.getUserName());
        log.setFileType(DictCodeListExt3.报送文件类别_理财产品账户余额.getValue());
        // 未处理
        log.setDealStatus(DictCodeListExt3.报送处理状态_未处理.getValue());
        log.setGenCondition(param.get("key")==null?"":param.get("key").toString());
        log.setCreateUser(LoginUserUtil.getUserName());
        log.setCreateTime(new Date());
        log.setUpdateUser(LoginUserUtil.getUserName());
        log.setUpdateTime(new Date());
        // 日志存表
        restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbSubinfoBatchLog/insert", log,
                ChbSubinfoBatchLog.class);
        // 启动批处理
        sysBatchStart001Service.executeBatch();
        // 批处理启动成功，可在批处理监控页面查看进度
        ajaxResult.setData(true);
        return ajaxResult;
    }

    /**
     * 统计数据行是否需要批导出
     * @param param
     * @return
     */
    public AjaxResult checkExport(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        boolean batchExport = false;
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

        String batchExportNum = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SUBINFO_BATCH_EXPORT_NUM);
        if (!StringUtil.isNotEmpty(batchExportNum)) {
            batchExportNum = "1000000"; // 默认为100w
        }

        int dataNum = chbProductsBalanceSubMapper.countExportNum(param);
        // 是否需要批处理导出
        if (dataNum >= Integer.parseInt(batchExportNum)) {
            batchExport = true;
        }
        ajaxResult.setData(batchExport);
        return ajaxResult;
    }
}
