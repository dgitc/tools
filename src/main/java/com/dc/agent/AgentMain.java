package com.dc.agent;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class AgentMain {
    public static void premain(String agentArgs, Instrumentation instrumentation){
        final ClassPool pool = new ClassPool();
        pool.appendSystemPath();
        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if(!"com/dc/agent/UserService".equals(className)){
                    return null;
                }
                try {
                    CtClass ctClass=pool.get("com.dc.agent.UserService");
                    CtMethod sayHello = ctClass.getDeclaredMethod("sayHello");
                    CtMethod copy= CtNewMethod.copy(sayHello,ctClass,null);
                    copy.setName("sayHelloCopy");
                    ctClass.addMethod(copy);
                    sayHello.setBody("{long begin=System.currentTimeMillis();\n"+
                            " sayHelloCopy($1);\n"+
                            " System.out.println(System.currentTimeMillis()-begin);}");
                    return ctClass.toBytecode();
                } catch (NotFoundException e) {
                    throw new RuntimeException(e);
                } catch (CannotCompileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
