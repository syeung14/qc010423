package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static game.Match.*;
import static java.util.stream.Collectors.toList;

enum Match {
  EXACT, NOPE, EXISTS
}

/*
Smells:
From the user of the code point of view, this code is quite fine.

From the maintainer of the code point of view, we have quite a few problems:
5 is a magic number
Too many moving parts, mutation based on the index

It violates the SLAP. We have nested levels of details and it has higher cognitive load.

We may take two separate approaches:
1. Imperative style solution to apply SLAP
2. Functional style solution to transform the data into the matches
 */

public interface Wordle {
  final static int WORD_SIZE = 5;

  /*
  //Imperative style solution
  static List<Match> evaluate(String targetWord, String guess) {
    List<Match> matches = initializeMatches();

    StringBuilder targetGiven = new StringBuilder(targetWord);
    StringBuilder guessWord = new StringBuilder(guess);

    evaluateExactPositionalMatches(targetGiven, guessWord, matches);

    evaluateNonExactPositionalMatches(targetGiven, guessWord, matches);

    return matches;
  }

  private static List<Match> initializeMatches() {
    List<Match> result = new ArrayList<>();

    for(int i = 0; i < WORD_SIZE; i++) {
      result.add(NOPE);
    }

    return result;
  }

  private static void evaluateExactPositionalMatches(StringBuilder targetGiven, StringBuilder guessWord, List<Match> matches) {
    for(int i = 0; i < WORD_SIZE; i++) {
      if(guessWord.charAt(i) == targetGiven.charAt(i)) {
        matches.set(i, EXACT);
        guessWord.setCharAt(i, ' ');
        targetGiven.setCharAt(i, ' ');
      }
    }
  }

  private static void evaluateNonExactPositionalMatches(StringBuilder targetGiven, StringBuilder guessWord, List<Match> matches) {
    for (int i = 0; i < WORD_SIZE; i++) {
      if(guessWord.charAt(i) != ' ') {
        if(targetGiven.indexOf("" + guessWord.charAt(i)) >= 0) {
          matches.set(i, EXISTS);
          guessWord.setCharAt(i, ' ');
          targetGiven.setCharAt(targetGiven.indexOf("" + guessWord.charAt(i)), ' ');
        }
      }
    }
  }
  */
  /*
  Good news: The code is much better thanks to dividing the function into smaller functions based on
  the level of details we are working with. SLAP. Easier to read, understand, to change.

  Opportunities for improvements:
  The disadvantage in this code is that we have variable that we pass from the evaluate to the
  smaller functions and these smaller functions mutate those variables/objects. This is very
  undesirable. Shared mutability is something we should avoid as much as possible.

  We may take a functional style approach to avoid such mutation.
   */

  //Functional style
  public static List<Match> evaluate(String targetWord, String guess) {
    return IntStream.range(0, WORD_SIZE)
      .mapToObj(index -> tallyForPosition(targetWord, guess, index))
      .collect(toList());
  }

  private static Match tallyForPosition(String targetWord, String guess, int position) {
    if(targetWord.charAt(position) == guess.charAt(position)) {
      return EXACT;
    }

    var theLetter = guess.charAt(position);

    var numberOfPositionalMatches = countPositionalMatches(targetWord, guess, theLetter);
    var numberOfNonPositionalOccurrencesInTargetWord =
      countNumberOfOccurrencesUntilPosition(targetWord, theLetter, WORD_SIZE - 1) - numberOfPositionalMatches;
    var numberOfOccurrancesInGuessUntilPosition =
      countNumberOfOccurrencesUntilPosition(guess, theLetter, position);

    if(numberOfNonPositionalOccurrencesInTargetWord >= numberOfOccurrancesInGuessUntilPosition) {
      return EXISTS;
    }

    return NOPE;
  }

  private static long countPositionalMatches(String targetWord, String guess, char letter) {
    return IntStream.range(0, WORD_SIZE)
      .filter(index -> targetWord.charAt(index) == letter)
      .filter(index -> targetWord.charAt(index) == guess.charAt(index))
      .count();
  }

  private static long countNumberOfOccurrencesUntilPosition(String word, char letter, int position) {
    return IntStream.rangeClosed(0, position)
      .filter(index -> word.charAt(index) == letter)
      .count();
  }
}
