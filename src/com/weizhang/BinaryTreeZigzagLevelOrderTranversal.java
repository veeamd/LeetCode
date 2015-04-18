package com.weizhang;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei Zhang on 4/18/15.
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree {3,9,20,#,#,15,7},
     3
    / \
   9  20
     /  \
    15   7
 return its zigzag level order traversal as:
 [
  [3],
  [20,9],
  [15,7]
 ]
 confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


 OJ's Binary Tree Serialization:
 The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

 Here's an example:
    1
   / \
  2   3
     /
    4
     \
      5
 The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 *
 */
public class BinaryTreeZigzagLevelOrderTranversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<TreeNode> childrenStack = new Stack<TreeNode>();
        stack.push(root);
        boolean leftToRight = true; // order to push children nodes
        List<Integer> levelResult = new ArrayList<Integer>();
        while ( ! stack.empty() || ! childrenStack.empty()) {
            if (stack.empty()) {
                stack = childrenStack;
                childrenStack = new Stack<TreeNode>();
                leftToRight = ! leftToRight;
                result.add(levelResult);
                levelResult = new ArrayList<Integer>();
            }
            TreeNode node = stack.pop();
            levelResult.add(node.val);
            if (leftToRight) {
                if (node.left != null) childrenStack.push(node.left);
                if (node.right != null) childrenStack.push(node.right);
            } else {
                if (node.right != null) childrenStack.push(node.right);
                if (node.left != null) childrenStack.push(node.left);
            }

        }
        // add the last level
        result.add(levelResult);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        root.left = two;
        root.right = three;

        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        three.left = four;
        three.right = five;

        BinaryTreeZigzagLevelOrderTranversal test = new BinaryTreeZigzagLevelOrderTranversal();
        System.out.print(test.zigzagLevelOrder(root));
    }
}
