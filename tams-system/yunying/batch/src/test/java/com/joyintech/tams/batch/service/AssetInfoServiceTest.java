package com.joyintech.tams.batch.service;

import com.joyintech.tams.common.AssetInfoFileImpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AssetInfoServiceTest {

    @Autowired
    private AssetInfoFileImpService assetInfoFileImpService;

    @Test
    public void test() {
        assetInfoFileImpService.getInfoByInterf("2022-06-01");
    }

}
