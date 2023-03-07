package com.joyintech.tams.svreport.pbc.specificinvestorshold.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.svreport.pbc.specificinvestorshold.dao.SpcInvestorsHoldTaskMapper;
import com.joyintech.tams.svreport.pbc.specificinvestorshold.dto.SpcInvestorsHoldTaskDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 特定目的载体持有信息(投资者) 生成批
 *
 * @author yangxianjiu
 * @date 2022/11/12
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SpcInvestorsHoldTaskService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SqlSessionTemplate template;

    @Autowired
    private SpcInvestorsHoldTaskMapper spcInvestorsHoldTaskMapper;

    public void generateData(Date batchTime) {
        //1.判断跑批日期，如果不是1号，批处理不执行。
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(batchTime);
        if (calendar.get(Calendar.DATE) != 1) {  //每月1日生成上月的数据
            logger.info("每月1日 生成上月的数据！");
            return;
        }
        String executeDate = DateUtil.format(batchTime, DateUtil.DEFAULT_DATE_FORMAT_EN);
        List<SpcInvestorsHoldTaskDto> dataList = spcInvestorsHoldTaskMapper.selectInvestHoldSubData(executeDate);
        // 数据入库
        dbData(dataList);
    }

    /**
     * 先删后插
     * @param list
     */
    private void dbData(List<SpcInvestorsHoldTaskDto> list) {

        logger.info("特定目的载体持有信息(投资者) 生成批 删除数据开始 ========>");
        int count = 0;
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            SpcInvestorsHoldTaskMapper batchMapper = session.getMapper(SpcInvestorsHoldTaskMapper.class);
            for (SpcInvestorsHoldTaskDto dto : list) {
                batchMapper.delete(dto);
                count++;
                if (count % 1000 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        } catch (Exception e) {
            session.rollback();
            logger.info("特定目的载体持有信息(投资者) 生成批 删除数据 报错:" + e.getMessage(), e);
            return;
        } finally {
            session.close();
        }
        logger.info("特定目的载体持有信息(投资者) 生成批 删除数据结束----------");

        logger.info("特定目的载体持有信息(投资者) 生成批 插入数据开始 ========>");
        // 插入
        count = 0;
        session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            SpcInvestorsHoldTaskMapper batchMapper = session.getMapper(SpcInvestorsHoldTaskMapper.class);
            for (SpcInvestorsHoldTaskDto dto : list) {
                dto.setUuid(IDGenerator.generate(36).toUpperCase());
                dto.setStatus("E");
                dto.setCreateUser(CommonUtil.getUserName());
                dto.setUpdateUser(CommonUtil.getUserName());
                dto.setCreateTime(new Date());
                dto.setUpdateTime(new Date());
                batchMapper.insert(dto);
                count++;
                if (count % 1000 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        } catch (Exception e) {
            session.rollback();
            logger.info("特定目的载体持有信息(投资者) 生成批 插入数据数据 报错:" + e.getMessage(), e);
        } finally {
            session.close();
        }
        logger.info("特定目的载体持有信息(投资者) 生成批 插入数据数据结束----------");

    }
}
