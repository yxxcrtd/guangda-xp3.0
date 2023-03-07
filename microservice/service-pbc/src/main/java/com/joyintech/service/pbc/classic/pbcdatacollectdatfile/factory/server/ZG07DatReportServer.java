package com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.server;

import com.joyintech.background.component.common.pack.common.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG07DatReportMapper;
import com.joyintech.tams.svreport.pbc.classic.zg07pbccolloandetailsub.dto.ZG07PbcColLoanDetailSubDto;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.PbcColDatFactoryServer;
import com.joyintech.service.pbc.classic.pbcdatacollectdatfile.factory.dao.ZG05DatReportMapper;
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
public class ZG07DatReportServer extends PbcColDatFactoryServer<ZG07PbcColLoanDetailSubDto> {
    //例子：如RATE字段要保留5位小数  put("RATE","#0.00000");
    private Map<String,String> specialKey = new HashMap<>();

    @Autowired
    private SqlSessionTemplate template;

    /**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;
    //暂时放着，解析excel似乎可以直接拿到，不确认顺序是否完全一致
//    private List<String> keyList = Arrays.asList(
//           "pbcProdId","loansTypeStr","loanTransferorId", "loanContractOrgId","loanContractOrgCode","borrowerType","borrowerCounty"
//            ,"borrowerId","industryInfo","enterpriseInvestorType","enterpriseScale","loanBasisId"
//            ,"loanPrdType","loanActualInvest","loanVdate","loanMdate","loanExtensionDueDate","rateFixed"
//            ,"rateLevel","loanGuaranteeMode","loanQuality","loanState","loanTransferorRate","origCcyStr"
//            ,"origContractAmount","origContractAmountCny","amtCcyStr","contractAmount","contractAmountCny"
//    );


    @Override
    public String getDatCode() {
        return "ZG07";
    }

    @Autowired
    private ZG07DatReportMapper zG07DatReportMapper;
    public ZG07DatReportServer(){
        specialKey.put("rateLevel","#0.00000");
        specialKey.put("loanTransferorRate","#0.00000");
    }
    @Override
    public PbcColDatFactoryServer getPClass(String reportNo) {
        if(StringUtil.isNotEmpty(reportNo)&& "ZG07".equals(reportNo)){
            return new ZG07DatReportServer();
        }
        return null;
    }

    @Override
    public String getPExportConfig() {
        return "/com/joyintech/tams/svreport/pbc/classic/zg07pbccolloandetailsub/conf/export_conf.xml";
    }

    @Override
    public List<ExcelValidDto> getPSameKeyData(List<Map<String,Object>> resultMap) {
        //1.3.1 查询是否有重复数据
        List<ExcelValidDto> list = new ArrayList<>();
        List<Map<String,String>> keysList=new ArrayList<>();


        if(CollectionUtils.isNotEmpty(resultMap)){
            Set<Object> allIdSet = resultMap.stream().map(e -> e.get("termId")).collect(Collectors.toSet());
            keysList = zG07DatReportMapper.getSameKeyList(allIdSet);
        }
        //1.3.2 重复数据组装
//        if(StringUtil.isNotEmpty(keysList)){
//            for(Map<String,String> str: keysList){
//                list.add(new ExcelValidDto("除回购和拆借外贷款明细信息："+ DateUtil.format(DateUtil.parse(str.get("termid")),"yyyy年MM月"),"","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
//            }
//        }
        if(CollectionUtils.isNotEmpty(keysList) && keysList.size()>0){
            list.add(new ExcelValidDto("除回购和拆借外贷款明细信息："+ DateUtil.format(DateUtil.parse(keysList.get(0).get("termid")),"yyyy年MM月"),
                    "","已报送或删除的数据，不支持修改。请在人行客户端修改！"));
        }
        return list;
    }


    @Override
    public List<Map<String, Object>> getPListByUUid(String uuid) {

        return zG07DatReportMapper.getPListByUUid(uuid);
    }

    @Override
    public Boolean updatePDatDataStatus(String uuid) {

        return zG07DatReportMapper.updatePDatDataStatus(uuid);
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
        return zG07DatReportMapper.updatePDataStatus(dataList);
    }

    @Override
    public List<Map<String, Object>> getPDefDataList(List dataList, String uuid) {
        //暂不处理
        return null;
    }

    @Override
    public String buildPDatFile(List<Map<String,Object>> dataList) {
        //1.6.1 定义文件内容字段													详细字段拼接顺序参考【dat文件拼接逻辑】

        //1.6.2  加密处理             仅ZG06和ZG07有
        //借款人类型为01 加密  BORROWER_TYPE
        dataList.forEach(map -> {
            if("1".equals(map.get("borrowerType"))){
                map.put("borrowerId",backCardChange((String)map.get("borrowerId")));//BORROWER_ID
            }
        });
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
                map.put("startdate",DateUtil.getFirstDay(DateUtil.parse(fileDate)));
                map.put("enddate",DateUtil.getLastDay(DateUtil.parse(fileDate)));
            });
        }
        //插入dat表 先执行 Dat表再执行登记。
        if (null != dataList) {
            zG07DatReportMapper.deleteByDat(dataList.subList(0, 1));
            insertDataMergeDat(dataList, UUid);

            //插入登记表
            zG07DatReportMapper.deleteBySub(dataList.subList(0, 1));
        }
        insertDataMergeSub(dataList,UUid);
        return true;
    }

    @Override
    public void batchDelete(String UUid) {
        zG07DatReportMapper.updateUuidSub(UUid);

    }

    private void insertDataMergeDat(List dataList,String UUid) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            ZG07DatReportMapper batchMapper = session.getMapper(ZG07DatReportMapper.class);
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
            ZG07DatReportMapper batchMapper = session.getMapper(ZG07DatReportMapper.class);
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
        List<Map<String,Object>> prodIdPbcProdIdList= zG07DatReportMapper.getProdIdPbcProdIdList();
        Map<String,String> prodIdPbcProdIdMap = new HashMap<>();
        if(prodIdPbcProdIdList!=null){
            for(Map<String,Object> map :prodIdPbcProdIdList){
                prodIdPbcProdIdMap.put((String) map.get("pbcprodid"),(String)map.get("prodid"));
            }
        }
        return prodIdPbcProdIdMap;
    }
}
