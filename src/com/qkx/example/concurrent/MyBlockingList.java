package com.qkx.example.concurrent;

import com.qkx.example.model.ListNode;

/**
 * Created by qkx on 17/3/22.
 */
public class MyBlockingList {
    private int maxSize;

    private ListNode head;
    private volatile int size = 0;

    public MyBlockingList(int maxSize) {
        this.maxSize = maxSize;
    }

    public void add(int x) {
        synchronized (this) {
            while (size == maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ListNode node = new ListNode(x);
            node.next = head;
            head = node;
            size++;
            System.out.println("add size :: " + size);
            notify();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void remove() {
        synchronized (this) {
            while (size == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            head = head.next;
            size--;
            System.out.println("remove size :: " + size);
            notify();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
