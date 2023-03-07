package com.joyintech.tams.batch.report.report.chbconsignordetailsub.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.batch.report.report.chbconsignordetailsub.dao.SvrChbConsignorDetailSubExportTaskMapper;
import com.joyintech.tams.batch.report.report.chbproductsbalancesub.dao.SvrChbProductsBalanceSubExportTaskMapper;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 委托人明细数据表批导出
 * @author yangxianjiu
 * @date 2022/04/14
 */
@Service
public class SvrChbConsignorDetailSubExportMainService extends AutoSendCBBaseService {

    @Autowired
    private SvrChbConsignorDetailSubExportTaskMapper svrChbConsignorDetailSubExportTaskMapper;

    @Autowired
    private SvrChbConsignorDetailSubExportCommonService svrChbConsignorDetailSubExportCommonService;

    @Autowired
    private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;


    public void getExportData() {
        setBatchBeanName(BatchBeanName.委托人明细数据表批导出);
        logInfoBatchStart();
        List<ChbSubinfoBatchLog> listBatchLog = svrChbConsignorDetailSubExportTaskMapper.getBatchLog(BatchBeanName.委托人明细数据表批导出.getTaskId());
        for (ChbSubinfoBatchLog batchLog : listBatchLog) {
            // 更新批处理状态 处理中
            batchLog.setDealStatus(DictCodeListExt3.报送处理状态_处理中.getValue());
            chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(batchLog);
            // 转出参数
            Map param = (Map)JSON.parse(batchLog.getGenCondition());
            //处理月份
            if(StringUtil.isNotEmpty(param.get("month"))) {
                this.dealMonth(param);
            }else{
                //获取平台日
                Date sysDate = DateUtil.platDateFromCache();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String lastDayHold = sdf.format(sysDate);
                //获得第一个-的位置
                int index=lastDayHold.indexOf("-");
                //根据第一个点的位置 获得第二个-的位置
                int index1=lastDayHold.indexOf("-", index+1);
                //获取平台日的月份
                String month = lastDayHold.substring(index+1,index1);;
                param.put("month",month);
                this.dealMonth(param);
            }
            long count = svrChbConsignorDetailSubExportTaskMapper.countExportNum(param);
            svrChbConsignorDetailSubExportCommonService.export(count,param,batchLog);
        }
        logInfoBatchEnd();
    }

    /**
     * 处理月份取得当前月份的最后一天
     * @param paramMap
     * @return
     */
    public void dealMonth(Map<String, Object> paramMap) {
        String monthString = paramMap.get("month").toString();
        //获取平台日
        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysDate);
        int year = calendar.get(Calendar.YEAR);
        int month =  Integer.parseInt(monthString);
        String lastDayHold = this.getLastDayOfMonth1(year,month);
        paramMap.put("lastDayHold",lastDayHold);
    }

    /**
     * 获取指定年月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month); //设置当前月的上一个月
        int lastDay = cal.getMinimum(Calendar.DATE); //获取月份中的最小值，即第一天
        cal.set(Calendar.DAY_OF_MONTH, lastDay - 1); //上月的第一天减去1就是当月的最后一天
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
}
