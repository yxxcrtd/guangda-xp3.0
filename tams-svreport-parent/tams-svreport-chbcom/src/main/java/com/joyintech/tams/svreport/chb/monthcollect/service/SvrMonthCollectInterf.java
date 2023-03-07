package com.joyintech.tams.svreport.chb.monthcollect.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.common.svreport.chb.constant.ProdConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.chb.monthcollect.dao.SvrMonthCollectInterfMapper;
import com.joyintech.tams.svreport.chb.monthcollect.dto.ChbMonthCollect;
import com.joyintech.tams.svreport.chb.monthcollect.dto.PrdPeriodyieldForMonthCollect;
import com.joyintech.tams.svreport.chb.monthcollect.dto.ccyExcharge;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrMonthCollectInterf extends BaseService{

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SvrMonthCollectInterfMapper svrMonthCollectInterfMapper;

	@Autowired
	private BatchInsertService batchInsertService;

	public void generateData(Date batchDate,Date batchDateStart ,Date batchDateEnd) {
		if(svrMonthCollectInterfMapper.checkAllMonth(DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd)) == 0){
			logger.info("请先执行明细汇总批处理，再执行月报生成批处理！");
		}
		int index=DateUtil.format(batchDate).lastIndexOf("-");
		String termid = DateUtil.format(batchDate).substring(0, index);
		if(svrMonthCollectInterfMapper.checkSubmission(termid) != 0){
			logger.info("已报送的月报，不再生成报送数据！");
			return;
		}
		if(svrMonthCollectInterfMapper.checkLastDay(DateUtil.format(batchDateEnd)) == 0){
			logger.info("本月的明细信息不全，请确认数据是否完善，并请先执行明细汇总批处理！");
		}
		if(svrMonthCollectInterfMapper.checkHold(DateUtil.format(batchDateEnd)) == 0){
			logger.info("当月月末的持有信息不存在，请确认后重新跑批！");
		}
		//1、开始检数据
		logger.info("销售月报每月明细汇总信息检索开始------->>>>>>");
		Map<String,Map<String,Map<String,ChbMonthCollect>>> AllMap = this.getList(batchDate,batchDateStart,batchDateEnd);
		logger.info("销售月报每月明细汇总信息检索结束-------<<<<<<");

		if(AllMap.size() == 0){
			logger.info("没有检索到数据,批处理结束!");
			return;
		}
		//更新表
		logger.info("销售月报每月明细汇总信息入库开始------->>>>>>");
		dbData(AllMap,batchDate,batchDateStart ,batchDateEnd);
		logger.info("销售月报每月明细汇总信息入库结束-------<<<<<<");
	}

	/**
	 * 先删后插
	 */
	private void dbData(Map<String,Map<String,Map<String,ChbMonthCollect>>> AllMap,Date batchDate,Date batchDateStart ,Date batchDateEnd) {
		//获取本行登记代码
		String bankCode =CommonUtil.selectBankCode();
		if (AllMap.size() != 0) {
			//获取下个月的第一天
			Date nextMonFirDay = getFirstDayOfNextMonth(DateUtil.format(batchDate));
			//中债报送截止日
			Date chbSubmitDeadline = new Date();
			Date submitDeadline = new Date();
			List<Date> dateList = null;
			Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
			dateList = ChbDeadlineUtil.getChbEndDate(
					DictCodeListExt2.报表名称_销售信息月度统计表.getValue(), "01", dateMap, nextMonFirDay);
			if (!CollectionUtils.isEmpty(dateList)) {
				//获取中债截止日
				chbSubmitDeadline = dateList.get(0);
				submitDeadline = dateList.get(1);
			}
			int index=DateUtil.format(batchDate).lastIndexOf("-");
			String termid = DateUtil.format(batchDate).substring(0, index);
			int termidIndex=termid.lastIndexOf("-");
			String termidYYYY = termid.substring(0, termidIndex);
			String termidMM = termid.substring(termidIndex+1);
			String reportName = "销售数据月度统计表_"+ termidYYYY + "年" + termidMM + "月";
			//清除当月月报信息 若已报送则停止跑批
			logger.debug("当月汇总数据清除开始----------");
			//查询要删除的reportUUID
			List<String> list = svrMonthCollectInterfMapper.selectReportuuid(termid);
			if(list.size() > 0 && !list.isEmpty()) {
				// 根据reportuuid删除明细表
				svrMonthCollectInterfMapper.deleteDetail(list);
				//根据期数删除月报主表
				svrMonthCollectInterfMapper.deleteReport(termid);
			}
			logger.debug("当月汇总数据清除结束----------");

			logger.debug("销售月报每月明细汇总信息插入数据：插入开始----------");

			for(String key:AllMap.keySet()){
				//以投资者分类插入
				if(key.equals("holderType")) {
					String reporttype = "1_1";
					String reportuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
					svrMonthCollectInterfMapper.insertIntoReport(reporttype,DateUtil.format(submitDeadline),DateUtil.format(chbSubmitDeadline),reportuuid,termid,reportName,DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
					for (String flag : AllMap.get("holderType").keySet()) {
						for(String A : AllMap.get("holderType").get(flag).keySet()){
							String detailuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
							ChbMonthCollect detail = AllMap.get("holderType").get(flag).get(A);
							detail.setReportuuid(reportuuid);
							detail.setDetailuuid(detailuuid);
							detail.setReporttype("1_1");
							if(detail.getInstitutionCode().equals(bankCode)){
								detail.setIsConsign(DictCodeList.是否_中债_否.getValue());
							}else{
								detail.setIsConsign(DictCodeList.是否_中债_是.getValue());
							}
							detail = amtOrShares(detail);
							svrMonthCollectInterfMapper.insertIntoDetail(detail);
						}
					}
				}
				//以地区分类插入
				if(key.equals("area")) {
					String reporttype = "1_2";
					String reportuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
					svrMonthCollectInterfMapper.insertIntoReport(reporttype,DateUtil.format(submitDeadline),DateUtil.format(chbSubmitDeadline),reportuuid,termid,reportName,DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
					for (String flag : AllMap.get("area").keySet()) {
						for(String A : AllMap.get("area").get(flag).keySet()){
							String detailuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
							ChbMonthCollect detail = AllMap.get("area").get(flag).get(A);
							detail.setReportuuid(reportuuid);
							detail.setDetailuuid(detailuuid);
							detail.setReporttype("1_2");
							if(detail.getInstitutionCode().equals(bankCode)){
								detail.setIsConsign(DictCodeList.是否_中债_否.getValue());
							}else{
								detail.setIsConsign(DictCodeList.是否_中债_是.getValue());
							}
							detail = amtOrShares(detail);
							svrMonthCollectInterfMapper.insertIntoDetail(detail);
						}
					}
				}
				//以渠道插入
				if(key.equals("institution")) {
					String reporttype = "1_3";
					String reportuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
					svrMonthCollectInterfMapper.insertIntoReport(reporttype,DateUtil.format(submitDeadline),DateUtil.format(chbSubmitDeadline),reportuuid,termid,reportName,DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
					for (String flag : AllMap.get("institution").keySet()) {
						for(String A : AllMap.get("institution").get(flag).keySet()){
							String detailuuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
							ChbMonthCollect detail = AllMap.get("institution").get(flag).get(A);
							detail.setReportuuid(reportuuid);
							detail.setDetailuuid(detailuuid);
							detail.setReporttype("1_3");
							if(detail.getInstitutionCode().equals(bankCode)){
								detail.setIsConsign(DictCodeList.是否_中债_否.getValue());
							}else{
								detail.setIsConsign(DictCodeList.是否_中债_是.getValue());
							}
							detail = amtOrShares(detail);
							svrMonthCollectInterfMapper.insertIntoDetail(detail);
						}
					}
				}
				logger.debug("销售月报每月明细汇总信息插入数据：插入结束----------");
			}
		}
	}

	/**
	 * 检索并处理数据
	 * @param batchDate
	 * @param batchDateStart
	 * @param batchDateEnd
	 * @return
	 */
	public Map<String,Map<String,Map<String,ChbMonthCollect>>> getList(Date batchDate,Date batchDateStart ,Date batchDateEnd) {
		logger.info("batchDateStart : {} batchDateEnd : {} ",DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
		//获取最新净值
		List<PrdPeriodyieldForMonthCollect> Periodyield =  svrMonthCollectInterfMapper.getPrdPeriodyield(DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));

		//汇总  总、净销售金额
		List<ChbMonthCollect> listAmountTotal = svrMonthCollectInterfMapper.selectAmountTotal(DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
		//获取正常的三合一与非三合一数据
		List<ChbMonthCollect> notChangeEquHoldList = svrMonthCollectInterfMapper.selectNotChangeEquHold(DateUtil.format(batchDateEnd));

		//delete by wangyaning 光大个性化改造 不需要考虑此场景 start
		//获取所有三合一数据 包含持有为非三合一但身份已变成三合一的数据
//		List<ChbMonthCollect> ThreeInOneList = svrMonthCollectInterfMapper.selectThreeInOneList(DateUtil.format(batchDateEnd));
		//获取当月持有check表中的关联主键
//		List<ChbMonthCollect> ChangeEquHoldList = svrMonthCollectInterfMapper.selectHolderNumFromCheck(DateUtil.format(batchDateEnd));
//		Map<String,ChbMonthCollect> ChangeEquHoldListMap = new HashMap<>();
//		for(ChbMonthCollect ChangeEquHold : ChangeEquHoldList){
//            String key = ChangeEquHold.getPrdRegistCode() + ChangeEquHold.getIndentifyMark()+ChangeEquHold.getCcy()+ChangeEquHold.getHoldDate();
//			ChangeEquHoldListMap.put(key,ChangeEquHold);
//		}
//		if(ThreeInOneList.size() != 0) {
//			for (ChbMonthCollect ThreeInOne:ThreeInOneList) {
//				String key = ThreeInOne.getPrdRegistCode() + ThreeInOne.getIndentifyMark()+ThreeInOne.getCcy()+ThreeInOne.getHoldDate();
//				if(ChangeEquHoldListMap.get(key) == null){
//					notChangeEquHoldList.add(ThreeInOne);
//				}
//			}
//		}
		//delete by wangyaning 光大个性化改造 不需要考虑此场景 end

		//报送表去除check表中识别标识相等的数据后再与check表合并
		List<ChbMonthCollect> List = new ArrayList<>();
		if(notChangeEquHoldList.size() != 0) {
			//插入临时表
			insertMonthTemp(notChangeEquHoldList);
			//通过临时表汇总
			List = svrMonthCollectInterfMapper.selectHolderNumExcheck(notChangeEquHoldList);
			//删除临时表
			svrMonthCollectInterfMapper.deleteAllMonthTemp();
		}
		List<ChbMonthCollect> listAmountTotalExList = new ArrayList<>();
//		通过五个主key来拼接数据
		for(ChbMonthCollect collect:listAmountTotal){
			for(ChbMonthCollect listCollect:List){
				if (collect.getPrdRegistCode().equals(listCollect.getPrdRegistCode()) && collect.getInstitutionCode().equals(listCollect.getInstitutionCode())
						&& collect.getAreaCode().equals(listCollect.getAreaCode()) && collect.getHolderType().equals(listCollect.getHolderType())
						&& collect.getCcy().equals(listCollect.getCcy())){
					collect.setBalanceShares(listCollect.getBalanceShares());
					collect.setBalanceAmt(listCollect.getBalanceAmt());
					collect.setCnyAmt(listCollect.getCnyAmt());
				}
			}
		}
		//不能拼接的List数据当做单条数据放在listAmountTotal中
		Map<String,ChbMonthCollect> listAmountTotalMap = new HashMap<String,ChbMonthCollect>();
		for(ChbMonthCollect collect:listAmountTotal){
			String totalFalg = collect.getPrdRegistCode() + collect.getInstitutionCode() + collect.getAreaCode() + collect.getHolderType() + collect.getCcy();
			if(listAmountTotalMap.get(totalFalg) == null){
				listAmountTotalMap.put(totalFalg,collect);
			}
		}
		for(ChbMonthCollect listCollect:List){
			String totalFalg = listCollect.getPrdRegistCode() + listCollect.getInstitutionCode() + listCollect.getAreaCode() + listCollect.getHolderType() + listCollect.getCcy();
			if(listAmountTotalMap.get(totalFalg) == null){
				listAmountTotalMap.put(totalFalg,listCollect);
			}
		}
		//获取新的拼接List组合
		for(String key :listAmountTotalMap.keySet()){
			listAmountTotalExList.add(listAmountTotalMap.get(key));
		}
		Map<String,BigDecimal> PeriodyieldMap= new HashMap<>();
		for(PrdPeriodyieldForMonthCollect dto:Periodyield){
			PeriodyieldMap.put(dto.getProdId(),dto.getProdValue());
		}
		//处理持有余额（持有份额*最新净值）
		dealBalanceMoney(PeriodyieldMap,listAmountTotalExList);

		//进行汇率转换
		listAmountTotal = this.currencyExchange(listAmountTotalExList,batchDateEnd,batchDateStart);
		// 报表保留6位,单位万元; 这里保留2位,单位元
		for (ChbMonthCollect collect: listAmountTotal) {
			// 持有余额
			Optional.ofNullable(collect.getBalanceAmt()).map(bd -> bd.setScale(2,BigDecimal.ROUND_HALF_UP)).ifPresent(collect::setBalanceAmt);
		}

		//取得银行月报信息
		List<ChbMonthCollect > chbPrdPosition = svrMonthCollectInterfMapper.selectChbPrdPosition(DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));

		Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();

		for(ChbMonthCollect collect : chbPrdPosition){
			if (!ProdConstants.CCY_CNY.equals(collect.getCcy())) {// 外币
				BigDecimal exchangeRate = ChbCcyConvertUtil.changeCnyMoney(collect.getCcy(), BigDecimal.ONE,
						batchDateEnd, rateMap);
				if (exchangeRate == null)
					exchangeRate = BigDecimal.ZERO;
				collect.setDirtyamtsum(collect.getDirtyamtsum().multiply(exchangeRate));
				collect.setCleanamtsum(collect.getCleanamtsum().multiply(exchangeRate));
				collect.setEndamtsumOth(collect.getEndamtsumOth().multiply(exchangeRate));
			}
		}


		//持有余额总和
		BigDecimal balanceAmtTypeAll= new BigDecimal("0");
		BigDecimal balanceAmtAreaAll= new BigDecimal("0");
		BigDecimal balanceAmtChannelAll= new BigDecimal("0");

		//总销售金额总和
		BigDecimal saleAmountTotalTypeAll= new BigDecimal("0");
		BigDecimal saleAmountTotalAreaAll= new BigDecimal("0");
		BigDecimal saleAmountTotalChannelAll= new BigDecimal("0");

		//净销售金额总和
		BigDecimal valueAmountTotalTypeAll= new BigDecimal("0");
		BigDecimal valueAmountTotalAreaAll= new BigDecimal("0");
		BigDecimal valueAmountTotalChannelAll= new BigDecimal("0");

		//以投资者类型汇总
		List<ChbMonthCollect >typeHolderNum = svrMonthCollectInterfMapper.typeHolderNum(DateUtil.format(batchDateEnd));//取得投资者数量
		Map<String,ChbMonthCollect> holderTypeCollect = new HashMap<String,ChbMonthCollect>();
		for(ChbMonthCollect a : listAmountTotal){
			String falg = a.getPrdRegistCode()+ ":" + a.getHolderType();
			if (holderTypeCollect.get(falg) == null){
				ChbMonthCollect bb = new ChbMonthCollect();
				bb.setInstitutionCode(a.getInstitutionCode());
				bb.setCcy(a.getCcy());
				bb.setIsConsign(a.getIsConsign());
				bb.setAreaCode(a.getAreaCode());
				bb.setCnyAmt(a.getCnyAmt());
				bb.setPrdRegistCode(a.getPrdRegistCode());
				bb.setHolderType(a.getHolderType());
				bb.setSaleAmountTotal(a.getSaleAmountTotal());
				bb.setValueAmountTotal(a.getValueAmountTotal());
				bb.setBalanceAmt(a.getBalanceAmt());
				bb.setBalanceShares(a.getBalanceShares());
				bb.setValueSharesTotal(a.getValueSharesTotal());
				bb.setSaleSharesTotal(a.getSaleSharesTotal());
				holderTypeCollect.put(falg, bb);
			}else{
				if(a.getSaleSharesTotal() != null) {
					if(holderTypeCollect.get(falg).getSaleSharesTotal() != null) {
						holderTypeCollect.get(falg).setSaleSharesTotal(holderTypeCollect.get(falg).getSaleSharesTotal().add(a.getSaleSharesTotal()));
					}else{
						holderTypeCollect.get(falg).setSaleSharesTotal(a.getSaleSharesTotal());
					}
				}
				if(a.getValueSharesTotal() != null){
					if(holderTypeCollect.get(falg).getValueSharesTotal() != null) {
						holderTypeCollect.get(falg).setValueSharesTotal(holderTypeCollect.get(falg).getValueSharesTotal().add(a.getValueSharesTotal()));
					}else{
						holderTypeCollect.get(falg).setValueSharesTotal(a.getValueSharesTotal());
					}
				}
				if(a.getSaleAmountTotal() != null) {
					if(holderTypeCollect.get(falg).getSaleAmountTotal() != null) {
						holderTypeCollect.get(falg).setSaleAmountTotal(holderTypeCollect.get(falg).getSaleAmountTotal().add(a.getSaleAmountTotal()));
					}else{
						holderTypeCollect.get(falg).setSaleAmountTotal(a.getSaleAmountTotal());
					}
				}
				if(a.getValueAmountTotal() != null){
					if(holderTypeCollect.get(falg).getValueAmountTotal() != null) {
						holderTypeCollect.get(falg).setValueAmountTotal(holderTypeCollect.get(falg).getValueAmountTotal().add(a.getValueAmountTotal()));
					}else{
						holderTypeCollect.get(falg).setValueAmountTotal(a.getValueAmountTotal());
					}
				}
				if (a.getBalanceAmt() != null) {
					if(holderTypeCollect.get(falg).getBalanceAmt() != null) {
						holderTypeCollect.get(falg).setBalanceAmt(holderTypeCollect.get(falg).getBalanceAmt().add(a.getBalanceAmt()));
					}else{
						holderTypeCollect.get(falg).setBalanceAmt(a.getBalanceAmt());
					}
				}
				if (a.getBalanceShares() != null) {
					if(holderTypeCollect.get(falg).getBalanceShares() != null) {
						holderTypeCollect.get(falg).setBalanceShares(holderTypeCollect.get(falg).getBalanceShares().add(a.getBalanceShares()));
					}else{
						holderTypeCollect.get(falg).setBalanceShares(a.getBalanceShares());
					}
				}
			}
		}
		//赋值投资者数量
		for(String key :holderTypeCollect.keySet()){
			for(int m = 0; m < typeHolderNum.size();m++) {
				String flag = typeHolderNum.get(m).getPrdRegistCode() + ":" + typeHolderNum.get(m).getHolderType();
				if(flag.equals(key)){
					holderTypeCollect.get(flag).setHolderNum(typeHolderNum.get(m).getHolderNum());
				}
			}
		}
		//转换成以产品为key的父级map
		Map<String,Map<String,ChbMonthCollect>> holderTypeCollectMap = new HashMap<String,Map<String,ChbMonthCollect>>();
		for(String key: holderTypeCollect.keySet()){
			int index=key.lastIndexOf(":");
			String proId = key.substring(0, index);
			if(holderTypeCollectMap.get(proId) == null){
				Map<String,ChbMonthCollect> holderTypeMap = new HashMap<String,ChbMonthCollect>();
				holderTypeMap.put(key,holderTypeCollect.get(key));
				holderTypeCollectMap.put(proId,holderTypeMap);
			}
			else {
				holderTypeCollectMap.get(proId).put(key,holderTypeCollect.get(key));
			}
		}
		//处理最大行值
		for(int i =0;i < chbPrdPosition.size() ;i++){
			for(String key :holderTypeCollectMap.keySet()) {
				if(chbPrdPosition.get(i).getPrdRegistCode() != null){
					if (chbPrdPosition.get(i).getPrdRegistCode().equals(key)) {
						BigDecimal SaleAmountTotalAll = new BigDecimal("0");
						BigDecimal SaleAmountTotalMax = null;
						String SaleAmountFlag = "";
						BigDecimal ValueAmountTotalAll = new BigDecimal("0");
						BigDecimal ValueAmountTotalMax = null;
						String ValueAmountFlag = "";
						BigDecimal SaleSharesTotalAll = new BigDecimal("0");
						BigDecimal SaleSharesTotalMax = null;
						String SaleSharesTotalFlag = "";
						BigDecimal ValueSharesTotalAll = new BigDecimal("0");
						BigDecimal ValueSharesTotalMax = null;
						String ValueSharesTotalFlag = "";
						BigDecimal BalanceSharesAll = new BigDecimal("0");
						BigDecimal BalanceSharesMax = null;
						String BalanceSharesFlag = "";
						BigDecimal BalanceAmtAll = new BigDecimal("0");
						BigDecimal BalanceAmtMax = null;
						String BalanceAmtFlag = "";
						for (String flag : holderTypeCollectMap.get(key).keySet()) {
							BigDecimal SaleAmountTotal = holderTypeCollectMap.get(key).get(flag).getSaleAmountTotal();
							BigDecimal ValueAmountTota = holderTypeCollectMap.get(key).get(flag).getValueAmountTotal();
							BigDecimal SaleSharesTotal = holderTypeCollectMap.get(key).get(flag).getSaleSharesTotal();
							BigDecimal ValueSharesTotal = holderTypeCollectMap.get(key).get(flag).getValueSharesTotal();
							BigDecimal BalanceShares = holderTypeCollectMap.get(key).get(flag).getBalanceShares();
							BigDecimal BalanceAmt = holderTypeCollectMap.get(key).get(flag).getBalanceAmt();
							if (SaleAmountTotal != null) {
								SaleAmountTotalAll = SaleAmountTotalAll.add(SaleAmountTotal);
							}
							if (ValueAmountTota != null) {
								ValueAmountTotalAll = ValueAmountTotalAll.add(ValueAmountTota);
							}
							if (SaleSharesTotal != null) {
								SaleSharesTotalAll = SaleSharesTotalAll.add(SaleSharesTotal);
							}
							if (ValueSharesTotal != null) {
								ValueSharesTotalAll = ValueSharesTotalAll.add(ValueSharesTotal);
							}
							if (BalanceShares != null) {
								BalanceSharesAll = BalanceSharesAll.add(BalanceShares);
							}
							if (BalanceAmt != null) {
								BalanceAmtAll = BalanceAmtAll.add(BalanceAmt);
							}
							if (SaleAmountTotal != null) {
								if(SaleAmountTotalMax == null){
									SaleAmountTotalMax = SaleAmountTotal;
									SaleAmountFlag = flag;
								}else if (SaleAmountTotalMax.compareTo(SaleAmountTotal) == 1) {
								} else {
									SaleAmountTotalMax = SaleAmountTotal;
									SaleAmountFlag = flag;
								}
							}
							if (ValueAmountTota != null) {
								if(ValueAmountTotalMax == null){
									ValueAmountTotalMax = ValueAmountTota;
									ValueAmountFlag = flag;
								}else if (ValueAmountTotalMax.compareTo(ValueAmountTota) == 1) {
								} else {
									ValueAmountTotalMax = ValueAmountTota;
									ValueAmountFlag = flag;
								}
							}
							if (SaleSharesTotal != null) {
								if(SaleSharesTotalMax == null){
									SaleSharesTotalMax = SaleSharesTotal;
									SaleSharesTotalFlag = flag;
								}else if (SaleSharesTotalMax.compareTo(SaleSharesTotal) == 1) {
								} else {
									SaleSharesTotalMax = SaleSharesTotal;
									SaleSharesTotalFlag = flag;
								}
							}
							if (ValueSharesTotal != null) {
								if(ValueSharesTotalMax == null){
									ValueSharesTotalMax = ValueSharesTotal;
									ValueSharesTotalFlag = flag;
								}else if (ValueSharesTotalMax.compareTo(ValueSharesTotal) == 1) {
								} else {
									ValueSharesTotalMax = ValueSharesTotal;
									ValueSharesTotalFlag = flag;
								}
							}
							if (BalanceShares != null) {
								if(BalanceSharesMax == null){
									BalanceSharesMax = BalanceShares;
									BalanceSharesFlag = flag;
								}else if (BalanceSharesMax.compareTo(BalanceShares) == 1) {
								} else {
									BalanceSharesMax = BalanceShares;
									BalanceSharesFlag = flag;
								}
							}
							if (BalanceAmt != null) {
								if(BalanceAmtMax == null){
									BalanceAmtMax = BalanceAmt;
									BalanceAmtFlag = flag;
								}else if (BalanceAmtMax.compareTo(BalanceAmt) == 1) {
								} else {
									BalanceAmtMax = BalanceAmt;
									BalanceAmtFlag = flag;
								}
							}
						}
						//处理总销售份额
						if (SaleSharesTotalFlag != null && SaleSharesTotalFlag != "") {
							if (chbPrdPosition.get(i).getDirtyamtsum() != null) {
								BigDecimal SaleSharesTotalDiff = SaleSharesTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleSharesTotalAll));
								holderTypeCollectMap.get(key).get(SaleSharesTotalFlag).setSaleSharesTotal(SaleSharesTotalDiff);
							} else {
								chbPrdPosition.get(i).setDirtyamtsum(BigDecimal.ZERO);
								BigDecimal SaleSharesTotalDiff = SaleSharesTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleSharesTotalAll));
								holderTypeCollectMap.get(key).get(SaleSharesTotalFlag).setSaleSharesTotal(SaleSharesTotalDiff);
							}
						}
						//处理净销份额
						if (ValueSharesTotalFlag != null && ValueSharesTotalFlag != "") {
							if (chbPrdPosition.get(i).getCleanamtsum() != null) {
								BigDecimal ValueSharesTotalDiff = ValueSharesTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueSharesTotalAll));
								holderTypeCollectMap.get(key).get(ValueSharesTotalFlag).setValueSharesTotal(ValueSharesTotalDiff);
							} else {
								chbPrdPosition.get(i).setCleanamtsum(BigDecimal.ZERO);
								BigDecimal ValueSharesTotalDiff = ValueSharesTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueSharesTotalAll));
								holderTypeCollectMap.get(key).get(ValueSharesTotalFlag).setValueSharesTotal(ValueSharesTotalDiff);
							}
						}
						//处理份额
						if (BalanceSharesFlag != null && BalanceSharesFlag != "") {
							if (chbPrdPosition.get(i).getEndamtsumOth() != null) {
								BigDecimal BalanceSharesDiff = BalanceSharesMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceSharesAll));
								holderTypeCollectMap.get(key).get(BalanceSharesFlag).setBalanceShares(BalanceSharesDiff);
							} else {
								chbPrdPosition.get(i).setEndamtsumOth(BigDecimal.ZERO);
								BigDecimal BalanceSharesDiff = BalanceSharesMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceSharesAll));
								holderTypeCollectMap.get(key).get(BalanceSharesFlag).setBalanceShares(BalanceSharesDiff);
							}
						}
						//处理总销售金额
						if (SaleAmountFlag != null && SaleAmountFlag != "") {
							if (chbPrdPosition.get(i).getDirtyamtsum() != null) {
								BigDecimal SaleAmountTotalDiff = SaleAmountTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleAmountTotalAll));
								holderTypeCollectMap.get(key).get(SaleAmountFlag).setSaleAmountTotal(SaleAmountTotalDiff);
							} else {
								chbPrdPosition.get(i).setDirtyamtsum(BigDecimal.ZERO);
								BigDecimal SaleAmountTotalDiff = SaleAmountTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleAmountTotalAll));
								holderTypeCollectMap.get(key).get(SaleAmountFlag).setSaleAmountTotal(SaleAmountTotalDiff);
							}
						}
						//处理净销售金额
						if (ValueAmountFlag != null && ValueAmountFlag != "") {
							if (chbPrdPosition.get(i).getCleanamtsum() != null) {
								BigDecimal ValueAmountTotalDiff = ValueAmountTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueAmountTotalAll));
								holderTypeCollectMap.get(key).get(ValueAmountFlag).setValueAmountTotal(ValueAmountTotalDiff);
							} else {
								chbPrdPosition.get(i).setCleanamtsum(BigDecimal.ZERO);
								BigDecimal ValueAmountTotalDiff = ValueAmountTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueAmountTotalAll));
								holderTypeCollectMap.get(key).get(ValueAmountFlag).setValueAmountTotal(ValueAmountTotalDiff);
							}
						}
						//处理余额
						if (BalanceAmtFlag != null && BalanceAmtFlag != "") {
							if (chbPrdPosition.get(i).getEndamtsumOth() != null) {
								BigDecimal BalanceAmtDiff = BalanceAmtMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceAmtAll));
								holderTypeCollectMap.get(key).get(BalanceAmtFlag).setBalanceAmt(BalanceAmtDiff);
							} else {
								chbPrdPosition.get(i).setEndamtsumOth(BigDecimal.ZERO);
								BigDecimal BalanceAmtDiff = BalanceAmtMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceAmtAll));
								holderTypeCollectMap.get(key).get(BalanceAmtFlag).setBalanceAmt(BalanceAmtDiff);
							}
						}
					}
				}
			}
		}


		//以地区汇总
		List<ChbMonthCollect >areaHolderNum = svrMonthCollectInterfMapper.areaHolderNum(DateUtil.format(batchDateEnd));//取得投资者数量
		Map<String,ChbMonthCollect> areaCollect = new HashMap<String,ChbMonthCollect>();
		for(ChbMonthCollect a : listAmountTotal){
			String falg = a.getPrdRegistCode()+ ":" + a.getAreaCode();
			if (areaCollect.get(falg) == null){
				ChbMonthCollect bb = new ChbMonthCollect();
				bb.setInstitutionCode(a.getInstitutionCode());
				bb.setCcy(a.getCcy());
				bb.setIsConsign(a.getIsConsign());
				bb.setAreaCode(a.getAreaCode());
				bb.setCnyAmt(a.getCnyAmt());
				bb.setPrdRegistCode(a.getPrdRegistCode());
				bb.setHolderType(a.getHolderType());
				bb.setSaleAmountTotal(a.getSaleAmountTotal());
				bb.setValueAmountTotal(a.getValueAmountTotal());
				bb.setBalanceAmt(a.getBalanceAmt());
				bb.setBalanceShares(a.getBalanceShares());
				bb.setValueSharesTotal(a.getValueSharesTotal());
				bb.setSaleSharesTotal(a.getSaleSharesTotal());
				areaCollect.put(falg, bb);
			}else{
				if(a.getSaleSharesTotal() != null) {
					if(areaCollect.get(falg).getSaleSharesTotal() != null) {
						areaCollect.get(falg).setSaleSharesTotal(areaCollect.get(falg).getSaleSharesTotal().add(a.getSaleSharesTotal()));
					}else{
						areaCollect.get(falg).setSaleSharesTotal(a.getSaleSharesTotal());
					}
				}
				if(a.getValueSharesTotal() != null){
					if(areaCollect.get(falg).getValueSharesTotal() != null) {
						areaCollect.get(falg).setValueSharesTotal(areaCollect.get(falg).getValueSharesTotal().add(a.getValueSharesTotal()));
					}else{
						areaCollect.get(falg).setValueSharesTotal(a.getValueSharesTotal());
					}
				}
				if(a.getSaleAmountTotal() != null) {
					if(areaCollect.get(falg).getSaleAmountTotal() != null) {
						areaCollect.get(falg).setSaleAmountTotal(areaCollect.get(falg).getSaleAmountTotal().add(a.getSaleAmountTotal()));
					}else{
						areaCollect.get(falg).setSaleAmountTotal(a.getSaleAmountTotal());
					}
				}
				if(a.getValueAmountTotal() != null){
					if(areaCollect.get(falg).getValueAmountTotal() != null) {
						areaCollect.get(falg).setValueAmountTotal(areaCollect.get(falg).getValueAmountTotal().add(a.getValueAmountTotal()));
					}else{
						areaCollect.get(falg).setValueAmountTotal(a.getValueAmountTotal());
					}
				}
				if (a.getBalanceAmt() != null) {
					if(areaCollect.get(falg).getBalanceAmt() != null) {
						areaCollect.get(falg).setBalanceAmt(areaCollect.get(falg).getBalanceAmt().add(a.getBalanceAmt()));
					}else{
						areaCollect.get(falg).setBalanceAmt(a.getBalanceAmt());
					}
				}
				if (a.getBalanceShares() != null) {
					if(areaCollect.get(falg).getBalanceShares() != null) {
						areaCollect.get(falg).setBalanceShares(areaCollect.get(falg).getBalanceShares().add(a.getBalanceShares()));
					}else{
						areaCollect.get(falg).setBalanceShares(a.getBalanceShares());
					}
				}
			}
		}
		//赋值投资者数量
		for(String key :areaCollect.keySet()){
			for(int m = 0; m < areaHolderNum.size();m++) {
				String flag = areaHolderNum.get(m).getPrdRegistCode() + ":" + areaHolderNum.get(m).getAreaCode();
				if(flag.equals(key)){
					areaCollect.get(flag).setHolderNum(areaHolderNum.get(m).getHolderNum());
				}
			}
		}
		//转换成以产品为key的父级map
		Map<String,Map<String,ChbMonthCollect>> areaCollectMap = new HashMap<String,Map<String,ChbMonthCollect>>();
		for(String key: areaCollect.keySet()){
			int index=key.lastIndexOf(":");
			String proId = key.substring(0, index);
			if(areaCollectMap.get(proId) == null){
				Map<String,ChbMonthCollect> areaMap = new HashMap<String,ChbMonthCollect>();
				areaMap.put(key,areaCollect.get(key));
				areaCollectMap.put(proId,areaMap);
			}
			else {
				areaCollectMap.get(proId).put(key,areaCollect.get(key));
			}
		}
		//处理最大行值
		for(int i =0;i < chbPrdPosition.size() ;i++){
			for(String key :areaCollectMap.keySet()) {
				if (chbPrdPosition.get(i).getPrdRegistCode() != null){
					if (chbPrdPosition.get(i).getPrdRegistCode().equals(key)) {
						BigDecimal SaleAmountTotalAll = new BigDecimal("0");
						BigDecimal SaleAmountTotalMax = null;
						String SaleAmountFlag = "";
						BigDecimal ValueAmountTotalAll = new BigDecimal("0");
						BigDecimal ValueAmountTotalMax = null;
						String ValueAmountFlag = "";
						BigDecimal SaleSharesTotalAll = new BigDecimal("0");
						BigDecimal SaleSharesTotalMax = null;
						String SaleSharesTotalFlag = "";
						BigDecimal ValueSharesTotalAll = new BigDecimal("0");
						BigDecimal ValueSharesTotalMax = null;
						String ValueSharesTotalFlag = "";
						BigDecimal BalanceSharesAll = new BigDecimal("0");
						BigDecimal BalanceSharesMax = null;
						String BalanceSharesFlag = "";
						BigDecimal BalanceAmtAll = new BigDecimal("0");
						BigDecimal BalanceAmtMax = null;
						String BalanceAmtFlag = "";
						for (String flag : areaCollectMap.get(key).keySet()) {
							BigDecimal SaleAmountTotal = areaCollectMap.get(key).get(flag).getSaleAmountTotal();
							BigDecimal ValueAmountTota = areaCollectMap.get(key).get(flag).getValueAmountTotal();
							BigDecimal SaleSharesTotal = areaCollectMap.get(key).get(flag).getSaleSharesTotal();
							BigDecimal ValueSharesTotal = areaCollectMap.get(key).get(flag).getValueSharesTotal();
							BigDecimal BalanceShares = areaCollectMap.get(key).get(flag).getBalanceShares();
							BigDecimal BalanceAmt = areaCollectMap.get(key).get(flag).getBalanceAmt();
							if (SaleAmountTotal != null) {
								SaleAmountTotalAll = SaleAmountTotalAll.add(SaleAmountTotal);
							}
							if (ValueAmountTota != null) {
								ValueAmountTotalAll = ValueAmountTotalAll.add(ValueAmountTota);
							}
							if (SaleSharesTotal != null) {
								SaleSharesTotalAll = SaleSharesTotalAll.add(SaleSharesTotal);
							}
							if (ValueSharesTotal != null) {
								ValueSharesTotalAll = ValueSharesTotalAll.add(ValueSharesTotal);
							}
							if (BalanceShares != null) {
								BalanceSharesAll = BalanceSharesAll.add(BalanceShares);
							}
							if (BalanceAmt != null) {
								BalanceAmtAll = BalanceAmtAll.add(BalanceAmt);
							}

							if (SaleAmountTotal != null) {
								if(SaleAmountTotalMax == null){
									SaleAmountTotalMax = SaleAmountTotal;
									SaleAmountFlag = flag;
								}else if (SaleAmountTotalMax.compareTo(SaleAmountTotal) == 1) {
								} else {
									SaleAmountTotalMax = SaleAmountTotal;
									SaleAmountFlag = flag;
								}
							}
							if (ValueAmountTota != null) {
								if(ValueAmountTotalMax == null){
									ValueAmountTotalMax = ValueAmountTota;
									ValueAmountFlag = flag;
								}else if (ValueAmountTotalMax.compareTo(ValueAmountTota) == 1) {
								} else {
									ValueAmountTotalMax = ValueAmountTota;
									ValueAmountFlag = flag;
								}
							}
							if (SaleSharesTotal != null) {
								if(SaleSharesTotalMax == null){
									SaleSharesTotalMax = SaleSharesTotal;
									SaleSharesTotalFlag = flag;
								}else if (SaleSharesTotalMax.compareTo(SaleSharesTotal) == 1) {
								} else {
									SaleSharesTotalMax = SaleSharesTotal;
									SaleSharesTotalFlag = flag;
								}
							}
							if (ValueSharesTotal != null) {
								if(ValueSharesTotalMax == null){
									ValueSharesTotalMax = ValueSharesTotal;
									ValueSharesTotalFlag = flag;
								}else if (ValueSharesTotalMax.compareTo(ValueSharesTotal) == 1) {
								} else {
									ValueSharesTotalMax = ValueSharesTotal;
									ValueSharesTotalFlag = flag;
								}
							}
							if (BalanceShares != null) {
								if(BalanceSharesMax == null){
									BalanceSharesMax = BalanceShares;
									BalanceSharesFlag = flag;
								}else if (BalanceSharesMax.compareTo(BalanceShares) == 1) {
								} else {
									BalanceSharesMax = BalanceShares;
									BalanceSharesFlag = flag;
								}
							}
							if (BalanceAmt != null) {
								if(BalanceAmtMax == null){
									BalanceAmtMax = BalanceAmt;
									BalanceAmtFlag = flag;
								}else if (BalanceAmtMax.compareTo(BalanceAmt) == 1) {
								} else {
									BalanceAmtMax = BalanceAmt;
									BalanceAmtFlag = flag;
								}
							}
						}
						//处理总销售份额
						if (SaleSharesTotalFlag != null && SaleSharesTotalFlag != "") {
							if (chbPrdPosition.get(i).getDirtyamtsum() != null) {
								BigDecimal SaleSharesTotalDiff = SaleSharesTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleSharesTotalAll));
								areaCollectMap.get(key).get(SaleSharesTotalFlag).setSaleSharesTotal(SaleSharesTotalDiff);
							} else {
								chbPrdPosition.get(i).setDirtyamtsum(BigDecimal.ZERO);
								BigDecimal SaleSharesTotalDiff = SaleSharesTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleSharesTotalAll));
								areaCollectMap.get(key).get(SaleSharesTotalFlag).setSaleSharesTotal(SaleSharesTotalDiff);
							}
						}
						//处理净销份额
						if (ValueSharesTotalFlag != null && ValueSharesTotalFlag != "") {
							if (chbPrdPosition.get(i).getCleanamtsum() != null) {
								BigDecimal ValueSharesTotalDiff = ValueSharesTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueSharesTotalAll));
								areaCollectMap.get(key).get(ValueSharesTotalFlag).setValueSharesTotal(ValueSharesTotalDiff);
							} else {
								chbPrdPosition.get(i).setCleanamtsum(BigDecimal.ZERO);
								BigDecimal ValueSharesTotalDiff = ValueSharesTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueSharesTotalAll));
								areaCollectMap.get(key).get(ValueSharesTotalFlag).setValueSharesTotal(ValueSharesTotalDiff);
							}
						}
						//处理份额
						if (BalanceSharesFlag != null && BalanceSharesFlag != "") {
							if (chbPrdPosition.get(i).getEndamtsumOth() != null) {
								BigDecimal BalanceSharesDiff = BalanceSharesMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceSharesAll));
								areaCollectMap.get(key).get(BalanceSharesFlag).setBalanceShares(BalanceSharesDiff);
							} else {
								chbPrdPosition.get(i).setEndamtsumOth(BigDecimal.ZERO);
								BigDecimal BalanceSharesDiff = BalanceSharesMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceSharesAll));
								areaCollectMap.get(key).get(BalanceSharesFlag).setBalanceShares(BalanceSharesDiff);
							}
						}
						//处理总销售金额
						if (SaleAmountFlag != null && SaleAmountFlag != "") {
							if (chbPrdPosition.get(i).getDirtyamtsum() != null) {
								BigDecimal SaleAmountTotalDiff = SaleAmountTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleAmountTotalAll));
								areaCollectMap.get(key).get(SaleAmountFlag).setSaleAmountTotal(SaleAmountTotalDiff);
							} else {
								chbPrdPosition.get(i).setDirtyamtsum(BigDecimal.ZERO);
								BigDecimal SaleAmountTotalDiff = SaleAmountTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleAmountTotalAll));
								areaCollectMap.get(key).get(SaleAmountFlag).setSaleAmountTotal(SaleAmountTotalDiff);
							}
						}
						//处理净销售金额
						if (ValueAmountFlag != null && ValueAmountFlag != "") {
							if (chbPrdPosition.get(i).getCleanamtsum() != null) {
								BigDecimal ValueAmountTotalDiff = ValueAmountTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueAmountTotalAll));
								areaCollectMap.get(key).get(ValueAmountFlag).setValueAmountTotal(ValueAmountTotalDiff);
							} else {
								chbPrdPosition.get(i).setCleanamtsum(BigDecimal.ZERO);
								BigDecimal ValueAmountTotalDiff = ValueAmountTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueAmountTotalAll));
								areaCollectMap.get(key).get(ValueAmountFlag).setValueAmountTotal(ValueAmountTotalDiff);
							}
						}
						//处理余额
						if (BalanceAmtFlag != null && BalanceAmtFlag != "") {
							if (chbPrdPosition.get(i).getEndamtsumOth() != null) {
								BigDecimal BalanceAmtDiff = BalanceAmtMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceAmtAll));
								areaCollectMap.get(key).get(BalanceAmtFlag).setBalanceAmt(BalanceAmtDiff);
							} else {
								chbPrdPosition.get(i).setEndamtsumOth(BigDecimal.ZERO);
								BigDecimal BalanceAmtDiff = BalanceAmtMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceAmtAll));
								areaCollectMap.get(key).get(BalanceAmtFlag).setBalanceAmt(BalanceAmtDiff);
							}
						}
					}
				}
			}
		}

		//以分销渠道汇总
		List<ChbMonthCollect >channelHolderNum = svrMonthCollectInterfMapper.channelHolderNum(DateUtil.format(batchDateEnd));//取得投资者数量
		Map<String,ChbMonthCollect> institutionCollect = new HashMap<String,ChbMonthCollect>();
		for(ChbMonthCollect a : listAmountTotal){
			String falg = a.getPrdRegistCode()+ ":" + a.getInstitutionCode();
			if (institutionCollect.get(falg) == null){
				ChbMonthCollect bb = new ChbMonthCollect();
				bb.setInstitutionCode(a.getInstitutionCode());
				bb.setCcy(a.getCcy());
				bb.setIsConsign(a.getIsConsign());
				bb.setAreaCode(a.getAreaCode());
				bb.setCnyAmt(a.getCnyAmt());
				bb.setPrdRegistCode(a.getPrdRegistCode());
				bb.setHolderType(a.getHolderType());
				bb.setSaleAmountTotal(a.getSaleAmountTotal());
				bb.setValueAmountTotal(a.getValueAmountTotal());
				bb.setBalanceAmt(a.getBalanceAmt());
				bb.setBalanceShares(a.getBalanceShares());
				bb.setValueSharesTotal(a.getValueSharesTotal());
				bb.setSaleSharesTotal(a.getSaleSharesTotal());
				institutionCollect.put(falg, bb);
			}else{
				if(a.getSaleSharesTotal() != null) {
					if(institutionCollect.get(falg).getSaleSharesTotal() != null) {
						institutionCollect.get(falg).setSaleSharesTotal(institutionCollect.get(falg).getSaleSharesTotal().add(a.getSaleSharesTotal()));
					}else{
						institutionCollect.get(falg).setSaleSharesTotal(a.getSaleSharesTotal());
					}
				}
				if(a.getValueSharesTotal() != null){
					if(institutionCollect.get(falg).getValueSharesTotal() != null) {
						institutionCollect.get(falg).setValueSharesTotal(institutionCollect.get(falg).getValueSharesTotal().add(a.getValueSharesTotal()));
					}else{
						institutionCollect.get(falg).setValueSharesTotal(a.getValueSharesTotal());
					}
				}
				if(a.getSaleAmountTotal() != null) {
					if(institutionCollect.get(falg).getSaleAmountTotal() != null) {
						institutionCollect.get(falg).setSaleAmountTotal(institutionCollect.get(falg).getSaleAmountTotal().add(a.getSaleAmountTotal()));
					}else{
						institutionCollect.get(falg).setSaleAmountTotal(a.getSaleAmountTotal());
					}
				}
				if(a.getValueAmountTotal() != null){
					if(institutionCollect.get(falg).getValueAmountTotal() != null) {
						institutionCollect.get(falg).setValueAmountTotal(institutionCollect.get(falg).getValueAmountTotal().add(a.getValueAmountTotal()));
					}else{
						institutionCollect.get(falg).setValueAmountTotal(a.getValueAmountTotal());
					}
				}
				if (a.getBalanceAmt() != null) {
					if(institutionCollect.get(falg).getBalanceAmt() != null) {
						institutionCollect.get(falg).setBalanceAmt(institutionCollect.get(falg).getBalanceAmt().add(a.getBalanceAmt()));
					}else{
						institutionCollect.get(falg).setBalanceAmt(a.getBalanceAmt());
					}
				}
				if (a.getBalanceShares() != null) {
					if(institutionCollect.get(falg).getBalanceShares() != null) {
						institutionCollect.get(falg).setBalanceShares(institutionCollect.get(falg).getBalanceShares().add(a.getBalanceShares()));
					}else{
						institutionCollect.get(falg).setBalanceShares(a.getBalanceShares());
					}
				}
			}
		}
		//赋值投资者数量
		for(String key :institutionCollect.keySet()){
			for(int m = 0; m < channelHolderNum.size();m++) {
				String flag = channelHolderNum.get(m).getPrdRegistCode() + ":" + channelHolderNum.get(m).getInstitutionCode();
				if(flag.equals(key)){
					institutionCollect.get(flag).setHolderNum(channelHolderNum.get(m).getHolderNum());
				}
			}
		}
		//转换成以产品为key的父级map
		Map<String,Map<String,ChbMonthCollect>> institutionCollectMap = new HashMap<String,Map<String,ChbMonthCollect>>();
		for(String key: institutionCollect.keySet()){
			int index=key.lastIndexOf(":");
			String proId = key.substring(0, index);
			if(institutionCollectMap.get(proId) == null){
				Map<String,ChbMonthCollect> channelMap = new HashMap<String,ChbMonthCollect>();
				channelMap.put(key,institutionCollect.get(key));
				institutionCollectMap.put(proId,channelMap);
			}
			else {
				institutionCollectMap.get(proId).put(key,institutionCollect.get(key));
			}
		}
		//处理最大行值
		for(int i =0;i < chbPrdPosition.size() ;i++){
			for(String key :institutionCollectMap.keySet()){
				if(chbPrdPosition.get(i).getPrdRegistCode() != null) {
					if (chbPrdPosition.get(i).getPrdRegistCode().equals(key)) {
						BigDecimal SaleAmountTotalAll = new BigDecimal("0");
						BigDecimal SaleAmountTotalMax = null;
						String SaleAmountFlag = "";
						BigDecimal ValueAmountTotalAll = new BigDecimal("0");
						BigDecimal ValueAmountTotalMax = null;
						String ValueAmountFlag = "";
						BigDecimal SaleSharesTotalAll = new BigDecimal("0");
						BigDecimal SaleSharesTotalMax = null;
						String SaleSharesTotalFlag = "";
						BigDecimal ValueSharesTotalAll = new BigDecimal("0");
						BigDecimal ValueSharesTotalMax = null;
						String ValueSharesTotalFlag = "";
						BigDecimal BalanceSharesAll = new BigDecimal("0");
						BigDecimal BalanceSharesMax = null;
						String BalanceSharesFlag = "";
						BigDecimal BalanceAmtAll = new BigDecimal("0");
						BigDecimal BalanceAmtMax = null;
						String BalanceAmtFlag = "";
						for (String flag : institutionCollectMap.get(key).keySet()) {
							BigDecimal SaleAmountTotal = institutionCollectMap.get(key).get(flag).getSaleAmountTotal();
							BigDecimal ValueAmountTota = institutionCollectMap.get(key).get(flag).getValueAmountTotal();
							BigDecimal SaleSharesTotal = institutionCollectMap.get(key).get(flag).getSaleSharesTotal();
							BigDecimal ValueSharesTotal = institutionCollectMap.get(key).get(flag).getValueSharesTotal();
							BigDecimal BalanceShares = institutionCollectMap.get(key).get(flag).getBalanceShares();
							BigDecimal BalanceAmt = institutionCollectMap.get(key).get(flag).getBalanceAmt();
							if (SaleAmountTotal != null) {
								SaleAmountTotalAll = SaleAmountTotalAll.add(SaleAmountTotal);
							}
							if (ValueAmountTota != null) {
								ValueAmountTotalAll = ValueAmountTotalAll.add(ValueAmountTota);
							}
							if (SaleSharesTotal != null) {
								SaleSharesTotalAll = SaleSharesTotalAll.add(SaleSharesTotal);
							}
							if (ValueSharesTotal != null) {
								ValueSharesTotalAll = ValueSharesTotalAll.add(ValueSharesTotal);
							}
							if (BalanceShares != null) {
								BalanceSharesAll = BalanceSharesAll.add(BalanceShares);
							}
							if (BalanceAmt != null) {
								BalanceAmtAll = BalanceAmtAll.add(BalanceAmt);
							}
							if (SaleAmountTotal != null) {
								if(SaleAmountTotalMax == null){
									SaleAmountTotalMax = SaleAmountTotal;
									SaleAmountFlag = flag;
								}else if (SaleAmountTotalMax.compareTo(SaleAmountTotal) == 1) {
								} else {
									SaleAmountTotalMax = SaleAmountTotal;
									SaleAmountFlag = flag;
								}
							}
							if (ValueAmountTota != null) {
								if(ValueAmountTotalMax == null){
									ValueAmountTotalMax = ValueAmountTota;
									ValueAmountFlag = flag;
								}else if (ValueAmountTotalMax.compareTo(ValueAmountTota) == 1) {
								} else {
									ValueAmountTotalMax = ValueAmountTota;
									ValueAmountFlag = flag;
								}
							}
							if (SaleSharesTotal != null) {
								if(SaleSharesTotalMax == null){
									SaleSharesTotalMax = SaleSharesTotal;
									SaleSharesTotalFlag = flag;
								}else if (SaleSharesTotalMax.compareTo(SaleSharesTotal) == 1) {
								} else {
									SaleSharesTotalMax = SaleSharesTotal;
									SaleSharesTotalFlag = flag;
								}
							}
							if (ValueSharesTotal != null) {
								if(ValueSharesTotalMax == null){
									ValueSharesTotalMax = ValueSharesTotal;
									ValueSharesTotalFlag = flag;
								}else if (ValueSharesTotalMax.compareTo(ValueSharesTotal) == 1) {
								} else {
									ValueSharesTotalMax = ValueSharesTotal;
									ValueSharesTotalFlag = flag;
								}
							}
							if (BalanceShares != null) {
								if(BalanceSharesMax == null){
									BalanceSharesMax = BalanceShares;
									BalanceSharesFlag = flag;
								}else if (BalanceSharesMax.compareTo(BalanceShares) == 1) {
								} else {
									BalanceSharesMax = BalanceShares;
									BalanceSharesFlag = flag;
								}
							}
							if (BalanceAmt != null) {
								if(BalanceAmtMax == null){
									BalanceAmtMax = BalanceAmt;
									BalanceAmtFlag = flag;
								}else if (BalanceAmtMax.compareTo(BalanceAmt) == 1) {
								} else {
									BalanceAmtMax = BalanceAmt;
									BalanceAmtFlag = flag;
								}
							}
						}
						//处理总销售份额
						if (SaleSharesTotalFlag != null && SaleSharesTotalFlag != "") {
							if (chbPrdPosition.get(i).getDirtyamtsum() != null) {
								BigDecimal SaleSharesTotalDiff = SaleSharesTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleSharesTotalAll));
								institutionCollectMap.get(key).get(SaleSharesTotalFlag).setSaleSharesTotal(SaleSharesTotalDiff);
							} else {
								chbPrdPosition.get(i).setDirtyamtsum(BigDecimal.ZERO);
								BigDecimal SaleSharesTotalDiff = SaleSharesTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleSharesTotalAll));
								institutionCollectMap.get(key).get(SaleSharesTotalFlag).setSaleSharesTotal(SaleSharesTotalDiff);
							}
						}
						//处理净销份额
						if (ValueSharesTotalFlag != null && ValueSharesTotalFlag != "") {
							if (chbPrdPosition.get(i).getCleanamtsum() != null) {
								BigDecimal ValueSharesTotalDiff = ValueSharesTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueSharesTotalAll));
								institutionCollectMap.get(key).get(ValueSharesTotalFlag).setValueSharesTotal(ValueSharesTotalDiff);
							} else {
								chbPrdPosition.get(i).setCleanamtsum(BigDecimal.ZERO);
								BigDecimal ValueSharesTotalDiff = ValueSharesTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueSharesTotalAll));
								institutionCollectMap.get(key).get(ValueSharesTotalFlag).setValueSharesTotal(ValueSharesTotalDiff);
							}
						}
						//处理份额
						if (BalanceSharesFlag != null && BalanceSharesFlag != "") {
							if (chbPrdPosition.get(i).getEndamtsumOth() != null) {
								BigDecimal BalanceSharesDiff = BalanceSharesMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceSharesAll));
								institutionCollectMap.get(key).get(BalanceSharesFlag).setBalanceShares(BalanceSharesDiff);
							} else {
								chbPrdPosition.get(i).setEndamtsumOth(BigDecimal.ZERO);
								BigDecimal BalanceSharesDiff = BalanceSharesMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceSharesAll));
								institutionCollectMap.get(key).get(BalanceSharesFlag).setBalanceShares(BalanceSharesDiff);
							}
						}
						//处理总销售金额
						if (SaleAmountFlag != null && SaleAmountFlag != "") {
							if (chbPrdPosition.get(i).getDirtyamtsum() != null) {
								BigDecimal SaleAmountTotalDiff = SaleAmountTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleAmountTotalAll));
								institutionCollectMap.get(key).get(SaleAmountFlag).setSaleAmountTotal(SaleAmountTotalDiff);
							} else {
								chbPrdPosition.get(i).setDirtyamtsum(BigDecimal.ZERO);
								BigDecimal SaleAmountTotalDiff = SaleAmountTotalMax.add(chbPrdPosition.get(i).getDirtyamtsum().subtract(SaleAmountTotalAll));
								institutionCollectMap.get(key).get(SaleAmountFlag).setSaleAmountTotal(SaleAmountTotalDiff);
							}
						}
						//处理净销售金额
						if (ValueAmountFlag != null && ValueAmountFlag != "") {
							if (chbPrdPosition.get(i).getCleanamtsum() != null) {
								BigDecimal ValueAmountTotalDiff = ValueAmountTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueAmountTotalAll));
								institutionCollectMap.get(key).get(ValueAmountFlag).setValueAmountTotal(ValueAmountTotalDiff);
							} else {
								chbPrdPosition.get(i).setCleanamtsum(BigDecimal.ZERO);
								BigDecimal ValueAmountTotalDiff = ValueAmountTotalMax.add(chbPrdPosition.get(i).getCleanamtsum().subtract(ValueAmountTotalAll));
								institutionCollectMap.get(key).get(ValueAmountFlag).setValueAmountTotal(ValueAmountTotalDiff);
							}
						}
						//处理余额
						if (BalanceAmtFlag != null && BalanceAmtFlag != "") {
							if (chbPrdPosition.get(i).getEndamtsumOth() != null) {
								BigDecimal BalanceAmtDiff = BalanceAmtMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceAmtAll));
								institutionCollectMap.get(key).get(BalanceAmtFlag).setBalanceAmt(BalanceAmtDiff);
							} else {
								chbPrdPosition.get(i).setEndamtsumOth(BigDecimal.ZERO);
								BigDecimal BalanceAmtDiff = BalanceAmtMax.add(chbPrdPosition.get(i).getEndamtsumOth().subtract(BalanceAmtAll));
								institutionCollectMap.get(key).get(BalanceAmtFlag).setBalanceAmt(BalanceAmtDiff);
							}
						}
					}
				}
			}
		}

		BigDecimal maxArea= new BigDecimal("0");
		BigDecimal maxChannel= new BigDecimal("0");
		String maxAreaFlag="";
		String maxChannelFlag="";

		BigDecimal saleMaxArea= new BigDecimal("0");
		BigDecimal saleMaxChannel= new BigDecimal("0");
		String saleMaxAreaFlag="";
		String saleMaxChannelFlag="";

		BigDecimal valueMaxArea= new BigDecimal("0");
		BigDecimal valueMaxChannel= new BigDecimal("0");
		String valueMaxAreaFlag="";
		String valueMaxChannelFlag="";

		Map<String,BigDecimal> balanceAmtTypeAllMap=new HashMap();
		Map<String,BigDecimal> saleAmountTotalTypeAllMap=new HashMap();
		Map<String,BigDecimal> valueAmountTotalTypeAllMap=new HashMap();

		Map<String,BigDecimal> balanceAmtAreaAllMap=new HashMap();
		Map<String,BigDecimal> saleAmountTotalAreaAllMap=new HashMap();
		Map<String,BigDecimal> valueAmountTotalAreaAllMap=new HashMap();

		Map<String,BigDecimal> balanceAmtChannelAllMap=new HashMap();
		Map<String,BigDecimal> saleAmountTotalChannelAllMap=new HashMap();
		Map<String,BigDecimal> valueAmountTotalChannelAllMap=new HashMap();

		String flagX="";


		for (String key : holderTypeCollectMap.keySet()) {//投资者分类维度，key:每个产品 flag:每个产品下的投资者
			for (String flag : holderTypeCollectMap.get(key).keySet()) {
				if (holderTypeCollectMap.get(key).get(flag).getBalanceAmt() != null && holderTypeCollectMap.get(key).get(flag).getBalanceAmt() != BigDecimal.ZERO) {
					holderTypeCollectMap.get(key).get(flag).setBalanceAmt(holderTypeCollectMap.get(key).get(flag).getBalanceAmt().setScale(2, BigDecimal.ROUND_HALF_UP));//和导出excel有效位数保持一致

					balanceAmtTypeAll = balanceAmtTypeAll.add(holderTypeCollectMap.get(key).get(flag).getBalanceAmt());//单产品持有余额总和
				}
				if (holderTypeCollectMap.get(key).get(flag).getSaleAmountTotal() != null && holderTypeCollectMap.get(key).get(flag).getSaleAmountTotal() != BigDecimal.ZERO) {
					holderTypeCollectMap.get(key).get(flag).setSaleAmountTotal(holderTypeCollectMap.get(key).get(flag).getSaleAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
					saleAmountTotalTypeAll = saleAmountTotalTypeAll.add(holderTypeCollectMap.get(key).get(flag).getSaleAmountTotal());
				}
				if (holderTypeCollectMap.get(key).get(flag).getValueAmountTotal() != null && holderTypeCollectMap.get(key).get(flag).getValueAmountTotal() != BigDecimal.ZERO) {
					holderTypeCollectMap.get(key).get(flag).setValueAmountTotal(holderTypeCollectMap.get(key).get(flag).getValueAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
					valueAmountTotalTypeAll = valueAmountTotalTypeAll.add(holderTypeCollectMap.get(key).get(flag).getValueAmountTotal());

				}
			}
			balanceAmtTypeAllMap.put(key,balanceAmtTypeAll);//key:对应每个产品，value:持有余额总和
			saleAmountTotalTypeAllMap.put(key,saleAmountTotalTypeAll);
			valueAmountTotalTypeAllMap.put(key,valueAmountTotalTypeAll);
			balanceAmtTypeAll=new BigDecimal("0");
			saleAmountTotalTypeAll=new BigDecimal("0");
			valueAmountTotalTypeAll=new BigDecimal("0");

		}


		for (String key : areaCollectMap.keySet()) {
			for (String flag : areaCollectMap.get(key).keySet()) {
				if (areaCollectMap.get(key).get(flag).getBalanceAmt() != null && areaCollectMap.get(key).get(flag).getBalanceAmt() != BigDecimal.ZERO) {
					areaCollectMap.get(key).get(flag).setBalanceAmt(areaCollectMap.get(key).get(flag).getBalanceAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
					balanceAmtAreaAll = balanceAmtAreaAll.add(areaCollectMap.get(key).get(flag).getBalanceAmt());
					/*if (maxArea.compareTo((areaCollectMap.get(key).get(flag).getBalanceAmt())) == 1) {
					} else {
						maxArea = areaCollectMap.get(key).get(flag).getBalanceAmt();
						maxAreaFlag = flag;
					}*/
				}
				if (areaCollectMap.get(key).get(flag).getSaleAmountTotal() != null && areaCollectMap.get(key).get(flag).getSaleAmountTotal() != BigDecimal.ZERO) {
					areaCollectMap.get(key).get(flag).setSaleAmountTotal(areaCollectMap.get(key).get(flag).getSaleAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
					saleAmountTotalAreaAll = saleAmountTotalAreaAll.add(areaCollectMap.get(key).get(flag).getSaleAmountTotal());
					/*if (saleMaxArea.compareTo((areaCollectMap.get(key).get(flag).getSaleAmountTotal())) == 1) {
					} else {
						saleMaxArea = areaCollectMap.get(key).get(flag).getSaleAmountTotal();
						saleMaxAreaFlag = flag;
					}*/
				}
				if (areaCollectMap.get(key).get(flag).getValueAmountTotal() != null && areaCollectMap.get(key).get(flag).getValueAmountTotal() != BigDecimal.ZERO) {
					areaCollectMap.get(key).get(flag).setValueAmountTotal(areaCollectMap.get(key).get(flag).getValueAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
					valueAmountTotalAreaAll = valueAmountTotalAreaAll.add(areaCollectMap.get(key).get(flag).getValueAmountTotal());
					/*if (valueMaxArea.compareTo((areaCollectMap.get(key).get(flag).getValueAmountTotal())) == 1) {
					} else {
						valueMaxArea = areaCollectMap.get(key).get(flag).getValueAmountTotal();
						valueMaxAreaFlag = flag;
					}*/
				}
			}
			balanceAmtAreaAllMap.put(key,balanceAmtAreaAll);
			saleAmountTotalAreaAllMap.put(key,saleAmountTotalAreaAll);
			valueAmountTotalAreaAllMap.put(key,valueAmountTotalAreaAll);
			balanceAmtAreaAll=new BigDecimal("0");
			saleAmountTotalAreaAll=new BigDecimal("0");
			valueAmountTotalAreaAll=new BigDecimal("0");

		}
		Set<String> keyBalanceSet = balanceAmtTypeAllMap.keySet();
		Set<String> keySaleSet = saleAmountTotalTypeAllMap.keySet();
		Set<String> keyValueSet = valueAmountTotalTypeAllMap.keySet();
		for(String key : keyBalanceSet){
			if(balanceAmtAreaAllMap.get(key).compareTo((balanceAmtTypeAllMap.get(key)))!=0) {//以第一个维度为标准，有误差，对应维度分别加上误差
				for (String flag : areaCollectMap.get(key).keySet()) {
					flagX = flag;
				}
				int index = flagX.lastIndexOf(":");
				String maxAreaKey = flagX.substring(0, index);
				if (areaCollectMap.get(maxAreaKey).get(flagX).getBalanceAmt() != null) {
					areaCollectMap.get(maxAreaKey).get(flagX).
							setBalanceAmt(areaCollectMap.get(maxAreaKey).get(flagX).getBalanceAmt().add(balanceAmtTypeAllMap.get(key).subtract(balanceAmtAreaAllMap.get(key))));

				}
			}
		}
		for(String key : keySaleSet){
			if(saleAmountTotalAreaAllMap.get(key).compareTo((saleAmountTotalTypeAllMap.get(key)))!=0) {
				for (String flag : areaCollectMap.get(key).keySet()) {
					flagX = flag;
				}
				int index = flagX.lastIndexOf(":");
				String maxAreaKey = flagX.substring(0, index);
				if (areaCollectMap.get(maxAreaKey).get(flagX).getSaleAmountTotal() != null) {
					areaCollectMap.get(maxAreaKey).get(flagX).
							setSaleAmountTotal(areaCollectMap.get(maxAreaKey).get(flagX).getSaleAmountTotal().add(saleAmountTotalTypeAllMap.get(key).subtract(saleAmountTotalAreaAllMap.get(key))));

				}
			}
		}
		for(String key : keyValueSet){
			if(valueAmountTotalAreaAllMap.get(key).compareTo((valueAmountTotalTypeAllMap.get(key)))!=0) {
				for (String flag : areaCollectMap.get(key).keySet()) {
					flagX = flag;
				}
				int index = flagX.lastIndexOf(":");
				String maxAreaKey = flagX.substring(0, index);
				if (areaCollectMap.get(maxAreaKey).get(flagX).getValueAmountTotal() != null) {
					areaCollectMap.get(maxAreaKey).get(flagX).
							setValueAmountTotal(areaCollectMap.get(maxAreaKey).get(flagX).getValueAmountTotal().add(valueAmountTotalTypeAllMap.get(key).subtract(valueAmountTotalAreaAllMap.get(key))));

				}
			}
		}
	/*	if(StringUtil.isNotEmpty(maxAreaFlag)){
			int index=maxAreaFlag.lastIndexOf(":");
			String maxAreaKey = maxAreaFlag.substring(0, index);
			if(areaCollectMap.get(maxAreaKey).get(maxAreaFlag).getBalanceAmt() != null){
				areaCollectMap.get(maxAreaKey).get(maxAreaFlag).
						setBalanceAmt(areaCollectMap.get(maxAreaKey).get(maxAreaFlag).getBalanceAmt().add(balanceAmtTypeAll.subtract(balanceAmtAreaAll)));
			}
		}
		if(StringUtil.isNotEmpty(saleMaxAreaFlag)){
			int index=saleMaxAreaFlag.lastIndexOf(":");
			String saleMaxAreaKey = saleMaxAreaFlag.substring(0, index);
			if(areaCollectMap.get(saleMaxAreaKey).get(saleMaxAreaFlag).getSaleAmountTotal() != null){
				areaCollectMap.get(saleMaxAreaKey).get(saleMaxAreaFlag).
						setSaleAmountTotal(areaCollectMap.get(saleMaxAreaKey).get(saleMaxAreaFlag).getSaleAmountTotal().add(saleAmountTotalTypeAll.subtract(saleAmountTotalAreaAll)));
			}

		}
		if(StringUtil.isNotEmpty(valueMaxAreaFlag)){
			int index=valueMaxAreaFlag.lastIndexOf(":");
			String valueMaxAreaKey = valueMaxAreaFlag.substring(0, index);
			if(areaCollectMap.get(valueMaxAreaKey).get(valueMaxAreaFlag).getValueAmountTotal() != null){
				areaCollectMap.get(valueMaxAreaKey).get(valueMaxAreaFlag).
						setValueAmountTotal(areaCollectMap.get(valueMaxAreaKey).get(valueMaxAreaFlag).getValueAmountTotal().add(valueAmountTotalTypeAll.subtract(valueAmountTotalAreaAll)));
			}

		}*/

		for (String key : institutionCollectMap.keySet()) {
			for (String flag : institutionCollectMap.get(key).keySet()) {
				if (institutionCollectMap.get(key).get(flag).getBalanceAmt() != null && institutionCollectMap.get(key).get(flag).getBalanceAmt() != BigDecimal.ZERO) {
					institutionCollectMap.get(key).get(flag).setBalanceAmt(institutionCollectMap.get(key).get(flag).getBalanceAmt().setScale(2, BigDecimal.ROUND_HALF_UP));
					balanceAmtChannelAll = balanceAmtChannelAll.add(institutionCollectMap.get(key).get(flag).getBalanceAmt());
					/*if (maxChannel.compareTo((institutionCollectMap.get(key).get(flag).getBalanceAmt())) == 1) {
					} else {
						maxChannel = institutionCollectMap.get(key).get(flag).getBalanceAmt();
						maxChannelFlag = flag;
					}*/
				}
				if (institutionCollectMap.get(key).get(flag).getSaleAmountTotal() != null && institutionCollectMap.get(key).get(flag).getSaleAmountTotal() != BigDecimal.ZERO) {
					institutionCollectMap.get(key).get(flag).setSaleAmountTotal(institutionCollectMap.get(key).get(flag).getSaleAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
					saleAmountTotalChannelAll = saleAmountTotalChannelAll.add(institutionCollectMap.get(key).get(flag).getSaleAmountTotal());
					/*if (saleMaxChannel.compareTo((institutionCollectMap.get(key).get(flag).getSaleAmountTotal())) == 1) {
					} else {
						saleMaxChannel = institutionCollectMap.get(key).get(flag).getSaleAmountTotal();
						saleMaxChannelFlag = flag;
					}*/
				}
				if (institutionCollectMap.get(key).get(flag).getValueAmountTotal() != null && institutionCollectMap.get(key).get(flag).getValueAmountTotal() != BigDecimal.ZERO) {
					institutionCollectMap.get(key).get(flag).setValueAmountTotal(institutionCollectMap.get(key).get(flag).getValueAmountTotal().setScale(2, BigDecimal.ROUND_HALF_UP));
					valueAmountTotalChannelAll = valueAmountTotalChannelAll.add(institutionCollectMap.get(key).get(flag).getValueAmountTotal());
					/*if (valueMaxChannel.compareTo((institutionCollectMap.get(key).get(flag).getValueAmountTotal())) == 1) {
					} else {
						valueMaxChannel = institutionCollectMap.get(key).get(flag).getValueAmountTotal();
						valueMaxChannelFlag = flag;
					}*/
				}
			}

			balanceAmtChannelAllMap.put(key,balanceAmtChannelAll);
			saleAmountTotalChannelAllMap.put(key,saleAmountTotalChannelAll);
			valueAmountTotalChannelAllMap.put(key,valueAmountTotalChannelAll);
			balanceAmtChannelAll=new BigDecimal("0");
			saleAmountTotalChannelAll=new BigDecimal("0");
			valueAmountTotalChannelAll=new BigDecimal("0");
		}
		for(String key : keyBalanceSet){
			if(balanceAmtChannelAllMap.get(key).compareTo((balanceAmtTypeAllMap.get(key)))!=0) {
				for (String flag : institutionCollectMap.get(key).keySet()) {
					flagX = flag;
				}
				int index = flagX.lastIndexOf(":");
				String maxAreaKey = flagX.substring(0, index);
				if (institutionCollectMap.get(maxAreaKey).get(flagX).getBalanceAmt() != null) {
                    institutionCollectMap.get(maxAreaKey).get(flagX).
							setBalanceAmt(institutionCollectMap.get(maxAreaKey).get(flagX).getBalanceAmt().add(balanceAmtTypeAllMap.get(key).subtract(balanceAmtChannelAllMap.get(key))));

				}
			}
		}
		for(String key : keySaleSet){
			if(saleAmountTotalChannelAllMap.get(key).compareTo((saleAmountTotalTypeAllMap.get(key)))!=0) {
				for (String flag : institutionCollectMap.get(key).keySet()) {
					flagX = flag;
				}
				int index = flagX.lastIndexOf(":");
				String maxAreaKey = flagX.substring(0, index);
				if (institutionCollectMap.get(maxAreaKey).get(flagX).getSaleAmountTotal() != null) {
                    institutionCollectMap.get(maxAreaKey).get(flagX).
							setSaleAmountTotal(institutionCollectMap.get(maxAreaKey).get(flagX).getSaleAmountTotal().add(saleAmountTotalTypeAllMap.get(key).subtract(saleAmountTotalChannelAllMap.get(key))));

				}
			}
		}
		for(String key : keyValueSet){
			if(valueAmountTotalChannelAllMap.get(key).compareTo((valueAmountTotalTypeAllMap.get(key)))!=0) {
				for (String flag : institutionCollectMap.get(key).keySet()) {
					flagX = flag;
				}
				int index = flagX.lastIndexOf(":");
				String maxAreaKey = flagX.substring(0, index);
				if (institutionCollectMap.get(maxAreaKey).get(flagX).getValueAmountTotal() != null) {
                    institutionCollectMap.get(maxAreaKey).get(flagX).
							setValueAmountTotal(institutionCollectMap.get(maxAreaKey).get(flagX).getValueAmountTotal().add(valueAmountTotalTypeAllMap.get(key).subtract(valueAmountTotalChannelAllMap.get(key))));

				}
			}
		}
	/*	if(StringUtil.isNotEmpty(maxChannelFlag)){
			int index=maxChannelFlag.lastIndexOf(":");
			String maxChannelKey = maxChannelFlag.substring(0, index);
			if(institutionCollectMap.get(maxChannelKey).get(maxChannelFlag).getBalanceAmt() != null){
				institutionCollectMap.get(maxChannelKey).get(maxChannelFlag).setBalanceAmt(institutionCollectMap.get(maxChannelKey).get(maxChannelFlag).getBalanceAmt().add(balanceAmtTypeAll.subtract(balanceAmtChannelAll)));
			}

		}
		if(StringUtil.isNotEmpty(saleMaxChannelFlag)){
			int index=saleMaxChannelFlag.lastIndexOf(":");
			String saleMaxChannelKey = saleMaxChannelFlag.substring(0, index);
			if(institutionCollectMap.get(saleMaxChannelKey).get(saleMaxChannelFlag).getSaleAmountTotal() != null){
				institutionCollectMap.get(saleMaxChannelKey).get(saleMaxChannelFlag).setSaleAmountTotal(institutionCollectMap.get(saleMaxChannelKey).get(saleMaxChannelFlag).getSaleAmountTotal().add(saleAmountTotalTypeAll.subtract(saleAmountTotalChannelAll)));
			}

		}
		if(StringUtil.isNotEmpty(valueMaxChannelFlag)){
			int index=valueMaxChannelFlag.lastIndexOf(":");
			String valueMaxChannelKey = valueMaxChannelFlag.substring(0, index);
			if(institutionCollectMap.get(valueMaxChannelKey).get(valueMaxChannelFlag).getValueAmountTotal() != null){
				institutionCollectMap.get(valueMaxChannelKey).get(valueMaxChannelFlag).setValueAmountTotal(institutionCollectMap.get(valueMaxChannelKey).get(valueMaxChannelFlag).getValueAmountTotal().add(valueAmountTotalTypeAll.subtract(valueAmountTotalChannelAll)));
			}

		}*/

		//处理后组装成新的总map
		Map<String,Map<String,Map<String,ChbMonthCollect>>> AllMap= new HashMap<String,Map<String,Map<String,ChbMonthCollect>>>();
		AllMap.put("holderType",holderTypeCollectMap);
		AllMap.put("area",areaCollectMap);
		AllMap.put("institution",institutionCollectMap);

		return AllMap;
	}

	/**
	 * 汇率转换
	 * @param listAmountTotal
	 * @return
	 */
	public List<ChbMonthCollect> currencyExchange (List<ChbMonthCollect> listAmountTotal,Date batchDateEnd,Date batchDateStart){
		String exchangeSource=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE);//获取系统参数中债汇率取值来源
		//获取美元兑 CFETS:外汇交易中心
		List<ccyExcharge> USDExchangeList = svrMonthCollectInterfMapper.USDExchange(exchangeSource,DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
		ccyExcharge USDExchangeCNYList = svrMonthCollectInterfMapper.USDExchangeCNY(exchangeSource,DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
		Map<String,ccyExcharge>USDExchangeMap = new HashMap<String,ccyExcharge>();
		for(ccyExcharge USDExcharge :USDExchangeList){
			USDExchangeMap.put(USDExcharge.getExchargeCode(),USDExcharge);
		}
		//获取人民币兑 SAFE：外管局
		List<ccyExcharge> CNYExchangeList = svrMonthCollectInterfMapper.CNYExchange(exchangeSource,DateUtil.format(batchDateStart),DateUtil.format(batchDateEnd));
		Map<String,ccyExcharge>CNYExchangeMap = new HashMap<String,ccyExcharge>();
		for(ccyExcharge CNYExcharge :CNYExchangeList){
			CNYExchangeMap.put(CNYExcharge.getExchargeCode(),CNYExcharge);
		}
		//开始汇率折算
		if (!listAmountTotal.isEmpty() && listAmountTotal.size() != 0) {
			for (int i = 0; i < listAmountTotal.size(); i++) {
				String ccy = listAmountTotal.get(i).getCcy();
				if(ccy.equals("EUR") || ccy.equals("USD") || ccy.equals("JPY")){//SAFE：外管局汇率数据
					String CNYExccy = ccy + "/CNY";
					if(CNYExchangeMap.get(CNYExccy) != null) {
						BigDecimal charge = CNYExchangeMap.get(CNYExccy).getCharge();
						//余额
						if (listAmountTotal.get(i).getBalanceAmt() != null && charge != null) {
							listAmountTotal.get(i).setBalanceAmt(listAmountTotal.get(i).getBalanceAmt().multiply(charge).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
						//总销售金额
						if (listAmountTotal.get(i).getSaleAmountTotal() != null && charge != null) {
							listAmountTotal.get(i).setSaleAmountTotal(listAmountTotal.get(i).getSaleAmountTotal().multiply(charge).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
						//净销售金额
						if (listAmountTotal.get(i).getValueAmountTotal() != null && charge != null) {
							listAmountTotal.get(i).setValueAmountTotal(listAmountTotal.get(i).getValueAmountTotal().multiply(charge).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
						//总销售份额
						if (listAmountTotal.get(i).getSaleSharesTotal() != null && charge != null) {
							listAmountTotal.get(i).setSaleSharesTotal(listAmountTotal.get(i).getSaleSharesTotal().multiply(charge).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
						//净销售份额
						if (listAmountTotal.get(i).getValueSharesTotal() != null && charge != null) {
							listAmountTotal.get(i).setValueSharesTotal(listAmountTotal.get(i).getValueSharesTotal().multiply(charge).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
					}
				}
				if(!ccy.equals("EUR") && !ccy.equals("USD") && !ccy.equals("CNY") && !ccy.equals("JPY")) {//美元对 CFETS:外汇交易中心
					String USDExccy = ccy + "/USD";
					if (USDExchangeMap.get(USDExccy) != null){
						BigDecimal USDcharge = USDExchangeMap.get(USDExccy).getCharge();//外币折算美元
						BigDecimal USDchargeCNY = USDExchangeCNYList.getCharge();//美元折算人民币
						//余额
						if (listAmountTotal.get(i).getBalanceAmt() != null && USDcharge != null && USDchargeCNY != null) {
//						先转美元
							BigDecimal USDAmt = listAmountTotal.get(i).getBalanceAmt().multiply(USDcharge).setScale(6,BigDecimal.ROUND_HALF_UP);
//						再转人民币
							listAmountTotal.get(i).setBalanceAmt(USDAmt.multiply(USDchargeCNY).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
						//总销售金额
						if (listAmountTotal.get(i).getSaleAmountTotal() != null && USDcharge != null && USDchargeCNY != null) {
//						先转美元
							BigDecimal USDSaleAmountTotal = listAmountTotal.get(i).getSaleAmountTotal().multiply(USDcharge).setScale(6,BigDecimal.ROUND_HALF_UP);
//						再转人民币
							listAmountTotal.get(i).setSaleAmountTotal(USDSaleAmountTotal.multiply(USDchargeCNY).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
						//净销售金额
						if (listAmountTotal.get(i).getValueAmountTotal() != null && USDcharge != null && USDchargeCNY != null) {
//						先转美元
							BigDecimal USDValueAmountTotal = listAmountTotal.get(i).getValueAmountTotal().multiply(USDcharge).setScale(6,BigDecimal.ROUND_HALF_UP);
//						再转人民币
							listAmountTotal.get(i).setValueAmountTotal(USDValueAmountTotal.multiply(USDchargeCNY).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
						//总销售份额
						if (listAmountTotal.get(i).getSaleSharesTotal() != null && USDcharge != null && USDchargeCNY != null) {
//						先转美元
							BigDecimal USDSaleSharesTotal = listAmountTotal.get(i).getSaleSharesTotal().multiply(USDcharge).setScale(6,BigDecimal.ROUND_HALF_UP);
//						再转人民币
							listAmountTotal.get(i).setSaleSharesTotal(USDSaleSharesTotal.multiply(USDchargeCNY).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
						//净销售金额
						if (listAmountTotal.get(i).getValueSharesTotal() != null && USDcharge != null && USDchargeCNY != null) {
//						先转美元
							BigDecimal USDValueSharesTotal = listAmountTotal.get(i).getValueSharesTotal().multiply(USDcharge).setScale(6,BigDecimal.ROUND_HALF_UP);
//						再转人民币
							listAmountTotal.get(i).setValueSharesTotal(USDValueSharesTotal.multiply(USDchargeCNY).setScale(6,BigDecimal.ROUND_HALF_UP));
						}
					}
				}
			}
		}
		return listAmountTotal;
	}
	/**

	 * 获取指定日期下个月的第一天
	 * @param dateStr
	 * @return
	 */
	public static Date getFirstDayOfNextMonth(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH,1);
			calendar.add(Calendar.MONTH, 1);
			return calendar.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据系统参数来控制显示份额还是金额
	 * @param detail
	 * @return
	 */
	public ChbMonthCollect  amtOrShares(ChbMonthCollect detail){
		String IsOrNotAmt  = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_CHBMONTH_REPORT);
		if(IsOrNotAmt.equals("02") && IsOrNotAmt != "" && IsOrNotAmt !=null){
			detail.setSaleAmountTotal(detail.getSaleSharesTotal());
			detail.setValueAmountTotal(detail.getValueSharesTotal());
			detail.setBalanceAmt(detail.getBalanceShares());
			detail.setValueSharesTotal(null);
			detail.setSaleSharesTotal(null);
			detail.setBalanceShares(null);
			if(detail.getHolderNum() == null){
				detail.setHolderNum(BigDecimal.ZERO);
			}
		}else{
			detail.setValueSharesTotal(null);
			detail.setSaleSharesTotal(null);
			detail.setBalanceShares(null);
			if(detail.getHolderNum() == null){
				detail.setHolderNum(BigDecimal.ZERO);
			}
		}
		return detail;
	}

	/**
	 * 分批插入临时表
	 * @param notChangeEquHoldList
	 */
	private void insertMonthTemp(List<ChbMonthCollect> notChangeEquHoldList){
		if(notChangeEquHoldList.size()<1000){
			List<ChbMonthCollect> newList = new ArrayList<>();
			newList.addAll(notChangeEquHoldList);
			int end = 100;
			//分批处理
			int part = newList.size() / end;
			for(int i = 0;i < part;i++){
				if(newList.size() <= 100){
					break;
				}
				svrMonthCollectInterfMapper.insertMonthTemp(newList.subList(0,end));
				newList.subList(0,end).clear();
			}
			//最后剩下的数据
			if(!CollectionUtils.isEmpty(newList)){
				svrMonthCollectInterfMapper.insertMonthTemp(newList);
			}
		}else {// 大于1000使用sqlloader入库
			try {
				//sqlloader插入
				ImpTable table = new ImpTable("CHB_MONTH_COUNT_TMP");
				table.setColumns(ImportGenerator.createImpTableColumnsWithOri(table.getName()));
				// 临时文件存放目录
				String path = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);

				batchInsertService.exeBatchldrWithOri(path, notChangeEquHoldList, table);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	//处理持有余额（份额*最新净值）
	public void dealBalanceMoney(Map<String,BigDecimal> PeriodyieldMap,List<ChbMonthCollect> listAmountTotalExList){
		listAmountTotalExList.forEach(detail -> {
			if (detail.getBalanceShares() != null && PeriodyieldMap.get(detail.getPrdRegistCode()) != null) {
				detail.setBalanceAmt(detail.getBalanceShares().multiply(PeriodyieldMap.get(detail.getPrdRegistCode())).setScale(6, BigDecimal.ROUND_HALF_UP));
			}
		});
	}
}
