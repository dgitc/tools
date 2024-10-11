package com.dc.agent;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class TestTransformer implements ClassFileTransformer {
//目标类名称，.分割
    private String targetClassName;
    //目标类名称，/分割
    private String targetVMClassName;
    private String targetMethodName;

    public TestTransformer(String className, String methodName) {
        this.targetVMClassName=new String(className).replaceAll("\\.","\\/");
        this.targetClassName = className;
        this.targetMethodName = methodName;
    }
    //类加载时会执行该函数，其中参数 classfileBuffer为原始类字节码，返回值为目标字节码，className为/前缀

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if(!className.equals(targetVMClassName)){
          return classfileBuffer;
        }
        ClassPool classPool= ClassPool.getDefault();
        try {
            CtClass ctClass = classPool.get(this.targetClassName);
            CtMethod ctMethod = ctClass.getDeclaredMethod(this.targetMethodName);
            String before="";
            System.out.println("----------insert start");
            ctMethod.insertBefore("{ System.out.println(\"----------insert start\");}");
            ctMethod.insertAfter("{ System.out.println(\"----------insert end\");}");
            return ctClass.toBytecode();
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (CannotCompileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
