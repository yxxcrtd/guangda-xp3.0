package com.joyintech.tams.batch.report.report.chbproductsbalancesub.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.service.SvrProductsBalanceSubInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangxianjiu
 * @date 2022/03/31
 */
@Service(value = "chbProductsBalanceSubMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ChbProductsBalanceSubMainService extends AutoSendCBBaseService {

    @Autowired
    private SvrProductsBalanceSubInterf svrProductsBalanceSubInterf;

    /**
     *
     * @param batchDate
     * @param batchDateStart 跑批上个月第一个自然日
     * @param batchDateEnd 跑批上个月第最后一个自然日
     */
    public void generateProductsBalanceSub(String batchDate,String batchDateStart ,String batchDateEnd) {
        svrProductsBalanceSubInterf.generateData(batchDate,batchDateStart,batchDateEnd);
    }
}
