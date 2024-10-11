package com.dc.agent;

import java.math.BigDecimal;

public class BigdecimalTest {
    public static void main(String[] args) {
        BigDecimal tmp = BigDecimal.ZERO.add(BigDecimal.valueOf(113.5100000000));
        System.out.println(tmp);
        BigDecimal amt = BigDecimal.valueOf(113.51);
        System.out.println(amt);
        System.out.println(tmp.equals(amt));
        System.out.println(tmp.subtract(amt).intValue());

    }
}
