package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.server;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG01DatReportMapper;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.dto.ZG01PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service.ZG01PbcPrdBaseinfoSubService001;
import org.apache.commons.collections4.CollectionUtils;
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
public class ZG01DatReportServer extends PbcColDatFactoryServer<ZG01PbcPrdBaseinfoSub> {
    //例子：如RATE字段要保留5位小数  put("RATE","#0.00000");
    private Map<String,String> specialKey = new HashMap(){{
        put("prodStage", "#");
    }};


    @Autowired
    private ZG01DatReportMapper zG01DatReportMapper;
    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private com.joyintech.tams.svreport.pbc.classic.zg01prdbaseinfosub.service.ZG01PbcPrdBaseinfoSubService001
            zG01PbcPrdBaseinfoSubService001;
    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;
    @Override
    public String getDatCode() {
        return "ZG01";
    }

    @Override
    public PbcColDatFactoryServer getPClass(String reportNo) {
        if(StringUtil.isNotEmpty(reportNo)&& "ZG01".equals(reportNo)){
            return new ZG01DatReportServer();
        }
        return null;
    }

    @Override
    public String getPExportConfig() {
        return "/com/joyintech/tams/svreport/pbc/classic/zg01prdbaseinfosub/conf/export_conf.xml";
    }

    @Override
    public void batchDelete(String UUid) {
        zG01DatReportMapper.updateUuidSub(UUid);
    }


    @Override
    public List<ExcelValidDto> getPSameKeyData(List resultMap) {
        //1.3.1 查询是否有重复数据
        List<ExcelValidDto> list = new ArrayList<>();

        //查询产品已报送的数据
        List<Map<String,String>> keysList = zG01DatReportMapper.getSameKeyList(resultMap);
        List<Map<String,Object>> result = resultMap;
        //1.3.2 如果导入信息类型= 1 新增的类型，则提示不给导入
        if(StringUtil.isNotEmpty(keysList)){
            int i=1;
            for (Map<String, Object> map : result) {
                i++;
                for(Map<String,String> str: keysList){
                    if(StringUtil.equals(str.get("PROD_ID"),map.get("prodId"))){
                        if(StringUtil.equals("1",map.get("infoType"))){
                            list.add(new ExcelValidDto("产品代码："+str.get("PROD_ID")+",信息类型："+map.get("INFO_TYPE"),String.valueOf(i),"已报送的数据，不支持新增。请修改信息类型！已报送的产品代码如下产品"+str.get("PROD_ID")));
                            break;
                        }
                    }
                }
            }
            }


        return list;
    }


    @Override
    public List<Map<String, Object>> getPListByUUid(String uuid) {
        return zG01DatReportMapper.getPListByUUid(uuid);
    }

    @Override
    public Boolean updatePDatDataStatus(String uuid) {
        //将数据移入his表中
        List<ZG01PbcPrdBaseinfoSub> listSub=zG01DatReportMapper.getListByUUid(uuid);
        //调用手工处理
        zG01PbcPrdBaseinfoSubService001.cancleManuelParam(listSub);

        return zG01DatReportMapper.updatePDatDataStatus(uuid);
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
        return zG01DatReportMapper.updatePDataStatus(dataList);
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
        zG01DatReportMapper.deleteByDat(dataList);
        insertDataMergeDat(dataList,UUid);

        //插入登记表
//        zG01DatReportMapper.deleteBySub(dataList);
        List<Map<String,Object>> data = dataList;
        for (Map<String, Object> datum : data) {
            if(StringUtil.equals("1",datum.get("infoType"))){
                zG01DatReportMapper.deleteBySub1(datum);
                continue;
            }
            if(StringUtil.equals("2",datum.get("infoType"))){
                zG01DatReportMapper.deleteBySub2(datum);
            }
        }
        insertDataMergeSub(dataList,UUid);
        return true;
    }
    private void insertDataMergeDat(List dataList,String UUid) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            ZG01DatReportMapper batchMapper = session.getMapper(ZG01DatReportMapper.class);
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
            ZG01DatReportMapper batchMapper = session.getMapper(ZG01DatReportMapper.class);
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
