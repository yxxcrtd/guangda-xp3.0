/*
 * 文件名：MstYieldCurveInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.dao;

import com.joyintech.tams.svreport.chb.businessdatamaintain.chbassetmapping.dto.ChbAssetMappingDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dto.ChbBranchNetworkDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 *资产代码匹配映射管理
 * 
 * @author wangzhao
 * @version 1.0 2022年5月16日
 * @see ChbAssetMappingMapper
 * @since 1.0
 */
@Repository
public interface ChbAssetMappingMapper {

    /**
     * 查询资产代码匹配映射管理
     * 
     * @param param 查询参数
     * @return 返回查询结果
     * @see
     * @since 1.0
     */
    List<ChbAssetMappingDto> select(Map<String, Object> param);

    /**
     * 查询新增数据主键是否重复
     * @param dto
     * @return list
     */

    ChbAssetMappingDto getChbAssetMappingByKey(ChbAssetMappingDto dto);

    /**
     * 更新状态
     * @param list
     * @return
     */
    int deleteChbAssetTypeByKey(ChbAssetMappingDto dto);

    int updateChbBotCodeByDelete (ChbAssetMappingDto dto);

    int updateChbBotCode (ChbAssetMappingDto dto);

    int updateChbBotCodeF (ChbAssetMappingDto dto);

    int insert (ChbAssetMappingDto dto);

    int deleteByUUid (String  uuid);

}
