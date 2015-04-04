package com.weizhang;

/**
 * Created by Wei Zhang on 3/31/15.
 *
 * This is not a problem from Leetcode
 *
 * requirements: Given two integers in an array, do subtraction operations on any two numbers in an array (bigger number subtract smaller number),
 * add the result back to the array; repeat this until there's no number you can add into the array.
 */
public class SubtractionArray {
    public int[] subtractionArray(int big, int small) {
        int[] arr = new int[big + 1];
        arr[big] = big;
        arr[small] = small;
        int size = 2;
        int loopStartingSize;
        do {
            loopStartingSize = size;
            for (int x = big - 1; x > 0; x--) {
                if (arr[x] == 0) {
                    // find numbers are bigger then arr[x]
                    for (int n = big; n > x; n--) {
                        if (arr[n] != 0 && arr[n - x] != 0) {
                            arr[x] = x;
                            size++;
                            break;
                        }
                    }
                }
            }
        } while (loopStartingSize != size);
        return arr;
    }

    /* improvement consideration: use a sorted data structure to only keep existing numbers to improvement performance int the beginning when the array is greatly empty.
    * (To query what's the next non-empty number); for example, a sorted linked list?
    * then instead of going through the whole the array, just try the existing numbers
    * */

    public static void main(String[] args) {
        SubtractionArray test = new SubtractionArray();
        test.subtractionArray(21, 19);
    }
}
