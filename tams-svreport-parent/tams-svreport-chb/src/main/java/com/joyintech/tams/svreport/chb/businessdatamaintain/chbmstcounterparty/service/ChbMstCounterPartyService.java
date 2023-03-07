package com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.service;


import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.ChbMstCounterPartyErrodataMapper;
import com.joyintech.tams.common.base.entity.ChbInfoMgrResultConfig;
import com.joyintech.tams.common.base.entity.ChbMstCounterPartyErrodataWithBLOBs;
import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dao.ChbInfoMgrResultConfigMapper;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.dao.ChbMstCounterPartyDao;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.dto.ChbMstCounterPartyDto;
import com.joyintech.tams.svreport.chb.common.util.DuplicateDataUtil;
import com.joyintech.tams.svreport.interf.chb.investtrade.service.SvrMstCounterPartFileImpService;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cff
 * @date 2022-04-27 14:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbMstCounterPartyService extends CommonExportService<ChbMstCounterPartyDto> implements BaseProcessService<ChbMstCounterPartyDto> {
    @Autowired
    private ChbMstCounterPartyDao chbMstCounterPartyDao;
    @Autowired
    private ChbInfoMgrResultConfigMapper chbInfoMgrResultConfigMapper;
    @Autowired
    private ChbMstCounterPartyErrodataMapper chbMstCounterPartyErrodataMapper;
    @Autowired
    private BaseCommonCheckService baseCommonCheckService;
    @Autowired
    private SvrMstCounterPartFileImpService svrMstCounterPartFileImpService;
    @Override
    public void doComplete(ChbMstCounterPartyDto entity, OperationType type) {
        if (OperationType.DELETE.equals(type)) {
            if("INFO".equals(entity.getInfo())){
                entity.setStatus(DictCodeListExt1.维护数据状态_删除.getValue());
                chbMstCounterPartyDao.update(entity);
            }else{
                chbMstCounterPartyDao.delete(entity);
            }
        }
        else if (OperationType.NEW.equals(type)) {
            entity.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            entity.setUuid(IDGenerator.generate(36));
            if(entity.getCounterType2()!=null){
                entity.setCounterType1(entity.getCounterType2().substring(0,2));
            }
            if("01".equals(entity.getCounterType())){
                entity.setCounterName(entity.getCounterName1());
            }else if("05".equals(entity.getCounterType())){
                entity.setCounterName(entity.getCounterName5());
            }
            chbMstCounterPartyDao.insert(entity);
            chbMstCounterPartyErrodataMapper.deleteByPrimaryKey(entity.getCounterId());

        }
        else if(OperationType.MODIFY.equals(type)){
                if(entity.getCounterType2()!=null){
                    entity.setCounterType1(entity.getCounterType2().substring(0,2));
                }
                if("01".equals(entity.getCounterType())){
                    entity.setCounterName(entity.getCounterName1());
                }else if("05".equals(entity.getCounterType())){
                    entity.setCounterName(entity.getCounterName5());
                }
                //如果是info表直接更新，error表插入info表，并删除原error数据
            if("INFO".equals(entity.getInfo())){
                chbMstCounterPartyDao.updateDto(entity);
            }else{
                entity.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
                chbMstCounterPartyDao.insert(entity);
                chbMstCounterPartyDao.delete(entity);
            }

        }

    }
    public Map<String,String> selectCounterIdByCounterName(String counterName) {
        return chbMstCounterPartyDao.selectCounterIdByCounterName(counterName);
    }

    public Page<SelectOptionDto> getCustomerName(Map<String, Object> param) {
        return queryForPageForSelectReturn(Integer.parseInt(null == param.get("page")?"1":(String) param.get("page")),
                Integer.parseInt(null == (String) param.get("length")?"1":(String) param.get("length")),()->chbMstCounterPartyDao.getCustomerName(param));
    }
    public Page<SelectOptionDto> getCounterName(Map<String, Object> param) {

        return queryForPageForSelectReturn(Integer.parseInt(null == param.get("page")?"1":(String) param.get("page")), Integer.parseInt(null == (String) param.get("length")?"1":(String) param.get("length")),()->chbMstCounterPartyDao.getCounterName(param));
    }

    public Map<String,String> getCustomerName1(Map<String, Object> param){
        Map<String,String> resultMap = new HashMap<>();

        List<SelectOptionDto> optionDtos = chbMstCounterPartyDao.getCustomerName(param);
        for(SelectOptionDto dto : optionDtos){
            resultMap.put(dto.getId(),dto.getText());
        }
        return resultMap;
    }

    public Page<ChbMstCounterPartyDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("counterTypes", StringUtil.isNotEmpty(pageParam.get("counterType"))?(pageParam.get("counterType").toString().split(",")):null);
        pageParam.put("counterNames", StringUtil.isNotEmpty(pageParam.get("counterName"))?(pageParam.get("counterName").toString().split(",")):null);
        pageParam.put("counterType2s", StringUtil.isNotEmpty(pageParam.get("counterType2"))?(pageParam.get("counterType2").toString().split(",")):null);
        pageParam.put("statusS", StringUtil.isNotEmpty(pageParam.get("status"))?(pageParam.get("status").toString().split(",")):null);

        return queryForPage(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), () -> chbMstCounterPartyDao.selectAll(pageParam));
    }
    public ChbMstCounterPartyDto selectByKey(String uuid) {
        return chbMstCounterPartyDao.selectByKey(uuid);
    }

    public ChbMstCounterPartyErrodataWithBLOBs selectErrorByKey(String uuid) {
        return chbMstCounterPartyDao.selectErrorByKey(uuid);
    }
    /**
     * 下拉框分页获取产品
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getRivalPartyName(Map<String, Object> param) {
        return chbMstCounterPartyDao.getRivalPartyName(param);
    }

    /**
     * 下拉框分页获取产品
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodId(Map<String, Object> param) {
        return chbMstCounterPartyDao.getFinprodId(param);
    }

    public List<ChbMstCounterPartyDto> selectAll(Map<String, Object> params) {
        List<ChbMstCounterPartyDto> list = chbMstCounterPartyDao.selectAll(params);
        return list;
    }

    public Map<String, String> selectRivalPartyType(String counterName) {
        return chbMstCounterPartyDao.selectRivalPartyType(counterName);
    }


    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName, String tableName) throws Exception {
        AjaxResult webResult = new AjaxResult();
        if (fileName.indexOf("交易对手") == -1){
            webResult.setMessage(this.getMessage("ImportFileName"), MessageType.错误消息);
            return webResult;
        }
        List<Map<String, String>> list = dataMapAnalysis(fileName);
        if(list.size()<1){
            webResult.setMessage("请勿上传空文件", MessageType.错误消息);
            return webResult;
        }
        int i = chbMstCounterPartyDao.countDuplicatedData(list);
        if(i>0){
            String[] checkEuplicatedKeys = ChbCheckTableInfo.交易对手表INFO.getCheckEuplicatedKeys();
            int dup = DuplicateDataUtil.countDuplicatedData(list, checkEuplicatedKeys);
            webResult.setMessage(this.getMessage("SvrDuplicateMessage",dup,i), MessageType.确认消息);
        }
        return webResult;
    }

    public List<ExcelValidDto> importData(String fileName,String tableName) throws Exception{
        List<Map<String, String>> list = dataMapAnalysis(fileName);
        List<ExcelValidDto> listExcel = new ArrayList<ExcelValidDto>();
        svrMstCounterPartFileImpService.importDB(list,new InterfFile(),null);
        return listExcel;

    }

    /**
     * excel导入数据整理
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<Map<String,String>> dataMapAnalysis(String flieName,String tableName)
            throws Exception {
        String prefix = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if(!StringUtil.startsWith(flieName, prefix)
                && !StringUtil.startsWith(flieName, prefix.replace("/", File.separator))) {
            flieName = prefix + File.separator + flieName;
        }
        com.joyintech.framework.util.ExcelUtil excelUtil = com.joyintech.framework.util.ExcelUtil.build(flieName, null);

        List<List<String>> dataList = excelUtil.readToList(0);
        List<String> headList = dataList.get(0);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tableId", tableName);
        param.put("flag", false);
       // List<ChbInfoMgrResultConfig> chbInfoMgrResultConfigList = chbInfoMgrResultConfigMapper.queryAllchbMgrTableInfo(param);
        Map<String,String> map = new HashMap<>();
        map.put("交易对手类别","COUNTER_TYPE");
        map.put("交易对手代码","COUNTER_ID");
        map.put("交易对手名称","COUNTER_NAME");
        map.put("交易对手类型","COUNTER_TYPE2");
        map.put("备注","REMARK");
        //chbInfoMgrResultConfigList.forEach(ele->map.put(ele.getColumnName().trim().replace("（","(").replace("）",")"),ele.getColumnId()));
        List<String> columnList = new ArrayList<>();
        for(int i=0;i<headList.size();++i){
            String columnName = headList.get(i);
            String temp = columnName.trim().replace("（","(").replace("）",")");
            columnName= map.get(temp);
            columnList.add(i,columnName);
        }
        List<Map<String,String>> list = new ArrayList<>();

        for(int i=1;i<dataList.size();++i){
            Map<String,String> subMap = new HashMap<>();
            List<String> subList = dataList.get(i);
            for(int j=0;j<subList.size();++j){
                if(StringUtil.isEmpty(subMap.get(columnList.get(j)))){
                    subMap.put(columnList.get(j),subList.get(j));
                }
            }
            subMap.put("UUID",IDGenerator.generate(36));
            subMap.put("COUNTER_TYPE",StringUtil.isEmpty(subMap.get("COUNTER_TYPE"))?null:subMap.get("COUNTER_TYPE").split(" ")[0]);
            subMap.put("COUNTER_ID",StringUtil.isEmpty(subMap.get("COUNTER_ID"))?null:subMap.get("COUNTER_ID").split(" ")[0]);
            subMap.put("COUNTER_NAME",StringUtil.isEmpty(subMap.get("COUNTER_NAME"))?null:subMap.get("COUNTER_NAME").split(" ")[0]);
            subMap.put("COUNTER_TYPE2",StringUtil.isEmpty(subMap.get("COUNTER_TYPE2"))?null:subMap.get("COUNTER_TYPE2").split(" ")[0]);
            subMap.put("REMARK",StringUtil.isEmpty(subMap.get("REMARK"))?null:subMap.get("REMARK").split(" ")[0]);
            list.add(subMap);
        }


        return list;
    }
    public List<ChbMstCounterPartyDto> converToBean(List<Map<String,String>> dataList)
            throws Exception {
        // 返回的list定义
        List<ChbMstCounterPartyDto> chbMstCounterPartyDtoList = new ArrayList<ChbMstCounterPartyDto>();
        Class<ChbMstCounterPartyDto> clazz = ChbMstCounterPartyDto.class;
        Map<String, Method> toMap = methodToMap(clazz);
        Map<String, String> filedToMap = filedToMap(clazz);
        Object var = null;
        for (Map<String, String> map : dataList) {
            Map<String,String> temp = new HashMap<>();
            Set<String> keys = map.keySet();
            for(String key :keys){
                if(null !=key){
                    temp.put(key.toLowerCase().replace("_", ""),map.get(key));
                }
            }

            ChbMstCounterPartyDto chbMstCounterPartyDto = clazz.newInstance();
            for(Map.Entry e:temp.entrySet()){
                for(Map.Entry el:toMap.entrySet()){
                    if(null !=e.getKey()){
                        if(((String)el.getKey()).substring(3).equalsIgnoreCase((String) e.getKey())){
                            if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(String.class.getName())){
                                var =StringUtil.isEmpty((String)e.getValue())?"":(String)e.getValue();
                            }else if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(Date.class.getName())){
                                var = StringUtil.isEmpty((String)e.getValue())?null: DateUtil.parse((String) e.getValue());
                            }else if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(Long.class.getName())){
                                var = StringUtil.isEmpty((String)e.getValue())?null:Long.valueOf((String) e.getValue()) ;
                            }else if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(Integer.class.getName())){
                                var = StringUtil.isEmpty((String)e.getValue())?null:Integer.valueOf((String) e.getValue()) ;
                            }else if(null !=filedToMap.get(e.getKey())&&filedToMap.get(e.getKey()).equals(BigDecimal.class.getName())){
                                var = StringUtil.isEmpty((String)e.getValue())?null:new BigDecimal((String) e.getValue());
                            }
                            ((Method)el.getValue()).invoke(chbMstCounterPartyDto,var);
                            break;
                        }
                    }
                }
            }
           // chbMstCounterPartyDto.setRemark(chbMstCounterPartyDto.getRemark()==null?"":chbMstCounterPartyDto.getRemark());
            chbMstCounterPartyDto.setErrMsg(null!=map.get("ERRMSG")?map.get("ERRMSG"):"");
            chbMstCounterPartyDto.setCreateUser(CommonUtil.getUserName());
            chbMstCounterPartyDto.setCreateDept(CommonUtil.getDeptCode()==null?"":CommonUtil.getDeptCode());
            chbMstCounterPartyDto.setCreateTime(DateUtil.now());
            chbMstCounterPartyDto.setUpdateUser(CommonUtil.getUserName());
            chbMstCounterPartyDto.setUpdateTime(DateUtil.now());
            chbMstCounterPartyDto.setDataSource(DictCodeListExt2.数据来源_新建导入.getValue());
            chbMstCounterPartyDto.setStatus(null!=map.get("STATUS") && map.get("STATUS").equals("A")? DictCodeListExt1.维护数据状态_待处理.getValue():DictCodeListExt1.维护数据状态_有效.getValue());
            chbMstCounterPartyDtoList.add(chbMstCounterPartyDto);
        }
        return  chbMstCounterPartyDtoList;
    }
    // 获取类的set方法
    public Map<String,Method> methodToMap(Class clazz){
        return Arrays.stream(clazz.getMethods()).filter(m -> m.getName().startsWith("set")).collect(Collectors.toMap(m -> m.getName(), m -> m));
    }
    // 获取类的属性类型
    public Map<String,String> filedToMap(Class clazz){
        Map<String, String> var1 = Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toMap(f -> f.getName().toLowerCase(), f -> f.getGenericType().getTypeName()));
        Map<String, String> var2 = Arrays.stream(clazz.getSuperclass().getDeclaredFields()).collect(Collectors.toMap(f -> f.getName().toLowerCase(), f -> f.getGenericType().getTypeName()));
        var2.putAll(var1);
        return var2;
    }
    private List<Map<String, String>> dataMapAnalysis(String fileName) throws FileNotFoundException {
        String prefix = CacheUtil.getSysParam(GlobalConstant.COM_PROP_UPLOAD_PATH);
        if(!StringUtil.startsWith(fileName, prefix)
                && !StringUtil.startsWith(fileName, prefix.replace("/", File.separator))) {
            fileName = prefix + File.separator + fileName;
        }
        com.joyintech.framework.util.ExcelUtil excelUtil = com.joyintech.framework.util.ExcelUtil.build(fileName, null);

        List<List<String>> dataList = excelUtil.readToList(0);
        List<String> headList = dataList.get(0);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tableId", "CHB_MST_COUNTER_PARTY");
        param.put("flag", false);
        List<ChbInfoMgrResultConfig> chbInfoMgrResultConfigList = chbInfoMgrResultConfigMapper.queryCloumnMapping(param);
        Map<String,String> map = new HashMap<>();
        chbInfoMgrResultConfigList.forEach(ele->map.put(ele.getColumnName().trim().replace("（","(").replace("）",")"),ele.getRealColumnId()));
        List<String> columnList = new ArrayList<>();
        for(int i=0;i<headList.size();++i){
            String columnName = headList.get(i);
            String temp = columnName.trim().replace("（","(").replace("）",")");
            columnName= map.get(temp);
            if(StringUtils.isEmpty(columnName)){
                System.out.println(" ");
            }
            columnList.add(i,columnName);
        }
        List<Map<String,String>> list = new ArrayList<>();
        for(int i=1;i<dataList.size();++i){
            Map<String,String> subMap = new HashMap<>();
            List<String> subList = dataList.get(i);
            for(int j=0;j<subList.size();++j){
                if(StringUtil.isEmpty(subMap.get(columnList.get(j)))){
                    subMap.put(columnList.get(j),subList.get(j));
                }
            }
            //COUNTER_TYPE 默认塞值.不处理
            subMap.put("COUNTER_TYPE","02");
            list.add(subMap);
        }
        for (ChbInfoMgrResultConfig config : chbInfoMgrResultConfigList) {
            if(!StringUtils.isEmpty(config.getResultdictCode())){
                for (Map<String, String> m : list) {
                    if(m.get(config.getRealColumnId())!=null){
                        m.put(config.getRealColumnId(),m.get(config.getRealColumnId()).split(" ")[0]);
                    }
                }
            }
        }
        return list;
    }

    public List<ChbMstCounterPartyDto> selectBykeys(List<String> uuid) {
        return chbMstCounterPartyDao.selectBykeys(uuid);
    }
}
