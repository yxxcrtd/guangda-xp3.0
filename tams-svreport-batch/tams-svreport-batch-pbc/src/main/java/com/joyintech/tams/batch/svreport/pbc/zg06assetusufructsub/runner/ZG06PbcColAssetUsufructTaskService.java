package com.joyintech.tams.batch.svreport.pbc.zg06assetusufructsub.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.svreport.pbc.zg06assetusufructsub.service.ZG06PbcColAssetUsufructMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;

@Service(value = "ZG06PbcColAssetUsufructTaskService")
@Scope("prototype")
public class ZG06PbcColAssetUsufructTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZG06PbcColAssetUsufructMainService zg06PbcColAssetUsufructMainService;

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
            zg06PbcColAssetUsufructMainService.generateReport(batchExecuteInfo.getExecuteDate());
            result.setStatus(BatchStatus.COMPLETED);
        }catch (Exception e){
            logger.error("ZG08 特定目的载体交易对手明细信息生成批处理执行失败");
            logger.info("批处理异常" + e.getMessage(),e);
            logger.error("特定目的载体交易对手明细信息生成批处理执行失败Error",e);
        }
        return result;
    }
}
