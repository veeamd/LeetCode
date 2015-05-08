package com.weizhang;

/**
 * Created by Wei Zhang on 5/7/15.
 */

public class TrieNode {
    static int Radix = 26;
    private int val;
    private TrieNode[] children;
    public TrieNode() {
        children = new TrieNode[Radix];
        val = 0;
    }

    public TrieNode(int val) {
        this.val = val;
        children = new TrieNode[Radix];
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public TrieNode addChildAt(int c) {
        assert c >= 0 && c <= 25 : "out of bound";
        TrieNode child = new TrieNode();
        children[c] = child;
        return child;
    }

    public TrieNode getChildAt(int c) {
        assert c >= 0 && c <= 25 : "out of bound";
        return children[c];
    }
}
