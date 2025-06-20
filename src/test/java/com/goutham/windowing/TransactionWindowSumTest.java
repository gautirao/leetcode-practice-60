package com.goutham.windowing;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TransactionWindowSumTest {

  private final int  windowSeconds = 5;
  @Test
  void getSumUsingThread() throws InterruptedException {
   TransactionWindowSum windowSum = new TransactionWindowSum(windowSeconds);

   windowSum.addTransaction(1);
   windowSum.addTransaction(1);
   windowSum.addTransaction(1);
   assertEquals(windowSum.getSum(),3);
  Thread.sleep(6000l);
  windowSum.addTransaction(1);
   assertEquals(1,windowSum.getSum());

  }
  @Test
  void getSumUsingExecutor() throws InterruptedException {

      int numOfThreads = 10;
      ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);
      TransactionWindowSum windowSum = new TransactionWindowSum(windowSeconds);
      double transactionAmount = 1;

      List<Callable<Void>> callableList = IntStream.range(0, 100).mapToObj(ignore -> (Callable<Void>) () -> {
          windowSum.addTransaction(transactionAmount);
          return null;
      }).toList();

      try {
          List<Future<Void>> futures = executorService.invokeAll(callableList);
                for (Future<Void> future : futures) {
                    future.get();
                }

      } catch (InterruptedException | ExecutionException e) {
          throw new RuntimeException("Tasks execution failed with ",e);
      }finally{
          executorService.shutdown();
          executorService.awaitTermination(10, TimeUnit.SECONDS);
      }
      assertEquals(100, windowSum.getSum());
  }
}
