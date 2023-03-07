package com.joyintech.tams.batch.report.report.chbproductsdealsub.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.report.chbproductsdealsub.dao.SvrChbProductsDealSubExportTaskMapper;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 理财产品交易明细表批导出
 * @author yangxianjiu
 * @date 2022/04/14
 */
@Service
public class SvrChbProductsDealSubExportMainService extends AutoSendCBBaseService {

    @Autowired
    private SvrChbProductsDealSubExportTaskMapper svrChbProductsDealSubExportTaskMapper;

    @Autowired
    private SvrChbProductsDealSubExportCommonService svrChbProductsDealSubExportCommonService;

    @Autowired
    private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;


    public void getExportData() {
        setBatchBeanName(BatchBeanName.理财产品交易明细表批导出);
        logInfoBatchStart();
        List<ChbSubinfoBatchLog> listBatchLog = svrChbProductsDealSubExportTaskMapper.getBatchLog(BatchBeanName.理财产品交易明细表批导出.getTaskId());
        for (ChbSubinfoBatchLog batchLog : listBatchLog) {
            // 更新批处理状态 处理中
            batchLog.setDealStatus(DictCodeListExt3.报送处理状态_处理中.getValue());
            chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);
            // 转出参数
            Map param = (Map)JSON.parse(batchLog.getGenCondition());
            String dateStatus = param.get("dateStatus").toString();
            param.put("dateStatus",dateStatus);

            long count = svrChbProductsDealSubExportTaskMapper.countExportNum(param);
            svrChbProductsDealSubExportCommonService.export(count,param,batchLog,dateStatus);
        }
        logInfoBatchEnd();
    }
}
