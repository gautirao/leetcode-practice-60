package com.goutham.multithreading;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

//Allow N requests per second
public class SimpleRateLimiter {

    private final int maxRequests;
    private final int intervalSeconds;
    private final Deque<Instant> timeStamps = new ArrayDeque<>();

    public SimpleRateLimiter(int maxRequests, int intervalSeconds) {
        this.maxRequests = maxRequests;
        this.intervalSeconds = intervalSeconds;
    }
    public synchronized boolean allowRequests(){
        Instant now = Instant.now();

        while(!timeStamps.isEmpty() && now.getEpochSecond() - timeStamps.peekFirst().getEpochSecond() >= intervalSeconds ){
            timeStamps.pollFirst();
        }

        if(timeStamps.size() < maxRequests){
            timeStamps.addLast(now);
            return true;
        }
        return false;
    }
}
