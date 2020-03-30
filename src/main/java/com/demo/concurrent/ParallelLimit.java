package com.demo.concurrent;

import java.util.concurrent.*;

/**
 * @author developer.wang
 * @date 2020/3/7
 */
public class ParallelLimit {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = new ThreadPoolExecutor(1, 200, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200, true), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("parallel-limit-%d");
                thread.setDaemon(false);
                return thread;
            }
        });

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            CountRunnable runnable = new CountRunnable(countDownLatch);
            pool.execute(runnable);
        }
        countDownLatch.await();
        System.out.println("concurrency counts = " + (100 - countDownLatch.getCount()));

    }

    static class CountRunnable implements Runnable {

        private final CountDownLatch countDownLatch;

        CountRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                synchronized (this.countDownLatch) {
                    System.out.println("thread counts(before countDown) = " + this.countDownLatch.getCount());
                    this.countDownLatch.countDown();
                    System.out.println("thread counts(after countDown) = " + this.countDownLatch.getCount());
                }
                // this.countDownLatch.await();
                // System.out.println("concurrency counts = " + (100 - this.countDownLatch.getCount()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
