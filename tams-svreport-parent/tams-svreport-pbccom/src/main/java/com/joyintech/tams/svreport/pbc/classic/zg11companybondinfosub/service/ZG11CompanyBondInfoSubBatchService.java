package com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.service;


import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysInstitution;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode001Mapper;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.*;
import com.joyintech.tams.svreport.pbc.assetposition.dao.PbcMonthAssetReportMapper;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dao.ZG11CompanyBondInfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11CompanyBondInfoSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11PbcReportCompanyDetailDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportConstants;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportProperycode;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dao.PbcReportTemplateMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.svreport.pbc.report.dto.PbcReportTemplateDto;

import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
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
 * ZG11 企业债券分行业、企业规模情况信息生成处理逻辑
 */
@Service
public class ZG11CompanyBondInfoSubBatchService {

    @Autowired
    private ZG11CompanyBondInfoSubMapper zG11CompanyBondInfoSubMapper;

    /**
     * 资产头寸mapper
     */
    @Autowired
    private PbcMonthAssetReportMapper pbcMonthAssetReportMapper;

    @Autowired
    private SysInstRegBankCode001Mapper sysInstRegBankCode001Mapper;

    /**
     * 资产头寸mapper
     */
    @Autowired
    private PbcReportTemplateMapper pbcReportTemplateMapper;

    /**
     * 主表mapper
     */
    @Autowired
    private PbcMonthReportMapper pbcMonthReportMapper;

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private PbcDataCheckUtil pbcDataCheckUtil;

    private Logger logger = LoggerFactory.getLogger(getClass());

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
        if (zG11CompanyBondInfoSubMapper.selectCountByTermId(termId,PbcReportType.PBC_REPORT_2_5) > 0) {
            logger.info("当月存在已报送数据，批处理不再继续执行！");
            return;
        }
        //删除当期所有数据
        zG11CompanyBondInfoSubMapper.deleteByTermId(termId,PbcReportType.PBC_REPORT_2_5);

        //报表生成
        genRegistReport(batchTime,null,false);

    }

    /**
     * 功能:ZG11
     *
     * @param executeDate 批处理日期
     * @param prodIdList  产品list
     * @param isEdit      是否是消息处理编辑触发
     * @since
     */
    public void genRegistReport(Date executeDate, List<String> prodIdList, boolean isEdit) {
        //联社模式获取
        String modelType = null;
        if(CommonUtil.isBAmodel()){
            modelType = DictCodeList.权限模式_联社模式.getValue();
        }
        List<ChbAssetInfo> assetList = queryAssetInfoBatch();
        Map<String,ChbAssetInfo> assetMap = assetList.stream().collect(Collectors.toMap(ChbAssetInfo::getAssetCode, a->a,(k1, k2)->k1));
        //根据模式编码是否为空来确认是否需要获取机构编码
        List<ZG11PbcMonthAssetPositionDto> posAssetList =
                zG11CompanyBondInfoSubMapper.selectByDateAndProdid(prodIdList, com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(executeDate),modelType);
        generateMonthReportCompanyBond(posAssetList,executeDate,assetMap,isEdit);
    }

    /**
     * 功能:生成表2-5 企业债券分行业、企业规模统计
     * @since 1.6.0
     * @version 1.6.0
     * @author：yuluan 2019年3月12日
     * @param
     *
     */
    public void generateMonthReportCompanyBond(List<ZG11PbcMonthAssetPositionDto> posAssetList, Date generateDate,
                                               Map<String, ChbAssetInfo> assetMap, boolean isEdit){

        // 生成月度报表主表数据
        PbcMonthReport pbcReport = genMonthReportByReporttype(PbcReportType.PBC_REPORT_2_5,generateDate,isEdit,false);

        Map<String, ZG11PbcReportCompanyDetailDto> result = genAssetMonthReportemplate(generateDate,  PbcReportType.PBC_REPORT_2_5, 20,pbcReport.getReportuuid());


        if(null==result){
            return;
        }

        Map<String,Map<String,ZG11PbcReportCompanyDetailDto>> deptMap = new HashMap();

        Map<String, BigDecimal> rateMap = new HashMap<>();
        // 汇率取值开关
        String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
        for(ZG11PbcMonthAssetPositionDto pos :posAssetList){
            if(pos.getAssetType3()!=null&& PbcMonthReportConstants.PBC_ASSETTYPE2_S05.equals(pos.getAssetType2())){//企业债券才统计
                // add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
                if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
                    Map<String,ZG11PbcReportCompanyDetailDto> singDeptMap = genAssetMonthReportemplate(generateDate,  PbcReportType.PBC_REPORT_2_5, 20,pbcReport.getReportuuid());

                    deptMap.put(pos.getInstitutionCode(), singDeptMap);
                }

                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_C10000_NLMYY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_01, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_C20000_CKY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_02, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_C30000_ZZY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_03, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_C40000_DLRLRQJSSCHGYY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_04, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_C50000_JZY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_05, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_C60000_PFHLSY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_06, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_C70000_JTYSCCHYZY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_07, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_C80000_ZSHCYY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_08, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_C90000_XXCSRJHXXJSFWY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_09, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CA0000_JRY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_10, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CB0000_FDCY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_11, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CC0000_ZLHSWFWY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_12, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CD0000_KXYJHJSFWY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_13, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CE0000_SLHJHGGSSGLY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_14, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CF0000_JMFWXLHQTFWY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_15, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CG0000_JY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_16, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CH0000_WSHSHGZ, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_17, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CI0000_WHTYHYLY, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_18, assetMap);
                setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.AMT_CJ0000_GGGLSHBZHSHZZ, PbcReportType.PBC_REPORT_2_5,
                        rateMap, source, PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_19, assetMap);
            }
        }


        //修改数据组装及入库公共方法
        buildSumValAndInster(result, null, pbcReport, !CollectionUtils.isEmpty(posAssetList),false,null,null,null);
    }
    /**
     * 功能:根据报表类型报表日期生成人行报表主数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @param reporttype 报表类型
     * @param
     * @return
     * PbcMonthReport
     */
    private PbcMonthReport genMonthReportByReporttype(String reporttype,Date reportDate,
                                                      boolean isEdit,boolean isOnePrd){
        PbcMonthReport pbcMonthReport = new PbcMonthReport();
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
        pbcMonthReport.setReporttype(reporttype);
        // 起止日期
        pbcMonthReport.setStartdate(reportStartDate);
        pbcMonthReport.setEnddate(reportEndDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        pbcMonthReport.setTermid(sdf.format(reportStartDate));
        // 数据日期
        pbcMonthReport.setStatusDate(reportDate);
        // 制表日期
//        pbcMonthReport.setReportDate(reportEndDate);
        // 报表频率：月报
        pbcMonthReport.setReportfrequ(PbcMonthReportConstants.REPORT_FREQU_M);
        // 发行机构代码取值
        pbcMonthReport.setIssuePartyId(CommonUtil.selectPbcBankCode(null));
        pbcMonthReport.setIssuePartyName(CommonUtil.selectBankCode());
        // 用户信息
        pbcMonthReport.setOrgCode(CommonUtil.getOrgCode());
        pbcMonthReport.setCreateDept(CommonUtil.getDeptCode());
        pbcMonthReport.setCreateUser(CommonUtil.getUserName());
        pbcMonthReport.setUpdateUser(CommonUtil.getUserName());
        // 数据状态
        // 根据是否编辑处理数据状态
        pbcMonthReport.setGen(true);
        // 不是单产品报表按月报出数据状态
        if (!isOnePrd){
            // 查询库中报表的数据状态
            List<PbcMonthReport> apbcMonthReport = pbcMonthReportMapper.selectSendDataByDateAndType(com.joyintech.tams.report.interf.common.constant.DateUtil.format(reportEndDate), reporttype);
            if (CollectionUtils.isEmpty(apbcMonthReport)){
                pbcMonthReport.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
            } else {// 用原报表的数据状态
                String status = DictCodeListExt2.报送数据状态_新建.getValue();
                for (PbcMonthReport report: apbcMonthReport){
                    if (DictCodeListExt2.报送状态_未报送.getValue().equals(report.getSendStatus())){
                        status = report.getSendStatus();
                    }
                }
                pbcMonthReport.setStatus(status);
            }
            // 如果只有一条已报送的数据并且不是消息触发则不生成报表
            if (!isEdit && (apbcMonthReport.size() == 1 && (DictCodeListExt2.报送状态_接口报送.equals(apbcMonthReport.get(0).getSendStatus())
                    ||DictCodeListExt2.报送状态_手工处理.equals(apbcMonthReport.get(0).getSendStatus())))){
                pbcMonthReport.setGen(false);
            }
//            }
        }
        pbcMonthReport.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
        // 报送时点
        // 当前批次报送时点集合
        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        if (isOnePrd){
            List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG11企业债券分行业企业规模情况信息.getValue(),
                    pbcMonthReport.getStatus(),dateMap, pbcMonthReport.getEnddate(),SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
            if (!StringUtil.isEmptyList(dateList)) {
                pbcMonthReport.setChbSubmitDeadline(dateList.get(0));
                pbcMonthReport.setSubmitDeadline(dateList.get(1));
            }
        } else {
            List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG11企业债券分行业企业规模情况信息.getValue(),
                    pbcMonthReport.getStatus(),dateMap, pbcMonthReport.getEnddate(),SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
            if (!StringUtil.isEmptyList(dateList)) {
                pbcMonthReport.setChbSubmitDeadline(dateList.get(0));
                pbcMonthReport.setSubmitDeadline(dateList.get(1));
            }
        }
        // 填报机构名称
        pbcMonthReport.setReportOrgName(CommonUtil.selectBankCode());
        ChbOrgRegistBankCode bankCode = CommonUtil.getUserBank();
        String  code=CommonUtil.selectBankCode();
        String  name=CommonUtil.selectBankCodeName();
        if(null!=bankCode){
            code = bankCode.getRegistBankCode();
            name = bankCode.getBelongInstitutionName();
        }
        // 发行机构代码取值
        pbcMonthReport.setIssuePartyId(code);
        pbcMonthReport.setIssuePartyName(name);
        pbcMonthReport.setReportOrgName(name);

        //多级法人改造start add by hxy 20200619,联社模式，在后续中修改，此处只修改银行模式
        if(!MultilevelDataAuthorityUtil.isEnabled()) {
            //银行模式
            SysInstRegBankCode001Dto bankDto=sysInstRegBankCode001Mapper.queryForPageWithMode(null);
            if(null!=bankDto){
                // 社会信用代码
                pbcMonthReport.setSocialCreditCode(bankDto.getInsitituteCode());
                // 金融机构编码
                pbcMonthReport.setFinInstitutionCode(bankDto.getRegistBankCode());

            }
        }
        //多级法人改造end add by hxy 20200619

        // 产品品种01银行非保本理财
        pbcMonthReport.setProdVariety(PbcMonthReportConstants.PBC_COL_PROD_TYPE_1);
        return pbcMonthReport;
    }

    /**
     *  模板组装 根据模板对应数据以及表路径获取对应数据
     * @param generateDate
     * @param
     * @param reportType
     * @param
     * @param size
     * @param uuid
     * @return
     */
    private Map genAssetMonthReportemplate(Date generateDate,String reportType,int size,String uuid){
        Map result = new HashMap();
        // 查询产品月报需要统计的项
        List<PbcReportTemplateDto> listKey = pbcReportTemplateMapper.selectByReporttype(reportType,
                DictCodeListExt1.维护数据状态_有效.getValue());
        if ( listKey.size() != size || listKey.size() == 0)  {
            logger.error("月报模板配置错误，请检查");
            return null;
        }

        for ( PbcReportTemplateDto k:listKey ) {
            //2-5模板初始化类
            if(PbcReportType.PBC_REPORT_2_5.equals(reportType)){
                ZG11PbcReportCompanyDetailDto detail = new ZG11PbcReportCompanyDetailDto();
                detail.setDetailuuid(IDGenerator.generate(36));
                detail.setReporttype(reportType);
                detail.setPropertycode(k.getPropertycode());
                detail.setReportuuid(uuid);
                detail.setLargeCompanyAmt(BigDecimal.ZERO);
                detail.setMediumCompanyAmt(BigDecimal.ZERO);
                detail.setMicroCompanyAmt(BigDecimal.ZERO);
                detail.setSmallCompanyAmt(BigDecimal.ZERO);
                detail.setOthAmt(BigDecimal.ZERO);
                result.put(k.getPropertycode(),detail);
            }
        }
        return result;
    }

    /**
     * 新增资产多法人统一入口
     * @param pos
     * @param dto
     * @param deptDto
     * @param code
     * @param rateMap
     * @param source
     * @param hxl
     * @param assetMap
     */
    private void setAssetReportValue(ZG11PbcMonthAssetPositionDto pos,Map dto,
                                     Map deptDto,String code,String rep_code,Map<String, BigDecimal> rateMap,String source,String hxl,Map assetMap){
        if(PbcReportType.PBC_REPORT_2_5.equals(rep_code)){
            //汇总数据计算
            setAssetReportCompanyBondValue(pos,(ZG11PbcReportCompanyDetailDto)dto.get(code), rateMap,source,hxl,assetMap);
            //部门数据计算
            if(null!=deptDto&&null!=deptDto.get(code)){

                setAssetReportCompanyBondValue(pos,(ZG11PbcReportCompanyDetailDto)deptDto.get(code), rateMap,source,hxl,assetMap);
            }
        }

    }

    /**
     * 表2-4 行塞值
     * @param pos
     * @param line
     * @param rateMap 汇率
     * @param source  汇率取值开关
     * @param
     */
    private  void setAssetReportCompanyBondValue(ZG11PbcMonthAssetPositionDto pos,ZG11PbcReportCompanyDetailDto line
            ,Map<String, BigDecimal> rateMap,String source,String hxl,Map<String,ChbAssetInfo> assetMap){
        BigDecimal cnyAmts = ChbCcyConvertUtil.exchangeMoney(
                pos.getCcy(),DictCodeListExt1.币种_中债_人民币.getValue(),pos.getEnddate(),pos.getAmt(),rateMap,source);
        BigDecimal intAmt = ChbCcyConvertUtil.exchangeMoney(
                pos.getCcy(),DictCodeListExt1.币种_中债_人民币.getValue(),pos.getEnddate(),pos.getIntAmt(),rateMap,source);
        //金额加上应计利息,应收利息
        BigDecimal cnyAmt = cnyAmts.add(intAmt);
        //查询资产要素信息获取发行机构所属行业一级分类和发行机构类型（按规模划分）
        ChbAssetInfo assetinfo=assetMap.get(pos.getAssetCode());
        if(assetinfo==null){
            logger.error("资产要素不存在");
            return;
        }
        String industryType1 = null;
        if (assetinfo != null){
            industryType1 = assetinfo.getIndustry();
            if (StringUtils.isEmpty(industryType1)){
                industryType1 = assetinfo.getIndustryPbc();
            }
        }
        /**行业一级分类国际组织统计到公共管理、社会保障和社会组织*/
        if("20".equals(industryType1)){
            industryType1=PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_19;
        }
        String  qygm=pos.getPartyType1();
        if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_01.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_01.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_02.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_02.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_03.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_03.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_04.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_04.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_05.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_05.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_06.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_06.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_07.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_07.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_08.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_08.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_09.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_09.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_10.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_10.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_11.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_11.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_12.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_12.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_13.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_13.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_14.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_14.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_15.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_15.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_16.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_16.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_17.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_17.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_18.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_18.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }else if(PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_19.equals(industryType1)&&PbcMonthReportConstants.PBC_INDUSTRY_TYPE1_19.equals(hxl)){
            setCompanyBondValue(pos,qygm,line,cnyAmt);
        }
    }
    private void setCompanyBondValue(ZG11PbcMonthAssetPositionDto pos,String qygm,ZG11PbcReportCompanyDetailDto line,BigDecimal cnyAmt){
        // 企业规模 (数据规范化变更)01 中型企业/02 小型企业/03 微型企业/04 大型企业/99 其他
        //line.setPbcProdId(pos.getPbcProdId());
        if (PbcMonthReportConstants.SVR_INSTIT_SIZE_01.equals(qygm)) {
            if (line.getMediumCompanyAmt() != null) {
                line.setMediumCompanyAmt(line.getMediumCompanyAmt().add(cnyAmt));
            } else {
                line.setMediumCompanyAmt(cnyAmt);
            }
        } else if (PbcMonthReportConstants.SVR_INSTIT_SIZE_04.equals(qygm)) {
            if (line.getLargeCompanyAmt() != null) {
                line.setLargeCompanyAmt(line.getLargeCompanyAmt().add(cnyAmt));
            } else {
                line.setLargeCompanyAmt(cnyAmt);
            }
        } else if (PbcMonthReportConstants.SVR_INSTIT_SIZE_02.equals(qygm)) {
            if (line.getSmallCompanyAmt() != null) {
                line.setSmallCompanyAmt(line.getSmallCompanyAmt().add(cnyAmt));
            } else {
                line.setSmallCompanyAmt(cnyAmt);
            }
        } else if (PbcMonthReportConstants.SVR_INSTIT_SIZE_03.equals(qygm)) {
            if (line.getMicroCompanyAmt() != null) {
                line.setMicroCompanyAmt(line.getMicroCompanyAmt().add(cnyAmt));
            } else {
                line.setMicroCompanyAmt(cnyAmt);
            }
        } else {
            /** 05 统计到中型*/
            // 企业规模(数据规范化变更)/05 其他-统计到中型*企业
            if (line.getMediumCompanyAmt() != null) {
                line.setMediumCompanyAmt(line.getMediumCompanyAmt().add(cnyAmt));
            } else {
                line.setMediumCompanyAmt(cnyAmt);
            }
        }
    }

    /**
     * 组装数据汇总项
     * @param result
     * @param deptCode
     * @param pbcReport
     * @param
     */
    private void  buildSumValAndInster  (Map result,String deptCode,
                                         PbcMonthReport pbcReport,Boolean insertFlag,Boolean subtractionFlag,Map<String, BigDecimal> rateMap,String source,List<PbcMonthReport> prodAssetRegList){

        ZG11CompanyBondInfoSubDto zg11CompanyBondInfoSubDto = new ZG11CompanyBondInfoSubDto();


        if(PbcReportType.PBC_REPORT_2_5.equals(pbcReport.getReporttype())){
            BigDecimal hjdxqy=BigDecimal.ZERO;//合计大型企业
            BigDecimal hjzxqy=BigDecimal.ZERO;//合计中型企业
            BigDecimal hjwxqy=BigDecimal.ZERO;//合计微型企业
            BigDecimal hjxxqy=BigDecimal.ZERO;//合计小型企业
            Iterator<Map.Entry> entries = result.entrySet().iterator();

            while(entries.hasNext()){
                Map.Entry<String, ZG11PbcReportCompanyDetailDto> dtoMap = entries.next();
                hjdxqy=hjdxqy.add(dtoMap.getValue().getLargeCompanyAmt()==null?BigDecimal.ZERO:dtoMap.getValue().getLargeCompanyAmt());
                hjzxqy=hjzxqy.add(dtoMap.getValue().getMediumCompanyAmt()==null?BigDecimal.ZERO:dtoMap.getValue().getMediumCompanyAmt());
                hjwxqy=hjwxqy.add(dtoMap.getValue().getMicroCompanyAmt()==null?BigDecimal.ZERO:dtoMap.getValue().getMicroCompanyAmt());
                hjxxqy=hjxxqy.add(dtoMap.getValue().getSmallCompanyAmt()==null?BigDecimal.ZERO:dtoMap.getValue().getSmallCompanyAmt());
                dtoMap.getValue().setSumAmt(dtoMap.getValue().getLargeCompanyAmt()==null?BigDecimal.ZERO:dtoMap.getValue().getLargeCompanyAmt()
                        .add(dtoMap.getValue().getMediumCompanyAmt()==null?BigDecimal.ZERO:dtoMap.getValue().getMediumCompanyAmt())
                        .add(dtoMap.getValue().getMicroCompanyAmt()==null?BigDecimal.ZERO:dtoMap.getValue().getMicroCompanyAmt())
                        .add(dtoMap.getValue().getSmallCompanyAmt()==null?BigDecimal.ZERO:dtoMap.getValue().getSmallCompanyAmt()));
            }

            ZG11PbcReportCompanyDetailDto sumDto = (ZG11PbcReportCompanyDetailDto) result.get(PbcReportProperycode.AMT_C00000_QYZQYEZJ);
            sumDto.setLargeCompanyAmt(hjdxqy);
            sumDto.setMediumCompanyAmt(hjzxqy);
            sumDto.setMicroCompanyAmt(hjwxqy);
            sumDto.setSmallCompanyAmt(hjxxqy);
            sumDto.setSumAmt(hjdxqy.add(hjzxqy).add(hjwxqy).add(hjxxqy));
        }

        List<ZG11CompanyBondInfoSubDto> insertList = new ArrayList<>();

        //根据部门进行数据插入
        if (pbcReport.isGen()){

            // 判断是否有头寸数据有则插入
            if (insertFlag){

                String[] code = getRegistCode(deptCode);

                String uuid = IDGenerator.generate(36);
                if(!PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT.equals(pbcReport.getReporttype())){
                    pbcReport.setCreateDept(deptCode);
                    pbcReport.setDeptCode(deptCode);

                    pbcReport.setIssuePartyId(code[0]);

                    //多级法人改造，add 20200620 hxy start 当机构不为空时，去查询机构登记银行代码管理中的登记银行代码
                    if(null!=deptCode && StringUtil.isNotEmpty(deptCode)){
                        //联社模式
                        Map<String, Object> parammode=new HashMap<String, Object>();
                        parammode.put("orgCode", deptCode);
                        SysInstRegBankCode001Dto bankDto=sysInstRegBankCode001Mapper.queryForPageWithMode(parammode);
                        if(null!=bankDto){
                            // 社会信用代码
                            pbcReport.setSocialCreditCode(bankDto.getInsitituteCode());
                            // 金融机构编码
                            pbcReport.setFinInstitutionCode(bankDto.getRegistBankCode());

                        }
                    }
                    //多级法人改造，add 20200620 hxy end 当机构不为空时，去查询机构登记银行代码管理中的登记银行代码
                }
                pbcReport.setReportuuid(uuid);
                pbcReport.setOrgCode(deptCode);

                BeanUtil.copyPropertiesIgnoreNull(zg11CompanyBondInfoSubDto, pbcReport);
                //人行报送截止日
                zg11CompanyBondInfoSubDto.setPbcSubmitDeadline(pbcReport.getChbSubmitDeadline());
                zg11CompanyBondInfoSubDto.setIssuePartyId(CommonUtil.selectPbcBankCode(null));

                // 插入资产负债表数据
                Iterator<Map.Entry> entries = result.entrySet().iterator();

                if(PbcReportType.PBC_REPORT_2_5.equals(pbcReport.getReporttype())){
                    //遍历数据并入库
                    while(entries.hasNext()){
                        // 插入资产负债表数据
                        Map.Entry<String, ZG11PbcReportCompanyDetailDto> dtoMap = entries.next();
                        zg11CompanyBondInfoSubDto.setPbcProdId(dtoMap.getValue().getPbcProdId());
                        if(PbcReportProperycode.AMT_C00000_QYZQYEZJ.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC00000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC01000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC02000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC03000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC04000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_C10000_NLMYY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC10000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC11000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC12000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC13000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC14000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_C20000_CKY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC20000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC21000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC22000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC23000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC24000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_C30000_ZZY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC30000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC31000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC32000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC33000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC34000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_C40000_DLRLRQJSSCHGYY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC40000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC41000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC42000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC43000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC44000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_C50000_JZY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC50000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC51000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC52000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC53000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC54000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_C60000_PFHLSY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC60000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC61000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC62000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC63000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC64000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_C70000_JTYSCCHYZY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC70000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC71000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC72000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC73000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC74000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_C80000_ZSHCYY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC80000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC81000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC82000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC83000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC84000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_C90000_XXCSRJHXXJSFWY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setC90000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC91000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC92000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC93000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setC94000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CA0000_JRY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCa0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCa1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCa2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCa3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCa4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CB0000_FDCY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCb0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCb1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCb2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCb3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCb4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CC0000_ZLHSWFWY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCc0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCc1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCc2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCc3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCc4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CD0000_KXYJHJSFWY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCd0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCd1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCd2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCd3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCd4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CE0000_SLHJHGGSSGLY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCe0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCe1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCe2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCe3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCe4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CF0000_JMFWXLHQTFWY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCf0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCf1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCf2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCf3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCf4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CG0000_JY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCg0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCg1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCg2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCg3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCg4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CH0000_WSHSHGZ.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCh0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCh1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCh2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCh3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCh4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CI0000_WHTYHYLY.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCi0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCi1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCi2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCi3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCi4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                        if(PbcReportProperycode.AMT_CJ0000_GGGLSHBZHSHZZ.equals(dtoMap.getValue().getPropertycode())){
                            zg11CompanyBondInfoSubDto.setCj0000(dtoMap.getValue().getSumAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCj1000(dtoMap.getValue().getLargeCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCj2000(dtoMap.getValue().getMediumCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCj3000(dtoMap.getValue().getSmallCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                            zg11CompanyBondInfoSubDto.setCj4000(dtoMap.getValue().getMicroCompanyAmt().setScale(2, RoundingMode.HALF_UP));
                        }
                    }
                    // 调整入库逻辑
                    insertList.add(zg11CompanyBondInfoSubDto);
                    //zG11CompanyBondInfoSubMapper.insertSelective(zg11CompanyBondInfoSubDto);
                }

            }
        }

        /**指引补充需求变更  start */
        // 处理检查字段是否显示0 add by wangyaning 20230111
        if(!CollectionUtils.isEmpty(insertList)){
            dealZeroNumber(insertList);
        }
        /**指引补充需求变更   end */

        logger.info("采集平台企业债券分行业企业规模情况信息：报表生成校验 开始----------");
        List<ZG11CompanyBondInfoSubDto> listData = pbcDataCheckUtil.getTrueData(insertList, null, DictCodeListExt2.采集平台企业债券分行业企业规模情况信息.getValue(), true, "1", ZG11CompanyBondInfoSubDto.class);
        logger.info("采集平台企业债券分行业企业规模情况信息：报表生成校验 结束----------");
        intoDatabase(listData);
    }

    /**
     * 入库
     * @param insertlist
     */
    private void intoDatabase(List<ZG11CompanyBondInfoSubDto> insertlist) {
        logger.debug("ZG11 采集平台企业债券分行业企业规模情况信息：插入开始----------");
        int count = 0;
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            ZG11CompanyBondInfoSubMapper batchMapper = session.getMapper(ZG11CompanyBondInfoSubMapper.class);
            for (ZG11CompanyBondInfoSubDto dto : insertlist) {
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
            logger.error("ZG11 采集平台企业债券分行业企业规模情况信息:" + e.getMessage(), e);
        } finally {
            session.close();
        }
        logger.debug("ZG11 采集平台企业债券分行业企业规模情况信息：插入结束----------");
    }

    public String[] getRegistCode(String orgCode){
        String issuePartyId = CommonUtil.selectBankCode();
        String  issuePartyName = CommonUtil.selectBankCode();
        // 发行机构代码取值
        if(null!=orgCode){
            Map<String,Object> param = new HashMap<>();
            List<String> orgCodeList = new ArrayList<>();
            orgCodeList.add(orgCode);
            param.put("orgCodeList", orgCodeList);
            List<SysInstRegBankCode001Dto> list = sysInstRegBankCode001Mapper.queryForPage(param);

            if(null!=list&&list.size()>0){
                issuePartyId = list.get(0).getRegistBankCode();
                SysInstitution dtp = (SysInstitution) CommonUtil.queryCode(GlobalConstant.SYS_INSTITUTION_KEY,orgCode);

                issuePartyName =  null!=dtp?dtp.getInstitutionName():issuePartyName;
            }
        }
        return new String[]{issuePartyId,issuePartyName};
    }

    /**
     * 功能:查询资产要素
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月11日
     * @param
     * @return
     * List<AssetInfoDto>
     */
    public List<ChbAssetInfo> queryAssetInfoBatch(){
        List<ChbAssetInfo> assetInfoList = new ArrayList<ChbAssetInfo>();
        Map<String,Object> param = new HashMap<String,Object>();
        List<String> statusList = new ArrayList<>();
        statusList.add(DictCodeListExt1.维护数据状态_有效.getValue());
        statusList.add(DictCodeListExt1.维护数据状态_修改.getValue());
        param.put("statusList", statusList);
        template.select("com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dao.ZG11CompanyBondInfoSubMapper.selectAssetByStatus",
                param,new ResultHandler() {
                    @Override
                    public void handleResult(ResultContext resultContext) {
                        assetInfoList.add((ChbAssetInfo) resultContext.getResultObject());
                    }
                });
        return assetInfoList;
    }


    /**
     * 根据PBC_IS_SHOW_ZERO参数处理字段为0或者空用于校验 add by wangyaning 20230111
     * @param insertlist
     */
    private void dealZeroNumber(List<ZG11CompanyBondInfoSubDto> insertlist) {
        String pbcIsShowZero = SvrDataUtil.getSysParam("PBC_IS_SHOW_ZERO");;
        for(ZG11CompanyBondInfoSubDto tempDto: insertlist) {
            //处理为空
            if (StringUtil.equals(pbcIsShowZero,"01")) {
                if (!ObjectUtils.isEmpty(tempDto.getC00000())) {
                    if(tempDto.getC00000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC00000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC01000())) {
                    if(tempDto.getC01000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC01000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC02000())) {
                    if(tempDto.getC02000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC02000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC03000())) {
                    if(tempDto.getC03000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC03000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC04000())) {
                    if(tempDto.getC04000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC04000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC10000())) {
                    if(tempDto.getC10000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC10000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC11000())) {
                    if(tempDto.getC11000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC11000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC12000())) {
                    if(tempDto.getC12000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC12000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC13000())) {
                    if(tempDto.getC13000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC13000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC14000())) {
                    if(tempDto.getC14000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC14000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC20000())) {
                    if(tempDto.getC20000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC20000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC21000())) {
                    if(tempDto.getC21000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC21000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC22000())) {
                    if(tempDto.getC22000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC22000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC23000())) {
                    if(tempDto.getC23000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC23000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC24000())) {
                    if(tempDto.getC24000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC24000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC30000())) {
                    if(tempDto.getC30000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC30000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC31000())) {
                    if(tempDto.getC31000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC31000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC32000())) {
                    if(tempDto.getC32000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC32000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC33000())) {
                    if(tempDto.getC33000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC33000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC34000())) {
                    if(tempDto.getC34000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC34000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC40000())) {
                    if(tempDto.getC40000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC40000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC41000())) {
                    if(tempDto.getC41000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC41000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC42000())) {
                    if(tempDto.getC42000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC42000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC43000())) {
                    if(tempDto.getC43000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC43000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC44000())) {
                    if(tempDto.getC44000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC44000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC50000())) {
                    if(tempDto.getC50000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC50000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC51000())) {
                    if(tempDto.getC51000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC51000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC52000())) {
                    if(tempDto.getC52000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC52000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC53000())) {
                    if(tempDto.getC53000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC53000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC54000())) {
                    if(tempDto.getC54000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC54000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC60000())) {
                    if(tempDto.getC60000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC60000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC61000())) {
                    if(tempDto.getC61000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC61000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC62000())) {
                    if(tempDto.getC62000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC62000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC63000())) {
                    if(tempDto.getC63000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC63000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC64000())) {
                    if(tempDto.getC64000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC64000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC70000())) {
                    if(tempDto.getC70000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC70000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC71000())) {
                    if(tempDto.getC71000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC71000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC72000())) {
                    if(tempDto.getC72000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC72000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC73000())) {
                    if(tempDto.getC73000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC73000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC74000())) {
                    if(tempDto.getC74000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC74000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC80000())) {
                    if(tempDto.getC80000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC80000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC81000())) {
                    if(tempDto.getC81000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC81000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC82000())) {
                    if(tempDto.getC82000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC82000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC83000())) {
                    if(tempDto.getC83000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC83000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC84000())) {
                    if(tempDto.getC84000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC84000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC90000())) {
                    if(tempDto.getC90000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC90000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC91000())) {
                    if(tempDto.getC91000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC91000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC92000())) {
                    if(tempDto.getC92000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC92000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC93000())) {
                    if(tempDto.getC93000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC93000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getC94000())) {
                    if(tempDto.getC94000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setC94000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCa0000())) {
                    if(tempDto.getCa0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCa0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCa1000())) {
                    if(tempDto.getCa1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCa1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCa2000())) {
                    if(tempDto.getCa2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCa2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCa3000())) {
                    if(tempDto.getCa3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCa3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCa4000())) {
                    if(tempDto.getCa4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCa4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCb0000())) {
                    if(tempDto.getCb0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCb0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCb1000())) {
                    if(tempDto.getCb1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCb1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCb2000())) {
                    if(tempDto.getCb2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCb2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCb3000())) {
                    if(tempDto.getCb3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCb3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCb4000())) {
                    if(tempDto.getCb4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCb4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCc0000())) {
                    if(tempDto.getCc0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCc0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCc1000())) {
                    if(tempDto.getCc1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCc1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCc2000())) {
                    if(tempDto.getCc2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCc2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCc3000())) {
                    if(tempDto.getCc3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCc3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCc4000())) {
                    if(tempDto.getCc4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCc4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCd0000())) {
                    if(tempDto.getCd0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCd0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCd1000())) {
                    if(tempDto.getCd1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCd1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCd2000())) {
                    if(tempDto.getCd2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCd2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCd3000())) {
                    if(tempDto.getCd3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCd3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCd4000())) {
                    if(tempDto.getCd4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCd4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCe0000())) {
                    if(tempDto.getCe0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCe0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCe1000())) {
                    if(tempDto.getCe1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCe1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCe2000())) {
                    if(tempDto.getCe2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCe2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCe3000())) {
                    if(tempDto.getCe3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCe3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCe4000())) {
                    if(tempDto.getCe4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCe4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCf0000())) {
                    if(tempDto.getCf0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCf0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCf1000())) {
                    if(tempDto.getCf1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCf1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCf2000())) {
                    if(tempDto.getCf2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCf2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCf3000())) {
                    if(tempDto.getCf3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCf3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCf4000())) {
                    if(tempDto.getCf4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCf4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCg0000())) {
                    if(tempDto.getCg0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCg0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCg1000())) {
                    if(tempDto.getCg1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCg1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCg2000())) {
                    if(tempDto.getCg2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCg2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCg3000())) {
                    if(tempDto.getCg3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCg3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCg4000())) {
                    if(tempDto.getCg4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCg4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCh0000())) {
                    if(tempDto.getCh0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCh0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCh1000())) {
                    if(tempDto.getCh1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCh1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCh2000())) {
                    if(tempDto.getCh2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCh2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCh3000())) {
                    if(tempDto.getCh3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCh3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCh4000())) {
                    if(tempDto.getCh4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCh4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCi0000())) {
                    if(tempDto.getCi0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCi0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCi1000())) {
                    if(tempDto.getCi1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCi1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCi2000())) {
                    if(tempDto.getCi2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCi2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCi3000())) {
                    if(tempDto.getCi3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCi3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCi4000())) {
                    if(tempDto.getCi4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCi4000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCj0000())) {
                    if(tempDto.getCj0000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCj0000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCj1000())) {
                    if(tempDto.getCj1000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCj1000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCj2000())) {
                    if(tempDto.getCj2000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCj2000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCj3000())) {
                    if(tempDto.getCj3000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCj3000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getCj4000())) {
                    if(tempDto.getCj4000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setCj4000(null);
                    }
                }
            } else if (StringUtil.equals(pbcIsShowZero,"02")) {
                //处理成0
                if (ObjectUtils.isEmpty(tempDto.getC00000())) {
                    tempDto.setC00000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC01000())) {
                    tempDto.setC01000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC02000())) {
                    tempDto.setC02000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC03000())) {
                    tempDto.setC03000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC04000())) {
                    tempDto.setC04000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC10000())) {
                    tempDto.setC10000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC11000())) {
                    tempDto.setC11000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC12000())) {
                    tempDto.setC12000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC13000())) {
                    tempDto.setC13000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC14000())) {
                    tempDto.setC14000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC20000())) {
                    tempDto.setC20000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC21000())) {
                    tempDto.setC21000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC22000())) {
                    tempDto.setC22000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC23000())) {
                    tempDto.setC23000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC24000())) {
                    tempDto.setC24000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC30000())) {
                    tempDto.setC30000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC31000())) {
                    tempDto.setC31000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC32000())) {
                    tempDto.setC32000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC33000())) {
                    tempDto.setC33000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC34000())) {
                    tempDto.setC34000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC40000())) {
                    tempDto.setC40000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC41000())) {
                    tempDto.setC41000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC42000())) {
                    tempDto.setC42000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC43000())) {
                    tempDto.setC43000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC44000())) {
                    tempDto.setC44000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC50000())) {
                    tempDto.setC50000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC51000())) {
                    tempDto.setC51000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC52000())) {
                    tempDto.setC52000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC53000())) {
                    tempDto.setC53000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC54000())) {
                    tempDto.setC54000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC60000())) {
                    tempDto.setC60000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC61000())) {
                    tempDto.setC61000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC62000())) {
                    tempDto.setC62000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC63000())) {
                    tempDto.setC63000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC64000())) {
                    tempDto.setC64000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC70000())) {
                    tempDto.setC70000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC71000())) {
                    tempDto.setC71000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC72000())) {
                    tempDto.setC72000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC73000())) {
                    tempDto.setC73000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC74000())) {
                    tempDto.setC74000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC80000())) {
                    tempDto.setC80000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC81000())) {
                    tempDto.setC81000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC82000())) {
                    tempDto.setC82000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC83000())) {
                    tempDto.setC83000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC84000())) {
                    tempDto.setC84000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC90000())) {
                    tempDto.setC90000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC91000())) {
                    tempDto.setC91000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC92000())) {
                    tempDto.setC92000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC93000())) {
                    tempDto.setC93000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getC94000())) {
                    tempDto.setC94000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCa0000())) {
                    tempDto.setCa0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCa1000())) {
                    tempDto.setCa1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCa2000())) {
                    tempDto.setCa2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCa3000())) {
                    tempDto.setCa3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCa4000())) {
                    tempDto.setCa4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCb0000())) {
                    tempDto.setCb0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCb1000())) {
                    tempDto.setCb1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCb2000())) {
                    tempDto.setCb2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCb3000())) {
                    tempDto.setCb3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCb4000())) {
                    tempDto.setCb4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCc0000())) {
                    tempDto.setCc0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCc1000())) {
                    tempDto.setCc1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCc2000())) {
                    tempDto.setCc2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCc3000())) {
                    tempDto.setCc3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCc4000())) {
                    tempDto.setCc4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCd0000())) {
                    tempDto.setCd0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCd1000())) {
                    tempDto.setCd1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCd2000())) {
                    tempDto.setCd2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCd3000())) {
                    tempDto.setCd3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCd4000())) {
                    tempDto.setCd4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCe0000())) {
                    tempDto.setCe0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCe1000())) {
                    tempDto.setCe1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCe2000())) {
                    tempDto.setCe2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCe3000())) {
                    tempDto.setCe3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCe4000())) {
                    tempDto.setCe4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCf0000())) {
                    tempDto.setCf0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCf1000())) {
                    tempDto.setCf1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCf2000())) {
                    tempDto.setCf2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCf3000())) {
                    tempDto.setCf3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCf4000())) {
                    tempDto.setCf4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCg0000())) {
                    tempDto.setCg0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCg1000())) {
                    tempDto.setCg1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCg2000())) {
                    tempDto.setCg2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCg3000())) {
                    tempDto.setCg3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCg4000())) {
                    tempDto.setCg4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCh0000())) {
                    tempDto.setCh0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCh1000())) {
                    tempDto.setCh1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCh2000())) {
                    tempDto.setCh2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCh3000())) {
                    tempDto.setCh3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCh4000())) {
                    tempDto.setCh4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCi0000())) {
                    tempDto.setCi0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCi1000())) {
                    tempDto.setCi1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCi2000())) {
                    tempDto.setCi2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCi3000())) {
                    tempDto.setCi3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCi4000())) {
                    tempDto.setCi4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCj0000())) {
                    tempDto.setCj0000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCj1000())) {
                    tempDto.setCj1000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCj2000())) {
                    tempDto.setCj2000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCj3000())) {
                    tempDto.setCj3000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getCj4000())) {
                    tempDto.setCj4000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
    }
}
