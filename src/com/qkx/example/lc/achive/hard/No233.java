package com.qkx.example.lc.achive.hard;

/**
 * @author kaixin
 * @since 2020-12-14 15:13
 */
//Given an integer n, count the total number of digit 1 appearing in all non-neg
//ative integers less than or equal to n.
//
// Example:
//
//
//Input: 13
//Output: 6
//Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
//
// Related Topics Math
// 👍 338 👎 680


//leetcode submit region begin(Prohibit modification and deletion)
public class No233 {

    public static void main(String[] args) {
        System.out.println(new No233().countDigitOne(13));
    }

    /**
     * n = abc (a >= 1)
     * 1. a == 1
     * 1bc 分为两部分
     * 1) 0 ~ 99
     * 2) 100 ~ 1bc
     * f(1bc) = f(99) + f(bc) + (bc+1)
     *
     * 2. a >= 2
     * 1) 0 ~ 99 ==> f(99)
     * 2) 100 ~ 199 ==> f(99) + 100
     * 3) 200 ~ abc
     * ==> 200 ~ (a-1)99 + a00 ~ abc
     * ==>(a-2) * f(99) + f(bc)
     * f(abc) = f(99) + f(99) + 100 + (a-2) * f(99) + f(bc)
     *        = a * f(99) + f(bc) + 100
     *
     * n = abcd
     * 1. a == 1 : 1bcd
     * 1) 0 ~ 999 => f(999)
     * 2) 1000 ~ 1bcd => f(bcd) + (bcd+1)
     *
     * 2. a >= 2
     * 1) 0 ~ 999 => f(999)
     * 2) 1000 ~ 1999 => f(999) + 1000
     * 3) 2000 ~ abcd => (a-2) * f(999) + f(bcd)
     * f(abcd) = a * f(999) + f(bcd) + 1000
     *
     * f(9) = 1
     * 99
     * 1) 0 ~ 9
     * 2) 10 ~ 19
     * 3) 20 ~ 99
     * f(99) = 10 * f(9) + 10 = 20
     *
     * f(999) = 10 * f(99) + 100 = 300
     * f(9999) = 10 * f(999) + 1000 = 4000
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }

        // 到目前为止前几位的结果数，以bc为例
        int fbc = 0;
        // 到目前为止前几位全为99的结果数，以99为例
        // 演化过程 f(99) = 10 * f(9) + time
        int f99 = 0;
        // 倍数
        int time = 1;
        // bc代表的数
        // bc = 10 * a + bc
        int bc = 0;
        while (n != 0) {
            int a = n % 10;
            if (a == 1) {
                //  f(99) + f(bc) + (bc+1)
                fbc = f99 + fbc + (bc + 1);
            } else if (a >= 2) {
                // a * f(99) + f(bc) + 100
                fbc = a * f99 + fbc + time;
            }
            // 倍数 100，用于计算f99
            f99 = 10 * f99 + time;
            bc = time * a + bc;
            time *= 10;
            n /= 10;
        }

        return fbc;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
