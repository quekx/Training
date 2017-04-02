package com.qkx.example.model;

/**
 * Created by qkx on 16/9/19.
 */
public class TreeLinkNode {
    public int val;
    public TreeLinkNode left, right, next;

    public TreeLinkNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.format("val >> %d", val);
    }
}
