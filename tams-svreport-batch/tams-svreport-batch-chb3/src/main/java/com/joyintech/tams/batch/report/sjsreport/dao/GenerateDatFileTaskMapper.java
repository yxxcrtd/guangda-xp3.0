package com.joyintech.tams.batch.report.sjsreport.dao;

import com.joyintech.tams.report.gd.report.chbassetselementssub.dto.ChbAssetsElementsSub001Dto;
import com.joyintech.tams.report.gd.report.chbfinanceproductssub.dto.ChbFinanceProductsSub001Dto;
import com.joyintech.tams.report.gd.report.chbproductsassetcompare.dto.ChbProductsAssetCompare001Dto;
import com.joyintech.tams.report.gd.report.chbproductsbalancesub.dto.ChbProductsBalanceSub001Dto;
import com.joyintech.tams.report.gd.report.chbproductsdealsub.dto.ChbProductsDealSub001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GenerateDatFileTaskMapper {
    /**
     * 理财产品登记列表查询
     * @param pageParam
     * @return
     */
    List<ChbFinanceProductsSub001Dto> selectProductsSubList(Map<String, Object> pageParam);

    /**
     * 理财产品交易明细列表查询
     * @param pageParam
     * @return
     */
    List<ChbProductsDealSub001Dto> selectProductsDealSubList(Map<String, Object> pageParam);

    /**
     * 理财产品账户余额列表查询
     * @param pageParam
     * @return
     */
    List<ChbProductsBalanceSub001Dto> selectProductsBalanceSubList(Map<String, Object> pageParam);

    /**
     * 理财产品资产对照列表查询
     * @param pageParam
     * @return
     */
    List<ChbProductsAssetCompare001Dto> selectProductsAssetCompareList(Map<String, Object> pageParam);


    /**
     * 理财产品资产要素登记列表
     * @param pageParam
     * @return
     */
    List<ChbAssetsElementsSub001Dto> selectAssetsElementsSubList(Map<String, Object> pageParam);
}
