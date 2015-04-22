package com.weizhang;

/**
 * Created by Wei Zhang on 4/20/15.
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

 For example, given the range [5, 7], you should return 4.
 *
 */
public class BitwiseANDofNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == n) return n;
        int numTrailingZeros = (int)log(2, n - m) + 1;
        int trailingZeros = ~0 << numTrailingZeros;
        return m & n & trailingZeros;
    }

    private double log(int base, int x) {
        // x can't be 0
        return Math.log(x) / Math.log(base);
    }


    public static void main(String[] args) {
        BitwiseANDofNumbersRange test = new BitwiseANDofNumbersRange();
        System.out.println(test.rangeBitwiseAnd(4, 7));
        System.out.println(test.rangeBitwiseAnd(5, 8));
        System.out.println(test.rangeBitwiseAnd(4, 5));
        System.out.println(test.rangeBitwiseAnd(5, 10));
        System.out.println(test.rangeBitwiseAnd(8, 12));
        System.out.println(test.rangeBitwiseAnd(8, 13));
        System.out.println(test.rangeBitwiseAnd(9, 13));
    }
}
