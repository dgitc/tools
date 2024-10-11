package com.dc.agent;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        //testJdkProxy();
        testCglib();

    }

    static void testJdkProxy() {
        UserService userService = (UserService) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            private UserServiceImpl userService = new UserServiceImpl();

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("hanlder --------before");
                String res = (String) method.invoke(userService, args);
                System.out.println("hanlder --------end");
                return res;
            }
        });
        System.out.println("jdk proxy method invoke result:" + userService.getName(99999));
    }
    static void testCglib(){
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, System.getProperty("user.dir"));
        Enhancer enhancer = new Enhancer();
        //设置enhancer对象的父类
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new cglibMethodInterceptor(new UserServiceImpl()));
        UserServiceImpl userService = (UserServiceImpl)enhancer.create();
        System.out.println("cglib proxy method invoke result:" + userService.getName(88888));
    }
}

interface UserService {
    String getName(int id);
}

class UserServiceImpl implements UserService {

    @Override
    public String getName(int id) {
        return "我是:" + id;
    }
}

class cglibMethodInterceptor implements MethodInterceptor {
    private UserServiceImpl target;

    public cglibMethodInterceptor(UserServiceImpl target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //下面2种写法都可以
        System.out.println("cglib --------before");
        Object result = methodProxy.invokeSuper(o, objects);
        //Object invoke = method.invoke(target, objects);
        System.out.println("cglib --------end");
        return result;
    }
}
