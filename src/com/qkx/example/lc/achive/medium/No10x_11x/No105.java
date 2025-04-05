package com.qkx.example.lc.achive.medium.No10x_11x;

import com.qkx.example.model.TreeNode;

/**
 * Created by qkx on 16/9/17.
 */
public class No105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return createNode(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode createNode(int[] preorder, int[] inorder,
                                int preStart, int preEnd, int inStart, int inEnd) {

        if (preStart > preEnd) {
            return null;
        }

        int cur = preorder[preStart];
        TreeNode h = new TreeNode(cur);

        int posInInorder = findCurInInooder(inorder, inStart, inEnd, cur);
        h.left = createNode(preorder, inorder, preStart + 1, preStart + posInInorder - inStart,
                inStart, posInInorder - 1);
        h.right = createNode(preorder, inorder, preStart + posInInorder - inStart + 1, preEnd,
                posInInorder + 1, inEnd);

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
