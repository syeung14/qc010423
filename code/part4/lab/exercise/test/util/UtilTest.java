package util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Util.*;

public class UtilTest {
  @Test
  void countWordsThatStartWithSOfLength2(){
    assertAll(
      () -> assertEquals(0, countWordsWithStartOfLength("s", 2, "Nope")),
      () -> assertEquals(1, countWordsWithStartOfLength("s", 2, "and so it is")),
      () -> assertEquals(2, countWordsWithStartOfLength("s", 2, "and so it is so"))
    );
  }

  @Test
  void countWordsThatStartWithNOfLength4(){
    assertAll(
      () -> assertEquals(1, countWordsWithStartOfLength("N", 4, "Nope")),
      () -> assertEquals(0, countWordsWithStartOfLength("N", 4, "and so it is")),
      () -> assertEquals(0, countWordsWithStartOfLength("N", 4, "and so it is so"))
    );
  }

  @Test
  void getWordsInUppercaseThatStartWithSOfLength2(){
    assertAll(
      () -> assertEquals(Arrays.asList(), getWordsInUppercaseThatStartOfLength("s", 2, "Nope")),
      () -> assertEquals(Arrays.asList("SO"), getWordsInUppercaseThatStartOfLength("s", 2, "and so it is")),
      () -> assertEquals(Arrays.asList("SO", "SO"), getWordsInUppercaseThatStartOfLength("s", 2, "and so it is so"))
    );
  }

  @Test
  void getWordsInUppercaseThatStartWithNOfLength4(){
    assertAll(
      () -> assertEquals(Arrays.asList("NOPE"), getWordsInUppercaseThatStartOfLength("N", 4, "Nope")),
      () -> assertEquals(Arrays.asList(), getWordsInUppercaseThatStartOfLength("N", 4, "and so it is")),
      () -> assertEquals(Arrays.asList(), getWordsInUppercaseThatStartOfLength("N", 4, "and so it is so"))
    );
  }
}
