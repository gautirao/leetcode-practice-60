package com.goutham.multithreading;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class SimpleRateLimiterTest {

    @Test
    public void allowRequest() throws InterruptedException {
        int maxRequests = 5;
        int intervalSeconds = 2;
        SimpleRateLimiter rateLimiter = new SimpleRateLimiter(maxRequests,intervalSeconds);

        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<Future<Boolean>> results = new ArrayList<>();
        for (int i=0 ; i < threadCount ; i++){
            results.add(executorService.submit(rateLimiter::allowRequests));

        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        long actualRequestsAllowed = results.stream().filter(booleanFuture -> {
            try {
           return    booleanFuture.get();
            } catch (Exception e) {
                return false;
            }
        }).count();
assertEquals(5,actualRequestsAllowed,"More than allowed requests were allowed");
    }
}
