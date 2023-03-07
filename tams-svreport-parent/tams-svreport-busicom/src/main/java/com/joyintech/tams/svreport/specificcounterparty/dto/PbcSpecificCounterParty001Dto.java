package com.joyintech.tams.svreport.specificcounterparty.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.svreport.specificcounterparty.validate.PbcSpecificCounterPartyValid;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.List;


/**
 * 特定目的载体对手方信息维护
 * <p>
 * PageId 002页面共通检验
 *
 * @author yangxianjiu
 * @date 2022/11/1
 */
@PageId("PbcSpecificCounterParty002")
@ValidLogic(targetClass = PbcSpecificCounterPartyValid.class, groups = {ValidateGroupEdit.class})
public class PbcSpecificCounterParty001Dto extends BaseEntity {
    /**
     * 主键UUID
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String pbcUuid;

    /**
     * 交易对手机构编码
     */
    private String partyOrgCode;

    /**
     * 交易对手机构名称
     */
    private String partyOrgName;

    /**
     * 交易对手产品代码
     */
    private String partyProdId;

    /**
     * 交易对手产品名称
     */
    private String partyProdName;

    /**
     * 交易对手产品种类
     */
    @DictFormat(DictCode.人行_产品种类)
    private String partyProdType;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 资产负债项目
     */
    @DictFormat(DictCode.人行_资产负债项目)
    private String assetType;

    /**
     * 画面Id
     */
    private String pageId;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面模式
     */
    private String pageModel;

    /**
     * elementId
     */
    private String elementId;

    /**
     * 选中数据
     */
    private transient List<PbcSpecificCounterParty001Dto> selectedItem;

    private String assetType2;

    /**
     * 资产基本信息 人行一级分类
     */
    private String pbcAssetType1;

    /**
     * 资产基本信息 人行二级分类
     */
    private String pbcAssetType2;

    /**
     * 资产基本信息 人行三级分类
     */
    private String pbcAssetType3;


    /**
     * 取得 主键UUID
     *
     * @return 主键UUID
     */
    public String getPbcUuid() {
        return pbcUuid;
    }

    /**
     * 设置 主键UUID
     *
     * @param pbcUuid 主键UUID
     */
    public void setPbcUuid(String pbcUuid) {
        this.pbcUuid = pbcUuid == null ? null : pbcUuid.trim();
    }

    /**
     * 取得 交易对手机构编码
     *
     * @return 交易对手机构编码
     */
    public String getPartyOrgCode() {
        return partyOrgCode;
    }

    /**
     * 设置 交易对手机构编码
     *
     * @param partyOrgCode 交易对手机构编码
     */
    public void setPartyOrgCode(String partyOrgCode) {
        this.partyOrgCode = partyOrgCode == null ? null : partyOrgCode.trim();
    }

    /**
     * 取得 交易对手机构名称
     *
     * @return 交易对手机构名称
     */
    public String getPartyOrgName() {
        return partyOrgName;
    }

    /**
     * 设置 交易对手机构名称
     *
     * @param partyOrgName 交易对手机构名称
     */
    public void setPartyOrgName(String partyOrgName) {
        this.partyOrgName = partyOrgName == null ? null : partyOrgName.trim();
    }

    /**
     * 取得 交易对手产品代码
     *
     * @return 交易对手产品代码
     */
    public String getPartyProdId() {
        return partyProdId;
    }

    /**
     * 设置 交易对手产品代码
     *
     * @param partyProdId 交易对手产品代码
     */
    public void setPartyProdId(String partyProdId) {
        this.partyProdId = partyProdId == null ? null : partyProdId.trim();
    }

    /**
     * 取得 交易对手产品名称
     *
     * @return 交易对手产品名称
     */
    public String getPartyProdName() {
        return partyProdName;
    }

    /**
     * 设置 交易对手产品名称
     *
     * @param partyProdName 交易对手产品名称
     */
    public void setPartyProdName(String partyProdName) {
        this.partyProdName = partyProdName == null ? null : partyProdName.trim();
    }

    /**
     * 取得 交易对手产品种类
     *
     * @return 交易对手产品种类
     */
    public String getPartyProdType() {
        return partyProdType;
    }

    /**
     * 设置 交易对手产品种类
     *
     * @param partyProdType 交易对手产品种类
     */
    public void setPartyProdType(String partyProdType) {
        this.partyProdType = partyProdType == null ? null : partyProdType.trim();
    }

    /**
     * 取得 资产代码
     *
     * @return 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产代码
     *
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 资产负债项目
     *
     * @return 资产负债项目
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产负债项目
     *
     * @param assetType 资产负债项目
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 取得 选中数据
     *
     * @return 选中数据
     */
    public List<PbcSpecificCounterParty001Dto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置 选中数据
     *
     * @param selectedItem 选中数据
     */
    public void setSelectedItem(List<PbcSpecificCounterParty001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }

    public String getPbcAssetType1() {
        return pbcAssetType1;
    }

    public void setPbcAssetType1(String pbcAssetType1) {
        this.pbcAssetType1 = pbcAssetType1;
    }

    public String getPbcAssetType2() {
        return pbcAssetType2;
    }

    public void setPbcAssetType2(String pbcAssetType2) {
        this.pbcAssetType2 = pbcAssetType2;
    }

    public String getPbcAssetType3() {
        return pbcAssetType3;
    }

    public void setPbcAssetType3(String pbcAssetType3) {
        this.pbcAssetType3 = pbcAssetType3;
    }

    /**
     * 数据状态格式化
     *
     * @return 数据状态格式化
     */
    public String getStatusStr() {
        return getStatus() == null ? "" : SvrDataUtil.getZZDictCodeAndName(DictCode.维护数据状态, this.getStatus());
    }

    public String getPartyProdTypeStr() {
        return getPartyProdType() == null ? "" : this.getPartyProdType() + " " + SysDataUtil.getNameFromDataDict(DictCode.人行_产品种类, this.getPartyProdType());
    }

    public String getAssetTypeStr() {
        return getAssetType() == null ? "" : this.getAssetType() + " " + SysDataUtil.getNameFromDataDict(DictCode.人行_资产负债项目, this.getAssetType());
    }

}