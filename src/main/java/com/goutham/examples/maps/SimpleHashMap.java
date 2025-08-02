package com.goutham.examples.maps;

public class SimpleHashMap<K, V> {
  private Entry<K, V>[] buckets;
  private static int CAPACITY = 16;
  private static int LOAD_FACTOR = 16;
  private int size;

  public SimpleHashMap() {
    buckets = new Entry[CAPACITY];
    size = 0;
  }

  private int getBucketIndex(K key) {
    return (key == null) ? 0 : Math.abs(key.hashCode()) % buckets.length;
  }



  public void put(K key, V value) {
    int index = getBucketIndex(key);
    Entry<K,V> head = buckets[index];


    // check if an entry exists and
    while(head != null){
      if(key==null && head.key == null || (key != null && key.equals(head.key))){
        head.value = value;
        return ;
      }
      head = head.next;
    }

    Entry<K,V> newEntry = new Entry<K,V>(key,value);
    newEntry.next = buckets[index];
    buckets[index] = newEntry;
    size++;
    
    if(size/buckets.length >= LOAD_FACTOR){
      resize();
    }
   }

   public V get(K key){
    int index = getBucketIndex(key);
    Entry<K,V> head = buckets[index];
    return head.value;
   }

  private void resize() {

    Entry<K, V>[] oldBuckets = buckets;
    buckets = new Entry[2 * oldBuckets.length];
    size = 0;
    for (Entry<K,V> head : oldBuckets) {
        while(head != null){
        put(head.key, head.value);
        head = head.next;
        }
    }
  }
}
