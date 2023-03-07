package com.joyintech.tams.svreport.fininstitution.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import com.joyintech.tams.common.svreport.datacheck.dto.CheckRule;
import com.joyintech.tams.common.svreport.datacheck.service.DataCheckService;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.svreport.fininstitution.dao.PbcFinInstitutionAssetInfo001Mapper;
import com.joyintech.tams.svreport.fininstitution.dto.PbcFinInstitutionAssetInfo001Dto;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 表内资产数据维护
 *
 * @author yangxianjiu
 * @date 2022/11/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PbcFinInstitutionAssetInfo001Service extends CommonExportService<PbcFinInstitutionAssetInfo001Dto> {

    public static final String TABLE_NAME = "PBC_FIN_INSTITUTION_ASSET_INFO";

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataCheckService dataCheckService;

    @Autowired
    private PbcFinInstitutionAssetInfo001Mapper pbcFinInstitutionAssetInfo001Mapper;

    /**
     * 分页
     * @param page
     * @param length
     * @param pageParam
     * @return
     */
    public Page<PbcFinInstitutionAssetInfo001Dto> searchPage(Integer page, Integer length, Map<String, Object> pageParam) {
        return super.queryForPage(page, length, ()->pbcFinInstitutionAssetInfo001Mapper.searchPage(pageParam));
    }

    /**
     * 双击详情
     *
     * @param paramMap
     * @return
     */
    public PbcFinInstitutionAssetInfo001Dto selectDetail(Map<String, Object> paramMap) {
        return pbcFinInstitutionAssetInfo001Mapper.selectDetail(paramMap);
    }

    /**
     * 删除
     * @param dto
     * @return
     */
    public Integer delete(PbcFinInstitutionAssetInfo001Dto dto) {
        return pbcFinInstitutionAssetInfo001Mapper.deleteByPrimaryKey(dto.getSelectedItem());
    }

    /**
     * 保存
     * @param dto
     * @return
     */
    public Integer save(PbcFinInstitutionAssetInfo001Dto dto) {
        Integer result = 0;
        if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, dto.getPageModel())) {
            dto.setPbcUuid(IDGenerator.generate(36).toUpperCase());
            result = pbcFinInstitutionAssetInfo001Mapper.insertSelective(dto);
        } else if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, dto.getPageModel())) {
            result = pbcFinInstitutionAssetInfo001Mapper.updateByPrimaryKeySelective(dto);
        }
        return result;
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/svreport/fininstitution/conf/export_conf.xml")
                .select(() -> pbcFinInstitutionAssetInfo001Mapper.searchPage(exportParam.getSelectParam()));
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
            if (fileName.indexOf("表内资产数据维护") == -1) {
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
            List<Map<String, String>> mapList = listToMap(dataList);
            // 文件
            if (mapList.size() < 1) {
                webResult.setMessage("请勿上传空文件", MessageType.错误消息);
                return webResult;
            }
            // 校验数据库重复
            int result = pbcFinInstitutionAssetInfo001Mapper.checkDuplicatedData(mapList);
            if (result > 0) {
                // 校验导入文件重复
                int dup = DataCheckService.checkDuplicatedData(mapList, new String[]{"TERM_ID"}).size();
                webResult.setMessage(this.getMessage("DuplicateMessage", dup, result), MessageType.确认消息);
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
            if (checkResult == null || StringUtil.isEmptyList(checkResult)){
                checkResult = new ArrayList<>();
            }
            List<Map<String, String>> mapList = listToMap(dataList);
            // 数据检查
            List<CheckRule> checkRules = dataCheckService.queryCheckRules(TABLE_NAME);
            List<Map<String, String>> checkDataList = DataCheckService.checkDataList(mapList, checkRules);
            // 拼接错误信息
            for (Map<String, String> map : checkDataList) {
                if (map.containsKey("ERRMSG")) {
                    checkResult.add(new ExcelValidDto(map.get("TERM_ID"), map.get("ERRMSG_ROW"), map.get("ERRMSG")));
                }
            }
            if (checkResult != null && !StringUtil.isEmptyList(checkResult)) {
                return checkResult;
            }
            // 数据检查通过 补充其余字段
            mapList.stream().forEach(item -> {
                item.put("PBC_UUID", IDGenerator.generate(36).toUpperCase());
                item.put("CREATE_USER", CommonUtil.getUserName());
                item.put("CREATE_TIME", DateUtil.format(new Date()));
                item.put("UPDATE_USER", CommonUtil.getUserName());
                item.put("UPDATE_TIME", DateUtil.format(new Date()));
                item.put("CREATE_DEPT", CommonUtil.getDeptCode());
            });
            // 过滤重复key
            Map<String, Map<String, String>> termIdList = mapList.stream().collect(Collectors.toMap(item -> item.get("TERM_ID"), value -> value, (k1, k2) -> k2));
            mapList.clear();
            mapList.addAll(termIdList.values());
            // map 转 entity
            List<PbcFinInstitutionAssetInfo001Dto> dtoList = SvrMaptoEntityUtil.convertMapToEntity(mapList, getListCloumn(), PbcFinInstitutionAssetInfo001Dto.class);
            if(dtoList != null && dtoList.size()>0){
                for(PbcFinInstitutionAssetInfo001Dto pbcFinInstitutionAssetInfo001Dto : dtoList){
                    if(pbcFinInstitutionAssetInfo001Dto.getFinAssetBalance() != null){
                        pbcFinInstitutionAssetInfo001Dto.setFinAssetBalance(pbcFinInstitutionAssetInfo001Dto.getFinAssetBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    if(pbcFinInstitutionAssetInfo001Dto.getFinInsAssetBalance() != null){
                        pbcFinInstitutionAssetInfo001Dto.setFinInsAssetBalance(pbcFinInstitutionAssetInfo001Dto.getFinInsAssetBalance().setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                }
            }

            // 插入数据
            if (null != dtoList) {
                insertDataPart(dtoList);
            }
            return checkResult;
        }
        return null;
    }

    /**
     * 数据 批次入库
     *
     * @param dataList
     */
    private void insertDataPart(List<PbcFinInstitutionAssetInfo001Dto> dataList) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            int maxDataCountOneTime = 500;
            int dataCount = dataList.size();
            if (dataCount <= maxDataCountOneTime) {
                pbcFinInstitutionAssetInfo001Mapper.mergeInfoList(dataList);
                return;
            }
            List<PbcFinInstitutionAssetInfo001Dto> newDataList = new ArrayList<>();
            newDataList.addAll(dataList);
            // 分批处理
            int part = dataCount / maxDataCountOneTime; // 分批数
            for (int i = 0; i < part; i++) {
                pbcFinInstitutionAssetInfo001Mapper.mergeInfoList(newDataList.subList(0, maxDataCountOneTime));
                newDataList.subList(0, maxDataCountOneTime).clear();
            }
            // 最后剩下的数据
            if (CollectionUtils.isNotEmpty(newDataList)) {
                pbcFinInstitutionAssetInfo001Mapper.mergeInfoList(newDataList);
            }
        }
    }

    /**
     * 解析文件为 Map
     *
     * @param dataList
     * @return
     */
    public List<Map<String, String>> listToMap(List<List<String>> dataList) {
        String[] fieldName = {"TERM_ID", "FIN_ASSET_BALANCE", "FIN_INS_ASSET_BALANCE"};
        List<Map<String, String>> tempList = Lists.newArrayList();
        for (List<String> list : dataList) {
            HashMap<String, String> map = Maps.newHashMap();
            map.put(fieldName[0], list.get(0) == null ? "" : list.get(0));
            map.put(fieldName[1], list.get(1) == null ? "" : list.get(1));
            map.put(fieldName[2], list.get(2) == null ? "" : list.get(2));
            tempList.add(map);
        }
        return tempList;
    }

    /**
     * 导入文件头配置
     *
     * @return 文件列
     */
    private List<String> getHeadList() {
        List<String> headList = Lists.newArrayList();
        headList.add("统计年月");
        headList.add("金融机构表内资产余额（本外币）");
        headList.add("金融机构表内金融资产余额（本外币）");
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
        listCloumn.add("TERM_ID");
        listCloumn.add("FIN_ASSET_BALANCE");
        listCloumn.add("FIN_INS_ASSET_BALANCE");
        listCloumn.add("CREATE_USER");
        listCloumn.add("CREATE_TIME");
        listCloumn.add("UPDATE_USER");
        listCloumn.add("UPDATE_TIME");
        listCloumn.add("CREATE_DEPT");
        return listCloumn;
    }


}
