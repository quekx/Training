package com.qkx.example.lc;

//Given an integer array nums, return an integer array counts where counts[i]
//is the number of smaller elements to the right of nums[i].
//
//
// Example 1:
//
//
//Input: nums = [5,2,6,1]
//Output: [2,1,1,0]
//Explanation:
//To the right of 5 there are 2 smaller elements (2 and 1).
//To the right of 2 there is only 1 smaller element (1).
//To the right of 6 there is 1 smaller element (1).
//To the right of 1 there is 0 smaller element.
//
//
// Example 2:
//
//
//Input: nums = [-1]
//Output: [0]
//
//
// Example 3:
//
//
//Input: nums = [-1,-1]
//Output: [0,0]
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
//
//
// Related Topics Array Binary Search Divide and Conquer Binary Indexed Tree
//Segment Tree Merge Sort Ordered Set 👍 8001 👎 216


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class No315 {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        LinkedList<Integer> ans = new LinkedList<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = n - 1; i >= 0; i--) {
            ans.addFirst(treeMap.size() - treeMap.tailMap(nums[i]).size());
            treeMap.put(nums[i], 0);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
