package com.qkx.example.lc.achive.hard;

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

import java.util.Arrays;

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
        } else if (len2  == 0) {
            return (nums1[len1 / 2] + nums1[(len1 - 1) / 2]) / 2.0D;
        }

        if (len1 % 2 != 0) {
            if (len2 % 2 == 0) {
                return findOnDiffArray(nums1, 0, len1 - 1,
                        nums2, 0, len2 - 1);
            } else {
                return findOnOddArray(nums1, 0, len1 - 1,
                        nums2, 0, len2 - 1);
            }
        } else {
            if (len2 % 2 == 0) {
                return findOnEvenArray(nums1, 0, len1 - 1,
                        nums2, 0, len2 - 1);
            } else {
                return findOnDiffArray(nums2, 0, len2 - 1,
                        nums1, 0, len1 - 1);
            }
        }
    }

    /**
     * 数组长度一偶数一奇数
     *
     * @return
     */
    public double findOnDiffArray(int[] oddArr1, int start1, int end1,
                                  int[] evenArr2, int start2, int end2) {

        int oddMedianIndex = (start1 + end1) / 2;
        int oddMedian = oddArr1[oddMedianIndex];

        int evenMedianIndex = (start2 + end2) / 2;
        int evenMedian = evenArr2[evenMedianIndex];

        if (start1 == end1) {
            if (oddMedian <= evenMedian) {
                return evenMedian;
            } else {
                return Math.min(oddMedian, evenArr2[evenMedianIndex + 1]);
            }
        }

        if (oddMedian == evenMedian) {
            return oddMedian;
        }

        int x = (end1 - start1) / 2;
        int y = (end2 - start2) / 2 + 1;
        if (oddMedian < evenMedian) {
            int discard = Math.min(x + 1, y);
            if (discard % 2 == 0) {
                return findOnDiffArray(oddArr1, start1 + discard, end1,
                        evenArr2, start2, end2 - discard);
            } else {
                return findOnDiffArray(evenArr2, start2, end2 - discard,
                        oddArr1, start1 + discard, end1);
            }
        } else {
            int discard = Math.min(x, y);
            if (discard % 2 == 0) {
                return findOnDiffArray(oddArr1, start1, end1 - discard,
                        evenArr2, start2 + discard, end2);
            } else {
                return findOnDiffArray(evenArr2, start2 + discard, end2,
                        oddArr1, start1, end1 - discard);
            }
        }
    }

    /**
     * 两个数组长度均为偶数
     *
     * @return
     */
    private double findOnEvenArray(int[] evenArr1, int start1, int end1,
                                   int[] evenArr2, int start2, int end2) {
        int evenMedianIndex1 = (start1 + end1) / 2;
        int evenMedian1 = evenArr1[evenMedianIndex1];

        int evenMedianIndex2 = (start2 + end2) / 2 + 1;
        int evenMedian2 = evenArr2[evenMedianIndex2];

        if (start1 + 1 == end1 && start2 + 1 == end2) {
            return findMedia(evenArr1[evenMedianIndex1], evenArr1[evenMedianIndex1 + 1],
                    evenArr2[evenMedianIndex2 - 1], evenArr2[evenMedianIndex2]);
        } else if (start1 + 1 == end1) {
            return findMedia(evenArr1[evenMedianIndex1], evenArr1[evenMedianIndex1 + 1],
                    evenArr2[evenMedianIndex2 - 2], evenArr2[evenMedianIndex2 - 1],
                    evenArr2[evenMedianIndex2], evenArr2[evenMedianIndex2 + 1]);
        } else if (start2 + 1 == end2) {
            return findMedia(evenArr2[evenMedianIndex2 - 1], evenArr2[evenMedianIndex2],
                    evenArr1[evenMedianIndex1 - 1], evenArr1[evenMedianIndex1],
                    evenArr1[evenMedianIndex1 + 1], evenArr1[evenMedianIndex1 + 2]);
        }

        if (evenMedian1 == evenMedian2) {
            return evenMedian1;
        }

        int x = (end1 - start1) / 2 + 1;
        int y = (end2 - start2) / 2 + 1;
        if (evenMedian1 < evenMedian2) {
            int discard = Math.min(x - 1, y - 1);
            if (discard % 2 == 0) {
                return findOnEvenArray(evenArr1, start1 + discard, end1,
                        evenArr2, start2, end2 - discard);
            } else {
                return findOnOddArray(evenArr1, start1 + discard, end1,
                        evenArr2, start2, end2 - discard);
            }
        } else {
            int discard = Math.min(x, y);
            if (discard % 2 == 0) {
                return findOnEvenArray(evenArr1, start1, end1 - discard,
                        evenArr2, start2 + discard, end2);
            } else {
                return findOnOddArray(evenArr1, start1, end1 - discard,
                        evenArr2, start2 + discard, end2);
            }
        }
    }

    /**
     * 两个数组长度均为奇数
     *
     * @return
     */
    private double findOnOddArray(int[] oddArr1, int start1, int end1,
                                  int[] oddArr2, int start2, int end2) {
        int oddMedianIndex1 = (start1 + end1) / 2;
        int oddMedian1 = oddArr1[oddMedianIndex1];

        int oddMedianIndex2 = (start2 + end2) / 2;
        int oddMedian2 = oddArr2[oddMedianIndex2];

        if (start1 == end1 && start2 == end2) {
            return findMedia(oddArr1[oddMedianIndex1], oddArr2[oddMedianIndex2]);
        } else if (start1 == end1) {
            return findMedia(oddArr1[oddMedianIndex1], oddArr2[oddMedianIndex2 - 1],
                    oddArr2[oddMedianIndex2], oddArr2[oddMedianIndex2 + 1]);
        } else if (start2 == end2) {
            return findMedia(oddArr2[oddMedianIndex2], oddArr1[oddMedianIndex1 - 1],
                    oddArr1[oddMedianIndex1], oddArr1[oddMedianIndex1 + 1]);
        }

        if (oddMedian1 == oddMedian2) {
            return oddMedian1;
        }

        int x = (end1 - start1) / 2;
        int y = (end2 - start2) / 2;
        if (oddMedian1 < oddMedian2) {
            int discard = Math.min(x, y);
            if (discard % 2 == 0) {
                return findOnOddArray(oddArr1, start1 + discard, end1,
                        oddArr2, start2, end2 - discard);
            } else {
                return findOnEvenArray(oddArr1, start1 + discard, end1,
                        oddArr2, start2, end2 - discard);
            }
        } else {
            int discard = Math.min(x, y);
            if (discard % 2 == 0) {
                return findOnOddArray(oddArr1, start1, end1 - discard,
                        oddArr2, start2 + discard, end2);
            } else {
                return findOnEvenArray(oddArr1, start1, end1 - discard,
                        oddArr2, start2 + discard, end2);
            }
        }
    }

    private double findMedia(int... nums) {
        System.out.println(Arrays.toString(nums));
        int len = nums.length;
        Arrays.sort(nums);
        return len % 2 == 0 ? (nums[len / 2 - 1] + nums[len / 2]) / 2D : nums[len / 2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

