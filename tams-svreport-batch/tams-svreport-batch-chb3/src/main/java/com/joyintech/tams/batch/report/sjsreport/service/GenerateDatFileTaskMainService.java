package com.joyintech.tams.batch.report.sjsreport.service;

import com.joyintech.tams.batch.report.sjsreport.dao.GenerateDatFileTaskMapper;
import com.joyintech.tams.batch.report.sjsreport.utils.CompressUtil;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.report.gd.report.chbassetselementssub.dto.ChbAssetsElementsSub001Dto;
import com.joyintech.tams.report.gd.report.chbfinanceproductssub.dto.ChbFinanceProductsSub001Dto;
import com.joyintech.tams.report.gd.report.chbproductsassetcompare.dto.ChbProductsAssetCompare001Dto;
import com.joyintech.tams.report.gd.report.chbproductsbalancesub.dto.ChbProductsBalanceSub001Dto;
import com.joyintech.tams.report.gd.report.chbproductsdealsub.dto.ChbProductsDealSub001Dto;
import com.joyintech.tams.report.xbrlsend.base.dao.SftpFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class GenerateDatFileTaskMainService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GenerateDatFileTaskMapper generateDatFileTaskMapper;

    @Autowired
    private SftpFileUtils sftpFileUtils;

    /**
     * 理财产品登记列表查询
     * @param pageParam
     * @return
     */
    public String genProductsSubListDAT(Map<String, Object> pageParam, Boolean genEmptyFile){
        String targetFileName = CompressUtil.getTargetFileName(CompressUtil.CHB_FINANCE_PRODUCTS_SUB_PREFIX);
        List<ChbFinanceProductsSub001Dto> list = Collections.emptyList();
        if(!genEmptyFile){
            list = generateDatFileTaskMapper.selectProductsSubList(pageParam);
        }
        try {
            byte[] buff = CompressUtil.genDatFile(list);
            String gzFilePath = CompressUtil.doGzipFile(buff, targetFileName);
            SysUploadFileInfo file = new SysUploadFileInfo();
            file.setPath(gzFilePath);
            sftpFileUtils.upload(file,CompressUtil.TARGET_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return targetFileName;
    }

    /**
     * 理财产品交易明细列表查询
     * @param pageParam
     * @return
     */
    public String genProductsDealSubDAT(Map<String, Object> pageParam, Boolean genEmptyFile){
        String targetFileName = CompressUtil.getTargetFileName(CompressUtil.CHB_PRODUCTS_DEAL_SUB_PREFIX);
        List<ChbProductsDealSub001Dto> list = Collections.emptyList();
        if(!genEmptyFile){
            list = generateDatFileTaskMapper.selectProductsDealSubList(pageParam);
        }
        try {
            byte[] buff = CompressUtil.genDatFile(list);
            String gzFilePath = CompressUtil.doGzipFile(buff, targetFileName);
            SysUploadFileInfo file = new SysUploadFileInfo();
            file.setPath(gzFilePath);
            sftpFileUtils.upload(file,CompressUtil.TARGET_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return targetFileName;
    }

    /**
     * 理财产品账户余额列表查询
     * @param pageParam
     * @return
     */
    public String genProductsBalanceSubDAT(Map<String, Object> pageParam, Boolean genEmptyFile){
        String targetFileName = CompressUtil.getTargetFileName(CompressUtil.CHB_PRODUCTS_BALANCE_SUB_PREFIX);
        List<ChbProductsBalanceSub001Dto> list = Collections.emptyList();
        if(!genEmptyFile){
            list = generateDatFileTaskMapper.selectProductsBalanceSubList(pageParam);
        }
        try {
            byte[] buff = CompressUtil.genDatFile(list);
            String gzFilePath = CompressUtil.doGzipFile(buff, targetFileName);
            SysUploadFileInfo file = new SysUploadFileInfo();
            file.setPath(gzFilePath);
            sftpFileUtils.upload(file,CompressUtil.TARGET_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return targetFileName;
    }

    /**
     * 理财产品资产对照表
     * @param pageParam
     * @return
     */
    public String genProductsAssetCompare(Map<String, Object> pageParam, Boolean genEmptyFile){
        String targetFileName = CompressUtil.getTargetFileName(CompressUtil.CHB_PRODUCTS_ASSET_COMPARE_PREFIX);
        List<ChbProductsAssetCompare001Dto> list = Collections.emptyList();
        if(!genEmptyFile){
            list = generateDatFileTaskMapper.selectProductsAssetCompareList(pageParam);
        }
        try {
            byte[] buff = CompressUtil.genDatFile(list);
            String gzFilePath = CompressUtil.doGzipFile(buff, targetFileName);
            SysUploadFileInfo file = new SysUploadFileInfo();
            file.setPath(gzFilePath);
            sftpFileUtils.upload(file,CompressUtil.TARGET_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return targetFileName;
    }

    /**
     * 理财产品资产要素登记列表
     * @param pageParam
     * @return
     */
    public String genAssetsElementsSubDAT(Map<String, Object> pageParam, Boolean genEmptyFile){
        String targetFileName = CompressUtil.getTargetFileName(CompressUtil.CHB_ASSETS_ELEMENTS_SUB_PREFIX);
        List<ChbAssetsElementsSub001Dto> list = Collections.emptyList();
        if(!genEmptyFile){
            list = generateDatFileTaskMapper.selectAssetsElementsSubList(pageParam);
        }
        try {
            byte[] buff = CompressUtil.genDatFile(list);
            String gzFilePath = CompressUtil.doGzipFile(buff, targetFileName);
            SysUploadFileInfo file = new SysUploadFileInfo();
            file.setPath(gzFilePath);
            sftpFileUtils.upload(file,CompressUtil.TARGET_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return targetFileName;
    }
}
