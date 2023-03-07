package com.joyintech.tams.svreport.submitbefore.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.generationcheck.dto.PbcReportGenerationCheck001Dto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

/**
 * 报送前检查
 *
 * @author yangxianjiu
 * @data 2022/11/29
 */
public class PbcSubmitBeforeCheck001Dto extends PbcReportGenerationCheck001Dto implements Serializable {

    private static final long serialVersionUID = 1921940973892308372L;
    /**
     * 关联报表
     */
    private String relevanceReport;

    /**
     * 取得 关联报表
     * @return 关联报表
     */
    public String getRelevanceReport() {
        return relevanceReport;
    }

    /**
     * 设置 关联报表
     * @param relevanceReport 关联报表
     */
    public void setRelevanceReport(String relevanceReport) {
        this.relevanceReport = relevanceReport == null ? null : relevanceReport.trim();
    }

    @Override
    public String getReportNameStr() {
        return SvrDataUtil.getNameFromDataDict(DictCode.大数据采集平台报表类型, this.getReportName());
    }

    @Override
    public String getRuleTypeStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.数据校验规则类型, this.getRuleType());
    }

    @Override
    public String getRuleCodeStr() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.数据校验规则标识, this.getRuleCode());
    }

    @Override
    public String getCheckDateStr() {
        return DateUtil.format(this.getCheckDate(), DateUtil.DEFAULT_DATETIME_FORMAT);
    }
}