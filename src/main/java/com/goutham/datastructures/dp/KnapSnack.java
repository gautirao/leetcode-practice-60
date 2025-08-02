package com.goutham.datastructures.dp;

//Given a set of items, each with a weight and a value, determine which items to include in the collection so that the total weight is less
// than or equal to a given limit and the total value is as large as possible..

import java.util.stream.IntStream;

public class KnapSnack {
public static void main(String[] args){
    IntStream.range(0, 5).forEach(i -> System.out.println("i="+i+"  (i+1)%5="+(i+1)%5));
}
}
