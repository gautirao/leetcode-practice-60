package com.goutham.datastructures.dp;

// A Fibonacci series is a sequence of numbers where each number is the sum of the two preceding
// ones, starting from 0 and 1.
//
// It typically begins like this: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, and so on.
public class Fibonacci {
    // basic recursion based solution

    public int fib(int n ){
        if( n<=1) return n;
        return fib(n-1) + fib(n-2);
    }

    public int tabfib(int n ){
        if ( n<=1) return n;
        int[] dp = new int[n+1]; // if we ask for fib of 2 we need an array that will hold three values as it starts from zero
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2;i <= n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }


    public static void main(String[] args){
        Fibonacci fibonacci = new Fibonacci();
        for(int i=0;i<6;i++){

    System.out.println(fibonacci.tabfib(i));
        }
    }
}
