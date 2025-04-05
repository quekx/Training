package com.qkx.example.lc.achive.medium;

//Given an array of integers with possible duplicates, randomly output the index
// of a given target number. You can assume that the given target number must exis
//t in the array.
//
// Note:
//The array size can be very large. Solution that uses too much extra space will
// not pass the judge.
//
// Example:
//
//
//int[] nums = new int[] {1,2,3,3,3};
//Solution solution = new Solution(nums);
//
//// pick(3) should return either index 2, 3, or 4 randomly. Each index should h
//ave equal probability of returning.
//solution.pick(3);
//
//// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
//solution.pick(1);
//
// Related Topics Reservoir Sampling
// 👍 550 👎 749


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
public class No398 {

    private class Solution {

        private Map<Integer, Integer[]> index;
        private Random random;

        public Solution(int[] nums) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                List<Integer> list = map.get(nums[i]);
                if (list == null) {
                    list = new ArrayList<>();
                    map.put(nums[i], list);
                }
                list.add(i);
            }

            this.random = new Random();
            this.index = new HashMap<>();
            for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                index.put(entry.getKey(), (Integer[]) entry.getValue().toArray());
            }
        }

        public int pick(int target) {
            Integer[] positions = this.index.get(target);
            int swapPosition = random.nextInt(positions.length);
//            Integer temp =
            return positions[0];
        }
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
//leetcode submit region end(Prohibit modification and deletion)
