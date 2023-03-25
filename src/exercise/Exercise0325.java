package exercise;

import java.util.HashMap;
import java.util.Map;

public class Exercise0325 {
    /**
     * 给定一个数组，操作每次选定一个数 x，使数据内所有 x -> x + 1
     * 问：最少操作多少次，使数组变为非降 O(n*log(n))
     */
    public int minOperate(int[] nums) {
        // 两个相同的数据 x，之间所有数据必然相等，因此可以进行分段
        // 记录每个数 x 出现的最大位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
        }
        int preMax = Integer.MIN_VALUE;
        int start = 0;
        while (start < nums.length) {
            // 分段
            int max = Integer.MIN_VALUE;
            int maxIdx = map.get(nums[start]);
            int end = start;
            while (end <= maxIdx) {
                maxIdx = Math.max(maxIdx, map.get(nums[end]));
                max = Math.max(max, nums[end]);
                end++;
            }
            // 计算 [start, end - 1, max] + preMax
            // 区间内数都变成 max(preMax, max)
            System.out.print("preMax: " + preMax + " >> ");
            for (int i = start; i <= end - 1; i++) {
                System.out.print(" " + nums[i]);
            }
            System.out.print("  << max: " + max + "\n");
            start = end;
            preMax = Math.max(preMax, max);
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 4, 3, 5, 5, 1, 6};
        new Exercise0325().minOperate(nums);
    }
}
