package com.joyintech.tams.svreport.pbc.classic.zg05prdassetdebt.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author liuyu
 * @version 1.0 2019年6月17日
 * @see ZG05PbcColPrdAssetDebtSubMService
 * @since 1.0
 */
@Service
public class ZG05PbcColPrdAssetDebtSubMService extends BaseService<ZG05PbcColPrdAssetDebtDto> {
   
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   public Page<ZG05PbcColPrdAssetDebtDto> searchPage(Map<String, Object> param) {
       return restTemplate.postForPage(
               SvrServiceApplication.PBC_SERVICE_NAME + "/ZG05PbcColPrdAssetDebtController/findPage",param,
               ZG05PbcColPrdAssetDebtDto.class);
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
   public void updateSumStaus(Map<String, Object> map) {
       restTemplate.postForObject(
               SvrServiceApplication.PBC_SERVICE_NAME + "/ZG05PbcColPrdAssetDebtController/updateSumStaus",map,
               Integer.class);
   }


    /**
     * 数据检查
     * @param map 查询条件
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public List<ExcelValidDto> reportCheck(Map<String,Object> map) {
        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG05PbcColPrdAssetDebtController/reportCheck", map,
                ExcelValidDto.class);
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
   public String cancelMannul(Map<String, Object> map) {
          return restTemplate.postForObject(
               SvrServiceApplication.PBC_SERVICE_NAME + "/ZG05PbcColPrdAssetDebtController/cancelMannul",map,
               String.class);
   }

    /***
     * 条件查询导出
     * @param list
     * @return
     */
    public List<ZG05PbcColPrdAssetDebtDto> searchByUuid(List<String> list) {
        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG05PbcColPrdAssetDebtController/searchByUuid",list,
                ZG05PbcColPrdAssetDebtDto.class);
    }

    /**
     * 勾选数据查询导出
     * @param paraMap
     * @return
     */
    public List<ZG05PbcColPrdAssetDebtDto> searchByExcel(Map<String, Object> paraMap) {
        return restTemplate.postForList(
                SvrServiceApplication.PBC_SERVICE_NAME + "/ZG05PbcColPrdAssetDebtController/searchByExcel",paraMap,
                ZG05PbcColPrdAssetDebtDto.class);
    }
}
