package com.joyintech.tams.batch.report.sjsreport.utils;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.report.gd.report.chbfinanceproductssub.dto.ChbFinanceProductsSub001Dto;
import com.joyintech.tams.report.xbrlsend.base.dao.GzFileUtils;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class CompressUtil {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(CompressUtil.class);

    /**
     * 目标文件夹
     */
    public static final String TARGET_PATH = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SJS_SEND_PATH);

    /**
     * 备份文件夹
     */
    public static final String BACKUP_PATH = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SJS_SEND_BACKUP_PATH);

    /**
     * 目标tar文件名前缀
     */
    public static final String TAR_FILE_PREFIX = "fad_data_ewurrp_";

    /**
     * 理财产品登记表dat文件名前缀
     */
    public static final String CHB_FINANCE_PRODUCTS_SUB_PREFIX = "a_ewurrp_chb_finance_products_sub_";

    /**
     * 理财产品交易明细表dat文件名前缀
     */
    public static final String CHB_PRODUCTS_DEAL_SUB_PREFIX = "i_ewurrp_chb_products_deal_sub_";

    /**
     * 理财产品账户余额表dat文件名前缀
     */
    public static final String CHB_PRODUCTS_BALANCE_SUB_PREFIX = "a_ewurrp_chb_products_balance_sub_";

    /**
     * 理财产品资产对照表dat文件名前缀
     */
    public static final String CHB_PRODUCTS_ASSET_COMPARE_PREFIX = "a_ewurrp_chb_products_asset_compare_";

    /**
     * 理财产品资产要素登记表dat文件名前缀
     */
    public static final String CHB_ASSETS_ELEMENTS_SUB_PREFIX = "a_ewurrp_chb_assets_elements_sub_";

    /**
     * dat文件后缀
     */
    public static final String DAT_Z_FILE_SUFFIX = "_000_000.dat.Z";

    /**
     * 检查目标文件夹是否存在，不存在则创建
     */
    static {
        List<File> dirList = Arrays.asList(new File(TARGET_PATH), new File(BACKUP_PATH));
        dirList.forEach(dir -> {
            if(!dir.exists()){
                dir.mkdirs();
            }
        });
    }

    /**
     * 生成dat文件字节流
     * @param list
     * @return
     */
    public static byte[] genDatFile(List<?> list){
        byte[] buff = null;
        // 没有数据直接返回
        if(list == null || list.isEmpty()){
            return null;
        }

        ByteArrayOutputStream byteos;
        PrintStream ps = null;
        try{
            byteos = new ByteArrayOutputStream();
            ps = new PrintStream(byteos);
            for(Object dto : list){
                String record = (String) dto.getClass().getMethod("buildReportStr").invoke(dto);
                ps.println(record);
            }
            ps.flush();
            buff = byteos.toByteArray();
        }catch (Exception e){
            logger.error("生成dat文件异常", e);
        }finally {
            if(ps != null){
                ps.close();
            }
        }
        return buff;
    }

    public static String getDatFormatStr(Object obj){
        StringBuffer sb = new StringBuffer();
        Field[] fields = obj.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                field.setAccessible(true);
                sb.append(field.get(obj));
                sb.append("|+|");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return sb.toString();
    }

    /**
     * 组装gz文件并且反馈文件路径
     *
     * @param buff  字节流
     * @param gzFilePath gz文件路径
     * @return
     * @throws IOException
     */
    public static String doGzipFile(byte[] buff, String gzFilePath) throws IOException {
        BufferedOutputStream bos = null;
        GZIPOutputStream gzos = null;
        FileOutputStream fos = null;

        if(buff == null){
            buff = "没有数据".getBytes(StandardCharsets.UTF_8);
        }

        ByteArrayInputStream byteis = new ByteArrayInputStream(buff);
        try {
            File txtGzFile = new File(gzFilePath);
            fos = FileUtils.openOutputStream(txtGzFile);
            bos = new BufferedOutputStream(fos);
            gzos = new GZIPOutputStream(bos);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = byteis.read(buffer)) != -1) {
                gzos.write(buffer, 0, len);
            }
            gzos.flush();
        } catch (Exception ex) {
            logger.error(ex.toString());
        } finally {
            gzos.close();
            byteis.close();
            bos.close();
            fos.close();
        }
        return gzFilePath;
    }

    /**
     * 打包tar文件
     *
     * @param filePaths 文件源路径
     * @param targetPath tar文件路径
     * @return
     */
    public static File genTarFile(List<String> filePaths, String targetPath) {
        File targetFile = new File(targetPath);

        FileOutputStream fos = null;
        TarArchiveOutputStream os = null;
        try {
            // 如果文件已经存在则先备份
            if(targetFile.exists()){
                backupFile(targetPath, CompressUtil.BACKUP_PATH);
            }

            fos = FileUtils.openOutputStream(targetFile);
            os = new TarArchiveOutputStream(fos);

            for (int i = 0; i < filePaths.size(); i++) {
                File file = new File(filePaths.get(i));
                FileInputStream fis = new FileInputStream(file);
                TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(file);
                tarArchiveEntry.setName(file.getName());
                os.putArchiveEntry(tarArchiveEntry);
                IOUtils.copy(fis, os);
                os.closeArchiveEntry();
                fis.close();
            }
        } catch (Exception e) {
            logger.error("打包异常", e);
        } finally {
            try{
                os.close();
                fos.close();

            }catch (Exception e){
                logger.error("关闭文件流异常", e);
            }
        }
        return targetFile;
    }

    /**
     * 备份文件
     * @param sourceFile
     * @param targetPath
     */
    public static void backupFile(String sourceFile, String targetPath){
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            String targetName = FilenameUtils.getName(sourceFile);
            inputStream = new FileInputStream(sourceFile);
            outputStream = FileUtils.openOutputStream(new File(targetPath + targetName));
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            logger.error("备份文件异常", e);
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                logger.error("关闭文件流异常", e);
            }
        }
    }

    /**
     * 生成OK文件
     * @param filePath
     * @param fileName
     */
    public static void genOkFile(String filePath, String fileName){
        try {
            File file = new File(filePath, fileName);
            FileUtils.touch(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除生成的临时文件
     * @param filePaths
     */
    public static void rmFiles(List<String> filePaths){
        filePaths.stream().forEach(item -> {
            try {
                Path path = Paths.get(item);
                boolean result = Files.deleteIfExists(path);
                if(!result){
                    logger.error("文件不存在：" + item);
                }
            } catch (IOException e) {
                logger.error("文件删除异常", e);
            }
        });
    }

    /**
     * 构建待打包文件列表
     * @return
     */
    public static List<String> getFileList(){
        List<String> list = new ArrayList<>();
        list.add(getTargetFileName(CHB_FINANCE_PRODUCTS_SUB_PREFIX));
        list.add(getTargetFileName(CHB_PRODUCTS_DEAL_SUB_PREFIX));
        list.add(getTargetFileName(CHB_PRODUCTS_BALANCE_SUB_PREFIX));
        list.add(getTargetFileName(CHB_PRODUCTS_ASSET_COMPARE_PREFIX));
        list.add(getTargetFileName(CHB_ASSETS_ELEMENTS_SUB_PREFIX));
        return list;
    }

    /**
     * 获取文件名字
     * @param typeName
     * @return
     */
    public static String getTargetFileName(String typeName){
        return CompressUtil.TARGET_PATH + typeName + DateUtil.format(new Date(), "yyyyMMdd") + DAT_Z_FILE_SUFFIX;
    }

    public static void main(String[] args) throws IOException {
        List<ChbFinanceProductsSub001Dto> list1 = new ArrayList<>();
        ChbFinanceProductsSub001Dto dto = new ChbFinanceProductsSub001Dto();
        dto.setOrgCode("aaaaaaa");
        dto.setBankMark("bbbbbbbbbb");
        dto.setContMode("kkkkkkkkkkkkk");
        list1.add(dto);
        ChbFinanceProductsSub001Dto dto2 = new ChbFinanceProductsSub001Dto();
        dto2.setOrgCode("cccccccccccccc");
        dto2.setBankMark("dddddddddddddddddd");
        dto2.setContMode("llllllllllllllll");
        list1.add(dto2);
        byte[] buff = CompressUtil.genDatFile(list1);

        CompressUtil.doGzipFile(buff, getTargetFileName(CHB_FINANCE_PRODUCTS_SUB_PREFIX));
        CompressUtil.doGzipFile(buff, getTargetFileName(CHB_PRODUCTS_DEAL_SUB_PREFIX));
        CompressUtil.doGzipFile(buff, getTargetFileName(CHB_PRODUCTS_BALANCE_SUB_PREFIX));
        CompressUtil.doGzipFile(buff, getTargetFileName(CHB_PRODUCTS_ASSET_COMPARE_PREFIX));
        CompressUtil.doGzipFile(buff, getTargetFileName(CHB_ASSETS_ELEMENTS_SUB_PREFIX));

        CompressUtil.genTarFile(getFileList(), CompressUtil.TARGET_PATH + TAR_FILE_PREFIX + DateUtil.format(new Date(), "yyyyMMdd")+ ".tar");
        CompressUtil.genOkFile(CompressUtil.TARGET_PATH, TAR_FILE_PREFIX + DateUtil.format(new Date(), "yyyyMMdd")+ ".ok");

        CompressUtil.rmFiles(getFileList());
    }
}
