package com.joyintech.tams.batch.svreport.pbc.specificcounterparty.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.pbc.specificcounterparty.service.SpcCounterPartyInterfService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 特定目的载体对手方信息维护接入批
 *
 * @author yangxianjiu
 * @date 2022/11/7
 */
@Service(value = "spcCounterPartyImpMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SpcCounterPartyImpMainService extends AutoSendCBBaseService {

    @Resource
    private SpcCounterPartyInterfService spcCounterPartyInterfService;

    /**
     * 特定目的载体对手方信息 生成
     * @param executeDate 批启动日期
     */
    public void generateCounterPartyData(Date executeDate) {
        spcCounterPartyInterfService.generateData(executeDate);
    }
}
