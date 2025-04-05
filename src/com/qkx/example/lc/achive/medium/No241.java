package com.qkx.example.lc.achive.medium;

import java.util.*;

/**
 * @author kaixin
 * @since 2021-11-02 11:32
 */
public class No241 {

    //(2*(3-(4*5))) = -34
    //((2*3)-(4*5)) = -14
    //((2*(3-4))*5) = -10
    //(2*((3-4)*5)) = -10
    //(((2*3)-4)*5) = 10

    /**
     * 解答成功: 执行耗时:1 ms,击败了100.00% 的Java用户 内存消耗:40.4 MB,击败了98.85% 的Java用
     */
    public List<Integer> diffWaysToCompute(String expression) {
        char[] arr = expression.toCharArray();
        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<Character> opts = new ArrayList<>();
        char opt = '+';
        int num = 0;
        for (char w : arr) {
            if (w == '+' || w == '-' || w == '*') {
                nums.add(num);
                opts.add(opt);
                opt = w;
                num = 0;
            } else {
                num = num * 10 + w - '0';
            }
        }
        nums.add(num);
        opts.add(opt);
        opts.remove(0);

        int m = nums.size();
        ArrayList<Integer>[][] dp = new ArrayList[m][m];
        for (int i = 0; i < m; i++) {
            dp[i][i] = new ArrayList<>();
            dp[i][i].add(nums.get(i));
        }
        for (int i = 0; i <= m - 2; i++) {
            dp[i][i + 1] = new ArrayList<>();
            dp[i][i + 1].add(doCal(nums.get(i), nums.get(i + 1), opts.get(i)));
        }
        for (int d = 2; d < m; d++) {
            for (int l = 0; l + d < m; l++) {
                int r = l + d;
                dp[l][r] = new ArrayList<>();

                ArrayList<Integer> pre = dp[l][r - 1];
                for (int lr : pre) {
                    dp[l][r].add(doCal(lr, nums.get(r), opts.get(r - 1)));
                }
                for (int s = l + 1; s < r; s++) {
                    // [l, s - 1] [s, r]
                    ArrayList<Integer> left = dp[l][s - 1];
                    ArrayList<Integer> right = dp[s][r];
                    System.out.println(l + "~" + (s - 1) + ":" + left);
                    System.out.println(s + "~" + r + ":" + right);
                    for (int lr : left) {
                        for (int rr : right) {
                            dp[l][r].add(doCal(lr, rr, opts.get(s - 1)));
                        }
                    }
                }
            }
        }
        return dp[0][m - 1];
    }

    private int doCal(int num1, int num2, char opt) {
        if (opt == '+') {
            return num1 + num2;
        } else if (opt == '-') {
            return num1 - num2;
        } else {
            return num1 * num2;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new No241().diffWaysToCompute("2*3-4*5"));
        System.out.println(new No241().diffWaysToCompute("2*3-4"));
    }

    public List<Integer> diffWaysToCompute2(String expression) {
        char[] arr = expression.toCharArray();
        ArrayList<int[]> pairs = new ArrayList<>();
        char opt = '+';
        int num = 0;
        for (char w : arr) {
            if (w == '+' || w == '-' || w == '*') {
                pairs.add(new int[]{opt, num});
                opt = w;
                num = 0;
            } else {
                num = num * 10 + w - '0';
            }
        }
        pairs.add(new int[]{opt, num});

        Stack<Integer> s = new Stack<>();
        s.add(0);
        List<Integer> ans = new ArrayList<>();
        f(s, 0, pairs, ans);
        return ans;
    }

    /**
     * 存在重复合并计算问题
     */
    private void f(Stack<Integer> s, int i, ArrayList<int[]> pairs, List<Integer> ans) {
        if (i == pairs.size()) {
            while (s.size() >= 3) {
                int a = s.pop();
                int opt = s.pop();
                int b = s.pop();
                if (opt == '+') {
                    s.push(b + a);
                } else if (opt == '-') {
                    s.push(b - a);
                } else {
                    s.push(b * a);
                }
            }
            ans.add(s.pop());
            return;
        }
        Stack<Integer> ns = (Stack<Integer>) s.clone();

        int[] p = pairs.get(i);
        int opt = p[0], num = p[1];
        s.push(opt);
        s.push(num);
        f(s, i + 1, pairs, ans);

        if (ns.size() >= 1) {
            int top = ns.pop();
            if (opt == '+') {
                ns.push(top + num);
            } else if (opt == '-') {
                ns.push(top - num);
            } else {
                ns.push(top * num);
            }
        }
        f(ns, i + 1, pairs, ans);
    }
}
