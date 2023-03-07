package com.joyintech.tams.eiam.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

@Repository
public interface OperatorMapper {

    int updateSysParaValue(@Param("systemCode") String systemCode, @Param("paraCode") String paraCode, @Param("paramValue") String paramValue);

    String getSysParaValue(@Param("systemCode") String systemCode, @Param("paraCode") String paraCode);

}
