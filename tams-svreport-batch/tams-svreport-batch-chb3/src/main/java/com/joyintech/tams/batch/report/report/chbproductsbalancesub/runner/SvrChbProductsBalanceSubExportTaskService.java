package com.joyintech.tams.batch.report.report.chbproductsbalancesub.runner;


import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.report.chbproductsbalancesub.service.SvrChbProductsBalanceSubExportMainService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 理财产品账户余额表批导出
 * @author yangxianjiu
 * @date 2022/04/14
 */
@Service(value = "svrChbProductsBalanceSubExportTaskService")
@Scope("prototype")
public class SvrChbProductsBalanceSubExportTaskService extends BatchBaseService {

    @Autowired
    private SvrChbProductsBalanceSubExportMainService mainService;

    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        mainService.getExportData();
        result.setStatus(BatchStatus.COMPLETED);
        return result;

    }
}
