package com.joyintech.tams.svreport.specificcounterparty.dto;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;

import java.util.List;

public enum ZG08SpecCounterQuityRule {
	A5200_1("A5200","1","P05,R01,L08","","1902,1901",""),
	B1200_1("B1200","1","P05,R01,L08","1902,1901","",""),
	A5200_2("A5200","2","P05,R01,L09","","1902,1901",""),
	B1200_2("B1200","2","P05,R01,L09","1902,1901","",""),
	A5200_3("A5200","3","P05,R01,L10","","1902,1901",""),
	B1200_3("B1200","3","P05,R01,L10","1902,1901","",""),
	A5200_4("A5200","4","P05,R01,L11","","1902,1901",""),
	B1200_4("B1200","4","P05,R01,L11","1902,1901","",""),
	A5200_5("A5200","5","P05,R01,L12","","1902,1901",""),
	B1200_5("B1200","5","P05,R01,L12","1902,1901","",""),
	A5200_6("A5200","6","P05,R01,L13","","1902,1901",""),
	B1200_6("B1200","6","P05,R01,L13","1902,1901","",""),
	A5200_7("A5200","7","P05,R01,L14","","1902,1901",""),
	B1200_7("B1200","7","P05,R01,L14","1902,1901","",""),
	A5200_8("A5200","8","P05,R01,L15","","1902,1901",""),
	B1200_8("B1200","8","P05,R01,L15","1902,1901","",""),
	A7200_1("A7200","1","P07,T02,M01","","",""),
	A7200_2("A7200","2","P07,T02,M02","","",""),
	A7200_3("A7200","3","P07,T02,M03","","",""),
	A7200_4("A7200","4","P07,T02,M04","","",""),
	A7200_5("A7200","5","P07,T02,M05","","",""),
	A7200_6("A7200","6","P07,T02,M06","","",""),
	A7200_7("A7200","7","P07,T02,M07","","",""),
	A7200_8("A7200","8","P07,T02,M08","","",""),
	C1200_2("C1200","2","","","","17"),
	C1200_3("C1200","3","","","","18"),
	C1200_4("C1200","4","","","","19"),
	C1200_5("C1200","5","","","","20"),
	C1200_6("C1200","6","","","","21"),
	C1200_8("C1200","8","","","","22"),
	;
	/**
	 * 资产负债项目
	 */
	private String assetType;

	/**
	 * 交易对手产品种类
	 */
	private String prrtyProdType;

	/**
	 * 人行资产属性匹配规则
	 */
	private String pbcAssetType;

	/**
	 * 中债资产规则匹配规则 等于时
	 */
	private List<String> chbCodeEqualsList;

	/**
	 * 中债资产规则匹配规则 不等于时
	 */
	private List<String> chbCodeNotEqualsList;

	/*
	* 中债投资者类别
	* */
	private String chbHolderType;

	ZG08SpecCounterQuityRule(String assetType, String prrtyProdType, String pbcAssetType, String chbCodeEqualsList, String chbCodeNotEqualsList, String chbHolderType){
		this.assetType = assetType;
		this.prrtyProdType = prrtyProdType;
		this.pbcAssetType = pbcAssetType;
		this.chbCodeEqualsList = StringUtil.splitToList(GlobalConstant.COMMA, chbCodeEqualsList);
		this.chbCodeNotEqualsList = StringUtil.splitToList(GlobalConstant.COMMA, chbCodeNotEqualsList);
		this.chbHolderType = chbHolderType;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getPrrtyProdType() {
		return prrtyProdType;
	}

	public void setPrrtyProdType(String prrtyProdType) {
		this.prrtyProdType = prrtyProdType;
	}

	public String getPbcAssetType() {
		return pbcAssetType;
	}

	public void setPbcAssetType(String pbcAssetType) {
		this.pbcAssetType = pbcAssetType;
	}

	public List<String> getChbCodeEqualsList() {
		return chbCodeEqualsList;
	}

	public void setChbCodeEqualsList(List<String> chbCodeEqualsList) {
		this.chbCodeEqualsList = chbCodeEqualsList;
	}

	public List<String> getChbCodeNotEqualsList() {
		return chbCodeNotEqualsList;
	}

	public void setChbCodeNotEqualsList(List<String> chbCodeNotEqualsList) {
		this.chbCodeNotEqualsList = chbCodeNotEqualsList;
	}

	public String getChbHolderType() {
		return chbHolderType;
	}

	public void setChbHolderType(String chbHolderType) {
		this.chbHolderType = chbHolderType;
	}
}
