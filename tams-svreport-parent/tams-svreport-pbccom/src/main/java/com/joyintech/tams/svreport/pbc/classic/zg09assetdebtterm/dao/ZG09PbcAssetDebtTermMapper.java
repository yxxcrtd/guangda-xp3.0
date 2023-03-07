package com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dao;

import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dto.ZG09PbcAssetDebtTermDto;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dto.ZG09PbcColReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Repository
public interface ZG09PbcAssetDebtTermMapper {

    int deleteByTermId(@Param("termId")String termId,@Param("reportType")String reportType);

    int selectCountByTermId(@Param("termId")String termId,@Param("reportType")String reportType);

    int insertSelective(ZG09PbcAssetDebtTermDto record);

    BigDecimal selectOtherDebt(@Param("termId")String termId,@Param("reportType")String reportType);

    List<ZG09PbcColReport> searchPage(Map<String, Object> map);

    List<ZG09PbcColReport> searchExport(Map<String, Object> map);


    int doManul(@Param("list")List<String> updateList);

    int cancelManul(@Param("list")List<String> list);

    List<ZG09PbcColReport> searchByUuid(@Param("uuid")String uuid);

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
