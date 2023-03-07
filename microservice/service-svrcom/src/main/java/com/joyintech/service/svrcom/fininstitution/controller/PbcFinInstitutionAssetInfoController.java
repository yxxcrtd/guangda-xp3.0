package com.joyintech.service.svrcom.fininstitution.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.fininstitution.dto.PbcFinInstitutionAssetInfo001Dto;
import com.joyintech.tams.svreport.fininstitution.service.PbcFinInstitutionAssetInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 表内资产数据维护 服务
 *
 * @author yangxianjiu
 * @date 2022/11/23
 */
@RestController
@RequestMapping("/PbcFinInstitutionAssetInfo")
public class PbcFinInstitutionAssetInfoController {

    @Autowired
    private PbcFinInstitutionAssetInfo001Service pbcFinInstitutionAssetInfo001Service;

    /**
     * 分页查询
     *
     * @param pageParam
     * @param page      页
     * @param length
     * @return
     */
    @PostMapping("/searchPage/{page}/{length}")
    public Page<PbcFinInstitutionAssetInfo001Dto> searchPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") Integer page, @PathVariable("length") Integer length) {
        return pbcFinInstitutionAssetInfo001Service.searchPage(page, length, pageParam);
    }

    /**
     * 双击详情
     *
     * @param paramMap
     * @return
     */
    @PostMapping("selectDetail")
    public PbcFinInstitutionAssetInfo001Dto selectDetail(@RequestBody Map<String, Object> paramMap) {
        return pbcFinInstitutionAssetInfo001Service.selectDetail(paramMap);
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PostMapping("delete")
    public Integer delete(@RequestBody PbcFinInstitutionAssetInfo001Dto dto) {
        return pbcFinInstitutionAssetInfo001Service.delete(dto);
    }

    /**
     * 保存
     *
     * @param dto
     */
    @PostMapping("save")
    public Integer save(@RequestBody PbcFinInstitutionAssetInfo001Dto dto) {
        return pbcFinInstitutionAssetInfo001Service.save(dto);
    }

    /**
     * 导入数据文件
     *
     * @param param 文件名
     * @return 校验结果
     */
    @SuppressWarnings("unchecked")
    @PostMapping("importData")
    public List<ExcelValidDto> importData(@RequestBody Map<String, Object> param) {
        return pbcFinInstitutionAssetInfo001Service.importData(param);
    }
}
