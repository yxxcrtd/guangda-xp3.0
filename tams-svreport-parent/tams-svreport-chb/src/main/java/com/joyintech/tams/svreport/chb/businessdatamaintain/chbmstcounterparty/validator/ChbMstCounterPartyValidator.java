package com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.validator;

import com.joyintech.background.component.common.check.DataCheckCommonUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.dao.ChbMstCounterPartyDao;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstcounterparty.dto.ChbMstCounterPartyDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cff
 * @date 2022-05-06 17:38
 */
@Component
public class ChbMstCounterPartyValidator implements BeanValidator {

    @Autowired
    private DataCheckCommonUtil chbDataCheckCommonUtil;

    @Autowired
    private ChbMstCounterPartyDao chbMstCounterPartyDao;
    @Override
    public List<ValidMessage> valid(Object value) {
        ChbMstCounterPartyDto dto = (ChbMstCounterPartyDto) value;
        if (dto.getOperateType().equals("submit")) {
            List<ValidMessage> resultList = new ArrayList<ValidMessage>();
            if (StringUtil.isEmpty(dto.getUuid())
                //新建
                ||!StringUtil.equals("INFO",dto.getInfo())) {
                ChbMstCounterPartyDto resultDto = chbMstCounterPartyDao.selectByCounterId(dto.getCounterId());
                if (null != resultDto) {
                    resultList.add(new ValidMessage("counterId", "交易对手已存在"));
                }
            }
//            if(null !=dto.getUnitPriceFull() && null !=dto.getUnitPriceNet()){
//                if(dto.getUnitPriceFull().compareTo(dto.getUnitPriceNet())<0){
//                    resultList.add(new ValidMessage("unitPriceFull","单位成交价（全价）不能小于单位成交价（净价）"));
//                }
//            }
            // add by zhangliang 20210126 中债check调用 start
            resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, null, null, ValidMessage.class);
            Map<String, Object> errMap = new HashMap<>();
            List<ValidMessage> result = new ArrayList<>();
            resultList.forEach(validMessage -> {
                if (!errMap.containsKey(validMessage.getMessage())) {
                    result.add(validMessage);
                    errMap.put(validMessage.getMessage(), validMessage.getMessage());
                }
            });
            resultList.clear();
            resultList.addAll(result);
            //modify by tuqirong 2022-01-10 end 错误信息去重处理
            return resultList;
        }
        return null;
    }
}
