package com.goutham.datastructures.queues;

/*
 * Queue implementation using array */
public class QueueArray {

  private int[] arr;
  private int front, rear, capacity, count;

  public QueueArray(int size) {
    arr = new int[size];
    capacity = size;
    front = 0;
    rear = -1;
    count = 0;
  }

  /* Tips:
  * The modulo operation (a % b) always gives you the remainder after dividing a by b.
  If a is less than b, the result will always be a (because b doesn't fit into a even once).
  If a is equal to b, the result will be 0 (because b fits exactly once, with no remainder).
  If a is greater than b, the result is whatever is left after subtracting as many full bs as possible from a.
  *
  * */
  public static void main(String[] args) {
    QueueArray q = new QueueArray(4);
    q.enqueue(10);
    q.enqueue(4);
    q.enqueue(2);
    q.enqueue(9);

    System.out.println(q.dequeue());
    System.out.println(q.dequeue());
  }

  public void enqueue(int item) {
    if (isFull()) {
      System.out.println("Queue is full ");
      return;
    }
    rear = (rear + 1) % capacity;
    arr[rear] = item;
    count++;
  }

  /*
      Example
  Suppose you have a circular queue of size 5 (capacity = 5) and your array indices go from 0 to 4.
  Let's say front = 4 (the last position in the array).
  When you dequeue an element, you want to move front to the next position.
  Normally, front + 1 would be 5, which is out of bounds.
  Using (front + 1) % capacity gives (4 + 1) % 5 = 0, so front wraps around to the start of the array.

  Step-by-step:
  front = 2, after dequeue: (2 + 1) % 5 = 3
  front = 3, after dequeue: (3 + 1) % 5 = 4
  front = 4, after dequeue: (4 + 1) % 5 = 0 (wraps to start)

      * */
  public int dequeue() {
    if (isQueueEmpty()) {
      System.out.println("Queue is empty!");
      return -1;
    }
    int item = arr[front];
    front = (front + 1) % capacity;
    count--;
    return item;
  }

  private boolean isQueueEmpty() {
    return count == 0;
  }

  private boolean isFull() {
    return count == capacity;
  }
}
