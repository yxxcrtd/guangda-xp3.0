package com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dao;

import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto.ZG10PbcColReportDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ZG10PbcColReportMapper {


    int deleteByTermId(@Param("termId")String termId,@Param("reportType")String reportType);

    int selectCountByTermId(@Param("termId")String termId,@Param("reportType")String reportType);

    List<ZG10PbcColReportDto> searchPage(Map<String, Object> map);
    List<ZG10PbcColReportDto> searchExport(Map<String, Object> map);

    void insertSelective(ZG10PbcColReportDto zg10Data);

    int doManul(@Param("list")List<String> updateList);

    int cancelManul(@Param("list")List<String> list);

    List<ZG10PbcColReportDto> searchByUuid(@Param("uuid")String uuid);

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
