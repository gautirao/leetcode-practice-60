package com.goutham.multithreading;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeDataStructures {
  // 1. ConcurrentHashMap: used as a cache
  static final Map<String, String> cache = new ConcurrentHashMap<>();
  // 2. CopyOnWriteArrayList: event listeners
  static final List<String> eventListeners = new CopyOnWriteArrayList<>();
  static final Queue<String> logBuffer = new ConcurrentLinkedQueue<>();
  static final BlockingQueue<String> jobQueue = new LinkedBlockingDeque<>();

  // leaderboard for sorted scores
  static final ConcurrentSkipListMap<Integer, String> leaderBoard = new ConcurrentSkipListMap<>();

  // undo/redo stack
  static final Deque<String> undoStack = new ConcurrentLinkedDeque<>();

  static final List<String> syncedList = Collections.synchronizedList(new ArrayList<>());

  // reentrant lock for fine grained locking
  static ReentrantLock customLock = new ReentrantLock();
  static List<String> customLockedList = new ArrayList<>();

  public static void main(String[] args) throws InterruptedException {
    // 1. ConncurrentHashMap
    cache.put("hello", "world");
    System.out.printf("Hello, %s", cache.get("hello"));

    eventListeners.add("ListenerA");
    for (String listener : eventListeners) {
      System.out.println("NOtifying :" + listener);
    }

    // 3 linked Queue
    logBuffer.add("Log1");
    logBuffer.add("Log2");
    System.out.println("Logs: " + logBuffer.poll());

    // blockiing queue
    new Thread(
            () -> {
              try {
                System.out.println("Consumer Recieved : " + jobQueue.take());
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            })
        .start();
    jobQueue.put("Job1");
    jobQueue.put("Job2");

    // ConcurrentSkipListMap sorts based on key, default is ascending
    leaderBoard.put(900, "player9");
    leaderBoard.put(300, "player3");
    leaderBoard.put(800, "player8");
    leaderBoard.put(100, "player1");
    System.out.println("Top Score:" + leaderBoard.lastEntry());

    // Synchronized list
    syncedList.add("Hello");
    syncedList.add("Hello2");
    syncedList.add("Hello23");
    synchronized (syncedList) {
      for (String str : syncedList) {
        System.out.println("Sync list item : " + str);
      }
    }

    // stack
    undoStack.push("Action1");
    undoStack.push("Action2");
    System.out.println("Undo: " + undoStack.pop());
    /// custom lock usage
    new Thread(
            () -> {
              customLock.lock();
              try {
                customLockedList.add("Thread safe item");
                System.out.println("Added thread safe item");
              } finally {
                customLock.unlock();
              }
            })
        .start();

    Thread.sleep(500);
  }
}
