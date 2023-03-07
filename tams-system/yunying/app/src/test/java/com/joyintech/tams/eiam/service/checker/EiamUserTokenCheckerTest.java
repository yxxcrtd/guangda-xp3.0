package com.joyintech.tams.eiam.service.checker;

import com.jh.iam.utils.crypto.sm.sm4.SM4Utils;
import com.joyintech.tams.TamsApplication;
import com.joyintech.tams.eiam.service.EiamConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TamsApplication.class)
@RunWith(SpringRunner.class)
public class EiamUserTokenCheckerTest {

    private static final Logger logger = LoggerFactory.getLogger(EiamUserTokenCheckerTest.class);

    @Autowired
    private EiamConfig config;
    @Autowired
    private EiamUserTokenChecker checker;

    @Test
    public void check() {
        String token = "abcd";
        String username = checker.check(token, null);
        logger.info("username: {}", username);
    }

    @Test
    public void encrypt() {
        String value = "1234";
        String encryptValue = checker.encrypt(value);
        logger.info("encrypt value: {}", encryptValue);
    }

}