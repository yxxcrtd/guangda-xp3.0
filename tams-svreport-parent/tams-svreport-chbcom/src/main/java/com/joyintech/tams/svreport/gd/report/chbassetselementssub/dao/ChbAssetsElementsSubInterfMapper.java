package com.joyintech.tams.svreport.gd.report.chbassetselementssub.dao;

import com.joyintech.tams.svreport.gd.report.chbassetselementssub.dto.ChbAssetsElementsSub;
import com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.dto.ChbProductsAssetCompare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChbAssetsElementsSubInterfMapper {

	List<ChbAssetsElementsSub> selectProducts(@Param("batchDateEnd") String batchDateEnd);

	void deleteProducts(@Param("batchDateEnd") String batchDateEnd);

	void insertIntoReport(@Param("List") List<ChbAssetsElementsSub> List);

	List<ChbAssetsElementsSub> selectTrade();

	List<ChbAssetsElementsSub> selectMarket();
}
