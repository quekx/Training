package com.qkx.example.lc.achive.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kaixin
 * @since 2021-01-27 15:37
 */
//Given an array nums of n integers and an integer target, are there elements a,
// b, c, and d in nums such that a + b + c + d = target? Find all unique quadruple
//ts in the array which gives the sum of target.
//
// Notice that the solution set must not contain duplicate quadruplets.
//
//
// Example 1:
// Input: nums = [1,0,-1,0,-2,2], target = 0
//Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// Example 2:
// Input: nums = [], target = 0
//Output: []
//
//
// Constraints:
//
//
// 0 <= nums.length <= 200
// -109 <= nums[i] <= 109
// -109 <= target <= 109
//
// Related Topics Array Hash Table Two Pointers
// 👍 2862 👎 388


//leetcode submit region begin(Prohibit modification and deletion)
public class No18 {

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println(new No18().fourSum(nums, target));
    }

    /**
     * 解答成功:
     * 执行耗时:33 ms,击败了17.01% 的Java用户
     * 内存消耗:41.1 MB,击败了12.79% 的Java用户
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }

        Arrays.sort(nums);

        int a = 0;
        while (a <= nums.length - 4) {
            int b = a + 1;
            while (b <= nums.length - 3) {
                int c = b + 1;
                int d = nums.length - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c + 1 <= nums.length - 1 && nums[c] == nums[c + 1]) {
                            c++;
                        }
                        c++;
                    } else if (sum > target) {
                        d--;
                    } else {
                        c++;
                    }
                }

                while (b + 1 <= nums.length - 1 && nums[b] == nums[b + 1]) {
                    b++;
                }
                b++;
            }

            while (a + 1 <= nums.length - 1 && nums[a] == nums[a + 1]) {
                a++;
            }
            a++;
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

