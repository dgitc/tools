package com.dc.agent;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
 /*       Map map = new HashMap();
        for(int i=0;i<8;i++){
            User user = new User();
            user.setId(i);
            user.setName("name_"+i);
            map.put(i, user);
        }
        System.out.println(map);
        //001
        System.out.println( "Integer.numberOfLeadingZeros() :"+Integer.numberOfLeadingZeros(7));

        System.out.println( "Integer.numberOfLeadingZeros() :"+Integer.numberOfLeadingZeros(15-1));
        int n = -1 >>> Integer.numberOfLeadingZeros(915-1);
        System.out.println( "--------n :"+n);
        n = -1 >>> 1;
        System.out.println( "--------n :"+n);
         n = -1 >>> Integer.numberOfLeadingZeros(15 - 1);
        System.out.println( "--------n :"+n);
        System.out.println(Integer.toBinaryString(n));*/
        Map map = new HashMap(15,0.75f);



    }
    static class User{
        int id;
        String name;

        public User() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
