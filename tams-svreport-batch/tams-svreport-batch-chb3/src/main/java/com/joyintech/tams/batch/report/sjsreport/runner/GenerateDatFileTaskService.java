package com.joyintech.tams.batch.report.sjsreport.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.batch.report.sjsreport.service.GenerateDatFileTaskMainService;
import com.joyintech.tams.batch.report.sjsreport.utils.CompressUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.*;

@Service(value = "generateDatFileTaskService")
@Scope("prototype")
public class GenerateDatFileTaskService extends BatchBaseService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenerateDatFileTaskMainService generateDatFileTaskMainService;

    @Override
    public JobExecution execute() throws BatchException {
        JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("无启动参数，批处理退出。");
            return result;
        }
        Date stime = new Date();
        logger.info("审计署报表批处理启动:"+ DateUtil.format(stime, "yyyy-MM-dd HH:mm:ss"));
        if(batchExecuteInfo.getExecuteDate() != null){
            stime = batchExecuteInfo.getExecuteDate();
        }

        String day = DateUtil.format(stime, "dd");
        Map<String,Object> params = new HashMap<>();
        // 前一天
        params.put("dateStatus", DateUtil.getYesterDayDate(stime));
        // 上月第一天
        params.put("dateStart", DateUtil.getFirstDayOfLayMonth(stime));
        // 上月最后一天
        params.put("dateEnd", DateUtil.getLastDayOfLayMonth(stime));

        try {
            if(day.equals("08")){ // 如果是每月的8号，则生成五个
                // 生成dat文件
                generateDatFileTaskMainService.genProductsSubListDAT(params, false);
                generateDatFileTaskMainService.genProductsDealSubDAT(params, false);
                generateDatFileTaskMainService.genProductsBalanceSubDAT(params, false);
                generateDatFileTaskMainService.genProductsAssetCompare(params, false);
                generateDatFileTaskMainService.genAssetsElementsSubDAT(params, false);

            }else{ // 如果不是8号则只只执行 ”理财产品交易明细表“
                // 生成dat文件
                generateDatFileTaskMainService.genProductsSubListDAT(params, true);
                generateDatFileTaskMainService.genProductsDealSubDAT(params, false);
                generateDatFileTaskMainService.genProductsBalanceSubDAT(params, true);
                generateDatFileTaskMainService.genProductsAssetCompare(params, true);
                generateDatFileTaskMainService.genAssetsElementsSubDAT(params, true);
            }

            // 打包tar文件
            CompressUtil.genTarFile(CompressUtil.getFileList(), CompressUtil.TARGET_PATH + CompressUtil.TAR_FILE_PREFIX + DateUtil.format(new Date(), "yyyyMMdd")+ ".tar");
            // 生成ok文件
            CompressUtil.genOkFile(CompressUtil.TARGET_PATH, CompressUtil.TAR_FILE_PREFIX + DateUtil.format(new Date(), "yyyyMMdd")+ ".ok");
            // 清理临时文件
            CompressUtil.rmFiles(CompressUtil.getFileList());

            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            result.setStatus(BatchStatus.FAILED);
            logger.error("审计署报表批处理Error",e);
        }finally{
            Date etime=new Date();
            Long time=(etime.getTime()-stime.getTime())/1000;
            logger.info("审计署报表批处理结束:"+DateUtil.format(stime, "yyyy-MM-dd HH:mm:ss")+" 耗时"+time+"秒");
        }

        return result;
    }


}
