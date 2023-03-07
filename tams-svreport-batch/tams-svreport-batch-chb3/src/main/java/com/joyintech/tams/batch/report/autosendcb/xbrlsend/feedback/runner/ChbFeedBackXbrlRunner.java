/*
 * 文件名：ChbFeedBackRunner.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年10月20日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.batch.report.autosendcb.xbrlsend.feedback.runner;

import cn.hutool.core.collection.CollectionUtil;
import com.joyintech.background.component.common.pack.common.util.JsonUtils;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.batch.batchbase.baseclass.BatchBaseService;
import com.joyintech.batch.batchbase.exception.BatchException;
import com.joyintech.component.svrreport.chb.contant.ChbConstant;
import com.joyintech.component.svrreport.chb.dto.AsynReport40Entity;
import com.joyintech.component.svrreport.chb.dto.AsynReportEntity;
import com.joyintech.component.svrreport.chb.dto.ChbInfoReportDto;
import com.joyintech.component.svrreport.chb.feedbackmsg.dto.ChbFeedbackFile;
import com.joyintech.component.svrreport.chb.feedbackmsg.service.ChbFeedbackMsgService;
import com.joyintech.component.svrreport.chb.msgsend.dto.ExtFprDClient;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.tams.report.xbrlsend.feedback.service.ChbFeedBackXbrlMainService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2020年10月20日
 * @see ChbFeedBackXbrlRunner
 * @since 1.0
 */
@Service(value = "ChbFeedBackXbrlRunner")
@Scope("prototype")
public class ChbFeedBackXbrlRunner extends BatchBaseService {

    @Autowired
    private ChbFeedBackXbrlMainService chbFeedBackXbrlMainService;



    private static final Logger logger = LoggerFactory.getLogger(ChbFeedBackXbrlRunner.class);

    @Override
    public JobExecution execute() throws BatchException {

        JobExecution result = new JobExecution(1l);
        // 调用组件处理反馈信息
        try {

            logger.debug("开始处理读取中债报送反馈结果文件");
            chbFeedBackXbrlMainService.getMessageSigle();
            logger.debug("开始处理读取中债报送反馈结果文件");
        } catch (Exception e) {
            logger.error("反馈文件获取异常",e);
            result.setStatus(BatchStatus.FAILED);
        }

        result.setStatus(BatchStatus.COMPLETED);
        return result;
    }








}
