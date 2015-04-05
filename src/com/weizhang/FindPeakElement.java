package com.weizhang;

/**
 * Created by Wei Zhang on 4/4/15.
 *
 * A peak element is an element that is greater than its neighbors.

 Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

 You may imagine that num[-1] = num[n] = -∞.

 For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

 *
 */
public class FindPeakElement {
    public int findPeakElement(int[] num) {
        // Binary search; the maximum element is definitely one of the peaks
        int left = 0;
        int right = num.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (num[mid + 1] > num[mid]) {
                left = mid + 1;
            } else
                right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        FindPeakElement test = new FindPeakElement();
        int[] num1 = {1};
        System.out.println(test.findPeakElement(num1));
        int[] num2 = {1, 2};
        System.out.println(test.findPeakElement(num2));
        int[] num3 = {3, 2};
        System.out.println(test.findPeakElement(num3));
        int[] num4 = {3, 2, 1};
        System.out.println(test.findPeakElement(num4));
        int[] num5 = {1, 2, 3};
        System.out.println(test.findPeakElement(num5));
        int[] num6 = {3, 2, 5};
        System.out.println(test.findPeakElement(num6));
    }
}
