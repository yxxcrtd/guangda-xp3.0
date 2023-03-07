package com.joyintech.tams.svreport.generationcheck.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.tams.common.base.entity.PbcReportGenerationCheckDetail;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.Date;
import java.util.List;

/**
 * 报表生成检查
 *
 * @author yangxianjiu
 * @data 2022/11/28
 */
public class PbcReportGenerationCheck001Dto extends BaseEntity {
    /**
     * UUID
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String dataUuid;

    /**
     * 数据唯一标识（业务主键）
     */
    private String dataId;

    /**
     * 规则编码
     */
    private String ruleId;

    /**
     * 人行要素编码
     */
    private String pbcElementCode;

    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 规则类型
     */
    private String ruleType;

    /**
     * 规则标识
     */
    private String ruleCode;

    /**
     * 规则详细内容
     */
    private String ruleDetails;

    /**
     * 检查时间
     */
    private Date checkDate;

    /**
     * 错误信息提示语
     */
    private String errMsg;

    /**
     * 错误数据
     */
    private String orgData;

    /**
     * 预览主键
     */
    private String flagUuid;

    /**
     * 检查结果
     */
    private String checkResult;

    /**
     * 检查时间格式化
     */
    private String checkDateStr;

    /**
     * 规则类型格式化
     */
    private String ruleTypeStr;

    /**
     * 报表名称格式化
     */
    private String reportNameStr;

    /**
     * 规则标识格式化
     */
    private String ruleCodeStr;

    //错误信息
    private String  errorInfo;
    //警告信息
    private String  warnInfo;

    private String uuid;

    /**
     * 明细 检查主键
     */
    private String checkUuid;

    /**
     * 检查结果标识
     */
    private String checkFlag;

    /**
     * 检查结果标识格式化
     */
    private String checkFlagStr;

    /**
     *
     */
    private String checkData;


    private Boolean backFlag;

    /**
     * 导入方式
     */
    private String  inFlag;

    /**
     * excel 行号
     *
     */
    private  String  rowNo;

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public String getInFlag() {
        return inFlag;
    }

    public void setInFlag(String inFlag) {
        this.inFlag = inFlag;
    }

    public Boolean getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(Boolean backFlag) {
        this.backFlag = backFlag;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getWarnInfo() {
        return warnInfo;
    }

    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo;
    }

    /**
     * 取得 UUID
     * @return UUID
     */
    public String getDataUuid() {
        return dataUuid;
    }

    /**
     * 设置 UUID
     * @param dataUuid UUID
     */
    public void setDataUuid(String dataUuid) {
        this.dataUuid = dataUuid == null ? null : dataUuid.trim();
    }

    /**
     * 取得 数据唯一标识（业务主键）
     * @return 数据唯一标识（业务主键）
     */
    public String getDataId() {
        return dataId;
    }

    /**
     * 设置 数据唯一标识（业务主键）
     * @param dataId 数据唯一标识（业务主键）
     */
    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    /**
     * 取得 规则编码
     * @return 规则编码
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 规则编码
     * @param ruleId 规则编码
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    /**
     * 取得 人行要素编码
     * @return 人行要素编码
     */
    public String getPbcElementCode() {
        return pbcElementCode;
    }

    /**
     * 设置 人行要素编码
     * @param pbcElementCode 人行要素编码
     */
    public void setPbcElementCode(String pbcElementCode) {
        this.pbcElementCode = pbcElementCode == null ? null : pbcElementCode.trim();
    }

    /**
     * 取得 报表名称
     * @return 报表名称
     */
    public String getReportName() {
        return reportName;
    }

    /**
     * 设置 报表名称
     * @param reportName 报表名称
     */
    public void setReportName(String reportName) {
        this.reportName = reportName == null ? null : reportName.trim();
    }

    /**
     * 取得 字段名称
     * @return 字段名称
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 设置 字段名称
     * @param fieldName 字段名称
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    /**
     * 取得 规则类型
     * @return 规则类型
     */
    public String getRuleType() {
        return ruleType;
    }

    /**
     * 设置 规则类型
     * @param ruleType 规则类型
     */
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType == null ? null : ruleType.trim();
    }

    /**
     * 取得 规则标识
     * @return 规则标识
     */
    public String getRuleCode() {
        return ruleCode;
    }

    /**
     * 设置 规则标识
     * @param ruleCode 规则标识
     */
    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    /**
     * 取得 规则详细内容
     * @return 规则详细内容
     */
    public String getRuleDetails() {
        return ruleDetails;
    }

    /**
     * 设置 规则详细内容
     * @param ruleDetails 规则详细内容
     */
    public void setRuleDetails(String ruleDetails) {
        this.ruleDetails = ruleDetails == null ? null : ruleDetails.trim();
    }

    /**
     * 取得 检查时间
     * @return 检查时间
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 设置 检查时间
     * @param checkDate 检查时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 取得 错误信息提示语
     * @return 错误信息提示语
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 错误信息提示语
     * @param errMsg 错误信息提示语
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }

    /**
     * 取得 错误数据
     * @return 错误数据
     */
    public String getOrgData() {
        return orgData;
    }

    /**
     * 设置 错误数据
     * @param orgData 错误数据
     */
    public void setOrgData(String orgData) {
        this.orgData = orgData == null ? null : orgData.trim();
    }

    public String getFlagUuid() {
        return flagUuid;
    }

    public void setFlagUuid(String flagUuid) {
        this.flagUuid = flagUuid;
    }

    public void setCheckDateStr(String checkDateStr) {
        this.checkDateStr = checkDateStr;
    }

    public String getCheckDateStr() {
        return this.checkDateStr;
    }

    public String getRuleTypeStr() {
        return ruleTypeStr;
    }

    public void setRuleTypeStr(String ruleTypeStr) {
        this.ruleTypeStr = ruleTypeStr;
    }

    public String getReportNameStr() {
        return reportNameStr;
    }

    public void setReportNameStr(String reportNameStr) {
        this.reportNameStr = reportNameStr;
    }

    public String getRuleCodeStr() {
        return ruleCodeStr;
    }

    public void setRuleCodeStr(String ruleCodeStr) {
        this.ruleCodeStr = ruleCodeStr;
    }

    public List<PbcReportGenerationCheckDetail> detailList;

    public List<PbcReportGenerationCheckDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<PbcReportGenerationCheckDetail> detailList) {
        this.detailList = detailList;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCheckUuid() {
        return checkUuid;
    }

    public void setCheckUuid(String checkUuid) {
        this.checkUuid = checkUuid;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getCheckData() {
        return checkData;
    }

    public void setCheckData(String checkData) {
        this.checkData = checkData;
    }

    public String getCheckFlagStr() {
        return checkFlagStr;
    }

    public void setCheckFlagStr(String checkFlagStr) {
        this.checkFlagStr = checkFlagStr;
    }
}