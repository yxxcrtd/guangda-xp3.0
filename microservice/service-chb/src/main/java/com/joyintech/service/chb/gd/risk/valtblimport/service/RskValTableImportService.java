/*
 * 文件名：RskValTableImportService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.chb.gd.risk.valtblimport.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.*;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDtlDto;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalassetcode.service.RskValAssetCodeService;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalrelateimport.dao.RskValRelateImportMapper;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalstructure.dto.RskValStructureDto;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalstructure.service.RskValStructuresService;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;
import com.joyintech.service.chb.gd.risk.penetrationmanagement.rskvalsumname.service.RskValSumname001Service;
import com.joyintech.service.chb.gd.risk.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;
import com.joyintech.service.chb.gd.risk.rskValtblPrtBotasset.service.RskValtblPrtBotassetService;
import com.joyintech.service.chb.gd.risk.valtblimport.dao.RskValTableImportMapper;
import com.joyintech.service.chb.gd.risk.valtblimport.dto.*;
import com.joyintech.service.chb.gd.risk.valtblimport.filtertool.filter.Filter;
import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumAssetSubMapper;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblSubject;
import com.joyintech.tams.common.base.entity.RskValtblSumAssetSub;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


@Service("backRskValTableImportService")
public class RskValTableImportService {

    @Autowired
    private RskValtblAssetSubMapper rskValtblAssetSubMapper;
    @Autowired
    private RskValtblSumAssetSubMapper rskValtblSumAssetSubMapper;
    @Autowired
    private RskValTableImportMapper rskValTableImportMapper;
    @Autowired
    private RskValRelateImportMapper rskValRelateImportMapper;
    @Autowired
    private RskValStructuresService rskValStructuresService;
    @Autowired
    private RskValAssetCodeService rskValAssetCodeService;
    @Autowired
    private RskValSumname001Service rskValSumname001Service;
    @Autowired
    private RskValtblPrtBotassetService rskValtblPrtBotassetService;



    private static final Logger logger = LoggerFactory.getLogger(RskValTableImportService.class);
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void saveValTbl(RskValtblAssetSubDto dto){
        rskValTableImportMapper.deleteValTable(dto);
        rskValTableImportMapper.deleteSumValTable(dto);
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            for(RskValtblAssetSubDto entity:dto.getSelectedItem()){
                if(StringUtil.equals(entity.getValType().getDesc(), ValType.ASSET.getDesc())){
                    RskValtblAssetSub record = new RskValtblAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblAssetSubMapper.insert(record);
                }else{
                    RskValtblSumAssetSub record = new RskValtblSumAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);

                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblSumAssetSubMapper.insert(record);
                }
            }
        }
    }

    /**
     *  估值表科目数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public int selectCount(Map<String,Object> paramMap){
        return rskValTableImportMapper.selectCount(paramMap);
    }

    /**
     *  查询无需特殊处理的汇总项科目代码
     *
     * @return
     * @see
     * @since 1.0
     */
    public List<String> selectSumSubjectMapping(){
        return rskValRelateImportMapper.selectSumSubjectMapping();
    }

    public List<RskValtblAssetSubDto> viewValTable(RskValTableImportParamDto dto) throws Exception{
        logger.debug("viewValTable dto:{}", JSONArray.toJSONString(dto));
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<RskValtblAssetSubDto> valList = new LinkedList<RskValtblAssetSubDto>();
        List<RskValtblAssetSubDto> valHand_oneList = new LinkedList<RskValtblAssetSubDto>();
        Map<String, Object> warnMap = new HashMap<>();
        dto.setWarnMap(warnMap);
        //指标结构映射
        List<RskValStructureDto> rskValStructureList = rskValStructuresService.selectForList(dto.getStructMapId());
        //资产科目映射
        List<RskValtblAssetSubMapDtlDto> rskValAssetCodeList= rskValAssetCodeService.selectDtlList(dto.getAssetSubMapId());
        //汇总科目映射
        List<RskValSumnameDto> rskValSumnameList =  rskValSumname001Service.selectForList(dto.getSumsubMapId());
        //目标资产科目
        List<RskValtblSubject> rskValtblSubjectList= rskValAssetCodeService.selecctRskValtblSubjectCodeList("01");
        //目标汇总科目
        List<RskValtblSubject> rskValtblSubjectList2= rskValAssetCodeService.selecctRskValtblSubjectCodeList("02");
        //无需特殊处理的汇总科目
        List<String> sumSubjectList = selectSumSubjectMapping();

        Map<String, ColumnMapEntry> vtstructureMap = new HashMap<String, ColumnMapEntry>();
        Map<String, SubCodeEntry> vtaccountMap = new HashMap<String, SubCodeEntry>();
        Map<String,SumNameCodeEntry> vtsumaccountMap = new HashMap<String, SumNameCodeEntry>();
        //构造结构映射map
        for(RskValStructureDto rskValStructureDto:rskValStructureList){
            ColumnMapEntry vtstructEntry = new ColumnMapEntry();
            vtstructEntry.setColIndex(Integer.parseInt(rskValStructureDto.getSrcColIndex()));
            vtstructEntry.setDataType(rskValStructureDto.getSrcColDataType());
            vtstructEntry.setValIndex(rskValStructureDto.getTgtIndex());
            vtstructureMap.put(rskValStructureDto.getSrcColIndex(), vtstructEntry);
        }
        //构造资产科目映射map
        for(RskValtblAssetSubMapDtlDto rskValtblAssetSubMapDtlDto:rskValAssetCodeList){
            SubCodeEntry subentry = new SubCodeEntry();
            subentry.setSrc_code(rskValtblAssetSubMapDtlDto.getSrcCode());
            subentry.setTgt_code(rskValtblAssetSubMapDtlDto.getTgtCode());
            subentry.setTgt_name(rskValtblAssetSubMapDtlDto.getTgtName());
            subentry.setAllowSum(rskValtblAssetSubMapDtlDto.getAllowSum());
            vtaccountMap.put(rskValtblAssetSubMapDtlDto.getSrcCode(), subentry);
        }
        //构造汇总资产科目映射map
        for(RskValSumnameDto vtsumaccountDto:rskValSumnameList){
            SumNameCodeEntry entry = new SumNameCodeEntry();
            entry.setSumName(vtsumaccountDto.getSumName().trim());
            entry.setSumCode(vtsumaccountDto.getSumCode().trim());
            entry.setNextColType(vtsumaccountDto.getNextIndexType());
            for(RskValtblSubject rskValtblSubject:rskValtblSubjectList2){
                if(StringUtil.equals(rskValtblSubject.getSubCode(), vtsumaccountDto.getSumCode())){
                    entry.setTgt_sumName(rskValtblSubject.getSubName());
                }
            }
            vtsumaccountMap.put(vtsumaccountDto.getSumName(), entry);
        }
        paramMap.put("start_row", dto.getStartRow());
        paramMap.put("subjectCodeCol", dto.getSubjectCodeCol());
        paramMap.put("trimChars",dto.getTrimChars());
        paramMap.put("vtstructureMap", vtstructureMap);
        paramMap.put("vtaccountMap", vtaccountMap);
        paramMap.put("vtsumaccountMap", vtsumaccountMap);
        // 六家管理人
        String[] split = dto.getValTableFileName().split("/");
        String fileName = split[split.length-1];
        paramMap.put("fileName", fileName);
        String managerName = StringUtils.EMPTY;
        if (fileName.contains("兴全") || fileName.contains("兴证全球")) {
            managerName = "XINGQUAN";
        } else if (fileName.contains("兴业")) {
            managerName = "XINGYE";
//       } else if (fileName.startsWith("广发")) {
//           managerName = "GUANGFA";
        } else if (fileName.contains("华泰")) {
            managerName = "HUATAI";
        } else if (fileName.contains("汇添富")) {
            managerName = "HUITIANFU";
        }  else if (fileName.contains("交银施罗德") || fileName.contains("交银")) {
            managerName = "JIAOYINSHILUODE";
        }
        paramMap.put("managerName", managerName);
        logger.debug("viewValTable managerName:{}, fileName:{}", managerName, fileName);
        // 处理没有币种列的情况，全部设置为CNY
        boolean isExistCcyCol = vtstructureMap.values().stream().noneMatch((e) -> e.getValIndex().equals(ValIndex.CCY));
        paramMap.put("isExistCcyCol", isExistCcyCol);
        // 处理没有投资目的列六家不配合的情况
        boolean isExistInvestType = vtstructureMap.values().stream().noneMatch((e) -> e.getValIndex().equals(ValIndex.INVEST_TYPE));
        paramMap.put("isExistInvestType", isExistInvestType);

        //处理excel数据
        if (StringUtil.isNotEmpty(dto.getValTableFileName())) {
           /*List<List<String>> dataList = ExcelUtil.build(FileNameUtil.getUploadFilePath(dto.getValTableFileName()),
               null).readToList(0,Integer.parseInt(dto.getStartRow())-1, Integer.parseInt(dto.getStartRow())-2);*/
            //框架遇到空行 不读取  估值表比较特殊 资产与汇总科目中间有空格 需要重写
            List<List<String>> dataList = readExcelUtil(dto);
            if (StringUtil.isEmptyList(dataList)) {
                return null;
            }

            for(List<String> list : dataList){
                //处理excel------估值表
                RskValtblAssetSubDto val = handExcelListForVal(list,paramMap, sumSubjectList);
                if(val != null) {
                    val.setValDate(dto.getValDate());
                    val.setPtlId(dto.getPtlId());
                    valList.add(val);
                }
            }
            List<RskValtblAssetSubDto> valListOrg = new ArrayList<>(valList);
            //处理valList---取出映射表配置的科目代码及底层资产
            List<RskValtblAssetSubDto> handval_List = handValList(valList, vtaccountMap, dto.getAssetName(), dto.getWarnMap());
            //处理handval_List---为null的数值赋0
            dealHandValList(handval_List);
            //底层---一级汇总
            valHand_oneList = sum_handValList(handval_List, vtaccountMap,rskValtblSubjectList);
            logger.debug("valListOrg : {}", JSONArray.toJSONString(valListOrg));
            logger.debug("handval_List : {}", JSONArray.toJSONString(handval_List));
            logger.debug("valHand_oneList : {}", JSONArray.toJSONString(valHand_oneList));
            // 一二三级科目的币种,汇率,投资目的和交易场所设为空
            for (RskValtblAssetSubDto handledDto : valHand_oneList) {
                if (handledDto.getSubCode().length() <= 8) {
                    handledDto.setCcy(null);
                    handledDto.setExchangeRate(null);
                    handledDto.setInvestType(null);
                    handledDto.setTradePlace(null);
                }
            }
           /*//处理币种和汇率以及投资目的
           for (RskValtblAssetSubDto handledDto : valHand_oneList) {
               if (handledDto.getSubCode().length() > 8) {
                   continue;
               }
               List<RskValtblAssetSubMapDtlDto> collect = rskValAssetCodeList.stream().filter(e -> e.getTgtCode().equals(handledDto.getSubCode())).collect(Collectors.toList());
               boolean flag = false;
               for (RskValtblAssetSubDto OrgDto : valListOrg) {
                   for (RskValtblAssetSubMapDtlDto dtlDto : collect) {
                       if (dtlDto.getSrcCode().equals(OrgDto.getSubCode())) {
                           handledDto.setCcy(OrgDto.getCcy());
                           handledDto.setExchangeRate(OrgDto.getExchangeRate());
                           handledDto.setInvestType(OrgDto.getInvestType());
                           flag = true;
                           break;
                       }
                   }
                   if (flag) {
                       break;
                   }
               }
           }*/

            //多条科目需汇总（不同投资目的可生成多条信息）
            Map<String,Integer> indexMap = new HashMap<>();
            int i=0;
            while(i<valHand_oneList.size()) {
                RskValtblAssetSubDto processDto = valHand_oneList.get(i);
                if(indexMap.containsKey(processDto.getSubCode())) {
                    RskValtblAssetSubDto sumDto = valHand_oneList.get(indexMap.get(processDto.getSubCode()));
                    //1.汇总计算
                    summary(sumDto,processDto);
                    //2.移除
                    valHand_oneList.remove(i);
                }
                else {
                    indexMap.put(processDto.getSubCode(), i++);
                }
            }
        }
        return valHand_oneList;
    }

    private boolean isWarn(String msg){
        if (msg.indexOf("估值")>=0 || msg.indexOf("折溢价")>=0 || msg.indexOf("溢折价")>=0 || msg.indexOf("估增")>=0){
            return false;
        }
        return true;
    }

    public void insert(RskValtblAssetSubDto dto) throws ParseException {
        RskValtblPrtBotassetMapDto rskValtblPrtBotassetMapDto = new RskValtblPrtBotassetMapDto();
        InterfPayhsrcsecinfoDto interfPayhsrcsecinfoDto = new InterfPayhsrcsecinfoDto();
        InfChinamutualfunddescriptionDto infChinamutualfunddescriptionDto = new InfChinamutualfunddescriptionDto();
        //是否自动加券
        boolean isAutoCoupon = false;
        List<String> isAutoCouponBondSecidList = new ArrayList<String>();
        List<String> isAutoCouponFundfInfoWindcodeList = new ArrayList<String>();
        //查找底层资产映射表
        List<RskValtblPrtBotassetMapDto> botAssetMapList = rskValtblPrtBotassetService.selectBotAssetMap(dto.getPtlId());
        //查找标的金融产品信息
        List<ChbAssetInfo> chbAssetInfoList = rskValRelateImportMapper.selectAllAsset();
        List<String> subNameList = new ArrayList<String>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        dto.getSelectedItem().forEach(item -> {
            if (StringUtil.isEmpty(item.getSubName())) {
                item.setSubName(StringUtil.EMPTY);
            }
            subNameList.add(item.getSubName());
        });
        paramMap.put("windNameList", subNameList);
        /*//查找wind债券信息
        List<InterfPayhsrcsecinfoDto> interfPayhsrcsecinfoList = commonAutoCouponService.selectWindBondAsset(paramMap);
        //查找wind基金信息
        List<InfChinamutualfunddescriptionDto> infChinamutualfunddescriptionList = commonAutoCouponService.selectWindFundAsset(paramMap);*/
        String actMarketCode = null;
        for (RskValtblAssetSubDto entity : dto.getSelectedItem()) {
            boolean flag = true;
            //新增底层资产
            boolean isAddSubBotMap = true;
            //更新底层资产
            boolean isUpdateSubBotMap = false;
            //第一步 匹配底层资产映射表
            if (null != botAssetMapList && !botAssetMapList.isEmpty()) {
                for (RskValtblPrtBotassetMapDto entityRskValtblPrtBotassetMap : botAssetMapList) {
                    if (StringUtil.equals(entity.getSubCode(), entityRskValtblPrtBotassetMap.getSubCode())) {
                        isAddSubBotMap = false;
                        if (StringUtil.isNotEmpty(entityRskValtblPrtBotassetMap.getFinprodId())) {
                            flag = false;
                            entity.setFinprodId(entityRskValtblPrtBotassetMap.getFinprodId());
                            break;
                        } else {
                            isUpdateSubBotMap = true;
                        }
                    }else {
                        if (entity.getSubCode().length()>8 ) {
                            String assetCode = entityRskValtblPrtBotassetMap.getFinprodId();
                            if (StringUtil.isNotEmpty(assetCode) && assetCode.indexOf(".")>0){
                                assetCode = assetCode.substring(0,assetCode.indexOf("."));
                            }
                            if ((entity.getSubCode().startsWith("120406") && (entity.getSubCode().substring(8).equals(assetCode) || StringUtil.equals(entity.getSubName(),entityRskValtblPrtBotassetMap.getSubName()) )) ||
                                    (entity.getSubCode().startsWith("120302")&& (entity.getSubCode().substring(6).equals(assetCode) || StringUtil.equals(entity.getSubName(),entityRskValtblPrtBotassetMap.getSubName()) )) ||
                                    (entity.getSubCode().startsWith("120301")&& (entity.getSubCode().substring(8).equals(assetCode) || StringUtil.equals(entity.getSubName(),entityRskValtblPrtBotassetMap.getSubName()) ))
                            ) {
                                flag = false;
                                isAddSubBotMap = false;
                                entity.setFinprodId(entityRskValtblPrtBotassetMap.getFinprodId());
                                break;
                            } else {
                                isUpdateSubBotMap = true;
                            }
                        }
                    }
                }
            }
            //底层资产映射没有匹配上  该资产写入映射表
            if (isAddSubBotMap) {
                entity.setWritebotAssetMap(true);
            }
            //底层资产映射匹配上  写入映射表
            if (isUpdateSubBotMap) {
                entity.setUpdatebotAssetMap(true);
            }
            // add by swwan 估值表导入 20210616 start
            String tradePlace = StringUtils.EMPTY;
            if(StringUtil.isNotEmpty(entity.getTradePlace())) {
                tradePlace = getTradePlaceFromStr(entity.getTradePlace());
                entity.setMarketId(tradePlace);
            }
            String issueCode = entity.getIssueCode();
//            if (StringUtil.isNotEmpty(issueCode)) {
//                entity.setMarketId(issueCode);
//            }

            // add by QuJianZhong 区分债券交易场所 20200501 start
            /*if (entity.getSubCode().contains("SH") ||
                    entity.getSubCode().contains("SZ") ||
                    entity.getSubCode().contains("CY")
                    || entity.getSubCode().contains("OF")//场外基金
                    || entity.getSubCode().contains("OTC")//大部分行场外基金后缀都是OTC

            ) {
                if (entity.getSubCode().contains("OTC")) {
                    actMarketCode = entity.getSubCode().substring(entity.getSubCode().length() - 3);
                } else {
                    actMarketCode = entity.getSubCode().substring(entity.getSubCode().length() - 2);
                }
                actMarketCode = actMarketCode.replace("CY", "IB");
                actMarketCode = actMarketCode.replace("OTC", "OF");
            } else {
                if (entity.getSubName().contains("上交所")) {
                    actMarketCode = "SH";
                }
                if (entity.getSubName().contains("深交所")) {
                    actMarketCode = "SZ";
                }
                if (entity.getSubName().contains("银行间")) {
                    actMarketCode = "IB";
                }
            }
            if (StringUtil.isNotEmpty(actMarketCode)) {
                entity.setMarketId(actMarketCode);
            }*/
            // modify by swwan 估值表导入 20210616 end
            // 第二步 匹配中债资产要素信息
            if (flag) {
                for (ChbAssetInfo info : chbAssetInfoList) {
                    String assetCode = info.getAssetCode();
                    if (assetCode.indexOf(".")>0){
                        assetCode = assetCode.substring(0,info.getAssetCode().indexOf("."));
                    }
                    if (assetCode.equals(issueCode) && (StringUtil.isEmpty(tradePlace)
                            || tradePlace.equals(info.getTradeMarket())) && entity.getSubCode().length() > 8 && StringUtil.isNotEmpty(entity.getSecurityQuantity()) ) {
                        flag = false;
                        entity.setAssetSource(DictCodeListExt1.估值资产来源_系统内.getValue());
                        entity.setFinprodId(info.getAssetCode());
                        break;
                    } else {
                        if(StringUtil.isEmpty(tradePlace)) {
                            // add by QuJianZhong 区分债券交易场所 20200501 start
                            if (entity.getSubCode().contains("SH") ||
                                    entity.getSubCode().contains("SZ") ||
                                    entity.getSubCode().contains("CY")
                                    || entity.getSubCode().contains("IB")
                                    || entity.getSubCode().contains("OF")//场外基金
                                    || entity.getSubCode().contains("OTC")//大部分行场外基金后缀都是OTC

                            ) {
                                if(entity.getSubCode().contains("OTC")){
                                    actMarketCode = entity.getSubCode().substring(entity.getSubCode().length() - 3);
                                }else{
                                    actMarketCode = entity.getSubCode().substring(entity.getSubCode().length() - 2);
                                }
                                actMarketCode = actMarketCode.replace("SH", DictCodeListExt2.交易流通场所_中债_上海证券交易所.getValue());
                                actMarketCode = actMarketCode.replace("SZ", DictCodeListExt2.交易流通场所_中债_深圳证券交易所.getValue());
                                actMarketCode = actMarketCode.replace("IB", DictCodeListExt2.交易流通场所_中债_银行间债券市场.getValue());
                                actMarketCode = actMarketCode.replace("CY", DictCodeListExt2.交易流通场所_中债_银行间债券市场.getValue());
                                actMarketCode = actMarketCode.replace("OTC", DictCodeListExt2.交易流通场所_中债_基金市场.getValue());
                                actMarketCode = actMarketCode.replace("OF", DictCodeListExt2.交易流通场所_中债_基金市场.getValue());
                            } else {
                                if (entity.getSubName().contains("上交所")) {
                                    actMarketCode = DictCodeListExt2.交易流通场所_中债_上海证券交易所.getValue();
                                } else if (entity.getSubName().contains("深交所")) {
                                    actMarketCode = DictCodeListExt2.交易流通场所_中债_深圳证券交易所.getValue();
                                } else if (entity.getSubName().contains("银行间")) {
                                    actMarketCode = DictCodeListExt2.交易流通场所_中债_银行间债券市场.getValue();
                                } else if (entity.getSubName().contains("沪港通") || entity.getSubName().contains("港股通")
                                        || entity.getSubName().contains("深港通") || entity.getSubName().contains("港交所")) {
                                    actMarketCode = DictCodeListExt2.交易流通场所_中债_其他交易流通场所.getValue();
                                }
                            }
                            if (StringUtil.isNotEmpty(actMarketCode)) {
                                entity.setMarketId(actMarketCode);
                            } else {
                                entity.setMarketId(DictCodeListExt2.交易流通场所_中债_其他交易流通场所.getValue());
                            }
                        }
                        /*非四级科目对应行，都不能有资产代码*/
                        if (entity.getSubCode().length() > 8 ) {
                            if (( StringUtil.isNotEmpty(entity.getSecurityQuantity()) &&  !entity.getSubCode().startsWith("22") &&  StringUtil.equals(entity.getSubName(), info.getAssetName()) && StringUtil.equals(entity.getMarketId(), info.getTradeMarket()))
                                    || (entity.getSubCode().startsWith("1003") && StringUtil.equals(entity.getSubCode().substring(6), info.getAssetCode()))
                                    || (entity.getSubCode().startsWith("120404") && StringUtil.equals(entity.getSubCode().substring(6), info.getAssetCode()))
                                    || StringUtil.equals(entity.getSubCode().substring(8), info.getAssetCode())) {
                                flag = false;
                                entity.setAssetSource(DictCodeListExt1.估值资产来源_系统内.getValue());
                                entity.setFinprodId(info.getAssetCode());
                                break;
                            }

                            if (entity.getSubCode().startsWith("1105") && info.getAssetCode().startsWith(entity.getSubCode().substring(8,14))  && StringUtil.isNotEmpty(entity.getSecurityQuantity())){
                                flag = false;
                                entity.setAssetSource(DictCodeListExt1.估值资产来源_系统内.getValue());
                                entity.setFinprodId(info.getAssetCode());
                                break;
                            }
                        }
                    }
                }
            }

            /*// add by QuJianZhong 区分债券交易场所 20200501 end
            //第二部  匹配标的金融产品信息
            if(flag&&null!=finProductList&&!finProductList.isEmpty()) {
                for (FinProduct entityFinProduct : finProductList) {
                    //匹配标的金融产品信息的交易场所add by lilan  20200528
                    String tradeMarket = entityFinProduct.getFinprodId().substring(entityFinProduct.getFinprodId().length() - 2);

                    if ((StringUtil.equals(entity.getSubName(), entityFinProduct.getFinprodName())
                            || StringUtil.equals(entity.getSubName(), entityFinProduct.getFinprodAbbr()))
                            && (StringUtil.equals(actMarketCode, tradeMarket) ||
                            // add by jiangpeng on 20201221 非债券资产
                            (StringUtil.isEmpty(actMarketCode)
                                    && !DictCodeListExt1.金融产品类型_债券.getValue().equals(entityFinProduct.getFinprodType2())))
                            && entity.getSubCode().length() > 8) {*//*非四级科目对应行，都不能有资产代码*//*
                        flag = false;
                        entity.setAssetSource(DictCodeListExt1.估值资产来源_系统内.getValue());
                        entity.setFinprodId(entityFinProduct.getFinprodId());
                        break;
                    }
                }
            }
            //第三步  匹配wind资产信息
            if (flag) {
                if (StringUtil.equals(entity.getValType().getDesc(), ValType.ASSET.getDesc())) {
                    //添加利息匹配1204
                    if (StringUtil.equals(entity.getSubCode().substring(0, 4), "1103")
                            || StringUtil.equals(entity.getSubCode().substring(0, 4), "1204")) {
                        if (null != interfPayhsrcsecinfoList && !interfPayhsrcsecinfoList.isEmpty()) {
                            for (InterfPayhsrcsecinfoDto entityInterfPayhsrcsecinfo : interfPayhsrcsecinfoList) {
                                if (StringUtil.equals(entity.getSubName(), entityInterfPayhsrcsecinfo.getSecname())
                                        || StringUtil.equals(entity.getSubName(), entityInterfPayhsrcsecinfo.getSecfullname())) {

                                    // add by QuJianZhong 区分债券交易场所 20200501 start
                                    String currentMarketCode = entityInterfPayhsrcsecinfo.getSecid().substring(entityInterfPayhsrcsecinfo.getSecid().indexOf(".") + 1);
                                    if (!StringUtil.equals(actMarketCode, currentMarketCode)) {
                                        // 当前接口表债券市场代码与估值表不同，则继续寻找
                                        continue;
                                    }
                                    // add by QuJianZhong 区分债券交易场所 20200501 end

                                    flag = false;
                                    //避免重复加券
                                    if (StringUtil.equals(entity.getSubCode().substring(0, 4), "1103")) {
                                        isAutoCoupon = true;
                                        isAutoCouponBondSecidList.add(entityInterfPayhsrcsecinfo.getSecid());
                                    }
                                    entity.setAssetSource(DictCodeListExt1.估值资产来源_WIND债券.getValue());
                                    entity.setFinprodId(entityInterfPayhsrcsecinfo.getFinprodId());
                                    entity.setFinprodMarketId(entityInterfPayhsrcsecinfo.getSecid());
                                    break;
                                }
                            }
                        }
                    } else if (StringUtil.equals(entity.getSubCode().substring(0, 4), "1105")) {
                        if (null != infChinamutualfunddescriptionList && !infChinamutualfunddescriptionList.isEmpty()) {
                            for (InfChinamutualfunddescriptionDto entityinfChinamutualfunddescription : infChinamutualfunddescriptionList) {
                                if (StringUtil.equals(entity.getSubName(), entityinfChinamutualfunddescription.getfInfoName())
                                        || StringUtil.equals(entity.getSubName(), entityinfChinamutualfunddescription.getfInfoFullname())) {
                                    flag = false;
                                    isAutoCoupon = true;
                                    isAutoCouponFundfInfoWindcodeList.add(entityinfChinamutualfunddescription.getfInfoWindcode());
                                    entity.setAssetSource(DictCodeListExt1.估值资产来源_WIND基金.getValue());
                                    entity.setFinprodId(entityinfChinamutualfunddescription.getObjectId());
                                    entity.setFinprodMarketId(entityinfChinamutualfunddescription.getfInfoWindcode());
                                    break;
                                }
                            }
                        }
                    }
                }
            }*/
        }
        botAssetMapList.clear();

        isAutoCouponBondSecidList = isAutoCouponBondSecidList.stream().distinct().collect(Collectors.toList());
        isAutoCouponFundfInfoWindcodeList = isAutoCouponFundfInfoWindcodeList.stream().distinct().collect(Collectors.toList());

        interfPayhsrcsecinfoDto.setSecidList(isAutoCouponBondSecidList);
        infChinamutualfunddescriptionDto.setfInfoWindcodeList(isAutoCouponFundfInfoWindcodeList);

        // modify by swwan 估值表导入 20210616 start
        //自动加券
        /*if (isAutoCoupon) {
            interfPayhsrcsecinfoDto = commonAutoCouponService.autoBondCoupon(interfPayhsrcsecinfoDto);
            infChinamutualfunddescriptionDto = commonAutoCouponService.autoFundCoupon(infChinamutualfunddescriptionDto);
        }*/
        // modify by swwan 估值表导入 20210616 end
        for (RskValtblAssetSubDto entity2 : dto.getSelectedItem()) {
            //导入方式
            entity2.setImpMode(dto.getImpMode());
            if (entity2.isWritebotAssetMap() || entity2.isUpdatebotAssetMap()) {
                if (StringUtil.equals(entity2.getValType().getDesc(), ValType.ASSET.getDesc())) {
                    RskValtblPrtBotassetMapDto rskValtblPrtBotassetMapDto2 = new RskValtblPrtBotassetMapDto();
                    if (StringUtil.isNotEmpty(entity2.getSubCode()) && entity2.getSubCode().length() > 8) {
                        if (StringUtil.equals(entity2.getSubCode().substring(0, 4), "1103")) {
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_债券.getValue());
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 4), "1105")) {
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_基金.getValue());
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 4), "1102")) {
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_股票.getValue());
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 4), "1701")) {
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_非标.getValue());
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 4), "1901")) {
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_项目类.getValue());
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 4), "1003")) {
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_存款类.getValue());
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 4), "1202")) {
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_逆回购.getValue());
                            String termType = getTermTypeFromStr(entity2.getSubCode(), entity2.getSubName());
                            if(termType != null) {
//                                entity2.setFinprodId(termType);
                            } else {
//                                entity2.setFinprodId(entity2.getSubCode().substring(8, entity2.getSubCode().length()));
                            }
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 4),"2202")){
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_正回购.getValue());
                            String termType = getTermTypeFromStr(entity2.getSubCode(), entity2.getSubName());
                            if(termType != null) {
//                                entity2.setFinprodId(termType);
                            } else {
//                                entity2.setFinprodId(entity2.getSubCode().substring(8, entity2.getSubCode().length()));
                            }
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 4),"2231")){
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_正回购.getValue());
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 6),"120410")){
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_逆回购.getValue());
                        } else if (StringUtil.equals(entity2.getSubCode().substring(0, 4),"3103")){
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_衍生品.getValue());
                        } else {
                            rskValtblPrtBotassetMapDto2.setSubClass(DictCodeListExt1.资产科目类型_其他.getValue());
                        }
                        entity2.setAssetSource(DictCodeListExt1.估值资产来源_系统内.getValue());

                        /*if (StringUtil.equals(entity2.getAssetSource(), DictCodeListExt1.估值资产来源_WIND债券.getValue())) {
                            if (null != interfPayhsrcsecinfoDto.getBaseFinProductBaseInfoList()
                                    && !interfPayhsrcsecinfoDto.getBaseFinProductBaseInfoList().isEmpty()) {
                                for (BaseFinProductBaseInfoDto baseFinProductBaseInfoDto : interfPayhsrcsecinfoDto.getBaseFinProductBaseInfoList()) {
                                    if (StringUtil.equals(entity2.getFinprodMarketId(), baseFinProductBaseInfoDto.getFinProduct().getFinprodMarketId())) {
                                        rskValtblPrtBotassetMapDto2.setFinprodId(baseFinProductBaseInfoDto.getFinProduct().getFinprodId());
                                        entity2.setFinprodId(baseFinProductBaseInfoDto.getFinProduct().getFinprodId());
                                        entity2.setAssetSource(DictCodeListExt1.估值资产来源_系统内.getValue());
                                    }
                                }
                            }
                        }
                        if (StringUtil.equals(entity2.getAssetSource(), DictCodeListExt1.估值资产来源_WIND基金.getValue())) {
                            if (null != infChinamutualfunddescriptionDto.getBaseFinProductBaseInfoList()
                                    && !infChinamutualfunddescriptionDto.getBaseFinProductBaseInfoList().isEmpty()) {
                                for (BaseFinProductBaseInfoDto baseFinProductBaseInfoDto : infChinamutualfunddescriptionDto.getBaseFinProductBaseInfoList()) {
                                    if (StringUtil.equals(entity2.getFinprodMarketId(), baseFinProductBaseInfoDto.getFinProduct().getFinprodMarketId())) {
                                        rskValtblPrtBotassetMapDto2.setFinprodId(baseFinProductBaseInfoDto.getFinProduct().getFinprodId());
                                        entity2.setFinprodId(baseFinProductBaseInfoDto.getFinProduct().getFinprodId());
                                        entity2.setAssetSource(DictCodeListExt1.估值资产来源_系统内.getValue());
                                    }
                                }
                            }
                        }*/
                        rskValtblPrtBotassetMapDto2.setSubCode(entity2.getSubCode());
                        rskValtblPrtBotassetMapDto2.setSubName(entity2.getSubName());
                        rskValtblPrtBotassetMapDto2.setPtlId(dto.getPtlId());
                        rskValtblPrtBotassetMapDto2.setFinprodId(entity2.getFinprodId());
                        rskValtblPrtBotassetMapDto2.setAssetSource(entity2.getAssetSource());
                        rskValtblPrtBotassetMapDto2.setTblMarketId(entity2.getMarketId());
                        if (entity2.isWritebotAssetMap()) {
                            rskValtblPrtBotassetMapDto2.setIsAdd(DictCodeList.是否_是.getValue());
                        }
                        botAssetMapList.add(rskValtblPrtBotassetMapDto2);
                    }
                }
            }
        }
        rskValtblPrtBotassetMapDto.setSelectedItem(botAssetMapList);
        //write映射表
        rskValtblPrtBotassetService.writebotAssetMap(rskValtblPrtBotassetMapDto);
        //保存估值表
        saveValTbl(dto);
    }

    /**
     * 获取期限品种
     *
     * @param subCodeStr
     * @param subNameStr
     * @return String 期限品种
     */
    public String getTermTypeFromStr(String subCodeStr, String subNameStr) {
        Map<String, DataDict> dataDictMap = CacheUtil.getAllDictCode();
        List<DataDictItem> dictItemList = new ArrayList<>();

        if (null != dataDictMap) {
            DataDict dto = dataDictMap.get(DictCode.回购期限品种.getValue());
            if (null != dto) {
                dictItemList = dto.getItems().stream().filter(item ->
                        StringUtil.equals(DictCode.回购期限品种.getValue(), item.getDictCode())).collect(Collectors.toList());
            }
        }

        if (StringUtil.isNotEmpty(subCodeStr)) {
            for (DataDictItem item : dictItemList) {
                if (subCodeStr.contains(item.getItemCode()) || subCodeStr.contains(item.getItemValue())) {
                    return item.getItemCode();
                }
            }
        }

        if (StringUtil.isNotEmpty(subNameStr)) {
            for (DataDictItem item : dictItemList) {
                if (subNameStr.contains(item.getItemCode()) || subNameStr.contains(item.getItemValue())) {
                    return item.getItemCode();
                }
            }
        }

        return null;
    }


    public String getTradePlaceFromStr(String tradePlaceStr) {
        if ("1".equals(tradePlaceStr) || tradePlaceStr.contains("SH") || tradePlaceStr.contains("上交所")) {
            return DictCodeListExt2.交易流通场所_中债_上海证券交易所.getValue();
        } else if ("2".equals(tradePlaceStr) || tradePlaceStr.contains("SZ") || tradePlaceStr.contains("深交所")) {
            return DictCodeListExt2.交易流通场所_中债_深圳证券交易所.getValue();
        } else if ("3".equals(tradePlaceStr) || tradePlaceStr.contains("IB") || tradePlaceStr.contains("CY") || tradePlaceStr.contains("银行间")) {
            return DictCodeListExt2.交易流通场所_中债_银行间债券市场.getValue();
        } else if ("4".equals(tradePlaceStr) || tradePlaceStr.contains("OTC") || tradePlaceStr.contains("OF") || tradePlaceStr.contains("场外")) {
            return DictCodeListExt2.交易流通场所_中债_基金市场.getValue();
        } else if ("5".equals(tradePlaceStr) || tradePlaceStr.contains("其他")) {
            return DictCodeListExt2.交易流通场所_中债_其他交易流通场所.getValue();
        }
        return org.apache.commons.lang.StringUtils.EMPTY;
    }

    private void summary(RskValtblAssetSubDto sumDto,RskValtblAssetSubDto processDto) {
        //证券数量 securityQuantity
        sumDto.setSecurityQuantity(processAdd(sumDto.getSecurityQuantity(), processDto.getSecurityQuantity()));
        //单位成本 unitCost 汇总处理待确认
        //证券成本 securityCost
        sumDto.setSecurityCost(processAdd(sumDto.getSecurityCost(), processDto.getSecurityCost()));
        //成本占净值比 ctnwr
        sumDto.setCtnwr(processAdd(sumDto.getCtnwr(), processDto.getCtnwr()));
        //行情收市价 mcp 汇总处理待确认
        //证券市值  smv
        sumDto.setSmv(processAdd(sumDto.getSmv(), processDto.getSmv()));
        //市值占净值比 mcr
        sumDto.setMcr(processAdd(sumDto.getMcr(), processDto.getMcr()));
        //估值增值 valueAdded
        sumDto.setValueAdded(processAdd(sumDto.getValueAdded(), processDto.getValueAdded()));
        //停牌信息 equityInfo
        //权益信息 valType
        //成本（原币）
        sumDto.setSecurityCostOri(processAdd(sumDto.getSecurityCostOri(), processDto.getSecurityCostOri()));
        //市值（原币）
        sumDto.setSmvOri(processAdd(sumDto.getSmvOri(), processDto.getSmvOri()));
        //估值增值（原币）
        sumDto.setValueAddedOri(processAdd(sumDto.getValueAddedOri(), processDto.getValueAddedOri()));
        //汇率

    }

    private BigDecimal processAdd(BigDecimal num1,BigDecimal num2) {
        if(num1==null&&num2==null) {
            return null;
        }
        return ArithmeticUtil.doAdd(num1,num2);
    }


    /**
     * 底层---一级汇总
     * <功能详细描述>
     * @param valList
     * @param vtaccountMap
     * @param vtcountSubList
     * @return
     * @see
     * @since 1.0
     */
    private List<RskValtblAssetSubDto> sum_handValList(List<RskValtblAssetSubDto> valList,Map<String,SubCodeEntry> vtaccountMap,List<RskValtblSubject>  vtcountSubList){
        try{
            List<RskValtblAssetSubDto> valHand_threeList = commonHandleSum(valList,vtcountSubList,"03");
           /*for (RskValtblAssetSubDto rskValtblSubject : valHand_threeList) {
               if(rskValtblSubject.getSubCode().startsWith("120404")){
                   System.out.println(rskValtblSubject.getSubCode());
               }
           }*/
            List<RskValtblAssetSubDto> valHand_twoList = commonHandleSum(valHand_threeList,vtcountSubList,"02");
            List<RskValtblAssetSubDto> valHand_oneList = commonHandleSum(valHand_twoList,vtcountSubList,"01");
            Collections.sort(valHand_oneList);
            return valHand_oneList;
            //底层---三级汇总
           /*Map<String,String> tempmap = new HashMap<String,String>();
           Map<String,String> tempmaptwo = new HashMap<String,String>();
           Map<String,String> tempmapthree = new HashMap<String,String>();
           for(RskValtblAssetSubDto por_three:valList){
               RskValtblAssetSubDto tempporDto = new RskValtblAssetSubDto();
                   BigDecimal security_cost=BigDecimal.ZERO;
                   BigDecimal ctnwr=BigDecimal.ZERO;
                   BigDecimal smv=BigDecimal.ZERO;
                   BigDecimal mcr=BigDecimal.ZERO;
                   boolean flag=false;
                   if(por_three.getSubCode().length()>=8){
                       String sub_subjcode=por_three.getSubCode().substring(0, 8);
                       if(tempmap.containsKey(sub_subjcode)){
                           continue;
                       }
                       tempmap.put(sub_subjcode, sub_subjcode);
                       for(RskValtblAssetSubDto por_all:valList){
                           if(por_all.getAllowSum()==null){
                               por_all.setAllowSum("Y");
                           }
                           if(por_all.getAllowSum().equals("N")){
                               continue;
                           }else{
                               if(flag==false){
                                   for(RskValtblSubject dto:vtcountSubList){
                                       if(dto.getSubCode().equals(sub_subjcode)){
                                           flag=true;
                                           tempporDto.setSubName(dto.getSubName());
                                       }
                                   }
                               }
                               if(por_all.getSubCode().startsWith(sub_subjcode)&&
                                       por_all.getSubCode().length()>=8){
                                   if(por_all.getCost()!=null){
                                       security_cost = ArithmeticUtil.doAdd(security_cost,por_all.getCost());
                                   }
                                   if(por_all.getCtnwr()!=null){
                                       ctnwr = ArithmeticUtil.doAdd(ctnwr,por_all.getCtnwr());
                                   }
                                   if(por_all.getSmv()!=null){
                                       smv = ArithmeticUtil.doAdd(smv,por_all.getSmv());
                                   }
                                   if(por_all.getMcr()!=null){
                                       mcr = ArithmeticUtil.doAdd(mcr,por_all.getMcr());
                                   }
                               }
                           }
                       }
                       if(flag==false){
                           tempporDto.setSubName("");
                       }
                       tempporDto.setValType(ValType.ASSET);
                       tempporDto.setSubCode(sub_subjcode);
                       tempporDto.setCost(security_cost);
                       tempporDto.setCtnwr(ctnwr);
                       tempporDto.setSmv(smv);
                       tempporDto.setMcr(mcr);
                       valHand_threeList.add(tempporDto);
                   }
           }
           //移除重复的科目代码数据
           for(RskValtblAssetSubDto por_three:valList){
               if(!tempmap.containsKey(por_three.getSubCode())){
                   valHand_threeList.add(por_three);
               }
           }
           //二级--三级汇总
           for(RskValtblAssetSubDto por_two:valHand_threeList){
               RskValtblAssetSubDto tempporDto = new RskValtblAssetSubDto();
               BigDecimal security_cost=BigDecimal.ZERO;
               BigDecimal ctnwr=BigDecimal.ZERO;
               BigDecimal smv=BigDecimal.ZERO;
               BigDecimal mcr=BigDecimal.ZERO;
               boolean flag=false;
               if(por_two.getSubCode().length()==8||por_two.getSubCode().length()==6){
                   String sub_subjcode=por_two.getSubCode().substring(0, 6);
                   if(tempmaptwo.containsKey(sub_subjcode)){
                       continue;
                   }
                   tempmaptwo.put(sub_subjcode, sub_subjcode);
                   for(RskValtblAssetSubDto por_all:valHand_threeList){
                       if(por_all.getAllowSum()==null){
                           por_all.setAllowSum("Y");
                       }
                       if(por_all.getAllowSum().equals("N")){
                           continue;
                       }else{
                           if(flag==false){
                               for(RskValtblSubject dto:vtcountSubList){
                                   if(dto.getSubCode().equals(sub_subjcode)){
                                       flag=true;
                                       tempporDto.setSubName(dto.getSubName());
                                   }
                               }
                           }
                           if(por_all.getSubCode().startsWith(sub_subjcode)){
                                   if(por_all.getSubCode().length()==8||por_all.getSubCode().length()==6){
                                       if(por_all.getCost()!=null){
                                           security_cost = ArithmeticUtil.doAdd(security_cost,por_all.getCost());
                                       }
                                       if(por_all.getCtnwr()!=null){
                                           ctnwr = ArithmeticUtil.doAdd(ctnwr,por_all.getCtnwr());
                                       }
                                       if(por_all.getSmv()!=null){
                                           smv = ArithmeticUtil.doAdd(smv,por_all.getSmv());
                                       }
                                       if(por_all.getMcr()!=null){
                                           mcr = ArithmeticUtil.doAdd(mcr,por_all.getMcr());
                                       }
                           }
                           }
                           }
                   }
                   if(flag==false){
                       tempporDto.setSubName("");
                   }
                   tempporDto.setValType(ValType.ASSET);
                   tempporDto.setSubCode(sub_subjcode);
                   tempporDto.setCost(security_cost);
                   tempporDto.setCtnwr(ctnwr);
                   tempporDto.setSmv(smv);
                   tempporDto.setMcr(mcr);
                   valHand_twoList.add(tempporDto);
               }
           }
           //移除重复的科目代码数据
           for(RskValtblAssetSubDto por_three:valHand_threeList){
               if(!tempmaptwo.containsKey(por_three.getSubCode())){
                   valHand_twoList.add(por_three);
               }
           }
           //一级汇总
           for(RskValtblAssetSubDto por_two:valHand_twoList){
               RskValtblAssetSubDto tempporDto = new RskValtblAssetSubDto();
               BigDecimal security_cost=BigDecimal.ZERO;
               BigDecimal ctnwr=BigDecimal.ZERO;
               BigDecimal smv=BigDecimal.ZERO;
               BigDecimal mcr=BigDecimal.ZERO;
               boolean flag=false;
               if(por_two.getSubCode().length()==6||por_two.getSubCode().length()==4){
                   String sub_subjcode=por_two.getSubCode().substring(0, 4);
                   if(tempmapthree.containsKey(sub_subjcode)){
                       continue;
                   }
                   tempmapthree.put(sub_subjcode, sub_subjcode);
                   for(RskValtblAssetSubDto por_all:valHand_twoList){
                       if(por_all.getAllowSum()==null){
                           por_all.setAllowSum("Y");
                       }
                       if(por_all.getAllowSum().equals("N")){
                           continue;
                       }else{
                           if(flag==false){
                               for(RskValtblSubject dto:vtcountSubList){
                                   if(dto.getSubCode().equals(sub_subjcode)){
                                       flag=true;
                                       tempporDto.setSubName(dto.getSubName());
                                   }
                               }
                           }
                           if(por_all.getSubCode().startsWith(sub_subjcode)){
                               if(por_all.getSubCode().length()==6||por_all.getSubCode().length()==4){
                                   if(por_all.getCost()!=null){
                                       security_cost = ArithmeticUtil.doAdd(security_cost,por_all.getCost());
                                   }
                                   if(por_all.getCtnwr()!=null){
                                       ctnwr = ArithmeticUtil.doAdd(ctnwr,por_all.getCtnwr());
                                   }
                                   if(por_all.getSmv()!=null){
                                       smv = ArithmeticUtil.doAdd(smv,por_all.getSmv());
                                   }
                                   if(por_all.getMcr()!=null){
                                       mcr = ArithmeticUtil.doAdd(mcr,por_all.getMcr());
                                   }
                           }
                           }
                       }
                   }
                   if(flag==false){
                       tempporDto.setSubName("");
                   }
                   tempporDto.setValType(ValType.ASSET);
                   tempporDto.setSubCode(sub_subjcode);
                   tempporDto.setCost(security_cost);
                   tempporDto.setCtnwr(ctnwr);
                   tempporDto.setSmv(smv);
                   tempporDto.setMcr(mcr);
                   valHand_oneList.add(tempporDto);
               }
           }
           //移除重复的科目代码数据
           for(RskValtblAssetSubDto por_three:valHand_twoList){
               if(!tempmapthree.containsKey(por_three.getSubCode())){
                   valHand_oneList.add(por_three);
               }
           }
           //排序
           Collections.sort(valHand_oneList);
           return valHand_oneList;*/
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * 底层向上汇总--共通
     * <功能详细描述>
     * @param valList
     * @param vtcountSubList
     * @param level 03(三级) 02(二级) 01(一级)
     * @return
     * @see
     * @since 1.0
     */
    private List<RskValtblAssetSubDto> commonHandleSum(List<RskValtblAssetSubDto> valList, List<RskValtblSubject> vtcountSubList, String level) {
        List<RskValtblAssetSubDto> resultList = new LinkedList<RskValtblAssetSubDto>();
        Map<String, String> tempmap = new HashMap<String, String>();
        for (RskValtblAssetSubDto por : valList) {
//           if (por.getSubCode().startsWith("120404")) {
//               System.out.println(por.getSubCode());
//           }
            String subSubjcode = StringUtil.EMPTY;
            String ccy = por.getCcy();
            BigDecimal exchangeRate = por.getExchangeRate();
            RskValtblAssetSubDto tempporDto = new RskValtblAssetSubDto();
            BigDecimal security_cost = BigDecimal.ZERO;
            BigDecimal ctnwr = BigDecimal.ZERO;
            BigDecimal smv = BigDecimal.ZERO;
            BigDecimal mcr = BigDecimal.ZERO;
            BigDecimal securityCostOri = BigDecimal.ZERO;
            BigDecimal smvOri = BigDecimal.ZERO;
            BigDecimal valueAddedOri = BigDecimal.ZERO;
            BigDecimal valueAdded = BigDecimal.ZERO;
            boolean flag = false;
            if (StringUtil.equals("03", level)) {
                if (por.getSubCode().length() >= 8) {
                    subSubjcode = por.getSubCode().substring(0, 8);
                }
            } else if (StringUtil.equals("02", level)) {
                if (por.getSubCode().length() <= 8 && por.getSubCode().length() >= 6) {
                    subSubjcode = por.getSubCode().substring(0, 6);
                }
            } else if (StringUtil.equals("01", level)) {
                if (por.getSubCode().length() <= 6 && por.getSubCode().length() >= 4) {
                    subSubjcode = por.getSubCode().substring(0, 4);

                }
            }
            if (tempmap.containsKey(subSubjcode)) {
                continue;
            }
            if (StringUtil.isNotEmpty(subSubjcode)) {
                tempmap.put(subSubjcode, subSubjcode);
                for (RskValtblAssetSubDto por_all : valList) {
                    if (StringUtil.isEmpty(por_all.getAllowSum())) {
                        por_all.setAllowSum("Y");
                    }
                    if (StringUtil.equals(por_all.getAllowSum(), "N")) {
                        continue;
                    } else {
                        if (flag == false) {
                            for (RskValtblSubject dto : vtcountSubList) {
                               /*if (dto.getSubCode().startsWith("120404")) {
                                   System.out.println(por.getSubCode());
                               }*/
                                if (dto.getSubCode().equals(subSubjcode)) {
                                    flag = true;
                                    tempporDto.setSubName(dto.getSubName());
                                }
                            }
                        }
                        if (por_all.getSubCode().startsWith(subSubjcode)) {
                            if ((StringUtil.equals("03", level) && (por_all.getSubCode().length() >= 8))
                                    || (StringUtil.equals("02", level) && (por_all.getSubCode().length() == 8 || por_all.getSubCode().length() == 6))
                                    || (StringUtil.equals("01", level) && ((por_all.getSubCode().length() == 6 || por_all.getSubCode().length() == 4)))) {

                                if (por_all.getSecurityCostOri() != null) {
                                    securityCostOri = ArithmeticUtil.doAdd(securityCostOri, por_all.getSecurityCostOri());
                                }
                                if (por_all.getSmvOri() != null) {
                                    smvOri = ArithmeticUtil.doAdd(smvOri, por_all.getSmvOri());
                                }
                                if (por_all.getValueAddedOri() != null) {
                                    valueAddedOri = ArithmeticUtil.doAdd(valueAddedOri, por_all.getValueAddedOri());
                                }
                                if (por_all.getValueAdded() != null) {
                                    valueAdded = ArithmeticUtil.doAdd(valueAdded, por_all.getValueAdded());
                                }
                               /*if (por_all.getExchangeRate() != null) {
                                   exchangeRate = ArithmeticUtil.doAdd(exchangeRate, por_all.getExchangeRate());
                               }*/
                                if (por_all.getSecurityCost() != null) {
                                    security_cost = ArithmeticUtil.doAdd(security_cost, por_all.getSecurityCost());
                                }
                                if (por_all.getCtnwr() != null) {
                                    ctnwr = ArithmeticUtil.doAdd(ctnwr, por_all.getCtnwr());
                                }
                                if (por_all.getSmv() != null) {
                                    smv = ArithmeticUtil.doAdd(smv, por_all.getSmv());
                                }
                                if (por_all.getMcr() != null) {
                                    mcr = ArithmeticUtil.doAdd(mcr, por_all.getMcr());
                                }
                            }
                        }
                    }
                }
                if (flag == false) {
                    tempporDto.setSubName(por.getSubName());
                }
                tempporDto.setValType(ValType.ASSET);
                tempporDto.setSubCode(subSubjcode);
                tempporDto.setSecurityCost(security_cost.setScale(8, BigDecimal.ROUND_HALF_UP));
                tempporDto.setCtnwr(ctnwr.setScale(8, BigDecimal.ROUND_HALF_UP));
                tempporDto.setSmv(smv.setScale(8, BigDecimal.ROUND_HALF_UP));
                tempporDto.setMcr(mcr.setScale(8, BigDecimal.ROUND_HALF_UP));
                tempporDto.setSecurityCostOri(securityCostOri.setScale(8, BigDecimal.ROUND_HALF_UP));
                tempporDto.setSmvOri(smvOri.setScale(8, BigDecimal.ROUND_HALF_UP));
                tempporDto.setValueAddedOri(valueAddedOri.setScale(8, BigDecimal.ROUND_HALF_UP));
                tempporDto.setValueAdded(valueAdded.setScale(8, BigDecimal.ROUND_HALF_UP));
                tempporDto.setExchangeRate(exchangeRate);
                tempporDto.setCcy(ccy);

                resultList.add(tempporDto);
            }
        }
        //移除重复的科目代码数据
        for (RskValtblAssetSubDto por : valList) {
            if (!tempmap.containsKey(por.getSubCode())) {
                resultList.add(por);
            }
        }
        return resultList;
    }

    /**
     * 为数值型字段赋初始值
     *
     * @param handval_list
     * @return
     */
    private void dealHandValList(List<RskValtblAssetSubDto> handval_list) {
        BigDecimal security_cost = BigDecimal.ZERO;
        BigDecimal ctnwr = BigDecimal.ZERO;
        BigDecimal smv = BigDecimal.ZERO;
        BigDecimal mcr = BigDecimal.ZERO;
        BigDecimal securityCostOri = BigDecimal.ZERO;
        BigDecimal smvOri = BigDecimal.ZERO;
        BigDecimal valueAddedOri = BigDecimal.ZERO;
        BigDecimal valueAdded = BigDecimal.ZERO;
        for (RskValtblAssetSubDto dto : handval_list) {
            if (ValType.SUM.equals(dto.getValType())) {
                continue;
            }
            if (Objects.isNull(dto.getSecurityCost())) {
                dto.setSecurityCost(BigDecimal.ZERO);
            }
            if (Objects.isNull(dto.getCtnwr())) {
                dto.setCtnwr(BigDecimal.ZERO);
            }
            if (Objects.isNull(dto.getSmv())) {
                dto.setSmv(BigDecimal.ZERO);
            }
            if (Objects.isNull(dto.getMcr())) {
                dto.setMcr(BigDecimal.ZERO);
            }
            if (Objects.isNull(dto.getSecurityCostOri())) {
                dto.setSecurityCostOri(BigDecimal.ZERO);
            }
            if (Objects.isNull(dto.getSmvOri())) {
                dto.setSmvOri(BigDecimal.ZERO);
            }
            if (Objects.isNull(dto.getValueAdded())) {
                dto.setValueAdded(BigDecimal.ZERO);
            }
            if (Objects.isNull(dto.getValueAddedOri())) {
                dto.setValueAddedOri(BigDecimal.ZERO);
            }
        }
    }

    /**
     * 处理资产科目
     * <功能详细描述>
     * @param valList
     * @param vtaccountMap
     * @return
     * @see
     * @since 1.0
     */
    private List<RskValtblAssetSubDto> handValList(List<RskValtblAssetSubDto> valList,Map<String,SubCodeEntry> vtaccountMap, String assetName, Map<String, Object> warnMap){
        //处理valList---取出映射表配置的科目代码及底层资产
        List<RskValtblAssetSubDto> one_handval_List = new LinkedList<RskValtblAssetSubDto>();
        List<RskValtblAssetSubDto> two_handval_List = new LinkedList<RskValtblAssetSubDto>();
        List<RskValtblAssetSubDto> sum_val_List = new LinkedList<RskValtblAssetSubDto>();
        String warmAssetName = "";
        for(RskValtblAssetSubDto handval:valList){
            if(handval.getValType().equals(ValType.SUM)){
                sum_val_List.add(handval);
            }
            String subjCode = handval.getSubCode();
            if (handval.getValType().equals(ValType.ASSET)) {
                if (vtaccountMap.containsKey(subjCode)) {
                    if (vtaccountMap.get(subjCode) != null) {
                        //允许汇总 Y N
                        handval.setAllowSum(vtaccountMap.get(subjCode).getAllowSum());
                        one_handval_List.add(handval);
                    } else {
                        if (handval.getSubCode().length()<9 && isWarn(handval.getSubName())) {
                            logger.debug("handValList warn: {} {}", subjCode, handval.getSubName());
                            warmAssetName += handval.getSubCode() + StringUtil.SPACE + handval.getSubName() + "、";
//                       warnMap.put(assetName, warnMap.get(assetName) + 1);
//                       }/** 新增**/
                        }
                    }
                } else {
                    boolean flag = true;
                    for (int i = subjCode.length(); i > 0; i--) {
                        String beforSub = subjCode.substring(0, i);
                        if (vtaccountMap.containsKey(beforSub)) {
                            one_handval_List.add(handval);
                            flag = false;
                            break;
                        }
                    }
                    // 考虑一级二级科目自动映射的情况
                    if (subjCode.length() < 8) {
                        for (String key : vtaccountMap.keySet()) {
                            if (key.startsWith(subjCode)) {
                                flag = false;
                            }
                        }
                    }
                    if (flag && handval.getSubCode().length()<9 && isWarn(handval.getSubName())) {
                        logger.debug("handValList warn: {} {}", subjCode, handval.getSubName());
                        warmAssetName += handval.getSubCode() + StringUtil.SPACE + handval.getSubName() + "、";
//                       warnMap.put(assetName, warnMap.get(assetName) + 1);
                    }
                }
            }
        }
        //保留叶子节点
        for(RskValtblAssetSubDto one_handval:one_handval_List){
            int i=0;
            for(RskValtblAssetSubDto two_handval:one_handval_List){
                if(two_handval.getSubCode().startsWith(one_handval.getSubCode())
                        &&two_handval.getSubCode().length()>one_handval.getSubCode().length()){
                    i++;
                    break;
                }
            }
            if(i==0){
                two_handval_List.add(one_handval);
            }
        }
        //资产科目代码替换成标准科目代码
        for(RskValtblAssetSubDto two_handval:two_handval_List){
            String subjCode = two_handval.getSubCode();
            if(vtaccountMap.containsKey(subjCode)){
                if(vtaccountMap.get(subjCode)!=null){
                    SubCodeEntry entity = (SubCodeEntry)vtaccountMap.get(subjCode);
                    //允许汇总 Y N
                    two_handval.setAllowSum(entity.getAllowSum());
                    two_handval.setSubCode(entity.getTgt_code());
                    two_handval.setSubName(entity.getTgt_name());
                }
            }else{
                boolean flag = true;
                for(int i=subjCode.length();i>0;i--){
                    String beforSub = subjCode.substring(0, i);
                    String afterSub = subjCode.substring(i,subjCode.length());
                    if(vtaccountMap.containsKey(beforSub)){
                        SubCodeEntry tempEntity = (SubCodeEntry)vtaccountMap.get(beforSub);
                        two_handval.setSubCode(tempEntity.getTgt_code()+afterSub);
                        flag = false;
                        break;
                    }
                }
                if (flag && two_handval.getSubCode().length()<9 && isWarn(two_handval.getSubName())) {
                    logger.debug("handValList warn: {} {}", subjCode, two_handval.getSubName());
                    warmAssetName += two_handval.getSubCode() + StringUtil.SPACE + two_handval.getSubName() + "、";
//                   warnMap.put(assetName, warnMap.get(assetName) + 1);
                }
            }
        }
        // 特殊处理1002科目 & 120401科目
        for(RskValtblAssetSubDto two_handval:two_handval_List) {
            String subjCode = two_handval.getSubCode();
            if (subjCode.startsWith("1002") && !"100201".equals(subjCode)) {
                String afterSub = StringUtils.EMPTY;
                if (subjCode.length() > 8) {
                    afterSub = subjCode.substring(8);
                } else if (subjCode.length() > 6) {
                    afterSub = subjCode.substring(6);
                } else if (subjCode.length() > 4) {
                    afterSub = subjCode.substring(4);
                }
                subjCode = "100201" + SysDataUtil.getNameFromDataDict(DictCode.估值币种映射, two_handval.getCcy()) +afterSub;
            } else if (subjCode.startsWith("120401") && !"120401".equals(subjCode)) {
                String afterSub = StringUtils.EMPTY;
                if (subjCode.length() > 8) {
                    afterSub = subjCode.substring(8);
                } else if (subjCode.length() > 6) {
                    afterSub = subjCode.substring(6);
                }
                subjCode = "120401" + SysDataUtil.getNameFromDataDict(DictCode.估值币种映射, two_handval.getCcy()) +afterSub;
            }
            two_handval.setSubCode(subjCode);

        }
        valList.clear();
        valList.addAll(sum_val_List);
        valList.addAll(two_handval_List);
        if (StringUtil.isNotEmpty(warmAssetName)) {
            warnMap.put(assetName, warmAssetName.substring(0, warmAssetName.length() - 1));
        }
        return valList;
    }

    private RskValtblAssetSubDto handExcelListForVal(List<String> list,Map<String, Object> paramMap, List<String> sumSubjectList){
        RskValtblAssetSubDto val = new RskValtblAssetSubDto();
        int subjCodeColIdx=Integer.valueOf(paramMap.get("subjectCodeCol").toString())-1;
        String subjCode = StringUtil.EMPTY;
        if(list.size()>subjCodeColIdx+1){
            subjCode = list.get(subjCodeColIdx);
        }
        if(StringUtil.isEmpty(subjCode)){return null;}else{
            subjCode.replaceAll(" ", "");
            //add by 张年  2019/07/30
            //科目代码统一去点、去横杠
            subjCode = subjCode.replaceAll(" ", "").replace(".","").replace("-","");}
        //add by 张年  2019/07/30
        //根据科目代码列 判断是资产科目还是汇总科目
        if(subjCode.length() >= 4) {
            try {
                Integer.valueOf(subjCode.substring(0, 4));
                val.setValType(ValType.ASSET);
            } catch(NumberFormatException e) {
                val.setValType(ValType.SUM);
            }
        } else {
            val.setValType(ValType.SUM);
        }
        val = handleSubjCodeCol(list,val,paramMap,subjCodeColIdx,subjCode,sumSubjectList);
        return val;
    }

    private RskValtblAssetSubDto handleSubjCodeCol(List<String> list, RskValtblAssetSubDto val, Map<String, Object> paramMap, int subjCodeColIdx, String subjCode, List<String> sumSubjectList) {
        SumNameCodeEntry sumNameCodeEntry = null;
        String subjName = StringUtil.EMPTY;
        switch (val.getValType()) {
            case ASSET:
                val.setSubCode(subjCode);
                val.setSubName(subjName);
                break;
            case SUM:
                Map<String, SumNameCodeEntry> vtsumaccountMap = (Map<String, SumNameCodeEntry>) paramMap.get("vtsumaccountMap");
                if (paramMap.get("trimChars") != null) {
                    if (StringUtil.isNotEmpty(paramMap.get("trimChars").toString())) {
                        String regex = "["
                                + paramMap.get("trimChars").toString()
                                .replaceAll("\\\\", "\\\\\\\\")
                                .replaceAll("\\[", "\\\\[")
                                .replaceAll("\\]", "\\\\]")
                                .replaceAll("\\^", "\\\\^")

                                + "]";
                        subjCode = subjCode.replaceAll(regex, "");
                    }
                }
                if (vtsumaccountMap.containsKey(subjCode)) {
                    sumNameCodeEntry = vtsumaccountMap.get(subjCode);
                }
                if (sumNameCodeEntry == null) {
                    return null;
                }
                String sumName = "";
                if (paramMap.get("trim_chars") != null) {
                    if (StringUtil.isNotEmpty(paramMap.get("trim_chars").toString())) {
                        String regex = "["
                                + paramMap.get("trim_chars").toString()
                                .replaceAll("\\\\", "\\\\\\\\")
                                .replaceAll("\\[", "\\\\[")
                                .replaceAll("\\]", "\\\\]")
                                .replaceAll("\\^", "\\\\^")
                                + "]";
                        sumName = sumNameCodeEntry.getTgt_sumName().replaceAll(regex, "");
                    } else {
                        sumName = sumNameCodeEntry.getTgt_sumName();
                    }
                } else {
                    sumName = sumNameCodeEntry.getTgt_sumName();
                }
                val.setSubName(sumName);
                val.setSubCode(sumNameCodeEntry.getSumCode());
                break;
        }
        //下一列
        subjName = StringUtil.isNotEmpty(list.get(subjCodeColIdx + 1)) ? list.get(subjCodeColIdx + 1) : "";
        if (val != null) {
            switch (val.getValType()) {
                case ASSET:
                    val.setSubName(subjName);
                    break;
                case SUM:
                    if ((ValDataType.NULL == (sumNameCodeEntry == null ? null : sumNameCodeEntry.getNextColType()))
                            && sumSubjectList.contains(sumNameCodeEntry.getSumCode())) {
                        break;
                    }
                    Double cellNum = null;
                    subjName = subjName.replaceAll(" ", "");
                    if (StringUtil.isNotEmpty(subjName)) {
                        subjName = subjName.replaceAll(",", "");
                        if (ValDataType.STRING == sumNameCodeEntry.getNextColType()) {
                            cellNum = null;
                        } else {
                            if (subjName.contains("%")) {
                                subjName = subjName.replaceAll("%", "");
                                cellNum = (BigDecimal.valueOf(Double.valueOf(subjName) / 100)).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
                            } else {
                                cellNum = (BigDecimal.valueOf(Double.valueOf(subjName))).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
                            }
                        }
                    } else {
                        cellNum = null;
                    }
                    if (cellNum != null) {
                        if (sumSubjectList.contains(sumNameCodeEntry.getSumCode())) {
                            if (ValDataType.PERCENT == sumNameCodeEntry.getNextColType()) {
                                cellNum = (BigDecimal.valueOf(cellNum / 100)).setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
                            }
                            val.setSecurityCost(BigDecimal.valueOf(cellNum));
                        } else {
                            val.setSecurityQuantity(BigDecimal.valueOf(cellNum));
                        }
                    }
                    break;
            }
            //处理其他列
            handleOtherCol(list, val, paramMap);
        }
        return val;
    }

    private void handleOtherCol(List<String> list, RskValtblAssetSubDto val, Map<String, Object> paramMap) {
        String cellVlaue = StringUtil.EMPTY;
        BigDecimal cellNum = null;
        String cellStr = StringUtil.EMPTY;
        Map<String, ColumnMapEntry> vtstructureMap = new HashMap<String, ColumnMapEntry>();
        vtstructureMap = (Map<String, ColumnMapEntry>) paramMap.get("vtstructureMap");
        for (int i = 0; i < list.size(); i++) {
            cellVlaue = StringUtil.isNotEmpty(list.get(i))  ? list.get(i) : "";
            cellStr = cellVlaue.replaceAll(" ", "");
            try {
                ColumnMapEntry colMapEntry = vtstructureMap.get(Integer.toString(i + 1));
                if (colMapEntry == null) {
                    continue;
                }
                if (StringUtil.isNotEmpty(cellStr)) {
                    cellStr = cellStr.replaceAll(",", "");
                    if (ValDataType.STRING == colMapEntry.getDataType()) {
                        cellNum = null;
                    } else {
                        if (cellVlaue.contains("%")) {
                            cellStr = cellStr.replaceAll("%", "");
                            cellNum = (BigDecimal.valueOf(Double.valueOf(cellStr) / 100)).setScale(8, BigDecimal.ROUND_HALF_UP);
                        } else {
                            cellNum = (BigDecimal.valueOf(Double.valueOf(cellStr))).setScale(8, BigDecimal.ROUND_HALF_UP);
                        }
                    }
                } else {
                    cellNum = null;
                }
                if (ValDataType.PERCENT == colMapEntry.getDataType()) {
                    cellNum = cellNum == null ? null : (cellNum.divide(new BigDecimal(100))).setScale(8, BigDecimal.ROUND_HALF_UP);
                }
                switch (colMapEntry.getValIndex()) {
                    case QUANTITY:
                        if (cellNum != null) {
                            val.setSecurityQuantity(cellNum);
                        }
                        break;
                    case UNIT_COST:
                        if (cellNum != null) {
                            val.setUnitCost(cellNum);
                        }
                        break;
                    case COST:
                        if (cellNum != null) {
                            if (val.getSecurityCost() == null) {
                                val.setSecurityCost(cellNum);
                            }
                        }
                        break;
                    case CTNWR:
                        if (cellNum != null) {
                            val.setCtnwr(cellNum);
                        }
                        break;
                    case MCP:
                        if (cellNum != null) {
                            val.setMcp(cellNum);
                        }
                        break;
                    case SMV:
                        if (cellNum != null) {
                            val.setSmv(cellNum);
                        }
                        break;
                    case MCR:
                        if (cellNum != null) {
                            val.setMcr(cellNum);
                        }
                        break;
                    case VAL_INC:
                        if (cellNum != null) {
                            val.setValueAdded(cellNum);
                        }
                        break;
                    case SUSPENSION_INFO:
                        val.setSuspensionInfo(cellStr);
                        break;
                    case EQUITY_INFO:
                        val.setEquityInfo(cellStr);
                        break;
                    case COST_ORI:
                        if (cellNum != null) {
                            if (val.getSecurityCostOri() == null) {
                                val.setSecurityCostOri(cellNum);
                            }
                        }
                        break;
                    case SMV_ORI:
                        if (cellNum != null) {
                            val.setSmvOri(cellNum);
                        }
                        break;
                    case VAL_INC_ORI:
                        if (cellNum != null) {
                            val.setValueAddedOri(cellNum);
                        }
                        break;
                    case TRADE_PLACE:
                        val.setTradePlace(cellStr);
                        break;
                    case CCY:
                        val.setCcy(cellStr);
                        break;
                    case EXCHANGE_RATE:
                        if (cellNum != null) {
                            val.setExchangeRate(cellNum);
                        }
                        break;
                    case INVEST_TYPE:
                        val.setInvestType(cellStr);
                        break;
                    case ISSUE_CODE:
                        val.setIssueCode(cellStr);
                        break;
                    default:
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ((Boolean) paramMap.get("isExistCcyCol")) {
            val.setCcy(DictCodeList.币种_人民币_CNY.getValue());
        }
        if ((Boolean) paramMap.get("isExistInvestType")) {
            dealInvestType(val, (String)paramMap.get("managerName"));
        }
    }

    private void dealInvestType(RskValtblAssetSubDto val, String managerName) {
        if (StringUtil.isNotEmpty(managerName) && val.getValType().equals(ValType.ASSET)) {
            String investType = StringUtils.EMPTY;
            String issueCode = StringUtils.EMPTY;
            String tradePlace = StringUtils.EMPTY;
            if (val.getSubCode().contains("SH") ||
                    val.getSubCode().contains("SZ") ||
                    val.getSubCode().contains("CY")
                    || val.getSubCode().contains("IB")
                    || val.getSubCode().contains("OF")//场外基金
                    || val.getSubCode().contains("OTC")//大部分行场外基金后缀都是OTC

            ) {
                if(val.getSubCode().contains("OTC")){
                    tradePlace = val.getSubCode().substring(val.getSubCode().length() - 3);
                }else{
                    tradePlace = val.getSubCode().substring(val.getSubCode().length() - 2);
                }
                tradePlace = tradePlace.replace("SH", "1");
                tradePlace = tradePlace.replace("SZ", "2");
                tradePlace = tradePlace.replace("IB", "3");
                tradePlace = tradePlace.replace("CY", "3");
                tradePlace = tradePlace.replace("OTC", "4");
                tradePlace = tradePlace.replace("OF", "5");
            }
            if (val.getSubCode().length()>=14){
                issueCode = val.getSubCode().substring(8,14);
            }
            if ("XINGQUAN".equals(managerName) || "XINGYE".equals(managerName)) {
                investType = getNameFromDictValue("MANAGER_INVEST_TYPE_" + managerName, val.getSubCode().substring(0, 3));

            } else if ("HUITIANFU".equals(managerName) && val.getSubCode().length() >= 8) {
                investType = getNameFromDictValue("MANAGER_INVEST_TYPE_" + managerName, val.getSubCode().substring(0, 8));
            }
            else {
                investType = getNameFromDictValue("MANAGER_INVEST_TYPE_" + managerName, val.getSubCode().substring(0, 4));
            }
            logger.debug("dealInvestType managerName:{}, subCode:{}, investType:{}", managerName, val.getSubCode(), investType);
            if (StringUtil.isNotEmpty(investType)) {
                val.setInvestType(investType);
            }

            if (StringUtil.isNotEmpty(issueCode)) {
                val.setIssueCode(issueCode);
            }

            if (StringUtil.isNotEmpty(tradePlace)) {
                val.setTradePlace(tradePlace);
            }

        }
    }

    public static String getNameFromDictValue(String dictValue, String itemCode) {
        List<? extends DataDictItem> dataDictItemList = getDataDictItem(dictValue);
        return (String)dataDictItemList.stream().filter((item) -> {
            return StringUtil.equals(item.getItemCode(), itemCode);
        }).map(DataDictItem::getItemValue).findFirst().orElse(null);
    }

    public static List<? extends DataDictItem> getDataDictItem(String dictCode) {
        DataDict dict = CacheUtil.getDictCode(dictCode);
        return dict != null ? dict.getItems() : null;
    }

    private List<List<String>> readExcelUtil(RskValTableImportParamDto dto) throws Exception {
        List<List<String>> resultList = new ArrayList<List<String>>();
        Workbook wb = ExcelUtil.loadWorkbook(dto.getValTableFileName(), null, null);
        if (Objects.isNull(wb)) {
            return null;
        }
        // ---modify by 裴家俊 20190514---start---------
        Filter filter = null;
        /**
         * 过滤器合并列 BussinessConstant引不到，hardcode
         * 光大项目修改
         */
        String VALUATION_FILTER_MERGECOL = "MergeColsFilter";

        if (StringUtil.isNotEmpty(dto.getFilterId())) {
            //if (dto.getFilterId().startsWith(BussinessConstant.VALUATION_FILTER_MERGECOL)) {
            if (dto.getFilterId().startsWith(VALUATION_FILTER_MERGECOL)) {

                    filter = (Filter) Class
                        .forName("com.joyintech.service.risk.valtblimport.filtertool." + VALUATION_FILTER_MERGECOL)
                        .newInstance();
            } else {
                filter = (Filter) Class.forName("com.joyintech.service.risk.valtblimport.filtertool." + dto.getFilterId())
                        .newInstance();
            }
        }
        if (filter != null) {
            if (dto.getFilterId().startsWith(VALUATION_FILTER_MERGECOL)) {
                wb = filter.doFilter(wb, 0,
                        dto.getFilterId().substring(VALUATION_FILTER_MERGECOL.length() + 1));
            } else {
                wb = filter.doFilter(wb, 0);
            }
            resultList = readToList(0, Integer.parseInt(dto.getStartRow()) - 1, Integer.parseInt(dto.getStartRow()) - 1,
                    wb);
        } else {
            resultList = readToList(0, Integer.parseInt(dto.getStartRow()) - 1, Integer.parseInt(dto.getStartRow()) - 2,
                    wb);
        }
        // ---modify by 裴家俊 20190514---end---------
        return resultList;
    }

    private List<List<String>> readToList(int sheetIndex, int skipRows, int titleRow,Workbook wb)
            throws Exception {
        List<List<String>> ls = new ArrayList<List<String>>();
        if (null != wb) {
            Sheet sh = wb.getSheetAt(sheetIndex);
            int rows = sh.getPhysicalNumberOfRows();
            int cells = sh.getRow(titleRow).getPhysicalNumberOfCells();
            //这步check多余   双表头无法读出数据
            /*Row tRow = sh.getRow(titleRow);*/
            // 列标题为空的话结束
           /*for (int c = 0; c < cells; c++ ) {
               if (tRow.getCell(c) == null
                   || StringUtil.isEmpty(getStringCellValue(tRow.getCell(c)))) {
                   cells = c;
                   break;
               }
           }*/

            for (int i = skipRows; i < rows; i++ ) {
                Row row = sh.getRow(i);
                if (row == null) {
                    break;
                }
                List<String> r = new ArrayList<String>(cells);
                boolean dataFlag = true;
                for (int c = 0; c < cells; c++ ) {
                    if (row.getCell(c) == null
                            || row.getCell(c).getCellTypeEnum() == CellType.BLANK) {
                        r.add("");
                    }
                    else {
                        dataFlag = false;
                        r.add(getStringCellValue(row.getCell(c)));
                    }
                }
                // 遇到一行全为空继续
                if (dataFlag) {
                    continue;
                }
                ls.add(r);
            }
        }
        return ls;
    }

    private static String getStringCellValue(Cell cell) {
        String value = null;
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // 数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = DateUtil.format(cell.getDateCellValue(),
                            DateUtil.DEFAULT_ANOTHER_FORMAT);
                    return value;
                }
                value = ExcelUtil.point.format(cell.getNumericCellValue());
                break;
            case STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case FORMULA: // 公式
                cell.setCellType(CellType.STRING);
                value = cell.getStringCellValue();
                break;
            case BLANK: // 空值
                break;
            case BOOLEAN: // Boolean
                break;
            case ERROR: // 故障
                break;
            default:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = DateUtil.format(cell.getDateCellValue());
                }
                break;
        }

        return value;
    }

}
