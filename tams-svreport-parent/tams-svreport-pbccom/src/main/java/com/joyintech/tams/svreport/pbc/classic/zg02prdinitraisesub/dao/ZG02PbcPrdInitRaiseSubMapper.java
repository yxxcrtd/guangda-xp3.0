package com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dto.ZG02PbcPrdInitRaiseSub;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZG02PbcPrdInitRaiseSubMapper {
    
    List<ZG02PbcPrdInitRaiseSub> searchPage(Map<String,Object> map);

	List<ZG02PbcPrdInitRaiseSub> searchPageExport(Map<String,Object> map);

    List<ZG02PbcPrdInitRaiseSub> searchProdId(Map<String,Object> map);




    //手工处理
	int doManul(@Param("list")List<ZG02PbcPrdInitRaiseSub> updateList,@Param("submitadd")String submitadd);

	//取消手工处理
	int cancelManul(List<ZG02PbcPrdInitRaiseSub> updateList);
	//找sub表有没有未报送的数据
	List<String> selectRegistSeqnoList(@Param("list")List<ZG02PbcPrdInitRaiseSub> registList,@Param("flag")String flag);
    //人行产品代码下拉框
	List<SelectOptionDto> getPbcProdId(Map<String, Object> map);
	//产品代码下拉框
	List<SelectOptionDto> getProdId(Map<String, Object> map);

	//批处理查询需要出登记的数据
	List<ZG02PbcPrdInitRaiseSub> selectPrdRegistInfo(@Param("vdate")String executeDate, @Param("prodIdList")List<String> prodIdList);
	//批处理查询已发行的产品数据
	List<ZG02PbcPrdInitRaiseSub> selectPrdInfo(@Param("vdate")String executeDate, @Param("prodIdList")List<String> prodIdList);
	//批处理查询产品头寸
	List<ChbPrdPosition> selectPsbcPrdposition(@Param("sublist")List<ZG02PbcPrdInitRaiseSub> raisingSubList);
	//批处理查询产品头寸
	List<ChbPrdPosition> selectPrdposition(@Param("sublist")List<ZG02PbcPrdInitRaiseSub> raisingSubList);
	//批处理查询登记信息
	List<ZG02PbcPrdInitRaiseSub> selectRegList(@Param("dto")ZG02PbcPrdInitRaiseSub dto);
	//批处理删除
	void deleteBatch(@Param("list")List<ZG02PbcPrdInitRaiseSub> subList);
	//批处理插入
	void insert(ZG02PbcPrdInitRaiseSub dto);

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