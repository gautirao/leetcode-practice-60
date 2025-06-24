package com.goutham.banking;

public class Account {
  private final String accountId;
  private double balance;

  public Account(String accountId, double initialBalance) {
    this.accountId = accountId;
    if (initialBalance < 0) throw new IllegalArgumentException("Balance cannot be negative");
    this.balance = initialBalance;
  }

  public String getAccountId() {
    return accountId;
  }

  public double getBalance() {
    return balance;
  }

  public void deposit(double amount) {
    if (amount <= 0) throw new IllegalArgumentException("amount should be greater than zero ");
    balance += amount;
  }

  public void withdraw(double amount) {
    if (amount <= 0) throw new IllegalArgumentException("amount should be greater than zero ");
    if (balance < amount) throw new IllegalStateException("insufficient funds");
    balance -= amount;
  }
}
