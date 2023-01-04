//Is this code correct?

import java.util.*;
import java.util.stream.*;

class Sample {                    
  public static boolean isPrime(int number) {
    return number > 1 &&
      IntStream.range(2, number)
               .noneMatch(i -> number % i == 0);
  } 
                      
  public static void main(String[] args) {
    int n = 101;
    int k = 51; 
    
    System.out.println(computeTotalOfFirstKPrimes(n, k));
  } 
  
  //Find the total of sqrt of first k primes starting with n
  public static double computeTotalOfFirstKPrimes(int starting, int k) {
  /*
    double total = 0;
    int index = starting;
    int count = 0;

    while(count < k) {
      if(isPrime(index)) {
        count++;
	total += Math.sqrt(index);
      }

      index++;
    }

    return total;
  */

    return Stream.iterate(starting, number -> number + 1)
      .filter(Sample::isPrime)
      .mapToDouble(Math::sqrt)
      .limit(k)
      .sum();
  }             
}


/*
The given code has too many moving parts in it - poor quality code
It takes time to verify if the code actually is doing the right things
cognitive load

The code is complex even though it is not very long and also uses
familar style

The modified code reads like the problem statement. Has less cognitive load
Has less accidental complex
Has fewer moving parts

Easier to understand, read, and maintain (once we understand functional
style of programming, of course).
*/
