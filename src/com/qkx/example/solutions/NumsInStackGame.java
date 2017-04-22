package com.qkx.example.solutions;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by qkx on 16/11/25.
 */
public class NumsInStackGame {
    /**
     * 问题：一个拿数字游戏：在一个堆栈里压入n个数字，玩家可以看到每一个数字，游戏规则如下：
     * 玩家轮流从堆栈里取出1-2个数字，直到堆栈空为止，
     * 谁得到的数字之和最大谁赢。如果玩家都是游戏好手，
     * 如果你有先拿或者后拿的选择权利，如何设计一个算法来进行选择使得你自己保持不败？
     */

    /**
     * @param stack 数字
     * @return 先拿是否能赢
     */
    public boolean canWin(int[] stack) {
        if (stack == null || stack.length <= 2) return true;

        int length = stack.length;
        int[] sum = new int[length];
        for (int i = 1; i < length; i++) {
            sum[i] = sum[i - 1] + sum[i];
        }

        int[] dp = new int[length];
        dp[0] = stack[0];
        dp[1] = stack[0] + stack[1];
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(stack[i] + sum[i - 1] - dp[i - 1], stack[i] + stack[i - 1] + sum[i - 2] - dp[i - 2]);
        }

        return dp[length - 1] * 2 > sum[length - 1];
    }

    // add record of index taken
    public List<Integer> solve(int[] stack) {
        List<Integer> result = new LinkedList<>();
        if (stack == null) return result;
        if (stack.length <= 2) {
            for (int num : stack) {
                result.add(num);
            }
            return result;
        }

        int length = stack.length;
        int[] sum = new int[length];
        for (int i = 1; i < length; i++) {
            sum[i] = sum[i - 1] + sum[i];
        }

        int[] num = new int[length];
        num[0] = 1;
        num[1] = 2;
        int[] dp = new int[length];
        dp[0] = stack[0];
        dp[1] = stack[0] + stack[1];
        for (int i = 2; i < length; i++) {
            int q1 = stack[i] + sum[i - 1] - dp[i - 1];
            int q2 = stack[i] + stack[i - 1] + sum[i - 2] - dp[i - 2];
            if (q1 >= q2) {
                num[i] = 1;
                dp[i] = q1;
            } else {
                num[i] = 2;
                dp[i] = q2;
            }
        }

        int index = length - 1;
        boolean turn = true;
        while (index >= 0) {
            if (turn) {
                for (int i = 0; i < num[index]; i++) {
                    result.add(index - i);
                }
            }
            index = index - num[index];
            turn = !turn;
        }

        return result;
    }
}
