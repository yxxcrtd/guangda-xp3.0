package com.joyintech.tams.batch.svreport.pbc.zg07pbccolloandetailsub.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.service.ZG07PbcColLoanDetailSubBatchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * ZG07 除回购和拆解外贷款明细登记
 *
 * @author yangxianjiu
 * @date 2022/11/10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ZG07PbcColLoanDetailSubMainService extends AutoSendCBBaseService {


    @Resource
    private ZG07PbcColLoanDetailSubBatchService pbcColLoanDetailSubBatchService;

    /**
     * 生成
     * @param executeDate
     */
    public void generateReport(Date executeDate) {
        pbcColLoanDetailSubBatchService.generateData(executeDate);
    }
}
