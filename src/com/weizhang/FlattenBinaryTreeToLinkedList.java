package com.weizhang;

/**
 * Created by Wei Zhang on 4/5/15.
 *
 * Given a binary tree, flatten it to a linked list in-place.

 For example,
 Given

    1
   / \
  2   5
 / \   \
 3   4   6
 The flattened tree should look like:
 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
 *
 */
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        flattenAndReturnEndNode(root);
    }

    /**
     * flatten tree
     * @param root node to flatten
     * @return end of the flattened tree
     */
    private TreeNode flattenAndReturnEndNode(TreeNode root) {
        if (root == null) return null;

        TreeNode LEFT = root.left;
        TreeNode endOfLEFT = flattenAndReturnEndNode(LEFT);

        TreeNode RIGHT = root.right;
        TreeNode endOfRight = flattenAndReturnEndNode(RIGHT);

        if (LEFT != null) {
            root.right = LEFT;
            root.left = null;
            endOfLEFT.right = RIGHT;
        }

        if (endOfRight != null) {
            return  endOfRight;
        } else if (endOfLEFT != null) {
            return endOfLEFT;
        } else {
            return root;
        }
    }

    //TODO: Try iterative method next time.

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        left.left = new TreeNode(3);
        left.right = new TreeNode(4);

        TreeNode right = new TreeNode(5);
        right.left = new TreeNode(6);
        right.right = new TreeNode(7);

        root.left = left;
        root.right = right;

        FlattenBinaryTreeToLinkedList test = new FlattenBinaryTreeToLinkedList();
        test.flatten(root);
    }
}
