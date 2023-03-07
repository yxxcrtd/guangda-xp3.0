package com.joyintech.tams.batch.report.handletype.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.report.handletype.service.EquityHoldInfoHandleTyplCBMainService;
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
 * 投资者类别日轮巡批处理
 *
 * @author zhengxiaolian
 * @version 3.0.6 2022年05月17日
 * @see
 * @since 1.0
 */
@Service(value = "equityHoldInfoHandleTypCBService")
@Scope("prototype")
public class EquityHoldInfoHandleTypCBService extends BatchBaseService{

	
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 投资者类别批处理 业务service
     */
    @Autowired
    private EquityHoldInfoHandleTyplCBMainService mainService;

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
        logger.info("投资者类别轮巡批处理批处理启动:"+sdf.format(bdate));
        try{
            mainService.autoManualCB(batchExecuteInfo.getExecuteDate());
        } catch (Exception e){
            logger.error("投资者类别轮巡批处理Error",e);
        }finally{
            Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
            logger.info("投资者类别轮巡批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");
        }

        result.setStatus(BatchStatus.COMPLETED);
        return result;
	}
}
