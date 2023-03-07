package com.joyintech.tams.batch.svreport.pbc.zg09assetdebtterm.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.service.ZG09PbcAssetDebtTermBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class ZG09PbcAssetDebtTermMainService extends AutoSendCBBaseService{

    
    @Autowired
    private ZG09PbcAssetDebtTermBatchService zG09PbcAssetDebtTermBatchService;

    /**
     * 功能:生成人行报表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param batchDate 跑批日期
     * @throws Exception
     */
    public void generateReport(Date batchDate) {
        zG09PbcAssetDebtTermBatchService.processData(batchDate);
    }
}
