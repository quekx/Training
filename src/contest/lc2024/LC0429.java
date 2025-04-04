package contest.lc2024;

import java.util.PriorityQueue;

public class LC0429 {
    public int maximizeSum(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        // n + n+1 + ... + n+k-1
        return (2 * max + k) * k / 2;
    }

    private int find(int x, int[] p) {
        if (p[x] != x) {
            p[x] = find(p[x], p);
        }
        return p[x];
    }

    /**
     * 执行结果：
     * 超出时间限制
     */
    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add(num);
        }
        int k = find(0, p);
        int ans = 0;
        while (!queue.isEmpty()) {
            int curMin = queue.peek();
            int np = find((k + 1) % n, p);
            ans++;
            if (nums[k] == curMin) {
                queue.poll();
                // 删除 k
                p[k] = np;

            }
            if (np == k) {
                break;
            }
            k = np;
        }
        return ans;
    }
}
