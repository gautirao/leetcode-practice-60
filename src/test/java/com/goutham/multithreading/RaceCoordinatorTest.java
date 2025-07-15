package com.goutham.multithreading;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;

class RaceCoordinatorTest {

  @Test
  void allRunnersShouldStartTogetherAfterAllAreReady() throws InterruptedException {
    int runners = 3;
    CountDownLatch raceStarted = new CountDownLatch(runners);
    AtomicBoolean barrierTriggered = new AtomicBoolean(false);

    RaceCoordinator coordinator =
        new RaceCoordinator(
            runners,
            () -> {
              System.out.println("Barrier triggered: all runners are ready.");
              barrierTriggered.set(true);
            });

    ExecutorService executor = Executors.newFixedThreadPool(runners);

    for (int i = 1; i <= runners; i++) {
      executor.submit(coordinator.createRunner("Runner-" + i, raceStarted));
    }

    // Wait for all runners to pass the barrier
    boolean allStarted = raceStarted.await(2, TimeUnit.SECONDS);

    executor.shutdownNow();

    assertTrue(barrierTriggered.get(), "Barrier action should have triggered");
    assertTrue(allStarted, "All runners should have started after barrier");
  }
}
