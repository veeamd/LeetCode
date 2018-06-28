package com.weizhang;

import java.util.Stack;

/**
 * Created by Wei Zhang on 6/21/15.
 *
 * Implement a basic calculator to evaluate a simple expression string.

 The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

 You may assume that the given expression is always valid.

 Some examples:
 "1 + 1" = 2
 " 2-1 + 2 " = 3
 "(1+(4+5+2)-3)+(6+8)" = 23
 Note: Do not use the eval built-in library function.
 *
 */
public class BasicCalculator {
    private String expression;
    private int position;
    public int calculate(String s) {
        expression = s;
        position = 0;

        boolean shouldEnque = true;
        Stack<String> numberStack = new Stack<String>();
        Stack<String> operatorStack = new Stack<String>();

        String ele = nextElement();
        while (ele != null) {
            if (shouldEnque && isNumber(ele)) {
                numberStack.push(ele);
            } else if (shouldEnque && isOperator(ele)) {
                operatorStack.push(ele);
                shouldEnque = false;
            } else if (shouldEnque && isOpenBracket(ele)) {
                numberStack.push(ele);
            } else if ( ! shouldEnque && isOpenBracket(ele)) {
                numberStack.push(ele);
                shouldEnque = true;
            } else if ( ! shouldEnque && isNumber(ele)) {
                String x = numberStack.pop();
                String y = ele;
                String op = operatorStack.pop();
                String z = doOperation(x, op, y);
                numberStack.push(z);
                shouldEnque = true;
            } else if (isClosingBracket(ele)) {
                String x = numberStack.pop();
                numberStack.pop(); // pop the (
                numberStack.push(x);
            }
            ele = nextElement();
        }

        while (!operatorStack.empty()) {
            // pop operator and do calcumation
        }

        return Integer.parseInt(numberStack.pop());
    }

    public String nextElement() {
        if (position >= expression.length())
            return null;
        String ele = "";
        char nextChar = expression.charAt(position);
        if (nextChar == ' ') {
            position++;
            return nextElement();
        } else if (nextChar == '+' || nextChar == '-' || nextChar == '(' || nextChar == ')') {
            position++;
            return ele + nextChar;
        } else {
            ele += nextChar;
            position++;
            while (position < expression.length() && expression.charAt(position) >= '0' && expression.charAt(position) <= '9') {
                ele += expression.charAt(position);
                position++;
            }
            return ele;
        }
    }

    public boolean hasNext() {
        return position < expression.length();
    }

    public boolean isNumber(String str) {
        char c = str.charAt(0);
        return c >= '0' && c <= '9';
    }

    public boolean isOperator(String str) {
        char c = str.charAt(0);
        return c == '+' || c == '-';
    }

    public boolean isOpenBracket(String str) {
        return str.charAt(0) == '(';
    }

    public boolean isClosingBracket(String str) {
        return str.charAt(0) == ')';
    }

    public String doOperation(String X, String operator, String Y) {
        int x = Integer.parseInt(X);
        int y = Integer.parseInt(Y);
        if (operator.equals("+")) {
            return Integer.toString(x + y);
        } else {
            return Integer.toString(x - y);
        }
    }

    public static void main(String[] args) {
        String expression = "     ( 1 + ( 4 + 5) ) - ( 3+8989)    ";
        BasicCalculator test = new BasicCalculator();
        test.calculate(expression);
    }
}
