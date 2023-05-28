package contest.lc2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC0115 {
    /**
     * 给你一个正整数数组 nums 。
     *
     * 元素和 是 nums 中的所有元素相加求和。
     * 数字和 是 nums 中每一个元素的每一数位（重复数位需多次求和）相加求和。
     * 返回 元素和 与 数字和 的绝对差。
     *
     * 注意：两个整数 x 和 y 的绝对差定义为 |x - y| 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/difference-between-element-sum-and-digit-sum-of-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 2 ms
     * , 在所有 Java 提交中击败了
     * 95.50%
     * 的用户
     * 内存消耗：
     * 41.8 MB
     * , 在所有 Java 提交中击败了
     * 6.42%
     * 的用户
     * 通过测试用例：
     * 142 / 142
     * @param nums
     * @return
     */
    public int differenceOfSum(int[] nums) {
        int sum = 0;
        int digitSum = 0;
        for (int num : nums) {
            sum += num;
            while (num > 0) {
                digitSum += num % 10;
                num /= 10;
            }
        }
        return Math.abs(sum - digitSum);
    }

    /**
     * 给你一个正整数 n ，表示最初有一个 n x n 、下标从 0 开始的整数矩阵 mat ，矩阵中填满了 0 。
     *
     * 另给你一个二维整数数组 query 。针对每个查询 query[i] = [row1i, col1i, row2i, col2i] ，请你执行下述操作：
     *
     * 找出 左上角 为 (row1i, col1i) 且 右下角 为 (row2i, col2i) 的子矩阵，将子矩阵中的 每个元素 加 1 。也就是给所有满足 row1i <= x <= row2i 和 col1i <= y <= col2i 的 mat[x][y] 加 1 。
     * 返回执行完所有操作后得到的矩阵 mat 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/increment-submatrices-by-one
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 20 ms
     * , 在所有 Java 提交中击败了
     * 73.54%
     * 的用户
     * 内存消耗：
     * 49.5 MB
     * , 在所有 Java 提交中击败了
     * 88.93%
     * 的用户
     * 通过测试用例：
     * 31 / 31
     */
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] ans = new int[n][n];
        for (int[] query : queries) {
            int r1 = query[0], c1 = query[1];
            int r2 = query[2], c2 = query[3];
            for (int r = r1; r <= r2; r++) {
                ans[r][c1]++;
                if (c2 + 1 < n) {
                    ans[r][c2 + 1]--;
                }
            }
        }
        for (int r = 0; r < n; r++) {
            for (int c = 1; c < n; c++) {
                ans[r][c] += ans[r][c - 1];
            }
        }
        return ans;
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回 nums 中 好 子数组的数目。
     *
     * 一个子数组 arr 如果有 至少 k 对下标 (i, j) 满足 i < j 且 arr[i] == arr[j] ，那么称它是一个 好 子数组。
     *
     * 子数组 是原数组中一段连续 非空 的元素序列。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-the-number-of-good-subarrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 63 ms
     * , 在所有 Java 提交中击败了
     * 23.98%
     * 的用户
     * 内存消耗：
     * 51.6 MB
     * , 在所有 Java 提交中击败了
     * 92.47%
     * 的用户
     * 通过测试用例：
     * 41 / 41
     */
    public long countGood(int[] nums, int k) {
        long ans = 0;
        // [l, r]
        int l = 0;
        Map<Integer, Integer> counter = new HashMap<>();
        long pairs = 0;
        for (int num : nums) {
            Integer x = counter.getOrDefault(num, 0);
            counter.put(num, x + 1);
            pairs += x;
            while (pairs - (counter.getOrDefault(nums[l], 0) - 1) >= k) {
                // l 左移
                Integer y = counter.getOrDefault(nums[l], 0);
                counter.put(nums[l], y - 1);
                pairs -= y - 1;
                l++;
            }
            if (pairs >= k) {
                ans += l + 1;
            }
        }
        return ans;
    }

    /**
     * 给你一个 n 个节点的无向无根图，节点编号为 0 到 n - 1 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。
     *
     * 每个节点都有一个价值。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价值。
     *
     * 一条路径的 价值和 是这条路径上所有节点的价值之和。
     *
     * 你可以选择树中任意一个节点作为根节点 root 。选择 root 为根的 开销 是以 root 为起点的所有路径中，价值和 最大的一条路径与最小的一条路径的差值。
     *
     * 请你返回所有节点作为根节点的选择中，最大 的 开销 为多少。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/difference-between-maximum-and-minimum-price-sum
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
     * 33.06%
     * 的用户
     * 内存消耗：
     * 79.1 MB
     * , 在所有 Java 提交中击败了
     * 99.59%
     * 的用户
     * 通过测试用例：
     * 58 / 58
     */
    public long maxOutput(int n, int[][] edges, int[] price) {
        ArrayList<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        int[] maxPrice = new int[n];
        int[] visit = new int[n];
        calMaxDist(0, g, visit, 1, maxPrice, price);
        int[] ans = new int[n];
        calMaxAns(0, g, visit, 2, maxPrice, 0, price, ans);
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, ans[i]);
        }
        return max;
    }

    private void calMaxAns(int i, ArrayList<Integer>[] g, int[] visit, int tag, int[] maxPrice, int reverseChildPrice, int[] price, int[] ans) {
        if (visit[i] == tag) {
            return;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        if (reverseChildPrice != 0) {
            q.add(reverseChildPrice);
        }
        visit[i] = tag;
        for (int child : g[i]) {
            if (visit[child] == tag) {
                continue;
            }
            q.add(maxPrice[child]);
            if (q.size() > 2) {
                q.poll();
            }
        }
        int first = 0;
        int second = 0;
        if (q.size() == 2) {
            second = q.poll();
            first = q.poll();
        } else if (q.size() == 1) {
            first = q.poll();
        }
        ans[i] = first;

        for (int child : g[i]) {
            calMaxAns(child, g, visit, tag, maxPrice, maxPrice[child] == first ? second + price[i] : first + price[i], price, ans);
        }
    }

    private void calMaxDist(int i, ArrayList<Integer>[] g, int[] visit, int tag, int[] maxPrice, int[] price) {
        if (visit[i] == tag) {
            return;
        }
        visit[i] = tag;
        int maxChildPrice = 0;
        for (int child : g[i]) {
            calMaxDist(child, g, visit, tag, maxPrice, price);
            maxChildPrice = Math.max(maxChildPrice, maxPrice[child]);
        }
        maxPrice[i] = maxChildPrice + price[i];
    }

    public static void main(String[] args) {
//        int[] nums = {2,3,1,3,2,3,3,3,1,1,3,2,2,2};
//        int k = 18;
//        System.out.println(new LC0115().countGood(nums, k));

        int n = 6;
        int[][] edges = {{0,1},{1,2},{1,3},{3,4},{3,5}};
        int[] price = {9,8,7,6,10,5};
        System.out.println(new LC0115().maxOutput(n, edges, price));
    }

}
