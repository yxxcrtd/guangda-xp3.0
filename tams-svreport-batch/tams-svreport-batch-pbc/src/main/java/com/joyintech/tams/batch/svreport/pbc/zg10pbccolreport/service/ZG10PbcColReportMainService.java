package com.joyintech.tams.batch.svreport.pbc.zg10pbccolreport.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.service.ZG10PbcColReportBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZG10PbcColReportMainService extends AutoSendCBBaseService {

    @Autowired
    private ZG10PbcColReportBatchService zg10PbcColReportBatchService;

    public void generateReport(Date batchDate){
        zg10PbcColReportBatchService.processData(batchDate);
    }
}
