package com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.data.service.PbcClassicsDataFileHelper;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dto.PbcPrdAccountSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dao.ZG05PbcColPrdAssetDebtMapper;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
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

@Service(value = "zG05PbcColPrdAssetDebtSubService")
@Transactional(rollbackFor = Exception.class)
public class ZG05PbcColPrdAssetDebtSubService extends CommonExportService<ZG05PbcColPrdAssetDebtDto> {

    @Autowired
    private ZG08SpeCounterQuityMapper zg08SpeCounterQuityMapper;

    @Autowired
    private ZG05PbcColPrdAssetDebtMapper zg05PbcColPrdAssetDebtMapper;
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

        List<ZG05PbcColPrdAssetDebtDto> equityInfoRegistList = new ArrayList<ZG05PbcColPrdAssetDebtDto>();
        param.put("submitStatus", DictCodeListExt2.报送状态_未报送.getValue());
        param.put("deptCode", CommonUtil.getDeptCode());
        List<String> keyList = new ArrayList<String>();
        if(param.get("checkKeys")!=null){
//            keyList= StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
            param.put("termId", param.get("checkKeys"));
        }
        Map<String, PbcPrdBaseinfoSub> repmap = new HashMap<String, PbcPrdBaseinfoSub>();
        deaSearchParam(param);
        equityInfoRegistList= zg05PbcColPrdAssetDebtMapper.searchPage(param);
        String uuid = IDGenerator.generate(36);
        //公共校验方法

        return pbcDataCheckUtil.getVailDateFromPage(equityInfoRegistList,uuid,DictCodeListExt2.采集平台资管产品资产负债信息.getValue()
                ,true,DictCodeListExt2.数据校验规则大类_报送前检查.getValue(),ZG05PbcColPrdAssetDebtDto.class);
    }

    /**
     * 页面查询
     *
     * @param searchParam
     * @return
     */
    public Page<ZG05PbcColPrdAssetDebtDto> searchPage(Map<String, Object> searchParam) {
        deaSearchParam(searchParam);
        int pagenum = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_PAGE), 1);
        int length = StringUtil.integerValueOf(""+searchParam.get(GlobalConstant.PARAM_LENGTH), GlobalConstant.DEFAULT_PAGE_SIZE);
        Page<ZG05PbcColPrdAssetDebtDto> page = queryForPage(pagenum,length,()->zg05PbcColPrdAssetDebtMapper.searchPage(searchParam));

        dealResultlist(page.getRoot());

        return page;
    }

    private void dealResultlist(List<ZG05PbcColPrdAssetDebtDto> resultlist){
        if(!CollectionUtils.isEmpty(resultlist)){
            for(ZG05PbcColPrdAssetDebtDto dto : resultlist){
                if(StringUtil.isNotEmpty(dto.getSendStatus())){
                    dto.setSendStatusStr(SvrDataUtil.getNameFromDataDict(DictCode.报送状态,dto.getSendStatus()));
                }
                if(StringUtil.isNotEmpty(dto.getCcy())){
                    dto.setCcyStr(SvrDataUtil.getNameFromDataDict(DictCode.币种_人行,dto.getCcy()));
                }
                if(StringUtil.isNotEmpty(dto.getStatusType())){
                    dto.setStatusTypeStr(SvrDataUtil.getZZDictCodeAndName(DictCode.数据类型_人行,dto.getStatusType()));
                }
            }
        }
    }

    /**
     * 查询参数处理
     * @param searchParam
     */
    private void deaSearchParam(Map<String, Object> searchParam) {
        if (StringUtil.isNotEmpty(searchParam.get("pbcProdId"))){
            searchParam.put("pbcProdIdList",
                    StringUtil.splitToList(",",(String) searchParam.get("pbcProdId")));
        } else {
            searchParam.put("pbcProdIdList",null);
        }
        if (StringUtil.isNotEmpty(searchParam.get("prodId"))){
            searchParam.put("prodIdList",
                    StringUtil.splitToList(",",(String) searchParam.get("prodId")));
        } else {
            searchParam.put("prodIdList",null);
        }
        if (StringUtil.isNotEmpty(searchParam.get("sendStatus"))){
            searchParam.put("sendStatusList",
                    StringUtil.splitToList(",",(String) searchParam.get("sendStatus")));
        } else {
            searchParam.put("sendStatusList",null);
        }
        if (StringUtil.isNotEmpty(searchParam.get("statusType"))){
            searchParam.put("statusTypeList",
                    StringUtil.splitToList(",",(String) searchParam.get("statusType")));
        } else {
            searchParam.put("statusTypeList",null);
        }
        if (StringUtil.isNotEmpty(searchParam.get("ccy"))){
            searchParam.put("ccyList",
                    StringUtil.splitToList(",",(String) searchParam.get("ccy")));
        } else {
            searchParam.put("ccy",null);
        }
        if (StringUtil.isNotEmpty(searchParam.get("enddate"))){
            searchParam.put("termId",searchParam.get("enddate").toString().replaceAll("-",""));
        }
    }

    /**
     * 手工处理
     * @param
     */
    @SuppressWarnings("unchecked")
    public void doManul(Map<String,Object> map) {
        logger.debug("手工处理开始:------------------------------"+new Date());
        String flag = map.get("flag").toString();
        List<ZG05PbcColPrdAssetDebtDto> infoList = new ArrayList<>();
        if("01".equals(flag)){
            // 选中的手工处理
            try {
                infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")),ZG05PbcColPrdAssetDebtDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                logger.debug("数据转换异常",e.getMessage(),e);
                logger.error(e.getMessage(),e);
                return ;
            }
        }else{
            Map<String,Object> pageParam = new HashMap<>();
            pageParam.putAll(map);
            deaSearchParam(pageParam);
            infoList = zg05PbcColPrdAssetDebtMapper.searchPage(pageParam);
        }
        if(infoList.isEmpty()){
            return ;
        }
        //获取查询数据的报表期限，去更新当月一整月的数据
        List<String>   listtermid=infoList.stream().map(a -> a.getTermid()).collect(Collectors.toList());
        listtermid=listtermid.stream().distinct().collect(Collectors.toList());

        Map<String,List<String>> datuuidMap = infoList.stream().map(ZG05PbcColPrdAssetDebtDto::getDatuuid).filter(com.joyintech.background.component.common.pack.common.util.StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
        List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());

        /* 手工处理数据批量进行 update by gaojian 2019-06-22 begin */
        int grps = listtermid.size()/ PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        int idx = 0;
        for ( int i=0;i<grps;i++ ) {
            List<String> list1 = listtermid.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
            if ( list1.size()>0 ) {
                // 原始表更新状态
                zg05PbcColPrdAssetDebtMapper.doManul(list1);
            }
            idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        }
        List<String> list2 = listtermid.subList(idx, listtermid.size());
        if ( list2.size()>0 ) {
            // 原始表更新状态
            zg05PbcColPrdAssetDebtMapper.doManul(list2);
        }
        // 更新DAT报送状态
        if (CollectionUtils.isNotEmpty(datuuidList)){
            zg05PbcColPrdAssetDebtMapper.doDatReportStatus(datuuidList);
        }
        /* 手工处理数据批量进行 update by gaojian 2019-06-22 end */
        logger.debug("手工处理结束:------------------------------"+new Date());
    }

    /**
     * 取消手工处理
     * @param
     */
    @SuppressWarnings("unchecked")
    public String doCancelManul(Map<String,Object> map) {
        logger.debug("取消手工处理开始:------------------------------"+new Date());
        String flag = map.get("flag").toString();
        List<ZG05PbcColPrdAssetDebtDto> infoList = new ArrayList<>();
        if("01".equals(flag)){
            // 选中的取消手工处理
            try {
                infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")),ZG05PbcColPrdAssetDebtDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                logger.debug("数据转换异常",e.getMessage(),e);
                logger.error(e.getMessage(),e);
                return "fail";

            }
        }else{
            Map<String,Object> pageParam = new HashMap<>();
            pageParam.putAll(map);
            deaSearchParam(pageParam);
            infoList = zg05PbcColPrdAssetDebtMapper.searchPage(pageParam);
        }
        if(infoList.isEmpty()){
            return "success";
        }
        //获取查询数据的报表期限，去更新当月一整月的数据
        List<String>   listtermid=infoList.stream().map(a -> a.getTermid()).collect(Collectors.toList());
        listtermid=listtermid.stream().distinct().collect(Collectors.toList());

        Map<String,List<String>> datuuidMap = infoList.stream().map(ZG05PbcColPrdAssetDebtDto::getDatuuid).filter(com.joyintech.background.component.common.pack.common.util.StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
        List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());

        int grps = listtermid.size()/PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        int idx = 0;
        for ( int i=0;i<grps;i++ ) {
            List<String> list1 = listtermid.subList(idx,idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
            if ( list1.size()>0 ) {
                // 更新状态
                zg05PbcColPrdAssetDebtMapper.cancelManul(list1);
            }
            idx += 	PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        }
        List<String> list2 = listtermid.subList(idx, listtermid.size());
        if ( list2.size()>0 ) {
            // 更新状态
            zg05PbcColPrdAssetDebtMapper.cancelManul(list2);
        }
        // 更新DAT报送状态
        if (CollectionUtils.isNotEmpty(datuuidList)){
            zg05PbcColPrdAssetDebtMapper.cancelDatReportStatus(datuuidList);
        }
        logger.debug("取消手工处理结束:------------------------------"+new Date());
        return "success";
    }

    public List<ZG05PbcColPrdAssetDebtDto> searchByUuid(List<String> list) {
        return zg05PbcColPrdAssetDebtMapper.searchByUuid(list.get(0));
    }

    public List<ZG05PbcColPrdAssetDebtDto> searchByExcel(Map<String, Object> map) {
        Map<String, Object> mapparam=new HashMap<>();
        mapparam.put("termId",map.get("enddate").toString().replaceAll("-",""));
        return zg05PbcColPrdAssetDebtMapper.searchExport(mapparam);
    }



}
