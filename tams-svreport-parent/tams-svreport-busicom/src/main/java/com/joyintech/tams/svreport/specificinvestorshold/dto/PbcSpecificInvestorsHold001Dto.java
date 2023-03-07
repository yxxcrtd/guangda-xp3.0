package com.joyintech.tams.svreport.specificinvestorshold.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.svreport.specificinvestorshold.validate.PbcSpecificInvestorsHoldValid;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 特定目的载体持有信息维护(投资者)
 *
 * @author yangxianjiu
 * @date 2022/11/4
 */
@PageId("PbcSpecificInvestorsHold002")
@ValidLogic(targetClass = PbcSpecificInvestorsHoldValid.class, groups = {ValidateGroupEdit.class})
public class PbcSpecificInvestorsHold001Dto extends BaseEntity {

    private DecimalFormat df2 = new DecimalFormat("0.00");

    private DecimalFormat df5 = new DecimalFormat("0.00");

    /**
     * 主键
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    private String uuid;

    /**
     * 内部产品代码
     */
    private String account;

    /**
     * 识别标识
     */
    private String indentifyMark;

    /**
     * 人行产品代码
     */
    private String pbcProdId;

    /**
     * 投资者名称
     */
    private String holderName;

    /**
     * 投资者类别
     */
    @DictFormat(DictCode.投资者类别)
    private String holderType;

    /**
     * 持有日期
     */
    private Date holdDate;

    /**
     * 币种
     */
    @DictFormat(DictCode.币种_中债)
    private String ccy;

    /**
     * 持有份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal qty;

    /**
     * 持有金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal amount;

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
    private transient List<PbcSpecificInvestorsHold001Dto> selectedItem;

    /**
     * 取得 主键
     *
     * @return 主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 主键
     *
     * @param uuid 主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 产品代码
     *
     * @return 产品代码
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置 产品代码
     *
     * @param account 产品代码
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 取得 识别标识
     *
     * @return 识别标识
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 识别标识
     *
     * @param indentifyMark 识别标识
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
    }

    public String getPbcProdId() {
        return pbcProdId;
    }

    public void setPbcProdId(String pbcProdId) {
        this.pbcProdId = pbcProdId;
    }

    /**
     * 取得 投资者名称
     *
     * @return 投资者名称
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * 设置 投资者名称
     *
     * @param holderName 投资者名称
     */
    public void setHolderName(String holderName) {
        this.holderName = holderName == null ? null : holderName.trim();
    }

    /**
     * 取得 投资者类别
     *
     * @return 投资者类别
     */
    public String getHolderType() {
        return holderType;
    }

    /**
     * 设置 投资者类别
     *
     * @param holderType 投资者类别
     */
    public void setHolderType(String holderType) {
        this.holderType = holderType == null ? null : holderType.trim();
    }

    /**
     * 取得 持有日期
     *
     * @return 持有日期
     */
    public Date getHoldDate() {
        return holdDate;
    }

    /**
     * 设置 持有日期
     *
     * @param holdDate 持有日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    /**
     * 取得 币种
     *
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     *
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 持有份额
     *
     * @return 持有份额
     */
    public BigDecimal getQty() {
        return qty;
    }

    /**
     * 设置 持有份额
     *
     * @param qty 持有份额
     */
    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    /**
     * 取得 持有金额
     *
     * @return 持有金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 持有金额
     *
     * @param amount 持有金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public List<PbcSpecificInvestorsHold001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<PbcSpecificInvestorsHold001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 格式化数据状态
     *
     * @return
     */
    public String getStatusStr() {
        return getStatus() == null ? "" : SvrDataUtil.getZZDictCodeAndName(DictCode.维护数据状态, this.getStatus());
    }

    public String getCcyStr() {
        return getCcy() == null ? "" : this.getCcy() + " " + SysDataUtil.getNameFromDataDict(DictCode.币种_中债, this.getCcy());
    }

    public String getHolderTypeStr() {
        return getHolderType() == null ? "" : this.getHolderType() + " " + SysDataUtil.getNameFromDataDict(DictCode.投资者类别, this.getHolderType());
    }

    public String getHoldDateStr() {
        return DateUtil.format(getHoldDate());
    }

    public String getQtyStr(){
        return getQty() == null ? "" : df5.format(getQty());
    }

    public String getAmountStr(){
        return getAmount() == null ? "" : df2.format(getAmount());
    }


}