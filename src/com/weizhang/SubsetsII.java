package com.weizhang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei Zhang on 5/10/15.
 *
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.

 Note:
 Elements in a subset must be in non-descending order.
 The solution set must not contain duplicate subsets.
 For example,
 If nums = [1,2,2], a solution is:

 [
   [2],
   [1],
   [1,2,2],
   [2,2],
   [1,2],
   []
 ]
 *
 */
public class SubsetsII {
    private int[] S;
    private List<List<Integer>> subsets;
    public List<List<Integer>> subsetsWithDup(int[] S) {
        subsets = new ArrayList<List<Integer>>();
        Stack<Integer> stack = new Stack<Integer>();
        Arrays.sort(S);
        this.S = S;
        dfs(stack, 0);
        return subsets;
    }

    private void dfs(Stack<Integer> stack, int begin) {
        saveStackAsSubset(stack);
        for (int i = begin; i < S.length; i++) {
            if (i == begin || S[i] != S[i-1]) {
                stack.push(S[i]);
                dfs(stack, i + 1);
                stack.pop();
            }
        }
    }

    private void saveStackAsSubset(Stack<Integer> stack) {
        ArrayList<Integer> subset = new ArrayList<Integer>(stack);
        subsets.add(subset);
    }

    public static void main(String[] args) {
        SubsetsII test = new SubsetsII();
        int[] S = {1, 2, 3, 3, 5, 5, 7};
        for (List<Integer> subset : test.subsetsWithDup(S))
            System.out.println(subset);
    }
}
