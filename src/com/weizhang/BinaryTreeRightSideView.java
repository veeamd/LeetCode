package com.weizhang;

import apple.laf.JRSUIUtils;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 4/4/15.
 *
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.

 For example:
 Given the following binary tree,
     1            <---
   /   \
  2     3         <---
   \     \
    5     4       <---
   /
  6               <---
 You should return [1, 3, 4, 6].
 *
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        // preorder dfs goes to the left first,
        // add any node you encounter, if already exists, that's fine
        // because we goes to the right later, any thing comes later is on the right side
        // use preorder to initialize the list
        List<Integer> rightView = new ArrayList<Integer>();
        preOrderDFS(root, 0, rightView);
        return rightView;
    }

    private void preOrderDFS(TreeNode node, int level, List<Integer> view) {
        if (node == null) return;
        if (level < view.size())
            view.set(level, node.val);
        else
            view.add(level, node.val);
        preOrderDFS(node.left, level + 1, view);
        preOrderDFS(node.right, level + 1, view);
    }

    public static void main(String[] args) {
        BinaryTreeRightSideView test = new BinaryTreeRightSideView();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        System.out.print(test.rightSideView(root));

    }
}