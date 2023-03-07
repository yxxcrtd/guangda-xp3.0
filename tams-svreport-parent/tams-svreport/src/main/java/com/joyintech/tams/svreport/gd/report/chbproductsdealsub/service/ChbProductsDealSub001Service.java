package com.joyintech.tams.svreport.gd.report.chbproductsdealsub.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.system.batch.batchstart.service.SysBatchStart001Service;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.gd.report.chbconsignordetailsub.dto.ChbConsignorDetailSub001Dto;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dao.ChbProductsDealSubMapper;
import com.joyintech.tams.report.gd.report.chbproductsdealsub.dto.ChbProductsDealSub001Dto;
import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 理财产品交易明细登记表
 * @author yangxianjiu
 * @date 2022/03/30
 */
@Service("ChbProductsDealSub001Service")
public class ChbProductsDealSub001Service extends CommonExportService<ChbProductsDealSub001Dto> {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    //
    @Autowired
    private ChbProductsDealSubMapper chbProductsDealSubMapper;

    /**
     * 批启动
     */
    @Autowired
    private SysBatchStart001Service sysBatchStart001Service;

    public Page<ChbProductsDealSub001Dto> selectForPage(Map<String, Object> pageParam) {
        try {
            Integer page = Integer.parseInt(pageParam.get("page").toString());
            Integer length = Integer.parseInt(pageParam.get("length").toString());
            if (pageParam.containsKey("dateStatus")){
                String dateStatus = pageParam.get("dateStatus").toString();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(DateUtil.parse(dateStatus+"-01"));
                // 获取当前月的最后一天
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH,0);
                pageParam.put("dateStatus",DateUtil.format(calendar.getTime()));
            }
            Page<ChbProductsDealSub001Dto> dtoPage = super.queryForPage(page,length,() -> chbProductsDealSubMapper.selectForPage(pageParam));
            dtoPage.getRoot().forEach(dto -> {
                // 交易份额
                dto.setShares(dto.getShares() == null ? null : dto.getShares().setScale(2, BigDecimal.ROUND_HALF_UP));
                // 交易金额
                dto.setAmount(dto.getAmount() == null ? null : dto.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
                // 折人民币交易金额
                dto.setAmountCny(dto.getAmountCny() == null ? null : dto.getAmountCny().setScale(2, BigDecimal.ROUND_HALF_UP));
            });
            return dtoPage;
        } catch (Exception e){
            logger.info("理财产品交易明细登记表查询错误",e);
        }
        return new Page<ChbProductsDealSub001Dto>();
    }

    /**
     * 导出查询
     * @param params
     * @return
     */
    public List<ChbProductsDealSub001Dto> queryAllList(Map<String, Object> params) {
        List<ChbProductsDealSub001Dto> dtoList = chbProductsDealSubMapper.selectForPage(params);
        dtoList.forEach(dto -> {
            // 交易份额
            dto.setShares(dto.getShares().setScale(2, BigDecimal.ROUND_HALF_UP));
            // 交易金额
            dto.setAmount(dto.getAmountCny().setScale(2, BigDecimal.ROUND_HALF_UP));
            // 折人民币交易金额
            dto.setAmountCny(dto.getAmountCny().setScale(2, BigDecimal.ROUND_HALF_UP));
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
        int count = chbProductsDealSubMapper.getNoendCountByTaskBeanName(BatchBeanName.理财产品交易明细表批导出.getTaskId());
        if (count > 0) {
            ajaxResult.setMessage("理财产品交易明细表导出批处理正在执行中或待执行！", MessageType.错误消息);
            ajaxResult.setData(false);
            return ajaxResult;
        }
        ChbSubinfoBatchLog log = new ChbSubinfoBatchLog();
        log.setBatchExeId(IDGenerator.generate());
        log.setTaskId(BatchBeanName.理财产品交易明细表批导出.getTaskId());
        log.setBusiConfirmDate(param.get("dateStatus")==null?"":param.get("dateStatus").toString());
        log.setOperator(LoginUserUtil.getUserName());
        log.setFileType(DictCodeListExt3.报送文件类别_理财产品交易明细.getValue());
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
            param.put("dateStatus", dateStatus);
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            param.put("dateStatus", DateUtil.format(lastday));
        }

        String batchExportNum = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_SUBINFO_BATCH_EXPORT_NUM);
        if (!StringUtil.isNotEmpty(batchExportNum)) {
            batchExportNum = "1000000"; // 默认为100w
        }

        int dataNum = chbProductsDealSubMapper.countExportNum(param);
        // 是否需要批处理导出
        if (dataNum >= Integer.parseInt(batchExportNum)) {
            batchExport = true;
        }
        ajaxResult.setData(batchExport);
        return ajaxResult;
    }

    /**
     * 导出
     * @param params
     * @return
     * @throws IOException
     */
    public ResponseEntity<byte[]> download(Map<String, Object> params) throws IOException {
        // 导出Map
        Map<String,Object> data  = new HashMap<String,Object>();
        // File List
        List<File> sourceFiles = new ArrayList<>();
        List<ChbProductsDealSub001Dto> listdto = null ;
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysDate);
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        Date lastday = calendar.getTime();
        String dateForName = "";
        // 有导出日期
        if (params.containsKey("dateStatus")) {
            // 获取当前月的最后一天
            String dateStatus = params.get("dateStatus").toString();
            dateForName = params.get("dateStatus").toString();
            calendar.setTime(DateUtil.parse(dateStatus));
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH,0);
            params.put("dateStatus", dateStatus);
            // 无选中行，有参数
            if (!params.containsKey("keys")) {
                listdto = chbProductsDealSubMapper.selectForPage(params);
                dataFormat(listdto);
                if (CollectionUtils.isNotEmpty(listdto)) {
                    lastday = listdto.get(0).getDateStatus();
                } else {
                    // 无数据
                    lastday = calendar.getTime();
                }
            } else {
                // 有选中行,有参数
                lastday = calendar.getTime();
                String keys = params.get("keys").toString();
                listdto = JsonUtils.json2List(keys, ChbProductsDealSub001Dto.class);;
            }
        } else {
            // 未选择导出参数默认导出平台日上月最后一个自然日
            params.put("dateStatus", DateUtil.format(lastday));
            // 无选中行,无参数
            if (!params.containsKey("keys")) {
                listdto = chbProductsDealSubMapper.selectForPage(params);
            } else {
                // 有选中行，无参数
                String keys = params.get("keys").toString();
                listdto = JsonUtils.json2List(keys, ChbProductsDealSub001Dto.class);;
            }
        }

        // 统计日期
        if(CollectionUtils.isNotEmpty(listdto)) {
            for (ChbProductsDealSub001Dto dto : listdto) {
                dto.setDataDate(DateUtil.format(lastday, DateUtil.DEFAULT_DATE_FORMAT_EN));
                // 交易日期
                dto.setEquityConfDateStr(dto.getEquityConfDateStr());
                // 理财产品变更申请类型代码信息
                dto.setBusiTypeStr(dto.getBusiTypeStr());
                // 交易渠道类型代码信息
                dto.setChannelStr(dto.getChannelStr());
                // 数据时点
                dto.setDateStatusStr(dto.getDateStatusStr());
            }
        }
        List<ChbProductsDealSub001Dto> tempList = new ArrayList<>();
        ChbProductsDealSub001Dto chbProductsDealSub001Dto = new ChbProductsDealSub001Dto();
        if(CollectionUtils.isNotEmpty(listdto)) {
            data.put("dataList", listdto);
            tempList.add(listdto.get(0));
            data.put("dataList1", tempList);
        } else {

            chbProductsDealSub001Dto.setDataDate(DateUtil.format(lastday, DateUtil.DEFAULT_DATE_FORMAT_EN));
            tempList.add(chbProductsDealSub001Dto);
            // 无数据
            listdto.add(new ChbProductsDealSub001Dto());
            data.put("dataList", listdto);
            data.put("dataList1",tempList);
        }

        File depositfile = TisUtil.createFile(data,"pbc/" +"ChbProductsDealSub001_export", "理财产品交易明细表"
                + dateForName + ".xls");
        sourceFiles.add(depositfile);
        File outFile = null;
        outFile = sourceFiles.get(0);
        return ExportUtil.export(outFile.getName()
                , outFile, true);
    }



    //处理查询到的数据
    public List<ChbProductsDealSub001Dto> dataFormat(List<ChbProductsDealSub001Dto> dtoList) {
        dtoList.forEach(item -> {
            if(StringUtil.isNotEmpty(item.getBusiType())){
                item.setBusiTypeStr(item.getBusiType() + " " + SysDataUtil.getNameFromDataDict(DictCode.业务种类, item.getBusiType()));
            }
            if(StringUtil.isNotEmpty(item.getChannel())){
                if(StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.投资者明细渠道, item.getChannel()))) {
                    item.setChannelStr(item.getChannel() + " " + SysDataUtil.getNameFromDataDict(DictCode.投资者明细渠道, item.getChannel()));
                }
            }
        });
        return dtoList;
    }
}
