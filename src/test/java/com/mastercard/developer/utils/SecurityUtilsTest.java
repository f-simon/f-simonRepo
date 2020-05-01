package com.mastercard.developer.utils;

import org.junit.Assert;
import org.junit.Test;

import java.security.PrivateKey;

@Deprecated
public class SecurityUtilsTest {

    @Test
    public void testLoadPrivateKey_ShouldReturnKey() throws Exception {

        // GIVEN
        String keyContainerPath = "./src/test/resources/test_key_container.p12";
        String keyAlias = "mykeyalias";
        String keyPassword = "Password1";

        // WHEN
        PrivateKey privateKey = SecurityUtils.loadPrivateKey(keyContainerPath, keyAlias, keyPassword);

        // THEN
        Assert.assertNotNull(privateKey.getEncoded());
        Assert.assertEquals("PKCS#8", privateKey.getFormat());
    }
}
