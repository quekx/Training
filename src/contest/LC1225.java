package contest;

import com.qkx.example.utils.ArrayUtil;

import java.util.Arrays;

/**
 * 15:00
 */
public class LC1225 {
    /**
     * 给你一个下标从 0 开始的 环形 字符串数组 words 和一个字符串 target 。环形数组 意味着数组首尾相连。
     *
     * 形式上， words[i] 的下一个元素是 words[(i + 1) % n] ，而 words[i] 的前一个元素是 words[(i - 1 + n) % n] ，其中 n 是 words 的长度。
     * 从 startIndex 开始，你一次可以用 1 步移动到下一个或者前一个单词。
     *
     * 返回到达目标字符串 target 所需的最短距离。如果 words 中不存在字符串 target ，返回 -1 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/shortest-distance-to-target-string-in-a-circular-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.1 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 164 / 164
     */
    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (target.equals(words[i])) {
                ans = Math.min(ans, (i - startIndex + n) % n);
                ans = Math.min(ans, (startIndex - i + n) % n);
            }
        }
        return ans != Integer.MAX_VALUE ? ans : -1;
    }

    /**
     * 给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。
     * <p>
     * 你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.3 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 138 / 138
     */
    public int takeCharacters(String s, int k) {
        if (s.length() < k * 3) {
            return -1;
        }
        int n = s.length();
        int[] count = new int[3];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        if (!isValid(count, k)) {
            return -1;
        }
        Arrays.fill(count, 0);
        int i = n, j = n - 1;
        do {
            i--;
            count[s.charAt(i) - 'a']++;
        } while (!isValid(count, k));

        // i ~ j
        int ans = j - i + 1;
        for (int x = i + 1; x <= n; x++) {
            count[s.charAt(x - 1) - 'a']--;
            while (!isValid(count, k)) {
                j++;
                count[s.charAt(j % n) - 'a']++;
            }
            // x ~ j
            ans = Math.min(ans, j - x + 1);
        }
        return ans;
    }

    private boolean isValid(int[] count, int k) {
        return count[0] >= k && count[1] >= k && count[2] >= k;
    }

    /**
     * 给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。
     *
     * 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。
     *
     * 返回礼盒的 最大 甜蜜度。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-tastiness-of-candy-basket
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 16:30 ~ 16:43
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 38 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 50.6 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 40 / 40
     */
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int m = k - 1;
        int n = price.length;
        int l = 0, r = (price[n - 1] - price[0]) / m;
        while (l < r) {
            int mid = r - ((r - l) >> 1);
            if (isValidDiff(mid, price, m)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    /**
     * 能否用大于等于 Diff 的差值，把数组分割成至少 m 段
     * 这样就能找到 m + 1 个数， 差值至少为 Diff
     */
    private boolean isValidDiff(int diff, int[] price, int m) {
        int pre = price[0];
        for (int i = 0; i < price.length && m > 0; i++) {
            if (price[i] - pre >= diff) {
                m--;
                pre = price[i];
            }
        }
        return m == 0;
    }

    /**
     * 给你一个正整数数组 nums 和一个整数 k 。
     *
     * 分区 的定义是：将数组划分成两个有序的 组 ，并满足每个元素 恰好 存在于 某一个 组中。如果分区中每个组的元素和都大于等于 k ，则认为分区是一个好分区。
     *
     * 返回 不同 的好分区的数目。由于答案可能很大，请返回对 109 + 7 取余 后的结果。
     *
     * 如果在两个分区中，存在某个元素 nums[i] 被分在不同的组中，则认为这两个分区不同。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/number-of-great-partitions
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * dp[i][x] 代表 nums[0] ~ nums[i] 所有排列集合中，和 = x 的个数
     * 压缩 dp[x] = dp[x] + dp[x - num[i]]
     * <p>
     * total[i] 代表 nums[0] ~ nums[i] 所有排列集合总数
     * total[x] = total[x - 1] * 2
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 23 ms
     * , 在所有 Java 提交中击败了
     * 18.86%
     * 的用户
     * 内存消耗：
     * 57.4 MB
     * , 在所有 Java 提交中击败了
     * 5.26%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     */
    public int countPartitions(int[] nums, int k) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum <= k * 2L) {
            return 0;
        }
        int mod = 1000_000_007, n = nums.length;
        long[][] dp = new long[n + 1][k];
        long[] total = new long[n + 1];
        dp[0][0] = 1L;
        total[0] = 1L;
        for (int i = 1; i <= n; i++) {
            // nums[i]
            total[i] = total[i - 1] * 2;
            total[i] = total[i] < mod ? total[i] : total[i] % mod;
            for (int x = 0; x < k; x++) {
                dp[i][x] = dp[i - 1][x];
                if (nums[i - 1] <= x) {
                    dp[i][x] += dp[i - 1][x - nums[i - 1]];
                }
                dp[i][x] %= mod;
                dp[i][x] = dp[i][x] < mod ? dp[i][x] : dp[i][x] % mod;
            }
        }
        long small = 0;
        for (int x = 0; x < k; x++) {
            small += dp[n][x];
            small %= mod;
        }
        return (int) ((total[n] + mod - small * 2) % mod);
    }

    public static void main(String[] args) {
//        String s = "ccbabcc";
//        int k = 1;
//        System.out.println(new LC1225().takeCharacters(s, k));

        int[] nums = {1,2,3,4};
        int k = 4;
        System.out.println(new LC1225().countPartitions(nums, k));
    }
}
