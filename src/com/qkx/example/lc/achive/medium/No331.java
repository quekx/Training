package com.qkx.example.lc.achive.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qkx on 17/6/17.
 */
public class No331 {
    public static boolean isValidSerialization(String preorder) {
        if (preorder == null) return false;
        String[] orders = preorder.split(",");
        if (orders.length == 0) return false;

        List<String> list = new ArrayList<>();
        for (String order : orders) {
            list.add(order);
        }

        while (true) {
            boolean isValid = false;
            List<String> temp = new ArrayList<>();
            int i = 0;
            while (i <= list.size() - 1) {
                if (i <= list.size() - 3 && !list.get(i).equals("#") && list.get(i + 1).equals("#") && list.get(i + 2).equals("#")) {
                    temp.add("#");
                    i += 3;
                    isValid = true;
                } else {
                    temp.add(list.get(i));
                    i++;
                }
            }
            if (isValid) {
                list = temp;
            } else {
                break;
            }
        }

        return list.size() == 1 && list.get(0).equals("#");
    }

    private static boolean isValid(String[] orders, int start, int end) {
        if (start == end && orders[start].equals("#")) return true;
        if (start + 1 == end) return false;
        if (orders[start].equals("#")) return false;

        for (int i = start + 1; i <= end - 1; i++) {
            if (isValid(orders, start + 1, i) && isValid(orders, i + 1, end)) return true;
        }

        return false;
    }
}
