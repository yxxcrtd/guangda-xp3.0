package com.joyintech.tams.batch.report.report.chbconsignordetailsub.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.service.SvrChbConsignorDetailSubInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangxianjiu
 * @date 2022/03/31
 */
@Service(value = "chbConsignorDetailSubMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ChbConsignorDetailSubMainService extends AutoSendCBBaseService {

    @Autowired
    private SvrChbConsignorDetailSubInterf svrChbConsignorDetailSubInterf;

    /**
     *
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     */
    public void generateConsignorDetailSub(String batchDate, String batchDateStart, String batchDateEnd) {
        svrChbConsignorDetailSubInterf.generateData(batchDate,batchDateStart,batchDateEnd);
    }
}
