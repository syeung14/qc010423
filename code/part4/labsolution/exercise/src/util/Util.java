package util;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/*
Smells:
Poor parameter names
Type specification plus poor name for lambda parameters
Multiline lambda
No vertical alignment of lambda
Unnecessary local variables
Complex filter - lacks SLAP
 */
public class Util {
  private static Stream<String> extractWordsThatStartWithAndOfLength(
    String letter, int length, String content) {

    return Stream.of(content.split(" "))
      .filter(word -> word.startsWith(letter))
      .filter(word -> word.length() == length);
  }

  public static long countWordsWithStartOfLength(String letter, int length, String content) {
    return extractWordsThatStartWithAndOfLength(letter, length, content)
      .count();
  }

  public static List<String> getWordsInUppercaseThatStartOfLength(String letter, int length, String contents) {
    return extractWordsThatStartWithAndOfLength(letter, length, contents)
      .map(String::toUpperCase)
      .toList(); //Java 8 .collect(toList());
  }
}
