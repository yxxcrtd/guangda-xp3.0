package com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.dao;

import com.joyintech.tams.report.gd.report.chbproductsassetcompare.dto.ChbProductsAssetCompare001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbProductsAssetCompare001Mapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbProductsAssetCompare001Dto record);

    int insertSelective(ChbProductsAssetCompare001Dto record);

    int insertBasicByBasicInfo(ChbProductsAssetCompare001Dto record);

    ChbProductsAssetCompare001Dto selectByPrimaryKey(String registSeqno);
    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<ChbProductsAssetCompare001Dto> findPage(Map<String, Object> pageParam);

    List<ChbProductsAssetCompare001Dto> queryAllList1(Map<String, Object> pageParam);
    int updateByPrimaryKeySelective(ChbProductsAssetCompare001Dto record);

    int updateByPrimaryKey(ChbProductsAssetCompare001Dto record);

    /**
     *
     * @param param
     * @return
     */
    int countExportNum(Map<String, Object> param);

    /**
     *
     * @param taskId
     * @return
     */
    int getNoendCountByTaskBeanName(String taskId);
}