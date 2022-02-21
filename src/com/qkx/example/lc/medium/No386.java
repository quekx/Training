package com.qkx.example.lc.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kaixin
 * @since 2020-12-15 16:11
 */
//Given an integer n, return 1 - n in lexicographical order.
//
// For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
//
// Please optimize your algorithm to use less time and space. The input size may
// be as large as 5,000,000.
// 👍 661 👎 82


//leetcode submit region begin(Prohibit modification and deletion)
public class No386 {

    public static void main(String[] args) {
        System.out.println(new No386().lexicalOrder(192));
    }

    /**
     * 1, 10, 100, 101, ..., 109, 11, 110,
     *
     * 199 2
     * 2, 20, 200, 201, ..., 209, 21, 210,
     *
     * n = 124
     *
     * 12 120 121 122 123 124 13 14 15 16 17 18 19 2 20
     *
     * n = 169
     *
     * 168 169 17 18 19
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n);

        int x = 1;
        for (int i = 1; i <= n; i++) {
            list.add(x);
            if (x * 10 <= n) {
                x = x * 10;
            } else if (x == n) {
                x = x / 10 + 1;
                while (x % 10 == 0) {
                    x = x / 10;
                }
            } else {
                x++;
                while (x % 10 == 0) {
                    x = x / 10;
                }
            }
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

