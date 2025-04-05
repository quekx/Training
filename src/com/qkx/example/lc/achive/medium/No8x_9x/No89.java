package com.qkx.example.lc.achive.medium.No8x_9x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/5/29.
 */
public class No89 {
    public List<Integer> grayCode(int n) {

        List<Integer> res = new LinkedList<>();

        List<Integer> temp = new LinkedList<>();
        temp.add(0);
        temp.add(1);

        if (n == 0) {
            res.add(0);
            return res;
        } else if (n == 1) {
            return temp;
        } else {
            add(temp, res, 2, n);
        }

        return res;
    }
    private void add(List<Integer> temp, List<Integer> res, int k, int n) {
        boolean isEven = true;
        List<Integer> ll = new LinkedList<>();
        for (int x : temp) {
            if (isEven) {
                ll.add(x << 1);
                ll.add((x << 1) + 1);
            } else {
                ll.add((x << 1) + 1);
                ll.add(x << 1);
            }
            isEven = !isEven;
        }
        if (k == n) {
            res.addAll(ll);
        } else {
            add(ll, res, k + 1, n);
        }
    }
}
