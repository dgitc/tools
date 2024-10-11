package com.dc.agent;

public class AgentTest {
    public static void main(String[] args) {
        /*AgentTest agentTest = new AgentTest();
        agentTest.getDomain();*/
        System.out.println("hello javaagent");
       /* boolean is=true;
        while(is){
            List<Object> list = new ArrayList<>();
            list.add("hello agent");
        }*/
        UserServiceTest userServiceTest = new UserServiceTest();
        userServiceTest.sayHello("张三");
    }
    public void getDomain(){
        System.out.println("***********getDomain*****");
    }
}
