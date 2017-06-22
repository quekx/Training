package com.qkx.example.model;

import com.qkx.example.exercise.tree.print.Node;

/**
 * Created by qkx on 16/6/12.
 */
public class TreeNode extends Node<Integer>{
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        super(x);
        val = x;
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
