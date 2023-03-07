package com.joyintech.tams.batch.report.report.chbconsignordetailsub.service;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.batch.report.report.chbconsignordetailsub.dao.SvrChbConsignorDetailSubExportTaskMapper;
import com.joyintech.tams.batch.report.report.chbproductsbalancesub.dao.SvrChbProductsBalanceSubExportTaskMapper;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.gd.report.chbconsignordetailsub.dto.ChbConsignorDetailSub001Dto;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 理财产品账户余额表批导出
 * @author wyn
 * @date 2022/05/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrChbConsignorDetailSubExportCommonService extends CommonExportService<ChbConsignorDetailSub001Dto> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SvrChbConsignorDetailSubExportCommonService.class);

    @Autowired
    private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;

    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;

    @Autowired
    private SvrChbConsignorDetailSubExportTaskMapper svrChbConsignorDetailSubExportTaskMapper;

    private static final String CHB_CONSIGNOR_DETAIL_SUB_NAME = "委托人明细数据表数据" ;

    public String export(long count, Map<String,Object> param, ChbSubinfoBatchLog batchLog){
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
                        return exportByPage(newParam);
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
                CHB_CONSIGNOR_DETAIL_SUB_NAME + GlobalConstant.UNDER_LINE  + DateUtil.getDateTimestamp().replaceAll(":", "") + ".zip";
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
        long finalCount = svrChbConsignorDetailSubExportTaskMapper.countExportNum(param);
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

    private boolean exportByPage(Map<String,Object> param){
        logger.info("分线程读取数据开始"+DateUtil.getDateTimestamp());
        List<ChbConsignorDetailSub001Dto> listDto =  svrChbConsignorDetailSubExportTaskMapper.selectForPage(param);
        dataFormat(listDto);
        //dealBigDecmailData(listDto);
        logger.info("分线程导出开始"+DateUtil.getDateTimestamp());
        batchMakeFile(listDto);
        logger.info("分线程导出结束"+DateUtil.getDateTimestamp());
        return true;
    }

    private File batchMakeFile(List<ChbConsignorDetailSub001Dto> contents){
        if (null == contents) {
            contents = Collections.emptyList();
        }
        int count = StringUtil.integerValueOf(CacheUtil.getSysParam("export_excel_row_count"), 50000);
        File targetFile = null;
        List<File> fileList = new ArrayList(contents.size() / count + 1);

        int sum = 1;
        try {
            while(contents.size() > count) {
                List<ChbConsignorDetailSub001Dto> subList = contents.subList(0, count);
                contents = contents.subList(count, contents.size());
                fileList.add(this.makeSingleFile(subList,sum));
                sum++;
            }

            fileList.add(this.makeSingleFile(contents,sum));
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

    private File makeSingleFile(List<ChbConsignorDetailSub001Dto> contents, int sum) throws IOException {
        if(!CollectionUtils.isNotEmpty(contents)){
            return null;
        }
        // 导出Map
        Map<String,Object> data  = new HashMap<String,Object>();
        Date lastday  = contents.get(0).getLastHoldDay();
        data.put("dataList", contents);
        File depositfile = TisUtil.createFileForGD(data,"pbc/" +"ChbConsignorDetailSub001", CHB_CONSIGNOR_DETAIL_SUB_NAME
                + "_" +DateUtil.format(lastday, DateUtil.MONTH_DATE_FORMAT_CN) + "_" + sum + ".xls");
        return  depositfile;
    }

    //处理查询到的数据
    public List<ChbConsignorDetailSub001Dto> dataFormat(List<ChbConsignorDetailSub001Dto> dtoList) {
        if(!CollectionUtils.isNotEmpty(dtoList)){
            return null;
        }
        dtoList.forEach(item -> {
            if(StringUtil.isNotEmpty(item.getProdType())){
                item.setProdType(item.getProdType() + " " + SysDataUtil.getNameFromDataDict(DictCode.产品类型, item.getProdType()));
            }
            if(StringUtil.isNotEmpty(item.getProdInvestType())){
                item.setProdInvestType(item.getProdInvestType() + " " + SysDataUtil.getNameFromDataDict(DictCode.投资性质, item.getProdInvestType()));
            }
            if(StringUtil.isNotEmpty(item.getRaiseType())){
                item.setRaiseType(item.getRaiseType() + " " + SysDataUtil.getNameFromDataDict(DictCode.募集方式_中债, item.getRaiseType()));
            }
            if(StringUtil.isNotEmpty(item.getHolderType())){
                item.setHolderType(item.getHolderType() + " " + SysDataUtil.getNameFromDataDict(DictCode.投资人类型, item.getHolderType()));
            }
        });
        return dtoList;
    }
}
