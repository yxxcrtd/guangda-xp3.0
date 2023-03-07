package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.server;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG05DatReportMapper;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.zg05pbccolprdassetdebt.dto.ZG05PbcColPrdAssetDebtDto;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: ZG05DatReportServer
 * @Description:
 * @Author: liubaoquan
 * @Date: 20221112 17:58
 */
@Service
public class ZG05DatReportServer extends PbcColDatFactoryServer<ZG05PbcColPrdAssetDebtDto> {
    //例子：如RATE字段要保留5位小数  put("RATE","#0.00000");
    private Map<String,String> specialKey = null;
    @Autowired
    private SqlSessionTemplate template;

    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;

    //暂时放着，解析excel似乎可以直接拿到，不确认顺序是否完全一致
//    private List<String> keyList = Arrays.asList(
//           "pbcProdId","ccy","statusType", "a0000Str","a1000Str","a2000Str","a2100Str"
//            ,"a2110Str","a2120Str","a2200Str","a2210Str","a2220Str","a3000Str","a3100Str"
//            ,"a3200Str","a4000Str","a4100Str","a4200Str","a4300Str","a4400Str","a4500Str"
//            ,"a4600Str","a4700Str","a4800Str","a5000Str","a5100Str","a5200Str","a5210Str"
//            ,"a5220Str","a5230Str","a5240Str","a5250Str","a5260Str","a5261Str","a5270Str"
//            ,"a5271Str","a5272Str","a5273Str","a5274Str","a5275Str","a5276Str","a5277Str"
//            ,"a5278Str","a5279Str","a527aStr","a5280Str","a6000Str","a6100Str","a6200Str"
//            ,"a6300Str","a6400Str","a6500Str","a6600Str","a6700Str","a7000Str","a7100Str"
//            ,"a7110Str","a7120Str","a7200Str","a7210Str","a7220Str","a7230Str","a7240Str"
//            ,"a7250Str","a7260Str","a7270Str","a7280Str","a7290Str","a72a0Str","a7300Str"
//            ,"a7310Str","a7320Str","a7400Str","a8000Str","a8100Str","a8200Str","a9000Str"
//            ,"aa000Str","ab000Str","ac000Str","d0000Str","b0000Str","b1000Str","b1100Str"
//            ,"b1200Str","b1210Str","b1220Str","b1230Str","b1240Str","b1250Str","b1260Str"
//            ,"b1261Str","b1270Str","b1271Str","b1272Str","b1273Str","b1274Str","b1275Str"
//            ,"b1276Str","b1277Str","b1278Str","b1279Str","b127aStr","b1280Str","b2000Str"
//            ,"b3000Str","b3100Str","b3200Str","b4000Str","b5000Str","c0000Str","c1000Str"
//            ,"c1100Str","c1110Str","c1120Str","c1130Str","c1140Str","c1150Str","c1160Str"
//            ,"c1170Str","c1171Str","c1172Str","c1173Str","c1174Str","c1175Str","c1176Str"
//            ,"c1177Str","c1178Str","c1179Str","c117aStr","c1180Str","c1200Str","c1210Str"
//            ,"c1220Str","c1230Str","c1240Str","c1250Str","c1260Str","c1270Str","c1271Str"
//            ,"c1272Str","c1273Str","c1274Str","c1275Str","c1276Str","c1277Str","c1278Str"
//            ,"c1279Str","c127aStr","c1280Str","c3000Str","c4000Str"
//    );


    @Autowired
    private ZG05DatReportMapper zG05DatReportMapper;

    @Override
    public String getDatCode() {
        return "ZG05";
    }

    @Override
    public PbcColDatFactoryServer getPClass(String reportNo) {
        if(StringUtil.isNotEmpty(reportNo)&& "ZG05".equals(reportNo)){
            return new ZG05DatReportServer();
        }
        return null;
    }

    @Override
    public String getPExportConfig() {
        return "/com/joyintech/tams/svreport/pbc/classic/zg05prdassetdebt/conf/export_conf.xml";
    }

    @Override
    public List<ExcelValidDto> getPSameKeyData(List<Map<String,Object>> resultMap) {
        //1.3.1 查询是否有重复数据
        List<ExcelValidDto> list = new ArrayList<>();
        List<Map<String,String>> keysList=new ArrayList<>();


        if(CollectionUtils.isNotEmpty(resultMap)){
            Set<Object> allIdSet = resultMap.stream().map(e -> e.get("termId")).collect(Collectors.toSet());
            keysList = zG05DatReportMapper.getSameKeyList(allIdSet);
        }

        //1.3.2 重复数据组装
//        if(StringUtil.isNotEmpty(keysList)){
//            for(Map<String,String> str: keysList){
//                list.add(new ExcelValidDto("资管产品资产负债信息："+ DateUtil.format(DateUtil.parse(str.get("termid")),"yyyy年MM月"),"","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
//            }
//        }
        if(CollectionUtils.isNotEmpty(keysList) && keysList.size()>0){
            list.add(new ExcelValidDto("资管产品资产负债信息："+ DateUtil.format(DateUtil.parse(keysList.get(0).get("termid")),"yyyy年MM月"),
                    "","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> getPListByUUid(String uuid) {

        return zG05DatReportMapper.getPListByUUid(uuid);
    }

    @Override
    public Boolean updatePDatDataStatus(String uuid) {

        return zG05DatReportMapper.updatePDatDataStatus(uuid);
    }

    @Override
    public int getTableWidth() {
        return 500;
    }

    @Override
    public Map<String,String> getDefMap() {
        return specialKey;
    }

    @Override
    public Boolean updatePDataStatus(List dataList) {
        return zG05DatReportMapper.updatePDataStatus(dataList);
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
    public Boolean batchPInsert(List<Map<String,Object>> dataList, String UUid, String fileDate) {
        //补充内部产品代码
        if(dataList!=null) {
            Map<String, String> prodIdPbcProdIdMap = getProdIdByPbcProdId();
            dataList.forEach(map -> {
                if (map.get("pbcProdId") != null) {
                    String prodId= prodIdPbcProdIdMap.get(map.get("pbcProdId"));
                    if(prodId==null||"".equals(prodId)) {
                        map.put("prodId", prodIdPbcProdIdMap.get("INS"+map.get("pbcProdId")));
                    }else{
                        map.put("prodId",prodId);
                    }
                }
                map.put("startdate", com.joyintech.tams.report.interf.common.constant.DateUtil.getFirstDay(com.joyintech.tams.report.interf.common.constant.DateUtil.parse(fileDate)));
                map.put("enddate", com.joyintech.tams.report.interf.common.constant.DateUtil.getLastDay(com.joyintech.tams.report.interf.common.constant.DateUtil.parse(fileDate)));
            });
        }
        //插入dat表 先执行 Dat表再执行登记。
        if (null != dataList) {
            zG05DatReportMapper.deleteByDat(dataList.subList(0, 1));
            insertDataMergeDat(dataList.subList(0, 1), UUid);

            //插入登记表
            zG05DatReportMapper.deleteBySub(dataList.subList(0, 1));
        }
        insertDataMergeSub(dataList, UUid);
        return true;
    }

    @Override
    public void batchDelete(String UUid) {
        zG05DatReportMapper.updateUuidSub(UUid);

    }

    private void insertDataMergeDat(List dataList,String UUid) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            ZG05DatReportMapper batchMapper = session.getMapper(ZG05DatReportMapper.class);
            int batchCount = reportInsertLongCount == 0 ? 50 : reportInsertLongCount;// 每批commit的个数
            int batchLastIndex = batchCount;// 每批最后一个的下标
            for (int index = 0; index < dataList.size();) {
                if (batchLastIndex > dataList.size() - 1) {
                    batchLastIndex = dataList.size();
                    batchMapper.insertDat(dataList.subList(index, batchLastIndex),UUid);
                    break;// 数据插入完毕,退出循环
                } else {
                    batchMapper.insertDat(dataList.subList(index, batchLastIndex),UUid);
                    index = batchLastIndex;// 设置下一批下标
                    batchLastIndex = index + batchCount;
                }
                count++;
                // 每20次提交
                if (count % 20 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        } finally {
            session.close();
        }
    }

    private void insertDataMergeSub(List dataList,String UUid) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            ZG05DatReportMapper batchMapper = session.getMapper(ZG05DatReportMapper.class);
            int batchCount = reportInsertLongCount == 0 ? 50 : reportInsertLongCount;// 每批commit的个数
            int batchLastIndex = batchCount;// 每批最后一个的下标
            for (int index = 0; index < dataList.size();) {
                if (batchLastIndex > dataList.size() - 1) {
                    batchLastIndex = dataList.size();
                    batchMapper.insertSub(dataList.subList(index, batchLastIndex),UUid);
                    break;// 数据插入完毕,退出循环
                } else {
                    batchMapper.insertSub(dataList.subList(index, batchLastIndex),UUid);
                    index = batchLastIndex;// 设置下一批下标
                    batchLastIndex = index + batchCount;
                }
                count++;
                // 每20次提交
                if (count % 20 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        } finally {
            session.close();
        }
    }


    /**
     * 获取产品代码，产品内部代码键值对
     * @return
     */
    private Map<String,String> getProdIdByPbcProdId(){
        List<Map<String,Object>> prodIdPbcProdIdList= zG05DatReportMapper.getProdIdPbcProdIdList();
        Map<String,String> prodIdPbcProdIdMap = new HashMap<>();
        if(prodIdPbcProdIdList!=null){
            for(Map<String,Object> map :prodIdPbcProdIdList){
                prodIdPbcProdIdMap.put((String) map.get("pbcprodid"),(String)map.get("prodid"));
            }
        }
        return prodIdPbcProdIdMap;
    }
}
