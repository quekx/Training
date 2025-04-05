package com.qkx.example.lc.achive.medium; /**
 * @author kaixin
 * @since 2021-09-22 18:06
 */

//Serialization is converting a data structure or object into a sequence of
//bits so that it can be stored in a file or memory buffer, or transmitted across a
//network connection link to be reconstructed later in the same or another computer
//environment.
//
// Design an algorithm to serialize and deserialize a binary search tree. There
//is no restriction on how your serialization/deserialization algorithm should
//work. You need to ensure that a binary search tree can be serialized to a string,
//and this string can be deserialized to the original tree structure.
//
// The encoded string should be as compact as possible.
//
//
// Example 1:
// Input: root = [2,1,3]
//Output: [2,1,3]
// Example 2:
// Input: root = []
//Output: []
//
//
// Constraints:
//
//
// The number of nodes in the tree is in the range [0, 10⁴].
// 0 <= Node.val <= 10⁴
// The input tree is guaranteed to be a binary search tree.
//
// Related Topics String Tree Depth-First Search Breadth-First Search Design
//Binary Search Tree Binary Tree 👍 2261 👎 109


//leetcode submit region begin(Prohibit modification and deletion)

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class No449 {
    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        String tree = ser.serialize(root);
        System.out.println("tree >> " + tree);
        TreeNode ans = deser.deserialize(tree);
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<String> codeResult = new LinkedList<>();
        preTraverse(root, codeResult);
        return String.join(",", codeResult);
    }

    private void preTraverse(TreeNode root, List<String> res) {
        if (root == null) {
            return;
        }
        res.add(String.valueOf(root.val));
        preTraverse(root.left, res);
        preTraverse(root.right, res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        String[] codeArrStr = data.split(",");
        int[] codeArr = new int[codeArrStr.length];
        for (int i = 0; i < codeArrStr.length; i++) {
            codeArr[i] = Integer.parseInt(codeArrStr[i]);
        }
        return parseNode(codeArr, 0, codeArr.length - 1);
    }

    private TreeNode parseNode(int[] codeArr, int start, int end) {
        TreeNode cur = new TreeNode(codeArr[start]);
        if (start == end) {
            return cur;
        }
        int i = start;
        while (i + 1 <= end && codeArr[i + 1] < codeArr[start]) {
            i++;
        }
        if (i != start) {
            cur.left = parseNode(codeArr, start + 1, i);
        }
        if (i != end) {
            cur.right = parseNode(codeArr, i + 1, end);
        }
        return cur;
    }
}

// Your com.qkx.example.solutions.LeetCode.medium.Codec object will be instantiated and called as such:
// com.qkx.example.solutions.LeetCode.medium.Codec ser = new com.qkx.example.solutions.LeetCode.medium.Codec();
// com.qkx.example.solutions.LeetCode.medium.Codec deser = new com.qkx.example.solutions.LeetCode.medium.Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
//leetcode submit region end(Prohibit modification and deletion)
