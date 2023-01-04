package compute;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static compute.PerfectNumber.isPerfect;

public class PerfectNumberTest {
  private PerfectNumber perfectNumber = new PerfectNumber();

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
    perfectNumber.setLower(0);
    perfectNumber.setUpper(1);

    assertEquals("", perfectNumber.perfectNumbersInRange());
  }

  @Test
  void perfectNumbersInRange06(){
    perfectNumber.setLower(0);
    perfectNumber.setUpper(6);

    assertEquals("6", perfectNumber.perfectNumbersInRange());
  }

  @Test
  void perfectNumbersInRange010(){
    perfectNumber.setLower(0);
    perfectNumber.setUpper(10);

    assertEquals("6", perfectNumber.perfectNumbersInRange());
  }

  @Test
  void perfectNumbersInRange030(){
    perfectNumber.setLower(0);
    perfectNumber.setUpper(30);

    assertEquals("6, 28", perfectNumber.perfectNumbersInRange());
  }
}
