package com.ultrapower.ams.util;

import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class EncryptUtilTest {

    @Test
    public void verifyPwd() throws Exception {
        boolean success = EncryptUtil.verifyPwd("{SHA}fEqNCco3Yq9h5ZUglD3CZJT4lBs=", "123456");
        Assert.assertTrue(success);
        success = EncryptUtil.verifyPwd("{SHA}fEqNCco3Yq9h5ZUglD3CZJT4lBs=", "123457");
        Assert.assertFalse(success);
    }

}