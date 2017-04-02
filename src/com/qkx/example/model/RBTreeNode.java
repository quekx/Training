package com.qkx.example.model;

/**
 * Created by qkx on 17/3/31.
 */
public class RBTreeNode {
    public int val;
    public Color color = Color.BLACK;
    public RBTreeNode parent;
    public RBTreeNode left;
    public RBTreeNode right;

    public enum Color {
        RED,
        BLACK
    }

    public RBTreeNode(int val) {
        this.val = val;
    }

    public RBTreeNode(int val, RBTreeNode left, RBTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
