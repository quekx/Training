package contest;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LC0423 {
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }

    // 3 5 7  1 1 1
    // 15 21 35 2 2 2
    // 105 3
    public int sumOfMultiples(int n) {
        return divideSum(n, 3) + divideSum(n, 5) + divideSum(n, 7)
                - (divideSum(n, 15) + divideSum(n, 21) + divideSum(n, 35))
                + divideSum(n, 105);
    }

    private int divideSum(int n, int x) {
        // x + 2x + ... kx
        int k = n / x;
        return (1 + k) * k * x / 2;
    }

    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int n = nums.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(x, Comparator.comparingInt(a -> a[0]));
        int size = 0;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= x - 2; i++) {
            queue.add(new int[]{nums[i], i});
        }
        return null;
    }

    /**
     * 给你一个下标从 0 开始的 正 整数数组 nums 。你可以对数组执行以下操作 任意 次：
     *
     * 选择一个满足 0 <= i < n - 1 的下标 i ，将 nums[i] 或者 nums[i+1] 两者之一替换成它们的最大公约数。
     * 请你返回使数组 nums 中所有元素都等于 1 的 最少 操作次数。如果无法让数组全部变成 1 ，请你返回 -1 。
     *
     * 两个正整数的最大公约数指的是能整除这两个数的最大正整数。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 40.8 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 1067 / 1067
     */
    public int minOperations(int[] nums) {
        // 1. 含有 1
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            }
        }
        if (count != 0) {
            return nums.length - count;
        }
        // 2. 不含 1, 找分段
        int gcd = nums[0];
        int i = 1, ans = Integer.MAX_VALUE;
        while (i < nums.length) {
            gcd = gcd(gcd, nums[i]);
            if (gcd != 1) {
                i++;
                continue;
            }
            int k = i;
            while (k >= 1) {
                int g = gcd(nums[k], nums[k - 1]);
                if (g == 1) {
                    // [k, i]
                    ans = Math.min(ans, i - k + nums.length);
                    break;
                }
                nums[k - 1] = g;
                k--;
            }
            gcd = nums[i];
            i++;
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
