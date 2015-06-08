package com.weizhang;

/**
 * Created by Wei Zhang on 6/7/15.
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

 For example, given the following matrix:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0

 Return 4.
 *
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;


        int edge = 0;
        int rowCount = matrix.length;
        int columnCount = matrix[0].length;

        int[][] A = new int[rowCount][columnCount];
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                A[r][c] = matrix[r][c] - '0';
            }
        }

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                int a_1_1 = 0, a_0_1 = 0, a_1_0 = 0;
                if (r - 1 >= 0 && c - 1 >= 0) {
                    a_1_1 = A[r - 1][c - 1];
                }
                if (r - 1 >= 0) {
                    a_0_1 = A[r - 1][c];
                }
                if (c - 1 >= 0) {
                    a_1_0 = A[r][c - 1];
                }
                if (a_1_1 >= 1 && A[r][c] == 1) {
                    A[r][c] = Math.min(a_1_1, Math.min(a_0_1, a_1_0)) + 1;
                }
                edge = Math.max(edge, A[r][c]);
            }
        }
        return (int)Math.pow(edge, 2);
    }

    public static void main(String[] args) {
        MaximalSquare test = new MaximalSquare();


        char[][] matrix1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'}
        };


        char[][] matrix2 = {
                "1111111111111101001111111100111011111111".toCharArray(),
                "1111011011111111101101111101111111111111".toCharArray(),
                "0111101011111101101101101111111111111111".toCharArray(),
                "0101101011111111111101111111010110111111".toCharArray(),
                "1111111111110111110110010111111111111111".toCharArray(),
                "1111111110110101111111111101011111101111".toCharArray(),
                "0110110101110011111111111111110111110101".toCharArray(),
                "0111111111111100111111100110011011010101".toCharArray(),
                "1111011110111111111011011011110101101011".toCharArray(),
                "1111111110111111111101101101110111101111".toCharArray(),
                "1110110011111111111100111111111111111111".toCharArray(),
                "1011111110111101111001110111111111111111".toCharArray(),
                "0110111111111111101111110111011111011111".toCharArray(),
                "1111111111111111011111111111110111111011".toCharArray(),
                "1111100111111110101100111111111111101111".toCharArray(),
                "1111101111111110111111011111111111011111".toCharArray(),
                "1111101111111111111111011001111110011111".toCharArray(),
                "1111110111111111011111111111110111110111".toCharArray(),
                "1011111111111111010111110010111110111111".toCharArray(),
                "1111110011111111111110111111111111111011".toCharArray(),
                "1111111111111111110111011111011111011011".toCharArray(),
                "1100011011111111111111011111011111111111".toCharArray(),
                "1111101011111111111101100101110011111111".toCharArray(),
                "1110010111111111111011011110111101111101".toCharArray(),
                "1111111111111101101111111111101111111111".toCharArray(),
                "1111111011111101111011111111111110111111".toCharArray(),
                "1110011111111110111011111111110111110111".toCharArray(),
                "1111111111111100111111010111111111110111".toCharArray(),
                "1111111111111111111111000111111111011101".toCharArray(),
                "1111110111111111111111111101100111011011".toCharArray(),
                "1111011011111101101101111110111111101111".toCharArray(),
                "1111111111011111111111111111111111111111".toCharArray(),
                "1111111111111111111111111111111111111111".toCharArray(),
                "1100011111111110111111111111101111111011".toCharArray(),
                "1111101111111101111010111111111111111111".toCharArray(),
                "0111111111110011111111110101011011111111".toCharArray(),
                "1011011111101111111111101111111111110011".toCharArray(),
                "1010111111111111111111111111111110011111".toCharArray(),
                "0111101111011111111111111111110111111111".toCharArray(),
                "0111111011111111011101111011101111111111".toCharArray(),
                "0111111111110101111011111101011001111011".toCharArray(),
                "1111111111111011110111111101111111101110".toCharArray(),
                "1111101111111100111111111110111111001111".toCharArray(),
                "1101101111110101111101111111100111010100".toCharArray(),
                "0110111111100111110010111110111011011101".toCharArray()
        };



        char[][] matrix3 = {
                {'1'}
        };

        test.maximalSquare(matrix1);
        test.maximalSquare(matrix2);
        test.maximalSquare(matrix3);

    }
}
