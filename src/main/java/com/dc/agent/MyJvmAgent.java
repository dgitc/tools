package com.dc.agent;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyJvmAgent {
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("this is an perform monitor agent.");
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------------哈哈");
                Metric.printMemoryInfo();
                Metric.printGCInfo();
            }
        }, 0, 3000, TimeUnit.MILLISECONDS);
    }
}
