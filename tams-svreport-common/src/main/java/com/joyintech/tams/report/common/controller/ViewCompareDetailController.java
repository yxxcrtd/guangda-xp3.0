/*
 * 文件名：ViewCompareDetailController.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zuoyinyi
 * 修改时间：2017年12月6日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.common.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.ChbPrdBaseinfoMapper;
import com.joyintech.tams.common.base.dao.ChbPrdresistCodeMapper;
import com.joyintech.tams.common.base.entity.ChbPrdBaseinfo;
import com.joyintech.tams.common.base.entity.ChbPrdresistCode;
import com.joyintech.tams.common.base.entity.ChbPrdresistCodeKey;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.report.common.dao.ViewCompareDetailMapper;
import com.joyintech.tams.report.common.dto.RegistDetailDto;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;

import oracle.sql.TIMESTAMP;

import javax.annotation.Resource;

/**
 * 查看对比详情共同画面
 *
 * @author zuoyinyi
 * @version 1.0 2017年12月6日
 * @see ViewCompareDetailController
 * @since 1.0
 */
@Controller
@PageId("ViewCompareDetail")
@RequestMapping("/common/detail")
public class ViewCompareDetailController extends BaseController {

    /**
     * 界面特殊处理字段
     */
    enum PageFiledList {

        产品资金募集信息("PbcPrdCashRaiseSub", new String[] { "PROD_VALUE_CNY", "UNIT_VALUE_TOTAL_CNY" }),

        产品发行登记("SvrProdPersonIssue001", new String[] { "YIELD_LOW", "YIELD_HIGH" });


        /**
         * 界面id
         */
        private String pageId;

        /**
         * 特殊处理字段
         */
        private String[] filedArr;

        public String getPageId() {
            return pageId;
        }

        public void setPageId(String pageId) {
            this.pageId = pageId;
        }

        public String[] getFiledArr() {
            return filedArr;
        }

        public void setFiledArr(String[] filedArr) {
            this.filedArr = filedArr;
        }

        PageFiledList(String pageId, String[] filedArr) {
            this.pageId = pageId;
            this.filedArr = filedArr;
        }
    }
    enum CCYDictCodeList{
        中债币种_人民币( "CNY", "人民币"),
        /**
         * 中债币种_美元
         */
        中债币种_美元("USD", "美元"),
        /**
         * 中债币种_欧元
         */
        中债币种_欧元( "EUR", "欧元"),
        /**
         * 中债币种_英镑
         */
        中债币种_英镑( "GBP", "英镑"),
        /**
         * 中债币种_日元
         */
        中债币种_日元( "JPY", "日元"),
        ;
        /**
         * 对应于数据字典code
         */
        private String value;

        /**
         * 明细名称
         */
        private String codeName;
        /**
         * 获取 value
         *
         * @return value.
         */
        public String getValue() {
            return value;
        }
        /**
         * 设置 value
         *
         * @param value value
         */
        public void setValue(String value) {
            this.value = value;
        }
        /**
         * 获取 codeName
         *
         * @return codeName.
         */
        public String getCodeName() {
            return codeName;
        }

        /**
         * 设置 codeName
         *
         * @param codeName codeName
         */
        public void setCodeName(String codeName) {
            this.codeName = codeName;
        }
        CCYDictCodeList( String value, String codeName) {
            this.value = value;
            this.codeName=codeName;
        }
        static CCYDictCodeList from( String name) {
            CCYDictCodeList[] values = CCYDictCodeList.values();
            for (CCYDictCodeList code : values) {
                if (StringUtil.equals(name, code.getCodeName())) {
                    return code;
                }
            }
            return null;
        }
    }
    /**
     * ViewCompareDetailMapper
     */
    @Autowired
    private ViewCompareDetailMapper mapper;

    /**
     * 产品登记编码
     */
    @Autowired
    private ChbPrdresistCodeMapper chbPrdresistCodeMapper;

    /**
     * 产品要素
     */
    @Autowired
    private ChbPrdBaseinfoMapper chbPrdBaseinfoMapper;

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    public static final List<String> TABLE_NAME_LIST = Arrays.asList("PBC_COL_PRD_BASEINFO_SUB","PBC_COL_PRD_BASEINFO_END_SUB",
            "PBC_COL_PRD_BASEINFO_END_SUB","PBC_COL_PROD_RAISING_SUB","PBC_COL_LOAN_DETAIL_SUB");

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return "report/common/ViewCompareDetail";
    }

    /**
     * 查询明细信息对比
     * @return 登记明细信息
     * @throws IOException
     */
    @RequestMapping("search")
    @ResponseBody
    public AjaxResult searchDetail() throws IOException {
        AjaxResult result = new AjaxResult();
        String pageId = this.getParam(GlobalConstant.PAGE_ID);
        String registSeqNo = this.getParam("registSeqNo");
        String content = this.getParam("fields");
        List<RegistDetailDto> detailList = createRegistDetail(pageId, registSeqNo, content);
        result.setData(detailList);
        return result;
    }

    /**
     *
     * 解析画面参数中的表格列定义
     * @param content 列定义JSON字符串
     * @return 明细列表
     * @throws IOException
     */
    private List<RegistDetailDto> createDetailList(String content) throws IOException {
        if (StringUtil.isNotEmpty(content)) {
            boolean isShowBelongInstitutionNameflag = MultilevelDataAuthorityUtil.isEnabled();
            List<RegistDetailDto> detailList = JsonUtils.json2List(content, RegistDetailDto.class);
            // 如果有反馈状态为feedbackStr则改为feedbackstatus\
//            detailList.forEach(item->{
//                if ("submitFeedbackStatusStr".equals(item.getData())){
//                    item.setData("submitFeedbackStatus");
//                }
//                if(isShowBelongInstitutionNameflag && "belongInstitutionName".equals(item.getData())){
//                    detailList.remove(item);
//                }
//
//            });
            Iterator<RegistDetailDto> iterator = detailList.iterator();
            while(iterator.hasNext()){
                RegistDetailDto item = iterator.next();
                // 如果有反馈状态为feedbackStr则改为feedbackstatus\
                if ("submitFeedbackStatusStr".equals(item.getData())){
                    item.setData("submitFeedbackStatus");
                }
                if (Objects.equals("pbcAccountIdShow",item.getData())){
                    item.setData("pbcAccountId");
                }
                // 银行模式不需要所属机构
                if(!isShowBelongInstitutionNameflag && "belongInstitutionName".equals(item.getData())){
                    iterator.remove();
                }
            }


            return detailList;
        }
        return null;
    }

    /**
     * 创建明细信息
     * @param pageId 画面ID
     * @param registSeqNo 登记序号
     * @param content 表格列定义
     * @return 明细信息列表
     * @throws IOException
     */
    private List<RegistDetailDto> createRegistDetail(String pageId, String registSeqNo,
                                                     String content) throws IOException {
        if (StringUtil.isNotEmpty(pageId)) {
            final List<RegistDetailDto> detailList = createDetailList(content);
            String serviceName = CommonUtil.firstLowerCase(pageId) + "Service";
            Object service = SpringContextUtil.getBean(serviceName);
            Class<?> targetService = service.getClass().getSuperclass();
            Type[] types = ((ParameterizedType)targetService.getGenericSuperclass()).getActualTypeArguments();
            if (types != null && types.length > 0) {
                try {
                    Class<?> targetClass = Class.forName(types[0].getTypeName());
                    TableName table = targetClass.getAnnotation(TableName.class);
                    String tableName = table.value();

                    if(CommonUtil.isBAmodel() && StringUtil.equals("SvrChbAstValueRegist001",pageId) && StringUtil.equals("CHB_AST_VAL_SUB",tableName) ){
                        tableName = "CHB_ASSET_SUB_STATUS";
                    }else if(CommonUtil.isBAmodel() && StringUtil.equals("SvrAssetInfoRegist001",pageId) && StringUtil.equals("CHB_ASSET_INFO_SUB",tableName) ){
                        tableName = "CHB_ASSET_SUB_STATUS";
                    }

                    final StringBuilder sb = new StringBuilder();
                    sb.append("current");
                    // 设置当前值
                    if(!StringUtil.equals("ZG05_PBC_COL_REPORT",tableName)){
                        setValue(detailList, tableName, registSeqNo, targetClass,sb,pageId);
                    }
                    if("PBC_PRD_BASEINFO_SUB_NEW".equals(tableName)){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("pbcProdType".equals(registDetailDto.getData())){
                                registDetailDto.setCurrentValue("1-银行非保本理财");
                            }
                        }
                    }

                    if(TABLE_NAME_LIST.contains(tableName)){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("pbcProdId".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue().substring(0,3).equals("INS"))
                                registDetailDto.setCurrentValue(registDetailDto.getCurrentValue().substring(3));
                            }
                        }
                    }

                    //ZG02 双击查看保留小数位
                    if(tableName.equals("PBC_COL_INIT_PROD_RAISING_SUB")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("initRaisingAmtsum".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("initRaisingAmtsumcny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("initRaisingQty".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                        }
                    }

                    //ZG03 双击查看保留小数位
                    if(tableName.equals("PBC_COL_PRD_BASEINFO_END_SUB")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("profit".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("profitCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("customProfit".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("customProfitCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cusYearYield".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(5, RoundingMode.HALF_UP)).toString());
                            }
                        }
                    }


                    //ZG04 双击查看保留小数位
                    if(tableName.equals("PBC_COL_PROD_RAISING_SUB")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("raisingAmtsum".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("raisingAmtsumCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("raisingQty".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("redamt".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("redamtCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("redqty".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("endamtsum".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("endamtsumCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("endqty".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }


                        }
                    }

                    //ZG05 双击查看保留小数位
                    if(tableName.equals("ZG05_PBC_COL_REPORT")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("a0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a2100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a2110".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a2120".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a2200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a2210".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a2220".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("a3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a3100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a3200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a4100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a4200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a4300".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a4400".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a4500".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a4600".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a4700".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a4800".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("a5000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("a5100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5210".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5220".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5230".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5240".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5250".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5260".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5261".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5270".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5271".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5272".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("a5273".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5274".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5275".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("a5276".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("a5277".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5278".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5279".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a527a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a5280".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a6000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a6100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a6200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a6300".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a6400".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a6500".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a6600".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("a6700".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7110".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7120".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7210".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7220".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7230".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7240".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7250".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7260".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("a7270".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7280".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7290".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a72a0".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7300".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7310".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7320".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a7400".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a8000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a8100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a8200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("a9000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("aa000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ab000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ac000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("d0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1210".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1220".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1230".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1240".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }


                            if("b1250".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1260".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1261".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1270".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1271".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1272".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1273".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1274".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1275".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1276".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("b1277".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1278".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1278".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b127a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b1280".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b3100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b3200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("b5000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("c1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1110".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1120".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1130".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1140".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1150".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1160".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1170".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1171".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1172".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1173".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("c1174".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1175".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1176".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("c1177".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1178".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1179".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c117a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1180".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1210".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1220".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1230".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1240".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1250".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1260".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("c1270".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1271".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1272".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1273".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1274".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1275".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1276".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1277".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1278".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1279".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c127a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c1280".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                        }
                    }

                    if("PBC_COL_PROD_RAISING_SUB".equals(tableName)){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("buyPlace".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue(registDetailDto.getCurrentValue()+" "+SysDataUtil.getNameFromDataDict(DictCode.行政区划_省,registDetailDto.getCurrentValue()));
                            }
                        }
                    }

                    //ZG06 双击查看保留小数位
                    if(tableName.equals("PBC_COL_ASSET_USUFRUCT_SUB")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("assetsOrigAmt".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("assetsOrigAmtCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("currencyTransferAmt".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("currencyTransferAmtCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("currencyAmt".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("currencyAmtCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                        }
                    }

                    //ZG07 双击查看保留小数位
                    if(tableName.equals("PBC_COL_LOAN_DETAIL_SUB")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("rateLevel".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(5, RoundingMode.HALF_UP)).toString());
                            }
                            if("loanTransferorRate".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(5, RoundingMode.HALF_UP)).toString());
                            }
                            if("origContractAmount".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("origContractAmountCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("contractAmount".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("contractAmountCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                        }
                    }

                    //ZG08 双击查看保留小数位
                    if(tableName.equals("PBC_COL_SPE_COUNTER_PARTY_SUB")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("amt".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("amtCny".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                        }
                    }

                    //ZG09 双击查看保留小数位
                    if(tableName.equals("ZG09_PBC_COL_REPORT")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("g000a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g000b".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g000c".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g000d".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g000e".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("g100a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g100b".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g100c".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g100d".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g100e".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g200a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g200b".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g200c".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g200d".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g200e".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g300a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g300b".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g300c".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g300d".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g300e".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("g400a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g400b".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g400c".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g400d".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g400e".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("g500a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g500b".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g500c".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g500d".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g500e".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("g600a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g600b".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g600c".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g600d".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g600e".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g700a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g700b".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g700c".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g700d".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g700e".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g800a".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g800b".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g800c".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g800d".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g800e".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g00001".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("g00002".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                        }
                    }

                    //ZG10 双击查看保留小数位
                    if(tableName.equals("ZG10_PBC_COL_REPORT")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("h10000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h11000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h11100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h11200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h11300".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("h12000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h12100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h12200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h12300".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h13000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h13100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h13200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h13300".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h14000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h14100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h14200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h14300".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h20000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h21000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h22000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("h23000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h30000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h31000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h32000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h33000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("h34000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h40000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h41000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h42000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h43000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("h44000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h45000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h46000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h46100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47100".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47200".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47300".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47400".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47500".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47600".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47700".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47800".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47900".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h47a00".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h48000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h50000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("h51000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h52000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h53000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("h54000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h55000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h56000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("h57000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("h58000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h59000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("h5a000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                        }
                    }

                    //ZG11 双击查看保留小数位
                    if(tableName.equals("ZG11_PBC_COL_REPORT")){
                        for(RegistDetailDto registDetailDto:detailList){
                            if("c00000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c01000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c02000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c03000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c04000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("c10000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c11000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c12000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c13000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c14000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c20000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c21000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c22000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c23000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c24000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c30000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c31000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c32000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c33000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c34000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("c40000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c41000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c42000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c43000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c44000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("c50000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c51000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c52000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c53000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c54000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("c60000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c61000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c62000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c63000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c64000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }


                            if("c70000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c71000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c72000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c73000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c74000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }


                            if("c80000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c81000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c82000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c83000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c84000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("c90000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c91000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c92000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c93000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("c94000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("ca0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ca1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ca2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ca3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ca4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("cb0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cb1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cb2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cb3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cb4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cc0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("cc1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cc2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cc3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cc4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cd0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cd1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cd2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cd3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cd4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ce0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ce1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ce2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ce3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ce4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cf0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cf1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cf2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("cf3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cf4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cg0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("cg1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cg2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cg3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("cg4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("ch0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ch1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ch2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ch3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ch4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("ci0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ci1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ci2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ci3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("ci4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                            if("cj0000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cj1000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cj2000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cj3000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }
                            if("cj4000".equals(registDetailDto.getData())){
                                if(registDetailDto.getCurrentValue()!=null)
                                    registDetailDto.setCurrentValue((new BigDecimal(registDetailDto.getCurrentValue()).setScale(2, RoundingMode.HALF_UP)).toString());
                            }

                        }
                    }


                    // 设置修改前值
                    String oldTableName = getTableName(detailList, tableName);

                    if(null != oldTableName) {
                        sb.setLength(0);
                        sb.append("old");
                        setValue(detailList, oldTableName, null, targetClass,sb,pageId);
                    }
                }
                catch (ClassNotFoundException e) {
                    this.getLogger().error("ViewCompareDetailController", e);
                }
            }


            //过滤掉隐藏字段
            List<RegistDetailDto> returndetailList =new ArrayList<RegistDetailDto>();
            for(RegistDetailDto registDetailDto : detailList){
                if(!StringUtil.equals("false",registDetailDto.getVisibleFlag())){
                    returndetailList.add(registDetailDto);
                }
                if(StringUtil.isEmpty(registDetailDto.getCurrentValue())){
                    registDetailDto.setCurrentValue("");
                }
                if(StringUtil.isEmpty(registDetailDto.getOldValue())){
                    registDetailDto.setOldValue("");
                }
            }
            return returndetailList;
        }
        return null;
    }

    /**
     * 设置属性值
     * @param detailList 明细信息列表
     * @param tableName 表名
     * @param registSeqNo 登记序列号
     * @param targetClass 目标class
     * @param sb 修改前后标志
     */
    private void setValue(List<RegistDetailDto> detailList, String tableName, String registSeqNo,
                          Class<?> targetClass,StringBuilder sb,String pageId) {
        Field[] fields = targetClass.getDeclaredFields();
        Map<String, Object> searchParam = new HashMap<>();
        //新人行表名称过长导致需要修改
        if("PBC_PRD_BASEINFO_END_SUB_NEW_HIS".equals(tableName)){
            tableName =  "PBC_PRD_BASEEND_SUB_NEW_HIS";
        }
        searchParam.put("tableName", tableName);
        List<Map<String, Object>> where = new ArrayList<>();
        searchParam.put("condition", where);
        //数据状态
        String status = null;
        //oder语句
        String oder = null;
        //报送状态
        String sendStatus = null;
        // 数据类型
        String type = null;
        // 原识别标识
        String orgIndentifyMark = null;
        if (null != registSeqNo) {
            Map<String, Object> registSeqNoMap = new HashMap<>(2);
            if(searchParam.get("tableName").equals("CHB_EQU_SUB") || searchParam.get("tableName").equals("CHB_EQUDET_SUB")
                    || searchParam.get("tableName").equals("CHB_EMPLOYEE_INFO_SUB")){
                registSeqNoMap.put("field", "SUMIT_SEQ");
            }
            else{
                registSeqNoMap.put("field", "REGIST_SEQNO");
            }
            registSeqNoMap.put("value", "'" + registSeqNo + "'");
            where.add(registSeqNoMap);

//            Map<String, Object> instMap = new HashMap<>(2);
//            if(CommonUtil.isBAmodel() && StringUtil.equals("CHB_ASSET_SUB_STATUS",tableName)){
//                instMap.put("field", "INSTITUTION_CODE");
//                instMap.put("value", "'$$$$$$'");
//                where.add(instMap);
//            }

        }
        else {
            for (Field field : fields) {
                BusinessKey busKey = field.getAnnotation(BusinessKey.class);
                if (null != busKey) {
                    String keyField = busKey.value();
                    String tmpKeyField = CommonUtil.dealString(StringUtil.lowerCase(keyField), false);
                    if(StringUtil.equals("registBankCode", tmpKeyField)){
                        tmpKeyField = "bankCode";
                    }
                    for (RegistDetailDto detail : detailList) {
                        if (StringUtil.equalsIgnoreCase(detail.getData(), tmpKeyField)) {
                            Map<String, Object> conditionMap = new HashMap<>(2);
                            field.setAccessible(true);
                            conditionMap.put("field", keyField);
                            // 如果业务主键存在联合主键一个键值为空时，则查不出数据
                            if (StringUtil.isEmpty(detail.getCurrentValue())){
                                continue;
                            }
                            if(tmpKeyField.equals("ccy")){
                                if(CCYDictCodeList.from(detail.getCurrentValue())!=null){
                                    detail.setCurrentValue(CCYDictCodeList.from(detail.getCurrentValue()).getValue());
                                }
                            }
                            if (StringUtil.equals(detail.getType(), "date")) {
                                conditionMap.put("value", "DATE '" + DateUtil.format(
                                        new Date(Long.valueOf(detail.getCurrentValue()))) + "'");
                            }
                            else if (StringUtil.equals(detail.getType(), "datetime")) {
                                conditionMap.put("value", "TIMESTAMP '"
                                        +
                                        DateUtil.format(
                                                new Date(Long.valueOf(detail.getCurrentValue())),
                                                DateUtil.DEFAULT_DATETIME_FORMAT) + "'");
                            }
                            else {
                                conditionMap.put("value", "'" + detail.getCurrentValue() + "'");
                                // add by zhangliang 20210324 从业人员登记双击查看特殊处理 start
                                if (StringUtil.equals(pageId, "SvrEmployeeInfoReg001") && StringUtil.equals(tmpKeyField, "employeeType")){
                                    conditionMap.put("value", "'" + StringUtil.splitToList(" ", detail.getCurrentValue()).get(0) + "'");
                                }
                                // add by zhangliang 20210324 从业人员登记双击查看特殊处理 end
                            }
                            where.add(conditionMap);
                        }
                        if(StringUtil.equalsIgnoreCase(detail.getData(), "status")){
                            status = detail.getCurrentValue();
                        }
                        if(StringUtil.equalsIgnoreCase(detail.getData(), "sendStatus")||StringUtil.equalsIgnoreCase(detail.getData(), "submitStatus")){
                            sendStatus = detail.getCurrentValue();
                        }
                        if(StringUtil.equalsIgnoreCase(detail.getData(), "submitStatus")){
                            oder = " ORDER BY STATUS DESC,SUBMIT_STATUS";
                        }
                        else if(StringUtil.equalsIgnoreCase(detail.getData(), "sendStatus")){
                            oder = " ORDER BY STATUS DESC,SEND_STATUS";
                        }

                        // 数据类型
                        if(StringUtil.equalsIgnoreCase(detail.getData(), "type")){
                            type = detail.getCurrentValue();
                        }
                        // 原识别标识
                        if(StringUtil.equalsIgnoreCase(detail.getData(), "orgIndentifyMark")){
                            orgIndentifyMark = detail.getCurrentValue();
                        }
                    }
                }
            }

            // 如果是履历表
            if(StringUtil.endsWith(tableName, "_HIS")) {
                //判断是mysql还是oracle数据库
                if (BaseCommonCheckService.isMYSQLDataBase()) {
                    searchParam.put("order", " ORDER BY IFNULL(UPDATE_TIME,CREATE_TIME) DESC");
                }else{
                    searchParam.put("order", " ORDER BY NVL(UPDATE_TIME,CREATE_TIME) DESC");
                }


            }
            else {
                if(searchParam.get("tableName").equals("CHB_SALER_STL_DEAL_SUB")){
                    oder = " ORDER BY DATA_STATUS DESC,SEND_STATUS";
                }
                searchParam.put("order", oder);
            }
        }
        List<Map<String, Object>> dataResultList = mapper.selectDetail(searchParam);

        //ZG01修改已报送 产品代码去除INS
        if(tableName.equals("PBC_COL_PRD_BASEINFO_SUB_HIS")){
            if(dataResultList.size()>0){
                String oldPbcProdId=dataResultList.get(0).get("PBC_PROD_ID").toString().substring(0,3);
                if(oldPbcProdId !=null && oldPbcProdId.equals("INS")){
                    String pbcProdId=dataResultList.get(0).get("PBC_PROD_ID").toString().substring(3);
                    dataResultList.get(0).put("PBC_PROD_ID",pbcProdId);
                }
            }
        }
        //修改未报送 产品代码去除INS 会比较两条数据
        if(tableName.equals("PBC_COL_PRD_BASEINFO_SUB")){
            if(dataResultList.size()>1){
                String oldPbcProdId=dataResultList.get(1).get("PBC_PROD_ID").toString().substring(0,3);
                if(oldPbcProdId !=null && oldPbcProdId.equals("INS")){
                    String pbcProdId=dataResultList.get(1).get("PBC_PROD_ID").toString().substring(3);
                    dataResultList.get(1).put("PBC_PROD_ID",pbcProdId);
                }
            }
        }
        // 产品资金募集信息特殊处理：为0时候显示空
        if (StringUtil.equals(pageId, PageFiledList.产品资金募集信息.getPageId())) {
            dataResultList.forEach(map->{
                map.forEach((key, value)->{
                    if(Arrays.asList(PageFiledList.产品资金募集信息.getFiledArr()).contains(key) && StringUtil.isNotEmpty(value)
                            && BigDecimal.ZERO.compareTo((BigDecimal)value)==0) {
                        map.put(key, null);
                    }
                });
            });
        }


        // 识别标识变更的情况，用原识别标识查询比较数据
        try {
            if (sb.toString().equals("old")) {
                if (StringUtil.isNotBlank(orgIndentifyMark)) {
                    for (Map<String, Object> dataMap : where) {
                        if (StringUtil.equalsIgnoreCase((String) dataMap.get("field"), "INDENTIFY_MARK")) {
                            dataMap.put("value", "'" + orgIndentifyMark + "'");
                        }
                    }

//                    List<Map<String, Object>> tempDataResultList = mapper.selectDetail(searchParam);
//                    dataResultList.addAll(0, tempDataResultList);

                    dataResultList = mapper.selectDetail(searchParam);
                    if(CollectionUtils.isEmpty(dataResultList)){
                        searchParam.put("tableName", tableName.replace("_HIS", ""));
                        searchParam.put("order", oder);
                        dataResultList = mapper.selectDetail(searchParam);
                    }
                }
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (CollectionUtils.isNotEmpty(dataResultList)) {
            Map<String, Object> dataResult = dataResultList.get(0);
            if(!StringUtil.equals(sendStatus, DictCodeListExt2.报送状态_未报送.getValue()) || StringUtil.equals(status, DictCodeListExt2.报送数据状态_新建.getValue()) || sb.toString().equals("old")){
                dataResult = dataResultList.get(dataResultList.size()-1);
            }

            //add by leipeng 20200708 多法人模式下界面中的发行机构代码字段由界面数字填充
            if(CommonUtil.isBAmodel()){
                String issue_party_id = "";
                String REGIST_BANK_CODE = "";
                String prodid = "";
                for (RegistDetailDto data : detailList) {
                    //获取前台界面传入的登记编码
                    if(data.getData().equals("issuePartyId")){
                        issue_party_id = data.getCurrentValue();
                    }
                    //前台传入的登记编码修改
                    if(data.getData().equals("registBankCode")){
                        REGIST_BANK_CODE = data.getCurrentValue();
                    }
                    //获取前台界面传入的产品编号
                    if(data.getData().equals("prodId")){
                        prodid = data.getCurrentValue();
                    }
                }
                if(StringUtil.isEmpty(issue_party_id)&&StringUtil.isEmpty(REGIST_BANK_CODE)
                        &&StringUtil.isNotEmpty(prodid)){
                    issue_party_id = mapper.selectBankCodeByOrgCode(prodid);
                    REGIST_BANK_CODE = issue_party_id;
                }
                dataResult.put("ISSUE_PARTY_ID", issue_party_id);
                dataResult.put("REGIST_BANK_CODE", REGIST_BANK_CODE);
            }

            for (Map<String, Object> map : dataResultList) {
                if(!StringUtil.equals(DictCodeListExt2.数据类型_中债_识别标识变更.getValue(), map.get("TYPE"))){
                    map.put("ORG_INDENTIFY_MARK", "");
                }
            }
            // 添加产品登记编码
            if (dataResult.containsKey("PROD_ID")){
                ChbPrdresistCodeKey key = new ChbPrdresistCodeKey();
                if (dataResult.get("PROD_ID") != null){
                    key.setProdInsId((String)dataResult.get("PROD_ID"));
                    key.setFlag("2");
                    if (dataResult.get("PROD_REG_CODE") == null){
                        // 查询产品登记编码
                        ChbPrdresistCode value = chbPrdresistCodeMapper.selectByPrimaryKey(key);
                        if (value != null){
                            dataResult.put("PROD_REG_CODE",value.getRegCode());
                        }
                    }
                    if (dataResult.get("PROD_NAME") == null){
                        // 查询产品名称
                        ChbPrdBaseinfo prdinfo = chbPrdBaseinfoMapper.selectByProdid((String)dataResult.get("PROD_ID"));
                        if (prdinfo != null){
                            dataResult.put("PROD_NAME",prdinfo.getProdName());
                        }
                    }
                }
            }
            dataResult.forEach((key, value) -> {
                String field = CommonUtil.dealString(StringUtil.lowerCase(key), false);
                detailList.forEach(detail -> {
                    if ((StringUtil.equalsIgnoreCase(detail.getData(), field)  &&StringUtil.isNotEmpty(value))|| (StringUtil.equals("registBankCode", field) && StringUtil.equals("bankCode", detail.getData()))
                            ||(StringUtil.equals("identifyType", detail.getData())&&(StringUtil.equals("personalIdentifyType", field)||StringUtil.equals("institutionIdentifyType", field))
                            &&StringUtil.isNotEmpty(value))
                            // modify by zhangliang 20201215 资产要素登记 委外投资属性特殊处理
                            || (StringUtil.equals("proProperty1", detail.getData()) && StringUtil.equals("proProperty", field))
                    ) {
                        String v ="";
                        if(StringUtil.equals("personalIdentifyType", field)||StringUtil.equals("institutionIdentifyType", field)){
                            v = getValue(value, field, fields);
                        }
                        else{
                            v = getValue(value, detail.getData(), fields);

                        }
                        if (sb.toString().equals("current")) {
                            detail.setCurrentValue(v);
                        }
                        else {
                            detail.setHasOldValue(true);
                            detail.setOldValue(v);
                        }
                    }
                });
            });
        }
    }

    /**
     * 获取属性值
     * @param value Object类型的值
     * @param field 属性名
     * @param fields Field[]
     * @return 转换后的值
     */
    private String getValue(Object value, String field, Field[] fields) {
        String v = StringUtil.EMPTY;
        try {
            DictFormat dict = null;
            for(Field ff : fields) {
                if(StringUtil.equalsIgnoreCase(ff.getName(), field)) {
                    dict = ff.getAnnotation(DictFormat.class);
                    break;
                }
            }
            // 数据字典
            if (null != dict) {
                DictCode code = dict.value();
                if(value instanceof String) {
                    // 只显示value不显示 code value的格式添加
                    if (DictCode.报送反馈状态.getValue().equals(code.getValue())
                            || DictCode.人行报送反馈状态.getValue().equals(code.getValue())
                            || DictCode.资产_负债类别.getValue().equals(code.getValue())){
                        v = SysDataUtil.getNameFromDataDict(code, (String)value);
                    } else {
                        v = SysDataUtil.getZZDictCodeAndName(code, (String)value);
                        //update zhangweiqi 2021-5-12 HYXXDJZL-101  【资产要素登记】项目类标的--非标资产/新增可投资，双击打开时，融资人所属地区字段为空 begin
                        if(DictCode.行政区划_省市县.getValue().equals(code.getValue())&&"finArea".equals(field)&&!StringUtil.isNotEmpty(v)){
                            if(StringUtil.isNotEmpty(SvrDataUtil.getNameFromDataDict(DictCode.行政区划_省市县,(String)value))){

                                v = (String)value+" "+SysDataUtil.getNameFromDataDict(DictCode.行政区划_省市县, (String)value);
                            }
                            else if(StringUtil.isNotEmpty(SvrDataUtil.getNameFromDataDict(DictCode.行政区划_省,(String)value))){

                                v = (String)value+" "+SysDataUtil.getNameFromDataDict(DictCode.行政区划_省, (String)value);

                            }else if(StringUtil.isNotEmpty(SvrDataUtil.getNameFromDataDict(DictCode.行政区划_市,(String)value))){

                                v = (String)value+" "+SysDataUtil.getNameFromDataDict(DictCode.行政区划_市, (String)value);

                            }else if(StringUtil.isNotEmpty(SvrDataUtil.getNameFromDataDict(DictCode.行政区划_县,(String)value))){

                                v = (String)value+" "+SysDataUtil.getNameFromDataDict(DictCode.行政区划_县, (String)value);
                            }
                        }
                        //update zhangweiqi 2021-5-12 HYXXDJZL-101  【资产要素登记】项目类标的--非标资产/新增可投资，双击打开时，融资人所属地区字段为空 end
                    }
                }
            }
            // 日期类型
            else if (value instanceof TIMESTAMP) {
                try {
                    TIMESTAMP time = (TIMESTAMP)value;
                    Date date = DateUtil.parse(time.dateValue() + " " + time.timeValue());
                    v = String.valueOf(date.getTime());
                }
                catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            else if (value instanceof Timestamp) {
                Timestamp time = (Timestamp)value;
                v = String.valueOf(time.getTime());
            }
            // 数值类型
            else if (value instanceof Number) {
                v = StringUtil.numberToString((BigDecimal)value);
            }
            else if(value instanceof String){
                v = value.toString();
            }
            //add by zcw mysql date类型特殊处理 20220427
            else if(value instanceof Date){
                Date time = (Date)value;
                v = String.valueOf(time.getTime());
            }
            //add by zcw mysql date类型特殊处理 20220427
        }
        catch (SecurityException e) {
            this.getLogger().error("ViewCompareDetailController.getValue", e);
        }
        return v;
    }

    /**
     * 获取对比数据来源的表名
     * @param detailList 明细列表
     * @param tableName 主表名
     * @return 对比数据表名
     */
    private String getTableName(List<RegistDetailDto> detailList, String tableName) {
        // 数据状态
        String status = StringUtil.EMPTY;
        // 报送状态
        String sendStatus = StringUtil.EMPTY;
        for(RegistDetailDto detail :detailList) {
            if(StringUtil.equals(detail.getData(), "status")) {
                status = detail.getCurrentValue();
            }
            else if(StringUtil.equals(detail.getData(), "sendStatus")||StringUtil.equals(detail.getData(), "submitStatus")) {
                sendStatus = detail.getCurrentValue();
            }
        }
        // 新建未报送和新建已报送的，不存在比较数据
        if(StringUtil.equals(status, DictCodeListExt2.报送数据状态_新建.getValue())||StringUtil.equals(status, DictCodeListExt2.报送数据状态_新建.getCodeName())) {
            return null;
        }
        // 修改状态的数据
        if(StringUtil.equals(status, DictCodeListExt2.报送数据状态_修改.getValue())||StringUtil.equals(status, DictCodeListExt2.报送数据状态_修改.getCodeName())) {
            // 报送状态为已报送的和履历表最新数据比较
            if(StringUtil.equals(sendStatus, DictCodeListExt2.报送状态_手工处理.getValue())
                    || StringUtil.equals(sendStatus, DictCodeListExt2.报送状态_接口报送.getValue())) {
                return tableName + "_HIS";
            }
            // 报送状态为未报送的和当前表前一条数据（已报送）比较
            if(StringUtil.equals(sendStatus, DictCodeListExt2.报送状态_未报送.getValue())) {
                return tableName;
            }
        }
        // 删除状态的数据同修改已报送
        if(StringUtil.equals(status, DictCodeListExt2.报送数据状态_删除.getValue())) {
            return tableName + "_HIS";
        }
        return null;
    }
}
