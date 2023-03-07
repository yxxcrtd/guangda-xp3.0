/*
 * 文件名：MstYieldCurveInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年8月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.service;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.base.service.BaseCommonCheckService;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dao.ChbBranchNetworkMapper;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbbranchnetwork.dto.ChbBranchNetworkDto;

import com.joyintech.tams.svreport.chb.common.util.ExcelUtil;
import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 网点信息映射表管理
 *
 * @author zhoubin
 * @version 1.0 2022年05月16日
 * @see ChbBranchNetworkService
 * @since 1.0
 */

@Service
public class ChbBranchNetworkService extends CommonExportService<ChbBranchNetworkDto> {

    private final Logger logger = LoggerFactory.getLogger(ChbBranchNetworkService.class);

    public static final String CONFIG_FILE = "/com/joyintech/tams/svreport/chb/businessdatamaintain/chbbranchnetwork/conf/export_conf.xml";
    /**
     * 表名
     */
    public static final String TABLE_NAME = "CHB_BRANCH_NETWORK";
    /**
     * 接口类型
     */
    public static final String CHECK_FLAG = "D";

    /**
     * 删除
     */
    public static final String DELETE = "DELETE";
    /**
     * 新建
     */
    public static final String NEW = "NEW";

    /**
     * 分页大小
     */
    public static final int PAGE_SIZE = 200;


    @Resource
    private ChbBranchNetworkMapper chbBranchNetworkMapper;
    @Resource
    private BaseCommonCheckService baseCommonCheckService;
    @Resource
    private BatchInsertService batchInsertService;


    /**
     * 网点信息映射表管理分页查询
     *
     * @param param 查询参数
     * @return 返回查询结果
     */
    public Page<ChbBranchNetworkDto> selectForPage(Map<String, Object> param) {
        return super.queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), () -> chbBranchNetworkMapper.select(param));
    }

    /**
     * 查询所有数据验证删除是否存在
     *
     * @param pageParam 查询参数
     * @return 查询结果
     */
    public List<ChbBranchNetworkDto> selectAllList(Map<String, Object> pageParam) {
        return chbBranchNetworkMapper.select(pageParam);
    }


    /**
     * 导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
        exportParam.config(CONFIG_FILE).dataSource(DataSourceType.MASERT);
    }

    @Override
    public Page<ChbBranchNetworkDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 设置导出单元格
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if (StringUtil.equals(propName, "tradeDate")) {
            cell.setCellValue(DateUtil.format((Date) value));
        }
    }

    /**
     * 导入数据文件之前检查数据
     *
     * @param fileName 导入的文件名
     * @return 处理结果
     */
    public AjaxResult checkConfirmMsgForImportData(String fileName) {

        logger.info("===========================导入校验===========================");
        AjaxResult webResult = new AjaxResult();
        // 检查文件名是否包含汇率行情信息
        if (!fileName.contains("网点信息映射表")) {
            webResult.setMessage(this.getMessage("ImportFileName"), MessageType.错误消息);
            return webResult;
        }
        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);
        List<List<String>> tempList = ExcelUtil.getTempList1(fileName);
        List<String> headList = new ArrayList<>(4);
        headList.add("网点编号");
        headList.add("网点名称");
        headList.add("网点所属监管");
        headList.add("网点所属地区");
        //校验文件中列是否一致
        List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
        if (!StringUtil.isEmptyList(tempCheckResult)) {
            webResult.setMessage(this.getMessage("ImportFileTemplate"), MessageType.错误消息);
            return webResult;
        }

        //List<List<String>>转成List<Map<String, String>>
        List<Map<String, String>> mapList = this.convertMapList(dataList);
        //check包含DB和业务CHECK
        List<ExcelValidDto> excelValidList = baseCommonCheckService.checkDataAll(CHECK_FLAG, mapList, TABLE_NAME, fileName, TABLE_NAME, TABLE_NAME, null, null);
        //总数据
        int count = mapList.size();
        //待处理数据
        long countA = mapList.stream().filter(e -> Objects.equals("A", e.get("STATUS"))).count();
        //无法接入系统的数据
        long countR = excelValidList.stream().filter(e -> Objects.equals("0未导入", e.getValidKey())).count();
        webResult.setMessage(this.getMessage("ChbImportMessage", count - countA, countA+countR,0), MessageType.确认消息);
        //按500条分页
        List<List<Map<String, String>>> partitionList = Lists.partition(mapList, PAGE_SIZE);
        //数据重复的个数
        int duplicateCount = 0;
         for (List<Map<String, String>> list:partitionList) {
             int i = chbBranchNetworkMapper.selectChbBranchNetworkCount(list);
             if (i>0){
                 duplicateCount+=i;
             }
         }
         //存在重复数据提示错误
         if (duplicateCount>0) {
             List<JoyinMessage> joyinMessages = new ArrayList<>(1);
             JoyinMessage ms = new JoyinMessage(this.getMessage("ChbImportDuplicateMessage",duplicateCount), MessageType.确认消息);
             joyinMessages.add(ms);
             webResult.setJoyinMessages(joyinMessages);
         }
        return webResult;
    }


    /**
     * 导入数据文件
     *
     * @param fileName 导入的文件名
     * @return 导入结果，包含验证内容，如果验证失败，返回message
     */
    public List<ExcelValidDto> importData(String fileName) {
        //根据文件名获取文件数据
        List<List<String>> dataList = ExcelUtil.getDataList1(fileName);

        //List<List<String>>转成List<Map<String, String>>
        List<Map<String, String>> mapList = this.convertMapList(dataList);

        //check包含DB和业务CHECK
        List<ExcelValidDto> excelValidList = baseCommonCheckService.checkDataAll(CHECK_FLAG, mapList, TABLE_NAME, fileName, TABLE_NAME, TABLE_NAME, null, null);

        List<ChbBranchNetworkDto> dtoList = this.convertToBean(mapList);
        //小于998条数据，批量分页插入
        if ( dtoList.size() < 998 ) {
            //按200条分页
            List<List<ChbBranchNetworkDto>> partitionList = Lists.partition(dtoList, PAGE_SIZE);
            //批量入库
            for (List<ChbBranchNetworkDto> list:partitionList) {
                chbBranchNetworkMapper.insertChbBranchNetwork(list);
            }
        } else {
            //sqlloader插入
            ImpTable table = new ImpTable(TABLE_NAME);
            table.setColumns(ImportGenerator.createImpTableColumns(table.getName()));
            // 临时文件存放目录
            String tmpPath = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);

            batchInsertService.exeBatchldr(tmpPath, dtoList, table);
        }


        return excelValidList;
    }

    /**
     * 增强check list转换为Bean
     *
     * @return 返回检查结果
     */
    private List<ChbBranchNetworkDto> convertToBean(List<Map<String, String>> mapList) {


        List<ChbBranchNetworkDto> returnList = new LinkedList<>();
        ChbBranchNetworkDto dto = null;
        for (Map<String, String> map : mapList) {
            dto = new ChbBranchNetworkDto();
            if (map.containsKey("STATUS")) {
               continue;
            }
            if (map.containsKey("BRANCH_NO")) {
                dto.setBranchNo(map.get("BRANCH_NO"));
            }
            if (map.containsKey("BRANCH_NAME")) {
                dto.setBranchName(map.get("BRANCH_NAME"));
            }
            if (map.containsKey("BRANCH_FSA")) {
                dto.setBranchFsa(map.get("BRANCH_FSA"));
            }
            if (map.containsKey("NETWORK_NO")) {
                dto.setNetworkNo(map.get("NETWORK_NO"));
            }
            returnList.add(dto);
        }
        return returnList;
    }


    /**
     * 数据操作，新增修改删除
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void submit(ChbBranchNetworkDto dto) {
        List<ChbBranchNetworkDto> list = new ArrayList<>(1);
        //删除状态是已删除的数据
        chbBranchNetworkMapper.deleteChbBranchNetworkByKey(dto.getBranchNo(),  DictCodeListExt2.数据状态_删除.getValue());
        list.add(dto);
        chbBranchNetworkMapper.insertChbBranchNetwork(list);
    }

    /**
     * 删除
     * @param dto
     */
    public void delete(ChbBranchNetworkDto dto) {
        dto.getSelectedItem().forEach(e->{e.setStatus(DictCodeListExt2.数据状态_删除.getValue());});
        chbBranchNetworkMapper.updateStatus(dto.getSelectedItem());
    }


    public Integer getChbBranchNetworkByKey(ChbBranchNetworkDto dto) {
        dto.setStatus(DictCodeListExt2.数据状态_有效.getValue());
        return chbBranchNetworkMapper.getChbBranchNetworkByKey(dto);
    }


    /**
     * List<List<String>>转成List<Map<String, String>>
     *
     * @param dataList
     * @return
     */
    private List<Map<String, String>> convertMapList(List<List<String>> dataList) {

        List<Map<String, String>> mapList = new LinkedList<>();
        for (int i = 0; i < dataList.size(); i++) {
            List<String> list = dataList.get(i);
            Map<String, String> map = new LinkedHashMap<>(4);
            map.put("BRANCH_NO", list.get(0));
            map.put("BRANCH_NAME",list.get(1));
            map.put("BRANCH_FSA", StringUtils.isNotBlank(list.get(2))?list.get(2).split(" ")[0]:null);
            map.put("NETWORK_NO", StringUtils.isNotBlank(list.get(3))?list.get(3).split(" ")[0]:null);
            mapList.add(map);
        }

        return mapList;

    }
}
