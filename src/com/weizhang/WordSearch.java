package com.weizhang;

import java.util.Stack;

/**
 * Created by Wei Zhang on 4/28/15.
 *
 * Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

 For example,
 Given board =

 [
    ["ABCE"],
    ["SFCS"],
    ["ADEE"]
 ]
 word = "ABCCED", -> returns true,
 word = "SEE", -> returns true,
 word = "ABCB", -> returns false.
 *
 */
public class WordSearch {
    private char[][] board;
    private boolean aux[][];
    private Stack<Coordinate> stack;
    private String word;
    public boolean exist(char[][] board, String word) {
        // we need a stack for saving coordinates;
        // and an auxiliary matrix for saving visited characters
        // and do DFS, and back-tracking: pop coordinates from stack and re-mark visisted character to false
        this.board = board;
        int maxRow = board.length;
        int maxColumn = board[0].length;
        Coordinate.setBoundry(maxRow - 1, maxColumn - 1);
        aux = new boolean[maxRow][maxColumn];
        stack = new Stack<Coordinate>();
        this.word = word;

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxColumn; j++) {
                if (dfs(0, new Coordinate(i, j)))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(int cIndex, Coordinate coordinate) {
        char boardChar = board[coordinate.row][coordinate.column];
        char wordChar = word.charAt(cIndex);
        if (boardChar == wordChar) {
            if (cIndex + 1 == word.length())
                return true;
            pushCoordinate(coordinate);
            if (shouldVisitLeftCoordinate(coordinate)
                    && dfs(cIndex + 1, coordinate.left())) {
                return true;
            }
            if (shouldVisitRightCoordiante(coordinate)
                    && dfs(cIndex + 1, coordinate.right())) {
                return true;
            }
            if (shouldVisitUpCoordiante(coordinate)
                    && dfs(cIndex + 1, coordinate.up())) {
                return true;
            }
            if (shouldVisitDownCoordiante(coordinate)
                    && dfs(cIndex + 1, coordinate.down())) {
                return true;
            }
            popCoordinate();
        }
        return false;
    }

    private boolean shouldVisitLeftCoordinate(Coordinate coordinate) {
        return coordinate.leftExists() && ! aux[coordinate.row][coordinate.column - 1];
    }

    private boolean shouldVisitRightCoordiante(Coordinate coordinate) {
        return coordinate.rightExists() && ! aux[coordinate.row][coordinate.column + 1];
    }

    private boolean shouldVisitUpCoordiante(Coordinate coordinate) {
        return coordinate.upExists() && ! aux[coordinate.row - 1][coordinate.column];
    }

    private boolean shouldVisitDownCoordiante(Coordinate coordinate) {
        return coordinate.downExists() && ! aux[coordinate.row + 1][coordinate.column];
    }

    private void pushCoordinate(Coordinate coord) {
        aux[coord.row][coord.column] = true;
        stack.push(coord);
    }

    private void popCoordinate() {
        Coordinate coord = stack.pop();
        aux[coord.row][coord.column] = false;
    }


    static public class Coordinate {
        private static int maxRow;
        private static int maxColumn;
        private int row;
        private int column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        static public void setBoundry(int maxRow, int maxColumn) {
            Coordinate.maxRow = maxRow;
            Coordinate.maxColumn = maxColumn;
        }

        public boolean leftExists() {
            return this.column > 0;
        }

        public boolean rightExists() {
            return this.column < maxColumn;
        }

        public boolean upExists() {
            return this.row > 0;
        }

        public boolean downExists() {
            return this.row < maxRow;
        }

        public Coordinate left() {
            return new Coordinate(row, column - 1);
        }

        public Coordinate right() {
            return new Coordinate(row, column + 1);
        }

        public Coordinate up() {
            return new Coordinate(row - 1, column);
        }

        public Coordinate down() {
            return new Coordinate(row + 1, column);
        }
    }
}
