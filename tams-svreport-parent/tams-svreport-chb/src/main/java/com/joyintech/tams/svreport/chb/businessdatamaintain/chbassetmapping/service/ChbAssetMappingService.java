/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.service;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.dao.ChbAssetMappingMapper;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.dto.ChbAssetMappingDto;
import com.joyintech.tams.svreport.chb.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 网点信息映射表管理
 *
 * @author zhoubin
 * @version 1.0 2022年05月16日
 * @see ChbAssetMappingService
 * @since 1.0
 */

@Service
public class ChbAssetMappingService extends CommonExportService<ChbAssetMappingDto> {

    private final Logger logger = LoggerFactory.getLogger(ChbAssetMappingService.class);

    public static final String CONFIG_FILE = "/com/joyintech/tams/svreport/chb/businessdatamaintain/chbassetmapping/conf/export_conf.xml";


    /**
     * 删除
     */
    public static final String DELETE = "DELETE";
    /**
     * 新建
     */
    public static final String NEW = "NEW";

    /**
     * 分页大小
     */
    public static final int PAGE_SIZE = 200;


    @Resource
    private ChbAssetMappingMapper chbAssetMappingMapper;


    /**
     * 网点信息映射表管理分页查询
     *
     * @param param 查询参数
     * @return 返回查询结果
     */
    public Page<ChbAssetMappingDto> selectForPage(Map<String, Object> param) {
        param.put("svrAssetCodes",StringUtil.splitToList(GlobalConstant.COMMA, null!=param.get("svrAssetCode")?param.get("svrAssetCode").toString():""));


        return super.queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), () -> chbAssetMappingMapper.select(param));
    }

    /**
     * 查询所有数据验证删除是否存在
     *
     * @param pageParam 查询参数
     * @return 查询结果
     */
    public List<ChbAssetMappingDto> selectAllList(Map<String, Object> pageParam) {
        return chbAssetMappingMapper.select(pageParam);
    }


    /**
     * 导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
        exportParam.config(CONFIG_FILE).dataSource(DataSourceType.MASERT);
    }

    @Override
    public Page<ChbAssetMappingDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 设置导出单元格
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {

    }




    /**
     * 数据操作，新增修改删除
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void submit(ChbAssetMappingDto dto) {

        //删除旧数据
        if(StringUtil.isNotEmpty(dto.getUuid())){
            chbAssetMappingMapper.deleteByUUid(dto.getUuid());
            chbAssetMappingMapper.updateChbBotCodeF(dto);
        }else{
            //批量修改CHB_BOT数据
            chbAssetMappingMapper.updateChbBotCode(dto);
        }
        //重新插入新数据
        chbAssetMappingMapper.insert(dto);
    }

    /**
     * 删除
     * @param dto
     */
    public void delete(ChbAssetMappingDto dto) {
        //批量修改CHB_BOT数据
        chbAssetMappingMapper.updateChbBotCodeByDelete(dto);
        chbAssetMappingMapper.deleteChbAssetTypeByKey(dto);
    }


    public String getChbAssetMappingByKey(ChbAssetMappingDto dto) {
        String message = null;
        ChbAssetMappingDto result = chbAssetMappingMapper.getChbAssetMappingByKey(dto);

        if(null!= result){
            //新建的数据才会检验代码映射
            if(StringUtil.equals(result.getSvrAssetCode(),dto.getSvrAssetCode())){
                message = "此报送系统的资产代码已映射，不能一对多映射";
            }else if(StringUtil.equals(result.getSadasAssetCode(),dto.getSadasAssetCode())&&StringUtil.isEmpty(dto.getUuid())){
                message = "此策略专户系统代码已映射，不能一对多映射";
            }
        }

        return message;
    }


}
