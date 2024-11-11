package com.nju.risk.util;


public class DoubleFormatter {
    public static double format(double value) {
        return Math.floor(value * 1_000_000) / 1_000_000;
    }
}
