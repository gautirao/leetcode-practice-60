package com.goutham.multithreading;

/**
 * Design and implement a bounded blocking queue in Java with the following methods:
 *
 * java
 * Copy
 * Edit
 * class BoundedBlockingQueue {
 *     public BoundedBlockingQueue(int capacity);
 *     public void enqueue(int item) throws InterruptedException;
 *     public int dequeue() throws InterruptedException;
 *     public int size();
 * }
 * enqueue(int item): Adds item to the queue. Blocks if the queue is full.
 *
 * dequeue(): Removes and returns the head element. Blocks if the queue is empty.
 *
 * size(): Returns the current number of elements in the queue.
 *
 * Use only core Java (synchronized, wait(), notifyAll()). Do not use java.util.concurrent package.
 */

import java.util.LinkedList;
import java.util.Queue;
public class BoundedBlockingQueue {

    private final Queue<Integer> queue;
    private final int capacity;

    public BoundedBlockingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void enqueue(int item) throws InterruptedException {
        while (queue.size() == capacity){
            wait();
        }
        queue.offer(item);
        notifyAll();
    }

    public synchronized int dequeue() throws InterruptedException {
        while(queue.isEmpty()){
            wait();
        }
        int result = queue.poll();
        notifyAll();
        return result;

    }

    public synchronized int size(){
        return queue.size();
    }
}
