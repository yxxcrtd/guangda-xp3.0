package com.joyintech.tams.batch.report.report.chbfinanceproductssub.service;

import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.batch.report.report.chbfinanceproductssub.dao.SvrChbFinanceProductsSubExportTaskMapper;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.framework.thread.JoyinThreadPoolExecutor;
import com.joyintech.tams.report.gd.report.chbfinanceproductssub.dto.ChbFinanceProductsSub001Dto;
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
import java.math.BigDecimal;
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
public class SvrChbFinanceProductsSubExportCommonService extends CommonExportService<ChbFinanceProductsSub001Dto> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(SvrChbFinanceProductsSubExportCommonService.class);

    @Autowired
    private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;

    @Autowired
    private FileAttachmentProcess fileAttachmentProcess;

    @Autowired
    private SvrChbFinanceProductsSubExportTaskMapper svrChbFinanceProductsSubExportTaskMapper;

    private static final String CHB_FINANCE_PRODUCTS_SUB_NAME = "理财产品登记表数据" ;

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
                CHB_FINANCE_PRODUCTS_SUB_NAME + GlobalConstant.UNDER_LINE  + DateUtil.getDateTimestamp().replaceAll(":", "") + ".zip";
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
        long finalCount = svrChbFinanceProductsSubExportTaskMapper.countExportNum(param);
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
        List<ChbFinanceProductsSub001Dto> listDto =  svrChbFinanceProductsSubExportTaskMapper.selectForPage(param);
        dealDataList(listDto);
        logger.info("分线程导出开始"+DateUtil.getDateTimestamp());
        batchMakeFile(listDto);
        logger.info("分线程导出结束"+DateUtil.getDateTimestamp());
        return true;
    }

    private File batchMakeFile(List<ChbFinanceProductsSub001Dto> contents){
        if (null == contents) {
            contents = Collections.emptyList();
        }
        int count = StringUtil.integerValueOf(CacheUtil.getSysParam("export_excel_row_count"), 50000);
        File targetFile = null;
        List<File> fileList = new ArrayList(contents.size() / count + 1);

        int sum = 1;
        try {
            while(contents.size() > count) {
                List<ChbFinanceProductsSub001Dto> subList = contents.subList(0, count);
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

    private File makeSingleFile(List<ChbFinanceProductsSub001Dto> contents, int sum) throws IOException {
        if(!CollectionUtils.isNotEmpty(contents)){
            return null;
        }
        // 导出Map
        Map<String,Object> data  = new HashMap<String,Object>();
        Date lastday  = new Date();
        List<ChbFinanceProductsSub001Dto> tempList = new ArrayList<>();
        data.put("dataList", contents);
        tempList.add(contents.get(0));
        data.put("dataList1",tempList);
        data.put("dataList2",tempList);
        File depositfile = TisUtil.createFileForGD(data,"pbc/" +"ChbFinanceProductsSub_export", CHB_FINANCE_PRODUCTS_SUB_NAME
                + DateUtil.format(lastday, DateUtil.DEFAULT_OTHER_FORMAT) + "_" + sum + ".xls");
        return  depositfile;
    }

    public List<ChbFinanceProductsSub001Dto> dealDataList(List<ChbFinanceProductsSub001Dto> dataList) {
        dataList.forEach(item->{
            if(StringUtil.isNotEmpty(item.getContMode())){
                item.setContMode(item.getContMode()+" "+ SysDataUtil.getNameFromDataDict(DictCode.合作模式, item.getContMode()));
            }
            if(StringUtil.isNotEmpty(item.getProfitType())){
                item.setProfitType(item.getProfitType()+" "+ SysDataUtil.getNameFromDataDict(DictCode.产品收益标识, item.getProfitType()));
            }
            if(StringUtil.isNotEmpty(item.getOperateMode())){
                item.setOperateMode(item.getOperateMode()+" "+ SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, item.getOperateMode()));
            }
            if(StringUtil.isNotEmpty(item.getCmmode())){
                item.setCmmode(item.getCmmode()+" "+ SysDataUtil.getNameFromDataDict(DictCode.产品投资模式, item.getCmmode()));
            }
            if(StringUtil.isNotEmpty(item.getInvAssetType())){
                item.setInvAssetType(item.getInvAssetType()+" "+ SysDataUtil.getNameFromDataDict(DictCode.投资类型信息, item.getInvAssetType()));
            }
            if(StringUtil.isNotEmpty(item.getCustomerType())){
                item.setCustomerType(item.getCustomerType()+" "+ SysDataUtil.getNameFromDataDict(DictCode.专属客户类型, item.getCustomerType()));
            }
            if(StringUtil.isNotEmpty(item.getInvestArae())){
                item.setInvestArae(item.getInvestArae()+" "+ SysDataUtil.getNameFromDataDict(DictCode.资金投向地区, item.getInvestArae()));
            }
            if(StringUtil.isNotEmpty(item.getIsConstracte())){
                item.setIsConstracte(item.getIsConstracte()+" "+ SysDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getIsConstracte()));
            }
        });
        dataList.forEach(dto->{
            if(dto.getRaiseAmtPlan()!=null){
                dto.setRaiseAmtPlan(dto.getRaiseAmtPlan().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(dto.getTotalAmt()!=null){
                dto.setTotalAmt(dto.getTotalAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(dto.getSaleAmtStr()!=null){
                dto.setSaleAmtStr(dto.getSaleAmtStr().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        });
        dataList.forEach(dto -> {
            dto.setRaiseVdateStr(dto.getRaiseVdateStr());
            dto.setRaiseEnddateStr(dto.getRaiseEnddateStr());
            dto.setVdateStr(dto.getVdateStr());
            dto.setMdateStr(dto.getMdateStr());
            dto.setDateStatusStr(dto.getDateStatusStr());
            dto.setContModeStr( dto.getContModeStr());
            dto.setProfitTypeStr(dto.getProfitTypeStr());
            dto.setOperateModeStr(dto.getOperateModeStr());
            dto.setCmmodeStr( dto.getCmmodeStr());
            dto.setInvAssetTypeStr( dto.getInvAssetTypeStr());
            dto.setCustomerTypeStr( dto.getCustomerTypeStr());
            dto.setInvestAraeStr(dto.getInvestAraeStr());
        });
        return dataList;
    }
}
