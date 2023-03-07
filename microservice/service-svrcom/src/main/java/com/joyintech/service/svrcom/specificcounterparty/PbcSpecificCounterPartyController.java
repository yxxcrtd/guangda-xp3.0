package com.joyintech.service.svrcom.specificcounterparty;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.specificcounterparty.dto.PbcSpecificCounterParty001Dto;
import com.joyintech.tams.svreport.specificcounterparty.service.PbcSpecificCounterParty001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 特定目的载体对手方信息维护
 *
 * @author yangxianjiu
 * @date 2022/11/1
 */
@RestController
@RequestMapping("/PbcSpecificCounterParty")
public class PbcSpecificCounterPartyController {

    @Autowired
    private PbcSpecificCounterParty001Service pbcSpecificCounterParty001Service;

    /**
     * 分页查询
     *
     * @param pageParam
     * @param page      页
     * @param length
     * @return
     */
    @PostMapping("/selectForPage/{page}/{length}")
    public Page<PbcSpecificCounterParty001Dto> selectForPage(@RequestBody Map<String, Object> pageParam, @PathVariable("page") Integer page, @PathVariable("length") Integer length) {
        return pbcSpecificCounterParty001Service.selectForPage(page, length, pageParam);
    }

    /**
     * 双击详情
     *
     * @param paramMap
     * @return
     */
    @PostMapping("selectDetail")
    public PbcSpecificCounterParty001Dto selectDetail(@RequestBody Map<String, Object> paramMap) {
        return pbcSpecificCounterParty001Service.selectDetail(paramMap);
    }

    /**
     * @param paramMap
     * @return
     */
    @PostMapping("queryAssetCodeIndentifyMark")
    public List<SuggestDto> queryAssetCodeIndentifyMark(@RequestBody Map<String, Object> paramMap) {
        return pbcSpecificCounterParty001Service.queryAssetCodeIndentifyMark(paramMap);
    }

    /**
     * 保存
     *
     * @param dto
     */
    @PostMapping("save")
    public Integer save(@RequestBody PbcSpecificCounterParty001Dto dto) {
        return pbcSpecificCounterParty001Service.save(dto);
    }

    /**
     * 删除
     *
     * @param dto
     * @return
     */
    @PostMapping("delete")
    public Integer delete(@RequestBody PbcSpecificCounterParty001Dto dto) {
        return pbcSpecificCounterParty001Service.delete(dto);
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param param 导入
     * @return 处理结果
     * @throws Exception
     */
    @PostMapping("checkConfirmMsgForImportData")
    public AjaxResult checkConfirmMsgForImportData(@RequestBody Map<String, Object> param) {
        return pbcSpecificCounterParty001Service.checkConfirmMsgForImportData(param);
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
        return pbcSpecificCounterParty001Service.importData(param);
    }

    /**
     * 资产类 通过资产代码获取 交易对手机构名称
     * @param param
     * @return
     */
    @PostMapping("getPartyOrgNameByAssetCode")
    public Map<String, Object> getPartyOrgNameByAssetCode(@RequestBody Map<String, Object> param){
        return pbcSpecificCounterParty001Service.getPartyOrgNameByAssetCode(param);
    }

    /**
     * 根据 资产代码字段 查找 资产基本信息中 资产名称、 根据 识别标识 查找 投资者名称
     * @param param
     * @return
     */
    @PostMapping("getPartyProdNameByAssetCode")
    public Map<String, Object> getPartyProdNameByAssetCode(@RequestBody Map<String, Object> param){
        return pbcSpecificCounterParty001Service.getPartyProdNameByAssetCode(param);
    }

    /**
     * 根据 资产代码字段 查找 资产基本信息中 交易对手产品种类
     * @param param
     * @return
     */
    @PostMapping("getPartyProdTypeByAssetCode")
    public Map<String, Object> getPartyProdTypeByAssetCode(@RequestBody Map<String, Object> param){
        return pbcSpecificCounterParty001Service.getPartyProdTypeByAssetCode(param);
    }
}
