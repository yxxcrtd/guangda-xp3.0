package com.joyintech.tams.svreport.interf.chb.prodinfo.dao;

import com.joyintech.tams.common.base.entity.ChbPrdPeriodYield;
import com.joyintech.tams.common.base.entity.ChbPrdStatusGd;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChbPrdStatusInterfMapper {

	/**
	 * @Description 批量插入
	 * @param list
	 * @return int
	 */
	int insertBatchList(@Param("list") List<ChbPrdStatusGd> list);

	int deleteByDate(@Param("date") Date valueDate);


}
