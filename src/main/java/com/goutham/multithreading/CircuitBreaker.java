package com.goutham.multithreading;

import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CircuitBreaker {

  private enum State {
    CLOSED,
    OPEN,
    HALF_OPEN
  }

  private final int failureThreshold; // max failures before opening circuit
  private final long failureWindowMillis; // time window for counting failures
  private final long openStateTimeoutMillis; // how long to stay open before trying half-open

  private volatile State state = State.CLOSED;

  // Thread-safe failure tracking
  private final ConcurrentLinkedQueue<Long> failureTimestamps = new ConcurrentLinkedQueue<>();
  private final AtomicInteger consecutiveSuccesses = new AtomicInteger(0);

  private volatile long openStateStartTime = 0L;

  private final ReentrantLock lock = new ReentrantLock();

  public CircuitBreaker(
      int failureThreshold, long failureWindowMillis, long openStateTimeoutMillis) {
    this.failureThreshold = failureThreshold;
    this.failureWindowMillis = failureWindowMillis;
    this.openStateTimeoutMillis = openStateTimeoutMillis;
  }

  /**
   * Executes the protected call. If circuit is open, calls fallback.
   *
   * @param protectedCall The main logic that may fail.
   * @param fallbackCall The fallback logic when circuit is open.
   * @param <T> Return type.
   * @return result of protectedCall or fallbackCall.
   * @throws Exception from protectedCall if fallback is not called.
   */
  public <T> T execute(Callable<T> protectedCall, Callable<T> fallbackCall) throws Exception {
    if (state == State.OPEN) {
      if (canTryHalfOpen()) {
        transitionToHalfOpen();
      } else {
        return fallbackCall.call();
      }
    }

    try {
      T result = protectedCall.call();
      onSuccess();
      return result;
    } catch (Exception e) {
      onFailure();
      if (state == State.OPEN) {
        return fallbackCall.call();
      }
      throw e;
    }
  }

  private boolean canTryHalfOpen() {
    return (Instant.now().toEpochMilli() - openStateStartTime) >= openStateTimeoutMillis;
  }

  private void transitionToHalfOpen() {
    lock.lock();
    try {
      if (state == State.OPEN && canTryHalfOpen()) {
        state = State.HALF_OPEN;
        consecutiveSuccesses.set(0);
        failureTimestamps.clear();
        System.out.println("CircuitBreaker: Transitioning to HALF_OPEN");
      }
    } finally {
      lock.unlock();
    }
  }

  private void onSuccess() {
    if (state == State.HALF_OPEN) {
      int successCount = consecutiveSuccesses.incrementAndGet();
      if (successCount >= 1) { // success threshold to close circuit
        lock.lock();
        try {
          state = State.CLOSED;
          failureTimestamps.clear();
          System.out.println("CircuitBreaker: Transitioning to CLOSED");
        } finally {
          lock.unlock();
        }
      }
    } else if (state == State.CLOSED) {
      cleanupOldFailures();
    }
  }

  private void onFailure() {
    long now = Instant.now().toEpochMilli();
    failureTimestamps.add(now);
    cleanupOldFailures();

    if (state == State.HALF_OPEN) {
      lock.lock();
      try {
        state = State.OPEN;
        openStateStartTime = now;
        System.out.println("CircuitBreaker: Transitioning back to OPEN from HALF_OPEN");
      } finally {
        lock.unlock();
      }
    } else if (state == State.CLOSED && failureTimestamps.size() >= failureThreshold) {
      lock.lock();
      try {
        if (state == State.CLOSED) {
          state = State.OPEN;
          openStateStartTime = now;
          System.out.println("CircuitBreaker: Transitioning to OPEN");
        }
      } finally {
        lock.unlock();
      }
    }
  }

  private void cleanupOldFailures() {
    long cutoff = Instant.now().toEpochMilli() - failureWindowMillis;
    while (true) {
      Long ts = failureTimestamps.peek();
      if (ts == null || ts >= cutoff) break;
      failureTimestamps.poll();
    }
  }

  public State getState() {
    return state;
  }
}
