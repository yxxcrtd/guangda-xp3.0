package com.joyintech.tams.batch.service;

import com.joyintech.tams.common.InvestTradeFileImpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class investTradeServiceTest {

    @Autowired
    private InvestTradeFileImpService investTradeFileImpService;

    @Test
    public void test() {
        investTradeFileImpService.getInfoByInterf("2022-07-04");
    }

}
