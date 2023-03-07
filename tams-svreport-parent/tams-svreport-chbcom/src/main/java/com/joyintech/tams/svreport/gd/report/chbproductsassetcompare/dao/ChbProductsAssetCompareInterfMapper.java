package com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.dao;

import com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.dto.ChbProductsAssetCompare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChbProductsAssetCompareInterfMapper {

	List<ChbProductsAssetCompare> selectProducts(@Param("batchDateEnd") String batchDateEnd);

	void deleteProducts(@Param("batchDateEnd") String batchDateEnd);

	void insertIntoReport(@Param("List") List<ChbProductsAssetCompare> List);



}
