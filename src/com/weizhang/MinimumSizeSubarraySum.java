package com.weizhang;

/**
 * Created by Wei Zhang on 6/1/15.
 *
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.


 More practice:
 If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 *
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int L = nums.length;
        int p1 = 0, p2 = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while (p2 < L) {
            while (sum < s && p2 < L) {
                sum += nums[p2++];
            }
            while (sum >= s) {
                minLength = Math.min(minLength, p2 - p1);
                sum -= nums[p1++];
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum test = new MinimumSizeSubarraySum();
        int[] A = {1, 4, 4};
        int s = 4;
        int result = test.minSubArrayLen(s, A);
        int[] A1 = {1, 2, 3};
        s = 7;
        result = test.minSubArrayLen(s, A1);
        int[] A2 = {2, 3, 1, 3, 4, 7, 8, 5, 4};
        result = test.minSubArrayLen(s, A2);
    }
}
