package com.weizhang;

import java.util.Scanner;

/**
 * Created by Wei Zhang on 4/11/15.
 *
 * Given N money, $B each bottle, M bottle caps could exchange for one free bottle of beer
 * How many bottles of beer you can get
 */
public class BottleCaps {
    public int totalBottles(int N, int B, int M) {
        // total bottles
        int S;
        // number of bottles
        int P = N/B;
        S = P;

        // number of free bottles
        int Q = P/M;
        int R = 0; // rest number of bottles after trade in.
        while (Q > 0) {
            S += Q;
            R = P % M;
            P = Q + R;
            Q = P / M;
        }

        return S;

    }

    public static void main(String[] args) {
        BottleCaps test1 = new BottleCaps();
/*
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int N = scanner.nextInt();
            int B = scanner.nextInt();
            int M = scanner.nextInt();
            System.out.println(test1.totalFreeBottles(N, B, M));
        }
        scanner.close();
        */
        System.out.println(test1.totalBottles(10000, 1, 3));
    }
}
