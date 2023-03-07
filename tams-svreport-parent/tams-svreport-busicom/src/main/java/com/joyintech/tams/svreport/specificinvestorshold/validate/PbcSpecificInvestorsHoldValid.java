package com.joyintech.tams.svreport.specificinvestorshold.validate;

import com.google.common.collect.Lists;
import com.joyintech.background.component.common.check.DataCheckCommonUtil;
import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.svreport.specificinvestorshold.dao.PbcSpecificInvestorsHold001Mapper;
import com.joyintech.tams.svreport.specificinvestorshold.dto.PbcSpecificInvestorsHold001Dto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 特定目的载体持有信息维护(投资者)
 * 数据检查
 *
 * @author yangxianjiu
 * @date 2022/11/4
 */
@Component
public class PbcSpecificInvestorsHoldValid implements BeanValidator {

    /**
     * 共通校验工具类
     */
    @Autowired
    private DataCheckCommonUtil chbDataCheckCommonUtil;

    @Autowired
    private PbcSpecificInvestorsHold001Mapper pbcSpecificInvestorsHold001Mapper;


    @Override
    public List<ValidMessage> valid(Object value) {
        PbcSpecificInvestorsHold001Dto entity = (PbcSpecificInvestorsHold001Dto)value;
        // 错误提示信息
        List<ValidMessage> errorList = Lists.newArrayList();
        if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, entity.getPageModel())) {
            List<PbcSpecificInvestorsHold001Dto> dtoList = pbcSpecificInvestorsHold001Mapper.checkExist(entity);
            if (CollectionUtils.isNotEmpty(dtoList)) {
                errorList.add(new ValidMessage("indentifyMark", "该理财产品的投资者已存在。"));
            }
            if (StringUtil.isNotEmpty(entity.getHoldDate())){
                if (DateUtil.compareDate(entity.getHoldDate(),DateUtil.platDateFromCache()) > 0){
                    errorList.add(new ValidMessage("holdDate", "持有日期不能超过当前日期。"));
                }
            }
        }
        // 数据校验
        errorList = chbDataCheckCommonUtil.chbCheckCommon(errorList, entity, null, null, ValidMessage.class);
        return errorList;
    }
}
