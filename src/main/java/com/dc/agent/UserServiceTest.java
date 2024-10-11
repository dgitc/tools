package com.dc.agent;

public class UserServiceTest {
    public void sayHello(String name){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("sayHello()");
    }

}
