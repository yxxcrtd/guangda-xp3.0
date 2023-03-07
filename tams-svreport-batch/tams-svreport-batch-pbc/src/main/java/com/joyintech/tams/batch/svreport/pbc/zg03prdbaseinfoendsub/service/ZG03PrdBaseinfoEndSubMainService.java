package com.joyintech.tams.batch.svreport.pbc.zg03prdbaseinfoendsub.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.service.PbcPrdAccountSubService;
import com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.service.PbcPrdAccountEndSubService;
import com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service.PbcPrdAssetDebtSubService;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubService;
import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.service.PbcPrdCashRaiseSubService;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.serivce.PbcPrdInfoEndSubService;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.service.PbcPrdInitRaiseSubService;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.serivce.ZG03PrdBaseinfoEndSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class ZG03PrdBaseinfoEndSubMainService extends AutoSendCBBaseService{

    @Autowired
    private ZG03PrdBaseinfoEndSubService zg03PrdBaseinfoEndSubService;

    /**
     * 功能:资管产品终止信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2022年11月11日
     * @param batchDate 跑批日期
     * @param prodIdList 产品id
     * @throws Exception 
     */
    public void generateReport(final Date batchDate,final List<String> prodIdList) {
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.采集平台资管产品终止信息ZG03生成批处理);
        logInfoBatchStart();
        // 资管产品终止信息
        zg03PrdBaseinfoEndSubService.genReport(batchDate, prodIdList);

        logInfoBatchEnd();
    }
}
