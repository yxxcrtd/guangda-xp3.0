package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.server;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG08DatReportMapper;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg08specounterequity.dto.ZG08SpeCounterQuityDto;
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
 * @ClassName: ZG08DatReportServer
 * @Description:
 * @Author:
 * @Date: 20221114
 */
@Service
public class ZG08DatReportServer extends PbcColDatFactoryServer<ZG08SpeCounterQuityDto> {

    //例子：如RATE字段要保留5位小数  put("RATE","#0.00000");
    private Map<String,String> specialKey = null;

    //暂时放着，解析excel似乎可以直接拿到，不确认顺序是否完全一致
    private List<String> keyList = Arrays.asList();

    @Autowired
    private SqlSessionTemplate template;

    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;
    @Autowired
    private ZG08DatReportMapper zG08DatReportMapper;

    @Override
    public String getDatCode() {
        return "ZG08";
    }

    @Override
    public PbcColDatFactoryServer getPClass(String reportNo) {
        if(StringUtil.isNotEmpty(reportNo)&& "ZG08".equals(reportNo)){
            return new ZG08DatReportServer();
        }
        return null;
    }

    @Override
    public String getPExportConfig() {
        return "/com/joyintech/tams/svreport/pbc/classic/zg08specounterequity/conf/export_conf.xml";
    }

    @Override
    public void batchDelete(String UUid) {
        zG08DatReportMapper.updateUuidSub(UUid);

    }

    @Override
    public List<ExcelValidDto> getPSameKeyData(List<Map<String,Object>> resultMap) {
        //1.3.1 查询是否有重复数据
        List<ExcelValidDto> list = new ArrayList<>();
        List<Map<String,String>> keysList=new ArrayList<>();

        if(CollectionUtils.isNotEmpty(resultMap)){
            Set<Object> allIdSet = resultMap.stream().map(e -> e.get("termId")).collect(Collectors.toSet());
            keysList = zG08DatReportMapper.getSameKeyList(allIdSet);
        }

        //1.3.2 重复数据组装
//        if(StringUtil.isNotEmpty(keysList)){
//            int i=0;
//            for(Map<String,String> str: keysList){
//                list.add(new ExcelValidDto("特定目的载体交易对手信息："+ DateUtil.format(DateUtil.parse(str.get("TERMID")),"yyyy年MM月"),
//                        "第"+(i+2)+"行","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
//                i++;
//            }
//        }
        if(CollectionUtils.isNotEmpty(keysList) && keysList.size()>0){
            list.add(new ExcelValidDto("特定目的载体交易对手信息："+ DateUtil.format(DateUtil.parse(keysList.get(0).get("TERMID")),"yyyy年MM月"),
                    "","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
        }

        return list;
    }


    @Override
    public List<Map<String, Object>> getPListByUUid(String uuid) {

        return zG08DatReportMapper.getPListByUUid(uuid);
    }

    @Override
    public Boolean updatePDatDataStatus(String uuid) {

        return zG08DatReportMapper.updatePDatDataStatus(uuid);
    }

    @Override
    public Boolean updatePDataStatus(List dataList) {
        return zG08DatReportMapper.updatePDataStatus(dataList);
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
        zG08DatReportMapper.deleteByDat(dataList.subList(0,1));
        insertDataMergeDat(dataList.subList(0,1),UUid);

        //插入登记表
        zG08DatReportMapper.deleteBySub(dataList.subList(0,1));
        insertDataMergeSub(dataList,UUid);
        return true;
    }
    private void insertDataMergeDat(List dataList,String UUid) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            ZG08DatReportMapper batchMapper = session.getMapper(ZG08DatReportMapper.class);
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
            ZG08DatReportMapper batchMapper = session.getMapper(ZG08DatReportMapper.class);
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
//        List<ZG08SpeCounterQuityDto> resultList = new ArrayList<>();
//        for(Map<String,Object> map : dataList){
//            resultList.add(BeanUtil.mapToObject(map,ZG08SpeCounterQuityDto.class));
//        }
//        return resultList;}
//    public Map<String,String> getDefMap() {
//        return specialKey;
//    }
        @Override
        public Map<String,String> getDefMap() {
            return specialKey;
        }
}
