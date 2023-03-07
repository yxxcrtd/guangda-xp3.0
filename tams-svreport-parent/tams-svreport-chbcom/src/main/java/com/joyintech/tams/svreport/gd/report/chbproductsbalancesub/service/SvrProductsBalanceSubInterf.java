package com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dao.SvrChbConsignorDetailSubInterfMapper;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dto.SvrChbConsignorDetailSubInterfDto;
import com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.dao.SvrProductsBalanceSubInterfMapper;
import com.joyintech.tams.svreport.gd.report.chbproductsbalancesub.dto.SvrProductsBalanceSubInterfDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author yangxianjiu
 * @date 2022/03/31
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrProductsBalanceSubInterf extends BaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SvrProductsBalanceSubInterfMapper svrProductsBalanceSubInterfMapper;

    @Autowired
    private SqlSessionTemplate template;

    /**
     *
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     */
    public void generateData(String batchDate, String batchDateStart, String batchDateEnd) {
        // 判断跑批日上月月末是否含有投资者明细信息和持有信息，若无明细信息或持有信息则进行提示停止跑批
        // 检查明细
        List<String> chbEqudetList = svrProductsBalanceSubInterfMapper.checkChbEqudet(batchDateStart,batchDateEnd);
        // 检查持有
        List<String> chbInvestholdInfoList = svrProductsBalanceSubInterfMapper.checkChbInvestholdInfo(batchDateStart,batchDateEnd);
        if (CollectionUtils.isEmpty(chbEqudetList) || CollectionUtils.isEmpty(chbInvestholdInfoList)) {
            logger.info("跑批日上月月末未含有投资者明细信息和投资者持有信息！");
            return;
        }

        //1、开始检数据
        logger.info("理财产品账户余额表信息检索开始------->>>>>>");
        List<SvrProductsBalanceSubInterfDto> dataList = this.getList(batchDate,batchDateStart,batchDateEnd);
        logger.info("理财产品账户余额表信息检索结束-------<<<<<<");
        if (dataList.size() == 0) {
            logger.info("没有检索到数据,批处理结束!");
            return;
        }
        //更新表
        logger.info("理财产品账户余额表信息入库开始------->>>>>>");
        dbData(dataList,batchDate,batchDateStart,batchDateEnd);
        logger.info("理财产品账户余额表信息入库结束-------<<<<<<");

    }

    /**
     * 入库 先删后插
     * @param dataList
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     */
    private void dbData(List<SvrProductsBalanceSubInterfDto> dataList, String batchDate, String batchDateStart, String batchDateEnd) {
        // 删除
        logger.info("理财产品账户余额表信息先删开始------->>>>>>");
        svrProductsBalanceSubInterfMapper.deleteProductsBalanceSub(batchDateEnd);
        logger.info("理财产品账户余额表信息先删结束-------<<<<<<");
        dataList.forEach(entity->{
            // 生成业务主键
            entity.setRegistSeqno(UUID.randomUUID().toString().toUpperCase());
            // 数据批次
            entity.setStatusBatch("1");
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
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(dataList)) {
            int count = 0;
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try {
                SvrProductsBalanceSubInterfMapper batchMapper = session.getMapper(SvrProductsBalanceSubInterfMapper.class);
                List<SvrProductsBalanceSubInterfDto> dataListTemp = new ArrayList<>();
                if (dataList.size() >= 500 ){
                    for (SvrProductsBalanceSubInterfDto entity : dataList) {
                        dataListTemp.add(entity);
                        if (dataListTemp.size() == 500) {
                            batchMapper.insertProductsBalanceSub(dataListTemp);
                            dataListTemp.clear();
                        }
                        count++;
                        if (count % 10000 == 0) {
                            session.commit();
                            // 清理缓存，防止溢出
                            session.clearCache();
                        }
                    }
                    // 剩下的数据
                    if (dataListTemp.size() > 0){
                        // 提交前面的插入
                        session.commit();
                        batchMapper.insertProductsBalanceSub(dataListTemp);
                        dataListTemp.clear();
                    }
                } else {
                    // 数据不足500条
                    dataListTemp.addAll(dataList);
                    batchMapper.insertProductsBalanceSub(dataListTemp);
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
     * 获取产品代码
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    private List<SvrProductsBalanceSubInterfDto> getList(String batchDate, String batchDateStart, String batchDateEnd){
        List<String> prodIdList = svrProductsBalanceSubInterfMapper.getProdId(batchDateEnd);
        Map<String, Object> param = new HashMap<>();
        // 产品代码
        param.put("prodIdList",prodIdList);
        param.put("batchDateStart",batchDateStart);
        param.put("batchDateEnd",batchDateEnd);
        // 抽取数据
        List<SvrProductsBalanceSubInterfDto> dtoList = this.getProductsBalanceSubList(param);
        return dtoList;
    }



    /**
     * 分批查询
     * @param param
     * @return
     */
    private List<SvrProductsBalanceSubInterfDto> getProductsBalanceSubList(Map<String, Object> param) {
        List<SvrProductsBalanceSubInterfDto> svrProductsBalanceSubInterfDtoList = null;
        List<SvrProductsBalanceSubInterfDto> svrProductsBalanceSubInterfDtoListTemp = null;
        List<String> prodIdList = (List<String>)param.get("prodIdList");
        int dataCount = prodIdList.size();
        int maxDataCountOneTime = 1000;
        String batchDateStart = param.get("batchDateStart").toString();
        String batchDateEnd = param.get("batchDateEnd").toString();
        // 是否需要分批
        if (dataCount <= maxDataCountOneTime) {
            svrProductsBalanceSubInterfDtoList = svrProductsBalanceSubInterfMapper.getProductsBalanceSubInterfData(prodIdList,batchDateStart,batchDateEnd);
        } else {
            // 分批处理
            int part = dataCount / maxDataCountOneTime;
            // 分批数
            for (int i = 0; i < part; i++) {
                List<String> prodIdListTemp = prodIdList.subList(0, maxDataCountOneTime);
                svrProductsBalanceSubInterfDtoListTemp = svrProductsBalanceSubInterfMapper.getProductsBalanceSubInterfData(prodIdListTemp,batchDateStart,batchDateEnd);
                prodIdList.subList(0, 1000).clear();
                // 追加临时列表的数据
                svrProductsBalanceSubInterfDtoList.addAll(svrProductsBalanceSubInterfDtoListTemp);
            }
            // 剩下的数据
            if (CollectionUtils.isNotEmpty(prodIdList)) {
                svrProductsBalanceSubInterfDtoListTemp = svrProductsBalanceSubInterfMapper.getProductsBalanceSubInterfData(prodIdList,batchDateStart,batchDateEnd);
                // 追加临时列表的数据
                svrProductsBalanceSubInterfDtoList.addAll(svrProductsBalanceSubInterfDtoListTemp);
            }
        }
        return svrProductsBalanceSubInterfDtoList;
    }

}
