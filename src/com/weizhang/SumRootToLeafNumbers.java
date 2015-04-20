package com.weizhang;

/**
 * Created by Wei Zhang on 4/19/15.
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

 An example is the root-to-leaf path 1->2->3 which represents the number 123.

 Find the total sum of all root-to-leaf numbers.

 For example,

   1
  / \
 2   3
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.

 Return the sum = 12 + 13 = 25.
 *
 */
public class SumRootToLeafNumbers {
    private int sum;

    public int sumNumbers(TreeNode root) {
        sumNumbersDFS(root, 0);
        return sum;
    }

    public void sumNumbersDFS(TreeNode node, int pathNum) {
        if (node == null) return;
        pathNum = pathNum * 10 + node.val;
        if (node.left == null && node.right == null) sum += pathNum;
        else {
            if (node.left != null) sumNumbersDFS(node.left, pathNum);
            if (node.right != null) sumNumbersDFS(node.right, pathNum);
        }
    }
}
