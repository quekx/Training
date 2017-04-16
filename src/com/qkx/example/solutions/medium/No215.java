package com.qkx.example.solutions.medium;

/**
 * Created by qkx on 17/4/16.
 */
public class No215 {
    public int findKthLargest(int[] nums, int k) {
        int[] heap = new int[k];
        System.arraycopy(nums, 0, heap, 0, k);

        for (int i = k - 1; i >= 0; i--) {
            heapFixDownMin(heap, i, k - 1);
        }

        for (int i = k; i < nums.length; i++) {
            if (heap[0] < nums[i]) {
                heap[0] = nums[i];
                heapFixDownMin(heap, 0, k - 1);
            }
        }

        return heap[0];
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private void heapFixDownMin(int[] minHeap, int i, int n) {
        int x = minHeap[i];
        int j = i * 2 + 1;
        while (j <= n) {
            if (j + 1 <= n && minHeap[j] > minHeap[j + 1]) j++;
            if (x > minHeap[j]) {
                swap(minHeap, i, j);
                i = j;
                j = i * 2 + 1;
            } else {
                break;
            }
        }
        minHeap[i] = x;
    }
}
