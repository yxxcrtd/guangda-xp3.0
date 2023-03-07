/*
 * 文件名：RskValtblPrtBotassetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.chb.gd.risk.rskValtblPrtBotasset.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.chb.gd.risk.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.tams.common.base.entity.RskValtblPrtBotassetMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 底层资产映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValtblPrtBotassetMapper
 * @since 1.0
 */
@Repository
public interface RskValtblPrtBotassetMapper {

    List<RskValtblPrtBotassetMapDto> queryForPage(Map<String, Object> pageParam);
    
    List<RskValtblPrtBotassetMapDto>  selectBotAssetMap(@Param("ptlId") String ptlId);
    
    RskValtblPrtBotassetMapDto selectAssetById(@Param("finprodId") String finprodId);
    
    RskValtblPrtBotassetMapDto selectWindBondAssetById(@Param("finprodId") String finprodId);
    
    Integer getCountForValUnmapped(Map<String, Object> paramMap);
    
    Integer getCountForValDiffmapped(Map<String, Object> paramMap);
    
    void applyMapForValUnmapped(Map<String, Object> paramMap);

    void applyForValUnmapped(RskValtblPrtBotassetMap record);
    
    void applyMapForValDiffmapped(Map<String, Object> paramMap);

    void cancleValmapped(@Param("ptlId") String ptlId, @Param("subCode") String subCode);

    void deletePrtBotassetMap(Map<String, Object> paramMap);

    List<SelectOptionDto> queryAllAsset(Map<String, Object> map);
}
