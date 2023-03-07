package com.joyintech.tams.svreport.pbc.checkRuleInfo.dao;

import com.joyintech.tams.svreport.pbc.checkRuleInfo.dto.PbcCheckRuleInfo001;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PbcCheckRuleInfo001Mapper {
    int insert(PbcCheckRuleInfo001 record);

    int insertSelective(PbcCheckRuleInfo001 record);

    int insertBasicByBasicInfo(PbcCheckRuleInfo001 record);

//    @Cacheable("pbc_check_rule_info_001")
    List<PbcCheckRuleInfo001> searchPage(Map<String,Object> Params);

    int deleteByRuleId(@Param("ruleId") String ruleId);

    int updateByRuleId(PbcCheckRuleInfo001 record);

    PbcCheckRuleInfo001 selectByPrimaryKey(@Param("ruleId") String ruleId);
}