package com.joyintech.tams.batch.report.autosendcb.investhold.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.submitmaintenance.chbinvestholdinfo.service.SvrChbInvestHoldTmpDBInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service(value = "chbInvestHoldTmpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ChbInvestHoldTmpMainService extends AutoSendCBBaseService{
    
    @Autowired
    private SvrChbInvestHoldTmpDBInterf svrChbInvestHoldTmpDBInterf;
    
    /**
     * 功能:投资者持有处理已入tmp表的数据
     * @param batchDate 跑批日
     * void
     */
    public void generateInvestHoldTmp(Date batchDate,String saveFlag){
        svrChbInvestHoldTmpDBInterf.generateData(batchDate,saveFlag);
    }
}
