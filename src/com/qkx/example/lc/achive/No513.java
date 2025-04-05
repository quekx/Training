package com.qkx.example.lc.achive;

import com.qkx.example.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class No513 {
    /**
     * 解答成功:
     * 	执行耗时:1 ms,击败了70.21% 的Java用户
     * 	内存消耗:42 MB,击败了58.21% 的Java用户
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode ans = root;
        while (!queue.isEmpty()) {
            ans = queue.peek();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return ans.val;
    }
}
