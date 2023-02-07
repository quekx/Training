package contest.lc2022;

import java.util.PriorityQueue;

public class LC1210 {
    /**
     * 一个由字母和数字组成的字符串的 值 定义如下：
     *
     * 如果字符串 只 包含数字，那么值为该字符串在 10 进制下的所表示的数字。
     * 否则，值为字符串的 长度 。
     * 给你一个字符串数组 strs ，每个字符串都只由字母和数字组成，请你返回 strs 中字符串的 最大值 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-value-of-a-string-in-an-array
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
     * 39 MB
     * , 在所有 Java 提交中击败了
     * 57.96%
     * 的用户
     * 通过测试用例：
     * 63 / 63
     */
    public int maximumValue(String[] strs) {
        int ans = 0;
        for (String str : strs) {
            ans = Math.max(ans, getValue(str));
        }
        return ans;
    }

    private int getValue(String str) {
        int ans = 0;
        int s = 1;
        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                ans += (c - '0') * s;
                s *= 10;
            } else {
                return str.length();
            }
        }
        return ans;
    }

    /**
     * 给你一个 n 个点的无向图，节点从 0 到 n - 1 编号。给你一个长度为 n 下标从 0 开始的整数数组 vals ，其中 vals[i] 表示第 i 个节点的值。
     * <p>
     * 同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条双向边。
     * <p>
     * 星图 是给定图中的一个子图，它包含一个中心节点和 0 个或更多个邻居。换言之，星图是给定图中一个边的子集，且这些边都有一个公共节点。
     * <p>
     * 下图分别展示了有 3 个和 4 个邻居的星图，蓝色节点为中心节点。
     *
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 49 ms
     * , 在所有 Java 提交中击败了
     * 86.62%
     * 的用户
     * 内存消耗：
     * 89.1 MB
     * , 在所有 Java 提交中击败了
     * 47.48%
     * 的用户
     * 通过测试用例：
     * 20 / 20
     */
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        PriorityQueue<Integer>[] g = new PriorityQueue[vals.length];
        for (int i = 0; i < vals.length; i++) {
            g[i] = new PriorityQueue<>((a, b) -> b - a);
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(vals[b]);
            g[b].add(vals[a]);
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < vals.length; i++) {
            int sum = vals[i];
            for (int x = 0; x < k; x++) {
                PriorityQueue<Integer> p = g[i];
                if (!p.isEmpty() && p.peek() > 0) {
                    sum += p.poll();
                } else {
                    break;
                }
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * 给你一个下标从 0 开始的整数数组 stones ，数组中的元素 严格递增 ，表示一条河中石头的位置。
     *
     * 一只青蛙一开始在第一块石头上，它想到达最后一块石头，然后回到第一块石头。同时每块石头 至多 到达 一次。
     *
     * 一次跳跃的 长度 是青蛙跳跃前和跳跃后所在两块石头之间的距离。
     *
     * 更正式的，如果青蛙从 stones[i] 跳到 stones[j] ，跳跃的长度为 |stones[i] - stones[j]| 。
     * 一条路径的 代价 是这条路径里的 最大跳跃长度 。
     *
     * 请你返回这只青蛙的 最小代价 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/frog-jump-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 45 ms
     * , 在所有 Java 提交中击败了
     * 9.42%
     * 的用户
     * 内存消耗：
     * 63 MB
     * , 在所有 Java 提交中击败了
     * 5.08%
     * 的用户
     * 通过测试用例：
     * 34 / 34
     */
    public int maxJump(int[] stones) {
        int l = 1, r = stones[stones.length - 1] - stones[0];
        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (isValidJump(stones, m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private boolean isValidJump(int[] stones, int jump) {
        boolean[] isUsed = new boolean[stones.length];
        if (!isValid(stones, 0, jump, isUsed)) {
            return false;
        }

        int p = 0;
        for (int i = 0; i < stones.length; i++) {
            if (!isUsed[i]) {
                if (stones[i] - stones[p] <= jump) {
                    p = i;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[] stones, int i, int jump, boolean[] isUsed) {
        if (i == stones.length - 1) {
            return true;
        }

        if (stones[i + 1] - stones[i] > jump) {
            return false;
        }

        isUsed[i] = true;
        int next = i + 1;
        while (next <= stones.length - 1 && stones[next] - stones[i] <= jump) {
            next++;
        }
        return isValid(stones, next - 1, jump, isUsed);
    }

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 4 ms
     * , 在所有 Java 提交中击败了
     * 62.07%
     * 的用户
     * 内存消耗：
     * 57.4 MB
     * , 在所有 Java 提交中击败了
     * 80.69%
     * 的用户
     * 通过测试用例：
     * 104 / 104
     */
    public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] count1 = new int[n + 1];
        int[] count2 = new int[n + 1];
        for (int i = 0; i < n; i++) {
            count1[nums1[i]]++;
            count2[nums2[i]]++;
        }
        int maxCount1 = 0, maxCountNum1 = -1;
        int maxCount2 = 0, maxCountNum2 = -1;
        for (int i = 1; i <= n; i++) {
            if (count1[i] > maxCount1) {
                maxCount1 = count1[i];
                maxCountNum1 = i;
            }
            if (count2[i] > maxCount2) {
                maxCount2 = count2[i];
                maxCountNum2 = i;
            }
        }
        if (maxCount1 > n / 2 && maxCount1 + count2[maxCountNum1] > n) {
            return -1L;
        }
        if (maxCount2 > n / 2 && maxCount2 + count1[maxCountNum2] > n) {
            return -1L;
        }

        int totalSame = 0;
        int[] sameCount = new int[n + 1];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i]) {
                totalSame++;
                sameCount[nums1[i]]++;
                ans += i;
            }
        }
        int maxSameCount = 0, maxSameCountNum = -1;
        for (int i = 1; i <= n; i++) {
            if (sameCount[i] > maxSameCount) {
                maxSameCount = sameCount[i];
                maxSameCountNum = i;
            }
        }

        // 修正
        // 1. 奇数个情况下，需要加一个外部的最小的无需交换的位置
        // 2. 最大的需要交换数和其他数匹配完的情况下，依旧多出未交换的部分
        System.out.println(totalSame + " --- " + maxSameCount);
        long a = 0;
        if (maxSameCount * 2 > totalSame) {
            int r = maxSameCount * 2 - totalSame;
            int i = 0;
            for (int k = 0; k < r; k++) {
                while (nums1[i] == nums2[i] || (nums1[i] == maxSameCountNum || nums2[i] == maxSameCountNum)) {
                    i++;
                }
                a += i;
                i++;
            }
        }
        return ans + a;
    }

    public static void main(String[] args) {
//        int[] stones = {0,2,5,6,7};
//        System.out.println(new LC1210().maxJump(stones));
        int[] num1 = {2,2,2,1,3};
        int[] num2 = {1,2,2,3,3};
        System.out.println(new LC1210().minimumTotalCost(num1, num2));
    }
}
