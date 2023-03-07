package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ZG09DatReportMapper {

	int deleteBySub(@Param("list") List<Map> record);

	int deleteByDat(@Param("list") List<Map> record);

	int insertSub(@Param("list") List<Map> record, @Param("uuid") String uuid);

	int insertDat(@Param("list") List<Map> record, @Param("uuid") String uuid);

	List<Map<String,String>> getSameKeyList(List<Map<String, Object>> dataMap);

	List<Map<String, Object>> getPListByUUid(String uuid);

	Boolean updatePDatDataStatus(String uuid);

	Boolean updatePDataStatus(List<Map<String, Object>> dataList);

	Boolean  updateUuidSub(String uuid);

}