package com.dc.agent;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Arrays;
import java.util.List;

public class Metric {
    private static final long MB = 1048576L;
    public static void printMemoryInfo() {
        try {
            MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
            MemoryUsage headMemory = memory.getHeapMemoryUsage();
            String info = String.format("\ninit: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                    headMemory.getInit() / MB + "MB",
                    headMemory.getMax() / MB + "MB",  headMemory.getUsed() / MB + "MB",
                    headMemory.getCommitted() / MB + "MB",
                    headMemory.getUsed() * 100 / headMemory.getCommitted() + "%");
            System.out.println(info);
            MemoryUsage noheadMemory = memory.getNonHeapMemoryUsage();
            info = String.format("\ninit: %s\t max: %s\t used: %s\t committed: %s\t use rate: %s\n",
                    noheadMemory.getInit() / MB + "MB",
                    noheadMemory.getMax() / MB + "MB",noheadMemory.getUsed() / MB + "MB",
                    noheadMemory.getCommitted() / MB + "MB",
                    noheadMemory.getUsed() * 100 / noheadMemory.getCommitted() + "%");
            System.out.println(info);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void printGCInfo(){
        List<GarbageCollectorMXBean> garbages=ManagementFactory.getGarbageCollectorMXBeans();
        garbages.stream().forEach(v->{
            String info= String.format("name: %s\t count:%s\t took:%s\t pool name:%s",
                    v.getName(),
                    v.getCollectionCount(),
                    v.getCollectionTime(),
                    Arrays.deepToString(v.getMemoryPoolNames()));
            System.out.println(info);
        });
    }
}
