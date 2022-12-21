package com.qkx.example.lc.hard;

//Given an integer array nums, return the maximum difference between two
//successive elements in its sorted form. If the array contains less than two elements,
//return 0.
//
// You must write an algorithm that runs in linear time and uses linear extra
//space.
//
//
// Example 1:
//
//
//Input: nums = [3,6,9,1]
//Output: 3
//Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9)
//has the maximum difference 3.
//
//
// Example 2:
//
//
//Input: nums = [10]
//Output: 0
//Explanation: The array contains less than 2 elements, therefore return 0.
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 10⁵
// 0 <= nums[i] <= 10⁹
//
//
// Related Topics Array Sorting Bucket Sort Radix Sort 👍 2570 👎 318


//leetcode submit region begin(Prohibit modification and deletion)
class No164 {

    /**
     * 解答成功:
     * 	执行耗时:8 ms,击败了100.00% 的Java用户
     * 	内存消耗:52.5 MB,击败了95.00% 的Java用户
     */
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int maxDiff = max - min;
        if (maxDiff <= 1) {
            return maxDiff;
        }

        int n = nums.length;
        int bucketSize = maxDiff / (n - 1) + 1;
        int[] minBucket = new int[n];
        int[] maxBucket = new int[n];
        for (int i = 0; i < n; i++) {
            minBucket[i] = Integer.MAX_VALUE;
            maxBucket[i] = Integer.MIN_VALUE;
        }
        for (int num : nums) {
            int bucketNum = (num - min) / bucketSize;
            minBucket[bucketNum] = Math.min(minBucket[bucketNum], num);
            maxBucket[bucketNum] = Math.max(maxBucket[bucketNum], num);
        }
        int ans = 0;
        int lastMax = max;
        for (int i = 0; i < minBucket.length; i++) {
            if (minBucket[i] == Integer.MAX_VALUE) {
                continue;
            }
            ans = Math.max(ans, maxBucket[i] - minBucket[i]);
            ans = Math.max(ans, minBucket[i] - lastMax);
            lastMax = maxBucket[i];
        }
        return ans;
    }

    /**
     * 解答成功:
     * 执行耗时:96 ms,击败了16.98% 的Java用户
     * 内存消耗:104.6 MB,击败了8.02% 的Java用户
     */
    public int maximumGap2(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int maxDiff = max - min;
        if (maxDiff <= 1) {
            return maxDiff;
        }

        int n = nums.length;
        int bucketSize = maxDiff / (n - 1) + 1;
        int[][] bucket = new int[n][0];
        for (int num : nums) {
            int bucketNum = (num - min) / bucketSize;
            if (bucket[bucketNum].length == 0) {
                bucket[bucketNum] = new int[]{num};
            } else {
                if (bucket[bucketNum].length == 1) {
                    int a = bucket[bucketNum][0];
                    bucket[bucketNum] = num < a
                            ? new int[]{num, a}
                            : new int[]{a, num};
                } else {
                    int a = bucket[bucketNum][0];
                    int b = bucket[bucketNum][1];
                    bucket[bucketNum][0] = Math.min(a, num);
                    bucket[bucketNum][1] = Math.max(b, num);
                }
            }
        }
        int ans = 0;
        int last = max;
        int j = 0;
        while (j < bucket.length) {
            if (bucket[j].length == 1) {
                ans = Math.max(ans, bucket[j][0] - last);
                last = bucket[j][0];
            } else if (bucket[j].length == 2) {
                ans = Math.max(ans, bucket[j][0] - last);
                ans = Math.max(ans, bucket[j][1] - bucket[j][0]);
                last = bucket[j][1];
            }
            j++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {3,6,9,1};
        System.out.println(new No164().maximumGap(nums));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
