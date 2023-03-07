package com.joyintech.tams.batch.svreport.pbc.specificinvestorshold.service;

import com.joyintech.tams.svreport.pbc.specificinvestorshold.service.SpcInvestorsHoldTaskService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 特定目的载体持有信息维护(投资者) 生成批
 *
 * @author yangxianjiu
 * @date 2022/11/12
 */
@Service(value = "spcInvestorsHoldMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SpcInvestorsHoldMainService {

    @Resource
    private SpcInvestorsHoldTaskService spcInvestorsHoldTaskService;

    /**
     * 生成 特定目的载体持有信息维护(投资者) 数据
     * @param executeDate
     */
    public void generateInvestorsHoldData(Date executeDate) {
        spcInvestorsHoldTaskService.generateData(executeDate);
    }
}
