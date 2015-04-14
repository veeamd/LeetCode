package com.weizhang;

import apple.laf.JRSUIUtils;

/**
 * Created by Wei Zhang on 4/8/15.
 *
 * The diameter of a tree is the number of nodes on the longest path between two leaves in the tree. The diagram below shows a tree with diameter nine, the leaves that form the ends of a longest path are shaded (note that there is more than one path in each tree of length nine, but no path longer than nine nodes).

 In particular, note that the diameter of a tree T is the largest of the following quantities:
 the diameter of T's left subtree
 the diameter of T's right subtree
 the longest path between leaves that goes through the root of T
 Given the root node of the tree, return the diameter of the tree
 *
 */
public class DiameterOfTree {
    private int diameter;
    public int diameterOfTree(TreeNode root) {
        heightOfTree(root);
        return diameter;
    }

    public int heightOfTree(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = heightOfTree(node.left);
        int rightHeight = heightOfTree(node.right);
        int d = leftHeight + rightHeight + 1;
        if (d > diameter) {
            diameter = d;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode left1 = new TreeNode(7);
        root.left = left1;

        TreeNode right1 = new TreeNode(8);
        TreeNode right2 = new TreeNode(9);
        TreeNode right3 = new TreeNode(18);
        TreeNode right31 = new TreeNode(28);
        TreeNode right311 = new TreeNode(38);
        TreeNode right312 = new TreeNode(48);
        TreeNode right32 = new TreeNode(58);
        TreeNode right321 = new TreeNode(68);
        TreeNode right322 = new TreeNode(78);
        right1.right = right2;
        right2.right = right3;
        right3.left = right31;
        right3.right = right32;
        right31.left = right311;
        right31.right = right312;
        right32.left = right321;
        right32.right = right322;
        root.right = right1;

        TreeNode right21 = new TreeNode(100);
        right2.left = right21;

        TreeNode right41 = new TreeNode(200);
        TreeNode right42 = new TreeNode(201);
        right311.left = right41;
        right41.left = right42;

        TreeNode right51 = new TreeNode(300);
        right322.right = right51;

        DiameterOfTree test = new DiameterOfTree();
        test.diameterOfTree(root);
    }
}
