package com.weizhang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 4/8/15.
 *
 * Given number n, output all the prime numbers that are less or equal to n.
 *
 */
public class NumberOfPrimeNumbers {
    public List<Integer> numberOfPrimeNumbers(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] numbers = new int[n+1];
        for (int i = 1; i <= n ; i++) {
            numbers[i] = i;
        }

        for (int i = 2; i <= n; i++) {
            if (numbers[i] != 0) {
                if (isPrime(i, primes)) {
                    primes.add(i);
                    // remove all the multiples
                    for (int j = 2, k = i * j; k <= n; j++, k = i * j) {
                        numbers[k] = 0;
                    }
                }
            }
        }
        return primes;

    }

    private boolean isPrime(int n, List<Integer> smallerPrimes) {
        for (int i : smallerPrimes) {
            if (n % i == 0)
                return false;
            if (i > Math.sqrt(n))
                return true;
        }
        return true;
    }

    public static void main(String[] args) {
        NumberOfPrimeNumbers test = new NumberOfPrimeNumbers();
        List<Integer> primes = test.numberOfPrimeNumbers(1000);
        System.out.print(primes);
    }
}
