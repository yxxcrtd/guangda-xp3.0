package com.joyintech.tams.svreport.interf.chb.prodinfo.dao;

import com.joyintech.tams.svreport.interf.chb.prodinfo.dto.PbcSaleSvrreport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChbPbcSaleSvrreportMpper {

    /**
     * 合并数据（入库）
     * @param insertList
     * @return
     */
    int mergeInfoList(@Param("newDataList") List<PbcSaleSvrreport> insertList);


}
