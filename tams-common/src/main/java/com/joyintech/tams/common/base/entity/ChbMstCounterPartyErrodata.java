package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbMstCounterPartyErrodata extends BaseEntity {
    /**
     * 交易对手代码
     */
    private String counterId;

    /**
     * uuid技术主键
     */
    private String uuid;

    /**
     * 交易对手类型，市场机构、资管产品、通道
     */
    private String counterType;

    /**
     * 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     */
    private String counterName;

    /**
     * 交易对手一级类型
     */
    private String counterType1;

    /**
     * 交易对手二级类型
     */
    private String counterType2;

    /**
     * 取得 交易对手代码
     * @return 交易对手代码
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 交易对手代码
     * @param counterId 交易对手代码
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId == null ? null : counterId.trim();
    }

    /**
     * 取得 uuid技术主键
     * @return uuid技术主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 uuid技术主键
     * @param uuid uuid技术主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 交易对手类型，市场机构、资管产品、通道
     * @return 交易对手类型，市场机构、资管产品、通道
     */
    public String getCounterType() {
        return counterType;
    }

    /**
     * 设置 交易对手类型，市场机构、资管产品、通道
     * @param counterType 交易对手类型，市场机构、资管产品、通道
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType == null ? null : counterType.trim();
    }

    /**
     * 取得 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     * @return 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * 设置 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     * @param counterName 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName == null ? null : counterName.trim();
    }

    /**
     * 取得 交易对手一级类型
     * @return 交易对手一级类型
     */
    public String getCounterType1() {
        return counterType1;
    }

    /**
     * 设置 交易对手一级类型
     * @param counterType1 交易对手一级类型
     */
    public void setCounterType1(String counterType1) {
        this.counterType1 = counterType1 == null ? null : counterType1.trim();
    }

    /**
     * 取得 交易对手二级类型
     * @return 交易对手二级类型
     */
    public String getCounterType2() {
        return counterType2;
    }

    /**
     * 设置 交易对手二级类型
     * @param counterType2 交易对手二级类型
     */
    public void setCounterType2(String counterType2) {
        this.counterType2 = counterType2 == null ? null : counterType2.trim();
    }
}