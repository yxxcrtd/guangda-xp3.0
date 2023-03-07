package com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.serivce;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbLoanDetailInfo;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao.ZG01PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto.ZG06PbcColAssetUsufructSubBatchDto;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dao.ZG06PbcColAssetUsufructBatchMapper;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
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
public class ZG06PbcColAssetUsufructSubBatchService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZG06PbcColAssetUsufructBatchMapper zg06PbcColAssetUsufructBatchMapper;

    @Autowired
    private ZG01PbcPrdBaseinfoSubMapper zg01PbcPrdBaseinfoSubMapper;

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
        if (zg06PbcColAssetUsufructBatchMapper.selectCountByTermId(termId)>0){
            logger.info("当月存在已报送数据，批处理不再继续执行！");
            return;
        }
        //查询人行产品资产池的数据
        List<ZG01PbcPrdBaseinfoSub> list=zg01PbcPrdBaseinfoSubMapper.selectAllPbcProdId();
        Map<String,String> map= list.stream().collect(Collectors.toMap(ZG01PbcPrdBaseinfoSub::getProdId,ZG01PbcPrdBaseinfoSub::getPbcProdId ));


        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG06资产收益权明细信息.getValue(),
                DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
                addDay, SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
        String exchangeSource = CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
        zg06PbcColAssetUsufructBatchMapper.deleteByTermId(termId);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("assetType08","08");
        List<OperationalLoanInfo001Dto> infoList = zg06PbcColAssetUsufructBatchMapper.selectAssetInfo(paramMap);
        List<ZG06PbcColAssetUsufructSubBatchDto> positionInfoList = zg06PbcColAssetUsufructBatchMapper.selectAssetPositionInfoList(DateUtil.format(addDay, DateUtil.DEFAULT_DATE_FORMAT_EN));
        if(!StringUtil.isEmptyList(positionInfoList) && positionInfoList.size()>0){
            positionInfoList.forEach(
                    item ->{
                        item.setCurrencyAmt(item.getCurrencyAmt().setScale(2, RoundingMode.HALF_UP));
                    }
            );
        }
        if (CollectionUtils.isEmpty(infoList)||CollectionUtils.isEmpty(positionInfoList)){
            logger.info("无需要生成的数据、批处理退出。");
        }
        Map<String, OperationalLoanInfo001Dto> infoMap = infoList.stream().collect(JoyinCollectors.toMap(ChbLoanDetailInfo::getAssetCode, x -> x));
        List<ZG06PbcColAssetUsufructSubBatchDto> insertList = Lists.newArrayList();
        Iterator<ZG06PbcColAssetUsufructSubBatchDto> iterator = positionInfoList.iterator();
        while (iterator.hasNext()){
            ZG06PbcColAssetUsufructSubBatchDto subBatchDto = iterator.next();
            //数据检查用字段，不入库
            if(map.containsKey(subBatchDto.getProdId())){
                subBatchDto.setPbcPordIdOut(map.get(subBatchDto.getProdId()));
            }else {
                if(subBatchDto.getPbcProdId().contains("INS")){
                    subBatchDto.setPbcPordIdOut(subBatchDto.getPbcProdId().substring(3));
                }else {
                    subBatchDto.setPbcPordIdOut(subBatchDto.getPbcProdId());
                }
            }
            if (infoMap.containsKey(subBatchDto.getAssetCode())){
                OperationalLoanInfo001Dto infoDto = infoMap.get(subBatchDto.getAssetCode());
                // 基础资产投向部门 多条
                if(infoDto.getAssetsInvDepa().contains(GlobalConstant.COMMA)){
                    List<String> invDepa = StringUtil.splitToList(GlobalConstant.COMMA, infoDto.getAssetsInvDepa());
                    List<String> eachProportioin = StringUtil.splitToList(GlobalConstant.COLON, infoDto.getEachProportion());
                    // 基础资产原始协议金额
                    BigDecimal assetsOrigamt = infoDto.getAssetsOrigamt()==null?BigDecimal.ZERO:infoDto.getAssetsOrigamt();
                    BigDecimal assetsOrigamtCny = infoDto.getAssetsOrigamtCny()==null?BigDecimal.ZERO:infoDto.getAssetsOrigamtCny();
                    // 基础资产转让金额
                    BigDecimal currencyTransferamt = infoDto.getCurrencyTransferamt()==null?BigDecimal.ZERO:infoDto.getCurrencyTransferamt();
                    BigDecimal currencyTransferamtcny = infoDto.getCurrencyTransferamtcny()==null?BigDecimal.ZERO:infoDto.getCurrencyTransferamtcny();
                    // 基础资产期末余额
                    BigDecimal currencyAmt = subBatchDto.getCurrencyAmt();

                    List<ZG06PbcColAssetUsufructSubBatchDto> invList = Lists.newArrayList();

                    for (int i = 0; i < invDepa.size(); i++) {
                        ZG06PbcColAssetUsufructSubBatchDto sub = new ZG06PbcColAssetUsufructSubBatchDto();
                        BeanUtil.copyPropertiesIgnoreNull(sub,subBatchDto);
                        sub.setAssetsInvDepa(invDepa.get(i));
                        // 按对应比例折算
                        // 基础资产原始协议金额
                        if (assetsOrigamt == null ) {
                            sub.setAssetsOrigAmt(new BigDecimal("0"));
                            sub.setAssetsOrigAmtCny(new BigDecimal("0"));
                        } else {
                            sub.setAssetsOrigAmt((new BigDecimal(eachProportioin.get(i))
                                    .divide(BigDecimal.valueOf(100)).multiply(assetsOrigamt)).setScale(2,BigDecimal.ROUND_HALF_UP));
                            sub.setAssetsOrigAmtCny((new BigDecimal(eachProportioin.get(i))
                                    .divide(BigDecimal.valueOf(100)).multiply(assetsOrigamtCny)).setScale(2,BigDecimal.ROUND_HALF_UP));
                        }
                        // 基础资产转让金额
                        if (currencyTransferamt == null ) {
                            sub.setCurrencyTransferAmt(new BigDecimal("0"));
                            sub.setCurrencyTransferAmtCny(new BigDecimal("0"));
                        } else {
                            sub.setCurrencyTransferAmt((new BigDecimal(eachProportioin.get(i))
                                    .divide(BigDecimal.valueOf(100)).multiply(currencyTransferamt)).setScale(2,BigDecimal.ROUND_HALF_UP));
                            sub.setCurrencyTransferAmtCny((new BigDecimal(eachProportioin.get(i))
                                    .divide(BigDecimal.valueOf(100)).multiply(currencyTransferamtcny)).setScale(2,BigDecimal.ROUND_HALF_UP));
                        }
                        // 基础资产期末余额
                        if (currencyAmt == null ) {
                            sub.setCurrencyAmt(new BigDecimal("0"));
                        } else {
                            sub.setCurrencyAmt((new BigDecimal(eachProportioin.get(i))
                                    .divide(BigDecimal.valueOf(100)).multiply(currencyAmt)).setScale(2,BigDecimal.ROUND_HALF_UP));

                        }
                        sub.setAssetLiabilities(infoDto.getAssetsLiabilities());
                        sub.setAssetUsufructCode(infoDto.getAssetUsufructCode());
                        sub.setAssetTransferName(infoDto.getAssetsTransferName());
                        sub.setAssetTransferId(infoDto.getAssetsTransferId());
                        sub.setAssetInvest(infoDto.getAssetsInvest());
                        sub.setAssetTransferIindu(infoDto.getAssetsTransferIindu());
                        //担保方式
                        sub.setGuaranteeMethod(infoDto.getGuaranteeMethod());
                        if (StringUtil.equals("1", infoDto.getAssetsInvest()) || StringUtil.equals("6", infoDto.getAssetsInvest())) {
                            sub.setAssetTransfer(null);
                        } else {
                            /*
                             * 若基础资产出让机构类型字段不为1：住户、 6：境外时，取“基础资产出让机构注册地区（县）”字段 ，若该字段为空则取“基础资产出让机构注册地区（市）”仍为空则取“基础资产出让机构注册地区（省）”*/
                            String value = infoDto.getAssetsTransferCounty() == null ? infoDto.getAssetsTransferCity() : infoDto.getAssetsTransferCounty();
                            value = value == null ? infoDto.getAssetsTransferProv() : value;
                            sub.setAssetTransfer(value);
                        }
                        sub.setEconomicComposition(infoDto.getEconomicComposition());
                        sub.setAssetTransferScale(infoDto.getAssetsTransferScale());
                        sub.setTransferVdate(infoDto.getTransferVdate());
                        sub.setTransferEstiMdate(infoDto.getTransferEstiMdate());
                        sub.setTransferExtdueMdate(infoDto.getTransferExtdueMdate());
                        sub.setTransferActMdate(infoDto.getTransferActmdate());
                        sub.setBasicAssetType(infoDto.getBasicassetType());
                        sub.setAssetsOrigCcy(infoDto.getAssetsOrigccy());
                        //sub.setAssetsOrigAmt(infoDto.getAssetsOrigamt());
                        //sub.setAssetsOrigAmtCny(infoDto.getAssetsOrigamtCny());
                        sub.setCurrencyTransferCcy(infoDto.getCurrencyTransferccy());
                        //sub.setCurrencyTransferAmt(infoDto.getCurrencyTransferamt());
                        //sub.setCurrencyTransferAmtCny(infoDto.getCurrencyTransferamtcny());
                        sub.setLabelTransferorMark(infoDto.getLabelTransferorMark());
                        sub.setRepurchaseMark(infoDto.getRepurchaseMark());
                        sub.setRateFixedFlag(infoDto.getRateFixedFlag());
                        sub.setAssetRateLevel(infoDto.getAssetRateLevel());
                        //sub.setAssetsInvDepa(infoDto.getAssetsInvDepa());
                        sub.setCurrencyEperiodCcy(infoDto.getCurrencyEperiodccy());
                        initCommonValue(sub,exchangeSource,dateList,batchTime,termId);
                        // 基础资产投向部门 多选 生成多条
                        invList.add(sub);
                    }
                    /**处理尾差 start */
                    //按照金额降序排序
                    Collections.sort(invList, new Comparator<ZG06PbcColAssetUsufructSubBatchDto>() {
                        public int compare(ZG06PbcColAssetUsufructSubBatchDto s1, ZG06PbcColAssetUsufructSubBatchDto s2) {
                            return s2.getAssetsOrigAmt().compareTo(s1.getAssetsOrigAmt());
                        }
                    });
                    if(CollectionUtils.isNotEmpty(invList)){
                        BigDecimal amt1=BigDecimal.ZERO;
                        BigDecimal amt2=BigDecimal.ZERO;
                        BigDecimal amt3=BigDecimal.ZERO;
                        BigDecimal amt4=BigDecimal.ZERO;
                        BigDecimal amt5=BigDecimal.ZERO;
                        for(int j=1;j<invList.size();j++){
                            if(invList.get(j).getAssetsOrigAmt()!=null){
                                if (amt1 != null) {
                                    amt1 = amt1.add(invList.get(j).getAssetsOrigAmt());
                                }
                            }
                            if(invList.get(j).getAssetsOrigAmtCny()!=null){
                                if (amt2 != null) {
                                    amt2 = amt2.add(invList.get(j).getAssetsOrigAmtCny());
                                }
                            }
                            if(invList.get(j).getCurrencyTransferAmt()!=null){
                                if (amt3 != null) {
                                    amt3 = amt3.add(invList.get(j).getCurrencyTransferAmt());
                                }
                            }
                            if(invList.get(j).getCurrencyTransferAmtCny()!=null){
                                if (amt4 != null) {
                                    amt4 = amt4.add(invList.get(j).getCurrencyTransferAmtCny());
                                }
                            }
                            if(invList.get(j).getCurrencyAmt()!=null){
                                if (amt5 != null) {
                                    amt5 = amt5.add(invList.get(j).getCurrencyAmt());
                                }
                            }
                        }

                        if (assetsOrigamt != null) {
                            invList.get(0).setAssetsOrigAmt(assetsOrigamt.subtract(amt1));
                        }
                        invList.get(0).setAssetsOrigAmtCny(assetsOrigamtCny.subtract(amt2));
                        invList.get(0).setCurrencyTransferAmt(currencyTransferamt.subtract(amt3));
                        invList.get(0).setCurrencyTransferAmtCny(currencyTransferamtcny.subtract(amt4));
                        invList.get(0).setCurrencyAmt(currencyAmt.subtract(amt5));
                        invList.get(0).setCurrencyAmtCny(currencyAmt.subtract(amt5));

                    }
                    /**处理尾差 end */
                    insertList.addAll(invList);


                } else {
                    subBatchDto.setAssetLiabilities(infoDto.getAssetsLiabilities());
                    subBatchDto.setAssetUsufructCode(infoDto.getAssetUsufructCode());
                    subBatchDto.setAssetTransferName(infoDto.getAssetsTransferName());
                    subBatchDto.setAssetTransferId(infoDto.getAssetsTransferId());
                    subBatchDto.setAssetInvest(infoDto.getAssetsInvest());
                    subBatchDto.setAssetTransferIindu(infoDto.getAssetsTransferIindu());
                    //担保方式
                    subBatchDto.setGuaranteeMethod(infoDto.getGuaranteeMethod());
                    if (StringUtil.equals("1", infoDto.getAssetsInvest()) || StringUtil.equals("6", infoDto.getAssetsInvest())) {
                        subBatchDto.setAssetTransfer(null);
                    } else {
                        /*
                         * 若基础资产出让机构类型字段不为1：住户、 6：境外时，取“基础资产出让机构注册地区（县）”字段 ，若该字段为空则取“基础资产出让机构注册地区（市）”仍为空则取“基础资产出让机构注册地区（省）”*/
                        String value = infoDto.getAssetsTransferCounty() == null ? infoDto.getAssetsTransferCity() : infoDto.getAssetsTransferCounty();
                        value = value == null ? infoDto.getAssetsTransferProv() : value;
                        subBatchDto.setAssetTransfer(value);
                    }
                    subBatchDto.setEconomicComposition(infoDto.getEconomicComposition());
                    subBatchDto.setAssetTransferScale(infoDto.getAssetsTransferScale());
                    subBatchDto.setTransferVdate(infoDto.getTransferVdate());
                    subBatchDto.setTransferEstiMdate(infoDto.getTransferEstiMdate());
                    subBatchDto.setTransferExtdueMdate(infoDto.getTransferExtdueMdate());
                    subBatchDto.setTransferActMdate(infoDto.getTransferActmdate());
                    subBatchDto.setBasicAssetType(infoDto.getBasicassetType());
                    subBatchDto.setAssetsOrigCcy(infoDto.getAssetsOrigccy());
                    subBatchDto.setAssetsOrigAmt(infoDto.getAssetsOrigamt());
                    subBatchDto.setAssetsOrigAmtCny(infoDto.getAssetsOrigamtCny());
                    subBatchDto.setCurrencyTransferCcy(infoDto.getCurrencyTransferccy());
                    subBatchDto.setCurrencyTransferAmt(infoDto.getCurrencyTransferamt());
                    subBatchDto.setCurrencyTransferAmtCny(infoDto.getCurrencyTransferamtcny());
                    subBatchDto.setLabelTransferorMark(infoDto.getLabelTransferorMark());
                    subBatchDto.setRepurchaseMark(infoDto.getRepurchaseMark());
                    subBatchDto.setRateFixedFlag(infoDto.getRateFixedFlag());
                    subBatchDto.setAssetRateLevel(infoDto.getAssetRateLevel());
                    subBatchDto.setAssetsInvDepa(infoDto.getAssetsInvDepa());
                    subBatchDto.setCurrencyEperiodCcy(infoDto.getCurrencyEperiodccy());
                    initCommonValue(subBatchDto,exchangeSource,dateList,batchTime,termId);
                    // 基础资产投向部门 单选 生成单条
                    insertList.add(subBatchDto);
                }
            }else {
                iterator.remove();
                continue;
            }
        }
        if (CollectionUtils.isNotEmpty(insertList)){
            logger.info("采集平台资产收益权明细信息：报表生成校验 开始----------");
            List<ZG06PbcColAssetUsufructSubBatchDto> listData = pbcDataCheckUtil.getTrueData(insertList, null, DictCodeListExt2.采集平台资产收益权明细信息.getValue(), true, "1", ZG06PbcColAssetUsufructSubBatchDto.class);
            logger.info("采集平台资产收益权明细信息：报表生成校验 结束----------");
            intoDatabase(listData);
        }
    }

    public void initCommonValue(ZG06PbcColAssetUsufructSubBatchDto batchDto,String exchangeSource,List<Date> dateList,Date batchTime,String termId){
        // 报送状态
        batchDto.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
        // 主键
        batchDto.setDetailUuid(IDGenerator.generate(36));
        // 数据日期
        batchDto.setStatusDate(batchTime);
        // 创建人
        batchDto.setCreateUser(CommonUtil.getUserName());
        // 更新人
        batchDto.setUpdateUser(CommonUtil.getUserName());
        // 创建部门
        batchDto.setCreateDept(CommonUtil.getDeptCode());
        batchDto.setCreateTime(new Date());
        batchDto.setUpdateTime(new Date());
        // 机构代码
        batchDto.setOrgCode(CommonUtil.getOrgCode());
        //数据状态
        batchDto.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
        //人行报送截止日
        batchDto.setPbcSubmitDeadline(dateList.get(0));
        //行内报送截止日
        batchDto.setSubmitDeadline(dateList.get(1));
        Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();
        //RHPTSJ-366
        /*BigDecimal assetsOrigamtCny = ChbCcyConvertUtil.exchangeMoney(batchDto.getAssetsOrigccy(), DictCodeListExt1.币种_中债_人民币.getValue(), new Date()
                , batchDto.getAssetsOrigamt(), rateMap, exchangeSource).setScale(2, RoundingMode.HALF_UP);
        batchDto.setAssetsOrigamtCny(assetsOrigamtCny);
        BigDecimal currencyTransferamtCny = ChbCcyConvertUtil.exchangeMoney(batchDto.getCurrencyTransferccy(), DictCodeListExt1.币种_中债_人民币.getValue(), new Date()
                , batchDto.getCurrencyTransferamt(), rateMap, exchangeSource).setScale(2, RoundingMode.HALF_UP);
        batchDto.setCurrencyTransferamtcny(currencyTransferamtCny);
        */
        //RHPTSJ-366
        BigDecimal currencyAmtCny = ChbCcyConvertUtil.exchangeMoney(batchDto.getCurrencyEperiodCcy(), DictCodeListExt1.币种_中债_人民币.getValue(), new Date()
                , batchDto.getCurrencyAmt(), rateMap, exchangeSource).setScale(2, RoundingMode.HALF_UP);
        batchDto.setCurrencyAmtCny(currencyAmtCny);

        batchDto.setTermId(termId);
    }

    private void intoDatabase(List<ZG06PbcColAssetUsufructSubBatchDto> insertlist) {
        logger.debug("ZG06 资产收益权明细信息插入数据：插入开始----------");
        int count = 0;
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            ZG06PbcColAssetUsufructBatchMapper batchMapper = session.getMapper(ZG06PbcColAssetUsufructBatchMapper.class);
            for (ZG06PbcColAssetUsufructSubBatchDto dto : insertlist) {
                batchMapper.insertSubData(dto);
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
            logger.error("ZG06 资产收益权明细信息入库报错:" + e.getMessage(), e);
        } finally {
            session.close();
        }
        logger.debug("ZG06 资产收益权明细信息插入数据：插入结束----------");
    }
}
