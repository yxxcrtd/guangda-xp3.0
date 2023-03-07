package com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dao;

import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dto.SvrChbConsignorDetailSubInterfDto;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dto.SvrChbPrdPeriodyieldDto;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dto.SvrMstExchargeQuoteDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangxianjiu
 * @date 2022/04/06
 */
@Repository
public interface SvrChbConsignorDetailSubInterfMapper {

    /**
     * 获取数据
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    List<SvrChbConsignorDetailSubInterfDto> getChbConsignorDetailSubData(@Param("batchDateStart") String batchDateStart, @Param("batchDateEnd") String batchDateEnd);

    /**
     * 检查持有
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    List<String> checkChbInvestholdInfo(@Param("batchDateStart") String batchDateStart,@Param("batchDateEnd") String batchDateEnd);

    /**
     * 跑批日上月是否已进行统计
     * @param batchDateEnd
     * @return
     */
    List<SvrChbConsignorDetailSubInterfDto> checkIsCounted(@Param("batchDateEnd") String batchDateEnd);

    /**
     * 获取汇率
     * @param batchDateEnd
     * @return
     */
    List<SvrMstExchargeQuoteDto> getMstExchargeQuote(@Param("batchDateEnd") String batchDateEnd);

    /**
     * 获取月末公布净值
     * @param batchDateEnd
     * @return
     */
    List<SvrChbPrdPeriodyieldDto> getChbPrdPeriodyield(@Param("batchDateStart") String batchDateStart,@Param("batchDateEnd") String batchDateEnd);

    /**
     * 数据入库
     * @param dataList
     * @return
     */
    int insert(@Param("dataList") List<SvrChbConsignorDetailSubInterfDto> dataList);

    /**
     * 删除数据
     * @param batchDateEnd
     * @return
     */
    int deleteData(@Param("batchDateEnd") String batchDateEnd);
}