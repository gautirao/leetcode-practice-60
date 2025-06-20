package com.goutham.multithreading;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ThreadSafeLoggerTest {
  @Test
  public void testBruteForce() throws InterruptedException {
    ThreadSafeLogger logger = new ThreadSafeLogger(10); // 10 seconds

    assertTrue(logger.shouldLog("Hello")); // true
    Thread.sleep(2000);
    assertFalse(logger.shouldLog("Hello")); // false
    Thread.sleep(9000);
    assertTrue(logger.shouldLog("Hello")); // true (after 11s total)

    assertTrue(logger.shouldLog("World")); // true (new message)
    assertFalse(logger.shouldLog("World")); // false
  }

  @Test
  public void testRateLimitedLogger() throws InterruptedException {
    int threadCount = 10;
    final String message = "log message";
    ThreadSafeLogger logger = new ThreadSafeLogger(10); // 10 seconds

    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    CountDownLatch countDownLatch = new CountDownLatch(10);
    AtomicInteger allowedCount = new AtomicInteger(0);
    for (int i = 0; i < threadCount; i++) {
      executorService.submit(
          () -> {
            if (logger.shouldLog(message)) {
              allowedCount.incrementAndGet();
              System.out.println(Thread.currentThread().getName() + ": ALLOWED");
            } else {
              System.out.println(Thread.currentThread().getName() + ": REJECTED");
            }
            countDownLatch.countDown();
          });
    }
    countDownLatch.await();
    executorService.shutdown();
    assertEquals(1, allowedCount.get());
  }
}
