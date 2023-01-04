package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static game.Match.*;

enum Match {
  EXACT, NOPE, EXISTS
}

public class Wordle {
  public static List<Match> evaluate(String targetWord, String guess) {
    List<Match> result = new ArrayList<>(Arrays.asList(NOPE, NOPE, NOPE, NOPE, NOPE));

    StringBuilder targetGiven = new StringBuilder(targetWord);
    StringBuilder guessWord = new StringBuilder(guess);

    for(int i = 0; i < 5; i++) {
      if(guessWord.charAt(i) == targetGiven.charAt(i)) {
        result.set(i, EXACT);
        guessWord.setCharAt(i, ' ');
        targetGiven.setCharAt(i, ' ');
      }
    }
    for (int i = 0; i < 5; i++) {
       if(guessWord.charAt(i) != ' ') {
         if(targetGiven.indexOf("" + guessWord.charAt(i)) >= 0) {
           result.set(i, EXISTS);
           guessWord.setCharAt(i, ' ');
           targetGiven.setCharAt(targetGiven.indexOf("" + guessWord.charAt(i)), ' ');
         }
       }
    }
    return result;
  }
}
