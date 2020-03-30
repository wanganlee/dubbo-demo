package com.demo.pattern;

/**
 * Singleton.
 *
 * @author developer.wang
 * @date 2020/3/2
 */
public final class Singleton {

    private Singleton() {
    }

    private static final class Holder {
        private static final Singleton SINGLETON = new Singleton();

        private Holder() {
        }
    }

    public synchronized static Singleton getInstance() {
        return Holder.SINGLETON;
    }

}
