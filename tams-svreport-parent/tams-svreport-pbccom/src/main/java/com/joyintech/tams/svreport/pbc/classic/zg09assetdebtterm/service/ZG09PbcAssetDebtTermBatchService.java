package com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.service;

import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.ChbCprodBalanceDto;
import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.svreport.interf.chb.cprdbalance.dao.ChbCprodBalanceDtoMapper;
import com.joyintech.tams.svreport.pbc.assetdebt.dao.PbcReportAssetDebtDetailMapper;
import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportDebtTermDetail;
import com.joyintech.tams.svreport.pbc.assetposition.dao.PbcMonthAssetPositionMapper;
import com.joyintech.tams.svreport.pbc.assetposition.dao.PbcMonthAssetReportMapper;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dao.ZG09PbcAssetDebtTermMapper;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dto.ZG09PbcAssetDebtTermDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportConstants;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportProperycode;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.svreport.pbc.prod.dao.PbcMonthPrdPostionMapper;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;
import com.joyintech.tams.svreport.pbc.report.dao.PbcReportTemplateMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.svreport.pbc.report.dto.PbcReportTemplateDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
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
 * ZG09 资产负债剩余期限
 */
@Service
public class ZG09PbcAssetDebtTermBatchService {

    @Autowired
    private ZG09PbcAssetDebtTermMapper zG09PbcAssetDebtTermMapper;
    @Autowired
    private PbcReportAssetDebtDetailMapper pbcReportAssetDebtDetailMapper;
    /**
     * 资产头寸mapper
     */
    @Autowired
    private PbcReportTemplateMapper pbcReportTemplateMapper;

    /**
     * 产品头寸mapper
     */
    @Autowired
    private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;

    /**
     * 资产头寸mapper
     */
    @Autowired
    private PbcMonthAssetReportMapper pbcMonthAssetReportMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private PbcMonthAssetPositionMapper pbcMonthAssetPositionMapper;

    @Autowired
    private ChbCprodBalanceDtoMapper chbCprodBalanceDtoMapper;

    @Autowired
    private PbcDataCheckUtil pbcDataCheckUtil;


    @Autowired
    private SqlSessionTemplate template;

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
        if (zG09PbcAssetDebtTermMapper.selectCountByTermId(termId,PbcReportType.PBC_REPORT_3_3) > 0) {
            logger.info("当月存在已报送数据，批处理不再继续执行！");
            return;
        }
        //删除当期所有数据
        zG09PbcAssetDebtTermMapper.deleteByTermId(termId,PbcReportType.PBC_REPORT_3_3);

        //报表生成
        genRegistReport(batchTime,null,false);

    }

    /**
     * 功能:ZG05-资管产品资产负债信息批处理
     *
     * @param generateDate 批处理日期
     * @param prodIdList  产品list
     * @param isEdit      是否是消息处理编辑触发
     *                    void
     * @version
     * @author：dongxiangyang 2022年11月7日
     * @since
     */
    public void genRegistReport(Date generateDate, List<String> prodIdList, boolean isEdit) {
        logger.debug("ZG09资产负债剩余期限信息----开始------" +  df.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(generateDate);

        //联社模式获取
        String modelType = null;
        if(CommonUtil.isBAmodel()){
            modelType = DictCodeList.权限模式_联社模式.getValue();
        }

        //根据模式编码是否为空来确认是否需要获取机构编码
        List<PbcMonthAssetPositionDto> posAssetList =
                pbcMonthAssetReportMapper.selectByDateAndProdid(prodIdList, com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(generateDate),modelType);
        // 权益类从产品头寸取值
        String bankCode=SvrDataUtil.getSysParam(SVReportGlobalConstant.CUSTOMER_BANK_CODE);
        List<PbcMonthPrdPostionDto> posProdList;

        //根据模式编码是否为空来确认是否需要获取机构编码
        if(StringUtil.equals(bankCode, "001")){
            posProdList =
                    pbcMonthPrdPostionMapper.selectByDatePsbc(com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(generateDate),modelType,prodIdList);
        }else{
            posProdList =
                    pbcMonthPrdPostionMapper.selectByDate(com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(generateDate),modelType,prodIdList);
        }

        // 表3-3 资管产品资产负债剩余期限
        generateMonthReportDebtterm(posAssetList,posProdList,generateDate, isEdit);
    }

    /**
     * 功能:生成表3-3 资管产品资产负债剩余期限
     * @since 1.6.0
     * @version 1.6.0
     * @author：yuluan 2019年3月12日
     * @param
     *
     */
    public void generateMonthReportDebtterm(List<PbcMonthAssetPositionDto> posAssetList,List<PbcMonthPrdPostionDto> posProdList
            ,Date generateDate, boolean isEdit) {

        // 生成月度报表主表数据
        PbcMonthReport pbcReport = genMonthReportByReporttype(PbcReportType.PBC_REPORT_3_3, generateDate, isEdit, false);
        //update by leipeng 20200319  汇总数据提取全量模板
        Map<String, PbcReportDebtTermDetail> result = genAssetMonthReportemplate(generateDate, PbcReportType.PBC_REPORT_3_3, 18, pbcReport.getReportuuid());
        if (null == result) {
            return;
        }
        Map<String, Map<String, PbcReportDebtTermDetail>> deptMap = new HashMap();
        Map<String, BigDecimal> rateMap = new HashMap<>();
        /* add by jyl 19.11.25*/
        PbcMonthAssetPositionDto qmode = new PbcMonthAssetPositionDto();
        List<String> prodList = new ArrayList<>();
        for (PbcMonthPrdPostionDto dto : posProdList) {
            prodList.add(dto.getProdid());
        }
        //产品代码去重 leipeng 20200916
        prodList = prodList.stream().distinct().collect(Collectors.toList());

        qmode.setBookdate(com.joyintech.tams.report.interf.common.constant.DateUtil.parse(com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(generateDate)));
        qmode.setProdids(prodList);
        String strj = SvrDataUtil.getSysParam(SVReportGlobalConstant.CHB_ASSETS_BASIS_DATA_CHILD);
        if ("Y".equals(strj)) {
            //查询子资管计划资产基础指标数据表
            List<PbcMonthAssetPositionDto> posAssetListdtx = pbcMonthAssetPositionMapper.selectPositionFromValutionSubSystemdtx(qmode);
            Map<String, List<PbcMonthAssetPositionDto>> cprodListMap =
                    posAssetListdtx.stream().collect(Collectors.groupingBy(PbcMonthAssetPositionDto::getProdid));

            for (ListIterator<PbcMonthAssetPositionDto> iterator = posAssetList.listIterator(); iterator.hasNext(); ) {
                PbcMonthAssetPositionDto it = iterator.next();
                //新疆农信：人行月报表3-3 资管产品资产负债剩余期限统计模板除实收本金栏目数据正确外，其他数据均不对 modify by hpp 2020-12-02 start
                if (cprodListMap.containsKey(it.getProdid())) {
                    //GZYY-431 这里去除母资管计划但是只补充了一条子资管计划,需要将所有的子资管计划都补上
                    List<PbcMonthAssetPositionDto> cprodList = cprodListMap.get(it.getProdid());
                    for (PbcMonthAssetPositionDto single : cprodList) {
                        //single.setAmt(amt);
                        if (single.getAssetCode().equals(it.getAssetCode())) {
                            iterator.remove();
                            for (PbcMonthAssetPositionDto dto : cprodList) {
                                iterator.add(convertBalanceToPrdPosition1(dto, generateDate, it));
                            }
                            break;
                        }
                    }
                }
            }
        }
        /*end by jyl*/
        // 汇率取值开关
        String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
        for (PbcMonthAssetPositionDto pos : posAssetList) {
            // add by leipeng 20200319 获取所有部门对应信息,并列出单独的表结构
            if (null != pos.getInstitutionCode() && null == deptMap.get(pos.getInstitutionCode())) {
                Map<String, PbcReportDebtTermDetail> singDeptMap = genAssetMonthReportemplate(generateDate, PbcReportType.PBC_REPORT_3_3, 18, pbcReport.getReportuuid());
                deptMap.put(pos.getInstitutionCode(), singDeptMap);
            }
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.ASSET_G10000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "01", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.ASSET_G20000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "02", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.ASSET_G30000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "03", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.ASSET_G40000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "04", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.ASSET_G50000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "05", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.ASSET_G60000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "06", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.ASSET_G70000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "07", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.ASSET_G80000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "08", null);


            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G10000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "01", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G20000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "02", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G30000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "03", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G40000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "04", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G50000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "05", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G60000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "06", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G70000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "07", null);
            setAssetReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G80000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "08", null);
        }

        //按照产品到期日还是期次到期日出3-3报表
        String str = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_PERIOD);
        if ("Y".equals(str)) {
            //查询对应月末的周期型产品数据
            List<ChbCprodBalanceDto> cprodList = chbCprodBalanceDtoMapper.selectByValuedate(pbcReport.getEnddate());
            Map<String, List<ChbCprodBalanceDto>> cprodListMap1 =
                    cprodList.stream().collect(Collectors.groupingBy(ChbCprodBalanceDto::getProdId));

            for (ListIterator<PbcMonthPrdPostionDto> iterator = posProdList.listIterator(); iterator.hasNext(); ) {
                PbcMonthPrdPostionDto it = iterator.next();
                if (cprodListMap1.containsKey(it.getProdid())) {
                    iterator.remove();
                    for (ChbCprodBalanceDto single : cprodListMap1.get(it.getProdid())) {
                        iterator.add(convertBalanceToPrdPosition(single, it));
                    }
                }
            }
        }

        for (PbcMonthPrdPostionDto pos : posProdList) {
            // 销售区域为计划单列市的不统计（省份内已经统计）：210200大连市、330200宁波市、350200厦门市、370200青岛市、440300深圳市
            if ("210200".equals(pos.getBuyPlace()) || "330200".equals(pos.getBuyPlace()) || "350200".equals(pos.getBuyPlace()) ||
                    "370200".equals(pos.getBuyPlace()) || "440300".equals(pos.getBuyPlace())) {
                continue;
            }

            if (null != pos.getInstitutionCode() && null == deptMap.get(pos.getInstitutionCode())) {
                Map<String, PbcReportDebtTermDetail> singDeptMap = genAssetMonthReportemplate(generateDate, PbcReportType.PBC_REPORT_3_3, 18, pbcReport.getReportuuid());
                deptMap.put(pos.getInstitutionCode(), singDeptMap);
            }

            setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G10000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "01", null, null);
            setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G20000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "02", null, null);
            setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G30000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "03", null, null);
            setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G40000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "04", null, null);
            setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G50000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "05", null, null);
            setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G60000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "06", null, null);
            setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G70000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "07", null, null);
            setPrdReportValue(pos, result, deptMap.get(pos.getInstitutionCode()), PbcReportProperycode.DEBT_G80000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02,
                    PbcReportType.PBC_REPORT_3_3, rateMap, source, "08", null, null);

        }

        // 其他负债信息
        BigDecimal qtfz = BigDecimal.ZERO;
        String termId = DateUtil.format(DateUtil.addDay(generateDate,-1), DateUtil.MONTH_OTHER_FORMAT);
        BigDecimal otherDebtAmt = zG09PbcAssetDebtTermMapper.selectOtherDebt(termId, PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT);
        if (otherDebtAmt != null) {
            qtfz = otherDebtAmt;
        }
        // 负债及权益_债务工具+其他负债
        result.get(PbcReportProperycode.DEBT_G80000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02).
                setDebtInstrument(result.get(PbcReportProperycode.DEBT_G80000 + PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02).getDebtInstrument().add(qtfz));

        //修改数据组装及入库公共方法
        buildSumValAndInster(result, null, pbcReport, (!CollectionUtils.isEmpty(posAssetList) ||
                !CollectionUtils.isEmpty(posProdList)), false, null, null, null);
    }


    /**
     * 组装数据汇总项
     * @param result
     * @param deptCode
     * @param pbcReport
     * @param
     */
    private void  buildSumValAndInster(Map result,String deptCode,
                                         PbcMonthReport pbcReport,Boolean insertFlag,Boolean subtractionFlag,Map<String, BigDecimal> rateMap,String source,List<PbcMonthReport> prodAssetRegList){
        if (PbcReportType.PBC_REPORT_3_3.equals(pbcReport.getReporttype())){
            //资产端
            BigDecimal hjzczwgz=BigDecimal.ZERO;//资产债务工具
            BigDecimal hjzcgq=BigDecimal.ZERO;//资产股权
            BigDecimal hjzctd=BigDecimal.ZERO;//资产特定目的载体份额

            //产品端
            BigDecimal hjfzzwgz=BigDecimal.ZERO;//负债债务工具
            BigDecimal hjfzssbj=BigDecimal.ZERO;//负债实收本金
            Iterator<Map.Entry> entries = result.entrySet().iterator();

            while(entries.hasNext()){
                Map.Entry<String, PbcReportDebtTermDetail> dtoMap = entries.next();
                //如果key包含DEPTcode则是
                if(dtoMap.getKey().contains(PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02)){
                    hjfzzwgz=hjfzzwgz.add(dtoMap.getValue().getDebtInstrument()==null?BigDecimal.ZERO:dtoMap.getValue().getDebtInstrument());
                    hjfzssbj=hjfzssbj.add(dtoMap.getValue().getActualAmt()==null?BigDecimal.ZERO:dtoMap.getValue().getActualAmt());
                }else{
                    hjzczwgz=hjzczwgz.add(dtoMap.getValue().getDebtInstrument()==null?BigDecimal.ZERO:dtoMap.getValue().getDebtInstrument());
                    hjzcgq=hjzcgq.add(dtoMap.getValue().getStockRight()==null?BigDecimal.ZERO:dtoMap.getValue().getStockRight());
                    hjzctd=hjzctd.add(dtoMap.getValue().getSpecialShare()==null?BigDecimal.ZERO:dtoMap.getValue().getSpecialShare());
                }
            }

            PbcReportDebtTermDetail lineg00000fz = (PbcReportDebtTermDetail) result.get(PbcReportProperycode.DEBT_G00000+PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02);
            PbcReportDebtTermDetail lineg00000 = (PbcReportDebtTermDetail) result.get(PbcReportProperycode.ASSET_G00000+PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01);

            lineg00000.setDebtInstrument(hjzczwgz);
            lineg00000.setStockRight(hjzcgq);
            lineg00000.setSpecialShare(hjzctd);

            lineg00000fz.setDebtInstrument(hjfzzwgz);
            lineg00000fz.setActualAmt(hjfzssbj);

            //表2-2数据组装
        }

        List<ZG09PbcAssetDebtTermDto> insertList = new ArrayList<>();

        ZG09PbcAssetDebtTermDto zG09Dto = new ZG09PbcAssetDebtTermDto();
        //复制共通属性
        BeanUtil.copyPropertiesIgnoreNull(zG09Dto, pbcReport);
        zG09Dto.setPbcSubmitDeadline(pbcReport.getChbSubmitDeadline());
        zG09Dto.setIssuePartyId(CommonUtil.selectPbcBankCode(null));
        // 默认1：银行非保本理财
        zG09Dto.setPbcProdType("1");
        zG09Dto.setDeptCode(deptCode);
        zG09Dto.setG00001(BigDecimal.ZERO);
        zG09Dto.setG00002(BigDecimal.ZERO);
        zG09Dto.setCreateTime(new Date());
        zG09Dto.setUpdateTime(new Date());

        //发行人表内资产余额（本外币合计）=当期“表内资产数据维护.金融机构表内资产余额（本外币）”
        //发行人表内金融资产余额（本外币合计）=当期“表内资产数据维护.金融机构表内金融资产余额（本外币）
        StringBuffer sb = new StringBuffer();
        sb.append(pbcReport.getTermid()).insert(4,"-");
        Map<String,BigDecimal> mapTemp = pbcReportAssetDebtDetailMapper.selectByReportTermId(sb.toString());
        if(mapTemp!=null&&!mapTemp.isEmpty()){
            //发行人表内资产余额（本外币合计）
            zG09Dto.setG00001(mapTemp.get("finAssetBalance"));
            //发行人表内金融资产余额（本外币合计）
            zG09Dto.setG00002(mapTemp.get("finInsAssetBalance"));
        } else {
            //发行人表内资产余额（本外币合计）
            zG09Dto.setG00001(null);
            //发行人表内金融资产余额（本外币合计）
            zG09Dto.setG00002(null);
        }

        mappingValueTosub(zG09Dto,result);
        // 调整入库逻辑
        // zG09PbcAssetDebtTermMapper.insertSelective(zG09Dto);
        insertList.add(zG09Dto);

        /**指引补充需求变更  start */
        // 处理检查字段是否显示0 add by wangyaning 20230111
        if(!CollectionUtils.isEmpty(insertList)){
            dealZeroNumber(insertList);
        }
        /**指引补充需求变更   end */

        logger.info("采集平台资产负债剩余期限信息：报表生成校验 开始----------");
        List<ZG09PbcAssetDebtTermDto> listData = pbcDataCheckUtil.getTrueData(insertList, null, DictCodeListExt2.采集平台资产负债剩余期限信息.getValue(), true, "1", ZG09PbcAssetDebtTermDto.class);
        logger.info("采集平台资产负债剩余期限信息：报表生成校验 结束----------");
        intoDatabase(listData);
    }

    /**
     * 入库
     * @param insertlist
     */
    private void intoDatabase(List<ZG09PbcAssetDebtTermDto> insertlist) {
        logger.debug("ZG09 采集平台资产负债剩余期限信息：插入开始----------");
        int count = 0;
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            ZG09PbcAssetDebtTermMapper batchMapper = session.getMapper(ZG09PbcAssetDebtTermMapper.class);
            for (ZG09PbcAssetDebtTermDto dto : insertlist) {
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
            logger.error("ZG09 采集平台资产负债剩余期限信息 入库错误:" + e.getMessage(), e);
        } finally {
            session.close();
        }
        logger.debug("ZG09 采集平台资产负债剩余期限信息：插入结束----------");
    }

    /**
     *处理明细数据映射到报送dto
     */
    private  void  mappingValueTosub(ZG09PbcAssetDebtTermDto dto,Map result){
        Iterator<Map.Entry> entries = result.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, PbcReportDebtTermDetail> dtoMap = entries.next();
            PbcReportDebtTermDetail detailDto = dtoMap.getValue();
            if(StringUtil.equals("asset_g00000",detailDto.getPropertycode())){
                dto.setG000a(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG000b(detailDto.getStockRight().setScale(2, RoundingMode.HALF_UP));
                dto.setG000c(detailDto.getSpecialShare().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("debt_g00000",detailDto.getPropertycode())){
                dto.setG000d(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG000e(detailDto.getActualAmt().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("asset_g10000",detailDto.getPropertycode())){
                dto.setG100a(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG100b(detailDto.getStockRight().setScale(2, RoundingMode.HALF_UP));
                dto.setG100c(detailDto.getSpecialShare().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("debt_g10000",detailDto.getPropertycode())){
                dto.setG100d(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG100e(detailDto.getActualAmt().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("asset_g20000",detailDto.getPropertycode())){
                dto.setG200a(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG200b(detailDto.getStockRight().setScale(2, RoundingMode.HALF_UP));
                dto.setG200c(detailDto.getSpecialShare().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("debt_g20000",detailDto.getPropertycode())){
                dto.setG200d(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG200e(detailDto.getActualAmt().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("asset_g30000",detailDto.getPropertycode())){
                dto.setG300a(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG300b(detailDto.getStockRight().setScale(2, RoundingMode.HALF_UP));
                dto.setG300c(detailDto.getSpecialShare().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("debt_g30000",detailDto.getPropertycode())){
                dto.setG300d(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG300e(detailDto.getActualAmt().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("asset_g40000",detailDto.getPropertycode())){
                dto.setG400a(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG400b(detailDto.getStockRight().setScale(2, RoundingMode.HALF_UP));
                dto.setG400c(detailDto.getSpecialShare().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("debt_g40000",detailDto.getPropertycode())){
                dto.setG400d(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG400e(detailDto.getActualAmt().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("asset_g50000",detailDto.getPropertycode())){
                dto.setG500a(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG500b(detailDto.getStockRight().setScale(2, RoundingMode.HALF_UP));
                dto.setG500c(detailDto.getSpecialShare().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("debt_g50000",detailDto.getPropertycode())){
                dto.setG500d(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG500e(detailDto.getActualAmt().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("asset_g60000",detailDto.getPropertycode())){
                dto.setG600a(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG600b(detailDto.getStockRight().setScale(2, RoundingMode.HALF_UP));
                dto.setG600c(detailDto.getSpecialShare().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("debt_g60000",detailDto.getPropertycode())){
                dto.setG600d(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG600e(detailDto.getActualAmt().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("asset_g70000",detailDto.getPropertycode())){
                dto.setG700a(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG700b(detailDto.getStockRight().setScale(2, RoundingMode.HALF_UP));
                dto.setG700c(detailDto.getSpecialShare().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("debt_g70000",detailDto.getPropertycode())){
                dto.setG700d(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG700e(detailDto.getActualAmt().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("asset_g80000",detailDto.getPropertycode())){
                dto.setG800a(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG800b(detailDto.getStockRight().setScale(2, RoundingMode.HALF_UP));
                dto.setG800c(detailDto.getSpecialShare().setScale(2, RoundingMode.HALF_UP));
            }
            else if(StringUtil.equals("debt_g80000",detailDto.getPropertycode())){
                dto.setG800d(detailDto.getDebtInstrument().setScale(2, RoundingMode.HALF_UP));
                dto.setG800e(detailDto.getActualAmt().setScale(2, RoundingMode.HALF_UP));
            }
        }
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
        pbcMonthReport.setSendStatus(DictCodeListExt2.报送状态_未报送.getValue());
        pbcMonthReport.setStatus(DictCodeListExt2.报送数据状态_新建.getValue());
        // 报送时点
        // 当前批次报送时点集合
        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG09资产负债剩余期限信息.getValue(),
                pbcMonthReport.getStatus(),dateMap, pbcMonthReport.getEnddate(),SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
        if (!StringUtil.isEmptyList(dateList)) {
            pbcMonthReport.setChbSubmitDeadline(dateList.get(0));
            pbcMonthReport.setSubmitDeadline(dateList.get(1));
        }
        // 填报机构名称
        pbcMonthReport.setReportOrgName(CommonUtil.selectBankCode());
        ChbOrgRegistBankCode bankCode = CommonUtil.getUserBank();
        String  code=CommonUtil.selectPbcBankCode(null);
        String  name=CommonUtil.selectBankCodeName();
        if(null!=bankCode){
            code = bankCode.getRegistBankCode();
            name = bankCode.getBelongInstitutionName();
        }
        // 发行机构代码取值
        pbcMonthReport.setIssuePartyId(code);
        pbcMonthReport.setIssuePartyName(name);
        pbcMonthReport.setReportOrgName(name);
        // 产品品种01银行非保本理财
        pbcMonthReport.setProdVariety(PbcMonthReportConstants.PBC_PROD_VARIETY_01);
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
            //3-3模板使用类型
            if(PbcReportType.PBC_REPORT_3_3.equals(reportType)){
                PbcReportDebtTermDetail detail = new PbcReportDebtTermDetail();
                detail.setDetailuuid(IDGenerator.generate(36));
                detail.setReporttype(PbcReportType.PBC_REPORT_3_3);
                detail.setPropertycode(k.getPropertycode());
                detail.setGrouptype(k.getGrouptype());
                detail.setReportuuid(uuid);
                detail.setDebtInstrument(BigDecimal.ZERO);
                detail.setStockRight(BigDecimal.ZERO);
                detail.setSpecialShare(BigDecimal.ZERO);
                detail.setActualAmt(BigDecimal.ZERO);
                result.put(k.getPropertycode()+k.getGrouptype(),detail);
            }
        }

        return result;
    }

    /**
     * 新增资产多法人统一入口(产品头寸入口)
     * @param pos
     * @param dto
     * @param deptDto
     * @param code
     * @param rateMap
     * @param source
     * @param hxl
     * @param assetMap
     */
    private  void setPrdReportValue(PbcMonthPrdPostionDto pos,Map dto,
                                    Map deptDto,String code,String rep_code,Map<String, BigDecimal> rateMap,String source,String hxl,Map assetMap,BigDecimal amt){

        if(PbcReportType.PBC_REPORT_3_3.equals(rep_code)){
            //汇总数据计算
            setReportDebttermValueProd(pos,(PbcReportDebtTermDetail)dto.get(code),rateMap,source,hxl);
            if(null!=deptDto&&null!=deptDto.get(code)){
                setReportDebttermValueProd(pos,(PbcReportDebtTermDetail)deptDto.get(code),rateMap,source,hxl);
            }
        }

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
    private void setAssetReportValue(PbcMonthAssetPositionDto pos,Map dto,
                                     Map deptDto,String code,String rep_code,Map<String, BigDecimal> rateMap,String source,String hxl,Map assetMap){
        if(PbcReportType.PBC_REPORT_3_3.equals(rep_code)){
            //汇总数据计算
            setAssetReportDebttermValue(pos,(PbcReportDebtTermDetail)dto.get(code),rateMap,source,hxl);
            if(null!=deptDto&&null!=deptDto.get(code)){
                setAssetReportDebttermValue(pos,(PbcReportDebtTermDetail)deptDto.get(code),rateMap,source,hxl);
            }
            //表2-2数据组装
        }

    }

    /**
     * 表3-3 行塞值 从资产头寸
     * @param
     * @param line
     * @param rateMap 汇率
     * @param source  汇率取值开关
     * @param
     */
    private  void   setAssetReportDebttermValue(PbcMonthAssetPositionDto pos,PbcReportDebtTermDetail line
            ,Map<String, BigDecimal> rateMap,String source,String hxl){
        if(pos.getEndDaysPbc()==null){
            pos.setEndDaysPbc(BigDecimal.ZERO);
        }
        //人行报送其期限
        String pbfalg = SvrDataUtil.getSysParam(SVReportGlobalConstant.People_Bank_Deadline_FLAG);
        if(StringUtil.equals(pbfalg,DictCodeList.是否_否.getValue())){
            if(pos.getEndDaysPbc().compareTo(BigDecimal.ZERO)>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(31))<=0
                    &&"01".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(31))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(92))<=0
                    &&"02".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(92))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(184))<=0
                    &&"03".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(184))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(366))<=0
                    &&"04".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(366))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(731))<=0
                    &&"05".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(731))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(1096))<=0
                    &&"06".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(1096))>0
                    &&"07".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(BigDecimal.ZERO)==0
                    &&"08".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }
        }else{
            if(pos.getEndDaysPbc().compareTo(BigDecimal.ZERO)>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(30))<=0
                    &&"01".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(30))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(90))<=0
                    &&"02".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(90))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(180))<=0
                    &&"03".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(180))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(360))<=0
                    &&"04".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(360))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(720))<=0
                    &&"05".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(720))>0&&pos.getEndDaysPbc().compareTo(new BigDecimal(1080))<=0
                    &&"06".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(new BigDecimal(1080))>0
                    &&"07".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }else if(pos.getEndDaysPbc().compareTo(BigDecimal.ZERO)==0
                    &&"08".equals(hxl)){
                setDebttermValue(pos,line,rateMap,source);
            }
        }
    }

    private void setDebttermValue(PbcMonthAssetPositionDto pos,PbcReportDebtTermDetail line,Map<String, BigDecimal> rateMap,String source){
        BigDecimal cnyAmt = ChbCcyConvertUtil.exchangeMoney(
                pos.getCcy(),DictCodeListExt1.币种_中债_人民币.getValue(),pos.getEnddate(),pos.getAmt(),rateMap,source);
        BigDecimal cnyIntAmt = ChbCcyConvertUtil.exchangeMoney(
                pos.getCcy(),DictCodeListExt1.币种_中债_人民币.getValue(),pos.getEnddate(),pos.getIntAmt(),rateMap,source);
        if (PbcMonthReportConstants.PBC_GROUPTYPE_3_3_01.equals(line.getGrouptype())
                && PbcMonthReportConstants.POSITION_TYPE_ZC.equals(pos.getPositionType())
                && !PbcMonthReportConstants.PBC_ASSETTYPE3_J03.equals(pos.getAssetType3())
                && !PbcMonthReportConstants.PBC_ASSETTYPE3_J04.equals(pos.getAssetType3())
        ) {
            if( PbcMonthReportConstants.PBC_ASSETTYPE1_P11.equals(pos.getAssetType1())) {
                // 应收账款的利息值统计到资产：债务工具
                if (line.getDebtInstrument() != null) {
                    line.setDebtInstrument(line.getDebtInstrument().add(cnyIntAmt));
                } else {
                    line.setDebtInstrument(cnyIntAmt);
                }
            }
            if (// PbcMonthReportConstants.PBC_ASSETTYPE1_P01.equals(pos.getAssetType1())// 现金
                // ||
                    PbcMonthReportConstants.PBC_ASSETTYPE1_P02.equals(pos.getAssetType1())// 存款
                            || PbcMonthReportConstants.PBC_ASSETTYPE1_P03.equals(pos.getAssetType1())// 可转让存单
                            || PbcMonthReportConstants.PBC_ASSETTYPE1_P04.equals(pos.getAssetType1())// 债务证券
                            || PbcMonthReportConstants.PBC_ASSETTYPE1_P05.equals(pos.getAssetType1())// 贷款
                            || PbcMonthReportConstants.PBC_ASSETTYPE1_P06.equals(pos.getAssetType1())// 资产收益权
                            || PbcMonthReportConstants.PBC_ASSETTYPE1_P08.equals(pos.getAssetType1())// 金融衍生品
                            || PbcMonthReportConstants.PBC_ASSETTYPE1_P11.equals(pos.getAssetType1())) {// 应收账款
                // 债务工具
                if (line.getDebtInstrument() != null) {
                    line.setDebtInstrument(line.getDebtInstrument().add(cnyAmt));
                } else {
                    line.setDebtInstrument(cnyAmt);
                }
            } else if (PbcMonthReportConstants.PBC_ASSETTYPE2_T01.equals(pos.getAssetType2())// 股票
                    || PbcMonthReportConstants.PBC_ASSETTYPE2_T03.equals(pos.getAssetType2())// 其他股权
                    || PbcMonthReportConstants.PBC_ASSETTYPE2_T04.equals(pos.getAssetType2())// 境外股权
            ) {// 股权
                if (line.getStockRight() != null) {
                    line.setStockRight(line.getStockRight().add(cnyAmt));
                } else {
                    line.setStockRight(cnyAmt);
                }
            } else if (PbcMonthReportConstants.PBC_ASSETTYPE2_T02.equals(pos.getAssetType2())) {// 特定目的载体份额
                if (line.getSpecialShare() != null) {
                    line.setSpecialShare(line.getSpecialShare().add(cnyAmt));
                } else {
                    line.setSpecialShare(cnyAmt);
                }
            }
        } else if (PbcMonthReportConstants.PBC_GROUPTYPE_3_3_02.equals(line.getGrouptype())
                && (PbcMonthReportConstants.POSITION_TYPE_FZ.equals(pos.getPositionType())
                || PbcMonthReportConstants.PBC_ASSETTYPE3_J03.equals(pos.getAssetType3())
                || PbcMonthReportConstants.PBC_ASSETTYPE3_J04.equals(pos.getAssetType3())
        )){
            if( PbcMonthReportConstants.PBC_ASSETTYPE1_P11.equals(pos.getAssetType1())) {
                // 应付账款的利息值统计到负债：债务工具
                if (line.getDebtInstrument() != null) {
                    line.setDebtInstrument(line.getDebtInstrument().add(cnyIntAmt));
                } else {
                    line.setDebtInstrument(cnyIntAmt);
                }
            }
            if (PbcMonthReportConstants.PBC_ASSETTYPE1_P05.equals(pos.getAssetType1())// 贷款
                    || PbcMonthReportConstants.PBC_ASSETTYPE1_P08.equals(pos.getAssetType1())// 金融衍生品
                    || PbcMonthReportConstants.PBC_ASSETTYPE1_P12.equals(pos.getAssetType1())// 应付账款
                    || PbcMonthReportConstants.PBC_ASSETTYPE1_P13.equals(pos.getAssetType1())// 其他负债
            ) {// 债务工具 负债权益类
                if (line.getDebtInstrument() != null) {
                    line.setDebtInstrument(line.getDebtInstrument().add(cnyAmt));
                } else {
                    line.setDebtInstrument(cnyAmt);
                }
            }
        }
    }

    private  void setReportDebttermValueProd(PbcMonthPrdPostionDto pos,PbcReportDebtTermDetail line
            ,Map<String, BigDecimal> rateMap,String source,String hxl){
        BigDecimal cnyAmt = ChbCcyConvertUtil.exchangeMoney(
                pos.getCcy(),DictCodeListExt1.币种_中债_人民币.getValue(),pos.getEnddate(),pos.getEndqtysum(),rateMap,source);
        //人行报送其期限
        String pbfalg = SvrDataUtil.getSysParam(SVReportGlobalConstant.People_Bank_Deadline_FLAG);
        if(StringUtil.equals(pbfalg,DictCodeList.是否_否.getValue())){
            if(pos.getEndDays().compareTo(BigDecimal.ZERO)>0&&pos.getEndDays().compareTo(new BigDecimal(31))<=0
                    &&"01".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(31))>0&&pos.getEndDays().compareTo(new BigDecimal(92))<=0
                    &&"02".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(92))>0&&pos.getEndDays().compareTo(new BigDecimal(184))<=0
                    &&"03".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(184))>0&&pos.getEndDays().compareTo(new BigDecimal(366))<=0
                    &&"04".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(366))>0&&pos.getEndDays().compareTo(new BigDecimal(731))<=0
                    &&"05".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(731))>0&&pos.getEndDays().compareTo(new BigDecimal(1096))<=0
                    &&"06".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(1096))>0
                    &&"07".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(BigDecimal.ZERO)==0
                    &&"08".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }
        }else{
            if(pos.getEndDays().compareTo(BigDecimal.ZERO)>0&&pos.getEndDays().compareTo(new BigDecimal(30))<=0
                    &&"01".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(30))>0&&pos.getEndDays().compareTo(new BigDecimal(90))<=0
                    &&"02".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(90))>0&&pos.getEndDays().compareTo(new BigDecimal(180))<=0
                    &&"03".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(180))>0&&pos.getEndDays().compareTo(new BigDecimal(360))<=0
                    &&"04".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(360))>0&&pos.getEndDays().compareTo(new BigDecimal(720))<=0
                    &&"05".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(720))>0&&pos.getEndDays().compareTo(new BigDecimal(1080))<=0
                    &&"06".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(new BigDecimal(1080))>0
                    &&"07".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }else if(pos.getEndDays().compareTo(BigDecimal.ZERO)==0
                    &&"08".equals(hxl)){
                if(line.getActualAmt()!=null){
                    line.setActualAmt(line.getActualAmt().add(cnyAmt));
                }else{
                    line.setActualAmt(cnyAmt);
                }
            }
        }
    }

    /**
     * 将周期型产品余额数据转换成计算需要的产品头寸数据
     * @param balance
     * @return
     */
    private PbcMonthPrdPostionDto convertBalanceToPrdPosition(ChbCprodBalanceDto balance,PbcMonthPrdPostionDto dto){
        PbcMonthPrdPostionDto position=new PbcMonthPrdPostionDto();
        position.setProdid(balance.getProdId());
        position.setCcy(balance.getCcy());
        int days= com.joyintech.tams.report.interf.common.constant.DateUtil.getBetweenDayNumber(balance.getValueDate(), balance.getMdate());
        if(days<0){
            days=0;
        }
        position.setEndDays(new BigDecimal(days));
        position.setEndqtysum(balance.getBalance());
        position.setEnddate(balance.getValueDate());
        position.setInstitutionCode(dto.getInstitutionCode());
        return position;
    }

    /**
     * 将周期型产品余额数据转换成计算需要的资产头寸数据
     * @param balance
     * @return
     */
    private PbcMonthAssetPositionDto convertBalanceToPrdPosition1(PbcMonthAssetPositionDto balance, Date generateDate,PbcMonthAssetPositionDto dto){
        PbcMonthAssetPositionDto position=new PbcMonthAssetPositionDto();
        position.setProdid(balance.getProdid());
        position.setCcy(balance.getCcy());
        int days= com.joyintech.tams.report.interf.common.constant.DateUtil.getBetweenDayNumber(balance.getBookdate(), balance.getMdate());
        if(days<0){
            days=0;
        }
        position.setEndDays(new BigDecimal(days));
        position.setEndDaysPbc(new BigDecimal(days));
        position.setAmt(balance.getAmt());
        position.setIntAmt(balance.getIntAmt());
        position.setEnddate(com.joyintech.tams.report.interf.common.constant.DateUtil.parse(com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(generateDate)));
        position.setBookdate(balance.getBookdate());
        position.setPositionType(balance.getPositionType());
        position.setAssetType1(balance.getAssetType1());
        position.setAssetType2(balance.getAssetType2());
        position.setAssetCode(balance.getAssetCode());
        position.setDelayPayAmt(balance.getDelayPayAmt());
        position.setBlnarea(balance.getBlnarea());
        position.setCounterid(balance.getCounterid());
        position.setIndustry(balance.getIndustry());
        position.setRepprofittype(balance.getRepprofittype());
        position.setMdate(balance.getMdate());
        position.setInstitutionCode(dto.getInstitutionCode());
        return position;
    }


    /**
     * 根据PBC_IS_SHOW_ZERO参数处理字段为0或者空用于校验 add by wangyaning 20230111
     * @param insertlist
     */
    private void dealZeroNumber(List<ZG09PbcAssetDebtTermDto> insertlist) {
        String pbcIsShowZero = SvrDataUtil.getSysParam("PBC_IS_SHOW_ZERO");;
        for(ZG09PbcAssetDebtTermDto tempDto: insertlist) {
            //处理为空
            if (StringUtil.equals(pbcIsShowZero,"01")) {
                if (!ObjectUtils.isEmpty(tempDto.getG000a())) {
                    if(tempDto.getG000a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG000a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG000b())) {
                    if(tempDto.getG000b().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG000b(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG000c())) {
                    if(tempDto.getG000c().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG000c(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG000d())) {
                    if(tempDto.getG000d().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG000d(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG000e())) {
                    if(tempDto.getG000e().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG000e(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG100a())) {
                    if(tempDto.getG100a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG100a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG100b())) {
                    if(tempDto.getG100b().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG100b(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG100c())) {
                    if(tempDto.getG100c().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG100c(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG100d())) {
                    if(tempDto.getG100d().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG100d(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG100e())) {
                    if(tempDto.getG100e().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG100e(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG200a())) {
                    if(tempDto.getG200a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG200a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG200b())) {
                    if(tempDto.getG200b().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG200b(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG200c())) {
                    if(tempDto.getG200c().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG200c(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG200d())) {
                    if(tempDto.getG200d().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG200d(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG200e())) {
                    if(tempDto.getG200e().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG200e(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG300a())) {
                    if(tempDto.getG300a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG300a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG300b())) {
                    if(tempDto.getG300b().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG300b(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG300c())) {
                    if(tempDto.getG300c().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG300c(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG300d())) {
                    if(tempDto.getG300d().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG300d(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG300e())) {
                    if(tempDto.getG300e().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG300e(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG400a())) {
                    if(tempDto.getG400a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG400a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG400b())) {
                    if(tempDto.getG400b().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG400b(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG400c())) {
                    if(tempDto.getG400c().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG400c(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG400d())) {
                    if(tempDto.getG400d().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG400d(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG400e())) {
                    if(tempDto.getG400e().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG400e(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG500a())) {
                    if(tempDto.getG500a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG500a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG500b())) {
                    if(tempDto.getG500b().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG500b(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG500c())) {
                    if(tempDto.getG500c().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG500c(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG500d())) {
                    if(tempDto.getG500d().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG500d(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG500e())) {
                    if(tempDto.getG500e().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG500e(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG600a())) {
                    if(tempDto.getG600a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG600a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG600b())) {
                    if(tempDto.getG600b().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG600b(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG600c())) {
                    if(tempDto.getG600c().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG600c(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG600d())) {
                    if(tempDto.getG600d().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG600d(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG600e())) {
                    if(tempDto.getG600e().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG600e(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG700a())) {
                    if(tempDto.getG700a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG700a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG700b())) {
                    if(tempDto.getG700b().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG700b(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG700c())) {
                    if(tempDto.getG700c().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG700c(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG700d())) {
                    if(tempDto.getG700d().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG700d(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG700e())) {
                    if(tempDto.getG700e().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG700e(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG800a())) {
                    if(tempDto.getG800a().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG800a(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG800b())) {
                    if(tempDto.getG800b().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG800b(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG800c())) {
                    if(tempDto.getG800c().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG800c(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG800d())) {
                    if(tempDto.getG800d().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG800d(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG800e())) {
                    if(tempDto.getG800e().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG800e(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG00001())) {
                    if(tempDto.getG00001().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG00001(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getG00002())) {
                    if(tempDto.getG00002().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setG00002(null);
                    }
                }
            } else if (StringUtil.equals(pbcIsShowZero,"02")) {
                //处理成0
                if (ObjectUtils.isEmpty(tempDto.getG000a())) {
                    tempDto.setG000a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG000b())) {
                    tempDto.setG000b(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG000c())) {
                    tempDto.setG000c(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG000d())) {
                    tempDto.setG000d(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG000e())) {
                    tempDto.setG000e(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG100a())) {
                    tempDto.setG100a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG100b())) {
                    tempDto.setG100b(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG100c())) {
                    tempDto.setG100c(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG100d())) {
                    tempDto.setG100d(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG100e())) {
                    tempDto.setG100e(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG200a())) {
                    tempDto.setG200a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG200b())) {
                    tempDto.setG200b(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG200c())) {
                    tempDto.setG200c(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG200d())) {
                    tempDto.setG200d(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG200e())) {
                    tempDto.setG200e(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG300a())) {
                    tempDto.setG300a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG300b())) {
                    tempDto.setG300b(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG300c())) {
                    tempDto.setG300c(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG300d())) {
                    tempDto.setG300d(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG300e())) {
                    tempDto.setG300e(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG400a())) {
                    tempDto.setG400a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG400b())) {
                    tempDto.setG400b(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG400c())) {
                    tempDto.setG400c(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG400d())) {
                    tempDto.setG400d(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG400e())) {
                    tempDto.setG400e(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG500a())) {
                    tempDto.setG500a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG500b())) {
                    tempDto.setG500b(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG500c())) {
                    tempDto.setG500c(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG500d())) {
                    tempDto.setG500d(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG500e())) {
                    tempDto.setG500e(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG600a())) {
                    tempDto.setG600a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG600b())) {
                    tempDto.setG600b(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG600c())) {
                    tempDto.setG600c(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG600d())) {
                    tempDto.setG600d(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG600e())) {
                    tempDto.setG600e(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG700a())) {
                    tempDto.setG700a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG700b())) {
                    tempDto.setG700b(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG700c())) {
                    tempDto.setG700c(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG700d())) {
                    tempDto.setG700d(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG700e())) {
                    tempDto.setG700e(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG800a())) {
                    tempDto.setG800a(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG800b())) {
                    tempDto.setG800b(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG800c())) {
                    tempDto.setG800c(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG800d())) {
                    tempDto.setG800d(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG800e())) {
                    tempDto.setG800e(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG00001())) {
                    tempDto.setG00001(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getG00002())) {
                    tempDto.setG00002(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
    }
}
