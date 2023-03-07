package com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ZG07 除回购和拆解外贷款明细登记表
 *
 * @author yangxianjiu
 * @date 2022/11/9
 */
@Repository
public interface ZG07PbcColLoanDetailSub001Mapper {

    int deleteByPrimaryKey(ZG07PbcColLoanDetailSubDto record);

    int insert(ZG07PbcColLoanDetailSubDto record);

    int insertSelective(ZG07PbcColLoanDetailSubDto record);

    int insertBasicByBasicInfo(ZG07PbcColLoanDetailSubDto record);

    ZG07PbcColLoanDetailSubDto selectByPrimaryKey(ZG07PbcColLoanDetailSubDto record);

    int updateByPrimaryKeySelective(ZG07PbcColLoanDetailSubDto record);

    int updateByPrimaryKey(ZG07PbcColLoanDetailSubDto record);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<ZG07PbcColLoanDetailSubDto> selectForPage(Map<String, Object> pageParam);

    List<ZG07PbcColLoanDetailSubDto> selectForExport(Map<String, Object> pageParam);

    /**
     * 获取 资产代码
     * @param param
     * @return
     */
    List<SelectOptionDto> getAssetCode(Map<String, Object> param);

    /**
     * 手工处理
     * @param updateList
     * @return
     */
    int doManual(@Param("list")List<String> updateList);

    /**
     * 取消手工处理
     * @param list
     * @return
     */
    int cancelManual(@Param("list")List<String> list);

    /**
     *
     * @param uuid
     * @return
     */
    List<ZG07PbcColLoanDetailSubDto> selectByUuid(@Param("uuid")String uuid);

    /**
     * 是否 是已报送的月报
     * @param termId
     * @return
     */
    int selectCountByTermId(@Param("termId") String termId);

    /**
     * 清除历史数据
     * @param termId
     * @return
     */
    int deleteByTermId(@Param("termId") String termId);

    /**
     * 查询 人行资产头寸
     * @param executeDate
     * @return
     */
    List<PbcMonthAssetPositionDto> selectAssetPositionInfoList(@Param("executeDate") String executeDate);

    /**
     * 查询 除回购和拆借外贷款明细信息
     * @param executeDate
     * @return
     */
    List<OperationalLoanInfo001Dto> selectLoanDetailInfo(@Param("executeDate") String executeDate);

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