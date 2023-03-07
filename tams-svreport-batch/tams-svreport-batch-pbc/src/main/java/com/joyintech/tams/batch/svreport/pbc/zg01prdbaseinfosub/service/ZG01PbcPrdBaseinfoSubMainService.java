package com.joyintech.tams.batch.svreport.pbc.zg01prdbaseinfosub.service;

import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.service.PbcPrdAccountSubService;
import com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.service.PbcPrdAccountEndSubService;
import com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service.PbcPrdAssetDebtSubService;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubService;
import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.service.PbcPrdCashRaiseSubService;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.serivce.PbcPrdInfoEndSubService;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.service.PbcPrdInitRaiseSubService;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service.ZG01PbcPrdBaseinfoSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service(value = "zG01PbcPrdBaseinfoSubMainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class ZG01PbcPrdBaseinfoSubMainService extends AutoSendCBBaseService{


    @Autowired
    private ZG01PbcPrdBaseinfoSubService zg01PbcPrdBaseinfoSubService;


    /**
     * 功能:生成人行单产品报表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月9日
     * @param batchDate 跑批日期
     * @param prodIdList 产品id
     * @param isEdit 是否是修改的数据
     * @throws Exception
     */
    public void generateReport(final Date batchDate,final List<String> prodIdList) {
        // 设置批处理bean
        setBatchBeanName(BatchBeanName.采集平台资管产品基本信息生成批处理);
        logInfoBatchStart();
    	 // 资管产品基本信息(ZG01)
        zg01PbcPrdBaseinfoSubService.genReport(batchDate, prodIdList);


        logInfoBatchEnd();
    }
}
