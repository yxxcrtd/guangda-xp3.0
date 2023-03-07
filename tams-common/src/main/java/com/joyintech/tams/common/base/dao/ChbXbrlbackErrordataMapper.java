package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbXbrlbackErrordata;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbXbrlbackErrordataMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ChbXbrlbackErrordata record);

    int insertSelective(ChbXbrlbackErrordata record);

    int insertBasicByBasicInfo(ChbXbrlbackErrordata record);

    ChbXbrlbackErrordata selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbXbrlbackErrordata record);

    int updateByPrimaryKey(ChbXbrlbackErrordata record);
}