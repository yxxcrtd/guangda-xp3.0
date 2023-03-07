package com.joyintech.tams.svreport.generationcheck.dao;

import com.joyintech.tams.common.base.entity.PbcReportGenerationCheckDetail;
import com.joyintech.tams.svreport.generationcheck.dto.PbcReportGenerationCheck001Dto;
import com.joyintech.tams.svreport.submitbefore.dto.PbcSubmitBeforeCheck001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcReportGenerationCheck001Mapper {
    int deleteByPrimaryKey(String dataUuid);

    int insert(PbcReportGenerationCheck001Dto record);

    int insertSelective(PbcReportGenerationCheck001Dto record);

    int insertBasicByBasicInfo(PbcReportGenerationCheck001Dto record);

    PbcReportGenerationCheck001Dto selectByPrimaryKey(String dataUuid);

    int updateByPrimaryKeySelective(PbcReportGenerationCheck001Dto record);

    int updateByPrimaryKeyWithBLOBs(PbcReportGenerationCheck001Dto record);

    int updateByPrimaryKey(PbcReportGenerationCheck001Dto record);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<PbcReportGenerationCheck001Dto> searchPage(Map<String, Object> pageParam);

    int insertReportGenerationCheck(@Param("list") List<PbcReportGenerationCheck001Dto> list);

    int deleteByKey(Map<String,String> map);

    /**
     * 删除明细
     * @param map
     * @return
     */
    int deleteDetailByKey(Map<String,String> map);

    /**
     * 导出报表
     * @param param
     * @return
     */
    List<PbcReportGenerationCheck001Dto> searchExportExcel(Map<String, Object> param);


    void deleteByBeforeBatchList(@Param("erroList")List<String> erroList,@Param("tableName")String tableName);

    void deleteByDetailBeBatchList(@Param("erroList")List<String> erroList,@Param("tableName")String tableName);

    void deleteByBatchList(@Param("erroList")List<String> erroList,@Param("tableName")String tableName);

    void deleteByFlag(@Param("tableName")String tableName);


    void deleteDetailByBatchList(@Param("erroList")List<String> erroList,@Param("tableName")String tableName);

    void deleteDetailByFlag(@Param("tableName")String tableName);


    int insertByBatchLBeforecheckist(@Param("subList")List<PbcSubmitBeforeCheck001Dto> subList);

    int insertByBatchLGcheckist(@Param("subList")List<PbcSubmitBeforeCheck001Dto> subList);

    int insertByBatchLGDetailist(@Param("subList")List<PbcReportGenerationCheckDetail> subList);

    int insertByBatchLGBeforeDetailist(@Param("subList")List<PbcReportGenerationCheckDetail> subList);

    List<PbcReportGenerationCheck001Dto> searchExportHtml( @Param("ruleId")String ruleId);


}