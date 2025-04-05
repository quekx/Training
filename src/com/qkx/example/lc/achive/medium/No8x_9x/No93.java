package com.qkx.example.lc.achive.medium.No8x_9x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/5/31.
 */
public class No93 {
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<>();

        if (s.length() < 4 || s.length() > 12) {
            return res;
        }

        char[] sc = s.toCharArray();

        StringBuilder builder = new StringBuilder();

        add(sc, 1, 0, 0, 0, builder, res);

        return res;
    }
    private static void add(char[] sc, int index, int len, int start, int last, StringBuilder builder, List<String> res) {
        if (sc.length - start > 3 * (4 - index) + (3 - len)) {
            return;
        }



        int current = 10 * last + sc[start] - '0';
        if (current > 255) {
            return;
        }
        if (start == sc.length - 1) {
            if (index != 4) {
                return;
            }
            StringBuilder temp = new StringBuilder(builder);
            temp.append(sc[start]);
            res.add(temp.toString());
            return;
        }

        if (current == 0) {
            StringBuilder temp = new StringBuilder(builder);
            temp.append(sc[start]);
            temp.append('.');
            add(sc, index + 1, 0, start + 1, 0, temp, res);
            return;
        }

        StringBuilder temp1 = new StringBuilder(builder);
        temp1.append(sc[start]);
        temp1.append('.');
        add(sc, index + 1, 0, start + 1, 0, temp1, res);

        StringBuilder temp2 = new StringBuilder(builder);
        temp2.append(sc[start]);
        add(sc, index, len + 1, start + 1, current, temp2, res);

    }
}
