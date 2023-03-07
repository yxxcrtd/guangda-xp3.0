package com.joyintech.tams.batch.svreport.prodinfo.service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.common.SvrProductsBalanceFileImpService;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 产品要素接入批处理
 * @author lingxq
 * @version 1.0 2019年2月19日
 * @see prodDealImpService
 * @since 1.0
 */
@Service(value = "svrProductsBalanceMainService")
@Scope("prototype")
public class SvrProductsBalanceMainService extends AutoSendCBBaseService{
	 /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SvrProductsBalanceFileImpService svrProductsBalanceFileImpService;
	 public List<String> generateProdInfoData(Date batchDate,List<String> prodIdList) {
	    	// 设置批处理bean
	    	setBatchBeanName(BatchBeanName.审计署报表接口数据接入);
			logInfoBatchStart();
			List<String> resultList= new ArrayList<String>();
			  ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(com.joyintech.tams.common.InterfDefine.审计署接口数据.getCode());
	            if (interfConfig == null){
	                logger.info("CHB_INTERF_CONFIG表中无配置项！");
	            }
	            String contentSrc = interfConfig.getContentSrc();
	            // 产品要素
	            logger.debug("审计署接入批处理开始");
				 String result = svrProductsBalanceFileImpService.execute(batchDate,com.joyintech.tams.common.InterfDefine.审计署接口数据.getCode());
				 if ("fail".equals(result)){
					 throw new BusinessException("fail");
				 }
	            
			logger.debug("审计署接入批处理结束");
			logInfoBatchEnd();
			return resultList;
	    }
}
