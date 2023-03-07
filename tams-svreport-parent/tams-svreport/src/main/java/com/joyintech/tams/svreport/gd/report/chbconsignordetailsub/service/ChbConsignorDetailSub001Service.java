package com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.system.batch.batchstart.service.SysBatchStart001Service;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dao.ChbConsignorDetailSubMapper;
import com.joyintech.tams.report.gd.report.chbconsignordetailsub.dto.ChbConsignorDetailSub001Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 委托人明细数据
 * @author yangxianjiu
 * @date 2022/03/29
 */
@Service("ChbConsignorDetailSub001Service")
public class ChbConsignorDetailSub001Service extends CommonExportService<ChbConsignorDetailSub001Dto> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    //
    @Autowired
    private ChbConsignorDetailSubMapper chbConsignorDetailSubMapper;

    /**
     * 批启动
     */
    @Autowired
    private SysBatchStart001Service sysBatchStart001Service;

    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    public Page<ChbConsignorDetailSub001Dto> selectForPage(Map<String, Object> pageParam) {
        try {
            Integer page = StringUtil.integerValueOf(pageParam.get("page").toString());
            Integer length = StringUtil.integerValueOf(pageParam.get("length").toString());
            Page<ChbConsignorDetailSub001Dto> dtoPage = super.queryForPage(page,length,() -> chbConsignorDetailSubMapper.selectForPage(pageParam));
            this.dataFormat(dtoPage.getRoot());
            return dtoPage;
        } catch (Exception e){
            logger.info("委托人明细数据登记表查询错误",e);
        }
        return new Page<ChbConsignorDetailSub001Dto>();
    }

    /**
     *
     * @param paramMap
     * @return
     */
    public List<SuggestDto> queryProdInfo(Map<String, Object> paramMap) {
        return chbConsignorDetailSubMapper.queryProdInfo(paramMap);
    }

    /**
     * 导出查询
     * @param params
     * @return
     */
    public List<ChbConsignorDetailSub001Dto> queryAllList(Map<String, Object> params) {
        List<ChbConsignorDetailSub001Dto> dtoList = chbConsignorDetailSubMapper.selectForPage(params);
        this.dataFormat(dtoList);
        return dtoList;
    }

    //处理查询到的数据
    public List<ChbConsignorDetailSub001Dto> dataFormat(List<ChbConsignorDetailSub001Dto> dtoList) {
        dtoList.forEach(item -> {
            if(StringUtil.isNotEmpty(item.getProdType())){
                item.setProdType(item.getProdType() + " " + SysDataUtil.getNameFromDataDict(DictCode.产品类型, item.getProdType()));
            }
            if(StringUtil.isNotEmpty(item.getProdInvestType())){
                item.setProdInvestType(item.getProdInvestType() + " " + SysDataUtil.getNameFromDataDict(DictCode.投资性质, item.getProdInvestType()));
            }
            if(StringUtil.isNotEmpty(item.getRaiseType())){
                item.setRaiseType(item.getRaiseType() + " " + SysDataUtil.getNameFromDataDict(DictCode.募集方式_中债, item.getRaiseType()));
            }
            if(StringUtil.isNotEmpty(item.getHolderType())){
                item.setHolderType(item.getHolderType() + " " + SysDataUtil.getNameFromDataDict(DictCode.投资人类型, item.getHolderType()));
            }
        });
        return dtoList;
    }

    /**
     * 批导出启动
     * @param param
     * @return
     */
    public AjaxResult batchExport(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        // 是否未执行或正在执行中
        int count = chbConsignorDetailSubMapper.getNoendCountByTaskBeanName(BatchBeanName.委托人明细数据表批导出.getTaskId());
        if (count > 0) {
            ajaxResult.setMessage("委托人明细数据表批导出处理正在执行中或待执行！", MessageType.错误消息);
            ajaxResult.setData(false);
            return ajaxResult;
        }
        String lastDayHold = param.get("lastDayHold").toString();
        ChbSubinfoBatchLog log = new ChbSubinfoBatchLog();
        log.setBatchExeId(IDGenerator.generate());
        log.setTaskId(BatchBeanName.委托人明细数据表批导出.getTaskId());
            // 未选择导出参数默认导出平台日上月最后一个自然日
        log.setBusiConfirmDate(lastDayHold);

        log.setOperator(LoginUserUtil.getUserName());
        log.setFileType(DictCodeListExt3.报送文件类别_委托人明细数据.getValue());
        // 未处理
        log.setDealStatus(DictCodeListExt3.报送处理状态_未处理.getValue());
        log.setGenCondition(param.get("key")==null?"":param.get("key").toString());
        log.setCreateUser(LoginUserUtil.getUserName());
        log.setCreateTime(new Date());
        log.setUpdateUser(LoginUserUtil.getUserName());
        log.setUpdateTime(new Date());
        // 日志存表
        restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/ChbSubinfoBatchLog/insert", log,
                ChbSubinfoBatchLog.class);
        // 启动批处理
        sysBatchStart001Service.executeBatch();
        // 批处理启动成功，可在批处理监控页面查看进度
        ajaxResult.setData(true);
        return ajaxResult;
    }

    /**
     * 统计数据行是否需要批导出
     * @param param
     * @return
     */
    public AjaxResult checkExport(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        boolean batchExport = false;

        String batchExportNum = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SUBINFO_BATCH_EXPORT_NUM);
        if (!StringUtil.isNotEmpty(batchExportNum)) {
            batchExportNum = "1000000"; // 默认为100w
        }

        int dataNum = chbConsignorDetailSubMapper.countExportNum(param);
        // 是否需要批处理导出
        if (dataNum >= Integer.parseInt(batchExportNum)) {
            batchExport = true;
        }
        ajaxResult.setData(batchExport);
        return ajaxResult;
    }
}
