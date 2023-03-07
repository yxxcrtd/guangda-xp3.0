package com.joyintech.service.chb.gd.risk.valtblassetcheck.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.chb.gd.risk.valtblassetcheck.dao.RskValAssetCheckMapper;
import com.joyintech.service.chb.gd.risk.valtblassetcheck.dto.RskValAssetCheckDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("backRskValAssetCheckService")
public class RskValAssetCheckService extends BaseService<RskValAssetCheckDto> {

    @Autowired
    private RskValAssetCheckMapper rskValAssetCheckMapper;


    public Page<RskValAssetCheckDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        Page<RskValAssetCheckDto> pages;
        if(StringUtil.isNotEmpty(pageParam.get("ptlId"))){
            pageParam.put("ptlIdList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("ptlId")));
        }
        if (StringUtil.isNotEmpty(page)) {
            pages = super.queryForPage(page, length, () -> rskValAssetCheckMapper.queryForPage(pageParam));
        }else {
            pages = new Page<>();
            pages.setRoot(rskValAssetCheckMapper.queryForPage(pageParam));
        }
        return pages;
    }
}
