package com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.dao.ChbProductsAssetCompareInterfMapper;
import com.joyintech.tams.svreport.gd.report.chbproductsassetcompare.dto.ChbProductsAssetCompare;
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

@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbProductsAssetCompareInterf extends BaseService{

	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChbProductsAssetCompareInterfMapper chbProductsAssetCompareInterfMapper;

	@Autowired
	private SqlSessionTemplate template;

	public void generateData(String batchDate,String batchDateStart ,String batchDateEnd) {
		//1、开始检数据
		logger.info("理财产品资产对照月报每月明细汇总信息检索开始------->>>>>>");
		List<ChbProductsAssetCompare> ProductsList = this.getList(batchDate,batchDateStart,batchDateEnd);
		logger.info("理财产品资产对照月报每月明细汇总信息检索结束-------<<<<<<");

		if(ProductsList.size() == 0){
			logger.info("没有检索到数据,批处理结束!");
			return;
		}
		//更新表
		logger.info("理财产品资产对照月报每月明细汇总信息入库开始------->>>>>>");
		dbData(ProductsList,batchDate,batchDateStart ,batchDateEnd);
		logger.info("理财产品资产对照月报每月明细汇总信息入库结束-------<<<<<<");

}
	//查询表
	public List<ChbProductsAssetCompare> getList(String batchDate,String batchDateStart ,String batchDateEnd) {
		List<ChbProductsAssetCompare> ProductsList = chbProductsAssetCompareInterfMapper.selectProducts(batchDateEnd);
		return ProductsList;
	}

	/**
	 * 先删后插
	 */
	private void dbData(List<ChbProductsAssetCompare> ProductsList,String batchDate, String batchDateStart , String batchDateEnd) {

		logger.info("当月汇总数据清除开始----------");
			//根据期数删除月报主表
			chbProductsAssetCompareInterfMapper.deleteProducts(batchDateEnd);
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
				ChbProductsAssetCompareInterfMapper batchMapper = session.getMapper(ChbProductsAssetCompareInterfMapper.class);
				List<ChbProductsAssetCompare> List = new ArrayList<>();
				for (ChbProductsAssetCompare asset : ProductsList) {
					String registSeqno = UUID.randomUUID().toString().toUpperCase();
					asset.setRegistSeqno(registSeqno);
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
}
