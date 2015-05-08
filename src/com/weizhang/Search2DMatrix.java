package com.weizhang;

/**
 * Created by Wei Zhang on 5/6/15.
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 For example,

 Consider the following matrix:

 [
   [1,   3,  5,  7],
   [10, 11, 16, 20],
   [23, 30, 34, 50]
 ]
 Given target = 3, return true.
 *
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null) return false;
        return binarySearchRowHead(matrix, 0, matrix.length - 1, target);
    }

    public boolean binarySearchRowHead(int[][] matrix, int loIndex, int hiIndex, int target) {
        if (loIndex > hiIndex) {
            return false;
        } else if (loIndex == hiIndex) {
            if (target < matrix[loIndex][0])
                return false;
            else if (target > matrix[loIndex][0]) {
                return binarySearch(matrix[loIndex], 1, matrix[loIndex].length - 1, target);
            } else {
                return true;
            }
        } else {
            // here garantees loIndex < hiIndex; makes the usage of midIndex + 1 valid
            int midIndex = (loIndex + hiIndex) / 2;
            if (target < matrix[midIndex][0]) {
                return binarySearchRowHead(matrix, loIndex, midIndex - 1, target);
            } else if (target > matrix[midIndex][0] && target < matrix[midIndex + 1][0]) {
                return binarySearch(matrix[midIndex], 1, matrix[midIndex].length - 1, target);
            } else if (target > matrix[midIndex + 1][0]) {
                return binarySearchRowHead(matrix, midIndex + 1, hiIndex, target);
            } else {
                return true;
            }
        }
    }

    public boolean binarySearch(int[] row, int loIndex, int hiIndex, int target) {
        if (loIndex <= hiIndex) {
            int mid = (loIndex + hiIndex) / 2;
            if (target > row[mid]) {
                return binarySearch(row, mid + 1, hiIndex, target);
            } else if (target < row[mid]) {
                return binarySearch(row, loIndex, mid - 1, target);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Search2DMatrix test = new Search2DMatrix();
        int[][] matrix = {{1, 3, 5}};
        test.searchMatrix(matrix, 5);
    }
}
