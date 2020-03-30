package com.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 *
 * <p>
 * 初始时，线程1获得锁1，线程2获得锁2；
 * 然后线程1等待线程2释放锁2，线程2等待线程1释放锁1，两者互相等待，各不相让，最终形成死锁。
 * <p>
 * <p>
 * 那么，如何避免和解决死锁问题呢？
 * 1、按顺序加锁
 * 上个例子线程间加锁的顺序各不一致，导致死锁，如果每个线程都按同一个的加锁顺序这样就不会出现死锁。
 * 2、获取锁时限
 * 每个获取锁的时候加上个时限，如果超过某个时间就放弃获取锁之类的。
 * 3、死锁检测
 * 按线程间获取锁的关系检测线程间是否发生死锁，如果发生死锁就执行一定的策略，如终端线程或回滚操作等。
 * </p>
 *
 * @author developer.wang
 * @date 2020/3/7
 */
public class DeadLock {

    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (LOCK1) {
                System.out.println("thread1 get lock1");
                try {
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK2) {
                    System.out.println("thread1 get lock2");
                }
            }
            System.out.println("thread1 end");
        }).start();

        new Thread(() -> {
            synchronized (LOCK2) {
                System.out.println("thread2 get lock2");
                try {
                    TimeUnit.SECONDS.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK1) {
                    System.out.println("thread2 get lock1");
                }
            }
            System.out.println("thread2 end");
        }).start();
    }
}
