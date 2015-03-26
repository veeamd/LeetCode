package com.weizhang;

import java.util.HashMap;

/**
 * Created by Wei Zhang on 3/21/15.
 *
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 *
 */
public class MajorityElement {
    public int majorityElement(int[] num) {
        // use moores vote
        int votes = 1;
        int theMajority = num[0];

        for (int i = 1; i < num.length; i++) {
            int currentElement = num[i];

            if (currentElement == theMajority) {
                votes++;
            } else {
                votes--;
                if (votes == 0) {
                    theMajority = currentElement;
                    votes = 1;
                }
            }
        }

        return theMajority;

    }
}
