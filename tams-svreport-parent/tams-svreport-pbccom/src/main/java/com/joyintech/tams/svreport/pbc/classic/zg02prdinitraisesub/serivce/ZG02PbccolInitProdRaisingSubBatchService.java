package com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.serivce ;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.tams.constant.DictCodeListExt4;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao.ZG01PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dao.ZG02PbcPrdInitRaiseSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dto.ZG02PbcPrdInitRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.base.service.ChbTypeMappingService;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:人行单产品报表-产品初始募集信息
 * @since 1.7.0
 * @version 1.7.0
 * @作者：jiangxiao
 * @创建时间：2019年6月5日
 * @修改记录：
 */
@Service
public class ZG02PbccolInitProdRaisingSubBatchService{

    //	'036->AUD，124->CAD，156->CNY，344->HKD，392->JPY，826->GBP，840->USD，978 ->EUR
    private static Map<String, String> cnyMap = new HashMap<String, String>();
    static {
        cnyMap.put("036", "AUD");
        cnyMap.put("124", "CAD");
        cnyMap.put("156", "CNY");
        cnyMap.put("344", "HKD");
        cnyMap.put("392", "JPY");
        cnyMap.put("826", "GBP");
        cnyMap.put("840", "USD");
        cnyMap.put("978", "EUR");
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ZG02PbcPrdInitRaiseSubMapper zg02PbcPrdInitRaiseSubMapper;

    @Autowired
    private ChbTypeMappingService chbTypeMappingService;

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private ZG01PbcPrdBaseinfoSubMapper zg01PbcPrdBaseinfoSubMapper;

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

    public void processData(Date executeDate, List<String> prodIdList){
        //查询产品基本信息中已发行产品
        List<ZG02PbcPrdInitRaiseSub> allProdList = new ArrayList<ZG02PbcPrdInitRaiseSub>();
        //查询需要生成登记的产品列表
        List<ZG02PbcPrdInitRaiseSub> raisingSubList = new ArrayList<ZG02PbcPrdInitRaiseSub>();
        //查询人行产品资产池的数据
        List<ZG01PbcPrdBaseinfoSub> list=zg01PbcPrdBaseinfoSubMapper.selectAllPbcProdId();
        Map<String,String> map= list.stream().collect(Collectors.toMap(ZG01PbcPrdBaseinfoSub::getProdId,ZG01PbcPrdBaseinfoSub::getPbcProdId ));

        String bankCode=SvrDataUtil.getSysParam(SVReportGlobalConstant.CUSTOMER_BANK_CODE);
        if(!CollectionUtils.isEmpty(prodIdList)){
            int end = 0;
            for(int i=0;i<prodIdList.size();){
                end = i+100;
                if(end>prodIdList.size()){
                    end = prodIdList.size();
                }
                raisingSubList.addAll(zg02PbcPrdInitRaiseSubMapper.selectPrdRegistInfo(DateUtil.format(executeDate),prodIdList.subList(i, end)));
                allProdList.addAll(zg02PbcPrdInitRaiseSubMapper.selectPrdInfo(DateUtil.format(executeDate),prodIdList.subList(i, end)));
                i = end;
            }
        }else{
            raisingSubList = zg02PbcPrdInitRaiseSubMapper.selectPrdRegistInfo(DateUtil.format(executeDate),prodIdList);
            allProdList = zg02PbcPrdInitRaiseSubMapper.selectPrdInfo(DateUtil.format(executeDate),prodIdList);
        }
        if(CollectionUtils.isEmpty(raisingSubList)){
            return;
        }

        @SuppressWarnings("unchecked")
        List<ZG02PbcPrdInitRaiseSub>  listsub=raisingSubList.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ZG02PbcPrdInitRaiseSub -> ZG02PbcPrdInitRaiseSub.getProdId()+ ZG02PbcPrdInitRaiseSub.getVdate()))),
                                ArrayList::new
                        )
                );
        //查询产品头寸
        List<ChbPrdPosition> positionList = new ArrayList<ChbPrdPosition>();
        int end = 0;
        for(int i=0;i<listsub.size();){
            end = i+100;
            if(end>listsub.size()){
                end = listsub.size();
            }
            if(StringUtil.equals(bankCode, "001")){
                positionList.addAll(zg02PbcPrdInitRaiseSubMapper.selectPsbcPrdposition(listsub.subList(i, end)));
            }else{
                positionList.addAll(zg02PbcPrdInitRaiseSubMapper.selectPrdposition(listsub.subList(i, end)));
            }
            i = end;
        }
        //判断产品认购交易数据状态(处理留痕)
        Map<String,ZG02PbcPrdInitRaiseSub> prodmap = new HashMap<String,ZG02PbcPrdInitRaiseSub>();
        for (ZG02PbcPrdInitRaiseSub dto : raisingSubList) {
            if (!DictCodeListExt2.数据状态_删除.getValue().equals(dto.getStatus())) {
                prodmap.put(dto.getPbcProdId(), dto);
            } else if (prodmap.get(dto.getPbcProdId()) == null
                    && DictCodeListExt2.数据状态_删除.getValue().equals(dto.getStatus())) {
                prodmap.put(dto.getPbcProdId(), dto);
            }
        }
        //处理登记数据
        //新增列表
        List<ZG02PbcPrdInitRaiseSub> insertlist = new ArrayList<ZG02PbcPrdInitRaiseSub>();
        //删除列表
        List<ZG02PbcPrdInitRaiseSub> deletelist = new ArrayList<ZG02PbcPrdInitRaiseSub>();
        Iterator<Map.Entry<String,ZG02PbcPrdInitRaiseSub>> iterator = prodmap.entrySet().iterator();
        //所有已发行产品
        Map<String,ZG02PbcPrdInitRaiseSub> allProdMap = new HashMap<String,ZG02PbcPrdInitRaiseSub>();
        if(CollectionUtils.isNotEmpty(allProdList)){
            for (ZG02PbcPrdInitRaiseSub dto : allProdList) {
                allProdMap.put(dto.getPbcProdId(), dto);
            }
        }
        //需要登记的数据并且有头寸的产品按照指定维度分组后的数据集合
        Map<String,ZG02PbcPrdInitRaiseSub> allSubMap = new HashMap<String,ZG02PbcPrdInitRaiseSub>();
        logger.debug("采集平台资管产品起始募集登记数据处理开始");
        while(iterator.hasNext()){
            Map.Entry<String,ZG02PbcPrdInitRaiseSub> entry = iterator.next();
            ZG02PbcPrdInitRaiseSub subDto = entry.getValue();
            //临时头寸list
            List<ChbPrdPosition> poslist = new ArrayList<ChbPrdPosition>();
            for(ChbPrdPosition pos : positionList){
                if(StringUtil.equals(bankCode, "001")){
                    if(cnyMap.get(pos.getCcy())!=null){
                        pos.setCcy(cnyMap.get(pos.getCcy()));
                    }
                }
                if(pos.getProdId().equals(subDto.getProdId())){
                    poslist.add(pos);
                }
            }
            if(poslist.size()==0){
                allProdMap.put(subDto.getPbcProdId(),subDto);//allProdMap中放进需要登记的数据但是没有头寸的产品
                continue;
            }else{
                allProdMap.remove(subDto.getPbcProdId());//allProdMap中去掉allSubMap已有的数据
            }
            subDto.setPrdPositionList(poslist);
            /*按产品维度生成初始募集信息Map
            (key:产品id+销售区域+客户类型+币种)
            (key:产品id+销售区域+客户类型)
            (key:产品id+币种+客户类型)*/
            allSubMap.putAll(raisingdataProcess(subDto,executeDate));
        }
        //处理allProdMap
        Iterator<Map.Entry<String,ZG02PbcPrdInitRaiseSub>> allProdMapiterator = allProdMap.entrySet().iterator();
        while(allProdMapiterator.hasNext()){
            Map.Entry<String,ZG02PbcPrdInitRaiseSub> entry = allProdMapiterator.next();
            ZG02PbcPrdInitRaiseSub dto = entry.getValue();
            ZG02PbcPrdInitRaiseSub sub = new ZG02PbcPrdInitRaiseSub();
            //产品代码
            sub.setPbcProdId(dto.getPbcProdId());
            //产品内部代码
            sub.setProdId(dto.getProdId());
            //地区代码
            sub.setBuyPlace(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PBC_RAISING_DEFAULT_BUYPALCE));
            //客户类型
            sub.setCustomerType1(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PBC_RAISING_DEFAULT_CUSTOMERTYPE));
            //币种代码 RHPTSJ-294
            sub.setCcy(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PBC_RAISING_DEFAULT_CCY));
            //起始募集金额
            sub.setInitRaisingAmtsum(BigDecimal.ZERO);
            //起始募集金额折人民币
            sub.setInitRaisingAmtsumcny(BigDecimal.ZERO);
            //起始募集份额
            sub.setInitRaisingQty(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
            sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
            sub.setRegistSeqno(IDGenerator.generate(36));
            //数据日期 - 没有交易取产品起息日
            sub.setStatusDate(dto.getVdate());
            sub.setCreateUser(CommonUtil.getUserName());
            sub.setUpdateUser(CommonUtil.getUserName());
            sub.setCreateDept(CommonUtil.getDeptCode());
            sub.setOrgCode(CommonUtil.getOrgCode());
            Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
            List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG02资管产品初始募集信息.getValue(),
            DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
            dto.getVdate(),SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
            if (!StringUtil.isEmptyList(dateList)) {
                sub.setPbcSubmitDeadline(dateList.get(0));
                sub.setSubmitDeadline(dateList.get(1));
            }
            /**填报指引需求变更  start*/
            ZG02PbcPrdInitRaiseSub sub02 = new ZG02PbcPrdInitRaiseSub();
            BeanUtil.copyPropertiesIgnoreNull(sub02, sub);
            sub02.setRegistSeqno(IDGenerator.generate(36));
            sub02.setBuyPlace("000000");
            allSubMap.put(sub02.getProdId()+sub02.getBuyPlace()+sub02.getCustomerType1()+sub02.getCcy(), sub02);
            ZG02PbcPrdInitRaiseSub sub03 = new ZG02PbcPrdInitRaiseSub();
            BeanUtil.copyPropertiesIgnoreNull(sub03, sub);
            sub03.setRegistSeqno(IDGenerator.generate(36));
            sub03.setCcy("BWB");
            allSubMap.put(sub03.getProdId()+sub03.getBuyPlace()+sub03.getCustomerType1()+sub03.getCcy(), sub03);
            ZG02PbcPrdInitRaiseSub sub04 = new ZG02PbcPrdInitRaiseSub();
            BeanUtil.copyPropertiesIgnoreNull(sub04, sub);
            sub04.setRegistSeqno(IDGenerator.generate(36));
            sub04.setCcy("BWB");
            sub04.setBuyPlace("000000");
            allSubMap.put(sub04.getProdId()+sub04.getBuyPlace()+sub04.getCustomerType1()+sub04.getCcy(), sub04);

            allSubMap.put(sub.getProdId()+sub.getBuyPlace()+sub.getCustomerType1()+sub.getCcy(), sub);
            /**填报指引需求变更  end*/

        }
        //处理allSubMap
        Iterator<Map.Entry<String,ZG02PbcPrdInitRaiseSub>> allSubMapiterator = allSubMap.entrySet().iterator();
        while(allSubMapiterator.hasNext()){
            Map.Entry<String,ZG02PbcPrdInitRaiseSub> entry = allSubMapiterator.next();
            ZG02PbcPrdInitRaiseSub subDto = entry.getValue();
            // 删除数据(未报送的删除)
            deletelist.add(subDto);
            // 按产品维度查询已登记的初始募集信息
            List<ZG02PbcPrdInitRaiseSub> raisingRegList = zg02PbcPrdInitRaiseSubMapper.selectRegList(subDto);
            if(raisingRegList.size()>0) {
                continue;
            }
            // ZG02不再考虑数据流向的问题，只生成新建未报送。
            subDto.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
            subDto.setCreateUser(CommonUtil.getUserName());
            subDto.setUpdateUser(CommonUtil.getUserName());
            //数据检查用字段，不入库
            if(map.containsKey(subDto.getProdId())){
                subDto.setPbcPordIdOut(map.get(subDto.getProdId()));
            }else {
                if(subDto.getPbcProdId().contains("INS")){
                    subDto.setPbcPordIdOut(subDto.getPbcProdId().substring(3));
                }else {
                    subDto.setPbcPordIdOut(subDto.getPbcProdId());
                }
            }
            insertlist.add(subDto);
        }
        /**指引补充需求变更  start */



        /**指引补充需求变更   end */

        //需要登记的数据并且有头寸的产品
        logger.debug("采集平台资管产品起始募集登记数据处理结束");


        logger.info("采集平台资管产品基本信息：报表生成校验 开始----------");
        List<ZG02PbcPrdInitRaiseSub> listData = pbcDataCheckUtil.getTrueData(insertlist, null, DictCodeListExt2.采集平台资管产品初始募集信息.getValue(), true, "1", ZG02PbcPrdInitRaiseSub.class);
        logger.info("采集平台资管产品基本信息：报表生成校验 结束----------");
        //入库
        intoDatabase(listData,deletelist);
    }

    private Map<String,ZG02PbcPrdInitRaiseSub> raisingdataProcess(ZG02PbcPrdInitRaiseSub dto,Date executeDate){
        Map<String,ZG02PbcPrdInitRaiseSub> resultMap = new HashMap<String,ZG02PbcPrdInitRaiseSub>();
        Map<String,ZG02PbcPrdInitRaiseSub> oldMap = new HashMap<String,ZG02PbcPrdInitRaiseSub>();
        Map<String,ZG02PbcPrdInitRaiseSub> bwbMap = new HashMap<String,ZG02PbcPrdInitRaiseSub>();
        Map<String,ZG02PbcPrdInitRaiseSub> buyPlaceMap = new HashMap<String,ZG02PbcPrdInitRaiseSub>();
        Map<String,ZG02PbcPrdInitRaiseSub> bwbbuyPlaceMap = new HashMap<String,ZG02PbcPrdInitRaiseSub>();

        Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();
        if(!CollectionUtils.isEmpty(dto.getPrdPositionList())){
            //报送截止日生成
            //当前批次报送时点集合
            Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
            List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG02资管产品初始募集信息.getValue(),
                    DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
                    dto.getVdate(),SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
            String bankCode=SvrDataUtil.getSysParam(SVReportGlobalConstant.CUSTOMER_BANK_CODE);
            for(ChbPrdPosition pos : dto.getPrdPositionList()){
                //根据客户二级类型映射成客户一级类型
                String customerType1 = chbTypeMappingService.getMappingValue("PBC_COL_RAISING_INVESTOR_TYPE", pos.getCustomerType2());
                String buyPlace = pos.getBuyplace();
                // 通过销售区域获取上级省
                if(!StringUtil.equals(bankCode, "001")){
                    if (SvrDataUtil.getNameFromDataDict(DictCode.行政区划_县, buyPlace) != null){
                        buyPlace = SvrDataUtil.getParentCodeFromDataDict(DictCode.行政区划_县, buyPlace);
                    }
                    if (SvrDataUtil.getNameFromDataDict(DictCode.行政区划_市, buyPlace) != null){
                        buyPlace = SvrDataUtil.getParentCodeFromDataDict(DictCode.行政区划_市, buyPlace);
                    }
                }
                //按照产品、地区、客户类型、币种 维度生成登记数据
                String key = pos.getProdId()+"|"+buyPlace+"|"+customerType1+"|"+pos.getCcy()+"|";
                processMapData(oldMap,key,dto,buyPlace,customerType1,pos,dateList,executeDate,"");
                //按照产品、地区、客户类型 维度生成登记数据
                String bwbKey = pos.getProdId()+"|"+buyPlace+"|"+customerType1+"|";
                processMapData(bwbMap,bwbKey,dto,buyPlace,customerType1,pos,dateList,executeDate,"0");
                //按照产品、币种、客户类型 维度生成登记数据
                String byuPlaceKey = pos.getProdId()+"|"+pos.getCcy()+"|"+customerType1+"|";
                processMapData(buyPlaceMap,byuPlaceKey,dto,buyPlace,customerType1,pos,dateList,executeDate,"1");
                String bbyuPlaceKey = pos.getProdId()+"|"+"BWB"+"|"+customerType1+"|";
                processMapData(bwbbuyPlaceMap,bbyuPlaceKey,dto,buyPlace,customerType1,pos,dateList,executeDate,"2");

            }
        }
        resultMap.putAll(oldMap);
        resultMap.putAll(bwbMap);
        resultMap.putAll(buyPlaceMap);
        resultMap.putAll(bwbbuyPlaceMap);
        return resultMap;
    }

    private void processMapData(Map<String,ZG02PbcPrdInitRaiseSub> resultMap,String key,ZG02PbcPrdInitRaiseSub dto,
                                String buyPlace,String customerType1,ChbPrdPosition pos,List<Date> dateList,Date executeDate,String flag){
        String exchangeSource = CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
        if(resultMap.get(key)==null){
            ZG02PbcPrdInitRaiseSub sub = new ZG02PbcPrdInitRaiseSub();
            //产品代码
            sub.setPbcProdId(dto.getPbcProdId());
            //产品内部代码
            sub.setProdId(dto.getProdId());
            //地区代码
            sub.setBuyPlace(buyPlace);
            if("1".equals(flag)||
                    "2".equals(flag)){
                sub.setBuyPlace("000000");
            }
            //客户类型
            sub.setCustomerType1(customerType1);
            //币种代码
            sub.setCcy(pos.getCcy());
            //起始募集金额
            BigDecimal amtsum = (sub.getInitRaisingAmtsum()==null?BigDecimal.ZERO:sub.getInitRaisingAmtsum()).add(pos.getBalanceAmt());
            sub.setInitRaisingAmtsum(amtsum);
            //起始募集金额折人民币
            if("0".equals(flag)||
                    "2".equals(flag)){
                BigDecimal amtsumcny = ChbCcyConvertUtil.exchangeMoney(pos.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(), pos.getCdate(), amtsum, null, exchangeSource).setScale(2, RoundingMode.HALF_UP);
                //BWB的初始募集金额 换算为人民币
                //BWB的初始募集金额 换算为人民币
                sub.setInitRaisingAmtsum(amtsumcny);
                sub.setInitRaisingAmtsumcny(amtsumcny);
                sub.setCcy(DictCodeListExt1.币种_人行_本位币.getValue());
            }else if(!DictCodeListExt1.币种_中债_人民币.getValue().equals(pos.getCcy())){
                BigDecimal amtsumcny = ChbCcyConvertUtil.exchangeMoney(pos.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(), pos.getCdate(), amtsum, null, exchangeSource).setScale(2, RoundingMode.HALF_UP);
                sub.setInitRaisingAmtsumcny(amtsumcny);
            }else{
                sub.setInitRaisingAmtsumcny(amtsum);
            }
            //起始募集份额
            BigDecimal initRaisingQty = (sub.getInitRaisingQty()==null?BigDecimal.ZERO:sub.getInitRaisingQty()).add(pos.getBalanceQty()).setScale(2, BigDecimal.ROUND_HALF_UP);
            sub.setInitRaisingQty(initRaisingQty.setScale(2, BigDecimal.ROUND_HALF_UP));
            sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
            sub.setRegistSeqno(IDGenerator.generate(36));
            //数据日期 - 有交易取交易认购日
            sub.setStatusDate(dto.getDealDate());
            sub.setCreateUser(CommonUtil.getUserName());
            sub.setUpdateUser(CommonUtil.getUserName());
            sub.setCreateDept(CommonUtil.getDeptCode());
            sub.setOrgCode(CommonUtil.getOrgCode());
            if (!StringUtil.isEmptyList(dateList)) {
                sub.setPbcSubmitDeadline(dateList.get(0));
                sub.setSubmitDeadline(dateList.get(1));
            }
            resultMap.put(key, sub);
        } else {
            ZG02PbcPrdInitRaiseSub sub = resultMap.get(key);
            //起始募集金额
            BigDecimal amtsum = (sub.getInitRaisingAmtsum()==null?BigDecimal.ZERO:sub.getInitRaisingAmtsum()).add(pos.getBalanceAmt());
            sub.setInitRaisingAmtsum(amtsum);
            //起始募集金额折人民币
            if("0".equals(flag)||
                    "2".equals(flag)){
                BigDecimal amtsumcny = ChbCcyConvertUtil.exchangeMoney(pos.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(), pos.getCdate(), amtsum, null, exchangeSource).setScale(2, RoundingMode.HALF_UP);
                sub.setInitRaisingAmtsum(amtsumcny);
                sub.setInitRaisingAmtsumcny(amtsumcny);
                sub.setCcy(DictCodeListExt1.币种_人行_本位币.getValue());
            }else if(!DictCodeListExt1.币种_中债_人民币.getValue().equals(pos.getCcy())){
                BigDecimal amtsumcny = ChbCcyConvertUtil.exchangeMoney(pos.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(), pos.getCdate(), amtsum, null, exchangeSource).setScale(2, RoundingMode.HALF_UP);
                sub.setInitRaisingAmtsumcny(amtsumcny);
            }else{
                sub.setInitRaisingAmtsumcny(amtsum);
            }
            //起始募集份额
            BigDecimal initRaisingQty = (sub.getInitRaisingQty()==null?BigDecimal.ZERO:sub.getInitRaisingQty()).add(pos.getBalanceQty()).setScale(2, BigDecimal.ROUND_HALF_UP);
            sub.setInitRaisingQty(initRaisingQty.setScale(2, BigDecimal.ROUND_HALF_UP));

        }
    }

    private void intoDatabase(List<ZG02PbcPrdInitRaiseSub> insertlist, List<ZG02PbcPrdInitRaiseSub> deletelist) {
        if (CollectionUtils.isNotEmpty(deletelist)) {
            // 插入前先删除未报送的登记数据
            logger.debug("采集平台资管产品初始募集信息删除未报送数据：删除开始----------");
            // 数据批量一次删除条数,如果参数设置过大则强制定位50
            int batchDeleteCount = reportSelectOrDeleteCount;
            // 每批最后一个的下标
            int batchDeleteLastIndex = batchDeleteCount;
            for(int index = 0; index < deletelist.size();){
                if(batchDeleteLastIndex > deletelist.size()-1){
                    batchDeleteLastIndex = deletelist.size();
                    zg02PbcPrdInitRaiseSubMapper.deleteBatch(deletelist.subList(index, batchDeleteLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                    zg02PbcPrdInitRaiseSubMapper.deleteBatch(deletelist.subList(index, batchDeleteLastIndex));
                    index = batchDeleteLastIndex;//设置下一批下标
                    batchDeleteLastIndex = index + batchDeleteCount;
                }
            }
            logger.debug("采集平台资管产品初始募集信息删除未报送数据：删除结束----------");
            logger.debug("采集平台资管产品初始募集信息插入数据：插入开始----------");
            int count = 0;
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try{
                ZG02PbcPrdInitRaiseSubMapper batchMapper =  session.getMapper(ZG02PbcPrdInitRaiseSubMapper.class);
                for(ZG02PbcPrdInitRaiseSub dto : insertlist){
                    batchMapper.insert(dto);
                    count++;
                    if(count%1000==0){
                        session.commit();
                        // 清理缓存，防止溢出
                        session.clearCache();
                    }
                }
                session.commit();
                // 清理缓存，防止溢出
                session.clearCache();
            } catch(Exception e) {
                session.rollback();
                logger.error("采集平台资管产品初始募集信息入库报错:"+e.getMessage(),e);
            } finally {
                session.close();
            }
            logger.debug("采集平台资管产品初始募集信息插入数据：插入结束----------");
        }
    }

    /**
     * @Description 比较数据，数据相同返回true
     * @param dto
     * @param regdto
     * @return boolean
     * @version 1.7
     */
    private boolean compareData(ZG02PbcPrdInitRaiseSub dto,ZG02PbcPrdInitRaiseSub regdto){
        ZG02PbcPrdInitRaiseSub compare1 = genDto(dto);
        ZG02PbcPrdInitRaiseSub compare2 = genDto(regdto);
        if(JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))){
            return true;
        }else{
            return false;
        }
    }

    private ZG02PbcPrdInitRaiseSub genDto(ZG02PbcPrdInitRaiseSub regdto) {
        ZG02PbcPrdInitRaiseSub sub = new ZG02PbcPrdInitRaiseSub();
        sub.setPbcProdId(regdto.getPbcProdId());
        sub.setBuyPlace(regdto.getBuyPlace());
        sub.setCustomerType1(regdto.getCustomerType1());
        sub.setCcy(regdto.getCcy());
        sub.setInitRaisingAmtsum((regdto.getInitRaisingAmtsum()==null?BigDecimal.ZERO:regdto.getInitRaisingAmtsum()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setInitRaisingAmtsumcny((regdto.getInitRaisingAmtsumcny()==null?BigDecimal.ZERO:regdto.getInitRaisingAmtsumcny()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setInitRaisingQty((regdto.getInitRaisingQty()==null?BigDecimal.ZERO:regdto.getInitRaisingQty()).setScale(2, BigDecimal.ROUND_HALF_UP));
        return sub;
    }
}