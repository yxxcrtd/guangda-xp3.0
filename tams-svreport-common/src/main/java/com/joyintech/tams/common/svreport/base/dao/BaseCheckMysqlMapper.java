package com.joyintech.tams.common.svreport.base.dao;

import com.joyintech.tams.common.svreport.base.dto.BaseCheckMysqlDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-03-31 15:00
 */
@Repository
public interface BaseCheckMysqlMapper {
    List<BaseCheckMysqlDto> select(@Param("schema") String schema,@Param("tableName") String tableName);

    List<Map<String,Object>>  selectTableRecodeList(@Param("permkeylist") List<String> permkeylist, @Param("list") List<Map<String,Object>> list, @Param("tableName") String tableName);

    void  updateTableStatus(@Param("permkeylist") List<String> permkeylist,@Param("list") List<Map<String,Object>> list, @Param("tableName") String tableName);
}
