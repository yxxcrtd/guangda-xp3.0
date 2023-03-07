package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdStatusGd;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbPrdStatusGdMapper {
    int insert(ChbPrdStatusGd record);

    int insertSelective(ChbPrdStatusGd record);

    int insertBasicByBasicInfo(ChbPrdStatusGd record);
}