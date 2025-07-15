package com.goutham.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class RaceCoordinator {
  private final CyclicBarrier barrier;

  public RaceCoordinator(int runnerCount, Runnable onAllReady) {
    this.barrier = new CyclicBarrier(runnerCount, onAllReady);
  }

  public Runnable createRunner(String name, CountDownLatch startedSignal) {
    return () -> {
      try {
        System.out.println(name + " is ready");
        barrier.await(); // Wait for others
        System.out.println(name + " starts running");
        startedSignal.countDown(); // signal after barrier passes
      } catch (InterruptedException | BrokenBarrierException e) {
        Thread.currentThread().interrupt();
      }
    };
  }
}
