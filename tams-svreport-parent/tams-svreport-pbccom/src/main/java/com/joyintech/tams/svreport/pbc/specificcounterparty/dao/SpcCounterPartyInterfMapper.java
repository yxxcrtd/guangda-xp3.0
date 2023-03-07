package com.joyintech.tams.svreport.pbc.specificcounterparty.dao;

import com.joyintech.tams.svreport.pbc.specificcounterparty.dto.SpcCounterPartyInterfDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 特定目的载体对手方信息维护接入批
 *
 * @author yangxianjiu
 * @date 2022/11/7
 */
@Repository
public interface SpcCounterPartyInterfMapper {

    /**
     * 从 资产端取数
     * @param batchDate
     * @return
     */
    List<SpcCounterPartyInterfDto> selectAssetData(@Param("batchDate") String batchDate);

    /**
     * 从 产品端取数
     * @param batchDate
     * @return
     */
    List<SpcCounterPartyInterfDto> selectProdData(@Param("batchDate") String batchDate);

    /**
     * 获取 已存在的
     * @param assetCodeList
     * @return
     */
    List<SpcCounterPartyInterfDto> getCounterPartyList(@Param("list") List<String> assetCodeList);

    /**
     * 批量 插入
     * @param dataListTemp
     * @return
     */
    int insert(@Param("dataList") List<SpcCounterPartyInterfDto> dataListTemp);
}
