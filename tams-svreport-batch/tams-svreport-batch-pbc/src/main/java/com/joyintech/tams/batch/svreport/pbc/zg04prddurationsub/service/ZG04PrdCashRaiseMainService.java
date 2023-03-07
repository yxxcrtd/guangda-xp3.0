package com.joyintech.tams.batch.svreport.pbc.zg04prddurationsub.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class ZG04PrdCashRaiseMainService extends AutoSendCBBaseService {

    @Autowired
    private com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.service.ZG04PbcPrdCashRaiseSubService zg04PbcPrdCashRaiseSubService;

    /**
     * 功能:资管产品存续期募集信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：dingfeng 2022年11月14日
     * @param batchDate 跑批日期
     * @param prodIdList 产品id
     * @throws Exception
     */
    public void generateReport(final Date batchDate, final List<String> prodIdList) {
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.采集平台资管产品存续期募集信息ZG04生成批处理);
        logInfoBatchStart();
        // 资管产品存续期募集信息
        zg04PbcPrdCashRaiseSubService.genReport(batchDate, prodIdList);

        logInfoBatchEnd();
    }
}
