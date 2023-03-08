package com.qkx.example.lc.medium;

//Koko loves to eat bananas. There are n piles of bananas, the iᵗʰ pile has
//piles[i] bananas. The guards have gone and will come back in h hours.
//
// Koko can decide her bananas-per-hour eating speed of k. Each hour, she
//chooses some pile of bananas and eats k bananas from that pile. If the pile has less
//than k bananas, she eats all of them instead and will not eat any more bananas
//during this hour.
//
// Koko likes to eat slowly but still wants to finish eating all the bananas
//before the guards return.
//
// Return the minimum integer k such that she can eat all the bananas within h
//hours.
//
//
// Example 1:
//
//
//Input: piles = [3,6,7,11], h = 8
//Output: 4
//
//
// Example 2:
//
//
//Input: piles = [30,11,23,4,20], h = 5
//Output: 30
//
//
// Example 3:
//
//
//Input: piles = [30,11,23,4,20], h = 6
//Output: 23
//
//
//
// Constraints:
//
//
// 1 <= piles.length <= 10⁴
// piles.length <= h <= 10⁹
// 1 <= piles[i] <= 10⁹
//
//
// Related Topics Array Binary Search 👍 6282 👎 301


//leetcode submit region begin(Prohibit modification and deletion)
class No875 {
    /**
     * 解答成功:
     * 	执行耗时:16 ms,击败了90.58% 的Java用户
     * 	内存消耗:43.2 MB,击败了58.28% 的Java用户
     */
    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for (int p : piles) {
            max = Math.max(max, p);
        }
        int l = 1, r = max;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (check(piles, h, mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int[] piles, int h, int k) {
        int total = 0;
        for (int p : piles) {
            total += p / k;
            total += p % k == 0 ? 0 : 1;
            if (total > h) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

