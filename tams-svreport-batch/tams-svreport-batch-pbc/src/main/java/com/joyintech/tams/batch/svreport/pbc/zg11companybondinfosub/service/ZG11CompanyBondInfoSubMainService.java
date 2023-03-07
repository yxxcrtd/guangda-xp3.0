package com.joyintech.tams.batch.svreport.pbc.zg11companybondinfosub.service;

import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.service.ZG11CompanyBondInfoSubBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZG11CompanyBondInfoSubMainService {

    @Autowired
    private ZG11CompanyBondInfoSubBatchService zg11CompanyBondInfoSubBatchService;

    public void generateReport(Date batchDate){
        zg11CompanyBondInfoSubBatchService.processData(batchDate);
    }
}
