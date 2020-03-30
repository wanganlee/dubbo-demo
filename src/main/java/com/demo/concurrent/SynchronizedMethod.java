package com.demo.concurrent;

/**
 * @author developer.wang
 * @date 2020/3/17
 */
public class SynchronizedMethod {
    
    public synchronized void method() {
        System.out.println("this is synchronized method.");
    }
}
