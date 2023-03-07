/*
 * 文件名：InvestorsHoldInfoRegist001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zuoyinyi
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.handletype.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.ChbDeadlineUtil;
import com.joyintech.tams.report.handletype.dao.ChbEquHandleTypeMapper;
import com.joyintech.tams.report.handletype.dto.EquityHolderTypeDto;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.report.submitmaintenance.equityinfo.dto.EquityInfo001Dto;
import com.joyintech.tams.report.submitmanagement.equityinforegist.dao.EquityInfoRegist001Mapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 投资者类别处理service
 *
 * @author 董向阳
 * @version 2022年05月17日
 * @see EquityHandleTypeRegist001Service
 * @since 1.0
 */
@Service("equityHandleTypeRegist001Service")
@Transactional(rollbackFor = Exception.class)
public class EquityHandleTypeRegist001Service  {


    /**
     * 长sql最大一次插入或者更新条数
     */
    @Value("${application.reportInsertLongCount}")
    public String reportInsertLongCount;


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChbEquHandleTypeMapper chbEquHandleTypeMapper;

    @Autowired
    private EquityInfoRegist001Mapper equityInfoRegist001Mapper;

    @Autowired
    private BatchInsertService batchInsertService;

    /**
     * 根据持有sub处理投资者类别
     * @param
     */
    public void excuteHandleType(Date excuteDate) {
        //临时文件目录
        String tmpPath =  CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);

        //1.查询持有>1000000,高净值和私人银行客户的信息。
        List<EquityHolderTypeDto> tlist=chbEquHandleTypeMapper.selectInvestHoldData(DateUtil.format(excuteDate));

        if(CollectionUtils.isEmpty(tlist)){
            return;
        }
        //2.将list入库临时表CHB_EQU_TYPE_TMP
        chbEquHandleTypeMapper.truncateTmp();
        ImpTable table = new ImpTable("CHB_EQU_TYPE_TMP");
        table.setColumns(ImportGenerator.createImpTableColumns(table.getName()));
        batchInsertService.exeBatchldr(tmpPath, tlist, table);

        //3.关联身份登记表，查询类型变更的数据
        List<EquityInfo001Dto> listsub=chbEquHandleTypeMapper.selectChangeHoldType();
        if(CollectionUtils.isEmpty(listsub)){
            logger.info("当日无投资者类型变更数据------------------");
            return;
        }

        //用于修改INFO表：投资者类型HOLDER_TYPE的集合
        List<EquityInfo001Dto>  listi=new ArrayList<>();

        //用于修改SUB表：投资者类型HOLDER_TYPE的集合
        List<EquityInfo001Dto>  lista=new ArrayList<>();

        //用于生成重要信息变更的数据的集合
        List<EquityInfo001Dto>  listb=new ArrayList<>();

        //4.循环处理list
        for(EquityInfo001Dto dto:listsub){

            //（1）数据状态=新建
        if(StringUtil.equals(DictCodeListExt2.报送数据状态_新建.getValue(),dto.getStatus())){
            if(StringUtil.equals(DictCodeListExt2.报送状态_未报送.getValue(),dto.getSubmitStatus())){
                ////报送状态=未报送；修改数据
                lista.add(dto);
                listi.add(dto);
            } else{
                ////报送状态=已报送;生成修改，重要信息变更
                EquityInfo001Dto equityInfo001Dto = new EquityInfo001Dto();
                copyProperties(equityInfo001Dto, dto);
                listb.add(equityInfo001Dto);
                listi.add(dto);
            }
        }else{
            //（2）数据状态=修改  ；重要信息变更
           if(StringUtil.equals(DictCodeListExt2.数据类型_中债_重要信息变更.getValue(),dto.getType())){
               if(StringUtil.equals(DictCodeListExt2.报送状态_未报送.getValue(),dto.getSubmitStatus())){
                   ////报送状态=未报送；修改数据
                   lista.add(dto);
               } else{
                   ////报送状态=已报送;生成修改重要信息变更
                   EquityInfo001Dto equityInfo001Dto = new EquityInfo001Dto();
                   copyProperties(equityInfo001Dto, dto);
                   listb.add(equityInfo001Dto);
                   listi.add(dto);
               }
               //（2）数据状态=修改  ；识别标识变更/其他信息变更
           }else  if(StringUtil.equals(DictCodeListExt2.数据类型_中债_识别标识变更.getValue(),dto.getType())||
                   StringUtil.equals(DictCodeListExt2.数据类型_中债_其他信息变更.getValue(),dto.getType())){
               if(StringUtil.equals(DictCodeListExt2.报送状态_未报送.getValue(),dto.getSubmitStatus())){
                   ////报送状态=未报送；不处理
                     continue;
               } else{
                   ////报送状态=已报送;生成重要信息变更
                   EquityInfo001Dto equityInfo001Dto = new EquityInfo001Dto();
                   copyProperties(equityInfo001Dto, dto);
                   listb.add(equityInfo001Dto);
                   listi.add(dto);

               }
           }
         }
        }

        //5.更新INFO表数据
        updateSubHandleTypefInfoBatch(listi);
        //6.更新SUB表数据
        updateSubHandleTypeBatch(lista);
        //7.插入数据
        insertBatchEquityRegistByPart(listb);


    }

    /**
     * 批量更新INFO表的类别
     * @param list
     */
    public  void  updateSubHandleTypefInfoBatch( List<EquityInfo001Dto>  list){
        if (StringUtil.isEmptyList(list)) {
            return;
        }

        int maxDataCountOneTime = Integer.parseInt(reportInsertLongCount);
        int dataCount = list.size();
        if (dataCount <= maxDataCountOneTime) {
            chbEquHandleTypeMapper.updateSubHandleInfoType(list);
            return;
        }

        List<EquityInfo001Dto> newDataList = new ArrayList<>();
        newDataList.addAll(list);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
            chbEquHandleTypeMapper.updateSubHandleInfoType(
                    newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            chbEquHandleTypeMapper.updateSubHandleInfoType(newDataList);
        }
    }

    /**
     * 批量更新sub表的类别
     * @param list
     */
    public  void  updateSubHandleTypeBatch( List<EquityInfo001Dto>  list){
        if (StringUtil.isEmptyList(list)) {
            return;
        }

        int maxDataCountOneTime = Integer.parseInt(reportInsertLongCount);
        int dataCount = list.size();
        if (dataCount <= maxDataCountOneTime) {
            chbEquHandleTypeMapper.updateSubHandleType(list);
            return;
        }

        List<EquityInfo001Dto> newDataList = new ArrayList<>();
        newDataList.addAll(list);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
            chbEquHandleTypeMapper.updateSubHandleType(
                    newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            chbEquHandleTypeMapper.updateSubHandleType(newDataList);
        }
    }


    /**
     * 批量插入sub表
     * @param
     */
    private void insertBatchEquityRegistByPart(List<EquityInfo001Dto> dataList) {
        if (StringUtil.isEmptyList(dataList)) {
            return;
        }

        int maxDataCountOneTime = Integer.parseInt(reportInsertLongCount);
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            equityInfoRegist001Mapper.insertBatchEquityRegist(dataList);
            return;
        }

        List<EquityInfo001Dto> newDataList = new ArrayList<EquityInfo001Dto>();
        newDataList.addAll(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
            equityInfoRegist001Mapper.insertBatchEquityRegist(
                    newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            equityInfoRegist001Mapper.insertBatchEquityRegist(newDataList);
        }
    }

    /***
     * 生成修改，中债信息变更数据
     * @param dto
     * @param rdto
     */
    public void  copyProperties(EquityInfo001Dto dto,EquityInfo001Dto rdto){
        // 当前批次报送时点集合
        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();
        //复制属性
        BeanUtil.copyPropertiesIgnoreNull(dto, rdto);
        //数据日期
        dto.setUpdateTime(DateUtil.now());
        dto.setCreateTime(DateUtil.now());
        //报送状态
        dto.setStatus(DictCodeListExt2.报送数据状态_修改.getValue());
        //数据类型
        dto.setType(DictCodeListExt2.数据类型_中债_重要信息变更.getValue());
        // 报送时点通用方法
        List<Date>  dateList = ChbDeadlineUtil.getChbEndDate(
                DictCodeListExt2.报表名称_投资者身份信息登记.getValue(),  DictCodeListExt2.报送数据状态_修改.getValue(), dateMap, DateUtil.now());
        if (CollectionUtils.isNotEmpty(dateList)) {
            dto.setChbSubmitDeadline(dateList.get(0));
            dto.setSubmitDeadline(dateList.get(1));
        }
    }

}
