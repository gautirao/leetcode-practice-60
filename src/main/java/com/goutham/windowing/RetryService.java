package com.goutham.windowing;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class RetryService {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public <T>CompletableFuture<T>  executeWithRetry(Supplier<T> task, int maxRetries, long initialDelayMillis){
        CompletableFuture<T> future = new CompletableFuture<>();
        attempt(task, maxRetries,initialDelayMillis,future,0);
        return future;
    }

    private <T> void attempt(Supplier<T> task, int maxRetries, long delay, CompletableFuture<T> future, int attempt) {
        scheduler.schedule(() ->{
            try{
                T result = task.get();
                future.complete(result) ;
            }catch(Exception e){
                if(attempt < maxRetries){
                    attempt(task,maxRetries,delay*2,future,attempt + 1 );
                }else{
                    future.completeExceptionally(e);
                }
            }
        },delay, TimeUnit.SECONDS);
    }

}
