package com.weizhang;

/**
 * Created by Wei Zhang on 4/6/15.
 *
 * Given a list of non negative integers, arrange them such that they form the largest number.

 For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

 Note: The result may be very large, so you need to return a string instead of an integer.
 *
 */
public class LargestNumber {
    public String largestNumber(int[] num) {
        // convert to strings
        // 3-way string quick sort the first letter, then in each group use custome comparator to sort
        // then concatenate all the numbers.


        if (num.length == 0) return "0";

        String[] numStrings = convertToString(num);
        quickSort(numStrings, 0, numStrings.length - 1, 0);
        StringBuilder sb = new StringBuilder();
        for (String s : numStrings)
            sb.append(s);
        if (sb.charAt(0) == '0') return "0";  // special case for when all elements in the num array are 0s
        return sb.toString();
    }

    private String[] convertToString(int[] num) {
        String[] numStrings = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            numStrings[i] = Integer.toString(num[i]);
        }
        return numStrings;
    }

    private void quickSort(String[] numStrings, int left, int right, int d) {
        if (left >= right) return;

        int lt = left;
        int gt = right;
        int v = lt;
        int i = v + 1;
        while (i <= gt) {
            int compareResult = compareCharAtIndex(numStrings[v], numStrings[i], d);
            // put the bigger one on the left; smaller one on the right
            if (compareResult == 1) {
                exchange(numStrings, i, lt++);
                v++; i++;
            } else if (compareResult == -1) {
                exchange(numStrings, i, gt--);
            } else {
                v++; i++;
            }
        }
        quickSort(numStrings, left, lt - 1, d);
        quickSort(numStrings, gt + 1, right, d);
        quickSort(numStrings, lt, v, d + 1);
    }

    // return char of string at index, if index out of bound, return the last char
    private char charOfStringAtIndex(String str, int index, String complement) {
        char vc;
        if (index < str.length())
            vc = str.charAt(index);
        else
            vc = complement.charAt(index - str.length());
        // 8308
        // 830
        // comparing 8 3 0 8,8 3 0
        //           8 3 0,8 3 0 8
        return vc;
    }

    // return 1 if string1 < string2; return -1 if string1 > string2
    private int compareCharAtIndex(String string1, String string2, int index) {
        int strLength1 = string1.length();
        int strLength2 = string2.length();

        char c1 = charOfStringAtIndex(string1, index, string2);
        char c2 = charOfStringAtIndex(string2, index, string1);

        if (c1 > c2)
            return -1;
        else if (c1 < c2)
            return 1;
        else {
            if (index == strLength1 - 1 && strLength1 == strLength2)
                return 1;
            else if (index == strLength1 + strLength2 - 1) {
                // shorter the bigger
                if (strLength1 < strLength2)
                    return -1;
                else
                    return 1;
            } else
                return 0;
        }
    }

    private void exchange(String[] strings, int i, int j) {
        String t = strings[i];
        strings[i] = strings[j];
        strings[j] = t;
    }

    public static void main(String[] args) {
        LargestNumber test = new LargestNumber();
//        int[] nums = {3, 30, 34, 5, 9, 333, 96, 8, 87}; // can't figure out 3, and 333 which one is bigger
//        System.out.println(test.largestNumber(nums));
//        int[] nums2 = {8, 8};
//        System.out.println(test.largestNumber(nums2));
        int[] nums3 = {66,8,33,1,72,93,51,88,59,86,66,39,71,82,95,77,44,75,91,4,52,28,20,73,74,91,87,82,94,12,69,13,22,18,45,68,97,65,4,86,44,32,36,96,88,11,21,8,14,4,67,40,57,90,84,27,42,9,39,14,11,79,68,49,1,51,91,56,35,10,22,99,23,8,76,32,46,40,37,43,89,83,91,40,94,43,62,74,8,42,99,7,34,67,39,55,22,87,73,91};
        System.out.println(test.largestNumber(nums3));
//        int[] nums4 = {824,938,1399,5607,6973,5703,9609,4398,8247};
//        System.out.println(test.largestNumber(nums4));
    }
}
