package com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.service;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.data.service.PbcClassicsDataFileHelper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto.ZG06PbcAssetSusfructSub;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dao.ZG07PbcColLoanDetailSub001Mapper;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import com.joyintech.tams.svreport.pbc.pbcDataCheck.PbcDataCheckUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ZG07 除回购和拆解外贷款明细登记表
 *
 * @author yangxianjiu
 * @date 2022/11/9
 */
@Service("zG07PbcColLoanDetailSub001Service")
@Transactional(rollbackFor = Exception.class)
public class ZG07PbcColLoanDetailSub001Service extends CommonExportService<ZG07PbcColLoanDetailSubDto> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ZG07PbcColLoanDetailSub001Mapper pbcColLoanDetailSub001Mapper;


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

        List<ZG07PbcColLoanDetailSubDto> equityInfoRegistList = new ArrayList<ZG07PbcColLoanDetailSubDto>();
        param.put("submitStatus", DictCodeListExt2.报送状态_未报送.getValue());
        param.put("deptCode", CommonUtil.getDeptCode());
        List<String> keyList = new ArrayList<String>();
        if(param.get("checkKeys")!=null){
//            keyList= com.joyintech.framework.util.StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
            param.put("termid", param.get("checkKeys"));
        }
        Map<String, PbcPrdBaseinfoSub> repmap = new HashMap<String, PbcPrdBaseinfoSub>();
        deaSearchParam(param);
        equityInfoRegistList= pbcColLoanDetailSub001Mapper.selectForPage(param);
        String uuid = IDGenerator.generate(36);
        //公共校验方法

        return pbcDataCheckUtil.getVailDateFromPage(equityInfoRegistList,uuid,DictCodeListExt2.采集平台除回购和拆借外贷款明细信息.getValue()
                ,true,DictCodeListExt2.数据校验规则大类_报送前检查.getValue(),ZG07PbcColLoanDetailSubDto.class);
    }

    /**
     * 分页查询
     *
     * @param pageParam 查询条件
     * @param page      页
     * @param length    条
     * @return
     */
    public Page<ZG07PbcColLoanDetailSubDto> selectForPage(Map<String, Object> pageParam, int page, int length) {
        // 处理查询参数
        deaSearchParam(pageParam);
        return super.queryForPage(page, length, () -> pbcColLoanDetailSub001Mapper.selectForPage(pageParam));
    }

    /**
     * 查询参数处理
     *
     * @param param
     */
    private void deaSearchParam(Map<String, Object> param) {
        // 统计年月
        if (param.get("termid") != null && StringUtil.isNotBlank(param.get("termid").toString())) {
            param.put("termid", param.get("termid").toString().replaceAll(GlobalConstant.SPLIT, ""));
        }
    }

    /**
     * 手工处理
     *
     * @param map
     * @return
     */
    public Integer doManual(Map<String, Object> map) {
        logger.info("手工处理开始:------------------------------" + new Date());
        String flag = map.get("flag").toString();
        List<ZG07PbcColLoanDetailSubDto> infoList = Lists.newArrayList();
        if ("01".equals(flag)) {
            // 选中的手工处理
            try {
                infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")), ZG07PbcColLoanDetailSubDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                logger.debug("数据转换异常", e.getMessage(), e);
                logger.error(e.getMessage(), e);
                return 0;
            }
        } else {
            Map<String, Object> pageParam = new HashMap<>();
            pageParam.putAll(map);
            deaSearchParam(pageParam);
            infoList = pbcColLoanDetailSub001Mapper.selectForPage(pageParam);
        }
        if (infoList.isEmpty()) {
            return 0;
        }
        //获取查询数据的报表期限，去更新当月一整月的数据
        List<String> listTermid = infoList.stream().map(ZG07PbcColLoanDetailSubDto::getTermid).collect(Collectors.toList());
        listTermid = listTermid.stream().distinct().collect(Collectors.toList());
        Map<String,List<String>> datuuidMap = infoList.stream().map(ZG07PbcColLoanDetailSubDto::getDatuuid).filter(StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
        List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());

        /* 手工处理数据批量进行 update by gaojian 2019-06-22 begin */
        int grps = listTermid.size() / PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        int idx = 0;
        for (int i = 0; i < grps; i++) {
            List<String> list1 = listTermid.subList(idx, idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
            if (list1.size() > 0) {
                // 原始表更新状态
                pbcColLoanDetailSub001Mapper.doManual(list1);
            }
            idx += PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        }
        List<String> list2 = listTermid.subList(idx, listTermid.size());
        if (list2.size() > 0) {
            // 原始表更新状态
            pbcColLoanDetailSub001Mapper.doManual(list2);
        }
        // 更新DAT报送状态
        if (CollectionUtils.isNotEmpty(datuuidList)){
            pbcColLoanDetailSub001Mapper.doDatReportStatus(datuuidList);
        }
        /* 手工处理数据批量进行 update by gaojian 2019-06-22 end */
        logger.info("手工处理结束:------------------------------" + new Date());
        return listTermid.size();
    }

    /**
     * 取消手工处理
     *
     * @param map
     * @return
     */
    public String cancelManual(Map<String, Object> map) {
        logger.info("取消手工处理开始:------------------------------" + new Date());
        String flag = map.get("flag").toString();
        List<ZG07PbcColLoanDetailSubDto> infoList = new ArrayList<>();
        if ("01".equals(flag)) {
            // 选中的取消手工处理
            try {
                infoList = JsonUtils.json2List(JsonUtils.object2Json(map.get("infoList")), ZG07PbcColLoanDetailSubDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                logger.debug("数据转换异常", e.getMessage(), e);
                logger.debug(e.getMessage(), e);
                return "fail";

            }
        } else {
            Map<String, Object> pageParam = new HashMap<>();
            pageParam.putAll(map);
            deaSearchParam(pageParam);
            infoList = pbcColLoanDetailSub001Mapper.selectForPage(pageParam);
        }
        if (infoList.isEmpty()) {
            return "success";
        }
        //获取查询数据的报表期限，去更新当月一整月的数据
        List<String> listTermid = infoList.stream().map(ZG07PbcColLoanDetailSubDto::getTermid).collect(Collectors.toList());
        listTermid = listTermid.stream().distinct().collect(Collectors.toList());
        Map<String,List<String>> datuuidMap = infoList.stream().map(ZG07PbcColLoanDetailSubDto::getDatuuid).filter(StringUtil::isNotBlank).collect(Collectors.groupingBy(key->key));
        List<String> datuuidList = new ArrayList<>(datuuidMap.keySet());

        int grps = listTermid.size() / PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        int idx = 0;
        for (int i = 0; i < grps; i++) {
            List<String> list1 = listTermid.subList(idx, idx + PbcClassicsDataFileHelper.COMMIT_CONSTANTS);
            if (list1.size() > 0) {
                // 更新状态
                pbcColLoanDetailSub001Mapper.cancelManual(list1);
            }
            idx += PbcClassicsDataFileHelper.COMMIT_CONSTANTS;
        }
        List<String> list2 = listTermid.subList(idx, listTermid.size());
        if (list2.size() > 0) {
            // 更新状态
            pbcColLoanDetailSub001Mapper.cancelManual(list2);
        }
        // 更新DAT报送状态
        if (CollectionUtils.isNotEmpty(datuuidList)){
            pbcColLoanDetailSub001Mapper.cancelDatReportStatus(datuuidList);
        }
        logger.info("取消手工处理结束:------------------------------" + new Date());
        return "success";
    }

    /**
     * 获取资产代码
     *
     * @param param
     * @param page
     * @param length
     * @return
     */
    public Page<SelectOptionDto> getAssetCode(Map<String, Object> param, int page, int length) {
        return super.queryForPageForSelectReturn(page, length, () -> pbcColLoanDetailSub001Mapper.getAssetCode(param));
    }

    /**
     * 导出 根据查询参数
     *
     * @param map
     * @return
     */
    public List<ZG07PbcColLoanDetailSubDto> searchByExcel(Map<String, Object> map) {
        deaSearchParam(map);
        return pbcColLoanDetailSub001Mapper.selectForExport(map);
    }

    /**
     * 导出 根据选中行
     *
     * @param list
     * @return
     */
    public List<ZG07PbcColLoanDetailSubDto> searchByUuid(List<String> list) {
        return pbcColLoanDetailSub001Mapper.selectByUuid(list.get(0));
    }


}
