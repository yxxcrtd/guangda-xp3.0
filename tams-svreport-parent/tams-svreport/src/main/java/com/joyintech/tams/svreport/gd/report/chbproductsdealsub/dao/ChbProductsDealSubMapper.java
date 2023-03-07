package com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dao;

import com.joyintech.tams.report.gd.report.chbproductsdealsub.dto.ChbProductsDealSub001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 理财产品交易明细登记表 Mapper
 * @author yangxianjiu
 * @date 2022/03/30
 */
@Repository
public interface ChbProductsDealSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbProductsDealSub001Dto record);

    int insertSelective(ChbProductsDealSub001Dto record);

    int insertBasicByBasicInfo(ChbProductsDealSub001Dto record);

    ChbProductsDealSub001Dto selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbProductsDealSub001Dto record);

    int updateByPrimaryKey(ChbProductsDealSub001Dto record);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<ChbProductsDealSub001Dto> selectForPage(Map<String, Object> pageParam);

    /**
     * 统计数据行
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