package com.joyintech.service.chb.gd.risk.valtblassetcheck.dao;

import com.joyintech.service.chb.gd.risk.valtblassetcheck.dto.RskValAssetCheckDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RskValAssetCheckMapper {

    List<RskValAssetCheckDto> queryForPage(Map<String, Object> pageParam);

}
