package com.qkx.example.solutions.LeetCode.medium.No8x_9x;//Given an integer n, generate all structurally unique BST's (binary search tree
//s) that store values 1 ... n.
//
// Example:
//
//
//Input: 3
//Output:
//[
//  [1,null,3,2],
//  [3,2,null,1],
//  [3,1,null,null,2],
//  [2,1,3],
//  [1,null,2,null,3]
//]
//Explanation:
//The above output corresponds to the 5 unique BST's shown below:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
//
//
//
// Constraints:
//
//
// 0 <= n <= 8
//
// Related Topics Dynamic Programming Tree
// 👍 2219 👎 159


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class com.qkx.example.solutions.LeetCode.medium.No8x_9x.TreeNode {
 * int val;
 * com.qkx.example.solutions.LeetCode.medium.No8x_9x.TreeNode left;
 * com.qkx.example.solutions.LeetCode.medium.No8x_9x.TreeNode right;
 * com.qkx.example.solutions.LeetCode.medium.No8x_9x.TreeNode() {}
 * com.qkx.example.solutions.LeetCode.medium.No8x_9x.TreeNode(int val) { this.val = val; }
 * com.qkx.example.solutions.LeetCode.medium.No8x_9x.TreeNode(int val, com.qkx.example.solutions.LeetCode.medium.No8x_9x.TreeNode left, com.qkx.example.solutions.LeetCode.medium.No8x_9x.TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class No95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        return genNode(1, n);
    }

    private List<TreeNode> genNode(int min, int max) {
        List<TreeNode> res = new ArrayList<>();
        if (min > max) {
            res.add(null);
            return res;
        }

        for (int i = min; i <= max; i++) {
            List<TreeNode> leftList = genNode(min, i - 1);
            List<TreeNode> rightList = genNode(i + 1, max);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = left;
                    cur.right = right;
                    res.add(cur);
                }
            }
        }
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
