package com.joyintech.tams.report.gd.report.chbfinanceproductssub.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

public class ChbFinanceProductsSub001Dto extends BaseEntity {

    /**
     * 主键uuid
     */
    private String registSeqno;

    /**
     * 数据批次
     */
    private String statusBatch;

    /**
     * 理财产品编号
     */
    private String prodId;

    /**
     * 理财产品名称
     */
    private String prodName;

    /**
     * 表内表外标识代码信息
     */
    private String surfaceInOut;

    /**
     * 产品品牌
     */
    private String prodBrand;

    /**
     * 产品期次
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prodStage;


    /**
     * 募集币种代码
     */
    private String raiseCcy;

    /**
     * 发行规模金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal raiseAmtPlan;

    /**
     * 实际募集金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalAmt;

    /**
     * 认购起点金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleAmtStr;

    /**
     * 募集起始日期
     */
    private Date raiseVdate;

    /**
     * 募集结束日期
     */
    private Date raiseEnddate;

    /**
     * 兑付本金币种代码
     */
    private String cashPrinCcy;

    /**
     * 兑付收益币种代码
     */
    private String cashProfCcy;

    /**
     * 产品起始日期
     */
    private Date vdate;

    /**
     * 产品终止日期
     */
    private Date mdate;

    /**
     * 预期最低收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowYield;

    /**
     * 预期最高收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highYield;

    /**
     * 结构化产品标志
     */
    private String isConstracte;

    /**
     * 理财产品风险等级代码信息
     */
    private String riskLevel;

    /**
     * 产品管理模式代码信息
     */
    private String prodManagePattern;

    /**
     * 理财产品合作模式代码信息
     */
    private String contMode;

    /**
     * 资产收益类型代码信息
     */
    private String profitType;

    /**
     * 产品销售分行名称
     */
    private String prodSalebankName;

    /**
     * 运作方式代码信息
     */
    private String operateMode;

    /**
     * 投资模式代码信息
     */
    private String cmmode;

    /**
     * 提前终止权标志
     */
    private String termRightFlag;

    /**
     * 产品合作方名称
     */
    private String cooperationName;

    /**
     * 投资类型代码信息
     */
    private String invAssetType;

    /**
     * 客户风险承受能力代码信息
     */
    private String customerRiskLevel;

    /**
     * 产品专属客户类型代码信息
     */
    private String customerType;

    /**
     * 投资区域代码信息
     */
    private String investArae;


    /**
     * 备注
     */
    private String remark;

    /**
     * 银行标识号
     */
    private String bankMark;

    /**
     * 数据时点
     */
    private Date dateStatus;

    /**
     * 组别
     */
    private String groupId;

    /**
     * 机构代码
     */
    private String orgCode;

    private String raiseVdateStr;

    private String raiseEnddateStr;

    private String vdateStr;

    private String mdateStr;

    private String dateStatusStr;

    private String contModeStr;

    private String profitTypeStr;

    private String operateModeStr;

    private String cmmodeStr;

    private String invAssetTypeStr;

    private String customerTypeStr;

    private String investAraeStr;

    public void setContModeStr(String contModeStr) {
        this.contModeStr = contModeStr;
    }

    public void setProfitTypeStr(String profitTypeStr) {
        this.profitTypeStr = profitTypeStr;
    }

    public void setOperateModeStr(String operateModeStr) {
        this.operateModeStr = operateModeStr;
    }

    public void setCmmodeStr(String cmmodeStr) {
        this.cmmodeStr = cmmodeStr;
    }

    public void setInvAssetTypeStr(String invAssetTypeStr) {
        this.invAssetTypeStr = invAssetTypeStr;
    }

    public void setCustomerTypeStr(String customerTypeStr) {
        this.customerTypeStr = customerTypeStr;
    }

    public void setInvestAraeStr(String investAraeStr) {
        this.investAraeStr = investAraeStr;
    }

    public void setRaiseVdateStr(String raiseVdateStr) {
        this.raiseVdateStr = raiseVdateStr;
    }

    public void setRaiseEnddateStr(String raiseEnddateStr) {
        this.raiseEnddateStr = raiseEnddateStr;
    }

    public void setVdateStr(String vdateStr) {
        this.vdateStr = vdateStr;
    }

    public void setMdateStr(String mdateStr) {
        this.mdateStr = mdateStr;
    }

    public void setDateStatusStr(String dateStatusStr) {
        this.dateStatusStr = dateStatusStr;
    }

    /**
     * 取得 主键uuid
     * @return 主键uuid
     */
    public String getRegistSeqno() {
        return registSeqno;
    }

    /**
     * 设置 主键uuid
     * @param registSeqno 主键uuid
     */
    public void setRegistSeqno(String registSeqno) {
        this.registSeqno = registSeqno == null ? null : registSeqno.trim();
    }

    /**
     * 取得 数据批次
     * @return 数据批次
     */
    public String getStatusBatch() {
        return statusBatch;
    }

    /**
     * 设置 数据批次
     * @param statusBatch 数据批次
     */
    public void setStatusBatch(String statusBatch) {
        this.statusBatch = statusBatch == null ? null : statusBatch.trim();
    }

    /**
     * 取得 理财产品编号
     * @return 理财产品编号
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 理财产品编号
     * @param prodId 理财产品编号
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 理财产品名称
     * @return 理财产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 理财产品名称
     * @param prodName 理财产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 取得 表内表外标识代码信息
     * @return 表内表外标识代码信息
     */
    public String getSurfaceInOut() {
        return surfaceInOut;
    }

    /**
     * 设置 表内表外标识代码信息
     * @param surfaceInOut 表内表外标识代码信息
     */
    public void setSurfaceInOut(String surfaceInOut) {
        this.surfaceInOut = surfaceInOut == null ? null : surfaceInOut.trim();
    }

    /**
     * 取得 产品品牌
     * @return 产品品牌
     */
    public String getProdBrand() {
        return prodBrand;
    }

    /**
     * 设置 产品品牌
     * @param prodBrand 产品品牌
     */
    public void setProdBrand(String prodBrand) {
        this.prodBrand = prodBrand == null ? null : prodBrand.trim();
    }

    /**
     * 取得 产品期次
     * @return 产品期次
     */
    public BigDecimal getProdStage() {
        return prodStage;
    }

    /**
     * 设置 产品期次
     * @param prodStage 产品期次
     */
    public void setProdStage(BigDecimal prodStage) {
        this.prodStage = prodStage;
    }

    /**
     * 取得 募集币种代码
     * @return 募集币种代码
     */
    public String getRaiseCcy() {
        return raiseCcy;
    }

    /**
     * 设置 募集币种代码
     * @param raiseCcy 募集币种代码
     */
    public void setRaiseCcy(String raiseCcy) {
        this.raiseCcy = raiseCcy == null ? null : raiseCcy.trim();
    }

    /**
     * 取得 发行规模金额
     * @return 发行规模金额
     */
    public BigDecimal getRaiseAmtPlan() {
        return raiseAmtPlan;
    }

    /**
     * 设置 发行规模金额
     * @param raiseAmtPlan 发行规模金额
     */
    public void setRaiseAmtPlan(BigDecimal raiseAmtPlan) {
        this.raiseAmtPlan = raiseAmtPlan;
    }

    /**
     * 取得 实际募集金额
     * @return 实际募集金额
     */
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    /**
     * 设置 实际募集金额
     * @param totalAmt 实际募集金额
     */
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    /**
     * 取得 认购起点金额
     * @return 认购起点金额
     */
    public BigDecimal getSaleAmtStr() {
        return saleAmtStr;
    }

    /**
     * 设置 认购起点金额
     * @param saleAmtStr 认购起点金额
     */
    public void setSaleAmtStr(BigDecimal saleAmtStr) {
        this.saleAmtStr = saleAmtStr;
    }

    /**
     * 取得 募集起始日期
     * @return 募集起始日期
     */
    public Date getRaiseVdate() {
        return raiseVdate;
    }

    public String getRaiseVdateStr() {
        return DateUtil.format(this.getRaiseVdate(),DateUtil.DEFAULT_OTHER_FORMAT);
    }

    /**
     * 设置 募集起始日期
     * @param raiseVdate 募集起始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseVdate(Date raiseVdate) {
        this.raiseVdate = raiseVdate;
    }


    /**
     * 取得 募集结束日期
     * @return 募集结束日期
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }
    public String getRaiseEnddateStr() {
        return DateUtil.format(this.getRaiseEnddate(),DateUtil.DEFAULT_OTHER_FORMAT);
    }

    /**
     * 设置 募集结束日期
     * @param raiseEnddate 募集结束日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 取得 兑付本金币种代码
     * @return 兑付本金币种代码
     */
    public String getCashPrinCcy() {
        return cashPrinCcy;
    }

    /**
     * 设置 兑付本金币种代码
     * @param cashPrinCcy 兑付本金币种代码
     */
    public void setCashPrinCcy(String cashPrinCcy) {
        this.cashPrinCcy = cashPrinCcy == null ? null : cashPrinCcy.trim();
    }

    /**
     * 取得 兑付收益币种代码
     * @return 兑付收益币种代码
     */
    public String getCashProfCcy() {
        return cashProfCcy;
    }

    /**
     * 设置 兑付收益币种代码
     * @param cashProfCcy 兑付收益币种代码
     */
    public void setCashProfCcy(String cashProfCcy) {
        this.cashProfCcy = cashProfCcy == null ? null : cashProfCcy.trim();
    }

    /**
     * 取得 产品起始日期
     * @return 产品起始日期
     */
    public Date getVdate() {
        return vdate;
    }
    public String getVdateStr() {
        return DateUtil.format(this.getVdate(),DateUtil.DEFAULT_OTHER_FORMAT);
    }

    /**
     * 设置 产品起始日期
     * @param vdate 产品起始日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 产品终止日期
     * @return 产品终止日期
     */
    public Date getMdate() {
        return mdate;
    }

    public String getMdateStr() {
        return DateUtil.format(this.getMdate(),DateUtil.DEFAULT_OTHER_FORMAT);
    }

    /**
     * 设置 产品终止日期
     * @param mdate 产品终止日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 预期最低收益率
     * @return 预期最低收益率
     */
    public BigDecimal getLowYield() {
        return lowYield;
    }

    /**
     * 设置 预期最低收益率
     * @param lowYield 预期最低收益率
     */
    public void setLowYield(BigDecimal lowYield) {
        this.lowYield = lowYield;
    }

    /**
     * 取得 预期最高收益率
     * @return 预期最高收益率
     */
    public BigDecimal getHighYield() {
        return highYield;
    }


    /**
     * 设置 预期最高收益率
     * @param highYield 预期最高收益率
     */
    public void setHighYield(BigDecimal highYield) {
        this.highYield = highYield;
    }

    /**
     * 取得 结构化产品标志
     * @return 结构化产品标志
     */
    public String getIsConstracte() {
        return isConstracte;
    }

    /**
     * 设置 结构化产品标志
     * @param isConstracte 结构化产品标志
     */
    public void setIsConstracte(String isConstracte) {
        this.isConstracte = isConstracte == null ? null : isConstracte.trim();
    }

    /**
     * 取得 理财产品风险等级代码信息
     * @return 理财产品风险等级代码信息
     */
    public String getRiskLevel() {
        return riskLevel;
    }


    /**
     * 设置 理财产品风险等级代码信息
     * @param riskLevel 理财产品风险等级代码信息
     */
    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel == null ? null : riskLevel.trim();
    }

    /**
     * 取得 产品管理模式代码信息
     * @return 产品管理模式代码信息
     */
    public String getProdManagePattern() {
        return prodManagePattern;
    }

    /**
     * 设置 产品管理模式代码信息
     * @param prodManagePattern 产品管理模式代码信息
     */
    public void setProdManagePattern(String prodManagePattern) {
        this.prodManagePattern = prodManagePattern == null ? null : prodManagePattern.trim();
    }

    /**
     * 取得 理财产品合作模式代码信息
     * @return 理财产品合作模式代码信息
     */
    public String getContMode() {
        return contMode;
    }

    public String getContModeStr() {
        return this.getContMode()+" "+SysDataUtil.getNameFromDataDict(DictCode.合作模式, this.getContMode());
    }

    /**
     * 设置 理财产品合作模式代码信息
     * @param contMode 理财产品合作模式代码信息
     */
    public void setContMode(String contMode) {
        this.contMode = contMode == null ? null : contMode.trim();
    }

    /**
     * 取得 资产收益类型代码信息
     * @return 资产收益类型代码信息
     */
    public String getProfitType() {
        return profitType;
    }

    public String getProfitTypeStr() {
        return this.getProfitType()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品收益标识, this.getProfitType());
    }

    /**
     * 设置 资产收益类型代码信息
     * @param profitType 资产收益类型代码信息
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 产品销售分行名称
     * @return 产品销售分行名称
     */
    public String getProdSalebankName() {
        return prodSalebankName;
    }

    /**
     * 设置 产品销售分行名称
     * @param prodSalebankName 产品销售分行名称
     */
    public void setProdSalebankName(String prodSalebankName) {
        this.prodSalebankName = prodSalebankName == null ? null : prodSalebankName.trim();
    }

    /**
     * 取得 运作方式代码信息
     * @return 运作方式代码信息
     */
    public String getOperateMode() {
        return operateMode;
    }

    public String getOperateModeStr() {
        return this.getOperateMode()+" "+SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, this.getOperateMode());
    }

    /**
     * 设置 运作方式代码信息
     * @param operateMode 运作方式代码信息
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 取得 投资模式代码信息
     * @return 投资模式代码信息
     */
    public String getCmmode() {
        return cmmode;
    }

    public String getCmmodeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品投资模式, this.getCmmode());
    }


    /**
     * 设置 投资模式代码信息
     * @param cmmode 投资模式代码信息
     */
    public void setCmmode(String cmmode) {
        this.cmmode = cmmode == null ? null : cmmode.trim();
    }

    /**
     * 取得 提前终止权标志
     * @return 提前终止权标志
     */
    public String getTermRightFlag() {
        return termRightFlag;
    }

    /**
     * 设置 提前终止权标志
     * @param termRightFlag 提前终止权标志
     */
    public void setTermRightFlag(String termRightFlag) {
        this.termRightFlag = termRightFlag == null ? null : termRightFlag.trim();
    }

    /**
     * 取得 产品合作方名称
     * @return 产品合作方名称
     */
    public String getCooperationName() {
        return cooperationName;
    }

    /**
     * 设置 产品合作方名称
     * @param cooperationName 产品合作方名称
     */
    public void setCooperationName(String cooperationName) {
        this.cooperationName = cooperationName == null ? null : cooperationName.trim();
    }

    /**
     * 取得 投资类型代码信息
     * @return 投资类型代码信息
     */
    public String getInvAssetType() {
        return invAssetType;
    }

    public String getInvAssetTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.投资类型信息, this.getInvAssetType());
    }

    /**
     * 设置 投资类型代码信息
     * @param invAssetType 投资类型代码信息
     */
    public void setInvAssetType(String invAssetType) {
        this.invAssetType = invAssetType == null ? null : invAssetType.trim();
    }

    /**
     * 取得 客户风险承受能力代码信息
     * @return 客户风险承受能力代码信息
     */
    public String getCustomerRiskLevel() {
        return customerRiskLevel;
    }

    /**
     * 设置 客户风险承受能力代码信息
     * @param customerRiskLevel 客户风险承受能力代码信息
     */
    public void setCustomerRiskLevel(String customerRiskLevel) {
        this.customerRiskLevel = customerRiskLevel == null ? null : customerRiskLevel.trim();
    }

    /**
     * 取得 产品专属客户类型代码信息
     * @return 产品专属客户类型代码信息
     */
    public String getCustomerType() {
        return customerType;
    }

    public String getCustomerTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.专属客户类型, this.getCustomerType());
    }

    /**
     * 设置 产品专属客户类型代码信息
     * @param customerType 产品专属客户类型代码信息
     */
    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    /**
     * 取得 投资区域代码信息
     * @return 投资区域代码信息
     */
    public String getInvestArae() {
        return investArae;
    }

    public String getInvestAraeStr() {
        return this.getInvestArae()+" "+SysDataUtil.getNameFromDataDict(DictCode.资金投向地区, this.getInvestArae());
    }

    /**
     * 设置 投资区域代码信息
     * @param investArae 投资区域代码信息
     */
    public void setInvestArae(String investArae) {
        this.investArae = investArae == null ? null : investArae.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 银行标识号
     * @return 银行标识号
     */
    public String getBankMark() {
        return bankMark;
    }

    /**
     * 设置 银行标识号
     * @param bankMark 银行标识号
     */
    public void setBankMark(String bankMark) {
        this.bankMark = bankMark == null ? null : bankMark.trim();
    }

    /**
     * 取得 数据时点
     * @return 数据时点
     */
    public Date getDateStatus() {
        return dateStatus;
    }

    public String getDateStatusStr() {
        return DateUtil.format(this.getDateStatus(),DateUtil.DEFAULT_OTHER_FORMAT);
    }

    /**
     * 设置 数据时点
     * @param dateStatus 数据时点
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDateStatus(Date dateStatus) {
        this.dateStatus = dateStatus;
    }

    /**
     * 取得 组别
     * @return 组别
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置 组别
     * @param groupId 组别
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 取得 机构代码
     * @return 机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置 机构代码
     * @param orgCode 机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    // ==================== 编码转名称 开始 ====================

    /**
     * 取得 结构化产品标志
     * @return 结构化产品标志
     */
    public String getIsConstracteName() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getIsConstracte());
    }

    /**
     * 取得 理财产品风险等级代码信息
     * @return 理财产品风险等级代码信息
     */
    public String getRiskLevelName() {
        // TODO 未知字典
        return this.getRiskLevel();
    }

    /**
     * 取得 理财产品合作模式代码信息
     * @return 理财产品合作模式代码信息
     */
    public String getContModeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.合作模式, this.getContMode());
    }

    /**
     * 取得 资产收益类型代码信息
     * @return 资产收益类型代码信息
     */
    public String getProfitTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品收益标识, this.getProfitType());
    }

    /**
     * 取得 运作方式代码信息
     * @return 运作方式代码信息
     */
    public String getOperateModeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, this.getOperateMode());
    }

    /**
     * 取得 投资模式代码信息
     * @return 投资模式代码信息
     */
    public String getCmmodeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.产品投资模式, this.getCmmode());
    }

    // ==================== 编码转名称 结束 ====================

    /**
     * 按照格式构建一条记录
     * @return
     */
    public String buildReportStr(){
        String separator = "|+|";

        StringBuffer sb = new StringBuffer();
        sb.append(nullCheck(getProdId())).append(separator);
        sb.append(nullCheck(getProdName())).append(separator);
        sb.append(nullCheck(getSurfaceInOut())).append(separator);
        sb.append(nullCheck(getProdBrand())).append(separator);
        sb.append(nullCheck(getProdStage())).append(separator);
        sb.append(nullCheck(getRaiseCcy())).append(separator);
        sb.append(nullCheck(getRaiseAmtPlan())).append(separator);
        sb.append(nullCheck(getTotalAmt())).append(separator);
        sb.append(nullCheck(getSaleAmtStr())).append(separator);
        sb.append(nullCheck(getRaiseVdateStr())).append(separator);
        sb.append(nullCheck(getRaiseEnddateStr())).append(separator);
        sb.append(nullCheck(getCashPrinCcy())).append(separator);
        sb.append(nullCheck(getCashProfCcy())).append(separator);
        sb.append(nullCheck(getVdateStr())).append(separator);
        sb.append(nullCheck(getMdateStr())).append(separator);
        sb.append(nullCheck(getLowYield())).append(separator);
        sb.append(nullCheck(getHighYield())).append(separator);
        sb.append(nullCheck(getIsConstracteName())).append(separator); // 结构化产品标志(金审为中文)
        sb.append(nullCheck(getRiskLevel())).append(separator); // 理财产品风险等级代码信息(金审为中文)
        sb.append(nullCheck(getProdManagePattern())).append(separator);
        sb.append(nullCheck(getContModeName())).append(separator); // 理财产品合作模式代码信息(金审为中文)
        sb.append(nullCheck(getProfitTypeName())).append(separator); // 资产收益类型代码信息(金审为中文)
        sb.append(nullCheck(getProdSalebankName())).append(separator);
        sb.append(nullCheck(getOperateModeName())).append(separator); // 运作方式代码信息(金审为中文)
        sb.append(nullCheck(getCmmodeName())).append(separator); // 投资模式代码信息(金审为中文)
        sb.append(nullCheck(getTermRightFlag())).append(separator);
        sb.append(nullCheck(getCooperationName())).append(separator);
        sb.append(nullCheck(getInvAssetType())).append(separator); // 投资类型代码信息
        sb.append(nullCheck(getCustomerRiskLevel())).append(separator);
        sb.append(nullCheck(getCustomerType())).append(separator); // 产品专属客户类型代码信息
        sb.append(nullCheck(getInvestArae())).append(separator); // 投资区域代码信息
        sb.append(nullCheck(getRemark())).append(separator);
        sb.append(nullCheck(getBankMark())).append(separator);
        sb.append(nullCheck(getDateStatus())).append("|"); // 数据时点

        return sb.toString();
    }

    /**
     * 空值检查
     * @param obj
     * @return
     */
    public String nullCheck(Object obj){
        if(obj == null){
            return "";
        }
        return obj.toString();
    }
}