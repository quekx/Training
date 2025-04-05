package com.qkx.example.lc.achive.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qkx on 17/7/3.
 */
public class No372 {
    /**
     * super pow
     *
     * Your task is to calculate ab mod 1337 where a is a positive integer
     * and b is an extremely large positive integer given in the form of an array.
     *
     * Example1:
     * a = 2
     * b = [3]
     * Result: 8
     *
     * Example2:
     * a = 2
     * b = [1,0]
     * Result: 1024
     */
    public static int superPow(int a, int[] b) {
        if (b == null || b.length == 0 || b[0] == 0) return 0;

        int N = 1337;
        a %= N;

        Map<Integer, Integer> resultMap = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        int curIndex = 1;
        int curResult = a;
        while (!map.containsKey(curResult)) {
            resultMap.put(curIndex, curResult);
            map.put(curResult, curIndex);
            curResult = (curResult * a) % N;
            curIndex++;
        }

        int startIndex = map.get(curResult);
        int len = curIndex - startIndex;

        // index =  (total + 1 - start) % len + start - 1
        int total = 0;
        int fact = 0;
        boolean isOver = false;
        for (int bn : b) {
            total = total * 10 + bn;
            total %= len;
            fact = fact * 10 + bn;
            if (fact > startIndex) {
                isOver = true;
            }
        }
        if (isOver) {
            int x = total - (startIndex - 1) % len;
            int index;
            if (x <= 0) {
                index = x + len + startIndex - 1;
            } else {
                index = x + startIndex - 1;
            }
            return resultMap.get(index);
        }

        return resultMap.get(fact);
    }
}
