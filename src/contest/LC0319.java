package contest;

import com.qkx.example.utils.ArrayUtil;

import java.util.Arrays;

public class LC0319 {
    public int[] evenOddBit(int n) {
        int even = 0, odd = 0;
        int sign = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                if (sign % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }
            n >>= 1;
            sign++;
        }
        return new int[]{even, odd};
    }

    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int[][] seq = new int[n * n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                seq[i * n + j][0] = i;
                seq[i * n + j][1] = j;
                seq[i * n + j][2] = grid[i][j];
            }
        }
        Arrays.sort(seq, (a, b) -> a[2] - b[2]);
        for (int i = 1; i < n * n; i++) {
            int x1 = seq[i - 1][0], y1 = seq[i - 1][1];
            int x2 = seq[i][0], y2 = seq[i][1];
            if (!check(x1, y1, x2, y2)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1)
                || (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2);
    }

    public int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        int[] counter = new int[1001];
        for (int num : nums) {
            counter[num]++;
        }
        int ans = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] - k || nums[j] == nums[i]) {
                    dp[i] += dp[j];
                } else if (nums[j] > nums[i] - k) {
                    dp[i] += dp[j] >> (nums[i] - k > 0 ? counter[nums[i] - k] : 0);
                }
            }
            dp[i]++;
            ans += dp[i];
        }
        return ans;
    }

    /**
     * 给你一个下标从 0 开始的整数数组 nums 和一个整数 value 。
     *
     * 在一步操作中，你可以对 nums 中的任一元素加上或减去 value 。
     *
     * 例如，如果 nums = [1,2,3] 且 value = 2 ，你可以选择 nums[0] 减去 value ，得到 nums = [-1,2,3] 。
     * 数组的 MEX (minimum excluded) 是指其中数组中缺失的最小非负整数。
     *
     * 例如，[-1,2,3] 的 MEX 是 0 ，而 [1,0,3] 的 MEX 是 2 。
     * 返回在执行上述操作 任意次 后，nums 的最大 MEX 。
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/smallest-missing-non-negative-integer-after-operations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findSmallestInteger(int[] nums, int value) {
        int[] counter = new int[value];
        for (int num : nums) {
            counter[mod(num, value)]++;
        }
        int minPos = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] < counter[minPos]) {
                minPos = i;
            }
        }
        return counter[minPos] * value + minPos;
    }

    private int mod(int num, int v) {
        int ans = num % v;
        return ans >= 0 ? ans : ans + v;
    }

    public static void main(String[] args) {
//        System.out.println(new LC0319().evenOddBit(17));

//        int[][] g = {{24, 11, 22, 17, 4}, {21, 16, 5, 12, 9}, {6, 23, 10, 3, 18}, {15, 20, 1, 8, 13}, {0, 7, 14, 19, 2}};
//        ArrayUtil.print(g);
//        System.out.println(new LC0319().checkValidGrid(g));

        //[10,4,5,7,2,1]
        //3
//        int[] nums = {10,4,5,7,2,1};
//        int k = 3;
//        System.out.println(new LC0319().beautifulSubsets(nums, k));
//
//        System.out.println(-14 % 5);

        int[] nums = {3,0,3,2,4,2,1,1,0,4};
        int v = 5;
        System.out.println(new LC0319().findSmallestInteger(nums, v));
    }
}
