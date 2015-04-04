package com.weizhang;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei Zhang on 4/1/15.
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 For example,
 If n = 4 and k = 2, a solution is:

 [
    [2,4],
    [3,4],
    [2,3],
    [1,2],
    [1,3],
    [1,4],
 ]

 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combinations = new LinkedList<List<Integer>>();
        if (k > 0) {
            // initialize the first list
            for (int i = 1; i <= n; i++) {
                List<Integer> comb = new ArrayList<Integer>();
                comb.add(i);
                combinations.add(comb);
            }
            for (int j = 2; j <= k; j++) {
                int combinationLength = j - 1;
                int listSize = combinations.size();
                // dequeue from the combinations
                // get the last element;
                // starting from the one element after, combine each into the current list
                // add back to the combinations
                for (int c = 0; c < listSize; c++) {
                    ArrayList<Integer> comb = (ArrayList<Integer>) combinations.remove(0);
                    int lastElement = comb.get(combinationLength - 1);
                    for (int next = lastElement + 1; next <= n; next++) {
                        ArrayList<Integer> combClone = (ArrayList<Integer>) comb.clone();
                        combClone.add(next);
                        combinations.add(combClone);
                    }
                }

            }
        }
        return combinations;
    }

    /*
     * Use DFS
     */
    public List<List<Integer>> combineII(int n, int k) {
        List<List<Integer>> queue = new ArrayList<List<Integer>>();
        Stack<Integer> trace = new Stack<Integer>();
        dfs(n, k, 1, queue, trace);
        return queue;
    }

    private void dfs(int n, int k, int i, List<List<Integer>> queue, Stack<Integer> trace) {
        // end case
        if (k == 0) {
            List<Integer> comb = new ArrayList<Integer>(trace);
            queue.add(comb);
            return;
        }

        // only go deep when necessary
        for (; i <= n - k + 1; i++) {
            trace.push(i);
            dfs(n, k - 1, i+1, queue, trace);
            trace.pop();
        }

    }

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        System.out.print(combinations.combineII(7, 3));
    }
}
