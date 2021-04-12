package com.qkx.example.solutions.grape;

/**
 * @author kaixin
 * @since 2021-04-12 下午6:01
 * 有三种葡萄，每种分别有 a, b, c 颗，现在有三个人，
 * 第一个人只吃第一种和第二种葡萄，
 * 第二个人只吃第二种和第三种葡萄，
 * 第三个人只吃第一种和第三种葡萄。
 * 现在给你输入 a, b, c 三个值，请你适当安排，让三个人吃完所有的葡萄，算法返回吃的最多的人最少要吃多少颗葡萄。
 */
public class GrapeEater {
    /**
     * 暴力遍历
     * a = a1 + a3
     * b = b1 + b2
     * c = c2 + c3
     * min(max(a1 + b1, b2 + c2, a3 + b3))
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minNum(int a, int b, int c) {
        int min = Integer.MAX_VALUE;
        for (int a1 = 0; a1 <= a; a1++) {
            int a3 = a - a1;
            for (int b2 = 0; b2 <= b; b2++) {
                int b1 = b - b2;
                for (int c3 = 0; c3 <= c; c3++) {
                    int c2 = c - c3;
                    int max = Integer.max(a1 + b1, b2 + c2);
                    max = Integer.max(max, a3 + c3);
                    min = Integer.min(min, max);
                }
            }
        }
        return min;
    }
}
