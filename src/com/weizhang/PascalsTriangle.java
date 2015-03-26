package com.weizhang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 3/25/15.
 *
 * Given numRows, generate the first numRows of Pascal's triangle.

 For example, given numRows = 5,
 Return

 [
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
 ]
 *
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pt = new ArrayList<List<Integer>>();
        if (numRows == 0) return pt;
        if (numRows >= 1) {
            ArrayList<Integer> levelOne = new ArrayList<Integer>(1);
            levelOne.add(1);
            pt.add(levelOne);
            if (numRows == 1) {
                return pt;
            }
        }
        if (numRows >= 2) {
            ArrayList<Integer> levelTwo = new ArrayList<Integer>(2);
            levelTwo.add(1);
            levelTwo.add(1);
            pt.add(levelTwo);
            if (numRows == 2) {
                return pt;
            }
        }

        for (int row = 2; row < numRows; row++) {
            ArrayList<Integer> level = new ArrayList<Integer>(row);
            level.add(0, 1);
            ArrayList<Integer> lastLevel = (ArrayList<Integer>)pt.get(row - 1);
            for (int col = 1; col < row; col++) {
                level.add(col, lastLevel.get(col - 1) + lastLevel.get(col));
            }
            level.add(row, 1);
            pt.add(level);
        }
        return pt;
    }

    public static void main(String[] args) {
        PascalsTriangle pt = new PascalsTriangle();
        List<List<Integer>> levels = pt.generate(9);
        for (int i = 0; i < levels.size(); i++) {
            System.out.print(levels.get(i));
            System.out.print("\n");
        }
    }
}
