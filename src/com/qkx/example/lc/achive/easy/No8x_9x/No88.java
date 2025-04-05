package com.qkx.example.lc.achive.easy.No8x_9x;

/**
 * Created by qkx on 16/5/29.
 */
public class No88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        while (j < n) {
            if (i >= m) {
                nums1[i] = nums2[j++];
            } else if (nums1[i] > nums2[0]) {
                int temp = nums1[i];
                nums1[i] = nums2[0];
                nums2[0] = temp;
                for (int k = 1; k < n; k++) {
                    if (nums2[k] < temp) {
                        nums2[k - 1] = nums2[k];
                    } else {
                        nums2[k - 1] = temp;
                        break;
                    }
                    if (k == n - 1) {
                        nums2[k] = temp;
                    }
                }
            }
            i++;
        }

    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {

        int[] res = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m || j < n) {
            if (i == m) {
                res[k++] = nums2[j++];
                continue;
            } else if (j == n) {
                res[k++] = nums1[i++];
                continue;
            }

            if (nums1[i] < nums2[j]) {
                res[k++] = nums1[i++];
            } else {
                res[k++] = nums2[j++];
            }
        }

        for (int x = 0; x < m + n; x++) {
            nums1[x] = res[x];
        }

    }

    public static void merge3(int[] nums1, int m, int[] nums2, int n) {

        if (n == 0) {
            return;
        }

        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }

        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 || j >= 0) {
            if (i < 0) {
                nums1[k--] = nums2[j--];
                continue;
            } else if (j < 0) {
                nums1[k--] = nums1[i--];
                continue;
            }

            if (nums1[i] < nums2[j]) {
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }
        }

    }
}
