package contest.lc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LC0205 {
    /**
     * 给你一个整数数组 gifts ，表示各堆礼物的数量。每一秒，你需要执行以下操作：
     *
     * 选择礼物数量最多的那一堆。
     * 如果不止一堆都符合礼物数量最多，从中选择任一堆即可。
     * 选中的那一堆留下平方根数量的礼物（向下取整），取走其他的礼物。
     * 返回在 k 秒后剩下的礼物数量。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/take-gifts-from-the-richest-pile
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
     * 85.48%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 15.86%
     * 的用户
     * 通过测试用例：
     * 102 / 102
     */
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(gifts.length, (a, b) -> b - a);
        for (int g : gifts) {
            queue.add(g);
        }
        for (int i = 0; i < k && !queue.isEmpty(); i++) {
            int m = queue.poll();
            queue.add((int) Math.sqrt(m));
        }
        long ans = 0;
        for (int num : queue) {
            ans += num;
        }
        return ans;
    }

    private int sqrt(int a) {
        if (a <= 3) {
            return 1;
        }
        int r = 2;
        while (r * r <= a) {
            r++;
        }
        return r - 1;
    }

    /**
     * 给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。
     *
     * 每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾的字符串的数目。
     *
     * 返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。
     *
     * 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-vowel-strings-in-ranges
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
     * 46.97%
     * 的用户
     * 内存消耗：
     * 80.6 MB
     * , 在所有 Java 提交中击败了
     * 90.87%
     * 的用户
     * 通过测试用例：
     * 93 / 93
     */
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        ;
        long[] values = new long[n + 1];
        for (int i = 0; i < n; i++) {
            String w = words[i];
            if (isVowel(w.charAt(0)) && isVowel(w.charAt(w.length() - 1))) {
                add(i, 1, values);
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            ans[i] = (int) range(query[0] - 1, query[1], values);
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private int lowBit(int x) {
        return x & -x;
    }

    private void add(int i, long v, long[] values) {
        i += 1;
        while (i < values.length) {
            values[i] += v;
            i += lowBit(i);
        }
    }

    private long query(int i, long[] values) {
        i += 1;
        long ans = 0;
        while (i > 0) {
            ans += values[i];
            i -= lowBit(i);
        }
        return ans;
    }

    private long range(int a, int b, long[] values) {
        return query(b, values) - query(a, values);
    }

    /**
     * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
     * <p>
     * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
     * <p>
     * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
     * <p>
     * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
     * <p>
     * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
     * <p>
     * 返回小偷的 最小 窃取能力。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/house-robber-iv
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minCapability(int[] nums, int k) {
        int l = 0, r = 0;
        for (int num : nums) {
            r = Math.max(r, num);
        }
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (isValid2(nums, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 29 ms
     * , 在所有 Java 提交中击败了
     * 74.01%
     * 的用户
     * 内存消耗：
     * 51.3 MB
     * , 在所有 Java 提交中击败了
     * 58.62%
     * 的用户
     * 通过测试用例：
     * 64 / 64
     */
    /**
     * 用 dp 计算更快
     */
    private boolean isValid2(int[] nums, int k, int capability) {
        int f2 = 0, f1 = 0;
        for (int num : nums) {
            int cur = f1;
            if (num <= capability) {
                cur = Math.max(cur, f2 + 1);
                if (cur >= k) {
                    return true;
                }
            }
            f2 = f1;
            f1 = cur;
        }
        return f1 >= k;
    }

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 283 ms
     * , 在所有 Java 提交中击败了
     * 5.01%
     * 的用户
     * 内存消耗：
     * 69.2 MB
     * , 在所有 Java 提交中击败了
     * 4.99%
     * 的用户
     * 通过测试用例：
     * 64 / 64
     */
    private boolean isValid(int[] nums, int k, int capability) {
        int[] ans = new int[nums.length];
        boolean[] visit = new boolean[nums.length];
        int num = getNum(nums, capability, 0, ans, visit);
        return num >= k;
    }

    private int getNum(int[] nums, int capability, int i, int[] ans, boolean[] visit) {
        if (i >= nums.length) {
            return 0;
        }
        if (visit[i]) {
            return ans[i];
        }

        visit[i] = true;
        int cur = getNum(nums, capability, i + 1, ans, visit);
        if (capability >= nums[i]) {
            cur = Math.max(cur, 1 + getNum(nums, capability, i + 2, ans, visit));
        }
        ans[i] = cur;
        return cur;
    }

    /**
     * dp[i][x] 代表 num[0] ~ num[i] 偷 x 间屋子的最小窃取能力
     * dp[i][x] = min(dp[i - 1][x], min(dp[i - 2][x - 1], num[i]))
     * <p>
     * 执行结果：
     * 超出时间限制
     */
    public int minCapability3(int[] nums, int k) {
        int n = nums.length;
        ;
        int[] dpLast = new int[n];
        dpLast[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dpLast[i] = Math.min(dpLast[i - 1], nums[i]);
        }
        int[] dp = new int[n];
        for (int x = 2; x <= k; x++) {
            dp[x * 2 - 2] = Math.max(nums[x * 2 - 2], dpLast[x * 2 - 4]);
            for (int i = x * 2 - 1; i < n; i++) {
                int cur = Math.max(nums[i], dpLast[i - 2]);
                dp[i] = Math.min(dp[i - 1], cur);
            }
            int[] t = dp;
            dp = dpLast;
            dpLast = t;
        }
        return dpLast[n - 1];
    }

    /**
     * 执行结果：
     * 超出内存限制
     */
    public int minCapability2(int[] nums, int k) {
        int n = nums.length;
        ;
        int[][] dp = new int[k + 1][n];
        dp[1][0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[1][i] = Math.min(dp[1][i - 1], nums[i]);
        }
        for (int x = 2; x <= k; x++) {
            for (int i = x * 2 - 2; i < n; i++) {
                int cur = Math.max(nums[i], dp[x - 1][i - 2]);
                dp[x][i] = Math.min(dp[x][i - 1] != 0 ? dp[x][i - 1] : Integer.MAX_VALUE, cur);
            }
        }
        return dp[k][n - 1];
    }

    /**
     * 你有两个果篮，每个果篮中有 n 个水果。给你两个下标从 0 开始的整数数组 basket1 和 basket2 ，用以表示两个果篮中每个水果的成本。
     *
     * 你希望两个果篮相等。为此，可以根据需要多次执行下述操作：
     *
     * 选中两个下标 i 和 j ，并交换 basket1 中的第 i 个水果和 basket2 中的第 j 个水果。
     * 交换的成本是 min(basket1i,basket2j) 。
     * 根据果篮中水果的成本进行排序，如果排序后结果完全相同，则认为两个果篮相等。
     *
     * 返回使两个果篮相等的最小交换成本，如果无法使两个果篮相等，则返回 -1 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/rearranging-fruits
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 两个数 a / b 交换
     * 1. 直接交换代价: min(a, b)
     * 2. 通过最小的数 m 过渡代价: min(a, m) + min(b, m) = 2*m
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 17 ms
     * , 在所有 Java 提交中击败了
     * 97.51%
     * 的用户
     * 内存消耗：
     * 59.9 MB
     * , 在所有 Java 提交中击败了
     * 59.96%
     * 的用户
     * 通过测试用例：
     * 40 / 40
     */
    public long minCost(int[] basket1, int[] basket2) {
        int n = basket1.length;
        Arrays.sort(basket1);
        Arrays.sort(basket2);
        ArrayList<Integer> b1 = new ArrayList<>();
        ArrayList<Integer> b2 = new ArrayList<>();
        int i = 0, j = 0;
        int tag1 = 0, tag2 = 0;
        while (i < n || j < n) {
            if ((i < n && j < n) && basket1[i] == basket2[j]) {
                i++;
                j++;
            } else if (((i < n && j < n) && basket1[i] < basket2[j]) || (i < n && j == n)) {
                b1.add(basket1[i]);
                tag1 ^= basket1[i];
                i++;
            } else {
                b2.add(basket2[j]);
                tag2 ^= basket2[j];
                j++;
            }
        }
        if (tag1 != 0 || tag2 != 0) {
            return -1;
        }
        int min = Math.min(basket1[0], basket2[0]);
        int m = b1.size();
        int l1 = 0, r1 = m - 1;
        int l2 = 0, r2 = m - 1;
        long ans = 0;
        while (l1 <= r1 && l2 <= r2) {
            if (b1.get(l1) < b2.get(l2)) {
                if (b1.get(l1) < min * 2) {
                    ans += b1.get(l1);
                } else {
                    ans += min * 2L;
                }
                l1 += 2;
                r2 -= 2;
            } else {
                if (b2.get(l2) < min * 2) {
                    ans += b2.get(l2);
                } else {
                    ans += min * 2L;
                }
                l2 += 2;
                r1 -= 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        String[] ws = new String[]{"aba","bcb","ece","aa","e"};
//        int[][] queries = {{0,2},{1,4},{1,1}};
//        ArrayUtil.print(new LC0205().vowelStrings(ws, queries));

//        int[] nums = {2, 3, 5, 9};
//        int k = 2;
//        System.out.println(new LC0205().minCapability(nums, k));

        int[] basket1 = {4,4,4,4,3};
        int[] basket2 = {5,5,5,5,3};
        System.out.println(new LC0205().minCost(basket1, basket2));
    }
}
