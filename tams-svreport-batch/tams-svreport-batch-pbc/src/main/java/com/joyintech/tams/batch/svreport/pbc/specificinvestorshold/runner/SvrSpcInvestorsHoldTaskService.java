package com.joyintech.tams.batch.svreport.pbc.specificinvestorshold.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.svreport.pbc.specificinvestorshold.service.SpcInvestorsHoldMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 特定目的载体持有信息(投资者) 生成批
 *
 * @author yangxianjiu
 * @date 2022/11/12
 */
@Service(value = "svrSpcInvestorsHoldTaskService")
@Scope("prototype")
public class SvrSpcInvestorsHoldTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SpcInvestorsHoldMainService mainService;

    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("特定目的载体持有信息(投资者) 生成批 批处理启动:"+sdf.format(bdate));
        try {
            mainService.generateInvestorsHoldData(batchExecuteInfo.getExecuteDate());
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.info("特定目的载体持有信息(投资者) 生成批 批处理Error",e);
        }finally{
            Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
            logger.info("特定目的载体持有信息(投资者) 生成批 批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");
        }
        return result;
    }
}
