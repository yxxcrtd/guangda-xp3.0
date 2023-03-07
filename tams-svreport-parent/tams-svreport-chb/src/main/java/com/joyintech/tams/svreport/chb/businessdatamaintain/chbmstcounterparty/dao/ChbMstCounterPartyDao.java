package com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.base.entity.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.dto.ChbMstCounterPartyDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author cff
 * @date 2022-04-27 16:37
 */
@Repository
public interface ChbMstCounterPartyDao {
    List<SelectOptionDto> getCustomerName(Map<String, Object> param);
    List<SelectOptionDto> getCounterName(Map<String, Object> param);

    Map<String,String> selectCounterIdByCounterName(String counterName);
    ChbMstCounterPartyDto selectByKey(@Param("uuid") String uuid);
    ChbMstCounterPartyErrodataWithBLOBs selectErrorByKey(@Param("uuid") String uuid);

    ChbMstCounterPartyDto selectByCounterId(@Param("counterId") String counterId);

    Map<String, String> selectRivalPartyType(String counterName);
    int selectValidRecords(@Param("list") List<Map<String,String>> list);

    void update(ChbMstCounterPartyDto entity);
    void delete(ChbMstCounterPartyDto entity);

    void insert(ChbMstCounterPartyDto entity);

    void updateDto(ChbMstCounterPartyDto entity);

    List<ChbMstCounterPartyDto> selectAll(Map<String, Object> pageParam);
    /**
     * 当交易对手类别为“内部交易对手”时,枚举值默认展示【产品基本信息维护】模块已维护的产品名称
     *
     * @return
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodId(Map<String, Object> param);

    List<SuggestDto> getRivalPartyName(Map<String, Object> param);

    void batchInsert(@Param("list") List<ChbMstCounterPartyDto> list);

    int countDuplicatedData(@Param("list") List<Map<String, String>> list);

    void deleteByKey(ChbMstCounterPartyDto entity);

    List<ChbMstCounterPartyDto> selectBykeys(@Param("list") List<String> list);
}
