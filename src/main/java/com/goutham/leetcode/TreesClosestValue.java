package com.goutham.leetcode;

import java.util.concurrent.CyclicBarrier;

// Problem Statement (Summary)
// You need to write a function that:
//
// Takes in:
//
// A Binary Search Tree (BST)
//
// A target integer value
//
// Returns:
//
// The value in the BST that is closest to the target.
//
// 📌 You can assume only one closest value will exist.
//
// 🔍 BST Rules Reminder:
// Each node contains:
//
// value
//
// left child (values < node)
//
// right child (values ≥ node)
//
// Children nodes are either:
//
// Valid BST nodes
//
// Or null
//
// 🌲 Sample BST:
// markdown
// Copy
// Edit
//        10
//       /  \
//      5    15
//     / \   / \
//    2   5 13 22
//   /         \
//  1           14
// Target: 12
//
// ✅ Output:
// Copy
// Edit
// 13
// Why? Because:
//
// Closest values to 12 are 10, 13, and 14
//
// Difference from 12: |10 - 12| = 2, |13 - 12| = 1, |14 - 12| = 2
//
// So 13 is the closest.
public class TreesClosestValue {


//    public static int findClosestValueInBst(TreeNode tree, int target){
//
//    }

//    public static void main(String[] args) {
//        java.util.concurrent.BlockingQueue<Integer> queue = new java.util.concurrent.LinkedBlockingQueue<>(5);
//
//        Runnable producer = () -> {
//            try {
//                for (int i = 0; i < 10; i++) {
//                    queue.put(i);
//                    System.out.println("Produced: " + i);
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        };
//
//        Runnable consumer = () -> {
//            try {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println("Consumed: " + queue.take());
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        };
//
//        new Thread(producer).start();
//        new Thread(consumer).start();
//    }
//public static void main(String[] args) {
//    Semaphore semaphore = new Semaphore(2);
//    Runnable task = () -> {
//        try {
//            semaphore.acquire();
//            System.out.println(Thread.currentThread().getName() + " acquired");
//            Thread.sleep(1000);
//        } catch (InterruptedException ignored) {}
//        finally { semaphore.release(); }
//    };
//    new Thread(task).start();
//    new Thread(task).start();
//    new Thread(task).start();
//}
public static void main(String[] args) {
    CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All reached barrier!"));
    Runnable task = () -> {
        try {
            System.out.println(Thread.currentThread().getName() + " waiting");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " running");

        } catch (Exception ignored) {}
    };
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
}
}
