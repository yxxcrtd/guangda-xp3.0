package com.joyintech.tams.svreport.specificcounterparty.validate;

import com.google.common.collect.Lists;
import com.joyintech.background.component.common.check.DataCheckCommonUtil;
import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.svreport.seq.dao.SysSeqMapper;
import com.joyintech.tams.common.svreport.seq.dto.SysSeqModel;
import com.joyintech.tams.svreport.specificcounterparty.dao.PbcSpecificCounterParty001Mapper;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 特定目的载体对手方信息维护
 * 数据校验
 *
 * @author yangxianjiu
 * @date 2022/11/2
 */
@Component
public class PbcSpecificCounterPartyValid implements BeanValidator {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(PbcSpecificCounterPartyValid.class);

    public static final  List<String> SPV_LIST = Arrays.asList("1","2","3","4");

    private static String CHECK_NUM="^[0-9]$";

    public static final String COUNTER_PARTY = "COUNTER_PARTY";

    /**
     * 共通校验工具类
     */
    @Autowired
    private DataCheckCommonUtil chbDataCheckCommonUtil;

    @Autowired
    private PbcSpecificCounterParty001Mapper pbcSpecificCounterParty001Mapper;

    /**
     * 校验数据合法性
     *
     * @param value 校验对象
     * @return 错误信息
     */
    @Override
    public List<ValidMessage> valid(Object value) {
        // 类型强转
        PbcSpecificCounterParty001Dto entity = (PbcSpecificCounterParty001Dto) value;
        // 错误提示信息
        List<ValidMessage> errorList = Lists.newArrayList();
        if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, entity.getPageModel())) {
            PbcSpecificCounterParty001Dto dto = pbcSpecificCounterParty001Mapper.checkExist(entity);
            if (dto != null) {
                errorList.add(new ValidMessage("partyProdName", "该资产已存在"));
            }
        }

        // 数据校验
        errorList = chbDataCheckCommonUtil.chbCheckCommon(errorList, entity, null, null, ValidMessage.class);

        return errorList;
    }

}
