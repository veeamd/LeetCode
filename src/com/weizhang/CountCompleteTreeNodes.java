package com.weizhang;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Wei Zhang on 6/7/15.
 *
 * Given a complete binary tree, count the number of nodes.

 Definition of a complete binary tree from Wikipedia:
 In a complete binary tree every level, except possibly the last, is completely filled,
 and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 */
public class CountCompleteTreeNodes {
    /*
     *  Naive queue solution: will take 0(N) time and O(N) space.
    */
    public int countNodesI(TreeNode root) {
        TreeNode node = root;
        if (node == null) return 0;

        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(node);
        int count = 0;
        while ( ! queue.isEmpty()) {
            node = queue.poll();
            count++;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return count;
    }

    /*
      recursive solution
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        TreeMeta meta = new TreeMeta(root);
        if (meta.isFullTree()) {
            return (1 << meta.height()) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    public class TreeMeta {
        private int leftHeight;
        private int rightHeight;
        public TreeMeta(TreeNode root) {
            TreeNode left = root;
            TreeNode right = root;
            leftHeight = 0;
            rightHeight = 0;
            while (right != null) {
                left = left.left;
                right = right.right;
                leftHeight++;
                rightHeight++;
            }
            if (left != null) {
                leftHeight++;
            }
        }

        public boolean isFullTree() {
            return leftHeight == rightHeight;
        }

        public int height() {
            return leftHeight;
        }
    }

    public static void main(String[] args) {
        CountCompleteTreeNodes test = new CountCompleteTreeNodes();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        int count = test.countNodes(root);
    }
}
