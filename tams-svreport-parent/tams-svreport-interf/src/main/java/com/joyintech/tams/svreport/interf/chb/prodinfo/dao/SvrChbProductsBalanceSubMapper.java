package com.joyintech.tams.svreport.interf.chb.prodinfo.dao;

import com.joyintech.tams.svreport.interf.chb.prodinfo.dto.SvrChbProductsBalanceSub001Dto;
import com.joyintech.tams.svreport.interf.chb.prodinfo.service.SvrProductsBalanceReadFileImpService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 理财产品账户余额登记表 Mapper
 * @author yangxianjiu
 * @date 2022/03/30
 */
@Repository
public interface SvrChbProductsBalanceSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(SvrChbProductsBalanceSub001Dto record);

    int insertSelective(SvrChbProductsBalanceSub001Dto record);

    int insertBasicByBasicInfo(SvrChbProductsBalanceSub001Dto record);

    SvrChbProductsBalanceSub001Dto selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(SvrChbProductsBalanceSub001Dto record);

    int updateByPrimaryKey(SvrChbProductsBalanceSub001Dto record);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<SvrChbProductsBalanceSub001Dto> selectForPage(Map<String, Object> pageParam);


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


    /**
     *  删除超过日期大于该日期的数据
     * @param date
     * @return
     */
    int deleteMoreThenDate(@Param("history") Date date, @Param("now")Date now);
}