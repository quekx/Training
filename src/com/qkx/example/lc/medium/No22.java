package com.qkx.example.lc.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kaixin
 * @since 2021-01-27 16:12
 */
//Given n pairs of parentheses, write a function to generate all combinations of
// well-formed parentheses.
//
//
// Example 1:
// Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
// Example 2:
// Input: n = 1
//Output: ["()"]
//
//
// Constraints:
//
//
// 1 <= n <= 8
//
// Related Topics String Backtracking
// 👍 6944 👎 311


//leetcode submit region begin(Prohibit modification and deletion)
public class No22 {

    public static void main(String[] args) {
        System.out.println(new No22().generateParenthesis(3));
    }

    /**
     * 解答成功:
     * 执行耗时:1 ms,击败了83.33% 的Java用户
     * 内存消耗:38.5 MB,击败了99.80% 的Java用户
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        traverse("", 0, 0, n, res);
        return res;
    }

    private void traverse(String prefix, int l, int r, int n, List<String> res) {
        if (l == n && r == n) {
            res.add(prefix);
        } else if (l == n) {
            traverse(prefix + ')', l, r + 1, n, res);
        } else {
            if (l == r) {
                traverse(prefix + '(', l + 1, r, n, res);
            } else {
                traverse(prefix + '(', l + 1, r, n, res);
                traverse(prefix + ')', l, r + 1, n, res);
            }
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
