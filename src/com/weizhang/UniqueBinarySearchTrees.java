package com.weizhang;

/**
 * Created by Wei Zhang on 4/21/15.
 *
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

 For example,
 Given n = 3, there are a total of 5 unique BST's.

 1         3     3      2      1
  \       /     /      / \      \
   3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3

 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] nt = new int[n+1];
        nt[0] = 1; nt[1] = 1;
        if (n > 1) {
            for (int numberOfNodes = 2; numberOfNodes <= n; numberOfNodes++) {
                int subtotal = 0;
                for (int currentRootNode = 1; currentRootNode <= numberOfNodes / 2; currentRootNode++) {
                    subtotal += nt[currentRootNode - 1] * nt[numberOfNodes - currentRootNode];
                }
                subtotal *= 2;
                if (numberOfNodes % 2 != 0) {
                    subtotal += nt[numberOfNodes / 2] * nt[numberOfNodes / 2];
                }
                nt[numberOfNodes] = subtotal;
            }
        }
        return nt[n];
    }

    public static void main(String[] args) {
        UniqueBinarySearchTrees test = new UniqueBinarySearchTrees();
        System.out.println(test.numTrees(1));
        System.out.println(test.numTrees(2));
        System.out.println(test.numTrees(3));
        System.out.println(test.numTrees(4));
        System.out.println(test.numTrees(5));
    }
}
