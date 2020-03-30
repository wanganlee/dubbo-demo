package com.demo.net;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ExecutorService for Socket Demo.
 *
 * @author developer.wang
 * @date 2020/3/22
 */
public class SocketExecutors {

    public static ExecutorService serverExecutors() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(50,
                50,
                2000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadFactoryBuilder().setNameFormat("server-%d").setDaemon(false).build(),
                new ThreadPoolExecutor.AbortPolicy());

        return TtlExecutors.getTtlExecutorService(executor);
    }

    public static ExecutorService clientExecutors() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(50,
                50,
                3000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(20),
                new ThreadFactoryBuilder().setNameFormat("client-%d").setDaemon(false).build(),
                new ThreadPoolExecutor.AbortPolicy());
        return TtlExecutors.getTtlExecutorService(executor);
    }
}
