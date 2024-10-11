package com.dc.agent;

import java.lang.instrument.Instrumentation;

public class MyPreMainAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("hello javaAgent");
    }
}