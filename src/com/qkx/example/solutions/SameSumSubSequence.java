package com.qkx.example.solutions;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author kaixin
 * @since 2022-04-14 11:07
 */
public class SameSumSubSequence {
    public static void main(String[] args) {
        int[] nums = {1, 20, 5, 7, 9};
        printSameSumSubSequence(nums);
    }

    /**
     * 给定一个数组a，长度为n，1<=n<=100,1<=a[i]<=100，问能否找到两个子序列，他们的和相等，如果能找到，分别输出两个子序列，如果不能，输出-1。
     * 能不能找到等和子序列，这个我用dp解决了，但是怎么输出这两个子序列没啥思路，难不成用回溯？但是回溯的时间复杂度有点高啊。。。ema1
     * --
     * dp[i] 标志位
     * dp[i] = 0：
     * 代表目前没有和为 i 的子序列
     * dp[i] != 0：
     * 代表目前存在和为 i 的子序列，并且最后一个元素的下标为 dp[i]
     * 该序列去除最后一个元素的和为 i - nums[dp[i]]
     * 则倒数第二个元素下标为 dp[i - nums[dp[i]]]
     *
     * @param nums
     */
    public static void printSameSumSubSequence(int[] nums) {
        Node[] nodes = new Node[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i);
            sum += nums[i];
        }
        Arrays.sort(nodes, new NodeComparator());
        int[] dp = new int[sum + 1];

        int sumMax = nodes[0].val;
        dp[sumMax] = nodes[0].index + 1;

        for (int i = 1; i < nodes.length; i++) {
            int curVal = nodes[i].val;
            int curIndex = nodes[i].index;
            if (dp[curVal] != 0) {
                System.out.println("sum >> " + curVal);
                System.out.println("first1: ");
                System.out.println(curIndex);

                System.out.println("second: ");
                printPreNode(dp, nums, curVal);
                return;
            }

            dp[curVal] = curIndex + 1;
            int max = sumMax;
            for (int k = 1; k <= sumMax; k++) {
                if (dp[k] != 0) {
                    int curSum = k + curVal;
                    if (dp[curSum] != 0) {
                        System.out.println("sum >> " + curSum);
                        System.out.println("first2: ");
                        printPreNode(dp, nums, k);
                        System.out.println("-" + curIndex);

                        System.out.println("second: ");
                        printPreNode(dp, nums, curSum);
                        return;
                    } else {
                        dp[curSum] = curIndex + 1;
                        max = Math.max(max, curSum);
                    }
                }
            }
            sumMax = max;
        }

    }

    private static void printPreNode(int[] dp, int[] nums, int sum) {
        if (sum <= 0) {
            return;
        }
        int index = dp[sum] - 1;
        printPreNode(dp, nums, sum - nums[index]);
        System.out.print("-");
        System.out.print(index);
    }

    private static class Node {
        int val;
        int index;

        public Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    private static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.val == o2.val) {
                return 0;
            }
            return o1.val < o2.val ? -1 : 1;
        }
    }
}


