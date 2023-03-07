package com.joyintech.tams.batch.svreport.pbc.zg06assetusufructsub.service;


import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.serivce.ZG06PbcColAssetUsufructSubBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZG06PbcColAssetUsufructMainService {

    @Autowired
    private ZG06PbcColAssetUsufructSubBatchService zg06PbcColAssetUsufructSubBatchService;

    public void generateReport(Date batchDate){
        zg06PbcColAssetUsufructSubBatchService.processData(batchDate);
    }
}
