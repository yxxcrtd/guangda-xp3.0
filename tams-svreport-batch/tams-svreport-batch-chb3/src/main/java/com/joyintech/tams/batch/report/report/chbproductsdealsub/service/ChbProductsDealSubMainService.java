package com.joyintech.tams.batch.report.report.chbproductsdealsub.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.service.SvrProductsDealSubInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangxianjiu
 * @date 2022/04/01
 */
@Service(value = "chbProductsDealSubMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ChbProductsDealSubMainService extends AutoSendCBBaseService {

    @Autowired
    private SvrProductsDealSubInterf svrProductsDealSubInterf;

    public void generateProductsBalanceSub(String batchDate, String batchDateStart, String batchDateEnd) {
        svrProductsDealSubInterf.generateData(batchDate,batchDateStart,batchDateEnd);
    }
}
