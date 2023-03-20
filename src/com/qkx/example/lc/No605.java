package com.qkx.example.lc;

//You have a long flowerbed in which some of the plots are planted, and some
//are not. However, flowers cannot be planted in adjacent plots.
//
// Given an integer array flowerbed containing 0's and 1's, where 0 means empty
//and 1 means not empty, and an integer n, return if n new flowers can be planted
//in the flowerbed without violating the no-adjacent-flowers rule.
//
//
// Example 1:
// Input: flowerbed = [1,0,0,0,1], n = 1
//Output: true
//
// Example 2:
// Input: flowerbed = [1,0,0,0,1], n = 2
//Output: false
//
//
// Constraints:
//
//
// 1 <= flowerbed.length <= 2 * 10⁴
// flowerbed[i] is 0 or 1.
// There are no two adjacent flowers in flowerbed.
// 0 <= n <= flowerbed.length
//
//
// Related Topics Array Greedy 👍 3910 👎 762


//leetcode submit region begin(Prohibit modification and deletion)
class No605 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        boolean[] isInvalid = new boolean[flowerbed.length];
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                if (isInvalid[i]) {
                    return false;
                }
                if (i + 1 < flowerbed.length) {
                    isInvalid[i + 1] = true;
                }
                if (i - 1 >= 0) {
                    isInvalid[i - 1] = true;
                }
            }
        }
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && !isInvalid[i]) {
                n--;
                if (n <= 0) {
                    return true;
                }
                if (i + 1 < flowerbed.length) {
                    isInvalid[i + 1] = true;
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


