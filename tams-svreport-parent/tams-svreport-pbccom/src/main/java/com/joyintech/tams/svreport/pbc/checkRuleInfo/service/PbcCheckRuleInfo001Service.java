package com.joyintech.tams.svreport.pbc.checkRuleInfo.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.svreport.generationcheck.dto.PbcReportGenerationCheck001Dto;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.dao.PbcCheckRuleInfo001Mapper;
import com.joyintech.tams.svreport.pbc.checkRuleInfo.dto.PbcCheckRuleInfo001;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:yangkunfeng
 * @Date:2022/11/30 15:00
 * @Description:数据校验规则信息表操作
 */
@Service
public class PbcCheckRuleInfo001Service extends CommonExportService<PbcCheckRuleInfo001> {

    @Autowired
    private PbcCheckRuleInfo001Mapper pbcCheckRuleInfo001Mapper;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public Integer save(PbcCheckRuleInfo001 dto) {
        Integer result = 0;
        Date dd=new Date();
        //格式化
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sim.format(dd);
        dto.setUpdateDataTime(time);
        if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, dto.getPageModel())) {
            result = pbcCheckRuleInfo001Mapper.insertSelective(dto);
        } else if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_EDIT, dto.getPageModel())) {
            result = pbcCheckRuleInfo001Mapper.updateByRuleId(dto);
        }
        return result;
    }

    public List<PbcCheckRuleInfo001> selectAll(Map<String, Object> pageParam){
        dealSearchParams(pageParam);
        List<PbcCheckRuleInfo001> pbcCheckRuleInfo001List = pbcCheckRuleInfo001Mapper.searchPage(pageParam);
        dealResult(pbcCheckRuleInfo001List);
        return pbcCheckRuleInfo001List;
    }

    public Integer delete(PbcCheckRuleInfo001 dto) {
        List<PbcCheckRuleInfo001> dtoMap = dto.getSelectedItem();
        int result = 0;
        for(PbcCheckRuleInfo001 pbcCheckRuleInfo001:dtoMap){
            result += pbcCheckRuleInfo001Mapper.deleteByRuleId(pbcCheckRuleInfo001.getRuleId());
        }
        return result;
    }

    public Page<PbcCheckRuleInfo001> selectForPage(Integer page, Integer length, Map<String, Object> pageParam) {
        dealSearchParams(pageParam);
        Page<PbcCheckRuleInfo001> pbcCheckRuleInfo001Page = queryForPage(page, length, () -> pbcCheckRuleInfo001Mapper.searchPage(pageParam));
        dealResult(pbcCheckRuleInfo001Page.getRoot());
        return pbcCheckRuleInfo001Page;
    }

    public PbcCheckRuleInfo001 selectDetail(Map<String, Object> paramMap) {
        List<PbcCheckRuleInfo001> pbcCheckRuleInfo001List = new ArrayList<PbcCheckRuleInfo001>();
        PbcCheckRuleInfo001 pbcCheckRuleInfo001 = pbcCheckRuleInfo001Mapper.selectByPrimaryKey(paramMap.get("ruleId").toString());
        pbcCheckRuleInfo001List.add(pbcCheckRuleInfo001);
        dealResult(pbcCheckRuleInfo001List);
        return pbcCheckRuleInfo001List.get(0);
    }

    public void updateStatus(Map<String, Object> param) {
        List<String> ruleIds = (List<String>) param.get("selectData");
        for (String ruleId:ruleIds){
            PbcCheckRuleInfo001 pbcCheckRuleInfo001 = pbcCheckRuleInfo001Mapper.selectByPrimaryKey(ruleId);
            if(param.get("flag").toString().equals("Y")){
                pbcCheckRuleInfo001.setIsstart("01");
            }else{
                pbcCheckRuleInfo001.setIsstart("02");
            }
            pbcCheckRuleInfo001Mapper.updateByRuleId(pbcCheckRuleInfo001);
        }
    }

    /**
     * 数据格式化
     *
     * @param list
     */
    public void dealResult(List<PbcCheckRuleInfo001> list){
        list.stream().forEach(item->{
            // 规则大类
            item.setRuleClassifyStr(SvrDataUtil.getNameFromDataDict(DictCode.数据校验规则大类, item.getRuleClassify()));
            // 启动状态
            item.setIsstartStr(SvrDataUtil.getNameFromDataDict(DictCode.数据校验规则状态, item.getIsstart()));
            // 规则类型
            item.setRuleTypeStr(SvrDataUtil.getNameFromDataDict(DictCode.数据校验规则类型, item.getRuleType()));
            // 规则标识
            item.setRuleIdentifyStr(SvrDataUtil.getNameFromDataDict(DictCode.数据校验规则标识, item.getRuleIdentify()));
            //报表名称
            item.setTableDataNameStr(SvrDataUtil.getNameFromDataDict(DictCode.大数据采集平台报表类型, item.getTableDataName()));
            //关联报表名称
//            item.setRelatedTableStr(SvrDataUtil.getNameFromDataDict(DictCode.大数据采集平台报表类型, item.getRelatedTable()));
            item.setRelatedTableStr(item.getRelatedTable());
            //时间
            item.setUpdareTimeStr(DateUtil.format(item.getUpdateTime(), DateUtil.DEFAULT_DATETIME_FORMAT));
        });
    }

    public void dealSearchParams(Map<String, Object> param){
        if (param.get("tableDataName") != null && StringUtil.isNotBlank(param.get("tableDataName").toString())){
            param.put("tableDataNameList", StringUtil.splitToList(GlobalConstant.COMMA, param.get("tableDataName").toString()));
            param.remove("tableDataName");
        }
        // 规则类型
        if (param.get("ruleType") != null && StringUtil.isNotBlank(param.get("ruleType").toString())){
            param.put("ruleTypeList", StringUtil.splitToList(GlobalConstant.COMMA, param.get("ruleType").toString()));
            param.remove("ruleType");
        }
    }

    /**
     * 构建导出参数
     *
     * @param exportParam
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        dealSearchParams(exportParam.getSelectParam());
        exportParam.config("/com/joyintech/tams/svreport/pbc/checkRuleInfo/config/export_conf.xml")
                .select(() -> this.selectAll(exportParam.getSelectParam()));
        super.buildExportParam(exportParam);
    }
}
