package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao;

import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ZG01DatReportMapper {

	int deleteBySub(@Param("list")List<Map> record);

	int deleteBySub1(Map record);
	int deleteBySub2(Map record);

	int deleteByDat(@Param("list")List<Map> record);

	int insertSub(@Param("list")List<Map> record,@Param("uuid") String uuid);

	int insertDat(@Param("list")List<Map> record,@Param("uuid") String uuid);

	List<Map<String,String>> getSameKeyList(List<Map<String,Object>> dataMap);

	List<Map<String, Object>> getPListByUUid(String uuid);

	Boolean updatePDatDataStatus(String uuid);

	Boolean updatePDataStatus(List<Map<String,Object>> dataList);


    int insertHis(@Param("uuid")String uuid);

	Boolean  updateUuidSub(String uuid);

	List<ZG01PbcPrdBaseinfoSub> getListByUUid(String uuid);

}