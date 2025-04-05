package com.qkx.example.lc.achive.medium;

import java.util.*;

/**
 * @author kaixin
 * @since 2021-01-21 15:20
 */
//Given an array nums of n integers, are there elements a, b, c in nums such tha
//t a + b + c = 0? Find all unique triplets in the array which gives the sum of ze
//ro.
//
// Notice that the solution set must not contain duplicate triplets.
//
//
// Example 1:
// Input: nums = [-1,0,1,2,-1,-4]
//Output: [[-1,-1,2],[-1,0,1]]
// Example 2:
// Input: nums = []
//Output: []
// Example 3:
// Input: nums = [0]
//Output: []
//
//
// Constraints:
//
//
// 0 <= nums.length <= 3000
// -105 <= nums[i] <= 105
//
// Related Topics Array Two Pointers
// 👍 9174 👎 960


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * [-1, 0, 1, 1]
 * [-1, -1, 0, 1]
 */
public class No15 {

    public static void main(String[] args) {
        int[] nums = {0,0,0};
        System.out.println(new No15().threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length <= 2) {
            return res;
        }

        Arrays.sort(nums);
        int i = 0;
        while (i <= nums.length - 3) {
            int sum = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == sum) {
                    List<Integer> tuple = Arrays.asList(nums[i], nums[left], nums[right]);
                    res.add(tuple);
                    while (left + 1 <= nums.length - 1 && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    while (right - 1 >= 0 && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < sum) {
                    left++;
                } else {
                    right--;
                }
            }
            while (i + 1 <= nums.length - 1 && nums[i + 1] == nums[i]) {
                i++;
            }
            i++;
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Map<Integer, Integer> nagMap = new HashMap<>();
        Map<Integer, Integer> posMap = new HashMap<>();
        boolean isZeroExist = false;
        int zeroNums = 0;
        int n;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                n = nagMap.getOrDefault(nums[i], 0);
                nagMap.put(nums[i], n + 1);
            } else if (nums[i] > 0) {
                n = posMap.getOrDefault(nums[i], 0);
                posMap.put(nums[i], n + 1);
            } else {
                if (!isZeroExist) {
                    isZeroExist = true;
                }
                zeroNums++;
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> nagSet = nagMap.keySet();
        Integer[] nag = nagSet.toArray(new Integer[nagSet.size()]);
        Set<Integer> posSet = posMap.keySet();
        Integer[] pos = posSet.toArray(new Integer[posSet.size()]);
        // -0+
        if (isZeroExist) {
            if (zeroNums >= 3) {
                List<Integer> l = new ArrayList<>();
                l.add(0);
                l.add(0);
                l.add(0);
                res.add(l);
            }
            for (int i = 0; i < nag.length; i++) {
                int p = -nag[i];
                if (posMap.containsKey(p)) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nag[i]);
                    l.add(0);
                    l.add(p);
                    res.add(l);
                }
            }
        }
        // --+
        for (int i = 0; i < nag.length; i++) {
            for (int j = i; j < nag.length; j++) {
                if (i == j) {
                    if (nagMap.get(nag[i]) < 2) {
                        continue;
                    } else {
                        int p = -2 * nag[i];
                        if (posMap.containsKey(p)) {
                            List<Integer> l = new ArrayList<>();
                            l.add(nag[i]);
                            l.add(nag[i]);
                            l.add(p);
                            res.add(l);
                        }
                    }
                } else {
                    int p = -nag[i] - nag[j];
                    if (posMap.containsKey(p)) {
                        List<Integer> l = new ArrayList<>();
                        int a = nag[i] < nag[j] ? nag[i] : nag[j];
                        int b = nag[i] > nag[j] ? nag[i] : nag[j];
                        l.add(a);
                        l.add(b);
                        l.add(p);
                        res.add(l);
                    }
                }
            }
        }
        // -++
        for (int i = 0; i < pos.length; i++) {
            for (int j = i; j < pos.length; j++) {
                if (i == j) {
                    if (posMap.get(pos[i]) < 2) {
                        continue;
                    } else {
                        int p = -2 * pos[i];
                        if (nagMap.containsKey(p)) {
                            List<Integer> l = new ArrayList<>();
                            l.add(p);
                            l.add(pos[i]);
                            l.add(pos[i]);
                            res.add(l);
                        }
                    }
                } else {
                    int p = -pos[i] - pos[j];
                    if (nagMap.containsKey(p)) {
                        List<Integer> l = new ArrayList<>();
                        int a = pos[i] < pos[j] ? pos[i] : pos[j];
                        int b = pos[i] > pos[j] ? pos[i] : pos[j];
                        l.add(p);
                        l.add(a);
                        l.add(b);
                        res.add(l);
                    }
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

