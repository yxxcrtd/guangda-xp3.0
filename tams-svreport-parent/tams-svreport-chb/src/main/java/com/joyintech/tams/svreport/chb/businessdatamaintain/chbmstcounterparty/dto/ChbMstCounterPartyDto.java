package com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.dto;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.component.svrreport.chb.contant.DictCodeList;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.validator.ChbMstCounterPartyValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * @author cff
 * @date 2022-04-27 14:09
 */
@PageId("ChbMstCounterParty002")
@ValidLogic(targetClass = ChbMstCounterPartyValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class ChbMstCounterPartyDto extends ChbMstCounterParty implements ProcessEntity {

    /**
     * 操作类型
     */
    private String operateType;

    private List<ChbMstCounterPartyDto> selectedItem;

    /**
     * 目的和意义以及可能会用到的地方
     */
    private String pageModel;
    /**
     * 要素id
     */
    private String elementId;


    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }
    /**
     * 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     */
    private String counterName1;
    /**
     * 交易对手名称，市场机构取机构名称，通道取金融产品简称，资管产品由页面输入。
     */
    private String counterName5;

    public String getCounterName1() {
        return counterName1;
    }

    public void setCounterName1(String counterName1) {
        this.counterName1 = counterName1;
    }

    public String getCounterName5() {
        return counterName5;
    }

    public void setCounterName5(String counterName5) {
        this.counterName5 = counterName5;
    }

    /**
     * 行号
     */
    private String excelNum;

    /**
     * 标识从info表还是error表取值
     */
    private String info;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 数据来源
     */
    private String dataSource;

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 有效状态
     */
    private String statusValid;

    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getCounterId(){
        return super.getCounterId();
    }
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getStatus(){
        return super.getStatus();
    }
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getCounterType(){
        return super.getCounterType();
    }


    // updateFlg 02 不展示  01 显示
    public String getUpdateFlg() {
        if(!StringUtil.isEmpty(statusValid) && StringUtil.equals(DictCodeList.维护数据状态_删除.getValue(), statusValid)){
            return "02";
        }else{
            return "01";
        }
    }
    public String getStatusValidStr() {
        return com.joyintech.framework.util.SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,
                getStatusValid());
    }

    public String getCounterTypeStr() {
        return com.joyintech.framework.util.SysDataUtil.getNameFromDataDict(DictCode.交易对手类别,
                getCounterType());
    }


    public String getRelatedTradeStatusStr(){
        return SvrDataUtil.getZZDictCodeAndName(DictCode.关联交易情况,
                getRelatedTradeStatus());
    }

    public String getCounterType2Str() {
        return SvrDataUtil.getZZDictCodeAndName(DictCode.客户二级类型,
                getCounterType2());
    }
    public String getStatusValid() {
        return statusValid;
    }

    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<ChbMstCounterPartyDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ChbMstCounterPartyDto> selectedItem) {
        this.selectedItem = selectedItem;
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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
    public String getupdateTimeStr(){
        return DateUtil.formatDateTime(getUpdateTime());
    }

}
