package com.weizhang;

/**
 * Created by Wei Zhang on 4/30/15.
 *
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note:
 You are not suppose to use the library's sort function for this problem.
 *
 */
public class SortColors {
    // three way quick sort

    public void sortColors(int[] A) {
        if (A == null || A.length == 0) return;

        int length = A.length;
        int lt, v, gt;
        // find first 1 and exchange that with A[0]
        for (int i = 0; i < length; i++) {
            if (A[i] == 1) {
                exchange(A, 0, i);
                break;
            }
        }
        // one pass quick sort
        lt = 0; v = 0; gt = length - 1;
        int i = v + 1;
        while (i <= gt) {
            if (A[i] < A[v]) {
                exchange(A, lt, i);
                lt++; v++; i++;
            } else if (A[i] > A[v]) {
                exchange(A, gt, i);
                gt--;
            } else {
                v++; i++;
            }
        }
    }

    private void exchange(int[] A, int a, int b) {
        int t = A[a];
        A[a] = A[b];
        A[b] = t;
    }
}
