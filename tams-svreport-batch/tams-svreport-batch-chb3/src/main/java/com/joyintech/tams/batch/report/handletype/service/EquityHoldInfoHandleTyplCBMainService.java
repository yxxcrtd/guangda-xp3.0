package com.joyintech.tams.batch.report.handletype.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.handletype.service.EquityHandleTypeRegist001Service;
import com.joyintech.tams.report.submitmaintenance.equityinfo.service.EquityInfoDistinctService;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.service.InvestorsHoldInfoRegist001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class EquityHoldInfoHandleTyplCBMainService extends AutoSendCBBaseService{
	
	/**
	 * 投资者持有数量信息登记
	 *
	 */
	@Autowired
	private EquityHandleTypeRegist001Service equityHandleTypeRegist001Service;
	
    /**
     * 手工处理
     *
     * @see
     * @since 1.0
     */
    public void autoManualCB(Date excuteDate) {
        setBatchBeanName(BatchBeanName.投资者类别转换轮巡批处理);
        logInfoBatchStart();
        equityHandleTypeRegist001Service.excuteHandleType(excuteDate);
        logInfoBatchEnd();
    }
}
