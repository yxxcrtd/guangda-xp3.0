package com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ZG04PbcProdRaiseSubMapper {
    /**
     * 功能:人行单产品报表查询-产品资金募集信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年6月13日
     * @param map
     * @return 
     * List<PbcMonthReport>
     */
    List<ZG04PbcProdRaiseSub> searchPageForOnePrd(Map<String, Object> map);

    /**
     * 手工处理
     * @param map
     * @return
     */
    int cancelManul(Map<String, Object> map);

    /**
     * 取消手工处理
     * @param map
     * @return
     */
	int doManul(Map<String, Object> map);

	int insetBatchSubFromHisByUpdateTime(List<ZG04PbcProdRaiseSub> insertList);

	int deleteBatchSubFromHisByUpdateTime(List<ZG04PbcProdRaiseSub> insertList);

	int insertHisBatchBySendStatus(List<ZG04PbcProdRaiseSub> insertList);

	void deleteBatchBySendStatus(List<ZG04PbcProdRaiseSub> deleteList);

    List<String> selectRegistSeqnoList(@Param("list") List<ZG04PbcProdRaiseSub> registList, @Param("flag") String flag);

    //人行产品代码下拉框
    List<SelectOptionDto> getPbcProdId(Map<String, Object> map);
    //产品代码下拉框
    List<SelectOptionDto> getProdId(Map<String, Object> map);


    /**
     * 功能:根据月末日期和产品id查询相应的头寸信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：dingfeng 2022年11月14日
     * @param cdate 月末
     * @param prodIdList 产品代码List
     * @return
     * List<ZG04PbcProdRaiseSub>
     */
    List<ZG04PbcProdRaiseSub> selectPrdCashRaise(@Param("ifpsbc") String ifpsbc, @Param("cdate") String cdate, @Param("prodIdList") List<String> prodIdList, @Param("pbcProdRaising") String pbcProdRaising);

    /**
     * 功能:根据月末日期查询已经生成登记的数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：dingfeng 2022年11月14日
     * @param cdate
     * @return
     * List<ZG04PbcProdRaiseSub>
     */
    List<ZG04PbcProdRaiseSub> selectPrdCashRaiseRegist(@Param("cdate") String cdate);

    /**
     * 功能:批量删除
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年6月11日
     * @param deletelist
     * void
     */
    void deleteBatch(@Param("list") List<ZG04PbcProdRaiseSub> deletelist);

    int insert(ZG04PbcProdRaiseSub record);

    ZG04PbcProdRaiseSub getTypeByProdId(@Param("prodId")String prodId);

    int deleteByTermId(@Param("termId")String termId);

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

    List<String>   getZeroProdList(@Param("sdate") String sdate,@Param("edate") String edate);

    List<ZG04PbcProdRaiseSub> searchList(String date);

    List<ZG04PbcProdRaiseSub> searchListTermination(String date);

    ZG04PbcProdRaiseSub  selectMaxYiledMdate(@Param("prodId")String prodId,@Param("mdate")String mdate);
}