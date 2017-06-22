package com.qkx.example.exercise.tree.print;

/**
 * Created by qkx on 17/6/22.
 */
public class Node<T extends Comparable<?>> {
    public Node<T> left;
    public Node<T> right;
    public T data;

    public Node(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public T getData() {
        return data;
    }
}

