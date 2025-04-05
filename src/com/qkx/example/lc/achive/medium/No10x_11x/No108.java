package com.qkx.example.lc.achive.medium.No10x_11x;

import com.qkx.example.model.TreeLinkNode;

/**
 * Created by qkx on 16/9/18.
 */
public class No108 {
    public static TreeLinkNode sortedArrayToBST(int[] nums) {



        return createNode(nums, 0, nums.length - 1);
    }

    private static TreeLinkNode createNode(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }


        int cur = start + ((end - start) >> 1);

        TreeLinkNode h = new TreeLinkNode(nums[cur]);
        h.left = createNode(nums, start, cur - 1);
        h.right = createNode(nums, cur + 1, end);

        return h;
    }
}
