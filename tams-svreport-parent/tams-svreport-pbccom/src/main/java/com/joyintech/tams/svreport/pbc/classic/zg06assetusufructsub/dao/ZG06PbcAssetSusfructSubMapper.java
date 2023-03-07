package com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto.ZG06PbcAssetSusfructSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZG06PbcAssetSusfructSubMapper {
    
    List<ZG06PbcAssetSusfructSub> searchPage(Map<String,Object> map);

    //手工处理
	int doManul(@Param("list")List<ZG06PbcAssetSusfructSub> updateList,@Param("submitadd")String submitadd,@Param("termId")String termId);

	//取消手工处理
	int cancelManul(@Param("updateList")List<ZG06PbcAssetSusfructSub> updateList,@Param("termId")String termId);
	//找sub表有没有未报送的数据
	List<String> selectRegistSeqnoList(@Param("list")List<ZG06PbcAssetSusfructSub> registList,@Param("flag")String flag,@Param("termId")String termId);
	// His表数据插入sub表
	int insetBatchSubFromHisByUpdateTime(List<ZG06PbcAssetSusfructSub> insertList);
	// 删除his表中移走的数据
	int deleteBatchSubFromHisByUpdateTime(List<ZG06PbcAssetSusfructSub> insertList);
	// 历史表插入
	int insertHisBatchBySendStatus(List<ZG06PbcAssetSusfructSub> insertList);
	// 原始表删除
	void deleteBatchBySendStatus(List<ZG06PbcAssetSusfructSub> deleteList);

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

	//人行产品代码下拉框
	List<SelectOptionDto> getPbcProdId(Map<String, Object> map);
}