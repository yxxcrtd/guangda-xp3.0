package com.joyintech.tams.svreport.pbc.specificinvestorshold.dao;

import com.joyintech.tams.svreport.pbc.specificinvestorshold.dto.SpcInvestorsHoldTaskDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 特定目的载体持有信息维护(投资者) 生成批
 *
 * @author yangxianjiu
 * @date 2022/11/12
 */
@Repository
public interface SpcInvestorsHoldTaskMapper {

    /**
     * 抽取数据
     * @param batchDate
     * @return
     */
    List<SpcInvestorsHoldTaskDto> selectInvestHoldSubData(@Param("batchDate") String batchDate);

    /**
     * 删除 特定持有数据
     * @return
     */
    int delete(SpcInvestorsHoldTaskDto dto);


    /**
     * 插入数据
     * @param dto
     * @return
     */
    int insert(SpcInvestorsHoldTaskDto dto);
}
