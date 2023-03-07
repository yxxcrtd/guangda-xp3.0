package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface ZG05DatReportMapper {

	int deleteBySub(@Param("list") List<Map<String,Object>> record);

	int deleteByDat(@Param("list") List<Map<String,Object>> record);

	int insertSub(@Param("list") List<Map<String,Object>> record, @Param("uuid") String uuid);

	int insertDat(@Param("list") List<Map<String,Object>> record, @Param("uuid") String uuid);

	List<Map<String,String>> getSameKeyList(@Param("keys") Set<Object> dataMap);

	List<Map<String, Object>> getPListByUUid(String uuid);

	Boolean updatePDatDataStatus(String uuid);

	Boolean updatePDataStatus(List<Map<String, Object>> dataList);


	List<Map<String, Object>> getProdIdPbcProdIdList();

	Boolean  updateUuidSub(String uuid);

}