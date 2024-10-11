package com.dc.agent.sign;

import com.alibaba.fastjson.JSON;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

public class SignTest {
    public static void main(String[] args) throws Exception {
       /* testOrgStoreSignature();*/
    }

    /*public static void testOrgStoreSignature() throws Exception { // 测试参数
        String appId = "POS";
        String httpMethod = "post";
        String url = "https://datacenter.zhengxinfood.com/interface/api/pos/orgStore/queryByCode";
        List<String> storeCodes = new ArrayList<>();
        storeCodes.add("720014");
        String requestBody = JSON.toJSONString(storeCodes);
        String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDIPUE+8H/DZYU5z2PJiXQd8OSIuKL/0LYpj mL6+HfNeUyQ1N6P+l44q+SY8V4wWcbnjU4fe3TjOjug4yRBbtvu+i4WBHReHGSKDJKC1p0DSWuTqWTZXvUx1K9 " +
                "UT9SNJRRyDYzkAPkCVCJqPOl1MkgUYdX1junpGg6CDUBulKkwAiJs2dHbmX2qkH6kGMPixUXg6NyGL0N8/Upi5 3+OlAmjx7ZEGcE1TuuPYb1/Lh+jNK2Gl/d0PbN+peQYg0UdwA28ZEl4FWbaxo5iWKyCBcG7VwJSs/f9hklZnuh D0lHl2RE1d413cg8VHsW8qcuWcnYv42SG6pAVcTS8SeOgU82VAgMBAAECggEBALVouWm6HSX7HZ3oQ/FcdDSD/ " +
                "6fWDuZQQhiYBRh2bqNa14EHObObEi2pnWrqkXigfVFedX6ULKuIIGl8H2agduSlB4eu9XYlpvhit4GNyaYmqC2 Y8+Ly4pUIqOdrg5d0FnfVyuTt9KqlaqBxGtiwexjsIhlcJpKmpqowM0V7rWPtjzB1bTc/+hCJK8WjWiCKuF767 FgJ8jNe/qU9B0w2WQuJD8nrbnpsAjY2JQqkb3PXJ4RvYuIXiBmLw3bWoa5yTe9LhdeBIoFXiWHVv8KEsdVK313 " +
                "oWjK5Zl9Iko8m0Fxaam8NerUSOweMRJgyfwDazeSYAvy7iLz3WZl+TokB+WkCgYEA9+YboK3iq6D/wnkL5Ke8W 93yadVLRnGPtiXyKqcQ00bCAge3jpfRnZi6tUqt9ijlJjTW2yvqi3AcBMI1dDpp5l5oy34gV+fnQSDTLKuIgtQ 4CvpXbgL2NfWrUMUN/xAiGmjcOn0NFkKsH6LkQML2G6x8N/mGjWCHYdqV3bfSgC8CgYEAzshuvzxnMiIcT3w1P Rq2BeIlO3r+YiN5KPsABbGnJhvj198rRjDTO6e/adFGSDFa2o/Wld+TZjV7Y7gvyZIhhixmlq+01zJvOLoAW3j " +
                "j07cYQSGjoz2KrnOkUYStMq6fNkt/v3LBIywMB+msvr/0bgn+9pWfqQWtyS938/mGeXsCgYA7/tfO4maKbROZC FylgSboAOYrUPC/4roKNlLOmdJLn8NSZHW7L+kOQClnuOQzQCB9R6p8Lru79W0ChdIUCBMbKtwn66V+QOTJ8Vz KYdVO/Hd3yLCiMltOId/NU3OYuFnYlGpASYWCTq6DlZOyTLztY6hIj75Ntv/s4c8FcviiBwKBgHEV+kMJhKJNN 1Bc7Dyzm/JRv+zskawYHE3GHzf99RyJSiyntuHXF95bGfqD4bzfiQFYgpFKSTEvdICPnUrOaRkYtS4Zf3vk8CC " +
                "E0Gc2ENqbGl+cRwKiSHKpeaa/OX0rNJUb6wxHdMqIbzfwmnFjRwja3J38K+ni2izZke9SejF7AoGATerMSlFyF " +
                "MGoJTTpvmPuqjfFzps/gkHTxcm5fp/fo5AA1j8nGBacRn38Y9jdfnDBwLjV3fbn9GxBnNN6ax1m1dgLbmHJLpe rPAAue3xgfZYNHsrCVHQ4bd9ZuFiSqzM60KPN7kLIDCE+0i1QhdZMONnMmcMZA083AU1wTquphjM=";
        // 参数转换
        String uri = URI.create(url).getRawPath();
        // 加载私钥
        // PrivateKey privateKey = readPrivateKeyForPkcs1(privateKeyStr);
        PrivateKey privateKey = readPrivateKey(privateKeyStr);
        // 签名
        String signature = sign(appId, httpMethod, uri, requestBody, privateKey);
        System.out.println("sign:\n"+signature);
    }

    public static String sign(String appId, String httpMethod, String uri, String requestBody,
                              PrivateKey privateKey) throws Exception {
        // 待签名内容
        String timeStamp = 1723105582870L+ ""; // 需要返回
        String random = "b54tb9DOfCAhbB5DXRZ8";        // 需要返回
        System.out.println(timeStamp + "/" + random);
        String plainText = new StringJoiner("&")
                .add(appId)
                .add(httpMethod.toUpperCase())
                .add(uri)
                .add(requestBody)
                .add(timeStamp)
                .add(random)
                .toString();

        // 签名算法
        Signature signature = Signature.getInstance("SHA256WithRSA");

        // 签名
        signature.initSign(privateKey);
        signature.update(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    public static PrivateKey readPrivateKey(String key) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] keys = Base64.getDecoder().decode(key);
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keys);
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new IllegalStateException("私钥无效：", e);
        }
    }

    public static PrivateKey readPrivateKeyForPkcs1(String key) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] keys = Base64.getDecoder().decode(key);
        try {
            RSAPrivateKey rsaPrivateKey = RSAPrivateKey.getInstance(keys);
            PKCS8EncodedKeySpec keySpec = new PKCS1EncodedKeySpec(rsaPrivateKey.getEncoded());
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new IllegalStateException("私钥无效：", e);
        }
    }
    public void testGenerateKey() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        generator.initialize(2048, secureRandom);
        KeyPair keyPair = generator.generateKeyPair(); // 公钥
        PublicKey publicKey = keyPair.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();
        System.out.println("公钥（pkcs#8）：");
        System.out.println(Base64.getEncoder().encodeToString(publicKeyBytes));

        byte[] publicKeyPkcs1Bytes = ASN1BitString.getInstance(ASN1Sequence.getInstance(publicKeyBytes).getObjectAt(1)).getBytes();
        System.out.println("公钥（pkcs#1）：");
        System.out.println(Base64.getEncoder().encodeToString(publicKeyPkcs1Bytes)); // 私钥

        PrivateKey privateKey = keyPair.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();
        System.out.println("私钥（pkcs#8）：");
        System.out.println(Base64.getEncoder().encodeToString(privateKeyBytes));

        byte[] privateKeyPkcs1Bytes = PrivateKeyInfo.getInstance(privateKeyBytes).parsePrivateKey().toASN1Primitive().getEncoded();
        System.out.println("私钥（pkcs#1）：");
        System.out.println(Base64.getEncoder().encodeToString(privateKeyPkcs1Bytes));
    }*/
}
