package com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dto.PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.position.dto.ChbPrdFeePosition;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZG03PbcPrdBaseinfoEndSubMapper {
    
    List<ZG03PbcPrdBaseinfoEndSub> searchPage(Map<String,Object> map);

	List<ZG03PbcPrdBaseinfoEndSub> searchPageExport(Map<String,Object> map);

    //手工处理
	int doManul(@Param("list")List<ZG03PbcPrdBaseinfoEndSub> updateList,@Param("submitadd")String submitadd);

	//取消手工处理
	int cancelManul(List<ZG03PbcPrdBaseinfoEndSub> updateList);
	//找sub表有没有未报送的数据
	List<String> selectRegistSeqnoList(@Param("list")List<ZG03PbcPrdBaseinfoEndSub> registList,@Param("flag")String flag);
	// His表数据插入sub表
	int insetBatchSubFromHisByUpdateTime(List<ZG03PbcPrdBaseinfoEndSub> insertList);
	// 删除his表中移走的数据
	int deleteBatchSubFromHisByUpdateTime(List<ZG03PbcPrdBaseinfoEndSub> insertList);
	// 历史表插入
	int insertHisBatchBySendStatus(List<ZG03PbcPrdBaseinfoEndSub> insertList);
	// 原始表删除
	void deleteBatchBySendStatus(List<ZG03PbcPrdBaseinfoEndSub> deleteList);
    //人行产品代码下拉框
	List<SelectOptionDto> getPbcProdId(Map<String, Object> map);
	//产品代码下拉框
	List<SelectOptionDto> getProdId(Map<String, Object> map);

	List<ZG03PbcPrdBaseinfoEndSub> getProdIdsByEndSub(@Param("batchDate")String format, @Param("tradType")String value, @Param("prodidlist")List<String> prodIdList);

	List<ZG03PbcPrdBaseinfoEndSub> getProdInfoByEndSub(@Param("list")List<ZG03PbcPrdBaseinfoEndSub> list,@Param("tradType") String tradType);

	List<ChbPrdFeePosition> getPrdFeePositionList(@Param("list")List<ZG03PbcPrdBaseinfoEndSub> list);

	List<ChbPrdPosition> getPrdDealPositionList(@Param("list")List<ZG03PbcPrdBaseinfoEndSub> list);

	List<ZG03PbcPrdBaseinfoEndSub> selectRegList(@Param("dto")ZG03PbcPrdBaseinfoEndSub list);

	void deleteBatch(@Param("list")List<ZG03PbcPrdBaseinfoEndSub> subList);

	int insert(ZG03PbcPrdBaseinfoEndSub record);

	List<ZG03PbcPrdBaseinfoEndSub> searchForManul(@Param("list")List<ZG03PbcPrdBaseinfoEndSub> list);

	BigDecimal gettdyUnitValueSumByProdId(@Param("prodId")String prodId,@Param("date") Date date);

	BigDecimal getunitValueTotalByProdId(@Param("prodId")String prodId,@Param("date") Date date);

	List<ZG03PbcPrdBaseinfoEndSub> searchForselectToImport(@Param("list")List<ZG03PbcPrdBaseinfoEndSub> list);

	/**
	 * 更新手工处理
	 * @param list
	 * @return
	 */
	int doDatReportStatus(@Param("list") List<String> list);

	/**
	 * 取消手工处理
	 * @param list
	 * @return
	 */
	int cancelDatReportStatus(@Param("list") List<String> list);
}