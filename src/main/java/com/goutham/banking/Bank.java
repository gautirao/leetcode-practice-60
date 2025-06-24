package com.goutham.banking;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bank {
  private final Map<String, Account> accounts = new ConcurrentHashMap<>();
  private final List<Transaction> transactionLog = new CopyOnWriteArrayList<>();

  public void transfer(String fromAccountId, String toAccountId, double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Transfer amount must be positive");
    }
    Account from = getRequiredAccount(fromAccountId);
    Account to = getRequiredAccount(toAccountId);

    /* using synchronized (this) would lock the whole class meaning only one transaction can happen
       * Use account level lock , but to avoid dead lock use consistent order
       * Using synchronized(this) would lock the entire Bank object, meaning:
       Only one transfer at a time can occur, regardless of which accounts are involved.
       This kills concurrency — all transfers serialize, even if they involve totally unrelated accounts.
    💡 Example: If Alice→Bob and Charlie→Dave transfers happen at the same time, there's no reason to block one for the other.
       So, synchronized(this) is safe but not scalable.
       *
       * So Why the "Consistent Lock Order"?
       This solves the deadlock problem.
       🧵 Imagine two threads:
       Thread A wants to transfer: Alice → Bob
       Thread B wants to transfer: Bob → Alice
       If each thread:
       Locks the first account
       Then tries to lock the second
       It could result in:
       Thread A	Thread B
       locks Alice	locks Bob
       waits for Bob 🔒	waits for Alice 🔒

       → This is a classic deadlock.
               * */
    // Account firstLock = getLock(from, to)
    Account[] locks = getLock(from, to);
    Account firstLock = locks[0];
    Account secondLock = locks[1];
    synchronized (firstLock) {
      synchronized (secondLock) {
        from.withdraw(amount);
        to.deposit(amount);
        transactionLog.add(new Transaction(fromAccountId, toAccountId, amount));
      }
    }
  }

  public void addAccount(Account account) {
    accounts.put(account.getAccountId(), account);
  }

  public double getBalance(String accountId) {
    Account account = getRequiredAccount(accountId);
    synchronized (account) {
      return account.getBalance();
    }
  }

  public List<Transaction> getTransactionLog() {
    return Collections.unmodifiableList(transactionLog);
  }

  private Account[] getLock(Account from, Account to) {
    return from.getAccountId().compareTo(to.getAccountId()) > 0
        ? new Account[] {from, to}
        : new Account[] {to, from};
  }

  private Account getRequiredAccount(String fromAccountId) {
    return Optional.ofNullable(accounts.get(fromAccountId))
        .orElseThrow(
            () -> new IllegalArgumentException("No account found with id: " + fromAccountId));
  }
}
