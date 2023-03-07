package com.joyintech.tams.svreport.fininstitution.dao;

import com.joyintech.tams.svreport.fininstitution.dto.PbcFinInstitutionAssetInfo001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 表内资产数据维护
 *
 * @author yangxianjiu
 * @date 2022/11/23
 */
@Repository
public interface PbcFinInstitutionAssetInfo001Mapper {
    /**
     * 删除
     * @param list
     * @return
     */
    int deleteByPrimaryKey(@Param("list") List<PbcFinInstitutionAssetInfo001Dto> list);

    int insert(PbcFinInstitutionAssetInfo001Dto record);

    int insertSelective(PbcFinInstitutionAssetInfo001Dto record);

    int insertBasicByBasicInfo(PbcFinInstitutionAssetInfo001Dto record);

    PbcFinInstitutionAssetInfo001Dto selectByPrimaryKey(String termId);

    int updateByPrimaryKeySelective(PbcFinInstitutionAssetInfo001Dto record);

    int updateByPrimaryKey(PbcFinInstitutionAssetInfo001Dto record);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<PbcFinInstitutionAssetInfo001Dto> searchPage(Map<String, Object> pageParam);

    /**
     * 双击详情
     * @param paramMap
     * @return
     */
    PbcFinInstitutionAssetInfo001Dto selectDetail(Map<String, Object> paramMap);

    /**
     * 新建 校重
     * @param entity
     * @return
     */
    PbcFinInstitutionAssetInfo001Dto checkExist(PbcFinInstitutionAssetInfo001Dto entity);

    /**
     * 导入 校验重复
     * @param mapList
     * @return
     */
    int checkDuplicatedData(@Param("list") List<Map<String, String>> mapList);

    /**
     * 导入
     * @param dataList
     * @return
     */
    int mergeInfoList(@Param("list") List<PbcFinInstitutionAssetInfo001Dto> dataList);
}