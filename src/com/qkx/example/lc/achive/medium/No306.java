package com.qkx.example.lc.achive.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 17/5/31.
 */
public class No306 {
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() <= 2) return false;

        long a = 0;
        for (int i = 0; i < num.length(); i++) {
            a = a * 10 + num.charAt(i) - '0';
            long b = 0;
            for (int j = i + 1; j < num.length(); j++) {
                List<Long> res = new LinkedList<>();
                b = b * 10 + num.charAt(j) - '0';
                res.add(a);
                res.add(b);
                if (isAdditive(a, b, j + 1, num, true, res)) {
                    for (Long x : res) {
                        System.out.print(x + " -> ");
                    }
                    return true;
                }
                if (b == 0) break;
            }
            if (a == 0) break;
        }

        return false;
    }

    private boolean isAdditive(long a, long b, int index, String num, boolean isFirst, List<Long> res) {
        if (index == num.length()) return !isFirst;

        long c = a + b;
        String next = String.valueOf(c);
        if (num.startsWith(next, index)) {
            res.add(c);
            return isAdditive(b, c, index + next.length(), num, false, res);
        }
        return false;
    }
}
