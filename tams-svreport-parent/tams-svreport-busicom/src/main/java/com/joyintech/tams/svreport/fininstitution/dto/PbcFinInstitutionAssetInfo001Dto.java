package com.joyintech.tams.svreport.fininstitution.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.svreport.fininstitution.validate.PbcFinInstitutionAssetInfoValid;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.math.BigDecimal;
import java.util.List;

/**
 * 表内资产数据维护
 *
 * @author yangxianjiu
 * @date 2022/11/23
 */
@PageId("PbcFinInstitutionAssetInfo002")
@ValidLogic(targetClass = PbcFinInstitutionAssetInfoValid.class, groups = {ValidateGroupEdit.class})
public class PbcFinInstitutionAssetInfo001Dto extends BaseEntity {
    /**
     * 统计年月
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String termId;

    /**
     * UUID
     */
    private String pbcUuid;

    /**
     * 金融机构表内资产余额（本外币）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finAssetBalance;

    /**
     * 金融机构表内金融资产余额（本外币）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal finInsAssetBalance;

    List<PbcFinInstitutionAssetInfo001Dto> selectedItem;

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
     * 取得 统计年月
     * @return 统计年月
     */
    public String getTermId() {
        return termId;
    }

    /**
     * 设置 统计年月
     * @param termId 统计年月
     */
    public void setTermId(String termId) {
        this.termId = termId == null ? null : termId.trim();
    }

    /**
     * 取得 UUID
     * @return UUID
     */
    public String getPbcUuid() {
        return pbcUuid;
    }

    /**
     * 设置 UUID
     * @param pbcUuid UUID
     */
    public void setPbcUuid(String pbcUuid) {
        this.pbcUuid = pbcUuid == null ? null : pbcUuid.trim();
    }

    /**
     * 取得 金融机构表内资产余额（本外币）
     * @return 金融机构表内资产余额（本外币）
     */
    public BigDecimal getFinAssetBalance() {
        return finAssetBalance;
    }

    /**
     * 设置 金融机构表内资产余额（本外币）
     * @param finAssetBalance 金融机构表内资产余额（本外币）
     */
    public void setFinAssetBalance(BigDecimal finAssetBalance) {
        this.finAssetBalance = finAssetBalance;
    }

    /**
     * 取得 金融机构表内金融资产余额（本外币）
     * @return 金融机构表内金融资产余额（本外币）
     */
    public BigDecimal getFinInsAssetBalance() {
        return finInsAssetBalance;
    }

    /**
     * 设置 金融机构表内金融资产余额（本外币）
     * @param finInsAssetBalance 金融机构表内金融资产余额（本外币）
     */
    public void setFinInsAssetBalance(BigDecimal finInsAssetBalance) {
        this.finInsAssetBalance = finInsAssetBalance;
    }

    public List<PbcFinInstitutionAssetInfo001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<PbcFinInstitutionAssetInfo001Dto> selectedItem) {
        this.selectedItem = selectedItem;
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
}