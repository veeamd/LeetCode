package com.weizhang;

import java.util.ArrayList;

/**
 * Created by Wei Zhang on 4/13/15.
 *
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

 For example, given the array [2,3,-2,4],
 the contiguous subarray [2,3] has the largest product = 6.
 *
 */
public class MaximumProductSubarray {
    private boolean creatingNew;
    private ArrayList<Integer> inProgress;
    private int maximum;
    public int maxProduct(int[] A) {
        creatingNew = false;
        inProgress = new ArrayList<Integer>();
        maximum = Integer.MIN_VALUE;
        int i = 0;
        int N = A.length;

        while (i < N) {
            // if A[i] == 0 potentially what we have so fas has a maximum
            if (A[i] < 0 || A[i] == 0 || i == N - 1) {
                setMaximum();
            }

            if (A[i] == 0) {
                // reset
                inProgress = new ArrayList<Integer>();
            }

            if (inProgress.isEmpty()) {
                inProgress.add(A[i]);
            } else {
                multiplyEach(A[i]);
            }
            // after multiply then add as new element. avoid multiply self
            if (creatingNew && A[i] > 0) {
                if (A[i] > 1) {
                    inProgress.add(A[i]);
                } else if (inProgress.isEmpty()) {
                    inProgress.add(A[i]);
                }
                creatingNew = false;
            }

            if (A[i] < 0) {
                creatingNew = !creatingNew;
            }

            i++;
        }
        return maximum;
    }

    private void setMaximum() {
        for (int i : inProgress) {
            if (i > maximum) {
                maximum = i;
            }
        }

    }

    private void multiplyEach(int num) {
        for (int i = 0; i < inProgress.size(); i++) {
            inProgress.set(i, inProgress.get(i) * num);
        }
    }


}
