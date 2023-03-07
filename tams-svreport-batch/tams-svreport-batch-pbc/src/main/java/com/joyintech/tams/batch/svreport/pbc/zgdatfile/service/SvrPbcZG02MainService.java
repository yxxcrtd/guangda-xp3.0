package com.joyintech.tams.batch.svreport.pbc.zgdatfile.service;

import com.joyintech.framework.exception.BusinessException;
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
 * @Description 采集平台人行202211升级ZG02 dat文件生成批处理
 * @author yanya
 * @version 1.0 2022-11-14
 * @since 1.0
 */
@Service(value = "svrPbcZG02MainService")
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class SvrPbcZG02MainService extends AutoSendCBBaseService{

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
		setBatchBeanName(BatchBeanName.采集平台资管产品初始募集信息ZG02dat生成批处理);
		logInfoBatchStart();
		try {
			//查询是否有对应的未报送数据
			int dataCout = pbcZGDatFileMapper.searchZG02Count();
			if(dataCout==0){
				logger.info("人行资管产品初始募集信息（ZG02）dat文件生成批处理没有未报送数据！");
				return mapPara;
			}
			String type = PbcZGDatFileHelper.DATAFILE_TYPE_ZG02;
			//删除
			pbcZGDatFileCommonService.deleteUnSendDataFile(batchDate, type);
			/**调整,更新sub前先把多余的主表未报送记录删除**/
			//先查出登记表未报送数据对应的datUUID
			List<String> uuidList = pbcZGDatFileMapper.searchZG02DatUuid();
			if(uuidList!=null&&uuidList.size()>0){
				int delNum = pbcZGDatFileMapper.deletePbcDataCollectDatFileByUuid(uuidList);
				logger.info("人行资管产品初始募集信息（ZG02）dat文件生成批处理删除主表数量：" + delNum);
			}
			PbcDataCollectDatFileDto record = svrPbcZGDatFileCommonSevice.buildPbcDataCollectDatFileDto(batchDate, type);
			pbcZGDatFileMapper.insertPbcDataCollectDatFile(record);
			Map<String, Object> searchParam = new HashMap<String, Object>();
			svrPbcZGDatFileCommonSevice.buildZGDetailMap(searchParam, batchDate, record.getUuid(), type);

			/** 调整明细表不插入dat数据，只更新sub uuid**/
//			int insNum = pbcZGDatFileMapper.insertZG02DetailDatFromSub(searchParam);
//			logger.debug("人行资管产品初始募集信息（ZG02）dat文件生成批处理明细数量：" + insNum);
			int updNum = pbcZGDatFileMapper.updateZG02SubDatUUID(searchParam);
			logger.info("人行资管产品初始募集信息（ZG02）dat文件生成批处理更新明细数量：" + updNum);
		} catch (Exception e) {
			logger.error("人行资管产品初始募集信息（ZG02）dat文件生成批处理main方法Error",e);
			throw new BusinessException(e);
		}
		logInfoBatchEnd();
		return mapPara;
	}

}
