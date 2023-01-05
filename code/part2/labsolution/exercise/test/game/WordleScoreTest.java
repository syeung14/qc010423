package game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import static game.Match.*;
import static game.Wordle.*;

public class WordleScoreTest {
  @Test
  void scorePerfectMatch(){
    assertEquals(Arrays.asList(EXACT, EXACT, EXACT, EXACT, EXACT),
      evaluate("FAVOR", "FAVOR"));
  }

  @Test
  void scoreForNoMatch(){
    assertEquals(Arrays.asList(NOPE, NOPE, NOPE, NOPE, NOPE),
      evaluate("FAVOR", "TESTS"));
  }

  @Test
  void scoreForOneMatch(){
    assertEquals(Arrays.asList(EXACT, NOPE, NOPE, NOPE, NOPE),
      evaluate("SKILL", "SPADE"));
  }

  @Test
  void scoreForOneMatchOneExactMatch(){
    assertEquals(Arrays.asList(EXISTS, EXACT, NOPE, NOPE, NOPE),
      evaluate("FAVOR", "RAPID"));
  }

  @Test
  void scoreForThreeExactMatches(){
    assertEquals(Arrays.asList(NOPE, EXACT, NOPE, EXACT, EXACT),
      evaluate("FAVOR", "MAYOR"));
  }

  @Test
  void scoreForDuplicateLetter(){
    assertEquals(Arrays.asList(NOPE, NOPE, EXACT, NOPE, EXACT),
      evaluate("FAVOR", "RIVER"));
  }

  @Test
  void scoreForAnotherDuplicateLetter(){
    assertEquals(Arrays.asList(NOPE, NOPE, EXACT, NOPE, EXACT),
      evaluate("FAVOR", "RIVER"));
  }

  @Test
  void scoreForYetAnotherDuplicateLetter(){
    assertEquals(Arrays.asList(EXACT, NOPE, EXACT, NOPE, NOPE),
      evaluate("SKILL", "SHIMS"));
  }

  @Test
  void scoreDuplicateBeforeExactMatch(){
    assertEquals(Arrays.asList(NOPE, NOPE, EXACT, NOPE, EXACT),
      evaluate("POETS", "SEEDS"));
  }

  @Test
  void scoreMatchBeforeExactMatch(){
    assertEquals(Arrays.asList(EXACT, EXISTS, EXISTS, EXACT, NOPE),
      evaluate("SKILL", "SILLY"));
  }
}

