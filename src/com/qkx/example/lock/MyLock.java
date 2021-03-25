package com.qkx.example.lock;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.LockSupport;

public class MyLock {
    /**
     * 用来标识获取锁状态，整型，同时可以表示获取锁次数
     */
    private volatile int state = 0;

    /**
     * 当前持有锁的线程
     */
    private Thread holdLockThread;

    /**
     * 得到魔法类UnSafe的实例
     */
    private static final Unsafe unSafe = UnsafeWrapper.getUnsafeInstanceByReflect();

    /**
     * state属性的偏移量：在类被加载后，得到state的在类对象中的偏移量位置，后面用CAS更新的时候，需要使用到
     */
    private static long stateOffset = -1;

    /**
     * 排队队列：获取锁失败的线程被阻塞后，存在队列中，方便被重新唤醒，继续进行获取锁操作
     * 这里要考虑线程安全，所以要是用线程安全的队列（不要用基于AQS实现的那些线程安全队列：例如BlockingQueue、我们是要自己写AQS逻辑）
     * ConcurrentLinkedDeque是基于CAS方式保证的线程安全
     */
    private ConcurrentLinkedDeque<Thread> queue = new ConcurrentLinkedDeque<Thread>();

    static {
        try {
            // 得到MyLock类中state属性的内存偏移量地址
            stateOffset = unSafe.objectFieldOffset(MyLock.class.getDeclaredField("state"));
            System.out.println("stateOffset >> " + stateOffset);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取锁
     */
    public void lock() {
        if (tryAcquire()) {
            // 获取锁成功,直接返回原来线程
            return;
        }
        // 得到当前用来获取锁的线程
        Thread current = Thread.currentThread();
        // 获取锁失败，将该线程放入到队列中
        queue.add(current);
        // 原来线程不能返回(自旋)，要阻塞住(利用LockSupport.park()方法,主要目的是让出CPU)
        for (; ; ) {
            // 如果队列中的第一个线程是当前线程，尝试去获取锁
            if (current == queue.peek() && tryAcquire()) {
                // 将自己从等待获取锁队列中弹出
                queue.poll();
                // 获取锁成功，退出等待
                return;
            }
            System.out.println("[" + current.getName() + "]:获取锁失败，阻塞等待前一个线程释放锁.");
            // 阻塞当前线程
            LockSupport.park(current);
        }
    }

    /**
     * 尝试去获取锁
     *
     * @return 获取锁成功true/获取锁失败false
     */
    public boolean tryAcquire() {
        // 得到当前用来获取锁的线程
        Thread current = Thread.currentThread();
        // 如果当前state还是0，未有人获取锁
        if (0 == getState()) {
            System.out.println("当前锁没有被占用，[" + current.getName() + "]尝试去获取锁");
            // 获取锁成功依靠CAS操作，将state由0加到1，加成功了，则表示获取到了锁。（这里仅演示获取锁，不演示重入锁问题）
            // 队列中已经有排队的且队列第一个元素是当前线程
            if ((queue.size() > 0 || queue.peek() == current) && this.compareAndSwapState(0, 1)) {
                // 获取锁成功后，将holdLockThread设置为当前的线程
                setHoldLockThread(current);
                System.out.println(">>> [" + current.getName() + "]:获取锁成功");
                // 直接返回
                return true;
            }
        }
        return false;
    }

    /**
     * 释放锁
     */
    public void unLock() {
        // 判断当前线程是不是获取到锁的那个线程
        if (Thread.currentThread() != holdLockThread) {
            // 如果获取锁的线程不是当前线程，抛错回去
            throw new RuntimeException("你不能释放这个锁！");
        }
        // 如果当前线程是获取到锁的线程，进行锁释放
        // 将state置位
        if (compareAndSwapState(getState(), 0)) {
            // state置位成功后，将holdLockThread清空
            setHoldLockThread(null);
            // 释放锁完毕，判断下队列汇中是否有等待的线程，取出来，唤醒它
            Thread head = queue.peek();
            if (null != head) {
                // 唤醒队列中等待获取锁的线程，尝试去获取锁
                LockSupport.unpark(head);
            }
            System.out.println("<<< [" + Thread.currentThread().getName() + "]:释放锁成功");
        }
    }

    /**
     * 利用UnSafe类的CAS命令进行对state属性的原子操作
     *
     * @param expect 预期值
     * @param target 目标值
     * @return 成功true/失败false
     */
    private boolean compareAndSwapState(int expect, int target) {
        return unSafe.compareAndSwapInt(this, stateOffset, expect, target);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getHoldLockThread() {
        return holdLockThread;
    }

    public void setHoldLockThread(Thread holdLockThread) {
        this.holdLockThread = holdLockThread;
    }
}
