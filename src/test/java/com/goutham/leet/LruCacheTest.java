package com.goutham.leet;
// LRUCacheTest.java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {

    @Test
    void testBasicPutAndGet() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1)); // returns 1
        cache.put(3, 3); // evicts key 2
        assertEquals(-1, cache.get(2)); // returns -1 (not found)
        cache.put(4, 4); // evicts key 1
        assertEquals(-1, cache.get(1)); // returns -1 (not found)
        assertEquals(3, cache.get(3)); // returns 3
        assertEquals(4, cache.get(4)); // returns 4
    }

    @Test
    void testUpdateValue() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(1, 10); // update value
        assertEquals(10, cache.get(1));
        cache.put(3, 3); // evicts key 2
        assertEquals(-1, cache.get(2));
    }

    @Test
    void testEvictionOrder() {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1); // access 1, so 2 is now LRU
        cache.put(4, 4); // evicts key 2
        assertEquals(-1, cache.get(2));
        assertEquals(1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    void testCapacityOne() {
        LRUCache cache = new LRUCache(1);
        cache.put(1, 1);
        assertEquals(1, cache.get(1));
        cache.put(2, 2); // evicts 1
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
    }

    @Test
    void testOverwriteDoesNotEvict() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(2, 22); // update value, should not evict
        cache.put(3, 3);  // evicts 1
        assertEquals(-1, cache.get(1));
        assertEquals(22, cache.get(2));
        assertEquals(3, cache.get(3));
    }
}

