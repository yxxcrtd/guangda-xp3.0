package com.joyintech.tams.batch.svreport.pbc.zg08speccounterquity.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.service.ZG08SpecCounterQuityBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZG08SpeCounterQuityMainService extends AutoSendCBBaseService {

    @Autowired
    private ZG08SpecCounterQuityBatchService zg08SpecCounterQuityBatchService;

    public void generateReport(Date batchDate){
        zg08SpecCounterQuityBatchService.processData(batchDate);
    }
}
