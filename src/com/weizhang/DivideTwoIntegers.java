package com.weizhang;

/**
 * Created by Wei Zhang on 4/29/15.
 *
 * Divide two integers without using multiplication, division and mod operator.

 If it is overflow, return MAX_INT.
 *
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        long m = dividend, n = divisor;

        boolean negative = (m < 0) ^ (n < 0);
        if (m < 0) {
            m = Math.abs(m);
        }
        if (n < 0) {
            n = Math.abs(n);
        }

        int result = 0;
        int shift = 0;
        while (m >= n) {
            long shiftedM = m >> shift;
            if (shiftedM < n) {
                result += 1 << (shift - 1);
                m -= n << (shift - 1);
                shift = 0;
            }
            shift++;
        }
        return negative ? -result : result;
    }

    public static void main(String[] args) {
        DivideTwoIntegers test = new DivideTwoIntegers();
        System.out.println(test.divide(-2147483648, 1));
    }
}
