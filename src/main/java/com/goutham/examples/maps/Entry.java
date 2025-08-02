package com.goutham.examples.maps;

public class Entry<K,V> {
    K key;
    V value;
    Entry<K,V> next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
