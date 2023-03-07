package com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbTypeMappingExMapper;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.assetposition.constants.PbcAssetPositionConstants;
import com.joyintech.tams.svreport.pbc.classic.data.service.PbcClassicsUtil;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dto.PbcPrdAccountSub;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao.ZG01PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.common.service.PbcClassicsSubService;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 资管产品基本信息(ZG01)生成登记 service
 * @author yuluan
 * @version 1.7 2019年06月04日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ZG01PbcPrdBaseinfoSubService extends PbcClassicsSubService<ZG01PbcPrdBaseinfoSub>{

	@Autowired
	private ZG01PbcPrdBaseinfoSubMapper zg01PbcPrdBaseinfoSubMapper;
	@Autowired
	private SqlSessionTemplate template;
	/**
	 * 映射配置mapper
	 */
	@Autowired
	private ChbTypeMappingExMapper chbTypeMappingExMapper;
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PbcDataCheckUtil pbcDataCheckUtil;

	public final static String PBC_PROD_BASEINFO_SEQCODE="PBCPRDBASEINFO";
	@Override
	public void genReport(Date executeDate, List<String> prodIdList) {
		generateRegistData(executeDate,prodIdList);

	}

	public void generateRegistData(Date executeDate, List<String> prodIdList){
		Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();

		//查询人行产品资产池的数据
		List<ZG01PbcPrdBaseinfoSub> list=zg01PbcPrdBaseinfoSubMapper.selectAllPbcProdId();
		Map<String,String> map= list.stream().collect(Collectors.toMap(ZG01PbcPrdBaseinfoSub::getProdId,ZG01PbcPrdBaseinfoSub::getPbcProdId ));
		//发行机构代码，发行机构名称
		ZG01PbcPrdBaseinfoSub dto=zg01PbcPrdBaseinfoSubMapper.selectbankCode();

		//查询需要生成登记的数据
        ZG01PbcPrdBaseinfoSub qmodle=new ZG01PbcPrdBaseinfoSub();
		qmodle.setStatusDate(executeDate);
		qmodle.setProdList(prodIdList);
		List<ZG01PbcPrdBaseinfoSub> needRegistSubList=zg01PbcPrdBaseinfoSubMapper.selectNeedRegisteData(qmodle);
		if(CollectionUtils.isEmpty(needRegistSubList)){
			return;
		}
		List<ChbTypeMappingEx>  subjectNoMappingList = chbTypeMappingExMapper
				.selectByMappingcode(PbcAssetPositionConstants.MAPPINGCODE_DEPT_CODE_NEW);
		Map<String,ChbTypeMappingEx> inDepCodeMap = subjectNoMappingList.stream().collect(Collectors.toMap(ChbTypeMappingEx::getObjfield1, a -> a,(k1,k2)->k1));
		Map<String,ZG01PbcPrdBaseinfoSub> regMap = new HashMap<String,ZG01PbcPrdBaseinfoSub>();
		Map<String,String > pbcProdIdMap = new HashMap<String,String>();
		//查询已生成人行产品代码的数据
		List<ZG01PbcPrdBaseinfoSub> allPbcProdIdlist= new ArrayList<ZG01PbcPrdBaseinfoSub>();
		int batchCount = Integer.parseInt( null == CacheUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"50" : CacheUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));//每批commit的个数
		int batchLastIndex = batchCount;//每批最后一个的下标
		for(int index = 0; index < needRegistSubList.size();){
			if(batchLastIndex > needRegistSubList.size()-1){
				batchLastIndex = needRegistSubList.size();
				allPbcProdIdlist.addAll(zg01PbcPrdBaseinfoSubMapper.selectAllRegisteData(needRegistSubList.subList(index, batchLastIndex)));
				break;//数据插入完毕,退出循环
			}else{
				allPbcProdIdlist.addAll(zg01PbcPrdBaseinfoSubMapper.selectAllRegisteData(needRegistSubList.subList(index, batchLastIndex)));
				index = batchLastIndex;//设置下一批下标
				batchLastIndex = index + batchCount;
			}
		}
		if(!CollectionUtils.isEmpty(allPbcProdIdlist)){
			for(ZG01PbcPrdBaseinfoSub regdto : allPbcProdIdlist){
				pbcProdIdMap.put(regdto.getProdId(), regdto.getPbcProdId());
			}
		}

		//查询已报送的数据
		List<ZG01PbcPrdBaseinfoSub> reglist= new ArrayList<ZG01PbcPrdBaseinfoSub>();
		int batchLastIndex2 = batchCount;//每批最后一个的下标
		for(int index = 0; index < needRegistSubList.size();){
			if(batchLastIndex2 > needRegistSubList.size()-1){
				batchLastIndex2 = needRegistSubList.size();
				reglist.addAll(zg01PbcPrdBaseinfoSubMapper.selectAlReadyRegisteData(needRegistSubList.subList(index, batchLastIndex2)));
				break;//数据插入完毕,退出循环
			}else{
				reglist.addAll(zg01PbcPrdBaseinfoSubMapper.selectAlReadyRegisteData(needRegistSubList.subList(index, batchLastIndex2)));
				index = batchLastIndex2;//设置下一批下标
				batchLastIndex2 = index + batchCount;
			}
		}
		if(!CollectionUtils.isEmpty(reglist)){
			for(ZG01PbcPrdBaseinfoSub regdto : reglist){
				regMap.put(regdto.getProdId(), regdto);
			}
		}
		// 产品发行登记插入列表
		List<ZG01PbcPrdBaseinfoSub> issueInsertDataList = new ArrayList<ZG01PbcPrdBaseinfoSub>();
		// 产品发行登记删除列表
		List<ZG01PbcPrdBaseinfoSub> issueDeleteDataList = new ArrayList<ZG01PbcPrdBaseinfoSub>();
		// 处理产品发行登记
		for(ZG01PbcPrdBaseinfoSub sub : needRegistSubList){

			if(inDepCodeMap.containsKey(sub.getInDepCode())){
				sub.setInDepCode(inDepCodeMap.get(sub.getInDepCode()).getObjfield2());
			}else {
				sub.setInDepCode(null);
			}
			String pbcProdId;
			//人行资产池存在外部产品代码
			if(map.containsKey(sub.getProdId())&&!pbcProdIdMap.containsKey(sub.getProdId())){
				pbcProdId=map.get(sub.getProdId());
				//报送检查用字段
				sub.setPbcPordIdOut(pbcProdId);
			}else if(pbcProdIdMap.containsKey(sub.getProdId())){
				pbcProdId=pbcProdIdMap.get(sub.getProdId());
				if(pbcProdId.contains("INS")){
                     //报送检查用字段
					sub.setPbcPordIdOut(pbcProdId.substring(3));
				}else{
					sub.setPbcPordIdOut(pbcProdId);
				}
			}else{
				pbcProdId=PbcClassicsUtil.getPbcProdId(sub.getRegistBankCode(), sub.getProfitType(), PBC_PROD_BASEINFO_SEQCODE,sub.getRaiseVdate(),1);
				//插入生成的人行产品代码
				ZG01PbcPrdBaseinfoSub zg01dto = new ZG01PbcPrdBaseinfoSub();
				zg01dto.setProdId(sub.getProdId());
				zg01dto.setPbcProdId(pbcProdId);
				//资产池产品代码不存在，则插入生成的产品代码
				if(map.get(sub.getProdId())==null){
					int i = zg01PbcPrdBaseinfoSubMapper.insertPbcProdId(zg01dto);
				}
				sub.setPbcPordIdOut(pbcProdId);
			}
			sub.setPbcProdId(pbcProdId);
			sub.setRegistSeqno(IDGenerator.generate(36));
			sub.setCreateUser(CommonUtil.getUserName());
			sub.setUpdateUser(CommonUtil.getUserName());
			sub.setCreateDept(CommonUtil.getDeptCode());
			sub.setOrgCode(CommonUtil.getOrgCode());
			sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
			sub.setStatusDate(executeDate);
			sub.setIssuePartyId(dto.getIssuePartyId());
			sub.setIssuePartyName(dto.getIssuePartyName());
			if(sub.getOutDepCtry()!=null){
				//多个用,切开
				String [] outDepCtrys=sub.getOutDepCtry().split(GlobalConstant.COMMA);
				String outDepCtry="";
				int i=0;
				for(String single:outDepCtrys){
					i++;
					single="000"+single;
					outDepCtry=outDepCtry+ single;
					if(i<outDepCtrys.length){
						outDepCtry=outDepCtry+ GlobalConstant.COMMA;
					}
				}
				sub.setOutDepCtry(outDepCtry);
			}

			if (StringUtil.isNotEmpty(sub.getCreditType())){
				//多个用,切开
				String [] creditTypes=sub.getCreditType().split(GlobalConstant.COMMA);
				String creditType="";
				String creditMode="";
				int i=0;

				for(String single:creditTypes){
					i++;
					if(single.equals("01")){
						creditType=creditType+"2";
					}
					if(single.equals("02")){
						creditType=creditType+"3";
					}if(single.equals("03")){
						creditType=creditType+"4";
					}
					if(single.equals("04")){
						creditType=creditType+"1";
					}if(single.equals("05")){
						creditType=creditType+"6";
					}
					if(i<creditTypes.length){
						creditType=creditType+ GlobalConstant.COMMA;
					}
					if(sub.getCreditMode()!=null){
						creditMode=sub.getCreditMode();
						for(int j=1;j<creditTypes.length;j++){
							creditMode=creditMode+ GlobalConstant.COMMA+sub.getCreditMode();
						}
					}

				}
				sub.setCreditType(creditType);
				sub.setCreditMode(creditMode);
			}
			// 产品发行登记报送时点:募集起始日期
			if(sub.getVdate()!=null){
				List<Date> issueDateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG01资管产品基本信息.getValue(),
						DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
						sub.getVdate(),SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
				if (CollectionUtils.isNotEmpty(issueDateList)) {
					sub.setPbcSubmitDeadline(issueDateList.get(0));
					sub.setSubmitDeadline(issueDateList.get(1));
				}
			}
			if (regMap.get(sub.getProdId()) != null) {
				issueDeleteDataList.add(sub);
				//根据基础表数据状态判断生成登记数据状态
				if(DictCodeListExt1.维护数据状态_删除.getValue().equals(sub.getStatus())){
					sub.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
				}else{
					// 判断要素表数据有没有修改
					if(compareIssueRegData(regMap.get(sub.getProdId()),sub)){
						continue;
					}else{
						sub.setStatus(DictCodeListExt2.报送数据状态_修改.getValue());
					}
				}
				// 准备插入处理未报送的数据
				issueInsertDataList.add(sub);
			}else{
				// 基础表数据状态为删除状态，且登记表无对应数据则不登记
				if (DictCodeListExt1.维护数据状态_删除.getValue().equals(sub.getStatusInfo())
					|| DictCodeListExt1.维护数据状态_删除.getValue().equals(sub.getStatusDnfo())){
					issueDeleteDataList.add(sub);
					continue;
				} else {
					issueDeleteDataList.add(sub);
					// 准备插入处理未报送的数据
					issueInsertDataList.add(sub);
					sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
				}
			}
			//新建
			if(sub.getStatus().equals("01")){
				sub.setInfoType("1");
			}else if(sub.getStatus().equals("02")){
				sub.setInfoType("2");
				if(sub.getDnfoReason()!=null){

				}
				//拼接变更原因
				String change01=sub.getDnfoReason();
				String change02=sub.getInfoReason();
				if(change01!=null && change02==null){
					sub.setChangeReason(change01);
				}else if(change01==null && change02!=null){
					sub.setChangeReason(change02);
				}else if(change01!=null && change02!=null){
					sub.setChangeReason(change01+";"+change02);
				}
				if(sub.getUpdateTimeInfo()!=null){
					sub.setCdate(sub.getUpdateTimeInfo());
				}else {
					sub.setCdate(sub.getUpdateTimeDnfo());
				}
			}
		}
		// 删除、插入发行登记
		logger.info("采集平台资管产品基本信息：报表生成校验 开始----------");
		List<ZG01PbcPrdBaseinfoSub> listData = pbcDataCheckUtil.getTrueData(issueInsertDataList, null, DictCodeListExt2.采集平台资管产品基本信息.getValue(), true, "1", ZG01PbcPrdBaseinfoSub.class);
		logger.info("采集平台资管产品基本信息：报表生成校验 结束----------");
		dealResultIssueCommitSingle(issueDeleteDataList,listData);

		//入库
	}

	/**
	 * 功能:带提交的方式单条处理数据结果，入库
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年3月7日
	 * @param
	 *
	 */
	private void dealResultIssueCommitSingle(List<ZG01PbcPrdBaseinfoSub> deleteDataList,List<ZG01PbcPrdBaseinfoSub> insertDataList){
		if (CollectionUtils.isNotEmpty(deleteDataList)) {
			int batchCount = Integer.parseInt( null == CacheUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"50" : CacheUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));//每批commit的个数
			int batchLastIndex = batchCount;//每批最后一个的下标
			for(int index = 0; index < deleteDataList.size();){
				if(batchLastIndex > deleteDataList.size()-1){
					batchLastIndex = deleteDataList.size();
					zg01PbcPrdBaseinfoSubMapper.deleteByProdid(deleteDataList.subList(index, batchLastIndex));
					break;//数据插入完毕,退出循环
				}else{
					zg01PbcPrdBaseinfoSubMapper.deleteByProdid(deleteDataList.subList(index, batchLastIndex));
					index = batchLastIndex;//设置下一批下标
					batchLastIndex = index + batchCount;
				}
			}
		}
		if (CollectionUtils.isNotEmpty(insertDataList)) {
			int count = 0;
			SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
			try{
				ZG01PbcPrdBaseinfoSubMapper batchMapper =  session.getMapper(ZG01PbcPrdBaseinfoSubMapper.class);
				for(ZG01PbcPrdBaseinfoSub asset : insertDataList){
					batchMapper.insert(asset);
					count++;
					if(count%1000==0){
						session.commit();
						// 清理缓存，防止溢出
						session.clearCache();
					}
				}
				session.commit();
				// 清理缓存，防止溢出
				session.clearCache();
			}
			catch(Exception e){
				session.rollback();
				logger.error(e.getMessage(),e);
			}
			finally {
				session.close();
			}
		}
	}
	/**
	 * @Description 判断要素表中登记数据是否有变
	 * @param dto
	 * @param regdto
	 * @return boolean
	 * @version 1.7 2019年5月22日
	 */
	public boolean compareIssueRegData(ZG01PbcPrdBaseinfoSub dto,ZG01PbcPrdBaseinfoSub regdto){
        ZG01PbcPrdBaseinfoSub compare1 = genIssueDto(dto);
        ZG01PbcPrdBaseinfoSub compare2 = genIssueDto(regdto);
		if(JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))){
			return true;
		}else{
			return false;
		}
	}
	private ZG01PbcPrdBaseinfoSub genIssueDto(ZG01PbcPrdBaseinfoSub dto){
        ZG01PbcPrdBaseinfoSub gendto = new ZG01PbcPrdBaseinfoSub();
		gendto.setProdName(dto.getProdName());
		gendto.setPbcProdType(dto.getPbcProdType());
		gendto.setIvstorientation(dto.getIvstorientation());
		gendto.setProdBrand(dto.getProdBrand());
		gendto.setProdStage(dto.getProdStage());
		gendto.setRaiseCcy(dto.getRaiseCcy());
		gendto.setCashPrinCcy(dto.getCashPrinCcy());
		gendto.setCashProfCcy(dto.getCashProfCcy());
		gendto.setRaiseType(dto.getRaiseType());
		gendto.setManageType(dto.getManageType());
		gendto.setOperateMode(dto.getOperateMode());
		gendto.setRaiseVdate(dto.getRaiseVdate());
		gendto.setRaiseEnddate(dto.getRaiseEnddate());
		gendto.setTermRightFlag(dto.getTermRightFlag());
		gendto.setCusRedeemFlag(dto.getCusRedeemFlag());
		gendto.setCreditFlag(dto.getCreditFlag());
		gendto.setCreditType(dto.getCreditType());
		gendto.setCreditMode(dto.getCreditMode());
		gendto.setInDepName(dto.getInDepName());
		gendto.setInDepCode(dto.getInDepCode());
		gendto.setOutDepCtry(dto.getOutDepCtry());
		gendto.setOutDepName(dto.getOutDepName());
		gendto.setVdate(dto.getVdate());
		gendto.setMdate(dto.getMdate());
		gendto.setFiduciaryDuty(dto.getFiduciaryDuty());
		gendto.setIsConstracte(dto.getIsConstracte());
		gendto.setTransferType(dto.getTransferType());
		gendto.setCashManaCate(dto.getCashManaCate());
		gendto.setCrossBorderFlag(dto.getCrossBorderFlag());
		gendto.setIsPropertyFlag(dto.getIsPropertyFlag());
		gendto.setBaseInfoSign(dto.getBaseInfoSign());
		return gendto;
	}










}