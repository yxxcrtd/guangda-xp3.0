package com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.data.service.PbcClassicsDataFileHelper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dao.ZG08SpeCounterQuityMapper;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service(value = "zG08SpeCounterQuityService")
@Transactional(rollbackFor = Exception.class)
public class ZG08SpecCounterQuity001Service extends CommonExportService<ZG08SpeCounterQuityDto> {

    @Autowired
    private ZG08SpeCounterQuityMapper zg08SpeCounterQuityMapper;

    /**
     * 日志.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PbcDataCheckUtil pbcDataCheckUtil;
    /**
     * 数据检查
     * @param checkKeys 检查数据的关键字
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public List<ExcelValidDto> checkData(Map<String, Object> param){

        List<ZG08SpeCounterQuityDto> equityInfoRegistList = new ArrayList<ZG08SpeCounterQuityDto>();
        param.put("submitStatus", DictCodeListExt2.报送状态_未报送.getValue());
        param.put("deptCode", CommonUtil.getDeptCode());
        List<String> keyList = new ArrayList<String>();
        if(param.get("checkKeys")!=null){
//            keyList= com.joyintech.framework.util.StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
            param.put("termId", param.get("checkKeys"));
        }
        Map<String, PbcPrdBaseinfoSub> repmap = new HashMap<String, PbcPrdBaseinfoSub>();
        deaSearchParam(param);
        equityInfoRegistList= zg08SpeCounterQuityMapper.searchPage(param);
        String uuid = IDGenerator.generate(36);
        //公共校验方法

        return pbcDataCheckUtil.getVailDateFromPage(equityInfoRegistList,uuid,DictCodeListExt2.采集平台特定目的载体交易对手明细信息.getValue()
                ,true,DictCodeListExt2.数据校验规则大类_报送前检查.getValue(),ZG08SpeCounterQuityDto.class);
    }

    /**
     * 页面查询
     *
     * @param searchParam
     * @return
     */
    public Page<ZG08SpeCounterQuityDto> searchPage(Map<String, Object> searchParam) {
        deaSearchParam(searchParam);
        int pagenum = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_PAGE), 1);
        int length = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
        Page<ZG08SpeCounterQuityDto> page = queryForPage(pagenum,length,()->zg08SpeCounterQuityMapper.searchPage(searchParam));
        //dealResult(page);
        return page;
    }

    /**
     * 查询参数处理
     * @param searchParam
     */
    private void deaSearchParam(Map<String, Object> searchParam) {
        if(searchParam.containsKey("keyList")){
            searchParam.put("keyList",
                    StringUtil.splitToList(",",(String) searchParam.get("keyList")));
        }
        if (StringUtil.isNotEmpty(searchParam.get("assetType"))){
            searchParam.put("assetTypeList",
                    StringUtil.splitToList(",",(String) searchParam.get("assetType")));
        } else {
            searchParam.put("assetTypeList",null);
        }
        if (StringUtil.isNotEmpty(searchParam.get("sendStatus"))){
            searchParam.put("sendStatusList",
                    StringUtil.splitToList(",",(String) searchParam.get("sendStatus")));
        } else {
            searchParam.put("sendStatusList",null);
        }
        if (StringUtil.isNotEmpty(searchParam.get("pbcProdId") )){
            searchParam.put("pbcProdIdList",
                    StringUtil.splitToList(",",(String) searchParam.get("pbcProdId")));
        } else {
            searchParam.put("pbcProdIdList",null);
        }
        if (StringUtil.isNotEmpty(searchParam.get("termId"))){
            searchParam.put("termId",searchParam.get("termId").toString().replaceAll("-",""));
        }
        if (StringUtil.isNotEmpty(searchParam.get("termIdList"))){
            searchParam.put("termIdList",
                    StringUtil.splitToList(",",(String) searchParam.get("termIdList")));
        }
    }

    /**
     * 手工处理
     * @param updateList
     */
    @SuppressWarnings("unchecked")
    public void doManul(Map<String,Object> map) {
        logger.debug("手工处理开始:------------------------------"+new Date());
        String flag = map.get("flag").toString();
        List<ZG08SpeCounterQuityDto> infoList = new ArrayList<>();
        if("01".equals(flag)){
            // 选中的手工处理
            try {
                infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")),ZG08SpeCounterQuityDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                logger.debug("数据转换异常",e.getMessage(),e);
                logger.error(e.getMessage(),e);
                return ;
            }
            //  RHPTSJ-139
            List<String> termList = new ArrayList<>();
            infoList.forEach(item -> {
                termList.add(item.getTermid());
            });
            Map<String,Object> pageParam = new HashMap<>();
            pageParam.put("termIdList",termList);
            pageParam.put("sendStatusList",StringUtil.splitToList(",","01,"));
            infoList = zg08SpeCounterQuityMapper.searchPage(pageParam);
            //  RHPTSJ-139
        }else{
            Map<String,Object> pageParam = new HashMap<>();
            pageParam.putAll(map);
            deaSearchParam(pageParam);
            infoList = zg08SpeCounterQuityMapper.searchPage(pageParam);
        }
        // His插入
        Iterator<ZG08SpeCounterQuityDto> it = infoList.iterator();
        while(it.hasNext()){
            ZG08SpeCounterQuityDto info = it.next();
            //if(SysDataUtil.getNameFromDataDict(DictCode.报送状态, "03").equals(info.getSendStatus())){
            if(StringUtils.equals(info.getSendStatus(),"03")){
                it.remove();
            }
        }
        if(infoList.isEmpty()){
            return ;
        }
        Map<String,List<String>> datuuidMap = infoList.stream().map(ZG08SpeCounterQuityDto::getDatuuid).filter(StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
        List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());
        /* 手工处理数据批量进行 update by gaojian 2019-06-22 begin */
        int grps = infoList.size()/ PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        int idx = 0;
        for ( int i=0;i<grps;i++ ) {
            List<ZG08SpeCounterQuityDto> list1 = infoList.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
            if ( list1.size()>0 ) {
                // 历史表插入
                //zg08SpeCounterQuityMapper.insertHisBatchBySendStatus(list1);
                // 原始表删除
                //zg08SpeCounterQuityMapper.deleteBatchBySendStatus(list1);
                // 原始表更新状态
                zg08SpeCounterQuityMapper.doManul(list1);
            }
            idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        }
        List<ZG08SpeCounterQuityDto> list2 = infoList.subList(idx, infoList.size());
        if ( list2.size()>0 ) {
            // 历史表插入
            //zg08SpeCounterQuityMapper.insertHisBatchBySendStatus(list2);
            // 原始表删除
            //zg08SpeCounterQuityMapper.deleteBatchBySendStatus(list2);
            // 原始表更新状态
            zg08SpeCounterQuityMapper.doManul(list2);
        }
        // 更新DAT报送状态
        if (CollectionUtils.isNotEmpty(datuuidList)){
            zg08SpeCounterQuityMapper.doDatReportStatus(datuuidList);
        }
        /* 手工处理数据批量进行 update by gaojian 2019-06-22 end */
        logger.debug("手工处理结束:------------------------------"+new Date());
    }

    /**
     * 取消手工处理
     * @param updateList
     */
    @SuppressWarnings("unchecked")
    public String doCancelManul(Map<String,Object> map) {
        logger.debug("取消手工处理开始:------------------------------"+new Date());
        String flag = map.get("flag").toString();
        List<ZG08SpeCounterQuityDto> infoList = new ArrayList<>();
        if("01".equals(flag)){
            // 根据选中的数据的期次取消手工处理
            //  RHPTSJ-139
            try {
                infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")),ZG08SpeCounterQuityDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                logger.debug("数据转换异常",e.getMessage(),e);
                logger.error(e.getMessage(),e);
                return "";
            }
            List<String> termList = new ArrayList<>();
            infoList.forEach(item -> {
                termList.add(item.getTermid());
            });
            Map<String,Object> pageParam = new HashMap<>();
            pageParam.put("termIdList",termList);
            pageParam.put("sendStatusList",StringUtil.splitToList(",","03,"));
            infoList = zg08SpeCounterQuityMapper.searchPage(pageParam);
            //  RHPTSJ-139
        }else{
            Map<String,Object> pageParam = new HashMap<>();
            pageParam.putAll(map);
            deaSearchParam(pageParam);
            infoList = zg08SpeCounterQuityMapper.searchPage(pageParam);
        }
        if(infoList.isEmpty()){
            return "success";
        }
        Map<String,List<String>> datuuidMap = infoList.stream().map(ZG08SpeCounterQuityDto::getDatuuid).filter(StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
        List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());
        int grps = infoList.size()/PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        int idx = 0;
        for ( int i=0;i<grps;i++ ) {
            List<ZG08SpeCounterQuityDto> list1 = infoList.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
            if ( list1.size()>0 ) {
                // 更新状态
                zg08SpeCounterQuityMapper.cancelManul(list1);
                // His表数据插入sub表
                //zg08SpeCounterQuityMapper.insetBatchSubFromHisByUpdateTime(list1);
                // 删除his表中移走的数据
                //zg08SpeCounterQuityMapper.deleteBatchSubFromHisByUpdateTime(list1);
            }
            idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        }
        List<ZG08SpeCounterQuityDto> list2 = infoList.subList(idx, infoList.size());
        if ( list2.size()>0 ) {
            // 更新状态
            zg08SpeCounterQuityMapper.cancelManul(list2);
            // His表数据插入sub表
            //zg08SpeCounterQuityMapper.insetBatchSubFromHisByUpdateTime(list2);
            // 删除his表中移走的数据
            //zg08SpeCounterQuityMapper.deleteBatchSubFromHisByUpdateTime(list2);
        }
        // 更新DAT报送状态
        if (CollectionUtils.isNotEmpty(datuuidList)){
            zg08SpeCounterQuityMapper.cancelDatReportStatus(datuuidList);
        }
        logger.debug("取消手工处理结束:------------------------------"+new Date());
        return "success";
    }

    public List<ZG08SpeCounterQuityDto> searchByUuid(List<String> list) {
        return zg08SpeCounterQuityMapper.searchByUuid(list.get(0));
    }

    public List<ZG08SpeCounterQuityDto> searchByExcel(Map<String, Object> map) {
        deaSearchParam(map);
        return zg08SpeCounterQuityMapper.searchExport(map);
    }

    /**
     * 查询产品名称
     *
     * @param pageParam 参数
     * @return 结果list
     * @see
     * @since 1.0
     */
    public List<SuggestDto> queryPrdNameList(String param) {
        return zg08SpeCounterQuityMapper.queryPrdNameList(param);
    }

    public List<SuggestDto> getCounterProdList(String param) {
        return zg08SpeCounterQuityMapper.getCounterProdList(param);
    }

}
