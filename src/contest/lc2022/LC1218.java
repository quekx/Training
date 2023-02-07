package contest.lc2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 18:00
public class LC1218 {
    /**
     * 给你一个下标从 0 开始的字符串数组 words 。
     *
     * 如果两个字符串由相同的字符组成，则认为这两个字符串 相似 。
     *
     * 例如，"abca" 和 "cba" 相似，因为它们都由字符 'a'、'b'、'c' 组成。
     * 然而，"abacba" 和 "bcfd" 不相似，因为它们不是相同字符组成的。
     * 请你找出满足字符串 words[i] 和 words[j] 相似的下标对 (i, j) ，并返回下标对的数目，其中 0 <= i < j <= word.length - 1 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-pairs-of-similar-strings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行用时：
     * 53 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.4 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 224 / 224
     */
    public int similarPairs(String[] words) {
        int ans = 0;
        for (int i = 0; i < words.length; i++) {
            for (int k = i + 1; k < words.length; k++) {
                if (isSimilar(words[i], words[k])) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean isSimilar(String a, String b) {
        int tag1 = 0;
        for (char k : a.toCharArray()) {
            tag1 |= 1 << (k - 'a');
        }
        int tag2 = 0;
        for (char k : b.toCharArray()) {
            tag2 |= 1 << (k - 'a');
        }
        return tag1 == tag2;
    }

    /**
     * 给你一个正整数 n 。
     *
     * 请你将 n 的值替换为 n 的 质因数 之和，重复这一过程。
     *
     * 注意，如果 n 能够被某个质因数多次整除，则在求和时，应当包含这个质因数同样次数。
     * 返回 n 可以取到的最小值。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/smallest-value-after-replacing-with-sum-of-prime-factors
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
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
     * 209 / 209
     */
    public int smallestValue(int n) {
        int sum = smallerSum(n);
        while (sum < n) {
            n = sum;
            sum = smallerSum(n);
        }
        return n;
    }

    private int smallerSum(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return i + smallerSum(n / i);
            }
        }
        return n;
    }

    /**
     * 给你一个有 n 个节点的 无向 图，节点编号为 1 到 n 。再给你整数 n 和一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条边。图不一定连通。
     *
     * 你可以给图中添加 至多 两条额外的边（也可以一条边都不添加），使得图中没有重边也没有自环。
     *
     * 如果添加额外的边后，可以使得图中所有点的度数都是偶数，返回 true ，否则返回 false 。
     *
     * 点的度数是连接一个点的边的数目。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/add-edges-to-make-degrees-of-all-nodes-even
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 30 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 85.4 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 47 / 47
     */
    public boolean isPossible(int n, List<List<Integer>> edges) {
        ArrayList<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        int[] degrees = new int[n + 1];
        for (List<Integer> e : edges) {
            int x = e.get(0), y = e.get(1);
            degrees[x]++;
            degrees[y]++;
            g[x].add(y);
            g[y].add(x);
        }
        ArrayList<Integer> oddNode = new ArrayList<>(4);
        for (int i = 1; i <= n; i++) {
            if (degrees[i] % 2 != 0) {
                oddNode.add(i);
            }
        }
        if (oddNode.size() == 0) {
            return true;
        }
        if (oddNode.size() == 2) {
            int a = oddNode.get(0);
            int b = oddNode.get(1);
            if (!isAdjust(a, b, g)) {
                return true;
            }
            for (int i = 1; i <= n; i++) {
                if (i == a || i == b) {
                    continue;
                }
                if (!isAdjust(i, a, g) && !isAdjust(i, b, g)) {
                    return true;
                }
            }
            return false;
        }
        if (oddNode.size() == 4) {
            return (!isAdjust(oddNode.get(0), oddNode.get(1), g) && !isAdjust(oddNode.get(2), oddNode.get(3), g))
                    || (!isAdjust(oddNode.get(0), oddNode.get(2), g) && !isAdjust(oddNode.get(1), oddNode.get(3), g))
                    || (!isAdjust(oddNode.get(0), oddNode.get(3), g) && !isAdjust(oddNode.get(1), oddNode.get(2), g));
        }
        return false;
    }

    /**
     * 给你一个整数 n ，表示你有一棵含有 2n - 1 个节点的 完全二叉树 。根节点的编号是 1 ，树中编号在[1, 2n - 1 - 1] 之间，编号为 val 的节点都有两个子节点，满足：
     *
     * 左子节点的编号为 2 * val
     * 右子节点的编号为 2 * val + 1
     * 给你一个长度为 m 的查询数组 queries ，它是一个二维整数数组，其中 queries[i] = [ai, bi] 。对于每个查询，求出以下问题的解：
     *
     * 在节点编号为 ai 和 bi 之间添加一条边。
     * 求出图中环的长度。
     * 删除节点编号为 ai 和 bi 之间新添加的边。
     * 注意：
     *
     * 环 是开始和结束于同一节点的一条路径，路径中每条边都只会被访问一次。
     * 环的长度是环中边的数目。
     * 在树中添加额外的边后，两个点之间可能会有多条边。
     * 请你返回一个长度为 m 的数组 answer ，其中 answer[i] 是第 i 个查询的结果。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/cycle-length-queries-in-a-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 31 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 78.6 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 27 / 27
     */
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            ans[i] = getLength(a, b) + 1;
        }
        return ans;
    }

    private int getLength(int a, int b) {
        int count = 0;
        while (b > a) {
            b /= 2;
            count++;
        }
        return a == b ? count : count + getLength(b, a);
    }

    private boolean isAdjust(int a, int b, ArrayList<Integer>[] g) {
        return g[a].contains(b);
    }

    public static void main(String[] args) {
//        System.out.println(new LC1218().smallestValue(16));
        int n = 21;
        int[][] nums = {{2,19},{16,17},{8,14},{2,16},{12,20},{12,14},{16,18},{15,16},{10,21},{3,5},{13,18},{17,20},{14,17},{9,12},{5,15},{5,6},{3,7},{2,21},{10,13},{8,16},{7,18},{4,6},{9,1},{13,21},{18,20},{7,14},{4,19},{5,8},{3,11},{11,1},{7,12},{4,7},{3,16},{13,17},{17,19},{9,13},{7,19},{10,16},{4,13},{4,5},{2,15},{12,19},{11,16},{2,9},{11,17},{17,1},{16,21},{4,10},{10,14},{14,16},{4,1},{13,20},{5,20},{4,14},{4,21},{10,20},{2,14},{8,15},{4,8},{6,19},{15,1},{19,1},{8,19},{15,21},{3,12},{11,18},{9,17},{18,19},{7,21},{3,21},{16,19},{11,15},{5,1},{8,17},{3,15},{8,1},{10,19},{3,8},{6,16},{2,8},{5,18},{11,13},{11,20},{14,21},{6,20},{4,20},{12,13},{5,12},{10,11},{9,15},{3,19},{9,20},{14,18},{21,1},{13,19},{8,21},{2,13},{3,10},{9,18},{19,21},{6,7},{3,18},{2,18},{6,14},{3,17},{5,21},{14,20},{8,9},{16,1},{3,4},{13,1},{5,9},{4,15},{17,21},{20,21},{2,17},{13,14},{11,14},{9,16},{10,18},{6,15},{6,12},{3,13},{5,11},{6,1},{12,17},{8,10},{5,10},{8,18},{4,12},{10,1},{6,13},{4,18},{7,20},{7,16},{2,6},{12,21},{4,17},{15,18},{13,16},{15,20},{7,10},{6,10},{2,20},{7,15},{18,1},{12,1},{3,20},{7,1},{14,15},{4,9},{11,19},{7,9},{5,17},{18,21},{6,21},{8,11},{6,17},{3,14},{7,11},{5,7},{7,13},{6,8},{6,9},{10,12},{5,16},{2,4},{17,18},{9,11},{12,16},{3,6},{12,18},{3,9},{11,12},{14,19},{10,15},{5,13},{8,13},{15,17},{2,10},{11,21},{20,1},{6,18},{2,12},{19,20},{6,11},{8,12},{2,3},{12,15},{2,11},{9,10},{7,17},{9,19},{13,15},{7,8},{4,11},{2,5},{5,19},{16,20},{15,19},{9,14},{14,1},{10,17},{9,21},{2,7},{8,20},{5,14},{4,16}};
        List<List<Integer>> edges = new ArrayList<>(nums.length);
        for (int[] num : nums) {
            edges.add(Arrays.asList(num[0], num[1]));
        }
        System.out.println(new LC1218().isPossible(n, edges));
    }
}
