package com.joyintech.tams.svreport.gd.report.chbassetselementssub.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.gd.report.chbassetselementssub.dao.ChbAssetsElementsSubInterfMapper;
import com.joyintech.tams.svreport.gd.report.chbassetselementssub.dto.ChbAssetsElementsSub;
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

import java.util.*;

@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbAssetsElementsSubInterf extends BaseService{

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChbAssetsElementsSubInterfMapper chbAssetsElementsSubInterfMapper;

	@Autowired
	private SqlSessionTemplate template;

	public void generateData(String batchDate,String batchDateStart ,String batchDateEnd) {
		//1、开始检数据
		logger.info("理财产品资产要素登记月报每月明细汇总信息检索开始------->>>>>>");
		List<ChbAssetsElementsSub> ProductsList = this.getList(batchDate,batchDateStart,batchDateEnd);
		logger.info("理财产品资产要素登记月报每月明细汇总信息检索结束-------<<<<<<");

		if(ProductsList.size() == 0){
			logger.info("没有检索到数据,批处理结束!");
			return;
		}
		//更新表
		logger.info("理财产品资产要素登记月报每月明细汇总信息入库开始------->>>>>>");
		dbData(ProductsList,batchDate,batchDateStart ,batchDateEnd);
		logger.info("理财产品资产要素登记月报每月明细汇总信息入库结束-------<<<<<<");

}
	//查询表
	public List<ChbAssetsElementsSub> getList(String batchDate, String batchDateStart , String batchDateEnd) {
		List<ChbAssetsElementsSub> ProductsList = chbAssetsElementsSubInterfMapper.selectProducts(batchDateEnd);
		return ProductsList;
	}

	/**
	 * 先删后插
	 */
	private void dbData(List<ChbAssetsElementsSub> ProductsList,String batchDate, String batchDateStart , String batchDateEnd) {

		logger.info("当月汇总数据清除开始----------");
			//根据期数删除月报主表
			chbAssetsElementsSubInterfMapper.deleteProducts(batchDateEnd);
		logger.info("当月汇总数据清除结束----------");

		logger.info("============插入开始===============" + DateUtil.getCurrentDateTime());
		if (CollectionUtils.isNotEmpty(ProductsList)) {
			int count = 0;
			SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
			try {
				List<ChbAssetsElementsSub> trade=chbAssetsElementsSubInterfMapper.selectTrade();
				Map<String, String> tradeMap = trade.stream().collect(JoyinCollectors.toMap(key -> key.getAssetCode(), value -> value.getTradeCounter()));

				List<ChbAssetsElementsSub> market=chbAssetsElementsSubInterfMapper.selectMarket();
				Map<String, String> marketMap = market.stream().collect(JoyinCollectors.toMap(key -> key.getCustomerId(), value -> value.getIndustry2()));


				ChbAssetsElementsSubInterfMapper batchMapper = session.getMapper(ChbAssetsElementsSubInterfMapper.class);
				List<ChbAssetsElementsSub> List = new ArrayList<>();
				for (ChbAssetsElementsSub asset : ProductsList) {

					String registSeqno = UUID.randomUUID().toString().toUpperCase();
					asset.setRegistSeqno(registSeqno);
					asset.setStatusBatch("1");

					if (StringUtil.equals(getAssetType(asset.getAssetType2()), "项目类资产")
							|| StringUtil.equals(getAssetType(asset.getAssetType2()), "计划类资产")
							|| getSAMSAssetType(asset.getAssetType2())) {
						asset.setAssetFlag("1");
					} else {
						asset.setAssetFlag("0");
					}

					//处理投资标的名称
					if (StringUtil.isNotEmpty(asset.getAssetType2())) {
						//场外期权
						if (StringUtil.equals(asset.getAssetType2(), "1404")) {
							asset.setAssetName(null);
						}
						//期货
						if (StringUtil.equals(asset.getAssetType2(), "1403")) {
							asset.setAssetName(null);
						}
						//正逆回购
						if (StringUtil.equals(asset.getAssetType2(), "1108") || StringUtil.equals(asset.getAssetType2(), "1902") ||StringUtil.equals(asset.getAssetType2(), "1107") || StringUtil.equals(asset.getAssetType2(), "1901")) {
							//计算期限品种
							if (asset.getvDate() != null && asset.getmDate() != null) {
								String QxType = getQxType(DateUtil.format(asset.getvDate()), DateUtil.format(asset.getmDate()));
								asset.setAssetName(QxType);
							} else {
								asset.setAssetName(null);
							}
						}
					}


					if (StringUtil.equals(getAssetType(asset.getAssetType2()), "债券类资产")) {
						asset.setFinancierName(asset.getChbIssuerId());
					} else if (StringUtil.equals(getAssetType(asset.getAssetType2()), "项目类资产")) {
						asset.setFinancierName(asset.getChbFinancierId());
					} else if (StringUtil.equals(getAssetType(asset.getAssetType2()), "计划类资产") || StringUtil.equals(getAssetType(asset.getAssetType2()), "基金类资产")) {
						asset.setFinancierName(asset.getChbManagerId());
					} else if (StringUtil.equals(getAssetType(asset.getAssetType2()), "正逆回购") || StringUtil.equals(getAssetType(asset.getAssetType2()), "债券借贷")
							|| StringUtil.equals(getAssetType(asset.getAssetType2()), "场外期权") || StringUtil.equals(getAssetType(asset.getAssetType2()), "期货")) {
						asset.setFinancierName(tradeMap.get(asset.getAssetId()));
					} else {
						asset.setFinancierName(null);
					}
					if (asset.getFinancierName() != null) {
						asset.setIndustryId(marketMap.get(asset.getFinancierName()));
					} else {
						asset.setIndustryId(null);
					}
				}

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

				for (ChbAssetsElementsSub asset : ProductsList) {
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
	 * 根据资产二级分类判断资产大类
	 * @param assetType2
	 * @return
	 */
	public String getAssetType(String assetType2) {
		if(StringUtil.isEmpty(assetType2)){
			return assetType2;
		}
		if(assetType2.equals("2202")||assetType2.equals("2101")||assetType2.equals("2299")||assetType2.equals("1503")||assetType2.equals("1599")||assetType2.equals("1201")
				||assetType2.equals("1202")||assetType2.equals("1203")||assetType2.equals("1204")||assetType2.equals("1205")||assetType2.equals("1206")||assetType2.equals("1207")||assetType2.equals("1208")
				||assetType2.equals("1209")||assetType2.equals("1210")||assetType2.equals("1211")||assetType2.equals("1212")||assetType2.equals("1213")){
            return "项目类资产";
		}
		if(assetType2.equals("1701")||assetType2.equals("1702")||assetType2.equals("1703")||assetType2.equals("1704")||assetType2.equals("1705")||assetType2.equals("1706")
				||assetType2.equals("2601")||assetType2.equals("2602")||assetType2.equals("2604")||assetType2.equals("2605")||assetType2.equals("2606")||assetType2.equals("2607")
				||assetType2.equals("2610")||assetType2.equals("2611")||assetType2.equals("2612")||assetType2.equals("2613")||assetType2.equals("2614")||assetType2.equals("2616")
				||assetType2.equals("2617")||assetType2.equals("2618")||assetType2.equals("1219")||assetType2.equals("1220")||assetType2.equals("2699")){
			return "计划类资产";
		}
		if(assetType2.equals("1101")||assetType2.equals("1102")||assetType2.equals("1109")||assetType2.equals("1110")||assetType2.equals("1111")||assetType2.equals("1112")
				||assetType2.equals("1113")||assetType2.equals("1114")||assetType2.equals("1115")||assetType2.equals("1116")||assetType2.equals("1117")||assetType2.equals("1501")
				||assetType2.equals("2501")||assetType2.equals("2502")){
			return "债券类资产";
		}
		if(assetType2.equals("1106")||assetType2.equals("1303")||assetType2.equals("2401")||assetType2.equals("2402")||assetType2.equals("2403")||assetType2.equals("2499")
				||assetType2.equals("1505")||assetType2.equals("1507")||assetType2.equals("1508")||assetType2.equals("1509")){
			return "基金类资产";
		}
		if(assetType2.equals("1002")||assetType2.equals("1003")||assetType2.equals("1104")||assetType2.equals("1105")||assetType2.equals("1107")){
			return "存款类资产";
		}
		if(assetType2.equals("1301")||assetType2.equals("1399")){
			return "权益类资产";
		}
		if(assetType2.equals("1108")||assetType2.equals("1902")||assetType2.equals("1506")){
			return "正逆回购";
		}
		if(assetType2.equals("1404")){
			return "场外期权";
		}
		if(assetType2.equals("1403")){
			return "期货";
		}
		if(assetType2.equals("1405")){
			return "债券借贷";
		}
        return assetType2;
	}

	/**
	 * SAMS根据资产二级分类判断资产大类是否为非标准化债权类资产
	 * @param assetType2
	 * @return
	 */
	public boolean getSAMSAssetType(String assetType2) {
		if(StringUtil.isEmpty(assetType2)){
			return false;
		}
		if(assetType2.equals("1201")||assetType2.equals("1202")||assetType2.equals("1203")||assetType2.equals("1204")||assetType2.equals("1205")||assetType2.equals("1206")
				||assetType2.equals("1207")||assetType2.equals("1208")||assetType2.equals("1209")||assetType2.equals("1210")||assetType2.equals("1211")||assetType2.equals("1212")||assetType2.equals("1213")
				||assetType2.equals("1299")||assetType2.equals("2101")||assetType2.equals("2202")){
			return true;
		}
		return false;
	}

	/**
	 * 倒算期限品种
	 * @param Vdate
	 * @param Mdate
	 * @return
	 */
	public String getQxType(String Vdate,String Mdate) {
		if(StringUtil.isEmpty(Vdate)||StringUtil.isEmpty(Mdate)){
			return null;
		}
		String QxType = "";
		long dayNum = DateUtil.getBetweenDayNumber(Vdate,Mdate);
		if(dayNum==1){
			QxType = "R001";
		}else if(1<dayNum && dayNum<=7){
			QxType = "R007";
		}
		else if(7<dayNum && dayNum<=14){
			QxType = "R014";
		}
		else if(14<dayNum && dayNum<=21){
			QxType = "R021";
		}else if(21<dayNum && dayNum<=30){
			QxType = "R1M";
		}else if(30<dayNum && dayNum<=60){
			QxType = "R2M";
		}else if(60<dayNum && dayNum<=90){
			QxType = "R3M";
		}else if(90<dayNum && dayNum<=120){
			QxType = "R4M";
		}else if(120<dayNum && dayNum<=180){
			QxType = "R6M";
		}else if(180<dayNum && dayNum<=270){
			QxType = "R9M";
		}else if(270<dayNum){
			QxType = "R1Y";
		}
		return QxType;
	}
}
