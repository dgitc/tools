package com.dc.agent;

public class StringTest {
    public static void main(String[] args) {
        java.lang.String s = "abc";
        System.out.println("s-------------------:"+s.hashCode());
        s = "bcd";
        System.out.println("s-------------------:"+s.hashCode());
        StringBuilder sb = new StringBuilder("123");
        System.out.println("sb-------------------:"+s.hashCode());
        sb.append("张三");
        System.out.println("sb-------------------:"+sb.length());
        String join = String.join("@", new String[]{"1", "2", "3", "4", "5"});
        System.out.println("join-------------------:"+join);

    }
}
