package com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dao;

import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAssetDebtDetailDto;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.PbcColReport;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11CompanyBondInfoSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.Zg11PbcColReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ZG11CompanyBondInfoSubMapper {


    int deleteByTermId(@Param("termId")String termId,@Param("reportType")String reportType);

    int selectCountByTermId(@Param("termId")String termId,@Param("reportType")String reportType);

    int insert(PbcColReport record);

    int insertColBalance(Zg11PbcColReport record);


    List<ZG11CompanyBondInfoSubDto> searchPage(Map<String, Object> map);

    void  insertSelective(ZG11CompanyBondInfoSubDto dto);

    int doManul(@Param("list")List<String> updateList);

    int cancelManul(@Param("list")List<String> list);

    List<ZG11CompanyBondInfoSubDto> searchByUuid(@Param("uuid")String uuid);

    /**
     * 功能:根据日期查询资产头寸
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @param bookDate
     * @return
     * List<PbcMonthAssetPositionDto>
     */
    List<ZG11PbcMonthAssetPositionDto> selectByDateAndProdid(@Param("prodList")List<String> prodList, @Param("bookDate")String bookDate,
                                                             @Param("modelType") String modelType);


    /**
     * 功能:根据数据状态查询资产要素(仅供中债使用)
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月19日
     * @param statusList
     * @return
     * List<AssetInfoDto>
     */
    List<ChbAssetInfo> selectAssetByStatus(@Param("statusList")List<String> statusList);

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
