package com.joyintech.tams.svreport.specificinvestorshold.dao;


import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.svreport.specificinvestorshold.dto.PbcSpecificInvestorsHold001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 特定目的载体持有信息维护(投资者)
 *
 * @author yangxianjiu
 * @date 2022/11/4
 */
@Repository
public interface PbcSpecificInvestorsHold001Mapper {

    int deleteByPrimaryKey(PbcSpecificInvestorsHold001Dto record);

    int insert(PbcSpecificInvestorsHold001Dto record);

    int insertSelective(PbcSpecificInvestorsHold001Dto record);

    int insertBasicByBasicInfo(PbcSpecificInvestorsHold001Dto record);

    /**
     * 详情
     *
     * @param uuid
     * @return
     */
    PbcSpecificInvestorsHold001Dto selectByPrimaryKey(@Param("uuid") String uuid);

    int updateByPrimaryKeySelective(PbcSpecificInvestorsHold001Dto record);

    /**
     * 删除
     *
     * @param dto
     * @param list
     * @return
     */
    int updateByPrimaryKey(@Param("dto") PbcSpecificInvestorsHold001Dto dto, @Param("list") List<PbcSpecificInvestorsHold001Dto> list);

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    List<PbcSpecificInvestorsHold001Dto> selectForPage(Map<String, Object> map);

    /**
     * 获取 内部产品代码
     *
     * @param param
     * @return
     */
    List<SelectOptionDto> getProdInsId(Map<String, Object> param);

    /**
     * 获取 人行产品代码
     *
     * @param param
     * @return
     */
    Map<String, Object> getPbcProdIdByAccount(Map<String, Object> param);

    /**
     * 判断 是否已存在
     *
     * @param entity
     * @return
     */
    List<PbcSpecificInvestorsHold001Dto> checkExist(PbcSpecificInvestorsHold001Dto entity);

    /**
     * 导入 检验存在数据
     *
     * @param list
     * @return
     */
    int countDuplicatedData(@Param("list") List<Map<String, String>> list);

    /**
     * 识别标识 与 投资者名称 校重
     *
     * @param param
     * @return
     */
    Integer getIndentifyMarkAndHoldName(Map<String, Object> param);

    /**
     * 获取 身份登记 识别标识
     *
     * @param param
     * @return
     */
    List<SuggestDto> getIndentifyMark(Map<String, Object> param);

    /**
     * 获取 投资者 类别
     *
     * @param param
     * @return
     */
    Map<String, Object> getHolderTypeByIndentifyMark(Map<String, Object> param);

    /**
     * 导入数据 查询已有数据
     * @param checkDataList
     * @return
     */
    List<PbcSpecificInvestorsHold001Dto> selectDataForAccountAndIndentifyMarkAndHoldDate(@Param("dataList") List<Map<String, Object>> checkDataList);

    /**
     * 删除错误信息
     * @param list
     * @return
     */
    Integer deleteErrorData(@Param("list") List<PbcSpecificInvestorsHold001Dto> list);

    /**
     * 删除已有 删除状态的数据
     * @param list
     * @return
     */
    Integer delete(@Param("dto") PbcSpecificInvestorsHold001Dto dto, @Param("list") List<PbcSpecificInvestorsHold001Dto> list);
}