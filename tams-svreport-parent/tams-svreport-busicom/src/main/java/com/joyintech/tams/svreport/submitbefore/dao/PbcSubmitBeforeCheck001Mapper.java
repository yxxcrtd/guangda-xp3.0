package com.joyintech.tams.svreport.submitbefore.dao;

import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.submitbefore.dto.PbcSubmitBeforeCheck001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcSubmitBeforeCheck001Mapper {
    int deleteByPrimaryKey(String dataUuid);

    int insert(PbcSubmitBeforeCheck001Dto record);

    int insertSelective(PbcSubmitBeforeCheck001Dto record);

    int insertBasicByBasicInfo(PbcSubmitBeforeCheck001Dto record);

    PbcSubmitBeforeCheck001Dto selectByPrimaryKey(String dataUuid);

    int updateByPrimaryKeySelective(PbcSubmitBeforeCheck001Dto record);

    int updateByPrimaryKeyWithBLOBs(PbcSubmitBeforeCheck001Dto record);

    int updateByPrimaryKey(PbcSubmitBeforeCheck001Dto record);

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    List<PbcSubmitBeforeCheck001Dto> searchPage(Map<String, Object> pageParam);


    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    List<ExcelValidDto> searchBackKey(@Param("backKey") String backKey);

    /**
     *
     * @param list
     * @return
     */
    int insertPbcSubmitBeforeCheck(@Param("list") List<PbcSubmitBeforeCheck001Dto> list);

    /**
     *
     * @param map
     * @return
     */
    int deleteByKey(Map<String, String> map);

    /**
     * 删除明细
     * @param map
     * @return
     */
    int deleteDetailByKey(Map<String, String> map);

    /**
     * 导出报表 查询原数据
     * @param map
     * @return
     */
    List<PbcSubmitBeforeCheck001Dto> searchExportExcel(Map<String, Object> map);

    /**
     * 导出 错误信息 选中行
     * @param list
     * @return
     */
    List<PbcSubmitBeforeCheck001Dto> searchExportErr(@Param("keyList") List<Map<String, Object>> list);

    /**
     * 查询 ZG01 上期数据
     * @return
     */
    List<Map<String,Object>> selectZg01ListTer();

    /***
     * 查询到期交易的产品
     */
    List<String>  selectHaveDate(@Param("termId") String termId);

    /**
     * 查询封闭式净值型，非成立月的产品
     */
    List<String>  selectNoVdate(@Param("termId") String termId);

    /**
     * 查询封闭式净值型，非终止月的产品
     */
    List<String>  selectNoMdate(@Param("termId") String termId);

    /**
     * 查询当期尚未终止的产品
     */
    List<String>  selecthMdate(@Param("termId") String termId);

    /**
     * 查询当期尚未终止的产品
     */
    List<String>  selectVdateList(@Param("termId") String termId);


    /**
     * 查询过了预期终止日期尚未终止的资管产品且未报产品终止信息的产品
     */
    List<String>  selectNotSubmitProdId(@Param("termId") String termId);

    /**
     * 查询过了预期终止日期3天尚未终止的资管产品且未报产品终止信息的产品
     */
    List<String>  selectNotSubmit3DayProdId(@Param("termId") String termId);

    /**
     * 查询出所有zg01的产品代码
     */
    List<String> selectAllZG01ProdId();
}