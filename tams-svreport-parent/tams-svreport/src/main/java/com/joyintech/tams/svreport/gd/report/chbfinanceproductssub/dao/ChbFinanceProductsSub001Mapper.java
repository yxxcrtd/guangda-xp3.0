package com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.dao;

import com.joyintech.tams.report.gd.report.chbfinanceproductssub.dto.ChbFinanceProductsSub001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbFinanceProductsSub001Mapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbFinanceProductsSub001Dto record);

    int insertSelective(ChbFinanceProductsSub001Dto record);

    int insertBasicByBasicInfo(ChbFinanceProductsSub001Dto record);

    ChbFinanceProductsSub001Dto selectByPrimaryKey(String registSeqno);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<ChbFinanceProductsSub001Dto> findPage(Map<String, Object> pageParam);

    int updateByPrimaryKeySelective(ChbFinanceProductsSub001Dto record);

    int updateByPrimaryKey(ChbFinanceProductsSub001Dto record);

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