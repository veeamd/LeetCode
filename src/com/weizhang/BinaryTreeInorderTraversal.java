package com.weizhang;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei Zhang on 4/20/15.
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.

 For example:
 Given binary tree {1,#,2,3},
 1
  \
   2
  /
 3
 return [1,3,2].

 Note: Recursive solution is trivial, could you do it iteratively?
 *
 */
public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        // iteratively
        List<Integer> result = new ArrayList<Integer>();
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (node != null || ! stack.empty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }
}
