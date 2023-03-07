/*
 * 文件名：MstYieldCurveInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dao;

import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dto.ChbBranchNetworkDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 *网点信息映射表管理
 * 
 * @author wangzhao
 * @version 1.0 2022年5月16日
 * @see ChbBranchNetworkMapper
 * @since 1.0
 */
@Repository
public interface ChbBranchNetworkMapper {

    /**
     * 查询网点信息映射表管理
     * 
     * @param param 查询参数
     * @return 返回查询结果
     * @see
     * @since 1.0
     */
    List<ChbBranchNetworkDto> select(Map<String, Object> param);


    /**
     * 查询新增数据主键是否重复
     * @param dto
     * @return list
     */

    Integer getChbBranchNetworkByKey(ChbBranchNetworkDto dto);

    /**
     *
     * 导入（新建）
     * <功能详细描述>
     * @param list 导入数据
     * @return 导入结果
     */
    int insertChbBranchNetwork(@Param("list")List<ChbBranchNetworkDto> list);


    /**
     * 更新状态
     * @param list
     * @return
     */
    int updateStatus(@Param("list")List<ChbBranchNetworkDto> list);

    /**
     * 主键删除
     * @return
     */
    int deleteChbBranchNetworkByKey(@Param("branchNo") String branchNo,@Param("status") String status);

    /**
     * 查询记录条数
     * @param list
     * @return
     */
    int  selectChbBranchNetworkCount(@Param("list")List<Map<String, String>> list);

}
