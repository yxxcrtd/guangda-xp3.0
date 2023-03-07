package com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.exception.BusinessException;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.base.service.ChbTypeMappingService;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao.ZG01PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dao.ZG04PbcProdRaiseSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.common.service.PbcClassicsSubService;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能:资管产品存续期募集信息
 * @since 1.7.0
 * @version 1.7.0
 * @作者：dingfeng
 * @创建时间：2019年6月11日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ZG04PbcPrdCashRaiseSubService extends PbcClassicsSubService<ZG04PbcProdRaiseSub> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ZG04PbcProdRaiseSubMapper zg04PbcProdRaiseSubMapper;

    @Autowired
    private ZG01PbcPrdBaseinfoSubMapper zg01PbcPrdBaseinfoSubMapper;
	
	@Autowired
    private ChbTypeMappingService chbTypeMappingService;
	
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
	
    /**
     * 功能:资管产品存续期募集信息批处理入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：dingfeng 2022年11月14日
     * @param executeDate
     * @param prodIdList 
     * @see PbcClassicsSubService#genReport(Date, List)
     */
	@Override
	public void genReport(Date executeDate, List<String> prodIdList) {
        //1.判断跑批日期，如果不是1号，批处理不执行。
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(executeDate);
        if (calendar.get(Calendar.DATE) != 1) {  //每月1日生成上月的数据
            logger.info("每月1日生成上月的数据！");
            return;
        }
        Date addDay = com.joyintech.framework.util.DateUtil.addDay(executeDate, -1);
        String termId = com.joyintech.framework.util.DateUtil.format(addDay, com.joyintech.framework.util.DateUtil.MONTH_OTHER_FORMAT);
        List<ZG04PbcProdRaiseSub> list= zg04PbcProdRaiseSubMapper.selectPrdCashRaiseRegist(termId);
        if (list!=null&&list.size() > 0) {
            logger.info("当月存在已报送数据，批处理不再继续执行！");
            return;
        }
        //删除当期所有数据
        zg04PbcProdRaiseSubMapper.deleteByTermId(termId);

        genRegistReport(executeDate,prodIdList);
	}
	
	/**
	 * 功能:资管产品存续期募集信息批处理
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：dingfeng 2022年11月14日
	 * @param executeDate 批处理日期
	 * @param prodIdList 产品list
	 * void
	 */
	public void genRegistReport(Date executeDate, List<String> prodIdList){

        //查询人行产品资产池的数据
        List<ZG01PbcPrdBaseinfoSub> list=zg01PbcPrdBaseinfoSubMapper.selectAllPbcProdId();
        Map<String,String> map= list.stream().collect(Collectors.toMap(ZG01PbcPrdBaseinfoSub::getProdId,ZG01PbcPrdBaseinfoSub::getPbcProdId ));

        // 根据月末日期查询产品头寸
		List<ZG04PbcProdRaiseSub> prdCashRaiseList = new ArrayList<>();
		String bankCode= SvrDataUtil.getSysParam(SVReportGlobalConstant.CUSTOMER_BANK_CODE);
        // 获取系统参数：非净值型产品赎回交易取值
        String pbcProdRaising = SvrDataUtil.getSysParam(SVReportGlobalConstant.PBC_PROD_RAISING);
        // 分批处理数据,100个产品查询一次
        if (!StringUtil.isEmptyList(prodIdList)){
	        for (int i = 0;i < prodIdList.size();){
	        	int end = i + 100;
	        	if (end > prodIdList.size()){
	        		end = prodIdList.size();
	        	}
	        	prdCashRaiseList.addAll(zg04PbcProdRaiseSubMapper.selectPrdCashRaise(bankCode, DateUtil.getLastDayOfLayMonth(executeDate),prodIdList.subList(i, end),pbcProdRaising));
	            i = end;
	        }
        } else {
        	prdCashRaiseList.addAll(zg04PbcProdRaiseSubMapper.selectPrdCashRaise(bankCode, DateUtil.getLastDayOfLayMonth(executeDate),null,pbcProdRaising));
        }
        DateUtil.format(DateUtil.addMonths(executeDate,-1));
        //查询当月发行成功，且无交易的产品
        List<String>  opordList= zg04PbcProdRaiseSubMapper.getZeroProdList(DateUtil.format(DateUtil.addMonths(executeDate,-1)), DateUtil.format(executeDate));

        //在获取登记数据前优先处理地区与客户类型
        if(CollectionUtils.isNotEmpty(prdCashRaiseList)) {
            for (ZG04PbcProdRaiseSub sub : prdCashRaiseList) {
                 //当月到期
                if(StringUtil.equals(com.joyintech.framework.util.DateUtil.format(sub.getCmdate(), com.joyintech.framework.util.DateUtil.MONTH_OTHER_FORMAT),
                        com.joyintech.framework.util.DateUtil.format(DateUtil.addDay(executeDate,-1), com.joyintech.framework.util.DateUtil.MONTH_OTHER_FORMAT))){
                    //查询到期日前净值
                    ZG04PbcProdRaiseSub univaluedto=zg04PbcProdRaiseSubMapper.selectMaxYiledMdate(sub.getProdId(),DateUtil.format(sub.getCmdate()));
                    if(univaluedto!=null){
                        if(univaluedto.getProdValue()!=null){
                            sub.setProdValue(univaluedto.getProdValue());
                        }
                        if(univaluedto.getUnitValueTotal()!=null){
                            sub.setUnitValueTotal(univaluedto.getUnitValueTotal());
                        }
                    }
                }

                // 先映射在分组
                // 地区代码映射到一级分类
                if (StringUtil.isNotEmpty(sub.getBuyPlace())) {
                    String buyPlace = sub.getBuyPlace();
                    // 通过销售区域获取上级省
                    // 县级往市级映射
                    if (SvrDataUtil.getNameFromDataDict(DictCode.行政区划_县, buyPlace) != null) {
                        buyPlace = SvrDataUtil.getParentCodeFromDataDict(DictCode.行政区划_县, buyPlace);
                    }
                    // 市级往省级映射
                    if (SvrDataUtil.getNameFromDataDict(DictCode.行政区划_市, buyPlace) != null) {
                        buyPlace = SvrDataUtil.getParentCodeFromDataDict(DictCode.行政区划_市, buyPlace);
                    }
                    sub.setBuyPlace(buyPlace);
                }
                // 根据客户二级类型映射成客户一级类型
                if (StringUtil.isNotEmpty(sub.getCustomerType1())) {
                    String customerType1 = chbTypeMappingService.getMappingValue("PBC_RAISING_TYPE", sub.getCustomerType1());
                    sub.setCustomerType1(customerType1);
                }
            }
        }


        Map<String, List<ZG04PbcProdRaiseSub>> posProdAllMap = prdCashRaiseList.stream().collect(
                Collectors.groupingBy(ZG04PbcProdRaiseSub::getPbcProdidAndOrgCode, Collectors.toList()));

        // 入库List
        List<ZG04PbcProdRaiseSub> insertList = Lists.newArrayList();
        /// 删除列表，月报默认全删全插
        List<ZG04PbcProdRaiseSub> deleteList = Lists.newArrayList();

        // 记录已经统计过得单产品(带机构)
        Map<String, String> prodMap = new HashMap<>();
        // 根据查询出的数据分组进行出单产品报表
        for (String key : posProdAllMap.keySet()) {
            if (!prodMap.containsKey(key)) {
                prodMap.put(key, key);
            } else {
                // 已经出过报表则跳过
                continue;
            }
            genSingleProdRegistReport(executeDate, key, posProdAllMap.get(key), null, opordList, map, insertList, deleteList);
        }

        //校验前处理小数位数
        if(!StringUtil.isEmptyList(insertList) && insertList.size()>0){
            insertList.forEach(
                    item ->{
                        item.setRaisingAmtsum(StringUtil.isNotEmpty(item.getRaisingAmtsum())?item.getRaisingAmtsum().setScale(2, RoundingMode.HALF_UP):null);
                        item.setRaisingAmtsumCny(StringUtil.isNotEmpty(item.getRaisingAmtsumCny())?item.getRaisingAmtsumCny().setScale(2, RoundingMode.HALF_UP):null);
                        item.setRaisingQty(StringUtil.isNotEmpty(item.getRaisingQty())?item.getRaisingQty().setScale(2, RoundingMode.HALF_UP):null);
                        item.setRedamt(StringUtil.isNotEmpty(item.getRedamt())?item.getRedamt().setScale(2, RoundingMode.HALF_UP):null);
                        item.setRedamtCny(StringUtil.isNotEmpty(item.getRedamtCny())?item.getRedamtCny().setScale(2, RoundingMode.HALF_UP):null);
                        item.setRedqty(StringUtil.isNotEmpty(item.getRedqty())?item.getRedqty().setScale(2, RoundingMode.HALF_UP):null);
                        item.setEndamtsum(StringUtil.isNotEmpty(item.getEndamtsum())?item.getEndamtsum().setScale(2, RoundingMode.HALF_UP):null);
                        item.setEndamtsumCny(StringUtil.isNotEmpty(item.getEndamtsumCny())?item.getEndamtsumCny().setScale(2, RoundingMode.HALF_UP):null);
                        item.setEndqty(StringUtil.isNotEmpty(item.getEndqty())?item.getEndqty().setScale(2, RoundingMode.HALF_UP):null);

                        item.setProdValue(StringUtil.isNotEmpty(item.getProdValue())?item.getProdValue().setScale(5, RoundingMode.HALF_UP):null);
                        item.setProdValueCny(StringUtil.isNotEmpty(item.getProdValueCny())?item.getProdValueCny().setScale(5, RoundingMode.HALF_UP):null);
                        item.setUnitValueTotal(StringUtil.isNotEmpty(item.getUnitValueTotal())?item.getUnitValueTotal().setScale(5, RoundingMode.HALF_UP):null);
                        item.setUnitValueTotalCny(StringUtil.isNotEmpty(item.getUnitValueTotalCny())?item.getUnitValueTotalCny().setScale(5, RoundingMode.HALF_UP):null);
                        item.setProdEndYield(StringUtil.isNotEmpty(item.getProdEndYield())?item.getProdEndYield().setScale(5, RoundingMode.HALF_UP):null);


                    }
            );
        }

        /**指引补充需求变更  start */
        // 处理检查字段是否显示0 add by wangyaning 20230111
        if(CollectionUtils.isNotEmpty(insertList)){
            dealZeroNumber(insertList);
        }
        /**指引补充需求变更   end */
//        if(!StringUtil.isEmptyList(insertList) && insertList.size()>0){
//            for (ZG04PbcProdRaiseSub dto : insertList) {
//                //码值转换 ZHXPCP-198
//                if (StringUtils.equals(DictCodeListExt4.行政区划_省_台湾省.getValue(),dto.getBuyPlace())){
//                    dto.setBuyPlace(DictCodeListExt4.行政区划_省_台湾.getValue());
//                    //港澳台地区 客户一级类型归为境外
//                    dto.setCustomerType1("6");
//                }
//                if (StringUtils.equals(DictCodeListExt4.行政区划_省_香港特别行政区.getValue(),dto.getBuyPlace())){
//                    dto.setBuyPlace(DictCodeListExt4.行政区划_省_香港.getValue());
//                    //港澳台地区 客户一级类型归为境外
//                    dto.setCustomerType1("6");
//                }
//                if (StringUtils.equals(DictCodeListExt4.行政区划_省_澳门特别行政区.getValue(),dto.getBuyPlace())){
//                    dto.setBuyPlace(DictCodeListExt4.行政区划_省_澳门.getValue());
//                    //港澳台地区 客户一级类型归为境外
//                    dto.setCustomerType1("6");
//                }
//            }
//        }
        // 数据入库
        logger.info("采集平台资管产品存续期募集信息：报表生成校验 开始----------"+ com.joyintech.framework.util.DateUtil.getCurrentDateTime());
        List<ZG04PbcProdRaiseSub> listData = pbcDataCheckUtil.getTrueData(insertList, null, DictCodeListExt2.采集平台资管产品存续期募集信息.getValue(), true, "1", ZG04PbcProdRaiseSub.class);
        logger.info("采集平台资管产品存续期募集信息：报表生成校验 结束----------"+ com.joyintech.framework.util.DateUtil.getCurrentDateTime());
        // 先删后插
        intoDatabase(listData,deleteList);

	}

	/**
	 * 功能:资管产品存续期募集信息批处理-生成单一产品的产品资金募集信息
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：dingfeng 2022年11月14日
	 * @param executeDate 跑批日
	 * @param prodid 产品代码
	 * @param prdCashRaiseList 跑批日当天的所有产品的产品头寸信息
	 * @param prdCashRaiseRegList 当月已登记的单产品信息
	 * void
	 */
    public void genSingleProdRegistReport(Date executeDate, String prodid
            , List<ZG04PbcProdRaiseSub> prdCashRaiseList, List<ZG04PbcProdRaiseSub> prdCashRaiseRegList, List<String> opordList, Map<String, String> map,
                                          List<ZG04PbcProdRaiseSub> insertList, List<ZG04PbcProdRaiseSub> deleteList) {
		// 新增列表
		//List<ZG04PbcProdRaiseSub> insertList = new ArrayList<>();
		// 删除列表，月报默认全删全插
		//List<ZG04PbcProdRaiseSub> deleteList = new ArrayList<>();
        // key：产品代码、地区代码、客户类型、币种代码 value:产品资金信息
        Map<String, ZG04PbcProdRaiseSub> resultMap = new HashMap<String, ZG04PbcProdRaiseSub>();
        // 人行汇率开关
        String exchangeSource = CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
        // 汇率map
        Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();
        // md5加密key
        String key = "joyin";

        if (CollectionUtils.isNotEmpty(prdCashRaiseList)){
            //createBwbData(prdCashRaiseList,rateMap,exchangeSource);
            // 如果重新生成数据则重新计算
            for (ZG04PbcProdRaiseSub sub : prdCashRaiseList){
                // 共通数据处理
                // key：产品代码、地区代码、客户类型、币种代码,汇总数据
                String keyStringSub = sub.getRaisingDate() + "|" + sub.getPbcProdId() + "|" + sub.getBuyPlace() + "|"
                        + sub.getCustomerType1() + "|" + sub.getCcy() + "|";
                String encodeStrSub=DigestUtils.md5Hex( keyStringSub + key);
                if (StringUtil.isNotEmpty(sub.getCcy()) && !DictCodeListExt1.币种_人行_本位币.getValue().equals(sub.getCcy()) && !DictCodeListExt1.币种_中债_人民币.getValue().equals(sub.getCcy())){
                    // 外部设置折算人民币
                    //当期申购金额折人民币
                    if (sub.getRaisingAmtsum() != null){
                        sub.setRaisingAmtsumCny(ChbCcyConvertUtil.
                                exchangeMoney(sub.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                        DateUtil.parse(sub.getRaisingDate()), sub.getRaisingAmtsum(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    //当期兑付/赎回金额折人民币
                    if(sub.getRedamt() != null) {
                        sub.setRedamtCny(ChbCcyConvertUtil.
                                exchangeMoney(sub.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                        DateUtil.parse(sub.getRaisingDate()), sub.getRedamt(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    //期末产品金额折人民币
                    if(sub.getEndamtsum() != null) {
                        sub.setEndamtsumCny(ChbCcyConvertUtil.
                                exchangeMoney(sub.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                        DateUtil.parse(sub.getRaisingDate()), sub.getEndamtsum(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                } else{
                    sub.setRaisingAmtsumCny(sub.getRaisingAmtsum().setScale(2, BigDecimal.ROUND_HALF_UP));
                    sub.setRedamtCny(sub.getRedamt().setScale(2, BigDecimal.ROUND_HALF_UP));
                    sub.setEndamtsumCny(sub.getEndamtsum().setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (resultMap.containsKey(encodeStrSub)){
                    // 已存在的数据进行汇总处理
                    ZG04PbcProdRaiseSub LastSub = resultMap.get(encodeStrSub);
                    // 当期申购金额计算累计值
                    BigDecimal raisingAmtsum = LastSub.getRaisingAmtsum() == null? BigDecimal.ZERO:LastSub.getRaisingAmtsum();
                    LastSub.setRaisingAmtsum(raisingAmtsum.add(sub.getRaisingAmtsum()==null? BigDecimal.ZERO:sub.getRaisingAmtsum()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    // 当期申购金额折人民币累计值
                    BigDecimal raisingAmtsumCny = LastSub.getRaisingAmtsumCny() == null? BigDecimal.ZERO:LastSub.getRaisingAmtsumCny();
                    LastSub.setRaisingAmtsumCny(raisingAmtsumCny.add(sub.getRaisingAmtsumCny()==null? BigDecimal.ZERO:sub.getRaisingAmtsumCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    // 当期申购份额累计值
                    BigDecimal raisingQty = LastSub.getRaisingQty() == null? BigDecimal.ZERO:LastSub.getRaisingQty();
                    LastSub.setRaisingQty(raisingQty.add(sub.getRaisingQty()==null? BigDecimal.ZERO:sub.getRaisingQty()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    // 当期兑付/赎回金额累计值
                    BigDecimal redamt = LastSub.getRedamt() == null? BigDecimal.ZERO:LastSub.getRedamt();
                    LastSub.setRedamt(redamt.add(sub.getRedamt()==null? BigDecimal.ZERO:sub.getRedamt()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    // 当期兑付/赎回金额折人民币累计值
                    BigDecimal redamtCny = LastSub.getRedamtCny() == null? BigDecimal.ZERO:LastSub.getRedamtCny();
                    LastSub.setRedamtCny(redamtCny.add(sub.getRedamtCny()==null? BigDecimal.ZERO:sub.getRedamtCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    // 当期兑付/赎回份额累计值
                    BigDecimal redqty = LastSub.getRedqty() == null? BigDecimal.ZERO:LastSub.getRedqty();
                    LastSub.setRedqty(redqty.add(sub.getRedqty()==null? BigDecimal.ZERO:sub.getRedqty()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    // 期末产品金额累计值
                    BigDecimal endamtsum = LastSub.getEndamtsum() == null? BigDecimal.ZERO:LastSub.getEndamtsum();
                    LastSub.setEndamtsum(endamtsum.add(sub.getEndamtsum()==null? BigDecimal.ZERO:sub.getEndamtsum()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    // 期末产品金额折人民币累计值
                    BigDecimal endamtsumCny = LastSub.getEndamtsumCny() == null? BigDecimal.ZERO:LastSub.getEndamtsumCny();
                    LastSub.setEndamtsumCny(endamtsumCny.add(sub.getEndamtsumCny()==null? BigDecimal.ZERO:sub.getEndamtsumCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    // 期末产品份额累计值
                    BigDecimal endqty = LastSub.getEndqty() == null? BigDecimal.ZERO:LastSub.getEndqty();
                    LastSub.setEndqty(endqty.add(sub.getEndqty()==null? BigDecimal.ZERO:sub.getEndqty()).setScale(2, BigDecimal.ROUND_HALF_UP));
                } else {
                    // 登记序列
                    sub.setRegistSeqno(IDGenerator.generate(36));
                    // 报送状态
                    sub.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
                    // 数据日期
                    sub.setStatusDate(executeDate);
                    // 报送截止日生成
                    // 当前批次报送时点集合
                    Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
                    List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG04资管产品存续期募集信息.getValue(),
                            DictCodeListExt2.报送数据状态_新建.getValue(),dateMap, DateUtil.getFirstDay(executeDate), SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);

                    if (!StringUtil.isEmptyList(dateList)) {
                        sub.setPbcSubmitDeadline(DateUtil.addDay(dateList.get(0),-1));
                        sub.setSubmitDeadline(DateUtil.addDay(dateList.get(1),-1));
                    }
                    // 创建人
                    sub.setCreateUser(CommonUtil.getUserName());
                    // 更新人
                    sub.setUpdateUser(CommonUtil.getUserName());
                    // 创建部门
                    sub.setCreateDept(CommonUtil.getDeptCode());
                    // 机构代码
                    if (StringUtil.isEmpty(sub.getOrgCode())){
                        sub.setOrgCode(CommonUtil.getOrgCode());
                    }
                    // 计入带入库列表
                    //属于0募集产品
                    if(opordList.contains(sub.getProdId())){
                        //地区代码
                        sub.setBuyPlace(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PBC_RAISING_DEFAULT_BUYPALCE));
                        //客户类型
                        sub.setCustomerType1(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PBC_RAISING_DEFAULT_CUSTOMERTYPE));
                        //币种代码
                        sub.setCcy(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PBC_RAISING_DEFAULT_CCY));
                        sub.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
                        sub.setRaisingDate(DateUtil.format(DateUtil.parse(sub.getRaisingDate()),"yyyyMM"));
                        sub.setProdValue(null);
                        sub.setUnitValueTotal(null);
                        //如果有业绩基础表现
                        if(sub.getProdEndYield()!=null&&sub.getProdEndYield().compareTo(BigDecimal.ZERO)!=0){
                            ZG04PbcProdRaiseSub subo=new ZG04PbcProdRaiseSub();
                            BeanUtil.copyPropertiesIgnoreNull(subo, sub);
                            subo.setRegistSeqno(IDGenerator.generate(36));
                            subo.setBuyPlace(null);
                            subo.setCustomerType1(null);
                            subo.setCcy(null);
                            subo.setRaisingAmtsum(null);
                            subo.setRaisingAmtsumCny(null);
                            subo.setRaisingQty(null);
                            subo.setRedamt(null);
                            subo.setRedamtCny(null);
                            subo.setRedqty(null);
                            subo.setEndamtsum(null);
                            subo.setEndamtsumCny(null);
                            subo.setEndqty(null);
                            subo.setProdValue(null);
                            subo.setProdValueCny(null);
                            subo.setUnitValueTotal(null);
                            subo.setUnitValueTotalCny(null);
                            insertList.add(subo);
                        }
                        /**填报指引需求变更  start*/
                        ZG04PbcProdRaiseSub sub02=new ZG04PbcProdRaiseSub();
                        BeanUtil.copyPropertiesIgnoreNull(sub02, sub);
                        sub02.setRegistSeqno(IDGenerator.generate(36));
                        sub02.setBuyPlace("000000");
                        ZG04PbcProdRaiseSub sub03=new ZG04PbcProdRaiseSub();
                        BeanUtil.copyPropertiesIgnoreNull(sub03, sub);
                        sub03.setRegistSeqno(IDGenerator.generate(36));
                        sub03.setCcy("BWB");
                        ZG04PbcProdRaiseSub sub04=new ZG04PbcProdRaiseSub();
                        BeanUtil.copyPropertiesIgnoreNull(sub04, sub);
                        sub04.setRegistSeqno(IDGenerator.generate(36));
                        sub04.setCcy("BWB");
                        sub04.setBuyPlace("000000");
                        if(!"BWB".equals(sub.getCcy())){
                            insertList.add(sub03);
                            insertList.add(sub04);
                        }
                        if(!"000000".equals(sub.getBuyPlace())){
                            insertList.add(sub02);
                        }
                        if(StringUtil.equals("1",sub.getOperateMode())||StringUtil.equals("3",sub.getOperateMode())){
                            ZG04PbcProdRaiseSub sub05=new ZG04PbcProdRaiseSub();
                            BeanUtil.copyPropertiesIgnoreNull(sub05, sub);
                            sub05.setRegistSeqno(IDGenerator.generate(36));
                            sub05.setCcy("BWB");
                            deamZG04PbcRaisSub(sub05,insertList);
                            ZG04PbcProdRaiseSub sub06=new ZG04PbcProdRaiseSub();
                            BeanUtil.copyPropertiesIgnoreNull(sub06, sub);
                            sub06.setRegistSeqno(IDGenerator.generate(36));
                            deamZG04PbcRaisSub(sub06,insertList);
                        }
                        /**填报指引需求变更  end*/
                        sub.setProdEndYield(null);
                        insertList.add(sub);
                    }else{
                        resultMap.put(encodeStrSub, sub);
                    }
                }
            }
        }


        // 要登记的数据
        List<ZG04PbcProdRaiseSub> prdCashRaiseListToReg = new ArrayList<>(resultMap.values());

        if(CollectionUtils.isNotEmpty(prdCashRaiseListToReg)){
            createBwbData(prdCashRaiseListToReg,rateMap,exchangeSource);
            // 数据的状态:默认新建
            String status = DictCodeListExt2.报送数据状态_新建.getValue();
            for (ZG04PbcProdRaiseSub sub : prdCashRaiseListToReg){
                if(sub.getProdValue()!=null&&BigDecimal.ZERO.compareTo(sub.getProdValue())==0){
                    sub.setProdValueCny(BigDecimal.ZERO);
                }
                if(sub.getUnitValueTotal()!=null&&BigDecimal.ZERO.compareTo(sub.getUnitValueTotal())==0){
                    sub.setUnitValueTotalCny(BigDecimal.ZERO);
                }
                String jsbankStr= SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_JSBK_IS_ROUND);
                // 数据状态
                sub.setStatus(status);

                // 修改开放式净值型产品期末净值、开放式净值型产品期末净值折人民币、开放式净值型产品期末累计净值、开放式净值型产品期末累计净值折人民币保留五位小数
                if(sub.getProdValue()!= null){
                    //江苏银行个性化保留小数位
                    if(StringUtil.isNotEmpty(jsbankStr)&&jsbankStr.equals("01")){
                        sub.setProdValue(sub.getProdValue().setScale(5, BigDecimal.ROUND_DOWN));
                    }else{
                        sub.setProdValue(sub.getProdValue().setScale(5, BigDecimal.ROUND_HALF_UP));
                    }
                }
                if(sub.getProdValueCny()!=null){
                    if(StringUtil.isNotEmpty(jsbankStr)&&jsbankStr.equals("01")){
                        sub.setProdValueCny(sub.getProdValueCny().setScale(5, BigDecimal.ROUND_DOWN));
                    }else{
                        sub.setProdValueCny(sub.getProdValueCny().setScale(5, BigDecimal.ROUND_HALF_UP));
                    }
                }
                if(sub.getUnitValueTotal()!=null){
                    if(StringUtil.isNotEmpty(jsbankStr)&&jsbankStr.equals("01")){
                        sub.setUnitValueTotal(sub.getUnitValueTotal().setScale(5, BigDecimal.ROUND_DOWN));
                    }else{
                        sub.setUnitValueTotal(sub.getUnitValueTotal().setScale(5, BigDecimal.ROUND_HALF_UP));
                    }
                }
                if(sub.getUnitValueTotalCny()!=null){
                    if(StringUtil.isNotEmpty(jsbankStr)&&jsbankStr.equals("01")){
                        sub.setUnitValueTotalCny(sub.getUnitValueTotalCny().setScale(5, BigDecimal.ROUND_DOWN));
                    }else{
                        sub.setUnitValueTotalCny(sub.getUnitValueTotalCny().setScale(5, BigDecimal.ROUND_HALF_UP));
                    }
                }
            }
            insertList.addAll(prdCashRaiseListToReg);


            //当期申购金额、当期申购份额、当期兑付/赎回金额、当期兑付/赎回份额、期末产品金额、期末产品份额字段同时为空或为0时，应该不生成pbc_prod_raising_sub表的数据 start 20200514
            Iterator<ZG04PbcProdRaiseSub> iterator = insertList.iterator();
            while(iterator.hasNext()){
                ZG04PbcProdRaiseSub ZG04PbcProdRaiseSub = iterator.next();

                //数据检查用字段，不入库
                if(map.containsKey(ZG04PbcProdRaiseSub.getProdId())){
                    ZG04PbcProdRaiseSub.setPbcPordIdOut(map.get(ZG04PbcProdRaiseSub.getProdId()));
                }else {
                    if(ZG04PbcProdRaiseSub.getPbcProdId().contains("INS")){
                        ZG04PbcProdRaiseSub.setPbcPordIdOut(ZG04PbcProdRaiseSub.getPbcProdId().substring(3));
                    }else {
                        ZG04PbcProdRaiseSub.setPbcPordIdOut(ZG04PbcProdRaiseSub.getPbcProdId());
                    }
                }

                //当期申购金额
                BigDecimal raisingAmtsum = ZG04PbcProdRaiseSub.getRaisingAmtsum();
                //当期申购份额
                BigDecimal raisingQty = ZG04PbcProdRaiseSub.getRaisingQty();
                //当期兑付/赎回金额
                BigDecimal redamt = ZG04PbcProdRaiseSub.getRedamt();
                //当期兑付/赎回份额
                BigDecimal redqty = ZG04PbcProdRaiseSub.getRedqty();
                //期末产品金额
                BigDecimal endamtsum = ZG04PbcProdRaiseSub.getEndamtsum();
                //期末产品份额
                BigDecimal endqty = ZG04PbcProdRaiseSub.getEndqty();
                BigDecimal prodValue = ZG04PbcProdRaiseSub.getProdValue();
                BigDecimal unitValueTotal = ZG04PbcProdRaiseSub.getUnitValueTotal();

                BigDecimal prodEndYield = ZG04PbcProdRaiseSub.getProdEndYield();

                if(raisingAmtsum == null && raisingQty == null && redamt == null && redqty == null && endamtsum == null && endqty == null && prodValue == null && unitValueTotal == null && prodEndYield == null){
                    iterator.remove();
                }
            }
        }
//        // 数据入库
//        logger.info("采集平台资管产品存续期募集信息：报表生成校验 开始----------");
//        List<ZG04PbcProdRaiseSub> listData = pbcDataCheckUtil.getTrueData(insertList, null, DictCodeListExt2.采集平台资管产品存续期募集信息.getValue(), true, "1", ZG04PbcProdRaiseSub.class);
//        logger.info("采集平台资管产品存续期募集信息：报表生成校验 结束----------");
//        // 先删后插
//		intoDatabase(listData,deleteList);
	}

	public void deamZG04PbcRaisSub( ZG04PbcProdRaiseSub subo,List<ZG04PbcProdRaiseSub> insertList){
        subo.setBuyPlace(null);
        subo.setCustomerType1(null);
        subo.setRaisingAmtsum(null);
        subo.setRaisingAmtsumCny(null);
        subo.setRaisingQty(null);
        subo.setRedamt(null);
        subo.setRedamtCny(null);
        subo.setRedqty(null);
        subo.setEndamtsum(null);
        subo.setEndamtsumCny(null);
        subo.setEndqty(null);
        subo.setProdValue(BigDecimal.ONE);
        subo.setProdValueCny(BigDecimal.ONE);
        subo.setUnitValueTotal(BigDecimal.ONE);
        subo.setUnitValueTotalCny(BigDecimal.ONE);
        insertList.add(subo);
    }

	private void createBwbData(List<ZG04PbcProdRaiseSub> list, Map<String, BigDecimal> rateMap, String exchangeSource){
        List<ZG04PbcProdRaiseSub> returnList = new ArrayList<>();
        if(StringUtil.isNotEmpty(list)){
            Map<String, ZG04PbcProdRaiseSub> area000000returnMap = new HashMap<>();
            Map<String, ZG04PbcProdRaiseSub> bwbarea000000returnMap = new HashMap<>();

            ZG04PbcProdRaiseSub sub1 = new ZG04PbcProdRaiseSub();
            ZG04PbcProdRaiseSub subi = new ZG04PbcProdRaiseSub();
            ZG04PbcProdRaiseSub sub2 = new ZG04PbcProdRaiseSub();

            ZG04PbcProdRaiseSub zg04PbcProdRaiseSub = zg04PbcProdRaiseSubMapper.getTypeByProdId(list.get(0).getProdId());
            list.stream().forEach(i->{
                if(StringUtil.isNotEmpty(zg04PbcProdRaiseSub)){
                    //生成一笔币种为BWB
                    ZG04PbcProdRaiseSub bwbSub = new ZG04PbcProdRaiseSub();
                    BeanUtil.copyPropertiesIgnoreNull(bwbSub, i);
                    bwbSub.setRegistSeqno(IDGenerator.generate(36));
                    //if(!DictCodeListExt1.币种_中债_人民币.getValue().equals(i.getCcy())){
                        //当前申购金额
                        if (i.getRaisingAmtsum() != null){
                            bwbSub.setRaisingAmtsum(ChbCcyConvertUtil.
                                    exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                            DateUtil.parse(i.getRaisingDate()), i.getRaisingAmtsum(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        //当期申购金额折人民币
                        if (i.getRaisingAmtsum() != null){
                            bwbSub.setRaisingAmtsumCny(ChbCcyConvertUtil.
                                    exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                            DateUtil.parse(i.getRaisingDate()), i.getRaisingAmtsum(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        //当期申购份额
                        if (i.getRaisingQty() != null){
                            bwbSub.setRaisingQty(ChbCcyConvertUtil.
                                    exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                            DateUtil.parse(i.getRaisingDate()), i.getRaisingQty(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        //当期兑付/赎回金额
                        if(i.getRedamt() != null) {
                            bwbSub.setRedamt(ChbCcyConvertUtil.
                                    exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                            DateUtil.parse(i.getRaisingDate()), i.getRedamt(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        //当期兑付/赎回金额折人民币
                        if(i.getRedamt() != null) {
                            bwbSub.setRedamtCny(ChbCcyConvertUtil.
                                    exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                            DateUtil.parse(i.getRaisingDate()), i.getRedamt(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        //当期兑付/赎回份额
                        if(i.getRedqty() != null) {
                            bwbSub.setRedqty(ChbCcyConvertUtil.
                                    exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                            DateUtil.parse(i.getRaisingDate()), i.getRedqty(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        //期末产品金额
                        if(i.getEndamtsum() != null) {
                            bwbSub.setEndamtsum(ChbCcyConvertUtil.
                                    exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                            DateUtil.parse(i.getRaisingDate()), i.getEndamtsum(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        //期末产品金额折人民币
                        if(i.getEndamtsum() != null) {
                            bwbSub.setEndamtsumCny(ChbCcyConvertUtil.
                                    exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                            DateUtil.parse(i.getRaisingDate()), i.getEndamtsum(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        //期末产品份额
                        if(i.getEndqty() != null) {
                            bwbSub.setEndqty(ChbCcyConvertUtil.
                                    exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                            DateUtil.parse(i.getRaisingDate()), i.getEndqty(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        bwbSub.setProdValue(null);
                        bwbSub.setProdValueCny(null);
                        bwbSub.setUnitValueTotal(null);
                        bwbSub.setUnitValueTotalCny(null);
                        bwbSub.setProdEndYield(null);
                    //}
                    bwbSub.setCcy(DictCodeListExt1.币种_人行_本位币.getValue());
                    bwbSub.setRaisingDate(DateUtil.format(DateUtil.parse(i.getRaisingDate()),"yyyyMM"));
                    returnList.add(bwbSub);
                    /**指引填报变更需求修改  start */
                    ZG04PbcProdRaiseSub subbb = new ZG04PbcProdRaiseSub();
                    BeanUtil.copyPropertiesIgnoreNull(subbb, bwbSub);
                    subbb.setRegistSeqno(IDGenerator.generate(36));
                    subbb.setBuyPlace("000000");
                    subbb.setProdValue(null);
                    subbb.setProdValueCny(null);
                    subbb.setUnitValueTotal(null);
                    subbb.setUnitValueTotalCny(null);
                    subbb.setProdEndYield(null);
                    String key1 = bwbSub.getCustomerType1() + bwbSub.getCcy();

                    if(bwbarea000000returnMap.get(key1) == null){
                        bwbarea000000returnMap.put(key1,subbb);
                    }else{
                        if(bwbarea000000returnMap.get(key1).getRaisingAmtsum() != null && subbb.getRaisingAmtsum() != null){
                            bwbarea000000returnMap.get(key1).setRaisingAmtsum(bwbarea000000returnMap.get(key1).getRaisingAmtsum().add(subbb.getRaisingAmtsum()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(bwbarea000000returnMap.get(key1).getRaisingAmtsum() == null && subbb.getRaisingAmtsum() != null){
                            bwbarea000000returnMap.get(key1).setRaisingAmtsum(subbb.getRaisingAmtsum().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(bwbarea000000returnMap.get(key1).getRaisingAmtsumCny() != null && subbb.getRaisingAmtsumCny() != null){
                            bwbarea000000returnMap.get(key1).setRaisingAmtsumCny(bwbarea000000returnMap.get(key1).getRaisingAmtsumCny().add(subbb.getRaisingAmtsumCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(bwbarea000000returnMap.get(key1).getRaisingAmtsumCny() == null && subbb.getRaisingAmtsumCny() != null){
                            bwbarea000000returnMap.get(key1).setRaisingAmtsumCny(subbb.getRaisingAmtsumCny().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(bwbarea000000returnMap.get(key1).getRaisingQty() != null && subbb.getRaisingQty() != null){
                            bwbarea000000returnMap.get(key1).setRaisingQty(bwbarea000000returnMap.get(key1).getRaisingQty().add(subbb.getRaisingQty()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(bwbarea000000returnMap.get(key1).getRaisingQty() == null && subbb.getRaisingQty() != null){
                            bwbarea000000returnMap.get(key1).setRaisingQty(subbb.getRaisingQty().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(bwbarea000000returnMap.get(key1).getRedamt() != null && subbb.getRedamt() != null){
                            bwbarea000000returnMap.get(key1).setRedamt(bwbarea000000returnMap.get(key1).getRedamt().add(subbb.getRedamt()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(bwbarea000000returnMap.get(key1).getRedamt() == null && subbb.getRedamt() != null){
                            bwbarea000000returnMap.get(key1).setRedamt(subbb.getRedamt().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(bwbarea000000returnMap.get(key1).getRedamtCny() != null && subbb.getRedamtCny() != null){
                            bwbarea000000returnMap.get(key1).setRedamtCny(bwbarea000000returnMap.get(key1).getRedamtCny().add(subbb.getRedamtCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(bwbarea000000returnMap.get(key1).getRedamtCny() == null && subbb.getRedamtCny() != null){
                            bwbarea000000returnMap.get(key1).setRedamtCny(subbb.getRedamtCny().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(bwbarea000000returnMap.get(key1).getRedqty() != null && subbb.getRedqty() != null){
                            bwbarea000000returnMap.get(key1).setRedqty(bwbarea000000returnMap.get(key1).getRedqty().add(subbb.getRedqty()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(bwbarea000000returnMap.get(key1).getRedqty() == null && subbb.getRedqty() != null){
                            bwbarea000000returnMap.get(key1).setRedqty(subbb.getRedqty().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(bwbarea000000returnMap.get(key1).getEndamtsum() != null && subbb.getEndamtsum() != null){
                            bwbarea000000returnMap.get(key1).setEndamtsum(bwbarea000000returnMap.get(key1).getEndamtsum().add(subbb.getEndamtsum()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(bwbarea000000returnMap.get(key1).getEndamtsum() == null && subbb.getEndamtsum() != null){
                            bwbarea000000returnMap.get(key1).setEndamtsum(subbb.getEndamtsum().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(bwbarea000000returnMap.get(key1).getEndamtsumCny() != null && subbb.getEndamtsumCny() != null){
                            bwbarea000000returnMap.get(key1).setEndamtsumCny(bwbarea000000returnMap.get(key1).getEndamtsumCny().add(subbb.getEndamtsumCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(bwbarea000000returnMap.get(key1).getEndamtsumCny() == null && subbb.getEndamtsumCny() != null){
                            bwbarea000000returnMap.get(key1).setEndamtsumCny(subbb.getEndamtsumCny().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(bwbarea000000returnMap.get(key1).getEndqty() != null && subbb.getEndqty() != null){
                            bwbarea000000returnMap.get(key1).setEndqty(bwbarea000000returnMap.get(key1).getEndqty().add(subbb.getEndqty()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(bwbarea000000returnMap.get(key1).getEndqty() == null && subbb.getEndqty() != null){
                            bwbarea000000returnMap.get(key1).setEndqty(subbb.getEndqty().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                    }
                    /**指引填报变更需求修改  end */

                    //生成地区为000000的数据
                    ZG04PbcProdRaiseSub sub = new ZG04PbcProdRaiseSub();
                    BeanUtil.copyPropertiesIgnoreNull(sub, i);
                    sub.setRegistSeqno(IDGenerator.generate(36));
                    sub.setBuyPlace("000000");
                    sub.setProdValue(null);
                    sub.setProdValueCny(null);
                    sub.setUnitValueTotal(null);
                    sub.setUnitValueTotalCny(null);
                    sub.setProdEndYield(null);
                    sub.setRaisingDate(DateUtil.format(DateUtil.parse(i.getRaisingDate()),"yyyyMM"));
                    String key = sub.getCustomerType1() + sub.getCcy();
                    if(area000000returnMap.get(key) == null){
                        area000000returnMap.put(key,sub);
                    }else{
                        if(area000000returnMap.get(key).getRaisingAmtsum() != null && sub.getRaisingAmtsum() != null){
                            area000000returnMap.get(key).setRaisingAmtsum(area000000returnMap.get(key).getRaisingAmtsum().add(sub.getRaisingAmtsum()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(area000000returnMap.get(key).getRaisingAmtsum() == null && sub.getRaisingAmtsum() != null){
                            area000000returnMap.get(key).setRaisingAmtsum(sub.getRaisingAmtsum().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(area000000returnMap.get(key).getRaisingAmtsumCny() != null && sub.getRaisingAmtsumCny() != null){
                            area000000returnMap.get(key).setRaisingAmtsumCny(area000000returnMap.get(key).getRaisingAmtsumCny().add(sub.getRaisingAmtsumCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(area000000returnMap.get(key).getRaisingAmtsumCny() == null && sub.getRaisingAmtsumCny() != null){
                            area000000returnMap.get(key).setRaisingAmtsumCny(sub.getRaisingAmtsumCny().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(area000000returnMap.get(key).getRaisingQty() != null && sub.getRaisingQty() != null){
                            area000000returnMap.get(key).setRaisingQty(area000000returnMap.get(key).getRaisingQty().add(sub.getRaisingQty()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(area000000returnMap.get(key).getRaisingQty() == null && sub.getRaisingQty() != null){
                            area000000returnMap.get(key).setRaisingQty(sub.getRaisingQty().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(area000000returnMap.get(key).getRedamt() != null && sub.getRedamt() != null){
                            area000000returnMap.get(key).setRedamt(area000000returnMap.get(key).getRedamt().add(sub.getRedamt()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(area000000returnMap.get(key).getRedamt() == null && sub.getRedamt() != null){
                            area000000returnMap.get(key).setRedamt(sub.getRedamt().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(area000000returnMap.get(key).getRedamtCny() != null && sub.getRedamtCny() != null){
                            area000000returnMap.get(key).setRedamtCny(area000000returnMap.get(key).getRedamtCny().add(sub.getRedamtCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(area000000returnMap.get(key).getRedamtCny() == null && sub.getRedamtCny() != null){
                            area000000returnMap.get(key).setRedamtCny(sub.getRedamtCny().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(area000000returnMap.get(key).getRedqty() != null && sub.getRedqty() != null){
                            area000000returnMap.get(key).setRedqty(area000000returnMap.get(key).getRedqty().add(sub.getRedqty()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(area000000returnMap.get(key).getRedqty() == null && sub.getRedqty() != null){
                            area000000returnMap.get(key).setRedqty(sub.getRedqty().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(area000000returnMap.get(key).getEndamtsum() != null && sub.getEndamtsum() != null){
                            area000000returnMap.get(key).setEndamtsum(area000000returnMap.get(key).getEndamtsum().add(sub.getEndamtsum()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(area000000returnMap.get(key).getEndamtsum() == null && sub.getEndamtsum() != null){
                            area000000returnMap.get(key).setEndamtsum(sub.getEndamtsum().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(area000000returnMap.get(key).getEndamtsumCny() != null && sub.getEndamtsumCny() != null){
                            area000000returnMap.get(key).setEndamtsumCny(area000000returnMap.get(key).getEndamtsumCny().add(sub.getEndamtsumCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(area000000returnMap.get(key).getEndamtsumCny() == null && sub.getEndamtsumCny() != null){
                            area000000returnMap.get(key).setEndamtsumCny(sub.getEndamtsumCny().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        if(area000000returnMap.get(key).getEndqty() != null && sub.getEndqty() != null){
                            area000000returnMap.get(key).setEndqty(area000000returnMap.get(key).getEndqty().add(sub.getEndqty()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }else if(area000000returnMap.get(key).getEndqty() == null && sub.getEndqty() != null){
                            area000000returnMap.get(key).setEndqty(sub.getEndqty().setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                    }

                    if("01".equals(zg04PbcProdRaiseSub.getCashManaCate())) {
                        //现管类产品  需要生成一条仅有产品产品期末业绩表现的数据
                        if(StringUtil.isEmpty(sub1.getProdId())){
                            BeanUtil.copyPropertiesIgnoreNull(sub1, i);
                            sub1.setRegistSeqno(IDGenerator.generate(36));
                            sub1.setBuyPlace(null);
                            sub1.setCustomerType1(null);
                            sub1.setCcy(null);
                            sub1.setRaisingAmtsum(null);
                            sub1.setRaisingAmtsumCny(null);
                            sub1.setRaisingQty(null);
                            sub1.setRedamt(null);
                            sub1.setRedamtCny(null);
                            sub1.setRedqty(null);
                            sub1.setEndamtsum(null);
                            sub1.setEndamtsumCny(null);
                            sub1.setEndqty(null);
                            sub1.setProdValue(null);
                            sub1.setProdValueCny(null);
                            sub1.setUnitValueTotal(null);
                            sub1.setUnitValueTotalCny(null);
                            if(!StringUtil.isNotEmpty(i.getProdEndYield()) && StringUtil.isNotEmpty(i.getUnitValue())){
                                // 计算产品期末业绩表现
                                BigDecimal prodEndYield = BigDecimal.ZERO;
                                BigDecimal basic = new BigDecimal(365);
                                if (DictCodeList.计息基础_A_360.getValue().equals(i.getBasic())) {
                                    basic = new BigDecimal(360);
                                }
                                prodEndYield = i.getUnitValue().divide(new BigDecimal(1),7,
                                        BigDecimal.ROUND_HALF_UP).multiply(basic).divide(
                                        new BigDecimal(DateUtil.getBetweenDayNumber(i.getVdate(),DateUtil.parse(i.getRaisingDate()))).add(new BigDecimal(1)),7,
                                        BigDecimal.ROUND_HALF_UP);
                                sub1.setProdEndYield(prodEndYield);
                            }
                            if(sub1.getProdEndYield()!=null){
                                sub1.setProdEndYield(sub1.getProdEndYield().setScale(5,BigDecimal.ROUND_HALF_UP));
                            }
                            sub1.setRaisingDate(DateUtil.format(DateUtil.parse(i.getRaisingDate()),"yyyyMM"));
                            returnList.add(sub1);
                        }
                    }
                    if( "01".equals(zg04PbcProdRaiseSub.getOperateMode())||
                            "03".equals(zg04PbcProdRaiseSub.getOperateMode()) ){
                        //封闭式净值型、开放式净值型产品 报送2条原币和BWB的净值
                        if(StringUtil.isEmpty(subi.getProdId())){
                            BeanUtil.copyPropertiesIgnoreNull(subi, i);
                            subi.setRegistSeqno(IDGenerator.generate(36));
                            subi.setBuyPlace(null);
                            subi.setCustomerType1(null);
                            subi.setRaisingAmtsum(null);
                            subi.setRaisingAmtsumCny(null);
                            subi.setRaisingQty(null);
                            subi.setRedamt(null);
                            subi.setRedamtCny(null);
                            subi.setRedqty(null);
                            subi.setEndamtsum(null);
                            subi.setEndamtsumCny(null);
                            subi.setEndqty(null);

                            //净值型产品期末净值折人民币
                            if(i.getProdValue() != null) {
                                /***终止数据净值为0.折人民币也应该为0**/
                                if(BigDecimal.ZERO.compareTo(i.getProdValue())!=0){
                                    subi.setProdValueCny(ChbCcyConvertUtil.
                                            exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                                    DateUtil.parse(i.getRaisingDate()), i.getProdValue(), rateMap, exchangeSource));
                                }else{
                                    subi.setProdValueCny(BigDecimal.ZERO);
                                }
                            }

                            //净值型产品期末累计净值折人民币
                            if (i.getUnitValueTotal() != null){
                                /***终止数据净值为0.折人民币也应该为0**/
                                if(BigDecimal.ZERO.compareTo(i.getUnitValueTotal())!=0){
                                subi.setUnitValueTotalCny(ChbCcyConvertUtil.
                                        exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                                DateUtil.parse(i.getRaisingDate()), i.getUnitValueTotal(), rateMap, exchangeSource));
                                }else{
                                    subi.setUnitValueTotalCny(BigDecimal.ZERO);
                                }
                            }
                            subi.setProdEndYield(null);
                            subi.setRaisingDate(DateUtil.format(DateUtil.parse(i.getRaisingDate()),"yyyyMM"));
                            returnList.add(subi);
                        }
                        if(StringUtil.isEmpty(sub2.getProdId())){
                            BeanUtil.copyPropertiesIgnoreNull(sub2, i);
                            sub2.setRegistSeqno(IDGenerator.generate(36));
                            sub2.setBuyPlace(null);
                            sub2.setCustomerType1(null);
                            sub2.setRaisingAmtsum(null);
                            sub2.setRaisingAmtsumCny(null);
                            sub2.setRaisingQty(null);
                            sub2.setRedamt(null);
                            sub2.setRedamtCny(null);
                            sub2.setRedqty(null);
                            sub2.setEndamtsum(null);
                            sub2.setEndamtsumCny(null);
                            sub2.setEndqty(null);
                            sub2.setCcy(DictCodeListExt1.币种_人行_本位币.getValue());
                            sub2.setProdEndYield(null);
                            //if(!DictCodeListExt1.币种_中债_人民币.getValue().equals(i.getCcy())){

                            //净值型产品期末净值折人民币
                            if(i.getProdValue() != null) {
                                /***终止数据净值为0.折人民币也应该为0**/
                                if(BigDecimal.ZERO.compareTo(i.getProdValue())!=0){
                                    sub2.setProdValueCny(ChbCcyConvertUtil.
                                            exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                                    DateUtil.parse(i.getRaisingDate()), i.getProdValue(), rateMap, exchangeSource));
                                }else{
                                    subi.setProdValueCny(BigDecimal.ZERO);
                                }
                            }

                            //净值型产品期末累计净值折人民币
                            if (i.getUnitValueTotal() != null){
                                if(BigDecimal.ZERO.compareTo(i.getUnitValueTotal())!=0){
                                sub2.setUnitValueTotalCny(ChbCcyConvertUtil.
                                        exchangeMoney(i.getCcy(), DictCodeListExt1.币种_中债_人民币.getValue(),
                                                DateUtil.parse(i.getRaisingDate()), i.getUnitValueTotal(), rateMap, exchangeSource));
                                }else{
                                    subi.setUnitValueTotalCny(BigDecimal.ZERO);
                                }
                            }
                            //}
                            sub2.setRaisingDate(DateUtil.format(DateUtil.parse(i.getRaisingDate()),"yyyyMM"));
                            returnList.add(sub2);
                        }
                    }
                }
                i.setProdValue(null);
                i.setProdValueCny(null);
                i.setUnitValueTotal(null);
                i.setUnitValueTotalCny(null);
                i.setProdEndYield(null);
                i.setRaisingDate(DateUtil.format(DateUtil.parse(i.getRaisingDate()),"yyyyMM"));
            });

            for(String key:area000000returnMap.keySet()){
                returnList.add(area000000returnMap.get(key));
            }
            for(String key:bwbarea000000returnMap.keySet()){
                returnList.add(bwbarea000000returnMap.get(key));
            }
        }
        list.addAll(returnList);
    }
	

	/**
	 * 功能:批量入库
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：dingfeng 2019年6月11日
	 * @param insertlist
	 * @param deletelist 
	 * void
	 */
	private void intoDatabase(List<ZG04PbcProdRaiseSub> insertlist, List<ZG04PbcProdRaiseSub> deletelist) {
		if (CollectionUtils.isNotEmpty(deletelist)) {
		    // 插入前先删除未报送的登记数据
		    logger.debug("人行产品资金募集信息删除未报送数据：删除开始----------");
		    // 数据批量一次删除条数,如果参数设置过大则强制定位50
            int batchDeleteCount = reportSelectOrDeleteCount;
            // 每批最后一个的下标
            int batchDeleteLastIndex = batchDeleteCount;            
            for(int index = 0; index < deletelist.size();){
                if(batchDeleteLastIndex > deletelist.size()-1){
                    batchDeleteLastIndex = deletelist.size();
                    zg04PbcProdRaiseSubMapper.deleteBatch(deletelist.subList(index, batchDeleteLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                    zg04PbcProdRaiseSubMapper.deleteBatch(deletelist.subList(index, batchDeleteLastIndex));
                    index = batchDeleteLastIndex;//设置下一批下标
                    batchDeleteLastIndex = index + batchDeleteCount;                     
                }
            }
            logger.debug("人行产品资金募集信息删除未报送数据：删除结束----------");
        }
        if (CollectionUtils.isNotEmpty(insertlist)){
            logger.debug("人行产品资金募集信息插入数据：插入开始----------");
            int count = 0;
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try{
                ZG04PbcProdRaiseSubMapper batchMapper =  session.getMapper(ZG04PbcProdRaiseSubMapper.class);
                for(ZG04PbcProdRaiseSub dto : insertlist){
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
                logger.error(e.getMessage(),e);
                session.rollback();
                throw new BusinessException(e);
            } finally {
                session.close();
            }
            logger.debug("人行产品资金募集信息插入数据：插入结束----------");
        }

	}

    /**
     * 功能:判断俩个登记数据是否一致
     * @since 1.7.0
     * @version 1.7.0
     * @author：dingfeng 2019年6月17日
     * @param prdRaiseMap
     * @param prdRaiseOrgMap
     * @return 
     * boolean
     */
    public boolean compareData(Map<String, ZG04PbcProdRaiseSub> prdRaiseMap, Map<String, ZG04PbcProdRaiseSub> prdRaiseOrgMap){
        // 俩者只要有一个为空则认为不相等
        if (prdRaiseMap == null || prdRaiseOrgMap == null){
            return false;
        }
        // 先判断生成的数据大小是否一致
        if (prdRaiseMap.keySet().size() != prdRaiseOrgMap.keySet().size()){
            return false;
        }
        // 判断数据是否相等
        for (String key : prdRaiseMap.keySet()){
            if (!prdRaiseOrgMap.containsKey(key)){
                return false;
            } 
            ZG04PbcProdRaiseSub compare1 = genDto(prdRaiseMap.get(key));
            ZG04PbcProdRaiseSub compare2 = genDto(prdRaiseOrgMap.get(key));
            if(!JSON.toJSONString(compare1).equals(JSON.toJSONString(compare2))){
                return false;
            }
        }
        return true;
    }

    /**
     * 功能:根据源数据获取比较的dto
     * @since 1.7.0
     * @version 1.7.0
     * @author：dingfeng 2019年6月17日
     * @param orgDto
     * @return 
     * ZG04PbcProdRaiseSub
     */
    private ZG04PbcProdRaiseSub genDto(ZG04PbcProdRaiseSub orgDto) {
        ZG04PbcProdRaiseSub sub = new ZG04PbcProdRaiseSub();
        sub.setRaisingDate(orgDto.getRaisingDate());
        sub.setPbcProdId(orgDto.getPbcProdId());
        sub.setBuyPlace(orgDto.getBuyPlace());
        sub.setCustomerType1(orgDto.getCustomerType1());
        sub.setCcy(orgDto.getCcy());
        sub.setRaisingAmtsum((orgDto.getRaisingAmtsum()==null? BigDecimal.ZERO:orgDto.getRaisingAmtsum()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setRaisingAmtsumCny((orgDto.getRaisingAmtsumCny()==null? BigDecimal.ZERO:orgDto.getRaisingAmtsumCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setRaisingQty((orgDto.getRaisingQty()==null? BigDecimal.ZERO:orgDto.getRaisingQty()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setRedamt((orgDto.getRedamt()==null? BigDecimal.ZERO:orgDto.getRedamt()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setRedamtCny((orgDto.getRedamtCny()==null? BigDecimal.ZERO:orgDto.getRedamtCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setRedqty((orgDto.getRedqty()==null? BigDecimal.ZERO:orgDto.getRedqty()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setEndamtsum((orgDto.getEndamtsum()==null? BigDecimal.ZERO:orgDto.getEndamtsum()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setEndamtsumCny((orgDto.getEndamtsumCny()==null? BigDecimal.ZERO:orgDto.getEndamtsumCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setEndqty((orgDto.getEndqty()==null? BigDecimal.ZERO:orgDto.getEndqty()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setProdValue((orgDto.getProdValue()==null? BigDecimal.ZERO:orgDto.getProdValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setProdValueCny((orgDto.getProdValueCny()==null? BigDecimal.ZERO:orgDto.getProdValueCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setUnitValueTotal((orgDto.getUnitValueTotal()==null? BigDecimal.ZERO:orgDto.getUnitValueTotal()).setScale(2, BigDecimal.ROUND_HALF_UP));
        sub.setUnitValueTotalCny((orgDto.getUnitValueTotalCny()==null? BigDecimal.ZERO:orgDto.getUnitValueTotalCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
        //sub.setProdHighYield((orgDto.getUnitValueTotalCny()==null?BigDecimal.ZERO:orgDto.getUnitValueTotalCny()).setScale(2, BigDecimal.ROUND_HALF_UP));
        //sub.setProdLowYield((orgDto.getProdLowYield()==null?BigDecimal.ZERO:orgDto.getProdLowYield()).setScale(2, BigDecimal.ROUND_HALF_UP));
        return sub;
    }


    /**
     * 根据PBC_IS_SHOW_ZERO参数处理字段为0或者空用于校验 add by wangyaning 20230111
     * @param insertlist
     */
    private void dealZeroNumber(List<ZG04PbcProdRaiseSub> insertlist) {
        String pbcIsShowZero = SvrDataUtil.getSysParam("PBC_IS_SHOW_ZERO");;
        for(ZG04PbcProdRaiseSub tempDto: insertlist) {
            //处理为空
            if (StringUtil.equals(pbcIsShowZero,"01")) {
                //当是净值记录或者产品业绩表现记录的时候
                if(StringUtil.isEmpty(tempDto.getBuyPlace())&&
                        StringUtil.isEmpty(tempDto.getCustomerType1())){
                    continue;
                }
                //当期申购金额
                if (!ObjectUtils.isEmpty(tempDto.getRaisingAmtsum())) {
                    if(tempDto.getRaisingAmtsum().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setRaisingAmtsum(null);
                    }
                }
                //当期申购金额折人民币
                if (!ObjectUtils.isEmpty(tempDto.getRaisingAmtsumCny())) {
                    if(tempDto.getRaisingAmtsumCny().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setRaisingAmtsumCny(null);
                    }
                }
                //当期申购份额
                if (!ObjectUtils.isEmpty(tempDto.getRaisingQty())) {
                    if(tempDto.getRaisingQty().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setRaisingQty(null);
                    }
                }
                //当期兑付/赎回份额
                if (!ObjectUtils.isEmpty(tempDto.getRedqty())) {
                    if(tempDto.getRedqty().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setRedqty(null);
                    }
                }
                //当期兑付/赎回金额
                if (!ObjectUtils.isEmpty(tempDto.getRedamt())) {
                    if(tempDto.getRedamt().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setRedamt(null);
                    }
                }
                //当期兑付/赎回金额折人民币
                if (!ObjectUtils.isEmpty(tempDto.getRedamtCny())) {
                    if(tempDto.getRedamtCny().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setRedamtCny(null);
                    }
                }
                //期末产品金额
                if (!ObjectUtils.isEmpty(tempDto.getEndamtsum())) {
                    if(tempDto.getEndamtsum().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setEndamtsum(null);
                    }
                }
                //期末产品金额折人民币
                if (!ObjectUtils.isEmpty(tempDto.getEndamtsumCny())) {
                    if(tempDto.getEndamtsumCny().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setEndamtsumCny(null);
                    }
                }
                //期末产品份额
                if (!ObjectUtils.isEmpty(tempDto.getEndqty())) {
                    if(tempDto.getEndqty().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setEndqty(null);
                    }
                }

            } else if (StringUtil.equals(pbcIsShowZero,"02")) {
                //当是净值记录或者产品业绩表现记录的时候
                if(StringUtil.isEmpty(tempDto.getBuyPlace())&&
                        StringUtil.isEmpty(tempDto.getCustomerType1())){
                    continue;
                }
                //处理成0
                //当期申购金额
                if (ObjectUtils.isEmpty(tempDto.getRaisingAmtsum())) {
                    tempDto.setRaisingAmtsum(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //当期申购金额折人民币
                if (ObjectUtils.isEmpty(tempDto.getRaisingAmtsumCny())) {
                    tempDto.setRaisingAmtsumCny(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //当期申购份额
                if (ObjectUtils.isEmpty(tempDto.getRaisingQty())) {
                    tempDto.setRaisingQty(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //当期兑付/赎回份额
                if (ObjectUtils.isEmpty(tempDto.getRedqty())) {
                    tempDto.setRedqty(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //当期兑付/赎回金额
                if (ObjectUtils.isEmpty(tempDto.getRedamt())) {
                    tempDto.setRedamt(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //当期兑付/赎回金额折人民币
                if (ObjectUtils.isEmpty(tempDto.getRedamtCny())) {
                    tempDto.setRedamtCny(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //期末产品金额
                if (ObjectUtils.isEmpty(tempDto.getEndamtsum())) {
                    tempDto.setEndamtsum(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //期末产品金额折人民币
                if (ObjectUtils.isEmpty(tempDto.getEndamtsumCny())) {
                    tempDto.setEndamtsumCny(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //期末产品份额
                if (ObjectUtils.isEmpty(tempDto.getEndqty())) {
                    tempDto.setEndqty(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
    }
}