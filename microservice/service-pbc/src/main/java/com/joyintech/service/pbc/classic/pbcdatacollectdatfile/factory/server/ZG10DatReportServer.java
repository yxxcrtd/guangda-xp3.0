package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.server;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG10DatReportMapper;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg10pbccolreport.dto.ZG10PbcColReportDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ZG10DatReportServer
 * @Description:
 * @Author:
 * @Date: 20221114
 */
@Service
public class ZG10DatReportServer extends PbcColDatFactoryServer<ZG10PbcColReportDto> {
    //例子：如RATE字段要保留5位小数  put("RATE","#0.00000");
    private Map<String,String> specialKey = null;

    @Autowired
    private ZG10DatReportMapper zG10DatReportMapper;


    @Override
    public String getDatCode() {
        return "ZG10";
    }

    @Override
    public PbcColDatFactoryServer getPClass(String reportNo) {
        if(StringUtil.isNotEmpty(reportNo)&& "ZG10".equals(reportNo)){
            return new ZG10DatReportServer();
        }
        return null;
    }

    @Override
    public String getPExportConfig() {
        return "/com/joyintech/tams/svreport/pbc/classic/zg10assetconfiguration/conf/export_conf.xml";
    }

    @Override
    public void batchDelete(String UUid) {
        zG10DatReportMapper.updateUuidSub(UUid);

    }

    @Override
    public List<ExcelValidDto> getPSameKeyData(List resultMap) {
        //1.3.1 查询是否有重复数据
        List<ExcelValidDto> list = new ArrayList<>();

        List<Map<String,String>> keysList = zG10DatReportMapper.getSameKeyList(resultMap);

        //1.3.2 重复数据组装
//        if(StringUtil.isNotEmpty(keysList)){
//            for(Map<String,String> str: keysList){
//                list.add(new ExcelValidDto("债券等资产配置情况信息："+ DateUtil.format(DateUtil.parse(str.get("termid")),"yyyy年MM月"),
//                        "","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
//            }
//        }
        if(CollectionUtils.isNotEmpty(keysList) && keysList.size()>0){
            list.add(new ExcelValidDto("债券等资产配置情况信息："+ DateUtil.format(DateUtil.parse(keysList.get(0).get("TERMID")),"yyyy年MM月"),
                    "","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> getPListByUUid(String uuid) {

        return zG10DatReportMapper.getPListByUUid(uuid);
    }

    @Override
    public Boolean updatePDatDataStatus(String uuid) {

        return zG10DatReportMapper.updatePDatDataStatus(uuid);
    }

    @Override
    public Boolean updatePDataStatus(List dataList) {
        return zG10DatReportMapper.updatePDataStatus(dataList);
    }

    @Override
    public List<Map<String, Object>> getPDefDataList(List dataList, String uuid) {
        //暂不处理
        return null;
    }

    @Override
    public String buildPDatFile(List dataList) {
        //1.6.1 定义文件内容字段													详细字段拼接顺序参考【dat文件拼接逻辑】

        //1.6.2  加密处理             仅ZG06和ZG07有

        //1.6.3 数据组装并返回
        //调用 【2.11 公共组装（入参.业务数据，【登记字符顺序 】，【私有特殊数字类型】）】

        return buildDatFileData(dataList,super.getKeyList(this.getPExportConfig()),specialKey);
    }

    @Override
    public List<ExcelValidDto> getPDataCheck(List dataList,String type) {
        //暂不明确。不处理
        return null;
    }

    @Override
    public Boolean batchPInsert(List dataList, String UUid, String fileDate) {
        //插入dat表 先执行 Dat表再执行登记。
        zG10DatReportMapper.deleteByDat(dataList);
        zG10DatReportMapper.insertDat(dataList,UUid);

        //插入登记表
        zG10DatReportMapper.deleteBySub(dataList);
        zG10DatReportMapper.insertSub(dataList,UUid);
        return true;
    }

    @Override
    public int getTableWidth() {
        return 150;
    }

//    @Override
//    public List getDataList(List<Map<String,Object>> dataList) {
//        dataList =  super.columnKeyChange(dataList,super.getKeyList(getPExportConfig()));
//        List<ZG01PbcPrdBaseinfoSub> resultList = new ArrayList<>();
//        for(Map<String,Object> map : dataList){
//            resultList.add(BeanUtil.mapToObject(map,ZG01PbcPrdBaseinfoSub.class));
//        }
//        return resultList;
//    public Map<String,String> getDefMap() {
//        return specialKey;
//    }
        @Override
        public Map<String,String> getDefMap() {
            return specialKey;
        }

}
