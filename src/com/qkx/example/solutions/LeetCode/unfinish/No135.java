package com.qkx.example.solutions.LeetCode.unfinish;

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
        int[] ratings3 = {1, 0, 2};
        System.out.println(new No135().candy(ratings3));
    }

    /**
     * num
     * a[i] > a[i+1] num[i+1] = num[i-1] - 1
     * a[i] < a[i+1] num[i+1] = num[i-1] + 1
     * a[i] = a[i+1] num[i+1] 与 num[i-1]无关
     * 当a[i] = a[i+1]，以此分为多个区间，区间内保证大小即可
     * 需要信息
     * 区间开始 start
     * 区间结束 end
     * 区间内最小 min
     * 当前值
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int result = 0;
        int start = 0;
        int min = 1;
        int curNum = 1;
        for (int i = 0; i <= ratings.length - 2; i++) {
            result += curNum;
            min = Math.min(min, curNum);
            if (ratings[i] == ratings[i + 1]) {
                // 下一位的初始值
                curNum = 1;
                min = 1;
                // 开始结算区间，如果min <= 0，需要整体补全到1
                // 整体需要加上(i - start + 1) * (1 - min)
                result += (i - start + 1) * (1 - min);
                start = i + 1;
            } else if (ratings[i] < ratings[i + 1]) {
                // 下一位的糖果+1
                curNum++;
            } else {
                // 下一位的糖果-1
                curNum--;
                curNum = Math.min(curNum, 1);
            }
        }

        result += curNum;
        min = Math.min(min, curNum);
        result += (ratings.length - 1 - start + 1) * (1 - min);

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

