package com.qkx.example.concurrent;

import java.util.List;

/**
 * Created by qkx on 17/3/22.
 */
public class MyConsumer implements Runnable {
    private List rep;

    public MyConsumer(List rep) {
        this.rep = rep;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (rep) {
                while (rep.isEmpty()) {
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
//                System.out.println("消费之前 :: " + rep.size());
                rep.remove(0);
                System.out.println(Thread.currentThread().getName() + " 消费之后 :: " + rep.size() + '\n');
//                rep.notifyAll();
                rep.notify();
            }
        }
    }
}
