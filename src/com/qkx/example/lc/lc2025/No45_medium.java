package com.qkx.example.lc.lc2025;

/**
 * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 *
 * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
 *
 * 0 <= j <= nums[i]
 * i + j < n
 * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
 *
 * medium
 */
public class No45_medium {
    /**
     * 通过
     * 110 / 110 个通过的测试用例
     * QueKaix
     * QueKaix
     * 提交于 2025.04.05 16:12
     *
     * 官方题解
     *
     * 写题解
     * 1024
     * 小红书特训计划
     * 掌握小红书面试高频考点
     * 执行用时分布
     * 1
     * ms
     * 击败
     * 99.47%
     * 复杂度分析
     * 消耗内存分布
     * 44.60
     * MB
     * 击败
     * 12.74%
     */
    public int jump(int[] nums) {
        int[] step = new int[nums.length];
        int maxJump = 0;
        for (int i = 0; i < nums.length; i++) {
            int newMaxJump = i + nums[i];
            if (newMaxJump > maxJump) {
                for (int j = maxJump + 1; j <= newMaxJump && j < nums.length; j++) {
                    step[j] = step[i] + 1;
                }
                maxJump = newMaxJump;
            }
        }
        return step[nums.length - 1];
    }
}
