package contest.lc2023;

import com.qkx.example.utils.ArrayUtil;

import java.util.*;

public class LC0409 {
    public int diagonalPrime(int[][] nums) {
        int m = nums.length, n = nums[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (i < n && isPrime(nums[i][i])) {
                ans = Math.max(ans, nums[i][i]);
            }
            if (n - 1 - i >= 0 && isPrime(nums[i][n - 1 - i])) {
                ans = Math.max(ans, nums[i][n - 1 - i]);
            }
        }
        return ans;
    }

    private boolean isPrime(int val) {
        if (val < 2) {
            return false;
        }
        for (int x = 2; x * x <= val; x++) {
            if (val % x == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     * <p>
     * 执行用时：
     * 78 ms
     * , 在所有 Java 提交中击败了
     * 6.05%
     * 的用户
     * 内存消耗：
     * 66.1 MB
     * , 在所有 Java 提交中击败了
     * 72.03%
     * 的用户
     * 通过测试用例：
     * 1068 / 1068
     */
    public long[] distance(int[] nums) {
        long[] ans = new long[nums.length];
        Map<Integer, Long> map = new HashMap<>();
        Map<Integer, Long> counter = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            map.put(cur, map.getOrDefault(cur, 0L) + i);
            counter.put(cur, counter.getOrDefault(cur, 0L) + 1);
            ans[i] += counter.get(cur) * i - map.get(cur);
        }
        map.clear();
        counter.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            int cur = nums[i];
            map.put(cur, map.getOrDefault(cur, 0L) + i);
            counter.put(cur, counter.getOrDefault(cur, 0L) + 1);
            ans[i] += map.get(cur) - counter.get(cur) * i;
        }
        return ans;
    }

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     * <p>
     * 执行用时：
     * 18 ms
     * , 在所有 Java 提交中击败了
     * 67.16%
     * 的用户
     * 内存消耗：
     * 56.2 MB
     * , 在所有 Java 提交中击败了
     * 22.06%
     * 的用户
     * 通过测试用例：
     * 1582 / 1582
     */
    public int minimizeMax(int[] nums, int p) {
        if (p == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int l = 0, r = nums[nums.length - 1] - nums[0];
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(nums, p, mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] nums, int p, int diff) {
        int count = 0;
        int i = 0;
        while (i + 1 < nums.length) {
            if (nums[i + 1] - nums[i] <= diff) {
                count++;
                i += 2;
                if (count >= p) {
                    return true;
                }
            } else {
                i++;
            }
        }
        return false;
    }

    private int find(int x, int[] p) {
        if (p[x] != x) {
            p[x] = find(p[x], p);
        }
        return p[x];
    }

    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 查看示例代码
     * 添加备注
     *
     * 执行用时：
     * 81 ms
     * , 在所有 Java 提交中击败了
     * 64.85%
     * 的用户
     * 内存消耗：
     * 79.7 MB
     * , 在所有 Java 提交中击败了
     * 29.87%
     * 的用户
     * 通过测试用例：
     * 1069 / 1069
     *
     * 并查集记录跳过已选集合
     */
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) {
            return 1;
        }
        int[][] px = new int[m][n + 1];
        int[][] py = new int[n][m + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= n; j++) {
                px[i][j] = j;
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= m; i++) {
                py[j][i] = i;
            }
        }
        Deque<int[]> deque = new ArrayDeque<>();
        Deque<int[]> next = new ArrayDeque<>();
        deque.add(new int[]{0, 0});
        int step = 1;
        while (!deque.isEmpty()) {
            while (!deque.isEmpty()) {
                int[] cur = deque.poll();
                int i = cur[0], j = cur[1], val = grid[i][j];
                if (j + 1 < n) {
                    int[] pi = px[i];
                    // right
                    int k = find(j, pi);
                    while (k < n && k <= j + val) {
                        if (i == m - 1 && k == n - 1) {
                            return step + 1;
                        }
                        next.add(new int[]{i, k});
                        // merge k and k + 1
                        pi[k] = find(k + 1, pi);
                        k = pi[k];
                    }
                }
                if (i + 1 < m) {
                    int[] pj = py[j];
                    // down
                    int k = find(i, pj);
                    while (k < m && k <= i + val) {
                        if (k == m - 1 && j == n - 1) {
                            return step + 1;
                        }
                        next.add(new int[]{k, j});
                        pj[k] = find(k + 1, pj);
                        k = pj[k];
                    }
                }

            }
            step++;
            Deque<int[]> tmp = next;
            next = deque;
            deque = tmp;
        }
        return -1;
    }

    public int minimumVisitedCells2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) {
            return 1;
        }
        Deque<int[]> deque = new ArrayDeque<>();
        Deque<int[]> next = new ArrayDeque<>();
        boolean[][] xVisit = new boolean[m][n];
        boolean[][] yVisit = new boolean[m][n];
        deque.add(new int[]{0, 0});
        int step = 1;
        while (!deque.isEmpty()) {
            while (!deque.isEmpty()) {
                int[] cur = deque.poll();
                int i = cur[0], j = cur[1], val = grid[i][j];
                for (int dy = Math.min(n - 1 - j, val); dy >= 1 && !yVisit[i][j + dy]; dy--) {
                    if (i == m - 1 && j + dy == n - 1) {
                        return step + 1;
                    }
                    yVisit[i][j + dy] = true;
                    next.add(new int[]{i, j + dy});
                }
                for (int dx = Math.min(m - 1 - i, val); dx >= 1 && !xVisit[i + dx][j]; dx--) {
                    if (i + dx == m - 1 && j == n - 1) {
                        return step + 1;
                    }
                    xVisit[i + dx][j] = true;
                    next.add(new int[]{i + dx, j});
                }
            }
            step++;
            Deque<int[]> tmp = next;
            next = deque;
            deque = tmp;
        }
        ArrayUtil.print(grid);
        ArrayUtil.print(yVisit);
        ArrayUtil.print(xVisit);
        return -1;
    }

    public static void main(String[] args) {
        int[][] g = {{1,1,1,1,4,0,0},{3,1,1,0,1,1,5},{4,1,1,1,1,0,0},{0,0,0,0,0,0,0}};
        System.out.println(new LC0409().minimumVisitedCells(g));
    }
}
