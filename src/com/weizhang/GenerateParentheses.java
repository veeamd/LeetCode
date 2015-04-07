package com.weizhang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 4/5/15.
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 "((()))", "(()())", "(())()", "()(())", "()()()"
 *
 */
public class GenerateParentheses {
    // dfs with constraints

    public List<String> generateParenthesis(int n) {
        List<String> parentheses = new ArrayList<String>();
        generateParenthesis(n, 0, 0, "", parentheses);
        return parentheses;
    }

    private void generateParenthesis(int n, int leftCount, int rightCount, String trace, List<String> parentheses) {
        boolean isLeftAllowed = leftCount < n;
        boolean isRightAllowed = rightCount < leftCount;
        if ( ! isLeftAllowed && ! isRightAllowed ) {
            parentheses.add(trace);
        } else {
            if (isLeftAllowed) {
                generateParenthesis(n, leftCount + 1, rightCount, trace + "(", parentheses);
            }
            if (isRightAllowed) {
                generateParenthesis(n, leftCount, rightCount + 1, trace + ")", parentheses);
            }
        }
    }

    public static void main(String[] args) {
        GenerateParentheses test = new GenerateParentheses();
        System.out.println(test.generateParenthesis(1));
        System.out.println(test.generateParenthesis(2));
        System.out.println(test.generateParenthesis(3));
        System.out.println(test.generateParenthesis(4));
    }
}
