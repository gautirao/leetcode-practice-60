package com.goutham.multithreading;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class RateLimiterTest {
  @Test
  public void testBruteForce() throws InterruptedException {
    RateLimiter limiter = new RateLimiter(5, 5); // 5 requests per second

    for (int i = 1; i <= 7; i++) {
      System.out.println("Request " + i + ": " + limiter.allowRequest());
      Thread.sleep(10); // simulate 10ms delay
    }

    // Wait to refill tokens
    Thread.sleep(1000);

    System.out.println("Request after 1s wait: " + limiter.allowRequest());
  }

  @Test
  public void testExecutorService() throws InterruptedException {
    int maxTokens = 5;
    int threads = 10;

    RateLimiter limiter = new RateLimiter(maxTokens, maxTokens); // 5 tokens/sec
    ExecutorService executor = Executors.newFixedThreadPool(threads);

    CountDownLatch latch = new CountDownLatch(threads);
    List<Boolean> results = Collections.synchronizedList(new ArrayList<>());
    for (int i = 0; i < threads; i++) {
      executor.submit(
          () -> {
            boolean allowed = limiter.allowRequest();
            results.add(allowed);
            System.out.println(
                Thread.currentThread().getName()
                    + " request: "
                    + (allowed ? "ALLOWED" : "REJECTED"));
            latch.countDown();
          });
    }

    // Wait for all threads to finish
    latch.await();
    executor.shutdown();
    long allowedCount = results.stream().filter(r -> r).count();
    long rejectedCount = results.size() - allowedCount;
    System.out.println("Total allowed: " + allowedCount);
    System.out.println("Total rejected: " + rejectedCount);

    // ✅ Assertion-like checks
    if (allowedCount > maxTokens) {
      throw new AssertionError("RateLimiter allowed more than max tokens!");
    } else {
      System.out.println("✅ Rate limiter test passed.");
    }
  }
}
