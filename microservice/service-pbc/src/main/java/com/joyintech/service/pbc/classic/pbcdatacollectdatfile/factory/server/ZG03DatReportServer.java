package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.server;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG03DatReportMapper;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg03prdinfoendsub.dto.ZG03PbcPrdBaseinfoEndSub;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName: ZG01DatReportServer
 * @Description:
 * @Author: liubaoquan
 * @Date: 20221112 17:58
 */
@Service
public class ZG03DatReportServer extends PbcColDatFactoryServer<ZG03PbcPrdBaseinfoEndSub> {
    //例子：如RATE字段要保留5位小数  put("RATE","#0.00000");
    private Map<String,String> specialKey = new HashMap<>();

    //暂时放着，解析excel似乎可以直接拿到，不确认顺序是否完全一致
//    private List<String> keyList = Arrays.asList(
//           "pbcProdId","mdateStr","ccy","profit",
//            "profitCny","customProfit","customProfitCny","cusYearYield"
//    );

    @Autowired
    private SqlSessionTemplate template;

    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;
    @Autowired
    private ZG03DatReportMapper zG03DatReportMapper;

    @Override
    public String getDatCode() {
        return "ZG03";
    }

    public ZG03DatReportServer(){
        //specialKey初始化
        specialKey.put("cusYearYield","#0.00000");
    }
    @Override
    public PbcColDatFactoryServer getPClass(String reportNo) {
        if(StringUtil.isNotEmpty(reportNo)&& "ZG03".equals(reportNo)){
            return new ZG03DatReportServer();
        }
        return null;
    }

    @Override
    public String getPExportConfig() {
        return "/com/joyintech/tams/svreport/pbc/classic/zg03prdinfoendsub/conf/export_conf.xml";
    }

    @Override
    public void batchDelete(String UUid) {
        zG03DatReportMapper.updateUuidSub(UUid);

    }

    @Override
    public List<ExcelValidDto> getPSameKeyData(List resultMap) {
        //1.3.1 查询是否有重复数据
        List<ExcelValidDto> list = new ArrayList<>();

        List<Map<String,String>> keysList = zG03DatReportMapper.getSameKeyList(resultMap);

        //1.3.2 重复数据组装
        if(StringUtil.isNotEmpty(keysList)){
            for(Map<String,String> str: keysList){
                list.add(new ExcelValidDto("产品代码："+str.get("PBC_PROD_ID"),"","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
            }
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> getPListByUUid(String uuid) {

        return zG03DatReportMapper.getPListByUUid(uuid);
    }

    @Override
    public Boolean updatePDatDataStatus(String uuid) {

        return zG03DatReportMapper.updatePDatDataStatus(uuid);
    }

    @Override
    public int getTableWidth() {
        return 150;
    }

    @Override
    public Map<String,String> getDefMap() {
        return specialKey;
    }

    @Override
    public Boolean updatePDataStatus(List dataList) {
        return zG03DatReportMapper.updatePDataStatus(dataList);
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
        zG03DatReportMapper.deleteByDat(dataList);
        insertDataMergeDat(dataList,UUid);

        //插入登记表
        zG03DatReportMapper.deleteBySub(dataList);
        insertDataMergeSub(dataList,UUid);
        return true;
    }
    private void insertDataMergeDat(List dataList,String UUid) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            ZG03DatReportMapper batchMapper = session.getMapper(ZG03DatReportMapper.class);
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
            ZG03DatReportMapper batchMapper = session.getMapper(ZG03DatReportMapper.class);
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
}
