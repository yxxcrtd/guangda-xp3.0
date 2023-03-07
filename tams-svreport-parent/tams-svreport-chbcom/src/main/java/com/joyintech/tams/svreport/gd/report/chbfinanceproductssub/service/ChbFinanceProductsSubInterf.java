package com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.dao.ChbFinanceProductsSubInterfMapper;
import com.joyintech.tams.svreport.gd.report.chbfinanceproductssub.dto.ChbFinanceProductsSub;
import com.joyintech.tools.collectors.JoyinCollectors;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbFinanceProductsSubInterf extends BaseService{

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChbFinanceProductsSubInterfMapper chbFinanceProductsSubInterfMapper;

	@Autowired
	private SqlSessionTemplate template;

	public void generateData(String batchDate, String batchDateStart, String batchDateEnd) {
		// 判断跑批日上月是否已进行统计，若已统计则提示但继续跑批
		List<ChbFinanceProductsSub> checkCounted = chbFinanceProductsSubInterfMapper.checkIsCounted(batchDateEnd);
		if (CollectionUtils.isNotEmpty(checkCounted)){
			logger.info("跑批日上月已进行统计！");
		}
		//1、开始检数据
		logger.info("理财产品登记月报每月明细汇总信息检索开始------->>>>>>");
		List<ChbFinanceProductsSub> ProductsList = this.getList(batchDate,batchDateStart,batchDateEnd);
		logger.info("理财产品登记月报每月明细汇总信息检索结束-------<<<<<<");

		if(ProductsList.size() == 0){
			logger.info("没有检索到数据,批处理结束!");
			return;
		}
		//更新表
		logger.info("理财产品登记月报每月明细汇总信息入库开始------->>>>>>");
		dbData(ProductsList,batchDate,batchDateStart ,batchDateEnd);
		logger.info("理财产品登记月报每月明细汇总信息入库结束-------<<<<<<");

}
	//查询表
	public List<ChbFinanceProductsSub> getList(String batchDate, String batchDateStart , String batchDateEnd) {
		List<ChbFinanceProductsSub> ProductsList = chbFinanceProductsSubInterfMapper.selectProducts(batchDateEnd);
		return ProductsList;
	}

	/**
	 * 先删后插
	 */
	private void dbData(List<ChbFinanceProductsSub> ProductsList,String batchDate,String batchDateStart ,String batchDateEnd) {

		logger.info("当月汇总数据清除开始----------");
			//根据期数删除月报主表
			chbFinanceProductsSubInterfMapper.deleteProducts(batchDateEnd);
		logger.info("当月汇总数据清除结束----------");

		ProductsList.forEach(entity->{
			//处理组别
			if(!StringUtil.isEmpty(entity.getGroupId())){
				if(StringUtil.equals(entity.getGroupId(),"bta")){
					entity.setGroupId("BTA");
				}else if(StringUtil.equals(entity.getGroupId(),"wechatpay") || StringUtil.equals(entity.getGroupId(),"netwealth")){
					entity.setGroupId("FSP");
				}else if(StringUtil.equals(entity.getGroupId(),"ewta")){
					entity.setGroupId("TYTA");
				}
			}
		});

		logger.info("============插入开始===============" + DateUtil.getCurrentDateTime());
		if (CollectionUtils.isNotEmpty(ProductsList)) {
			int count = 0;
			SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
			try {
				ChbFinanceProductsSubInterfMapper batchMapper = session.getMapper(ChbFinanceProductsSubInterfMapper.class);
				List<ChbFinanceProductsSub> List = new ArrayList<ChbFinanceProductsSub>();
				List<ChbFinanceProductsSub> amountTotal=chbFinanceProductsSubInterfMapper.selectAmountTotal();
				Map<String, ChbFinanceProductsSub> amountTotalMap = amountTotal.stream().collect(JoyinCollectors.toMap(key -> key.getProdId(), value -> value));
				String exchangeSource= SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE);//汇率计算

				/*rateMap  汇率集合(key:日期+折算币种+汇率计算规则+原币种，value:汇率)*/

				for (ChbFinanceProductsSub asset : ProductsList) {
					String registSeqno = UUID.randomUUID().toString().toUpperCase();
					asset.setRegistSeqno(registSeqno);


					if("01".equals(asset.getRiskLevel())){
						asset.setRiskLevel("1R");
					}else if("02".equals(asset.getRiskLevel())) {
						asset.setRiskLevel("2R");
					}else if("03".equals(asset.getRiskLevel())) {
						asset.setRiskLevel("3R");
					}else if("04".equals(asset.getRiskLevel())) {
						asset.setRiskLevel("4R");
					}else if("05".equals(asset.getRiskLevel())) {
						asset.setRiskLevel("5R");
					}

					if("01".equals(asset.getTermRightFlag())){
						asset.setTermRightFlag("是");
					}

					if("02".equals(asset.getTermRightFlag())){
						asset.setTermRightFlag("否");
					}
                    //募集起始日期
					String raiseVdate = DateUtil.format(asset.getRaiseVdate());
					//募集结束日期
					String raiseEnddate = DateUtil.format(asset.getRaiseEnddate());

					if(amountTotalMap.get(asset.getProdId())==null){
						asset.setTotalAmt(BigDecimal.ZERO);
					}
					else if (amountTotalMap.get(asset.getProdId())!=null){
						String ccyOriginal=amountTotalMap.get(asset.getProdId()).getCcy();
						String ccyConvert=asset.getRaiseCcy();
						Date settDate=asset.getDateStatus();

						BigDecimal amt=amountTotalMap.get(asset.getProdId()).getTotalAmt();

						BigDecimal exchangeAmountTotal= ChbCcyConvertUtil.exchangeMoney( ccyOriginal,ccyConvert, settDate,amt,
								null, exchangeSource);
						asset.setTotalAmt(exchangeAmountTotal);
					}

					//处于募集期及未到募集期的产品募集金额写成0(募集结束日大于跑批日的上个月最后一天)
					if(StringUtil.isNotEmpty(raiseEnddate)){
						if(StringUtil.equals(compareTime(raiseEnddate,batchDateEnd,"yyyy-MM-dd"),-1)){
							asset.setTotalAmt(BigDecimal.ZERO);
						}
					}


					List.add(asset);

					if(List.size() == 500) {
						batchMapper.insertIntoReport(List);
						List.clear();
					}
					count++;
					if (count % 5000 == 0) {
						session.commit();
						// 清理缓存，防止溢出
						session.clearCache();
					}
				}
				if(CollectionUtils.isNotEmpty(List)){
					batchMapper.insertIntoReport(List);
				}
				session.commit();
				// 清理缓存，防止溢出
				session.clearCache();
			} catch (Exception e) {
				session.rollback();
				logger.error(e.getMessage(), e);
			} finally {
				session.close();
			}
		}
		logger.info("============插入结束===============" +DateUtil.getCurrentDateTime());
	}


	/**
	 * @description: 两个String类型，按照日期格式对比
	 *              eg:
	 *                  dateOne：2015-12-26
	 *                  dateTwo：2015-12-26
	 *                  dateFormatType: yyyy-MM-dd
	 *                  返回类型：-1：dateOne小于dateTwo， 0：dateOne=dateTwo ，1：dateOne大于dateTwo
	 * @param dateOne
	 * @param dateTwo
	 * @param dateFormatType：yyyy-MM-dd / yyyy-MM-dd HH:mm:ss /等
	 * @return -1，0，1，100
	 */
	public static int compareTime(String dateOne, String dateTwo , String dateFormatType){

		DateFormat df = new SimpleDateFormat(dateFormatType);
		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();

		try {
			calendarStart.setTime(df.parse(dateOne));
			calendarEnd.setTime(df.parse(dateTwo));
		} catch (ParseException e) {
			e.printStackTrace();
			return 100;
		}

		int result = calendarStart.compareTo(calendarEnd);
		if(result > 0){
			result = 1;
		}else if(result < 0){
			result = -1;
		}else{
			result = 0 ;
		}
		return result ;
	}

}
