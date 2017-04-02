package com.qkx.example.solutions.hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by qkx on 16/5/26.
 */
public class No76 {
    /**
     * AC
     */
    public static String minWindow(String s, String t) {

        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        Map<Character, Integer> current = new HashMap<>();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < tc.length; i++) {
            int num = map.getOrDefault(tc[i], 0);
            map.put(tc[i], num + 1);
        }

        String res = "";
        int resLen = Integer.MAX_VALUE;

        int head = 0;
        for (int i = 0; i < sc.length; i++) {
            char si = sc[i];
            if (map.containsKey(si)) {
                int num = current.getOrDefault(si, 0);
                current.put(si, num + 1);
            } else {
                continue;
            }
            while (head <= i && (!map.containsKey(sc[head]) || current.get(sc[head]) > map.get(sc[head]))) {
                if (current.containsKey(sc[head])) {
                    current.put(sc[head], current.get(sc[head]) - 1);
                }
                head++;
            }
            boolean isFind = true;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (current.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                    isFind = false;
                    break;
                }
            }
            if (isFind && i - head + 1 < resLen) {
                res = s.substring(head, i + 1);
                resLen = i - head + 1;
            }
        }

        return res;
    }

    /**
     * Not AC
     */
    public static String minWindow2(String s, String t) {

        Map<Character, Integer> map = new HashMap<>();

        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();

        int[] primes = getPrimeNums(tc.length);

        int pos = 0;
        long x = 1;
        for (int i = 0; i < tc.length; i++) {
            if (!map.containsKey(tc[i])) {
                map.put(tc[i], primes[pos++]);
            }
            x *= map.get(tc[i]);
        }

        long y = 1;
        int j = 0;

        int i = 0;

        String res = "";
        int resLen = Integer.MAX_VALUE;

        while (i < sc.length) {
            y *= map.getOrDefault(sc[i], 1);
            while (j <= i && (!map.containsKey(sc[j]) || y % (x * map.get(sc[j])) == 0)) {
                if (map.containsKey(sc[j])) {
                    y = y / map.get(sc[j]);
                }
                j++;
            }
            if (y % x == 0 && i - j + 1 < resLen) {
                res = s.substring(j, i + 1);
                resLen = i - j + 1;
            }
            i++;
        }
        System.out.println();
        return res;
    }
    public static int[] getPrimeNums(int n) {
        int[] res = new int[n];
        res[0] = 2;
        int i = 1;
        int count = 3;
        while (i < n) {
            for (int x : res) {
                if (x == 0 || x * x > count) {
                    res[i++] = count++;
                    break;
                }
                if (count % x == 0) {
                    count++;
                    break;
                }
            }
        }
        return res;
    }
}