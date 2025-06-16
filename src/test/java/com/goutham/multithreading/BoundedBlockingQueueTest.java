package com.goutham.multithreading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BoundedBlockingQueueTest {

  private final BoundedBlockingQueue bbq = new BoundedBlockingQueue(2);

  private Runnable producer;
  private Runnable consumer;

  @BeforeEach
  public void init() {
    producer =
        () -> {
          try {
            for (int i = 1; i <= 5; i++) {
              bbq.enqueue(i);
              System.out.println("Produced: " + i);
            }

          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        };
    consumer =
        () -> {
          try {
            for (int i = 1; i <= 5; i++) {
              int item = bbq.dequeue();
              System.out.println("Consumed: " + i);
            }
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        };
  }

  @Test
  public void testImplementation() {

    new Thread(producer).start();
    new Thread(consumer).start();
  }

  @Test
  public void testThreadPoolImplementation() throws InterruptedException {
    final ExecutorService executorService = Executors.newFixedThreadPool(4);
    final CountDownLatch countDownLatch = new CountDownLatch(2);
    List<Integer> consumedItems = Collections.synchronizedList(new ArrayList<>());

    Runnable producer =
        () -> {
          try {
            for (int i = 1; i <= 5; i++) {
              bbq.enqueue(i);
              System.out.println("Produced: " + i);
            }

          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          } finally {
            // use latch to notify that process is finished
            countDownLatch.countDown();
          }
        };

    Runnable consumer =
        () -> {
          try {
            for (int i = 1; i <= 5; i++) {
              int item = bbq.dequeue();
              consumedItems.add(item);
              System.out.println("Consumed: " + i);
            }
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          } finally {
            // use latch to notify that process is finished
            countDownLatch.countDown();
          }
        };

    executorService.execute(producer);
    executorService.execute(consumer);

    countDownLatch.await(); // waits till the latch has had two notifications
    executorService.shutdown();
    assert bbq.size() == 0 : "Queue was not empty";

    List<Integer> expectedItems = List.of(1, 2, 3, 4, 5);
    assert consumedItems.containsAll(expectedItems) : "Not all items consumed correctly";
  }
}
