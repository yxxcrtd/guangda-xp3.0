package com.joyintech.tams.svreport.pbc.specificcounterparty.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.List;

/**
 * 特定目的载体对手方信息维护接入批
 *
 * @author yangxianjiu
 * @date 2022/11/7
 */
@TableName("PBC_SPE_COUNTER_PARTY")
public class SpcCounterPartyInterfDto extends BaseEntity {

    /**
     * 主键UUID
     */
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
    private String partyProdType;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 资产负债项目
     */
    private String assetType;

    /**
     * 人行资产一级分类
     */
    private String pbcAssetType1;

    /**
     * 人行资产二级分类
     */
    private String pbcAssetType2;

    /**
     * 人行资产三级分类
     */
    private String pbcAssetType3;

    /**
     * 中债资产/负债 类别
     */
    private String assetType2;


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

    public String getAssetType2() {
        return assetType2;
    }

    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2;
    }
}
