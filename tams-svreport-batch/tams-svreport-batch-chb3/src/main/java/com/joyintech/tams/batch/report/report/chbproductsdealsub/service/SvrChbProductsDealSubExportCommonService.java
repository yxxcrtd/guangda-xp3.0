package com.joyintech.tams.batch.report.report.chbproductsdealsub.service;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.report.report.chbproductsdealsub.dao.SvrChbProductsDealSubExportTaskMapper;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.gd.report.chbproductsdealsub.dto.ChbProductsDealSub001Dto;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 理财产品交易明细表批导出
 * @author yangxianjiu
 * @date 2022/04/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrChbProductsDealSubExportCommonService extends CommonExportService<ChbProductsDealSub001Dto> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SvrChbProductsDealSubExportCommonService.class);

    @Autowired
    private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;

    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;

    @Autowired
    private SvrChbProductsDealSubExportTaskMapper svrChbProductsDealSubExportTaskMapper;

    private static final String CHB_PRODUCTS_DEAL_SUB_NAME = "理财交易明细数据" ;

    public String export(long count, Map<String,Object> param, ChbSubinfoBatchLog batchLog,String dateStatus){
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        int size = StringUtil.integerValueOf(CacheUtil.getSysParam("export_excel_row_count"), 50000);
        long fileCount = (long)Math.ceil((double)count/size);
        int maxCorePoolSize = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_MAX_CORE_POOL_SIZE)?"5":SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_MAX_CORE_POOL_SIZE));
        ExecutorService service = new JoyinThreadPoolExecutor(maxCorePoolSize);
        List<Callable<Boolean>> tasks = new ArrayList<>();
        boolean success = true;
        if(count>0){
            long start = 0;
            long end = 0;
            for (int i = 0; i < fileCount; i++) {
                start = end;
                end = Math.min(count,size * (i + 1));
                int index = i+1;
                Map<String,Object> newParam = new HashMap<>();
                param.put("start",start);
                param.put("end",end);
                param.put("index", index);
                newParam.putAll(param);
                tasks.add(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return exportByPage(newParam,dateStatus);
                    }
                });
            }
        }
        // 为线程池添加任务
        List<Future<Boolean>> taskResult = null;
        try {
            taskResult = service.invokeAll(tasks);
        }
        catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
            // 异常结束
            batchLog.setDealStatus(DictCodeListExt3.报送处理状态_异常结束.getValue());
            batchLog.setDealMessage("导出发生异常,请联系管理员!");
            chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);
            return "fail";
        }
        for (Future<Boolean> res : taskResult) {
            try {
                success = success && res.get();
            }
            catch (InterruptedException | ExecutionException e) {
                logger.error(e.getMessage(), e);
                // 异常结束
                batchLog.setDealStatus("02");
                batchLog.setDealMessage(DictCodeListExt3.报送处理状态_异常结束.getValue());
                chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);
                return "fail";
            }
        }

        // 任务结束，停止线程池的所有线程
        service.shutdown();


        File tmpPath = new File(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_TMP_PATH));
        File[] files = tmpPath.listFiles();

        String filePath = CacheUtil.getSysParam("access_exportfilepath") +
                CHB_PRODUCTS_DEAL_SUB_NAME + GlobalConstant.UNDER_LINE  + DateUtil.getDateTimestamp().replaceAll(":", "") + ".zip";
        File outFile = new File(filePath);
        try {
            ZipFileUtil.compressZip(outFile,true,files);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }

        String fileName = outFile.getName();
        String seqNo = IDGenerator.generate();
        UploadFile uploadFile = new UploadFile(fileName,seqNo,outFile);

        if(null != fileAttachmentProcess) {
            fileAttachmentProcess.upload(uploadFile);
        }

        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        long cosumeTime = endTime.getTime() - startTime.getTime();
        batchLog.setCompresFileId(seqNo);
        batchLog.setCompresFileName(fileName);
        batchLog.setCompresFilePath(filePath);
        batchLog.setFileCount(fileCount);
        batchLog.setRecordCount(count);
        batchLog.setConsumeTime(cosumeTime);
        batchLog.setStartTime(startTime);
        batchLog.setEndTime(endTime);
        batchLog.setUpdateUser(LoginUserUtil.getUserName());
        batchLog.setUpdateTime(endTime);
        long finalCount = svrChbProductsDealSubExportTaskMapper.countExportNum(param);
        if(count == finalCount){
            // 正常结束
            batchLog.setDealStatus(DictCodeListExt3.报送处理状态_正常结束.getValue());
            batchLog.setDealMessage("成功生成文件!");
        }else {
            // 异常结束
            batchLog.setDealStatus(DictCodeListExt3.报送处理状态_警告.getValue());
            batchLog.setDealMessage("总记录数不符合或者其他异常,请联系管理员!");
        }
        chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);

        return "success";
    }

    private boolean exportByPage(Map<String,Object> param,String dateStatus){
        logger.info("分线程读取数据开始"+DateUtil.getDateTimestamp());
        List<ChbProductsDealSub001Dto> listDto =  svrChbProductsDealSubExportTaskMapper.selectForPage(param);
        //dealDataList(listDto);
        //dealBigDecmailData(listDto);
        logger.info("分线程导出开始"+DateUtil.getDateTimestamp());
        batchMakeFile(listDto,dateStatus);
        logger.info("分线程导出结束"+DateUtil.getDateTimestamp());
        return true;
    }

    private File batchMakeFile(List<ChbProductsDealSub001Dto> contents,String dateStatus){
        if (null == contents) {
            contents = Collections.emptyList();
        }
        int count = StringUtil.integerValueOf(CacheUtil.getSysParam("export_excel_row_count"), 50000);
        File targetFile = null;
        List<File> fileList = new ArrayList(contents.size() / count + 1);

        int sum = 1;
        try {
            while(contents.size() > count) {
                List<ChbProductsDealSub001Dto> subList = contents.subList(0, count);
                contents = contents.subList(count, contents.size());
                fileList.add(this.makeSingleFile(subList,sum,dateStatus));
                sum++;
            }

            fileList.add(this.makeSingleFile(contents,sum,dateStatus));
        } catch (Exception var24) {
            logger.error(var24.getMessage(),var24);
        }

        if (fileList.size() == 1) {
            targetFile = (File)fileList.get(0);
        } else {
            String exportFilePath = CacheUtil.getSysParam("access_exportfilepath");
            String zipFileName = FileNameUtil.createFileName("zip");
            targetFile = new File(exportFilePath + File.separator + zipFileName);

            try {
                ZipFileUtil.compressZip(targetFile, true, (File[])fileList.toArray(new File[0]));
            } catch (Exception var23) {
                Iterator var12 = fileList.iterator();

                while(var12.hasNext()) {
                    File file = (File)var12.next();
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        }

        return targetFile;
    }

    private File makeSingleFile(List<ChbProductsDealSub001Dto> contents, int sum,String dateStatus) throws IOException {
        if(!CollectionUtils.isNotEmpty(contents)){
            return null;
        }
        // 导出Map
        Map<String,Object> data  = new HashMap<String,Object>();
        Date lastday = contents.get(0).getDateStatus();
        // 统计日期
        for (ChbProductsDealSub001Dto dto : contents) {
            dto.setDataDate(DateUtil.format(lastday, DateUtil.DEFAULT_DATE_FORMAT_EN));
            // 交易日期
            dto.setEquityConfDateStr(dto.getEquityConfDateStr());
            // 理财产品变更申请类型代码信息
            dto.setBusiTypeStr(dto.getBusiTypeStr());
            // 交易渠道类型代码信息
            dto.setChannelStr(dto.getChannelStr());
            // 数据时点
            dto.setDateStatusStr(dto.getDateStatusStr());
        }
        List<ChbProductsDealSub001Dto> tempList = new ArrayList<>();
        ChbProductsDealSub001Dto chbProductsDealSub001Dto = new ChbProductsDealSub001Dto();
            data.put("dataList", contents);
            tempList.add(contents.get(0));
            data.put("dataList1", tempList);
        File depositfile = TisUtil.createFileForGD(data,"pbc/" +"ChbProductsDealSub001_export", "理财产品交易明细表"
                + dateStatus + "_" + sum + ".xls");
        return  depositfile;
    }



}
