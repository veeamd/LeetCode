package com.weizhang;

/**
 * Created by Wei Zhang on 5/6/15.
 *
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


 Note:
 A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 *
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[] rowsBit = new int[9];
        int[] colsBit = new int[9];

        for (int zoneRow = 0; zoneRow < 3; zoneRow++) {
            for (int zoneCol = 0; zoneCol < 3; zoneCol++) {
                int row, col, cellBit;
                int zone = 0;
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        row = zoneRow * 3 + y;
                        col = zoneCol * 3 + x;
                        char cell = board[row][col];
                        if (cell == '.')
                            continue;
                        cellBit = 1 << (cell - '0');
                        if ((zone & cellBit) == cellBit
                                ||
                                (rowsBit[row] & cellBit) == cellBit
                                ||
                                (colsBit[col] & cellBit) == cellBit) {
                            return false;
                        }

                        zone |= cellBit;
                        rowsBit[row] |= cellBit;
                        colsBit[col] |= cellBit;
                    }
                }
            }
        }
        return true;
    }
}
