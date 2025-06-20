package com.goutham.windowing;

import java.time.Instant;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

// Sum of transactions in a time window
public class TransactionWindowSum {

    private record TimedPayment(Instant timestamp, double amount){}

    private final Deque<TimedPayment> payments = new ConcurrentLinkedDeque<>();
  private final long windowSeconds;

    public TransactionWindowSum(long windowSeconds) {
        this.windowSeconds = windowSeconds;
    }

    public void addTransaction(double amount){
        Instant now = Instant.now();
    payments.addLast(new TimedPayment(now,amount));
    cleanOld(now);
    }


    public synchronized double getSum(){
    System.out.println("payments size "+ payments.size());
        cleanOld(Instant.now());
        return payments.stream().mapToDouble( payment ->  payment.amount ).sum();
    }
    private synchronized void cleanOld(Instant now) {

        while ( !payments.isEmpty() && now.getEpochSecond() - payments.peekFirst().timestamp().getEpochSecond() > windowSeconds ){
            payments.removeFirst();
        }

    }
}
