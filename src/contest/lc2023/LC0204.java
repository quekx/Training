package contest.lc2023;

import com.qkx.example.utils.ArrayUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC0204 {
    /**
     * 给你一个正整数数组 nums ，请你返回一个数组 answer ，你需要将 nums 中每个整数进行数位分割后，按照 nums 中出现的 相同顺序 放入答案数组中。
     *
     * 对一个整数进行数位分割，指的是将整数各个数位按原本出现的顺序排列成数组。
     *
     * 比方说，整数 10921 ，分割它的各个数位得到 [1,0,9,2,1] 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/separate-the-digits-in-an-array
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
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 68.42%
     * 的用户
     * 通过测试用例：
     * 52 / 52
     */
    public int[] separateDigits(int[] nums) {
        int len = 0;
        for (int num : nums) {
            do {
                len++;
                num /= 10;
            } while (num > 0);
        }
        int[] ans = new int[len];
        int p = len - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            int x = nums[i];
            do {
                ans[p] = x % 10;
                p--;
                x /= 10;
            } while (x > 0);
        }
        return ans;
    }

    /**
     * 给你一个整数数组 banned 和两个整数 n 和 maxSum 。你需要按照以下规则选择一些整数：
     * <p>
     * 被选择整数的范围是 [1, n] 。
     * 每个整数 至多 选择 一次 。
     * 被选择整数不能在数组 banned 中。
     * 被选择整数的和不超过 maxSum 。
     * 请你返回按照上述规则 最多 可以选择的整数数目。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-number-of-integers-to-choose-from-a-range-i
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
     * 54 ms
     * , 在所有 Java 提交中击败了
     * 30.77%
     * 的用户
     * 内存消耗：
     * 43.2 MB
     * , 在所有 Java 提交中击败了
     * 57.89%
     * 的用户
     * 通过测试用例：
     * 208 / 208
     */
    public int maxCount(int[] banned, int n, int maxSum) {
        Arrays.sort(banned);
        // [1 ~ b[0] - 1]
        int sum = 0;
        int ans = 0;
        int i = 0;
        while (i < banned.length && banned[i] - 1 <= n && sum + sum(i == 0 ? 1 : banned[i - 1] + 1, banned[i] - 1) <= maxSum) {
            sum += sum(i == 0 ? 1 : banned[i - 1] + 1, banned[i] - 1);
            ans += i == 0 ? banned[i] - 1 : Math.max(banned[i] - banned[i - 1] - 1, 0);
            i++;
        }
        // b[i - 1] + 1 ~ n ~ b[i] - 1
        int top;
        System.out.println(123);
        if (i < banned.length) {
            top = Math.min(n, banned[i] - 1);
        } else {
            top = n;
        }
        for (int k = (i == 0 ? 1 : banned[i - 1] + 1); k <= top; k++) {
            if (sum + k <= maxSum) {
                sum += k;
                ans++;
            } else {
                break;
            }
        }
        return ans;
    }

    private int sum(int a, int b) {
        if (a > b) {
            return 0;
        }
        return (a + b) * (b - a + 1) / 2;
    }

    /**
     * 在 X轴 上有一些奖品。给你一个整数数组 prizePositions ，它按照 非递减 顺序排列，其中 prizePositions[i] 是第 i 件奖品的位置。数轴上一个位置可能会有多件奖品。再给你一个整数 k 。
     *
     * 你可以选择两个端点为整数的线段。每个线段的长度都必须是 k 。你可以获得位置在任一线段上的所有奖品（包括线段的两个端点）。注意，两个线段可能会有相交。
     *
     * 比方说 k = 2 ，你可以选择线段 [1, 3] 和 [2, 4] ，你可以获得满足 1 <= prizePositions[i] <= 3 或者 2 <= prizePositions[i] <= 4 的所有奖品 i 。
     * 请你返回在选择两个最优线段的前提下，可以获得的 最多 奖品数目。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximize-win-from-two-segments
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
     * 5 ms
     * , 在所有 Java 提交中击败了
     * 57.91%
     * 的用户
     * 内存消耗：
     * 51 MB
     * , 在所有 Java 提交中击败了
     * 52.36%
     * 的用户
     * 通过测试用例：
     * 72 / 72
     */
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        ;
        // p[0] ~ p[i] 一条线段覆盖的最大值
        int[] left = new int[n];
        int pl = 0;
        for (int i = 0; i < n; i++) {
            while (prizePositions[i] - prizePositions[pl] > k) {
                pl++;
            }
            // pl ~ i
            left[i] = Math.max(i - pl + 1, i == 0 ? 0 : left[i - 1]);
        }
        int ans = 0;
        int pr = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (prizePositions[pr] - prizePositions[i] > k) {
                pr--;
            }
            // i ~ pr
            ans = Math.max(ans, (pr - i + 1) + (i == 0 ? 0 : left[i - 1]));
        }
        return ans;
    }

    /**
     * 给你一个下标从 0 开始的 m x n 二进制 矩阵 grid 。你可以从一个格子 (row, col) 移动到格子 (row + 1, col) 或者 (row, col + 1) ，前提是前往的格子值为 1 。如果从 (0, 0) 到 (m - 1, n - 1) 没有任何路径，我们称该矩阵是 不连通 的。
     * <p>
     * 你可以翻转 最多一个 格子的值（也可以不翻转）。你 不能翻转 格子 (0, 0) 和 (m - 1, n - 1) 。
     * <p>
     * 如果可以使矩阵不连通，请你返回 true ，否则返回 false 。
     * <p>
     * 注意 ，翻转一个格子的值，可以使它的值从 0 变 1 ，或从 1 变 0 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/disconnect-path-in-a-binary-matrix-by-at-most-one-flip
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
     * 25 ms
     * , 在所有 Java 提交中击败了
     * 15.09%
     * 的用户
     * 内存消耗：
     * 58.3 MB
     * , 在所有 Java 提交中击败了
     * 32.12%
     * 的用户
     * 通过测试用例：
     * 54 / 54
     */
    public boolean isPossibleToCutPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] isValid = new boolean[m][n];
        isValid[m - 1][n - 1] = true;
        queue.add(new int[]{m - 1, n - 1});
        while (!queue.isEmpty()) {
            int k = queue.size();
            for (int i = 0; i < k; i++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                if (x - 1 >= 0 && grid[x - 1][y] == 1) {
                    if (!isValid[x - 1][y]) {
                        queue.add(new int[]{x - 1, y});
                        isValid[x - 1][y] = true;
                    }
                }
                if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                    if (!isValid[x][y - 1]) {
                        queue.add(new int[]{x, y - 1});
                        isValid[x][y - 1] = true;
                    }
                }
            }
        }

        boolean[][] visit = new boolean[m][n];
        visit[0][0] = true;
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int k = queue.size();
            if (k == 1) {
                int[] cur = queue.peek();
                if ((cur[0] != 0 || cur[1] != 0) && (cur[0] != m - 1 || cur[1] != n - 1)) {
                    return true;
                }
            }
            for (int i = 0; i < k; i++) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                if (x + 1 < m && grid[x + 1][y] == 1) {
                    if (!visit[x + 1][y] && isValid[x + 1][y]) {
                        queue.add(new int[]{x + 1, y});
                        visit[x + 1][y] = true;
                    }
                }
                if (y + 1 < n && grid[x][y + 1] == 1) {
                    if (!visit[x][y + 1] && isValid[x][y + 1]) {
                        queue.add(new int[]{x, y + 1});
                        visit[x][y + 1] = true;
                    }
                }
            }
        }
        return !visit[m - 1][n - 1];
    }

    public static void main(String[] args) {
//        int[] b = {87,193,85,55,14,69,26,133,171,180,4,8,29,121,182,78,157,53,26,7,117,138,57,167,8,103,32,110,15,190,139,16,49,138,68,69,92,89,140,149,107,104,2,135,193,87,21,194,192,9,161,188,73,84,83,31,86,33,138,63,127,73,114,32,66,64,19,175,108,80,176,52,124,94,33,55,130,147,39,76,22,112,113,136,100,134,155,40,170,144,37,43,151,137,82,127,73};
//        int n = 1079;
//        int maxSum = 87;
//        System.out.println(new LC0204().maxCount(b, n, maxSum));

//        int[] p = {3937,3938,3939,3951,3951,3959,3975,3988,3993,4010,4031,4033,4036,4038,4039,4041,4047,4058,4059,4064,4072,4081,4084,4084,4089,4094,4098,4112,4114,4116,4123,4123,4127,4130,4135,4143,4149,4152,4163,4164,4176,4178,4180,4198,4216,4224,4233,4240,4253,4259,4273,4286,4305,4322,4335,4350,4364,4378,4396,4397,4398,4404,4415,4421,4430,4469,4476,4490,4492,4497,4504,4519,4519,4525,4526,4530,4530,4540,4550,4554,4563,4571,4571,4595,4595,4606,4639,4639,4660,4663,4676,4678,4680,4695,4697,4709,4709,4711,4724,4751,4781,4786,4786,4794,4797,4801,4807,4808,4817,4822,4824,4825,4840,4851,4887,4889,4891,4910,4917,4927,4931,4932,4951,4959,4964,4993,4997,5003,5003,5006,5006,5022,5029,5035,5043,5045,5045,5046,5059,5060,5079,5084,5105,5109,5109,5112,5120,5126,5130,5142,5143,5151,5152,5154,5156,5168,5189,5213,5214,5223,5226,5235,5247,5259,5272,5289,5303,5309,5317,5322,5344,5347,5352,5374,5379,5380,5383,5385,5391,5418,5425,5429,5432,5479,5486,5490,5502,5502,5505,5506,5509,5515,5518,5519,5521,5526,5528,5533,5536,5536,5538,5555,5556,5557,5557,5566,5571,5580,5585,5596,5604,5619,5634,5649,5668,5694,5696,5699,5701,5704,5709,5732,5745,5745,5746,5749,5762,5766,5766,5770,5773,5796,5810,5817,5823,5838,5843,5846,5860,5869,5872,5877,5880,5896,5899,5902,5905,5910,5913,5913,5915,5923};
//        int k = 220;
//        System.out.println(new LC0204().maximizeWin(p, k));

//        int[][] g = {{1, 1, 1, 0, 0}, {1, 0, 1, 0, 0}, {1, 1, 1, 1, 1}, {0, 0, 1, 1, 1}, {0, 0, 1, 1, 1}};
//        int[][] g = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] g = {{1}};
        ArrayUtil.print(g);
        System.out.println(new LC0204().isPossibleToCutPath(g));
    }
}
