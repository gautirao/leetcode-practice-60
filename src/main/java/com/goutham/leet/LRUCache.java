package com.goutham.leet;

import java.util.HashMap;

//1. HashMap + Doubly Linked List (Optimal O(1) Solution)
public class LRUCache {
    class Node{
        int key, value;
        Node prev,next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final HashMap<Integer,Node> cache;

    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0,0);// dummy nodes
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        if(!cache.containsKey(key)) return -1;
        Node node   = cache .get(key);
        removeNode(node);
        insertToHead(node);
        return node.value;
    }

    public void put(int key, int value){
        if(cache.containsKey(key)) {
            Node old = cache.get(key);
            old.value = value;
            removeNode(old);
            insertToHead(old);
        }else {
            if(cache.size() == capacity){
                Node lru = tail.prev;
                removeNode(lru);
                cache.remove(lru.key);
            }
            Node node = new Node(key,value);
            insertToHead(node);
            cache.put(key,node);
        }

    }

    private void insertToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
