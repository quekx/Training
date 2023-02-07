package contest.lc2022;

import com.qkx.example.utils.ArrayUtil;

import java.util.*;

public class LC1211 {
    /**
     * 给你一个 m x n 大小的矩阵 grid ，由若干正整数组成。
     *
     * 执行下述操作，直到 grid 变为空矩阵：
     *
     * 从每一行删除值最大的元素。如果存在多个这样的值，删除其中任何一个。
     * 将删除元素中的最大值与答案相加。
     * 注意 每执行一次操作，矩阵中列的数据就会减 1 。
     *
     * 返回执行上述操作后的答案。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/delete-greatest-value-in-each-row
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 41.9 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 55 / 55
     */
    public int deleteGreatestValue(int[][] grid) {
        for (int[] row : grid) {
            Arrays.sort(row);
        }
        ArrayUtil.print(grid);
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for (int col = n - 1; col >= 0; col--) {
            int max = grid[0][col];
            for (int r = 1; r <= m - 1; r++) {
                max = Math.max(max, grid[r][col]);
            }
            System.out.println(max);
            ans += max;
        }
        return ans;
    }

    /**
     * 给你一个整数数组 nums 。如果 nums 的子序列满足下述条件，则认为该子序列是一个 方波 ：
     * <p>
     * 子序列的长度至少为 2 ，并且
     * 将子序列从小到大排序 之后 ，除第一个元素外，每个元素都是前一个元素的 平方 。
     * 返回 nums 中 最长方波 的长度，如果不存在 方波 则返回 -1 。
     * <p>
     * 子序列 也是一个数组，可以由另一个数组删除一些或不删除元素且不改变剩余元素的顺序得到。
     * <p>
     *  
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/longest-square-streak-in-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
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
     * 55.6 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 87 / 87
     */
    public int longestSquareStreak(int[] nums) {
        int[] s = new int[100002];
        Arrays.sort(nums);
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int cur = nums[i];
            int n = cur * cur;
            s[cur] = n > 0 && n < s.length ? s[n] + 1 : 1;
            ans = Math.max(ans, s[cur]);
        }
        return ans > 1 ? ans : -1;
    }

    static class Allocator {

        class Cpt implements Comparator<int[]> {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        }

        private PriorityQueue<int[]> idleSpaces;
        private PriorityQueue<int[]> busySpaces;

        public Allocator(int n) {
            idleSpaces = new PriorityQueue<>(new Cpt());
            idleSpaces.add(new int[]{0, n});
            busySpaces = new PriorityQueue<>(new Cpt());
        }

        public int allocate(int size, int mID) {
            Iterator<int[]> it = idleSpaces.iterator();
            while (it.hasNext()) {
                int[] s = it.next();
                if (s[1] >= size) {
                    it.remove();
                    busySpaces.add(mergeBusy(new int[]{s[0], size, mID}));
                    if (s[1] > size) {
                        idleSpaces.add(new int[]{s[0] + size, s[1] - size});
                    }
                    return s[0];
                }
            }
            return -1;
        }

        private int[] mergeBusy(int[] s) {
            Iterator<int[]> it = busySpaces.iterator();
            while (it.hasNext()) {
                int[] cur = it.next();
                if (cur[2] == s[2] && cur[0] + cur[1] == s[0]) {
                    it.remove();
                    s = new int[]{cur[0], cur[1] + s[1], cur[2]};
                } else if (cur[2] == s[2] && s[0] + s[1] == cur[0]) {
                    it.remove();
                    s = new int[]{s[0], cur[1] + s[1], cur[2]};
                }
            }
            return s;
        }

        public int free(int mID) {
            List<int[]> freeSpace = new ArrayList<>();
            Iterator<int[]> it = busySpaces.iterator();
            while (it.hasNext()) {
                int[] s = it.next();
                if (s[2] == mID) {
                    it.remove();
                    freeSpace.add(s);
                }
            }

            int size = 0;
            for (int[] s : freeSpace) {
                size += s[1];
                idleSpaces.add(mergeIdle(s));
            }
            return size;
        }

        private int[] mergeIdle(int[] s) {
            Iterator<int[]> freeIt = idleSpaces.iterator();
            while (freeIt.hasNext()) {
                int[] cur = freeIt.next();
                if (cur[0] + cur[1] == s[0]) {
                    freeIt.remove();
                    s = new int[]{cur[0], cur[1] + s[1]};
                } else if (s[0] + s[1] == cur[0]) {
                    freeIt.remove();
                    s = new int[]{s[0], cur[1] + s[1]};
                }
            }
            return s;
        }
    }

    /**
     * Your Allocator object will be instantiated and called as such:
     * Allocator obj = new Allocator(n);
     * int param_1 = obj.allocate(size,mID);
     * int param_2 = obj.free(mID);
     */

    class PointComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    }

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 91 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 59.6 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 通过测试用例：
     * 20 / 20
     */
    public int[] maxPoints(int[][] grid, int[] queries) {
        ArrayList<int[]> arr = new ArrayList<>();
        arr.add(new int[]{0, 0});

        int m = grid.length, n = grid[0].length;
        int score = 0;
        int max = Integer.MIN_VALUE;
        boolean[][] f = new boolean[m][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new PointComparator());
        queue.add(new int[]{grid[0][0], 0, 0});
        f[0][0] = true;
        while (!queue.isEmpty()) {
            int[] min = queue.poll();
            score++;
            max = Math.max(max, min[0]);
            int x = min[1], y = min[2];
            if (x - 1 >= 0 && !f[x - 1][y]) {
                queue.add(new int[]{grid[x - 1][y], x - 1, y});
                f[x - 1][y] = true;
            }
            if (x + 1 <= m - 1 && !f[x + 1][y]) {
                queue.add(new int[]{grid[x + 1][y], x + 1, y});
                f[x + 1][y] = true;
            }
            if (y - 1 >= 0 && !f[x][y - 1]) {
                queue.add(new int[]{grid[x][y - 1], x, y - 1});
                f[x][y - 1] = true;
            }
            if (y + 1 <= n - 1 && !f[x][y + 1]) {
                queue.add(new int[]{grid[x][y + 1], x, y + 1});
                f[x][y + 1] = true;
            }
            //
            if (queue.isEmpty() || queue.peek()[0] > max) {
                arr.add(new int[]{max, score});
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = getSum(queries[i], arr);
        }
        return ans;
    }

    private int getSum(int query, ArrayList<int[]> arr) {
        int l = 0, r = arr.size() - 1;
        while (l < r) {
            int m = r - ((r - l) >> 1);
            int[] mid = arr.get(m);
            if (mid[0] < query) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return arr.get(l)[1];
    }

    public static void main(String[] args) {
//        int[][] grid = {{1, 2, 4}, {3, 3, 1}};
//        System.out.println(new LC1211().deleteGreatestValue(grid));
//
//        System.out.println(Math.sqrt(166831));

        int[][] grid = {{5, 2, 1}, {1, 1, 2}};
        int[] queries = {3};
        System.out.println(Arrays.toString(new LC1211().maxPoints(grid, queries)));
    }
}
