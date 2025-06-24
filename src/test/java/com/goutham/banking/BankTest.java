package com.goutham.banking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankTest {

  private Bank bank;

  @BeforeEach
  void setUp() {
    bank = new Bank();
    bank.addAccount(new Account("A1", 1000));
    bank.addAccount(new Account("A2", 500));
  }

  @Test
  void shouldTransferSuccessfully() {
    bank.transfer("A1", "A2", 200);
    assertEquals(800, bank.getBalance("A1"));
    assertEquals(700, bank.getBalance("A2"));
    assertEquals(1, bank.getTransactionLog().size());
  }

  @Test
  void testInsufficientFunds() {
    assertThrows(IllegalStateException.class, () -> bank.transfer("A1", "A2", 100000));
  }

  @Test
  void testInvalidAccount() {
    assertThrows(IllegalArgumentException.class, () -> bank.transfer("A3", "A2", 100000));
  }

  @Test
  void testNegativeAmount() {
    assertThrows(IllegalArgumentException.class, () -> bank.transfer("A3", "A2", -10));
  }

  @Test
  void testTransactionLogEntry() {
    bank.transfer("A1", "A2", 100);
    Transaction t = bank.getTransactionLog().get(0);
    assertEquals("A1", t.getFromAccountId());
    assertEquals("A2", t.getToAccountId());
    assertEquals(100, t.getAmount());
    assertNotNull(t.getTimestamp());
  }

  @Test
  void testConcurrentTransfers() throws InterruptedException {

    int numOfThreads = 100;
    CountDownLatch countDownLatch = new CountDownLatch(numOfThreads);
    ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);
    IntStream.range(0, numOfThreads)
        .forEach(
            i -> {
              try {
                bank.transfer("A1", "A2", 5);
                bank.transfer("A2", "A1", 5);
              } finally {
                countDownLatch.countDown();
              }
            });

    countDownLatch.await();
    executorService.shutdown();

    double totalBalance = bank.getBalance("A1") + bank.getBalance("A2");
    assertEquals(1500, totalBalance, 0.0001);
  }
}
