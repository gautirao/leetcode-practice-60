package com.goutham.multithreading;

// Implement a thread-safe rate limiter that allows at most N requests per second.
/*
* Method: boolean allowRequest()

The limiter must only allow up to maxTokens per time window (e.g., 1 second)

Extra requests are rejected (false)

Use Token Bucket algorithm

Must be thread-safe (used in multithreaded environments)
* */
public class RateLimiter {
  private final int maxTokens;
  private final long refillIntervalMillis ;
  private double currentTokens;
  private long lastRefillTimestamp;

    public RateLimiter(int maxTokens, long refillRatePerSecond) {
        this.maxTokens = maxTokens;
        this.refillIntervalMillis = 1000L / refillRatePerSecond;
        this.currentTokens = maxTokens;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

  public synchronized boolean allowRequest() {
      long now = System.currentTimeMillis();
      long millisSinceLast = now - lastRefillTimestamp;

      // Calculate how many new tokens we can add
      double newTokens = (double) millisSinceLast / refillIntervalMillis;
      if (newTokens > 0) {
          currentTokens = Math.min(maxTokens, currentTokens + newTokens);
          lastRefillTimestamp = now;
      }

      if (currentTokens >= 1) {
          currentTokens -= 1;
          return true;
      } else {
          return false;
      }

  }
    }
