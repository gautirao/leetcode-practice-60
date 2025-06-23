package com.goutham.simple;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
Method: removeDuplicatesAndSort(List<String> input)
Description:

Takes a list of strings

Removes duplicate elements

Sorts the list in natural order

Returns a new list
* */
public class ListProcessor {

  public static List<String> removeDuplicatesAndSort(List<String> input) {
    if (null == input) throw new IllegalArgumentException("Input list cannot be null");
    return input.stream().filter(Objects::nonNull).distinct().sorted().collect(Collectors.toList());
  }
}
