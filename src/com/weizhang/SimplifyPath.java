package com.weizhang;

import java.util.Stack;

/**
 * Created by Wei Zhang on 4/19/15.
 *
 * Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../../../c/", => "/c"
 *
 */
public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] components = path.split("/");
        Stack<String> componentStack = new Stack<String>();
        StringBuilder sb = new StringBuilder("/");
        if (components.length > 0) {
            for (String component : components) {
                component = component.trim();
                if (component.length() > 0) {
                    if ( ! component.equals(".")) {
                        if ( ! component.equals("..")) {
                            componentStack.push(component);
                        } else if ( ! componentStack.empty()){
                            componentStack.pop();
                        }
                    }
                }
            }

        }
        for (String component : componentStack) {
            sb.append(component);
            sb.append("/");
        }
        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        SimplifyPath test = new SimplifyPath();
        System.out.println(test.simplifyPath("/a/./b/../../..//../c/b/t/"));
    }
}
