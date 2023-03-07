package com.joyintech.tams.svreport.submitbefore.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.submitbefore.dao.PbcSubmitBeforeCheck001Mapper;
import com.joyintech.tams.svreport.submitbefore.dto.PbcSubmitBeforeCheck001Dto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

;

/**
 * @author yangxianjiu
 * @date 2022/11/28
 */
@Service("PbcSubmitBeforeCheck001Service")
@Transactional(rollbackFor = Exception.class)
public class PbcSubmitBeforeCheck001Service extends CommonExportService<PbcSubmitBeforeCheck001Dto> {


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PbcSubmitBeforeCheck001Mapper pbcSubmitBeforeCheck001Mapper;

    /**
     * 分页
     *
     * @param page
     * @param length
     * @param pageParam
     * @return
     */
    public Page<PbcSubmitBeforeCheck001Dto> searchPage(Integer page, Integer length, Map<String, Object> pageParam) {
        dealSearchParams(pageParam);
        Page<PbcSubmitBeforeCheck001Dto> tempPage = super.queryForPage(page, length, () -> pbcSubmitBeforeCheck001Mapper.searchPage(pageParam));
        dealResult(tempPage.getRoot());
        // 源数据太大 导致选中导出数据缺失
        tempPage.getRoot().forEach(item -> item.setOrgData(null));
        return tempPage;
    }

    /**
     * 处理查询参数
     *
     * @param param
     */
    public void dealSearchParams(Map<String, Object> param){
        // 报表名称
        if (param.get("reportName") != null && StringUtil.isNotBlank(param.get("reportName").toString())){
            param.put("reportNameList", StringUtil.splitToList(GlobalConstant.COMMA, param.get("reportName").toString()));
        }
        // 关联报表
        if (param.get("relevanceReport") != null && StringUtil.isNotBlank(param.get("relevanceReport").toString())){
            param.put("relevanceReportList", StringUtil.splitToList(GlobalConstant.COMMA, param.get("relevanceReport").toString()));
        }
    }

    /**
     * 数据格式化
     *
     * @param list
     */
    public void dealResult(List<PbcSubmitBeforeCheck001Dto> list){
        list.stream().forEach(item->{
            // 报表名称
            item.setReportNameStr(SvrDataUtil.getNameFromDataDict(DictCode.大数据采集平台报表类型, item.getReportName()));
            // 时间
            item.setCheckDateStr(DateUtil.format(item.getCheckDate(), DateUtil.DEFAULT_DATETIME_FORMAT));
            // 规则类型
            item.setRuleTypeStr(SvrDataUtil.getZZDictCodeAndName(DictCode.数据校验规则类型, item.getRuleType()));
            // 规则标识
            item.setRuleCodeStr(SvrDataUtil.getZZDictCodeAndName(DictCode.数据校验规则标识, item.getRuleCode()));
            //校验结果
            item.setCheckFlagStr(SvrDataUtil.getZZDictCodeAndName(DictCode.数据校验状态, item.getCheckFlag()));
        });
    }

    /**
     * 导出 查询参数
     *
     * @param param
     * @return
     */
    public List<PbcSubmitBeforeCheck001Dto> searchExport(Map<String, Object> param) {
        dealSearchParams(param);
        List<PbcSubmitBeforeCheck001Dto> tempList = pbcSubmitBeforeCheck001Mapper.searchPage(param);
        dealResult(tempList);
        return tempList;
    }

    /**
     * 导出报表
     * @param param
     * @return
     */
    public List<PbcSubmitBeforeCheck001Dto> searchExportExcel(Map<String, Object> param) {
        List<PbcSubmitBeforeCheck001Dto> tempList = pbcSubmitBeforeCheck001Mapper.searchExportExcel(param);
        return tempList;
    }

}