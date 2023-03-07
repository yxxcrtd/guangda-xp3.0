package com.joyintech.tams.batch.report.report.chbproductsassetcompare.dao;

import com.joyintech.tams.report.gd.report.chbproductsassetcompare.dto.ChbProductsAssetCompare001Dto;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 理财产品交易明细表批导出
 * @author yangxianjiu
 * @date 2022/04/14
 */
@Repository
public interface SvrChbProductsAssetCompareExportTaskMapper {

    /**
     * 查询
     * @param paramMap
     * @return
     */
    List<ChbProductsAssetCompare001Dto> selectForPage(Map<String, Object> paramMap);

    List<ChbProductsAssetCompare001Dto> queryAllList1(Map<String, Object> pageParam);

    /**
     * 统计数据行
     * @param paramMap
     * @return
     */
    int countExportNum(Map<String, Object> paramMap);

    /**
     * 批对象
     * @return
     */
    List<ChbSubinfoBatchLog> getBatchLog(@Param("taskId") String taskId);
}
