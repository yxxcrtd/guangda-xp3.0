package com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.dao;

import com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.dto.ChbFinanceProductsSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChbFinanceProductsSubInterfMapper {

	List<ChbFinanceProductsSub> selectProducts(@Param("batchDateEnd")String batchDateEnd);
	/**
	 * 跑批日上月是否已进行统计
	 * @param batchDateEnd
	 * @return
	 */
	List<ChbFinanceProductsSub> checkIsCounted(@Param("batchDateEnd") String batchDateEnd);

	void deleteProducts(@Param("batchDateEnd") String batchDateEnd);

	void insertIntoReport(@Param("List")List<ChbFinanceProductsSub> List);


	List<ChbFinanceProductsSub> selectAmountTotal();
}
