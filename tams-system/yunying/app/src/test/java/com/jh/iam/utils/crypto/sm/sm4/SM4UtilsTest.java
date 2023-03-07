package com.jh.iam.utils.crypto.sm.sm4;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class SM4UtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(SM4UtilsTest.class);

    @Test
    public void sm4() {
        String value = "1234";
        String appKey = "9jbXkV0M74U=";
        String encryptValue = SM4Utils.sm4(value, appKey);
        logger.info("encrypt value: {}", encryptValue);
    }
}