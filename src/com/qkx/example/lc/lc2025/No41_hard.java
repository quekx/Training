package com.qkx.example.lc.lc2025;

// hard
public class No41_hard {

    /**
     * 通过
     * 178 / 178 个通过的测试用例
     * QueKaix
     * QueKaix
     * 提交于 2025.04.04 10:50
     *
     * 官方题解
     *
     * 写题解
     * 执行用时分布
     * 2
     * ms
     * 击败
     * 41.31%
     * 复杂度分析
     * 消耗内存分布
     * 56.05
     * MB
     * 击败
     * 80.72%
     */
    public int firstMissingPositive(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= 0) {
                continue;
            }
            min = Math.min(min, num);
        }
        if (min != 1) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            swap(nums, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    /**
     * len >= p >= 1
     * 把 p 放在nums[p - 1]的位置
     */
    private void swap(int[] nums, int p) {
        if (p <= 0 || p > nums.length) {
            return;
        }
        int next = nums[p - 1];
        if (next == p) {
            return;
        }
        nums[p - 1] = p;
        swap(nums, next);
    }
}
