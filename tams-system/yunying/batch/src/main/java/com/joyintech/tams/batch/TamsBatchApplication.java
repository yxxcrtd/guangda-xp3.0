package com.joyintech.tams.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.batch.config.BatchConfiguration;
import com.joyintech.framework.JoyinApplication;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.framework.config.WebApplication;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.tams.report.common.constant.StartMode;

/**
 * 批处理启动类
 * @author 王波
 *
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class })
@Import({ BatchConfiguration.class })
public class TamsBatchApplication  extends WebApplication {
	public static void main(String[] args) throws BatchException, IOException {
		JoyinApplication.run(TamsBatchApplication.class, args);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String beanName = br.readLine();
			runSingleBatch(beanName);
		}
	}

	/**
	 * @throws BatchException
	 *
	 */
	private static void runSingleBatch(String beanName) throws BatchException {
		SysBatchExecInfo batchExecuteInfo = genBatchExecuteInfo();
		BatchBaseService service = (BatchBaseService) SpringContextUtil.getBean(beanName);
		service.setBatchExecuteInfo(batchExecuteInfo);
		service.setBatchExecuteDetail(new SysBatchgrpExecInfo());
		service.setProductIdList(Arrays.asList("HB001"));
		service.execute();
	}

	/**
	 * @return
	 */
	private static SysBatchExecInfo genBatchExecuteInfo() {
		SysBatchExecInfo info = new SysBatchExecInfo();
		info.setSeqNo("1234567");
		info.setSystemCode("SVR");
		info.setTaskStatus("UNR");
		info.setBeanName("ZG10PbcColReportTaskService");//svrPbcZG02DatFileTask
		info.setExecuteDate(DateUtil.parse("2022-12-01"));
		info.setStartMode(StartMode.手动.getCode());
		return info;
	}
}
