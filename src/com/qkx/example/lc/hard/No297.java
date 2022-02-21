package com.qkx.example.lc.hard;

import com.qkx.example.model.TreeNode;
import com.qkx.example.utils.TreeUtil;

import java.util.LinkedList;
import java.util.List;

//Serialization is the process of converting a data structure or object into a
//sequence of bits so that it can be stored in a file or memory buffer, or
//transmitted across a network connection link to be reconstructed later in the same or
//another computer environment.
//
// Design an algorithm to serialize and deserialize a binary tree. There is no
//restriction on how your serialization/deserialization algorithm should work. You
//just need to ensure that a binary tree can be serialized to a string and this
//string can be deserialized to the original tree structure.
//
// Clarification: The input/output format is the same as how LeetCode
//serializes a binary tree. You do not necessarily need to follow this format, so please be
//creative and come up with different approaches yourself.
//
//
// Example 1:
//
//
//Input: root = [1,2,3,null,null,4,5]
//Output: [1,2,3,null,null,4,5]
//
//
// Example 2:
//
//
//Input: root = []
//Output: []
//
//
// Example 3:
//
//
//Input: root = [1]
//Output: [1]
//
//
// Example 4:
//
//
//Input: root = [1,2]
//Output: [1,2]
//
//
//
// Constraints:
//
//
// The number of nodes in the tree is in the range [0, 10⁴].
// -1000 <= Node.val <= 1000
//
// Related Topics String Tree Depth-First Search Breadth-First Search Design
//Binary Tree 👍 5127 👎 218


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */


/**
 * @author kaixin
 * @since 2021-09-23 16:57
 */
public class No297 {
    // 测试用例:[1,2,3,null,null,4,5]
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(4);
//        root.right.right = new TreeNode(5);
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        TreeUtil.printNode(root);

        Codec ser = new Codec();
        String code = ser.serialize(root);
        System.out.println(code);
        Codec deser = new Codec();
        TreeNode ans = deser.deserialize(code);
        TreeUtil.printNode(ans);
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<String> preorderCode = new LinkedList<>();
        preorderTransverse(root, preorderCode, 1);
        List<String> inorderCode = new LinkedList<>();
        inorderTransverse(root, inorderCode, 1);
        return String.join(",", preorderCode) + "|" + String.join(",", inorderCode);
    }

    private void preorderTransverse(TreeNode cur, List<String> preorderCode, int level) {
        if (cur == null) {
            return;
        }
        preorderCode.add(String.valueOf(cur.val) + "_" + level);
        preorderTransverse(cur.left, preorderCode, level + 1);
        preorderTransverse(cur.right, preorderCode, level + 1);
    }

    private void inorderTransverse(TreeNode cur, List<String> inorderCode, int level) {
        if (cur == null) {
            return;
        }
        inorderTransverse(cur.left, inorderCode, level + 1);
        inorderCode.add(String.valueOf(cur.val) + "_" + level);
        inorderTransverse(cur.right, inorderCode, level + 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] codes = data.split("\\|");
        String preorderCode = codes[0];
        String[] preArrStr = preorderCode.split(",");

        String inorderCode = codes[1];
        String[] inArrStr = inorderCode.split(",");

        return parseNode(preArrStr, 0, preArrStr.length - 1, inArrStr, 0, inArrStr.length - 1);
    }

    private TreeNode parseNode(String[] preArrStr, int preStart, int preEnd, String[] inArrStr, int inStart, int inEnd) {
        String curValue = preArrStr[preStart];
        TreeNode cur = new TreeNode(Integer.parseInt(curValue.split("_")[0]));
        if (preStart == preEnd) {
            return cur;
        }

        int inorderIndex = inStart;
        while (!inArrStr[inorderIndex].equals(curValue)) {
            inorderIndex++;
        }

        int leftSize = inorderIndex - inStart;
        if (preStart + 1 <= preStart + leftSize) {
            cur.left = parseNode(preArrStr, preStart + 1, preStart + leftSize, inArrStr, inStart, inorderIndex - 1);
        }
        if (preStart + leftSize + 1 <= preEnd) {
            cur.right = parseNode(preArrStr, preStart + leftSize + 1, preEnd, inArrStr, inorderIndex + 1, inEnd);
        }
        return cur;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
