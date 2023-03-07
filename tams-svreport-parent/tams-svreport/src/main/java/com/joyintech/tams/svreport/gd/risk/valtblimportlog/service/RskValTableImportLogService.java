package com.joyintech.tams.svreport.gd.risk.valtblimportlog.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.system.batch.batchstart.service.SysBatchStart001Service;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.gd.risk.valtblimport.dto.RskValtblAssetSumDto;
import com.joyintech.tams.svreport.gd.risk.valtblimportlog.dto.RskValTableImportLogDto;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class RskValTableImportLogService extends CommonExportService<RskValtblAssetSumDto>{

    private Logger logger = LoggerFactory.getLogger(RskValTableImportLogService.class);

    @Autowired
    private SysBatchStart001Service sysBatchStart001Service;

    public Page<RskValTableImportLogDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
                SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValTableImportLog/findPage",
                pageParam, RskValTableImportLogDto.class);
    }

    public ResponseEntity<byte[]> downLoadValTable(Map<String, Object> param)
            throws IOException {
        String datas = String.valueOf(param.get("dtoList"));
        List<RskValTableImportLogDto> dtoList = JsonUtils.json2List(datas, RskValTableImportLogDto.class);
        if (StringUtil.isEmptyList(dtoList)) {
            dtoList = restTemplate.postForList(
                    SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValTableImportLog/selectAllData",
                    param, RskValTableImportLogDto.class);;
        }
        if (StringUtil.isEmptyList(dtoList)){
            return null;
        }else {
            if (dtoList.size() == 1){
                restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValTableImportLog/updateBatch",dtoList, Integer.class);
                return ExportUtil.export(dtoList.get(0).getFileName(), new File(dtoList.get(0).getFilePath()), false);
            }else {
                String exportFilePath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
                String zipFileName ="估值表-" + FileNameUtil.createFileName("zip");
                File zipFile = new File(exportFilePath + File.separator + zipFileName);
                ZipOutputStream zipOut = null;
                try {
                    zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
                    // 设置UTF8编码
                    zipOut.setEncoding(StandardCharsets.UTF_8.displayName());
                    for (RskValTableImportLogDto dto : dtoList){
                        zipOut.putNextEntry(new ZipEntry(dto.getFileName()));

                        FileInputStream fos = null;
                        BufferedInputStream bis = null;
                        try {
                            fos = new FileInputStream(new File(dto.getFilePath()));
                            bis = new BufferedInputStream(fos);
                            int len;
                            byte[] buf = new byte[1024];
                            while ((len = bis.read(buf, 0, 1024)) != -1) {
                                zipOut.write(buf, 0, len);
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }finally {
                            if(bis!=null)
                            {
                                bis.close();

                            }
                            if(fos!=null)
                            {
                                fos.close();

                            }
                        }
                    }
                    zipOut.close();
                }catch (Exception e){
                   logger.info("压缩估值表文件失败!");
                }
                restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValTableImportLog/updateBatch",dtoList, Integer.class);
                return ExportUtil.export(zipFileName, zipFile, true);
            }

        }




    }

    /**
     * 启动估值自动导入批处理
     * @return
     */
    public AjaxResult runAutoImport() {
        AjaxResult ajaxResult = new AjaxResult();
        String taskId = RequestUtil.getParam("selectedBatchGroup");
        // 是否未执行或正在执行中
        if (!checkBatchClash(taskId)) {
            ajaxResult.setMessage(this.getMessage("RSK_G_Z_0072"), MessageType.错误消息);
            return ajaxResult;
        }
        sysBatchStart001Service.executeBatch();
        // 批处理启动成功，可在批处理监控页面查看进度
// 		return new AjaxResult("导出excel文件批处理启动成功，可在批处理监控页面查看进度");
        ajaxResult.setData(true);
        return ajaxResult;
    }

    /**
     * 验证批处理是否正在运行
     * @param beanName
     * @return
     */
    public boolean checkBatchClash(String taskId) {
        return restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/RskValTableImportLog/checkBatchClash", taskId, boolean.class);
    }
}
