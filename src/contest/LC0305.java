package contest;

import com.qkx.example.model.TreeNode;
import com.qkx.example.utils.ArrayUtil;

import java.util.*;

public class LC0305 {
    /**
     * n 个人站成一排，按从 1 到 n 编号。
     *
     * 最初，排在队首的第一个人拿着一个枕头。每秒钟，拿着枕头的人会将枕头传递给队伍中的下一个人。一旦枕头到达队首或队尾，传递方向就会改变，队伍会继续沿相反方向传递枕头。
     *
     * 例如，当枕头到达第 n 个人时，TA 会将枕头传递给第 n - 1 个人，然后传递给第 n - 2 个人，依此类推。
     * 给你两个正整数 n 和 time ，返回 time 秒后拿着枕头的人的编号。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/pass-the-pillow
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
     * 38.3 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 68 / 68
     */
    public int passThePillow(int n, int time) {
        int r = (n - 1) * 2;
        time = time % r;
        if (time <= n - 1) {
            return 1 + time;
        } else {
            return 1 + (r - time);
        }
    }

    /**
     * 给你一棵二叉树的根节点 root 和一个正整数 k 。
     * <p>
     * 树中的 层和 是指 同一层 上节点值的总和。
     * <p>
     * 返回树中第 k 大的层和（不一定不同）。如果树少于 k 层，则返回 -1 。
     * <p>
     * 注意，如果两个节点与根节点的距离相同，则认为它们在同一层。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/kth-largest-sum-in-a-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 17 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 58.1 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     */
    public long kthLargestLevelSum(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        PriorityQueue<Long> ans = new PriorityQueue<>(k);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            for (int j = 0; j < size; j++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            if (ans.size() < k) {
                ans.add(sum);
            } else if (sum > ans.peek()) {
                ans.poll();
                ans.add(sum);
            }
        }
        return ans.size() < k ? -1 : ans.peek();
    }

    /**
     * 给你一个长度为 n 的整数数组 nums ，下标从 0 开始。
     * <p>
     * 如果在下标 i 处 分割 数组，其中 0 <= i <= n - 2 ，使前 i + 1 个元素的乘积和剩余元素的乘积互质，则认为该分割 有效 。
     * <p>
     * 例如，如果 nums = [2, 3, 3] ，那么在下标 i = 0 处的分割有效，因为 2 和 9 互质，而在下标 i = 1 处的分割无效，因为 6 和 3 不互质。在下标 i = 2 处的分割也无效，因为 i == n - 1 。
     * 返回可以有效分割数组的最小下标 i ，如果不存在有效分割，则返回 -1 。
     * <p>
     * 当且仅当 gcd(val1, val2) == 1 成立时，val1 和 val2 这两个值才是互质的，其中 gcd(val1, val2) 表示 val1 和 val2 的最大公约数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/split-the-array-to-make-coprime-products
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 83 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 39 / 39
     */
    public int findValidSplit(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 对 num[i] 因式分解
            int t = nums[i], x = 2;
            while (x * x <= t) {
                if (t % x == 0) {
                    if (!map.containsKey(x)) {
                        map.put(x, i + 1);
                    } else {
                        left[i] = map.get(x);
                    }
                    while (t % x == 0) {
                        t /= x;
                    }
                }
                x++;
            }
            if (t > 1) {
                if (!map.containsKey(t)) {
                    map.put(t, i + 1);
                } else {
                    left[i] = map.get(t);
                }
            }
        }
        int ans = -1;
        int minLeft = Integer.MAX_VALUE;
        for (int r = n - 1; r >= 1; r--) {
            // left[p] - 1 ~ p 都不能选
            // [4,7,8,15,3,5]
            int q = left[r];
            minLeft = left[r] != 0 ? Math.min(minLeft, left[r] - 1) : minLeft;
            if (minLeft >= r) {
                ans = r - 1;
            }
        }
        return ans;
    }

    private long gcd(long a, long b) {
        while (b > 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    /**
     * 考试中有 n 种类型的题目。给你一个整数 target 和一个下标从 0 开始的二维整数数组 types ，其中 types[i] = [counti, marksi] 表示第 i 种类型的题目有 counti 道，每道题目对应 marksi 分。
     *
     * 返回你在考试中恰好得到 target 分的方法数。由于答案可能很大，结果需要对 109 +7 取余。
     *
     * 注意，同类型题目无法区分。
     *
     * 比如说，如果有 3 道同类型题目，那么解答第 1 和第 2 道题目与解答第 1 和第 3 道题目或者第 2 和第 3 道题目是相同的。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/number-of-ways-to-earn-points
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * dp[i][t] 表示 0 ~ i 种题，获得 t 分的方法数目
     * dp[i][t] = dp[i - 1][t] + dp[i - 1][t - si] + dp[i - 1][t - 2*si] + ...
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 28 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.5 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 60 / 60
     */
    public int waysToReachTarget(int target, int[][] types) {
        int n = types.length, mod = 1000_000_007;
        long[][] dp = new long[n + 1][target + 1];
//        for (int i = 0; i <= n; i++) {
//            dp[i][0] = 1;
//        }
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            int count = types[i][0], score = types[i][1];
            for (int t = 0; t <= target; t++) {
                // dp[i + 1][t]
                for (int k = 0; k <= count && k * score <= t; k++) {
                    dp[i + 1][t] += dp[i][t - k * score];
                }
                dp[i + 1][t] %= mod;
            }
        }
        return (int) dp[n][target];
    }

    public static void main(String[] args) {
        int[] nums = {4,7,8,15,3,5};
//        int[] nums = {557663,280817,472963,156253,273349,884803,756869,763183,557663,964357,821411,887849,891133,453379,464279,574373,852749,15031,156253,360169,526159,410203,6101,954851,860599,802573,971693,279173,134243,187367,896953,665011,277747,439441,225683,555143,496303,290317,652033,713311,230107,770047,308323,319607,772907,627217,119311,922463,119311,641131,922463,404773,728417,948281,612373,857707,990589,12739,9127,857963,53113,956003,363397,768613,47981,466027,981569,41597,87149,55021,600883,111953,119083,471871,125641,922463,562577,269069,806999,981073,857707,831587,149351,996461,432457,10903,921091,119083,72671,843289,567323,317003,802129,612373};
        System.out.println(new LC0305().findValidSplit(nums));

        //6
        //[[6,1],[3,2],[2,3]]
//        int t = 6;
//        int[][] types = {{6, 1}, {3, 2}, {2, 3}};
//        System.out.println(new LC0305().waysToReachTarget(t, types));
    }
}
