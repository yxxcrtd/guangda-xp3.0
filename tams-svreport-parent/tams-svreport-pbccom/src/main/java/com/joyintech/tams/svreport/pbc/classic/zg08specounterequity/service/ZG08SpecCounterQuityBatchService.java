package com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao.ZG01PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dao.ZG08SpeCounterQuityMapper;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityBatchDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpecCounterQuityRule;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZG08SpecCounterQuityBatchService {

    @Autowired
    private ZG08SpeCounterQuityMapper zg08SpeCounterQuityMapper;

    @Autowired
    private ZG01PbcPrdBaseinfoSubMapper zg01PbcPrdBaseinfoSubMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private PbcDataCheckUtil pbcDataCheckUtil;

    public void processData(Date batchTime){
        //1.判断跑批日期，如果不是1号，批处理不执行。
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(batchTime);
        if ( calendar.get(Calendar.DATE)!=1 ) {  //每月1日生成上月的数据
            logger.info("每月1日生成上月的数据！");
            return;
        }
        Date addDay = DateUtil.addDay(batchTime, -1);
        String termId = DateUtil.format(addDay, DateUtil.MONTH_OTHER_FORMAT);
        String executeDate = DateUtil.format(addDay, DateUtil.DEFAULT_DATE_FORMAT_EN);
        if (zg08SpeCounterQuityMapper.selectCountByTermId(termId)>0){
            logger.info("当月存在已报送数据，批处理不再继续执行！");
            return;
        }
        //删除当期所有数据
        zg08SpeCounterQuityMapper.deleteByTermId(termId);

        //查询人行产品资产池的数据
        List<ZG01PbcPrdBaseinfoSub> list=zg01PbcPrdBaseinfoSubMapper.selectAllPbcProdId();
        Map<String,String> map= list.stream().collect(Collectors.toMap(ZG01PbcPrdBaseinfoSub::getProdId,ZG01PbcPrdBaseinfoSub::getPbcProdId ));

        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG08特定目的载体交易对手明细信息.getValue(),
                DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
                addDay, SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
        String exchangeSource = CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
        //处理月报资产头寸数据
        List<ZG08SpeCounterQuityBatchDto> arrayList = zg08SpeCounterQuityMapper.selectAssetPositionInfoList(executeDate);
        //特定目的载体持有信息数据
        List<ZG08SpeCounterQuityBatchDto> arrayList1 = zg08SpeCounterQuityMapper.selectHoldInfoList(executeDate);
        arrayList.addAll(arrayList1);
        if(!StringUtil.isEmptyList(arrayList) && arrayList.size()>0){
            arrayList.forEach(
                    item ->{
                        item.setAmt(StringUtil.isNotEmpty(item.getAmt())?item.getAmt().setScale(2, RoundingMode.HALF_UP):null);
                        item.setAmtCny(StringUtil.isNotEmpty(item.getAmtCny())?item.getAmtCny().setScale(2, RoundingMode.HALF_UP):null);
                    }
            );
        }
        //匹配规则
        matchRule(arrayList,dateList,exchangeSource,addDay,termId,map);
        logger.info("采集平台特定目的载体交易对手明细信息：报表生成校验 开始----------");
        List<ZG08SpeCounterQuityBatchDto> listData = pbcDataCheckUtil.getTrueData(arrayList, null, DictCodeListExt2.采集平台特定目的载体交易对手明细信息.getValue(), true, "1", ZG08SpeCounterQuityBatchDto.class);
        logger.info("采集平台特定目的载体交易对手明细信息：报表生成校验 结束----------");
        //数据批量入库
        intoDatabase(listData);
    }

    public void matchRule(List<ZG08SpeCounterQuityBatchDto> dataList,List<Date> dateList,String exchangeSource,Date batchTime,String termId,Map<String,String> map){
        //for (ZG08SpeCounterQuityBatchDto batchDto : dataList) {
        Iterator<ZG08SpeCounterQuityBatchDto> iterator = dataList.iterator();
        while (iterator.hasNext()){
            ZG08SpeCounterQuityBatchDto batchDto = iterator.next();
            //数据检查用字段，不入库
            if(map.containsKey(batchDto.getProdId())){
                batchDto.setPbcPordIdOut(map.get(batchDto.getProdId()));
            }else {
                if(batchDto.getPbcProdId().contains("INS")){
                    batchDto.setPbcPordIdOut(batchDto.getPbcProdId().substring(3));
                }else {
                    batchDto.setPbcPordIdOut(batchDto.getPbcProdId());
                }
            }
            //初始化共同属性
            initCommonValue(dateList,batchDto,exchangeSource,batchTime,termId);
            String pbcAssetType=batchDto.getPbcAssetType1()+ GlobalConstant.COMMA
                    +batchDto.getPbcAssetType2()+GlobalConstant.COMMA+batchDto.getPbcAssetType3();
            //循环做数据匹配
            for (ZG08SpecCounterQuityRule rule : ZG08SpecCounterQuityRule.values()) {
                if (rule.getPbcAssetType().equals(pbcAssetType)){//匹配资产端数据
                    if (CollectionUtils.isNotEmpty(rule.getChbCodeEqualsList())
                            &&rule.getChbCodeEqualsList().contains(batchDto.getChbAssetType2())){
                        batchDto.setAssetType(rule.getAssetType());
                        batchDto.setPrrtyProdType(rule.getPrrtyProdType());
                    }else if (CollectionUtils.isNotEmpty(rule.getChbCodeNotEqualsList())
                            &&!rule.getChbCodeNotEqualsList().contains(batchDto.getChbAssetType2())){
                        batchDto.setAssetType(rule.getAssetType());
                        batchDto.setPrrtyProdType(rule.getPrrtyProdType());
                    }else if (CollectionUtils.isEmpty(rule.getChbCodeEqualsList())&&CollectionUtils.isEmpty(rule.getChbCodeNotEqualsList())){
                        batchDto.setAssetType(rule.getAssetType());
                        batchDto.setPrrtyProdType(rule.getPrrtyProdType());
                    }
                }else if (rule.getChbHolderType().equals(batchDto.getChbHolderType())){//匹配产品端数据
                    batchDto.setAssetType(rule.getAssetType());
                    batchDto.setPrrtyProdType(rule.getPrrtyProdType());
                }
            }
            if (StringUtil.isEmpty(batchDto.getPbcProdId())||StringUtil.isEmpty(batchDto.getPrrtyOrgCode())
                    ||StringUtil.isEmpty(batchDto.getPrrtyProdId())){
                iterator.remove();
            }
        }
    }

    public void initCommonValue(List<Date> dateList,ZG08SpeCounterQuityBatchDto dto,String exchangeSource,Date batchTime,String termId){
        // 报送状态
        dto.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
        // 主键
        dto.setDetailuuid(IDGenerator.generate(36));
        // 数据日期
        dto.setStatusDate(DateUtil.addDay(batchTime, 1));
        // 创建人
        dto.setCreateUser(CommonUtil.getUserName());
        // 更新人
        dto.setUpdateUser(CommonUtil.getUserName());
        // 创建部门
        dto.setCreateDept(CommonUtil.getDeptCode());
        // 机构代码
        dto.setOrgCode(CommonUtil.getOrgCode());
        //数据状态
        dto.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
        //人行报送截止日
        dto.setPbcSubmitDeadline(dateList.get(0));
        //行内报送截止日
        dto.setSubmitDeadline(dateList.get(1));
        Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();
        BigDecimal decimal = ChbCcyConvertUtil.exchangeMoney(dto.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(), new Date()
                , dto.getAmt(), rateMap, exchangeSource).setScale(2, RoundingMode.HALF_UP);
        dto.setAmtCny(decimal);
        dto.setTermid(termId);
    }

    private void intoDatabase(List<ZG08SpeCounterQuityBatchDto> insertlist) {
        logger.debug("ZG08 特定目的载体交易对手明细信息插入数据：插入开始----------");
        int count = 0;
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            ZG08SpeCounterQuityMapper batchMapper = session.getMapper(ZG08SpeCounterQuityMapper.class);
            for (ZG08SpeCounterQuityBatchDto dto : insertlist) {
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
            logger.error("ZG08 特定目的载体交易对手明细信息入库报错:" + e.getMessage(), e);
        } finally {
            session.close();
        }
        logger.debug("ZG08 特定目的载体交易对手明细信息插入数据：插入结束----------");
    }
}
