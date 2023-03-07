package com.joyintech.tams.batch.svreport.pbc.zgdatfile.service;

import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.batch.report.autosendcb.common.service.AutoSendCBBaseService;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.dao.PbcZGDatFileMapper;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.dto.PbcDataCollectDatFileDto;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.service.PbcZGDatFileCommonService;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.service.PbcZGDatFileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Description 采集平台人行202211升级ZG06 dat文件生成批处理
 * @author yanya
 * @version 1.0 2022-11-14
 * @since 1.0
 */
@Service(value = "svrPbcZG06MainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SvrPbcZG06MainService extends AutoSendCBBaseService{

	/**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PbcZGDatFileCommonService pbcZGDatFileCommonService;

	@Autowired
	private SvrPbcZGDatFileCommonSevice svrPbcZGDatFileCommonSevice;

	@Autowired
	private PbcZGDatFileMapper pbcZGDatFileMapper;
    
	public Map<String, List<String>> generatePbcDatFile(Date batchDate,List<String> prodList) {
		Map<String, List<String>> mapPara = new HashMap<String, List<String>>();
		// 设置批处理bean
		setBatchBeanName(BatchBeanName.采集平台资产收益权明细信息ZG06dat生成批处理);
		logInfoBatchStart();
		try {
			//1.判断跑批日期，如果不是1号，批处理不执行。
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(batchDate);
			if (calendar.get(Calendar.DATE) != 1) {  //每月1日生成上月的数据
				logger.info("采集平台资产收益权明细信息ZG06dat生成批处理：每月1日生成上月的数据！");
			}
			else {
				//查询是否有对应的未报送数据
				int dataCout = pbcZGDatFileMapper.searchZG06Count(DateUtil.format(DateUtil.addDay(batchDate, -1), DateUtil.MONTH_OTHER_FORMAT));
				if(dataCout==0){
					logger.info("采集平台资管产品资产负债信息ZG06dat生成批处理:没有未报送数据！");
					return mapPara;
				}
				String type = PbcZGDatFileHelper.DATAFILE_TYPE_ZG06;
				//删除
				pbcZGDatFileCommonService.deleteUnSendDataFile(batchDate, type);
				PbcDataCollectDatFileDto record = svrPbcZGDatFileCommonSevice.buildPbcDataCollectDatFileDto(batchDate, type);
				/**根据文件名删除 start*/
				pbcZGDatFileMapper.deletePbcDataCollectDatFileByName(record.getDatFileName());
				/**根据文件名删除 end*/
				pbcZGDatFileMapper.insertPbcDataCollectDatFile(record);
				Map<String, Object> searchParam = new HashMap<String, Object>();
				svrPbcZGDatFileCommonSevice.buildZGDetailMap(searchParam, batchDate, record.getUuid(), type);
				/** 调整明细表不插入dat数据，只更新sub uuid**/
//				int insNum = pbcZGDatFileMapper.insertZG06DetailDatFromSub(searchParam);
//				logger.debug("采集平台资产收益权明细信息（ZG06）dat文件生成批处理明细数量：" + insNum);
				int updNum = pbcZGDatFileMapper.updateZG06SubDatUUID(searchParam);
				logger.info("采集平台资产收益权明细信息（ZG06）dat文件生成批处理更新明细数量：" + updNum);
			}
		} catch (Exception e) {
			logger.error("采集平台资产收益权明细信息（ZG06）dat文件生成批处理main方法Error",e);
			throw new BusinessException(e);
		}
		logInfoBatchEnd();
		return mapPara;
	}

}
