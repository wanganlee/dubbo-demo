package com.demo.pattern;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author developer.wang
 * @date 2020/3/2
 */
public class SingletonTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        System.out.println(Singleton.getInstance());

        List<CompletableFuture> futures = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                Singleton instance = Singleton.getInstance();
                System.out.println(String.format("this is %s time. Instance: %s", finalI, instance));
            });
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).get();
    }
}
