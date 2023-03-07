package com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysInstitution;
import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;
import com.joyintech.tams.common.svreport.base.service.ChbTypeMappingService;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode001Mapper;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.*;
import com.joyintech.tams.svreport.pbc.assetposition.dao.PbcMonthAssetReportMapper;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dao.ZG01PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dao.ZG05PbcColPrdAssetDebtMapper;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.PbcColReport;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.PbcColReportAssetDebtDetailDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportConstants;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportProperycode;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.svreport.pbc.prod.dao.PbcMonthPrdPostionMapper;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dao.PbcReportTemplateMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.svreport.pbc.report.dto.PbcReportTemplateDto;
import com.joyintech.tams.svreport.pbc.reportdetail.dto.PbcMonthReportDetailAsset;
import com.joyintech.tams.svreport.pbc.reportdetail.service.PbcMonthReportDetailAssetUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ZG05 资管产品资产负债信息生成处理逻辑
 */
@Service
public class ZG05PbcColPrdAssetDebtBatchService {

    @Autowired
    private ZG05PbcColPrdAssetDebtMapper zg05PbcColPrdAssetDebtMapper;

    @Autowired
    private SysInstRegBankCode001Mapper sysInstRegBankCode001Mapper;
    /**
     * 资产头寸mapper
     */
    @Autowired
    private PbcReportTemplateMapper pbcReportTemplateMapper;
    /**
     * 映射service
     */
    @Autowired
    private ChbTypeMappingService chbTypeMappingService;
    /**
     * 产品头寸mapper
     */
    @Autowired
    private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;

    @Autowired
    private ZG01PbcPrdBaseinfoSubMapper zg01PbcPrdBaseinfoSubMapper;

    /**
     * 资产头寸mapper
     */
    @Autowired
    private PbcMonthAssetReportMapper pbcMonthAssetReportMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private List<PbcMonthReportDetailAsset> assetDetailList;

    @Autowired
    private PbcMonthReportDetailAssetUtil pbcMonthReportDetailAssetUtil;

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private PbcDataCheckUtil pbcDataCheckUtil;

    public void processData(Date batchTime) {
        //1.判断跑批日期，如果不是1号，批处理不执行。
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(batchTime);
        if (calendar.get(Calendar.DATE) != 1) {  //每月1日生成上月的数据
            logger.info("每月1日生成上月的数据！");
            return;
        }
        Date addDay = DateUtil.addDay(batchTime, -1);
        String termId = DateUtil.format(addDay, DateUtil.MONTH_OTHER_FORMAT);
        if (zg05PbcColPrdAssetDebtMapper.selectCountByTermId(termId,DictCodeListExt2.采集平台资管产品资产负债信息.getValue()) > 0) {
            logger.info("当月存在已报送数据，批处理不再继续执行！");
            return;
        }
        //删除当期所有数据
        zg05PbcColPrdAssetDebtMapper.deleteByTermId(termId,DictCodeListExt2.采集平台资管产品资产负债信息.getValue());

        //报表生成
        genRegistReport(batchTime,null,false);

    }

    /**
     * 功能:ZG05-资管产品资产负债信息批处理
     *
     * @param executeDate 批处理日期
     * @param prodIdList  产品list
     * @param isEdit      是否是消息处理编辑触发
     *                    void
     * @version
     * @author：dongxiangyang 2022年11月7日
     * @since
     */
    public void genRegistReport(Date executeDate, List<String> prodIdList, boolean isEdit) {

        //查询人行产品资产池的数据
        List<ZG01PbcPrdBaseinfoSub> list=zg01PbcPrdBaseinfoSubMapper.selectAllPbcProdId();
        Map<String,String> map= list.stream().collect(Collectors.toMap(ZG01PbcPrdBaseinfoSub::getProdId,ZG01PbcPrdBaseinfoSub::getPbcProdId ));

        // 入库集合
        List<ZG05PbcColPrdAssetDebtDto> insertList = new ArrayList<>();

        // 一次性将所有数据全部查出
        List<PbcMonthAssetPositionDto> posAssetList = new ArrayList<>();
        // 权益类从产品头寸取值
        List<PbcMonthPrdPostionDto> posProdList = new ArrayList<>();

        posAssetList.addAll(pbcMonthAssetReportMapper.selectByDateAndProdidWithPbcPrdCode(prodIdList, com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(executeDate)));
        //多法人模式改造  20200725 leipeng
        if (CommonUtil.isBAmodel()) {
            posProdList.addAll(pbcMonthPrdPostionMapper.selectByDateWithAssetPoolCodeEx(prodIdList, com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(executeDate)));
        } else {
            posProdList.addAll(pbcMonthPrdPostionMapper.selectByDateWithPbcPrdCode(prodIdList, com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(executeDate)));
        }
        // 按产品分头寸
        Map<String, List<PbcMonthAssetPositionDto>> posAssetAllMap = posAssetList.stream().collect(
                Collectors.groupingBy(PbcMonthAssetPositionDto::getPbcProdidAndCcy, Collectors.toList()));
        // 按产品分头寸
        Map<String, List<PbcMonthPrdPostionDto>> posProdAllMap = posProdList.stream().collect(
                Collectors.groupingBy(PbcMonthPrdPostionDto::getPbcProdidAndCcy, Collectors.toList()));

        // 记录已经统计过得单产品
        Map<String, String> prodMap = new HashMap<>();
        //本位币合计
        Map<String, ZG05PbcColPrdAssetDebtDto>  rezg05map=new HashMap<>();
        // 根据查询出的数据分组进行出单产品报表
        for (String key : posAssetAllMap.keySet()) {
            if (!prodMap.containsKey(key)) {
                prodMap.put(key, key);
            } else {
                // 已经出过报表则跳过
                continue;
            }
            List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
            if (posAssetAllMap.get(key) != null) {
                assetPosition = posAssetAllMap.get(key);
            }
            List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
            if (posProdAllMap.get(key) != null) {
                prodPosition = posProdAllMap.get(key);
            }
            generateMonthAssetDebtReport(assetPosition, prodPosition, executeDate, isEdit,
                    PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT, true, null,rezg05map, insertList,map);
        }
        // 根据查询出的数据分组进行出单产品报表
        for (String key : posProdAllMap.keySet()) {
            if (!prodMap.containsKey(key)) {
                prodMap.put(key, key);
            } else {
                // 已经出过报表则跳过
                continue;
            }
            List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
            if (posAssetAllMap.get(key) != null) {
                assetPosition = posAssetAllMap.get(key);
            }
            List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
            if (posProdAllMap.get(key) != null) {
                prodPosition = posProdAllMap.get(key);
            }
            generateMonthAssetDebtReport(assetPosition, prodPosition, executeDate, isEdit,
                    PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT, true, null,rezg05map, insertList,map);
        }

         //本位币合计值入表
         for(String  key:rezg05map.keySet()){
             //zg05PbcColPrdAssetDebtMapper.insertSelective(rezg05map.get(key));
             insertList.add(rezg05map.get(key));
         }

        if (CollectionUtils.isEmpty(insertList)) {
            logger.info("数据集合为空");
            return;
        }

        // 处理检查字段是否显示0 add by wangyaning 20230111
        dealZeroNumber(insertList);

        logger.info("资管产品终止信息数据处理：报表生成校验 开始----------");
        List<ZG05PbcColPrdAssetDebtDto> listData = pbcDataCheckUtil.getTrueData(insertList, null, DictCodeListExt2.采集平台资管产品资产负债信息.getValue(), true, "1", ZG05PbcColPrdAssetDebtDto.class);
        logger.info("资管产品终止信息数据处理：报表生成校验 结束----------");
        intoDatabase(listData);
    }

    /**
     * 入库
     * @param insertlist
     */
    private void intoDatabase(List<ZG05PbcColPrdAssetDebtDto> insertlist) {
        logger.debug("ZG05 采集平台资管产品资产负债信息：插入开始----------");
        int count = 0;
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            ZG05PbcColPrdAssetDebtMapper batchMapper = session.getMapper(ZG05PbcColPrdAssetDebtMapper.class);
            for (ZG05PbcColPrdAssetDebtDto dto : insertlist) {
                batchMapper.insertSelective(dto);
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
            logger.error("ZG05 采集平台资管产品资产负债信息 入库报错:" + e.getMessage(), e);
        } finally {
            session.close();
        }
        logger.debug("ZG05 采集平台资管产品资产负债信息：插入结束----------");
    }

    /**
     * 功能:生成表2-1 资管产品资产负债统计
     *
     * @param posAssetList     资产头寸
     * @param posProdList      产品头寸
     * @param generateDate     生成日
     * @param isEdit           是否为修改
     * @param reportType       报表类型
     * @param isOnePrd         是否单产品
     * @param prodAssetRegList 已登记的数据
     * @return 其他负债值
     * void
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @since 1.6.0
     */
    @SuppressWarnings("unused")
    public Map<String, BigDecimal> generateMonthAssetDebtReport(List<PbcMonthAssetPositionDto> posAssetList, List<PbcMonthPrdPostionDto> posProdList,
                                                                Date generateDate, boolean isEdit, String reportType, boolean isOnePrd, List<PbcMonthReport> prodAssetRegList,Map<String, ZG05PbcColPrdAssetDebtDto>  rezg05map, List<ZG05PbcColPrdAssetDebtDto> insertList,
                                                                Map<String,String> map) {
        Map<String, BigDecimal> reback = new HashMap<>();

        // 从系统缓存中获取人行月报2-1 倒减逻辑：01 其他负债，02 未分配利润,改变815000其他负债、822000资本公积、823000未分配利润取值逻辑
        String svrMonthSubtractionLogic = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_MONTH_SUBTRACTION_LOGIC);

        // 生成月度报表主表数据
        PbcColReport pbcReport = genMonthReportByReporttype(reportType, generateDate, isEdit, isOnePrd);

        //插入币种和人行产品代码+币种标识，用于处理多币种
        if(posProdList!=null&&posProdList.size()>0){
            pbcReport.setCcy(posProdList.get(0).getCcy());
            pbcReport.setPbcProdidAndCcy(posProdList.get(0).getPbcProdidAndCcy());
        }
        if(posAssetList!=null&&posAssetList.size()>0){
            pbcReport.setCcy(posAssetList.get(0).getCcy());
            pbcReport.setPbcProdidAndCcy(posAssetList.get(0).getPbcProdidAndCcy());
        }

        //update by leipeng 20200319  汇总数据提取全量模板
        Map<String, PbcColReportAssetDebtDetailDto> result = genAssetMonthReportemplate(generateDate, reportType, 151, pbcReport.getReportuuid());
        if (null == result) {
            return null;
        }
        Map<String, Map<String, PbcColReportAssetDebtDetailDto>> deptMap = new HashMap();

        // 汇率取值开关
        String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);

        // 汇率map,可重复使用
        Map<String, BigDecimal> rateMap = new HashMap<>();

        Map<String, Date> massageMap = new HashMap<>();

        massageMap.put("endDate", pbcReport.getEnddate());

        for (PbcMonthAssetPositionDto pos : posAssetList) {
            // 头寸-科目余额是否为资本公积金、未分配理论
            String assetType1ForBalance = "";
            if (PbcMonthReportConstants.POSITION_TYPE_KMYE.equals(pos.getPositionType())) {
                assetType1ForBalance = chbTypeMappingService.getMappingValue(PbcMonthReportConstants.MAPPINGCODE_SUBJECTNO,
                        pos.getAssetCode());
            }

//			// add by leipeng 20200319 获取所有部门对应信息,并列出单独的表数据
            if (null != pos.getInstitutionCode() && null == deptMap.get(pos.getInstitutionCode())) {
                Map<String, PbcColReportAssetDebtDetailDto> singDeptMap = genAssetMonthReportemplate(generateDate, reportType, 146, pbcReport.getReportuuid());
                singDeptMap.putAll(singDeptMap);
                deptMap.put(pos.getInstitutionCode(), singDeptMap);
            }

            // 资产合计统计头寸类型为资产：01 (境内外资产为03或者04代表为负债 add by gaojian 2019-07-30)
            if (PbcMonthReportConstants.POSITION_TYPE_ZC.equals(pos.getPositionType())
                    && !PbcMonthReportConstants.PBC_ASSETTYPE3_J03.equals(pos.getAssetType3())
                    && !PbcMonthReportConstants.PBC_ASSETTYPE3_J04.equals(pos.getAssetType3())) {

                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产-“（减）资产减值准备”（AK000）
                // 720000 存款 = 境内存款 + 境外存款
                // 721100 境内存款= D01 活期存款+ D02 定期和其他存款
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_D01.equals(pos.getAssetType3())) {
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_721100_HQCK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_721000_JNCK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_720000_CK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);
                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产-“（减）资产减值准备”（AK000）
                // 720000 存款 = 境内存款 + 境外存款
                // 721000 境内存款= D01 活期存款+ D02 定期和其他存款
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_D02.equals(pos.getAssetType3())) {
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_721200_DQHQTCK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_721000_JNCK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_720000_CK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);
                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产 -“（减）资产减值准备”（AK000）
                // 720000 存款 = 境内存款 + 境外存款
                // 722000 境内存款= D03 活期存款+ D04 定期和其他存款
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_D03.equals(pos.getAssetType3())) {
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_722100_HQCK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_722000_JWCK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_720000_CK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);
                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产-“（减）资产减值准备”（AK000）
                // 720000 存款 = 境内存款 + 境外存款
                // 722000 境内存款= D03 活期存款+ D04 定期和其他存款
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_D04.equals(pos.getAssetType3())) {
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_722200_DQHQTCK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_722000_JWCK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_720000_CK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);
                }

                if (PbcMonthReportConstants.PBC_ASSETTYPE1_P14.equals(pos.getAssetType1())) {
                    //减值
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_7K0000_ZCJZZB, reportType, rateMap, source, null, massageMap);

                    setAssetDebtDetailValueSubtract(result.get(PbcReportProperycode.AMT_700000_ZCHJ), pos.getCcy(), pos.getAmt(), rateMap, (Date) massageMap.get("endDate"), source);
                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 730000 可转让存单 = 731000 同业存单+732000 大额存单
                // 731000 同业存单:资产三级分类=K01 同业存单
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_K01.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_731000_TYCD, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_730000_KZRCD, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 730000 可转让存单 = 731000 同业存单+732000 大额存单
                // 732000 大额存单 :资产三级分类=K02 大额存单
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_K02.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_732000_DECD, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_730000_KZRCD, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 740000 债务证券 = 741000 政府债券+742000中央银行债券
                // +743000政策性金融债券+744000其他金融债券+745000企业债券
                // +746000 票据+747000资产支持证券+748000境外债券
                // 741000 政府债券 :资产三级分类=S01 政府债券
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_S01.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_741000_ZFZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_740000_ZWZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 740000 债务证券 = 741000 政府债券+742000中央银行债券
                // +743000政策性金融债券+744000其他金融债券+745000企业债券
                // +746000 票据+747000资产支持证券+748000境外债券
                // 742000 中央银行债券:资产三级分类=S02 中央银行债券
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_S02.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_742000_ZYYXZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_740000_ZWZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 740000 债务证券 = 741000 政府债券+742000中央银行债券
                // +743000政策性金融债券+744000其他金融债券+745000企业债券
                // +746000 票据+747000资产支持证券+748000境外债券
                // 743000 政策性金融债券:资产三级分类=S03 政策性金融债券
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_S03.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_743000_ZCXJRZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_740000_ZWZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 740000 债务证券 = 741000 政府债券+742000中央银行债券
                // +743000政策性金融债券+744000其他金融债券+745000企业债券
                // +746000 票据+747000资产支持证券+748000境外债券
                // 744000 其他金融债券:资产三级分类=S04 其他金融债券
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_S04.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_744000_QTJRZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_740000_ZWZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 740000 债务证券 = 741000 政府债券+742000中央银行债券
                // +743000政策性金融债券+744000其他金融债券+745000企业债券
                // +746000 票据+747000资产支持证券+748000境外债券
                // 745000 企业债券:资产三级分类=S05 企业债券
                if (PbcMonthReportConstants.PBC_ASSETTYPE2_S05.equals(pos.getAssetType2())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_745000_QYZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_740000_ZWZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 740000 债务证券 = 741000 政府债券+742000中央银行债券
                // +743000政策性金融债券+744000其他金融债券+745000企业债券
                // +746000 票据+747000资产支持证券+748000境外债券
                // 746000 票据:资产三级分类=S06 票据
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_S06.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_746000_PJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_740000_ZWZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 740000 债务证券 = 741000 政府债券+742000中央银行债券
                // +743000政策性金融债券+744000其他金融债券+745000企业债券
                // +746000 票据+747000资产支持证券+748000境外债券
                // 747000 资产支持证券:资产三级分类=S07 资产支持证券
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_S07.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_747000_ZCZCZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_740000_ZWZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 740000 债务证券 = 741000 政府债券+742000中央银行债券
                // +743000政策性金融债券+744000其他金融债券+745000企业债券
                // +746000 票据+747000资产支持证券+748000境外债券
                // 748000 境外债券:资产三级分类=S08 境外债券
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_S08.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_748000_JWZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_740000_ZWZQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 751000 除回购和拆借外贷款:资产三级分类=R02 其他
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_R02.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_751000_CHGHCJWDK, reportType, rateMap, source, "01", massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, "01", massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_790000_YSZK, reportType, rateMap, source, "02", massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752100 住户:资产三级分类=L01 住户
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L01.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752100_ZH, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);



                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752200 广义政府:资产三级分类=L02 广义政府:
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L02.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752200_GYZF, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);



                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752300 非金融企业:=资产三级分类=L03 非金融企业
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L03.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752300_FJRQY, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);



                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752400 银行业存款类金融机构:=资产三级分类=L04 银行业存款类金融机构
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L04.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752400_YXYCKLJRJG, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752500 银行业非存款类金融机构:资产三级分类=L05 银行业非存款类金融机构
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L05.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752500_YXYFCKLJRJG, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752600 非银行业金融机构 = 752610 其中：中央交易对手方 + 资产三级分类=L06 非银行业金融机构-非中央交易对手
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L06.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752600_FYXYJRJG, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752600 非银行业金融机构 = 752610 其中：中央交易对手方 + 资产三级分类=L06 非银行业金融机构-非中央交易对手
                // 752610 其中：中央交易对手方:资产三级分类=L07 非银行业金融机构-中央交易对手
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L07.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752610_QZZYJYDSF, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752600_FYXYJRJG, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 752710 银行非保本理财:资产三级分类=L08 银行非保本理财
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L08.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752710_YXFBBLC, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 752720 信托公司资管产品:资产三级分类=L09 信托公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L09.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752720_XTGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 752730 证券公司及其子公司资管产品:资产三级分类=L10 证券公司及其子公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L10.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752730_ZQGSJQZGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 752740 基金管理公司及其子公司专户:资产三级分类=L11 基金管理公司及其子公司专户
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L11.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752740_JJGLGSJQZGSZH, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 752750 期货公司及其子公司资管产品:资产三级分类=L12 期货公司及其子公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L12.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752750_QHGSJQZGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 752760 保险资管产品:资产三级分类=L13 保险资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L13.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752760_BXZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 752770 金融资产投资公司资管产品:资产三级分类=L14 金融资产投资公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L14.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752770_JRZCTZGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 752780 公募基金:资产三级分类=L15 公募基金
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L15.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752780_GMJJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);



                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 752790 私募机构私募基金:资产三级分类=L16 私募机构私募基金
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L16.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752790_SMJGSMJJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752700 特定目的载体 =752710银行非保本理财+752720信托公司资管产品
                // +752730证券公司及其子公司资管产品+752740基金管理公司及其子公司专户
                // +752750期货公司及其子公司资管产品+752760保险资管产品+752770金融资产投资公司资管产品
                // +752780公募基金+752790私募机构私募基金+7527a0其他特定目的载体
                // 7527a0 其他特定目的载体:资产三级分类=L17 其他特定目的载体
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L99.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_7527A0_QTTDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752700_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 750000 贷款 = 751000除回购和拆借外贷款+752000回购和拆借
                // 752000 回购和拆借 = 752100 住户+752200 广义政府+752300 非金融企业+752400
                // 银行业存款类金融机构+752500 银行业非存款类金融机构
                // +752600 非银行业金融机构+752700 特定目的载体+752800 境外
                // 752800 境外:资产三级分类=L18 境外
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L98.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752800_JW, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_752000_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_750000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }

                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 760000 资产收益权 = 761000 贷款收益权+762000 债务证券收益权+763000 应收账款收益权
                // +764000其他债权收益权+765000 股票收益权+766000 其他股权收益权
                // +767000 物权资产收益权
                // 761000 贷款收益权:资产三级分类=Q01 贷款收益权
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_Q01.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_761000_DKSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_760000_ZCSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 760000 资产收益权 = 761000 贷款收益权+762000 债务证券收益权+763000 应收账款收益权
                // +764000其他债权收益权+765000 股票收益权+766000 其他股权收益权
                // +767000 物权资产收益权
                // 762000 债务证券收益权:资产三级分类=Q02 债务证券收益权
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_Q02.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_762000_ZWZQSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_760000_ZCSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 760000 资产收益权 = 761000 贷款收益权+762000 债务证券收益权+763000 应收账款收益权
                // +764000其他债权收益权+765000 股票收益权+766000 其他股权收益权
                // +767000 物权资产收益权
                // 763000 应收账款收益权:资产三级分类=Q03 应收账款收益权
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_Q03.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_763000_YSZKSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_760000_ZCSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 760000 资产收益权 = 761000 贷款收益权+762000 债务证券收益权+763000 应收账款收益权
                // +764000其他债权收益权+765000 股票收益权+766000 其他股权收益权
                // +767000 物权资产收益权
                // 764000 其他债权收益权:资产三级分类=Q04 其他债权收益权
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_Q04.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_764000_QTZQSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_760000_ZCSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 760000 资产收益权 = 761000 贷款收益权+762000 债务证券收益权+763000 应收账款收益权
                // +764000其他债权收益权+765000 股票收益权+766000 其他股权收益权
                // +767000 物权资产收益权
                // 765000 股票收益权:资产三级分类=Q05 股票收益权
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_Q05.equals(pos.getAssetType3())) {
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_765000_GPSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_760000_ZCSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 760000 资产收益权 = 761000 贷款收益权+762000 债务证券收益权+763000 应收账款收益权
                // +764000其他债权收益权+765000 股票收益权+766000 其他股权收益权
                // +767000 物权资产收益权
                // 766000 其他股权收益权:资产三级分类=Q06 其他股权收益权
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_Q06.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_766000_QTGQSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_760000_ZCSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);


                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 760000 资产收益权 = 761000 贷款收益权+762000 债务证券收益权+763000 应收账款收益权
                // +764000其他债权收益权+765000 股票收益权+766000 其他股权收益权
                // +767000 物权资产收益权
                // 767000 物权资产收益权:资产三级分类=Q07 物权资产收益权
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_Q07.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_767000_WQZCSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_760000_ZCSYQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 771000 股票 = 771100非金融企业股票+771200金融机构股票
                // 771100 非金融企业股票:资产三级分类=G01 非金融企业股票
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_G01.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_771100_FJRQYGP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_771000_GP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 771000 股票 = 771100非金融企业股票+771200金融机构股票
                // 771200 金融机构股票:资产三级分类=G02 金融机构股票
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_G02.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_771200_JRJGGP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_771000_GP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772100 银行非保本理财:资产三级分类=M01 银行非保本理财
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M01.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772100_YXFBBLC, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772200 信托公司资管产品:资产三级分类=M02 信托公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M02.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772200_XTGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772300 证券公司及其子公司资管产品:资产三级分类=M03 证券公司及其子公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M03.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772300_ZQGSJQZGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772400 基金管理公司及其子公司专户:资产三级分类=M04 基金管理公司及其子公司专户
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M04.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772400_JJGLGSJQZGSZH, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772500 期货公司及其子公司资管产品 :资产三级分类=M05 期货公司及其子公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M05.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772500_QHGSJQZGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772600 保险资管产品:资产三级分类=M06 保险资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M06.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772600_BXZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772700 金融资产投资公司资管产品:资产三级分类=M07 金融资产投资公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M07.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772700_JRZCTZGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772800 公募基金:资产三级分类=M08 公募基金
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M08.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772800_GMJJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772900 私募机构私募基金:资产三级分类=M09 私募机构私募基金
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M09.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772900_SMJGSMJJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 772000 特定目的载体份额 =
                // 772100银行非保本理财+772200信托公司资管产品+772300证券公司及其子公司资管产品
                // +772400 基金管理公司及其子公司专户+772500期货公司及其子公司资管产品+772600保险资管产品
                // +772700金融资产投资公司资管产品+772800公募基金+772900私募机构私募基金+772a00其他特定目的载体
                // 772a00 其他特定目的载体:资产三级分类=M10 其他特定目的载体
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_M99.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772A00_QTTDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_772000_TDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 773000 其他股权：773100非金融企业股权+773200金融机构股权
                // 773100 非金融企业股权:资产三级分类=O01 非金融企业股权
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_O01.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_773100_FJRQYGQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_773000_QTGQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 773000 其他股权：773100非金融企业股权+773200金融机构股权
                // 773200 金融机构股权:资产三级分类=O02 金融机构股权
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_O02.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_773200_JRJGGQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_773000_QTGQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 770000 股权及特定目的载体份额 =
                // 771000股票+772000特定目的载体份额+773000其他股权+774000境外股权
                // 774000 境外股权：:资产三级分类=T04
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_T04.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_774000_JWGQ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_770000_GQJTDMDZTFE, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 780000 金融衍生品 = 781000境内+782000境外
                // 781000 境内:资产三级分类=J01 境内
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_J01.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_781000_JN, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_780000_JRYSP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 780000 金融衍生品 = 781000境内+782000境外
                // 782000 境外:资产三级分类=J02 境外
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_J02.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_782000_JW, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_780000_JRYSP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }
                // 700000 资产合计 = 现金 + 存款 + 可转让存单 + 债务证券
                // + 贷款 + 资产收益权 + 股权及特定目的载体份额 + 金融衍生品 + 应收账款 + 黄金 + 非金融资产
                // 790000 应收账款=所有本机构资产（类型=资产）的利息值+资产三级类型=P11金额值
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_P11.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_790000_YSZK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_700000_ZCHJ, reportType, rateMap, source, null, massageMap);

                }

            } else if (PbcMonthReportConstants.POSITION_TYPE_FZ.equals(pos.getPositionType())
                    // 境内外资产为03或者04也进负债（add by gaojian 2019-07-30）
                    || PbcMonthReportConstants.PBC_ASSETTYPE3_J03.equals(pos.getAssetType3())
                    || PbcMonthReportConstants.PBC_ASSETTYPE3_J04.equals(pos.getAssetType3())) {// 负债合计
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811100 除回购和拆借外贷款：资产三级类型=R02 其他
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_R02.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811100_CHGHCJWDK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811210 住户:资产三级分类=L01 住户
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L01.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811210_ZH, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811220 广义政府:资产三级分类=L02 广义政府
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L02.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811220_GYZF, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811230 非金融企业:资产三级分类=L03 非金融企业
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L03.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811230_FJRQY, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811240 银行业存款类金融机构:资产三级分类=L04 银行业存款类金融机构
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L04.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811240_YXYCKLJRJG, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);


                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811250 银行业非存款类金融机构:资产三级分类=L05 银行业非存款类金融机构
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L05.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811250_YXYFCKLJRJG, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811260 非银行业金融机构= 811261 其中：中央交易对手方 + 资产三级分类=L06 非银行业金融机构-非中央交易对手
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L06.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811260_FYXYJRJG, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811260 非银行业金融机构= 811261 其中：中央交易对手方 + 资产三级分类=L06 非银行业金融机构-非中央交易对手
                // 811261 其中：中央交易对手方:资产三级分类=L07 非银行业金融机构-中央交易对手
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L07.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811261_QZZYJYDSF, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811260_FYXYJRJG, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 811271 银行非保本理财:资产三级分类=L08 银行非保本理财
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L08.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811271_YXFBBLC, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 811272 信托公司资管产品:资产三级分类=L09 信托公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L09.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811272_XTGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 811273 证券公司及其子公司资管产品:资产三级分类=L10 证券公司及其子公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L10.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811273_ZQGSJQZGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);


                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 811274 基金管理公司及其子公司专户:资产三级分类=L11 基金管理公司及其子公司专户
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L11.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811274_JJGLGSJQZGSZH, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 811275 期货公司及其子公司资管产品:资产三级分类=L12 期货公司及其子公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L12.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811275_QHGSJQZGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);


                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 811276 保险资管产品:资产三级分类=L13 保险资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L13.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811276_BXZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);


                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 811277 金融资产投资公司资管产品:资产三级分类=L14 金融资产投资公司资管产品
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L14.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811277_JRZCTZGSZGCP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);


                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 811278 公募基金:资产三级分类=L15 公募基金
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L15.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811278_GMJJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);


                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 811279 私募机构私募基金:资产三级分类=L16 私募机构私募基金
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L16.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811279_SMJGSMJJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);


                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811270 特定目的载体 =811271银行非保本理财+811272信托公司资管产品+811273证券公司及其子公司资管产品
                //                  +811274基金管理公司及其子公司专户+811275期货公司及其子公司资管产品+811276保险资管产品
                //                  +811277金融资产投资公司资管产品+811278公募基金+811279私募机构私募基金+81127a其他特定目的载体
                // 81127a 其他特定目的载体:资产三级分类=L17 其他特定目的载体
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L99.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_81127A_QTTDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811270_TDMDZT, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 811000 贷款=811100除回购和拆借外贷款+811200回购和拆借
                // 811200 回购和拆借 = 811210住户+ 811220广义政府+811230非金融企业+811240银行业存款类金融机构
                //                  +811250银行业非存款类金融机构+811260非银行业金融机构+811270特定目的载体+811280境外
                // 811280 境外:资产三级分类=L18 境外
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_L98.equals(pos.getAssetType3())) {

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811280_JW, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811200_HGHCJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_811000_DK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 813000 金融衍生品： 813100 境内+813200 境外
                // 813100 境内:资产三级分类=J03 境内
//                if (PbcMonthReportConstants.PBC_ASSETTYPE3_J01.equals(pos.getAssetType3())) {
                /* 负债金融衍生品境内外枚举值修改 modify by gaojian 2019-07-22 begin*/
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_J03.equals(pos.getAssetType3())) {
                    /* 负债金融衍生品境内外枚举值修改 modify by gaojian 2019-07-22 end*/


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_813100_JN, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_813000_JRYSP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);


                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 813000 金融衍生品： 813100 境内+813200 境外
                // 813200 境外:资产三级分类=J04 境外
//                if (PbcMonthReportConstants.PBC_ASSETTYPE3_J02.equals(pos.getAssetType3())) {
                /* 负债金融衍生品境内外枚举值修改 modify by gaojian 2019-07-22 begin*/
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_J04.equals(pos.getAssetType3())) {
                    /* 负债金融衍生品境内外枚举值修改 modify by gaojian 2019-07-22 end*/

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_813200_JW, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_813000_JRYSP, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 810000 负债合计 = 811000贷款+812000资产支持证券+813000金融衍生品+814000应付账款+815000其他负债
                // 814000 应付账款=统计所有本机构资产（类型=负债）的利息值+资产三级类型=P12 应付账款 的金额值
                if (PbcMonthReportConstants.PBC_ASSETTYPE3_P12.equals(pos.getAssetType3())) {


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_814000_YFZK, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_810000_FZHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
            } else if (PbcMonthReportConstants.POSITION_TYPE_KMYE.equals(pos.getPositionType())) {
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
                // 822000 资本公积：根据系统配置的科目号从【人行报表资产头寸】.【资产代码】=相应科目号的【金额】值
                if (PbcMonthReportConstants.PBC_ASSETTYPE1_ZBGJJ.equals(assetType1ForBalance)) {
                    // 822000 资本公积
                    // 822000 资本公积(取值修改逻辑为	822000 统计逻辑：根据系统配置的科目号从【人行报表资产头寸】.【资产代码】=相应科目号的【金额】值，同时加上下方的值：
                    // 人行产品头寸的sum（剩余成本-剩余份额）


                }
                if (PbcMonthReportConstants.PBC_ASSETTYPE1_QTZHSY.equals(assetType1ForBalance)) {
                    //其他综合收益
                    //"权益合计”（C0000）=“实收本金”（C1000）+“未分配利润”（C3000）+“其他综合收益”（C4000)
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_824000_QTZHSY, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_820000_QYHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);
                }
                // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
                // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
                // 823000 未分配利润：根据系统配置的科目号从【人行报表资产头寸】.【资产代码】=相应科目号的【金额】值
                if (PbcMonthReportConstants.PBC_ASSETTYPE1_WFPLR.equals(assetType1ForBalance)) {
                    // 823000 未分配利润
                    // 增加参数“人行月报2-1 倒减逻辑：01 其他负债，02 未分配利润”
                    // 参数值=01 时，根据系统配置的科目号从【人行报表资产头寸】.【资产代码】=相应科目号的【金额】值，同时减去下方的值：
                    // 人行产品头寸的sum（剩余成本-剩余份额）
                    // 参数值=02 时，823000未分配利润：=资产合计-负债合计-实收本金-资本公积；合计列倒减，人民币列=合计列，其他列不统计
                    if (StringUtils.isNotBlank(svrMonthSubtractionLogic)) {
                        if (StringUtil.equals(DictCodeListExt1.是或否_是.getValue(), svrMonthSubtractionLogic)) {

                            setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_823000_WFPLR, reportType, rateMap, source, null, massageMap);

                        }
                    }


                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_820000_QYHJ, reportType, rateMap, source, null, massageMap);

                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_800000_FZJQYHJ, reportType, rateMap, source, null, massageMap);

                }
            }
        }
        Set<String> set = new HashSet<String>();
        for (PbcMonthPrdPostionDto pos : posProdList) {
            set.add(pos.getProdid());
            // 销售区域为计划单列市的不统计（省份内已经统计）：210200大连市、330200宁波市、350200厦门市、370200青岛市、440300深圳市
            if ("210200".equals(pos.getBuyPlace()) || "330200".equals(pos.getBuyPlace()) || "350200".equals(pos.getBuyPlace()) ||
                    "370200".equals(pos.getBuyPlace()) || "440300".equals(pos.getBuyPlace())) {
                continue;
            }

            // add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
            if (null != pos.getInstitutionCode() && null == deptMap.get(pos.getInstitutionCode())) {
                Map<String, PbcColReportAssetDebtDetailDto> singDeptMap = genAssetMonthReportemplate(generateDate, reportType, 146, pbcReport.getReportuuid());
                singDeptMap.putAll(singDeptMap);
                deptMap.put(pos.getInstitutionCode(), singDeptMap);
            }

            // 参数值=01 时，根据系统配置的科目号从【人行报表资产头寸】.【资产代码】=相应科目号的【金额】值，同时减去下方的值：
            // 人行产品头寸的sum（剩余成本-剩余份额）
            if (StringUtils.isNotBlank(svrMonthSubtractionLogic)) {
                if (StringUtil.equals(DictCodeListExt1.是或否_是.getValue(), svrMonthSubtractionLogic)) {
                    if (pos.getEndCost() == null) {
                        pos.setEndCost(new BigDecimal("0"));
                    }
                    if (pos.getEndqtysum() == null) {
                        pos.setEndqtysum(new BigDecimal("0"));
                    }

                }
            }
            if (pos.getEndCost() == null) {
                pos.setEndCost(new BigDecimal("0"));
            }
            if (pos.getEndqtysum() == null) {
                pos.setEndqtysum(new BigDecimal("0"));
            }

            // 权益合计统计产品头寸
            // 劣后级从【人行报表产品头寸】统计【剩余份额】，根据客户二级分类进行统计
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821210 住户 :客户二级类型=C0100住户
            if (DictCodeListExt2.客户二级类型_住户.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821210_ZH,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821220 广义政府:客户二级类型=C0200广义政府
            if (DictCodeListExt2.客户二级类型_广义政府.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821220_GYZF,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821230 非金融企业 :客户二级类型=C0300非金融企业
            if (DictCodeListExt2.客户二级类型_非金融企业.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821230_FJRQY,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821240 银行业存款类金融机构:客户二级类型=C0400银行业存款类金融机构
            if (DictCodeListExt2.客户二级类型_银行业存款类金融机构.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821240_YXYCKLJRJG,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821250 银行业非存款类金融机构 :客户二级类型=C0401银行业非存款类金融机构
            if (DictCodeListExt2.客户二级类型_银行业非存款类金融机构.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821250_YXYFCKLJRJG,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821260 非银行业金融机构 :客户二级类型=C0402非银行业金融机构
            if (DictCodeListExt2.客户二级类型_非银行业金融机构.getValue().equals(pos.getCustomerType2())
                    /*****G06升级改造 leipeng 20210128   start**/
                    || DictCodeListExt2.客户二级类型_保险业金融机构.getValue().equals(pos.getCustomerType2())
                    || DictCodeListExt2.客户二级类型_信托公司.getValue().equals(pos.getCustomerType2())
                    || DictCodeListExt2.客户二级类型_证券公司.getValue().equals(pos.getCustomerType2())
                    || DictCodeListExt2.客户二级类型_基金公司.getValue().equals(pos.getCustomerType2())
                    || DictCodeListExt2.客户二级类型_其他金融机构.getValue().equals(pos.getCustomerType2())
            /*****G06升级改造 leipeng 20210128   end**/
            ) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821260_FYXYJRJG,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 821271 银行非保本理财  :客户二级类型=C0500银行非保本理财
            if (DictCodeListExt2.客户二级类型_银行非保本理财.getValue().equals(pos.getCustomerType2())) {
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821271_YXFBBLC,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 821272 信托公司资管产品 :客户二级类型=C0501信托公司资管产品
            if (DictCodeListExt2.客户二级类型_信托公司资管产品.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821272_XTGSZGCP,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 821273 证券公司及其子公司资管产品:客户二级类型=C0502证券公司及其子公司资管产品
            if (DictCodeListExt2.客户二级类型_证券公司及其子公司资管产品.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821273_ZQGSJQZGSZGCP,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 821274 基金管理公司及其子公司专户:客户二级类型=C0503基金管理公司及其子公司专户
            if (DictCodeListExt2.客户二级类型_基金管理公司及其子公司专户.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821274_JJGLGSJQZGSZH,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 821275 期货公司及其子公司资管产品:客户二级类型=C0504期货公司及其子公司资管产品
            if (DictCodeListExt2.客户二级类型_期货公司及其子公司资管产品.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821275_QHGSJQZGSZGCP,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 821276 保险资管产品:客户二级类型=C0505保险资管产品
            if (DictCodeListExt2.客户二级类型_保险资管产品.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821276_BXZGCP,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 821277 金融资产投资公司资管产品:客户二级类型=C0506金融资产投资公司资管产品
            if (DictCodeListExt2.客户二级类型_金融资产投资公司资管产品.getValue().equals(pos.getCustomerType2())) {


                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821277_JRZCTZGSZGCP,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 821278 公募基金 :客户二级类型=C0507公募基金
            if (DictCodeListExt2.客户二级类型_公募基金.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821278_GMJJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 821279 私募机构私募基金  :客户二级类型=C0508私募机构私募基金
            if (DictCodeListExt2.客户二级类型_私募机构私募基金.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821279_SMJGSMJJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821270 特定目的载体 = 822171 银行非保本理财 +822172 信托公司资管产品+822173 证券公司及其子公司资管产品
            //                   +822174 基金管理公司及其子公司专户 +822175 期货公司及其子公司资管产品 +822176 保险资管产品
            //                   +822177 金融资产投资公司资管产品 +822178 公募基金+822179 私募机构私募基金 +82217a 其他特定目的载体
            // 82127a 其他特定目的载体:客户二级类型=C0509其他特定目的载体
            if (DictCodeListExt2.客户二级类型_其他特定目的载体.getValue().equals(pos.getCustomerType2())) {

                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_82127A_QTTDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821270_TDMDZT,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            // 800000 负债及权益合计 = 810000 负债合计+820000权益合计
            // 820000 权益合计=821000实收本金+822000资本公积+823000未分配利润
            // 821000 实收资本 = 821100优先级+821200劣后级
            // 821200 劣后级 = 821210 住户   +822120 广义政府+822130 非金融企业+822140 银行业存款类金融机构
            //               +822150 银行业非存款类金融机构 +822160 非银行业金融机构  +822170 特定目的载体 +822180 境外
            // 821280 境外:客户二级类型=C0509境外
            if (pos.getCustomerType2() != null && pos.getCustomerType2().contains(DictCodeListExt2.客户一级类型_境外.getValue())) {


                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821280_JW,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821200_LHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_821000_SSBJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_820000_QYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());
                setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_800000_FZJQYHJ,
                        reportType, rateMap, source, null, massageMap, pos.getEndqtysum());

            }
            if (StringUtils.isNotBlank(svrMonthSubtractionLogic)) {
                if (StringUtil.equals(DictCodeListExt1.是或否_否.getValue(), svrMonthSubtractionLogic)) {
                    buildSvrMonthSubtraction(result, deptMap.get(pos.getInstitutionCode()), pos.getCcy());
                }
            }
        }
        // 单产品报表
        if (isOnePrd) {

            // 设置资产池代码 和机构代码
            //  START add by wujian 20200317 多法人改造--单产品报表生成 区分部门  增加createDept值的判断
            String pbcProdId = "";
            String prodId="";
            String orgCode = "";
            String createDept = "";
            if (!org.springframework.util.CollectionUtils.isEmpty(posAssetList)) {
                pbcProdId = posAssetList.get(0).getAssetPoolCode();
                prodId = posAssetList.get(0).getProdid();
                orgCode = posAssetList.get(0).getOrgCode();
                createDept = posAssetList.get(0).getCreateDept();
            }
            if (StringUtil.isEmpty(pbcProdId) && !org.springframework.util.CollectionUtils.isEmpty(posProdList)) {
                pbcProdId = posProdList.get(0).getAssetPoolCode();
                prodId = posProdList.get(0).getProdid();
                orgCode = posProdList.get(0).getOrgCode();
                createDept = posProdList.get(0).getInstitutionCode();
            }
            pbcReport.setPbcProdId(pbcProdId);
            pbcReport.setProdId(prodId);
            pbcReport.setOrgCode(orgCode);
            pbcReport.setCreateDept(createDept);
            //  END add by wujian 20200317 多法人改造--单产品报表生成 区分部门

            // 生成修改未报送
            // 数据的状态:默认新建
            String status = DictCodeListExt2.报送数据状态_新建.getValue();
             // 没有已报送的数据
                // 没有头寸数据则不生成报表
                if (org.springframework.util.CollectionUtils.isEmpty(posAssetList)
                        && org.springframework.util.CollectionUtils.isEmpty(posProdList)) {
                    pbcReport.setPbcProdId(prodAssetRegList.get(0).getPbcAccountId());
                    pbcReport.setProdId(prodAssetRegList.get(0).getProdid());
                    pbcReport.setOrgCode(prodAssetRegList.get(0).getOrgCode());
                    pbcReport.setGen(false);
                } else {
                    // 没有已报送的数据则生成新建数据
                    status = DictCodeListExt2.报送数据状态_新建.getValue();
                }

            pbcReport.setStatus(status);
            //报送截止日
            Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
            List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG05资管产品资产负债信息.getValue(),
                    pbcReport.getStatus(), dateMap, pbcReport.getEnddate(), SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
            if (!StringUtil.isEmptyList(dateList)) {
                pbcReport.setPbcSubmitDeadline(dateList.get(0));
                pbcReport.setSubmitDeadline(dateList.get(1));
            }
            //数据检查用字段，不入库
            if(map.containsKey(pbcReport.getProdId())){
                pbcReport.setPbcPordIdOut(map.get(pbcReport.getProdId()));
            }else {
                if(pbcReport.getPbcProdId().contains("INS")){
                    pbcReport.setPbcPordIdOut(pbcReport.getPbcProdId().substring(3));
                }else {
                    pbcReport.setPbcPordIdOut(pbcReport.getPbcProdId());
                }
            }

        }


        // 插入数据，先删除明细表数据，再删除主表数据
        //WFPLR倒剪数据处理
        if (!StringUtil.isEmptyList(assetDetailList)) {
            assetDetailList.add(pbcMonthReportDetailAssetUtil.buildAssetDetailwfplr(PbcReportProperycode.AMT_823000_WFPLR, result.get(PbcReportProperycode.AMT_823000_WFPLR).getbCny(), generateDate));

        }

        //组装数据汇总项并且入库明细表
        buildSumValAndInster(result, null, pbcReport, !org.springframework.util.CollectionUtils.isEmpty(posAssetList) ||
                        !org.springframework.util.CollectionUtils.isEmpty(posProdList), StringUtils.isNotBlank(svrMonthSubtractionLogic) && StringUtil.equals(DictCodeListExt1.是或否_是.getValue(), svrMonthSubtractionLogic)
                , rateMap, source, prodAssetRegList,rezg05map, insertList);

        // 返回其他负债
        return reback;
    }

    /**
     * 新增资产多法人统一入口(产品头寸入口)
     *
     * @param pos
     * @param dto
     * @param deptDto
     * @param code
     * @param rateMap
     * @param source
     * @param hxl
     * @param assetMap
     */
    private void setPrdReportValue(PbcMonthPrdPostionDto pos, Map dto,
                                   Map deptDto, String code, String rep_code, Map<String, BigDecimal> rateMap, String source, String hxl, Map assetMap, BigDecimal amt) {

        if (PbcReportType.PBC_REPORT_2_1.equals(rep_code) || PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT.equals(rep_code)) {

            setAssetDebtDetailValue((PbcColReportAssetDebtDetailDto) dto.get(code), pos.getCcy(),
                    amt, rateMap, (Date) assetMap.get("endDate"), source);
            if (PbcMonthReportDetailAssetUtil.isAssetPrd(code) && PbcReportType.PBC_REPORT_2_1.equals(rep_code)) {
                assetDetailList.add(PbcMonthReportDetailAssetUtil.buildAssetDetailPrd(pos, dto.get(code), code, amt));
            }
            if (null != deptDto && null != deptDto.get(code)) {
                setAssetDebtDetailValue((PbcColReportAssetDebtDetailDto) deptDto.get(code), pos.getCcy(),
                        amt, rateMap, (Date) assetMap.get("endDate"), source);
            }

        }

    }

    /**
     * 功能:汇率折算人民币 考虑外币，不考虑多币种
     *
     * @param dto
     * @param ccy
     * @param value
     * @param rateMap
     * @param generateDate void
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @since 1.6.0
     */
    public static void setAssetDebtDetailValue(PbcColReportAssetDebtDetailDto dto, String ccy, BigDecimal value, Map<String,
            BigDecimal> rateMap, Date generateDate, String source) {
        BigDecimal cny = ChbCcyConvertUtil.exchangeMoney(
                ccy, DictCodeListExt1.币种_中债_人民币.getValue(), generateDate, value, rateMap, source);
        //原币
        dto.setoCny(dto.getoCny().add(value));
        //折人名币
        dto.setOthToCny(dto.getOthToCny().add(cny));
        //本位币
        dto.setbCny(dto.getbCny().add(cny));

    }

    /**
     * 功能:根据报表类型报表日期生成人行报表主数据
     *
     * @param reporttype 报表类型
     * @param
     * @return PbcMonthReport
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @since 1.6.0
     */
    private PbcColReport genMonthReportByReporttype(String reporttype, Date reportDate,
                                                      boolean isEdit, boolean isOnePrd) {
        PbcColReport pbcMonthReport = new PbcColReport();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reportDate);
        // 获取上月的第一天
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date reportStartDate = calendar.getTime();
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date reportEndDate = calendar.getTime();
        // 报表ID
        pbcMonthReport.setReportuuid(IDGenerator.generate(36));
        // 报表类型
        pbcMonthReport.setReporttype(DictCodeListExt2.采集平台资管产品资产负债信息.getValue());
        // 起止日期
        pbcMonthReport.setStartdate(reportStartDate);
        pbcMonthReport.setEnddate(reportEndDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        pbcMonthReport.setTermid(sdf.format(reportStartDate));
        // 数据日期
        pbcMonthReport.setStatusDate(reportDate);
        // 制表日期
        // 发行机构代码取值
        pbcMonthReport.setIssuePartyId(CommonUtil.selectBankCode());
        pbcMonthReport.setIssuePartyName(CommonUtil.selectBankCode());
        // 用户信息
        pbcMonthReport.setOrgCode(CommonUtil.getOrgCode());
        pbcMonthReport.setCreateDept(CommonUtil.getDeptCode());
        pbcMonthReport.setCreateUser(CommonUtil.getUserName());
        pbcMonthReport.setUpdateUser(CommonUtil.getUserName());
        // 数据状态
        // 根据是否编辑处理数据状态
        pbcMonthReport.setGen(true);
        pbcMonthReport.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
        // 报送时点
        // 当前批次报送时点集合
        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG05资管产品资产负债信息.getValue(),
                    pbcMonthReport.getStatus(), dateMap, pbcMonthReport.getEnddate(), SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
            if (!StringUtil.isEmptyList(dateList)) {
                pbcMonthReport.setPbcSubmitDeadline(dateList.get(0));
                pbcMonthReport.setSubmitDeadline(dateList.get(1));
         }
        ChbOrgRegistBankCode bankCode = CommonUtil.getUserBank();
        String code = CommonUtil.selectBankCode();
        String name = CommonUtil.selectBankCodeName();
        if (null != bankCode) {
            code = bankCode.getRegistBankCode();
            name = bankCode.getBelongInstitutionName();
        }
        // 发行机构代码取值
        pbcMonthReport.setIssuePartyId(code);
        pbcMonthReport.setIssuePartyName(name);
        return pbcMonthReport;
    }


    /**
     * 模板组装 根据模板对应数据以及表路径获取对应数据
     *
     * @param generateDate
     * @param
     * @param reportType
     * @param
     * @param size
     * @param uuid
     * @return
     */
    private Map genAssetMonthReportemplate(Date generateDate, String reportType, int size, String uuid) {
        Map result = new HashMap();
        // 查询产品月报需要统计的项
        List<PbcReportTemplateDto> listKey = pbcReportTemplateMapper.selectByReporttype(reportType,
                DictCodeListExt1.维护数据状态_有效.getValue());
        if (listKey.size() != size || listKey.size() == 0) {
            logger.error("月报模板配置错误，请检查");
            return null;
        }

        for (PbcReportTemplateDto k : listKey) {

            //2-1模板使用类型
            if (PbcReportType.PBC_REPORT_2_1.equals(reportType) || PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT.equals(reportType)) {
                PbcColReportAssetDebtDetailDto detail = new PbcColReportAssetDebtDetailDto();
                detail.setDetailuuid(IDGenerator.generate(36));
                detail.setPropertycode(k.getPropertycode());
                detail.setReportuuid(uuid);
                detail.setbCny(BigDecimal.ZERO);
                detail.setoCny(BigDecimal.ZERO);
                detail.setOthToCny(BigDecimal.ZERO);
                result.put(k.getPropertycode(), detail);
            }
        }

        return result;
    }


    /**
     * 新增资产多法人统一入口
     *
     * @param pos
     * @param dto
     * @param deptDto
     * @param code
     * @param rateMap
     * @param source
     * @param hxl
     * @param assetMap
     */
    private void setAssetReportValue(PbcMonthAssetPositionDto pos, Map dto,
                                     Map deptDto, String code, String rep_code, Map<String, BigDecimal> rateMap, String source, String hxl, Map assetMap) {


        if (PbcReportType.PBC_REPORT_2_1.equals(rep_code) || PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT.equals(rep_code)) {

            //相同科目存在取AMT或者INTAMT的情况
            BigDecimal amt = pos.getAmt().add(pos.getIntAmt());
            if (StringUtil.isNotEmpty(hxl)&&StringUtil.equals(hxl,"01")) {
                amt = pos.getAmt();
            }else if(StringUtil.isNotEmpty(hxl)&&StringUtil.equals(hxl,"02")){
                amt = pos.getIntAmt();
            }
            //汇总数据计算
            setAssetDebtDetailValue((PbcColReportAssetDebtDetailDto) dto.get(code), pos.getCcy(), amt, rateMap, (Date) assetMap.get("endDate"), source);
            if (PbcMonthReportDetailAssetUtil.isAssetDate(code) && PbcReportType.PBC_REPORT_2_1.equals(rep_code)) {
                assetDetailList.add(PbcMonthReportDetailAssetUtil.buildAssetDetail(pos, dto.get(code), code, hxl));
            }
            if (null != deptDto && null != deptDto.get(pos.getInstitutionCode())) {
                setAssetDebtDetailValue(((Map<String, PbcColReportAssetDebtDetailDto>) deptDto.get(pos.getInstitutionCode())).get(code), pos.getCcy(), amt, rateMap, (Date) assetMap.get("endDate"), source);
            }
        }

    }

    public static void setAssetDebtDetailValueSubtract(PbcColReportAssetDebtDetailDto dto, String ccy, BigDecimal value, Map<String,
            BigDecimal> rateMap, Date generateDate, String source) {
        BigDecimal cny = ChbCcyConvertUtil.exchangeMoney(
                ccy, DictCodeListExt1.币种_中债_人民币.getValue(), generateDate, value, rateMap, source);
        //原币
        dto.setoCny(dto.getoCny().subtract(value));
        //折人名币
        dto.setOthToCny(dto.getOthToCny().subtract(cny));
        //本位币
        dto.setbCny(dto.getbCny().subtract(cny));

    }

    private void buildSvrMonthSubtraction(Map<String, PbcColReportAssetDebtDetailDto> dto, Map<String, PbcColReportAssetDebtDetailDto> deptDto, String ccy) {

        build21report(dto, ccy);

        if (null != deptDto) {
            build21report(deptDto, ccy);
        }

    }


    private void build21report(Map<String, PbcColReportAssetDebtDetailDto> dto, String ccy) {
        PbcColReportAssetDebtDetailDto line823000 = dto.get(PbcReportProperycode.AMT_823000_WFPLR);
        PbcColReportAssetDebtDetailDto line700000 = dto.get(PbcReportProperycode.AMT_700000_ZCHJ);
        PbcColReportAssetDebtDetailDto line810000 = dto.get(PbcReportProperycode.AMT_810000_FZHJ);
        PbcColReportAssetDebtDetailDto line821000 = dto.get(PbcReportProperycode.AMT_821000_SSBJ);
        PbcColReportAssetDebtDetailDto line822000 = dto.get(PbcReportProperycode.AMT_822000_ZBGJ);
        PbcColReportAssetDebtDetailDto line820000 = dto.get(PbcReportProperycode.AMT_820000_QYHJ);
        PbcColReportAssetDebtDetailDto line800000 = dto.get(PbcReportProperycode.AMT_800000_FZJQYHJ);

        PbcColReportAssetDebtDetailDto line824000 = dto.get(PbcReportProperycode.AMT_824000_QTZHSY);


        line823000.setoCny(line700000.getoCny().subtract(line810000.getoCny()).subtract(line821000.getoCny()).subtract(line824000.getoCny()));
        line823000.setbCny(line700000.getbCny().subtract(line810000.getbCny()).subtract(line821000.getbCny()).subtract(line824000.getbCny()));
        line823000.setOthToCny(line700000.getOthToCny().subtract(line810000.getOthToCny()).subtract(line821000.getOthToCny()).subtract(line824000.getOthToCny()));



        line820000.setoCny(line821000.getoCny().add(line824000.getoCny()).add(line823000.getoCny()));
        line820000.setbCny(line821000.getbCny().add(line824000.getbCny()).add(line823000.getbCny()));
        line820000.setOthToCny(line821000.getOthToCny().add(line824000.getOthToCny()).add(line823000.getOthToCny()));



        line800000.setoCny(line810000.getoCny().add(line820000.getoCny()));
        line800000.setbCny(line810000.getbCny().add(line820000.getbCny()));
        line800000.setOthToCny(line810000.getOthToCny().add(line820000.getOthToCny()));

    }

    /**
     * 组装数据汇总项
     *
     * @param result
     * @param deptCode
     * @param pbcReport
     * @param
     */
    private void buildSumValAndInster(Map result, String deptCode,
                                      PbcColReport pbcReport, Boolean insertFlag, Boolean subtractionFlag, Map<String, BigDecimal> rateMap, String source, List<PbcMonthReport> prodAssetRegList,
                                      Map<String, ZG05PbcColPrdAssetDebtDto>  rezg05map, List<ZG05PbcColPrdAssetDebtDto> insertList) {


        if (DictCodeListExt2.采集平台资管产品资产负债信息.getValue().equals(pbcReport.getReporttype())) {

            PbcColReportAssetDebtDetailDto line700000 = (PbcColReportAssetDebtDetailDto) result.get(PbcReportProperycode.AMT_700000_ZCHJ);
            PbcColReportAssetDebtDetailDto line800000 = (PbcColReportAssetDebtDetailDto) result.get(PbcReportProperycode.AMT_800000_FZJQYHJ);
            PbcColReportAssetDebtDetailDto line810000 = (PbcColReportAssetDebtDetailDto) result.get(PbcReportProperycode.AMT_810000_FZHJ);
            PbcColReportAssetDebtDetailDto line811000 = (PbcColReportAssetDebtDetailDto) result.get(PbcReportProperycode.AMT_811000_DK);
            PbcColReportAssetDebtDetailDto line812000 = (PbcColReportAssetDebtDetailDto) result.get(PbcReportProperycode.AMT_812000_ZCZCZQ);
            PbcColReportAssetDebtDetailDto line813000 = (PbcColReportAssetDebtDetailDto) result.get(PbcReportProperycode.AMT_813000_JRYSP);
            PbcColReportAssetDebtDetailDto line814000 = (PbcColReportAssetDebtDetailDto) result.get(PbcReportProperycode.AMT_814000_YFZK);
            PbcColReportAssetDebtDetailDto line815000 = (PbcColReportAssetDebtDetailDto) result.get(PbcReportProperycode.AMT_815000_QTFZ);
            PbcColReportAssetDebtDetailDto line820000 = (PbcColReportAssetDebtDetailDto) result.get(PbcReportProperycode.AMT_820000_QYHJ);

            if (subtractionFlag) {
                line815000.setbCny(line700000.getbCny().subtract(line820000.getbCny())
                        .subtract(line811000.getbCny()).subtract(line812000.getbCny())
                        .subtract(line813000.getbCny()).subtract(line814000.getbCny()));
                line815000.setoCny(line700000.getoCny().subtract(line820000.getoCny())
                        .subtract(line811000.getoCny()).subtract(line812000.getoCny())
                        .subtract(line813000.getoCny()).subtract(line814000.getoCny()));
                line815000.setOthToCny(line815000.getbCny());


            }
            // 810000 负债合计
            line810000.setoCny(line810000.getoCny().add(line815000.getoCny()));
            line810000.setOthToCny(line810000.getOthToCny().add(line815000.getOthToCny()));
            line810000.setbCny(line810000.getbCny().add(line815000.getbCny()));
            // 800000 负债及权益合计
            line800000.setoCny(line800000.getoCny().add(line815000.getoCny()));
            line800000.setOthToCny(line800000.getOthToCny().add(line815000.getOthToCny()));
            line800000.setbCny(line800000.getbCny().add(line815000.getbCny()));

        }

        //根据部门进行数据插入
        if (pbcReport.isGen()) {
            // 判断是否有头寸数据有则插入
            if (insertFlag) {

//                String[] code = getRegistCode(deptCode);

//                String uuid = IDGenerator.generate(36);

//                pbcReport.setReportuuid(uuid);
                pbcReport.setOrgCode(deptCode);
                // 插入主表数据
//                zg05PbcColPrdAssetDebtMapper.insert(pbcReport);

                // 插入资产负债表数据
//                Iterator<Map.Entry> entries = result.entrySet().iterator();
//                 while (entries.hasNext()) {
//                                    Map.Entry<String, PbcReportAssetDebtDetailDto> dtoMap = entries.next();
//                                    //add by leipeng 20200319  多法人改造
//                                    dtoMap.getValue().setReportuuid(uuid);
//                                    zg05PbcColPrdAssetDebtMapper.insertColBalance(dtoMap.getValue());
//                  }
                /**新增组装ZG05报送数据 */
                assemblyData(pbcReport,result,  rezg05map, insertList);

            }
        }

    }

    /**
     *
     * @param pbcReport  主表数据
     * @param result   明细数据
     */
    private  void   assemblyData(PbcColReport pbcReport,Map result,Map<String,ZG05PbcColPrdAssetDebtDto> rezg05map, List<ZG05PbcColPrdAssetDebtDto> insertList){

        //1.本位币
        ZG05PbcColPrdAssetDebtDto zg05BWB=new ZG05PbcColPrdAssetDebtDto();
        //复制共通属性
        BeanUtil.copyPropertiesIgnoreNull(zg05BWB, pbcReport);
        //币种
        zg05BWB.setCcy(DictCodeListExt1.币种_人行_本位币.getValue());
        zg05BWB.setStatusType(DictCodeListExt3.数据类型_人行_本外币合计据.getValue());
        zg05BWB.setReportuuid(IDGenerator.generate(36));
        mappingValueTosub(zg05BWB,result);
        if(rezg05map.containsKey(pbcReport.getPbcProdId())){
            ZG05PbcColPrdAssetDebtDto  hj05BWB=rezg05map.get(pbcReport.getPbcProdId());
            //处理BWB合计
            sumZg05Dto(zg05BWB,hj05BWB);
        }else{
            rezg05map.put(pbcReport.getPbcProdId(),zg05BWB);
        }


        //2.原币
        ZG05PbcColPrdAssetDebtDto zg05YB=new ZG05PbcColPrdAssetDebtDto();
         //复制报送信息
        BeanUtil.copyPropertiesIgnoreNull(zg05YB, pbcReport);
        //币种
        zg05YB.setCcy(pbcReport.getCcy());
        zg05YB.setStatusType(DictCodeListExt3.数据类型_人行_原币种数据.getValue());
        zg05YB.setReportuuid(IDGenerator.generate(36));

        mappingValueTosub(zg05YB,result);
        // 调整入库逻辑
        //zg05PbcColPrdAssetDebtMapper.insertSelective(zg05YB);
        insertList.add(zg05YB);

        //3.折人民币
        ZG05PbcColPrdAssetDebtDto zg05CNY=new ZG05PbcColPrdAssetDebtDto();
        //复制报送信息
        BeanUtil.copyPropertiesIgnoreNull(zg05CNY, pbcReport);
        //币种
        zg05CNY.setCcy(pbcReport.getCcy());
        zg05CNY.setStatusType(DictCodeListExt3.数据类型_人行_折人民币数据.getValue());
        zg05CNY.setReportuuid(IDGenerator.generate(36));

        mappingValueTosub(zg05CNY,result);
        //  调整入库逻辑
        //zg05PbcColPrdAssetDebtMapper.insertSelective(zg05CNY);
        insertList.add(zg05CNY);

    }

    /**
     * 合计ZG05本位币的值
     */
    private  void  sumZg05Dto(ZG05PbcColPrdAssetDebtDto dto1,ZG05PbcColPrdAssetDebtDto dto2){
        dto2.setA0000(dto2.getA0000().add(dto1.getA0000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA1000(dto2.getA1000().add(dto1.getA1000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA2000(dto2.getA2000().add(dto1.getA2000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA2100(dto2.getA2100().add(dto1.getA2100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA2110(dto2.getA2110().add(dto1.getA2110()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA2120(dto2.getA2120().add(dto1.getA2120()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA2200(dto2.getA2200().add(dto1.getA2200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA2210(dto2.getA2210().add(dto1.getA2210()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA2220(dto2.getA2220().add(dto1.getA2220()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA3000(dto2.getA3000().add(dto1.getA3000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA3100(dto2.getA3100().add(dto1.getA3100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA3200(dto2.getA3200().add(dto1.getA3200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA4000(dto2.getA4000().add(dto1.getA4000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA4100(dto2.getA4100().add(dto1.getA4100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA4200(dto2.getA4200().add(dto1.getA4200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA4300(dto2.getA4300().add(dto1.getA4300()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA4400(dto2.getA4400().add(dto1.getA4400()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA4500(dto2.getA4500().add(dto1.getA4500()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA4600(dto2.getA4600().add(dto1.getA4600()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA4700(dto2.getA4700().add(dto1.getA4700()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA4800(dto2.getA4800().add(dto1.getA4800()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5000(dto2.getA5000().add(dto1.getA5000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5100(dto2.getA5100().add(dto1.getA5100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5200(dto2.getA5200().add(dto1.getA5200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5210(dto2.getA5210().add(dto1.getA5210()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5220(dto2.getA5220().add(dto1.getA5220()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5230(dto2.getA5230().add(dto1.getA5230()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5240(dto2.getA5240().add(dto1.getA5240()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5250(dto2.getA5250().add(dto1.getA5250()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5260(dto2.getA5260().add(dto1.getA5260()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5261(dto2.getA5261().add(dto1.getA5261()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5270(dto2.getA5270().add(dto1.getA5270()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5271(dto2.getA5271().add(dto1.getA5271()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5272(dto2.getA5272().add(dto1.getA5272()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5273(dto2.getA5273().add(dto1.getA5273()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5274(dto2.getA5274().add(dto1.getA5274()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5275(dto2.getA5275().add(dto1.getA5275()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5276(dto2.getA5276().add(dto1.getA5276()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5277(dto2.getA5277().add(dto1.getA5277()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5278(dto2.getA5278().add(dto1.getA5278()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5279(dto2.getA5279().add(dto1.getA5279()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA527a(dto2.getA527a().add(dto1.getA527a()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA5280(dto2.getA5280().add(dto1.getA5280()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA6000(dto2.getA6000().add(dto1.getA6000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA6100(dto2.getA6100().add(dto1.getA6100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA6200(dto2.getA6200().add(dto1.getA6200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA6300(dto2.getA6300().add(dto1.getA6300()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA6400(dto2.getA6400().add(dto1.getA6400()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA6500(dto2.getA6500().add(dto1.getA6500()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA6600(dto2.getA6600().add(dto1.getA6600()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA6700(dto2.getA6700().add(dto1.getA6700()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7000(dto2.getA7000().add(dto1.getA7000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7100(dto2.getA7100().add(dto1.getA7100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7110(dto2.getA7110().add(dto1.getA7110()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7120(dto2.getA7120().add(dto1.getA7120()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7200(dto2.getA7200().add(dto1.getA7200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7210(dto2.getA7210().add(dto1.getA7210()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7220(dto2.getA7220().add(dto1.getA7220()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7230(dto2.getA7230().add(dto1.getA7230()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7240(dto2.getA7240().add(dto1.getA7240()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7250(dto2.getA7250().add(dto1.getA7250()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7260(dto2.getA7260().add(dto1.getA7260()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7270(dto2.getA7270().add(dto1.getA7270()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7280(dto2.getA7280().add(dto1.getA7280()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7290(dto2.getA7290().add(dto1.getA7290()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA72a0(dto2.getA72a0().add(dto1.getA72a0()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7300(dto2.getA7300().add(dto1.getA7300()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7310(dto2.getA7310().add(dto1.getA7310()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7320(dto2.getA7320().add(dto1.getA7320()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA7400(dto2.getA7400().add(dto1.getA7400()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA8000(dto2.getA8000().add(dto1.getA8000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA8100(dto2.getA8100().add(dto1.getA8100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA8200(dto2.getA8200().add(dto1.getA8200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setA9000(dto2.getA9000().add(dto1.getA9000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setAa000(dto2.getAa000().add(dto1.getAa000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setAb000(dto2.getAb000().add(dto1.getAb000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setAc000(dto2.getAc000().add(dto1.getAc000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setD0000(dto2.getD0000().add(dto1.getD0000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB0000(dto2.getB0000().add(dto1.getB0000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1000(dto2.getB1000().add(dto1.getB1000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1100(dto2.getB1100().add(dto1.getB1100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1200(dto2.getB1200().add(dto1.getB1200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1210(dto2.getB1210().add(dto1.getB1210()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1220(dto2.getB1220().add(dto1.getB1220()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1230(dto2.getB1230().add(dto1.getB1230()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1240(dto2.getB1240().add(dto1.getB1240()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1250(dto2.getB1250().add(dto1.getB1250()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1260(dto2.getB1260().add(dto1.getB1260()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1261(dto2.getB1261().add(dto1.getB1261()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1270(dto2.getB1270().add(dto1.getB1270()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1271(dto2.getB1271().add(dto1.getB1271()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1272(dto2.getB1272().add(dto1.getB1272()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1273(dto2.getB1273().add(dto1.getB1273()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1274(dto2.getB1274().add(dto1.getB1274()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1275(dto2.getB1275().add(dto1.getB1275()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1276(dto2.getB1276().add(dto1.getB1276()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1277(dto2.getB1277().add(dto1.getB1277()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1278(dto2.getB1278().add(dto1.getB1278()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1279(dto2.getB1279().add(dto1.getB1279()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB127a(dto2.getB127a().add(dto1.getB127a()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB1280(dto2.getB1280().add(dto1.getB1280()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB2000(dto2.getB2000().add(dto1.getB2000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB3000(dto2.getB3000().add(dto1.getB3000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB3100(dto2.getB3100().add(dto1.getB3100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB3200(dto2.getB3200().add(dto1.getB3200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB4000(dto2.getB4000().add(dto1.getB4000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setB5000(dto2.getB5000().add(dto1.getB5000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC0000(dto2.getC0000().add(dto1.getC0000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1000(dto2.getC1000().add(dto1.getC1000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1100(dto2.getC1100().add(dto1.getC1100()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1110(dto2.getC1110().add(dto1.getC1110()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1120(dto2.getC1120().add(dto1.getC1120()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1130(dto2.getC1130().add(dto1.getC1130()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1140(dto2.getC1140().add(dto1.getC1140()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1150(dto2.getC1150().add(dto1.getC1150()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1160(dto2.getC1160().add(dto1.getC1160()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1170(dto2.getC1170().add(dto1.getC1170()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1171(dto2.getC1171().add(dto1.getC1171()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1172(dto2.getC1172().add(dto1.getC1172()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1173(dto2.getC1173().add(dto1.getC1173()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1174(dto2.getC1174().add(dto1.getC1174()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1175(dto2.getC1175().add(dto1.getC1175()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1176(dto2.getC1176().add(dto1.getC1176()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1177(dto2.getC1177().add(dto1.getC1177()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1178(dto2.getC1178().add(dto1.getC1178()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1179(dto2.getC1179().add(dto1.getC1179()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC117a(dto2.getC117a().add(dto1.getC117a()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1180(dto2.getC1180().add(dto1.getC1180()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1200(dto2.getC1200().add(dto1.getC1200()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1210(dto2.getC1210().add(dto1.getC1210()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1220(dto2.getC1220().add(dto1.getC1220()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1230(dto2.getC1230().add(dto1.getC1230()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1240(dto2.getC1240().add(dto1.getC1240()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1250(dto2.getC1250().add(dto1.getC1250()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1260(dto2.getC1260().add(dto1.getC1260()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1270(dto2.getC1270().add(dto1.getC1270()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1271(dto2.getC1271().add(dto1.getC1271()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1272(dto2.getC1272().add(dto1.getC1272()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1273(dto2.getC1273().add(dto1.getC1273()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1274(dto2.getC1274().add(dto1.getC1274()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1275(dto2.getC1275().add(dto1.getC1275()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1276(dto2.getC1276().add(dto1.getC1276()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1277(dto2.getC1277().add(dto1.getC1277()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1278(dto2.getC1278().add(dto1.getC1278()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1279(dto2.getC1279().add(dto1.getC1279()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC127a(dto2.getC127a().add(dto1.getC127a()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC1280(dto2.getC1280().add(dto1.getC1280()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC3000(dto2.getC3000().add(dto1.getC3000()).setScale(2, BigDecimal.ROUND_HALF_UP));
        dto2.setC4000(dto2.getC4000().add(dto1.getC4000()).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     *处理明细数据映射到报送dto
     */
    private  void  mappingValueTosub(ZG05PbcColPrdAssetDebtDto dto,Map result){
        Iterator<Map.Entry> entries = result.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, PbcColReportAssetDebtDetailDto> dtoMap = entries.next();
            PbcColReportAssetDebtDetailDto  detailDto=dtoMap.getValue();
            BigDecimal value=BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
            if(StringUtil.equals(DictCodeListExt3.数据类型_人行_原币种数据.getValue(),dto.getStatusType())){
                value=detailDto.getoCny().setScale(2, BigDecimal.ROUND_HALF_UP);
            }else if(StringUtil.equals(DictCodeListExt3.数据类型_人行_折人民币数据.getValue(),dto.getStatusType())){
                value= detailDto.getOthToCny().setScale(2, BigDecimal.ROUND_HALF_UP);
            }else if(StringUtil.equals(DictCodeListExt3.数据类型_人行_本外币合计据.getValue(),dto.getStatusType())){
                value = detailDto.getbCny().setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            if(StringUtil.equals("AMT_700000_ZCHJ",detailDto.getPropertycode())){
                dto.setA0000(value);
            }else if(StringUtil.equals("AMT_710000_XJ",detailDto.getPropertycode())){
                dto.setA1000(value);
            }else if(StringUtil.equals("AMT_720000_CK",detailDto.getPropertycode())){
                dto.setA2000(value);
            }else if(StringUtil.equals("AMT_721000_JNCK",detailDto.getPropertycode())){
                dto.setA2100(value);
            }else if(StringUtil.equals("AMT_721100_HQCK",detailDto.getPropertycode())){
                dto.setA2110(value);
            }else if(StringUtil.equals("AMT_721200_DQHQTCK",detailDto.getPropertycode())){
                dto.setA2120(value);
            }else if(StringUtil.equals("AMT_722000_JWCK",detailDto.getPropertycode())){
                dto.setA2200(value);
            }else if(StringUtil.equals("AMT_722100_HQCK",detailDto.getPropertycode())){
                dto.setA2210(value);
            }else if(StringUtil.equals("AMT_722200_DQHQTCK",detailDto.getPropertycode())){
                dto.setA2220(value);
            }else if(StringUtil.equals("AMT_730000_KZRCD",detailDto.getPropertycode())){
                dto.setA3000(value);
            }else if(StringUtil.equals("AMT_731000_TYCD",detailDto.getPropertycode())){
                dto.setA3100(value);
            }else if(StringUtil.equals("AMT_732000_DECD",detailDto.getPropertycode())){
                dto.setA3200(value);
            }else if(StringUtil.equals("AMT_740000_ZWZQ",detailDto.getPropertycode())){
                dto.setA4000(value);
            }else if(StringUtil.equals("AMT_741000_ZFZQ",detailDto.getPropertycode())){
                dto.setA4100(value);
            }else if(StringUtil.equals("AMT_742000_ZYYXZQ",detailDto.getPropertycode())){
                dto.setA4200(value);
            }else if(StringUtil.equals("AMT_743000_ZCXJRZQ",detailDto.getPropertycode())){
                dto.setA4300(value);
            }else if(StringUtil.equals("AMT_744000_QTJRZQ",detailDto.getPropertycode())){
                dto.setA4400(value);
            }else if(StringUtil.equals("AMT_745000_QYZQ",detailDto.getPropertycode())){
                dto.setA4500(value);
            }else if(StringUtil.equals("AMT_746000_PJ",detailDto.getPropertycode())){
                dto.setA4600(value);
            }else if(StringUtil.equals("AMT_747000_ZCZCZQ",detailDto.getPropertycode())){
                dto.setA4700(value);
            }else if(StringUtil.equals("AMT_748000_JWZQ",detailDto.getPropertycode())){
                dto.setA4800(value);
            }else if(StringUtil.equals("AMT_750000_DK",detailDto.getPropertycode())){
                dto.setA5000(value);
            }else if(StringUtil.equals("AMT_751000_CHGHCJWDK",detailDto.getPropertycode())){
                dto.setA5100(value);
            }else if(StringUtil.equals("AMT_752000_HGHCJ",detailDto.getPropertycode())){
                dto.setA5200(value);
            }else if(StringUtil.equals("AMT_752100_ZH",detailDto.getPropertycode())){
                dto.setA5210(value);
            }else if(StringUtil.equals("AMT_752200_GYZF",detailDto.getPropertycode())){
                dto.setA5220(value);
            }else if(StringUtil.equals("AMT_752300_FJRQY",detailDto.getPropertycode())){
                dto.setA5230(value);
            }else if(StringUtil.equals("AMT_752400_YXYCKLJRJG",detailDto.getPropertycode())){
                dto.setA5240(value);
            }else if(StringUtil.equals("AMT_752500_YXYFCKLJRJG",detailDto.getPropertycode())){
                dto.setA5250(value);
            }else if(StringUtil.equals("AMT_752600_FYXYJRJG",detailDto.getPropertycode())){
                dto.setA5260(value);
            }else if(StringUtil.equals("AMT_752610_QZZYJYDSF",detailDto.getPropertycode())){
                dto.setA5261(value);
            }else if(StringUtil.equals("AMT_752700_TDMDZT",detailDto.getPropertycode())){
                dto.setA5270(value);
            }else if(StringUtil.equals("AMT_752710_YXFBBLC",detailDto.getPropertycode())){
                dto.setA5271(value);
            }else if(StringUtil.equals("AMT_752720_XTGSZGCP",detailDto.getPropertycode())){
                dto.setA5272(value);
            }else if(StringUtil.equals("AMT_752730_ZQGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setA5273(value);
            }else if(StringUtil.equals("AMT_752740_JJGLGSJQZGSZH",detailDto.getPropertycode())){
                dto.setA5274(value);
            }else if(StringUtil.equals("AMT_752750_QHGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setA5275(value);
            }else if(StringUtil.equals("AMT_752760_BXZGCP",detailDto.getPropertycode())){
                dto.setA5276(value);
            }else if(StringUtil.equals("AMT_752770_JRZCTZGSZGCP",detailDto.getPropertycode())){
                dto.setA5277(value);
            }else if(StringUtil.equals("AMT_752780_GMJJ",detailDto.getPropertycode())){
                dto.setA5278(value);
            }else if(StringUtil.equals("AMT_752790_SMJGSMJJ",detailDto.getPropertycode())){
                dto.setA5279(value);
            }else if(StringUtil.equals("AMT_7527a0_QTTDMDZT",detailDto.getPropertycode())){
                dto.setA527a(value);
            }else if(StringUtil.equals("AMT_752800_JW",detailDto.getPropertycode())){
                dto.setA5280(value);
            }else if(StringUtil.equals("AMT_760000_ZCSYQ",detailDto.getPropertycode())){
                dto.setA6000(value);
            }else if(StringUtil.equals("AMT_761000_DKSYQ",detailDto.getPropertycode())){
                dto.setA6100(value);
            }else if(StringUtil.equals("AMT_762000_ZWZQSYQ",detailDto.getPropertycode())){
                dto.setA6200(value);
            }else if(StringUtil.equals("AMT_763000_YSZKSYQ",detailDto.getPropertycode())){
                dto.setA6300(value);
            }else if(StringUtil.equals("AMT_764000_QTZQSYQ",detailDto.getPropertycode())){
                dto.setA6400(value);
            }else if(StringUtil.equals("AMT_765000_GPSYQ",detailDto.getPropertycode())){
                dto.setA6500(value);
            }else if(StringUtil.equals("AMT_766000_QTGQSYQ",detailDto.getPropertycode())){
                dto.setA6600(value);
            }else if(StringUtil.equals("AMT_767000_WQZCSYQ",detailDto.getPropertycode())){
                dto.setA6700(value);
            }else if(StringUtil.equals("AMT_770000_GQJTDMDZTFE",detailDto.getPropertycode())){
                dto.setA7000(value);
            }else if(StringUtil.equals("AMT_771000_GP",detailDto.getPropertycode())){
                dto.setA7100(value);
            }else if(StringUtil.equals("AMT_771100_FJRQYGP",detailDto.getPropertycode())){
                dto.setA7110(value);
            }else if(StringUtil.equals("AMT_771200_JRJGGP",detailDto.getPropertycode())){
                dto.setA7120(value);
            }else if(StringUtil.equals("AMT_772000_TDMDZTFE",detailDto.getPropertycode())){
                dto.setA7200(value);
            }else if(StringUtil.equals("AMT_772100_YXFBBLC",detailDto.getPropertycode())){
                dto.setA7210(value);
            }else if(StringUtil.equals("AMT_772200_XTGSZGCP",detailDto.getPropertycode())){
                dto.setA7220(value);
            }else if(StringUtil.equals("AMT_772300_ZQGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setA7230(value);
            }else if(StringUtil.equals("AMT_772400_JJGLGSJQZGSZH",detailDto.getPropertycode())){
                dto.setA7240(value);
            }else if(StringUtil.equals("AMT_772500_QHGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setA7250(value);
            }else if(StringUtil.equals("AMT_772600_BXZGCP",detailDto.getPropertycode())){
                dto.setA7260(value);
            }else if(StringUtil.equals("AMT_772700_JRZCTZGSZGCP",detailDto.getPropertycode())){
                dto.setA7270(value);
            }else if(StringUtil.equals("AMT_772800_GMJJ",detailDto.getPropertycode())){
                dto.setA7280(value);
            }else if(StringUtil.equals("AMT_772900_SMJGSMJJ",detailDto.getPropertycode())){
                dto.setA7290(value);
            }else if(StringUtil.equals("AMT_772a00_QTTDMDZT",detailDto.getPropertycode())){
                dto.setA72a0(value);
            }else if(StringUtil.equals("AMT_773000_QTGQ",detailDto.getPropertycode())){
                dto.setA7300(value);
            }else if(StringUtil.equals("AMT_773100_FJRQYGQ",detailDto.getPropertycode())){
                dto.setA7310(value);
            }else if(StringUtil.equals("AMT_773200_JRJGGQ",detailDto.getPropertycode())){
                dto.setA7320(value);
            }else if(StringUtil.equals("AMT_774000_JWGQ",detailDto.getPropertycode())){
                dto.setA7400(value);
            }else if(StringUtil.equals("AMT_780000_JRYSP",detailDto.getPropertycode())){
                dto.setA8000(value);
            }else if(StringUtil.equals("AMT_781000_JN",detailDto.getPropertycode())){
                dto.setA8100(value);
            }else if(StringUtil.equals("AMT_782000_JW",detailDto.getPropertycode())){
                dto.setA8200(value);
            }else if(StringUtil.equals("AMT_790000_YSZK",detailDto.getPropertycode())){
                dto.setA9000(value);
            }else if(StringUtil.equals("AMT_7a0000_HJ",detailDto.getPropertycode())){
                dto.setAa000(value);
            }else if(StringUtil.equals("AMT_7b0000_FJRZC",detailDto.getPropertycode())){
                dto.setAb000(value);
            }else if(StringUtil.equals("AMT_7K0000_ZCJZZB",detailDto.getPropertycode())){
                dto.setAc000(value);
            }else if(StringUtil.equals("AMT_800000_FZJQYHJ",detailDto.getPropertycode())){
                dto.setD0000(value);
            }else if(StringUtil.equals("AMT_810000_FZHJ",detailDto.getPropertycode())){
                dto.setB0000(value);
            }else if(StringUtil.equals("AMT_811000_DK",detailDto.getPropertycode())){
                dto.setB1000(value);
            }else if(StringUtil.equals("AMT_811100_CHGHCJWDK",detailDto.getPropertycode())){
                dto.setB1100(value);
            }else if(StringUtil.equals("AMT_811200_HGHCJ",detailDto.getPropertycode())){
                dto.setB1200(value);
            }else if(StringUtil.equals("AMT_811210_ZH",detailDto.getPropertycode())){
                dto.setB1210(value);
            }else if(StringUtil.equals("AMT_811220_GYZF",detailDto.getPropertycode())){
                dto.setB1220(value);
            }else if(StringUtil.equals("AMT_811230_FJRQY",detailDto.getPropertycode())){
                dto.setB1230(value);
            }else if(StringUtil.equals("AMT_811240_YXYCKLJRJG",detailDto.getPropertycode())){
                dto.setB1240(value);
            }else if(StringUtil.equals("AMT_811250_YXYFCKLJRJG",detailDto.getPropertycode())){
                dto.setB1250(value);
            }else if(StringUtil.equals("AMT_811260_FYXYJRJG",detailDto.getPropertycode())){
                dto.setB1260(value);
            }else if(StringUtil.equals("AMT_811261_QZZYJYDSF",detailDto.getPropertycode())){
                dto.setB1261(value);
            }else if(StringUtil.equals("AMT_811270_TDMDZT",detailDto.getPropertycode())){
                dto.setB1270(value);
            }else if(StringUtil.equals("AMT_811271_YXFBBLC",detailDto.getPropertycode())){
                dto.setB1271(value);
            }else if(StringUtil.equals("AMT_811272_XTGSZGCP",detailDto.getPropertycode())){
                dto.setB1272(value);
            }else if(StringUtil.equals("AMT_811273_ZQGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setB1273(value);
            }else if(StringUtil.equals("AMT_811274_JJGLGSJQZGSZH",detailDto.getPropertycode())){
                dto.setB1274(value);
            }else if(StringUtil.equals("AMT_811275_QHGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setB1275(value);
            }else if(StringUtil.equals("AMT_811276_BXZGCP",detailDto.getPropertycode())){
                dto.setB1276(value);
            }else if(StringUtil.equals("AMT_811277_JRZCTZGSZGCP",detailDto.getPropertycode())){
                dto.setB1277(value);
            }else if(StringUtil.equals("AMT_811278_GMJJ",detailDto.getPropertycode())){
                dto.setB1278(value);
            }else if(StringUtil.equals("AMT_811279_SMJGSMJJ",detailDto.getPropertycode())){
                dto.setB1279(value);
            }else if(StringUtil.equals("AMT_81127a_QTTDMDZT",detailDto.getPropertycode())){
                dto.setB127a(value);
            }else if(StringUtil.equals("AMT_811280_JW",detailDto.getPropertycode())){
                dto.setB1280(value);
            }else if(StringUtil.equals("AMT_812000_ZCZCZQ",detailDto.getPropertycode())){
                dto.setB2000(value);
            }else if(StringUtil.equals("AMT_813000_JRYSP",detailDto.getPropertycode())){
                dto.setB3000(value);
            }else if(StringUtil.equals("AMT_813100_JN",detailDto.getPropertycode())){
                dto.setB3100(value);
            }else if(StringUtil.equals("AMT_813200_JW",detailDto.getPropertycode())){
                dto.setB3200(value);
            }else if(StringUtil.equals("AMT_814000_YFZK",detailDto.getPropertycode())){
                dto.setB4000(value);
            }else if(StringUtil.equals("AMT_815000_QTFZ",detailDto.getPropertycode())){
                dto.setB5000(value);
            }else if(StringUtil.equals("AMT_820000_QYHJ",detailDto.getPropertycode())){
                dto.setC0000(value);
            }else if(StringUtil.equals("AMT_821000_SSBJ",detailDto.getPropertycode())){
                dto.setC1000(value);
            }else if(StringUtil.equals("AMT_821100_YXJ",detailDto.getPropertycode())){
                dto.setC1100(value);
            }else if(StringUtil.equals("AMT_821110_ZH",detailDto.getPropertycode())){
                dto.setC1110(value);
            }else if(StringUtil.equals("AMT_821120_GYZF",detailDto.getPropertycode())){
                dto.setC1120(value);
            }else if(StringUtil.equals("AMT_821130_FJRQY",detailDto.getPropertycode())){
                dto.setC1130(value);
            }else if(StringUtil.equals("AMT_821140_YXYCKLJRJG",detailDto.getPropertycode())){
                dto.setC1140(value);
            }else if(StringUtil.equals("AMT_821150_YXYFCKLJRJG",detailDto.getPropertycode())){
                dto.setC1150(value);
            }else if(StringUtil.equals("AMT_821160_FYXYJRJG",detailDto.getPropertycode())){
                dto.setC1160(value);
            }else if(StringUtil.equals("AMT_821170_TDMDZT",detailDto.getPropertycode())){
                dto.setC1170(value);
            }else if(StringUtil.equals("AMT_821171_YXFBBLC",detailDto.getPropertycode())){
                dto.setC1171(value);
            }else if(StringUtil.equals("AMT_821172_XTGSZGCP",detailDto.getPropertycode())){
                dto.setC1172(value);
            }else if(StringUtil.equals("AMT_821173_ZQGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setC1173(value);
            }else if(StringUtil.equals("AMT_821174_JJGLGSJQZGSZH",detailDto.getPropertycode())){
                dto.setC1174(value);
            }else if(StringUtil.equals("AMT_821175_QHGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setC1175(value);
            }else if(StringUtil.equals("AMT_821176_BXZGCP",detailDto.getPropertycode())){
                dto.setC1176(value);
            }else if(StringUtil.equals("AMT_821177_JRZCTZGSZGCP",detailDto.getPropertycode())){
                dto.setC1177(value);
            }else if(StringUtil.equals("AMT_821178_GMJJ",detailDto.getPropertycode())){
                dto.setC1178(value);
            }else if(StringUtil.equals("AMT_821179_SMJGSMJJ",detailDto.getPropertycode())){
                dto.setC1179(value);
            }else if(StringUtil.equals("AMT_82117a_QTTDMDZT",detailDto.getPropertycode())){
                dto.setC117a(value);
            }else if(StringUtil.equals("AMT_821180_JW",detailDto.getPropertycode())){
                dto.setC1180(value);
            }else if(StringUtil.equals("AMT_821200_LHJ",detailDto.getPropertycode())){
                dto.setC1200(value);
            }else if(StringUtil.equals("AMT_821210_ZH",detailDto.getPropertycode())){
                dto.setC1210(value);
            }else if(StringUtil.equals("AMT_821220_GYZF",detailDto.getPropertycode())){
                dto.setC1220(value);
            }else if(StringUtil.equals("AMT_821230_FJRQY",detailDto.getPropertycode())){
                dto.setC1230(value);
            }else if(StringUtil.equals("AMT_821240_YXYCKLJRJG",detailDto.getPropertycode())){
                dto.setC1240(value);
            }else if(StringUtil.equals("AMT_821250_YXYFCKLJRJG",detailDto.getPropertycode())){
                dto.setC1250(value);
            }else if(StringUtil.equals("AMT_821260_FYXYJRJG",detailDto.getPropertycode())){
                dto.setC1260(value);
            }else if(StringUtil.equals("AMT_821270_TDMDZT",detailDto.getPropertycode())){
                dto.setC1270(value);
            }else if(StringUtil.equals("AMT_821271_YXFBBLC",detailDto.getPropertycode())){
                dto.setC1271(value);
            }else if(StringUtil.equals("AMT_821272_XTGSZGCP",detailDto.getPropertycode())){
                dto.setC1272(value);
            }else if(StringUtil.equals("AMT_821273_ZQGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setC1273(value);
            }else if(StringUtil.equals("AMT_821274_JJGLGSJQZGSZH",detailDto.getPropertycode())){
                dto.setC1274(value);
            }else if(StringUtil.equals("AMT_821275_QHGSJQZGSZGCP",detailDto.getPropertycode())){
                dto.setC1275(value);
            }else if(StringUtil.equals("AMT_821276_BXZGCP",detailDto.getPropertycode())){
                dto.setC1276(value);
            }else if(StringUtil.equals("AMT_821277_JRZCTZGSZGCP",detailDto.getPropertycode())){
                dto.setC1277(value);
            }else if(StringUtil.equals("AMT_821278_GMJJ",detailDto.getPropertycode())){
                dto.setC1278(value);
            }else if(StringUtil.equals("AMT_821279_SMJGSMJJ",detailDto.getPropertycode())){
                dto.setC1279(value);
            }else if(StringUtil.equals("AMT_82127a_QTTDMDZT",detailDto.getPropertycode())){
                dto.setC127a(value);
            }else if(StringUtil.equals("AMT_821280_JW",detailDto.getPropertycode())){
                dto.setC1280(value);
            }else if(StringUtil.equals("AMT_823000_WFPLR",detailDto.getPropertycode())){
                dto.setC3000(value);
            }else if(StringUtil.equals("AMT_824000_QTZHSY",detailDto.getPropertycode())){
                dto.setC4000(value);
            }
        }
//        dto.setA0000(dto.getA1000().add(dto.getA2000().add(dto.getA3000()).add(dto.getA4000())
//                .add(dto.getA5000()).add(dto.getA6000()).add(dto.getA7000()).add(dto.getA8000())
//                .add(dto.getA9000()).add(dto.getAa000()).add(dto.getAb000()).add(dto.getAc000())));
    }



    /**
     * 将ss 非人民币 折算后金额保留2位有效数字
     *
     * @param ss
     * @return
     */
    private String setCcyAmountSacle2(String ss) {
        if (StringUtil.isNotEmpty(ss)) {
            // 按币种分组
            List<String> ccyAmountList = StringUtil.splitToList(GlobalConstant.SEMICOLON, ss);
            Map<String, String> ccyAmountMap = ccyAmountList.stream()
                    .collect(Collectors.toMap(p ->
                    {
                        return StringUtil.splitToList(GlobalConstant.COMMA, p).get(0);
                    }, p -> p, (key1, key2) -> key2));
            for (Map.Entry<String, String> entry : ccyAmountMap.entrySet()) {
                if (DictCodeListExt1.币种_中债_人民币.getValue().equals(entry.getKey())) {
                    continue;
                } else {
                    String value = entry.getValue();
                    List<String> singleCcyAmountList = StringUtil
                            .splitToList(GlobalConstant.COMMA, value);
                    if (!singleCcyAmountList.isEmpty() && singleCcyAmountList.size() >= 3) {
                        String s3 = singleCcyAmountList.get(2);
                        BigDecimal bs3 = new BigDecimal(s3);
                        bs3 = bs3.setScale(2, RoundingMode.HALF_UP);
                        singleCcyAmountList.set(2, bs3.toString());
                    }
                    String newValue = StringUtil.joinString(singleCcyAmountList, GlobalConstant.COMMA);
                    entry.setValue(newValue);
                }
            }
            List<String> ccyAmountResult = new ArrayList<>(ccyAmountMap.values());
            String ss1 = StringUtil.joinString(ccyAmountResult, GlobalConstant.SEMICOLON);
            return ss1;
        }
        return ss;
    }


    public String[] getRegistCode(String orgCode) {
        // String issuePartyId = CommonUtil.selectBankCode();
        String issuePartyId = CommonUtil.selectBankCode();
        String issuePartyName = CommonUtil.selectBankCode();
        // 发行机构代码取值
        if (null != orgCode) {
            Map<String, Object> param = new HashMap<>();
            List<String> orgCodeList = new ArrayList<>();
            orgCodeList.add(orgCode);
            param.put("orgCodeList", orgCodeList);
            List<SysInstRegBankCode001Dto> list = sysInstRegBankCode001Mapper.queryForPage(param);

            if (null != list && list.size() > 0) {
                issuePartyId = list.get(0).getRegistBankCode();
                SysInstitution dtp = (SysInstitution) CommonUtil.queryCode(GlobalConstant.SYS_INSTITUTION_KEY, orgCode);

                issuePartyName = null != dtp ? dtp.getInstitutionName() : issuePartyName;
            }
        }
        return new String[]{issuePartyId, issuePartyName};
    }

    /**
     * 根据PBC_IS_SHOW_ZERO参数处理字段为0或者空用于校验 add by wangyaning 20230111
     * @param insertlist
     */
    private void dealZeroNumber(List<ZG05PbcColPrdAssetDebtDto> insertlist) {
        String pbcIsShowZero = SvrDataUtil.getSysParam("PBC_IS_SHOW_ZERO");;
        for(ZG05PbcColPrdAssetDebtDto tempDto: insertlist) {
            //处理为空
            if (StringUtil.equals(pbcIsShowZero,"01")) {
                if (!ObjectUtils.isEmpty(tempDto.getA0000())) {
                    if(tempDto.getA0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA1000())) {
                    if(tempDto.getA1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA2000())) {
                    if(tempDto.getA2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA2100())) {
                    if(tempDto.getA2100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA2100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA2110())) {
                    if(tempDto.getA2110().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA2110(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA2120())) {
                    if(tempDto.getA2120().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA2120(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA2200())) {
                    if(tempDto.getA2200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA2200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA2210())) {
                    if(tempDto.getA2210().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA2210(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA2220())) {
                    if(tempDto.getA2220().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA2220(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA3000())) {
                    if(tempDto.getA3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA3100())) {
                    if(tempDto.getA3100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA3100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA3200())) {
                    if(tempDto.getA3200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA3200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA4000())) {
                    if(tempDto.getA4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA4100())) {
                    if(tempDto.getA4100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA4100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA4200())) {
                    if(tempDto.getA4200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA4200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA4300())) {
                    if(tempDto.getA4300().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA4300(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA4400())) {
                    if(tempDto.getA4400().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA4400(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA4500())) {
                    if(tempDto.getA4500().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA4500(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA4600())) {
                    if(tempDto.getA4600().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA4600(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA4700())) {
                    if(tempDto.getA4700().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA4700(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA4800())) {
                    if(tempDto.getA4800().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA4800(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5000())) {
                    if(tempDto.getA5000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5100())) {
                    if(tempDto.getA5100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5200())) {
                    if(tempDto.getA5200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5210())) {
                    if(tempDto.getA5210().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5210(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5220())) {
                    if(tempDto.getA5220().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5220(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5230())) {
                    if(tempDto.getA5230().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5230(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5240())) {
                    if(tempDto.getA5240().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5240(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5250())) {
                    if(tempDto.getA5250().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5250(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5260())) {
                    if(tempDto.getA5260().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5260(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5261())) {
                    if(tempDto.getA5261().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5261(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5270())) {
                    if(tempDto.getA5270().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5270(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5271())) {
                    if(tempDto.getA5271().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5271(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5272())) {
                    if(tempDto.getA5272().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5272(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5273())) {
                    if(tempDto.getA5273().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5273(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5274())) {
                    if(tempDto.getA5274().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5274(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5275())) {
                    if(tempDto.getA5275().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5275(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5276())) {
                    if(tempDto.getA5276().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5276(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5277())) {
                    if(tempDto.getA5277().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5277(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5278())) {
                    if(tempDto.getA5278().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5278(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5279())) {
                    if(tempDto.getA5279().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5279(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA527a())) {
                    if(tempDto.getA527a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA527a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA5280())) {
                    if(tempDto.getA5280().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA5280(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA6000())) {
                    if(tempDto.getA6000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA6000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA6100())) {
                    if(tempDto.getA6100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA6100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA6200())) {
                    if(tempDto.getA6200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA6200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA6300())) {
                    if(tempDto.getA6300().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA6300(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA6400())) {
                    if(tempDto.getA6400().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA6400(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA6500())) {
                    if(tempDto.getA6500().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA6500(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA6600())) {
                    if(tempDto.getA6600().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA6600(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA6700())) {
                    if(tempDto.getA6700().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA6700(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7000())) {
                    if(tempDto.getA7000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7100())) {
                    if(tempDto.getA7100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7110())) {
                    if(tempDto.getA7110().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7110(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7120())) {
                    if(tempDto.getA7120().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7120(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7200())) {
                    if(tempDto.getA7200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7210())) {
                    if(tempDto.getA7210().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7210(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7220())) {
                    if(tempDto.getA7220().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7220(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7230())) {
                    if(tempDto.getA7230().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7230(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7240())) {
                    if(tempDto.getA7240().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7240(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7250())) {
                    if(tempDto.getA7250().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7250(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7260())) {
                    if(tempDto.getA7260().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7260(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7270())) {
                    if(tempDto.getA7270().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7270(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7280())) {
                    if(tempDto.getA7280().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7280(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7290())) {
                    if(tempDto.getA7290().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7290(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA72a0())) {
                    if(tempDto.getA72a0().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA72a0(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7300())) {
                    if(tempDto.getA7300().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7300(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7310())) {
                    if(tempDto.getA7310().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7310(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7320())) {
                    if(tempDto.getA7320().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7320(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA7400())) {
                    if(tempDto.getA7400().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA7400(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA8000())) {
                    if(tempDto.getA8000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA8000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA8100())) {
                    if(tempDto.getA8100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA8100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA8200())) {
                    if(tempDto.getA8200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA8200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getA9000())) {
                    if(tempDto.getA9000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setA9000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getAa000())) {
                    if(tempDto.getAa000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setAa000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getAb000())) {
                    if(tempDto.getAb000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setAb000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getAc000())) {
                    if(tempDto.getAc000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setAc000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getD0000())) {
                    if(tempDto.getD0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setD0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB0000())) {
                    if(tempDto.getB0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1000())) {
                    if(tempDto.getB1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1100())) {
                    if(tempDto.getB1100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1200())) {
                    if(tempDto.getB1200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1210())) {
                    if(tempDto.getB1210().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1210(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1220())) {
                    if(tempDto.getB1220().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1220(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1230())) {
                    if(tempDto.getB1230().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1230(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1240())) {
                    if(tempDto.getB1240().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1240(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1250())) {
                    if(tempDto.getB1250().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1250(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1260())) {
                    if(tempDto.getB1260().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1260(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1261())) {
                    if(tempDto.getB1261().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1261(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1270())) {
                    if(tempDto.getB1270().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1270(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1271())) {
                    if(tempDto.getB1271().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1271(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1272())) {
                    if(tempDto.getB1272().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1272(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1273())) {
                    if(tempDto.getB1273().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1273(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1274())) {
                    if(tempDto.getB1274().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1274(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1275())) {
                    if(tempDto.getB1275().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1275(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1276())) {
                    if(tempDto.getB1276().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1276(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1277())) {
                    if(tempDto.getB1277().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1277(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1278())) {
                    if(tempDto.getB1278().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1278(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1279())) {
                    if(tempDto.getB1279().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1279(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB127a())) {
                    if(tempDto.getB127a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB127a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB1280())) {
                    if(tempDto.getB1280().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB1280(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB2000())) {
                    if(tempDto.getB2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB3000())) {
                    if(tempDto.getB3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB3100())) {
                    if(tempDto.getB3100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB3100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB3200())) {
                    if(tempDto.getB3200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB3200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB4000())) {
                    if(tempDto.getB4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getB5000())) {
                    if(tempDto.getB5000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setB5000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC0000())) {
                    if(tempDto.getC0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1000())) {
                    if(tempDto.getC1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1100())) {
                    if(tempDto.getC1100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1110())) {
                    if(tempDto.getC1110().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1110(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1120())) {
                    if(tempDto.getC1120().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1120(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1130())) {
                    if(tempDto.getC1130().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1130(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1140())) {
                    if(tempDto.getC1140().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1140(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1150())) {
                    if(tempDto.getC1150().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1150(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1160())) {
                    if(tempDto.getC1160().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1160(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1170())) {
                    if(tempDto.getC1170().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1170(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1171())) {
                    if(tempDto.getC1171().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1171(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1172())) {
                    if(tempDto.getC1172().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1172(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1173())) {
                    if(tempDto.getC1173().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1173(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1174())) {
                    if(tempDto.getC1174().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1174(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1175())) {
                    if(tempDto.getC1175().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1175(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1176())) {
                    if(tempDto.getC1176().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1176(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1177())) {
                    if(tempDto.getC1177().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1177(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1178())) {
                    if(tempDto.getC1178().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1178(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1179())) {
                    if(tempDto.getC1179().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1179(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC117a())) {
                    if(tempDto.getC117a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC117a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1180())) {
                    if(tempDto.getC1180().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1180(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1200())) {
                    if(tempDto.getC1200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1210())) {
                    if(tempDto.getC1210().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1210(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1220())) {
                    if(tempDto.getC1220().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1220(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1230())) {
                    if(tempDto.getC1230().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1230(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1240())) {
                    if(tempDto.getC1240().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1240(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1250())) {
                    if(tempDto.getC1250().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1250(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1260())) {
                    if(tempDto.getC1260().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1260(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1270())) {
                    if(tempDto.getC1270().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1270(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1271())) {
                    if(tempDto.getC1271().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1271(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1272())) {
                    if(tempDto.getC1272().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1272(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1273())) {
                    if(tempDto.getC1273().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1273(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1274())) {
                    if(tempDto.getC1274().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1274(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1275())) {
                    if(tempDto.getC1275().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1275(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1276())) {
                    if(tempDto.getC1276().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1276(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1277())) {
                    if(tempDto.getC1277().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1277(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1278())) {
                    if(tempDto.getC1278().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1278(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1279())) {
                    if(tempDto.getC1279().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1279(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC127a())) {
                    if(tempDto.getC127a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC127a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC1280())) {
                    if(tempDto.getC1280().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC1280(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC3000())) {
                    if(tempDto.getC3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC4000())) {
                    if(tempDto.getC4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC4000(null);
                    }
                }

            } else if (StringUtil.equals(pbcIsShowZero,"02")) {
                //处理成0.00
                if (ObjectUtils.isEmpty(tempDto.getA0000())) {
                    tempDto.setA0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA1000())) {
                    tempDto.setA1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA2000())) {
                    tempDto.setA2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA2100())) {
                    tempDto.setA2100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA2110())) {
                    tempDto.setA2110(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA2120())) {
                    tempDto.setA2120(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA2200())) {
                    tempDto.setA2200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA2210())) {
                    tempDto.setA2210(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA2220())) {
                    tempDto.setA2220(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA3000())) {
                    tempDto.setA3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA3100())) {
                    tempDto.setA3100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA3200())) {
                    tempDto.setA3200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA4000())) {
                    tempDto.setA4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA4100())) {
                    tempDto.setA4100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA4200())) {
                    tempDto.setA4200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA4300())) {
                    tempDto.setA4300(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA4400())) {
                    tempDto.setA4400(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA4500())) {
                    tempDto.setA4500(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA4600())) {
                    tempDto.setA4600(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA4700())) {
                    tempDto.setA4700(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA4800())) {
                    tempDto.setA4800(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5000())) {
                    tempDto.setA5000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5100())) {
                    tempDto.setA5100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5200())) {
                    tempDto.setA5200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5210())) {
                    tempDto.setA5210(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5220())) {
                    tempDto.setA5210(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5230())) {
                    tempDto.setA5230(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5240())) {
                    tempDto.setA5240(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5250())) {
                    tempDto.setA5250(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5260())) {
                    tempDto.setA5260(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5261())) {
                    tempDto.setA5261(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5270())) {
                    tempDto.setA5270(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5271())) {
                    tempDto.setA5271(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5272())) {
                    tempDto.setA5272(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5273())) {
                    tempDto.setA5273(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5274())) {
                    tempDto.setA5274(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5275())) {
                    tempDto.setA5275(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5276())) {
                    tempDto.setA5276(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5277())) {
                    tempDto.setA5277(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5278())) {
                    tempDto.setA5278(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5279())) {
                    tempDto.setA5279(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA527a())) {
                    tempDto.setA527a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA5280())) {
                    tempDto.setA5280(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA6000())) {
                    tempDto.setA6000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA6100())) {
                    tempDto.setA6100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA6200())) {
                    tempDto.setA6200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA6300())) {
                    tempDto.setA6300(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA6400())) {
                    tempDto.setA6400(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA6500())) {
                    tempDto.setA6500(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA6600())) {
                    tempDto.setA6600(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA6700())) {
                    tempDto.setA6700(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7000())) {
                    tempDto.setA7000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7100())) {
                    tempDto.setA7100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7110())) {
                    tempDto.setA7110(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7120())) {
                    tempDto.setA7120(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7200())) {
                    tempDto.setA7200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7210())) {
                    tempDto.setA7210(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7220())) {
                    tempDto.setA7220(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7230())) {
                    tempDto.setA7230(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7240())) {
                    tempDto.setA7240(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7250())) {
                    tempDto.setA7250(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7260())) {
                    tempDto.setA7260(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7270())) {
                    tempDto.setA7270(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7280())) {
                    tempDto.setA7280(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7290())) {
                    tempDto.setA7290(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA72a0())) {
                    tempDto.setA72a0(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7300())) {
                    tempDto.setA7300(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7310())) {
                    tempDto.setA7310(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7320())) {
                    tempDto.setA7320(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA7400())) {
                    tempDto.setA7400(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA8000())) {
                    tempDto.setA8000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA8100())) {
                    tempDto.setA8100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA8200())) {
                    tempDto.setA8200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getA9000())) {
                    tempDto.setA9000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getAa000())) {
                    tempDto.setAa000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getAb000())) {
                    tempDto.setAb000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getAc000())) {
                    tempDto.setAc000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getD0000())) {
                    tempDto.setD0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB0000())) {
                    tempDto.setB0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1000())) {
                    tempDto.setB1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1100())) {
                    tempDto.setB1100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1200())) {
                    tempDto.setB1200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1210())) {
                    tempDto.setB1210(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1220())) {
                    tempDto.setB1220(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1230())) {
                    tempDto.setB1230(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1240())) {
                    tempDto.setB1240(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1250())) {
                    tempDto.setB1250(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1260())) {
                    tempDto.setB1260(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1261())) {
                    tempDto.setB1261(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1270())) {
                    tempDto.setB1270(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1271())) {
                    tempDto.setB1271(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1272())) {
                    tempDto.setB1272(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1273())) {
                    tempDto.setB1273(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1274())) {
                    tempDto.setB1274(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1275())) {
                    tempDto.setB1275(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1276())) {
                    tempDto.setB1276(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1277())) {
                    tempDto.setB1277(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1278())) {
                    tempDto.setB1278(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1279())) {
                    tempDto.setB1279(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB127a())) {
                    tempDto.setB127a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB1280())) {
                    tempDto.setB1280(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB2000())) {
                    tempDto.setB2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB3000())) {
                    tempDto.setB3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB3100())) {
                    tempDto.setB3100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB3200())) {
                    tempDto.setB3200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB4000())) {
                    tempDto.setB4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getB5000())) {
                    tempDto.setB5000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC0000())) {
                    tempDto.setC0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1000())) {
                    tempDto.setC1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1100())) {
                    tempDto.setC1100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1110())) {
                    tempDto.setC1110(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1120())) {
                    tempDto.setC1120(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1130())) {
                    tempDto.setC1130(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1140())) {
                    tempDto.setC1140(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1150())) {
                    tempDto.setC1150(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1160())) {
                    tempDto.setC1160(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1170())) {
                    tempDto.setC1170(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1171())) {
                    tempDto.setC1171(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1172())) {
                    tempDto.setC1172(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1173())) {
                    tempDto.setC1173(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1174())) {
                    tempDto.setC1174(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1175())) {
                    tempDto.setC1175(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1176())) {
                    tempDto.setC1176(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1177())) {
                    tempDto.setC1177(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1178())) {
                    tempDto.setC1178(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1179())) {
                    tempDto.setC1179(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC117a())) {
                    tempDto.setC117a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1180())) {
                    tempDto.setC1180(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1200())) {
                    tempDto.setC1200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1210())) {
                    tempDto.setC1210(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1220())) {
                    tempDto.setC1220(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1230())) {
                    tempDto.setC1230(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1240())) {
                    tempDto.setC1240(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1250())) {
                    tempDto.setC1250(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1260())) {
                    tempDto.setC1260(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1270())) {
                    tempDto.setC1270(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1271())) {
                    tempDto.setC1271(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1272())) {
                    tempDto.setC1272(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1273())) {
                    tempDto.setC1273(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1274())) {
                    tempDto.setC1274(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1275())) {
                    tempDto.setC1275(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1276())) {
                    tempDto.setC1276(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1277())) {
                    tempDto.setC1277(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1278())) {
                    tempDto.setC1278(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1279())) {
                    tempDto.setC1279(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC127a())) {
                    tempDto.setC127a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC1280())) {
                    tempDto.setC1280(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC3000())) {
                    tempDto.setC3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC4000())) {
                    tempDto.setC4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
    }
}
