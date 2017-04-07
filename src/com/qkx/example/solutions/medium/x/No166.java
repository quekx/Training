package com.qkx.example.solutions.medium.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qkx on 17/3/3.
 */
public class No166 {

    /**
     * Given two integers representing the numeratorL and denominatorL of a fraction, return the fraction in string format.
     * <p>
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     * <p>
     * For example,
     * <p>
     * Given numeratorL = 1, denominatorL = 2, return "0.5".
     * Given numeratorL = 2, denominatorL = 1, return "2".
     * Given numeratorL = 2, denominatorL = 3, return "0.(6)".
     */
//    public static String fractionToDecimal(int numeratorL, int denominatorL) {
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return 0 + "";

        long numeratorL = numerator;
        long denominatorL = denominator;
        boolean isPositive = true;
        if (numeratorL < 0) {
            numeratorL = -numeratorL;
            isPositive = !isPositive;
        }
        if (denominatorL < 0) {
            denominatorL = -denominatorL;
            isPositive = !isPositive;
        }

        long intPart = numeratorL / denominatorL;
        long remain = numeratorL % denominatorL;

        StringBuilder res = new StringBuilder();
        if (!isPositive) {
            res.append('-');
        }
        res.append(intPart);
        if (remain == 0) return res.toString();
        res.append('.');

        StringBuilder builder = new StringBuilder();
        int pos = 0;
        Map<Long, Integer> map = new HashMap<>();
        map.put(remain, 0);
        long add;
        while (remain != 0) {
            remain *= 10;
            add = remain / denominatorL;
            remain %= denominatorL;
            Integer last;
            if ((last = map.get(remain)) == null) {
                pos++;
                map.put(remain, pos);
                builder.append(add);
            } else {
                builder.insert(Math.toIntExact(last), '(');
                builder.append(add);
                builder.append(')');
                break;
            }
        }
        res.append(builder);
        return res.toString();
    }
}
