package com.joyintech.tams.report.submitmaintenance.chbinvestholdinfo.dao;

import com.joyintech.tams.common.base.entity.ChbInvestholdInfoTmp;
import com.joyintech.tams.report.investormerger.dto.ChbInvestholdInfoCheckDto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ChbInvestHoldInfoTmpMpper {

    /**
     * 合并数据（入库）
     * @param insertList
     * @return
     */
    int mergeInfoList(@Param("newDataList") List<ChbInvestholdInfoTmp> insertList);

    int insert(@Param("newDataList") List<ChbInvestholdInfoTmp> insertList);

    int saveFlag(String date);

    int checkTmpNum(String date);

    /**
     * 获取所有的登记银行编码和机构代码的集合
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<Map<String,String>> getAllRegistBankCodeSet();

    /**
     * 获取所有的产品登记编码与产品代码的集合
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<Map<String,String>> getAllRegistProdCodeSet();
    /**
     * 查询当天持有信息条数
     *
     * @param date 参数
     * @return 当天持有信息条数
     * @see
     * @since 1.0
     */
    int selectInvestHoldInfoDataCount(Date date);

    /**
     * 获取当日第一次接入tmp未合并的三合一数据
     * @param date
     * @return
     */
    List<ChbInvestholdInfoTmp>  getThreeInOneTmpList(String date);

    /**
     * 获取当日第一次接入tmp未合并的非三合一数据
     * @param date
     * @return
     */
    List<ChbInvestholdInfoTmp>  getNotThreeInOneTmpList(String date);

    /**
     * 获取当日非第一次接入tmp未合并的三合一数据
     * @param date
     * @return
     */
    List<ChbInvestholdInfoTmp>  getThreeInOneTmpList2(@Param("date") String date,@Param("keyList")List<ChbInvestholdInfoTmp> keyList);

    /**
     * 获取当日非第一次接入tmp未合并的非三合一数据
     * @param date
     * @return
     */
    List<ChbInvestholdInfoTmp>  getNotThreeInOneTmpList2(@Param("date") String date,@Param("keyList")List<ChbInvestholdInfoTmp> keyList);

    /**
     * 获取今日刚接入未合并处理数据
     * @param date
     * @return
     */
    List<ChbInvestholdInfoTmp>  getDataKey(String date);

    /**
     * 批量插入check表
     * <功能详细描述>
     * @param newDataList 数据集合
     * @see
     * @since 1.0
     */
    void insertChbHoldInfoCheckData(@Param("newDataList")List<ChbInvestholdInfoCheckDto> newDataList);

    /**
     * 批量删除check表
     * <功能详细描述>
     * @param newDataList 数据集合
     * @see
     * @since 1.0
     */
    void deleteChbHoldInfoCheckDataByPart(@Param("newDataList")List<ChbInvestholdInfoCheckDto> newDataList);

    void updateTmpOperatestatus();

    void deleteTmpByBatchDate(String date);

    /**
     * 获取今日刚接入未合并处理数据
     * @param date
     * @return
     */
    List<ChbInvestholdInfoTmp>  getAllDataKey(@Param("newDataList")List<ChbInvestholdInfoTmp> newDataList);

    /**
     * 获取当日非第一次接入tmp未合并的三合一数据
     * @param date
     * @return
     */
    List<ChbInvestholdInfoTmp>  getAllTmpList(@Param("keyList")List<ChbInvestholdInfoTmp> keyList);

    List<ChbInvestholdInfoTmp>  getAllTmp(ChbInvestholdInfoTmp key);

    int editCheck(InvestorsHoldInfo001Dto key);
}
