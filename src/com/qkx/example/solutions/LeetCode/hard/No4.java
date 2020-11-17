package com.qkx.example.solutions.LeetCode.hard;

/**
 * @author kaixin
 * @since 2020-11-10 21:50
 */
//Given two sorted arrays nums1 and nums2 of size m and n respectively, return t
//he median of the two sorted arrays.
//
// Follow up: The overall run time complexity should be O(log (m+n)).
//
//
// Example 1:
//
//
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
//
//
// Example 2:
//
//
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//
//
// Example 3:
//
//
//Input: nums1 = [0,0], nums2 = [0,0]
//Output: 0.00000
//
//
// Example 4:
//
//
//Input: nums1 = [], nums2 = [1]
//Output: 1.00000
//
//
// Example 5:
//
//
//Input: nums1 = [2], nums2 = []
//Output: 2.00000
//
//
//
// Constraints:
//
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -106 <= nums1[i], nums2[i] <= 106
//
// Related Topics Array Binary Search Divide and Conquer
// 👍 8377 👎 1296

/**
 * 策略：
 * 实际结果中位数以x表示，得：
 * 有(m + n)/2个数比x大
 * 有(m + n)/2个数比x小
 * 数组1的中位数为a，数组2的中位数为b
 * 假设a < b，可得
 * 1. a为数组1的中位数，则有m/2个数据比a小
 * 2. b为数组2的中位数，则有n/2个数据比b小
 * 3. a < b，得：(1) m/2个比a小的数必定比b小; (2) n/2个比b大的数必定比a大
 * 加上a比b小，得：必有(1) （m + n）/ 2 + 1个数比b小; (2) （m + n）/ 2 + 1个数比a大
 * 因为x有准确的(m + n)/2个数比x大，(m + n)/2个数比x小
 * 所以：
 * 1. b > x，x必定不在数组2大于b的部分，这部分num[n/2, n]可以丢弃
 * 2. a < x，x必定不再数据1小于a的部分，这部分num[0, m/2]可以丢弃
 * 剩余部分为num1[m/2 + 1, m] + num2[0, n/2]
 */
//leetcode submit region begin(Prohibit modification and deletion)
public class No4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 == 0 && len2 == 0) {
            return 0;
        } else if (len1 == 0) {
            return (nums2[len2 / 2] + nums2[(len2 - 1) / 2]) / 2.0D;
        } else if (len2 == 0) {
            return (nums1[len1 / 2] + nums1[(len1 - 1) / 2]) / 2.0D;
        }

        return find(nums1, 0, len1 - 1, nums2, 0, len2 - 1);
    }

    public double find(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2) {
        if (start1 == end1) {
            return findOnOneArray(nums2, start2, end2, nums1[start1]);
        } else if (start2 == end2) {
            return findOnOneArray(nums1, start1, end1, nums2[start2]);
        }

        int media1 = (start1 + end1) / 2;
        int k = nums1[media1];
        int media2 = (start2 + end2) / 2;
        int m = nums2[media2];
        if (k == m) {
            return k;
        }



        return 0;
    }

    private double findOnOneArray(int[] arr, int start, int end, int num) {
        if (start == end) {
            return (arr[start] + num) / 2.0;
        }

        int media = (start + end) / 2;
        // 奇数偶数区分
        if ((end - start) % 2 == 0) {
            // m1, m2, num2
            int m1 = arr[media];
            int m2 = arr[media + 1];
            if (num < m1) {
                return m1;
            } else if (num < m2) {
                return num;
            } else {
                return m2;
            }
        } else {
            int m1 = arr[media - 1];
            int m2 = arr[media];
            int m3 = arr[media + 1];
            if (num < m1) {
                return (m1 + m2) / 2.0;
            } else if (num < m2) {
                return (num + m2) / 2.0;
            } else if (num < m3) {
                return (m2 + num) / 2.0;
            } else {
                return (m2 + m3) / 2.0;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

