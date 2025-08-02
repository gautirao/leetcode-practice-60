package com.goutham.datastructures.heaps;

import java.util.PriorityQueue;

public class MinHeapDemo {
  public static void main(String[] args) {
    MinHeap heap = new MinHeap();
    heap.insert(5);
    heap.insert(3);
    heap.insert(8);
    heap.insert(1);

    heap.printHeap(); // Shows internal array-based heap structure

    //        System.out.println(heap.peek()); // 1
    //        System.out.println(heap.poll()); // 1
    //        System.out.println(heap.poll()); // 3
    //        heap.printHeap(); // Updated heap
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    minHeap.add(5);
    minHeap.add(3);
    minHeap.add(8);
    minHeap.add(1);
    System.out.println(minHeap);
  }
}
