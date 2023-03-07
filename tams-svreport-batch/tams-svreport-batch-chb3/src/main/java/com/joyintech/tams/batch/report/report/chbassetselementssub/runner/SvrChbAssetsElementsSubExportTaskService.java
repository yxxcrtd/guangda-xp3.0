package com.joyintech.tams.batch.report.report.chbassetselementssub.runner;


import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.tams.batch.report.report.chbassetselementssub.service.SvrChbAssetsElementsSubExportMainService;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 委托人明细数据表批导出
 * @author wyn
 * @date 2022/05/14
 */
@Service(value = "svrChbAssetsElementsSubExportTaskService")
@Scope("prototype")
public class SvrChbAssetsElementsSubExportTaskService extends BatchBaseService {

    @Autowired
    private SvrChbAssetsElementsSubExportMainService mainService;

    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        mainService.getExportData();
        result.setStatus(BatchStatus.COMPLETED);
        return result;

    }
}
