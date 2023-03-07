package com.joyintech.tams.batch.svreport.pbc.zgdatfile.runner;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.batch.svreport.pbc.zgdatfile.service.SvrPbcZG03MainService;
import com.joyintech.tams.batch.svreport.pbc.zgdatfile.service.SvrPbcZGDatFileCommonSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 人行资管产品基本信息（ZG03）dat文件生成批处理
 * @author yanya
 * @version 1.0 2022-11-11
 * @since 1.0
 */
@Service(value = "svrPbcZG03DatFileTask")
@Scope("prototype")
public class SvrPbcZG03DatFileTask extends BatchBaseService {

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SvrPbcZG03MainService svrPbcZG03MainService;

    @Autowired
    private SvrPbcZGDatFileCommonSevice svrPbcZGDatFileCommonSevice;

	@Override
	public JobExecution execute() throws BatchException {
		JobExecution result = new JobExecution(1l);
        result.setStatus(BatchStatus.FAILED);
        SysBatchExecInfo batchExecuteInfo = this.getBatchExecuteInfo();
        if (batchExecuteInfo == null) {
            logger.info("人行资管产品基本信息（ZG03）dat文件生成批处理无启动参数，批处理退出。");
            return result;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bdate = new Date();
        Long bt = bdate.getTime();
        logger.info("人行资管产品基本信息（ZG03）dat文件生成批处理启动: " + sdf.format(bdate));
        try {
            SysBatchgrpExecInfo batchExecuteDetail = this.getBatchExecuteDetail();
            List<String> prodList = new ArrayList<String>();
            //svrPbcZGDatFileCommonSevice.dealProdList(prodList, batchExecuteDetail, this.getProductIdList());
            svrPbcZG03MainService.generatePbcDatFile(batchExecuteInfo.getExecuteDate(), prodList);
            result.setStatus(BatchStatus.COMPLETED);
        }
        catch (Exception e) {
            logger.error("人行资管产品基本信息（ZG03）dat文件生成批处理Error", e);
        }
        finally {
        	Date edate = new Date();
            Long et = edate.getTime();
            Long time = (et - bt) / 1000;
        	logger.info("人行资管产品基本信息（ZG03）dat文件生成批处理结束: " + sdf.format(edate) + "耗时 " + time + " 秒");
        }
        return result;
	}

}
