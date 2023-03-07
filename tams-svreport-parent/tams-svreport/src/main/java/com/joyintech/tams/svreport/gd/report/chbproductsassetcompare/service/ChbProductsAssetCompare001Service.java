package com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.batch.batchstart.service.SysBatchStart001Service;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.gd.report.chbproductsassetcompare.dto.ChbProductsAssetCompare001Dto;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.dao.ChbProductsAssetCompare001Mapper;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dao.ChbProductsDealSubMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChbProductsAssetCompare001Service extends CommonExportService<ChbProductsAssetCompare001Dto> {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired(required = false)
    private ChbProductsAssetCompare001Mapper chbProductsAssetCompare001Mapper;

    /**
     * 批启动
     */
    @Autowired
    private SysBatchStart001Service sysBatchStart001Service;

    public Page<ChbProductsAssetCompare001Dto> findPage(Map<String, Object> pageParam) {
        try{
            Integer page = Integer.parseInt(pageParam.get("page").toString());
            Integer length = Integer.parseInt(pageParam.get("length").toString());
            if (pageParam.containsKey("dateStatus")) {
                String dateStatus = pageParam.get("dateStatus").toString();
                Calendar calendar = Calendar.getInstance();
                /*calendar.setTime(DateUtil.parse(dateStatus));*/
                calendar.setTime(DateUtil.parse(dateStatus+"-01"));
                // 获取上月的最后一天
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                pageParam.put("dateStatus", DateUtil.format(calendar.getTime()));
            }
            return super.queryForPage(page, length, () -> chbProductsAssetCompare001Mapper.findPage(pageParam));
        }catch(Exception e) {
            logger.info("理财产品登记表查询错误", e);
        }
        return new Page<ChbProductsAssetCompare001Dto>();
    }

    public List<ChbProductsAssetCompare001Dto> queryAllList(Map<String, Object> params) {
        List<ChbProductsAssetCompare001Dto> list = chbProductsAssetCompare001Mapper.findPage(params);
        return list;
    }

    public List<ChbProductsAssetCompare001Dto> queryAllList1(Map<String, Object> params) {
        List<ChbProductsAssetCompare001Dto> list = chbProductsAssetCompare001Mapper.queryAllList1(params);
        return list;
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

        int dataNum = chbProductsAssetCompare001Mapper.countExportNum(param);
        // 是否需要批处理导出
        if (dataNum >= Integer.parseInt(batchExportNum)) {
            batchExport = true;
        }
        ajaxResult.setData(batchExport);
        return ajaxResult;
    }

    /**
     * 批导出启动
     * @param param
     * @return
     */
    public AjaxResult batchExport(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        // 是否未执行或正在执行中
        int count = chbProductsAssetCompare001Mapper.getNoendCountByTaskBeanName(BatchBeanName.理财产品资产对照表批导出.getTaskId());
        if (count > 0) {
            ajaxResult.setMessage("理财产品资产对照表批导出批处理正在执行中或待执行！", MessageType.错误消息);
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
        log.setTaskId(BatchBeanName.理财产品资产对照表批导出.getTaskId());
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
        log.setFileType(DictCodeListExt3.报送文件类别_理财产品资产对照.getValue());
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

}
