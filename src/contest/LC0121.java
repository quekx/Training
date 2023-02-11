package contest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC0121 {
    /**
     * 给你两个整数数组 nums1 和 nums2 ，它们已经按非降序排序，请你返回两个数组的 最小公共整数 。如果两个数组 nums1 和 nums2 没有公共整数，请你返回 -1 。
     *
     * 如果一个整数在两个数组中都 至少出现一次 ，那么这个整数是数组 nums1 和 nums2 公共 的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-common-value
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 99.53%
     * 的用户
     * 内存消耗：
     * 57.7 MB
     * , 在所有 Java 提交中击败了
     * 77.31%
     * 的用户
     * 通过测试用例：
     * 40 / 40
     */
    public int getCommon(int[] nums1, int[] nums2) {
        int a = 0, b = 0;
        while (a < nums1.length && b < nums2.length) {
            if (nums1[a] == nums2[b]) {
                return nums1[a];
            } else if (nums1[a] < nums2[b]) {
                a++;
            } else {
                b++;
            }
        }
        return -1;
    }

    /**
     * 给你两个整数数组 nums1 和 nums2 ，两个数组长度都是 n ，再给你一个整数 k 。你可以对数组 nums1 进行以下操作：
     *
     * 选择两个下标 i 和 j ，将 nums1[i] 增加 k ，将 nums1[j] 减少 k 。换言之，nums1[i] = nums1[i] + k 且 nums1[j] = nums1[j] - k 。
     * 如果对于所有满足 0 <= i < n 都有 num1[i] == nums2[i] ，那么我们称 nums1 等于 nums2 。
     *
     * 请你返回使 nums1 等于 nums2 的 最少 操作数。如果没办法让它们相等，请你返回 -1 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-operations-to-make-array-equal-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 88.29%
     * 的用户
     * 内存消耗：
     * 59.8 MB
     * , 在所有 Java 提交中击败了
     * 5.24%
     * 的用户
     * 通过测试用例：
     * 68 / 68
     */
    public long minOperations(int[] nums1, int[] nums2, int k) {
        if (k == 0) {
            for (int i = 0; i < nums1.length; i++) {
                if (nums1[i] != nums2[i]) {
                    return -1;
                }
            }
            return 0;
        }
        long sum1 = 0, sum2 = 0;
        for (int num : nums1) {
            sum1 += num;
        }
        for (int num : nums2) {
            sum2 += num;
        }
        if (sum1 != sum2) {
            return -1;
        }
        long ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            int delta = Math.abs(nums1[i] - nums2[i]);
            if (delta % k != 0) {
                return -1;
            } else {
                ans += delta / k;
            }
        }
        return ans / 2;
    }

    /**
     * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两者长度都是 n ，再给你一个正整数 k 。你必须从 nums1 中选一个长度为 k 的 子序列 对应的下标。
     *
     * 对于选择的下标 i0 ，i1 ，...， ik - 1 ，你的 分数 定义如下：
     *
     * nums1 中下标对应元素求和，乘以 nums2 中下标对应元素的 最小值 。
     * 用公示表示： (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]) 。
     * 请你返回 最大 可能的分数。
     *
     * 一个数组的 子序列 下标是集合 {0, 1, ..., n-1} 中删除若干元素得到的剩余集合，也可以不删除任何元素。
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-subsequence-score
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 71 ms
     * , 在所有 Java 提交中击败了
     * 83.68%
     * 的用户
     * 内存消耗：
     * 56.6 MB
     * , 在所有 Java 提交中击败了
     * 56.88%
     * 的用户
     * 通过测试用例：
     * 27 / 27
     */
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        Arrays.sort(p, Comparator.comparingInt(a -> nums2[a]));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        long sum = 0;
        for (int i = n - 1; i >= n - k + 1; i--) {
            queue.add(nums1[p[i]]);
            sum += nums1[p[i]];
        }
        long ans = 0;
        for (int i = n - k; i >= 0; i--) {
            ans = Math.max(ans, (sum + nums1[p[i]]) * nums2[p[i]]);
            if (!queue.isEmpty() && nums1[p[i]] > queue.peek()) {
                sum -= queue.poll();
                sum += nums1[p[i]];
                queue.add(nums1[p[i]]);
            }
        }
        return ans;
    }

    /**
     * 给你一个无穷大的网格图。一开始你在 (1, 1) ，你需要通过有限步移动到达点 (targetX, targetY) 。
     *
     * 每一步 ，你可以从点 (x, y) 移动到以下点之一：
     *
     * (x, y - x)
     * (x - y, y)
     * (2 * x, y)
     * (x, 2 * y)
     * 给你两个整数 targetX 和 targetY ，分别表示你最后需要到达点的 X 和 Y 坐标。如果你可以从 (1, 1) 出发到达这个点，请你返回true ，否则返回 false 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/check-if-point-is-reachable
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isReachable(int targetX, int targetY) {
        int gcd = gcd(targetX, targetY);
        return (gcd & (gcd - 1)) == 0;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
