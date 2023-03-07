package com.joyintech.tams.svreport.pbc.specificcounterparty.service;


import com.joyintech.background.component.common.pack.common.util.IDGenerator;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.pbc.specificcounterparty.dao.SpcCounterPartyInterfMapper;
import com.joyintech.tams.svreport.pbc.specificcounterparty.dto.SpcCounterPartyInterfDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author yangxianjiu
 * @date 2022/11/7
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SpcCounterPartyInterfService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlSessionTemplate template;

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private SpcCounterPartyInterfMapper mapper;

    /**
     * 生成 特定目的载体对手方信息
     * @param executeDate
     */
    public void generateData(Date executeDate) {
        String batchDate = df.format(executeDate);
        List<SpcCounterPartyInterfDto> dataAllList = new ArrayList<>();
        // 1.1：从资产端取数
        logger.info("特定目的载体对手方信息维护 从资产端取数 ==========> 开始");
        List<SpcCounterPartyInterfDto> assetDataList = mapper.selectAssetData(batchDate);
        logger.info("特定目的载体对手方信息维护 从资产端取数 <========== 结束");
        // 2.1：产品端从特定目的载体持有信息维护取数
        logger.info("特定目的载体对手方信息维护 从产品端取数 ==========> 开始");
        List<SpcCounterPartyInterfDto> prodDataList = mapper.selectProdData(batchDate);
        logger.info("特定目的载体对手方信息维护 从产品端取数 <========== 结束");
        dataAllList.addAll(assetDataList);
        dataAllList.addAll(prodDataList);
        //
        Map<String, SpcCounterPartyInterfDto> collect = dataAllList.stream().collect(Collectors.toMap(SpcCounterPartyInterfDto::getAssetCode, value -> value));
        // 所有资产代码
        List<String> allAssetCodeList = dataAllList.stream().map(SpcCounterPartyInterfDto::getAssetCode).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(allAssetCodeList)){
            logger.info("特定目的载体对手方信息维护 检索到 0 条数据");
            return;
        }
        // 处理入库数据
        List<SpcCounterPartyInterfDto> existList = getCounterPartyList(allAssetCodeList);
        // 剔除已存在数据
        for (SpcCounterPartyInterfDto dto : existList) {
            if (collect.containsKey(dto.getAssetCode())){
                collect.remove(dto.getAssetCode());
            }
        }
        List<SpcCounterPartyInterfDto> dataList = new ArrayList<>();
        // 存放数据 及映射数据
        for (Map.Entry<String, SpcCounterPartyInterfDto> entry : collect.entrySet()) {
            SpcCounterPartyInterfDto value = entry.getValue();
            // 资产端
            if (StringUtil.isNotEmpty(value.getPbcAssetType2())){
                if (DictCodeListExt3.人行资产二级分类_拆借和回购.getValue().equals(value.getPbcAssetType2())){
                    if (DictCodeListExt3.人行资产三级分类_银行非保本理财.getValue().equals(value.getPbcAssetType3())){
                        // 资产负债项目
                        if (DictCodeListExt2.资产_负债二级类别_卖出回购.getValue().equals(value.getAssetType2())){
                            value.setAssetType("B1200");
                        } else {
                            value.setAssetType("A5200");
                        }
                        // 交易对手产品种类
                        value.setPartyProdType("1");
                    } else  if (DictCodeListExt3.人行资产三级分类_信托公司资管产品.getValue().equals(value.getPbcAssetType3())){
                        // 资产负债项目
                        if (DictCodeListExt2.资产_负债二级类别_卖出回购.getValue().equals(value.getAssetType2())){
                            value.setAssetType("B1200");
                        } else {
                            value.setAssetType("A5200");
                        }
                        // 交易对手产品种类
                        value.setPartyProdType("2");
                    } else  if (DictCodeListExt3.人行资产三级分类_证券公司及其子公司资管产品.getValue().equals(value.getPbcAssetType3())){
                        // 资产负债项目
                        if (DictCodeListExt2.资产_负债二级类别_卖出回购.getValue().equals(value.getAssetType2())){
                            value.setAssetType("B1200");
                        } else {
                            value.setAssetType("A5200");
                        }
                        // 交易对手产品种类
                        value.setPartyProdType("3");
                    } else  if (DictCodeListExt3.人行资产三级分类_基金管理公司及其子公司专户.getValue().equals(value.getPbcAssetType3())){
                        // 资产负债项目
                        if (DictCodeListExt2.资产_负债二级类别_卖出回购.getValue().equals(value.getAssetType2())){
                            value.setAssetType("B1200");
                        } else {
                            value.setAssetType("A5200");
                        }
                        // 交易对手产品种类
                        value.setPartyProdType("4");
                    } else  if (DictCodeListExt3.人行资产三级分类_期货公司及其子公司资管产品.getValue().equals(value.getPbcAssetType3())){
                        // 资产负债项目
                        if (DictCodeListExt2.资产_负债二级类别_卖出回购.getValue().equals(value.getAssetType2())){
                            value.setAssetType("B1200");
                        } else {
                            value.setAssetType("A5200");
                        }
                        // 交易对手产品种类
                        value.setPartyProdType("5");
                    } else  if (DictCodeListExt3.人行资产三级分类_保险资管产品.getValue().equals(value.getPbcAssetType3())){
                        // 资产负债项目
                        if (DictCodeListExt2.资产_负债二级类别_卖出回购.getValue().equals(value.getAssetType2())){
                            value.setAssetType("B1200");
                        } else {
                            value.setAssetType("A5200");
                        }
                        // 交易对手产品种类
                        value.setPartyProdType("6");
                    } else  if (DictCodeListExt3.人行资产三级分类_金融资产投资公司资管产品.getValue().equals(value.getPbcAssetType3())){
                        // 资产负债项目
                        if (DictCodeListExt2.资产_负债二级类别_卖出回购.getValue().equals(value.getAssetType2())){
                            value.setAssetType("B1200");
                        } else {
                            value.setAssetType("A5200");
                        }
                        // 交易对手产品种类
                        value.setPartyProdType("7");
                    } else  if (DictCodeListExt3.人行资产三级分类_公募基金.getValue().equals(value.getPbcAssetType3())){
                        // 资产负债项目
                        if (DictCodeListExt2.资产_负债二级类别_卖出回购.getValue().equals(value.getAssetType2())){
                            value.setAssetType("B1200");
                        } else {
                            value.setAssetType("A5200");
                        }
                        // 交易对手产品种类
                        value.setPartyProdType("8");
                    }
                } else if (DictCodeListExt3.人行资产二级分类_特定目的载体份额.getValue().equals(value.getPbcAssetType2())){
                    if (DictCodeListExt3.人行资产三级分类_银行非保本理财_M01.getValue().equals(value.getPbcAssetType3())){
                        value.setPartyProdType("1");
                    } else if (DictCodeListExt3.人行资产三级分类_信托公司资管产品_M02.getValue().equals(value.getPbcAssetType3())){
                        value.setPartyProdType("2");
                    } else if (DictCodeListExt3.人行资产三级分类_证券公司及其子公司资管产品_M03.getValue().equals(value.getPbcAssetType3())){
                        value.setPartyProdType("3");
                    } else if (DictCodeListExt3.人行资产三级分类_基金管理公司及其子公司专户_M04.getValue().equals(value.getPbcAssetType3())){
                        value.setPartyProdType("4");
                    } else if (DictCodeListExt3.人行资产三级分类_期货公司及其子公司资管产品_M05.getValue().equals(value.getPbcAssetType3())){
                        value.setPartyProdType("5");
                    } else if (DictCodeListExt3.人行资产三级分类_保险资管产品_M06.getValue().equals(value.getPbcAssetType3())){
                        value.setPartyProdType("6");
                    } else if (DictCodeListExt3.人行资产三级分类_金融资产投资公司资管产品_M07.getValue().equals(value.getPbcAssetType3())){
                        value.setPartyProdType("7");
                    } else if (DictCodeListExt3.人行资产三级分类__公募基金_M08.getValue().equals(value.getPbcAssetType3())){
                        value.setPartyProdType("8");
                    }
                    value.setAssetType("A7200");
                }
            } else {
                // 产品端
                if (DictCodeListExt1.投资者类别_信托产品.getValue().equals(value.getPartyProdType())){
                    value.setAssetType("C1200");
                    value.setPartyProdType("2");
                } else if (DictCodeListExt1.投资者类别_券商资产管理计划.getValue().equals(value.getPartyProdType())) {
                    value.setAssetType("C1200");
                    value.setPartyProdType("3");
                } else if (DictCodeListExt1.投资者类别_基金资产管理计划.getValue().equals(value.getPartyProdType())) {
                    value.setAssetType("C1200");
                    value.setPartyProdType("4");
                } else if (DictCodeListExt1.投资者类别_期货资产管理计划.getValue().equals(value.getPartyProdType())) {
                    value.setAssetType("C1200");
                    value.setPartyProdType("5");
                } else if (DictCodeListExt1.投资者类别_保险资产管理计划.getValue().equals(value.getPartyProdType())) {
                    value.setAssetType("C1200");
                    value.setPartyProdType("6");
                } else if (DictCodeListExt1.投资者类别_社会保障类基金.getValue().equals(value.getPartyProdType())) {
                    value.setAssetType("C1200");
                    value.setPartyProdType("8");
                }
            }
            // 入库待处理
            value.setStatus(DictCodeListExt3.中债数据状态_待处理.getValue());
            value.setPbcUuid(IDGenerator.generate(36).toUpperCase());
            dataList.add(value);
        }
        logger.info("特定目的载体对手方信息维护 入库 ==========> 开始");
        dbData(dataList);
        logger.info("特定目的载体对手方信息维护 入库 <========== 结束");
    }

    /**
     * 数据入库
     * @param dataList
     */
    private void dbData(List<SpcCounterPartyInterfDto> dataList) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            SqlSession session = template.getSqlSessionFactory().openSession();
            try {
                int count = 0;
                SpcCounterPartyInterfMapper batchMapper = session.getMapper(SpcCounterPartyInterfMapper.class);
                List<SpcCounterPartyInterfDto> dataListTemp = new ArrayList<>();
                //
                for (SpcCounterPartyInterfDto entity : dataList) {
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
                if (CollectionUtils.isNotEmpty(dataListTemp)){
                    batchMapper.insert(dataListTemp);
                    dataListTemp.clear();
                }
                session.commit();
                // 清理缓存，防止溢出
                session.clearCache();
            } catch (Exception e) {
                session.rollback();
                logger.info(e.getMessage(), e);
            } finally {
                session.close();
            }
        }

    }

    /**
     * 根据资产代码 获取已存在的对手方信息
     * @param assetCodeList
     * @return
     */
    public List<SpcCounterPartyInterfDto> getCounterPartyList(List<String> assetCodeList){
        List<SpcCounterPartyInterfDto> resultList = new ArrayList<>();
        int max = 1000;
        // 是否需要分批
        if (assetCodeList.size() < max){
            resultList = mapper.getCounterPartyList(assetCodeList);
        } else {
            int part = assetCodeList.size() / max;
            List<String> newDataList = new ArrayList<>();
            newDataList.addAll(assetCodeList);
            // 批次查询
            for (int i = 0; i < part; i++) {
                resultList.addAll(mapper.getCounterPartyList(newDataList.subList(0, max)));
                newDataList.subList(0, max).clear();
            }
            if (CollectionUtils.isNotEmpty(newDataList)) {
                // 最后剩下的数据
                resultList.addAll(mapper.getCounterPartyList(newDataList));
            }
        }
        return resultList;
    }
}
