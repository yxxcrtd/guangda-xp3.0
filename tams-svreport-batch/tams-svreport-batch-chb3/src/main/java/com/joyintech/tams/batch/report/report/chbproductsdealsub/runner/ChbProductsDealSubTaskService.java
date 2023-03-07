package com.joyintech.tams.batch.report.report.chbproductsdealsub.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.report.report.chbproductsdealsub.service.ChbProductsDealSubMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 理财产品交易明细登记表批处理
 * @author yangxianjiu
 * @date 2022/03/31
 */
@Service(value = "chbProductsDealSubTaskService")
@Scope("prototype")
public class ChbProductsDealSubTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChbProductsDealSubMainService chbProductsDealSubMainService;

    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }

        //
        Calendar calendar = Calendar.getInstance();
        // 获取跑批的执行日期
        calendar.setTime(batchExecuteInfo.getExecuteDate());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        String batchDate = sdf.format(batchExecuteInfo.getExecuteDate());
        //获得本月第一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = sdf.format(calendar.getTime());
        //获得本月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDay = sdf.format(calendar.getTime());
//
//        // 获取上月的第一天
//        calendar.add(Calendar.MONTH, -1);
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        Date firstday = calendar.getTime();
//        // 获取上月的最后一天
//        calendar.add(Calendar.MONTH, 1);
//        calendar.set(Calendar.DAY_OF_MONTH, 0);
//        Date lastday = calendar.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        // 上月第一天
//        String batchDateStart = DateUtil.format(firstday);
//        // 上月最后一天
//        String batchDateEnd = DateUtil.format(lastday);
//        Date bdate=new Date();
//        Long bt=bdate.getTime();
        logger.info("理财产品交易明细登记表批处理启动:"+sdf.format(bdate));
        try {
            chbProductsDealSubMainService.generateProductsBalanceSub(batchDate,firstDay,lastDay);
            result.setStatus(BatchStatus.COMPLETED);
        } catch (Exception e) {
            logger.error("理财产品交易明细登记表批处理Error",e);
        } finally {
            Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
            logger.info("理财产品交易明细登记表批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");
        }
        return result;
    }
}
