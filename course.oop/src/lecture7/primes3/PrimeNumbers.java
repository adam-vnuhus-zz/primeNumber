package lecture7.primes3;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {
    private int n;

    public PrimeNumbers() {
        n = 100;
    }

    public PrimeNumbers(int max) {
        this.n = max;
    }

    /**
     * Tests whether an integer is primary
     *
     * @param k a positive integer
     * @return <code>true</code> or <code>false</code>
     */
    public boolean isPrime(int k) {
        boolean prime = true;
        for (int i = 2; i <= Math.sqrt(k); i++)
            if (k % i == 0) {
                prime = false;
                break;
            }
        return prime;
    }

    /**
     * Lists all prime numbers which less than <code>n</code>
     */
    public void listPrimeNumbers() {
        for (int k = 2; k <= n; k++) {
            if (isPrime(k)) {
                System.out.print(k + ", ");
            }
        }
    }

    /**
     * Returns a string containing all prime numbers less than a number, numbers
     * are separated by a new line character.
     *
     * @param max a positive integer.
     * @return a string containing prime numbers.
     */
    public String listPrimeNumbers(int max) {
        StringBuilder sb = new StringBuilder();
        for (int k = 2; k <= max; k++) {
            if (isPrime(k)) {
                sb.append(k);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns a list of prime numbers less than a number.
     *
     * @param max a positive integer
     * @return a list of prime numbers
     */
    public List<Integer> computePrimeNumbers(int max) {
        List<Integer> list = new ArrayList<Integer>();
        for (int k = 2; k <= max; k++) {
            if (isPrime(k)) {
                list.add(k);
            }
        }
        return list;
    }
}
