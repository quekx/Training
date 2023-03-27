package com.qkx.example.lc;

//You are given an integer num. You can swap two digits at most once to get the
//maximum valued number.
//
// Return the maximum valued number you can get.
//
//
// Example 1:
//
//
//Input: num = 2736
//Output: 7236
//Explanation: Swap the number 2 and the number 7.
//
//
// Example 2:
//
//
//Input: num = 9973
//Output: 9973
//Explanation: No swap.
//
//
//
// Constraints:
//
//
// 0 <= num <= 10⁸
//
//
// Related Topics Math Greedy 👍 2960 👎 165


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class No670 {
    /**
     * 解答成功:
     * 	执行耗时:0 ms,击败了100.00% 的Java用户
     * 	内存消耗:39.4 MB,击败了38.94% 的Java用户
     */
    public int maximumSwap(int num) {
        ArrayList<Integer> digits = new ArrayList<>();
        int t = num;
        while (t > 0) {
            digits.add(t % 10);
            t /= 10;
        }
        int n = digits.size();
        int[] f = new int[n];
        f[0] = 0;
        for (int i = 1; i < n; i++) {
            if (digits.get(i) > digits.get(f[i - 1])) {
                f[i] = i;
            } else {
                f[i] = f[i - 1];
            }
        }
        for (int i = n - 1; i > 0; i--) {
            // i vs f[i]
            if (digits.get(i) < digits.get(f[i])) {
                num += (-digits.get(i) + digits.get(f[i])) * pow(i);
                num -= (-digits.get(i) + digits.get(f[i])) * pow(f[i]);
                return num;
            }
        }
        return num;
    }

    private int pow(int n) {
        int ans = 1;
        while (n > 0) {
            ans *= 10;
            n--;
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

