package com.weizhang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 4/25/15.
 *
 * The gray code is a binary numeral system where two successive values differ in only one bit.

 Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

 For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

 00 - 0
 01 - 1
 11 - 3
 10 - 2

 Note:
 For a given n, a gray code sequence is not uniquely defined.

 For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

 For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 *
 */
public class GrayCode {

    ///////// refer to http://en.wikipedia.org/wiki/Gray_code
    ///////// there is a much easier way to do this

    private List<Integer> result;
    private boolean[] marked;
    private int numberOfBits;
    public List<Integer> grayCode(int n) {
        // solution 1:  we could build a Graph, connecting all the pair of numbers that diffs only one bit.
        //              then we do DFS, mark visited on the way. once we visited all the node, then we have the result

        // solution 2:  we could start with DFS directly, conditionally pick a route, when stuck and not all the node is marked, pop, unmark the node.
        //              until we have all the node in the result array.

        // we go with the second solution, seems like an improved version of solution 1

        numberOfBits = n;
        int totalNumber = (int)Math.pow(2, n);
        result = new ArrayList<Integer>(totalNumber);
        marked = new boolean[totalNumber];
        addGrayCode(0);
        if (n == 0)
            return result;
        addGrayCode(1);
        if (n == 1)
            return result;
        dfs(1, 0);
        return result;
    }

    private boolean dfs(int previous, int bitChanged) {
        if (result.size() == marked.length) {
            // end case
            return true;
        }

        for (int i = 0; i < numberOfBits; i++) {
            if (i == bitChanged)
                continue;
            int next = ( 1 << i ) ^ previous;
            if ( ! marked[next]) {
                addGrayCode(next);
                if ( ! dfs(next, i)) {
                    popGrayCode(next);
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private void addGrayCode(int code) {
        result.add(code);
        marked[code] = true;
    }

    private void popGrayCode(int code) {
        result.remove(result.size() - 1);
        marked[code] = false;
    }

    public static void main(String[] args) {
        GrayCode test = new GrayCode();
        System.out.println(test.grayCode(0));
        System.out.println(test.grayCode(1));
        System.out.println(test.grayCode(2));
        System.out.println(test.grayCode(3));
        System.out.println(test.grayCode(4));
    }
}
