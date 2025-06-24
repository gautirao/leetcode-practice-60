package com.goutham.simple;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ListProcessorTest {

  static Stream<List<String>> provideEdgeCaseList() {
    return Stream.of(
        List.of(),
        List.of("apple"),
        List.of("apple,berry,apple"),
        Arrays.asList("apple", null, "berry"),
        createLargeList(1000 + new Random().nextInt(1001))); // generates numbers from 1000 to 2000
  }

  private static List<String> createLargeList(int size) {
    // duplicates every 10
    return IntStream.range(0, size).mapToObj(i -> "item" + (i % 10)).collect(Collectors.toList());
  }

  @ParameterizedTest
  @MethodSource("provideEdgeCaseList")
  void testRemoveNullDuplicatesAndSort(List<String> input) {
    List<String> result = ListProcessor.removeDuplicatesAndSort(input);

    long distinctCount = result.stream().distinct().count();
    assertEquals(distinctCount, result.size(), "The result contains duplicates");

    List<String> sorted = result.stream().sorted().collect(Collectors.toList());
    assertEquals(sorted, result, "the collection is not sorted");

    assertFalse(result.contains(null), "result contains null");
  }

  @Test
  void shouldThrowExceptionOnNull() {
    assertThrows(IllegalArgumentException.class, () -> ListProcessor.removeDuplicatesAndSort(null));
  }

  @Test
  void learnModuloArrayWrapping() {
    String[] colors = {"red", "green", "blue"};
    int currentIndex = 0;

    for (int i = 0; i < 10; i++) {
      System.out.printf(
          "Current index:%s  colors.length:%s Colour: %s (currentIndex + 1) mod colors.length: %s \n ",
          currentIndex, colors.length, colors[currentIndex], (currentIndex + 1) % colors.length);
      currentIndex = (currentIndex + 1) % colors.length; // wraps back to 0 after 2
    }
  }
}
