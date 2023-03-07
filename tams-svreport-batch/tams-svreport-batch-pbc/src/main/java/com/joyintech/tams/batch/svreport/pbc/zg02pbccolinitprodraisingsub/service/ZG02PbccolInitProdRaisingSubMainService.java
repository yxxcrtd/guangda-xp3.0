package com.joyintech.tams.batch.svreport.pbc.zg02pbccolinitprodraisingsub.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.serivce.ZG02PbccolInitProdRaisingSubBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZG02PbccolInitProdRaisingSubMainService extends AutoSendCBBaseService {

    @Autowired
    private ZG02PbccolInitProdRaisingSubBatchService zg02PbccolInitProdRaisingSubBatchService;

    public void generateReport(Date batchDate, List<String> prodIdList){
        zg02PbccolInitProdRaisingSubBatchService.processData(batchDate,prodIdList);
    }
}
