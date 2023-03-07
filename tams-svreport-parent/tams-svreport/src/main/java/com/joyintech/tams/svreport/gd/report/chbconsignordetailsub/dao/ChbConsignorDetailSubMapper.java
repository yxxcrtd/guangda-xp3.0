package com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.gd.report.chbconsignordetailsub.dto.ChbConsignorDetailSub001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 委托人明细数据 Mapper
 * @author yangxianjiu
 * @date 2022/03/29
 */
@Repository
public interface ChbConsignorDetailSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbConsignorDetailSub001Dto record);

    int insertSelective(ChbConsignorDetailSub001Dto record);

    int insertBasicByBasicInfo(ChbConsignorDetailSub001Dto record);

    ChbConsignorDetailSub001Dto selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbConsignorDetailSub001Dto record);

    int updateByPrimaryKey(ChbConsignorDetailSub001Dto record);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<ChbConsignorDetailSub001Dto> selectForPage(Map<String, Object> pageParam);

    /**
     *
     * @param paramMap
     * @return
     */
    List<SuggestDto> queryProdInfo(Map<String, Object> paramMap);

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
