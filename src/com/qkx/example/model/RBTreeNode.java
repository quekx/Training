package com.qkx.example.model;

import com.qkx.example.exercise.tree.print.Node;

/**
 * Created by qkx on 17/3/31.
 */
public class RBTreeNode extends Node<Integer> {
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
        super(val);
        this.val = val;
    }

    public RBTreeNode(int val, RBTreeNode left, RBTreeNode right) {
        this(val);
        this.left = left;
        this.right = right;
    }

    @Override
    public Node<Integer> getLeft() {
        return this.left;
    }

    @Override
    public Node<Integer> getRight() {
        return this.right;
    }
}
