package com.joyintech.service.pbc.classic.zg04prdcashraisesub.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.dto.ZG04PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.zg04prdcashraisesub.service.ZG04PbcPrdCashRaiseSubService001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 
* ZG04产品资金募集信息controller。
* @ClassName: ZG04PbcPrdCashRaiseSubController
* @author liuyu
* @date 2019年6月17日 上午10:38:00 
*
 */
@RestController
@RequestMapping("/ZG04PbcPrdCashRaiseSub")
public class ZG04PbcPrdCashRaiseSubController {
    /**
     * 产品资金募集信息service
     */
    @Autowired
    private ZG04PbcPrdCashRaiseSubService001 zg04pbcPrdCashRaiseSubService001;
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<ZG04PbcProdRaiseSub> searchPage(@RequestBody Map<String, Object> param) {
       Page<ZG04PbcProdRaiseSub> page = zg04pbcPrdCashRaiseSubService001.searchPage(param);
       return page;
   }

    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return zg04pbcPrdCashRaiseSubService001.checkData(map);
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
       zg04pbcPrdCashRaiseSubService001.doManul(map);
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
       return zg04pbcPrdCashRaiseSubService001.doCancelManul(map);
   }

    /**
     * 人行产品代码下拉框
     */
    @PostMapping("getPbcProdId")
    public List<SelectOptionDto> getPbcProdId(@RequestBody Map<String, Object> map) {
        return zg04pbcPrdCashRaiseSubService001.getPbcProdId(map);
    }

    /**
     * 产品代码下拉框
     */
    @PostMapping("getProdId")
    public List<SelectOptionDto> getProdId(@RequestBody Map<String, Object> map) {
        return zg04pbcPrdCashRaiseSubService001.getProdId(map);
    }
}
