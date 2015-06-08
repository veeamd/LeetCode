package com.weizhang;

/**
 * Created by Wei Zhang on 6/7/15.
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 *
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode node, TreeNode min, TreeNode max) {
        if (node == null) return true;

        if (min != null && node.val <= min.val) return false;
        if (max != null && node.val >= max.val) return false;

        return isValidBST(node.left, min, node) && isValidBST(node.right, node, max);
    }


}