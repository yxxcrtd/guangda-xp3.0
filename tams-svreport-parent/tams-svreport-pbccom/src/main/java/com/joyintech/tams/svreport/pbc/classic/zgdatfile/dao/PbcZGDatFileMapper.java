package com.joyintech.tams.svreport.pbc.classic.zgdatfile.dao;

import com.joyintech.tams.report.system.peoplebankcodepoolmanagement.dto.ProPeoplebankcodeManagement001Dto;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.dto.PbcDataCollectDatFileDto;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.dto.PbcDataZG01DatDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface PbcZGDatFileMapper {

    /**
     * 插入采集平台dat主表
     * @param record
     * @return
     */
    int insertPbcDataCollectDatFile(PbcDataCollectDatFileDto record);


    int deletePbcDataCollectDatFileByName(String datFileName);

    /**
     * 根据指定条件删除采集平台dat主表
     * @param params
     * @return
     */
    int deletePbcDataCollectDatFileByParams(Map<String, Object> params);

    /**
     * 根据指定条件查找采集平台dat主表
     * @param params
     * @return
     */
    List<PbcDataCollectDatFileDto> findPbcDataCollectDatFile(Map<String, Object> params);

    /**
     * 删除采集平台主表
     * @param record
     * @return
     */
    int deletePbcDataCollectDatFile(PbcDataCollectDatFileDto record);

    /**
     * 根据人行主表uuid删除明细表ZG01
     * @param detailid
     * @return
     */
    int deleteZG01DetailDat(@Param("detailid") String detailid);

    /**
     * 根据人行主表uuid删除明细表ZG02
     * @param detailid
     * @return
     */
    int deleteZG02DetailDat(@Param("detailid") String detailid);

    /**
     * 根据人行主表uuid删除明细表ZG03
     * @param detailid
     * @return
     */
    int deleteZG03DetailDat(@Param("detailid") String detailid);

    /**
     * 根据人行主表uuid删除明细表ZG04
     * @param detailid
     * @return
     */
    int deleteZG04DetailDat(@Param("detailid") String detailid);

    /**
     * 根据人行主表uuid删除明细表ZG05
     * @param detailid
     * @return
     */
    int deleteZG05DetailDat(@Param("detailid") String detailid);

    /**
     * 根据人行主表uuid删除明细表ZG06
     * @param detailid
     * @return
     */
    int deleteZG06DetailDat(@Param("detailid") String detailid);
    /**
     * 根据人行主表uuid删除明细表ZG07
     * @param detailid
     * @return
     */

    int deleteZG07DetailDat(@Param("detailid") String detailid);

    /**
     * 根据人行主表uuid删除明细表ZG08
     * @param detailid
     * @return
     */
    int deleteZG08DetailDat(@Param("detailid") String detailid);

    /**
     * 根据人行主表uuid删除明细表ZG09
     * @param detailid
     * @return
     */
    int deleteZG09DetailDat(@Param("detailid") String detailid);
    /**
     * 根据人行主表uuid删除明细表ZG10
     * @param detailid
     * @return
     */

    int deleteZG10DetailDat(@Param("detailid") String detailid);

    /**
     * 根据人行主表uuid删除明细表ZG11
     * @param detailid
     * @return
     */
    int deleteZG11DetailDat(@Param("detailid") String detailid);

    /**
     * 根据指定条件从登记表插入dat(ZG01)
     * @param params
     * @return
     */
    int insertZG01DetailDatFromSub(Map<String, Object> params);

    /**
     * 批量插入插入dat(ZG01)
     * @param params
     * @return
     */
    int insertZG01DetailDat(@Param("list") List<PbcDataZG01DatDto> list);//不用了

    /**
     * 根据指定条件从登记表插入dat(ZG02)
     * @param params
     * @return
     */
    int insertZG02DetailDatFromSub(Map<String, Object> params);

    /**
     * 根据指定条件从登记表插入dat(ZG03)
     * @param params
     * @return
     */
    int insertZG03DetailDatFromSub(Map<String, Object> params);

    /**
     * 根据指定条件从登记表插入dat(ZG04)
     * @param params
     * @return
     */
    int insertZG04DetailDatFromSub(Map<String, Object> params);

    /**
     * 根据指定条件从登记表插入dat(ZG05)
     * @param params
     * @return
     */
    int insertZG05DetailDatFromSub(Map<String, Object> params);

    /**
     * 根据指定条件从登记表插入dat(ZG06)
     * @param params
     * @return
     */
    int insertZG06DetailDatFromSub(Map<String, Object> params);


    /**
     * 根据指定条件从登记表插入dat(ZG07)
     * @param params
     * @return
     */
    int insertZG07DetailDatFromSub(Map<String, Object> params);

    /**
     * 根据指定条件从登记表插入dat(ZG08)
     * @param params
     * @return
     */
    int insertZG08DetailDatFromSub(Map<String, Object> params);

    /**
     * 根据指定条件从登记表插入dat(ZG09)
     * @param params
     * @return
     */
    int insertZG09DetailDatFromSub(Map<String, Object> params);

    /**
     * 根据指定条件从登记表插入dat(ZG10)
     * @param params
     * @return
     */
    int insertZG10DetailDatFromSub(Map<String, Object> params);

    /**
     * 根据指定条件从登记表插入dat(ZG11)
     * @param params
     * @return
     */
    int insertZG11DetailDatFromSub(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG01的UUID
     * @param params
     * @return
     */
    int updateZG01SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG02的UUID
     * @param params
     * @return
     */
    int updateZG02SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG03的UUID
     * @param params
     * @return
     */
    int updateZG03SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG04的UUID
     * @param params
     * @return
     */
    int updateZG04SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG05的UUID
     * @param params
     * @return
     */
    int updateZG05SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG06的UUID
     * @param params
     * @return
     */
    int updateZG06SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG07的UUID
     * @param params
     * @return
     */
    int updateZG07SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG08的UUID
     * @param params
     * @return
     */
    int updateZG08SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG09的UUID
     * @param params
     * @return
     */
    int updateZG09SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG10的UUID
     * @param params
     * @return
     */
    int updateZG10SubDatUUID(Map<String, Object> params);

    /**
     * 根据指定条件更新登记表ZG11的UUID
     * @param params
     * @return
     */
    int updateZG11SubDatUUID(Map<String, Object> params);

    /**
     * 通过ZG01产品基本信息查询外部人行产品代码
     */
    List<ProPeoplebankcodeManagement001Dto>   selectAllPbcId();

    /**
     * 根据UUID删除主表数据
     * @param params
     * @return
     */
    int deletePbcDataCollectDatFileByUuid(@Param("list")List<String> list);

    List<String> searchZG01DatUuid();
    List<String> searchZG02DatUuid();
    List<String> searchZG03DatUuid();
    int searchZG01Count();
    int searchZG02Count();
    int searchZG03Count();
    int searchZG04Count(@Param("termid")String termid);
    int searchZG05Count(@Param("termid")String termid);
    int searchZG06Count(@Param("termid")String termid);
    int searchZG07Count(@Param("termid")String termid);
    int searchZG08Count(@Param("termid")String termid);
    int searchZG09Count(@Param("termid")String termid);
    int searchZG10Count(@Param("termid")String termid);
    int searchZG11Count(@Param("termid")String termid);

}
