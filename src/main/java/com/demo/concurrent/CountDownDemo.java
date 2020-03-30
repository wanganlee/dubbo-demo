package com.demo.concurrent;

import java.util.concurrent.*;

/**
 * @author developer.wang
 * @date 2020/3/7
 */
public class CountDownDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = new ThreadPoolExecutor(1, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Worker worker = new Worker("A", countDownLatch);
        Worker worker2 = new Worker("B", countDownLatch);
        Worker worker3 = new Worker("C", countDownLatch);

        Boss boss = new Boss(countDownLatch);

        pool.submit(worker);
        pool.submit(worker3);
        pool.submit(worker2);
        pool.submit(boss);

        TimeUnit.SECONDS.sleep(10);

        pool.shutdown();
    }

    static class Boss implements Runnable {

        private final CountDownLatch countDownLatch;

        public Boss(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("boss is waiting for work to be done.");
            try {
                this.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("boss is going to check work.");
        }
    }

    static class Worker implements Runnable {

        private final String name;
        private final CountDownLatch countDownLatch;

        public Worker(String name, CountDownLatch countDownLatch) {
            this.name = name;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            this.doWork();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s wok is done.", this.name));
            this.countDownLatch.countDown();
        }

        private void doWork() {
            System.out.println(String.format("%s is working...", this.name));
        }
    }
}
