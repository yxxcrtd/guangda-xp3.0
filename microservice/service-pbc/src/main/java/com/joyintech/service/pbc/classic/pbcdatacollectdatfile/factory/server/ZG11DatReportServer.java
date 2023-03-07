package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.server;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG11DatReportMapper;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg11companybondinfosub.dto.ZG11CompanyBondInfoSubDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: ZG05DatReportServer
 * @Description:
 * @Author: liubaoquan
 * @Date: 20221112 17:58
 */
@Service
public class ZG11DatReportServer extends PbcColDatFactoryServer<ZG11CompanyBondInfoSubDto> {
    //例子：如RATE字段要保留5位小数  put("RATE","#0.00000");
    private Map<String,String> specialKey = new HashMap<>();

    //暂时放着，解析excel似乎可以直接拿到，不确认顺序是否完全一致
//    private List<String> keyList = Arrays.asList("issuePartyId","prodVariety"
//            ,"c00000Str","c01000Str","c02000Str","c03000Str","c04000Str","c10000Str","c11000Str","c12000Str","c13000Str","c14000Str"
//            ,"c20000Str","c21000Str","c22000Str","c23000Str","c24000Str","c30000Str","c31000Str","c32000Str","c33000Str","c34000Str"
//            ,"c40000Str","c41000Str","c42000Str","c43000Str","c44000Str","c50000Str","c51000Str","c52000Str","c53000Str","c54000Str"
//            ,"c60000Str","c61000Str","c62000Str","c63000Str","c64000Str","c70000Str","c71000Str","c72000Str","c73000Str","c74000Str"
//            ,"c80000Str","c81000Str","c82000Str","c83000Str","c84000Str","c90000Str","c91000Str","c92000Str","c93000Str","c94000Str"
//            ,"ca0000Str","ca1000Str","ca2000Str","ca3000Str","ca4000Str","cb0000Str","cb1000Str","cb2000Str","cb3000Str","cb4000Str"
//            ,"cc0000Str","cc1000Str","cc2000Str","cc3000Str","cc4000Str","cd0000Str","cd1000Str","cd2000Str","cd3000Str","cd4000Str"
//            ,"ce0000Str","ce1000Str","ce2000Str","ce3000Str","ce4000Str","cf0000Str","cf1000Str","cf2000Str","cf3000Str","cf4000Str"
//            ,"cg0000Str","cg1000Str","cg2000Str","cg3000Str","cg4000Str","ch0000Str","ch1000Str","ch2000Str","ch3000Str","ch4000Str"
//            ,"ci0000Str","ci1000Str","ci2000Str","ci3000Str","ci4000Str","cj0000Str","cj1000Str","cj2000Str","cj3000Str","cj4000Str"
//    );


    @Autowired
    private ZG11DatReportMapper zG11DatReportMapper;


    @Override
    public String getDatCode() {
        return "ZG11";
    }
    @Override
    public PbcColDatFactoryServer getPClass(String reportNo) {
        if(StringUtil.isNotEmpty(reportNo)&& "ZG11".equals(reportNo)){
            return new ZG11DatReportServer();
        }
        return null;
    }

    @Override
    public String getPExportConfig() {
        return "/com/joyintech/tams/svreport/pbc/classic/zg11companybondinfosub/conf/export_conf.xml";
    }

    @Override
    public void batchDelete(String UUid) {
        zG11DatReportMapper.updateUuidSub(UUid);
    }

    @Override
    public List<ExcelValidDto> getPSameKeyData(List resultMap) {
        //1.3.1 查询是否有重复数据
        List<ExcelValidDto> list = new ArrayList<>();

        List<Map<String,String>> keysList = zG11DatReportMapper.getSameKeyList(resultMap);

        //1.3.2 重复数据组装
//        if(StringUtil.isNotEmpty(keysList)){
//            for(Map<String,String> str: keysList){
//                list.add(new ExcelValidDto("企业债券分行业、企业规模情况信息："+ DateUtil.format(DateUtil.parse(str.get("termid")),"yyyy年MM月"),"","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
//            }
//        }
        if(CollectionUtils.isNotEmpty(keysList) && keysList.size()>0){
            list.add(new ExcelValidDto("企业债券分行业、企业规模情况信息："+ DateUtil.format(DateUtil.parse(keysList.get(0).get("termid")),"yyyy年MM月"),
                    "","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> getPListByUUid(String uuid) {

        return zG11DatReportMapper.getPListByUUid(uuid);
    }

    @Override
    public Boolean updatePDatDataStatus(String uuid) {

        return zG11DatReportMapper.updatePDatDataStatus(uuid);
    }

    @Override
    public int getTableWidth() {
        return 100;
    }

    @Override
    public Map<String,String> getDefMap() {
        return specialKey;
    }

    @Override
    public Boolean updatePDataStatus(List dataList) {
        return zG11DatReportMapper.updatePDataStatus(dataList);
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
        zG11DatReportMapper.deleteByDat(dataList);
        zG11DatReportMapper.insertDat(dataList,UUid);

        //插入登记表
        zG11DatReportMapper.deleteBySub(dataList);
        zG11DatReportMapper.insertSub(dataList,UUid);
        return true;
    }
}
