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
        Stack<Integer> stack = new Stack<Integer>();
        this.S = S;
        dfs(stack, 0);
        return subsets;
    }

    private void dfs(Stack<Integer> stack, int begin) {
        saveStackAsSubset(stack);
        for (int i = begin; i < S.length; i++) {
            stack.push(S[i]);
            dfs(stack, i + 1);
            stack.pop();
        }
    }

    private void saveStackAsSubset(Stack<Integer> stack) {
        ArrayList<Integer> subset = new ArrayList<Integer>(stack);
        subsets.add(subset);
    }

    public static void main(String[] args) {
        Subsets test = new Subsets();
        int[] S = {1, 2, 3, 4, 5, 6, 7};
        for (List<Integer> subset : test.subsets(S))
            System.out.println(subset);
    }
}
