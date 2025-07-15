package com.goutham.multithreading;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/*
* Design a thread-safe logger with this method:

public boolean shouldLog(String message)
Constraints:

Allow a message to be logged only once every 10 seconds

If shouldLog("foo") is called within 10s of the last allowed log for "foo", return false

Must work concurrently across threads

Only return true if the message should be logged right now
* */
public class ThreadSafeLogger {

    private final ConcurrentHashMap<String, Long> lastLogYimeStamps = new ConcurrentHashMap<>();
    private final long intervalMills;

    public ThreadSafeLogger(long intervalseconds) {
        this.intervalMills = intervalseconds * 1000;
    }

    public boolean shouldLog(String message){
        long now = System.currentTimeMillis();
        AtomicBoolean allowed = new AtomicBoolean(false);

    lastLogYimeStamps.compute(
        message,
        (msg, lastTime) -> {
          if (lastTime == null || now - lastTime >= intervalMills) {
            allowed.set(true);
            return now;
          } else {
            return lastTime;
          }
        });

    return allowed.get();
    }
}
