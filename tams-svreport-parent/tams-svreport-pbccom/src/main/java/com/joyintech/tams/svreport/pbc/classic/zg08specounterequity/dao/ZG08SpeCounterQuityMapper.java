package com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityBatchDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ZG08SpeCounterQuityMapper {

    List<ZG08SpeCounterQuityDto> searchPage(Map<String, Object> map);

    List<ZG08SpeCounterQuityDto> searchExport(Map<String, Object> map);

    int insertHisBatchBySendStatus(@Param("list")List<ZG08SpeCounterQuityDto> list);

    int deleteBatchBySendStatus(@Param("list")List<ZG08SpeCounterQuityDto> list);

    int doManul(@Param("list")List<ZG08SpeCounterQuityDto> updateList);

    int cancelManul(@Param("list")List<ZG08SpeCounterQuityDto> list);

    int insetBatchSubFromHisByUpdateTime(@Param("list")List<ZG08SpeCounterQuityDto> list);

    int deleteBatchSubFromHisByUpdateTime(@Param("list")List<ZG08SpeCounterQuityDto> list);

    List<ZG08SpeCounterQuityDto> searchByUuid(@Param("uuid")String uuid);

    List<SuggestDto> queryPrdNameList(@Param("prodId")String prodId);

    List<ZG08SpeCounterQuityBatchDto> selectAssetPositionInfoList(@Param("executeDate")String executeDate);

    List<ZG08SpeCounterQuityBatchDto> selectHoldInfoList(@Param("executeDate")String executeDate);

    int insert(ZG08SpeCounterQuityBatchDto dto);

    int deleteByTermId(@Param("termId")String termId);

    int selectCountByTermId(@Param("termId")String termId);

    List<SuggestDto> getCounterProdList(@Param("prodId")String param);

    /**
     * 更新手工处理
     * @param list
     * @return
     */
    int doDatReportStatus(@Param("list") List<String> list);

    /**
     * 取消手工处理
     * @param list
     * @return
     */
    int cancelDatReportStatus(@Param("list") List<String> list);
}
