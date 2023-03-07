package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbMstCounterPartyErrodata;
import com.joyintech.tams.common.base.entity.ChbMstCounterPartyErrodataWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMstCounterPartyErrodataMapper {
    int deleteByPrimaryKey(String counterId);

    int insert(ChbMstCounterPartyErrodataWithBLOBs record);

    int insertSelective(ChbMstCounterPartyErrodataWithBLOBs record);

    int insertBasicByBasicInfo(ChbMstCounterPartyErrodataWithBLOBs record);

    ChbMstCounterPartyErrodataWithBLOBs selectByPrimaryKey(String counterId);

    int updateByPrimaryKeySelective(ChbMstCounterPartyErrodataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbMstCounterPartyErrodataWithBLOBs record);

    int updateByPrimaryKey(ChbMstCounterPartyErrodata record);
}