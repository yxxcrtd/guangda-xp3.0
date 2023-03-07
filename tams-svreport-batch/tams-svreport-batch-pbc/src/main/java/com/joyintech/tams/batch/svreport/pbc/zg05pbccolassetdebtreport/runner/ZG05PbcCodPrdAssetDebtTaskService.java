package com.joyintech.tams.batch.svreport.pbc.zg05pbccolassetdebtreport.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.svreport.pbc.zg05pbccolassetdebtreport.service.ZG05PbcColPrdAssetDebtMainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service(value = "ZG05PbcCodPrdAssetDebtTaskService")
@Scope("prototype")
public class ZG05PbcCodPrdAssetDebtTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZG05PbcColPrdAssetDebtMainService zg05PbcColPrdAssetDebtMainService;

    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1L);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate=new Date();
        Long bt=bdate.getTime();
        logger.info("ZG05 资管产品资产负债信息生成批处理启动:"+sdf.format(bdate));
        try{
            zg05PbcColPrdAssetDebtMainService.generateReport(batchExecuteInfo.getExecuteDate());
            result.setStatus(BatchStatus.COMPLETED);
        }catch (Exception e){
            logger.error("ZG05 资管产品资产负债信息生成批处理执行失败");
            logger.info("批处理异常" + e.getMessage(),e);
            logger.error("资管产品资产负债信息生成批处理执行失败Error",e);
        }finally{
            Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
            logger.info("ZG05 资管产品资产负债信息生成批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");
        }
        return result;
    }
}
