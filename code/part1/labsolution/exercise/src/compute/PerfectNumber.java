package compute;

/*
Smells we found in the code:
Poor parameter names
Poor variable names
complex logic
Missing { separators
Mutation of variable
Unnecessary use of state for lower and upper
perfectNumbersInRange - violates the SLAP
*/

import java.util.stream.IntStream;
import static java.util.stream.Collectors.joining;

public interface PerfectNumber {
  static boolean isPerfect(int number) {
    var sumOfFactors = IntStream.rangeClosed(1, number)
      .filter(index -> number % index == 0)
      .sum();

    return number > 1 && number * 2 == sumOfFactors;
  }

  static String perfectNumbersInRange(int lowerLimit, int upperLimit) {
    return IntStream.rangeClosed(lowerLimit, upperLimit)
      .filter(PerfectNumber::isPerfect)
      .mapToObj(Integer::toString)
      .collect(joining(", "));
  }
}
