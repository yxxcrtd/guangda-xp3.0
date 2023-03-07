package com.joyintech.tams.batch.report.report.chbproductsassetcompare.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.report.chbproductsassetcompare.dao.SvrChbProductsAssetCompareExportTaskMapper;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 委托人明细数据表批导出
 * @author yangxianjiu
 * @date 2022/04/14
 */
@Service
public class SvrChbProductsAssetCompareExportMainService extends AutoSendCBBaseService {

    @Autowired
    private SvrChbProductsAssetCompareExportTaskMapper svrChbProductsAssetCompareExportTaskMapper;

    @Autowired
    private SvrChbProductsAssetCompareExportCommonService svrChbProductsAssetCompareExportCommonService;

    @Autowired
    private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;


    public void getExportData() {
        setBatchBeanName(BatchBeanName.理财产品资产对照表批导出);
        logInfoBatchStart();
        List<ChbSubinfoBatchLog> listBatchLog = svrChbProductsAssetCompareExportTaskMapper.getBatchLog(BatchBeanName.理财产品资产对照表批导出.getTaskId());
        // 获取平台日期
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysDate);
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        Date lastday = calendar.getTime();
        for (ChbSubinfoBatchLog batchLog : listBatchLog) {
            // 更新批处理状态 处理中
            batchLog.setDealStatus(DictCodeListExt3.报送处理状态_处理中.getValue());
            chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);
            // 转出参数
            Map param = (Map)JSON.parse(batchLog.getGenCondition());
            // 有导出日期
            if (StringUtil.isNotEmpty(param.get("dateStatus"))) {
                // 获取当前月的最后一天
                String dateStatus = param.get("dateStatus").toString();
                calendar.setTime(DateUtil.parse(dateStatus + "-01"));
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                param.put("dateStatus",DateUtil.format(calendar.getTime()));
            } else {
                // 未选择导出参数默认导出平台日上月最后一个自然日
                param.put("dateStatus",DateUtil.format(lastday));
            }

            long count = svrChbProductsAssetCompareExportTaskMapper.countExportNum(param);
            svrChbProductsAssetCompareExportCommonService.export(count,param,batchLog);
        }
        logInfoBatchEnd();
    }
}
