package com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.service;

import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.ChbOrgRegistBankCode;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.util.DeadlineUtil;
import com.joyintech.tams.svreport.pbc.assetposition.dao.PbcMonthAssetReportMapper;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dao.ZG10PbcColReportMapper;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto.PbcColReport;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto.PbcColReportAssetConfigurationDto;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto.ZG10PbcColReportDto;
import com.joyintech.tams.svreport.pbc.common.constants.PbcMonthReportConstants;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportProperycode;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.svreport.pbc.report.dao.PbcReportTemplateMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.svreport.pbc.report.dto.PbcReportTemplateDto;
import com.joyintech.tams.svreport.pbc.reportdetail.dto.PbcMonthReportDetailAsset;
import com.joyintech.tams.svreport.pbc.reportdetail.service.PbcMonthReportDetailAssetUtil;
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

@Service
public class ZG10PbcColReportBatchService {

    @Autowired
    private ZG10PbcColReportMapper zg10PbcColReportMapper;

    /**
     * 资产头寸mapper
     */
    @Autowired
    private PbcReportTemplateMapper pbcReportTemplateMapper;

    @Autowired
    private PbcDataCheckUtil pbcDataCheckUtil;

    @Autowired
    private SqlSessionTemplate template;

    /**
     * 资产头寸mapper
     */
    @Autowired
    private PbcMonthAssetReportMapper pbcMonthAssetReportMapper;
    private List<PbcMonthReportDetailAsset> assetDetailList;
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
        if (zg10PbcColReportMapper.selectCountByTermId(termId,PbcReportType.PBC_REPORT_4_1) > 0) {
            logger.info("当月存在已报送数据，批处理不再继续执行！");
            return;
        }
        //删除当期所有数据
        zg10PbcColReportMapper.deleteByTermId(termId,PbcReportType.PBC_REPORT_4_1);

        //报表生成
        genRegistReport(batchTime,null,false);

    }

    /**
     * 功能:ZG10 债券等资产配置情况信息
     *
     * @param executeDate 批处理日期
     * @param prodIdList  产品list
     * @version
     * @author：shenpengfei 2022年11月7日
     * @since
     */
    public void genRegistReport(Date executeDate, List<String> prodIdList, boolean isEdit) {
        assetDetailList = new ArrayList<>();
        //查询资产头寸
        List<PbcMonthAssetPositionDto> posAssetList = new ArrayList<>();
        posAssetList.addAll(pbcMonthAssetReportMapper.selectAssetPositionWithPbcPrdCode(prodIdList, com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDayOfLayMonth(executeDate)));
        generateMonthReportAssetConfiguration(posAssetList,executeDate, isEdit,PbcReportType.PBC_REPORT_4_1);
    }

    /**
     * 功能:表4-1 资管产品资产配置情况
     * @since 1.0.0
     * @version 1.0.0
     * @author：zhengxiaolian 2022年01月11日
     * @param
     */
    public void generateMonthReportAssetConfiguration(List<PbcMonthAssetPositionDto> posAssetList,
                                                      Date generateDate,boolean isEdit,String reportType){
        // 生成月度报表主表数据
        PbcColReport pbcReport = genMonthReportByReporttype(PbcReportType.PBC_REPORT_4_1,generateDate,isEdit,false);

        Map<String, PbcColReportAssetConfigurationDto> result = genAssetMonthReportemplate(generateDate,  reportType, 57,pbcReport.getReportuuid());

        Map<String,Map<String,PbcMonthAssetPositionDto>> deptMap = new HashMap();

        // 汇率取值开关
        String source = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);

        // 汇率map,可重复使用
        Map<String, BigDecimal> rateMap = new HashMap<>();

        Map<String,Date> massageMap = new HashMap<>();

        massageMap.put("endDate", pbcReport.getEnddate());

        for(PbcMonthAssetPositionDto pos :posAssetList){
            if(null!=pos.getInstitutionCode()&&null==deptMap.get(pos.getInstitutionCode())){
                Map<String,PbcMonthAssetPositionDto> singDeptMap = genAssetMonthReportemplate(generateDate,  reportType, 57,pbcReport.getReportuuid());
                singDeptMap.putAll(singDeptMap);
                deptMap.put(pos.getInstitutionCode(), singDeptMap);
            }
            if(PbcMonthReportConstants.PBC_ASSETTYPE2_S03.equals(pos.getAssetType2())){
                //政策性金融债券 统计人行资产二级分类为“政策性金融债券”的资产
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H11000_ZCXJRZQ, reportType, rateMap, source, null, massageMap);

                //h11300	    无评级	统计“政策性金融债券”中没有评级的债券
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H11300_WPJ, reportType, rateMap, source, null, massageMap);
//                }
            }

            if(PbcMonthReportConstants.PBC_ASSETTYPE2_S04.equals(pos.getAssetType2())){
                //h12000	  其他金融债券	统计人行资产二级分类为“其他金融债券”的资产
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H12000_QTJRZQ, reportType, rateMap, source, null, massageMap);

                if(DictCodeListExt2.资产评级_AAAPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AAA.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AAADAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AAPLUS.getValue().equals(pos.getRating())
                ){
                    //h11100	    AA+（含）以上	统计“其他金融债券”中评级结果为AAA+、AAA-、AAA、AA+的债券
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H12100_AAJYS, reportType, rateMap, source, null, massageMap);
                }else if(DictCodeListExt2.资产评级_AA.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AADAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_APLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_A.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN1.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN2.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN3.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBBPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBB.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBBDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BB.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_B.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCCPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCC.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCCDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CC.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_C.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_D.getValue().equals(pos.getRating())
                ){
                    //h11200	    AA+以下	统计“其他金融债券”中评级结果为AA、AA-、A+、A、A-、BBB+、BBB、BBB-、BB+、BB、BB-、B+、B、B-、CCC+、CCC、CCC-、CC+、CC、CC-、C+、C、C-、D的债券
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H12200_AAJYX, reportType, rateMap, source, null, massageMap);
                }else{
                    //h11300	    无评级	统计“其他金融债券”中没有评级的债券
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H12300_WPJ, reportType, rateMap, source, null, massageMap);
                }
            }
            if(PbcMonthReportConstants.PBC_ASSETTYPE2_S05.equals(pos.getAssetType2())){
                //h13000	  企业债券	统计人行资产二级分类为=“企业债券”的资产
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H13000_QYZQ, reportType, rateMap, source, null, massageMap);

                if(DictCodeListExt2.资产评级_AAAPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AAA.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AAADAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AAPLUS.getValue().equals(pos.getRating())
                ){
                    //h13100	    AA+（含）以上	统计“企业债券”中评级结果为AAA+、AAA-、AAA、AA+的债券
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H13100_AAJYS, reportType, rateMap, source, null, massageMap);
                }else if(DictCodeListExt2.资产评级_AA.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AADAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_APLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_A.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN1.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN2.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN3.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBBPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBB.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBBDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BB.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_B.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCCPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCC.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCCDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CC.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_C.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_D.getValue().equals(pos.getRating())
                ){
                    //h13200	    AA+以下	统计“企业债券”中评级结果为AA、AA-、A+、A、A-、BBB+、BBB、BBB-、BB+、BB、BB-、B+、B、B-、CCC+、CCC、CCC-、CC+、CC、CC-、C+、C、C-、D的债券
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H13200_AAJYX, reportType, rateMap, source, null, massageMap);
                }else{
                    //h13300	    无评级	统计“企业债券”中没有评级的债券
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H13300_WPJ, reportType, rateMap, source, null, massageMap);
                }
            }
            if(PbcMonthReportConstants.PBC_ASSETTYPE2_S07.equals(pos.getAssetType2())){
                //h14000	  资产支持证券	统计人行资产二级分类为“资产支持证券”的资产
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H14000_ZCZCZQ, reportType, rateMap, source, null, massageMap);

                if(DictCodeListExt2.资产评级_AAAPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AAA.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AAADAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AAPLUS.getValue().equals(pos.getRating())
                ){
                    //h14100	    AA+（含）以上	统计“资产支持证券”中评级结果为AAA+、AAA-、AAA、AA+的债券
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H14100_AAJYS, reportType, rateMap, source, null, massageMap);
                }else if(DictCodeListExt2.资产评级_AA.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_AADAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_APLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_A.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN1.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN2.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_ADAWN3.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBBPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBB.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBBDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BB.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BBDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_B.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_BDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCCPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCC.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCCDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CC.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CCDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CPLUS.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_C.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_CDAWN.getValue().equals(pos.getRating())
                        ||DictCodeListExt2.资产评级_D.getValue().equals(pos.getRating())
                ){
                    //h14200	    AA+以下	统计“资产支持证券”中评级结果为AA、AA-、A+、A、A-、BBB+、BBB、BBB-、BB+、BB、BB-、B+、B、B-、CCC+、CCC、CCC-、CC+、CC、CC-、C+、C、C-、D的债券
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H14200_AAJYX, reportType, rateMap, source, null, massageMap);
                }else{
                    //h14300	    无评级	统计“资产支持证券”中没有评级的债券
                    setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H14300_WPJ, reportType, rateMap, source, null, massageMap);
                }
            }

            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Y01.equals(pos.getAssetType3())){
                //h21000	  其中：企业债	统计人行资产三级分类为“企业债”的资产
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H21000_QZQYZ, reportType, rateMap, source, null, massageMap);
            }

            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Y02.equals(pos.getAssetType3())){
                //h22000	       公司债	统计人行人行资产三级分类为“公司债”的资产
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H22000_GSZ, reportType, rateMap, source, null, massageMap);
            }

            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Y03.equals(pos.getAssetType3())){
                //h23000	       非金融企业债务融资工具	统计人行资产三级分类为“企业债券融资工具”的资产
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H23000_FJRQYZW, reportType, rateMap, source, null, massageMap);
            }

            if(!PbcMonthReportConstants.PBC_ASSETTYPE1_P14.equals(pos.getAssetType1())&&
                    !PbcMonthReportConstants.PBC_ASSETTYPE1_P11.equals(pos.getAssetType1())){
                if(DictCodeListExt3.银行资本补充工具_永续债_债性.getValue().equals(pos.getBankCapital())||
                        DictCodeListExt3.银行资本补充工具_永续债_股性.getValue().equals(pos.getBankCapital())||
                        DictCodeListExt3.银行资本补充工具_二级资本债.getValue().equals(pos.getBankCapital())||
                        DictCodeListExt3.银行资本补充工具_优先股.getValue().equals(pos.getBankCapital())) {
                    //h30000	三、银行资本补充工具合计	=“h31000优先股+h32000永续债（债性）+h33000永续债（股性）+h34000二级资本债”
                    //h30000	三、银行资本补充工具合计	=“h31000优先股+h32000永续债（债性）+h33000永续债（股性）+h34000二级资本债”
                    if (DictCodeListExt3.银行资本补充工具_优先股.getValue().equals(pos.getBankCapital())) {
                        //h31000	  其中：优先股	统计资产要素信息中 是否商业银行优先股=“是”的资产
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H31000_QZYXG, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H30000_YHZBBCGJ, reportType, rateMap, source, null, massageMap);
                    }

                    if (DictCodeListExt3.银行资本补充工具_永续债_债性.getValue().equals(pos.getBankCapital())) {
                        //h32000	        永续债（债性）	统计资产要素信息中 是否商业银行永续债=“是”，且债券性质=“债性”的资产
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H32000_YXZZX, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H30000_YHZBBCGJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (DictCodeListExt3.银行资本补充工具_永续债_股性.getValue().equals(pos.getBankCapital())) {
                        //h33000 永续债（股性）	统计资产要素信息中 是否商业银行永续债=“是”，且债券性质=“股性”的资产
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H33000_YXZGX, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H30000_YHZBBCGJ, reportType, rateMap, source, null, massageMap);
                    }

                    if (DictCodeListExt3.银行资本补充工具_二级资本债.getValue().equals(pos.getBankCapital())) {
                        //h34000	   二级资本债	统计资产要素信息中 是否商业银行二级资本债=“是”的资产
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H34000_EJZBZ, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H30000_YHZBBCGJ, reportType, rateMap, source, null, massageMap);
                    }
                }
                //h40000	四、债券逆回购和同业拆借 	=“h41000住户+h42000广义政府+h43000非金融企业+h44000银行业存款类机构
                // +h45000银行业非存款类金融机构+h46000非银行业金融机构+h47000特定目的载体+h48000境外”
                if(PbcMonthReportConstants.PBC_ASSETTYPE2_R03.equals(pos.getAssetType2())) {
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L01.equals(pos.getAssetType3())) {
                        //h41000	  住户	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“住户”　
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H41000_ZH, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }

                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L02.equals(pos.getAssetType3())) {
                        //h42000	　广义政府	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“广义政府”　
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H42000_GYZF, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }

                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L03.equals(pos.getAssetType3())) {
                        //h43000	　非金融企业  	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“非金融企业”　　
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H43000_FJRQY, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }

                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L04.equals(pos.getAssetType3())) {
                        //h44000	  银行业存款类金融机构	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“银行业存款类金融机构”　
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H44000_YHYCKLJRJG, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }

                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L05.equals(pos.getAssetType3())) {
                        //h45000	　银行业非存款类金融机构	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“银行业非存款类金融机构”　
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H45000_YHYFCKLJRJG, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }

                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L06.equals(pos.getAssetType3())) {
                        //h46000	　非银行业金融机构  	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“非银行业金融机构”　　
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H46000_FYHYJRJG, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L07.equals(pos.getAssetType3())) {
                        //h46100	 其中：中央交易对手方	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“非银行业金融机构-中央交易对手”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H46100_QZZYJYDSF, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H46000_FYHYJRJG, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    //h47000	特定目的载体	=“h47100银行非保本理财+h47200信托公司资管产品+h47300证券公司及其子公司资管产品+h47400基金管理公司及其子公司专户
                    // + h47500期货公司及其子公司资管产品+h47600保险资管产品+h47700金融资产投资公司资管产品+h47800公募基金+
                    // h47900私募机构私募基金+h47a00其他特定目的载体”
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L08.equals(pos.getAssetType3())) {
                        //h47100	银行非保本理财	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“银行非保本理财”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47100_YHFBBLC, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L09.equals(pos.getAssetType3())) {
                        //h47200	信托公司资管产品	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“信托公司资管产品”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47200_XTGSZGCP, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L10.equals(pos.getAssetType3())) {
                        //h47300	证券公司及其子公司资管产品	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“证券公司及其子公司资管产品”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47300_ZQGSJQZGS, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L11.equals(pos.getAssetType3())) {
                        //h47400	基金管理公司及其子公司专户	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“基金管理公司及其子公司专户”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47400_JJGLGSJQZGS, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L12.equals(pos.getAssetType3())) {
                        //h47500	期货公司及其子公司资管产品	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“期货公司及其子公司资管产品”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47500_QHGLGSJQZGS, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L13.equals(pos.getAssetType3())) {
                        //h47600	保险资管产品	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“保险资管产品”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47600_BXZGCP, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L14.equals(pos.getAssetType3())) {
                        //h47700	金融资产投资公司资管产品	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“金融资产投资公司资管产品”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47700_JRZCTZGSJQZGS, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L15.equals(pos.getAssetType3())) {
                        //h47800	公募基金	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“公募基金”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47800_GMJJ, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L16.equals(pos.getAssetType3())) {
                        //h47900	私募机构私募基金	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“私募机构私募基金”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47900_SMJGSMJJ, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L99.equals(pos.getAssetType3())) {
                        //h47a00	其他特定目的载体	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“其他特定目的载体”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47A00_QTTDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H47000_TDMDZT, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                    if (PbcMonthReportConstants.PBC_ASSETTYPE3_L98.equals(pos.getAssetType3())) {
                        //h48000	　境外	统计人行资产二级分类为“拆借和回购”，且人行资产三级分类为“境外”
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H48000_JW, reportType, rateMap, source, null, massageMap);
                        setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H40000_ZQNHGHTYCJ, reportType, rateMap, source, null, massageMap);
                    }
                }
            }
            //“存款”（h51000）+“可转让存单”（h52000）+“债务证券”（h53000）+“除回购和拆借外贷款”（h54000）+“回购和拆借”（h55000）
            // +“资产收益权”（h56000）+“股权及特定目的载体份额”（h57000）+“金融衍生品”（h58000）+“应收账款”（h59000）+“其他”（h5a000）
            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Z01.equals(pos.getAssetType3())){
                //h51000	  存款	取上游系统“130209存放同业减值准备”科目余额非逾期资产
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H51000_CK, reportType, rateMap, source, null, massageMap);
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H50000_ZCJZZB, reportType, rateMap, source, null, massageMap);
            }
            //h52000	  可转让存单
            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Z02.equals(pos.getAssetType3())){
                //h52000
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H52000_KZRCD, reportType, rateMap, source, null, massageMap);
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H50000_ZCJZZB, reportType, rateMap, source, null, massageMap);
            }
            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Z03.equals(pos.getAssetType3())){
                //h53000	  债务证券	取上游系统二级科目为“130201摊余成本类债券减值准备”科目，
                // 对应的所以三级科目且三级对应四级科目对应明细资产的人行资产一级分类为“债务证券”非逾期资产
                //头寸的时候做过映射处理，这里直接取
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H53000_ZWZQ, reportType, rateMap, source, null, massageMap);
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H50000_ZCJZZB, reportType, rateMap, source, null, massageMap);
            }
            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Z04.equals(pos.getAssetType3())){
                //h54000	  除回购和拆借外贷款	取上游系统二级科目为“130201摊余成本类债券减值准备”或“130203 摊余成本类计划类资产减值准备”科目，
                // 对应的所以三级科目且三级对应且四级科目对应明细资产的人行资产二级分类为“除回购和拆借外贷款” 非逾期资产
                //头寸的时候做过映射处理，这里直接取
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H54000_CHGHCJWDK, reportType, rateMap, source, null, massageMap);
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H50000_ZCJZZB, reportType, rateMap, source, null, massageMap);
            }

            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Z05.equals(pos.getAssetType3())){
                //h55000	  回购和拆借	取上游系统“130207买入返售资产减值准备”+“130210拆放同业减值准备”科目余额非逾期资产
                //头寸的时候做过映射处理，这里直接取
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H55000_HGHCJ, reportType, rateMap, source, null, massageMap);
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H50000_ZCJZZB, reportType, rateMap, source, null, massageMap);
            }

            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Z06.equals(pos.getAssetType3())){
                //h56000	  资产收益权	取上游系统二级科目为 “130203 摊余成本类计划类资产减值准备”科目，
                // 对应的所以三级科目且三级对应且四级科目对应明细资产的人行资产一级分类为“资产收益权”非逾期资产
                //头寸的时候做过映射处理，这里直接取
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H56000_ZCSYQ, reportType, rateMap, source, null, massageMap);
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H50000_ZCJZZB, reportType, rateMap, source, null, massageMap);
            }

            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Z07.equals(pos.getAssetType3())){
                //h57000	  股权及特定目的载体份额	取上游系统二级科目为 “130203 摊余成本类计划类资产减值准备”科目，对应的所以三级科目且三级对应且四级科目对应明细资产的人行资产一级分类为“股权及特定目的载体份额”的非逾期资产
                //头寸的时候做过映射处理，这里直接取
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H57000_GQJTDMDDZTFE, reportType, rateMap, source, null, massageMap);
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H50000_ZCJZZB, reportType, rateMap, source, null, massageMap);
            }
            //h58000	  金融衍生品	置空
            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Z08.equals(pos.getAssetType3())){
                //头寸的时候做过映射处理，这里直接取
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H58000_JRYSP, reportType, rateMap, source, null, massageMap);
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H50000_ZCJZZB, reportType, rateMap, source, null, massageMap);
            }
            if(PbcMonthReportConstants.PBC_ASSETTYPE3_Z09.equals(pos.getAssetType3())){
                //h59000	  应收账款	取上游系统二级科目为 “130203 摊余成本类计划类资产减值准备”、“130201 摊余成本类债券减值准备”科目、“130207 买入返售资产减值准备”、“130209 存放同业减值准备”、“130210 拆放同业减值准备”科目，对应的所以三级科目且三级对应且四级科目对应明细资产的已逾期
                //头寸的时候做过映射处理，这里直接取
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H59000_YSZK, reportType, rateMap, source, null, massageMap);
                setAssetReportValue(pos, result, deptMap, PbcReportProperycode.AMT_H50000_ZCJZZB, reportType, rateMap, source, null, massageMap);
            }
        }

        List<ZG10PbcColReportDto> insertList = new ArrayList<>();

        //修改数据组装及入库公共方法
        insertList.add(buildSumValAndInster(result, null, pbcReport, !CollectionUtils.isEmpty(posAssetList),false,null,null,null));
        //循环分支机构的数据组装及入库
        for (Map.Entry<String, Map<String, PbcMonthAssetPositionDto>> dtoMap : deptMap.entrySet()) {
            insertList.add(buildSumValAndInster(dtoMap.getValue(), dtoMap.getKey(), pbcReport, !CollectionUtils.isEmpty(posAssetList),false,null,null,null));
        }
        if (CollectionUtils.isEmpty(insertList)){
            logger.info("数据集为空");
            return;
        }

        /**指引补充需求变更  start */
        // 处理检查字段是否显示0 add by wangyaning 20230111
        if(!CollectionUtils.isEmpty(insertList)){
            dealZeroNumber(insertList);
        }
        /**指引补充需求变更   end */

        logger.info("采集平台债券等资产配置情况信息：报表生成校验 开始----------");
        List<ZG10PbcColReportDto> listData = pbcDataCheckUtil.getTrueData(insertList, null, DictCodeListExt2.采集平台债券等资产配置情况信息.getValue(), true, "1", ZG10PbcColReportDto.class);
        logger.info("采集平台债券等资产配置情况信息：报表生成校验 结束----------");
        intoDatabase(listData);
    }

    /**
     * 入库
     * @param insertlist
     */
    private void intoDatabase(List<ZG10PbcColReportDto> insertlist) {
        logger.debug("ZG10 采集平台债券等资产配置情况信息：插入开始----------");
        int count = 0;
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            ZG10PbcColReportMapper batchMapper = session.getMapper(ZG10PbcColReportMapper.class);
            for (ZG10PbcColReportDto dto : insertlist) {
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
            logger.error("ZG10 采集平台债券等资产配置情况信息 入库错误:" + e.getMessage(), e);
        } finally {
            session.close();
        }
        logger.debug("ZG10 采集平台债券等资产配置情况信息：插入结束----------");
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
        pbcMonthReport.setReporttype(reporttype);
        // 起止日期
        pbcMonthReport.setStartdate(reportStartDate);
        pbcMonthReport.setEnddate(reportEndDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        pbcMonthReport.setTermid(sdf.format(reportStartDate));
        // 数据日期
        pbcMonthReport.setStatusDate(reportDate);
        // 制表日期
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
        List<Date> dateList = DeadlineUtil.getChbEndDate(DictCodeListExt2.报表名称_ZG10债券等资产配置情况信息登记.getValue(),
                pbcMonthReport.getStatus(), dateMap, pbcMonthReport.getEnddate(), SVReportGlobalConstant.SVR_SUBMIT_TIME_PBC);
        if (!StringUtil.isEmptyList(dateList)) {
            pbcMonthReport.setPbcSubmitDeadline(dateList.get(0));
            pbcMonthReport.setSubmitDeadline(dateList.get(1));
        }
        ChbOrgRegistBankCode bankCode = CommonUtil.getUserBank();
        String code = CommonUtil.selectPbcBankCode(null);
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

            //4-1模板使用类型
            if (PbcReportType.PBC_REPORT_4_1.equals(reportType)) {
                PbcColReportAssetConfigurationDto detail = new PbcColReportAssetConfigurationDto();
                detail.setDetailuuid(IDGenerator.generate(36));
                detail.setPropertycode(k.getPropertycode());
                detail.setReportuuid(uuid);
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


        //表4-1数据组装
        if(PbcReportType.PBC_REPORT_4_1.equals(rep_code)){

            //汇总数据计算
            setAssetConfigurationDetailValue(pos,(PbcColReportAssetConfigurationDto)dto.get(code), rateMap,source);
            if(PbcMonthReportDetailAssetUtil.isAssetDate3(code)&&PbcReportType.PBC_REPORT_4_1.equals(rep_code)){
                assetDetailList.add(PbcMonthReportDetailAssetUtil.buildAssetDetail3(pos,dto.get(code),code,hxl));
            }
            //部门数据计算
            if(null!=deptDto&&null!=deptDto.get(code)){

                setAssetConfigurationDetailValue(pos,(PbcColReportAssetConfigurationDto)deptDto.get(code), rateMap,source);
            }

        }

    }

    /**
     * 功能:4-1 资管产品资产配置情况
     * @since 1.1.0
     * @version 1.1.0
     * @author：zhengxiaolian 2022年1月11日
     * @param pos
     * @param dto
     * void
     */
    public void setAssetConfigurationDetailValue(PbcMonthAssetPositionDto pos,PbcColReportAssetConfigurationDto dto,
                                                 Map<String, BigDecimal> rateMap,String source){
        //人行资产余额的计算公式：成本+应计利息+估值增值。
        BigDecimal cnyAmt = ChbCcyConvertUtil.exchangeMoney(
                pos.getCcy(),DictCodeListExt1.币种_中债_人民币.getValue(),pos.getEnddate(),pos.getAmt(),rateMap,source);
        BigDecimal cnyIntAmt = ChbCcyConvertUtil.exchangeMoney(
                pos.getCcy(),DictCodeListExt1.币种_中债_人民币.getValue(),pos.getEnddate(),pos.getIntAmt(),rateMap,source);
        BigDecimal balanceAmt = BigDecimal.ZERO;
        balanceAmt = balanceAmt.add(cnyAmt).add(cnyIntAmt);
        dto.setBalance(dto.getBalance()==null?BigDecimal.ZERO:dto.getBalance());
        dto.setBalance(dto.getBalance().add(balanceAmt));
    }
    /**
     * 组装数据汇总项
     *
     * @param result
     * @param deptCode
     * @param pbcReport
     * @param
     */
    private ZG10PbcColReportDto buildSumValAndInster(Map result, String deptCode,
                                      PbcColReport pbcReport, Boolean insertFlag, Boolean subtractionFlag, Map<String, BigDecimal> rateMap, String source, List<PbcMonthReport> prodAssetRegList) {
        //根据部门进行数据插入
        if (pbcReport.isGen()) {
            // 判断是否有头寸数据有则插入
            if (insertFlag) {
                pbcReport.setOrgCode(deptCode);
                /**新增组装ZG10报送数据 */
                return insertZG10Data(pbcReport, result);
            }
        }
        return null;
    }

    /**
     * 暂不考虑多币种
     * @param pbcReport  主表数据
     * @param result   明细数据
     */
    private ZG10PbcColReportDto insertZG10Data(PbcColReport pbcReport, Map result){

        //1.本位币
        ZG10PbcColReportDto zg10Data=new ZG10PbcColReportDto();
        //复制共通属性
        BeanUtil.copyPropertiesIgnoreNull(zg10Data, pbcReport);
        zg10Data.setReportuuid(IDGenerator.generate(36));
        zg10Data.setPbcProdType(PbcMonthReportConstants.PBC_COL_PROD_TYPE_1);// 产品品种1银行非保本理财);
        zg10Data.setIssuePartyId(CommonUtil.selectPbcBankCode(null));
        mappingValueTosub(zg10Data,result);
        // 调整入库逻辑
        //zg10PbcColReportMapper.insertSelective(zg10Data);
        return zg10Data;
    }

    /**
     *处理明细数据映射到报送dto
     */
    private  void  mappingValueTosub(ZG10PbcColReportDto dto,Map result){
        Iterator<Map.Entry> entries = result.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, PbcColReportAssetConfigurationDto> dtoMap = entries.next();
            PbcColReportAssetConfigurationDto  detailDto=dtoMap.getValue();
            BigDecimal value = detailDto.getBalance() == null ? BigDecimal.ZERO : detailDto.getBalance();
            if(StringUtil.equals("AMT_h10000_ZWZQ",detailDto.getPropertycode())){dto.setH10000(null);}
            else if(StringUtil.equals("AMT_h11000_ZCXJRZQ",detailDto.getPropertycode())){dto.setH11000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h11100_AAJYS",detailDto.getPropertycode())){dto.setH11100(null);}
            else if(StringUtil.equals("AMT_h11200_AAJYX",detailDto.getPropertycode())){dto.setH11200(null);}
            else if(StringUtil.equals("AMT_h11300_WPJ",detailDto.getPropertycode())){dto.setH11300(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h12000_QTJRZQ",detailDto.getPropertycode())){dto.setH12000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h12100_AAJYS",detailDto.getPropertycode())){dto.setH12100(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h12200_AAJYX",detailDto.getPropertycode())){dto.setH12200(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h12300_WPJ",detailDto.getPropertycode())){dto.setH12300(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h13000_QYZQ",detailDto.getPropertycode())){dto.setH13000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h13100_AAJYS",detailDto.getPropertycode())){dto.setH13100(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h13200_AAJYX",detailDto.getPropertycode())){dto.setH13200(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h13300_WPJ",detailDto.getPropertycode())){dto.setH13300(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h14000_ZCZCZQ",detailDto.getPropertycode())){dto.setH14000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h14100_AAJYS",detailDto.getPropertycode())){dto.setH14100(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h14200_AAJYX",detailDto.getPropertycode())){dto.setH14200(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h14300_WPJ",detailDto.getPropertycode())){dto.setH14300(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h20000_ZYZQAQZFL",detailDto.getPropertycode())){dto.setH20000(null);}
            else if(StringUtil.equals("AMT_h21000_QZQYZ",detailDto.getPropertycode())){dto.setH21000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h22000_GSZ",detailDto.getPropertycode())){dto.setH22000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h23000_FJRQYZW",detailDto.getPropertycode())){dto.setH23000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h30000_YHZBBCGJ",detailDto.getPropertycode())){dto.setH30000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h31000_QZYXG",detailDto.getPropertycode())){dto.setH31000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h32000_YXZZX",detailDto.getPropertycode())){dto.setH32000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h33000_YXZGX",detailDto.getPropertycode())){dto.setH33000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h34000_EJZBZ",detailDto.getPropertycode())){dto.setH34000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h40000_ZQNHGHTYCJ",detailDto.getPropertycode())){dto.setH40000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h41000_ZH",detailDto.getPropertycode())){dto.setH41000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h42000_GYZF",detailDto.getPropertycode())){dto.setH42000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h43000_FJRQY",detailDto.getPropertycode())){dto.setH43000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h44000_YHYCKLJRJG",detailDto.getPropertycode())){dto.setH44000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h45000_YHYFCKLJRJG",detailDto.getPropertycode())){dto.setH45000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h46000_FYHYJRJG",detailDto.getPropertycode())){dto.setH46000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h46100_QZZYJYDSF",detailDto.getPropertycode())){dto.setH46100(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47000_TDMDZT",detailDto.getPropertycode())){dto.setH47000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47100_YHFBBLC",detailDto.getPropertycode())){dto.setH47100(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47200_XTGSZGCP",detailDto.getPropertycode())){dto.setH47200(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47300_ZQGSJQZGS",detailDto.getPropertycode())){dto.setH47300(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47400_JJGLGSJQZGS",detailDto.getPropertycode())){dto.setH47400(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47500_QHGLGSJQZGS",detailDto.getPropertycode())){dto.setH47500(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47600_BXZGCP",detailDto.getPropertycode())){dto.setH47600(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47700_JRZCTZGSJQZGS",detailDto.getPropertycode())){dto.setH47700(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47800_GMJJ",detailDto.getPropertycode())){dto.setH47800(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47900_SMJGSMJJ",detailDto.getPropertycode())){dto.setH47900(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h47a00_QTTDMDZT",detailDto.getPropertycode())){dto.setH47a00(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h48000_JW",detailDto.getPropertycode())){dto.setH48000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h50000_ZCJZZB",detailDto.getPropertycode())){dto.setH50000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h51000_CK",detailDto.getPropertycode())){dto.setH51000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h52000_KZRCD",detailDto.getPropertycode())){dto.setH52000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h53000_ZWZQ",detailDto.getPropertycode())){dto.setH53000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h54000_CHGHCJWDK",detailDto.getPropertycode())){dto.setH54000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h55000_HGHCJ",detailDto.getPropertycode())){dto.setH55000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h56000_ZCSYQ",detailDto.getPropertycode())){dto.setH56000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h57000_GQJTDMDDZTFE",detailDto.getPropertycode())){dto.setH57000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h58000_JRYSP",detailDto.getPropertycode())){dto.setH58000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h59000_YSZK",detailDto.getPropertycode())){dto.setH59000(value.setScale(2, RoundingMode.HALF_UP));}
            else if(StringUtil.equals("AMT_h5a000_QT",detailDto.getPropertycode())){dto.setH5a000(value.setScale(2, RoundingMode.HALF_UP));}
        }
    }


    /**
     * 根据PBC_IS_SHOW_ZERO参数处理字段为0或者空用于校验 add by wangyaning 20230111
     * @param insertlist
     */
    private void dealZeroNumber(List<ZG10PbcColReportDto> insertlist) {
        String pbcIsShowZero = SvrDataUtil.getSysParam("PBC_IS_SHOW_ZERO");;
        for(ZG10PbcColReportDto tempDto: insertlist) {
            //处理为空
            if (StringUtil.equals(pbcIsShowZero,"01")) {
//                if (!ObjectUtils.isEmpty(tempDto.getH10000())) {
//                    if(tempDto.getH10000().compareTo(BigDecimal.ZERO) == 0){
//                        tempDto.setH10000(null);
//                    }
//                }
                if (!ObjectUtils.isEmpty(tempDto.getH11000())) {
                    if(tempDto.getH11000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH11000(null);
                    }
                }
//                if (!ObjectUtils.isEmpty(tempDto.getH11100())) {
//                    if(tempDto.getH11100().compareTo(BigDecimal.ZERO) == 0){
//                        tempDto.setH11100(null);
//                    }
//                }
//                if (!ObjectUtils.isEmpty(tempDto.getH11200())) {
//                    if(tempDto.getH11200().compareTo(BigDecimal.ZERO) == 0){
//                        tempDto.setH11200(null);
//                    }
//                }
                if (!ObjectUtils.isEmpty(tempDto.getH11300())) {
                    if(tempDto.getH11300().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH11300(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH12000())) {
                    if(tempDto.getH12000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH12000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH12100())) {
                    if(tempDto.getH12100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH12100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH12200())) {
                    if(tempDto.getH12200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH12200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH12300())) {
                    if(tempDto.getH12300().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH12300(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH13000())) {
                    if(tempDto.getH13000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH13000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH13100())) {
                    if(tempDto.getH13100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH13100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH13200())) {
                    if(tempDto.getH13200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH13200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH13300())) {
                    if(tempDto.getH13300().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH13300(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH14000())) {
                    if(tempDto.getH14000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH14000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH14100())) {
                    if(tempDto.getH14100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH14100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH14200())) {
                    if(tempDto.getH14200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH14200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH14300())) {
                    if(tempDto.getH14300().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH14300(null);
                    }
                }
//                if (!ObjectUtils.isEmpty(tempDto.getH20000())) {
//                    if(tempDto.getH20000().compareTo(BigDecimal.ZERO) == 0){
//                        tempDto.setH20000(null);
//                    }
//                }
                if (!ObjectUtils.isEmpty(tempDto.getH21000())) {
                    if(tempDto.getH21000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH21000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH22000())) {
                    if(tempDto.getH22000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH22000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH23000())) {
                    if(tempDto.getH23000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH23000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH30000())) {
                    if(tempDto.getH30000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH30000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH31000())) {
                    if(tempDto.getH31000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH31000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH32000())) {
                    if(tempDto.getH32000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH32000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH33000())) {
                    if(tempDto.getH33000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH33000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH34000())) {
                    if(tempDto.getH34000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH34000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH40000())) {
                    if(tempDto.getH40000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH40000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH41000())) {
                    if(tempDto.getH41000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH41000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH42000())) {
                    if(tempDto.getH42000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH42000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH43000())) {
                    if(tempDto.getH43000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH43000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH44000())) {
                    if(tempDto.getH44000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH44000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH45000())) {
                    if(tempDto.getH45000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH45000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH46000())) {
                    if(tempDto.getH46000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH46000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH46100())) {
                    if(tempDto.getH46100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH46100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47000())) {
                    if(tempDto.getH47000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47100())) {
                    if(tempDto.getH47100().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47100(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47200())) {
                    if(tempDto.getH47200().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47200(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47300())) {
                    if(tempDto.getH47300().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47300(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47400())) {
                    if(tempDto.getH47400().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47400(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47500())) {
                    if(tempDto.getH47500().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47500(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47600())) {
                    if(tempDto.getH47600().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47600(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47700())) {
                    if(tempDto.getH47700().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47700(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47800())) {
                    if(tempDto.getH47800().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47800(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47900())) {
                    if(tempDto.getH47900().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47900(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH47a00())) {
                    if(tempDto.getH47a00().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH47a00(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH48000())) {
                    if(tempDto.getH48000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH48000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH50000())) {
                    if(tempDto.getH50000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH50000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH51000())) {
                    if(tempDto.getH51000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH51000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH52000())) {
                    if(tempDto.getH52000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH52000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH53000())) {
                    if(tempDto.getH53000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH53000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH54000())) {
                    if(tempDto.getH54000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH54000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH55000())) {
                    if(tempDto.getH55000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH55000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH56000())) {
                    if(tempDto.getH56000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH56000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH57000())) {
                    if(tempDto.getH57000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH57000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH58000())) {
                    if(tempDto.getH58000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH58000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH59000())) {
                    if(tempDto.getH59000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH59000(null);
                    }
                }
                if (!ObjectUtils.isEmpty(tempDto.getH5a000())) {
                    if(tempDto.getH5a000().compareTo(BigDecimal.ZERO) == 0){
                        tempDto.setH5a000(null);
                    }
                }
            } else if (StringUtil.equals(pbcIsShowZero,"02")) {
                //处理成0
//                if (ObjectUtils.isEmpty(tempDto.getH10000())) {
//                    tempDto.setH10000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
//                }
                if (ObjectUtils.isEmpty(tempDto.getH11000())) {
                    tempDto.setH11000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
//                if (ObjectUtils.isEmpty(tempDto.getH11100())) {
//                    tempDto.setH11100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
//                }
//                if (ObjectUtils.isEmpty(tempDto.getH11200())) {
//                    tempDto.setH11200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
//                }
                if (ObjectUtils.isEmpty(tempDto.getH11300())) {
                    tempDto.setH11300(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH12000())) {
                    tempDto.setH12000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH12100())) {
                    tempDto.setH12100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH12200())) {
                    tempDto.setH12200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH12300())) {
                    tempDto.setH12300(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH13000())) {
                    tempDto.setH13000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH13100())) {
                    tempDto.setH13100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH13200())) {
                    tempDto.setH13200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH13300())) {
                    tempDto.setH13300(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH14000())) {
                    tempDto.setH14000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH14100())) {
                    tempDto.setH14100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH14200())) {
                    tempDto.setH14200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH14300())) {
                    tempDto.setH14300(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
//                if (ObjectUtils.isEmpty(tempDto.getH20000())) {
//                    tempDto.setH20000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
//                }
                if (ObjectUtils.isEmpty(tempDto.getH21000())) {
                    tempDto.setH21000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH22000())) {
                    tempDto.setH22000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH23000())) {
                    tempDto.setH23000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH30000())) {
                    tempDto.setH30000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH31000())) {
                    tempDto.setH31000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH32000())) {
                    tempDto.setH32000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH33000())) {
                    tempDto.setH33000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH34000())) {
                    tempDto.setH34000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH40000())) {
                    tempDto.setH40000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH41000())) {
                    tempDto.setH41000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH42000())) {
                    tempDto.setH42000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH43000())) {
                    tempDto.setH43000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH44000())) {
                    tempDto.setH44000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH45000())) {
                    tempDto.setH45000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH46000())) {
                    tempDto.setH46000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH46100())) {
                    tempDto.setH46100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47000())) {
                    tempDto.setH47000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47100())) {
                    tempDto.setH47100(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47200())) {
                    tempDto.setH47200(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47300())) {
                    tempDto.setH47300(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47400())) {
                    tempDto.setH47400(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47500())) {
                    tempDto.setH47500(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47600())) {
                    tempDto.setH47600(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47700())) {
                    tempDto.setH47700(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47800())) {
                    tempDto.setH47800(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47900())) {
                    tempDto.setH47900(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH47a00())) {
                    tempDto.setH47a00(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH48000())) {
                    tempDto.setH48000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH50000())) {
                    tempDto.setH50000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH51000())) {
                    tempDto.setH51000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH52000())) {
                    tempDto.setH52000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH53000())) {
                    tempDto.setH53000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH54000())) {
                    tempDto.setH54000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH55000())) {
                    tempDto.setH55000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH56000())) {
                    tempDto.setH56000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH57000())) {
                    tempDto.setH57000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH58000())) {
                    tempDto.setH58000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH59000())) {
                    tempDto.setH59000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                if (ObjectUtils.isEmpty(tempDto.getH5a000())) {
                    tempDto.setH5a000(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
    }

}
