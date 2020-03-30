package com.demo.concurrent;

/**
 * @author developer.wang
 * @date 2020/3/17
 */
public class SynchronizedDemo {

    public void method() {
        synchronized (this) {
            System.out.println("synchronized code");
        }
    }
}
