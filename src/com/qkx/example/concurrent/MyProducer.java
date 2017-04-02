package com.qkx.example.concurrent;

import java.util.List;

/**
 * Created by qkx on 17/3/22.
 */
public class MyProducer implements Runnable {
    private List rep;
    private int maxSize;

    public MyProducer(List rep, int maxSize) {
        this.rep = rep;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (rep) {
                while (rep.size() == maxSize) {
                    try {
                        rep.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("生产之前 :: " + rep.size());
                rep.add(new Object());
                System.out.println(Thread.currentThread().getName() + " 生产之后 :: " + rep.size() + '\n');
//                rep.notifyAll();
                rep.notify();
            }
        }
    }
}
