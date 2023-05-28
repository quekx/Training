package contest.lc2023;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 10:40
public class LC0122 {
    /**
     * 给你一个正整数 n 。n 中的每一位数字都会按下述规则分配一个符号：
     *
     * 最高有效位 上的数字分配到 正 号。
     * 剩余每位上数字的符号都与其相邻数字相反。
     * 返回所有数字及其对应符号的和。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/alternating-digit-sum
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
     * 38.1 MB
     * , 在所有 Java 提交中击败了
     * 91.47%
     * 的用户
     * 通过测试用例：
     * 118 / 118
     */
    public int alternateDigitSum(int n) {
        int sign = 1;
        int ans = 0;
        while (n > 0) {
            ans += sign * (n % 10);
            n /= 10;
            sign *= -1;
        }
        return -sign * ans;
    }

    /**
     * 班里有 m 位学生，共计划组织 n 场考试。给你一个下标从 0 开始、大小为 m x n 的整数矩阵 score ，其中每一行对应一位学生，而 score[i][j] 表示第 i 位学生在第 j 场考试取得的分数。矩阵 score 包含的整数 互不相同 。
     * <p>
     * 另给你一个整数 k 。请你按第 k 场考试分数从高到低完成对这些学生（矩阵中的行）的排序。
     * <p>
     * 返回排序后的矩阵。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/sort-the-students-by-their-kth-score
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> a[k] - b[k]);
        return score;
    }

    /**
     * 给你两个下标从 0 开始的 二元 字符串 s 和 target ，两个字符串的长度均为 n 。你可以对 s 执行下述操作 任意 次：
     *
     * 选择两个 不同 的下标 i 和 j ，其中 0 <= i, j < n 。
     * 同时，将 s[i] 替换为 (s[i] OR s[j]) ，s[j] 替换为 (s[i] XOR s[j]) 。
     * 例如，如果 s = "0110" ，你可以选择 i = 0 和 j = 2，然后同时将 s[0] 替换为 (s[0] OR s[2] = 0 OR 1 = 1)，并将 s[2] 替换为 (s[0] XOR s[2] = 0 XOR 1 = 1)，最终得到 s = "1110" 。
     *
     * 如果可以使 s 等于 target ，返回 true ，否则，返回 false 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/apply-bitwise-operations-to-make-strings-equal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * si, sj 四种情况
     * 1） 00 -> 00
     * 2） 01 -> 11
     * 3） 10 -> 11
     * 4） 11 -> 10
     * 1）没有变化不考虑
     * 2）和 3）类似，有一位从 0->1
     * 4）有一位从 1->0
     * 两者都需要有一位 1 来辅助进行翻转
     * 说明两个字符串如果存在有一个相同位置的 1，都可以进行任意翻转
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     * <p>
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 65.13%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 90.92%
     * 的用户
     * 通过测试用例：
     * 71 / 71
     */
    public boolean makeStringsEqual(String s, String target) {
        boolean hasOne = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                hasOne = true;
                break;
            }
        }
        if (!hasOne) {
            return s.equals(target);
        }

        for (int i = 0; i < s.length(); i++) {
            if (target.charAt(i) == '1') {
                return true;
            }
        }
        return false;
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k 。
     * <p>
     * 将数组拆分成一些非空子数组。拆分的 代价 是每个子数组中的 重要性 之和。
     * <p>
     * 令 trimmed(subarray) 作为子数组的一个特征，其中所有仅出现一次的数字将会被移除。
     * <p>
     * 例如，trimmed([3,1,2,4,3,4]) = [3,4,3,4] 。
     * 子数组的 重要性 定义为 k + trimmed(subarray).length 。
     * <p>
     * 例如，如果一个子数组是 [1,2,3,3,3,4,4] ，trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4] 。这个子数组的重要性就是 k + 5 。
     * 找出并返回拆分 nums 的所有可行方案中的最小代价。
     * <p>
     * 子数组 是数组的一个连续 非空 元素序列。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-cost-to-split-an-array
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
     * 339 ms
     * , 在所有 Java 提交中击败了
     * 23.92%
     * 的用户
     * 内存消耗：
     * 48.8 MB
     * , 在所有 Java 提交中击败了
     * 22.88%
     * 的用户
     * 通过测试用例：
     * 59 / 59
     */
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        ;
        int[] dp = new int[n];
        dfs(0, nums, dp, k);
        return dp[0];
    }

    private int dfs(int i, int[] nums, int[] dp, int k) {
        if (i == nums.length) {
            return 0;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        Set<Integer> uniq = new HashSet<>();
        Set<Integer> dup = new HashSet<>();
        int ans = Integer.MAX_VALUE;
        for (int x = i; x < nums.length; x++) {
            if (!dup.contains(nums[x])) {
                if (uniq.contains(nums[x])) {
                    uniq.remove(nums[x]);
                    dup.add(nums[x]);
                } else {
                    uniq.add(nums[x]);
                }
            }
            int cur = x - i + 1 - uniq.size() + k;
            int next = dfs(x + 1, nums, dp, k);
            ans = Math.min(ans, cur + next);
        }
        dp[i] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 1};
        int k = 5;
        System.out.println(new LC0122().minCost(nums, k));
    }
}
