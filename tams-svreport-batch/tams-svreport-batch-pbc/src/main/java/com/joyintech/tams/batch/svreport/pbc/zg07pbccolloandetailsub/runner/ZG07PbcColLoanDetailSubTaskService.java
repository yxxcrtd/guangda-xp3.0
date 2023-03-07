package com.joyintech.tams.batch.svreport.pbc.zg07pbccolloandetailsub.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.svreport.pbc.zg07pbccolloandetailsub.service.ZG07PbcColLoanDetailSubMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * ZG07 除回购和拆解外贷款明细登记 批处理
 *
 * @author yangxianjiu
 * @date 2022/11/10
 */
@Service(value = "ZG07PbcColLoanDetailSubTaskService")
@Scope("prototype")
public class ZG07PbcColLoanDetailSubTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZG07PbcColLoanDetailSubMainService mainService;


    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1L);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        try{
            mainService.generateReport(batchExecuteInfo.getExecuteDate());
            result.setStatus(BatchStatus.COMPLETED);
        }catch (Exception e){
            logger.info("ZG07 除回购和拆解外贷款明细登记 批处理 执行失败");
            logger.info("批处理异常" + e.getMessage(),e);
            logger.error("除回购和拆解外贷款明细登记 批处理 执行失败Error",e);
        }
        return result;
    }
}
