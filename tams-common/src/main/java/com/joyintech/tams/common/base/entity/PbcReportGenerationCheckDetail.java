package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PbcReportGenerationCheckDetail extends BaseEntity {
    /**
     * 主键
     */
    private String uuid;

    /**
     * 数据检查uuid
     */
    private String checkUuid;

    /**
     * 结果错误信息
     */
    private String checkResult;

    /**
     * 检查状态- 0-成功,1-失败
     */
    private String checkFlag;

    /**
     * 错误数据-值
     */
    private String checkData;
    /**
     * 规则编码
     */
    private String ruleId;


    private String ruleClassify;


    private String  dataKey;

    private String ruleIdentify;

    private String  befCheckDataId;

    public String getRuleIdentify() {
        return ruleIdentify;
    }

    public void setRuleIdentify(String ruleIdentify) {
        this.ruleIdentify = ruleIdentify;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getRuleClassify() {
        return ruleClassify;
    }

    public void setRuleClassify(String ruleClassify) {
        this.ruleClassify = ruleClassify;
    }



    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * 取得 主键
     * @return 主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 主键
     * @param uuid 主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 数据检查uuid
     * @return 数据检查uuid
     */
    public String getCheckUuid() {
        return checkUuid;
    }

    /**
     * 设置 数据检查uuid
     * @param checkUuid 数据检查uuid
     */
    public void setCheckUuid(String checkUuid) {
        this.checkUuid = checkUuid == null ? null : checkUuid.trim();
    }

    /**
     * 取得 结果错误信息
     * @return 结果错误信息
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * 设置 结果错误信息
     * @param checkResult 结果错误信息
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    /**
     * 取得 检查状态- 0-成功,1-失败
     * @return 检查状态- 0-成功,1-失败
     */
    public String getCheckFlag() {
        return checkFlag;
    }

    /**
     * 设置 检查状态- 0-成功,1-失败
     * @param checkFlag 检查状态- 0-成功,1-失败
     */
    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag == null ? null : checkFlag.trim();
    }

    /**
     * 取得 错误数据-值
     * @return 错误数据-值
     */
    public String getCheckData() {
        return checkData;
    }

    /**
     * 设置 错误数据-值
     * @param checkData 错误数据-值
     */
    public void setCheckData(String checkData) {
        this.checkData = checkData == null ? null : checkData.trim();
    }

    public String getBefCheckDataId() {
        return befCheckDataId;
    }

    public void setBefCheckDataId(String befCheckDataId) {
        this.befCheckDataId = befCheckDataId;
    }
}