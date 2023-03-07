package com.joyintech.tams.svreport.loaninfomanagement.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbLoanDetailInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.loaninfomanagement.validator.OperationalLoanInfo001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 贷款明细信息维护dto
 * @author hpp
 *
 */
@PageId("OperationalLoanInfo002")
@ValidLogic(targetClass = OperationalLoanInfo001Validator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class OperationalLoanInfo001Dto extends ChbLoanDetailInfo implements ProcessEntity{
	//计划主键
	private String filterid;
	//业务数据维护待维护表ID
	private String basetableid;

	public String getFilterid() {
		return filterid;
	}

	public void setFilterid(String filterid) {
		this.filterid = filterid;
	}

	public String getBasetableid() {
		return basetableid;
	}

	public void setBasetableid(String basetableid) {
		this.basetableid = basetableid;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    /**
     * 选中的数据
     */
    private List<OperationalLoanInfo001Dto > selectedItem;

	//资产名称
	private String assetName;

	//人行资产三级分类
	private String pbcAssetType;

	//转换后的贷款及资产收益权类型 01收益权 02贷款
	private String loanFlag;

	//贷款合同原始发放机构所在地代码
	//取值贷款及资产收益权明细维护模块“贷款合同原始发放机构所在地代码（省）”、“贷款合同原始发放机构所在地代码（市）”、
	//“贷款合同原始发放机构所在地代码（县）”字段，若县一级无值则取市一级，依次类推。
	private String loanContractOrg;

    @PrimaryKey(PrimaryKey.Field.PK_1)
	@Override
	public String getAssetUuid() {
        return super.getAssetUuid();
    }
    
	//金融产品代码
	private String finprodId;
    
	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
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

	public List<OperationalLoanInfo001Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<OperationalLoanInfo001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getStatusStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.中债数据状态,
//				getStatus());
//		return result == null ? getStatus() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.中债数据状态, getStatus());
	}

	public String getAssetTypeStr() {
		String result = SysDataUtil.getNameFromDataDict(DictCode.中债贷款分类转人行资产三级分类,
				getAssetType());
		return result == null ? getAssetType() : result;
//		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.中债贷款分类转人行资产三级分类, getAssetType());
	}

	public String getAssetTypeEStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.中债贷款分类,
//				getAssetType());
//		return result == null ? getAssetType() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.中债贷款分类, getAssetType());
	}

	public String getAssetsInvestPageStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新人行增信机构类型,
//				getAssetsInvest());
//		return result == null ? getAssetsInvest() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.客户一级类型, getAssetsInvest());
	}

	public String getAssetsInvestStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新人行增信机构类型,
//				getAssetsInvest());
//		return result == null ? getAssetsInvest() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.客户一级类型, getAssetsInvest());
	}

	public String getAssetsLiabilitiesStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.资产负债项目,
//				getAssetsLiabilities());
//		return result == null ? getAssetsLiabilities() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.资产负债项目, getAssetsLiabilities());
	}

	public String getAssetsTransferIinduStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.行业门内编码,
//				getAssetsTransferIindu());
//		return result == null ? getAssetsTransferIindu() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.行业门内编码, getAssetsTransferIindu());
	}

	public String getAssetsTransferCountryStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区,
//				getAssetsTransferCountry());
//		return result == null ? getAssetsTransferCountry() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, getAssetsTransferCountry());
	}

	public String getAssetsTransferProvStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_省,
//				getAssetsTransferProv());
//		return result == null ? getAssetsTransferProv() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新_区域划分_省, getAssetsTransferProv());
	}

	public String getAssetsTransferCityStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_市,
//				getAssetsTransferCity());
//		return result == null ? getAssetsTransferCity() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新_区域划分_市, getAssetsTransferCity());
	}

	public String getAssetsTransferCountyStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_县,
//				getAssetsTransferCounty());
//		return result == null ? getAssetsTransferCounty() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新_区域划分_县, getAssetsTransferCounty());
	}

	public String getEconomicCompositionStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.经济成分二级,
//				getEconomicComposition());
//		return result == null ? getEconomicComposition() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.经济成分二级, getEconomicComposition());
	}

	public String getAssetsTransferScaleStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.企业规模,
//				getAssetsTransferScale());
//		return result == null ? getAssetsTransferScale() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.企业规模, getAssetsTransferScale());
	}

	public String getBasicassetTypeStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.基础资产类型,
//				getBasicassetType());
//		return result == null ? getBasicassetType() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.基础资产类型, getBasicassetType());
	}

	public String getAssetsOrigccyStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.币种_人行,
//				getAssetsOrigccy());
//		return result == null ? getAssetsOrigccy() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.币种_人行, getAssetsOrigccy());
	}

	public String getCurrencyTransferccyStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.币种_人行,
//				getCurrencyTransferccy());
//		return result == null ? getCurrencyTransferccy() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.币种_人行, getCurrencyTransferccy());
	}

	public String getLabelTransferorMarkStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.人行是否,
//				getLabelTransferorMark());
//		return result == null ? getLabelTransferorMark() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.人行是否, getLabelTransferorMark());
	}

	public String getRepurchaseMarkStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.人行是否,
//				getRepurchaseMark());
//		return result == null ? getRepurchaseMark() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.人行是否, getRepurchaseMark());
	}

	public String getRateFixedFlagStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.利率是否固定,
//				getRateFixedFlag());
//		return result == null ? getRateFixedFlag() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.利率是否固定, getRateFixedFlag());
	}

	public String getGuaranteeMethodStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.贷款担保方式,
//				getGuaranteeMethod());
//		return result == null ? getGuaranteeMethod() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.贷款担保方式, getGuaranteeMethod());
	}

	public String getAssetsInvDepaStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新人行增信机构类型,
//				getAssetsInvDepa());
//		return result == null ? getAssetsInvDepa() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新人行增信机构类型, getAssetsInvDepa());
	}

	public String getCurrencyEperiodccyStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.币种_人行,
//				getCurrencyEperiodccy());
//		return result == null ? getCurrencyEperiodccy() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.币种_人行, getCurrencyEperiodccy());
	}

	private String transferVdateStr;
  	public String getTransferVdateStr() {
		transferVdateStr = DateUtil.format(this.getTransferVdate());
		return transferVdateStr;
	}

	private String transferEstiMdateStr;
	public String getTransferEstiMdateStr() {
		transferEstiMdateStr = DateUtil.format(this.getTransferEstiMdate());
		return transferEstiMdateStr;
	}

	private String transferExtdueMdateStr;
	public String getTransferExtdueMdateStr() {
		transferExtdueMdateStr = DateUtil.format(this.getTransferExtdueMdate());
		return transferExtdueMdateStr;
	}

	private String transferActmdateStr;
	public String getTransferActmdateStr() {
		transferActmdateStr = DateUtil.format(this.getTransferActmdate());
		return transferActmdateStr;
	}

	//贷款数据字典转换
	public String getLoansTypeStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.贷款种类,
//				getLoansType());
//		return result == null ? getLoansType() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.贷款种类, getLoansType());
	}

	public String getLoanContractOrgProvinceStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_省,
//				getLoanContractOrgProvince());
//		return result == null ? getLoanContractOrgProvince() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新_区域划分_省, getLoanContractOrgProvince());
	}

	public String getLoanContractOrgCityStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_市,
//				getLoanContractOrgCity());
//		return result == null ? getLoanContractOrgCity() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新_区域划分_市, getLoanContractOrgCity());
	}

	public String getLoanContractOrgCountyStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_县,
//				getLoanContractOrgCounty());
//		return result == null ? getLoanContractOrgCounty() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新_区域划分_县, getLoanContractOrgCounty());
	}

	public String getBorrowerTypeStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.借款人类型,
//				getBorrowerType());
//		return result == null ? getBorrowerType() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.借款人类型, getBorrowerType());
	}

	public String getIndustryInfoStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.行业门内编码,
//				getIndustryInfo());
//		return result == null ? getIndustryInfo() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.行业门内编码, getIndustryInfo());
	}

	public String getBorrowerNationalityStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.所属国家或地区,
//				getBorrowerNationality());
//		return result == null ? getBorrowerNationality() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.所属国家或地区, getBorrowerNationality());
	}

	public String getBorrowerProvinceStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_省,
//				getBorrowerProvince());
//		return result == null ? getBorrowerProvince() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新_区域划分_省, getBorrowerProvince());
	}

	public String getBorrowerCityStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_市,
//				getBorrowerCity());
//		return result == null ? getBorrowerCity() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新_区域划分_市, getBorrowerCity());
	}

	public String getBorrowerCountyStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.新_区域划分_县,
//				getBorrowerCounty());
//		return result == null ? getBorrowerCounty() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.新_区域划分_县, getBorrowerCounty());
	}

	public String getEnterpriseInvestorTypeFirstStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.经济成分一级,
//				getEnterpriseInvestorTypeFirst());
//		return result == null ? getEnterpriseInvestorTypeFirst() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.经济成分一级, getEnterpriseInvestorTypeFirst());
	}

	public String getEnterpriseInvestorTypeSecStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.经济成分二级,
//				getEnterpriseInvestorTypeSec());
//		return result == null ? getEnterpriseInvestorTypeSec() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.经济成分二级, getEnterpriseInvestorTypeSec());
	}

	public String getEnterpriseInvestorTypeThirdStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.经济成分三级,
//				getEnterpriseInvestorTypeThird());
//		return result == null ? getEnterpriseInvestorTypeThird() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.经济成分三级, getEnterpriseInvestorTypeThird());
	}

	public String getEnterpriseScaleStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.企业规模,
//				getEnterpriseScale());
//		return result == null ? getEnterpriseScale() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.企业规模, getEnterpriseScale());
	}

	public String getLoanPrdTypeStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.贷款产品类别,
//				getLoanPrdType());
//		return result == null ? getLoanPrdType() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.贷款产品类别, getLoanPrdType());
	}

	public String getLoanActualInvestStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.行业大类编码,
//				getLoanActualInvest());
//		return result == null ? getLoanActualInvest() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.贷款实际投向, getLoanActualInvest());
	}

	private String loanVdateStr;
	public String getLoanVdateStr() {
		loanVdateStr = DateUtil.format(this.getLoanVdate());
		return loanVdateStr;
	}

	private String loanMdateStr;
	public String getLoanMdateStr() {
		loanMdateStr = DateUtil.format(this.getLoanMdate());
		return loanMdateStr;
	}

	private String loanExtensionDueDateStr;
	public String getLoanExtensionDueDateStr() {
		loanExtensionDueDateStr = DateUtil.format(this.getLoanExtensionDueDate());
		return loanExtensionDueDateStr;
	}

	public String getRateFixedStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.利率是否固定,
//				getRateFixed());
//		return result == null ? getRateFixed() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.利率是否固定, getRateFixed());
	}

	public String getLoanGuaranteeModeStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.贷款担保方式,
//				getLoanGuaranteeMode());
//		return result == null ? getLoanGuaranteeMode() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.贷款担保方式, getLoanGuaranteeMode());
	}

	public String getLoanQualityStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.贷款质量,
//				getLoanQuality());
//		return result == null ? getLoanQuality() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.贷款质量, getLoanQuality());
	}

	public String getLoanStateStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.贷款状态,
//				getLoanState());
//		return result == null ? getLoanState() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.贷款状态, getLoanState());
	}

	public String getOrigCcyStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.币种_人行,
//				getOrigCcy());
//		return result == null ? getOrigCcy() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.币种_人行, getOrigCcy());
	}

	public String getLoanBalanceCcyStr() {
//		String result = SysDataUtil.getNameFromDataDict(DictCode.币种_人行,
//				getLoanBalanceCcy());
//		return result == null ? getLoanBalanceCcy() : result;
		return com.joyintech.tams.report.common.util.SysDataUtil.getZZDictCodeAndName(DictCode.币种_人行, getLoanBalanceCcy());
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getPbcAssetType() {
		return pbcAssetType;
	}

	public void setPbcAssetType(String pbcAssetType) {
		this.pbcAssetType = pbcAssetType;
	}

	public String getLoanContractOrg() {
		return loanContractOrg;
	}

	public void setLoanContractOrg(String loanContractOrg) {
		this.loanContractOrg = loanContractOrg;
	}

	public String getLoanFlag() {
		return loanFlag;
	}

	public void setLoanFlag(String loanFlag) {
		this.loanFlag = loanFlag;
	}
}
