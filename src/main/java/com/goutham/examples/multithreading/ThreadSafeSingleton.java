package com.goutham.examples.multithreading;

//Implement a thread-safe singleton in Java
public class ThreadSafeSingleton {

    private static volatile ThreadSafeSingleton instance ;

    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstance(){
        if(instance == null){
            synchronized (ThreadSafeSingleton.class){
                instance = new ThreadSafeSingleton();
            }
        }
    return instance;
    }
}
