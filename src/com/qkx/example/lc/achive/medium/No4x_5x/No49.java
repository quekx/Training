package com.qkx.example.lc.achive.medium.No4x_5x;

import java.util.*;

/**
 * Created by qkx on 16/5/20.
 */
public class No49 {
    /**
     * Given an array of strings, group anagrams together.
     For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
     Return:
     [
        ["ate", "eat","tea"],
        ["nat","tan"],
        ["bat"]
        ]
     Note:
     For the return value, each inner list's elements must follow the lexicographic order.
     All inputs will be in lower-case.
     */


    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> res = new LinkedList<>();

        Map<Integer, List<String>> map = new HashMap<>();

        int[] primes = getPrimeNumbers(26);
        for (String s : strs) {
            int key = hash(s, primes);
            List<String> list;
            if (map.containsKey(key)) {
                list = map.get(key);
                list.add(s);
            } else {
                list = new LinkedList<>();
                list.add(s);
                map.put(key, list);
            }
        }
        for (List<String> e : map.values()) {
            Collections.sort(e);
            res.add(e);
        }
        return res;
    }
    private int[] getPrimeNumbers(int n) {
        int[] primes = new int[n];
        primes[0] = 2;
        int cur = 3;
        int count = 1;

        while (count < n) {
            for (int num : primes) {
                if (num == 0 || num * num > cur) {
                    primes[count++] = cur++;
                    break;
                }
                if (cur % num == 0) {
                    cur++;
                    break;
                }
            }
        }
        return primes;
    }
    private int hash(String s, int[] primes) {
        int res = 1;
        char[] values = s.toCharArray();
        for (char c : values) {
            res *= primes[(c - 'a')];
        }
        return res;
    }
}
