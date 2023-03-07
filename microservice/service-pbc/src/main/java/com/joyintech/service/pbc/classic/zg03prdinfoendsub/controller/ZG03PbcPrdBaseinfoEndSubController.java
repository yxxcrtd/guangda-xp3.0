package com.joyintech.service.pbc.classic.zg03prdinfoendsub.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.serivce.ZG03PrdBaseinfoEndSubService001;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 
* ZG03产品终止信息controller。
* @ClassName: ZG03PbcPrdBaseinfoEndSubController
* @author liuyu
* @date 2019年6月17日 上午10:38:00 
*
 */
@RestController
@RequestMapping("/ZG03PbcPrdBaseinfoEndSub")
public class ZG03PbcPrdBaseinfoEndSubController {
    /**
     * 产品终止信息service
     */
    @Autowired
    private ZG03PrdBaseinfoEndSubService001 ZG03PbcPrdBaseinfoEndSubService001;
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<ZG03PbcPrdBaseinfoEndSub> searchPage(@RequestBody Map<String, Object> param) {
       Page<ZG03PbcPrdBaseinfoEndSub> page = ZG03PbcPrdBaseinfoEndSubService001.searchPage(param);
       return page;
   }

    @PostMapping("reportCheck")
    public List<ExcelValidDto> reportCheck(@RequestBody Map<String, Object> map) {
        return ZG03PbcPrdBaseinfoEndSubService001.checkData(map);
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
       ZG03PbcPrdBaseinfoEndSubService001.doManul(map);
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
       return ZG03PbcPrdBaseinfoEndSubService001.doCancelManul(map);
   }

    /**
     * 人行产品代码下拉框
     */
    @PostMapping("getPbcProdId")
    public List<SelectOptionDto> getPbcProdId(@RequestBody Map<String, Object> map) {
        return ZG03PbcPrdBaseinfoEndSubService001.getPbcProdId(map);
    }

    /**
     * 产品代码下拉框
     */
    @PostMapping("getProdId")
    public List<SelectOptionDto> getProdId(@RequestBody Map<String, Object> map) {
        return ZG03PbcPrdBaseinfoEndSubService001.getProdId(map);
    }
}
