package com.joyintech.tams.svreport.specificinvestorshold.service;


import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.PbcTargetInfoErrordataMapper;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.common.base.entity.PbcTargetInfoErrordataWithBLOBs;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.dao.ChbInfoMgrResultConfigMapper;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.svreport.interf.chb.prodinfo.service.SvrProdTargetFileImpService;
import com.joyintech.tams.svreport.specificinvestorshold.dao.PbcSpecificInvestorsHold001Mapper;
import com.joyintech.tams.svreport.specificinvestorshold.dto.PbcSpecificInvestorsHold001Dto;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 特定目的载体持有信息维护(投资者)
 *
 * @author yangxianjiu
 * @date 2022/11/4
 */
@Service("PbcSpecificInvestorsHold001Service")
@Transactional(rollbackFor = Exception.class)
public class PbcSpecificInvestorsHold001Service extends CommonExportService<PbcSpecificInvestorsHold001Dto> {

    @Autowired
    private SvrProdTargetFileImpService svrProdTargetFileImpService;

    /**
     * Mapper
     */
    @Autowired
    private PbcSpecificInvestorsHold001Mapper pbcSpecificInvestorsHold001Mapper;

    @Autowired
    private ChbInfoMgrResultConfigMapper chbInfoMgrResultConfigMapper;

    @Autowired
    private PbcTargetInfoErrordataMapper pbcTargetInfoErrordataMapper;

    private static Logger logger = LoggerFactory.getLogger(PbcSpecificInvestorsHold001Service.class);

    /**
     * 分页查询
     *
     * @param pageParam 查询参数
     * @return 返回数据
     */
    public Page<PbcSpecificInvestorsHold001Dto> selectForPage(Integer page, Integer length, Map<String, Object> pageParam) {
        return queryForPage(page, length, () -> pbcSpecificInvestorsHold001Mapper.selectForPage(pageParam));
    }

    /**
     * 获取详情
     *
     * @param param 主键
     * @return 对应数据
     */
    public PbcSpecificInvestorsHold001Dto selectDetail(Map<String, Object> param) {
        PbcSpecificInvestorsHold001Dto dto = new PbcSpecificInvestorsHold001Dto();
        if(param.containsKey("status")){
            if ("A".equals(param.get("status"))){
                PbcTargetInfoErrordataWithBLOBs errordata = pbcTargetInfoErrordataMapper.selectByPrimaryKey(param.get("uuid").toString());
                if(errordata!=null){
                    dto = errdataConvert(dto.getClass(), errordata.getOrgData());
                    dto.setStatus(DictCodeListExt3.中债数据状态_待处理.getValue());
                    dto.setUuid(errordata.getUuid());
                }
            } else {
                dto = pbcSpecificInvestorsHold001Mapper.selectByPrimaryKey(param.get("uuid").toString());
            }
        } else {
            dto = pbcSpecificInvestorsHold001Mapper.selectByPrimaryKey(param.get("uuid").toString());
        }
        return dto;
    }

    /**
     * 保存
     *
     * @param dto 对象
     * @return 返回结果
     */
    public Integer save(PbcSpecificInvestorsHold001Dto dto) {
        Integer result = 0;
        if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, dto.getPageModel())) {
            dto.setUuid(IDGenerator.generate(36).toUpperCase());
            dto.setStatus(DictCodeListExt3.中债数据状态_有效.getValue());
            result = pbcSpecificInvestorsHold001Mapper.insertSelective(dto);
        } else if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, dto.getPageModel())) {
            // 待处理数据
            if (StringUtil.equals(dto.getStatus(),DictCodeListExt3.中债数据状态_待处理.getValue())) {
                List<PbcSpecificInvestorsHold001Dto> dtoList = new ArrayList<>();
                dtoList.add(dto);
                // 删除错误信息
                result = pbcSpecificInvestorsHold001Mapper.deleteErrorData(dtoList);
                dto.setUuid(IDGenerator.generate(36).toUpperCase());
                dto.setStatus(DictCodeListExt3.中债数据状态_有效.getValue());
                // 有效数据
                result += pbcSpecificInvestorsHold001Mapper.insertSelective(dto);
            } else if (StringUtil.equals(dto.getStatus(),DictCodeListExt3.中债数据状态_有效.getValue())){
                // 修改修改
                result += pbcSpecificInvestorsHold001Mapper.updateByPrimaryKeySelective(dto);
            }
        }
        return result;
    }

    /**
     * 删除数据
     *
     * @param dto 删除
     * @return 影响行数
     */
    public Integer delete(PbcSpecificInvestorsHold001Dto dto) {
        Integer result = 0;
        dto.setUpdateTime(new Date());
        dto.setUpdateUser(CommonUtil.getUserName());
        Map<String, List<PbcSpecificInvestorsHold001Dto>> dtoMap = dto.getSelectedItem().stream().collect(Collectors.groupingBy(item-> item.getStatus()));
        if (dtoMap.containsKey(DictCodeListExt3.中债数据状态_待处理.getValue())) {
            // 删除错误数据
            result += pbcSpecificInvestorsHold001Mapper.deleteErrorData(dtoMap.get(DictCodeListExt3.中债数据状态_待处理.getValue()));
        }
        if (dtoMap.containsKey(DictCodeListExt3.中债数据状态_有效.getValue())) {
            dto.setStatus(DictCodeListExt3.中债数据状态_删除.getValue());
            // 删除原有
            result += pbcSpecificInvestorsHold001Mapper.delete(dto, dtoMap.get(DictCodeListExt3.中债数据状态_有效.getValue()));
            // 更新删除状态
            result += pbcSpecificInvestorsHold001Mapper.updateByPrimaryKey(dto, dtoMap.get(DictCodeListExt3.中债数据状态_有效.getValue()));
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
        exportParam.config("/com/joyintech/tams/svreport/specificinvestorshold/conf/export_conf.xml")
                .select(() -> pbcSpecificInvestorsHold001Mapper.selectForPage(exportParam.getSelectParam()))
                .dataSource(DataSourceType.MASERT);
        super.buildExportParam(exportParam);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param param 导入
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(Map<String, Object> param) {
        String fileName = param.get("fileName").toString();
        AjaxResult webResult = new AjaxResult();
        try {
            if (fileName.indexOf("特定目的载体持有信息") == -1) {
                webResult.setMessage(this.getMessage("ImportFileName"), MessageType.错误消息);
                return webResult;
            }
            List<List<String>> hList = com.joyintech.tams.report.common.util.ExcelUtil.getTempList(fileName);
            List<ExcelValidDto> excelValidDtos = checkExcelTemp(hList, getHeadList());
            if (!StringUtil.isEmptyList(excelValidDtos)) {
                webResult.setMessage("导入模板不一致，请重新选择文件！", MessageType.错误消息);
                return webResult;
            }
            List<Map<String, String>> list = dataMapAnalysis(fileName);
            if (list.size() < 1) {
                webResult.setMessage("请勿上传空文件", MessageType.错误消息);
                return webResult;
            }
            Map<String, PbcSpecificInvestorsHold001Dto> dtoMap = this.selectAllData(list);
            // 导入 数据map
            Map<String, List<Map<String, String>>> dataMap = Maps.newHashMap();
            // 导入数据
            if (list != null && list.size() > 0) {
                // 将序号放入list中 int 需要 通过行号排序
                List<Integer> excelDbNumList = new ArrayList<>();
                List<Integer> excelImportNumList = new ArrayList<>();
                for (Map<String, String> stringMap : list) {
                    String key = stringMap.get("ACCOUNT") + GlobalConstant.UNDER_LINE + stringMap.get("INDENTIFY_MARK") + GlobalConstant.UNDER_LINE + stringMap.get("HOLD_DATE");
                    // 已存在 数据库数据
                    if (dtoMap != null && dtoMap.size() > 0){
                        if (dtoMap.containsKey(key)) {
                            excelDbNumList.add(Integer.parseInt(stringMap.get("ROW_NO")));
                        }
                    }
                    // 已存在 导入的重复数据
                    if (dataMap.containsKey(key)) {
                        List<Map<String, String>> tempList = dataMap.get(key);
                        tempList.add(stringMap);
                        // 保存重复的行号
                        excelImportNumList.add(Integer.parseInt(stringMap.get("ROW_NO")));
                        dataMap.put(key, tempList);
                    } else {
                        //
                        List<Map<String, String>> tempList = Lists.newArrayList();
                        tempList.add(stringMap);
                        dataMap.put(key, tempList);
                    }
                }
                // 行号排序
                Collections.sort(excelDbNumList);
                Collections.sort(excelImportNumList);
                if (CollectionUtils.isNotEmpty(excelDbNumList) && CollectionUtils.isNotEmpty(excelImportNumList)){
                    // 即 有 已存在 又有 重复数据
                    webResult.setMessage(
                            this.getMessage("PBC_TARGET_INFO_DuplicateMessage",
                                    StringUtil.joinString(excelDbNumList.stream().map(String::valueOf).collect(Collectors.toList()), GlobalConstant.COMMA),
                                    StringUtil.joinString(excelImportNumList.stream().map(String::valueOf).collect(Collectors.toList()), GlobalConstant.COMMA)),
                            MessageType.确认消息);
                } else if (CollectionUtils.isNotEmpty(excelDbNumList)){
                    // 只有 已存在 数据
                    webResult.setMessage(
                            this.getMessage("PBC_TARGET_INFO_ISEXIST",
                                    StringUtil.joinString(excelDbNumList.stream().map(String::valueOf).collect(Collectors.toList()), GlobalConstant.COMMA)),
                            MessageType.确认消息);
                } else if (CollectionUtils.isNotEmpty(excelImportNumList)){
                    // 只有 重复数据
                    webResult.setMessage(
                            this.getMessage("PBC_TARGET_INFO_ISCOVER",
                                    StringUtil.joinString(excelImportNumList.stream().map(String::valueOf).collect(Collectors.toList()), GlobalConstant.COMMA), excelImportNumList.size()),
                            MessageType.确认消息);
                } else {
                    // 防止在进行导入前check时进行了两次的弹框
                    webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
                }
            } else {
                // 防止在进行导入前check时进行了两次的弹框
                webResult.setData(ReportGlobalConstant.NO_INFO_SHOWOUT);
            }
        } catch (Exception e) {
            logger.info("导入错误", e.getMessage());
        }
        return webResult;
    }

    /**
     * 查询 已存在数据
     *
     * @param list 数据
     * @return 反馈 拼接好的 map 数据
     */
    public Map<String, PbcSpecificInvestorsHold001Dto> selectAllData(List<Map<String, String>> list) {
        // 已存在数据 重复map
        Map<String, PbcSpecificInvestorsHold001Dto> dtoMap = Maps.newHashMap();
        // 已存在数据 集合
        List<PbcSpecificInvestorsHold001Dto> dtoList = Lists.newArrayList();
        List<Map<String, Object>> checkDataList = list.stream().map(item -> {
            Map<String, Object> mapList = Maps.newHashMap();
            mapList.put("ACCOUNT", item.get("ACCOUNT"));
            mapList.put("INDENTIFY_MARK", item.get("INDENTIFY_MARK"));
            mapList.put("HOLD_DATE", item.get("HOLD_DATE"));
            return mapList;
        }).collect(Collectors.toList());
        int maxDataCountOneTime = 1000;
        int dataCount = checkDataList.size();
        if (dataCount <= maxDataCountOneTime) {
            dtoList = pbcSpecificInvestorsHold001Mapper.selectDataForAccountAndIndentifyMarkAndHoldDate(checkDataList);
        } else {
            List<Map<String, Object>> newKeyList = Lists.newArrayList();
            newKeyList.addAll(checkDataList);
            // 分批处理
            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++ ) {
                dtoList.addAll(pbcSpecificInvestorsHold001Mapper.selectDataForAccountAndIndentifyMarkAndHoldDate(newKeyList.subList(0, maxDataCountOneTime)));
                newKeyList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newKeyList)) {
                dtoList.addAll(pbcSpecificInvestorsHold001Mapper.selectDataForAccountAndIndentifyMarkAndHoldDate(newKeyList));
            }
        }
        // 有已存在数据 进行 产品代码 + 识别标识 + 持有日期 拼接 map
        if (CollectionUtils.isNotEmpty(dtoList)) {
            dtoMap = dtoList.stream().collect(Collectors.toMap(key -> key.getAccount() + GlobalConstant.UNDER_LINE + key.getIndentifyMark() + GlobalConstant.UNDER_LINE + DateUtil.format(key.getHoldDate()), value -> value));
        }
        return dtoMap;
    }

    /**
     * 导入数据文件
     *
     * @param param 导入
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(Map<String, Object> param) throws Exception {
        String fileName = param.get("fileName").toString();
        List<ExcelValidDto> listExcel = new ArrayList<>();
        List<Map<String, String>> list = dataMapAnalysis(fileName);
        List<Map<String, String>> mapList = Lists.newArrayList();
        // 导入 数据map
        Map<String, List<Map<String, String>>> dataMap = Maps.newHashMap();
        // 导入数据
        if (CollectionUtils.isNotEmpty(list)) {
            for (Map<String, String> stringMap : list) {
                String key = stringMap.get("ACCOUNT") + GlobalConstant.UNDER_LINE + stringMap.get("INDENTIFY_MARK") + GlobalConstant.UNDER_LINE + stringMap.get("HOLD_DATE");
                // 已存在 导入的重复数据
                if (dataMap.containsKey(key)) {
                    List<Map<String, String>> tempList = dataMap.get(key);
                    tempList.add(stringMap);
                    dataMap.put(key, tempList);
                } else {
                    //
                    List<Map<String, String>> tempList = Lists.newArrayList();
                    tempList.add(stringMap);
                    dataMap.put(key, tempList);
                }
            }
            for (Map.Entry<String, List<Map<String, String>>> entry : dataMap.entrySet()) {
                List<Map<String, String>> tempList = entry.getValue();
                // 导入 文件中不存在 重复数据
                if (tempList.size() == 1){
                    mapList.add(tempList.get(0));
                } else {
                    // 导入 文件存在重复数据 取最后最后一条
                    mapList.add(tempList.get(tempList.size() - 1));
                }
            }
            svrProdTargetFileImpService.importDB(listExcel, mapList, new InterfFile(), null);
        }
        return listExcel;
    }

    /**
     * 解析文件
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    private List<Map<String, String>> dataMapAnalysis(String fileName) throws FileNotFoundException {
        String prefix = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if (!StringUtil.startsWith(fileName, prefix)
                && !StringUtil.startsWith(fileName, prefix.replace("/", File.separator))) {
            fileName = prefix + File.separator + fileName;
        }
        com.joyintech.framework.util.ExcelUtil excelUtil = com.joyintech.framework.util.ExcelUtil.build(fileName, null);

        List<List<String>> dataList = excelUtil.readToList(0);
        List<String> headList = dataList.get(0);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tableId", "PBC_TARGET_INFO");
        param.put("flag", false);
        List<ChbInfoMgrResultConfig> chbInfoMgrResultConfigList = chbInfoMgrResultConfigMapper.queryCloumnMapping(param);
        Map<String, String> map = new HashMap<>();
        chbInfoMgrResultConfigList.forEach(ele -> map.put(ele.getColumnName().trim().replace("（", "(").replace("）", ")"), ele.getRealColumnId()));
        List<String> columnList = new ArrayList<>();
        // 解析出文件存在多余 空列 故在此 取导入固定下标
        for (int i = 0; i < 9; ++i) {
            String columnName = headList.get(i);
            String temp = columnName.trim().replace("（", "(").replace("）", ")");
            columnName = map.get(temp);
            if (StringUtils.isEmpty(columnName)) {
                System.out.println(" ");
            }
            columnList.add(i, columnName);
        }
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 1; i < dataList.size(); ++i) {
            Map<String, String> subMap = new HashMap<>();
            List<String> subList = dataList.get(i);
            for (int j = 0; j < 9; ++j) {
                if (StringUtil.isEmpty(subMap.get(columnList.get(j)))) {
                    subMap.put(columnList.get(j), subList.get(j));
                }
            }
            subMap.put("ROW_NO", String.valueOf(i));
            // 导入
            subMap.put("DATA_SOURCE", "IMPORT");
            subMap.remove(null);
            list.add(subMap);
        }
        for (ChbInfoMgrResultConfig config : chbInfoMgrResultConfigList) {
            if (!StringUtils.isEmpty(config.getResultdictCode())) {
                for (Map<String, String> m : list) {
                    if (m.get(config.getRealColumnId()) != null) {
                        m.put(config.getRealColumnId(), m.get(config.getRealColumnId()).split(" ")[0]);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 校验 文件中数据重复项
     *
     * @param list
     * @param duplicateKey
     * @return
     */
    public static int countDuplicatedData(List<Map<String, String>> list, String... duplicateKey) {
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
     * 获取 内部产品代码
     *
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getProdInsId(Integer page, Integer length, Map<String, Object> param) {
        return queryForPageForSelectReturn(page, length, () -> pbcSpecificInvestorsHold001Mapper.getProdInsId(param));
    }

    /**
     * 获取 人行产品代码
     *
     * @param param
     * @return
     */
    public Map<String, Object> getPbcProdIdByAccount(Map<String, Object> param) {
        return pbcSpecificInvestorsHold001Mapper.getPbcProdIdByAccount(param);
    }

    /**
     * 识别标识 与 投资者名称 校重
     *
     * @param param
     * @return
     */
    public Integer getIndentifyMarkAndHoldName(Map<String, Object> param) {
        return pbcSpecificInvestorsHold001Mapper.getIndentifyMarkAndHoldName(param);
    }

    /**
     * 获取 身份登记 识别标识
     *
     * @param param
     * @return
     */
    public List<SuggestDto> getIndentifyMark(Map<String, Object> param) {
        return pbcSpecificInvestorsHold001Mapper.getIndentifyMark(param);
    }

    /**
     * 获取 投资者 类别
     *
     * @param param
     * @return
     */
    public Map<String, Object> getHolderTypeByIndentifyMark(Map<String, Object> param) {
        return pbcSpecificInvestorsHold001Mapper.getHolderTypeByIndentifyMark(param);
    }

    /**
     * 错误信息表解析共通处理
     */
    public static <T> T errdataConvert(Class<T> clazz, String orgData) {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
        if ( StringUtil.isNotEmpty(orgData)){
            logger.info("调用ErrdataConvertUtil.errdataAssetInfoConvert，传入的orgData:" + orgData);
            Map<String, Object> dataMap = clobStrToMap(orgData);
            for (String column : dataMap.keySet()) {
                Object value = dataMap.get(column);
                if (!StringUtil.isNotEmpty(value)) {
                    continue;
                }
                if (StringUtil.equals(column, "CREATE_TIME") || StringUtil.equals(column, "UPDATE_TIME")){
                    continue;
                }
                //根据数据库字段获取类的属性字段
                Field field = ReflectionUtils.findField(clazz, subBlank(StringUtil.lowerCase(column)));
                //如果字段没有则跳过。
                if(field==null){
                    logger.warn("该字段在所转换的实体类中没有，对象名：{}，字段名：{}", clazz.getName(), column);
                    continue;
                }
                field.setAccessible(true);
                //获取属性对应类型
                Type type = field.getGenericType();
                try {
                    //对于BigDecimal类型处理
                    if ("java.math.BigDecimal".equals(type.getTypeName())) {
                        //对类的实例字段进行赋值
                        field.set(t, new BigDecimal(value.toString()));
                        //对于Integer类型处理
                    } else if ("java.lang.Integer".equals(type.getTypeName()) || "int".equals(type.getTypeName())) {
                        //对类的实例字段进行赋值
                        field.set(t, Integer.valueOf(value.toString()));
                        //对于Long类型处理
                    } else if ("java.lang.Long".equals(type.getTypeName())) {
                        //对类的实例字段进行赋值
                        field.set(t, Long.valueOf(value.toString()));
                        //对于Short类型处理
                    } else if ("java.lang.Short".equals(type.getTypeName())) {
                        //对类的实例字段进行赋值
                        field.set(t, Short.valueOf(value.toString()));
                        //对于Date类型处理
                    } else if ("java.util.Date".equals(type.getTypeName())) {
                        //对类的实例字段进行赋值
                        field.set(t, com.joyintech.tams.report.interf.common.constant.DateUtil.parse(value.toString()));
                    } else {
                        //其它类型处理
                        //对类的实例字段进行赋值
                        field.set(t, value);
                    }
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage(),e);
                    continue;
                }catch (Exception e) {
                    logger.error(e.getMessage(),e);
                    continue;
                }
            }
        }

        return t;
    }

    /**
     *  clob字段解析方法
     * @param orgData 待解析数据
     * @return Map<key,value>对象
     */
    private static Map<String, Object> clobStrToMap(String orgData){
        return JsonUtils.stringToMap(orgData);
    }

    /**
     * 功能:将表字段转换成属性字段
     *
     * @since
     * @version
     * @author：
     * @param str
     * @return String
     */
    private static String subBlank(String str) {
        if (str == null) {
            return null;
        }
        String[] strarray = str.split("_");
        if (strarray.length == 0) {
            return null;
        }
        String result = strarray[0];
        for (int i = 1; i < strarray.length; i++)
            result = result + (new StringBuilder()).append(Character.toUpperCase(strarray[i].charAt(0)))
                    .append(strarray[i].substring(1)).toString();
        return result;
    }

    /**
     * 导入文件头配置
     *
     * @return 文件列
     */
    private List<String> getHeadList() {
        List<String> headList = com.google.common.collect.Lists.newArrayList();
        headList.add("内部产品代码");
        headList.add("人行产品代码");
        headList.add("识别标识");
        headList.add("投资者名称");
        headList.add("投资者类别");
        headList.add("持有日期");
        headList.add("币种");
        headList.add("持有份额");
        headList.add("持有金额");
        return headList;
    }

    public List<ExcelValidDto> checkExcelTemp(List<List<String>> tempDataList, List<String> headList) {
        List<ExcelValidDto> tempCheckResult = new ArrayList<>();
        String errorMsg = "导入模板不一致，请重新选择文件！";
        ExcelValidDto excelValidDto = new ExcelValidDto();
        excelValidDto.setErrorInfo(errorMsg);
        tempCheckResult.add(excelValidDto);


        if (StringUtil.isEmptyList(tempDataList)) {
            // Excel没有有效数据
            return tempCheckResult;
        }
        List<String> excelHeadList = tempDataList.get(0);
        if (excelHeadList.size() != headList.size()) {
            // Excel中列数少于要求列数
            return tempCheckResult;
        }

        for (int i = 0; i < headList.size(); i++ ) {
            if (!excelHeadList.get(i).contains(headList.get(i))) {
                // Excel中列不一致
                return tempCheckResult;
            }
        }
        return new ArrayList<>();
    }
}
