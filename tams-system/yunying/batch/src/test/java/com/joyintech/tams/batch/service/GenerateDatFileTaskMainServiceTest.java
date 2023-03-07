package com.joyintech.tams.batch.service;

import com.google.common.collect.Maps;
import com.joyintech.tams.batch.report.sjsreport.service.GenerateDatFileTaskMainService;
import com.joyintech.tams.common.InvestTradeFileImpService;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

// 审计署报表对接接口报送
@SpringBootTest
@RunWith(SpringRunner.class)
public class GenerateDatFileTaskMainServiceTest {

    @Autowired
    private GenerateDatFileTaskMainService service;

    private static Map<String, Object> params;

    @BeforeClass
    public static void beforeClass() throws ParseException {
        params = Maps.newHashMap();
        Date stime = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE).parse("2022-09-08");
        // 前一天
        params.put("dateStatus", DateUtil.getYesterDayDate(stime));
        // 上月第一天
        params.put("dateStart", DateUtil.getFirstDayOfLayMonth(stime));
        // 上月最后一天
        params.put("dateEnd", DateUtil.getLastDayOfLayMonth(stime));
    }

    // 理财产品登记
    @Test
    public void genProductsSubListDAT() {
        service.genProductsSubListDAT(params, false);
    }

}
