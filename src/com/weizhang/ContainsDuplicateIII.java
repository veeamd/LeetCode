package com.weizhang;

import java.util.TreeSet;

/**
 * Created by Wei Zhang on 6/6/15.
 *
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that
 * the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
 *
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;

        TreeSet<Integer> window = new TreeSet<Integer>();

        // 1. use floor and ceiling function to check if a number in [ num[i] - t, num[i] + t ] exists in the array
        // 2. insert number into the tree set;
        // 3. starting i == k, remove the nums[i - k] from the tree set to keep a sliding window size equal or less then k
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            Integer floor = window.floor(n + t);
            Integer ceiling = window.ceiling(n - t);
            if (floor != null && floor >= n
                || ceiling != null && ceiling <= n) {
                return true;
            }

            window.add(n);
            if (i >= k) {
                window.remove(nums[i - k]);
            }
        }
        return false;
    }
}
