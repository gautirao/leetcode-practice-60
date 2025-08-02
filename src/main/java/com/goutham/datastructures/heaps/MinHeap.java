package com.goutham.datastructures.heaps;

import java.util.ArrayList;

public class MinHeap {
  private ArrayList<Integer> heap = new ArrayList<>();

  public void insert(int val) {
    heap.add(val);
    heapifyUp(heap.size() - 1);
  }

  public int peek() {
    if (heap.isEmpty()) throw new IllegalStateException("Heap is empty");
    return heap.get(0);
  }

  public int poll() {
    if (heap.isEmpty()) throw new IllegalStateException("Heap is empty");

    int min = heap.get(0);
    int last = heap.remove(heap.size() - 1);
    if (!heap.isEmpty()) {
      heap.set(0, last);
      heapifyDown(0);
    }
    return min;
  }

  private void heapifyUp(int index) {
    while (index > 0) {
      int parent = (index - 1) / 2;
      if (heap.get(index) < heap.get(parent)) {
        swap(index, parent);
        index = parent;
      } else break;
    }
  }

  private void heapifyDown(int index) {
    int size = heap.size();
    while (index < size) {
      int left = 2 * index + 1;
      int right = 2 * index + 2;
      int smallest = index;

      if (left < size && heap.get(left) < heap.get(smallest)) {
        smallest = left;
      }

      if (right < size && heap.get(right) < heap.get(smallest)) {
        smallest = right;
      }

      if (smallest == index) break;

      swap(index, smallest);
      index = smallest;
    }
  }

  private void swap(int i, int j) {
    int tmp = heap.get(i);
    heap.set(i, heap.get(j));
    heap.set(j, tmp);
  }

  public boolean isEmpty() {
    return heap.isEmpty();
  }

  public void printHeap() {
    System.out.println(heap);
  }
}
