/*
 * 文件名：InvestorsHoldInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */

package com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto;




public class InvestorsHoldResultDto {


    /**
     * 识别标识
     */
    private String indentifyMark;


    /**
     * 投资者所属银行代码
     */
    private String theBankCode;


    /**
     * 账户开户所在地
     */
    private String fundAccPlace;


    public String getIndentifyMark() {
        return indentifyMark;
    }

    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark;
    }

    public String getTheBankCode() {
        return theBankCode;
    }

    public void setTheBankCode(String theBankCode) {
        this.theBankCode = theBankCode;
    }

    public String getFundAccPlace() {
        return fundAccPlace;
    }

    public void setFundAccPlace(String fundAccPlace) {
        this.fundAccPlace = fundAccPlace;
    }
}
