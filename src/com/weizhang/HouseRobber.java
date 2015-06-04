package com.weizhang;

/**
 * Created by Wei Zhang on 5/27/15.
 *
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that
 * adjacent houses have security system connected and it will automatically contact the police
 * if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int length = nums.length;
        int[] cash = new int[length];
        if (length > 0) {
            cash[0] = nums[0];
            if (length > 1) {
                cash[1] = nums[1];
                if (length > 2) {
                    cash[2] = nums[2] + nums[0];
                    for (int i = 3; i < length; i++) {
                        cash[i] = nums[i] + Math.max(cash[i - 2], cash[i - 3]);
                    }
                }
                return Math.max(cash[length - 1], cash[length - 2]);
            } else {
                return cash[0];
            }
        } else {
            return 0;
        }
    }
}
