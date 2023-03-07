package com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dao;

import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto.ZG06PbcColAssetUsufructSubBatchDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ZG06PbcColAssetUsufructBatchMapper {

    List<OperationalLoanInfo001Dto> selectAssetInfo(HashMap<String, Object> paramMap);

    List<ZG06PbcColAssetUsufructSubBatchDto> selectAssetPositionInfoList(@Param("executeDate")String executeDate);

    void insertSubData(ZG06PbcColAssetUsufructSubBatchDto dto);

    int selectCountByTermId(@Param("termId")String termId);

    void deleteByTermId(@Param("termId")String termId);
}
