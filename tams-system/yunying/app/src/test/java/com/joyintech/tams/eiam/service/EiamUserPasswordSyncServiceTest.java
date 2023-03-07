package com.joyintech.tams.eiam.service;

import com.joyintech.tams.TamsApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@SpringBootTest(classes = TamsApplication.class)
@RunWith(SpringRunner.class)
public class EiamUserPasswordSyncServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EiamUserPasswordSyncServiceTest.class);

    @Autowired
    private EiamUserPasswordSyncService eiamUserPasswordSyncService;

    private String projectPath;

    @Before
    public void beforeClass() {
        String path = EiamUserPasswordSyncServiceTest.class.getClassLoader().getResource(".").getPath();
        projectPath = path.substring(0, path.indexOf("/target"));
    }

    @Test
    public void sync() {
        File file = new File(projectPath, "/src/test/java/com/joyintech/tams/eiam/service/IAM001_20100319.eak");
        logger.info("file:{}", file.getAbsolutePath());
        boolean success = eiamUserPasswordSyncService.sync(file);
        Assert.assertTrue(success);
    }

}