package com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dao;

import com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.dto.SvrProductsBalanceSubInterfDto;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dto.SvrProductsDealSubInterfDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 理财产品交易明细表 Mapper
 * @author yangxianjiu
 * @date 2022/04/01
 */
@Repository
public interface SvrProductsDealSubInterfMapper {

    /**
     * 检查是否含有投资者明细
     * @param batchDate
     * @param
     * @return
     */
    List<String> checkChbEqudet(@Param("batchDate") String batchDate);

    /**
     * 获取数据
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    List<SvrProductsDealSubInterfDto> getProductsDealSubInterfData(@Param("batchDate") String batchDate,@Param("batchDateStart") String batchDateStart, @Param("batchDateEnd") String batchDateEnd);

    /**
     * 删除
     * @param batchDate
     * @return
     */
    int deleteProductsDealSub(@Param("batchDate") String batchDate);

    /**
     * 插入数据
     * @param dataList
     * @return
     */
    int insertProductsDealSub(@Param("dataList") List<SvrProductsDealSubInterfDto> dataList);
}
