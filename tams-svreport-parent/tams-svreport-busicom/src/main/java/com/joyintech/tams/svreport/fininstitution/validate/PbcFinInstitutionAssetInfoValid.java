package com.joyintech.tams.svreport.fininstitution.validate;

import com.google.common.collect.Lists;
import com.joyintech.background.component.common.check.DataCheckCommonUtil;
import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.svreport.fininstitution.dao.PbcFinInstitutionAssetInfo001Mapper;
import com.joyintech.tams.svreport.fininstitution.dto.PbcFinInstitutionAssetInfo001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 表内资产数据维护
 *
 * @author yangxianjiu
 * @date 2022/11/23
 */
@Component
public class PbcFinInstitutionAssetInfoValid implements BeanValidator {

    /**
     * 共通校验工具类
     */
    @Autowired
    private DataCheckCommonUtil chbDataCheckCommonUtil;

    @Autowired
    private PbcFinInstitutionAssetInfo001Mapper pbcFinInstitutionAssetInfo001Mapper;

    @Override
    public List<ValidMessage> valid(Object value) {
        PbcFinInstitutionAssetInfo001Dto entity = (PbcFinInstitutionAssetInfo001Dto) value;
        // 错误提示信息
        List<ValidMessage> errorList = Lists.newArrayList();
        if (StringUtil.equals(GlobalConstant.DETAIL_PAGE_MODEL_NEW, entity.getPageModel())) {
            PbcFinInstitutionAssetInfo001Dto dto = pbcFinInstitutionAssetInfo001Mapper.checkExist(entity);
            if (dto != null) {
                errorList.add(new ValidMessage("termId", "系统已存在对应统计年月的记录"));
            }
        }
        // 数据校验 结果
        errorList = chbDataCheckCommonUtil.chbCheckCommon(errorList, entity, null, null, ValidMessage.class);

        return errorList;
    }
}
