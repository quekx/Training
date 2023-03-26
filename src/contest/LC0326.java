package contest;

import com.qkx.example.utils.ArrayUtil;

import java.util.*;

public class LC0326 {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {
            return k;
        }
        int r = k - numOnes;
        if (numZeros >= r) {
            return numOnes;
        }
        return numOnes - (r - numZeros);
    }

    public boolean primeSubOperation(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            // ni - d == (ni+1 - 1)
            int d = nums[i] - nums[i + 1] + 1;
            if (d <= 0) {
                continue;
            }
            // [d, ni) 找到质数
            int a = Math.max(d, 2);
            while (a < nums[i] && !isPrime(a)) {
                a++;
            }
            if (a >= nums[i]) {
                return false;
            }
            nums[i] -= a;
        }
        return true;
    }

    private boolean isPrime(int a) {
        if (a == 2 || a == 3) {
            return true;
        }
        for (int x = 2; x * x <= a; x++) {
            if (a % x == 0) {
                return false;
            }
        }
        return true;
    }

    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] opts = new long[n];
        for (int i = 1; i < n; i++) {
            opts[0] += nums[i] - nums[0];
        }
        for (int i = 1; i < n; i++) {
            // ni-1 -> ni
            opts[i] = opts[i - 1] - (n - i * 2L) * (nums[i] - nums[i - 1]);
        }
        ArrayUtil.print(nums);
        ArrayUtil.print(opts);
        List<Long> ans = new ArrayList<>(queries.length);
        for (int query : queries) {
            ans.add(query(nums, opts, query));
        }
        return ans;
    }

    private long query(int[] nums, long[] opts, int query) {
        int n = nums.length;
        if (query <= nums[0]) {
            return opts[0] + (long) (nums[0] - query) * n;
        }
        if (query >= nums[n - 1]) {
            return opts[n - 1] + (long) (query - nums[n - 1]) * n;
        }

        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == query) {
                return opts[mid];
            } else if (nums[mid] < query) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return opts[r] - (n - r * 2L - 2) * (query - nums[r]);
    }

    /**
     * 给你一个 n 个节点的无向无根树，节点编号从 0 到 n - 1 。给你整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。再给你一个长度为 n 的数组 coins ，其中 coins[i] 可能为 0 也可能为 1 ，1 表示节点 i 处有一个金币。
     *
     * 一开始，你需要选择树中任意一个节点出发。你可以执行下述操作任意次：
     *
     * 收集距离当前节点距离为 2 以内的所有金币，或者
     * 移动到树中一个相邻节点。
     * 你需要收集树中所有的金币，并且回到出发节点，请你返回最少经过的边数。
     *
     * 如果你多次经过一条边，每一次经过都会给答案加一。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/collect-coins-in-a-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * todo: 拓扑排序学习
     */
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        if (n <= 3) {
            return 0;
        }
        ArrayList<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
            degree[x]++;
            degree[y]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1 && coins[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            degree[x]--;
            for (int y : g[x]) {
                degree[y]--;
                if (degree[y] == 1 && coins[y] == 0) {
                    queue.add(y);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.add(i);
            }
        }
        int[] time = new int[n];
        while (!queue.isEmpty()) {
            int x = queue.poll();
            degree[x]--;
            for (int y : g[x]) {
                degree[y]--;
                if (degree[y] == 1) {
                    time[y] = time[x] + 1;
                    queue.add(y);
                }
            }
        }
        // cnt[i] 表示删 i 次可以删掉的点数，对应可以删除的边数
        // 第 0 次删除可以删掉 cnt[0] 个点，对应 cnt[0] 个边
        int[] cnt = new int[n];
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            cnt[Math.min(time[x], time[y])]++;
        }
        // cnt[i] 表示删 i 次之后剩下的点数
        for (int i = n - 2; i >= 0; i--) {
            cnt[i] += cnt[i + 1];
        }
        return cnt[2] * 2;
    }

    public static void main(String[] args) {
        int[] nums = {3,1,6,8};
        int[] q = {1,5};
        System.out.println(new LC0326().minOperations(nums, q));
    }
}
