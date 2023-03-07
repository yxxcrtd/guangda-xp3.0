package com.joyintech.tams.svreport.generationcheck.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.svreport.generationcheck.constant.ReportGenerationCheckConstant;
import com.joyintech.tams.svreport.generationcheck.dto.PbcReportGenerationCheck001Dto;
import com.joyintech.tams.svreport.generationcheck.service.PbcReportGenerationCheck001Service;
import com.joyintech.tams.svreport.generationcheck.service.PbcReportGenerationCheckMService;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service.ZG01PbcPrdBaseinfoSubMService;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dto.ZG02PbcPrdInitRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.serivce.ZG02PbcPrdInitRaiseSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.serivce.ZG03PrdBaseinfoEndSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.service.ZG04PbcPrdCashRaiseSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.service.ZG05PbcColPrdAssetDebtSubService;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto.ZG06PbcAssetSusfructSub;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.serivce.ZG06PbcAssetSusfructSubService001;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.service.ZG07PbcColLoanDetailSubMService;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.service.ZG08SpecCounterQuityMService;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dto.ZG09PbcColReport;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.service.ZG09PbcAssetDebtTermSubService;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto.ZG10PbcColReportDto;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.service.ZG10PbcColAssetConfigurationSubService;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11CompanyBondInfoSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.service.ZG11CompanyBondInfoSubService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 报表生成检查
 *
 * @author yangxianjiu
 * @date 2022/11/28
 */
@PageId("PbcReportGenerationCheck001")
@Controller
@RequestMapping("/svreport/generationcheck/PbcReportGenerationCheck001")
public class PbcReportGenerationCheck001Controller extends BaseController {

    @Autowired
    private PbcReportGenerationCheckMService pbcReportGenerationCheckMService;

    @Autowired
    private PbcReportGenerationCheck001Service pbcReportGenerationCheck001Service;

    /**
     * 微服务service
     */
    @Autowired
    private ZG01PbcPrdBaseinfoSubMService zg01PbcPrdBaseinfoSubMService;

    /**
     * 产品初始募集登记service
     */
    @Autowired
    private ZG02PbcPrdInitRaiseSubService001 zg02PbcPrdInitRaiseSubService001;

    /**
     * 产品终止登记service
     */
    @Autowired
    private ZG03PrdBaseinfoEndSubService001 zg03PrdBaseinfoEndSubService001;

    /**
     * 产品资金募集信息service
     */
    @Autowired
    private ZG04PbcPrdCashRaiseSubService001 zg04PbcPrdCashRaiseSubService001;

    @Autowired
    private ZG05PbcColPrdAssetDebtSubService zG05PbcColPrdAssetDebtSubService;

    /**
     * 产品初始募集登记service
     */
    @Autowired
    private ZG06PbcAssetSusfructSubService001 zg06PbcAssetSusfructSubService001;

    @Autowired
    private ZG07PbcColLoanDetailSubMService zg07PbcColLoanDetailSubMService;

    @Autowired
    private ZG08SpecCounterQuityMService zg08SpecCounterQuityMService;

    @Autowired
    private ZG09PbcAssetDebtTermSubService zg09PbcAssetDebtTermSubService;

    @Autowired
    private ZG10PbcColAssetConfigurationSubService zG10PbcColAssetConfigurationSubService;

    /**
     * 微服务service
     */
    @Autowired
    private ZG11CompanyBondInfoSubService zg11CompanyBondInfoSubService;


    @Override
    public String init(Model model) {
        model.addAttribute("checkFlag","1");
        super.setSelectOptionToModel(model, DictCode.大数据采集平台报表类型, DictCode.数据校验规则类型, DictCode.数据校验规则标识,DictCode.数据校验状态);
        return super.init(model);
    }

    /**
     * 分页查询
     *
     * @return 查询数据
     */
    @ResponseBody
    @RequestMapping(ReportGlobalConstant.SEARCH_PAGE)
    public Page<PbcReportGenerationCheck001Dto> searchPage() {
        return pbcReportGenerationCheckMService.searchPage(RequestUtil.getPageParam());
    }

    /**
     * excel 导出
     *
     * @return 导出数据
     * @throws Exception 异常
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        String issuePartyId =  CommonUtil.selectPbcBankCode(null);
        Map<String, Object> params = RequestUtil.getPageParam();
        List<PbcReportGenerationCheck001Dto> checkList = Lists.newArrayList();
        List<ZG01PbcPrdBaseinfoSub> zg01 = Lists.newArrayList();
        List<ZG02PbcPrdInitRaiseSub> zg02 = Lists.newArrayList();
        List<ZG03PbcPrdBaseinfoEndSub> zg03 = Lists.newArrayList();
        List<ZG04PbcProdRaiseSub> zg04 = Lists.newArrayList();
        List<ZG05PbcColPrdAssetDebtDto> zg05 = Lists.newArrayList();
        List<ZG06PbcAssetSusfructSub> zg06 = Lists.newArrayList();
        List<ZG07PbcColLoanDetailSubDto> zg07 = Lists.newArrayList();
        List<ZG08SpeCounterQuityDto> zg08 = Lists.newArrayList();
        List<ZG09PbcColReport> zg09 = Lists.newArrayList();
        List<ZG10PbcColReportDto> zg10 = Lists.newArrayList();
        List<ZG11CompanyBondInfoSubDto> zg11 = Lists.newArrayList();
        String termid="";
        File outFile = null;
        // 导出名称
        String downloadFileName = "";
        if (params.containsKey("flag") && StringUtil.equals("1", params.get("flag"))) {
            List<PbcReportGenerationCheck001Dto> tempList = Lists.newArrayList();
            // 选中导出
            if (params.containsKey("datas")) {
                // 去重
                List<String> dataUuidList = JsonUtils.json2List(params.get("selectData").toString(), PbcReportGenerationCheck001Dto.class)
                        .stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(PbcReportGenerationCheck001Dto::getDataUuid))), ArrayList::new))
                        .stream().map(PbcReportGenerationCheck001Dto::getDataUuid).collect(Collectors.toList());
                Map<String, Object> map = new HashMap<>();
                map.put("keyIdList", dataUuidList);
                // 查询 导出原数据
                tempList = pbcReportGenerationCheck001Service.searchExportExcel(map);
            } else {
                // 查询导出
                tempList = pbcReportGenerationCheck001Service.searchExport(params);
                // 去重
                tempList = tempList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(PbcReportGenerationCheck001Dto::getDataUuid))), ArrayList::new));
            }
            String reportName = params.get("reportName").toString();
            // 构建导出数据
            // 报表配置
            // 导出配置文件 及 导出文件名
            Date maxDate=new Date();
            List<Date> dateList=new ArrayList<>();
            if (StringUtil.equals(DictCodeListExt2.采集平台资管产品基本信息.getValue(), reportName)) {
                zg01.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG01PbcPrdBaseinfoSub.class)).collect(Collectors.toList()));
                if(!StringUtil.isEmptyList(zg01) && zg01.size()>0){
                    zg01.forEach(
                            item ->{
                                if("2".equals(item.getInfoType())){
                                    //变更日期
                                    dateList.add(item.getCdate());
                                }else{
                                    //起息日
                                    dateList.add(item.getVdate());
                                }
                            }
                    );
                    maxDate = Collections.max(dateList);
                }
                if(maxDate!=null){
                    termid=DateUtil.format(maxDate,"yyyyMMdd");
                }else {
                    termid=DateUtil.format(new Date(),"yyyyMMdd");
                }
                outFile = zg01PbcPrdBaseinfoSubMService.makeFile(ReportGenerationCheckConstant.ZG01_CONF_XML_PATH, zg01);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG01"+GlobalConstant.UNDER_LINE
                        + termid + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台资管产品初始募集信息.getValue(), reportName)) {
                zg02.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG02PbcPrdInitRaiseSub.class)).collect(Collectors.toList()));
                if(!StringUtil.isEmptyList(zg02) && zg02.size()>0){
                    zg02.forEach(
                            item ->{
                                item.getStatusDate();
                                dateList.add(item.getStatusDate());
                            }
                    );
                    maxDate = Collections.max(dateList);
                }
                if(maxDate!=null){
                    termid=DateUtil.format(maxDate,"yyyyMMdd");
                }else {
                    termid=DateUtil.format(new Date(),"yyyyMMdd");
                }
                outFile = zg02PbcPrdInitRaiseSubService001.makeFile(ReportGenerationCheckConstant.ZG02_CONF_XML_PATH_01, zg02);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG02"+GlobalConstant.UNDER_LINE
                        + termid  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台资管产品终止信息.getValue(), reportName)) {
                zg03.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG03PbcPrdBaseinfoEndSub.class)).collect(Collectors.toList()));
                if(!StringUtil.isEmptyList(zg03) && zg03.size()>0){
                    zg03.forEach(
                            item ->{
                                item.getStatusDate();
                                dateList.add(item.getStatusDate());
                            }
                    );
                    maxDate = Collections.max(dateList);
                }
                if(maxDate!=null){
                    termid=DateUtil.format(maxDate,"yyyyMMdd");
                }else {
                    termid=DateUtil.format(new Date(),"yyyyMMdd");
                }
                outFile = zg03PrdBaseinfoEndSubService001.makeFile(ReportGenerationCheckConstant.ZG03_CONF_XML_PATH_01, zg03);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG03"+GlobalConstant.UNDER_LINE
                        + termid + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台资管产品存续期募集信息.getValue(), reportName)) {
                zg04.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG04PbcProdRaiseSub.class)).collect(Collectors.toList()));
                if(zg04 !=null && zg04.size()>0){
                    termid=zg04.get(0).getRaisingDate();
                }
                //去除INS代码
                zg04.forEach(
                        item ->{
                            item.setPbcProdId(item.getPbcProdId().replace("INS",""));
                        }
                );
                termid=com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(4,6)),"yyyyMMdd");
                outFile = zg04PbcPrdCashRaiseSubService001.makeFile(ReportGenerationCheckConstant.ZG04_CONF_XML_PATH_01, zg04);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG04"+GlobalConstant.UNDER_LINE
                        + termid+ GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台资管产品资产负债信息.getValue(), reportName)) {
                zg05.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG05PbcColPrdAssetDebtDto.class)).collect(Collectors.toList()));
                if(zg05 !=null && zg05.size()>0){
                    termid=zg05.get(0).getTermid();
                }
                termid=com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(4,6)),"yyyyMMdd");
                outFile = zG05PbcColPrdAssetDebtSubService.makeFile(ReportGenerationCheckConstant.ZG05_CONF_XML_PATH_01, zg05);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG05"+GlobalConstant.UNDER_LINE
                        + termid + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台资产收益权明细信息.getValue(), reportName)) {
                zg06.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG06PbcAssetSusfructSub.class)).collect(Collectors.toList()));
                if(zg06 !=null && zg06.size()>0){
                    termid=zg06.get(0).getTermId();
                }
                termid=com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(4,6)),"yyyyMMdd");
                outFile = zg06PbcAssetSusfructSubService001.makeFile(ReportGenerationCheckConstant.ZG06_CONF_XML_PATH_01, zg06);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG06"+GlobalConstant.UNDER_LINE
                        + termid + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台除回购和拆借外贷款明细信息.getValue(), reportName)) {
                zg07.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG07PbcColLoanDetailSubDto.class)).collect(Collectors.toList()));
                if(zg07 !=null && zg07.size()>0){
                    termid=zg07.get(0).getTermid();
                }
                termid=com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(4,6)),"yyyyMMdd");
                outFile = zg07PbcColLoanDetailSubMService.makeFile(ReportGenerationCheckConstant.ZG07_CONF_XML_PATH_01, zg07);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG07"+GlobalConstant.UNDER_LINE
                        + termid  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台特定目的载体交易对手明细信息.getValue(), reportName)) {
                zg08.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG08SpeCounterQuityDto.class)).collect(Collectors.toList()));
                if(zg08 !=null && zg08.size()>0){
                    termid=zg08.get(0).getTermid();
                }
                termid=com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(4,6)),"yyyyMMdd");
                outFile = zg08SpecCounterQuityMService.makeFile(ReportGenerationCheckConstant.ZG08_CONF_XML_PATH_01, zg08);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG08"+GlobalConstant.UNDER_LINE
                        + termid  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台资产负债剩余期限信息.getValue(), reportName)) {
                zg09.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG09PbcColReport.class)).collect(Collectors.toList()));
                if(zg09 !=null && zg09.size()>0){
                    termid=zg09.get(0).getTermid();
                }
                termid=com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(4,6)),"yyyyMMdd");
                outFile = zg09PbcAssetDebtTermSubService.makeFile(ReportGenerationCheckConstant.ZG09_CONF_XML_PATH_01, zg09);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG09"+GlobalConstant.UNDER_LINE
                        + termid  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台债券等资产配置情况信息.getValue(), reportName)) {
                zg10.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG10PbcColReportDto.class)).collect(Collectors.toList()));
                if(zg10 !=null && zg10.size()>0){
                    termid=zg10.get(0).getTermid();
                }
                termid=com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(4,6)),"yyyyMMdd");
                outFile = zG10PbcColAssetConfigurationSubService.makeFile(ReportGenerationCheckConstant.ZG10_CONF_XML_PATH_01, zg10);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG10"+GlobalConstant.UNDER_LINE
                        + termid   + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            } else if (StringUtil.equals(DictCodeListExt2.采集平台企业债券分行业企业规模情况信息.getValue(), reportName)) {
                zg11.addAll(tempList.stream().map(item -> JsonUtils.json2Object(item.getOrgData(), ZG11CompanyBondInfoSubDto.class)).collect(Collectors.toList()));
                if(zg11 !=null && zg11.size()>0){
                    termid=zg11.get(0).getTermid();
                }
                termid=com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(Integer.parseInt(termid.substring(0,4)),Integer.parseInt(termid.substring(4,6)),"yyyyMMdd");
                outFile = zg11CompanyBondInfoSubService.makeFile(ReportGenerationCheckConstant.ZG11_CONF_XML_PATH_01, zg11);
                downloadFileName = issuePartyId+GlobalConstant.UNDER_LINE+"ZG11"+GlobalConstant.UNDER_LINE
                        + termid  + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);

            }
        } else {
            // 选中导出
            if (params.containsKey("selectData") && StringUtil.endsWith(params.get(GlobalConstant.ELEMENT_ID).toString(), "_btn_exportCheck")) {
                // stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(PbcReportGenerationCheck001Dto::getDataUuid))), ArrayList::new))
                // dataUuid 去重
                checkList = JsonUtils.json2List(params.get("selectData").toString(), PbcReportGenerationCheck001Dto.class);
                outFile = pbcReportGenerationCheck001Service.makeFile(
                        "/com/joyintech/tams/svreport/generationcheck/conf/export_conf.xml", checkList);
            } else {
                // 查询导出
                checkList = pbcReportGenerationCheck001Service.searchExport(params);
                outFile = pbcReportGenerationCheck001Service.makeFile(
                        "/com/joyintech/tams/svreport/generationcheck/conf/export_conf.xml", checkList);
            }
            downloadFileName = "报表生成检查" + GlobalConstant.OPEN_PARENTHESIS + DateUtil.format(new Date())
                    + GlobalConstant.CLOSE_PARENTHESIS + GlobalConstant.POINT + FileNameUtil.getFileExtName(outFile);
        }
        return ExportUtil.export(downloadFileName, outFile, true);
    }

}
