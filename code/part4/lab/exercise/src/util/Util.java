package util;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Util {
  public static long countWordsWithStartOfLength(String l, int n, String c) {
    return Stream.of(c.split(" ")).filter((String s) -> {
        boolean t = s.startsWith(l);
        int len = s.length();

        return t == true && len == n;
      }).map((String s) -> 1L).reduce(0L, (Long t, Long x) -> t + x);
  }

  public static List<String> getWordsInUppercaseThatStartOfLength(String l, int n, String c) {
    return Stream.of(c.split(" ")).filter((String s) -> {
      boolean t = s.startsWith(l);
      int len = s.length();

      return t == true && len == n;
    }).map((String s) -> s.toUpperCase()).collect(toList());
  }
}
