package com.joyintech.tams.batch.report.report.chbfinanceproductssub.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.service.ChbFinanceProductsSubInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "chbFinanceProductsSubMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ChbFinanceProductsSubMainService extends AutoSendCBBaseService{
    
    @Autowired
    private ChbFinanceProductsSubInterf chbFinanceProductsSubInterf;
    
    /**
     * 功能:每日批
     * @param batchDateStart 跑批日当月第一天
     * @param batchDateEnd 跑批日当月最后一天
     * void
     */
    public void generateSalesTransactionSubInfo(String batchDate,String batchDateStart ,String batchDateEnd){
        chbFinanceProductsSubInterf.generateData(batchDate,batchDateStart,batchDateEnd);
    }
}
