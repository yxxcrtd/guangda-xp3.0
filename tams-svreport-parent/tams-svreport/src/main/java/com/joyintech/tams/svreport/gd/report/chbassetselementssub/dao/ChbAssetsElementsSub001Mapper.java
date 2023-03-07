package com.joyintech.tams.svreport.gd.report.chbassetselementssub.dao;

import com.joyintech.tams.report.gd.report.chbassetselementssub.dto.ChbAssetsElementsSub001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChbAssetsElementsSub001Mapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbAssetsElementsSub001Dto record);

    int insertSelective(ChbAssetsElementsSub001Dto record);

    int insertBasicByBasicInfo(ChbAssetsElementsSub001Dto record);

    ChbAssetsElementsSub001Dto selectByPrimaryKey(String registSeqno);

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    List<ChbAssetsElementsSub001Dto> findPage(Map<String, Object> pageParam);

    int updateByPrimaryKeySelective(ChbAssetsElementsSub001Dto record);

    int updateByPrimaryKey(ChbAssetsElementsSub001Dto record);

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