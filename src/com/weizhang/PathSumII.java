package com.weizhang;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wei Zhang on 3/26/15.
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

 For example:
 Given the below binary tree and sum = 22,
       5
      / \
     4   8
    /   / \
   11  13  4
  /  \    / \
 7    2  5   1

 return
 [
    [5,4,11,2],
    [5,8,4,5]
 ]
 *
 */
public class PathSumII {
    private List<List<Integer>> paths;
    private LinkedList<Integer> nodeQueue;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        paths = new ArrayList<List<Integer>>();
        nodeQueue = new LinkedList<Integer>();
        findSumPath(root, sum);
        return paths;
    }

    public void findSumPath(TreeNode root, int sum) {
        if (root == null) return;

        nodeQueue.add(root.val);

        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                List<Integer> onePath = (List<Integer>) nodeQueue.clone();
                paths.add(onePath);
            }
        } else {
            if (root.left != null) {
                findSumPath(root.left, sum - root.val);
            }
            if (root.right != null){
                findSumPath(root.right, sum - root.val);
            }
        }

        // remove the last anyway, clear the queue for the other branch
        nodeQueue.removeLast();
    }
}
