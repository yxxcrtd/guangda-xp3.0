package com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.service;

import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.ChbLoanDetailInfo;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.svreport.loaninfomanagement.dto.OperationalLoanInfo001Dto;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao.ZG01PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dao.ZG07PbcColLoanDetailSub001Mapper;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * ZG07 除回购和拆解外贷款明细登记
 *
 * @author yangxianjiu
 * @date 2022/11/10
 */
@Service
public class ZG07PbcColLoanDetailSubBatchService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private ZG07PbcColLoanDetailSub001Mapper pbcColLoanDetailSub001Mapper;

    @Autowired
    private ZG01PbcPrdBaseinfoSubMapper zg01PbcPrdBaseinfoSubMapper;

    @Autowired
    private PbcDataCheckUtil pbcDataCheckUtil;

    public void generateData(Date batchTime) {
        //1.判断跑批日期，如果不是1号，批处理不执行。
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(batchTime);
        if (calendar.get(Calendar.DATE) != 1) {  //每月1日生成上月的数据
            logger.info("每月1日生成上月的数据！");
            return;
        }
        // 上月末
        Date addDay = DateUtil.addDay(batchTime, -1);
        // 格式 yyyyMM
        String termId = DateUtil.format(addDay, DateUtil.MONTH_OTHER_FORMAT);
        String executeDate = DateUtil.format(addDay, DateUtil.DEFAULT_DATE_FORMAT_EN);
        String batchDate = DateUtil.format(batchTime, DateUtil.DEFAULT_DATE_FORMAT_EN);
        if (pbcColLoanDetailSub001Mapper.selectCountByTermId(termId) > 0) {
            logger.info("当月存在已报送数据，批处理不再继续执行！");
            return;
        }
        //删除当期所有数据
        pbcColLoanDetailSub001Mapper.deleteByTermId(termId);

        //查询人行产品资产池的数据
        List<ZG01PbcPrdBaseinfoSub> list=zg01PbcPrdBaseinfoSubMapper.selectAllPbcProdId();
        Map<String,String> map= list.stream().collect(Collectors.toMap(ZG01PbcPrdBaseinfoSub::getProdId,ZG01PbcPrdBaseinfoSub::getPbcProdId ));


        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        // 生成 报送截止日
        List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG07除回购和拆解外贷款明细登记.getValue(),
                DictCodeListExt2.报送数据状态_新建.getValue(), dateMap,
                addDay, SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
        String exchangeSource = CacheUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
        logger.info("ZG07 除回购和拆解外贷款明细登记 获取资产头寸开始 =============>");
        // 查询人行资产头寸
        List<PbcMonthAssetPositionDto> listAssetPosition = pbcColLoanDetailSub001Mapper.selectAssetPositionInfoList(batchDate);
        logger.info("ZG07 除回购和拆解外贷款明细登记 获取资产头寸结束 <=============");

        logger.info("ZG07 除回购和拆解外贷款明细登记 获取明细信息开始 =============>");
        // 查询除回购和拆借外贷款明细信息
        List<OperationalLoanInfo001Dto> loanDetailInfoList = pbcColLoanDetailSub001Mapper.selectLoanDetailInfo(executeDate);
        Map<String, OperationalLoanInfo001Dto> loanInfoMap = loanDetailInfoList.stream().collect(Collectors.toMap(ChbLoanDetailInfo::getAssetCode, value -> value , (k1, k2) ->k1));
        logger.info("ZG07 除回购和拆解外贷款明细登记 获取明细信息结束 <=============");
        // 登记DtoList
        List<ZG07PbcColLoanDetailSubDto> insertList = Lists.newArrayList();
        // 汇率map
        Map<String, BigDecimal> rateMap = new HashMap<String, BigDecimal>();
        logger.info("ZG07 除回购和拆解外贷款明细登记 转换数据开始 =============>");
        // 遍历头寸
        for (PbcMonthAssetPositionDto position : listAssetPosition) {
            ZG07PbcColLoanDetailSubDto subDto = new ZG07PbcColLoanDetailSubDto();
            if (loanInfoMap.get(position.getAssetCode()) != null) {
                OperationalLoanInfo001Dto orgDto = loanInfoMap.get(position.getAssetCode());
                // 复制 数据值
                //BeanUtil.copyPropertiesIgnoreNull(subDto, orgDto);
                subDto.setDetailuuid(IDGenerator.generate(36).toUpperCase());
                // 期数
                subDto.setTermid(termId);
                // 人行产品代码
                subDto.setPbcProdId(position.getAssetPoolCode());
                // 内部产品代码
                subDto.setProdId(position.getProdid());
                //数据检查用字段，不入库
                if(map.containsKey(subDto.getProdId())){
                    subDto.setPbcPordIdOut(map.get(subDto.getProdId()));
                }else {
                    //if(subDto.getPbcProdId().contains("INS")){
                    if(position.getAssetPoolCode().contains("INS")){
                        subDto.setPbcPordIdOut(subDto.getPbcProdId().substring(3));
                    }else {
                        subDto.setPbcPordIdOut(subDto.getPbcProdId());
                    }
                }
                // 资产代码
                subDto.setAssetCode(orgDto.getAssetCode());
                // 贷款种类
                subDto.setLoansType(orgDto.getLoansType());
                // 贷款转让方机构代码
                subDto.setLoanTransferorId(orgDto.getLoanTransferorId());
                // 贷款合同原始发放机构代码
                subDto.setLoanContractOrgId(orgDto.getLoanContractOrgId());
                // 贷款合同原始发放记机构所在地代码
                if (StringUtil.isNotEmpty(orgDto.getLoanContractOrgProvince())) {
                    subDto.setLoanContractOrgCode(orgDto.getLoanContractOrgProvince());
                }
                if (StringUtil.isNotEmpty(orgDto.getLoanContractOrgCity())) {
                    subDto.setLoanContractOrgCode(orgDto.getLoanContractOrgCity());
                }
                if (StringUtil.isNotEmpty(orgDto.getLoanContractOrgCounty())) {
                    subDto.setLoanContractOrgCode(orgDto.getLoanContractOrgCounty());
                }
                // 借款人类型
                subDto.setBorrowerType(orgDto.getBorrowerType());
                // 地区代码
                if (StringUtil.isNotEmpty(orgDto.getBorrowerProvince())) {
                    subDto.setBorrowerCounty(orgDto.getBorrowerProvince());
                }
                if (StringUtil.isNotEmpty(orgDto.getBorrowerCity())) {
                    subDto.setBorrowerCounty(orgDto.getBorrowerCity());
                }
                if (StringUtil.isNotEmpty(orgDto.getBorrowerCounty())) {
                    subDto.setBorrowerCounty(orgDto.getBorrowerCounty());
                }
                // 借款人代码
                subDto.setBorrowerId(orgDto.getBorrowerId());
                // 行业信息
                subDto.setIndustryInfo(orgDto.getIndustryInfo());
                // 企业出资人经济成分
                if (StringUtil.isNotEmpty(orgDto.getEnterpriseInvestorTypeFirst())) {
                    subDto.setEnterpriseInvestorType(orgDto.getEnterpriseInvestorTypeFirst());
                }
                if (StringUtil.isNotEmpty(orgDto.getEnterpriseInvestorTypeSec())) {
                    subDto.setEnterpriseInvestorType(orgDto.getEnterpriseInvestorTypeSec());
                }
                if (StringUtil.isNotEmpty(orgDto.getEnterpriseInvestorTypeThird())) {
                    subDto.setEnterpriseInvestorType(orgDto.getEnterpriseInvestorTypeThird());
                }
                // 企业规模
                subDto.setEnterpriseScale(orgDto.getEnterpriseScale());
                // 贷款借据编码
                subDto.setLoanBasisId(orgDto.getLoanBasisId());
                // 贷款产品类别
                subDto.setLoanPrdType(orgDto.getLoanPrdType());
                // 贷款实际投向
                subDto.setLoanActualInvest(orgDto.getLoanActualInvest());
                // 贷款发放日期
                subDto.setLoanVdate(orgDto.getLoanVdate());
                // 贷款到期日期
                subDto.setLoanMdate(orgDto.getLoanMdate());
                // 贷款展期到期日期
                subDto.setLoanExtensionDueDate(orgDto.getLoanExtensionDueDate());
                // 利率是否固定
                subDto.setRateFixed(orgDto.getRateFixed());
                // 利率水平
                subDto.setRateLevel(orgDto.getRateLevel());
                // 贷款担保方式
                subDto.setLoanGuaranteeMode(orgDto.getLoanGuaranteeMode());
                // 贷款质量
                subDto.setLoanQuality(orgDto.getLoanQuality());
                // 贷款状态
                subDto.setLoanState(orgDto.getLoanState());
                // 贷款转让折扣率
                subDto.setLoanTransferorRate(orgDto.getLoanTransferorRate());
                // 原始合同币种
                subDto.setOrigCcy(orgDto.getOrigCcy());
                // 原始合同金额
                subDto.setOrigContractAmount(orgDto.getOrigContractAmount());
                // 原始合同金额折人民币
                subDto.setOrigContractAmountCny(orgDto.getOrigContractAmountCny());
                // 贷款余额币种
                subDto.setAmtCcy(orgDto.getLoanBalanceCcy());
                // 贷款余额
                subDto.setContractAmount(position.getAmt());
                BigDecimal amountCny = ChbCcyConvertUtil.exchangeMoney(position.getCcy(), DictCodeListExt1.币种_人行_人民币.getValue(),
                        new Date(), position.getAmt(), rateMap, exchangeSource).setScale(2, BigDecimal.ROUND_HALF_UP);
                // 贷款余额折人民币
                subDto.setContractAmountCny(amountCny);

                // 报送状态
                subDto.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
                // 数据日期
                subDto.setStatusDate(DateUtil.addDay(addDay, 1));
                // 创建人
                subDto.setCreateUser(CommonUtil.getUserName());
                // 更新人
                subDto.setUpdateUser(CommonUtil.getUserName());
                // 创建部门
                subDto.setCreateDept(CommonUtil.getDeptCode());
                // 机构代码
                subDto.setOrgCode(CommonUtil.getOrgCode());
                //数据状态
                subDto.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
                //人行报送截止日
                subDto.setPbcSubmitDeadline(dateList.get(0));
                //行内报送截止日
                subDto.setSubmitDeadline(dateList.get(1));
                subDto.setSubmitTime(null);

            } else {
                continue;
            }
            insertList.add(subDto);
        }
        logger.info("ZG07 除回购和拆解外贷款明细登记 转换数据结束 <============");
        //匹配规则
        //matchRule(arrayList,dateList,exchangeSource,addDay,termId);
        // 数据批量入库
        if (CollectionUtils.isEmpty(insertList)){
            logger.info("ZG07 除回购和拆解外贷款明细登记 空数据 =============>");
            return;
        }
        logger.info("采集平台除回购和拆借外贷款明细信息：报表生成校验 开始----------");
        List<ZG07PbcColLoanDetailSubDto> listData = pbcDataCheckUtil.getTrueData(insertList, null, DictCodeListExt2.采集平台除回购和拆借外贷款明细信息.getValue(), true, "1", ZG07PbcColLoanDetailSubDto.class);
        logger.info("采集平台除回购和拆借外贷款明细信息：报表生成校验 结束----------");
        dbData(listData);
    }

    /**
     * 批量入库
     *
     * @param insertList
     */
    private void dbData(List<ZG07PbcColLoanDetailSubDto> insertList) {
        logger.info("ZG07 除回购和拆解外贷款明细登记信息插入数据：插入开始----------");
        int count = 0;
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            ZG07PbcColLoanDetailSub001Mapper batchMapper = session.getMapper(ZG07PbcColLoanDetailSub001Mapper.class);
            for (ZG07PbcColLoanDetailSubDto dto : insertList) {
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
            logger.info("ZG07 除回购和拆解外贷款明细登记信息入库报错:" + e.getMessage(), e);
        } finally {
            session.close();
        }
        logger.info("ZG07 除回购和拆解外贷款明细登记信息插入数据：插入结束----------");
    }
}
