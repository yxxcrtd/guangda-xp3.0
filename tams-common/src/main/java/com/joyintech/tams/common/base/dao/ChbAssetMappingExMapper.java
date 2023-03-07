package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAssetMappingEx;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAssetMappingExMapper {
    int insert(ChbAssetMappingEx record);

    int insertSelective(ChbAssetMappingEx record);

    int insertBasicByBasicInfo(ChbAssetMappingEx record);
}