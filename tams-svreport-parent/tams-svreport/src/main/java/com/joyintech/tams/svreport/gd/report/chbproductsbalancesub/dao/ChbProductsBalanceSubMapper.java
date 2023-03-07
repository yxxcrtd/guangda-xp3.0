package com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.dao;

import com.joyintech.tams.report.gd.report.chbproductsbalancesub.dto.ChbProductsBalanceSub001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 理财产品账户余额登记表 Mapper
 * @author yangxianjiu
 * @date 2022/03/30
 */
@Repository
public interface ChbProductsBalanceSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbProductsBalanceSub001Dto record);

    int insertSelective(ChbProductsBalanceSub001Dto record);

    int insertBasicByBasicInfo(ChbProductsBalanceSub001Dto record);

    ChbProductsBalanceSub001Dto selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbProductsBalanceSub001Dto record);

    int updateByPrimaryKey(ChbProductsBalanceSub001Dto record);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<ChbProductsBalanceSub001Dto> selectForPage(Map<String, Object> pageParam);


    /**
     *
     * @param taskId
     * @return
     */
    int getNoendCountByTaskBeanName(String taskId);
    /**
     *
     * @param param
     * @return
     */
    int countExportNum(Map<String, Object> param);
}