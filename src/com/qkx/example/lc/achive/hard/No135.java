package com.qkx.example.lc.achive.hard;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kaixin
 * @since 2020-12-03 20:47
 */
//There are N children standing in a line. Each child is assigned a rating value
//.
//
// You are giving candies to these children subjected to the following requireme
//nts:
//
//
// Each child must have at least one candy.
// Children with a higher rating get more candies than their neighbors.
//
//
// What is the minimum candies you must give?
//
// Example 1:
//
//
//Input: [1,0,2]
//Output: 5
//Explanation: You can allocate to the first, second and third child with 2, 1,
//2 candies respectively.
//
//
// Example 2:
//
//
//Input: [1,2,2]
//Output: 4
//Explanation: You can allocate to the first, second and third child with 1, 2,
//1 candies respectively.
//             The third child gets 1 candy because it satisfies the above two c
//onditions.
//
// Related Topics Greedy
// 👍 1202 👎 177


//leetcode submit region begin(Prohibit modification and deletion)
public class No135 {
    public static void main(String[] args) {
        int[] ratings = {1, 3, 2, 2, 1};
        int[] ratings2 = {1, 3, 4, 5, 2};
        int[] ratings3 = {1,2,3,5,4,3,2,1,4,3,2,1,3,2,1,1,2,3,4};
        System.out.println(new No135().candy(ratings3));
    }

    /**
     * 先扫描一遍数据，取出所有洼地点
     * 1. 各个洼地点置1
     * 2. 从挖地点开始向左右两边遍历
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        if (ratings == null && ratings.length == 0) {
            return 0;
        }
        if (ratings.length == 1) {
            return 1;
        }

        int length = ratings.length;
        List<Integer> lowNodes = new LinkedList<>();
        if (ratings[0] < ratings[1]) {
            lowNodes.add(0);
        }
        for (int i = 1; i <= length - 2; i++) {
            if (ratings[i - 1] > ratings[i] && ratings[i] < ratings[i + 1]) {
                lowNodes.add(i);
            } else if (ratings[i - 1] > ratings[i] && ratings[i] == ratings[i + 1]) {
                lowNodes.add(i);
            } else if (ratings[i - 1] == ratings[i] && ratings[i] < ratings[i + 1]) {
                lowNodes.add(i);
            }
        }
        if (ratings[length - 2] > ratings[length - 1]) {
            lowNodes.add(length - 1);
        }

        int[] num = new int[length];
        for (int lowNode : lowNodes) {
            num[lowNode] = 1;
            // 向左遍历
            for (int left = lowNode - 1; left >= 0 && ratings[left] > ratings[left + 1]; left--) {
                int leftNum = num[left + 1] + 1;
                num[left] = Math.max(num[left], leftNum);
            }
            // 向右遍历
            for (int right = lowNode + 1; right <= length - 1 && ratings[right - 1] < ratings[right]; right++) {
                int rightNum = num[right - 1] + 1;
                num[right] = Math.max(num[right], rightNum);
            }
        }

        int result = 0;
        for (int i = 0; i <= length - 1; i++) {
            if (num[i] == 0) {
                num[i] = 1;
            }
            result += num[i];
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

