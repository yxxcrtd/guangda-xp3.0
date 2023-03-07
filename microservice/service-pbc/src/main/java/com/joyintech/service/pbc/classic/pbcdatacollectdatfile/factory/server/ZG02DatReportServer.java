package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.server;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG02DatReportMapper;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg02prdinitraisesub.dto.ZG02PbcPrdInitRaiseSub;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ZG02DatReportServer
 * @Description:
 * @Author:
 * @Date: 20221114 17:58
 */
@Service
public class ZG02DatReportServer extends PbcColDatFactoryServer<ZG02PbcPrdInitRaiseSub> {
    //例子：如RATE字段要保留5位小数  put("RATE","#0.00000");
    private Map<String,String> specialKey = null;

    @Autowired
    private ZG02DatReportMapper zG02DatReportMapper;
    @Autowired
    private SqlSessionTemplate template;

    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;


    @Override
    public String getDatCode() {
        return "ZG02";
    }
    @Override
    public PbcColDatFactoryServer getPClass(String reportNo) {
        if(StringUtil.isNotEmpty(reportNo)&& "ZG02".equals(reportNo)){
            return new ZG02DatReportServer();
        }
        return null;
    }

    @Override
    public String getPExportConfig() {
        return "/com/joyintech/tams/svreport/pbc/classic/zg02prdinitraisesub/conf/export_conf.xml";
    }

    @Override
    public void batchDelete(String UUid) {
        zG02DatReportMapper.updateUuidSub(UUid);
    }

    @Override
    public List<ExcelValidDto> getPSameKeyData(List resultMap) {
        //1.3.1 查询是否有重复数据
        List<ExcelValidDto> list = new ArrayList<>();

        List<Map<String,String>> keysList = zG02DatReportMapper.getSameKeyList(resultMap);

        //1.3.2 重复数据组装
        if(CollectionUtils.isNotEmpty(keysList)){
            int i=2;
            for(Map<String,String> str: keysList){
                list.add(new ExcelValidDto("产品代码："+str.get("PBC_PROD_ID")+",币种："+str.get("CCY")+",地区："+str.get("BUY_PLACE")+",客户类型："+str.get("CUSTOMER_TYPE1"),
                        String.valueOf(i),"已报送或删除的数据，不支持修改。请在人行客户端修改！"));
                i++;
            }
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> getPListByUUid(String uuid) {

        return zG02DatReportMapper.getPListByUUid(uuid);
    }

    @Override
    public Boolean updatePDatDataStatus(String uuid) {

        return zG02DatReportMapper.updatePDatDataStatus(uuid);
    }

    @Override
    public Boolean updatePDataStatus(List dataList) {
        return zG02DatReportMapper.updatePDataStatus(dataList);
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
        zG02DatReportMapper.deleteByDat(dataList);
        insertDataMergeDat(dataList,UUid);

        //插入登记表
        zG02DatReportMapper.deleteBySub(dataList);
        insertDataMergeSub(dataList,UUid);
        return true;
    }
    private void insertDataMergeDat(List dataList,String UUid) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            ZG02DatReportMapper batchMapper = session.getMapper(ZG02DatReportMapper.class);
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
            ZG02DatReportMapper batchMapper = session.getMapper(ZG02DatReportMapper.class);
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


    @Override
    public int getTableWidth() {
        return 150;
    }

//    @Override
//    public List getDataList(List<Map<String,Object>> dataList) {
//        dataList =  super.columnKeyChange(dataList,super.getKeyList(getPExportConfig()));
//        List<ZG02PbcPrdInitRaiseSub> resultList = new ArrayList<>();
//        for(Map<String,Object> map : dataList){
//            resultList.add(BeanUtil.mapToObject(map,ZG02PbcPrdInitRaiseSub.class));
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
