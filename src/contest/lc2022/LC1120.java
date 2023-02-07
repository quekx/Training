package contest.lc2022;

import com.qkx.example.model.TreeNode;

import java.util.*;

public class LC1120 {
    /**
     * 17:30 ~ 18:30
     * 给你一个 二叉搜索树 的根节点 root ，和一个由正整数组成、长度为 n 的数组 queries 。
     * <p>
     * 请你找出一个长度为 n 的 二维 答案数组 answer ，其中 answer[i] = [mini, maxi] ：
     * <p>
     * mini 是树中小于等于 queries[i] 的 最大值 。如果不存在这样的值，则使用 -1 代替。
     * maxi 是树中大于等于 queries[i] 的 最小值 。如果不存在这样的值，则使用 -1 代替。
     * 返回数组 answer 。
     */
    public List<List<Integer>> closestNodes2(TreeNode root, List<Integer> queries) {
        int[] min = new int[queries.size()];
        int[] max = new int[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            traverse(root, queries.get(i), min, max, i, -1, -1);
        }
        List<List<Integer>> ans = new ArrayList<>(queries.size());
        for (int i = 0; i < queries.size(); i++) {
            ans.add(Arrays.asList(min[i], max[i]));
        }
        return ans;
    }

    /**
     * 1. 如果当前 val == target ，直接返回
     * 2. 如果当前 左子树 < val < target，左子树可以忽略，向右遍历
     * 2.1. 小于 target 的最大值一定为 val 或者在右子树
     * 2.2. 大于 target 的最小值一定在右子树，或者不存在
     * 3. 如果当前 右子树 > val > target，右子树可以忽略，向左遍历
     * 3.1. 大于 target 的最小值一定为 val 或者在左子树
     * 3.2. 小于 target 的最大值一定在左子树，或者不存在
     */
    private void traverse(TreeNode node, int query, int[] min, int[] max, int i, int preMin, int preMax) {
        if (node.val == query) {
            min[i] = node.val;
            max[i] = node.val;
            return;
        }

        if (node.val < query) {
            // 目前小值的最大值为 val
            // 大值的最小值无影响
            if (node.right != null) {
                traverse(node.right, query, min, max, i, node.val, preMax);
            } else {
                min[i] = node.val;
                max[i] = preMax;
            }
        } else {
            if (node.left != null) {
                traverse(node.left, query, min, max, i, preMin, node.val);
            } else {
                min[i] = preMin;
                max[i] = node.val;
            }
        }
    }


    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 59 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 88.7 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 37 / 37
     */
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> list = new LinkedList<>();
        traverse(root, list);

        int[] nums = new int[list.size()];
        int idx = 0;
        for (Integer ele : list) {
            nums[idx++] = ele;
        }

        List<List<Integer>> ans = new ArrayList<>(queries.size());
        for (int i = 0; i < queries.size(); i++) {
            int query = queries.get(i);
            ans.add(Arrays.asList(getMin(nums, query), getMax(nums, query)));
        }
        return ans;
    }

    private int getMin(int[] nums, int query) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = r - ((r - l) >> 1);
            if (nums[mid] == query) {
                return query;
            } else if (nums[mid] < query) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        if (nums[l] == query) {
            return query;
        }
        return nums[l] < query ? nums[l] : -1;
    }

    private int getMax(int[] nums, int query) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == query) {
                return query;
            } else if (nums[mid] < query) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if (nums[l] == query) {
            return query;
        }
        return nums[l] > query ? nums[l] : -1;
    }

    private void traverse(TreeNode node, List<Integer> list) {
        if (node.left != null) {
            traverse(node.left, list);
        }
        list.add(node.val);
        if (node.right != null) {
            traverse(node.right, list);
        }
    }

    /**
     * 给你一棵 n 个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从 0 到 n - 1 ，且恰好有 n - 1 条路。0 是首都。给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
     * <p>
     * 每个城市里有一个代表，他们都要去首都参加一个会议。
     * <p>
     * 每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目。
     * <p>
     * 城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。
     * <p>
     * 请你返回到达首都最少需要多少升汽油。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital
     * <p>
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 69 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 99.5 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 131 / 131
     */
    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        Map<Integer, List<Integer>> roadMap = new HashMap<>(n);
        for (int[] road : roads) {
            List<Integer> list1 = roadMap.computeIfAbsent(road[0], k -> new LinkedList<>());
            list1.add(road[1]);
            List<Integer> list2 = roadMap.computeIfAbsent(road[1], k -> new LinkedList<>());
            list2.add(road[0]);
        }
        boolean[] isTraverse = new boolean[n];
        traverse(0, roadMap, isTraverse, seats);
        return ans;
    }

    // 计算当前的花费，和总人数
    long ans = 0L;

    private int traverse(int cur, Map<Integer, List<Integer>> roadMap, boolean[] isTraverse, int seats) {
        if (isTraverse[cur]) {
            return 0;
        }
        isTraverse[cur] = true;

        List<Integer> nodes = roadMap.get(cur);
        int totalNum = 1;
        for (int node : nodes) {
            int num = traverse(node, roadMap, isTraverse, seats);
            totalNum += num;
            ans += num / seats;
            ans += num % seats != 0 ? 1 : 0;
        }
        return totalNum;
    }

    /**
     * 给你一个字符串 s ，每个字符是数字 '1' 到 '9' ，再给你两个整数 k 和 minLength 。
     *
     * 如果对 s 的分割满足以下条件，那么我们认为它是一个 完美 分割：
     *
     * s 被分成 k 段互不相交的子字符串。
     * 每个子字符串长度都 至少 为 minLength 。
     * 每个子字符串的第一个字符都是一个 质数 数字，最后一个字符都是一个 非质数 数字。质数数字为 '2' ，'3' ，'5' 和 '7' ，剩下的都是非质数数字。
     * 请你返回 s 的 完美 分割数目。由于答案可能很大，请返回答案对 109 + 7 取余 后的结果。
     *
     * 一个 子字符串 是字符串中一段连续字符串序列。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/number-of-beautiful-partitions
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * dp[k][i] 代表 s[0][i - 1] 切分数目
     * dp[0][0]代表空字符
     * <p>
     * 73 / 73 个通过测试用例
     * 状态：超出时间限制
     */
    public int beautifulPartitions2(String s, int k, int minLength) {
        int n = s.length();
        int[][] dp = new int[k + 1][n + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= k; j++) {
            for (int i = j * minLength; i <= n; i++) {
                // dp[j][i] 代表 s[0][i - 1], i 为长度
                if (isPrime(s.charAt(i - 1)) || j * minLength > i) {
                    dp[j][i] = 0;
                } else {
                    long total = 0;
                    for (int l = i - minLength; l >= 0 && (j - 1) * minLength <= l; l--) {
                        if (isPrime(s.charAt(l))) {
                            if (l == 0 || !isPrime(s.charAt(l - 1))) {
                                total += dp[j - 1][l];
                            }
                        }
                    }
                    dp[j][i] = (int) (total % (1000_000_000 + 7));
                }
            }
        }
        return dp[k][n];
    }

    private boolean isPrime(char c) {
        return c == '2' || c == '3' || c == '5' || c == '7';
    }

    /**
     * 记录之前的分割点
     * 71 / 73 个通过测试用例
     * 状态：超出时间限制
     * 提交时间：几秒前
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 449 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 42.6 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 73 / 73
     * 炫耀一下:
     */
    public int beautifulPartitions(String s, int k, int minLength) {
        int n = s.length();
        if (!isPrime(s.charAt(0)) || isPrime(s.charAt(n - 1))) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        List<Integer> prePoint = new ArrayList<>();
        for (int i = minLength; i <= n - minLength; i++) {
            if (isPrime(s.charAt(i)) && !isPrime(s.charAt(i - 1))) {
                prePoint.add(i);
            }
        }
        System.out.println(prePoint);
        int size = prePoint.size();
        // dp[j][i]
        int[][] dp = new int[k][size];
        for (int i = 0; i <= size - 1; i++) {
            dp[1][i] = 1;
        }

        for (int j = 2; j <= k - 1; j++) {
            for (int i = j - 1; i <= size - 1; i++) {
                int cur = prePoint.get(i);
                long total = 0;
                for (int x = 0; x < i && (cur - prePoint.get(x)) >= minLength; x++) {
                    total += dp[j - 1][x];
                }
                dp[j][i] = (int) (total % (1000_000_000 + 7));
            }
        }

        long ans = 0L;
        for (int i = 0; i <= size - 1; i++) {
            ans += dp[k - 1][i];
        }
        return (int) (ans % (1000_000_000 + 7));
    }

    public static void main(String[] args) {
        System.out.println(new LC1120().beautifulPartitions("783938233588472343879134266968",
                4,
                6));
    }
}
