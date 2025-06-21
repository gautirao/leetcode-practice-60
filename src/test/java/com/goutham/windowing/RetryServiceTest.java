package com.goutham.windowing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RetryServiceTest {

  public static final String SUCCESS = "Success";
  public static final int MAX_RETRIES = 3;
  public static final int INITIAL_DELAY_MILLIS = 10;
  private RetryService retryService;

  @BeforeEach
  void setUp() {
    retryService = new RetryService();
  }

  @AfterEach
  void tearDown() {
    retryService = null;
  }

  @Test
  void shouldSucceedImmediately() throws Exception {
    Supplier<String> task =
        () -> SUCCESS;
    CompletableFuture<String> future =
        retryService.executeWithRetry(task, MAX_RETRIES, INITIAL_DELAY_MILLIS);
    assertEquals(SUCCESS, future.get(15, TimeUnit.MILLISECONDS));
  }

  @Test
  void shouldRetryAndEventuallySucceed() throws Exception {

    AtomicInteger attempts = new AtomicInteger();

    Supplier<String> task =
        () -> {
          if (attempts.incrementAndGet() < 3) throw new RuntimeException();
          return SUCCESS;
        };

    CompletableFuture<String> future =
        retryService.executeWithRetry(task, MAX_RETRIES, INITIAL_DELAY_MILLIS);
    assertEquals(SUCCESS, future.get(2, TimeUnit.SECONDS));
    assertEquals(3, attempts.get());
  }

  @Test
  void shouldRetryAndEventuallyFail() {

    AtomicInteger attempts = new AtomicInteger();

    Supplier<String> task =
        () -> {
          attempts.incrementAndGet();
          throw new RuntimeException("Failing");
        };

    CompletableFuture<String> future =
        retryService.executeWithRetry(task, MAX_RETRIES, INITIAL_DELAY_MILLIS);

    ExecutionException executionException =
        assertThrows(ExecutionException.class, future::get);
    assertInstanceOf(RuntimeException.class, executionException.getCause());

    assertEquals("Failing", executionException.getCause().getMessage());
    assertEquals(3, attempts.get());
  }
}
