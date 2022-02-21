package com.qkx.example.lc.medium;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2021-09-26 15:43
 */
//Given four integer arrays nums1, nums2, nums3, and nums4 all of length n,
//return the number of tuples (i, j, k, l) such that:
//
//
// 0 <= i, j, k, l < n
// nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
//
//
//
// Example 1:
//
//
//Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
//Output: 2
//Explanation:
//The two tuples are:
//1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1)
// + 2 = 0
//2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1)
// + 0 = 0
//
//
// Example 2:
//
//
//Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
//Output: 1
//
//
//
// Constraints:
//
//
// n == nums1.length
// n == nums2.length
// n == nums3.length
// n == nums4.length
// 1 <= n <= 200
// -2²⁸ <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2²⁸
//
// Related Topics Array Hash Table 👍 2395 👎 89


//leetcode submit region begin(Prohibit modification and deletion)
public class No454 {
    //Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
    // 1 -2 -1 2
    // 2 -1 -1 0
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        int count = new No454().fourSumCount(nums1, nums2, nums3, nums4);
        System.out.println(count);
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        if (nums1.length == 0) {
            return 0;
        }
        Arrays.sort(nums3);
        Arrays.sort(nums4);
        int n = nums1.length;
        int count = 0;
        for (int i = 0; i <= n - 1; i++) {
            for (int j = 0; j <= n - 1; j++) {
                int target = -nums1[i] - nums2[j];
                int left = 0;
                int right = n - 1;
                while (left >= 0 && left <= n - 1 && right >= 0 && right <= n - 1) {
                    int sum = nums3[left] + nums4[right];
                    if (sum == target) {
                        int leftCount = 1;
                        int rightCount = 1;
                        while (left + 1 <= n - 1 && nums3[left + 1] == nums3[left]) {
                            left++;
                            leftCount++;
                        }
                        while (right - 1 >= 0 && nums4[right - 1] == nums4[right]) {
                            right--;
                            rightCount++;
                        }
                        count += leftCount * rightCount;
                        left++;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

