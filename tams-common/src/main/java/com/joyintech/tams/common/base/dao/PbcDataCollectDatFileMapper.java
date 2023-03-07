package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcDataCollectDatFile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface PbcDataCollectDatFileMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(PbcDataCollectDatFile record);

    int insertSelective(PbcDataCollectDatFile record);

    PbcDataCollectDatFile selectByPrimaryKey(String registSeqno);

    List<PbcDataCollectDatFile> searchPage(Map<String, Object> map);

    int updateDatStatus(String uuid);

    List<Map<String,String>> getProdIdList(@Param("list") Set<String> pbcProdId);

}