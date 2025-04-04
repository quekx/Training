package contest.lc2024;

import java.util.Arrays;

public class LC1208 {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int target = (i + nums[i]) % n;
            target = target >= 0 ? target : target + n;
            ans[i] = nums[target];
        }
        return ans;
    }

    public int maxRectangleArea(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int ans = -1;
        for (int i = 0; i < points.length; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            if (i + 1 >= points.length || points[i + 1][0] != x1) {
                continue;
            }
            int y2 =  points[i + 1][1];
            // 下一个右边的点
            ans = Math.max(ans, (y2 - y1) * findDelta(x1, y1, y2, i + 2, points));
        }
        return 0;
    }

    private int findDelta(int x1, int y1, int y2, int j, int[][] points) {
        for (; j <= points.length - 2; j++) {
            int x = points[j][0];
            int y = points[j][1];
            if (y == y1) {
                return points[j + 1][0] == x && points[j + 1][1] == y2 ? x - x1 : -1;
            }
            if (points[j][1] > y1 && points[j][1] <= y2) {
                return -1;
            }
        }
        return -1;
    }

    /**
     * pre[0] = 0
     * pre[1] = num[0]
     * pre[x] = num[0] + ... + num[x - 1]
     *
     * dp[x] -> 以 num[x] 为结尾，长度为 k 倍数的所有子数组和中的最大值
     * x <= k - 2 时 dp[x] = 0
     * dp[k - 1] = num[0] + ... + num[k - 1]
     * dp[x] = dp[x - k] + num[x - k + 1] + ... + num[x]
     */
    public long maxSubarraySum(int[] nums, int k) {
        if (nums.length < k) {
            return -1;
        }
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        long ans = preSum[k];
        long[] dp = new long[nums.length];
        dp[k - 1] = preSum[k];
        for (int x = k; x < nums.length; x++) {
            dp[x] = preSum[x + 1] - preSum[x - k + 1] + Math.max(dp[x - k], 0);
            ans = Math.max(ans, dp[x]);
        }
        return ans;
    }

    public long maxRectangleArea(int[] xCoord, int[] yCoord) {
        int n = xCoord.length;
        int[][] point = new int[n][2];
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {-1,-2,-3,-4,-5};
        int k = 4;
        long ans = new LC1208().maxSubarraySum(nums, k);
        System.out.println(ans);
    }
}
