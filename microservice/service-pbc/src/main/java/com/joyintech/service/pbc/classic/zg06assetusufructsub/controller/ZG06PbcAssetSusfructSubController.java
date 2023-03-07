package com.joyintech.service.pbc.classic.zg06assetusufructsub.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.dto.ZG06PbcAssetSusfructSub;
import com.joyintech.tams.svreport.pbc.classic.zg06assetusufructsub.serivce.ZG06PbcAssetSusfructSubService001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
* ZG06资产收益权明细信息controller。
* @ClassName: ZG06PbcAssetSusfructSubController
* @author liuyu
* @date 2019年6月17日 上午10:38:00 
*
 */
@RestController
@RequestMapping("/ZG06PbcAssetSusfructSub")
public class ZG06PbcAssetSusfructSubController {
    /**
     * 产品终止信息service
     */
    @Autowired
    private ZG06PbcAssetSusfructSubService001 zg06PbcAssetSusfructSubService001;
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<ZG06PbcAssetSusfructSub> searchPage(@RequestBody Map<String, Object> param, @PathVariable String length) {
       Page<ZG06PbcAssetSusfructSub> page = zg06PbcAssetSusfructSubService001.searchPage(param);
       return page;
   }

    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return zg06PbcAssetSusfructSubService001.checkData(map);
    }
   /**
    * 手工处理功能
    *
    * @return 提示信息
    * @param map
    *            条件map
    * @seea
    * @since 1.0
    */
   @PostMapping("updateSumStaus")
   public void updateSumStaus(@RequestBody Map<String, Object> map) {
       zg06PbcAssetSusfructSubService001.doManul(map);
   }
   /**
    * 取消手工处理功能
    *
    * @return 提示信息
    * @param map
    *            条件map
    * @seea
    * @since 1.0
    */
   @PostMapping("cancelMannul")
   public String cancelMannul(@RequestBody Map<String, Object> map) {
       return zg06PbcAssetSusfructSubService001.doCancelManul(map);
   }

    /**
     * 人行产品代码下拉框
     */
    @PostMapping("getPbcProdId")
    public List<SelectOptionDto> getPbcProdId(@RequestBody Map<String, Object> map) {
        return zg06PbcAssetSusfructSubService001.getPbcProdId(map);
    }

}
