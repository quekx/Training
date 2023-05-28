package contest.lc2023;

import java.util.*;

public class LC0129 {
    /**
     * 给你一个正整数 n ，开始时，它放在桌面上。在 109 天内，每天都要执行下述步骤：
     *
     * 对于出现在桌面上的每个数字 x ，找出符合 1 <= i <= n 且满足 x % i == 1 的所有数字 i 。
     * 然后，将这些数字放在桌面上。
     * 返回在 109 天之后，出现在桌面上的 不同 整数的数目。
     *
     * 注意：
     *
     * 一旦数字放在桌面上，则会一直保留直到结束。
     * % 表示取余运算。例如，14 % 3 等于 2 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-distinct-numbers-on-board
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
     * 24.06%
     * 的用户
     * 内存消耗：
     * 38.8 MB
     * , 在所有 Java 提交中击败了
     * 16.09%
     * 的用户
     * 通过测试用例：
     * 100 / 100
     */
    public int distinctIntegers(int n) {
        Set<Integer> ans = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        while (!queue.isEmpty()) {
            for (int k = 1; k <= queue.size(); k++) {
                int cur = queue.poll();
                ans.add(cur);
                if (cur >= 3 && !ans.contains(cur - 1)) {
                    queue.add(cur - 1);
                }
                for (int i = 2; i * i <= cur - 1; i++) {
                    if ((cur - 1) % i == 0 && !ans.contains(i) && !queue.contains(i)) {
                        queue.add(i);
                    }
                }
            }
        }
        return ans.size();
    }

    /**
     * 现在有一个正凸多边形，其上共有 n 个顶点。顶点按顺时针方向从 0 到 n - 1 依次编号。每个顶点上 正好有一只猴子 。下图中是一个 6 个顶点的凸多边形。
     *
     *
     *
     * 每个猴子同时移动到相邻的顶点。顶点 i 的相邻顶点可以是：
     *
     * 顺时针方向的顶点 (i + 1) % n ，或
     * 逆时针方向的顶点 (i - 1 + n) % n 。
     * 如果移动后至少有两个猴子位于同一顶点，则会发生 碰撞 。
     *
     * 返回猴子至少发生 一次碰撞 的移动方法数。由于答案可能非常大，请返回对 109+7 取余后的结果。
     *
     * 注意，每只猴子只能移动一次。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-collisions-of-monkeys-on-a-polygon
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * ans = 2 ^ n - 2
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 63.23%
     * 的用户
     * 通过测试用例：
     * 83 / 83
     */
    public int monkeyMove(int n) {
        int mod = 1000_000_000 + 7;
        return (int) ((pow(n, mod) - 2 + mod) % mod);
    }

    private long pow(int n, int mod) {
        if (n == 1) {
            return 2;
        }
        long x = pow(n / 2, mod);
        if (n % 2 == 0) {
            return (x * x) % mod;
        } else {
            return (x * x * 2) % mod;
        }
    }

    /**
     * 你有 k 个背包。给你一个下标从 0 开始的整数数组 weights ，其中 weights[i] 是第 i 个珠子的重量。同时给你整数 k 。
     *
     * 请你按照如下规则将所有的珠子放进 k 个背包。
     *
     * 没有背包是空的。
     * 如果第 i 个珠子和第 j 个珠子在同一个背包里，那么下标在 i 到 j 之间的所有珠子都必须在这同一个背包中。
     * 如果一个背包有下标从 i 到 j 的所有珠子，那么这个背包的价格是 weights[i] + weights[j] 。
     * 一个珠子分配方案的 分数 是所有 k 个背包的价格之和。
     *
     * 请你返回所有分配方案中，最大分数 与 最小分数 的 差值 为多少。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/put-marbles-in-bags
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
     * 34 ms
     * , 在所有 Java 提交中击败了
     * 94.49%
     * 的用户
     * 内存消耗：
     * 58 MB
     * , 在所有 Java 提交中击败了
     * 36.24%
     * 的用户
     * 通过测试用例：
     * 103 / 103
     */
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;;
        int[] v = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            v[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(v);
        long max = 0, min = 0;
        for (int i = 0; i < k; i++) {
            min += v[i];
            max += v[n - 2 - i];
        }
        return max - min;
    }

    /**
     * 给你一个长度为 n 下标从 0 开始的整数数组 nums ，它包含 1 到 n 的所有数字，请你返回上升四元组的数目。
     *
     * 如果一个四元组 (i, j, k, l) 满足以下条件，我们称它是上升的：
     *
     * 0 <= i < j < k < l < n 且
     * nums[i] < nums[k] < nums[j] < nums[l] 。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-increasing-quadruplets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 1 3 2 4
     *
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 268 ms
     * , 在所有 Java 提交中击败了
     * 23.70%
     * 的用户
     * 内存消耗：
     * 214.4 MB
     * , 在所有 Java 提交中击败了
     * 34.32%
     * 的用户
     * 通过测试用例：
     * 121 / 121
     */
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        // 预处理，用于查询 i 位置前的元素中，比 x 小的元素个数
        long[][] query = new long[n][n];
        long[] cur = new long[n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(cur, 0, query[i], 0, n);
            add(nums[i] - 1, 1, cur);
        }

        long ans = 0;
        long[] count = new long[n];
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    ans += count[i];
                } else {
                    count[i] += query(nums[j] - 2, query[i]);
                }
            }
        }
        return ans;
    }

    private int lowBit(int x) {
        return x & -x;
    }

    private void add(int i, long v, long[] pos) {
        i += 1;
        while (i < pos.length) {
            pos[i] += v;
            i += lowBit(i);
        }
    }

    private long query(int i, long[] pos) {
        i += 1;
        long ans = 0;
        while (i > 0) {
            ans += pos[i];
            i -= lowBit(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(new LC0129().distinctIntegers(n));
    }
}
