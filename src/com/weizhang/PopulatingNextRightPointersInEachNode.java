package com.weizhang;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Wei Zhang on 4/4/15.
 *
 * Given a binary tree

 struct TreeLinkNode {
 TreeLinkNode *left;
 TreeLinkNode *right;
 TreeLinkNode *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.

 Note:

 You may only use constant extra space.
 You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 For example,
 Given the following perfect binary tree,

       1
     /  \
    2    3
   / \  / \
  4  5  6  7

 After calling your function, the tree should look like:

       1 -> NULL
     /  \
    2 -> 3 -> NULL
   / \  / \
  4->5->6->7 -> NULL

 *
 */
public class PopulatingNextRightPointersInEachNode {

    /*
     * This solution is not using constant extra space
     */
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        List<TreeLinkNode> currentLevel = new ArrayList<TreeLinkNode>();
        currentLevel.add(root);

        List<TreeLinkNode> otherLevel;

        while (currentLevel.size() != 0) {
            otherLevel = new ArrayList<TreeLinkNode>();
            for (int i = 0; i < currentLevel.size(); i++) {
                TreeLinkNode node = currentLevel.get(i);
                node.next = i + 1 < currentLevel.size() ? currentLevel.get(i + 1) : null;
                if (node.left != null) {
                    otherLevel.add(node.left);
                }
                if (node.right != null) {
                    otherLevel.add(node.right);
                }
            }
            currentLevel = otherLevel;
        }


    }

    /**
     * Try to use constant extra space here
     * @param root the root node
     */
    public void connectII(TreeLinkNode root) {
        connectII(null, root, true);
    }

    private void connectII(TreeLinkNode parent, TreeLinkNode node, boolean isLeft) {
        if (node == null) return;

        if (parent != null) {
            if (isLeft) {
                node.next = parent.right;
            } else if (parent.next != null) {
                node.next = parent.next.left;
            }
        }
        connectII(node, node.left, true);
        connectII(node, node.right, false);

    }

    /**
     * connectII is recursive, still not constant
     * Third try
     */
    public void connectIII(TreeLinkNode root) {
        TreeLinkNode cur;
        TreeLinkNode nextLevel = root;
        while (nextLevel != null) {
            cur = nextLevel;
            // at each level, connects the children level
            while (cur != null &&
                    cur.left != null // checking for leaf nodes
                    ) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }

            nextLevel = nextLevel.left;

        }
    }
}
