package com.qkx.example.lock;

public class MyLockTest {
    /**
     * 自定义的锁对象
     */
    static MyLock myLock = new MyLock();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread("线程" + i) {
                @Override
                public void run() {
                    // 获取锁
                    myLock.lock();
                    try {
                        // 线程doSomething
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // 有异常，释放锁
                        myLock.unLock();
                        e.printStackTrace();
                    }
                    // 正常释放锁
                    myLock.unLock();
                }
            };
            thread.start();
        }
    }
}
