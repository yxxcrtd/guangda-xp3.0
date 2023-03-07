package com.joyintech.service.chb.gd.risk.valtblimportlog.dao;

import com.joyintech.service.chb.gd.risk.valtblimportlog.dto.RskValTableImportLogDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RskValTableImportLogMapper {

    List<RskValTableImportLogDto> queryForPage(Map<String, Object> pageParam);

    void updateBatch(@Param("updateList") List<RskValTableImportLogDto> updateList);

    int selectNoendCountByTaskBeanName(String taskId);
}
