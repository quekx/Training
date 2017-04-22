package com.qkx.example.solutions.LeetCode.medium.No10x_11x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/17.
 */
public class No106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return createNode(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode createNode(int[] inorder, int[] postorder,
                                  int inStart, int inEnd, int postStart, int postEnd) {

        if (postStart > postEnd) {
            return null;
        }

        int cur = postorder[postEnd];
        int posInInorder = findCurInInooder(inorder, inStart, inEnd, cur);

        TreeNode h = new TreeNode(cur);
        h.left = createNode(inorder, postorder, inStart, posInInorder - 1,
                postStart, posInInorder - 1 - inStart + postStart);

        h.right = createNode(inorder, postorder, posInInorder + 1, inEnd,
                posInInorder - inStart + postStart, postEnd - 1);

        return h;
    }

    private int findCurInInooder(int[] inorder, int inStart, int inEnd, int cur) {
        for (int i = inStart; i <= inEnd ; i++) {
            if (inorder[i] == cur) {
                return i;
            }
        }
        return -1;
    }
}
