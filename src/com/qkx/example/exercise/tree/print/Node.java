package com.qkx.example.exercise.tree.print;

/**
 * Created by qkx on 17/6/22.
 */
public class Node<T extends Comparable<?>> {
    Node<T> left, right;
    T data;

    public Node(T data) {
        this.data = data;
    }
}

