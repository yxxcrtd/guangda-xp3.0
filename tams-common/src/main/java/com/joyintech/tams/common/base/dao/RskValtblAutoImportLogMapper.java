package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblAutoImportLog;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblAutoImportLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(RskValtblAutoImportLog record);

    int insertSelective(RskValtblAutoImportLog record);

    int insertBasicByBasicInfo(RskValtblAutoImportLog record);

    RskValtblAutoImportLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RskValtblAutoImportLog record);

    int updateByPrimaryKeyWithBLOBs(RskValtblAutoImportLog record);

    int updateByPrimaryKey(RskValtblAutoImportLog record);
}