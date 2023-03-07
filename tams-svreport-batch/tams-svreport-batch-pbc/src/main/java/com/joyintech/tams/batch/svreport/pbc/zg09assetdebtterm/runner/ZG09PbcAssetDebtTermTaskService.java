package com.joyintech.tams.batch.svreport.pbc.zg09assetdebtterm.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.svreport.pbc.zg09assetdebtterm.service.ZG09PbcAssetDebtTermMainService;
import com.joyintech.tams.common.svreport.chb.util.ToolsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service(value = "ZG09PbcAssetDebtTermTaskService")
@Scope("prototype")
public class ZG09PbcAssetDebtTermTaskService extends BatchBaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ZG09PbcAssetDebtTermMainService zG09PbcAssetDebtTermMainService;
	
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
        logger.info("ZG09资产负债剩余期限信息:"+sdf.format(bdate));
        try {
            zG09PbcAssetDebtTermMainService.generateReport(ToolsUtil.getCurrentTime(batchExecuteInfo.getExecuteDate())            		);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
			logger.info("批处理异常" + e.getMessage(),e);
            logger.error("资产负债剩余期限信息批处理Error",e);
        }finally{
        	Date edate=new Date();
            Long et=edate.getTime();
            Long time=(et-bt)/1000;
        	logger.info("人行报表批处理结束:"+sdf.format(edate)+"耗时"+time+"秒");	
        }
        return result;
        
	}

}
