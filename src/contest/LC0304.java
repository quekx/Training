package contest;

import com.qkx.example.utils.ArrayUtil;
import com.sun.org.apache.xerces.internal.impl.dv.xs.SchemaDVFactoryImpl;

import java.util.*;

public class LC0304 {
    /**
     * 给你一个正整数 num ，请你将它分割成两个非负整数 num1 和 num2 ，满足：
     * <p>
     * num1 和 num2 直接连起来，得到 num 各数位的一个排列。
     * 换句话说，num1 和 num2 中所有数字出现的次数之和等于 num 中所有数字出现的次数。
     * num1 和 num2 可以包含前导 0 。
     * 请你返回 num1 和 num2 可以得到的和的 最小 值。
     * <p>
     * 注意：
     * <p>
     * num 保证没有前导 0 。
     * num1 和 num2 中数位顺序可以与 num 中数位顺序不同。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/split-with-minimum-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.5 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 138 / 138
     */
    public int splitNum(int num) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        while (num > 0) {
            queue.add(num % 10);
            num /= 10;
        }
        int sign = 1;
        int ans = 0;
        while (!queue.isEmpty()) {
            int a = queue.poll();
            int b = !queue.isEmpty() ? queue.poll() : 0;
            ans += (a + b) * sign;
            sign *= 10;
        }
        return ans;
    }

    /**
     * 有一个无穷大的二维网格图，一开始所有格子都未染色。给你一个正整数 n ，表示你需要执行以下步骤 n 分钟：
     * <p>
     * 第一分钟，将 任一 格子染成蓝色。
     * 之后的每一分钟，将与蓝色格子相邻的 所有 未染色格子染成蓝色。
     * 下图分别是 1、2、3 分钟后的网格图。
     * <p>
     * <p>
     * 请你返回 n 分钟之后 被染色的格子 数目。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-total-number-of-colored-cells
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 34 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 45.1 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 94 / 94
     */
    public long coloredCells(int n) {
        if (n == 1) {
            return 1;
        }
        return coloredCells(n - 1) + (n - 1) * 4L;
    }

    /**
     * 给你一个二维整数数组 ranges ，其中 ranges[i] = [starti, endi] 表示 starti 到 endi 之间（包括二者）的所有整数都包含在第 i 个区间中。
     * <p>
     * 你需要将 ranges 分成 两个 组（可以为空），满足：
     * <p>
     * 每个区间只属于一个组。
     * 两个有 交集 的区间必须在 同一个 组内。
     * 如果两个区间有至少 一个 公共整数，那么这两个区间是 有交集 的。
     * <p>
     * 比方说，区间 [1, 3] 和 [2, 5] 有交集，因为 2 和 3 在两个区间中都被包含。
     * 请你返回将 ranges 划分成两个组的 总方案数 。由于答案可能很大，将它对 109 + 7 取余 后返回。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-ways-to-group-overlapping-ranges
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 14 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 98.5 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     */
    public int countWays(int[][] ranges) {
        if (ranges.length == 0) {
            return 1;
        }
        Arrays.sort(ranges, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        int n = 0;
        int[] pre = ranges[0];
        for (int i = 1; i < ranges.length; i++) {
            int[] cur = ranges[i];
            if (cur[0] <= pre[1]) {
                pre[1] = Math.max(pre[1], cur[1]);
            } else {
                n++;
                pre = cur;
            }
        }
        n++;
        return (int) pow(n);
    }

    private long pow(int n) {
        if (n == 1) {
            return 2;
        }
        int mod = 1000_000_007;
        long x = pow(n / 2);
        return n % 2 == 0 ? (x * x) % mod : (x * x * 2) % mod;
    }

    /**
     * Alice 有一棵 n 个节点的树，节点编号为 0 到 n - 1 。树用一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ai, bi] ，表示树中节点 ai 和 bi 之间有一条边。
     * <p>
     * Alice 想要 Bob 找到这棵树的根。她允许 Bob 对这棵树进行若干次 猜测 。每一次猜测，Bob 做如下事情：
     * <p>
     * 选择两个 不相等 的整数 u 和 v ，且树中必须存在边 [u, v] 。
     * Bob 猜测树中 u 是 v 的 父节点 。
     * Bob 的猜测用二维整数数组 guesses 表示，其中 guesses[j] = [uj, vj] 表示 Bob 猜 uj 是 vj 的父节点。
     * <p>
     * Alice 非常懒，她不想逐个回答 Bob 的猜测，只告诉 Bob 这些猜测里面 至少 有 k 个猜测的结果为 true 。
     * <p>
     * 给你二维整数数组 edges ，Bob 的所有猜测和整数 k ，请你返回可能成为树根的 节点数目 。如果没有这样的树，则返回 0。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-number-of-possible-root-nodes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 78 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 130.7 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 51 / 51
     */
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        int n = edges.length + 1;
        ArrayList<Integer>[] graph = new ArrayList[n];
        Set<Integer>[] guessMap = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            guessMap[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        for (int[] guess : guesses) {
            int p = guess[0], c = guess[1];
            guessMap[p].add(c);
        }
        int[] visit = new int[n];
        int[] counter = new int[n];
        // 深度遍历算出以 0 为根节点，各个节点的猜测正确数
        dfs(0, graph, guessMap, visit, counter, 1);
        int[] result = new int[n];
        // 从 0 节点开始深度遍历，遍历子节点相当于一次旋转操作，把根节点作为新子树的猜测结果带下去合并计算
        visit(0, graph, guessMap, visit, counter, 2, result, 0, -1);
        int ans = 0;
        for (int r : result) {
            if (r >= k) {
                ans++;
            }
        }
        return ans;
    }

    private int dfs(int i, ArrayList<Integer>[] graph, Set<Integer>[] guessMap, int[] visit, int[] counter, int tag) {
        visit[i] = tag;
        int count = 0;
        for (int child : graph[i]) {
            if (visit[child] == tag) {
                continue;
            }
            if (guessMap[i].contains(child)) {
                count++;
            }
            count += dfs(child, graph, guessMap, visit, counter, tag);
        }
        counter[i] = count;
        return count;
    }

    private void visit(int i, ArrayList<Integer>[] graph, Set<Integer>[] guessMap, int[] visit, int[] counter, int tag, int[] ans, int preCount, int parent) {
        if (visit[i] == tag) {
            return;
        }
        visit[i] = tag;
        int count = counter[i];
        if (guessMap[i].contains(parent)) {
            count++;
        }
        ans[i] = count + preCount;
        for (int child : graph[i]) {
            // 旋转
            int childPreCount = ans[i] - counter[child] - (guessMap[i].contains(child) ? 1 : 0);
            visit(child, graph, guessMap, visit, counter, tag, ans, childPreCount, i);
        }
    }

    public static void main(String[] args) {
//        int mod = 1000_000_007;
//        System.out.println(2097152 * 2097152L);

        int[][] e = {{0, 1}, {1, 2}, {1, 3}, {4, 2}};
        int[][] guess = {{1, 3}, {0, 1}, {1, 0}, {2, 4}};
        int k = 3;
        System.out.println(new LC0304().rootCount(e, guess, k));
    }
}
