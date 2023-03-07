package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.server;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG09DatReportMapper;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg09assetdebtterm.dto.ZG09PbcColReport;
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
public class ZG09DatReportServer extends PbcColDatFactoryServer<ZG09PbcColReport> {
    //例子：如RATE字段要保留5位小数  put("RATE","#0.00000");
    private Map<String,String> specialKey = null;

    //暂时放着，解析excel似乎可以直接拿到，不确认顺序是否完全一致
//    private List<String> keyList = Arrays.asList(
//      "issuePartyId","pbcProdType","g000aStr","g000bStr","g000cStr","g000dStr","g000eStr"
//      ,"g100aStr","g100bStr","g100cStr","g100dStr","g100eStr","g200aStr","g200bStr","g200cStr","g200dStr","g200eStr"
//      ,"g300aStr","g300bStr","g300cStr","g300dStr","g300eStr","g400aStr","g400bStr","g400cStr","g400dStr","g400eStr"
//      ,"g500aStr","g500bStr","g500cStr","g500dStr","g500eStr","g300aStr","g600bStr","g600cStr","g600dStr","g600eStr"
//      ,"g700aStr","g700bStr","g700cStr","g700dStr","g700eStr","g800aStr","g800bStr","g800cStr","g800dStr","g800eStr"
//      ,"g00001Str","g00002Str"
//    );


    @Autowired
    private ZG09DatReportMapper zG09DatReportMapper;

    @Override
    public String getDatCode() {
        return "ZG09";
    }

    @Override
    public PbcColDatFactoryServer getPClass(String reportNo) {
        if(StringUtil.isNotEmpty(reportNo)&& "ZG09".equals(reportNo)){
            return new ZG09DatReportServer();
        }
        return null;
    }

    @Override
    public String getPExportConfig() {
        return "/com/joyintech/tams/svreport/pbc/classic/zg09assetdebtterm/conf/export_conf.xml";
    }

    @Override
    public void batchDelete(String UUid) {
        zG09DatReportMapper.updateUuidSub(UUid);

    }

    @Override
    public List<ExcelValidDto> getPSameKeyData(List resultMap) {
        //1.3.1 查询是否有重复数据
        List<ExcelValidDto> list = new ArrayList<>();

        List<Map<String,String>> keysList = zG09DatReportMapper.getSameKeyList(resultMap);

        //1.3.2 重复数据组装
//        if(StringUtil.isNotEmpty(keysList)){
//            for(Map<String,String> str: keysList){
//                list.add(new ExcelValidDto("资产负债剩余期限信息："+ DateUtil.format(DateUtil.parse(str.get("termid")),"yyyy年MM月"),"","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
//            }
//        }
        if(CollectionUtils.isNotEmpty(keysList) && keysList.size()>0){
            list.add(new ExcelValidDto("资产负债剩余期限信息："+ DateUtil.format(DateUtil.parse(keysList.get(0).get("termid")),"yyyy年MM月"),
                    "","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> getPListByUUid(String uuid) {

        return zG09DatReportMapper.getPListByUUid(uuid);
    }

    @Override
    public Boolean updatePDatDataStatus(String uuid) {

        return zG09DatReportMapper.updatePDatDataStatus(uuid);
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
        return zG09DatReportMapper.updatePDataStatus(dataList);
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
        zG09DatReportMapper.deleteByDat(dataList);
        zG09DatReportMapper.insertDat(dataList,UUid);

        //插入登记表
        zG09DatReportMapper.deleteBySub(dataList);
        zG09DatReportMapper.insertSub(dataList,UUid);
        return true;
    }
}
