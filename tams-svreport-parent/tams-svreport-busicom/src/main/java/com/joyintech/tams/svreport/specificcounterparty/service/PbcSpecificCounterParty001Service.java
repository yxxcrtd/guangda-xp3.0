package com.joyintech.tams.svreport.specificcounterparty.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import com.joyintech.tams.common.svreport.datacheck.dto.CheckRule;
import com.joyintech.tams.common.svreport.datacheck.service.DataCheckService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.dao.ChbInfoMgrResultConfigMapper;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.svreport.specificcounterparty.dao.PbcSpecificCounterParty001Mapper;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.specificcounterparty.dto.ZG08SpecCounterQuityRule;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 特定目的载体对手方信息维护
 *
 * @author yangxianjiu
 * @date 2022/11/1
 */
@Service("PbcSpecificCounterParty001Service")
@Transactional(rollbackFor = Exception.class)
public class PbcSpecificCounterParty001Service extends CommonExportService<PbcSpecificCounterParty001Dto> {

    /**
     * 表名
     */
    public static final String PBC_SPE_COUNTER_PARTY = "PBC_SPE_COUNTER_PARTY";

    @Autowired
    private DataCheckService dataCheckService;

    /**
     * Mapper
     */
    @Autowired
    private PbcSpecificCounterParty001Mapper pbcSpecificCounterParty001Mapper;

    @Autowired
    private ChbInfoMgrResultConfigMapper chbInfoMgrResultConfigMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 分页查询
     *
     * @param pageParam 查询参数
     * @return 返回数据
     */
    public Page<PbcSpecificCounterParty001Dto> selectForPage(Integer page, Integer length, Map<String, Object> pageParam) {
        return queryForPage(page, length, () -> pbcSpecificCounterParty001Mapper.selectForPage(pageParam));
    }

    /**
     * 获取详情
     *
     * @param param
     * @return 对应数据
     */
    public PbcSpecificCounterParty001Dto selectDetail(Map<String, Object> param) {
        return pbcSpecificCounterParty001Mapper.selectByPrimaryKey(param.get("pbcUuid").toString());
    }

    /**
     * @param paramMap
     * @return
     */
    public List<SuggestDto> queryAssetCodeIndentifyMark(Map<String, Object> paramMap) {
        String flag = paramMap.get("flag").toString();
        List<SuggestDto> list = new ArrayList<>();
        if (StringUtil.equals("asset", flag)) {
            list = pbcSpecificCounterParty001Mapper.queryAssetCode(paramMap);
        } else {
            list = pbcSpecificCounterParty001Mapper.queryIndentifyMark(paramMap);
        }
        return list;
    }

    /**
     * 保存
     *
     * @param dto 对象
     * @return 返回结果
     */
    public Integer save(PbcSpecificCounterParty001Dto dto) {
        Integer result = 0;
        if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, dto.getPageModel())) {
            dto.setPbcUuid(IDGenerator.generate(36).toUpperCase());
            dto.setStatus(DictCodeListExt3.中债数据状态_有效.getValue());
            result = pbcSpecificCounterParty001Mapper.insertSelective(dto);
        } else if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, dto.getPageModel())) {
            dto.setStatus(DictCodeListExt3.中债数据状态_有效.getValue());
            result = pbcSpecificCounterParty001Mapper.updateByPrimaryKeySelective(dto);
        }
        return result;
    }

    /**
     * 删除数据
     *
     * @param dto 删除
     * @return 影响行数
     */
    public Integer delete(PbcSpecificCounterParty001Dto dto) {
        Map<String, List<PbcSpecificCounterParty001Dto>> dtoMap = dto.getSelectedItem().stream().collect(Collectors.groupingBy(item-> item.getStatus()));
        int result = 0;
        if (dtoMap.containsKey(DictCodeListExt3.中债数据状态_待处理.getValue())){
            // 删除待处理
            dto.setStatus(DictCodeListExt3.中债数据状态_待处理.getValue());
            result += pbcSpecificCounterParty001Mapper.deleteByPrimaryKey(dtoMap.get(DictCodeListExt3.中债数据状态_待处理.getValue()));
        }
        if (dtoMap.containsKey(DictCodeListExt3.中债数据状态_有效.getValue())){
            // 删除 数据状态为 删除 数据
            dto.setStatus(DictCodeListExt3.中债数据状态_删除.getValue());
            result += pbcSpecificCounterParty001Mapper.delete(dto, dtoMap.get(DictCodeListExt3.中债数据状态_有效.getValue()));
            // 删除有效数据
            result += pbcSpecificCounterParty001Mapper.updateByPrimaryKey(dto, dtoMap.get(DictCodeListExt3.中债数据状态_有效.getValue()));
        }
        return result;
    }

    /**
     * 构建导出参数
     *
     * @param exportParam
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/svreport/specificcounterparty/conf/export_conf.xml")
                .select(() -> pbcSpecificCounterParty001Mapper.selectForPage(exportParam.getSelectParam()));
        super.buildExportParam(exportParam);
    }

    /**
     * 导入数据文件之前检查数据
     * 不用微服务调用 错误信息提示会 导致http响应码500
     *
     * @param param
     * @return
     * @throws Exception
     */
    public AjaxResult checkConfirmMsgForImportData(Map<String, Object> param) {
        String fileName = param.get("fileName").toString();
        AjaxResult webResult = new AjaxResult();
        try {


            if (fileName.indexOf("特定目的载体对手") == -1) {
                webResult.setMessage(this.getMessage("ImportFileName"), MessageType.错误消息);
                return webResult;
            }
            List<List<String>> dataList = ExcelUtil.getDataList(fileName);
            List<List<String>> tempList = ExcelUtil.getTempList(fileName);
            List<ExcelValidDto> checkResult = ExcelUtil.checkExcelTemp(tempList, getHeadList());
            if (!StringUtil.isEmptyList(checkResult)) {
                webResult.setMessage("导入模板不一致，请重新选择文件！", MessageType.错误消息);
                return webResult;
            }
            List<PbcSpecificCounterParty001Dto> list = listToDto(dataList);
            List<Map<String, String>> mapList = listToMap(dataList);
            // 文件
            if (list.size() < 1) {
                webResult.setMessage("请勿上传空文件", MessageType.错误消息);
                return webResult;
            }
            int result = pbcSpecificCounterParty001Mapper.countDuplicatedData(list);
            if (result > 0) {
                int dup = countDuplicatedData(mapList, "ASSETC_CODE");
                webResult.setMessage(this.getMessage("SvrDuplicateMessage", dup, result), MessageType.确认消息);
            }
        } catch (Exception e) {
            logger.info("检查数据错误" + e.getMessage());
        }
        return webResult;
    }

    /**
     * 导入数据文件
     *
     * @param param 导入
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(Map<String, Object> param) {
        // 资产端
        List<PbcSpecificCounterParty001Dto> allAssetCode = pbcSpecificCounterParty001Mapper.selectAllAssetCode();
        List<PbcSpecificCounterParty001Dto> allInvestCode = pbcSpecificCounterParty001Mapper.selectAllInvestCode();
        Map<String,PbcSpecificCounterParty001Dto>  tzCodeMap=allInvestCode.stream().collect(
                JoyinCollectors.toMap(x -> x.getAssetCode(), x -> x));

        String fileName = param.get("fileName").toString();
        List<List<String>> dataList = ExcelUtil.getDataList(fileName);
        List<List<String>> tempList = ExcelUtil.getTempList(fileName);
        List<ExcelValidDto> checkResult = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(dataList)) {
            // 校验数据模板
            checkResult = ExcelUtil.checkExcelTemp(tempList, getHeadList());
            if (checkResult != null && !StringUtil.isEmptyList(checkResult)) {
                return checkResult;
            }
            checkResult = new ArrayList<>();
            //  SvrMaptoEntityUtil.convertMapToEntity()
            List<Map<String, String>> mapList = listToMap(dataList);

            //去除导入的交易对手产品种类 只根据资产端映射入库  add by wangyaning 20230112  start
            //处理交易对手种类
            for(Map<String, String> map : mapList){
                if(StringUtil.isNotEmpty(map.get("ASSET_CODE"))){
                    Map<String, Object> assetCodeParam = new HashMap<>();
                    assetCodeParam.put("flag","asset");
                    assetCodeParam.put("search",map.get("ASSET_CODE"));
                    Map<String, Object> partyProdTypeMap = getPartyProdTypeByAssetCode(assetCodeParam);
                    if(!ObjectUtils.isEmpty(partyProdTypeMap.get("partyProdType"))){
                        map.put("PARTY_PROD_TYPE",String.valueOf(partyProdTypeMap.get("partyProdType")));
                        continue;
                    }
                    //再去映射投资者
                    if(tzCodeMap.containsKey(map.get("ASSET_CODE"))){
                        PbcSpecificCounterParty001Dto dto=tzCodeMap.get(map.get("ASSET_CODE"));
                        mapPartyProdType(dto,map);
                    }
                }
            }
            //去除导入的交易对手产品种类 只根据资产端映射入库  add by wangyaning 20230112  start

            // 数据检查
            List<CheckRule> checkRules = dataCheckService.queryCheckRules(PBC_SPE_COUNTER_PARTY);
            List<Map<String, String>> checkDataList = DataCheckService.checkDataList(mapList, checkRules);
            // 拼接错误信息
            for (Map<String, String> map : checkDataList) {
                if (map.containsKey("ERRMSG")) {
                    checkResult.add(new ExcelValidDto(map.get("ASSET_CODE"), map.get("ERRMSG_ROW"), map.get("ERRMSG")));
                }
            }
            if (checkResult != null && !StringUtil.isEmptyList(checkResult)) {
                return checkResult;
            }
            // 映射资产负债项目
            allAssetCode.addAll(allInvestCode);
            List<PbcSpecificCounterParty001Dto> party001Dtos = mapAssetType(allAssetCode);
            // 资产代码
            Map<String, PbcSpecificCounterParty001Dto> collect = party001Dtos.stream().collect(Collectors.toMap(PbcSpecificCounterParty001Dto::getAssetCode, v -> v, (k1, k2) -> k1));
            // 数据检查通过 补充其余字段
//            mapList.stream().forEach(item -> {
//                item.put("PBC_UUID", IDGenerator.generate(36));
//                item.put("STATUS", DictCodeListExt3.中债数据状态_有效.getValue());
//                item.put("CREATE_USER", CommonUtil.getUserName());
//                item.put("CREATE_TIME", DateUtil.format(new Date()));
//                item.put("UPDATE_USER", CommonUtil.getUserName());
//                item.put("UPDATE_TIME", DateUtil.format(new Date()));
//                item.put("CREATE_DEPT", CommonUtil.getDeptCode());
//            });
            Iterator<Map<String, String>> iterator = mapList.iterator();
            while (iterator.hasNext()){
                Map<String, String> tempMap = iterator.next();
                if (collect.containsKey(tempMap.get("ASSET_CODE"))){
                    // 映射资产负债项目
                    tempMap.put("ASSET_TYPE", collect.get(tempMap.get("ASSET_CODE")).getAssetType());
                    tempMap.put("PBC_UUID", IDGenerator.generate(36));
                    tempMap.put("STATUS", DictCodeListExt3.中债数据状态_有效.getValue());
                    tempMap.put("CREATE_USER", CommonUtil.getUserName());
                    tempMap.put("CREATE_TIME", DateUtil.format(new Date()));
                    tempMap.put("UPDATE_USER", CommonUtil.getUserName());
                    tempMap.put("UPDATE_TIME", DateUtil.format(new Date()));
                    tempMap.put("CREATE_DEPT", CommonUtil.getDeptCode());
                } else {
                    // 没有存在的资产代码 移除
                    iterator.remove();
                    // 不让导入的资产代码 增加 提示不存在
                    checkResult.add(new ExcelValidDto(tempMap.get("ASSET_CODE"), tempMap.get("ROW_NO"), "请先维护资产代码！"));
                }
            }
            // map 转 entity
            List<PbcSpecificCounterParty001Dto> dtoList = SvrMaptoEntityUtil.convertMapToEntity(mapList, getListCloumn(), PbcSpecificCounterParty001Dto.class);
            // 插入数据
            insertDataPart(dtoList);
            return checkResult;
        }
        return null;
    }

    /**
     * 映射 资产端 投资端 资产负债项目
     * @param list
     * @return
     */
    public List<PbcSpecificCounterParty001Dto> mapAssetType(List<PbcSpecificCounterParty001Dto> list){
        for (PbcSpecificCounterParty001Dto value : list) {
            // 资产端
            if (StringUtil.isNotEmpty(value.getPbcAssetType2())){
                if (DictCodeListExt3.人行资产二级分类_拆借和回购.getValue().equals(value.getPbcAssetType2())){
                    // 资产负债项目
                    if (DictCodeListExt2.资产_负债二级类别_卖出回购.getValue().equals(value.getAssetType2())){
                        value.setAssetType("B1200");
                    } else {
                        value.setAssetType("A5200");
                    }
                } else if (DictCodeListExt3.人行资产二级分类_特定目的载体份额.getValue().equals(value.getPbcAssetType2())){
                    value.setAssetType("A7200");
                }
            } else {
                value.setAssetType("C1200");
            }
        }
        return list;
    }

    /**
     * 映射交易对手产品种类
     * @param
     * @return
     */
    public void mapPartyProdType(PbcSpecificCounterParty001Dto dto,Map<String,String> map){
        if (DictCodeListExt1.投资者类别_信托产品.getValue().equals(dto.getPartyProdType())){
            map.put("PARTY_PROD_TYPE","2");
        } else if (DictCodeListExt1.投资者类别_券商资产管理计划.getValue().equals(dto.getPartyProdType())) {
            map.put("PARTY_PROD_TYPE","3");
        } else if (DictCodeListExt1.投资者类别_基金资产管理计划.getValue().equals(dto.getPartyProdType())) {
            map.put("PARTY_PROD_TYPE","4");
        } else if (DictCodeListExt1.投资者类别_期货资产管理计划.getValue().equals(dto.getPartyProdType())) {
            map.put("PARTY_PROD_TYPE","5");
        } else if (DictCodeListExt1.投资者类别_保险资产管理计划.getValue().equals(dto.getPartyProdType())) {
            map.put("PARTY_PROD_TYPE","6");
        } else if (DictCodeListExt1.投资者类别_社会保障类基金.getValue().equals(dto.getPartyProdType())) {
            map.put("PARTY_PROD_TYPE","8");
        }

    }

    /**
     * 检查导入文件中的重复数据
     *
     * @param list
     * @param duplicateKey
     * @return
     */
    private int countDuplicatedData(List<Map<String, String>> list, String... duplicateKey) {
        if (list == null || list.size() < 1) {
            return 0;
        }
        Set<String> set = new HashSet<>();

        if (duplicateKey != null && duplicateKey.length > 1) {
            list.forEach(e -> {
                StringBuilder sb = new StringBuilder();
                Stream.of(duplicateKey).forEach(ele -> {
                    sb.append(e.get(ele));
                });
                set.add(sb.toString());
            });
            return list.size() - set.size();
        } else {
            //duplicateKey 为空则查询所有列
            list.forEach(e -> {
                StringBuilder sb = new StringBuilder();
                e.keySet().forEach(ele -> {
                    sb.append(e.get(ele));
                });
                set.add(sb.toString());
            });
            return list.size() - set.size();
        }
    }

    /**
     * 导入文件头配置
     *
     * @return 文件列
     */
    private List<String> getHeadList() {
        List<String> headList = Lists.newArrayList();
        headList.add("资产代码");
        headList.add("交易对手机构名称");
        headList.add("交易对手产品名称");
        //去除导入的交易对手产品种类 只根据资产端映射入库  delete by wangyaning 20230112  start
        //headList.add("交易对手产品种类");
        //去除导入的交易对手产品种类 只根据资产端映射入库  delete by wangyaning 20230112  end
        headList.add("交易对手机构编码");
        headList.add("交易对手产品代码");
        ;
        return headList;
    }

    /**
     * 获取转换列名
     *
     * @return
     */
    private List<String> getListCloumn() {
        List<String> listCloumn = Lists.newArrayList();
        listCloumn.add("PBC_UUID");
        listCloumn.add("ASSET_CODE");
        listCloumn.add("PARTY_ORG_NAME");
        listCloumn.add("PARTY_PROD_NAME");
        listCloumn.add("ASSET_TYPE");
        listCloumn.add("PARTY_PROD_TYPE");
        listCloumn.add("PARTY_ORG_CODE");
        listCloumn.add("PARTY_PROD_ID");
        listCloumn.add("STATUS");
        listCloumn.add("CREATE_USER");
        listCloumn.add("CREATE_TIME");
        listCloumn.add("UPDATE_USER");
        listCloumn.add("UPDATE_TIME");
        listCloumn.add("CREATE_DEPT");
        return listCloumn;
    }

    /**
     * 转换为 entity
     *
     * @param dataList
     * @return
     */
    public List<PbcSpecificCounterParty001Dto> listToDto(List<List<String>> dataList) {
        String[] fieldName = {"assetCode", "partyOrgName", "partyProdName"
                , "partyOrgCode", "partyProdId"};
        List<PbcSpecificCounterParty001Dto> dtoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (List<String> list : dataList) {
                if (list.size() == 5) {
                    PbcSpecificCounterParty001Dto dto = new PbcSpecificCounterParty001Dto();
                    // UUID
                    dto.setPbcUuid(IDGenerator.generate(36));
                    // 资产代码
                    dto.setAssetCode(list.get(0) == null ? "" : list.get(0));
                    // 交易对手机构名称
                    dto.setPartyOrgName(list.get(1) == null ? "" : list.get(1));
                    // 交易对手产品名称
                    dto.setPartyProdName(list.get(2) == null ? "" : list.get(2));
                    // 资产负债项目
                    //dto.setAssetType(list.get(3) == null ? "" : list.get(3).trim().split(" ")[0]);

//去除导入的交易对手产品种类 只根据资产端映射入库  delete by wangyaning 20230112  start
                    // 交易对手产品种类
//                    dto.setPartyProdType(list.get(3) == null ? "" : list.get(3).trim().split(" ")[0]);
//去除导入的交易对手产品种类 只根据资产端映射入库  delete by wangyaning 20230112  end

                    // 交易对手机构编码
                    dto.setPartyOrgCode(list.get(3) == null ? "" : list.get(3));
                    // 交易对手产品代码
                    dto.setPartyProdId(list.get(4) == null ? "" : list.get(4));
                    dto.setCreateUser(CommonUtil.getUserName());
                    dto.setUpdateUser(CommonUtil.getUserName());
                    dto.setCreateDept(CommonUtil.getDeptCode());
                    dto.setCreateTime(new Date());
                    dto.setUpdateTime(new Date());
                    dtoList.add(dto);
                }
            }
        }
        return dtoList;
    }

    /**
     * 解析文件为 Map
     *
     * @param dataList
     * @return
     */
    public List<Map<String, String>> listToMap(List<List<String>> dataList) {
        String[] fieldName = {"ASSET_CODE", "PARTY_ORG_NAME", "PARTY_PROD_NAME"
                , "PARTY_ORG_CODE", "PARTY_PROD_ID"};
        List<Map<String, String>> tempList = Lists.newArrayList();
        int index = 0;
        for (List<String> list : dataList) {
            HashMap<String, String> map = Maps.newHashMap();
            map.put(fieldName[0], list.get(0) == null ? "" : list.get(0));
            map.put(fieldName[1], list.get(1) == null ? "" : list.get(1));
            map.put(fieldName[2], list.get(2) == null ? "" : list.get(2));
            //map.put(fieldName[3], list.get(3) == null ? "" : list.get(3).trim().split(" ")[0]);

            //去除导入的交易对手产品种类 只根据资产端映射入库  delete by wangyaning 20230112  start
//            map.put(fieldName[3], list.get(3) == null ? "" : list.get(3).trim().split(" ")[0]);
            //去除导入的交易对手产品种类 只根据资产端映射入库  delete by wangyaning 20230112  end

            map.put(fieldName[3], list.get(3) == null ? "" : list.get(3));
            map.put(fieldName[4], list.get(4) == null ? "" : list.get(4));
            // 未有错误信息 提示行号
            map.put("ROW_NO", String.valueOf(dataList.indexOf(list) + 2));
            tempList.add(map);
        }
        return tempList;
    }

    /**
     * 数据 批次入库
     *
     * @param dataList
     */
    private void insertDataPart(List<PbcSpecificCounterParty001Dto> dataList) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            int maxDataCountOneTime = 50;
            int dataCount = dataList.size();
            if (dataCount <= maxDataCountOneTime) {
                pbcSpecificCounterParty001Mapper.mergeInfoList(dataList);
                return;
            }
            List<PbcSpecificCounterParty001Dto> newDataList = new ArrayList<>();
            newDataList.addAll(dataList);
            // 分批处理
            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++) {
                pbcSpecificCounterParty001Mapper.mergeInfoList(newDataList.subList(0, maxDataCountOneTime));
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newDataList)) {
                pbcSpecificCounterParty001Mapper.mergeInfoList(newDataList);
            }
        }
    }

    /**
     * 资产类 通过资产代码获取 交易对手机构名称
     * @param param
     * @return
     */
    public Map<String, Object> getPartyOrgNameByAssetCode(Map<String, Object> param) {
        return pbcSpecificCounterParty001Mapper.getPartyOrgNameByAssetCode(param);
    }

    /**
     * 根据 资产代码字段 查找 资产基本信息中 资产名称、 根据 识别标识 查找 投资者名称
     * @param param
     * @return
     */
    public Map<String, Object> getPartyProdNameByAssetCode(Map<String, Object> param) {
        return pbcSpecificCounterParty001Mapper.getPartyProdNameByAssetCode(param);
    }


    /**
     * 根据 资产代码字段 查找 资产基本信息中资产二级分类，人行资产一级分类，人行资产二级分类，人行资产三级分类 来映射出交易对手产品种类
     * @param param
     * @return
     */
    public Map<String, Object> getPartyProdTypeByAssetCode(Map<String, Object> param) {
        Map<String, Object> AssetMap = new HashMap<>();
        AssetMap = pbcSpecificCounterParty001Mapper.getPartyProdTypeByAssetCode(param);
        if(AssetMap != null) {
            String pbcAssetType = String.valueOf(AssetMap.get("pbcAssetType1")) + GlobalConstant.COMMA
                    + String.valueOf(AssetMap.get("pbcAssetType2")) + GlobalConstant.COMMA + String.valueOf(AssetMap.get("pbcAssetType3"));
            String chbAssetType2 = String.valueOf(AssetMap.get("chbAssetType2"));
            //循环做数据匹配
            for (ZG08SpecCounterQuityRule rule : ZG08SpecCounterQuityRule.values()) {
                if (rule.getPbcAssetType().equals(pbcAssetType)) {//匹配资产端数据
                    if (CollectionUtils.isNotEmpty(rule.getChbCodeEqualsList())
                            && rule.getChbCodeEqualsList().contains(chbAssetType2)) {
                        AssetMap.put("partyProdType", rule.getPrrtyProdType());
                    } else if (CollectionUtils.isNotEmpty(rule.getChbCodeNotEqualsList())
                            && !rule.getChbCodeNotEqualsList().contains(chbAssetType2)) {
                        AssetMap.put("partyProdType", rule.getPrrtyProdType());
                    } else if (CollectionUtils.isEmpty(rule.getChbCodeEqualsList()) && CollectionUtils.isEmpty(rule.getChbCodeNotEqualsList())) {
                        AssetMap.put("partyProdType", rule.getPrrtyProdType());
                    }
                }
            }
            if (ObjectUtils.isEmpty(AssetMap.get("partyProdType"))) {
                AssetMap.put("partyProdType", null);
            }
        }else{
            Map<String, Object> AssetNullMap = new HashMap<>();
            AssetNullMap.put("partyProdType", null);
            return AssetNullMap;
        }
        return AssetMap;
    }
}
