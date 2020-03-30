package com.demo.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author developer.wang
 * @date 2020/3/19
 */
public class DemoLock extends AbstractQueuedSynchronizer {


    @Override
    protected boolean tryAcquire(int arg) {

        // getState()


        return super.tryAcquire(arg);
    }
}
