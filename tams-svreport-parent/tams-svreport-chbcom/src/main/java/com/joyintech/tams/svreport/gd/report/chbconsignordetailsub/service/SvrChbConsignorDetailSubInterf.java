package com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dao.SvrChbConsignorDetailSubInterfMapper;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dto.SvrChbConsignorDetailSubInterfDto;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dto.SvrChbPrdPeriodyieldDto;
import com.joyintech.tams.svreport.gd.report.chbconsignordetailsub.dto.SvrMstExchargeQuoteDto;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dao.SvrProductsDealSubInterfMapper;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dto.SvrProductsDealSubInterfDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.netflix.discovery.converters.Auto;
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
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author yangxianjiu
 * @date 2022/04/06
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrChbConsignorDetailSubInterf extends BaseService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SvrChbConsignorDetailSubInterfMapper svrChbConsignorDetailSubInterfMapper;

    @Autowired
    private SqlSessionTemplate template;

    /**
     *
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     */
    public void generateData(String batchDate, String batchDateStart, String batchDateEnd) {
        // 判断跑批日上月月末是否含有持有信息，若无持有信息则进行提示停止跑批
        List<String> chbInvestholdInfoList = svrChbConsignorDetailSubInterfMapper.checkChbInvestholdInfo(batchDateStart, batchDateEnd);
        if (CollectionUtils.isEmpty(chbInvestholdInfoList)){
            logger.info("跑批日上月月末未含有投资者持有信息！");
            return;
        }
        // 判断跑批日上月是否已进行统计，若已统计则提示但继续跑批
        List<SvrChbConsignorDetailSubInterfDto> checkCounted = svrChbConsignorDetailSubInterfMapper.checkIsCounted(batchDateEnd);
        if (CollectionUtils.isNotEmpty(checkCounted)){
            logger.info("跑批日上月已进行统计！");
        }
        //1、开始检数据
        logger.info("委托人明细数据登记表信息检索开始------->>>>>>");
        List<SvrChbConsignorDetailSubInterfDto> dataList = this.getList(batchDate,batchDateStart,batchDateEnd);
        logger.info("委托人明细数据登记表信息检索结束-------<<<<<<");
        if (dataList.size() == 0) {
            logger.info("没有检索到数据,批处理结束!");
            return;
        }
        //更新表
        logger.info("委托人明细数据登记表信息入库开始------->>>>>>");
        dbData(dataList,batchDate,batchDateStart,batchDateEnd);
        logger.info("委托人明细数据登记表信息入库结束-------<<<<<<");

    }

    /**
     * 数据入库 先删后插
     * @param dataList
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     */
    private void dbData(List<SvrChbConsignorDetailSubInterfDto> dataList, String batchDate, String batchDateStart, String batchDateEnd) {
        // 删除
        logger.info("委托人明细数据登记表信息先删开始------->>>>>>");
        svrChbConsignorDetailSubInterfMapper.deleteData(batchDateEnd);
        logger.info("委托人明细数据登记表信息先删结束-------<<<<<<");
        dataList.forEach(entity -> {
            // 生成主键
            entity.setRegistSeqno(UUID.randomUUID().toString().toUpperCase());
        });

        logger.info("============插入开始===============" + DateUtil.getCurrentDateTime());
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(dataList)) {
            int count = 0;
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try {
                SvrChbConsignorDetailSubInterfMapper batchMapper = session.getMapper(SvrChbConsignorDetailSubInterfMapper.class);
                List<SvrChbConsignorDetailSubInterfDto> dataListTemp = new ArrayList<>();
                if (dataList.size() >= 500 ){
                    for (SvrChbConsignorDetailSubInterfDto entity : dataList) {
                        dataListTemp.add(entity);
                        if (dataListTemp.size() == 500) {
                            batchMapper.insert(dataListTemp);
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
                        batchMapper.insert(dataListTemp);
                        dataListTemp.clear();
                    }
                } else {
                    // 数据不足500条
                    dataListTemp.addAll(dataList);
                    batchMapper.insert(dataListTemp);
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
     * 获取数据
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    private List<SvrChbConsignorDetailSubInterfDto> getList(String batchDate, String batchDateStart, String batchDateEnd) {
        Map<String, Object> param = new HashMap<>();
        param.put("batchDateStart",batchDateStart);
        param.put("batchDateEnd",batchDateEnd);
        // 抽取数据
        List<SvrChbConsignorDetailSubInterfDto> dtoList = this.getChbConsignorDetailSubList(param);
        // 获取汇率
        List<SvrMstExchargeQuoteDto> mstExchargeQuoteList = svrChbConsignorDetailSubInterfMapper.getMstExchargeQuote(batchDateEnd);
        // 存入map避免多次访问数据库
        Map<String, BigDecimal> mstExchargeQuoteMap = mstExchargeQuoteList.stream().collect(JoyinCollectors.toMap(key -> key.getExchargeCode(), value -> value.getMidPrice()));
        // 获取月末公布净值
        List<SvrChbPrdPeriodyieldDto> chbPrdPeriodyieldList = svrChbConsignorDetailSubInterfMapper.getChbPrdPeriodyield(batchDateStart,batchDateEnd);
        Map<String, BigDecimal> chbPrdPeriodyieldMap = chbPrdPeriodyieldList.stream().collect(JoyinCollectors.toMap(key -> key.getProdId(), value -> value.getProdValue()));
        // 自然人 按照产品维度进行份额的汇总
        List<SvrChbConsignorDetailSubInterfDto> naturalPerson = new ArrayList<SvrChbConsignorDetailSubInterfDto>();
        // 非自然人  产品和投资者维度进行份额的汇总
        List<SvrChbConsignorDetailSubInterfDto> nonNaturalPerson = new ArrayList<SvrChbConsignorDetailSubInterfDto>();
        // 将权益人类型为自然人的数据存放在另一  List中
        dtoList.forEach(entity -> {
            if ("01".equals(entity.getHolderType())
                    || "02".equals(entity.getHolderType())
                        || "03".equals(entity.getHolderType())){
                naturalPerson.add(entity);
            } else {
                nonNaturalPerson.add(entity);
            }
        });
        // 自然人
        Map<String, SvrChbConsignorDetailSubInterfDto> naturalPersonMap = new HashMap<>();
        for (SvrChbConsignorDetailSubInterfDto person : naturalPerson) {
            // 自然人
            person.setHolderType("1000");
            if (naturalPersonMap.containsKey(person.getProdId())){
                // 以产品维度 汇总份额
                person.setQty(naturalPersonMap.get(person.getProdId()).getQty().add(person.getQty()));
                naturalPersonMap.put(person.getProdId(), person);
            } else {
                naturalPersonMap.put(person.getProdId(), person);
            }
        }

        // 非自然人
        Map<String, SvrChbConsignorDetailSubInterfDto> nonNaturalPersonMap = new HashMap<>();
        for (SvrChbConsignorDetailSubInterfDto person : nonNaturalPerson) {
            // 金融机构法人
            if ("04".equals(person.getHolderType())
                    || "05".equals(person.getHolderType())
                    || "06".equals(person.getHolderType())
                    || "07".equals(person.getHolderType())
                    || "08".equals(person.getHolderType())
                    || "09".equals(person.getHolderType())
                    || "10".equals(person.getHolderType())) {
                person.setHolderType("2110");
            } else if ("11".equals(person.getHolderType())
                    || "12".equals(person.getHolderType())
                    || "13".equals(person.getHolderType())
                    || "14".equals(person.getHolderType())
                    || "15".equals(person.getHolderType())){
                // 非企业法人
                person.setHolderType("2200");
            } else if ("16".equals(person.getHolderType())){
                // 非金融机构法人
                person.setHolderType("2120");
            } else if ("17".equals(person.getHolderType())){
                // 信托计划
                person.setHolderType("3205");
            } else if ("18".equals(person.getHolderType())){
                // 证券公司及其子公司资产管理计划
                person.setHolderType("3202");
            } else if ("19".equals(person.getHolderType())){
                // 基金公司及其子公司特定客户资产管理组合
                person.setHolderType("3203");
            } else if ("20".equals(person.getHolderType())){
                // 期货公司及其子公司资产管理产品
                person.setHolderType("3206");
            } else if ("21".equals(person.getHolderType())){
                // 保险公司资产管理计划及保险产品
                person.setHolderType("3204");
            } else if ("22".equals(person.getHolderType())){
                // 待定
                person.setHolderType("4000");
            } else if ("23".equals(person.getHolderType())){
                // 私募投资基金
                person.setHolderType("3207");
            } else {
                // 无该类型投资者，不处理
                person.setHolderType("4000");
            }

            if (nonNaturalPersonMap.containsKey(person.getProdId() + "_" + person.getHolderType() + "_" + person.getHolderName() )){
                // 以产品 和 投资人类型 维度 汇总份额
                person.setQty(nonNaturalPersonMap.get(person.getProdId() + "_" + person.getHolderType() + "_" + person.getHolderName() ).getQty().add(person.getQty()));
                nonNaturalPersonMap.put(person.getProdId() + "_" + person.getHolderType() + "_" + person.getHolderName() ,person);
            } else {
                nonNaturalPersonMap.put(person.getProdId() + "_" + person.getHolderType() + "_" + person.getHolderName() , person);
            }
        }

        // 2.1.4 合并数据
        List<SvrChbConsignorDetailSubInterfDto> tempList = new ArrayList<>(naturalPersonMap.values());
        tempList.addAll(nonNaturalPersonMap.values());
        // 2.1.5  2.1.4的数据以产品维度汇总份额
        Map<String, SvrChbConsignorDetailSubInterfDto> tempMap = new HashMap<>();
        // 以 产品维度汇总份额
        for (SvrChbConsignorDetailSubInterfDto interfDto : tempList) {
            SvrChbConsignorDetailSubInterfDto interfDtoTemp = new SvrChbConsignorDetailSubInterfDto();//临时储存产品代码和产品份额
            if (tempMap.containsKey(interfDto.getProdId())){
                interfDtoTemp.setCcy(interfDto.getCcy());
                interfDtoTemp.setProdId(interfDto.getProdId());
                if(StringUtil.isNotEmpty(interfDto.getQty()) && StringUtil.isNotEmpty(tempMap.get(interfDto.getProdId()).getQty())) {
                    interfDtoTemp.setQty(tempMap.get(interfDto.getProdId()).getQty().add(interfDto.getQty()));
                }
                tempMap.put(interfDto.getProdId(), interfDtoTemp);
            } else {
                interfDtoTemp.setCcy(interfDto.getCcy());
                interfDtoTemp.setProdId(interfDto.getProdId());
                interfDtoTemp.setQty(interfDto.getQty());
                tempMap.put(interfDto.getProdId(), interfDtoTemp);
            }
        }
        //以产品维度汇总金额
        Map<String,BigDecimal> tempMoneyMap = new HashMap<>();
        for(SvrChbConsignorDetailSubInterfDto dto:tempMap.values()){
            if (!StringUtil.equals(dto.getCcy(),"CNY")) {
                // 份额×月末公布净值×月末时点外汇管理局中间价
                // 产品月末净值
                BigDecimal prodValue = chbPrdPeriodyieldMap.get(dto.getProdId());
                // 中间价
                BigDecimal midPrice = mstExchargeQuoteMap.get(dto.getCcy() + "/CNY");
                // 赋值给投资金额
                if(StringUtil.isNotEmpty(prodValue)&&StringUtil.isNotEmpty(midPrice)&&StringUtil.isNotEmpty(dto.getQty())) {
                    tempMoneyMap.put(dto.getProdId(), dto.getQty().multiply(prodValue).multiply(midPrice));
                }
            } else {
                // 份额×月末公布净值×月末时点外汇管理局中间价
                // 产品月末净值
                BigDecimal prodValue = chbPrdPeriodyieldMap.get(dto.getProdId());
                if(StringUtil.isNotEmpty(prodValue)&&StringUtil.isNotEmpty(dto.getQty())) {
                    tempMoneyMap.put(dto.getProdId(), dto.getQty().multiply(prodValue));
                }
            }
        }


        // 2.1.6  循环2.1.4
        for (SvrChbConsignorDetailSubInterfDto dto : tempList) {
            if (!StringUtil.equals(dto.getCcy(),"CNY")) {
                // 份额×月末公布净值×月末时点外汇管理局中间价
                // 产品月末净值
                BigDecimal prodValue = chbPrdPeriodyieldMap.get(dto.getProdId());
                // 中间价
                BigDecimal midPrice = mstExchargeQuoteMap.get(dto.getCcy() + "/CNY");
                // 赋值给投资金额
                if(StringUtil.isNotEmpty(prodValue)&&StringUtil.isNotEmpty(midPrice)&&StringUtil.isNotEmpty(dto.getQty())) {
                    dto.setInvestAmount(dto.getQty().multiply(prodValue).multiply(midPrice));
                }
            } else {
                // 份额×月末公布净值×月末时点外汇管理局中间价
                // 产品月末净值
                BigDecimal prodValue = chbPrdPeriodyieldMap.get(dto.getProdId());
                if(StringUtil.isNotEmpty(prodValue)&&StringUtil.isNotEmpty(dto.getQty())) {
                    dto.setInvestAmount(dto.getQty().multiply(prodValue));
                }
            }
            // 投资比例 投资金额/2.1.5中的计算结果
            BigDecimal investRatio =new BigDecimal(BigInteger.ZERO);
            if(StringUtil.isNotEmpty(dto.getInvestAmount())&&StringUtil.isNotEmpty(tempMoneyMap.get(dto.getProdId()))) {
                investRatio = dto.getInvestAmount().divide(tempMoneyMap.get(dto.getProdId()), 2, RoundingMode.HALF_UP);
            }
            dto.setInvestRatio(investRatio);

            // 赋值投资人份额（份额）
            dto.setHolderShare(dto.getQty());
            // 赋值产品规模（2.1.5中的计算结果）
            dto.setProdScale(tempMoneyMap.get(dto.getProdId()));
            // 根据产品运作模式判断是开放式产品还是封闭式产品
            if ("03".equals(dto.getOperateMode())
                    || "04".equals(dto.getOperateMode())) {
                // 开放式
                dto.setTermid("无固定期限");
            } else if ("01".equals(dto.getOperateMode())
                    || "02".equals(dto.getOperateMode())){
                // 封闭式
                BigDecimal termId = new BigDecimal(BigInteger.ZERO);
                if(StringUtil.isNotEmpty(dto.getMdate().getTime())&&StringUtil.isNotEmpty(dto.getVdate().getTime())) {
                    termId = new BigDecimal((dto.getMdate().getTime() - dto.getVdate().getTime()) / (24 * 60 * 60 * 1000));
                }
                dto.setTermid(termId.divide(new BigDecimal(365),1,RoundingMode.HALF_UP).toString());
            }
            // 产品份额
            dto.setProdShare(tempMap.get(dto.getProdId()).getQty());

            if ("1000".equals(dto.getHolderType())){
                dto.setHolderName("自然人");
            }
        }


        return tempList;

    }


    private List<SvrChbConsignorDetailSubInterfDto> getChbConsignorDetailSubList(Map<String, Object> param) {
        String batchDateStart = param.get("batchDateStart").toString();
        String batchDateEnd = param.get("batchDateEnd").toString();
        return svrChbConsignorDetailSubInterfMapper.getChbConsignorDetailSubData(batchDateStart,batchDateEnd);
    }
}
