package com.goutham.examples.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

// Five philosophers sit around a circular table, alternating between thinking and eating. Each
// philosopher needs two forks (one to their left and one to their right) to eat. Forks are shared
// between adjacent philosophers, creating a scenario where resource contention can lead to
// deadlocks or starvation
public class DiningPhilosophers {

    private static final int NO_OF_PHILOSOPHERS = 5;

    public static void main(String[] args){
        Philosopher[] philosophers = new Philosopher[NO_OF_PHILOSOPHERS];
        Object[] chopsticks = IntStream.range(0,NO_OF_PHILOSOPHERS).mapToObj(i -> new Object()).toArray();
        ExecutorService executorService = Executors.newFixedThreadPool(NO_OF_PHILOSOPHERS);
        IntStream.range(0,NO_OF_PHILOSOPHERS).forEach(
                i -> executorService.submit(() ->{
                    // assign chopstick in a round robin fashion
                    Object leftChopstick = chopsticks[i];
                    Object rightChopstick = chopsticks[(i+1)% NO_OF_PHILOSOPHERS];
                    // if it is tjhe last philopher exchange the left and right chopstick so that we break the deadlock
                if( i == (NO_OF_PHILOSOPHERS-1)){
                    philosophers[i] = new Philosopher(leftChopstick,rightChopstick,(i+1));

                    }else {
                    philosophers[i] = new Philosopher(rightChopstick,leftChopstick,(i+1));
                }

                philosophers[i].start();
                })
        );
    }
}
