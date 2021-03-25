package com.qkx.example.exercise.tree;

import com.qkx.example.model.TreeNode;

import java.util.Stack;

/**
 * Created by qkx on 16/6/12.
 */
public class MyTreeTraversal {

    // 中序遍历
    public static void inorderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode top = stack.pop();
                // 输出
//                System.out.println(top.val);
                System.out.print((char) top.val + " ");
                p = top.right;
            }
        }
        System.out.println();
    }

    // 前序遍历
    public static void preorderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                // 输出
                System.out.print((char) p.val + " ");
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop().right;
            }
        }
        System.out.println();
    }

    public static void preorderTraverse2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode p = stack.pop();
            System.out.print((char) p.val + " ");
            if (p.right != null) {
                stack.push(p.right);
            }
            if (p.left != null) {
                stack.push(p.left);
            }
        }
        System.out.println();
    }

    // 后续
    public static void postorderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = root;
        TreeNode pre = null;

        while (!stack.isEmpty()) {
//            if (cur.left == pre || cur.right == pre) {
//                // 输出
//                System.out.print((char) cur.val + " ");
//                pre = cur;
//                stack.pop();
//                cur = stack.peek();
//            } else {
//
//            }
            if (cur.left == pre || cur.right == pre || (cur.left == null && cur.right == null)) {
                // 输出
                System.out.print((char) cur.val + " ");
                pre = cur;
                stack.pop();
                if (!stack.empty()) {
                    cur = stack.peek();
                }
            } else {
                if (cur.left != null) {
                    if (cur.right != null) {
                        stack.push(cur.right);
                        stack.push(cur.left);
                    } else {
                        stack.push(cur.left);
                    }
                    cur = cur.left;
                } else {
                    stack.push(cur.right);
                    cur = cur.right;
                }

            }

//            if (cur.left == null && cur.right == null) {
//                // 输出
//                System.out.print((char) cur.val + " ");
//                pre = cur;
//                stack.pop();
//                cur = stack.peek();
//            } else {
//                if (cur.left == pre || cur.right == pre) {
//                    // 输出
//                    System.out.print((char) cur.val + " ");
//                    pre = cur;
//                    stack.pop();
//                    cur = stack.peek();
//                } else {
//                    if (cur.right != null && cur.left != null) {
//                        stack.push(cur.right);
//                        stack.push(cur.left);
//                        cur = cur.left;
//                    } else if (cur.right != null && cur.left == null) {
//                        stack.push(cur.right);
//                        cur = cur.right;
//                    } else {
//                        stack.push(cur.left);
//                        cur = cur.left;
//                    }
//                }
//            }
        }

    }
}
