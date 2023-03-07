package com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
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
import com.joyintech.tams.report.gd.report.chbfinanceproductssub.dto.ChbFinanceProductsSub001Dto;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.dao.ChbFinanceProductsSub001Mapper;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dao.ChbProductsDealSubMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ChbFinanceProductsSub001Service extends CommonExportService<ChbFinanceProductsSub001Dto> {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    private ChbFinanceProductsSub001Mapper chbFinanceProductsSub001Mapper;

    /**
     * 批启动
     */
    @Autowired
    private SysBatchStart001Service sysBatchStart001Service;

    public Page<ChbFinanceProductsSub001Dto> findPage(Map<String, Object> pageParam) {
      try{
        Integer page = Integer.parseInt(pageParam.get("page").toString());
        Integer length = Integer.parseInt(pageParam.get("length").toString());
        if (pageParam.containsKey("dateStatus")) {
            String dateStatus = pageParam.get("dateStatus").toString();
            Calendar calendar = Calendar.getInstance();
            /*calendar.setTime(DateUtil.parse(dateStatus));*/
            calendar.setTime(DateUtil.parse(dateStatus+"-01"));
            // 获取上月的最后一天
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            pageParam.put("dateStatus", DateUtil.format(calendar.getTime()));
        }
        Page<ChbFinanceProductsSub001Dto> pageList=super.queryForPage(page, length, () -> chbFinanceProductsSub001Mapper.findPage(pageParam));
        pageList.getRoot().forEach(dto->{
            if(dto.getRaiseAmtPlan()!=null){
                dto.setRaiseAmtPlan(dto.getRaiseAmtPlan().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(dto.getTotalAmt()!=null){
                dto.setTotalAmt(dto.getTotalAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(dto.getSaleAmtStr()!=null){
                dto.setSaleAmtStr(dto.getSaleAmtStr().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        });
        this.dataFormat(pageList.getRoot());
        return pageList;
    }catch(Exception e) {
        logger.info("理财产品登记表查询错误", e);
    }
        return new Page<ChbFinanceProductsSub001Dto>();
}
    public List<ChbFinanceProductsSub001Dto> queryAllList(Map<String, Object> params) {
        List<ChbFinanceProductsSub001Dto> list = chbFinanceProductsSub001Mapper.findPage(params);
        list.forEach(dto->{
            if(dto.getRaiseAmtPlan()!=null){
                dto.setRaiseAmtPlan(dto.getRaiseAmtPlan().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(dto.getTotalAmt()!=null){
                dto.setTotalAmt(dto.getTotalAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            if(dto.getSaleAmtStr()!=null){
                dto.setSaleAmtStr(dto.getSaleAmtStr().setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        });
        this.dataFormat(list);
        return list;
    }

    public List<ChbFinanceProductsSub001Dto> dataFormat(List<ChbFinanceProductsSub001Dto> dataList) {
        dataList.forEach(item->{
            if(StringUtil.isNotEmpty(item.getContMode())){
                item.setContMode(item.getContMode()+" "+ SysDataUtil.getNameFromDataDict(DictCode.合作模式, item.getContMode()));
            }
            if(StringUtil.isNotEmpty(item.getProfitType())){
                item.setProfitType(item.getProfitType()+" "+ SysDataUtil.getNameFromDataDict(DictCode.产品收益标识, item.getProfitType()));
            }
            if(StringUtil.isNotEmpty(item.getOperateMode())){
                item.setOperateMode(item.getOperateMode()+" "+ SysDataUtil.getNameFromDataDict(DictCode.产品运作模式, item.getOperateMode()));
            }
            if(StringUtil.isNotEmpty(item.getCmmode())){
                item.setCmmode(item.getCmmode()+" "+ SysDataUtil.getNameFromDataDict(DictCode.产品投资模式, item.getCmmode()));
            }
            if(StringUtil.isNotEmpty(item.getInvAssetType())){
                item.setInvAssetType(item.getInvAssetType()+" "+ SysDataUtil.getNameFromDataDict(DictCode.投资类型信息, item.getInvAssetType()));
            }
            if(StringUtil.isNotEmpty(item.getCustomerType())){
                item.setCustomerType(item.getCustomerType()+" "+ SysDataUtil.getNameFromDataDict(DictCode.专属客户类型, item.getCustomerType()));
            }
            if(StringUtil.isNotEmpty(item.getInvestArae())){
                item.setInvestArae(item.getInvestArae()+" "+ SysDataUtil.getNameFromDataDict(DictCode.资金投向地区, item.getInvestArae()));
            }
            if(StringUtil.isNotEmpty(item.getIsConstracte())){
                item.setIsConstracte(item.getIsConstracte()+" "+ SysDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getIsConstracte()));
            }
        });
        return dataList;
    }

    /**
     * 统计数据行是否需要批导出
     * @param param
     * @return
     */
    public AjaxResult checkExport(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        boolean batchExport = false;
        // 获取平台日期
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysDate);
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        Date lastday = calendar.getTime();
        // 有导出日期
        if (StringUtil.isNotEmpty(param.get("dateStatus"))) {
            // 获取当前月的最后一天
            String dateStatus = param.get("dateStatus").toString();
            calendar.setTime(com.joyintech.framework.util.DateUtil.parse(dateStatus + "-01"));
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            param.put("dateStatus", DateUtil.format(calendar.getTime()));
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            param.put("dateStatus", DateUtil.format(lastday));
        }

        String batchExportNum = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SUBINFO_BATCH_EXPORT_NUM);
        if (!StringUtil.isNotEmpty(batchExportNum)) {
            batchExportNum = "1000000"; // 默认为100w
        }

        int dataNum = chbFinanceProductsSub001Mapper.countExportNum(param);
        // 是否需要批处理导出
        if (dataNum >= Integer.parseInt(batchExportNum)) {
            batchExport = true;
        }
        ajaxResult.setData(batchExport);
        return ajaxResult;
    }

    /**
     * 批导出启动
     * @param param
     * @return
     */
    public AjaxResult batchExport(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        // 是否未执行或正在执行中
        int count = chbFinanceProductsSub001Mapper.getNoendCountByTaskBeanName(BatchBeanName.理财产品登记表批导出.getTaskId());
        if (count > 0) {
            ajaxResult.setMessage("理财产品登记表批导出批处理正在执行中或待执行！", MessageType.错误消息);
            ajaxResult.setData(false);
            return ajaxResult;
        }
        // 获取平台日期
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysDate);
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        Date lastday = calendar.getTime();

        ChbSubinfoBatchLog log = new ChbSubinfoBatchLog();
        log.setBatchExeId(IDGenerator.generate());
        log.setTaskId(BatchBeanName.理财产品登记表批导出.getTaskId());
        // 有导出日期
        if (StringUtil.isNotEmpty(param.get("dateStatus"))) {
            // 获取当前月的最后一天
            String dateStatus = param.get("dateStatus").toString();
            calendar.setTime(com.joyintech.framework.util.DateUtil.parse(dateStatus + "-01"));
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            log.setBusiConfirmDate(com.joyintech.tams.report.interf.common.constant.DateUtil.format(calendar.getTime()));
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            log.setBusiConfirmDate(com.joyintech.tams.report.interf.common.constant.DateUtil.format(lastday));
        }
        log.setOperator(LoginUserUtil.getUserName());
        log.setFileType(DictCodeListExt3.报送文件类别_理财产品登记.getValue());
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
}
