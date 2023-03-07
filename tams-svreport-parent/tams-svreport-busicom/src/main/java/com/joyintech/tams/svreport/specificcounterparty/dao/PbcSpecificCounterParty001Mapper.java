package com.joyintech.tams.svreport.specificcounterparty.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 特定目的载体对手方信息维护
 *
 * @author yangxianjiu
 * @date 2022/11/1
 */
@Repository
public interface PbcSpecificCounterParty001Mapper {
    /**
     * 删除
     * @return
     */
    int deleteByPrimaryKey(@Param("list") List<PbcSpecificCounterParty001Dto> list);

    /**
     * 删除已有 删除状态的数据
     * @param list
     * @return
     */
    int delete(@Param("dto") PbcSpecificCounterParty001Dto dto, @Param("list") List<PbcSpecificCounterParty001Dto> list);

    int insert(PbcSpecificCounterParty001Dto record);

    int insertSelective(PbcSpecificCounterParty001Dto record);

    int insertBasicByBasicInfo(PbcSpecificCounterParty001Dto record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PbcSpecificCounterParty001Dto record);

    /**
     * 详情
     * @param pbcUuid
     * @return
     */
    PbcSpecificCounterParty001Dto selectByPrimaryKey(String pbcUuid);

    /**
     * 删除
     * @param dto
     * @param list
     * @return
     */
    int updateByPrimaryKey(@Param("dto") PbcSpecificCounterParty001Dto dto, @Param("list") List<PbcSpecificCounterParty001Dto> list);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<PbcSpecificCounterParty001Dto> selectForPage(Map<String, Object> pageParam);

    /**
     * 检验数据重复
     * @param entity 校验对象
     * @return 数据对象
     */
    PbcSpecificCounterParty001Dto checkExist(PbcSpecificCounterParty001Dto entity);

    /**
     * 获取资产代码
     * @param paramMap
     * @return
     */
    List<SuggestDto> queryAssetCode(Map<String, Object> paramMap);

    /**
     * 获取识别标识
     * @param paramMap
     * @return
     */
    List<SuggestDto> queryIndentifyMark(Map<String, Object> paramMap);

    /**
     * 导入校验 数据存在
     * @param list
     * @return
     */
    int countDuplicatedData(List<PbcSpecificCounterParty001Dto> list);

    /**
     * 插入数据
     * @param dataList
     */
    void mergeInfoList(@Param("list") List<PbcSpecificCounterParty001Dto> dataList);

    /**
     * 资产类 通过资产代码 获取 交易对手机构名称
     * @param param
     * @return
     */
    Map<String, Object> getPartyOrgNameByAssetCode(Map<String, Object> param);

    /**
     * 资产 获取 资产代码 / 投资 获取 投资者名称
     * @param param
     * @return
     */
    Map<String, Object> getPartyProdNameByAssetCode(Map<String, Object> param);

    /**
     * 资产 获取 交易对手产品种类
     * @param param
     * @return
     */
    Map<String, Object> getPartyProdTypeByAssetCode(Map<String, Object> param);

    /**
     * 获取 资产端 所有资产代码
     * @return
     */
    List<PbcSpecificCounterParty001Dto> selectAllAssetCode();

    /**
     * 获取 投资产品端 所有资产代码
     * @return
     */
    List<PbcSpecificCounterParty001Dto> selectAllInvestCode();

}