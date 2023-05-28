package contest.lc2023;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC0318 {
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }
        int r = money - children;
        int count = r / 7;
        int remain = r % 7;
        if (count == children) {
            return remain == 0 ? count : count - 1;
        } else if (count == children - 1) {
            return remain == 3 ? count - 1 : count;
        } else if (count > children) {
            return children - 1;
        } else {
            return count;
        }
    }

    /**
     * 给你一个下标从 0 开始的整数数组 nums 。你需要将 nums 重新排列成一个新的数组 perm 。
     *
     * 定义 nums 的 伟大值 为满足 0 <= i < nums.length 且 perm[i] > nums[i] 的下标数目。
     *
     * 请你返回重新排列 nums 后的 最大 伟大值。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximize-greatness-of-an-array
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
     * 8 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 56.1 MB
     * , 在所有 Java 提交中击败了
     * 46.99%
     * 的用户
     * 通过测试用例：
     * 1071 / 1071
     */
    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int j = 0;
        for (int num : nums) {
            while (j < nums.length && nums[j] <= num) {
                j++;
            }
            if (j < nums.length) {
                ans++;
                j++;
            } else {
                break;
            }
        }
        return ans;
    }

    /**
     * 给你一个数组 nums ，它包含若干正整数。
     * <p>
     * 一开始分数 score = 0 ，请你按照下面算法求出最后分数：
     * <p>
     * 从数组中选择最小且没有被标记的整数。如果有相等元素，选择下标最小的一个。
     * 将选中的整数加到 score 中。
     * 标记 被选中元素，如果有相邻元素，则同时标记 与它相邻的两个元素 。
     * 重复此过程直到数组中所有元素都被标记。
     * 请你返回执行上述算法后最后的分数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-score-of-an-array-after-marking-all-elements
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
     * 200 ms
     * , 在所有 Java 提交中击败了
     * 42.51%
     * 的用户
     * 内存消耗：
     * 59.5 MB
     * , 在所有 Java 提交中击败了
     * 27.80%
     * 的用户
     * 通过测试用例：
     * 1044 / 1044
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 188 ms
     * , 在所有 Java 提交中击败了
     * 59.89%
     * 的用户
     * 内存消耗：
     * 59.7 MB
     * , 在所有 Java 提交中击败了
     * 27.00%
     * 的用户
     * 通过测试用例：
     * 1044 / 1044
     */
    public long findScore(int[] nums) {
        int n = nums.length;
        boolean[] isDel = new boolean[n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(n, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        for (int i = 0; i < n; i++) {
            queue.add(new int[]{nums[i], i});
        }
        long ans = 0L;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty() && isDel[queue.peek()[1]]) {
                queue.poll();
            }
            if (queue.isEmpty()) {
                break;
            }
            int[] cur = queue.poll();
            ans += cur[0];
            if (cur[1] + 1 < n) {
                isDel[cur[1] + 1] = true;
            }
            if (cur[1] - 1 >= 0) {
                isDel[cur[1] - 1] = true;
            }
        }
        return ans;
    }

    /**
     * 给你一个整数数组 ranks ，表示一些机械工的 能力值 。ranksi 是第 i 位机械工的能力值。能力值为 r 的机械工可以在 r * n2 分钟内修好 n 辆车。
     *
     * 同时给你一个整数 cars ，表示总共需要修理的汽车数目。
     *
     * 请你返回修理所有汽车 最少 需要多少时间。
     *
     * 注意：所有机械工可以同时修理汽车。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-time-to-repair-cars
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
     * 435 ms
     * , 在所有 Java 提交中击败了
     * 11.46%
     * 的用户
     * 内存消耗：
     * 48.8 MB
     * , 在所有 Java 提交中击败了
     * 70.75%
     * 的用户
     * 通过测试用例：
     * 1071 / 1071
     */
    public long repairCars(int[] ranks, int cars) {
        long l = 0L, r = (long) ranks[0] * cars * cars;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
//            System.out.println(l + " >> " + mid + " >> " + r);
            if (check(ranks, cars, mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] ranks, int cars, long time) {
        // n ^ 2 = t / r
        for (int r : ranks) {
            cars -= sqrt(time / r);
            if (cars <= 0) {
                return true;
            }
        }
        return false;
    }

    private long sqrt(long a) {
        long i = 0;
        while (i * i <= a) {
            i++;
        }
        return i - 1;
    }

    public static void main(String[] args) {
        System.out.println(50000000000000L * 50000000000000L);

        int[] ranks = {100};
        int car = 100_0000;
//        System.out.println(new LC0318().repairCars(ranks, car));
        System.out.println(new LC0318().sqrt(100000000000000L));
    }
}
