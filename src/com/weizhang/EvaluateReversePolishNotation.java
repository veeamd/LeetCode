package com.weizhang;

import java.util.Stack;

/**
 * Created by Wei Zhang on 4/25/15.
 *
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *          http://en.wikipedia.org/wiki/Reverse_Polish_notation

 Valid operators are +, -, *, /. Each operand may be an integer or another expression.

 Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> operands = new Stack<Integer>();
        for (String op : tokens) {
            if (isOperator(op)) {
                int operand2 = operands.pop();
                int operand1 = operands.pop();
                operands.push(doOperation(operand1, operand2, op));
            } else {
                operands.push(Integer.parseInt(op));
            }
        }
        return operands.pop();
    }

    public boolean isOperator(String op) {
        return (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"));
    }

    public int doOperation(int operand1, int operand2, String operator) {
        if (operator.equals("+")) {
            return operand1 + operand2;
        } else if (operator.equals("-")) {
            return operand1 - operand2;
        } else if (operator.equals("*")) {
            return operand1 * operand2;
        } else {
            return operand1 / operand2;
        }
    }
}
