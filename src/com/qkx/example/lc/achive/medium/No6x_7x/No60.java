package com.qkx.example.lc.achive.medium.No6x_7x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/5/24.
 */
public class No60 {
    public static String getPermutation(int n, int k) {

        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int[] factorials = getFactorials(n);
        StringBuilder builder = new StringBuilder();
        add(list, k - 1, factorials, builder);

        return builder.toString();
    }
    private static void add(List<Integer> list, int index, int[] factorials, StringBuilder builder) {
        if (list.isEmpty()) {
            return;
        }

        int num = list.size();
        int factorial = factorials[num - 1];

        int i = index / factorial;
        int ki = index - i * factorial;

        builder.append(list.get(i));
        list.remove(i);

        add(list, ki, factorials, builder);
    }
    private static int[] getFactorials(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            res[i] = res[i - 1] * i;
        }
        return res;
    }
}
