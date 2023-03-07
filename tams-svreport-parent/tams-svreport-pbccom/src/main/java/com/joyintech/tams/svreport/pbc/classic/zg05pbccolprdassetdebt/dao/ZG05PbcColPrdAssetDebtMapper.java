package com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dao;

import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAssetDebtDetailDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.PbcColReport;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ZG05PbcColPrdAssetDebtMapper {


    int deleteByTermId(@Param("termId")String termId,@Param("reportType")String reportType);

    int selectCountByTermId(@Param("termId")String termId,@Param("reportType")String reportType);

    int insert(PbcColReport record);

    int insertColBalance(PbcReportAssetDebtDetailDto record);


    List<ZG05PbcColPrdAssetDebtDto> searchPage(Map<String, Object> map);

    List<ZG05PbcColPrdAssetDebtDto> searchExport(Map<String, Object> map);

    void  insertSelective(ZG05PbcColPrdAssetDebtDto dto);

    int doManul(@Param("list")List<String> updateList);

    int cancelManul(@Param("list")List<String> list);

    List<ZG05PbcColPrdAssetDebtDto> searchByUuid(@Param("uuid")String uuid);

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
