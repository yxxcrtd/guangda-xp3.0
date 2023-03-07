package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMstCounterPartyMapper {
    int deleteByPrimaryKey(String counterId);

    int insert(ChbMstCounterParty record);

    int insertSelective(ChbMstCounterParty record);

    int insertBasicByBasicInfo(ChbMstCounterParty record);

    ChbMstCounterParty selectByPrimaryKey(String counterId);

    int updateByPrimaryKeySelective(ChbMstCounterParty record);

    int updateByPrimaryKeyWithBLOBs(ChbMstCounterParty record);

    int updateByPrimaryKey(ChbMstCounterParty record);
}