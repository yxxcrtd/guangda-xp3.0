package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInfoReportXbrl;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInfoReportXbrlMapper {
    int deleteByPrimaryKey(String reportNo);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(ChbInfoReportXbrl record);

    int updateByWorkflowId(ChbInfoReportXbrl record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(ChbInfoReportXbrl record);

    int insertSelective(ChbInfoReportXbrl record);

    int insertBasicByBasicInfo(ChbInfoReportXbrl record);

    ChbInfoReportXbrl selectByPrimaryKey(String reportNo);

    int updateByPrimaryKeySelective(ChbInfoReportXbrl record);

    int updateByPrimaryKey(ChbInfoReportXbrl record);
}