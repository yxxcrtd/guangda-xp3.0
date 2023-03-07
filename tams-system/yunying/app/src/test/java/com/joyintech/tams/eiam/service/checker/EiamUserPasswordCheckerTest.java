package com.joyintech.tams.eiam.service.checker;

import com.joyintech.tams.TamsApplication;
import com.joyintech.tams.eiam.service.checker.EiamUserPasswordChecker;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TamsApplication.class)
@RunWith(SpringRunner.class)
public class EiamUserPasswordCheckerTest {

    @Autowired
    private EiamUserPasswordChecker checker;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("application.joyinsso", "true");
    }

    @Test
    public void checkUserAndPassword() {
        boolean success = checker.checkUserAndPassword("admin", "888888");
        Assert.assertTrue(success);
        success = checker.checkUserAndPassword("admin", "123456");
        Assert.assertFalse(success);
    }

}