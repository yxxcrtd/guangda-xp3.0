package com.joyintech.tams.common.svreport.datacheck.beforeinsert.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommonCodeCheckDao {

	@Select("${sql}")
	List<List<String>> selectList(@Param("sql") String sql);
	
}
