package com.weizhang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei Zhang on 4/22/15.
 *
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

 For example,
 Given n = 3, your program should return all 5 unique BST's shown below.

 1         3     3      2      1
  \       /     /      / \      \
   3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3
 *
 */
public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        return generateSubtrees(1, n);
    }

    private List<TreeNode> generateSubtrees(int lo, int hi) {
        List<TreeNode> subtrees = new ArrayList<TreeNode>();
        if (lo > hi) {
            subtrees.add(null);  // ArrayList allow insert null into it. Amazingly, by using this feature, greatly simplify the code.
            return subtrees;
        }

        for (int i = lo; i <= hi; i++) {
            List<TreeNode> leftSubtrees = generateSubtrees(lo, i-1);
            List<TreeNode> rightSubtrees = generateSubtrees(i+1, hi);
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    subtrees.add(root);
                }
            }
        }
        return subtrees;
    }
}
