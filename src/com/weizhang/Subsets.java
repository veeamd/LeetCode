package com.weizhang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei Zhang on 4/25/15.
 *
 * Given a set of distinct integers, S, return all possible subsets.

 Note:
 Elements in a subset must be in non-descending order.
 The solution set must not contain duplicate subsets.
 For example,
 If S = [1,2,3], a solution is:

 [
    [3],
    [1],
    [2],
    [1,2,3],
    [1,3],
    [2,3],
    [1,2],
    []
 ]
 *
 */

public class Subsets {
    private int[] S;
    private List<List<Integer>> subsets;
    public List<List<Integer>> subsets(int[] S) {
        subsets = new ArrayList<List<Integer>>();
        if (S.length > 0) {
            Arrays.sort(S);
            this.S = S;
            for (int m = 1; m <= S.length; m++) {
                Stack<Integer> stack = new Stack<Integer>();
                dfs(stack, m, 0);
            }
        }
        // empty subset is a subset as well
        subsets.add(new ArrayList<Integer>());
        return subsets;
    }

    private void dfs(Stack<Integer> stack, int length, int index) {
        if (length == 0) {
            ArrayList<Integer> subset = new ArrayList<Integer>(stack);
            subsets.add(subset);
            return;
        }

        for (int i = index; i < S.length && S.length >= i + length; i++) {
            stack.push(S[i]);
            dfs(stack, length - 1, i + 1);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        Subsets test = new Subsets();
        int[] S = {1, 2, 3, 4, 5, 6, 7};
        test.subsets(S);
    }
}
