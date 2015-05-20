package com.weizhang;

import java.util.List;
import java.util.Stack;

/**
 * Created by Wei Zhang on 5/19/15.
 *
 * Given an integer, convert it to a roman numeral.

 Input is guaranteed to be within the range from 1 to 3999.
 *
 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        // put all digits in a stack
        // {9, 9, 9, 3}
        // assign letter options
        // {{'X', 'V', 'I'}, {'C', 'L', 'X'}, {'M', 'D', 'C'}, {' ', ' ', 'M'}};
        // for each digit
        // cases are:
        // if (10 - d == 1)
        // if (10 - d > 1 && d - 5 >= 0)
        // if (5 - d == 1)
        // if (d - 0 > 0)
        Stack<Integer> stack = new Stack<Integer>();
        while (num > 0) {
            stack.push(num % 10);
            num = num / 10;
        }

        char[][] letterOptionsArr = {{'X', 'V', 'I'}, {'C', 'L', 'X'}, {'M', 'D', 'C'}, {' ', ' ', 'M'}};

        StringBuilder romanBuilder = new StringBuilder();
        while (stack.size() > 0) {
            int stackSize = stack.size();
            char[] letterOptions = letterOptionsArr[stackSize - 1];
            int digit = stack.pop();
            if (10 - digit == 1) {
                romanBuilder.append(letterOptions[2]);
                romanBuilder.append(letterOptions[0]);
            } else if  (10 - digit > 1 && digit - 5 >= 0) {
                romanBuilder.append(letterOptions[1]);
                for (int i = 0; i < digit - 5; i++) {
                    romanBuilder.append(letterOptions[2]);
                }
            } else if (5 - digit == 1) {
                romanBuilder.append(letterOptions[2]);
                romanBuilder.append(letterOptions[1]);
            } else {
                for (int i = 0; i < digit; i++) {
                    romanBuilder.append(letterOptions[2]);
                }
            }
        }

        return romanBuilder.toString();
    }

    public static void main(String[] main) {
        IntegerToRoman test = new IntegerToRoman();
        test.intToRoman(3999);
        test.intToRoman(3458);
    }
}
