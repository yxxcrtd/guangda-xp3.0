package com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dto.PbcPrdAccountSub;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ZG01PbcPrdBaseinfoSubMapper {



	List<ZG01PbcPrdBaseinfoSub> searchPage(Map<String, Object> searchParam);
	List<ZG01PbcPrdBaseinfoSub> searchPageExport(Map<String, Object> searchParam);

	int insertHisBatchBySendStatus(List<String> insertList);

	void deleteBatchBySendStatus(List<String> deleteList);

	int doManul(@Param("list") List<ZG01PbcPrdBaseinfoSub> updateList, @Param("submitadd") String submitadd);

	List<String> selectRegistSeqnoList(@Param("list") List<ZG01PbcPrdBaseinfoSub> registList, @Param("flag") String flag);

	int cancelManul(List<ZG01PbcPrdBaseinfoSub> updateList);

	int insetBatchSubFromHisByUpdateTime(List<ZG01PbcPrdBaseinfoSub> insertList);

	int deleteBatchSubFromHisByUpdateTime(List<ZG01PbcPrdBaseinfoSub> insertList);

	List<ZG01PbcPrdBaseinfoSub> searchByUuid(List<String> list);


	List<ZG01PbcPrdBaseinfoSub> selectNeedRegisteData(ZG01PbcPrdBaseinfoSub qmodel);

	List<ZG01PbcPrdBaseinfoSub> selectAllRegisteData(@Param("reglist") List<ZG01PbcPrdBaseinfoSub> reglist);

	List<ZG01PbcPrdBaseinfoSub> selectAlReadyRegisteData(@Param("reglist") List<ZG01PbcPrdBaseinfoSub> reglist);

	void deleteByProdid(@Param("deleteList") List<ZG01PbcPrdBaseinfoSub> subList);

	int insert(ZG01PbcPrdBaseinfoSub record);

	List<ZG01PbcPrdBaseinfoSub> selectAllPbcProdId();

	int insertPbcProdId(ZG01PbcPrdBaseinfoSub record);

	ZG01PbcPrdBaseinfoSub selectbankCode();
	/**
	 * 更新手工处理
	 * @param list
	 * @return
	 */
	int doDatReportStatus(@Param("list") List<String> list);

	/**
	 * 取消手工处理insertPbcProdId
	 * @param list
	 * @return
	 */
	int cancelDatReportStatus(@Param("list") List<String> list);

}