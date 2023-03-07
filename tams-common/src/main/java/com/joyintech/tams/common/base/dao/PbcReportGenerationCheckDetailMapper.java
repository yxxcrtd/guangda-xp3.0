package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcReportGenerationCheckDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface PbcReportGenerationCheckDetailMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(PbcReportGenerationCheckDetail record);

    int insertSelective(PbcReportGenerationCheckDetail record);

    int insertBasicByBasicInfo(PbcReportGenerationCheckDetail record);

    PbcReportGenerationCheckDetail selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PbcReportGenerationCheckDetail record);

    int updateByPrimaryKey(PbcReportGenerationCheckDetail record);
}