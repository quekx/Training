package contest.lc2024;

import java.util.*;

public class LC1201 {
    public int smallestNumber(int n) {
        int ans = 1;
        while (ans < n) {
            ans = (ans << 1) + 1;
        }
        return ans;
    }

    public int getLargestOutlier(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> counter = new HashMap<>();
        int sum = 0;
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
            sum += num;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            int ans = nums[i];
            if ((sum - ans) % 2 != 0) {
                continue;
            }
            int x = (sum - ans) / 2;
            int numX = counter.getOrDefault(x, 0);
            if ((x == ans) && numX >= 2) {
                return ans;
            }
            if ((x != ans) && numX >= 1) {
                return ans;
            }
        }
        return -1;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1, m = edges2.length + 1;
        int[][] dist1 = new int[n][n];
        int[] nums1 = new int[n];
        for (int[] edges : edges1) {
            int a = edges[0];
            int b = edges[1];

        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = {6,-31,50,-35,41,37,-42,13};
        int x = new LC1201().getLargestOutlier(nums);
        System.out.println(x);
    }
}
