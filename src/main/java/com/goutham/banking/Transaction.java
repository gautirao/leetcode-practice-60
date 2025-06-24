package com.goutham.banking;

import java.time.Instant;

public class Transaction {
  private final String fromAccountId;
  private final String toAccountId;
  private final double amount;
  private final Instant timestamp;

  public Transaction(String fromAccountId, String toAccountId, double amount) {
    this.fromAccountId = fromAccountId;
    this.toAccountId = toAccountId;
    this.amount = amount;
    this.timestamp = Instant.now();
  }

  public String getFromAccountId() {
    return fromAccountId;
  }

  public String getToAccountId() {
    return toAccountId;
  }

  public double getAmount() {
    return amount;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s -> %s: $%.2f", timestamp, fromAccountId, toAccountId, amount);
  }
}
