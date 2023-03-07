/**
 * 文件名：PbcClassicsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chenxc
 * 修改时间：2019年6月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.pbc.classic.zgdatfile.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.dao.PbcZGDatFileMapper;
import com.joyintech.tams.svreport.pbc.classic.zgdatfile.dto.PbcDataCollectDatFileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 采集平台ZG dat文件生成通过service
 * @author yanya
 * @version 1.0 2022-11-12
 * @see PbcZGDatFileCommonService
 * @since 1.0
 */
@Service
public class PbcZGDatFileCommonService {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(PbcZGDatFileCommonService.class);

	@Autowired
	private PbcZGDatFileMapper pbcZGDatFileMapper;

	/**
	 * 删除本日的数据
	 * @param batchDate 跑批日期
	 * @param type zg类型
	 * @return
	 */
	public String deleteUnSendDataFile(Date batchDate, String type) {
		// 1.查询上日及本日生成的dat文件留痕信息，获得未发送状态数据
		Map<String, Object> searchParam = new HashMap<String, Object>();
		if(StringUtil.equals("ZG04",type)||
				StringUtil.equals("ZG05",type)||
				StringUtil.equals("ZG06",type)||
				StringUtil.equals("ZG07",type)||
				StringUtil.equals("ZG08",type)||
				StringUtil.equals("ZG09",type)||
				StringUtil.equals("ZG10",type)||
				StringUtil.equals("ZG11",type)
		){
			searchParam.put("createDate", batchDate);
		}
		searchParam.put("reportStatus", DictCodeListExt2.报送状态_未报送.getValue());
		//searchParam.put("status", DictCodeListExt2.数据状态_有效.getValue());
		searchParam.put("reportName", type);
		List<PbcDataCollectDatFileDto> fileList = pbcZGDatFileMapper.findPbcDataCollectDatFile(searchParam);
		if (CollectionUtils.isEmpty(fileList)) {
			return null;
		}
		// 2.遍历生成文件list，删除未发送状态数据的留痕记录
		for (PbcDataCollectDatFileDto dto : fileList) {
			//不往明细表插入数据，这里也不错删除处理了
//			deleteZGDetailDatByType(type, dto.getUuid());
			pbcZGDatFileMapper.deletePbcDataCollectDatFile(dto);
		}
		return null;
	}

	/**
	 * 根据类型及uuid删除明细数据
	 * @param type
	 * @param detailid
	 * @return
	 */
	public int deleteZGDetailDatByType(String type, String detailid) {
		int num = 0;
		switch (type) {
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG01:
				num = pbcZGDatFileMapper.deleteZG01DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG02:
				num = pbcZGDatFileMapper.deleteZG02DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG03:
				num = pbcZGDatFileMapper.deleteZG03DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG04:
				num = pbcZGDatFileMapper.deleteZG04DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG05:
				num = pbcZGDatFileMapper.deleteZG05DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG06:
				num = pbcZGDatFileMapper.deleteZG06DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG07:
				num = pbcZGDatFileMapper.deleteZG07DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG08:
				num = pbcZGDatFileMapper.deleteZG08DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG09:
				num = pbcZGDatFileMapper.deleteZG09DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG10:
				num = pbcZGDatFileMapper.deleteZG10DetailDat(detailid);
				break;
			case PbcZGDatFileHelper.DATAFILE_TYPE_ZG11:
				num = pbcZGDatFileMapper.deleteZG11DetailDat(detailid);
				break;
			default:
		}
		return num;
	}

}
