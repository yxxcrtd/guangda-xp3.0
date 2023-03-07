package com.joyintech.tams.svreport.interf.chb.prodinfo.dao;


import com.joyintech.tams.svreport.interf.chb.prodinfo.dto.SvrChbTargetInfo001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChbPrdTargetInterfMapper {


      void mergeChbPrdTargetData(@Param("list") List<SvrChbTargetInfo001Dto> list);

      List<SvrChbTargetInfo001Dto> queryProdId();

}
