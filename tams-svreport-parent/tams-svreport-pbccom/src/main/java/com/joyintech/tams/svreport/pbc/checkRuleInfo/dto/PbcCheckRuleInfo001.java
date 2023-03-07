package com.joyintech.tams.svreport.pbc.checkRuleInfo.dto;
import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.validate.PbcCheckRuleInfoValid;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.Date;
import java.util.List;

@PageId("PbcCheckRuleInfo002")
@ValidLogic(targetClass = PbcCheckRuleInfoValid.class, groups = {ValidateGroupEdit.class})
public class PbcCheckRuleInfo001 extends BaseEntity implements ITableRow {
    /**
     * 规则编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String ruleId;

    /**
     * 数据唯一标识
     */
    public String dataId;

    /**
     * 关联报表
     */
    private String relatedTable;

    /**
     * 关联报表格式化
     */
    private String relatedTableStr;

    public String getRelatedTableStr() {
        return relatedTableStr;
    }

    public void setRelatedTableStr(String relatedTableStr) {
        this.relatedTableStr = relatedTableStr;
    }

    /**
     * 更新时间
     */
    private String updateDataTime;

    public String getUpdateDataTime() {
        return updateDataTime;
    }

    public void setUpdateDataTime(String updateDataTime) {
        this.updateDataTime = updateDataTime;
    }

    /**
     * 更新时间格式化
     */
    private String updareTimeStr;

    public String getUpdareTimeStr() {
        return updareTimeStr;
    }

    public void setUpdareTimeStr(String updareTimeStr) {
        this.updareTimeStr = updareTimeStr;
    }

    /**
     * 规则大类
     */
    @DictFormat(DictCode.数据校验规则大类)
    public String ruleClassify;

    /**
     * 规则类型
     */
    @DictFormat(DictCode.数据校验规则类型)
    public String ruleType;

    /**
     * 参数（规则类型对应的参数）
     */
    public String ruleParam;

    /**
     * 报表/数据名称
     */
    public String tableDataName;

    /**
     * 报表/数据名称格式化
     */
    private String tableDataNameStr;

    /**
     * 检查字段名称
     */
    public String checkedFieldName;

    /**
     * 字段值
     */
    public String checkedFiledValue;

    /**
     * 规则方法
     */
    public String ruleMethod;

    /**
     * 规则内容
     */
    public String ruleContent;

    /**
     * 规则提示语
     */
    public String rulePrompt;

    /**
     * 是否启用：01启动，02禁用
     */
    @DictFormat(DictCode.数据校验规则状态)
    public String isstart;

    /**
     * 人行要素编码
     */
    public String pbcAssetCode;

    /**
     * 规则大类格式化
     */
    private String ruleClassifyStr;

    /**
     * 规则类型格式化
     */
    private String ruleTypeStr;

    /**
     * 启动状态格式化
     */
    private String isstartStr;

    /**
     * 规则标识格式化
     */
    private String ruleIdentifyStr;

    /**
     * 规则标识
     */
    @DictFormat(DictCode.数据校验规则标识)
    public String ruleIdentify;

    /**
     * 取得 规则编号
     * @return 规则编号
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 规则编号
     * @param ruleId 规则编号
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    /**
     * 取得 规则大类
     * @return 规则大类
     */
    public String getRuleClassify() {
        return ruleClassify;
    }

    /**
     * 设置 规则大类
     * @param ruleClassify 规则大类
     */
    public void setRuleClassify(String ruleClassify) {
        this.ruleClassify = ruleClassify == null ? null : ruleClassify.trim();
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
     * 取得 参数（规则类型对应的参数）
     * @return 参数（规则类型对应的参数）
     */
    public String getRuleParam() {
        return ruleParam;
    }

    /**
     * 设置 参数（规则类型对应的参数）
     * @param ruleParam 参数（规则类型对应的参数）
     */
    public void setRuleParam(String ruleParam) {
        this.ruleParam = ruleParam == null ? null : ruleParam.trim();
    }

    /**
     * 取得 报表/数据名称
     * @return 报表/数据名称
     */
    public String getTableDataName() {
        return tableDataName;
    }

    /**
     * 设置 报表/数据名称
     * @param tableDataName 报表/数据名称
     */
    public void setTableDataName(String tableDataName) {
        this.tableDataName = tableDataName == null ? null : tableDataName.trim();
    }

    /**
     * 取得 检查字段名称
     * @return 检查字段名称
     */
    public String getCheckedFieldName() {
        return checkedFieldName;
    }

    /**
     * 设置 检查字段名称
     * @param checkedFieldName 检查字段名称
     */
    public void setCheckedFieldName(String checkedFieldName) {
        this.checkedFieldName = checkedFieldName == null ? null : checkedFieldName.trim();
    }

    /**
     * 取得 字段值
     * @return 字段值
     */
    public String getCheckedFiledValue() {
        return checkedFiledValue;
    }

    /**
     * 设置 字段值
     * @param checkedFiledValue 字段值
     */
    public void setCheckedFiledValue(String checkedFiledValue) {
        this.checkedFiledValue = checkedFiledValue == null ? null : checkedFiledValue.trim();
    }

    /**
     * 取得 规则方法
     * @return 规则方法
     */
    public String getRuleMethod() {
        return ruleMethod;
    }

    /**
     * 设置 规则方法
     * @param ruleMethod 规则方法
     */
    public void setRuleMethod(String ruleMethod) {
        this.ruleMethod = ruleMethod == null ? null : ruleMethod.trim();
    }

    /**
     * 取得 规则内容
     * @return 规则内容
     */
    public String getRuleContent() {
        return ruleContent;
    }

    /**
     * 设置 规则内容
     * @param ruleContent 规则内容
     */
    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent == null ? null : ruleContent.trim();
    }

    /**
     * 取得 规则提示语
     * @return 规则提示语
     */
    public String getRulePrompt() {
        return rulePrompt;
    }

    /**
     * 设置 规则提示语
     * @param rulePrompt 规则提示语
     */
    public void setRulePrompt(String rulePrompt) {
        this.rulePrompt = rulePrompt == null ? null : rulePrompt.trim();
    }

    /**
     * 取得 是否启动：01启动，02禁用
     * @return 是否启动：01启动，02禁用
     */
    public String getIsstart() {
        return isstart;
    }

    /**
     * 设置 是否启动：01启动，02禁用
     * @param isstart 是否启动：01启动，02禁用
     */
    public void setIsstart(String isstart) {
        this.isstart = isstart == null ? null : isstart.trim();
    }

    /**
     * 取得 人行要素编码
     * @return 人行要素编码
     */
    public String getPbcAssetCode() {
        return pbcAssetCode;
    }

    /**
     * 设置 人行要素编码
     * @param pbcAssetCode 人行要素编码
     */
    public void setPbcAssetCode(String pbcAssetCode) {
        this.pbcAssetCode = pbcAssetCode == null ? null : pbcAssetCode.trim();
    }

    public String getRuleIdentify() {
        return ruleIdentify;
    }

    public void setRuleIdentify(String ruleIdentify) {
        this.ruleIdentify = ruleIdentify;
    }

    /**
     * 参数值
     */
    public String PARAMS01;

    public String getPARAMS01() {
        return PARAMS01;
    }

    public void setPARAMS01(String PARAMS01) {
        this.PARAMS01 = PARAMS01;
    }

    public String getPARAMS02() {
        return PARAMS02;
    }

    public void setPARAMS02(String PARAMS02) {
        this.PARAMS02 = PARAMS02;
    }

    public String getPARAMS03() {
        return PARAMS03;
    }

    public void setPARAMS03(String PARAMS03) {
        this.PARAMS03 = PARAMS03;
    }

    public String getPARAMS04() {
        return PARAMS04;
    }

    public void setPARAMS04(String PARAMS04) {
        this.PARAMS04 = PARAMS04;
    }

    public String PARAMS02;
    public String PARAMS03;
    public String PARAMS04;

    /**
     * 选中数据
     */
    public transient List<PbcCheckRuleInfo001> selectedItem;

    /**
     * 取得 选中数据
     *
     * @return 选中数据
     */
    public List<PbcCheckRuleInfo001> getSelectedItem() {
        return selectedItem;
    }
    /**
     * 设置 选中数据
     *
     * @param selectedItem 选中数据
     */
    public void setSelectedItem(List<PbcCheckRuleInfo001> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 操作类型
     */
    public String operateType;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 页面模式
     */
    public String pageModel;

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getRuleClassifyStr() {
        return ruleClassifyStr;
    }

    public void setRuleClassifyStr(String ruleClassifyStr) {
        this.ruleClassifyStr = ruleClassifyStr;
    }

    public String getRuleTypeStr() {
        return ruleTypeStr;
    }

    public void setRuleTypeStr(String ruleTypeStr) {
        this.ruleTypeStr = ruleTypeStr;
    }

    public String getIsstartStr() {
        return isstartStr;
    }

    public void setIsstartStr(String isstartStr) {
        this.isstartStr = isstartStr;
    }

    public String getRuleIdentifyStr() {
        return ruleIdentifyStr;
    }

    public void setRuleIdentifyStr(String ruleIdentifyStr) {
        this.ruleIdentifyStr = ruleIdentifyStr;
    }

    public String getTableDataNameStr() {
        return tableDataNameStr;
    }

    public void setTableDataNameStr(String tableDataNameStr) {
        this.tableDataNameStr = tableDataNameStr;
    }

    public String getRelatedTable() {
        return relatedTable;
    }

    public void setRelatedTable(String relatedTable) {
        this.relatedTable = relatedTable;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
}