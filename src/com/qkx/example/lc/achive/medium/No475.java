package com.qkx.example.lc.achive.medium;

import java.util.Arrays;

/**
 * @author kaixin
 * @since 2022-02-21 15:43
 */
//Winter is coming! During the contest, your first job is to design a standard
//heater with a fixed warm radius to warm all the houses.
//
// Every house can be warmed, as long as the house is within the heater's warm
//radius range.
//
// Given the positions of houses and heaters on a horizontal line, return the
//minimum radius standard of heaters so that those heaters could cover all houses.
//
// Notice that all the heaters follow your radius standard, and the warm radius
//will the same.
//
//
// Example 1:
//
//
//Input: houses = [1,2,3], heaters = [2]
//Output: 1
//Explanation: The only heater was placed in the position 2, and if we use the
//radius 1 standard, then all the houses can be warmed.
//
//
// Example 2:
//
//
//Input: houses = [1,2,3,4], heaters = [1,4]
//Output: 1
//Explanation: The two heater was placed in the position 1 and 4. We need to
//use radius 1 standard, then all the houses can be warmed.
//
//
// Example 3:
//
//
//Input: houses = [1,5], heaters = [2]
//Output: 3
//
//
//
// Constraints:
//
//
// 1 <= houses.length, heaters.length <= 3 * 10⁴
// 1 <= houses[i], heaters[i] <= 10⁹
//
// Related Topics Array Two Pointers Binary Search Sorting 👍 1260 👎 1007


//leetcode submit region begin(Prohibit modification and deletion)
class No475 {
    public static void main(String[] args) {
        int[] houses = {1,2,3,4,5};
        int[] heaters = {1,3,5};
        System.out.println(new No475().findRadius(houses, heaters));
    }

    /**
     * 解答成功:
     * 执行耗时:17 ms,击败了69.64% 的Java用户
     * 内存消耗:54.1 MB,击败了29.21% 的Java用户
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int curLeft = -1;
        int curRight = 0;
        int radius = 0;
        for (int i = 0; i < houses.length; i++) {
            // left
            while (curLeft + 1 >= 0 && curLeft + 1 <= heaters.length - 1 && heaters[curLeft + 1] <= houses[i]) {
                curLeft++;
            }
            // right
            while (curRight >= 0 && curRight <= heaters.length - 1 && heaters[curRight] < houses[i]) {
                curRight++;
            }

            int curRadius = Integer.MAX_VALUE;
            if (curLeft >= 0 && curLeft <= heaters.length - 1) {
                curRadius = Math.min(curRadius, houses[i] - heaters[curLeft]);
            }
            if (curRight >= 0 && curRight <= heaters.length - 1) {
                curRadius = Math.min(curRadius, heaters[curRight] - houses[i]);
            }
            radius = Math.max(radius, curRadius);
        }
        return radius;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

