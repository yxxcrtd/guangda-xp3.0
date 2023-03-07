package com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.dao;

import com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.dto.SvrProductsBalanceSubInterfDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author yangxianjiu
 * @date 2022/03/31
 */
@Repository
public interface SvrProductsBalanceSubInterfMapper {

    /**
     * 获取所有产品代码
     * @return
     */
    List<String> getProdId(@Param("batchDateEnd") String batchDateEnd);

    /**
     * 获取数据
     * @return
     */
    List<SvrProductsBalanceSubInterfDto> getProductsBalanceSubInterfData(@Param("prodIdList") List<String> prodIdList,@Param("batchDateStart") String batchDateStart,@Param("batchDateEnd") String batchDateEnd);

    /**
     * 检查明细
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    List<String> checkChbEqudet(@Param("batchDateStart") String batchDateStart,@Param("batchDateEnd") String batchDateEnd);

    /**
     * 检查持有
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    List<String> checkChbInvestholdInfo(@Param("batchDateStart") String batchDateStart,@Param("batchDateEnd") String batchDateEnd);

    /**
     * 删除
     * @param batchDateEnd
     * @return
     */
    int deleteProductsBalanceSub(@Param("batchDateEnd") String batchDateEnd);

    /**
     * 入库
     * @param dataList
     */
    int insertProductsBalanceSub(@Param("dataList") List<SvrProductsBalanceSubInterfDto> dataList);
}
