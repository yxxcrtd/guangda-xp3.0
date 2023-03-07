package com.joyintech.tams.svreport.gd.report.chbproductsdealsub.service;


import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.base.service.BatchInsertService;
import com.joyintech.tams.report.interf.common.imp.ImportGenerator;
import com.joyintech.tams.report.interf.common.imp.sqlldr.ImpTable;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dao.SvrProductsDealSubInterfMapper;
import com.joyintech.tams.svreport.gd.report.chbproductsdealsub.dto.SvrProductsDealSubInterfDto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.slf4j.LoggerFactory.*;

/**
 * 理财产品交易明细表 Service
 * @author yangxianjiu
 * @date 2022/04/01
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrProductsDealSubInterf extends BaseService {

    /**
     * 日志
     */
    private Logger logger = getLogger(getClass());

    @Autowired
    private SvrProductsDealSubInterfMapper svrProductsDealSubInterfMapper;

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private BatchInsertService batchInsertService;
    /**
     *
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     */
    public void generateData(String batchDate, String batchDateStart, String batchDateEnd) {
        // 判断跑批日上月月末是否含有投资者明细信息，若无明细信息则进行提示并停止跑批
        // 检查明细
        List<String> chbEqudetList = svrProductsDealSubInterfMapper.checkChbEqudet(batchDate);
        if (CollectionUtils.isEmpty(chbEqudetList)) {
            logger.info("跑批日未含有投资者明细信息！");
        }

        //1、开始检数据
        logger.info("理财产品交易明细表信息检索开始------->>>>>>");
        List<SvrProductsDealSubInterfDto> dataList = this.getList(batchDate,batchDateStart,batchDateEnd);
        logger.info("理财产品交易明细表信息检索结束-------<<<<<<");
        if (dataList.size() == 0) {
            logger.info("没有检索到数据,批处理结束!");
            return;
        }
        //更新表
        logger.info("理财产品交易明细表信息入库开始------->>>>>>");
        dbData(dataList,batchDate,batchDateStart,batchDateEnd);
        logger.info("理财产品交易明细表信息入库结束-------<<<<<<");
    }

    /**
     *
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     * @return
     */
    private List<SvrProductsDealSubInterfDto> getList(String batchDate, String batchDateStart, String batchDateEnd){
        List<SvrProductsDealSubInterfDto> dtoList =svrProductsDealSubInterfMapper.getProductsDealSubInterfData(batchDate,batchDateStart,batchDateEnd);
        return dtoList;
    }

    /**
     * 数据入库 先删后插
     * @param dataList
     * @param batchDate
     * @param batchDateStart
     * @param batchDateEnd
     */
    private void dbData(List<SvrProductsDealSubInterfDto> dataList, String batchDate, String batchDateStart, String batchDateEnd) {
        // 删除
        logger.info("理财产品交易明细表信息先删开始------->>>>>>");
        svrProductsDealSubInterfMapper.deleteProductsDealSub(batchDate);
        logger.info("理财产品交易明细表信息先删结束-------<<<<<<");
        dataList.forEach(entity->{
            // 生成业务主键
            entity.setRegistSeqno(UUID.randomUUID().toString().toUpperCase());
            // 是否代销
            if (StringUtil.equals(entity.getIsConsign(),"02")){
                entity.setOrgId("1018");
            } else if (StringUtil.equals(entity.getIsConsign(),"01")){
                entity.setOrgId(entity.getAgencyCode().substring(entity.getAgencyCode().length()-4));
            }
            //处理组别
            if(!StringUtil.isEmpty(entity.getGroupId())){
                if(StringUtil.equals(entity.getGroupId(),"bta")){
                    entity.setGroupId("BTA");
                }else if(StringUtil.equals(entity.getGroupId(),"wechatpay") || StringUtil.equals(entity.getGroupId(),"netwealth")){
                    entity.setGroupId("FSP");
                }else if(StringUtil.equals(entity.getGroupId(),"ewta")){
                    entity.setGroupId("TYTA");
                }
            }
        });

        logger.info("============插入开始===============" + DateUtil.getCurrentDateTime());

        if (CollectionUtils.isNotEmpty(dataList) && dataList.size() <= 1000) {
            int count = 0;
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try {
                SvrProductsDealSubInterfMapper batchMapper = session.getMapper(SvrProductsDealSubInterfMapper.class);
                List<SvrProductsDealSubInterfDto> dataListTemp = new ArrayList<>();
                if (dataList.size() >= 500 ){
                    for (SvrProductsDealSubInterfDto entity : dataList) {
                        dataListTemp.add(entity);
                        if (dataListTemp.size() == 500) {
                            batchMapper.insertProductsDealSub(dataListTemp);
                            dataListTemp.clear();
                        }
                        count++;
                        if (count % 10000 == 0) {
                            session.commit();
                            // 清理缓存，防止溢出
                            session.clearCache();
                        }
                    }
                    // 剩下的数据
                    if (dataListTemp.size() > 0){
                        // 提交前面的插入
                        session.commit();
                        batchMapper.insertProductsDealSub(dataListTemp);
                        dataListTemp.clear();
                    }
                } else {
                    // 数据不足500条
                    dataListTemp.addAll(dataList);
                    batchMapper.insertProductsDealSub(dataListTemp);
                }
                session.commit();
                // 清理缓存，防止溢出
                session.clearCache();
            } catch (Exception e) {
                session.rollback();
                logger.error(e.getMessage(), e);
            } finally {
                session.close();
            }
        } else {// 大于1000使用sqlloader入库
            try {
                //sqlloader插入
                ImpTable table = new ImpTable("CHB_PRODUCTS_DEAL_SUB");
                table.setColumns(ImportGenerator.createImpTableColumns(table.getName()));
                // 临时文件存放目录
                String path = CacheUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);

                batchInsertService.exeBatchldr(path, dataList, table);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }


        if (CollectionUtils.isNotEmpty(dataList)) {
        }
        logger.info("============插入结束===============" +DateUtil.getCurrentDateTime());
    }
}
