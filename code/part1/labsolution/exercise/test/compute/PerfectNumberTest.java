package compute;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static compute.PerfectNumber.isPerfect;
import static compute.PerfectNumber.perfectNumbersInRange;

public class PerfectNumberTest {
  @Test
  void checkPerfectNumbers(){
    assertAll(
      () -> assertTrue(isPerfect(6)),
      () -> assertTrue(isPerfect(28)),
      () -> assertTrue(isPerfect(496))
    );
  }

  @Test
  void checkNoPerfect(){
    assertAll(
      () -> assertFalse(isPerfect(0)),
      () -> assertFalse(isPerfect(1)),
      () -> assertFalse(isPerfect(2)),
      () -> assertFalse(isPerfect(5)),
      () -> assertFalse(isPerfect(7)),
      () -> assertFalse(isPerfect(29))
    );
  }

  @Test
  void perfectNumbersInRange01(){
    assertEquals("", perfectNumbersInRange(0, 1));
  }

  @Test
  void perfectNumbersInRange06(){
    assertEquals("6", perfectNumbersInRange(0, 6));
  }

  @Test
  void perfectNumbersInRange010(){
    assertEquals("6", perfectNumbersInRange(0, 10));
  }

  @Test
  void perfectNumbersInRange030(){
    assertEquals("6, 28", perfectNumbersInRange(0, 30));
  }
}
