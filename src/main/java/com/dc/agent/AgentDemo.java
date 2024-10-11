package com.dc.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.LinkedList;
import java.util.List;

public class AgentDemo {
   private static java.lang.String className="com.dc.agent.AgentTest";
   private static java.lang.String methodName="getDomain";
    public static void agentmain(String args, Instrumentation instrumentation){
        List<Class> needRetransFromClasses = new LinkedList<>();
        Class[] loadedClasses = instrumentation.getAllLoadedClasses();
        for (Class loadedClass : loadedClasses) {
            if (loadedClass.getName().equals(className)) {
                needRetransFromClasses.add(loadedClass);
            }
        }
        instrumentation.addTransformer(new TestTransformer(className,methodName));
        try {
            instrumentation.retransformClasses(needRetransFromClasses.toArray(new Class[0]));
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }
    }
    public static void premain(String args, Instrumentation instrumentation){
        instrumentation.addTransformer(new TestTransformer(className,methodName));
    }

/*    public void testGenerateKey() throws Exception {
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
