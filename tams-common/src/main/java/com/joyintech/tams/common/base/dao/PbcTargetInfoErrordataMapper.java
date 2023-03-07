package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcTargetInfoErrordataWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PbcTargetInfoErrordataMapper {
    int insert(PbcTargetInfoErrordataWithBLOBs record);

    int insertSelective(PbcTargetInfoErrordataWithBLOBs record);

    int insertBasicByBasicInfo(PbcTargetInfoErrordataWithBLOBs record);

    PbcTargetInfoErrordataWithBLOBs selectByPrimaryKey(@Param("uuid") String uuid);
}