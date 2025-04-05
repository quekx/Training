package com.qkx.example.lc.achive.medium.No12x_13x;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by qkx on 16/11/25.
 */
public class No131 {
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new LinkedList<>();

        if (s == null || s.length() == 0) return res;

        HashMap<Integer, List<Integer>> map = new HashMap<>(s.length());

        List<Integer> list = new LinkedList<>();
        list.add(1);
        map.put(s.length() - 1, list);

        for (int i = s.length() - 2; i >= 0; i--) {
            List<Integer> temp = map.get(i);
            if (temp == null) {
                temp = new LinkedList<>();
                map.put(i, temp);
                temp.add(1);
            }
            // 相邻
            if (s.charAt(i) == s.charAt(i + 1)) {
                temp.add(2);
            }

            // add
            for (int k : map.get(i + 1)) {
                if (i + k + 1 <= s.length() - 1 && s.charAt(i) == s.charAt(i + k + 1)) {
                    temp.add(k + 2);
                }
            }
        }

        List<String> stringList = new LinkedList<>();
        get(0, s.length() - 1, s, map, stringList, res);

        return res;
    }

    private static void get(int startIndex, int endIndex, String s, Map<Integer, List<Integer>> map,
                     List<String> list, List<List<String>> res) {
        if (startIndex > endIndex) {
            res.add(list);
            return;
        }
//        if (startIndex == endIndex) {
//            list.add(s.substring(startIndex, startIndex + 1));
//            res.add(list);
//            return;
//        }

        for (int len : map.get(startIndex)) {
            List<String> temp = new LinkedList<>(list);
            temp.add(s.substring(startIndex, startIndex + len));
            get(startIndex + len, endIndex, s, map, temp, res);
        }
    }
}
