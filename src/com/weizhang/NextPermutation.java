package com.weizhang;

/**
 * Created by Wei Zhang on 4/30/15.
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 *
 */
public class NextPermutation {
    public void nextPermutation(int[] num) {
        // 1. check from right
        //      find the first number m, that's decreasing, eg: 3, 6, 5, 4; 3 is the m
        // 2. starting from m, find the next smallest number n. 4 is n from the last example.  other example: 1, 3, 4, 2. m = 3, n = 4
        // 3. bubble n up until it's on the left of m, then you get the result
        // 4. if you the head of list, didn't find m. then the array is like: 3, 2, 1. in that case, reverse the array

        if (num == null || num.length <= 1) return;

        int m = -1;
        for (int i = num.length - 1; i > 0; i--) {
            if (num[i - 1] < num[i]) {
                m = i - 1;
                break;
            }
        }
        if (m == -1) {
            // special case: 3, 2 ,1
            int lo = 0; int hi = num.length - 1;
            while (lo < hi) {
                int t = num[hi];
                num[hi] = num[lo];
                num[lo] = t;
                lo++; hi--;
            }
        } else {
            // find n, what's here is wrong. find next smallest after m.
            int n = num.length - 1;
            // bubble last number up to index m.
            while (n > m) {
                int t = num[n];
                num[n] = num[n-1];
                num[n-1] = t;
                n--;
            }
        }
    }

    public static void main(String[] args) {
        NextPermutation test = new NextPermutation();
        int[] num = {1, 2, 3, 4};
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);
        test.nextPermutation(num);

        int[] num2 = {3, 2, 1};
        test.nextPermutation(num2);
        System.out.println(num2);

    }
}
