package com.qkx.example;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(Integer.toBinaryString(39));

    }

    private void test() {
        Queue<A> queue = new LinkedList<>();
        queue.add(new A());
    }

    private class A {}
}
