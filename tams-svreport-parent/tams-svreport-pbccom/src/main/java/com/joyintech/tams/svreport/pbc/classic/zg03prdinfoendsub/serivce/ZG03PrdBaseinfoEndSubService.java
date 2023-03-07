package com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.serivce;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dao.PbcPrdBaseinfoEndSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao.ZG01PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dao.ZG03PbcPrdBaseinfoEndSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.common.service.PbcClassicsSubService;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.svreport.position.dto.ChbPrdFeePosition;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.util.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * 功能:资管产品终止信息
 * @since 1.7.0
 * @version 1.7.0
 * @作者：dingfeng
 * @创建时间：2022年2022月11日
 * @修改记录：
 */
@Service(value="zg03PrdBaseinfoEndSubService")
@Transactional(rollbackFor = Exception.class)
public class ZG03PrdBaseinfoEndSubService extends PbcClassicsSubService<ZG03PbcPrdBaseinfoEndSub> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ZG03PbcPrdBaseinfoEndSubMapper zg03PbcPrdBaseinfoEndSubMapper;

	@Autowired
	private SqlSessionTemplate template;

	/**
	 * 数据批量一次性insert条数(单条数据字段较多)
	 */
	@Value("${application.reportInsertLongCount}")
	private int reportInsertLongCount;

	/**
	 * 数据批量一次查询或者删除条数
	 */
	@Value("${application.reportSelectOrDeleteCount}")
	private int reportSelectOrDeleteCount;

	@Autowired
	private PbcDataCheckUtil pbcDataCheckUtil;

	@Autowired
	private ZG01PbcPrdBaseinfoSubMapper zg01PbcPrdBaseinfoSubMapper;

	@Override
	public void genReport(Date executeDate, List<String> prodIdList) {
		generateRegistData(executeDate, prodIdList);
	}

	public void generateRegistData(Date executeDate, List<String> prodIdList) {
		// 根据跑批日和产品id集合查询所有需要生成登记的产品
		logger.debug("资管产品终止信息：查询开始----------");
		//查询人行产品资产池的数据
		List<ZG01PbcPrdBaseinfoSub> list=zg01PbcPrdBaseinfoSubMapper.selectAllPbcProdId();
		Map<String,String> map= list.stream().collect(Collectors.toMap(ZG01PbcPrdBaseinfoSub::getProdId,ZG01PbcPrdBaseinfoSub::getPbcProdId ));


		String dividendReinvestmentCustomerReceiptsFlag = SvrDataUtil.getSysParam(SVReportGlobalConstant.DIVIDEND_REINVESTMENT_CUSTOMER_RECEIPTS_FLAG);
		List<ZG03PbcPrdBaseinfoEndSub> prodidsublist = new ArrayList<ZG03PbcPrdBaseinfoEndSub>();
		StringBuffer tradType = new StringBuffer();
		tradType.append(DictCodeListExt2.业务种类_到期兑付.getValue());
		if("01".equals(dividendReinvestmentCustomerReceiptsFlag)){
			tradType.append(",");
			tradType.append(DictCodeListExt2.业务种类_收益再投资.getValue());
		}
		if (!CollectionUtils.isEmpty(prodIdList)) {
			int end = 0;
			for (int i = 0; i < prodIdList.size();) {
				end = i + 100;
				if (end > prodIdList.size()) {
					end = prodIdList.size();
				}
				prodidsublist.addAll(zg03PbcPrdBaseinfoEndSubMapper.getProdIdsByEndSub(DateUtil.format(executeDate),
						tradType.toString(), prodIdList.subList(i, end)));
				i = end;
			}
		} else {
			prodidsublist = zg03PbcPrdBaseinfoEndSubMapper.getProdIdsByEndSub(DateUtil.format(executeDate),
					tradType.toString(), prodIdList);
		}
		if (!CollectionUtils.isEmpty(prodidsublist)) {
			List<List<ZG03PbcPrdBaseinfoEndSub>> endprods = createList(prodidsublist, 50);
			// 产品基础信息
			List<ZG03PbcPrdBaseinfoEndSub> prodidlist = new ArrayList<ZG03PbcPrdBaseinfoEndSub>();
			// 费用头寸信息
			List<ChbPrdFeePosition> feeList = new ArrayList<ChbPrdFeePosition>();
			// 产品交易头寸信息
			List<ChbPrdPosition> dealList = new ArrayList<ChbPrdPosition>();
			// 新增数据
			List<ZG03PbcPrdBaseinfoEndSub> insertlist = new ArrayList<ZG03PbcPrdBaseinfoEndSub>();
			// 删除数据
			List<ZG03PbcPrdBaseinfoEndSub> deletelist = new ArrayList<ZG03PbcPrdBaseinfoEndSub>();

			// 分批查询
			for (int i = 0; i < endprods.size(); i++) {
				// 查询产品基本信息
				List<ZG03PbcPrdBaseinfoEndSub> prodinfolist = zg03PbcPrdBaseinfoEndSubMapper.getProdInfoByEndSub(endprods.get(i),tradType.toString());
				// 查询费用头寸
				List<ChbPrdFeePosition> fee = zg03PbcPrdBaseinfoEndSubMapper.getPrdFeePositionList(endprods.get(i));
				// 查询交易头寸
				List<ChbPrdPosition> deal = zg03PbcPrdBaseinfoEndSubMapper.getPrdDealPositionList(endprods.get(i));
				prodidlist.addAll(prodinfolist);
				feeList.addAll(fee);
				dealList.addAll(deal);
			}
			logger.debug("资管产品终止信息：查询结束----------");
			if (!CollectionUtils.isEmpty(prodidlist)) {
				Map<String, ZG03PbcPrdBaseinfoEndSub> prodidmap = new HashMap<String, ZG03PbcPrdBaseinfoEndSub>();
				// 判断到期交易是否全是D状态
				for (ZG03PbcPrdBaseinfoEndSub prodForTerminationDto : prodidlist) {
					if (!DictCodeListExt2.数据状态_删除.getValue().equals(prodForTerminationDto.getStatus())) {
						prodidmap.put(prodForTerminationDto.getProdId(), prodForTerminationDto);
					} else if (prodidmap.get(prodForTerminationDto.getProdId()) == null
							&& DictCodeListExt2.数据状态_删除.getValue().equals(prodForTerminationDto.getStatus())) {
						prodidmap.put(prodForTerminationDto.getProdId(), prodForTerminationDto);
					}
				}
				Iterator<Entry<String, ZG03PbcPrdBaseinfoEndSub>> iterator = prodidmap.entrySet().iterator();
				logger.debug("资管产品终止信息数据处理：处理开始----------");
				while (iterator.hasNext()) {
					ZG03PbcPrdBaseinfoEndSub sub = iterator.next().getValue();
					// 临时费用list
					List<ChbPrdFeePosition> listFee = new ArrayList<ChbPrdFeePosition>();
					// 临时交易list
					List<ChbPrdPosition> listDeal = new ArrayList<ChbPrdPosition>();
					// 费用处理
					for (ChbPrdFeePosition chbPrdFeeDeal : feeList) {
						if (sub.getProdId().equals(chbPrdFeeDeal.getProdId())) {
							listFee.add(chbPrdFeeDeal);
						}
					}
					// 交易处理
					for (ChbPrdPosition pos : dealList) {
						if (sub.getProdId().equals(pos.getProdId())) {
							listDeal.add(pos);
						}
					}
					sub.setFeePositionList(listFee);
					sub.setDealPositionList(listDeal);
					// 按产品维度查询产品终止信息map(key:币种)
					Map<String,ZG03PbcPrdBaseinfoEndSub> regMap = new HashMap<String,ZG03PbcPrdBaseinfoEndSub>();
					List<ZG03PbcPrdBaseinfoEndSub> regList = zg03PbcPrdBaseinfoEndSubMapper.selectRegList(sub);
//					String status = "";
					if(!CollectionUtils.isEmpty(regList)){
						//按照产品生成map
						for(ZG03PbcPrdBaseinfoEndSub reg : regList){
							String key = reg.getCcy()+"&"+reg.getPbcProdId();
							regMap.put(key, reg);
//							status = reg.getStatus();
						}
					}
					Map<String, ZG03PbcPrdBaseinfoEndSub> result = dataProcess(sub, executeDate);
					// 没有已登记的数据且没有头寸，删除未报送数据
					if(MapUtils.isEmpty(regMap)&&MapUtils.isEmpty(result)){
						deletelist.add(sub);
					}

					Iterator<Entry<String, ZG03PbcPrdBaseinfoEndSub>> itera = result.entrySet().iterator();
					// 循环生成的数据
					while (itera.hasNext()) {
						ZG03PbcPrdBaseinfoEndSub res = itera.next().getValue();

						//数据检查用字段，不入库
						if(map.containsKey(res.getProdId())){
							res.setPbcPordIdOut(map.get(res.getProdId()));
						}else {
							if(res.getPbcProdId().contains("INS")){
								res.setPbcPordIdOut(res.getPbcProdId().substring(3));
							}else {
								res.setPbcPordIdOut(res.getPbcProdId());
							}
						}
						// 兑付客户收益率 保留小数位
						res.setCusYearYield(res.getCusYearYield() == null ? null : res.getCusYearYield().setScale(5, BigDecimal.ROUND_HALF_UP));

						//光大个性化.不考虑资产状态.直接出
						boolean iszero = true;
						if (CollectionUtils.isEmpty(regMap.keySet())) {// 没有已报送的
							// 若业务数据状态为删除则不生成登记数据
							if (DictCodeListExt2.数据状态_删除.getValue().equals(sub.getStatus())) {
								// 删除数据(未报送的删除)
								deletelist.add(res);
								continue;
							} else {
								// 判断产品持仓,剩余现金是否为零
								if (iszero) {
									//设置数据状态
									res.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
									insertlist.add(res);
									// 删除数据(未报送的删除)
									deletelist.add(res);
								}
							}
						}
					}
				}
			}
			logger.debug("资管产品终止信息数据处理：处理结束----------");

			/**指引补充需求变更  start */
			// 处理检查字段是否显示0 add by wangyaning 20230111
			if(!CollectionUtils.isEmpty(insertlist)){
				dealZeroNumber(insertlist);
			}
			/**指引补充需求变更   end */

			// 入库
			logger.info("资管产品终止信息数据处理：报表生成校验 开始----------");
			List<ZG03PbcPrdBaseinfoEndSub> listData = pbcDataCheckUtil.getTrueData(insertlist, null, DictCodeListExt2.采集平台资管产品终止信息.getValue(), true, DictCodeListExt2.数据校验规则大类_报表生成检查.getValue(), ZG03PbcPrdBaseinfoEndSub.class);
			logger.info("资管产品终止信息数据处理：报表生成校验 结束----------");
			intoDatabase(listData, deletelist);
		}
	}

	private void intoDatabase(List<ZG03PbcPrdBaseinfoEndSub> insertlist, List<ZG03PbcPrdBaseinfoEndSub> deletelist) {
		// 插入前先删除未报送的登记数据
		logger.debug("资管产品终止信息删除未报送数据：删除开始----------");
		// 数据批量一次删除条数,如果参数设置过大则强制定位50
		int batchDeleteCount = reportSelectOrDeleteCount;
		// 每批最后一个的下标
		int batchDeleteLastIndex = batchDeleteCount;
		for (int index = 0; index < deletelist.size();) {
			if (batchDeleteLastIndex > deletelist.size() - 1) {
				batchDeleteLastIndex = deletelist.size();
				zg03PbcPrdBaseinfoEndSubMapper.deleteBatch(deletelist.subList(index, batchDeleteLastIndex));
				break;// 数据插入完毕,退出循环
			} else {
				zg03PbcPrdBaseinfoEndSubMapper.deleteBatch(deletelist.subList(index, batchDeleteLastIndex));
				index = batchDeleteLastIndex;// 设置下一批下标
				batchDeleteLastIndex = index + batchDeleteCount;
			}
		}
		logger.debug("资管产品终止信息删除未报送数据：删除结束----------");
		logger.debug("资管产品终止信息插入数据：插入开始----------");
		int count = 0;
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			ZG03PbcPrdBaseinfoEndSubMapper batchMapper = session.getMapper(ZG03PbcPrdBaseinfoEndSubMapper.class);
			for (ZG03PbcPrdBaseinfoEndSub dto : insertlist) {
				batchMapper.insert(dto);
				count++;
				if (count % 1000 == 0) {
					session.commit();
					// 清理缓存，防止溢出
					session.clearCache();
				}
			}
			session.commit();
			// 清理缓存，防止溢出
			session.clearCache();
		} catch (Exception e) {
			session.rollback();
			logger.error("资管产品终止信息入库报错:" + e.getMessage(), e);
		} finally {
			session.close();
		}
		logger.debug("资管产品终止信息插入数据：插入结束----------");
	}

	private Map<String, ZG03PbcPrdBaseinfoEndSub> dataProcess(ZG03PbcPrdBaseinfoEndSub arg0, Date executeDate) {
		// 币种Map
		Map<String, ZG03PbcPrdBaseinfoEndSub> result = new HashMap<String, ZG03PbcPrdBaseinfoEndSub>();
		// 产品交易头寸
		List<ChbPrdPosition> dealpos = arg0.getDealPositionList();
		// 产品费用头寸
		List<ChbPrdFeePosition> feepos = arg0.getFeePositionList();
		// 汇率来源
		String exchangeSource = CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
		// 汇率Map
		Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();
		// 兑付客户收益
		BigDecimal customProfit = BigDecimal.ZERO;
		// 实际募集金额
		BigDecimal raiseAmt = BigDecimal.ZERO;
		// 取产品头寸的币种Map
		if (!CollectionUtils.isEmpty(dealpos)) {
			for (ChbPrdPosition pos : dealpos) {
				String key = pos.getCcy()+"&"+arg0.getPbcProdId();
				// 兑付客户收益
				customProfit = customProfit
						.add(ChbCcyConvertUtil.exchangeMoney(pos.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
								pos.getCdate(), pos.getTotalIntamt(), rateMap, exchangeSource));
				// 实际募集金额
				raiseAmt = raiseAmt
						.add(ChbCcyConvertUtil.exchangeMoney(pos.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
								pos.getCdate(), pos.getRaiseamt(), rateMap, exchangeSource));
				if (result.get(key) == null) {
					ZG03PbcPrdBaseinfoEndSub res = new ZG03PbcPrdBaseinfoEndSub();
					List<ChbPrdPosition> deal = new ArrayList<ChbPrdPosition>();
					deal.add(pos);
					res.setDealPositionList(deal);
					result.put(key, res);
				} else {
					result.get(key).getDealPositionList().add(pos);
				}
			}
		}
		// 取费用头寸的币种Map
		if (!CollectionUtils.isEmpty(feepos)) {
			for (ChbPrdFeePosition pos : feepos) {
				String key = pos.getCcy()+"&"+arg0.getPbcProdId();
				if (result.get(key) == null) {
					ZG03PbcPrdBaseinfoEndSub res = new ZG03PbcPrdBaseinfoEndSub();
					List<ChbPrdFeePosition> deal = new ArrayList<ChbPrdFeePosition>();
					deal.add(pos);
					res.setFeePositionList(deal);
					result.put(key, res);
				} else {
					if (!CollectionUtils.isEmpty(result.get(key).getFeePositionList())) {
						result.get(key).getFeePositionList().add(pos);
					} else {
						List<ChbPrdFeePosition> deal = new ArrayList<ChbPrdFeePosition>();
						deal.add(pos);
						result.get(key).setFeePositionList(deal);
					}
				}
			}
		}
		//生成BWB(本位币数据)
		if (!CollectionUtils.isEmpty(feepos)) {
			for (ChbPrdFeePosition pos : feepos) {
				if(!DictCodeListExt1.币种_中债_人民币.getValue().equals(pos.getCcy())){
					if(StringUtil.isNotEmpty(pos.getAmt())){
						pos.setAmt(ChbCcyConvertUtil.exchangeMoney(pos.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
								pos.getCdate(), pos.getAmt(), rateMap, exchangeSource));
					}
				}
				pos.setCcy(DictCodeListExt1.币种_人行_本位币.getValue());
				String key = pos.getCcy()+"&"+arg0.getPbcProdId();
				if (result.get(key) == null) {
					ZG03PbcPrdBaseinfoEndSub res = new ZG03PbcPrdBaseinfoEndSub();
					List<ChbPrdFeePosition> deal = new ArrayList<ChbPrdFeePosition>();
					deal.add(pos);
					res.setFeePositionList(deal);
					result.put(key, res);
				} else {
					if (!CollectionUtils.isEmpty(result.get(key).getFeePositionList())) {
						result.get(key).getFeePositionList().add(pos);
					} else {
						List<ChbPrdFeePosition> deal = new ArrayList<ChbPrdFeePosition>();
						deal.add(pos);
						result.get(key).setFeePositionList(deal);
					}
				}
			}
		}
		if (!CollectionUtils.isEmpty(dealpos)) {
			for (ChbPrdPosition pos : dealpos) {
				if(!DictCodeListExt1.币种_中债_人民币.getValue().equals(pos.getCcy())){
					if(StringUtil.isNotEmpty(pos.getTotalIntamt())){
						pos.setTotalIntamt(ChbCcyConvertUtil.exchangeMoney(pos.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
								pos.getCdate(), pos.getTotalIntamt(), rateMap, exchangeSource));
					}
				}
				pos.setCcy(DictCodeListExt1.币种_人行_本位币.getValue());
				String key = pos.getCcy()+"&"+arg0.getPbcProdId();
				if (result.get(key) == null) {
					ZG03PbcPrdBaseinfoEndSub res = new ZG03PbcPrdBaseinfoEndSub();
					List<ChbPrdPosition> deal = new ArrayList<ChbPrdPosition>();
					deal.add(pos);
					res.setDealPositionList(deal);
					result.put(key, res);
				} else {
                    if (!CollectionUtils.isEmpty(result.get(key).getDealPositionList())) {
                        result.get(key).getDealPositionList().add(pos);
                    } else {
                        List<ChbPrdPosition> deal = new ArrayList<ChbPrdPosition>();
                        deal.add(pos);
                        result.get(key).setDealPositionList(deal);
                    }
				}
			}
		}
		// 计算兑付客户收益率
		BigDecimal cusYearYield = BigDecimal.ZERO;
		BigDecimal basic = new BigDecimal(365);
		if (DictCodeList.计息基础_A_360.getValue().equals(arg0.getBasic())) {
					basic = new BigDecimal(360);
		}
		//现金类管理
		if("01".equals(arg0.getCashManaCate())){
				BigDecimal tdyUnitValueSum = zg03PbcPrdBaseinfoEndSubMapper.gettdyUnitValueSumByProdId(arg0.getProdId(),arg0.getMdate());
				if(StringUtil.isNotEmpty(tdyUnitValueSum)){
					cusYearYield = tdyUnitValueSum.multiply(basic).divide(
							new BigDecimal(10000).multiply(
									new BigDecimal(DateUtil.getBetweenDayNumber(arg0.getVdate(), arg0.getMdate()))),7,
								BigDecimal.ROUND_HALF_UP);
				}else {
					BigDecimal unitValueTotal = zg03PbcPrdBaseinfoEndSubMapper.getunitValueTotalByProdId(arg0.getProdId(),arg0.getMdate());
					if(StringUtil.isNotEmpty(unitValueTotal)){
						cusYearYield = unitValueTotal.subtract(
								new BigDecimal(1)).divide(new BigDecimal(1),7,
								BigDecimal.ROUND_HALF_UP).multiply(basic).divide(
								new BigDecimal(DateUtil.getBetweenDayNumber(arg0.getVdate(), arg0.getMdate())),7,
								BigDecimal.ROUND_HALF_UP);
					}
				}
		}else if ("N".equals(arg0.getProdtype())){
			//净值型管理
			BigDecimal unitValueTotal = zg03PbcPrdBaseinfoEndSubMapper.getunitValueTotalByProdId(arg0.getProdId(),arg0.getMdate());
			if(StringUtil.isNotEmpty(unitValueTotal)){
				cusYearYield = unitValueTotal.subtract(
						new BigDecimal(1)).divide(new BigDecimal(1),7,
						BigDecimal.ROUND_HALF_UP).multiply(basic).divide(
						new BigDecimal(DateUtil.getBetweenDayNumber(arg0.getVdate(), arg0.getMdate())),7,
						BigDecimal.ROUND_HALF_UP);
			}
		}
//		if (raiseAmt.compareTo(BigDecimal.ZERO) != 0) {
//			/**
//			 * 客户实际年化收益率=兑付客户收益÷实际募集金额×全年实际天数÷产品实际运作天数
//			 */
//			BigDecimal basic = new BigDecimal(365);
//			if (customProfit.compareTo(BigDecimal.ZERO) != 0) {
//				if (DictCodeList.计息基础_A_360.getValue().equals(arg0.getBasic())) {
//					basic = new BigDecimal(360);
//				}
//				cusYearYield = customProfit.multiply(basic)
//						.divide(raiseAmt.multiply(
//								new BigDecimal(DateUtil.getBetweenDayNumber(arg0.getVdate(), arg0.getMdate()))), 7,
//								BigDecimal.ROUND_HALF_UP);
//			}
//		}
		// 报送截止日生成
		// 当前批次报送时点集合
		Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
		List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG03资管产品终止信息.getValue(),
                DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
                arg0.getMdate(),SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
		// 循环币种Map(同一产品按币种维度出值)
		Iterator<Entry<String, ZG03PbcPrdBaseinfoEndSub>> iterator = result.entrySet().iterator();
		// 生成bwb 币种数据
		Map<String, ZG03PbcPrdBaseinfoEndSub> bwbResult = new HashMap<String, ZG03PbcPrdBaseinfoEndSub>();
		while (iterator.hasNext()) {
			Entry<String, ZG03PbcPrdBaseinfoEndSub> entry = iterator.next();
			ZG03PbcPrdBaseinfoEndSub sub = entry.getValue();
			String ccy = entry.getKey().split("&")[0];
			// 发行机构实现收入
			BigDecimal profit = BigDecimal.ZERO;
			// 发行机构实现收入折人民币
			BigDecimal profitCny = BigDecimal.ZERO;
			// 兑付客户收益
			BigDecimal customAmt = BigDecimal.ZERO;
			// 兑付客户收益折人民币
			BigDecimal customAmtCny = BigDecimal.ZERO;
			if (!CollectionUtils.isEmpty(sub.getDealPositionList())) {
				for (ChbPrdPosition dto : sub.getDealPositionList()) {
					customAmt = customAmt.add(dto.getTotalIntamt());
				}
			}
			if (!CollectionUtils.isEmpty(sub.getFeePositionList())) {
				for (ChbPrdFeePosition dto : sub.getFeePositionList()) {
					profit = profit.add(dto.getAmt());
				}
			}
			if (!DictCodeListExt1.币种_中债_人民币.getValue().equals(ccy)) {
				profitCny = ChbCcyConvertUtil.exchangeMoney(ccy, DictCodeListExt1.币种_中债_人民币.getValue(), arg0.getMdate(),
						profit, rateMap, exchangeSource);
				customAmtCny = ChbCcyConvertUtil.exchangeMoney(ccy, DictCodeListExt1.币种_中债_人民币.getValue(),
						arg0.getMdate(), customAmt, rateMap, exchangeSource);
			}else {
				profitCny = profit;
				customAmtCny = customAmt;
			}

			// 兑付客户收益
			sub.setCustomProfit(customAmt);
			// 兑付客户收益折人民币
			sub.setCustomProfitCny(customAmtCny);
			// 兑付客户收益率
			//sub.setCusYearYield(cusYearYield.multiply(new BigDecimal(100)));
			// 发行机构实现收入
			sub.setProfit(profit);
			// 发行机构实现收入折人民币
			sub.setProfitCny(profitCny);
			// 人行产品代码
			sub.setPbcProdId(arg0.getPbcProdId());
			// 产品代码
			sub.setProdId(arg0.getProdId());
			// 产品实际终止日
			sub.setMdate(arg0.getMdate());
			// 币种
			sub.setCcy(ccy);
			// 报送状态
			sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
			// 主键
			sub.setRegistSeqno(IDGenerator.generate(36));
			// 数据日期
			sub.setStatusDate(sub.getMdate());
			// 创建人
			sub.setCreateUser(CommonUtil.getUserName());
			// 更新人
			sub.setUpdateUser(CommonUtil.getUserName());
			// 创建部门
			sub.setCreateDept(CommonUtil.getDeptCode());
			// 机构代码
			sub.setOrgCode(CommonUtil.getOrgCode());
			if (!StringUtil.isEmptyList(dateList)) {
				sub.setPbcSubmitDeadline(dateList.get(0));
				sub.setSubmitDeadline(dateList.get(1));
			}
			if(DictCodeListExt1.币种_人行_本位币.getValue().equals(ccy)) {
				ZG03PbcPrdBaseinfoEndSub sub1 = new ZG03PbcPrdBaseinfoEndSub();
				// 兑付客户收益率
				sub1.setCusYearYield(cusYearYield.multiply(new BigDecimal(100)));
				// 人行产品代码
				sub1.setPbcProdId(arg0.getPbcProdId());
				// 产品代码
				sub1.setProdId(arg0.getProdId());
				// 产品实际终止日
				sub1.setMdate(arg0.getMdate());
				// 主键
				sub1.setRegistSeqno(IDGenerator.generate(36));
				// 报送状态
				sub1.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
				sub1.setStatusDate(sub.getMdate());
				if (!StringUtil.isEmptyList(dateList)) {
					sub1.setPbcSubmitDeadline(dateList.get(0));
					sub1.setSubmitDeadline(dateList.get(1));
				}
				bwbResult.put(sub1.getCcy()+"&"+arg0.getPbcProdId(), sub1);
			}
		}
		result.putAll(bwbResult);
		return result;
	}

	private List<List<ZG03PbcPrdBaseinfoEndSub>> createList(List<ZG03PbcPrdBaseinfoEndSub> targe, int size) {
		List<List<ZG03PbcPrdBaseinfoEndSub>> listArr = new ArrayList<List<ZG03PbcPrdBaseinfoEndSub>>();
		// 获取被拆分的数组个数
		int arrSize = targe.size() % size == 0 ? targe.size() / size : targe.size() / size + 1;
		for (int i = 0; i < arrSize; i++) {
			List<ZG03PbcPrdBaseinfoEndSub> sub = new ArrayList<ZG03PbcPrdBaseinfoEndSub>();
			// 把指定索引数据放入到list中
			for (int j = i * size; j <= size * (i + 1) - 1; j++) {
				if (j <= targe.size() - 1) {
					sub.add(targe.get(j));
				}
			}
			listArr.add(sub);
		}
		return listArr;
	}

	private boolean compareMap(Map<String, ZG03PbcPrdBaseinfoEndSub> regMap, Map<String, ZG03PbcPrdBaseinfoEndSub> result) {
		if(MapUtils.isEmpty(regMap)||MapUtils.isEmpty(result)){
			return false;
		}
		if(regMap.size()!=result.size()){
			return false;
		}
		Iterator<Entry<String, ZG03PbcPrdBaseinfoEndSub>> iterator = regMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, ZG03PbcPrdBaseinfoEndSub> entry = iterator.next();
			ZG03PbcPrdBaseinfoEndSub reg = entry.getValue();
			String key = entry.getKey();
			if(result.get(key)==null||!compareData(reg,result.get(key))){
				return false;
			}
		}
		return true;
	}
	
	private boolean compareData(ZG03PbcPrdBaseinfoEndSub dto,ZG03PbcPrdBaseinfoEndSub regdto){
		ZG03PbcPrdBaseinfoEndSub compare1 = genDto(dto);
		ZG03PbcPrdBaseinfoEndSub compare2 = genDto(regdto);
        if(JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))){
            return true;
        }else{
            return false;
        }
    }

	private ZG03PbcPrdBaseinfoEndSub genDto(ZG03PbcPrdBaseinfoEndSub dto) {
		ZG03PbcPrdBaseinfoEndSub reg = new ZG03PbcPrdBaseinfoEndSub();
		reg.setPbcProdId(dto.getPbcProdId());
		reg.setMdate(dto.getMdate());
		reg.setCcy(dto.getCcy());
		reg.setProfit((dto.getProfit()==null?BigDecimal.ZERO:dto.getProfit()).setScale(2, BigDecimal.ROUND_HALF_UP));
		reg.setProfitCny((dto.getProfitCny()==null?BigDecimal.ZERO:dto.getProfitCny()).setScale(2,BigDecimal.ROUND_HALF_UP));
		reg.setCustomProfit((dto.getCustomProfit()==null?BigDecimal.ZERO:dto.getCustomProfit()).setScale(2, BigDecimal.ROUND_HALF_UP));
		reg.setCustomProfitCny((dto.getCustomProfitCny()==null?BigDecimal.ZERO:dto.getCustomProfitCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
		reg.setCusYearYield((dto.getCusYearYield()==null?BigDecimal.ZERO:dto.getCusYearYield()).setScale(12,BigDecimal.ROUND_HALF_UP));
		return reg;
	}


	/**
	 * 根据PBC_IS_SHOW_ZERO参数处理字段为0或者空用于校验 add by wangyaning 20230111
	 * @param insertlist
	 */
	private void dealZeroNumber(List<ZG03PbcPrdBaseinfoEndSub> insertlist) {
		String pbcIsShowZero = SvrDataUtil.getSysParam("PBC_IS_SHOW_ZERO");;
		for(ZG03PbcPrdBaseinfoEndSub tempDto: insertlist) {
			//处理为空
			if (StringUtil.equals(pbcIsShowZero,"01")) {
				if(StringUtil.isEmpty(tempDto.getCcy())){
					continue;
				}
				//发行机构实现收入
				if (!ObjectUtils.isEmpty(tempDto.getProfit())) {
					if(tempDto.getProfit().compareTo(BigDecimal.ZERO) == 0){
						tempDto.setProfit(null);
					}
				}
				//发行机构实现收入折人民币
				if (!ObjectUtils.isEmpty(tempDto.getProfitCny())) {
					if(tempDto.getProfitCny().compareTo(BigDecimal.ZERO) == 0){
						tempDto.setProfitCny(null);
					}
				}
				//兑付客户收益
				if (!ObjectUtils.isEmpty(tempDto.getCustomProfit())) {
					if(tempDto.getCustomProfit().compareTo(BigDecimal.ZERO) == 0){
						tempDto.setCustomProfit(null);
					}
				}
				//兑付客户收益折人民币
				if (!ObjectUtils.isEmpty(tempDto.getCustomProfitCny())) {
					if(tempDto.getCustomProfitCny().compareTo(BigDecimal.ZERO) == 0){
						tempDto.setCustomProfitCny(null);
					}
				}

			} else if (StringUtil.equals(pbcIsShowZero,"02")) {
				if(StringUtil.isEmpty(tempDto.getCcy())){
					continue;
				}
				//处理成0
				//发行机构实现收入
				if (ObjectUtils.isEmpty(tempDto.getProfit())) {
					tempDto.setProfit(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
                //发行机构实现收入折人民币
				if (ObjectUtils.isEmpty(tempDto.getProfitCny())) {
					tempDto.setProfitCny(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				//兑付客户收益
				if (ObjectUtils.isEmpty(tempDto.getCustomProfit())) {
					tempDto.setCustomProfit(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				//兑付客户收益折人民币
				if (ObjectUtils.isEmpty(tempDto.getCustomProfitCny())) {
					tempDto.setCustomProfitCny(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}
		}
	}
}
