package contest;

import com.qkx.example.utils.ArrayUtil;
import com.sun.jmx.snmp.SnmpNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//1643
public class LC0218 {
    /**
     * 给你一个整数 num 。你知道 Danny Mittal 会偷偷将 0 到 9 中的一个数字 替换 成另一个数字。
     *
     * 请你返回将 num 中 恰好一个 数字进行替换后，得到的最大值和最小值的差位多少。
     *
     * 注意：
     *
     * 当 Danny 将一个数字 d1 替换成另一个数字 d2 时，Danny 需要将 nums 中所有 d1 都替换成 d2 。
     * Danny 可以将一个数字替换成它自己，也就是说 num 可以不变。
     * Danny 可以将数字分别替换成两个不同的数字分别得到最大值和最小值。
     * 替换后得到的数字可以包含前导 0 。
     * Danny Mittal 获得周赛 326 前 10 名，让我们恭喜他。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-difference-by-remapping-a-digit
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     * <p>
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.4 MB
     * , 在所有 Java 提交中击败了
     * 88.93%
     * 的用户
     * 通过测试用例：
     * 214 / 214
     */
    public int minMaxDifference(int num) {
        int maxD = 0;
        int minD = 0;
        int t = num;
        while (t > 0) {
            int x = t % 10;
            if (x != 9) {
                maxD = x;
            }
            minD = x;
            t /= 10;
        }
        int ans = 0;
        int sign = 1;
        while (num > 0) {
            int x = num % 10;
            if (x == maxD) {
                ans += (9 - maxD) * sign;
            }
            if (x == minD) {
                ans += minD * sign;
            }
            sign *= 10;
            num /= 10;
        }
        return ans;
    }

    /**
     * 给你一个下标从 0 开始的整数数组 nums 。
     *
     * nums 的 最小 得分是满足 0 <= i < j < nums.length 的 |nums[i] - nums[j]| 的最小值。
     * nums的 最大 得分是满足 0 <= i < j < nums.length 的 |nums[i] - nums[j]| 的最大值。
     * nums 的分数是 最大 得分与 最小 得分的和。
     * 我们的目标是最小化 nums 的分数。你 最多 可以修改 nums 中 2 个元素的值。
     *
     * 请你返回修改 nums 中 至多两个 元素的值后，可以得到的 最小分数 。
     *
     * |x| 表示 x 的绝对值。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-score-by-changing-two-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     * <p>
     * 执行用时：
     * 16 ms
     * , 在所有 Java 提交中击败了
     * 98.61%
     * 的用户
     * 内存消耗：
     * 54.3 MB
     * , 在所有 Java 提交中击败了
     * 97.00%
     * 的用户
     * 通过测试用例：
     * 69 / 69
     */
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        if (n <= 3) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, nums[n - 3] - nums[0]);
        ans = Math.min(ans, nums[n - 2] - nums[1]);
        ans = Math.min(ans, nums[n - 1] - nums[2]);
        return ans;
    }

    /**
     * 给你一个下标从 0 开始的整数数组 nums 。
     *
     * 如果存在一些整数满足 0 <= index1 < index2 < ... < indexk < nums.length ，得到 nums[index1] | nums[index2] | ... | nums[indexk] = x ，那么我们说 x 是 可表达的 。换言之，如果一个整数能由 nums 的某个子序列的或运算得到，那么它就是可表达的。
     *
     * 请你返回 nums 不可表达的 最小非零整数 。
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-impossible-or
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     * <p>
     * 执行用时：
     * 23 ms
     * , 在所有 Java 提交中击败了
     * 87.76%
     * 的用户
     * 内存消耗：
     * 56.7 MB
     * , 在所有 Java 提交中击败了
     * 24.78%
     * 的用户
     * 通过测试用例：
     * 68 / 68
     */
    public int minImpossibleOR(int[] nums) {
        Arrays.sort(nums);
        int ans = 1;
        while (find(nums, ans)) {
            ans = ans << 1;
        }
        return ans;
    }

    private boolean find(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

    /**
     * 给你两个下标从 0 开始的数组 nums1 和 nums2 ，和一个二维数组 queries 表示一些操作。总共有 3 种类型的操作：
     * <p>
     * 操作类型 1 为 queries[i] = [1, l, r] 。你需要将 nums1 从下标 l 到下标 r 的所有 0 反转成 1 或将 1 反转成 0 。l 和 r 下标都从 0 开始。
     * 操作类型 2 为 queries[i] = [2, p, 0] 。对于 0 <= i < n 中的所有下标，令 nums2[i] = nums2[i] + nums1[i] * p 。
     * 操作类型 3 为 queries[i] = [3, 0, 0] 。求 nums2 中所有元素的和。
     * 请你返回一个数组，包含所有第三种操作类型的答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/handling-sum-queries-after-update
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[] values = new int[n + 1];
        return null;
    }

    public long[] handleQuery2(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        // 差分数组
        int[] f = new int[n];
        LinkedList<Long> t = new LinkedList<>();
        long sum = 0L;
        for (int num : nums2) {
            sum += num;
        }
        for (int[] query : queries) {
            if (query[0] == 1) {
                int l = query[1], r = query[2];
                f[l]++;
                if (r + 1 < n) {
                    f[r + 1]--;
                }
            } else if (query[0] == 2) {
                int p = query[1];
                int fSum = 0;
                for (int i = 0; i < n; i++) {
                    fSum += f[i];
                    sum += (long) p * (fSum % 2 == 0 ? nums1[i] : (1 - nums1[i]));
                }
            } else {
                t.add(sum);
            }
        }
        int size = t.size();
        long[] ans = new long[size];
        int i = 0;
        for (long e : t) {
            ans[i++] = e;
        }
        return ans;
    }

    public static void main(String[] args) {
//        int num = 11891;
//        System.out.println(new LC0218().minMaxDifference(90));

        int[] nums1 = {1, 0, 1};
        int[] nums2 = {0, 0, 0};
        int[][] queries = {{1, 1, 1}, {2, 1, 0}, {3, 0, 0}};
        ArrayUtil.print(new LC0218().handleQuery(nums1, nums2, queries));
    }
}
