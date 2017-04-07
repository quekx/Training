package com.qkx.example.solutions.medium.x;

import java.util.*;

/**
 * Created by qkx on 16/11/18.
 */
public class No322 {

    // 用数组存储
    // AC 42ms
    public static int coinChange(int[] coins, int amount) {
        if (coins.length == 0) return -1;
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        Arrays.sort(coins);
        if (coins[0] > amount) return -1;

        int[][] dp = new int[coins.length][amount + 1];

        return get(coins, coins.length - 1, amount, dp);
    }

    private static int get(int[] coins, int index, int num, int[][] dp) {
        if (index < 0) return -1;
        if (num == 0) return 0;

        if (dp[index][num] != 0) return dp[index][num];

        if (coins[index] > num) {
            int temp = get(coins, index - 1, num, dp);
            dp[index][num] = temp;
            return temp;
        }

        int a = get(coins, index - 1, num, dp);
        int b = get(coins, index, num - coins[index], dp) + 1;
        int res;
        if (a > 0 && b > 0) {
            res = Math.min(a, b);
        } else if (a > 0) {
            res = a;
        } else if (b > 0) {
            res = b;
        } else {
            res = -1;
        }
        dp[index][num] = res;
        return res;
    }

        // AC 600+ms
    public static int coinChange2(int[] coins, int amount) {
        if (coins.length == 0) return -1;
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        Arrays.sort(coins);
        if (coins[0] > amount) return -1;

//        Map<Integer, Integer> map = new HashMap<>();
        List<Map<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            list.add(new HashMap<>());
        }


        return get2(coins, coins.length - 1, amount, list);
    }

    // coins[0] ~ coins[index] 组成num的个数
    private static int get2(int[] coins, int index, int num, List<Map<Integer, Integer>> list) {
        if (index < 0) return -1;
        if (num == 0) return 0;

        Map<Integer, Integer> map1;
        if ((map1 = list.get(index)).containsKey(num)) return map1.get(num);

        if (coins[index] > num) {
            int temp = get2(coins, index - 1, num, list);
            list.get(index).put(num, temp);
            return temp;
        }

        // 没有coin[index]
        int a = get2(coins, index - 1, num, list);
        // 有coin[index]
        int b = get2(coins, index, num - coins[index], list) + 1;
        int res;
        if (a > 0 && b > 0) {
            res = Math.min(a, b);
        } else if (a > 0) {
            res = a;
        } else if (b > 0) {
            res = b;
        } else {
            res = -1;
        }
        list.get(index).put(num, res);
        return res;


//        int res;
//        int numIndex = Arrays.binarySearch(coins, num, 0, index);
//        if (numIndex >= 0) {
//            list.get2(numIndex).put(num, 1);
//            return 1;
//        } else {
//            // 最大但不大于num的数下标
//            int largestIndex = -(numIndex + 2);
//            if (largestIndex < 0) {
//                // 不存在,既找不到比num小的数
//                list.get2(numIndex).put(num, - 1);
//                return -1;
//            } else {
//                int numA = num - coins[largestIndex];
//                int a = get2(coins, )
//            }
//        }


    }


    // TLE
    // [442,295,365,485] 8091
    public static int coinChange222(int[] coins, int amount) {
        if (coins.length == 0) return -1;
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        Arrays.sort(coins);
        if (coins[0] > amount) return -1;

        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        list.add(coins[0]);
        map.put(coins[0], 1);

        int cur = coins[0] + 1;
        while (cur <= amount) {
            int index = Arrays.binarySearch(coins, cur);
            if (index >= 0) {
                list.add(cur);
                map.put(cur, 1);

                cur++;
                continue;
            }
            int num1, num2;
            int temp = -1;
            for (int i = 0; i < list.size() && (num1 = list.get(i)) <= cur / 2; i++) {
                num2 = cur - num1;
                if (map.containsKey(num2)) {
                    temp = temp == -1 ? map.get(num1) + map.get(num2) :
                            Math.min(temp, map.get(num1) + map.get(num2));
                }
            }
            if (temp > 0) {
                list.add(cur);
                map.put(cur, temp);
            }
            cur++;
        }
        return map.containsKey(amount) ? map.get(amount) : -1;
    }
}
