package com.weizhang;

/**
 * Created by Wei Zhang on 3/21/15.
 */
public class PowXN {

    public double pow(double x, int n) {
        return powHelper(x, n);
    }

    private double powHelper(double x, int n) {
        if (n == 0) return 1;
        if (x == 0) return 0;
        if (x == 1) return 1;
        if (x == -1) return n % 2 == 1 ? -1 : 1;

        double tmp = powHelper(x, Math.abs(n/2));

        if (n % 2 == 0)
            tmp = tmp * tmp;
        else
            tmp = tmp * tmp * x;

        if (n < 0)
            tmp = 1 / tmp;
        return tmp;
    }

    public static void main(String[] args) {
        PowXN test = new PowXN();
        // 0.00001, 2147483647 out of heap
        System.out.println(test.pow(1.00000, -2147483648));
    }
}
