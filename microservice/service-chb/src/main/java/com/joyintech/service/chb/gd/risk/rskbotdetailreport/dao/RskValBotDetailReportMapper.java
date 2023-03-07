package com.joyintech.service.chb.gd.risk.rskbotdetailreport.dao;

import com.joyintech.service.chb.gd.risk.rskbotdetailreport.dto.RskValBotDetailReportDto;

import java.util.List;
import java.util.Map;


public interface RskValBotDetailReportMapper {

    List<RskValBotDetailReportDto> queryForPage(Map<String, Object> pageParam);
}
