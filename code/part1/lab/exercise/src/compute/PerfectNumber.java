package compute;

public class PerfectNumber {
  private int lower;
  private int upper;

  public void setLower(int lower) {
    this.lower = lower;
  }

  public void setUpper(int upper) {
    this.upper = upper;
  }

  public static boolean isPerfect(int n) {
    if(n <= 1)
      return false;
    int s = 0;
    for(int i = 1; i <= n; i++) {
      if(n % i == 0)
        s += i;
    }
    return n * 2 == s;
  }

  public String perfectNumbersInRange() {
    String pnos = "";
    for(int i = lower; i <= upper; i++) {
      if(isPerfect(i)) {
        if(!pnos.equals(""))
          pnos += ", ";
        pnos += i;
      }
    }
    return pnos;
  }
}
