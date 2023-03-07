package com.joyintech.tams.batch.svreport.pbc.zg05pbccolassetdebtreport.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.service.ZG05PbcColPrdAssetDebtBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZG05PbcColPrdAssetDebtMainService extends AutoSendCBBaseService {

    @Autowired
    private ZG05PbcColPrdAssetDebtBatchService zg05PbcColPrdAssetDebtBatchService;

    public void generateReport(Date batchDate){
        zg05PbcColPrdAssetDebtBatchService.processData(batchDate);
    }
}
