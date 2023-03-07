package com.joyintech.tams.batch.report.autosendcb.investhold.runner;

import com.joyintech.tams.batch.report.autosendcb.investhold.service.ChbInvestHoldTaskService;
import com.joyintech.tams.batch.report.autosendcb.investhold.service.ChbInvestHoldTmpMainService;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmaintenance.chbinvestholdinfo.dao.ChbInvestHoldInfoTmpMpper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;

import java.util.Date;

/**
 * 中债上游接口批处理
 *
 * @author wyn
 * @version 1.0 20220520
 * @see SvrChbInvestHoldInterfTastService
 * @since 1.0
 */
@Service(value = "svrChbInvestHoldInterfTastService")
@Scope("prototype")
public class SvrChbInvestHoldInterfTastService extends BatchBaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 接口轮询任务
     */
    @Autowired
    private ChbInvestHoldTaskService chbInvestHoldTaskService;

    @Autowired
    private ChbInvestHoldTmpMainService chbInvestHoldTmpMainService;

    @Autowired
    private ChbInvestHoldInfoTmpMpper chbInvestHoldInfoTmpMpper;

    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        try {
            //判断是否第一次接入
            String saveFlag = "true";
            int countTmp = chbInvestHoldInfoTmpMpper.saveFlag(DateUtil.format(batchExecuteInfo.getExecuteDate()));
            if(countTmp > 0){
                saveFlag = "false";
            }
            // 调用接口轮询任务逻辑处理反馈信息
            chbInvestHoldTaskService.execute(batchExecuteInfo.getExecuteDate(),saveFlag);
            logger.info("数据入tmp处理结束:"+new Date());
            //处理入库的tmp数据
            chbInvestHoldTmpMainService.generateInvestHoldTmp(batchExecuteInfo.getExecuteDate(),saveFlag);
            Date bdate=new Date();
            Long bt=bdate.getTime();
            Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
            logger.info("上游解析三期持有文件结束----------:"+DateUtil.format(batchExecuteInfo.getExecuteDate())+"耗时"+time+"秒");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }
}
