package com.goutham.simple;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// A simple class that provides a edgcase for all common datatypes
public class EdgeCaseFactory {

  public static List<Integer> edgeIntegers() {
    return List.of(Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE);
  }

  public static List<Long> edgeLongs() {
    return List.of(Long.MIN_VALUE, -1L, 0L, 1L, Long.MAX_VALUE);
  }

  public static List<Double> edgeDoubles() {
    return List.of(
        Double.MIN_VALUE,
        Double.NEGATIVE_INFINITY,
        Double.POSITIVE_INFINITY,
        Double.MAX_VALUE,
        -Double.MAX_VALUE,
        -1.0,
        0.0,
        1.0,
        Double.NaN);
  }

  public static List<String> edgeStrings() {
    return List.of(
        null,
        "",
        "  ",
        "\n",
        "A very long string ".repeat(10),
        "<script>captureWIfiPassword()</script>",
        "");
  }

  public static List<BigDecimal> edgeBigDecimals() {
    return List.of(
        null,
        BigDecimal.ZERO,
        BigDecimal.ONE,
        new BigDecimal("-1"),
        new BigDecimal("0.000000000001"),
        new BigDecimal("1000000000000000000"),
        new BigDecimal("1.0000"),
        new BigDecimal("-999999999999999999999999999999999999.99999"));
  }

  public static <T> List<List<T>> edgeCollections(T... elements) {
    return List.of(null, List.of(), List.of(elements), new ArrayList<>(List.of(elements)));
  }
}
